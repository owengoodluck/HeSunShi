package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.PrintMethodPrice;

public interface PrintMethodPriceDao {

	// 新增印刷方式价格
	public int insert(PrintMethodPrice printMethodPrice);

	// 分页印刷方式价格
	List<PrintMethodPrice> queryByPage(@Param("printMethodId") String printMethodId);

	// 更新印刷方式加格尔
	public int update(PrintMethodPrice printMethodPrice);

	// 删除价格区间
	public int delete(long id);

}
