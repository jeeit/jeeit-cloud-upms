

package com.jeeit.upms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jeeit.upms.constant.CommonConstant;
import com.jeeit.upms.entity.SysMenu;
import com.jeeit.upms.vo.MenuVO;
import com.jeeit.upms.mapper.SysMenuMapper;
import com.jeeit.upms.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author  傅枫
 * @since 2017-10-29
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	@Cacheable(value = "menu_details", key = "#role  + '_menu'")
	public List<MenuVO> findMenuByRoleCode(String role) {
		return sysMenuMapper.findMenuByRoleCode(role);
	}

	@Override
	@CacheEvict(value = "menu_details", allEntries = true)
	public Boolean deleteMenu(Integer id) {
		// 删除当前节点
		SysMenu condition1 = new SysMenu();
		condition1.setMenuId(id);
		condition1.setDelFlag(CommonConstant.STATUS_DEL);
		this.updateById(condition1);

		// 删除父节点为当前节点的节点
		SysMenu conditon2 = new SysMenu();
		conditon2.setParentId(id);
		SysMenu sysMenu = new SysMenu();
		sysMenu.setDelFlag(CommonConstant.STATUS_DEL);
		return this.update(sysMenu, new EntityWrapper<>(conditon2));
	}

	@Override
	@CacheEvict(value = "menu_details", allEntries = true)
	public Boolean updateMenuById(SysMenu sysMenu) {
		return this.updateById(sysMenu);
	}
}
