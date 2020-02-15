package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Poster;
import com.xseagull.boxing.features.service.IPosterService;

@RestController
@RequestMapping("/poster")
public class PosterController {
	@Autowired
	private IPosterService posterService;

	/**
	 * 分页查询海报
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		return posterService.queryByPage(pageNo, pageSize);
	}

	/**
	 * 新增海报
	 *
	 * @param notifyPersonnel
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(Poster poster) {
		return posterService.insert(poster);
	}

	/**
	 * 删除海报
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public ResultData delete(@PathVariable String id) {
		return posterService.delete(id);
	}

}
