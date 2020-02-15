package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.vo.RefundFileVO;
import com.xseagull.boxing.features.vo.RefundVO;

public interface RefundDao {

	List<RefundVO> queryByPage(@Param("status") String status, @Param("refundNumber") String refundNumber,
			@Param("userName") String userName);

	List<RefundFileVO> findRefundFileById(@Param("id") String id);

	int updateStatus(@Param("id") long id, @Param("status") int status);

	void updateStatusByrefundNumber(@Param("refundNumber") String refundNumber, @Param("status") int status);

}
