package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.Refund;
import com.xseagull.boxing.features.entity.RefundFile;
import com.xseagull.boxing.features.vo.RefundVO;

/**
 * 退款管理
 */
public interface RefundDao {

	// 添加退款信息
	public void insertRefund(Refund refund);

	// 添加退款附件
	public void insertRefundFile(RefundFile refundFile);

	// 查询用户退款列表
	public List<RefundVO> findRefundList(@Param("userId") long userId);

}
