

package com.jeeit.upms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 部门关系表
 * </p>
 *
 * @author  傅枫
 * @since 2018-01-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dept_relation")
public class SysDeptRelation extends Model<SysDeptRelation> {

	private static final long serialVersionUID = 1L;

	/**
	 * 祖先节点
	 */
	private Integer ancestor;
	/**
	 * 后代节点
	 */
	private Integer descendant;


	@Override
	protected Serializable pkVal() {
		return this.ancestor;
	}

}
