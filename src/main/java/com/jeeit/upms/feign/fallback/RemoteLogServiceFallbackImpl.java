

package com.jeeit.upms.feign.fallback;

import com.jeeit.upms.entity.SysLog;
import com.jeeit.upms.feign.RemoteLogService;
import com.jeeit.upms.util.R;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author  傅枫
 * @date 2018/6/26
 */
@Slf4j
@Component
public class RemoteLogServiceFallbackImpl implements RemoteLogService {
	@Setter
	private Throwable cause;

	/**
	 * 保存日志
	 *
	 * @param sysLog
	 * @return R
	 */
	@Override
	public R<Boolean> saveLog(SysLog sysLog) {
		log.error("feign 插入日志失败", cause);
		return null;
	}
}
