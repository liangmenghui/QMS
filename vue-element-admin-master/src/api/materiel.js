import request from '@/utils/request'

/*新增物料*/
export function addMaterielInfo(data) {
  return request({
    url: '/materielInfo/add',   
    method: 'post',
      data 
  })
}

/*删除物料*/
export function delMaterielInfo(id) {
  return request({
    url: '/materielInfo/delete',
    method: 'post',
    params: { 'id':id}
  })
}

export function getUserInfo(token) {
  return request({
    url: '/sysUser/getUserInfo',
    method: 'post',
    params: { token }
  })
}

/*获取物料列表*/
export function getMaterielList(mateK3Code,mateName,rows,page,rowsK3,pageK3) {
  return request({
    url: '/materielInfo/getlistAll',
    method: 'get',
    params: { 'mateK3Code':mateK3Code, 'mateName':mateName,'page':page,'rows':rows,'pageK3':pageK3,'rowsK3':rowsK3}
  })
}

/*编辑物料*/
export function editMateriel(data) {
  return request({
    url: '/materielInfo/edit',
    method: 'post',
    data
  })
}













