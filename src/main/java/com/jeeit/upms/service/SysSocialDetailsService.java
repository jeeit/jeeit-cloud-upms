

package com.jeeit.upms.service;

import com.baomidou.mybatisplus.service.IService;
import com.jeeit.upms.entity.SysSocialDetails;

import java.util.Map;

/**
 * 系统社交登录账号表
 *
 * @author pigx code generator
 * @date 2018-08-16 21:30:41
 */
public interface SysSocialDetailsService extends IService<SysSocialDetails> {

	/**
	 * 绑定社交账号
	 *
	 * @param state 类型
	 * @param code  code
	 * @return
	 */
	Boolean bindSocial(String state, String code);

	/**
	 * 通过客户端信息查询openId
	 *
	 * @param inStr appid @ code
	 * @return
	 */
	Map<String, String> findOpenId(String inStr);
}

