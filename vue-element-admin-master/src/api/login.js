import request from '@/utils/request'

export function loginByUsername(username, password) {
  const data = {
    username,
    password
  }
  let param = new URLSearchParams()
  param.append('username', username)
  param.append('password', password)
  return request({
    url: '/login',
    method: 'get',
    //date
    params: { 'username':username, 'password':password}
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo(token) {
  return request({
    url: '/sysUser/getUserInfo',
    method: 'post',
    params: { token }
  })
}

