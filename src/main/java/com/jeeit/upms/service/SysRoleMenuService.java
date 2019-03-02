

package com.jeeit.upms.service;


import com.baomidou.mybatisplus.service.IService;
import com.jeeit.upms.entity.SysRoleMenu;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author  傅枫
 * @since 2017-10-29
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

	/**
	 * 更新角色菜单
	 *
	 * @param role
	 * @param roleId  角色
	 * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return
	 */
	Boolean insertRoleMenus(String role, Integer roleId, String menuIds);
}
