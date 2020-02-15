package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.ResourceFile;
import com.xseagull.boxing.features.service.IResourceService;


@RestController
@RequestMapping("/resource")
public class ResourceController {
	@Autowired
	private IResourceService resourceService;

	/**
	 * 删除资源
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/delete")
	public ResultData delete(ResourceFile resourceFile) {
		return resourceService.delete(resourceFile);
	}

	/**
	 * 根据编号查询资源列表
	 * 
	 * @param dept
	 * @return
	 */
	@GetMapping("/findResourceByNumber/{number}")
	public ResultData findResourceByNumber(@PathVariable String number) {
		return resourceService.findResourceByNumber(number);
	}
}
