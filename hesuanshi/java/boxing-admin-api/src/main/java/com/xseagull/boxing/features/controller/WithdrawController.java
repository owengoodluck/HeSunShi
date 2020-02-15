package com.xseagull.boxing.features.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IWithdrawService;
import com.xseagull.boxing.features.vo.WithdrawVO;

@RestController
@RequestMapping("/withdraw")
public class WithdrawController {
	@Autowired
	private IWithdrawService withdrawService;

	/**
	 * 根据用户id查询提现记录
	 */
	@PostMapping("/findWithdrawByUserId")
	public ResultData findWithdrawByUserId(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long uId) {
		return withdrawService.findWithdrawByUserId(pageNo, pageSize, uId);
	}

	/**
	 * 查询提现记录列表
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String name) {
		return withdrawService.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 转账
	 */
	@PostMapping("/transfer")
	public ResultData transfer(HttpServletRequest request, WithdrawVO withdrawVO) {
		return withdrawService.transfer(request, withdrawVO);
	}
}
