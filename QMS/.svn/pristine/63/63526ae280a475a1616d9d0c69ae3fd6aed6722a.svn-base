package com.unind.qms.web.shipment.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.fs.dao.file.FsFileDao;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.basic.service.FileQmsService;
import com.unind.qms.web.shipment.dao.ShipmentInspectFileDao;
import com.unind.qms.web.shipment.dao.ShipmentInspectRecordFileDao;
import com.unind.qms.web.shipment.entity.ShipmentInspectFile;
import com.unind.qms.web.shipment.entity.ShipmentInspectRecord;
import com.unind.qms.web.shipment.entity.ShipmentInspectRecordFile;
import com.unind.qms.web.shipment.service.ShipmentInspectFileService;
import com.unind.qms.web.shipment.service.ShipmentInspectRecordFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class ShipmentInspectRecordFileImpl extends BaseOprService implements ShipmentInspectRecordFileService {
	@Autowired
	private ShipmentInspectRecordFileDao shipmentInspectRecordFileDao;
	@Autowired
	private FsFileDao fsFileDao;
	@Autowired
	private FileQmsService fileQmsService;

	@Transactional
	public ApiResponseResult add(Long bsShipmentRecordId, MultipartFile file) throws BusinessException {
		FsFile fsFile = new FsFile();
		ApiResponseResult result = fileQmsService.upload(fsFile, file);
		if(!result.isResult()) {
			return result;
		}
//		fsFile.setBsFilePath((String) result.getData());
//		fsFile.setBsCreatedTime(new Date());
//		fsFileDao.save(fsFile);

		ShipmentInspectRecordFile shipmentInspectRecordFile = new ShipmentInspectRecordFile();
		shipmentInspectRecordFile.setBsShipmentRecordId(bsShipmentRecordId);
		shipmentInspectRecordFile.setFsFileId(fsFile.getId());
		shipmentInspectRecordFile.setBsCreatedTime(new Date());
		shipmentInspectRecordFileDao.save(shipmentInspectRecordFile);

//		TransactionAspectSupport.currentTransactionStatus().createSavepoint();
		return ApiResponseResult.success().data(shipmentInspectRecordFile);
	}

	@Transactional
	public ApiResponseResult delete(Long id) throws BusinessException {
		ShipmentInspectRecordFile o = shipmentInspectRecordFileDao.findOne(id);
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
//		o.setBsIsDel(BaseEnumConstants.TRUE);
		shipmentInspectRecordFileDao.delete(o);
		FsFile fsFile = fsFileDao.findOne(o.getFsFileId());
		fsFile.setBsIsDel(BaseEnumConstants.TRUE);
		fsFileDao.save(fsFile);
		return ApiResponseResult.success();
	}
}
