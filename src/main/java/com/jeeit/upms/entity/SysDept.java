

package com.jeeit.upms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author  傅枫
 * @since 2018-01-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "dept_id", type = IdType.AUTO)
	private Integer deptId;
	/**
	 * 部门名称
	 */
	@NotBlank(message = "部门名称不能为空")
	private String name;
	/**
	 * 排序
	 */
	private Integer orderNum;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	@TableLogic
	private String delFlag;

	private Integer parentId;


	@Override
	protected Serializable pkVal() {
		return this.deptId;
	}
}
