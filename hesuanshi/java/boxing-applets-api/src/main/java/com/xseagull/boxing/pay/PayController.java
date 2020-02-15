package com.xseagull.boxing.pay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.pay.weixin.WechatNotify;
import com.xseagull.boxing.pay.weixin.WeiChartConfig;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;

@RestController
@RequestMapping("/pay")
public class PayController {
	@Autowired
	private IPayService payService;

	@Value("${orderNotify}")
	private String orderNotify;


	/**
	 * 订单支付同步通知
	 */
	@PostMapping("/orderNotify")
	public ResultData orderNotify(String outTradeNo) {
		return payService.orderNotify(outTradeNo);
	}

	/**
	 * 微信订单支付异步通知
	 */
	@PostMapping("/orderWxPayNotify")
	public String orderWxPayNotify(HttpServletRequest request) {
		System.out.println("11111111111111111111111111111111");
		// 从 request 对象中获取 WechatNotify 对象
		WechatNotify notify = WeiChartUtils.getNotifyBean(request);
		// 如果 notify 对象不为空 并且 result_code 和 return_code 都为 'SUCCESS' 则表示支付成功
		if (notify != null && notify.getResult_code().equals("SUCCESS") && notify.getReturn_code().equals("SUCCESS")) {
			System.out.println("微信订单支付异步通知成功" + notify.getOut_trade_no());
			payService.orderNotify(notify.getOut_trade_no());
			return WeiChartConfig.NOTIFY_SUCCESS;
		} else {
			System.out.println("微信订单支付异步通知失败");
			return WeiChartConfig.NOTIFY_FAIL;
		}
	}
}
