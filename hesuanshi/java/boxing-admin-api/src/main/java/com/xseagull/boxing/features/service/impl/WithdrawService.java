package com.xseagull.boxing.features.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.CommissionRecordDao;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.dao.WithdrawDao;
import com.xseagull.boxing.features.service.IWithdrawService;
import com.xseagull.boxing.features.vo.WithdrawVO;
import com.xseagull.boxing.pay.FundTrans;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.ServiceMsgUtils;
import com.xseagull.boxing.util.TemplateData;

@Service
@Transactional
public class WithdrawService implements IWithdrawService {

	@Autowired
	private WithdrawDao withdrawDao;

	@Autowired
	private CommissionRecordDao commissionRecordDao;

	@Autowired
	private UserDao userDao;

	/**
	 * 根据用户id查询提现记录
	 */
	@Override
	public ResultData findWithdrawByUserId(Integer pageNo, Integer pageSize, long uId) {
		List<WithdrawVO> list = withdrawDao.findWithdrawByUserId(uId);
		for (WithdrawVO withdrawVO : list) {
			if (withdrawVO.getStatus().equals("0")) {
				withdrawVO.setStatus("待处理");
			} else if (withdrawVO.getStatus().equals("1")) {
				withdrawVO.setStatus("处理中");
			} else if (withdrawVO.getStatus().equals("2")) {
				withdrawVO.setStatus("已处理");
			}
		}
		return ResultData.success(list);
	}

	/**
	 * 查询提现记录列表
	 */
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<WithdrawVO> list = withdrawDao.queryByPage(name);
		for (WithdrawVO withdrawVO : list) {
			if (withdrawVO.getStatus().equals("0")) {
				withdrawVO.setStatus("待处理");
			} else if (withdrawVO.getStatus().equals("1")) {
				withdrawVO.setStatus("处理中");
			} else if (withdrawVO.getStatus().equals("2")) {
				withdrawVO.setStatus("已处理");
			}
		}
		PageInfo<WithdrawVO> page = new PageInfo<WithdrawVO>(list);
		return ResultData.success(page);
	}

	/**
	 * 转账
	 */
	@Transactional
	@Override
	public ResultData transfer(HttpServletRequest request, WithdrawVO withdrawVO) {
		// 把金额转化为分为单位
		BigDecimal money = withdrawVO.getMoney().multiply(new BigDecimal(100));
		FundTrans fundTrans = new FundTrans();
		fundTrans.setOutBizNo(String.valueOf(System.currentTimeMillis())); // 随机生成商户系统内部的订单号
		fundTrans.setAccount(withdrawVO.getOpenId()); // 服务号openid
		fundTrans.setRealName(withdrawVO.getNickName()); // 收款用户真实姓名
		fundTrans.setTotalAmount(money.toString()); // 付款金额
		fundTrans.setRemark("提现");
		withdrawDao.updateStatus(withdrawVO.getId(), 1);
		// 调用企业转账的接口
		Map<String, String> map = WeiChartUtils.transfers(request, fundTrans);
		if (map.get("result_code").equals("SUCCESS")) {
			// 更新提现记录状态
			withdrawDao.updateStatus(withdrawVO.getId(), 2);
			// 更新佣金记录表的佣金状态
			commissionRecordDao.updateStatus(withdrawVO.getId(), 3);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, TemplateData> m = new HashMap<String, TemplateData>();
			m.put("keyword1", new TemplateData(withdrawVO.getMoney().toString()));
			m.put("keyword2", new TemplateData(df.format(new Date())));
			m.put("keyword3", new TemplateData("您的佣金提现已到账,请注意查收"));
			ServiceMsgUtils.sendAppletsMsg(WeiChartUtils.getAppletsAccessToken().getAccess_token(),
					userDao.findOpenidById(withdrawVO.getuId()), "45GMwtmGJdCJ9-7uvkQOZizERcLkx0JjmYT96jWRCaY",
					"pages/orderList/orderList?type=2", withdrawVO.getFormId(), m);
			return ResultData.success(null);
		} else {
			return ResultData.warn(ResultCode.PAY_ERROR, map.get("err_code_des"));
		}
	}

}
