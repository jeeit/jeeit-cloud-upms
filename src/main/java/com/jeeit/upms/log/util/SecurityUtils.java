

package com.jeeit.upms.log.util;


import cn.hutool.core.util.StrUtil;

import com.jeeit.upms.constant.SecurityConstants;
import com.jeeit.upms.service.OauthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 安全工具类
 *
 * @author L.cm
 */
public class SecurityUtils {

	/**
	 * 获取Authentication
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 */
	public static OauthUser getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof OauthUser) {
			return (OauthUser) principal;
		}
		return null;
	}

	public static String getClientId() {
		Authentication authentication = getAuthentication();
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
			return auth2Authentication.getOAuth2Request().getClientId();
		}
		return null;
	}

	/**
	 * 获取用户
	 */
	public static OauthUser getUser() {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return null;
		}
		return getUser(authentication);
	}

	/**
	 * 获取用户角色信息
	 *
	 * @return 角色集合
	 */
	public static List<String> getRoles() {
		Authentication authentication = getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<>();
		authorities.stream()
			.filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
			.forEach(granted -> roles.add(StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE)));
		return roles;
	}
}
