package com.xseagull.boxing.features.dao;

import java.util.List;
import com.xseagull.boxing.features.vo.MerchantStaffVO;

/**
 * 商户店员
 */
public interface MerchantStaffDao {

	List<MerchantStaffVO> findStaffById(long merchantId);

	MerchantStaffVO selectMerchantStaffByMerchantId(long merchantId);

}
