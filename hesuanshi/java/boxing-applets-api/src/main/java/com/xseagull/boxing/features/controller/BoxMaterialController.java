package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IBoxMaterialService;

@RestController
@RequestMapping("/boxMaterial")
public class BoxMaterialController {
	@Autowired
	private IBoxMaterialService boxMaterialService;

	/**
	 * 查询分类
	 */
	@GetMapping("/queryClassify")
	public ResultData queryClassify(long boxTypeDetailId) {
		return boxMaterialService.queryClassify(boxTypeDetailId);
	}
	
	/**
	 * 查询分类详情
	 */
	@GetMapping("/queryClassifyDetailById")
	public ResultData queryClassifyDetailById(long id) {
		return boxMaterialService.queryClassifyDetailById(id);
	}
	
	/**
	 * 查询分类详情数量
	 */
	@GetMapping("/findClassifyDetailNumByTId")
	public ResultData findClassifyDetailNumByTId(long tId) {
		return boxMaterialService.findClassifyDetailNumByTId(tId);
	}
	
	/**
	 * 查询分类详情数量
	 */
	@GetMapping("/queryClassifyDetailByTId")
	public ResultData queryClassifyDetailByTId(long tId) {
		return boxMaterialService.queryClassifyDetailByTId(tId);
	}
	
}
