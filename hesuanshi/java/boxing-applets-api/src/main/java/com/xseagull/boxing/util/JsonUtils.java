package com.xseagull.boxing.util;

import java.util.Map;

import net.sf.json.JSONObject;
/**
 * 发给微信服务消息的json
 */
public class JsonUtils {
	/**
	 * 小程序json
	 * @param touser 用户openid
	 * @param templateId 模板id
	 * @param fromId 前端穿的form_id
	 * @param m 参数
	 * @return
	 */
	public static String getAppletsJsonString(String touser, String templateId, String page, String formId, Map<String,TemplateData> m) {
		WxAppletsTemplate t = new WxAppletsTemplate();
		t.setTouser(touser);
		t.setTemplate_id(templateId);
		t.setPage(page);
		t.setForm_id(formId);
		t.setData(m);
		return JSONObject.fromObject(t).toString();
	}
	
	/**
	 * 服务号json
	 * @param touser 用户openid
	 * @param templateId 模板id
	 * @param m 参数
	 * @return
	 */
	public static String getServiceNumberJsonString(String touser, String templateId, Map<String,TemplateData> m) {
		WxServiceNumberTemplate t = new WxServiceNumberTemplate();
		t.setTouser(touser);
		t.setTemplate_id(templateId);
		t.setData(m);
		return JSONObject.fromObject(t).toString();
	}
}
