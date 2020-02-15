package com.xseagull.boxing.pay.weixin;

public class WeiChartConfig {

	/**
	 * 预支付请求地址
	 */
	public static final String PrepayUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	/**
	 * 查询订单地址
	 */
	public static final String OrderUrl = "https://api.mch.weixin.qq.com/pay/orderquery";

	/**
	 * 关闭订单地址
	 */
	public static final String CloseOrderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";

	/**
	 * 申请退款地址
	 */
	public static final String RefundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	/**
	 * 查询退款地址
	 */
	public static final String RefundQueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery";

	/**
	 * 下载账单地址
	 */
	public static final String DownloadBillUrl = "https://api.mch.weixin.qq.com/pay/downloadbill";

	/**
	 * 企业付款地址
	 */
	public static final String TRANSFERSUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";

	/**
	 * 小程序登录凭证
	 */
	public static final String APPLETS_LOGIN_CERTIFICATE = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=CODE&grant_type=authorization_code";

	/**
	 * 服务号登录凭证
	 */
	public static final String SERVICE_NUMBER__LOGIN_CERTIFICATE = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	/**
	 * 获取小程序及服务号access_token地址
	 */
	public static final String APPLETS_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";

	/**
	 * 服务号拉取用户信息(需scope为 snsapi_userinfo)
	 */
	public static final String SERVICE_NUMBER_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * 获取服务号票据地址
	 */
	public static final String JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	/**
	 * 小程序APPID
	 */

	public static final String Applets_APPID = "wx3da9e50b5d2564bf";

	/**
	 * 小程序SERCRET
	 */
	public static final String APPLETS_SERCRET = "a9f42126fea0e988854e224bd7098de1";

	/**
	 * 服务号APPID
	 */
	public static final String SERVICE_NUMBER_APPID = "wx06895c8a970b67f7";

	/**
	 * 服务号SERCRET
	 */
	public static final String SERVICE_NUMBER__SERCRET = "35e9aa742250c260de2ef3d0aa03441e";

	/**
	 * 商户账户 获取支付能力后，从邮件中得到
	 */
	public static final String MCH_ID = "1501259351";

	/**
	 * 微信商户秘钥key
	 */
	public static final String MCH_KEY = "nrt35i5w2vflbqjzklpnw3nmb24j0nve";

	/**
	 * 退款需要证书文件，证书文件的地址
	 */
	public static final String REFUND_FILE_PATH = "";

	/**
	 * 企业转账需要证书文件，证书文件的地址
	 */
	public static final String TRANSFERS_FILE_PATH = "cert/apiclient_cert.p12";

	/**
	 * 异步通知成功返回
	 */
	public static final String NOTIFY_SUCCESS = "<xml>\n<return_code><![CDATA[SUCCESS]]></return_code>\n<return_msg><![CDATA[OK]]></return_msg>\n</xml>";

	/**
	 * 异步通知失败返回
	 */
	public static final String NOTIFY_FAIL = "<xml>\n<return_code><![CDATA[FAIL]]></return_code>\n<return_msg><![CDATA[ERROR]]></return_msg>\n</xml>";
	/**
	 * 小程序发送模板消息地址
	 */
	public static final String SEND_APPLETS_MSG = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
	/**
	 * 服务号发送模板消息地址
	 */
	public static final String SEND_SERVICE_NUMBER_MSG = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
}
