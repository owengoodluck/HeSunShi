package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.PrintMethod;
import com.xseagull.boxing.features.entity.PrintMethodPrice;

/**
 * 印刷方式
 */
public interface PrintMethodDao {
	/**
	 * 查询列表
	 */
	public List<PrintMethod> queryList();
	/**
	 * 查询靠近的区间价格
	 */
	public PrintMethodPrice queryNear(@Param("printMethodId") long printMethodId, @Param("piece") int piece);
}
