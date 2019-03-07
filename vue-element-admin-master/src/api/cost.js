import request from '@/utils/request'

export function uplaodFile(data) {
  return request({
    headers:{'Content-Type':'multipart/form-data'},
    url: '/customerBom/importBom',
    method: 'post',
    data
  })
}

export function viewFile(fsFileId) {
  return request({
    headers:{'Content-Type':'multipart/form-data'},
    url: '/file/view',
    method: 'get',
    params: { 'fsFileId':fsFileId}
  })
}

export function getFile(fsFileId) {
  
  return request({
    headers:{'Content-Type':'multipart/form-data'},
    url: '/file/get',
    method: 'get',
    params: { 'fsFileId':fsFileId}
  })
}


//匹配K3物料信息
  export function getK3Bom(standardCol,categoryCol,quantityCol,packageCol,makerCol,splitList,fileId) {
  return request({    
    url: '/customerBom/getK3Bom',
    method: 'get',
    params: {'standardCol':standardCol,'categoryCol':categoryCol,'quantityCol':quantityCol,'packageCol':packageCol,'makerCol':makerCol,'splitList':splitList,'fileId':fileId}
  })
}

//匹配K3物料信息
export function getBomMatch(cusBomId,mateCategory,topNum,matchNum) {
  return request({    
    url: '/customerBom/getBomMatch',
    method: 'get',
    params: {'cusBomId':cusBomId,'mateCategory':mateCategory,'topNum':topNum,'matchNum':matchNum}
  })
}

/*获取询价列表*/
export function getBomList( keyWord,rows,page) {
  return request({
    url: '/customerBom/getBomList',
    method: 'get',
    params: { 'keyWord':keyWord,'page':page,'rows':rows}
  })
}
/*删除*/
export function delBom(id) {
  return request({
    url: '/customerBom/delete',
    method: 'post',
    params: { 'fileId':id}
  })
}
/*根据fileId获取参数设置内容以及excel数据*/
export function getBomData( fileId) {
  return request({
    url: '/customerBom/getBomData',
    method: 'get',
    params: { 'fileId':fileId}
  })
}

//获取价格曲线
export function getPriceChart(mateK3Code,startDate,endDate,flag) {
  return request({    
    url: '/costChart/getPriceChart',
    method: 'get',
    params: {'mateK3Code':mateK3Code,'startDate':startDate,'endDate':endDate,'flag':flag}
  })
}

//修改备注
export function modifyRemark(id,remark) {
  return request({    
    url: '/customerBom/addRemark',
    method: 'post',
    params: {'id':id,'remark':remark}
  })
}


//fyx-选中/取消匹配的物料
export function doCheckMateriel(id,checkStatus) {
  return request({    
    url: '/customerBom/doCheckMateriel',
    method: 'post',
    params: {'id':id,'checkStatus':checkStatus}
  })
}







