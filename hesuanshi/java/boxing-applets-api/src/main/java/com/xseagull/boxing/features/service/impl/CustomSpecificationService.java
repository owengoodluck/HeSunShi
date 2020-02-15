package com.xseagull.boxing.features.service.impl;

import java.util.List;

import com.xseagull.boxing.constant.Constants;
import com.xseagull.boxing.features.dao.OrderDetailDao;
import com.xseagull.boxing.features.dao.ResourceFileDao;
import com.xseagull.boxing.features.dao.SysConfigDao;
import com.xseagull.boxing.features.entity.CustomOrderDetail;
import com.xseagull.boxing.features.entity.ResourceFile;
import com.xseagull.boxing.features.entity.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.CustomSpecificationDao;
import com.xseagull.boxing.features.entity.CustomSpecification;
import com.xseagull.boxing.features.service.ICustomSpecificationService;

@Service
@Transactional
public class CustomSpecificationService implements ICustomSpecificationService {

	@Autowired
	private CustomSpecificationDao customSpecificationDao;

	@Autowired
	private OrderDetailDao orderDetailDao;

	@Autowired
	private SysConfigDao sysConfigDao;
	
	@Autowired
	private ResourceFileDao resourceFileDao;

	@Override
	public ResultData insert(CustomSpecification customSpecification) {
		customSpecification.setCustomNumber(String.valueOf(System.currentTimeMillis()));
		customSpecificationDao.insert(customSpecification);
		return ResultData.success(customSpecification);
	}

	@Override
	public ResultData update(CustomSpecification customSpecification) {
		customSpecificationDao.update(customSpecification);
		return ResultData.success(customSpecification);
	}

	/**
	 * 分页方案
	 */
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, Integer type, long userId) {
		if(type == 3 ){
			return queryByPage4OrderAdvancePayment( pageNo,  pageSize,  userId);
		}
		if(type == 4 ){
			return queryByPage4FullPay( pageNo,  pageSize,  userId);
		}
		PageHelper.startPage(pageNo, pageSize);
		List<CustomSpecification> list = customSpecificationDao.queryByPage(type, userId);
		String advancePaymentPrice = getAdvancePaymentPriceConfig();
		int advancePaymentPriceInt = Integer.valueOf(advancePaymentPrice);
		list.forEach(e -> e.setAdvancePaymentPrice(advancePaymentPriceInt));
		for(CustomSpecification customSpecification : list) {
			ResourceFile resourceFile = findResourceByTypeAndNumber("3", customSpecification.getCustomNumber());
			if(resourceFile != null) {
				customSpecification.setCustomImage(resourceFile.getFile());
			}
		}

		PageInfo<CustomSpecification> page = new PageInfo<CustomSpecification>(list);
		return ResultData.success(page);

	}
	
	private ResourceFile findResourceByTypeAndNumber(String type, String number) {
		ResourceFile resourceFile = resourceFileDao.findResourceByTypeAndNumber(type, number);
		return resourceFile;
	}

	/**
	 * 分页方案: 查询已预付
	 * 从支付(order_pay)中查询
	 */
	private ResultData queryByPage4OrderAdvancePayment(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		/**
		 * 更加订单号，去查是否已支付的；
		 */
		
		/**
		List<OrderDetailCustomSpecification> list = this.orderDetailCustomSpecificationDao.queryByPage4AdvancePayment( userId);
		PageInfo<OrderDetailCustomSpecification> page = new PageInfo<OrderDetailCustomSpecification>(list);
		return ResultData.success(page);
		*/
		List<CustomOrderDetail> list = this.orderDetailDao.getCompleteAdvancePaymentList();
		PageInfo<CustomOrderDetail> page = new PageInfo<CustomOrderDetail>(list);
		return ResultData.success(page);
	}

	/**
	 * 分页方案: 查询已下单
	 */
	private ResultData queryByPage4FullPay(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		/**
		List<OrderDetailCustomSpecification> list = this.orderDetailCustomSpecificationDao.queryByPage4FullPay( userId);
		PageInfo<OrderDetailCustomSpecification> page = new PageInfo<OrderDetailCustomSpecification>(list);
		return ResultData.success(page);
		*/

		List<CustomOrderDetail> list = this.orderDetailDao.getCompleteAllPaymentList();
		PageInfo<CustomOrderDetail> page = new PageInfo<CustomOrderDetail>(list);
		return ResultData.success(page);
	}

	/**
	 * 删除方案
	 */
	@Override
	public ResultData delete(long id) {
		customSpecificationDao.updateStatus(id, 0);
		return ResultData.success(null);
	}

	private String getAdvancePaymentPriceConfig(){
		SysConfig config = sysConfigDao.queryConfigByItemKey(Constants.ADVANCE_PAYMENT_PRICE);
		if(config!=null && config.getItemValue()!=null){
			return config.getItemValue();
		}else{
			return "200"; // Default Value . TODO
		}
	}
}
