package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxLayout;
import com.xseagull.boxing.features.service.IBoxLayoutService;

@RestController
@RequestMapping("/boxLayout")
public class BoxLayoutController {

	@Autowired
	private IBoxLayoutService boxLayoutServiceImpl;

	/**
	 * 新增版面
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(BoxLayout boxLayout) {
		return boxLayoutServiceImpl.insert(boxLayout);
	}

	/**
	 * 分页版面
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
		return boxLayoutServiceImpl.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 更新版面
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/update")
	public ResultData update(BoxLayout boxLayout) {
		return boxLayoutServiceImpl.update(boxLayout);
	}

	/**
	 * 更新版面状态
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/updateStatus")
	public ResultData updateStatus(BoxLayout boxLayout) {
		return boxLayoutServiceImpl.updateStatus(boxLayout);
	}

	/**
	 * 删除版面
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public ResultData delete(@PathVariable long id) {
		return boxLayoutServiceImpl.delete(id);
	}
}
