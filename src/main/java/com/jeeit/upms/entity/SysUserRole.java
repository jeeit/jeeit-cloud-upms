

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
 * 用户角色表
 * </p>
 *
 * @author  傅枫
 * @since 2017-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
public class SysUserRole extends Model<SysUserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId(type = IdType.INPUT)
	private Integer userId;
	/**
	 * 角色ID
	 */
	@TableId(type = IdType.INPUT)
	private Integer roleId;


	@Override
	protected Serializable pkVal() {
		return this.userId;
	}
}
