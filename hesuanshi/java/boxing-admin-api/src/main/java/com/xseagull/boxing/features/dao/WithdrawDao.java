package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.vo.WithdrawVO;

public interface WithdrawDao {

	// 根据用户id查询提现记录
	List<WithdrawVO> findWithdrawByUserId(@Param("uId") long uId);

	// 查询提现记录列表
	List<WithdrawVO> queryByPage(@Param("name") String name);

	// 修改提现记录状态
	public void updateStatus(@Param("id") long id, @Param("status") int status);

}
