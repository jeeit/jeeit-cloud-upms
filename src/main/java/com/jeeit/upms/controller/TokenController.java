

package com.jeeit.upms.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.jeeit.upms.constant.SecurityConstants;
import com.jeeit.upms.feign.RemoteTokenService;
import com.jeeit.upms.util.R;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author  傅枫
 * @date 2018/9/4
 * token 管理
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class TokenController {
	private final RemoteTokenService remoteTokenService;

	/**
	 * 分页token 信息
	 *
	 * @param params 参数集
	 * @return token集合
	 */
	@GetMapping("/page")
	public Page token(@RequestParam Map<String, Object> params) {
		return remoteTokenService.selectPage(params, SecurityConstants.FROM_IN);
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_token_del')")
	public R<Boolean> delete(@PathVariable String id) {
		return remoteTokenService.deleteTokenById(id, SecurityConstants.FROM_IN);
	}
}
