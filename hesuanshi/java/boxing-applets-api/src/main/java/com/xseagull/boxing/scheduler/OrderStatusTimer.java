package com.xseagull.boxing.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xseagull.boxing.features.dao.MerchantOrderDao;
import com.xseagull.boxing.features.dao.OrderDao;
import com.xseagull.boxing.features.entity.MerchantOrder;
import com.xseagull.boxing.features.vo.CustomOrderVO;
import com.xseagull.boxing.util.DateUtils;

@Component
public class OrderStatusTimer {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private MerchantOrderDao merchantOrderDao;

	/**
	 * 发货超过15天改为待评价
	 */
	// @Scheduled(cron = "0/4 * * * * ? ") // 间隔5秒执行
	@Scheduled(cron = "0 0 1 * * ?") // 每天凌晨一点
	public void updateReceiptStatus() {
		List<CustomOrderVO> list = orderDao.findAllOrderByStatus(2);
		for (CustomOrderVO customOrderVO : list) {
			int days = DateUtils.getDayDate(customOrderVO.getShipTime());
			if (days >= 15) {
				orderDao.updateOrderAndDetail(customOrderVO.getId());
			}
		}
	}

	/**
	 * 派单的超过6小时未确认自动驳回
	 */
	// @Scheduled(cron = "0 0 2 * * ?") // 每天凌晨2点
	@Scheduled(cron = "0 0/30 * * * ?")
	public void updateMerchantOrderStatus() {
		List<MerchantOrder> list = merchantOrderDao.findMerchantOrderByStatus(0);
		for (MerchantOrder merchantOrder : list) {
			int hour = DateUtils.getHourDate(merchantOrder.getCreateTime());
			if (hour >= 6) {
				merchantOrderDao.updateStatus(merchantOrder.getId(), 2, "超时未处理,自动驳回");
			}
		}
	}
}
