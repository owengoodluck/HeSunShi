package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.SysMenuDao;
import com.xseagull.boxing.features.dto.MenuDTO;
import com.xseagull.boxing.features.entity.SysMenu;
import com.xseagull.boxing.features.service.ISysMenuService;
import com.xseagull.boxing.util.TreeUtils;

@Service
@Transactional
public class SysMenuServiceImpl implements ISysMenuService {

	@Autowired
	private SysMenuDao sysMenuDao;

	// 查询菜单给下拉框
	@Override
	public ResultData findMenu() {
		List<SysMenu> list = sysMenuDao.findMenu();
		return ResultData.success(list);
	}

	// 添加菜单
	@Override
	public ResultData insertMenu(SysMenu menu) {
		menu.setStatus("1");
		if (StringUtils.isEmpty(menu.getPid())) {
			menu.setPid("0");
		}
		return ResultData.success(sysMenuDao.insertMenu(menu));
	}

	// 分页查询菜单
	@Override
	public ResultData queryMenuByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<SysMenu> list = sysMenuDao.queryMenuByPage(name);
		PageInfo<SysMenu> page = new PageInfo<SysMenu>(list);
		return ResultData.success(page);
	}

	// 修改菜单
	@Override
	public ResultData updateMenu(SysMenu menu) {
		return ResultData.success(sysMenuDao.updateMenu(menu));
	}

	// 删除菜单
	@Override
	public ResultData deleteMenu(String id) {
		// 查询出这级菜单下有没有东西 有就不能删除
		List<SysMenu> list = sysMenuDao.findSunMenuById(id);
		if (!list.isEmpty()) {
			return ResultData.warn(ResultCode.PARAMETER_ERROR, "不能删除有下级菜单的菜单!!");
		}
		return ResultData.success(sysMenuDao.deleteMenu(id));
	}

	// 查询菜单树
	@Override
	public ResultData findMenuTree() {
		List<MenuDTO> list = sysMenuDao.findMenuTree();
		for (MenuDTO menuDTO : list) {
			if (menuDTO.getId().equals("1")) {
				TreeUtils.createTree(list, menuDTO, "id", "pid", "children");
			}
		}
		return ResultData.success(list);
	}

}
