package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.PrintMethod;
import com.xseagull.boxing.features.service.IPrintMethodService;

@RestController
@RequestMapping("/printMethod")
public class PrintMethodController {

	@Autowired
	private IPrintMethodService printMethodServiceImpl;

	/**
	 * 查询印刷方式用于下拉框回显
	 *
	 * @return
	 */
	@GetMapping("/findPrintMethod")
	public ResultData findPrintMethod() {
		return printMethodServiceImpl.findPrintMethod();
	}

	/**
	 * 新增印刷方式
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(PrintMethod printMethod) {
		return printMethodServiceImpl.insert(printMethod);
	}

	/**
	 * 分页印刷方式
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
		return printMethodServiceImpl.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 更新印刷方式
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/update")
	public ResultData update(PrintMethod printMethod) {
		return printMethodServiceImpl.update(printMethod);
	}

	/**
	 * 更新印刷方式状态
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/updateStatus")
	public ResultData updateStatus(PrintMethod printMethod) {
		return printMethodServiceImpl.updateStatus(printMethod);
	}

}
