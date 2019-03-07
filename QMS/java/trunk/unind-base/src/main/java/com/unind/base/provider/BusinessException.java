package com.unind.base.provider;

public class BusinessException extends Exception {
	private static final long serialVersionUID = -2916417491174942974L;

	private String message;
    public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
