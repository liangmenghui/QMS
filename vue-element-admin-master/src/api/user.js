import request from '@/utils/request'

/*新增用户*/
export function addUserInfo(data) {
  return request({
    url: '/sysUser/add',
    method: 'post',
      data 
  })
}

export function getUserInfo(token) {
  return request({
    url: '/sysUser/getUserInfo',
    method: 'post',
    params: { token }
  })
}

/*获取用户列表*/
export function getUserList(usercode,username,rows,page) {
  return request({
    url: '/sysUser/getlist',
    method: 'get',
    params: { 'username':username, 'usercode':usercode,'page':page,'rows':rows}
  })
}

/*编辑用户*/
export function editUser(data) {
  return request({
    url: '/sysUser/edite',
    method: 'post',
    data
  })
}

/*删除用户*/
export function delUserInfo(id) {
  return request({
    url: '/sysUser/delete',
    method: 'post',
    params: { 'id':id}
  })
}

/*修改密码*/
export function changePassworde(loginName,oldPassword,password,rePassword) {
  return request({
    url: '/sysUser/changePassword',
    method: 'post',
    params: { 'loginName':loginName,'oldPassword':oldPassword, 'password':password,'rePassword':rePassword,}
  })
}

/*重置密码（管理员修改密码使用）*/
export function resetPassword(id,password,rePassword) {
  return request({
    url: '/sysUser/resetPassword',
    method: 'post',
    params: { 'id':id,'password':password,'rePassword':rePassword}
  })
}