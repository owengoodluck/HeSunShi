package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxMaterialClassify;
import com.xseagull.boxing.features.service.IBoxMaterialClassifyService;

@RestController
@RequestMapping("/boxMaterialClassify")
public class BoxMaterialClassifyController {

	@Autowired
	private IBoxMaterialClassifyService boxMaterialClassifyServiceImpl;

	 /**
	 * 查询材质分类用于下拉框回显
	 *
	 * @return
	 */
	 @GetMapping("/findClassify")
	 public ResultData findClassify() {
	 return boxMaterialClassifyServiceImpl.findClassify();
	 }
	
	/**
	 * 新增材质分类
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(BoxMaterialClassify boxMaterialClassify) {
		return boxMaterialClassifyServiceImpl.insert(boxMaterialClassify);
	}

	/**
	 * 分页材质分类
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param deptName
	 * @return
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "name") String name) {
		return boxMaterialClassifyServiceImpl.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 更新材质分类
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/update")
	public ResultData update(BoxMaterialClassify boxMaterialClassify) {
		return boxMaterialClassifyServiceImpl.update(boxMaterialClassify);
	}

	/**
	 * 更新材质分类状态
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/updateStatus")
	public ResultData updateStatus(BoxMaterialClassify boxMaterialClassify) {
		return boxMaterialClassifyServiceImpl.updateStatus(boxMaterialClassify);
	}

}
