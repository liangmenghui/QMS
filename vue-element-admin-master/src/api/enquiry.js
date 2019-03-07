import request from '@/utils/request'

/*新增询价模块*/
export function addEnquiry(data) {
  return request({
    url: '/enquiry/add',
    method: 'post',
      data 
  })
}

/*删除供应商*/
export function delEnquiry(id) {
  return request({
    url: '/enquiry/delete',
    method: 'post',
    params: { 'id':id}
  })
}

/*编辑*/
export function editEnquiry(data) {
  return request({
    url: '/enquiry/edit',
    method: 'post',
    data
  })
}

/*获取供应商列表*/
export function getEnquiryList(eqStatus,keyword,startDate,endDate,rows,page) {
  return request({
    url: '/enquiry/getlist',
    method: 'get',
    params: {'eqStatus':eqStatus,'keyword':keyword,'startDate':startDate,'endDate':endDate,'page':page,'rows':rows}
  })
}

/*获取询价详情信息*/

export function getEnquiryInfo(id) {
  return request({
    url: '/enquiry/getEnquiryInfo',
    method: 'get',
    params: {'id':id}
  })
}

/*编辑新增物料*/
export function addEnquiryMateriel(data) {
  return request({
    url: '/enquiryMateriel/add',
    method: 'post',
      data 
  })
}

/*编辑新增供应商*/
export function addEnquirySupplier(data) {
  return request({
    url: '/enquirySupplier/add',
    method: 'post',
      data 
  })
}












