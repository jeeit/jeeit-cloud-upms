

package com.jeeit.upms.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.jeeit.upms.entity.SysUserRole;
import com.jeeit.upms.constant.CommonConstant;
import com.jeeit.upms.constant.SecurityConstants;
import com.jeeit.upms.constant.enums.EnumLoginType;
import com.jeeit.upms.dto.UserDTO;
import com.jeeit.upms.dto.UserInfo;
import com.jeeit.upms.entity.SysUser;
import com.jeeit.upms.log.annotation.SysLog;
import com.jeeit.upms.log.util.SecurityUtils;
import com.jeeit.upms.service.SysUserService;
import com.jeeit.upms.util.Query;
import com.jeeit.upms.util.R;
import com.jeeit.upms.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author  傅枫
 * @date 2017/10/28
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
	@Autowired
	private SysUserService userService;

	/**
	 * 获取当前用户信息（角色、权限）
	 * 并且异步初始化用户部门信息
	 *
	 * @param from     请求标志，该接口会被 auth、 前端调用
	 * @param username 用户名
	 * @return 用户名
	 */
	@GetMapping(value = {"/info", "/info/{username}"})
	public R<UserInfo> user(@PathVariable(required = false) String username,
							@RequestHeader(required = false) String from) {
		// 查询用户不为空时判断是不是内部请求
		if (StrUtil.isNotBlank(username) && !StrUtil.equals(SecurityConstants.FROM_IN, from)) {
			return new R<>(null, "error");
		}
		//为空时查询当前用户
		if (StrUtil.isBlank(username)) {
			username = SecurityUtils.getUser().getUsername();
		}

		return new R<>(userService.findUserInfo(EnumLoginType.PWD.getType(), username));
	}

	/**
	 * 通过ID查询当前用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public UserVO user(@PathVariable Integer id) {
		return userService.selectUserVoById(id);
	}

	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return R
	 */
	@SysLog("删除用户信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	@ApiOperation(value = "删除用户", notes = "根据ID删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path", example = "1000")
	public R<Boolean> userDel(@PathVariable Integer id) {
		SysUser sysUser = userService.selectById(id);
		return new R<>(userService.deleteUserById(sysUser));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@SysLog("添加用户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public R<Boolean> user(@RequestBody UserDTO userDto) {
		SysUser deletedUser = userService.selectDeletedUserByUsername(userDto.getUsername());
		if (deletedUser != null) {
			userService.deleteSysUserByUsernameAndUserId(userDto.getUsername(), deletedUser.getUserId());
		}
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setDelFlag(CommonConstant.STATUS_NORMAL);
		sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
		userService.insert(sysUser);
		userDto.getRole().forEach(roleId -> {
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(sysUser.getUserId());
			userRole.setRoleId(roleId);
			userRole.insert();
		});
		return new R<>(Boolean.TRUE);
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public R<Boolean> userUpdate(@Valid @RequestBody UserDTO userDto) {
		SysUser user = userService.selectById(userDto.getUserId());
		return new R<>(userService.updateUser(userDto, user.getUsername()));
	}

	/**
	 * 分页查询用户
	 *
	 * @param params 参数集
	 * @return 用户集合
	 */
	@GetMapping("/userPage")
	public Page userPage(@RequestParam Map<String, Object> params) {
		return userService.selectWithRolePage(new Query(params));
	}

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return success/false
	 */
	@PutMapping("/editInfo")
	public R<Boolean> editInfo(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto, SecurityUtils.getUser().getUsername());
	}
}
