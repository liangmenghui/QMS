package com.unind.base.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
public class AppConfig {
	@Autowired
	private Environment env;

	public String getString(String key) {
		return env.getProperty(key);
	}

	public String getString(String key, String defVal) {
		return env.getProperty(key, defVal);
	}

	public Integer getInt(String key) {
		return env.getProperty(key, Integer.class);
	}

	public int getInt(String key, int defVal) {
		return env.getProperty(key, Integer.class, defVal);
	}

	public boolean getBoolean(String key) {
		return env.getProperty(key, Boolean.class);
	}

	public boolean getBoolean(String key, boolean defVal) {
		return env.getProperty(key, Boolean.class, defVal);
	}

}
