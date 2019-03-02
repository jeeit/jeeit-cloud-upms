

package com.jeeit.upms.service;

import com.baomidou.mybatisplus.service.IService;
import com.jeeit.upms.vo.PreLogVo;
import com.jeeit.upms.entity.SysLog;

import java.util.List;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author  傅枫
 * @since 2017-11-20
 */
public interface SysLogService extends IService<SysLog> {

	/**
	 * 通过ID删除日志（逻辑删除）
	 *
	 * @param id 日志ID
	 * @return true/false
	 */
	Boolean updateByLogId(Long id);

	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	Boolean insertLogs(List<PreLogVo> preLogVoList);
}
