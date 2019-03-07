package com.unind.qms.web.samplePlan.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.samplePlan.dao.SampleLevelCodeNumDao;
import com.unind.qms.web.samplePlan.dao.SampleLevelRiskDao;
import com.unind.qms.web.samplePlan.dao.SampleLevelTotalCodeDao;
import com.unind.qms.web.samplePlan.entity.SampleLevelCodeNum;
import com.unind.qms.web.samplePlan.entity.SampleLevelRisk;
import com.unind.qms.web.samplePlan.entity.SampleLevelTotalCode;
import com.unind.qms.web.samplePlan.service.SampleLevelCodeNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 检验水平——样本代字——抽样数关系
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class SampleLevelCodeNumImpl extends BaseOprService implements SampleLevelCodeNumService {
    @Autowired
    private SampleLevelCodeNumDao sampleLevelCodeNumDao;
    @Autowired
    private SampleLevelRiskDao sampleLevelRiskDao;
    @Autowired
    private SampleLevelTotalCodeDao sampleLevelTotalCodeDao;

    @Override
    @Transactional
    public ApiResponseResult edit(SampleLevelCodeNum sampleLevelCodeNum) throws BusinessException {
        if(sampleLevelCodeNum == null || sampleLevelCodeNum.getId() == null){
            return ApiResponseResult.failure("记录ID为必填项！");
        }
        SampleLevelCodeNum o = sampleLevelCodeNumDao.findOne(sampleLevelCodeNum.getId());
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }

        //o.setBsCode(sampleLevelCodeNum.getBsCode());
        //o.setBsCodeNo(sampleLevelCodeNum.getBsCodeNo());
        o.setBsLevel0(sampleLevelCodeNum.getBsLevel0());
        o.setBsLevel1(sampleLevelCodeNum.getBsLevel1());
        o.setBsLevel2(sampleLevelCodeNum.getBsLevel2());
        o.setBsLevel3(sampleLevelCodeNum.getBsLevel3());
        o.setBsLevel4(sampleLevelCodeNum.getBsLevel4());
        o.setBsLevel5(sampleLevelCodeNum.getBsLevel5());
        o.setBsLevel6(sampleLevelCodeNum.getBsLevel6());
        o.setBsLevel7(sampleLevelCodeNum.getBsLevel7());
        o.setBsLevel8(sampleLevelCodeNum.getBsLevel8());
        o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
        o.setBsModifiedTime(new Date());
        sampleLevelCodeNumDao.save(o);
        return ApiResponseResult.success("修改成功！").data(o);
    }

    @Override
    @Transactional
    public ApiResponseResult getlist(String bsCode, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if(bsCode != null){
            filters.add(new SearchFilter("bsCode", SearchFilter.Operator.EQ, bsCode.trim()));
        }
        Specification<SampleLevelCodeNum> spec = Specifications.where(super.and(filters, SampleLevelCodeNum.class));
        Page<SampleLevelCodeNum> page = sampleLevelCodeNumDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }

    /**
     * 根据风险等级和批量获取抽样数
     * @param bsRiskLevel
     * @param bsTotal
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public ApiResponseResult getSampleNumber(Integer bsRiskLevel, Integer bsTotal) throws BusinessException{
        if(bsRiskLevel == null){
            return ApiResponseResult.failure("风险等级不能为空！");
        }
        if(bsTotal == null){
            return ApiResponseResult.failure("批量不能为空！");
        }

        Integer levelNo = null;  //检验水平序号（0到8分别代表R到T）
        String levelName = "";  //检验水平名称（R到T）
        Integer codeNo = null;  //样本代字序号（1到5分别代表A到E）
        String codeName = "";   //样本代字名称（A到E）
        String standardName = "";  //检验水平标准名称
        int score = 0;  //分数线（Ac=0，即0收1退）
        int number = 0;     //抽样数
        Map<String, Object> map = new HashMap<String, Object>();

        //1.获取检验水平序号
        //0到8分别代表R到T
        List<SampleLevelRisk> sampleLevelRiskList = sampleLevelRiskDao.findByBsIsDelAndAndBsRiskLevel(BooleanStateEnum.FALSE.intValue(), bsRiskLevel);
        if(sampleLevelRiskList.size() > 0){
            SampleLevelRisk o = sampleLevelRiskList.get(0);
            if(o != null){
                //1.1检验水平序号和名称
                if(o.getBsLevel0() == 1){
                    levelNo = 0;
                    levelName = "R";
                }
                if(o.getBsLevel1() == 1){
                    levelNo = 1;
                    levelName = "I";
                }
                if(o.getBsLevel2() == 1){
                    levelNo = 2;
                    levelName = "II";
                }
                if(o.getBsLevel3() == 1){
                    levelNo = 3;
                    levelName = "III";
                }
                if(o.getBsLevel4() == 1){
                    levelNo = 4;
                    levelName = "IV";
                }
                if(o.getBsLevel5() == 1){
                    levelNo = 5;
                    levelName = "V";
                }
                if(o.getBsLevel6() == 1){
                    levelNo = 6;
                    levelName = "VI";
                }
                if(o.getBsLevel7() == 1){
                    levelNo = 7;
                    levelName = "VII";
                }
                if(o.getBsLevel8() == 1){
                    levelNo = 8;
                    levelName = "T";
                }
                //1.2检验水平标准名称
                standardName = o.getBsStandardName();
                //1.3分数线
                score = o.getBsScore();
            }
        }

        //2.获取样本代字序号
        //1到5分别代表A到E
        List<SampleLevelTotalCode> sampleLevelTotalCodeList = sampleLevelTotalCodeDao.findByBsIsDel(BooleanStateEnum.FALSE.intValue());
        if(sampleLevelTotalCodeList.size() > 0){
            List<SampleLevelTotalCode> list = new ArrayList<SampleLevelTotalCode>();
            //2.1根据批量筛选数据
            if(bsTotal >= 30721){
                list = sampleLevelTotalCodeList.stream().filter(s -> s.getBsLowerLimit() == 30721).collect(Collectors.toList());
            }
            if(bsTotal >= 2 && bsTotal < 30721){
                list = sampleLevelTotalCodeList.stream().filter(s -> s.getBsLowerLimit() <= bsTotal && s.getBsUpperLimit() >= bsTotal).collect(Collectors.toList());
            }
            if(list.size() > 0 && levelNo != null){
                //2.2样本代字序号
                if(levelNo == 1){
                    codeNo = list.get(0).getBsLevel1();
                }
                if(levelNo == 2){
                    codeNo = list.get(0).getBsLevel2();
                }
                if(levelNo == 3){
                    codeNo = list.get(0).getBsLevel3();
                }
                if(levelNo == 4){
                    codeNo = list.get(0).getBsLevel4();
                }
                if(levelNo == 5){
                    codeNo = list.get(0).getBsLevel5();
                }
                if(levelNo == 6){
                    codeNo = list.get(0).getBsLevel6();
                }
                if(levelNo == 7){
                    codeNo = list.get(0).getBsLevel7();
                }
            }
        }

        //3.获取抽样数
        if(codeNo != null){
            List<SampleLevelCodeNum> sampleLevelCodeNumList = sampleLevelCodeNumDao.findByBsIsDelAndBsCodeNo(BooleanStateEnum.FALSE.intValue(), codeNo);
            if(sampleLevelCodeNumList.size() > 0){
                SampleLevelCodeNum o = sampleLevelCodeNumList.get(0);
                if(o != null && levelNo != null){
                    //3.1抽样数
                    if(levelNo == 0){
                        number = o.getBsLevel0();
                    }
                    if(levelNo == 1){
                        number = o.getBsLevel1();
                    }
                    if(levelNo == 2){
                        number = o.getBsLevel2();
                    }
                    if(levelNo == 3){
                        number = o.getBsLevel3();
                    }
                    if(levelNo == 4){
                        number = o.getBsLevel4();
                    }
                    if(levelNo == 5){
                        number = o.getBsLevel5();
                    }
                    if(levelNo == 6){
                        number = o.getBsLevel6();
                    }
                    if(levelNo == 7){
                        number = o.getBsLevel7();
                    }
                    if(levelNo == 8){
                        number = o.getBsLevel8();
                    }
                    //3.2样本代字名称
                    codeName = o.getBsCode();
                }
            }
        }

        //4.将数据封装到Map
        map.put("bsNum", number);
        map.put("bsStandardName", standardName);
        map.put("bsLevelName", levelName);
        map.put("bsScore", score);
        map.put("bsCodeName", codeName);

        return ApiResponseResult.success("获取抽样数成功！").data(map);
    }
}
