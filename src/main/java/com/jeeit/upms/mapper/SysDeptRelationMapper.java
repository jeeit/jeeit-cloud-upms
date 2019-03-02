

package com.jeeit.upms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jeeit.upms.entity.SysDeptRelation;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author  傅枫
 * @since 2018-02-12
 */
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation> {
	/**
	 * 删除部门关系表数据
	 *
	 * @param id 部门ID
	 */
	void deleteAllDeptRealtion(Integer id);

	/**
	 * 更改部分关系表数据
	 *
	 * @param deptRelation
	 */
	void updateDeptRealtion(SysDeptRelation deptRelation);

	/**
	 * 添加部分关系表数据
	 *
	 * @param deptRelation
	 */
	void insertDeptRealtion(SysDeptRelation deptRelation);

}
