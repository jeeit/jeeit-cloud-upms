

package com.jeeit.upms.exception;

import lombok.NoArgsConstructor;

/**
 * @author  傅枫
 * @date 2018年06月22日16:22:03
 * 403 授权拒绝
 */
@NoArgsConstructor
public class OauthDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OauthDeniedException(String message) {
		super(message);
	}

	public OauthDeniedException(Throwable cause) {
		super(cause);
	}

	public OauthDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public OauthDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
