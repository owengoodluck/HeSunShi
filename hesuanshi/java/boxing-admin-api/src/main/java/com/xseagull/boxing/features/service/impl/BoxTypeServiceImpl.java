package com.xseagull.boxing.features.service.impl;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.BoxLayoutDao;
import com.xseagull.boxing.features.dao.BoxMaterialDao;
import com.xseagull.boxing.features.dao.BoxTypeDao;
import com.xseagull.boxing.features.dao.DeliveryConfigDao;
import com.xseagull.boxing.features.entity.BoxType;
import com.xseagull.boxing.features.entity.DeliveryConfig;
import com.xseagull.boxing.features.service.IBoxTypeService;

@Service
@Transactional
public class BoxTypeServiceImpl implements IBoxTypeService {

	@Autowired
	private BoxTypeDao boxTypeDao;

	@Autowired
	private BoxMaterialDao boxMaterialDao;

	@Autowired
	private BoxLayoutDao boxLayoutDao;

	@Autowired
	private DeliveryConfigDao deliveryConfigDao;

	// 新增盒型
	@Override
	public ResultData insert(BoxType boxType) {
		try {
			ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
			String deliveryFormula = boxType.getValuationFormula();
			deliveryFormula = deliveryFormula.replaceAll("\\$L", "1.00");
			deliveryFormula = deliveryFormula.replaceAll("\\$W", "1.00");
			deliveryFormula = deliveryFormula.replaceAll("\\$H", "1.00");
			deliveryFormula = deliveryFormula.replaceAll("\\$M", "1.00");
			deliveryFormula = deliveryFormula.replaceAll("\\$N", "1.00");
			jse.eval(deliveryFormula);
			return ResultData.success(boxTypeDao.insert(boxType));
		} catch (ScriptException e) {
			return ResultData.warn(ResultCode.PARAMETER_ERROR, "公式参数有误");
		}

	}

	// 分页盒型分类
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<BoxType> list = boxTypeDao.queryByPage(name);
		PageInfo<BoxType> page = new PageInfo<BoxType>(list);
		return ResultData.success(page);
	}

	// 更新盒型
	@Override
	public ResultData update(BoxType boxType) {
		try {
			ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
			String deliveryFormula = boxType.getValuationFormula();
			deliveryFormula = deliveryFormula.replaceAll("\\$L", "1.00");
			deliveryFormula = deliveryFormula.replaceAll("\\$W", "1.00");
			deliveryFormula = deliveryFormula.replaceAll("\\$H", "1.00");
			deliveryFormula = deliveryFormula.replaceAll("\\$M", "1.00");
			deliveryFormula = deliveryFormula.replaceAll("\\$N", "1.00");
			jse.eval(deliveryFormula);
			return ResultData.success(boxTypeDao.update(boxType));
		} catch (ScriptException e) {
			return ResultData.warn(ResultCode.PARAMETER_ERROR, "公式参数有误");
		}
	}

	// 更新盒型状态
	@Override
	public ResultData updateStatus(BoxType boxType) {
		return ResultData.success(boxTypeDao.update(boxType));
	}

	// 删除盒型
	@Override
	public ResultData delete(long id) {
		System.out.println(id);
		int x = boxMaterialDao.findNumByBoxtypeId(id);
		int y = boxLayoutDao.findNumByBoxtypeId(id);
		if (x == 0 && y == 0) {
			return ResultData.success(boxTypeDao.delete(id));
		} else {
			return ResultData.warn(ResultCode.NOT_DELETE, "当前盒型被使用中,不能删除");
		}
	}

	// 查询盒型用于下拉框回显
	@Override
	public ResultData findBoxType() {
		return ResultData.success(boxTypeDao.findBoxType());
	}

	@Override
	public ResultData queryDeliveryConfig() {
		return ResultData.success(deliveryConfigDao.queryDeliveryConfig());
	}

	@Override
	public ResultData updateDeliveryConfig(DeliveryConfig deliveryConfig) {
		try {
			ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
			String deliveryFormula = deliveryConfig.getFormula();
			deliveryFormula = deliveryFormula.replaceAll("\\$G", "1");
			deliveryFormula = deliveryFormula.replaceAll("\\$Y", "1");
			deliveryFormula = deliveryFormula.replaceAll("\\$M", "1");
			deliveryFormula = deliveryFormula.replaceAll("\\$N", "1");
			deliveryFormula = deliveryFormula.replaceAll("\\$F", String.valueOf(deliveryConfig.getFirstWeight()));
			deliveryFormula = deliveryFormula.replaceAll("\\$C", String.valueOf(deliveryConfig.getContinuedWeight()));
			deliveryFormula = deliveryFormula.replaceAll("\\$Q", String.valueOf(deliveryConfig.getFirstCost()));
			deliveryFormula = deliveryFormula.replaceAll("\\$V", String.valueOf(deliveryConfig.getContinuedCost()));
			jse.eval(deliveryFormula);
			deliveryConfigDao.updateDeliveryConfig(deliveryConfig);
			return ResultData.success(null);
		} catch (Exception e) {
			return ResultData.warn(ResultCode.PARAMETER_ERROR, "公式参数有误");
		}
	}
}
