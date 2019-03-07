import request from '@/utils/request'

/*新增权限*/
export function addRole(data) {
  return request({
    url: '/sysRole/add',
    method: 'post',
      data 
  })
}

/*获取权限列表*/
export function getRoleList(rolecode,rolename,page,rows) {
  return request({
    url: '/sysRole/getlist',
    method: 'get',
    params: { 'roleCode':rolecode, 'roleName':rolename,'page':page,'rows':rows}
  })
}

/*获取权限配置列表*/
export function getCheckedRoles(userId) {
  return request({
    url: '/sysRole/getCheckedRoles',
    method: 'get',
    params: { 'userId':userId}
  })
}

/*保存用户权限*/
export function saveUserRoles(userId,roles) {
  return request({
    url: '/sysRole/saveUserRoles',
    method: 'get',
    params: { 'userId':userId,'roles':roles}
  })
}

/*编辑权限列表*/
export function editRole(data) {
  return request({
    url: '/sysRole/edite',
    method: 'post',
    data
  })
}

/*删除角色*/
export function delRoleInfo(id) {
  return request({
    url: '/sysRole/delete',
    method: 'post',
    params: { 'id':id}
  })
}