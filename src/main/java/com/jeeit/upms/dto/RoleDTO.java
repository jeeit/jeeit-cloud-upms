

package com.jeeit.upms.dto;

import com.jeeit.upms.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author  傅枫
 * @date 2018/1/20
 * 角色Dto
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends SysRole {
	/**
	 * 角色部门Id
	 */
	private Integer roleDeptId;

	/**
	 * 部门名称
	 */
	private String deptName;
}
