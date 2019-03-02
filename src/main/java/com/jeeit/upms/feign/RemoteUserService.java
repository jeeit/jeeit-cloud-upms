

package com.jeeit.upms.feign;

import com.jeeit.upms.constant.ServiceNameConstant;
import com.jeeit.upms.dto.UserInfo;
import com.jeeit.upms.feign.factory.RemoteUserServiceFallbackFactory;
import com.jeeit.upms.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author  傅枫
 * @date 2018/6/22
 */
@FeignClient(value = ServiceNameConstant.UMPS_SERVICE, fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
    R<UserInfo> info(@PathVariable("username") String username
            , @RequestHeader("from") String from);

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	R<UserInfo> social(@PathVariable("inStr") String inStr);
}
