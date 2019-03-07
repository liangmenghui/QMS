import request from '@/utils/request'

//新增供应商评分
export function addScore(data) {
  return request({    
    url: '/supplierScore/add',
    method: 'post',
    data
  })
}

//编辑供应商评分
export function editScore(data) {
  return request({    
    url: '/supplierScore/edit',
    method: 'post',
    data
  })
}

//删除供应商评分
export function delScore(id) {
  return request({
    url: '/supplierScore/delete',
    method: 'post',
    params: { 'id':id }
  })
}

//获取供应商评分列表
export function getScoreList(keyword,rows,page) {
  return request({
    url: '/supplierScore/getlist',
    method: 'get',
    params: { 'keyword':keyword,'page':page,'rows':rows }
  })
}