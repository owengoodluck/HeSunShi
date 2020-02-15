package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.SysDeptDao;
import com.xseagull.boxing.features.dao.SysUserDao;
import com.xseagull.boxing.features.entity.SysDept;
import com.xseagull.boxing.features.entity.SysUser;
import com.xseagull.boxing.features.service.ISysDeptService;

@Service
@Transactional
public class SysDeptServiceImpl implements ISysDeptService {

	@Autowired
	private SysDeptDao sysDeptDao;

	@Autowired
	private SysUserDao sysUserDao;

	// 查询部门用于回显下拉框
	@Override
	public ResultData findDept() {
		List<SysDept> list = sysDeptDao.findDept();
		return ResultData.success(list);
	}

	// 新增部门
	@Override
	public ResultData insertDept(SysDept dept) {
		return ResultData.success(sysDeptDao.insertDept(dept));
	}

	// 分页查询部门信息
	@Override
	public ResultData queryDeptByPage(Integer pageNo, Integer pageSize, String deptName) {
		PageHelper.startPage(pageNo, pageSize);
		List<SysDept> list = sysDeptDao.queryDeptByPage(deptName);
		PageInfo<SysDept> page = new PageInfo<SysDept>(list);
		return ResultData.success(page);
	}

	// 更新部门
	@Override
	public ResultData updateDept(SysDept dept) {
		return ResultData.success(sysDeptDao.updateDept(dept));
	}

	// 删除部门
	@Override
	public ResultData deleteDept(String id) {
		// 删除之前看看该部门有没有用户
		List<SysUser> list = sysUserDao.findSysUserByDeptId(id);
		if (list != null && !list.isEmpty()) {
			return ResultData.warn(ResultCode.NOT_DELETE, "不能删除有成员的部门!");
		}
		return ResultData.success(sysDeptDao.deleteDept(id));
	}
}
