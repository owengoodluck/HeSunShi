package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysRole;
import com.xseagull.boxing.features.service.ISysRoleService;

@RestController
@RequestMapping("/sys_role")
public class SysRoleController {

	@Autowired
	private ISysRoleService sysRoleServiceImpl;

	/**
	 * 查询角色用户管理分配角色
	 * 
	 * @return
	 */
	@GetMapping("/findRole")
	public ResultData findRole() {
		return sysRoleServiceImpl.findRole();
	}

	/**
	 * 新增角色
	 * 
	 * @param role
	 * @return
	 */
	@PostMapping("/insertRole")
	public ResultData insertRole(SysRole role) {
		return sysRoleServiceImpl.insertRole(role);
	}

	/**
	 * 分页查询角色信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param roleName
	 * @return
	 */
	@GetMapping("/queryRoleByPage")
	public ResultData queryRoleByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "roleName") String roleName) {
		return sysRoleServiceImpl.queryRoleByPage(pageNo, pageSize, roleName);
	}

	/**
	 * 更新角色
	 * 
	 * @param role
	 * @return
	 */
	@PostMapping("/updateRole")
	public ResultData updateRole(SysRole role) {
		return sysRoleServiceImpl.updateRole(role);
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteRole/{id}")
	public ResultData deleteRole(@PathVariable String id) {
		return sysRoleServiceImpl.deleteRole(id);
	}

	/**
	 * 获取角色的权限用于回显
	 * @param id
	 * @return
	 */
	@GetMapping("/getRoleEmpower/{id}")
	public ResultData getRoleEmpower(@PathVariable String id) {
		return sysRoleServiceImpl.getRoleEmpower(id);
	}
	
	/**
	 * 给角色赋权
	 * @param Role
	 * @return
	 */
	@PostMapping("/roleEmpower")
	public ResultData roleEmpower(SysRole sysRole) {
		return sysRoleServiceImpl.roleEmpower(sysRole);
	}
	
	/**
	 * 批量启用角色
	 * 
	 * @param userIds
	 * @return
	 */
	@PostMapping("/enable/{roleIds}")
	public ResultData enableRoles(@PathVariable String roleIds) {
		return sysRoleServiceImpl.enableRoles(roleIds);
	}

	/**
	 * 批量禁用角色
	 * 
	 * @param userIds
	 * @return
	 */
	@PostMapping("/disable/{roleIds}")
	public ResultData disableRoles(@PathVariable String roleIds) {
		return sysRoleServiceImpl.disableRoles(roleIds);
	}
}
