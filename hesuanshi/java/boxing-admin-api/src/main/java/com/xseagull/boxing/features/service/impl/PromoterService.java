package com.xseagull.boxing.features.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.CommissionRecordDao;
import com.xseagull.boxing.features.dao.PromoterDao;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.entity.CommissionRecord;
import com.xseagull.boxing.features.entity.Promoter;
import com.xseagull.boxing.features.service.IPromoterService;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.ServiceMsgUtils;
import com.xseagull.boxing.util.TemplateData;

@Service
@Transactional
public class PromoterService implements IPromoterService {

	@Autowired
	private PromoterDao promoterDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CommissionRecordDao commissionRecordDao;

	/**
	 * 分页查询推广人员
	 */
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<Promoter> list = promoterDao.queryByPage(name);
		PageInfo<Promoter> page = new PageInfo<Promoter>(list);
		return ResultData.success(page);

	}

	/**
	 * 修改佣金比例
	 */
	@Override
	public ResultData updateRate(Promoter promoter) {
		return ResultData.success(promoterDao.updateRate(promoter));
	}

	/**
	 * 审核推广人员
	 */
	@Override
	public ResultData review(Promoter promoter) {
		promoterDao.updateStatus(promoter.getId(), Integer.parseInt(promoter.getStatus()), promoter.getRemark());

		Map<String, TemplateData> m = new HashMap<String, TemplateData>();
		String url = "";
		if (promoter.getStatus().equals("1")) {
			url = "pages/myCommission/myCommission";
			m.put("keyword1", new TemplateData("您申请为推广人员已通过"));
			m.put("keyword2", new TemplateData("打开小程序开始推广赚取佣金吧"));
		} else {
			url = "pages/promoterFail/promoterFail";
			m.put("keyword1", new TemplateData("您申请为推广人员不通过"));
			m.put("keyword2", new TemplateData(promoter.getRemark()));
		}
		ServiceMsgUtils.sendAppletsMsg(WeiChartUtils.getAppletsAccessToken().getAccess_token(),
				userDao.findOpenidById(promoter.getuId()), "vCt2CAQuqRzmqL0aRu1Ow7vk7gO0XIPb08QyWSugZYQ", url,
				promoter.getFormId(), m);
		return ResultData.success(null);
	}

	/**
	 * 用户佣金记录列表
	 */
	@Override
	public ResultData findCommissionRecordByUserId(Integer pageNo, Integer pageSize, long uId) {
		List<CommissionRecord> list = commissionRecordDao.findCommissionRecordByUserId(uId);
		for (CommissionRecord commissionRecord : list) {
			if (commissionRecord.getStatus().equals("0")) {
				commissionRecord.setStatus("待结算");
			} else if (commissionRecord.getStatus().equals("1")) {
				commissionRecord.setStatus("可提现");
			} else if (commissionRecord.getStatus().equals("2")) {
				commissionRecord.setStatus("提现中");
			} else if (commissionRecord.getStatus().equals("3")) {
				commissionRecord.setStatus("已提现");
			}
		}
		return ResultData.success(list);
	}

}
