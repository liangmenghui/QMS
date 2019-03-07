package com.web.settings.service;

import org.springframework.data.domain.PageRequest;

import com.app.base.data.ApiResponseResult;

/**
 * 基础设置
 */
public interface SettingService {

	public ApiResponseResult getlist( PageRequest pageRequest) throws Exception;

    public ApiResponseResult updateSetting(Float bomCheck) throws Exception;
}
