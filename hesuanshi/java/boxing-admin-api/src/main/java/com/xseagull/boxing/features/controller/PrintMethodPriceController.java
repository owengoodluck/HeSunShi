package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.PrintMethodPrice;
import com.xseagull.boxing.features.service.IPrintMethodPriceService;

@RestController
@RequestMapping("/printMethodPrice")
public class PrintMethodPriceController {

	@Autowired
	private IPrintMethodPriceService printMethodPriceServiceImpl;

	/**
	 * 新增印刷方式价格
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(PrintMethodPrice printMethodPrice) {
		return printMethodPriceServiceImpl.insert(printMethodPrice);
	}

	/**
	 * 分页印刷方式价格
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param deptName
	 * @return
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "printMethodId") String printMethodId) {
		return printMethodPriceServiceImpl.queryByPage(pageNo, pageSize, printMethodId);
	}

	/**
	 * 更新印刷方式价格
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/update")
	public ResultData update(PrintMethodPrice printMethodPrice) {
		return printMethodPriceServiceImpl.update(printMethodPrice);
	}

	/**
	 * 删除价格区间
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public ResultData delete(@PathVariable long id) {
		return printMethodPriceServiceImpl.delete(id);
	}
}
