package com.xseagull.boxing.features.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.dao.WalletDao;
import com.xseagull.boxing.features.entity.User;
import com.xseagull.boxing.features.entity.Wallet;
import com.xseagull.boxing.features.entity.WalletRecord;
import com.xseagull.boxing.features.service.IWalletService;
import com.xseagull.boxing.pay.OrderPay;
import com.xseagull.boxing.pay.weixin.Prepay;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;

@Service
@Transactional
public class WalletService implements IWalletService {

	@Value("${orderNotify}")
	private String orderNotify;

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private UserDao userDao;

	/**
	 * 根据用户id查询用户钱包余额
	 */
	@Override
	public ResultData findWallet(long userId) {
		Wallet wallet = walletDao.findWallet(userId);
		return ResultData.success(wallet);
	}

	/**
	 * 充值
	 */
	@Override
	public ResultData recharge(WalletRecord walletRecord) {
		walletRecord.setBusinessNumber(String.valueOf(System.currentTimeMillis()));
		walletRecord.setType(0);
		walletDao.insertRecord(walletRecord);

		User user = userDao.findUserById(walletRecord.getUserId() + "");
		OrderPay orderPay = new OrderPay();
		orderPay.setNotifyUrl(orderNotify);
		orderPay.setOpenid(user.getOpenid());
		orderPay.setOutTradeNo(walletRecord.getBusinessNumber());
		orderPay.setBody("交易编号:" + walletRecord.getBusinessNumber());
		orderPay.setTotalAmount(walletRecord.getMoney().doubleValue());
		// 微信预订单
		Map<String, String> map = WeiChartUtils.orderPay(orderPay);
		if (map.get("result_code").equals("SUCCESS")) {
			String appid = map.get("appid");
			String nonce_str = map.get("nonce_str");
			String prepay_id = map.get("prepay_id");
			String timestamp = WeiChartUtils.getTenTimes();
			String extended = "prepay_id=" + prepay_id;
			String signType = "MD5";
			Map<String, String> reqMap = new HashMap<String, String>();
			reqMap.put("appId", appid);
			reqMap.put("nonceStr", nonce_str);
			reqMap.put("package", extended);
			reqMap.put("signType", signType);
			reqMap.put("timeStamp", timestamp);
			String sign = WeiChartUtils.getSign(reqMap);
			// 预下单成功，返回给前端的参数
			Prepay prepay = new Prepay();
			prepay.setAppid(appid);
			prepay.setExtended(extended);
			prepay.setNonce_str(nonce_str);
			prepay.setPrepay_id(prepay_id);
			prepay.setSign(sign);
			prepay.setTimestamp(timestamp);
			prepay.setOutTradeNo(orderPay.getOutTradeNo());
			return ResultData.success(prepay);
		} else {
			return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 我的钱包记录
	 */
	@Override
	public ResultData findRecord(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<WalletRecord> list = walletDao.findRecord(userId);
		PageInfo<WalletRecord> page = new PageInfo<WalletRecord>(list);
		return ResultData.success(page);
	}

}
