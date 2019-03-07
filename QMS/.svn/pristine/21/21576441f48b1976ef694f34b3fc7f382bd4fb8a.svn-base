package com.unind.qms.web.approved.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.fs.dao.file.FsFileDao;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.approved.dao.ApprovedItemsRecordFileDao;
import com.unind.qms.web.approved.entity.ApprovedItemsRecordFile;
import com.unind.qms.web.approved.service.ApprovedItemsRecordFileService;
import com.unind.qms.web.basic.service.FileQmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class ApprovedItemsRecordFileImpl extends BaseOprService implements ApprovedItemsRecordFileService {
	@Autowired
	private ApprovedItemsRecordFileDao approvedItemsRecordFileDao;
	@Autowired
	private FsFileDao fsFileDao;
	@Autowired
	private FileQmsService fileQmsService;

	@Transactional
	public ApiResponseResult add(Long bsItemsRecordId, MultipartFile file) throws BusinessException {
		FsFile fsFile = new FsFile();
		ApiResponseResult result = fileQmsService.upload(fsFile, file);
		if(!result.isResult()) {
			return result;
		}
//		fsFile.setBsFilePath((String) result.getData());
//		fsFile.setBsCreatedTime(new Date());
//		fsFileDao.save(fsFile);

		ApprovedItemsRecordFile approvedItemsRecordFile = new ApprovedItemsRecordFile();
		approvedItemsRecordFile.setBsItemsRecordId(bsItemsRecordId);
		approvedItemsRecordFile.setFsFileId(fsFile.getId());
		approvedItemsRecordFile.setBsCreatedTime(new Date());
		approvedItemsRecordFileDao.save(approvedItemsRecordFile);

//		TransactionAspectSupport.currentTransactionStatus().createSavepoint();
		return ApiResponseResult.success().data(approvedItemsRecordFile);
	}

	@Transactional
	public ApiResponseResult delete(Long id) throws BusinessException {
		ApprovedItemsRecordFile o = approvedItemsRecordFileDao.findOne(id);
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
//		o.setBsIsDel(BaseEnumConstants.TRUE);
		approvedItemsRecordFileDao.delete(o);
		FsFile fsFile = fsFileDao.findOne(o.getFsFileId());
		fsFile.setBsIsDel(BaseEnumConstants.TRUE);
		fsFileDao.save(fsFile);
		return ApiResponseResult.success();
	}
}
