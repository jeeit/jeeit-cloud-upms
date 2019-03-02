

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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author  傅枫
 * @since 2017-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict")
public class SysDict extends Model<SysDict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 数据值
	 */
	@NotBlank(message = "字典项数据值不能为空")
	private String value;
	/**
	 * 标签名
	 */
	@NotBlank(message = "字典项标签不能为空")
	private String label;
	/**
	 * 类型
	 */
	@NotBlank(message = "字典项数据类型不能为空")
	private String type;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 排序（升序）
	 */
	private BigDecimal sort;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
