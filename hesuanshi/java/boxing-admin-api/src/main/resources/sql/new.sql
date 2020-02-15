INSERT INTO `sys_menu` VALUES ('1', '0', '顶级', null, null, null, null);
INSERT INTO `sys_menu` VALUES ('2', '1', '系统管理', 'sysManage:show', null, '', '1');
INSERT INTO `sys_menu` VALUES ('3', '2', '机构管理', 'deptManage:show', null, '', '1');
INSERT INTO `sys_menu` VALUES ('4', '3', '新增机构', 'deptManage:add', null, '', '1');
INSERT INTO `sys_menu` VALUES ('5', '3', '修改机构', 'deptManage:edit', null, '', '1');
INSERT INTO `sys_menu` VALUES ('6', '3', '删除机构', 'deptManage:del', null, '', '1');
INSERT INTO `sys_menu` VALUES ('7', '2', '角色管理', 'roleManage:show', null, '', '1');
INSERT INTO `sys_menu` VALUES ('8', '7', '新增角色', 'roleManage:add', null, '', '1');
INSERT INTO `sys_menu` VALUES ('9', '7', '修改角色', 'roleManage:edit', null, '', '1');
INSERT INTO `sys_menu` VALUES ('10', '7', '删除角色', 'roleManage:del', null, '', '1');
INSERT INTO `sys_menu` VALUES ('11', '7', '权限配置', 'roleManage:empower', null, '', '1');
INSERT INTO `sys_menu` VALUES ('12', '2', '系统用户管理', 'sysUserManage:show', null, '', '1');
INSERT INTO `sys_menu` VALUES ('13', '12', '新增系统用户', 'sysUserManage:add', null, '', '1');
INSERT INTO `sys_menu` VALUES ('16', '12', '角色分配', 'sysUserManage:assignRole', null, '', '1');
INSERT INTO `sys_menu` VALUES ('17', '2', '菜单管理', 'menuManage:show', null, '', '1');
INSERT INTO `sys_menu` VALUES ('18', '17', '新增菜单', 'menuManage:add', null, '', '1');
INSERT INTO `sys_menu` VALUES ('19', '17', '修改菜单', 'menuManage:edit', null, '', '1');
INSERT INTO `sys_menu` VALUES ('20', '17', '删除菜单', 'menuManage:del', null, '', '1');

INSERT INTO `sys_user` VALUES ('1', '管理员', 'admin', 'dc483e80a7a0bd9ef71d8cf973673924', '1', '1', '2019-04-09 22:39:21');
INSERT INTO `sys_dept` VALUES ('1', '管理部', '');
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '2,3,4,5,6,7,8,9,10,11,12,13,16,17,18,19,20', '');

INSERT INTO `sys_config` VALUES ('1', 'advance_payment', '200', '2019-04-09 22:39:21','2019-04-09 22:39:21','预付款金额');
