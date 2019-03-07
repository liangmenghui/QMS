package com.unind.qms.web.approved.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.fs.dao.file.FsFileDao;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.approved.dao.ApprovedTermsScoreFileDao;
import com.unind.qms.web.approved.entity.ApprovedTermsScoreFile;
import com.unind.qms.web.approved.service.ApprovedTermsScoreFileService;
import com.unind.qms.web.basic.service.FileQmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class ApprovedTermsScoreFileImpl extends BaseOprService implements ApprovedTermsScoreFileService {
	@Autowired
	private ApprovedTermsScoreFileDao approvedTermsScoreFileDao;
	@Autowired
	private FsFileDao fsFileDao;
	@Autowired
	private FileQmsService fileQmsService;

	@Transactional
	public ApiResponseResult add(Long bsTermsScoreId, MultipartFile file) throws BusinessException {
		FsFile fsFile = new FsFile();
		ApiResponseResult result = fileQmsService.upload(fsFile, file);
		if(!result.isResult()) {
			return result;
		}
//		fsFile.setBsFilePath((String) result.getData());
//		fsFile.setBsCreatedTime(new Date());
//		fsFileDao.save(fsFile);

		ApprovedTermsScoreFile approvedTermsScoreFile = new ApprovedTermsScoreFile();
		approvedTermsScoreFile.setBsTermsScoreId(bsTermsScoreId);
		approvedTermsScoreFile.setFsFileId(fsFile.getId());
		approvedTermsScoreFile.setBsCreatedTime(new Date());
		approvedTermsScoreFileDao.save(approvedTermsScoreFile);

//		TransactionAspectSupport.currentTransactionStatus().createSavepoint();
		return ApiResponseResult.success().data(approvedTermsScoreFile);
	}

	@Transactional
	public ApiResponseResult delete(Long id) throws BusinessException {
		ApprovedTermsScoreFile o = approvedTermsScoreFileDao.findOne(id);
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
//		o.setBsIsDel(BaseEnumConstants.TRUE);
		approvedTermsScoreFileDao.delete(o);
		FsFile fsFile = fsFileDao.findOne(o.getFsFileId());
		fsFile.setBsIsDel(BaseEnumConstants.TRUE);
		fsFileDao.save(fsFile);
		return ApiResponseResult.success();
	}
}