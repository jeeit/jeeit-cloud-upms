

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
 * 角色与部门对应关系
 * </p>
 *
 * @author  傅枫
 * @since 2018-01-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_dept")
public class SysRoleDept extends Model<SysRoleDept> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 部门ID
	 */
	private Integer deptId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
