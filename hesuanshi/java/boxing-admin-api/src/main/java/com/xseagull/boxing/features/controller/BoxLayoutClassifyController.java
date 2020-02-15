package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxLayoutClassify;
import com.xseagull.boxing.features.service.IBoxLayoutClassifyService;

@RestController
@RequestMapping("/boxLayoutClassify")
public class BoxLayoutClassifyController {

	@Autowired
	private IBoxLayoutClassifyService boxLayoutClassifyServiceImpl;

	/**
	 * 查询版面分类用于下拉框回显
	 *
	 * @return
	 */
	@GetMapping("/findClassify")
	public ResultData findClassify() {
		return boxLayoutClassifyServiceImpl.findClassify();
	}

	/**
	 * 新增版面分类
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(BoxLayoutClassify boxLayoutClassify) {
		return boxLayoutClassifyServiceImpl.insert(boxLayoutClassify);
	}

	/**
	 * 分页版面分类
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
		return boxLayoutClassifyServiceImpl.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 更新版面分类
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/update")
	public ResultData update(BoxLayoutClassify boxLayoutClassify) {
		return boxLayoutClassifyServiceImpl.update(boxLayoutClassify);
	}

	/**
	 * 更新版面分类状态
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/updateStatus")
	public ResultData updateStatus(BoxLayoutClassify boxLayoutClassify) {
		return boxLayoutClassifyServiceImpl.updateStatus(boxLayoutClassify);
	}

}
