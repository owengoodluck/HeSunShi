package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxMaterial;
import com.xseagull.boxing.features.service.IBoxMaterialService;

@RestController
@RequestMapping("/boxMaterial")
public class BoxMaterialController {

	@Autowired
	private IBoxMaterialService boxMaterialServiceImpl;


	
	/**
	 * 新增材质
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/insert")
	public ResultData insert(BoxMaterial boxMaterial) {
		return boxMaterialServiceImpl.insert(boxMaterial);
	}

	/**
	 * 分页材质
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
		return boxMaterialServiceImpl.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 更新材质
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/update")
	public ResultData update(BoxMaterial boxMaterial) {
		return boxMaterialServiceImpl.update(boxMaterial);
	}

	/**
	 * 更新材质状态
	 *
	 * @param dept
	 * @return
	 */
	@PostMapping("/updateStatus")
	public ResultData updateStatus(BoxMaterial boxMaterial) {
		return boxMaterialServiceImpl.updateStatus(boxMaterial);
	}
	
	/**
	 * 删除材质
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/delete/{id}")
	public ResultData delete(@PathVariable long id) {
		return boxMaterialServiceImpl.delete(id);
	}
}
