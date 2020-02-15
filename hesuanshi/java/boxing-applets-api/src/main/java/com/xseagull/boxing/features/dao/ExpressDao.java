package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.Express;
import com.xseagull.boxing.features.vo.ExpressDetailVO;

/**
 * 快递管理
 */
public interface ExpressDao {

	// 根据订单id查询订单的快递信息
	ExpressDetailVO findExpressDetail(@Param("orderId") long orderId);

	List<Express> getExpress();

}
