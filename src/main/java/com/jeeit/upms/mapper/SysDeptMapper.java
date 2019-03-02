

package com.jeeit.upms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jeeit.upms.entity.SysDept;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author  傅枫
 * @since 2018-01-20
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

	/**
	 * 关联dept——relation
	 *
	 * @param delFlag 删除标记
	 * @return 数据列表
	 */
	List<SysDept> selectDeptDtoList(String delFlag);
}
