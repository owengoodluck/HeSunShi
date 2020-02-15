package com.xseagull.boxing.auth;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.config.TokenConfig;
import com.xseagull.boxing.features.dao.MerchantDao;
import com.xseagull.boxing.features.dao.MerchantStaffDao;
import com.xseagull.boxing.features.dao.PhoneCodeDao;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.dao.WalletDao;
import com.xseagull.boxing.features.entity.Merchant;
import com.xseagull.boxing.features.entity.MerchantLogin;
import com.xseagull.boxing.features.entity.MerchantStaff;
import com.xseagull.boxing.features.entity.PhoneCode;
import com.xseagull.boxing.features.entity.User;
import com.xseagull.boxing.features.entity.Wallet;
import com.xseagull.boxing.pay.weixin.AppletsLoginCertificate;
import com.xseagull.boxing.pay.weixin.ServiceNumberUserInfo;
import com.xseagull.boxing.pay.weixin.WeChatData;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.security.JwtTokenUtil;

import net.sf.json.JSONObject;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WalletDao walletDao;

	@Autowired
	private TokenConfig tokenConfig;
	
	@Autowired
	private MerchantDao merchantDao;
	
	@Autowired
	private MerchantStaffDao merchantStaffDao;
	
	@Autowired
	private PhoneCodeDao phoneCodeDao;

	@Override
	public ResultData refresh(String oldToken) {
		String token = oldToken.substring(tokenConfig.getTokenHead().length());
		String userId = jwtTokenUtil.getUserIdFromToken(token);
		String userName = jwtTokenUtil.getUsernameFromToken(token);
		if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(userName)) {
			return ResultData.success(jwtTokenUtil.generateToken(userId));
		} else {
			return ResultData.error(ResultCode.UNAUTHTOKEN);
		}
	}

	@Override
	public ResultData getUserPhone(String encrypted, String iv, String code, long userId) {
		byte[] encrypData = Base64.decodeBase64(encrypted);
		byte[] ivData = Base64.decodeBase64(iv);
		byte[] sessionKey = Base64.decodeBase64(WeiChartUtils.getAppletsLoginCertificate(code).getSession_key());
		try {

			String decrypt = decrypt(sessionKey, ivData, encrypData);
			JSONObject jsonobject = JSONObject.fromObject(decrypt);
			PhoneJiexi phoneJiexi = (PhoneJiexi) JSONObject.toBean(jsonobject, PhoneJiexi.class);
			userDao.updatePhone(userId + "", phoneJiexi.getPhoneNumber());
			return ResultData.success(decrypt);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		// 解析解密后的字符串
		return new String(cipher.doFinal(encData), "UTF-8");
	}

	@Override
	public ResultData getServiceNumberLoginCertificate(ServiceNumberUserInfo serviceNumberUserInfo) {
		return ResultData.success(serviceNumberUserInfo);
	}

	@Override
	public ResultData loginByOpenId(String openid) {
		User user = userDao.getUserByOpenid(openid);
		if (user != null) {
			AccessToken accessToken = jwtTokenUtil.generateToken(String.valueOf(user.getId()));
			accessToken.setAvatarUrl(user.getAvatar());
			accessToken.setNickName(user.getNickName());
			accessToken.setPhone(user.getPhone());
			return ResultData.success(accessToken);
		} else {
			return ResultData.success(null);
		}
	}

	/**
	 * 小程序登录
	 */
	@Override
	@Transactional
	public ResultData login(User user) {
		System.out.println("sdlfhslkdfhlskdfjh");
		AppletsLoginCertificate appletsLoginCertificate = WeiChartUtils.getAppletsLoginCertificate(user.getCode());
		if (appletsLoginCertificate.getErrcode() == 0) {
			if (StringUtils.isEmpty(appletsLoginCertificate.getUnionid())) {
				// 没有需要解析
				byte[] encrypData = Base64.decodeBase64(user.getEncrypted());
				byte[] ivData = Base64.decodeBase64(user.getIv());
				byte[] sessionKey = Base64.decodeBase64(appletsLoginCertificate.getSession_key());
				try {
					String decrypt = decrypt(sessionKey, ivData, encrypData);
					ObjectMapper mapper = new ObjectMapper();
					WeChatData weChatData = mapper.readValue(decrypt, WeChatData.class);
					appletsLoginCertificate.setUnionid(weChatData.getUnionId());
				} catch (Exception e) {
					e.printStackTrace();
					return ResultData.warn(ResultCode.JSON_RESOLVE_ERROR, "解析失败!");
				}
			}
			User oldUser = userDao.getUserByUnionid(appletsLoginCertificate.getUnionid());
			if (oldUser == null) {
				user.setQuoteOpenid(appletsLoginCertificate.getOpenid());
				user.setUnionid(appletsLoginCertificate.getUnionid());
				userDao.insert(user);
				AccessToken accessToken = jwtTokenUtil.generateToken(String.valueOf(user.getId()));
				Wallet wallet = new Wallet();
				wallet.setUserId(user.getId());
				walletDao.insert(wallet);
				return ResultData.success(accessToken);
			} else {
				user.setId(oldUser.getId());
				user.setQuoteOpenid(appletsLoginCertificate.getOpenid());
				userDao.update(user);
				AccessToken accessToken = jwtTokenUtil.generateToken(String.valueOf(user.getId()));
				accessToken.setPhone(oldUser.getPhone());
				return ResultData.success(accessToken);
			}
		} else {
			return ResultData.warn(ResultCode.INVALID_CODE);
		}
	}

	@Override
	public ResultData merchantLogin(MerchantLogin merchantLogin) {
		PhoneCode phoneCode = phoneCodeDao.findLastPhone(merchantLogin.getPhone());
		if (phoneCode == null) {
			return ResultData.warn(ResultCode.SMS_CODE_ERROR, "验证码错误");
		} else {
			Date now = new Date();
//			Date endTime = phoneCode.getEndTime();
//			if ((endTime.getTime() + 1000 * 60 * 5) > now.getTime()) {
//				if (phoneCode.getCode().equals(merchantLogin.getCode())) {
					Merchant merchant = merchantDao.selectByPhone(merchantLogin.getPhone());
					if (merchant == null) {
						return ResultData.warn(ResultCode.NOT_MERCHANT);
					} else {
						if (merchant.getStatus() == 1) {
							MerchantStaff merchantStaff = merchantStaffDao.selectByPhone(merchantLogin.getPhone());
							if (merchantStaff != null && 
									!StringUtils.isEmpty(merchantLogin.getUnionid()) &&
									!StringUtils.isEmpty(merchantLogin.getOpenid()) && 
									StringUtils.isEmpty(merchantStaff.getUnionid()) && 
									StringUtils.isEmpty(merchantStaff.getServiceNumberOpenid())) {
								merchantStaff.setUnionid(merchantLogin.getUnionid());
								merchantStaff.setServiceNumberOpenid(merchantLogin.getOpenid());
								merchantStaffDao.update(merchantStaff);
							}
							AccessToken accessToken = jwtTokenUtil.generateToken(String.valueOf(merchant.getId()));
							accessToken.setAvatarUrl(merchant.getLogo());
							accessToken.setNickName(merchant.getContact());
							accessToken.setPhone(merchant.getPhone());
							return ResultData.success(accessToken);
						} else {
							return ResultData.warn(ResultCode.APPLY, "商户认证审核中");
						}
					}
//				} else {
//					return ResultData.warn(ResultCode.SMS_CODE_ERROR, "验证码错误");
//				}
//			} else {
//				return ResultData.warn(ResultCode.SMS_CODE_ERROR, "验证码错误");
//			}
		}
	}
}
