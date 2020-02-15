package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxType;
import com.xseagull.boxing.features.entity.DeliveryConfig;
import com.xseagull.boxing.features.service.IBoxTypeService;

@RestController
@RequestMapping("/boxType")
public class BoxTypeController {

	@Autowired
	private IBoxTypeService boxTypeServiceImpl;

	/**
	 * 新增盒型
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(BoxType boxType) {
		return boxTypeServiceImpl.insert(boxType);
	}

	/**
	 * 分页盒型
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
		return boxTypeServiceImpl.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 更新盒型
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/update")
	public ResultData update(BoxType boxType) {
		return boxTypeServiceImpl.update(boxType);
	}

	/**
	 * 更新盒型状态
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/updateStatus")
	public ResultData updateStatus(BoxType boxType) {
		return boxTypeServiceImpl.updateStatus(boxType);
	}

	/**
	 * 删除盒型
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public ResultData delete(@PathVariable long id) {
		return boxTypeServiceImpl.delete(id);
	}

	/**
	 * 查询盒型用于下拉框回显
	 *
	 * @return
	 */
	@GetMapping("/findBoxType")
	public ResultData findBoxType() {
		return boxTypeServiceImpl.findBoxType();
	}
	
	/**
	 * 查询配送配置
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param deptName
	 * @return
	 */
	@GetMapping("/queryDeliveryConfig")
	public ResultData queryDeliveryConfig() {
		return boxTypeServiceImpl.queryDeliveryConfig();
	}
	
	/**
	 * 更新配送配置
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/updateDeliveryConfig")
	public ResultData updateDeliveryConfig(DeliveryConfig deliveryConfig) {
		return boxTypeServiceImpl.updateDeliveryConfig(deliveryConfig);
	}
}
