
-- 用户
insert into t_user(name, username) values 
('刘一', 'liuyi'),
('陈二', 'chener'),
('张三', 'zhangsan'),
('李四', 'lisi'),
('王五', 'wangwu'),
('赵六', 'zhaoliu'),
('孙七', 'sunqi'),
('周八', 'zhouba'),
('吴九', 'wujiu'),
('郑十', 'zhengshi');

-- 组织
insert into t_org(parent_id, org_name, org_code) values 
(0, '财务部', 'dept_fin'),
(0, '人事部', 'dept_hr'),
(0, '销售部', 'dept_sale'),
(0, '采购部', 'dept_purchase');

-- 角色
insert into t_role(role_name, role_code) values 
('管理员', 'admin'),
('经理', 'manager'),
('会计', 'accountant'),
('人事', 'hr'),
('销售员', 'salesman'),
('采购员', 'buyer');

-- 菜单
insert into t_menu(menu_name, menu_code, permission) values 
('用户管理', 'user_mgr', 'sys:user:mgr'),
('新增用户', 'user_add', 'sys:user:add'),
('修改用户', 'user_edit', 'sys:user:edit'),
('删除用户', 'user_del', 'sys:user:del'),
('组织管理', 'org_mgr', 'sys:org:mgr'),
('新增组织', 'org_add', 'sys:org:add'),
('修改组织', 'org_edit', 'sys:org:edit'),
('删除组织', 'org_del', 'sys:org:del'),
('角色管理', 'role_mgr', 'sys:role:mgr'),
('新增角色', 'role_add', 'sys:role:add'),
('修改角色', 'role_edit', 'sys:role:edit'),
('删除角色', 'role_del', 'sys:role:del');
-- 菜单 end

-- 用户组织关联
insert into t_user_org(user_id, org_id) values
((select id from t_user where name = '张三'),(select id from t_org where org_name = '财务部')),
((select id from t_user where name = '李四'),(select id from t_org where org_name = '人事部')),
((select id from t_user where name = '王五'),(select id from t_org where org_name = '销售部')),
((select id from t_user where name = '赵六'),(select id from t_org where org_name = '采购部')),
((select id from t_user where name = '孙七'),(select id from t_org where org_name = '财务部')),
((select id from t_user where name = '周八'),(select id from t_org where org_name = '人事部')),
((select id from t_user where name = '吴九'),(select id from t_org where org_name = '销售部')),
((select id from t_user where name = '郑十'),(select id from t_org where org_name = '采购部'));

-- 用户角色关联
insert into t_user_role(user_id, role_id) values
((select id from t_user where name = '刘一'),(select id from t_role where role_name = '管理员')),
((select id from t_user where name = '陈二'),(select id from t_role where role_name = '经理')),
((select id from t_user where name = '张三'),(select id from t_role where role_name = '经理')),
((select id from t_user where name = '李四'),(select id from t_role where role_name = '经理')),
((select id from t_user where name = '王五'),(select id from t_role where role_name = '经理')),
((select id from t_user where name = '赵六'),(select id from t_role where role_name = '经理')),
((select id from t_user where name = '孙七'),(select id from t_role where role_name = '会计')),
((select id from t_user where name = '周八'),(select id from t_role where role_name = '人事')),
((select id from t_user where name = '吴九'),(select id from t_role where role_name = '销售员')),
((select id from t_user where name = '郑十'),(select id from t_role where role_name = '采购员'));

-- 角色菜单关联
insert into t_role_menu(role_id, menu_id) values
((select id from t_role where role_name = '人事'),(select id from t_menu where menu_name = '用户管理')),
((select id from t_role where role_name = '人事'),(select id from t_menu where menu_name = '新增用户')),
((select id from t_role where role_name = '人事'),(select id from t_menu where menu_name = '修改用户')),
((select id from t_role where role_name = '人事'),(select id from t_menu where menu_name = '删除用户'));

