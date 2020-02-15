package com.xseagull.boxing.features.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.CustomSpecificationDao;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.entity.CustomSpecification;
import com.xseagull.boxing.features.service.ICustomSpecificationService;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.ServiceMsgUtils;
import com.xseagull.boxing.util.TemplateData;

@Service
@Transactional
public class CustomSpecificationService implements ICustomSpecificationService {

	@Value("${templateId}")
	private String templateId;

	@Autowired
	private CustomSpecificationDao customSpecificationDao;

	@Autowired
	private UserDao userDao;

	/**
	 * 分页方案
	 */
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, Integer type) {
		PageHelper.startPage(pageNo, pageSize);
		List<CustomSpecification> list = customSpecificationDao.queryByPage(type);
		PageInfo<CustomSpecification> page = new PageInfo<CustomSpecification>(list);
		return ResultData.success(page);
	}

	/**
	 * 方案报价
	 */
	@Override
	public ResultData quotedPrice(CustomSpecification customSpecification) {
		customSpecificationDao.quotedPrice(customSpecification);
		Map<String, TemplateData> m = new HashMap<String, TemplateData>();
		m.put("keyword1", new TemplateData(customSpecification.getCustomNumber()));
		m.put("keyword2", new TemplateData(customSpecification.getName()));
		m.put("keyword3", new TemplateData(customSpecification.getPiece() + ""));
		m.put("keyword4", new TemplateData(customSpecification.getPrice() + ""));
		ServiceMsgUtils.sendAppletsMsg(WeiChartUtils.getAppletsAccessToken().getAccess_token(),
				userDao.findOpenidById(customSpecification.getuId()), templateId, "pages/shoppingCart/shoppingCart",
				customSpecification.getFormId(), m);
		return ResultData.success(null);
	}

	@Override
	public ResultData queryByCustomNumber(String customNumber) {
		CustomSpecification customSpecification = customSpecificationDao.queryByCustomNumber(customNumber);
		if(customSpecification != null) {
			return ResultData.success(customSpecification);
		}else {
			return ResultData.error(ResultCode.NO_CUSTOM_NUMBER);
		}
	}

	@Override
	public ResultData isUpload(String customNumber) {
		int i = customSpecificationDao.isUpload(customNumber);
		if(i == 1) {
			
			return ResultData.success("已完成上传更新");
			
		}else {
			
			return ResultData.error(ResultCode.NO_CUSTOM_NUMBER);
		}
	}

}
