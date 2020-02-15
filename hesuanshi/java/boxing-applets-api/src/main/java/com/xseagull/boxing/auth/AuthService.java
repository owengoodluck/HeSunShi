package com.xseagull.boxing.auth;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.MerchantLogin;
import com.xseagull.boxing.features.entity.User;
import com.xseagull.boxing.pay.weixin.ServiceNumberUserInfo;

/**
 * 权限服务接口
 */
public interface AuthService {
	/**
	 * 刷新新的token
	 */
	public ResultData refresh(String oldToken);

	/**
	 * 解析用户手机号
	 * 
	 * @param encrypted
	 * @param iv
	 * @param code
	 * @return
	 */
	public ResultData getUserPhone(String encrypted, String iv, String code, long userId);

	/**
	 * 微信服务号登陆
	 */
	public ResultData getServiceNumberLoginCertificate(ServiceNumberUserInfo serviceNumberUserInfo);

	/**
	 * 微信登录(根据openId)
	 */
	public ResultData loginByOpenId(String openid);

	/**
	 * 微信登录(改)
	 */
	public ResultData login(User user);
	
	/**
	 * 商户登录
	 */
	public ResultData merchantLogin(MerchantLogin merchantLogin);
}
