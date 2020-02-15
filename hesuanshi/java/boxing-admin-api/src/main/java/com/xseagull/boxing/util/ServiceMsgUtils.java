package com.xseagull.boxing.util;

import java.util.Map;

import com.xseagull.boxing.pay.weixin.WeiChartConfig;

/**
 * 服务通知
 */
public class ServiceMsgUtils {

	/**
	 * 给微信小程序发送服务通知
	 * 
	 * @param touser
	 *            用户openid
	 * @param templateId
	 *            模板id
	 * @param page
	 *            跳转页面
	 * @param formId
	 *            表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
	 * @param m
	 *            参数
	 * @return
	 */
	public static String sendAppletsMsg(String accessToken, String touser, String templateId, String page,
			String formId, Map<String, TemplateData> m) {
		String rs = null;
		try {

			String param = JsonUtils.getAppletsJsonString(touser, templateId, page, formId, m);
			rs = HttpUtil.sendPost(WeiChartConfig.SEND_APPLETS_MSG.replace("ACCESS_TOKEN", accessToken), param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 给微信服务号发送服务通知
	 * 
	 * @param touser
	 *            用户openid
	 * @param templateId
	 *            模板id(d0lJ2gW5UTvzVIwViMvTQ9OW0GjkqcLByEXYY-1lhtc)
	 * @param url
	 *            跳转链接
	 * @param m
	 *            参数
	 * @return
	 */
	public static String sendServiceNumberMsg(String accessToken, String touser, String templateId, String url,
			Map<String, TemplateData> m) {
		String rs = null;
		try {

			String param = JsonUtils.getServiceNumberJsonString(touser, templateId, url, m);
			rs = HttpUtil.sendPost(WeiChartConfig.SEND_SERVICE_NUMBER_MSG.replace("ACCESS_TOKEN", accessToken), param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
