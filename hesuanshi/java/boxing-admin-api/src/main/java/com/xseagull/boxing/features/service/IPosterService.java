package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Poster;

/**
 * 推广海报
 */
public interface IPosterService {

	// 分页查询海报
	ResultData queryByPage(Integer pageNo, Integer pageSize);

	// 新增海报
	ResultData insert(Poster poster);

	// 删除海报
	ResultData delete(String id);

}
