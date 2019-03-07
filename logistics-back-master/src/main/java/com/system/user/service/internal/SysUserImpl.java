package com.system.user.service.internal;

import com.app.base.data.ApiResponseResult;
import com.app.base.data.DataGrid;
import com.app.base.utils.MD5Util;
import com.auth0.jwt.JWT;
import com.system.role.dao.UserRolesMapDao;
import com.system.role.entity.UserRolesMap;
import com.system.role.service.SysRoleService;
import com.system.user.dao.SysUserDao;
import com.system.user.entity.SysUser;
import com.system.user.service.SysUserService;

import com.utils.UserUtil;
import com.utils.enumeration.BasicStateEnum;
import com.web.supplier.dao.SupplierInfoDao;
import com.web.supplier.entity.SupplierInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service(value = "sysUserService")
@Transactional(propagation = Propagation.REQUIRED)
public class SysUserImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private UserRolesMapDao userRolesMapDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;

    /**
     * 注册新用户
     * @param sysUser
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult add(SysUser sysUser) throws Exception{
        if(sysUser == null){
            return ApiResponseResult.failure("用户不能为空！");
        }
        if(StringUtils.isEmpty(sysUser.getUserCode()) || StringUtils.isEmpty(sysUser.getUserName())){
            return ApiResponseResult.failure("用户名或名称不能为空！");
        }
        //admin和super为管理员用户，无法操作
        if("admin".equals(sysUser.getUserCode().trim()) || "super".equals(sysUser.getUserCode().trim())){
            return ApiResponseResult.failure("此用户名为管理员用户，无法添加！");
        }
        int count = sysUserDao.countByIsDelAndUserCode(0, sysUser.getUserCode());
        if(count > 0){
            return ApiResponseResult.failure("用户名已存在，请填写其他用户名！");
        }

        sysUser.setUserCode(sysUser.getUserCode().trim());
        sysUser.setUserName(sysUser.getUserName().trim());
        //密码为空时默认为“123456”
        if(StringUtils.isEmpty(sysUser.getUserPassword())){
            sysUser.setUserPassword(MD5Util.MD5("123456"));
        }else{
            sysUser.setUserPassword(MD5Util.MD5(sysUser.getUserPassword()));
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        sysUser.setCreatedTime(new Date());
        sysUser.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
        sysUserDao.save(sysUser);
        return ApiResponseResult.success("用户添加成功！");
    }

    /**
     * 编辑用户
     * @param sysUser
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult edite(SysUser sysUser) throws Exception {
        // TODO Auto-generated method stub
        if(sysUser == null){
            return ApiResponseResult.failure("用户不能为空！");
        }
        if(StringUtils.isEmpty(sysUser.getId().toString()) ||
                StringUtils.isEmpty(sysUser.getUserCode()) ||
                StringUtils.isEmpty(sysUser.getUserName())){
            return ApiResponseResult.failure("ID或用户名或名称不能为空！");
        }

        //1.修改用户信息
        //admin和super为管理员用户，无法操作
        if("admin".equals(sysUser.getUserCode().trim()) || "super".equals(sysUser.getUserCode().trim())){
            return ApiResponseResult.failure("此用户名为管理员用户，无法编辑！");
        }
        Optional<SysUser> us = sysUserDao.findById(sysUser.getId());
        if(us == null){
            return ApiResponseResult.failure("用户不存在！");
        }
        SysUser u = us.get();
        String originalCode = u.getUserCode();  //原用户编号，供后面供应商用户查询修改使用（对应供应商登录名）
        if(u.getUserCode().equals(sysUser.getUserCode())){
        }else{
            int count = sysUserDao.countByIsDelAndUserCode(0, sysUser.getUserCode());
            if(count > 0){
                return ApiResponseResult.failure("用户编号已存在，请填写其他用户编号！");
            }
            u.setUserCode(sysUser.getUserCode().trim());
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        u.setUserName(sysUser.getUserName().trim());
        u.setUserMobile(sysUser.getUserMobile());
        u.setUserEmail(sysUser.getUserEmail());
        u.setUserComment(sysUser.getUserComment());
        u.setUserStatus(sysUser.getUserStatus());
        u.setModifiedTime(new Date());
        u.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        sysUserDao.save(u);

        //2.如果是供应商用户，修改供应商信息
        if(u.getUserType() == 1){
            List<SupplierInfo> supplierList = supplierInfoDao.findByIsDelAndLoginName(BasicStateEnum.FALSE.intValue(), originalCode);
            if(supplierList.size() > 0){
                for(int j = 0; j < supplierList.size(); j++){
                    SupplierInfo supplierInfo = supplierList.get(j);
                    if(supplierInfo != null){
                        supplierInfo.setLoginName(sysUser.getUserCode());
                        supplierInfo.setModifiedTime(new Date());
                        supplierInfo.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
                        supplierInfoDao.save(supplierInfo);
                    }
                }
            }
        }

        return ApiResponseResult.success("编辑成功！");
    }

    /**
     * 删除用户
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws Exception{
        if(id == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        List<SysUser> sysUserList = sysUserDao.findById((long) id);
        if(sysUserList == null || sysUserList.size() == 0){
            return ApiResponseResult.failure("该用户不存在或已删除！");
        }
        SysUser o = sysUserList.get(0);
        if(o == null){
            return ApiResponseResult.failure("该用户不存在或已删除！");
        }

        //1.删除用户
        //admin和super为管理员用户，无法操作
        if("admin".equals(o.getUserCode().trim()) || "super".equals(o.getUserCode().trim())){
            return ApiResponseResult.failure("此用户名为管理员用户，无法删除！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        o.setIsDel(BasicStateEnum.TRUE.intValue());
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        sysUserDao.save(o);

        //2.删除与该用户关联的用户角色关系
        List<UserRolesMap> userRolesMapList = userRolesMapDao.findByIsDelAndUserId(BasicStateEnum.FALSE.intValue(), o.getId());
        if(userRolesMapList.size() > 0){
            for(int i = 0; i < userRolesMapList.size(); i++){
                UserRolesMap userRolesMap = userRolesMapList.get(i);
                if(userRolesMap != null){
                    userRolesMap.setIsDel(BasicStateEnum.TRUE.intValue());
                    userRolesMap.setModifiedTime(new Date());
                    userRolesMap.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
                    userRolesMapDao.save(userRolesMap);
                }
            }
        }

        //3.如果是供应商用户，删除关联的供应商
        if(o.getUserType() == 1){
            List<SupplierInfo> supplierList = supplierInfoDao.findByIsDelAndLoginName(BasicStateEnum.FALSE.intValue(), o.getUserCode());
            if(supplierList.size() > 0){
                for(int j = 0; j < supplierList.size(); j++){
                    SupplierInfo supplierInfo = supplierList.get(j);
                    if(supplierInfo != null){
                        supplierInfo.setIsDel(BasicStateEnum.TRUE.intValue());
                        supplierInfo.setModifiedTime(new Date());
                        supplierInfo.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
                        supplierInfoDao.save(supplierInfo);
                    }
                }
            }
        }

        return ApiResponseResult.success("删除成功！");
    }

    /**
     * 修改密码
     * @param loginName
     * @param password
     * @param rePassword
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult changePassword(String loginName, String oldPassword, String password, String rePassword) throws Exception{
        //验证，原密码和新密码都不能为空，并且原密码要求输入正确，新密码和确认密码一致
        if(StringUtils.isEmpty(loginName)){
            return ApiResponseResult.failure("登录名不能为空！");
        }
        if(StringUtils.isEmpty(oldPassword)){
            return ApiResponseResult.failure("原密码不能为空！");
        }
        if(StringUtils.isEmpty(password) || StringUtils.isEmpty(rePassword)){
            return ApiResponseResult.failure("密码不能为空！");
        }
        SysUser o = sysUserDao.findByIsDelAndUserCode(BasicStateEnum.FALSE.intValue(), loginName);
        if(o == null){
            return ApiResponseResult.failure("该用户不存在或已删除！");
        }
        if(!o.getUserPassword().equals(MD5Util.MD5(oldPassword))){
            return ApiResponseResult.failure("原密码输入错误！");
        }
        if(!password.equals(rePassword)){
            return ApiResponseResult.failure("密码不一致，请确认！");
        }

        //1.修改用户密码
        //admin和super为管理员用户，无法操作
        if("admin".equals(o.getUserCode().trim()) || "super".equals(o.getUserCode().trim())){
            return ApiResponseResult.failure("此用户名为管理员用户，无法修改密码！");
        }

        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        o.setUserPassword(MD5Util.MD5(password));
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        sysUserDao.save(o);

        //2.如果是供应商用户，修改供应商密码
        if(o.getUserType() == 1){
            List<SupplierInfo> supplierList = supplierInfoDao.findByIsDelAndLoginName(BasicStateEnum.FALSE.intValue(), o.getUserCode());
            if(supplierList.size() > 0){
                for(int j = 0; j < supplierList.size(); j++){
                    SupplierInfo supplierInfo = supplierList.get(j);
                    if(supplierInfo != null){
                        supplierInfo.setLoginPwd(MD5Util.MD5(password));
                        supplierInfo.setModifiedTime(new Date());
                        supplierInfo.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
                        supplierInfoDao.save(supplierInfo);
                    }
                }
            }
        }

        return ApiResponseResult.success("密码修改成功！");
    }

    /**
     * 重置密码（管理员修改密码使用）
     * @param id
     * @param password
     * @param rePassword
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult resetPassword(Long id, String password, String rePassword) throws Exception{
        //验证，新密码不能为空，并且要求新密码和确认密码一致
        if(id == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        if(StringUtils.isEmpty(password) || StringUtils.isEmpty(rePassword)){
            return ApiResponseResult.failure("密码不能为空！");
        }
        if(!password.equals(rePassword)){
            return ApiResponseResult.failure("密码不一致，请确认！");
        }
        List<SysUser> sysUserList = sysUserDao.findById((long) id);
        if(sysUserList == null || sysUserList.size() == 0){
            return ApiResponseResult.failure("该用户不存在或已删除！");
        }
        SysUser o = sysUserList.get(0);
        if(o == null){
            return ApiResponseResult.failure("该用户不存在或已删除！");
        }

        //1.修改用户密码
        //admin和super为管理员用户，无法操作
        if("admin".equals(o.getUserCode().trim()) || "super".equals(o.getUserCode().trim())){
            return ApiResponseResult.failure("此用户名为管理员用户，无法修改密码！");
        }

        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        o.setUserPassword(MD5Util.MD5(password));
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        sysUserDao.save(o);

        //2.如果是供应商用户，修改供应商密码
        if(o.getUserType() == 1){
            List<SupplierInfo> supplierList = supplierInfoDao.findByIsDelAndLoginName(BasicStateEnum.FALSE.intValue(), o.getUserCode());
            if(supplierList.size() > 0){
                for(int j = 0; j < supplierList.size(); j++){
                    SupplierInfo supplierInfo = supplierList.get(j);
                    if(supplierInfo != null){
                        supplierInfo.setLoginPwd(MD5Util.MD5(password));
                        supplierInfo.setModifiedTime(new Date());
                        supplierInfo.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
                        supplierInfoDao.save(supplierInfo);
                    }
                }
            }
        }

        return ApiResponseResult.success("密码重置成功！");
    }

	@Override
	public ApiResponseResult getUserInfo(String token) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		String userCode = JWT.decode(token).getAudience().get(0);
		SysUser userForBase= sysUserDao.findByIsDelAndUserCode(0, userCode);
		map.put("userCode", userForBase.getUserCode());
        map.put("userName", userForBase.getUserName());
        map.put("userComment", userForBase.getUserComment());
        map.put("userEmail", userForBase.getUserEmail());
        map.put("userType", userForBase.getUserType());
        map.put("roles", sysRoleService.getRolesByUserId(userForBase.getId()).getData());
        return ApiResponseResult.success("登录成功！").data(map);
	}

	@Override
	public SysUser findByUserCode(String userCode) throws Exception {
		// TODO Auto-generated method stub
		return sysUserDao.findByIsDelAndUserCode(0, userCode);
	}

    /**
     * 获取用户列表
     * @param usercode
     * @param username
     * @param pageRequest
     * @return
     * @throws Exception
     */
	@Override
    @Transactional
	public ApiResponseResult getlist(String usercode,String username,PageRequest pageRequest) throws Exception {
		// TODO Auto-generated method stub
		/*Sort sort = new Sort(Sort.Direction.DESC, "id");
        List<SysUser> list = sysUserDao.findAll(sort);*/
        
		SysUser demoBean = new SysUser();
		demoBean.setUserIsSuper(0);   //查询条件（0为普通用户，1为超级管理员，只显示普通用户）
		if(!StringUtils.isEmpty(usercode)){
			demoBean.setUserCode(usercode); //查询条件
		}
		if(!StringUtils.isEmpty(username)){
			demoBean.setUserName(username); //查询条件
		}

		//创建匹配器
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("userCode", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("userName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("userType", "userStatus");
        //创建查询参数
        Example<SysUser> example = Example.of(demoBean, matcher);
		/*//获取排序对象
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		//创建分页对象
		PageRequest pageRequest = new PageRequest(0, 10, sort);*/
		//分页查询
		//List<SysUser> list  = sysUserDao.findAll(example, pageRequest).getContent();
		Page<SysUser> page = sysUserDao.findAll(example, pageRequest);
 
        return ApiResponseResult.success().data(DataGrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
	}

}
