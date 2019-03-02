

package com.jeeit.upms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jeeit.upms.constant.CommonConstant;
import com.jeeit.upms.vo.PreLogVo;
import com.jeeit.upms.entity.SysLog;
import com.jeeit.upms.mapper.SysLogMapper;
import com.jeeit.upms.service.SysLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author  傅枫
 * @since 2017-11-20
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

	@Override
	public Boolean updateByLogId(Long id) {

		SysLog sysLog = new SysLog();
		sysLog.setId(id);
		sysLog.setDelFlag(CommonConstant.STATUS_DEL);
		sysLog.setUpdateTime(LocalDateTime.now());
		return updateById(sysLog);
	}

	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	@Override
	public Boolean insertLogs(List<PreLogVo> preLogVoList) {
		List<SysLog> sysLogs = new ArrayList<>();
		preLogVoList.forEach(pre -> {
			SysLog log = new SysLog();
			log.setType(CommonConstant.STATUS_LOCK);
			log.setTitle(pre.getInfo());
			log.setException(pre.getStack());
			log.setParams(pre.getMessage());
			log.setCreateTime(LocalDateTime.now());
			log.setRequestUri(pre.getUrl());
			log.setCreateBy(pre.getUser());
			sysLogs.add(log);
		});
		return this.insertBatch(sysLogs);
	}
}
