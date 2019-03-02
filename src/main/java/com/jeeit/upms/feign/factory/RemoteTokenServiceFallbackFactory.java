

package com.jeeit.upms.feign.factory;

import com.jeeit.upms.feign.fallback.RemoteTokenServiceFallbackImpl;
import com.jeeit.upms.feign.RemoteTokenService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author  傅枫
 * @date 2018/9/4
 */
@Component
public class RemoteTokenServiceFallbackFactory implements FallbackFactory<RemoteTokenService> {

	@Override
	public RemoteTokenService create(Throwable throwable) {
		RemoteTokenServiceFallbackImpl remoteTokenServiceFallback = new RemoteTokenServiceFallbackImpl();
		remoteTokenServiceFallback.setCause(throwable);
		return remoteTokenServiceFallback;
	}
}
