package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.NotifyPersonnel;
import com.xseagull.boxing.features.service.INotifyPersonnelService;

@RestController
@RequestMapping("/notifyPersonnel")
public class NotifyPersonnelController {
	@Autowired
	private INotifyPersonnelService notifyPersonnelService;

	/**
	 * 分页查询通知人员
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		return notifyPersonnelService.queryByPage(pageNo, pageSize);
	}

	/**
	 * 新增通知人员
	 * 
	 * @param notifyPersonnel
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(NotifyPersonnel notifyPersonnel) {
		return notifyPersonnelService.insert(notifyPersonnel);
	}

	/**
	 * 删除通知人员
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public ResultData findUserByPhone(@PathVariable String id) {
		return notifyPersonnelService.delete(id);
	}
}
