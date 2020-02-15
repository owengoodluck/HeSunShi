package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysDept;
import com.xseagull.boxing.features.service.ISysDeptService;

@RestController
@RequestMapping("/sys_dept")
public class SysDeptController {

	@Autowired
	private ISysDeptService sysDeptServiceImpl;

	/**
	 * 查询机构用于添加用户部门选择下拉框赋值
	 * 
	 * @return
	 */
	@GetMapping("/findDept")
	public ResultData findDept() {
		return sysDeptServiceImpl.findDept();
	}

	/**
	 * 新增机构
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/insertDept")
	public ResultData insertDept(SysDept dept) {
		return sysDeptServiceImpl.insertDept(dept);
	}

	/**
	 * 分页查询机构
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param deptName
	 * @return
	 */
	@GetMapping("/queryDeptByPage")
	public ResultData queryDeptByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "deptName") String deptName) {
		return sysDeptServiceImpl.queryDeptByPage(pageNo, pageSize, deptName);
	}

	/**
	 * 更新机构
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/updateDept")
	public ResultData updateDept(SysDept dept) {
		return sysDeptServiceImpl.updateDept(dept);
	}

	/**
	 * 删除机构
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteDept/{id}")
	public ResultData deleteDept(@PathVariable String id) {
		return sysDeptServiceImpl.deleteDept(id);
	}
}
