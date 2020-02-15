package com.xseagull.boxing.scheduler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.xseagull.boxing.pay.weixin.AppletsAccessToken;
import com.xseagull.boxing.pay.weixin.ServiceNumberJsapiTicket;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;

/**
 * 服务启动执行
 */
@Component
public class StartupRunner implements CommandLineRunner {
	
	public static final Map<String,String> JSAPI_TICKET = new HashMap<String,String>();

	@Override
	public void run(String... arg0) throws Exception {
		AppletsAccessToken serviceNumberAccessToken = WeiChartUtils.getServiceNumberAccessToken();
		if (serviceNumberAccessToken != null) {
			ServiceNumberJsapiTicket serviceNumberJsapiTicket = WeiChartUtils.getServiceNumberJsapiTicket(serviceNumberAccessToken.getAccess_token());
			JSAPI_TICKET.put("jsapi_ticket", serviceNumberJsapiTicket.getTicket());
		}
	}
}
