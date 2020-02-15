package com.xseagull.boxing.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xseagull.boxing.pay.weixin.AppletsAccessToken;
import com.xseagull.boxing.pay.weixin.ServiceNumberJsapiTicket;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;

@Component
public class JsapiTicketTimer {

	@Scheduled(cron="0 0/60 * * * ?")
	public void insertSalaryList() {
		AppletsAccessToken serviceNumberAccessToken = WeiChartUtils.getServiceNumberAccessToken();
		if (serviceNumberAccessToken != null) {
			ServiceNumberJsapiTicket serviceNumberJsapiTicket = WeiChartUtils.getServiceNumberJsapiTicket(serviceNumberAccessToken.getAccess_token());
			StartupRunner.JSAPI_TICKET.put("jsapi_ticket", serviceNumberJsapiTicket.getTicket());
		}
	}

}