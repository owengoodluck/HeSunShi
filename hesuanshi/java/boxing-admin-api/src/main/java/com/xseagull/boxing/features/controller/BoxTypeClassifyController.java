package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxTypeClassify;
import com.xseagull.boxing.features.service.IBoxTypeClassifyService;

@RestController
@RequestMapping("/boxTypeClassify")
public class BoxTypeClassifyController {

	@Autowired
	private IBoxTypeClassifyService boxTypeClassifyServiceImpl;

	 /**
	 * 查询盒型分类用于下拉框回显
	 *
	 * @return
	 */
	 @GetMapping("/findClassify")
	 public ResultData findClassify() {
	 return boxTypeClassifyServiceImpl.findClassify();
	 }
	
	/**
	 * 新增盒型分类
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(BoxTypeClassify boxTypeClassify) {
		return boxTypeClassifyServiceImpl.insert(boxTypeClassify);
	}

	/**
	 * 分页盒型分类
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
		return boxTypeClassifyServiceImpl.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 更新盒型分类
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/update")
	public ResultData update(BoxTypeClassify boxTypeClassify) {
		return boxTypeClassifyServiceImpl.update(boxTypeClassify);
	}

	/**
	 * 更新盒型分类状态
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/updateStatus")
	public ResultData updateStatus(BoxTypeClassify boxTypeClassify) {
		return boxTypeClassifyServiceImpl.updateStatus(boxTypeClassify);
	}

}
