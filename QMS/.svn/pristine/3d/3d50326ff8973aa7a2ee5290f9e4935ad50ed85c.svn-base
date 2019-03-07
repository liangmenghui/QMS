package com.unind.qms.web.supplier.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.fs.dao.file.FsFileDao;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.basic.service.FileQmsService;
import com.unind.qms.web.supplier.dao.CustomerApprovedFileDao;
import com.unind.qms.web.supplier.entity.CustomerApprovedFile;
import com.unind.qms.web.supplier.service.CustomerApprovedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 客户审核文件关联
 * @author Shen
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class CustomerApprovedFileImpl extends BaseOprService implements CustomerApprovedFileService {

    @Autowired
    private CustomerApprovedFileDao customerApprovedFileDao;
    @Autowired
    private FsFileDao fsFileDao;
    @Autowired
    private FileQmsService fileQmsService;

    @Override
    @Transactional
    public ApiResponseResult add(Long bsCustomerApprovedId, MultipartFile file) throws BusinessException {
        FsFile fsFile = new FsFile();
        ApiResponseResult result = fileQmsService.upload(fsFile, file);
        if(!result.isResult()) {
            return result;
        }

        CustomerApprovedFile customerApprovedFile = new CustomerApprovedFile();
        customerApprovedFile.setBsCustomerApprovedId(bsCustomerApprovedId);
        customerApprovedFile.setFsFileId(fsFile.getId());
        customerApprovedFile.setBsCreatedTime(new Date());
        customerApprovedFileDao.save(customerApprovedFile);

        return ApiResponseResult.success().data(customerApprovedFile);
    }

    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        CustomerApprovedFile o = customerApprovedFileDao.findOne(id);
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        customerApprovedFileDao.delete(o);

        FsFile fsFile = fsFileDao.findOne(o.getFsFileId());
        fsFile.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        fsFileDao.save(fsFile);
        return ApiResponseResult.success("删除成功！");
    }
}
