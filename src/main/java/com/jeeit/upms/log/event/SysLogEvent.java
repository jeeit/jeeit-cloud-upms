

package com.jeeit.upms.log.event;


import com.jeeit.upms.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author  傅枫
 * 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLog source) {
		super(source);
	}
}
