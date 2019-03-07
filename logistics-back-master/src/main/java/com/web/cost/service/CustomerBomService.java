package com.web.cost.service;

import com.app.base.data.ApiResponseResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * 客户BOM成本
 */
public interface CustomerBomService {

    public ApiResponseResult importBom(MultipartFile file, Integer startRow) throws Exception;

    public ApiResponseResult getK3Bom(String standardCol,String categoryCol,String quantityCol,String packageCol,String makerCol,String splitList,String fileId) throws Exception;

    public ApiResponseResult getBomMatch(Long cusBomId,String mateCategory, float num, Integer num2) throws Exception;

    public ApiResponseResult getBomList(String keyWord, PageRequest pageRequest) throws Exception;

    public ApiResponseResult addRemark(Long id, String remark) throws Exception;

    public ApiResponseResult delete(Long fileId) throws Exception;

    public ApiResponseResult getBomData(Long fileId) throws Exception;
    
    public ApiResponseResult doCheckMateriel(Long id, int checkStatus) throws Exception;
    
}
