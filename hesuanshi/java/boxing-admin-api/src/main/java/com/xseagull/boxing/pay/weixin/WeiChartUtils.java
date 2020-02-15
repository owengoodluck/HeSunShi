package com.xseagull.boxing.pay.weixin;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xseagull.boxing.pay.FundTrans;
import com.xseagull.boxing.pay.OrderPay;
import com.xseagull.boxing.util.EncryptUtils;
import com.xseagull.boxing.util.HttpClientUtil;
import com.xseagull.boxing.util.IpUtils;
import com.xseagull.boxing.util.XMLBeanUtils;

public class WeiChartUtils {

	private static final Logger logger = LoggerFactory.getLogger(WeiChartUtils.class);

	/**
	 * 返回状态码
	 */
	public static final String ReturnCode = "return_code";

	/**
	 * 返回信息
	 */
	public static final String ReturnMsg = "return_msg";

	/**
	 * 业务结果
	 */
	public static final String ResultCode = "result_code";

	/**
	 * 预支付交易会话标识
	 */
	public static final String PrepayId = "prepay_id";

	/**
	 * 得到微信预付单的返回ID
	 * 
	 * @param orderId
	 *            商户自己的订单号
	 * @param totalFee
	 *            总金额 （分）
	 * @return
	 */
	public static Map<String, String> orderPay(OrderPay orderPay) {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.Applets_APPID);
		reqMap.put("mch_id", WeiChartConfig.MCH_ID);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("body", orderPay.getBody());
		reqMap.put("detail", orderPay.getSubject()); // 非必填
		reqMap.put("out_trade_no", orderPay.getOutTradeNo()); // 商户系统内部的订单号,
		reqMap.put("total_fee", changeToFen(orderPay.getTotalAmount())); // 订单总金额，单位为分
		reqMap.put("spbill_create_ip", getHostIp()); // 用户端实际ip
		reqMap.put("notify_url", orderPay.getNotifyUrl()); // 通知地址
		reqMap.put("trade_type", "JSAPI"); // 交易类型
		reqMap.put("openid", orderPay.getOpenid());// openid
		reqMap.put("sign", getSign(reqMap));

