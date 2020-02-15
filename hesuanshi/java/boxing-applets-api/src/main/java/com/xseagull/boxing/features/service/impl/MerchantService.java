package com.xseagull.boxing.features.service.impl;

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
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.MerchantDao;
import com.xseagull.boxing.features.dao.MerchantStaffDao;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.entity.Merchant;
import com.xseagull.boxing.features.entity.MerchantStaff;
import com.xseagull.boxing.features.service.IMerchantService;
import com.xseagull.boxing.pay.weixin.ServiceNumberUserInfo;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.ServiceMsgUtils;
import com.xseagull.boxing.util.TemplateData;

@Service
public class MerchantService implements IMerchantService {

	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private MerchantStaffDao merchantStaffDao;
	
	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public ResultData insert(Merchant merchant) {
		Merchant oldMerchant = merchantDao.selectByPhone(merchant.getPhone());
		if (oldMerchant == null) {
			merchant.setLogo("https://bzmall.oss-cn-shanghai.aliyuncs.com/logo.png");
			merchantDao.insert(merchant);
			MerchantStaff merchantStaff = new MerchantStaff();
			merchantStaff.setMerchantId(merchant.getId());
			merchantStaff.setContact(merchant.getContact());
			merchantStaff.setPhone(merchant.getPhone());
			merchantStaff.setServiceNumberOpenid(merchant.getServiceNumberOpenid());
			merchantStaff.setUnionid(merchant.getUnionid());
			merchantStaff.setType(1);
			merchantStaffDao.insert(merchantStaff);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, TemplateData> m = new HashMap<String, TemplateData>();
			m.put("first", new TemplateData("商户审核通知"));
			m.put("remark", new TemplateData("你有新的商户提交审核，请及时处理！"));
			m.put("keyword1", new TemplateData(merchant.getContact()));
			m.put("keyword2", new TemplateData(merchant.getPhone()));
			m.put("keyword3", new TemplateData(df.format(new Date())));
			List<String> list = userDao.findServiceNumber();
			for (String string : list) {
				ServiceMsgUtils.sendServiceNumberMsg(WeiChartUtils.getServiceNumberAccessToken().getAccess_token(), string,
						"xPkKZMwxWcynLYWHQYKyyIczRewUSMip6ioae536BK4", m);
			}
			return ResultData.success(0);
		} else {
			return ResultData.success(oldMerchant.getStatus());
		}
	}

	@Override
	public ResultData applyJudge(ServiceNumberUserInfo serviceNumberUserInfo) {
		Merchant merchant = merchantDao.selectByUnionid(serviceNumberUserInfo.getUnionid());
		if (merchant == null) {
			return ResultData.success(serviceNumberUserInfo);
		} else {
			return ResultData.success(merchant.getStatus());
		}
	}

	/**
	 * 查询商户工作台数据
	 */
	@Override
	public ResultData selectHomeData(long userId) {
		return ResultData.success(merchantDao.selectHomeData(userId));
	}

	/**
	 * 查询商户信息
	 */
	@Override
	public ResultData selectMerchantDetail(long userId) {
		return ResultData.success(merchantDao.selectById(userId));
	}

	/**
	 * 更新商户信息
	 */
	@Override
	public ResultData update(Merchant merchant) {
		return ResultData.success(merchantDao.update(merchant));
	}

	/**
	 * 查询商户的店员
	 */
	@Override
	public ResultData selectMyStaff(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<MerchantStaff> list = merchantStaffDao.selectStaffByMerchantId(userId);
		PageInfo<MerchantStaff> page = new PageInfo<MerchantStaff>(list);
		return ResultData.success(page);
	}

	/**
	 * 新增店员
	 */
	@Override
	public ResultData insertStaff(MerchantStaff merchantStaff, long userId) {
		MerchantStaff staff = merchantStaffDao.selectByPhone(merchantStaff.getPhone());
		if (staff == null) {
			merchantStaff.setMerchantId(userId);
			merchantStaff.setType(2);
			return ResultData.success(merchantStaffDao.insert(merchantStaff));
		} else {
			return ResultData.error(ResultCode.REGISTER_ERROR);
		}
	}

	/**
	 * 删除店员
	 */
	@Override
	public ResultData deleteStaff(long staffId) {
		return ResultData.success(merchantStaffDao.delete(staffId));
	}
}
