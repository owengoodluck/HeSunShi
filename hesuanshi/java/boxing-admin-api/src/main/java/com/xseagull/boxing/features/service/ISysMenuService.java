package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysMenu;

public interface ISysMenuService {

	// 查询菜单给下拉框
	ResultData findMenu();

	// 添加菜单
	ResultData insertMenu(SysMenu menu);

	// 分页查询菜单
	ResultData queryMenuByPage(Integer pageNo, Integer pageSize, String name);

	// 修改菜单
	ResultData updateMenu(SysMenu menu);

	// 删除菜单
	ResultData deleteMenu(String id);
	
	//查询菜单树
	ResultData findMenuTree();
}