		String reqStr = XMLBeanUtils.map2XmlString(reqMap);
		String retStr = HttpClientUtil.postHttplient(WeiChartConfig.PrepayUrl, reqStr);
		System.out.println(reqStr);
		return XMLBeanUtils.readStringXmlOut(retStr);
	}

	/**
	 * 企业转账
	 * 
	 * @param openid
	 *            用户openid
	 * @param tradeId
	 *            商户订单
	 * @param amount
	 *            企业付款金额，单位为分
	 * @param realName
	 *            真实姓名
	 * @param desc
	 *            转账描述
	 * @return
	 */
	public static Map<String, String> transfers(HttpServletRequest request, FundTrans fundTrans) {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("mch_appid", WeiChartConfig.SERVICE_NUMBER_APPID);// 服务号appid
		reqMap.put("mchid", WeiChartConfig.MCH_ID);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("partner_trade_no", fundTrans.getOutBizNo()); // 商户订单号
		reqMap.put("openid", fundTrans.getAccount()); // 服务号openid
		reqMap.put("check_name", "NO_CHECK"); // 校验用户姓名选项(默认：FORCE_CHECK)
		reqMap.put("re_user_name", fundTrans.getRealName()); //收款用户真实姓名
		reqMap.put("amount", changeToFen(Double.valueOf(fundTrans.getTotalAmount()))); // 企业付款金额，单位为分
		reqMap.put("desc", fundTrans.getRemark()); // 转账备注
		reqMap.put("spbill_create_ip", IpUtils.getIpAddr(request)); // 用户端实际ip
		reqMap.put("sign", getSign(reqMap));
		String reqStr = XMLBeanUtils.map2XmlString(reqMap);
		String retStr = "";
		try {
			retStr = HttpClientUtil.postHttplientNeedSSL(WeiChartConfig.TRANSFERSUrl, reqStr,
					WeiChartConfig.TRANSFERS_FILE_PATH, WeiChartConfig.MCH_ID);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		System.out.println(retStr);
		return XMLBeanUtils.readStringXmlOut(retStr);
	}

	/**
	 * 关闭订单
	 * 
	 * @param orderId
	 *            商户自己的订单号
	 * @return
	 */
	public static Map<String, String> closeOrder(String orderId) {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.Applets_APPID);
		reqMap.put("mch_id", WeiChartConfig.MCH_ID);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("out_trade_no", orderId); // 商户系统内部的订单号,
		reqMap.put("sign", getSign(reqMap));

		String reqStr = XMLBeanUtils.map2XmlString(reqMap);
		String retStr = HttpClientUtil.postHttplient(WeiChartConfig.CloseOrderUrl, reqStr);
		return XMLBeanUtils.readStringXmlOut(retStr);
	}

	/**
	 * 查询订单
	 * 
	 * @param orderId
	 *            商户自己的订单号
	 * @return
	 */
	public static String getOrder(String orderId) {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.Applets_APPID);
		reqMap.put("mch_id", WeiChartConfig.MCH_ID);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("out_trade_no", orderId); // 商户系统内部的订单号,
		reqMap.put("sign", getSign(reqMap));

		String reqStr = XMLBeanUtils.map2XmlString(reqMap);
		String retStr = HttpClientUtil.postHttplient(WeiChartConfig.OrderUrl, reqStr);
		return retStr;
	}
	
	/**
	 * 退款
	 * 
	 * @param orderId 商户订单号
	 * @param refundId 退款单号
	 * @param totralFee 总金额（分）
	 * @param refundFee 退款金额（分）
	 * @param notifyUrl 异步通知
	 * @return
	 */
	public static Map<String, String> refundWei(String orderId, String refundId, String totralFee, String refundFee, String notifyUrl) {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.Applets_APPID);
		reqMap.put("mch_id", WeiChartConfig.MCH_ID);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("out_trade_no", orderId); // 商户系统内部的订单号,
		reqMap.put("out_refund_no", refundId); // 商户退款单号
		reqMap.put("total_fee", totralFee); // 总金额
		reqMap.put("refund_fee", refundFee); // 退款金额
		reqMap.put("notify_url", notifyUrl); //退款结果通知url
		reqMap.put("sign", getSign(reqMap));

		String reqStr = XMLBeanUtils.map2XmlString(reqMap);
		String retStr = "";
		try {
			retStr = HttpClientUtil.postHttplientNeedSSL(WeiChartConfig.RefundUrl, reqStr,
					WeiChartConfig.REFUND_FILE_PATH, WeiChartConfig.MCH_ID);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return XMLBeanUtils.readStringXmlOut(retStr);
	}

	/**
	 * 退款查询
	 * 
	 * @param refundId
	 *            退款单号
	 * @return
	 */
	public static Map<String, String> getRefundWeiInfo(String refundId) {
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("appid", WeiChartConfig.Applets_APPID);
		reqMap.put("mch_id", WeiChartConfig.MCH_ID);
		reqMap.put("nonce_str", getRandomString());
		reqMap.put("out_refund_no", refundId); // 商户退款单号
		reqMap.put("sign", getSign(reqMap));

		String reqStr = XMLBeanUtils.map2XmlString(reqMap);
		String retStr = HttpClientUtil.postHttplient(WeiChartConfig.RefundQueryUrl, reqStr);
		return XMLBeanUtils.readStringXmlOut(retStr);
	}

	/**
	 * 得到加密值
	 * 
	 * @param map
	 * @return
	 */
	public static String getSign(Map<String, String> map) {
		String[] keys = map.keySet().toArray(new String[0]);
		Arrays.sort(keys);
		StringBuffer reqStr = new StringBuffer();
		for (String key : keys) {
			String v = map.get(key);
			if (v != null && !v.equals("")) {
				reqStr.append(key).append("=").append(v).append("&");
			}
		}
		// reqStr.append("key").append("=").append(WeiChartConfig.AppSercret);

		reqStr.append("key").append("=").append(WeiChartConfig.MCH_KEY);

		// MD5加密
		return EncryptUtils.md5Encode(reqStr.toString()).toUpperCase();
	}

	/**
	 * 得到10 位的时间戳 如果在JAVA上转换为时间要在后面补上三个0
	 * 
	 * @return
	 */
	public static String getTenTimes() {
		String t = new Date().getTime() + "";
		t = t.substring(0, t.length() - 3);
		return t;
	}

	/**
	 * 得到随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString() {
		int length = 32;
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(62);// [0,62)
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 将金额转换成分
	 * 
	 * @param fee
	 *            元格式的
	 * @return 分
	 */
	public static String changeToFen(Double fee) {
		String priceStr = "";
		if (fee != null) {
			int p = (int) (fee * 100); // 价格变为分
			priceStr = Integer.toString(p);
		}
		return priceStr;
	}

	/**
	 * 得到本地机器的IP
	 * 
	 * @return
	 */
	private static String getHostIp() {
		String ip = "";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
		}
		return ip;
	}

	/**
	 * 微信回调成功后 将 xml 转换为 WechatNotify 对象
	 * 
	 * @param request
	 * @return WechatNotify 对象
	 */
	public static WechatNotify getNotifyBean(HttpServletRequest request) {
		try {
			DataInputStream in = new DataInputStream(request.getInputStream());
			byte[] dataOrigin = new byte[request.getContentLength()];
			// 根据长度，将消息实体的内容读入字节数组dataOrigin中
			in.readFully(dataOrigin);
			// 关闭数据流
			in.close();
			// 从字节数组中得到表示实体的字符串
			String xml = new String(dataOrigin);
			// 将 xml 转换为 WechatNotify 对象
			Object object = XMLBeanUtils.xmlToBean(WechatNotify.class, xml);
			if (object != null && object instanceof WechatNotify) {
				WechatNotify notify = (WechatNotify) object;
				return notify;
			} else {
				return null;
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	/**
	 * 微信退款通知回调成功后 将 xml 转换为 WechatNotify 对象
	 * 
	 * @param request
	 * @return WechatNotify 对象
	 */
	public static WechatRefundNotify getRefundNotifyBean(HttpServletRequest request) {
		try {
			DataInputStream in = new DataInputStream(request.getInputStream());
			byte[] dataOrigin = new byte[request.getContentLength()];
			// 根据长度，将消息实体的内容读入字节数组dataOrigin中
			in.readFully(dataOrigin);
			// 关闭数据流
			in.close();
			// 从字节数组中得到表示实体的字符串
			String xml = new String(dataOrigin);
			// 将 xml 转换为 WechatNotify 对象
			Object object = XMLBeanUtils.xmlToBean(WechatRefundNotify.class, xml);
			if (object != null && object instanceof WechatRefundNotify) {
				WechatRefundNotify notify = (WechatRefundNotify) object;
				return notify;
			} else {
				return null;
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	/**
	 * 获取小程序access_token
	 */
	public static AppletsAccessToken getAppletsAccessToken() {
		AppletsAccessToken accessToken = new AppletsAccessToken();
		try {
			String url = WeiChartConfig.APPLETS_ACCESS_TOKEN.replace("APPID", WeiChartConfig.Applets_APPID).replace("SECRET",
					WeiChartConfig.APPLETS_SERCRET);
			String result = HttpClientUtil.doGet(url);
			if (!StringUtils.isEmpty(result)) {
				ObjectMapper mapper = new ObjectMapper();
				accessToken = mapper.readValue(result, AppletsAccessToken.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	/**
	 * 获取小程序登录凭证
	 */
	public static AppletsLoginCertificate getAppletsLoginCertificate(String code) {
		AppletsLoginCertificate appletsLoginCertificate = new AppletsLoginCertificate();
		try {
			String url = WeiChartConfig.APPLETS_LOGIN_CERTIFICATE.replace("APPID", WeiChartConfig.Applets_APPID)
					.replace("SECRET", WeiChartConfig.APPLETS_SERCRET).replace("CODE", code);
			String result = HttpClientUtil.doGet(url);
			System.out.println(result);
			if (!StringUtils.isEmpty(result)) {
				ObjectMapper mapper = new ObjectMapper();
				appletsLoginCertificate = mapper.readValue(result, AppletsLoginCertificate.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appletsLoginCertificate;
	}
	
	/**
	 * 获取服务号access_token
	 */
	public static AppletsAccessToken getServiceNumberAccessToken() {
		AppletsAccessToken accessToken = new AppletsAccessToken();
		try {
			String url = WeiChartConfig.APPLETS_ACCESS_TOKEN.replace("APPID", WeiChartConfig.SERVICE_NUMBER_APPID).replace("SECRET",
					WeiChartConfig.SERVICE_NUMBER__SERCRET);
			String result = HttpClientUtil.doGet(url);
			if (!StringUtils.isEmpty(result)) {
				ObjectMapper mapper = new ObjectMapper();
				accessToken = mapper.readValue(result, AppletsAccessToken.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	/**
	 * 获取服务号票据
	 */
	public static ServiceNumberJsapiTicket getServiceNumberJsapiTicket(String accessToken) {
		ServiceNumberJsapiTicket serviceNumberJsapiTicket = new ServiceNumberJsapiTicket();
		try {
			String url = WeiChartConfig.JSAPI_TICKET.replace("ACCESS_TOKEN", accessToken);
			String result = HttpClientUtil.doGet(url);
			if (!StringUtils.isEmpty(result)) {
				ObjectMapper mapper = new ObjectMapper();
				serviceNumberJsapiTicket = mapper.readValue(result, ServiceNumberJsapiTicket.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceNumberJsapiTicket;
	}
	
	/**
	 * 获取服务号登录凭证
	 */
	public static ServiceNumberLoginCertificate getServiceNumberLoginCertificate(String code) {
		ServiceNumberLoginCertificate serviceNumberLoginCertificate = new ServiceNumberLoginCertificate();
		try {
			String url = WeiChartConfig.SERVICE_NUMBER__LOGIN_CERTIFICATE.replace("APPID", WeiChartConfig.SERVICE_NUMBER_APPID).
					replace("SECRET",WeiChartConfig.SERVICE_NUMBER__SERCRET).replace("CODE", code);
			String result = HttpClientUtil.doGet(url);
			if (!StringUtils.isEmpty(result)) {
				ObjectMapper mapper = new ObjectMapper();
				serviceNumberLoginCertificate = mapper.readValue(result, ServiceNumberLoginCertificate.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceNumberLoginCertificate;
	}
	/**
	 * 获取服务号拉取用户信息(需scope为 snsapi_userinfo)
	 */
	public static ServiceNumberUserInfo getServiceNumberUserInfo(String accessToken, String openid) {
		ServiceNumberUserInfo serviceNumberUserInfo = new ServiceNumberUserInfo();
		try {
			String url = WeiChartConfig.SERVICE_NUMBER_USERINFO.replace("ACCESS_TOKEN", accessToken).replace("OPENID",openid);
			String result = HttpClientUtil.doGet(url);
			if (!StringUtils.isEmpty(result)) {
				ObjectMapper mapper = new ObjectMapper();
				serviceNumberUserInfo = mapper.readValue(result, ServiceNumberUserInfo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceNumberUserInfo;
	}
}
