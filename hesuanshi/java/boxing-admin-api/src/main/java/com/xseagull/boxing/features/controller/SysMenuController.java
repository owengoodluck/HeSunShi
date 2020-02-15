package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysMenu;
import com.xseagull.boxing.features.service.ISysMenuService;

@RestController
@RequestMapping("/sys_menu")
public class SysMenuController {

	@Autowired
	private ISysMenuService sysMenuServiceImpl;

	/**
	 * 查询菜单用于下拉框回显
	 * @return
	 */
	@GetMapping("/findMenu")
	public ResultData findMenu() {
		return sysMenuServiceImpl.findMenu();
	}

	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	@PostMapping("/insertMenu")
	public ResultData insertMenu(SysMenu menu) {
		return sysMenuServiceImpl.insertMenu(menu);
	}

	/**
	 * 分页查询菜单信息
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @return
	 */
	@GetMapping("/queryMenuByPage")
	public ResultData queryMenuByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "name") String name) {
		return sysMenuServiceImpl.queryMenuByPage(pageNo, pageSize, name);
	}

	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	@PostMapping("/updateMenu")
	public ResultData updateMenu(SysMenu menu) {
		return sysMenuServiceImpl.updateMenu(menu);
	}

	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteMenu/{id}")
	public ResultData deleteMenu(@PathVariable String id) {
		return sysMenuServiceImpl.deleteMenu(id);
	}
	
	/**
	 * 查询菜单树,用于角色赋权的树
	 * @return
	 */
	@GetMapping("/findMenuTree")
	public ResultData findMenuTree() {
		return sysMenuServiceImpl.findMenuTree();
	}

}
