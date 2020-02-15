package com.xseagull.boxing.features.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.MerchantDao;
import com.xseagull.boxing.features.dao.PhoneCodeDao;
import com.xseagull.boxing.features.entity.Merchant;
import com.xseagull.boxing.features.entity.PhoneCode;
import com.xseagull.boxing.features.service.IPhoneCodeService;
import com.xseagull.boxing.util.SmsUtils;

@Service
public class PhoneCodeService implements IPhoneCodeService {
	@Autowired
	private PhoneCodeDao phoneCodeDao;
	
	@Autowired
	private MerchantDao merchantDao;

	@Override
	public ResultData sendTextCode(String phone) {
		String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
		SendSmsResponse sendSmsResponse = SmsUtils.sendSms(phone, code);
		if (sendSmsResponse == null) {
			return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
		} else {
			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				PhoneCode phoneCode = new PhoneCode();
				phoneCode.setCode(code);
				phoneCode.setPhone(phone);
				Date now = new Date();
				phoneCode.setEndTime(new Date(now.getTime() + 1000 * 60 * 5));
				phoneCodeDao.insert(phoneCode);
				return ResultData.success(null);
			} else {
				return ResultData.warn(ResultCode.SMS_CODE_ERROR, "请求太频繁，请稍后再试!");
			}
		}
	}

	// 根据手机号码查询验证码
	@Override
	public ResultData findLastCode(String phone) {
		PhoneCode code2 = phoneCodeDao.findLastPhone(phone);
		if (code2 != null) {
			Date now1 = new Date();
			Date endTime = code2.getEndTime();
			if ((endTime.getTime() + 1000 * 60 * 5) > now1.getTime()) {
				return ResultData.success(code2.getCode());
			} else {
				return ResultData.warn(ResultCode.SMS_CODE_ERROR, "您的验证码已经过期!");
			}
		} else {
			return ResultData.warn(ResultCode.SMS_CODE_ERROR, "号码输入正确或还未获取验证码!");
		}
	}

	@Override
	public ResultData sendMerchantTextCode(String phone) {
		Merchant merchant = merchantDao.selectByPhone(phone);
		if (merchant == null) {
			return ResultData.warn(ResultCode.NOT_MERCHANT);
		} else {
			if (merchant.getStatus() == 1) {
				String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
				SendSmsResponse sendSmsResponse = SmsUtils.sendSms(phone, code);
				if (sendSmsResponse == null) {
					return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
				} else {
					if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
						PhoneCode phoneCode = new PhoneCode();
						phoneCode.setCode(code);
						phoneCode.setPhone(phone);
						Date now = new Date();
						phoneCode.setEndTime(new Date(now.getTime() + 1000 * 60 * 5));
						phoneCodeDao.insert(phoneCode);
						return ResultData.success(null);
					} else {
						return ResultData.warn(ResultCode.SMS_CODE_ERROR, "请求太频繁，请稍后再试!");
					}
				}
			} else {
				return ResultData.warn(ResultCode.APPLY, "商户认证审核中");
			}
		}
	}
}
