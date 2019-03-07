import request from '@/utils/request'

/*获取列表*/
export function getList() {
  return request({
    url: '/setting/getlist',
    method: 'get',
    params: { }
  })
}

/*编辑*/
export function updateSetting(checkRate) {
  return request({
    url: '/setting/updateSetting',
    method: 'post',
    params: { 'bomCheck':checkRate}
  })
}













