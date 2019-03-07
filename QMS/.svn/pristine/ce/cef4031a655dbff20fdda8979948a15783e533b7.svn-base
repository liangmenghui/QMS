package com.unind.qms.web.risk.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.fs.dao.file.FsFileDao;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.basic.service.FileQmsService;
import com.unind.qms.web.risk.dao.RiskEvidenceDao;
import com.unind.qms.web.risk.entity.RiskEvidence;
import com.unind.qms.web.risk.service.RiskEvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 风险证据描述
 * @author Shen
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class RiskEvidenceImpl extends BaseOprService implements RiskEvidenceService {

    @Autowired
    private RiskEvidenceDao riskEvidenceDao;
    @Autowired
    private FsFileDao fsFileDao;
    @Autowired
    private FileQmsService fileQmsService;

    @Override
    @Transactional
    public ApiResponseResult add(RiskEvidence riskEvidence, MultipartFile file) throws BusinessException {
        if(riskEvidence == null){
            return ApiResponseResult.failure("风险证据不能为空！");
        }
        if(riskEvidence.getBsPrId() == null && riskEvidence.getBsSuppId() == null){
            return ApiResponseResult.failure("产品和供应商不能同时为空！");
        }
        //1.上传文件
        FsFile fsFile = new FsFile();
        ApiResponseResult result = fileQmsService.upload(fsFile, file);
        if(!result.isResult()){
            return result;
        }

        //2.添加风险证据描述表
        riskEvidence.setFsFileId(fsFile.getId());
        riskEvidence.setBsCreatedTime(new Date());
        riskEvidenceDao.save(riskEvidence);
        return ApiResponseResult.success("新增成功！").data(riskEvidence);
    }

    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        RiskEvidence o = riskEvidenceDao.findOne(id);
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除！");
        }
        riskEvidenceDao.delete(o);

        FsFile fsFile = fsFileDao.findOne(o.getFsFileId());
        fsFile.setBsIsDel(BaseEnumConstants.TRUE);
        fsFileDao.save(fsFile);
        return ApiResponseResult.success("删除成功！");
    }

    @Override
    @Transactional
    public ApiResponseResult getlist(Long bsPrId, Long bsSuppId, Integer bsRiskNo, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if(bsPrId != null){
            filters.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, bsPrId));
        }
        if(bsSuppId != null){
            filters.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, bsSuppId));
        }
        if(bsRiskNo != null){
            filters.add(new SearchFilter("bsRiskNo", SearchFilter.Operator.EQ, bsRiskNo));
        }
        Specification<RiskEvidence> spec  = Specifications.where(super.and(filters, RiskEvidence.class));
        Page<RiskEvidence> page = riskEvidenceDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
