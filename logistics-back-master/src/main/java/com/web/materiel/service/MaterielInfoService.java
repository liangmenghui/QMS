package com.web.materiel.service;

import org.springframework.data.domain.PageRequest;

import com.app.base.data.ApiResponseResult;
import com.web.materiel.entity.MaterielInfo;

public interface MaterielInfoService {

    public ApiResponseResult add(MaterielInfo materielInfo) throws Exception;

    public ApiResponseResult edit(MaterielInfo materielInfo) throws Exception;

    public ApiResponseResult delete(Long id) throws Exception;

    public ApiResponseResult getlist(String mateK3Code, String mateName, PageRequest pageRequest) throws Exception;

    public ApiResponseResult getlistAll(String mateK3Code, String mateName, PageRequest pageRequest, PageRequest pageRequest2) throws Exception;
}
