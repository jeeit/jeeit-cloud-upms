

package com.jeeit.upms.log.util;

import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import com.jeeit.upms.constant.CommonConstant;
import com.jeeit.upms.entity.SysLog;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统日志工具类
 *
 * @author L.cm
 */
public class SysLogUtils {
	public static SysLog getSysLog() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		SysLog sysLog = new SysLog();
		sysLog.setCreateBy(SecurityUtils.getUser().getUsername());
		sysLog.setType(CommonConstant.STATUS_NORMAL);
		sysLog.setRemoteAddr(HttpUtil.getClientIP(request));
		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader("user-agent"));
		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		sysLog.setServiceId(SecurityUtils.getClientId());
		return sysLog;
	}
}
