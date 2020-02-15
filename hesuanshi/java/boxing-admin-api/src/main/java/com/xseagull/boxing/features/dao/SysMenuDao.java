package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.dto.MenuDTO;
import com.xseagull.boxing.features.entity.SysMenu;

public interface SysMenuDao {

	//根据菜单id查询权限字符串
	List<String> findPowerByMenuIds(@Param("temp") List<String> temp);

	// 查询菜单给下拉框s
	public List<SysMenu> findMenu();

	// 添加菜单
	public int insertMenu(SysMenu menu);

	// 分页查询菜单
	public List<SysMenu> queryMenuByPage(@Param("name") String name);

	// 修改菜单
	public int updateMenu(SysMenu menu);

	// 根据id查询菜单的子项
	public List<SysMenu> findSunMenuById(String id);

	// 删除菜单
	public int deleteMenu(String id);

	// 查询菜单树
	public List<MenuDTO> findMenuTree();
}
