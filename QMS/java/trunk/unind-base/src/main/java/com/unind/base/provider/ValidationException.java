package com.unind.base.provider;

/**
 * 参数校验异常类
 * @author tanxiang
 *
 */
public class ValidationException extends BusinessException {
	private static final long serialVersionUID = 2296261755542427299L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ValidationException(String message) {
		super(message);
		this.message = message;
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
}
