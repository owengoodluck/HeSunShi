package com.xseagull.boxing.pay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.features.dao.OrderDao;
import com.xseagull.boxing.features.dao.RefundDao;
import com.xseagull.boxing.pay.weixin.WechatRefundInfoNotify;
import com.xseagull.boxing.pay.weixin.WechatRefundNotify;
import com.xseagull.boxing.pay.weixin.WeiChartConfig;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.AESUtils;

@RestController
@RequestMapping("/pay")
public class PayController {
	@Value("${orderNotify}")
	private String orderNotify;
	
	@Autowired
	private RefundDao refundDao;

	@Autowired
	private OrderDao orderDao;

	/**
	  * 微信订单退款异步通知
	*/
	@PostMapping("/refundWeiNotifyUrl")
	public String refundWeiNotifyUrl(HttpServletRequest request){
		// 从 request 对象中获取 WechatNotify 对象
		WechatRefundNotify notify = WeiChartUtils.getRefundNotifyBean(request);
		// 如果 notify 对象不为空 并且 result_code 和 return_code 都为 'SUCCESS' 则表示支付成功
		if (notify != null &&  notify.getReturn_code().equals("SUCCESS")) {
			WechatRefundInfoNotify wechatRefundInfoNotify = AESUtils.decrypt(notify.getReq_info());
			// SUCCESS CHANGE REFUNDCLOSE
			System.out.println("微信订单退款异步通知成功" + wechatRefundInfoNotify.getOut_refund_no());
			if (wechatRefundInfoNotify != null &&  wechatRefundInfoNotify.getRefund_status().equals("SUCCESS")) {
				//退款成功 
				refundDao.updateStatusByrefundNumber(wechatRefundInfoNotify.getOut_refund_no(), 1);
				orderDao.updateStatusByorderDetailId(wechatRefundInfoNotify.getOut_refund_no());		
			} else {
				//退款失败
				refundDao.updateStatusByrefundNumber(wechatRefundInfoNotify.getOut_refund_no(), 2);
			}
			return WeiChartConfig.NOTIFY_SUCCESS;
		} else {
			System.out.println("微信订单退款异步通知失败");
			return WeiChartConfig.NOTIFY_FAIL;
		}
	}
}
