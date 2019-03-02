

package com.jeeit.upms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jeeit.upms.entity.SysRoleMenu;
import com.jeeit.upms.mapper.SysRoleMenuMapper;
import com.jeeit.upms.service.SysRoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author  傅枫
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
	private final CacheManager cacheManager;

	/**
	 * @param role
	 * @param roleId  角色
	 * @param menuIds 菜单ID拼成的字符串，每个id之间根据逗号分隔
	 * @return
	 */
	@Override
	@CacheEvict(value = "menu_details", key = "#role + '_menu'")
	public Boolean insertRoleMenus(String role, Integer roleId, String menuIds) {
		SysRoleMenu condition = new SysRoleMenu();
		condition.setRoleId(roleId);
		this.delete(new EntityWrapper<>(condition));

		if (StrUtil.isBlank(menuIds)) {
			return Boolean.TRUE;
		}

		List<SysRoleMenu> roleMenuList = new ArrayList<>();
		List<String> menuIdList = Arrays.asList(menuIds.split(","));

		for (String menuId : menuIdList) {
			SysRoleMenu roleMenu = new SysRoleMenu();
			roleMenu.setRoleId(roleId);
			roleMenu.setMenuId(Integer.valueOf(menuId));
			roleMenuList.add(roleMenu);
		}

		//清空userinfo
		cacheManager.getCache("user_details").clear();
		return this.insertBatch(roleMenuList);
	}
}
