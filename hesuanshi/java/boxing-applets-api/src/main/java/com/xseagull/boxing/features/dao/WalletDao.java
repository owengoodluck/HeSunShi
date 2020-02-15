package com.xseagull.boxing.features.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.Wallet;
import com.xseagull.boxing.features.entity.WalletRecord;

/**
 * 用户钱包管理
 */
public interface WalletDao {
	/**
	 * 添加用户钱包
	 */
	public int insert(Wallet wallet);

	/**
	 * 根据用户id查询用户钱包余额
	 * 
	 * @param userId
	 * @return
	 */
	public Wallet findWallet(@Param("userId") long userId);

	/**
	 * 新增钱包记录
	 * 
	 * @param walletRecord
	 */
	public void insertRecord(WalletRecord walletRecord);

	/**
	 * 根据交易编号查询钱包记录
	 * 
	 * @param outTradeNo
	 * @return
	 */
	public WalletRecord findWalletRecordByOutTradeNo(@Param("outTradeNo") String outTradeNo);

	/**
	 * 根据交易编号修改钱包记录状态
	 * 
	 * @param outTradeNo
	 * @param status
	 */
	public void updateRecordStatus(@Param("outTradeNo") String outTradeNo, @Param("status") int status);

	/**
	 * 根据用户id修改余额
	 * 
	 * @param money
	 * @param userId
	 */
	public void updateMoney(@Param("money") BigDecimal money, @Param("userId") long userId);

	/**
	 * 我的钱包记录
	 * 
	 * @param userId
	 * @return
	 */
	public List<WalletRecord> findRecord(@Param("userId") long userId);

}
