package com.xseagull.boxing.features.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xseagull.boxing.constant.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.MerchantCommissionDao;
import com.xseagull.boxing.features.dao.MerchantDao;
import com.xseagull.boxing.features.dao.MerchantOrderDao;
import com.xseagull.boxing.features.dao.MerchantStaffDao;
import com.xseagull.boxing.features.dao.MerchantWithdrawDao;
import com.xseagull.boxing.features.dao.OrderDao;
import com.xseagull.boxing.features.entity.Merchant;
import com.xseagull.boxing.features.service.IMerchantService;
import com.xseagull.boxing.features.vo.MerchantCommissionVO;
import com.xseagull.boxing.features.vo.MerchantOrderVO;
import com.xseagull.boxing.features.vo.MerchantStaffVO;
import com.xseagull.boxing.features.vo.MerchantWithdrawVO;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.ServiceMsgUtils;
import com.xseagull.boxing.util.SmsUtils;
import com.xseagull.boxing.util.TemplateData;

@Service
@Transactional
public class MerchantService implements IMerchantService {

	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private MerchantStaffDao merchantStaffDao;

	@Autowired
	private MerchantCommissionDao merchantCommissionDao;

	@Autowired
	private MerchantWithdrawDao merchantWithdrawDao;

	@Autowired
	private MerchantOrderDao merchantOrderDao;

	@Autowired
	private OrderDao orderDao;

