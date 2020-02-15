package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxType;
import com.xseagull.boxing.features.entity.DeliveryConfig;

public interface IBoxTypeService {

	// 新增盒型
	ResultData insert(BoxType boxType);

	// 分页盒型
	ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 更新盒型
	ResultData update(BoxType boxType);

	// 更新盒型状态
	ResultData updateStatus(BoxType boxType);

	// 删除盒型
	ResultData delete(long id);

	// 查询盒型用于下拉框回显
	ResultData findBoxType();

	ResultData queryDeliveryConfig();

	ResultData updateDeliveryConfig(DeliveryConfig deliveryConfig);

}
