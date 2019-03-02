

package com.jeeit.upms.log;

import com.jeeit.upms.feign.RemoteLogService;
import com.jeeit.upms.log.aspect.SysLogAspect;
import com.jeeit.upms.log.event.SysLogListener;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author  傅枫
 * @date 2018/6/28
 * 日志自动配置
 */
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
@EnableFeignClients({"com.pig4cloud.pigx.admin.api.feign"})
public class LogAutoConfiguration {
	private final RemoteLogService remoteLogService;

	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener(remoteLogService);
	}

	@Bean
	public SysLogAspect sysLogAspect() {
		return new SysLogAspect();
	}
}
