package com.unind.base.provider.admin.agg.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.unind.base.dao.admin.SysPermDao;
import com.unind.base.dao.admin.SysResrceDao;
import com.unind.base.dao.admin.agg.SysResrcePermAggDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.agg.SysResrcePermAgg;
import com.unind.base.domain.admin.perm.SysPerm;
import com.unind.base.domain.admin.resrce.SysResrce;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.admin.agg.SysResrcePermAggService;

@Service
public class SysResrcePermAggServiceImpl extends BaseOprService implements SysResrcePermAggService {

	@Autowired
	private SysResrceDao sysResrceDao;

	@Autowired
	private SysPermDao sysPermDao;

	@Autowired
	private SysResrcePermAggDao sysResrcePermAggDao;

	/**
	 * 给资源配置权限 ,用以资源关联权限
	 * 
	 * @param resrceId
	 *            资源主键id
	 * @param premIds
	 *            权限主键id
	 * @return
	 */
	@Transactional
	public ApiResponseResult save(Long resrceId, Long[] premIds) {
		if (resrceId == null || premIds == null) {
			return ApiResponseResult.failure("参数异常");
		}

		SysResrce sysResrce = sysResrceDao.findOne(resrceId);
		if (sysResrce == null) {
			return ApiResponseResult.failure("资源不存在");
		}

		// 删除资源配置的权限
		sysResrcePermAggDao.deleteByPkResrce(resrceId);

		SysPerm sysPerm = null;
		SysResrcePermAgg sysResrcePermAgg = null;
		for (Long permId : premIds) {
			sysPerm = sysPermDao.findOne(permId);
			if (sysPerm != null) {
				sysResrcePermAgg = new SysResrcePermAgg();
				sysResrcePermAgg.setPkResrce(resrceId);
				sysResrcePermAgg.setPkPerm(permId);

				// 插入已选的权限
				sysResrcePermAggDao.save(sysResrcePermAgg);
			}
		}

		return ApiResponseResult.success();
	}

	@Override
	public ApiResponseResult getlist(Long pkResrce) {
		Map<String, Object> map = new HashMap<String, Object>();
		//查询所有权限列表
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		Specifications<SysPerm> spec1 = Specifications.where(super.and(filters, SysPerm.class));
		List<SysPerm> perms = sysPermDao.findAll(spec1);
		map.put("perms", perms);

		//查询已分配的权限列表
		filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter("pkResrce", Operator.EQ, pkResrce));
		Specifications<SysResrcePermAgg> spec2 = Specifications.where(super.and(filters, SysResrcePermAgg.class));
		List<SysResrcePermAgg> alloced = sysResrcePermAggDao.findAll(spec2);
		map.put("alloced", alloced);
		return ApiResponseResult.success().data(map);
	}

}
