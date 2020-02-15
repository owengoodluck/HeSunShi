package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Merchant;
import com.xseagull.boxing.features.service.IMerchantService;
import com.xseagull.boxing.features.vo.MerchantOrderVO;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
	@Autowired
	private IMerchantService merchantService;

	/**
	 * 分页查询商户
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "name") String name) {
		return merchantService.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 查询所有通过审核的商户
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/getMerchant")
	public ResultData getMerchant() {
		return merchantService.getMerchant();
	}

	/**
	 * 审核商户
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/review")
	public ResultData review(Merchant merchant) {
		return merchantService.review(merchant);
	}

	/**
	 * 查询店员
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/findStaffById/{id}")
	public ResultData findStaffById(@PathVariable long id) {
		return merchantService.findStaffById(id);
	}

	/**
	 * 商户佣金记录列表
	 */
	@GetMapping("/findCommissionByMerchantId/{merchantId}")
	public ResultData findCommissionByMerchantId(@PathVariable long merchantId) {
		return merchantService.findCommissionByMerchantId(merchantId);
	}

	/**
	 * 商户提现记录列表
	 */
	@GetMapping("/findWithdrawByMerchantId/{merchantId}")
	public ResultData findWithdrawByMerchantId(@PathVariable long merchantId) {
		return merchantService.findWithdrawByMerchantId(merchantId);
	}

	/**
	 * 查询所有用户提现记录列表
	 */
	@GetMapping("/queryWithdrawByPage")
	public ResultData queryWithdrawByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String name, Integer status) {
		return merchantService.queryWithdrawByPage(pageNo, pageSize, name, status);
	}

	/**
	 * 转账
	 */
	@GetMapping("/transfer/{id}")
	public ResultData transfer(@PathVariable long id) {
		return merchantService.transfer(id);
	}

	/**
	 * 根据订单ID查询派单情况
	 */
	@GetMapping("/findMerchantOrderByOrderId/{orderId}")
	public ResultData findMerchantOrderByOrderId(@PathVariable long orderId) {
		return merchantService.findMerchantOrderByOrderId(orderId);
	}

	/**
	 * 派单
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/sendOrder")
	public ResultData sendOrder(MerchantOrderVO merchantOrderVO) {
		return merchantService.sendOrder(merchantOrderVO);
	}
}
