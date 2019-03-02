

package com.jeeit.upms.feign.factory;

import com.jeeit.upms.feign.fallback.RemoteLogServiceFallbackImpl;
import com.jeeit.upms.feign.RemoteLogService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author  傅枫
 * @date 2018/9/4
 */
@Component
public class RemoteLogServiceFallbackFactory implements FallbackFactory<RemoteLogService> {

	@Override
	public RemoteLogService create(Throwable throwable) {
		RemoteLogServiceFallbackImpl remoteLogServiceFallback = new RemoteLogServiceFallbackImpl();
		remoteLogServiceFallback.setCause(throwable);
		return remoteLogServiceFallback;
	}
}
