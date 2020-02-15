package com.xseagull.boxing.features.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.xseagull.boxing.features.dao.WithdrawRecordDao;
import com.xseagull.boxing.features.entity.CommissionRecord;
import com.xseagull.boxing.features.entity.Promoter;
import com.xseagull.boxing.features.entity.WithdrawRecord;
import com.xseagull.boxing.features.service.IPromoterService;
import com.xseagull.boxing.features.vo.CommissionDataVO;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.ServiceMsgUtils;
import com.xseagull.boxing.util.TemplateData;

@Service
@Transactional
public class PromoterService implements IPromoterService {

	@Autowired
	private PromoterDao promoterDao;

	@Autowired
	private CommissionRecordDao commissionRecordDao;

	@Autowired
	private WithdrawRecordDao withdrawRecordDao;

	@Autowired
	private UserDao userDao;

	/**
	 * 保存推广人员信息
	 */
	@Override
	public ResultData save(Promoter promoter) {
		Promoter pro = promoterDao.findPromoterByUserId(promoter.getUserId());
		if (pro != null) {
			promoterDao.update(promoter);
		} else {
			promoterDao.insert(promoter);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, TemplateData> m = new HashMap<String, TemplateData>();
		m.put("first", new TemplateData("会员审核通知"));
		m.put("remark", new TemplateData("你有新的会员提交审核，请及时处理！"));
		m.put("keyword1", new TemplateData(promoter.getRealName()));
		m.put("keyword2", new TemplateData(promoter.getPhone()));
		m.put("keyword3", new TemplateData(df.format(new Date())));
		List<String> list = userDao.findServiceNumber();
		for (String string : list) {
			ServiceMsgUtils.sendServiceNumberMsg(WeiChartUtils.getServiceNumberAccessToken().getAccess_token(), string,
					"xPkKZMwxWcynLYWHQYKyyIczRewUSMip6ioae536BK4", m);
		}
		return ResultData.success(null);
	}

	/**
	 * 根据用户id查询是不是推广人员
	 */
	@Override
	public ResultData findPromoterByUserId(long userId) {
		return ResultData.success(promoterDao.findPromoterByUserId(userId));
	}
	
	/**
	 * 根据用户id查询是不是推广人员
	 */
	@Override
	public ResultData getPromoterByUserId(long userId) {
		Promoter promoter = promoterDao.findPromoterByUserId(userId);
		if (promoter != null) {
			return ResultData.success(promoter.getStatus());
		} else {
			return ResultData.success("-1");
		}
	}

	/**
	 * 增加邀请次数
	 */
	@Override
	public ResultData addNumber(long userId) {
		return ResultData.success(promoterDao.addNumber(userId));
	}

	/**
	 * 查询佣金数据
	 */
	@Override
	public ResultData findCommissionData(long userId) {
		CommissionDataVO commissionDataVO = new CommissionDataVO();
		commissionDataVO = commissionRecordDao.findCommissionData(userId);
		return ResultData.success(commissionDataVO);
	}

	/**
	 * 全部提现
	 */
	@Override
	public ResultData withdrawableAll(String withdrawableMoney, String formId, long userId) {
		// 新增一条提现记录
		WithdrawRecord withdrawRecord = new WithdrawRecord();
		withdrawRecord.setMoney(new BigDecimal(withdrawableMoney));
		withdrawRecord.setUserId(userId);
		withdrawRecord.setFormId(formId);
		withdrawRecordDao.insertWithdrawRecord(withdrawRecord);
		commissionRecordDao.updateStatusAndWithdrawRecordId(withdrawRecord.getId(), userId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, TemplateData> m = new HashMap<String, TemplateData>();
		m.put("first", new TemplateData("提现申请通知"));
		m.put("remark", new TemplateData("有新的提现申请，请及时处理！"));
		m.put("keyword1", new TemplateData(df.format(new Date())));
		m.put("keyword2", new TemplateData(withdrawableMoney));
		List<String> list = userDao.findServiceNumber();
		for (String string : list) {
			ServiceMsgUtils.sendServiceNumberMsg(WeiChartUtils.getServiceNumberAccessToken().getAccess_token(), string,
					"0qngixh-abtCKpdMVkNx_bAGuLRG50g4rqf8sWkp8tk", m);
		}
		return ResultData.success(null);
	}

	/**
	 * 提现记录列表
	 */
	@Override
	public ResultData findTixianRecord(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<WithdrawRecord> list = withdrawRecordDao.findTixianRecord(userId);
		PageInfo<WithdrawRecord> page = new PageInfo<WithdrawRecord>(list);
		return ResultData.success(page);
	}

	/**
	 * 佣金记录列表
	 */
	@Override
	public ResultData findCommissionRecord(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<CommissionRecord> list = commissionRecordDao.findCommissionRecord(userId);
		PageInfo<CommissionRecord> page = new PageInfo<CommissionRecord>(list);
		return ResultData.success(page);
	}

}