	/**
	 * 分页查询商户
	 */
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<Merchant> list = merchantDao.queryByPage(name);
		PageInfo<Merchant> page = new PageInfo<Merchant>(list);
		return ResultData.success(page);
	}

	/**
	 * 审核商户
	 */
	@Override
	public ResultData review(Merchant merchant) {
		merchantDao.updateStatus(merchant.getId(), merchant.getStatus(), merchant.getRemark());
		MerchantStaffVO merchantStaffVO = merchantStaffDao.selectMerchantStaffByMerchantId(merchant.getId());
		if (StringUtils.isEmpty(merchantStaffVO.getServiceNumberOpenid())) {
			String url = "";
			if (merchant.getStatus() == 1) {
				url = "已通过";
			} else {
				url = "未通过,拒绝理由:" + merchant.getRemark();
			}
			SendSmsResponse sendSmsResponse = SmsUtils.sendSmsMerchant(merchantStaffVO.getPhone(), url);
			if (sendSmsResponse == null) {
				return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
			} else {
				if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
					return ResultData.success(null);
				} else {
					return ResultData.warn(ResultCode.SMS_CODE_ERROR, "请求太频繁，请稍后再试!");
				}
			}
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, TemplateData> m = new HashMap<String, TemplateData>();
			String url = "https://h5.cwzjapp.com/#/apply";
			if (merchant.getStatus() == 1) {
				m.put("first", new TemplateData("您的商户申请已通过"));
				m.put("remark", new TemplateData("感谢您的使用"));
			} else {
				m.put("first", new TemplateData("您的商户申请不通过"));
				m.put("remark", new TemplateData(merchant.getRemark()));
			}
			m.put("keyword1", new TemplateData(merchantStaffVO.getContact()));
			m.put("keyword2", new TemplateData(merchantStaffVO.getPhone()));
			m.put("keyword3", new TemplateData(df.format(new Date())));
			ServiceMsgUtils.sendServiceNumberMsg(WeiChartUtils.getServiceNumberAccessToken().getAccess_token(),
					merchantStaffVO.getServiceNumberOpenid(), "xPkKZMwxWcynLYWHQYKyyIczRewUSMip6ioae536BK4", url, m);
		}
		return ResultData.success(null);
	}

	/**
	 * 商户佣金记录列表
	 */
	@Override
	public ResultData findCommissionByMerchantId(long merchantId) {
		List<MerchantCommissionVO> list = merchantCommissionDao.findCommissionByMerchantId(merchantId);
		for (MerchantCommissionVO merchantCommissionVO : list) {
			if (merchantCommissionVO.getStatus().equals("0")) {
				merchantCommissionVO.setStatus("待结算");
			} else if (merchantCommissionVO.getStatus().equals("1")) {
				merchantCommissionVO.setStatus("可提现");
			} else if (merchantCommissionVO.getStatus().equals("2")) {
				merchantCommissionVO.setStatus("提现中");
			} else if (merchantCommissionVO.getStatus().equals("3")) {
				merchantCommissionVO.setStatus("已提现");
			}
		}
		return ResultData.success(list);
	}

	/**
	 * 查询店员
	 */
	@Override
	public ResultData findStaffById(long id) {
		return ResultData.success(merchantStaffDao.findStaffById(id));
	}

	/**
	 * 商户提现记录列表
	 */
	@Override
	public ResultData findWithdrawByMerchantId(long merchantId) {
		List<MerchantWithdrawVO> list = merchantWithdrawDao.findWithdrawByMerchantId(merchantId);
		for (MerchantWithdrawVO merchantWithdrawVO : list) {
			if (merchantWithdrawVO.getStatus().equals("0")) {
				merchantWithdrawVO.setStatus("待处理");
			} else if (merchantWithdrawVO.getStatus().equals("1")) {
				merchantWithdrawVO.setStatus("处理中");
			} else if (merchantWithdrawVO.getStatus().equals("2")) {
				merchantWithdrawVO.setStatus("已处理");
			}
		}
		return ResultData.success(list);
	}

	/**
	 * 查询所有用户提现记录
	 */
	@Override
	public ResultData queryWithdrawByPage(Integer pageNo, Integer pageSize, String name, Integer status) {
		PageHelper.startPage(pageNo, pageSize);
		List<MerchantWithdrawVO> list = merchantWithdrawDao.queryWithdrawByPage(name, status);
		for (MerchantWithdrawVO merchantWithdrawVO : list) {
			if (merchantWithdrawVO.getStatus().equals("0")) {
				merchantWithdrawVO.setStatus("待处理");
			} else if (merchantWithdrawVO.getStatus().equals("1")) {
				merchantWithdrawVO.setStatus("处理中");
			} else if (merchantWithdrawVO.getStatus().equals("2")) {
				merchantWithdrawVO.setStatus("已处理");
			}
		}
		PageInfo<MerchantWithdrawVO> page = new PageInfo<MerchantWithdrawVO>(list);
		return ResultData.success(page);
	}

	/**
	 * 处理提现
	 */
	@Override
	public ResultData transfer(long id) {
		merchantWithdrawDao.updateStatus(id, 2);
		merchantCommissionDao.updateStatus(id, 3);
		return ResultData.success(null);
	}

	/**
	 * 根据订单ID查询派单情况
	 */
	@Override
	public ResultData findMerchantOrderByOrderId(long orderId) {
		List<MerchantOrderVO> list = merchantOrderDao.findMerchantOrderByOrderId(orderId);
		for (MerchantOrderVO merchantOrderVO : list) {
			if (merchantOrderVO.getStatus().equals("0")) {
				merchantOrderVO.setStatus("未处理");
			} else if (merchantOrderVO.getStatus().equals("1")) {
				merchantOrderVO.setStatus("同意");
			} else if (merchantOrderVO.getStatus().equals("2")) {
				merchantOrderVO.setStatus("驳回");
			}
		}
		return ResultData.success(list);
	}

	/**
	 * 查询所有通过审核的商户
	 * 
	 * @return
	 */
	@Override
	public ResultData getMerchant() {
		List<Merchant> list = merchantDao.getMerchant();
		return ResultData.success(list);
	}

	/**
	 * 派单
	 */
	@Override
	public ResultData sendOrder(MerchantOrderVO merchantOrderVO) {
		merchantOrderDao.insert(merchantOrderVO);
		orderDao.updateMerchantStatus(merchantOrderVO.getOrderId(), 1);
		orderDao.updateStatus(merchantOrderVO.getOrderId(), OrderStatusEnum.PENDING_SAMPLE_CONFIRM.getValue());
		MerchantStaffVO merchantStaffVO = merchantStaffDao
				.selectMerchantStaffByMerchantId(merchantOrderVO.getMerchantId());
		if (StringUtils.isEmpty(merchantStaffVO.getServiceNumberOpenid())) {
			SendSmsResponse sendSmsResponse = SmsUtils.sendSmsPaidan(merchantStaffVO.getPhone());
			if (sendSmsResponse == null) {
				return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
			} else {
				if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
					return ResultData.success(null);
				} else {
					return ResultData.warn(ResultCode.SMS_CODE_ERROR, "请求太频繁，请稍后再试!");
				}
			}
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, TemplateData> m = new HashMap<String, TemplateData>();
			String url = "https://h5.cwzjapp.com/#/order";
			m.put("first", new TemplateData("您有新的派单，请及时确认！"));
			m.put("remark", new TemplateData("请及时处理!"));
			m.put("keyword1", new TemplateData(orderDao.selectOrderNumberById(merchantOrderVO.getOrderId())));
			m.put("keyword2", new TemplateData(df.format(new Date())));
			ServiceMsgUtils.sendServiceNumberMsg(WeiChartUtils.getServiceNumberAccessToken().getAccess_token(),
					merchantStaffVO.getServiceNumberOpenid(), "lb1d2sbC0l1puufHS_kE7jk5ayaTtrhbU5kY77pJAoc", url, m);
		}
		return ResultData.success(null);
	}

}
