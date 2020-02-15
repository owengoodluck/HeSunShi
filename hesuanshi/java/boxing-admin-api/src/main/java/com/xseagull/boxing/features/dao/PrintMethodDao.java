package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.PrintMethod;

public interface PrintMethodDao {

	// 新增印刷方式
	public int insert(PrintMethod printMethod);

	// 分页印刷方式
	List<PrintMethod> queryByPage(@Param("name") String name);

	// 更新印刷方式
	public int update(PrintMethod printMethod);

	// 查询印刷方式用于回显下拉框
	List<PrintMethod> findPrintMethod();

}
