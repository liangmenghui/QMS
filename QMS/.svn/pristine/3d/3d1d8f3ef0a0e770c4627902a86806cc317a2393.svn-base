package com.unind.base.provider;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.unind.base.dbconnection.dao.internal.JdbcDao;
import com.unind.base.domain.admin.IdEntity;


public abstract class CommonImpl<T extends IdEntity> implements CommonService<T> {
	@Autowired
	protected JdbcDao jdbcDao;

	public void isRequired(String value, Object...args) throws ValidationException {
		isRequired(value, "{0}不能为空", args);
	}

	public void isRequired(String value, String message, Object...args) throws ValidationException {
		if(StringUtils.isEmpty(value) || StringUtils.isEmpty(value.trim())) {
			throw new ValidationException(MessageFormat.format(message, args));
		}
	}
}
