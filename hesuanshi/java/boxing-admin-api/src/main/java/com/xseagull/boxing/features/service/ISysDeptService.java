package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysDept;

public interface ISysDeptService {

	// 查询部门用于回显下拉框
	ResultData findDept();

	// 新增部门
	ResultData insertDept(SysDept dept);

	// 分页查询部门信息
	ResultData queryDeptByPage(Integer pageNo, Integer pageSize, String deptName);

	// 更新部门
	ResultData updateDept(SysDept dept);

	// 删除部门
	ResultData deleteDept(String id);

}
