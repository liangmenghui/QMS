package com.web.cost.service;

import com.app.base.data.ApiResponseResult;
import com.web.cost.entity.SMTPoints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * SMT点数
 *
 */
public interface SMTPointsService {

    public ApiResponseResult getTreeList(Integer isSet, String keyword, Integer parentId, Integer sLevel, PageRequest pageRequest) throws Exception;

    public List<SMTPoints> getCategory(Integer parentId) throws Exception;

    public Page<SMTPoints> getPoints(Integer setStatus, String keyword, Integer categoryId, PageRequest pageRequest)throws Exception;

    public ApiResponseResult updatePoints(SMTPoints smtPoints) throws Exception;
}
