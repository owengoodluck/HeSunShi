package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.controller.UploadController;
import com.xseagull.boxing.features.dao.ResourceFileDao;
import com.xseagull.boxing.features.entity.ResourceFile;
import com.xseagull.boxing.features.service.IResourceService;

@Service
@Transactional
public class ResourceService implements IResourceService {

	@Autowired
	private ResourceFileDao resourceFileDao;

	@Override
	public ResultData delete(ResourceFile resourceFile) {
		UploadController.deleteObject(resourceFile.getFile());
		resourceFileDao.delete(resourceFile);
		return ResultData.success(null);
	}

	/**
	 * 根据编号查询资源列表
	 */
	@Override
	public ResultData findResourceByNumber(String number) {
		List<ResourceFile> list = resourceFileDao.findResourceByNumber(number);
		return ResultData.success(list);
	}

	/** 
	 * 根据类型和编号查询资源列表
	 */
	@Override
	public ResultData findResourceByTypeAndNumber(String type, String number) {
		ResourceFile file = resourceFileDao.findResourceByTypeAndNumber(type,number);
		return ResultData.success(file);
	}

}
