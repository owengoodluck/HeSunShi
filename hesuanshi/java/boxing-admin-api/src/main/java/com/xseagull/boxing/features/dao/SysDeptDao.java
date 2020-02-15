package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.SysDept;

public interface SysDeptDao {

	// 查询部门用于回显下拉框
	List<SysDept> findDept();

	// 新增部门
	public int insertDept(SysDept dept);

	// 分页查询部门信息
	public List<SysDept> queryDeptByPage(@Param("deptName") String deptName);

	// 更新部门
	public int updateDept(SysDept dept);

	// 删除部门
	public int deleteDept(String id);

	// 根据部门id查询部门名称
	String findDeptNameByid(@Param("deptid") String deptid);

}
