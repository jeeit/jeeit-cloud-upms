

package com.jeeit.upms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jeeit.upms.entity.SysMenu;
import com.jeeit.upms.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author  傅枫
 * @since 2017-10-29
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 通过角色编号查询菜单
	 *
	 * @param role 角色编号
	 * @return
	 */
	List<MenuVO> findMenuByRoleCode(String role);

	/**
	 * 通过角色ID查询权限
	 *
	 * @param roleIds Ids
	 * @return
	 */
	List<String> findPermissionsByRoleIds(String roleIds);
}
