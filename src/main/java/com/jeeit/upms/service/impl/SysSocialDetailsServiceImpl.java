

package com.jeeit.upms.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jeeit.upms.constant.SecurityConstants;
import com.jeeit.upms.log.util.SecurityUtils;
import com.jeeit.upms.constant.enums.EnumLoginType;
import com.jeeit.upms.entity.SysSocialDetails;
import com.jeeit.upms.entity.SysUser;
import com.jeeit.upms.mapper.SysSocialDetailsMapper;
import com.jeeit.upms.mapper.SysUserMapper;
import com.jeeit.upms.service.SysSocialDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  傅枫
 * @date 2018年08月16日
 */
@Slf4j
@AllArgsConstructor
@Service
public class SysSocialDetailsServiceImpl extends ServiceImpl<SysSocialDetailsMapper, SysSocialDetails> implements SysSocialDetailsService {
	private final CacheManager cacheManager;
	private final RestTemplate restTemplate;
	private final SysUserMapper sysUserMapper;

	/**
	 * 通过客户端信息查询openId
	 *
	 * @param inStr appid @ code
	 * @return
	 */
	@Override
	public Map<String, String> findOpenId(String inStr) {
		String[] inStrs = inStr.split("@");
		return getOpenId(inStrs[0], inStrs[1]);
	}

	/**
	 * 绑定社交账号
	 *
	 * @param appId appId
	 * @param code  code
	 * @return
	 */
	@Override
	public Boolean bindSocial(String appId, String code) {
		Map<String, String> result = getOpenId(appId, code);

		SysUser sysUser = sysUserMapper.selectById(SecurityUtils.getUser().getId());
		sysUser.setWxOpenid(result.get("openId"));

		sysUserMapper.updateAllColumnById(sysUser);
		//更新緩存
		cacheManager.getCache("user_details").evict(result.get("openId"));
		return Boolean.TRUE;
	}

	/**
	 * 通过appid 、code 获得openID
	 *
	 * @param appId
	 * @param code
	 * @return
	 */
	private Map<String, String> getOpenId(String appId, String code) {
		SysSocialDetails condition = new SysSocialDetails();
		condition.setAppId(appId);
		SysSocialDetails socialDetails = this.baseMapper.selectOne(condition);

		String openId = null;
		//微信登录
		if (EnumLoginType.WECHAT.getType().equals(socialDetails.getType())) {
			String url = String.format(SecurityConstants.WX_AUTHORIZATION_CODE_URL
				, socialDetails.getAppId(), socialDetails.getAppSecret(), code);
			String result = restTemplate.getForObject(url, String.class);
			log.debug("微信响应报文:{}", result);

			Object obj = JSONUtil.parseObj(result).get("openid");
			if (obj == null) {
				return null;
			}
			openId = obj.toString();
		}

		Map<String, String> result = new HashMap<>(2);
		result.put("type", socialDetails.getType());
		result.put("openId", openId);
		return result;
	}

}
