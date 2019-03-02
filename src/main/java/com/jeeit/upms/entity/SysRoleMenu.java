

package com.jeeit.upms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author  傅枫
 * @since 2017-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_menu")
public class SysRoleMenu extends Model<SysRoleMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@TableId(type = IdType.INPUT)
	private Integer roleId;
	/**
	 * 菜单ID
	 */
	@TableId(type = IdType.INPUT)
	private Integer menuId;

	@Override
	protected Serializable pkVal() {
		return this.roleId;
	}
}
