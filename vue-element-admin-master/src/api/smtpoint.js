import request from '@/utils/request'


/*获取SMT点数*/
export function getSmtPoint(parentId,sLevel,setStatus,keyword,rows,page) {
  return request({
    url: '/smtPoints/getTreeList',
    method: 'get',
    params: {'parentId':parentId,'sLevel':sLevel,'setStatus':setStatus,'keyword':keyword,'rows':rows,'page':page}
  })
}

/*修改SMT点数*/
export function updateStmPoints(data) {
  return request({
    url: '/smtPoints/updatePoints',
    method: 'post',
    data
  })
}












