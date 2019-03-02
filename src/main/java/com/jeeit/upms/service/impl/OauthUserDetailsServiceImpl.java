

package com.jeeit.upms.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.jeeit.upms.constant.CommonConstant;
import com.jeeit.upms.constant.SecurityConstants;
import com.jeeit.upms.dto.UserInfo;
import com.jeeit.upms.util.R;
import com.jeeit.upms.entity.SysUser;
import com.jeeit.upms.feign.RemoteUserService;
import com.jeeit.upms.service.OauthUser;
import com.jeeit.upms.service.OauthUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户详细信息
 *
 * @author 傅枫
 */
@Slf4j
@Service
@AllArgsConstructor
public class OauthUserDetailsServiceImpl implements OauthUserDetailsService {
	private final RemoteUserService remoteUserService;

	/**
	 * 用户密码登录
	 *
	 * @param username 用户名
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
		return getUserDetails(result);
	}


	/**
	 * 根据社交登录code 登录
	 *
	 * @param inStr TYPE@CODE
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserBySocial(String inStr) throws UsernameNotFoundException {
		return getUserDetails(remoteUserService.social(inStr));
	}

	/**
	 * 构建userdetails
	 *
	 * @param result 用户信息
	 * @return
	 */
	private UserDetails getUserDetails(R<UserInfo> result) {
		if (result == null || result.getData() == null) {
			throw new UsernameNotFoundException("用户不存在");
		}

		UserInfo info = result.getData();
		Set<String> dbAuthsSet = new HashSet<>();
		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
			// 获取资源
			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

		}
		Collection<? extends GrantedAuthority> authorities
			= AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUser user = info.getSysUser();

		// 构造security用户
		return new OauthUser(user.getUserId(), user.getDeptId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(),
			StrUtil.equals(user.getDelFlag(), CommonConstant.STATUS_NORMAL),true, true, true, authorities);
	}
}
