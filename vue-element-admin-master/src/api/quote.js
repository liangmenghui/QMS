import request from '@/utils/request'

/*新增报价模块*/
export function addQuote(data) {
  return request({
    url: '/quote/doQuote',
    method: 'post',
      data 
  })
}


/*编辑*/
export function editQuote(data) {
  return request({
    url: '/quote/edit',
    method: 'post',
    data
  })
}

/*获取报价列表*/
export function getQuoteList(qtStatus,keyword,startDate,endDate,rows,page) {
  return request({
    url: '/quote/getlist',
    method: 'get',
    params: {'qtStatus':qtStatus,'keyword':keyword,'startDate':startDate,'endDate':endDate,'page':page,'rows':rows}
  })
}

/*获取报价详情信息*/
export function getQuoteInfo(id) {
  return request({
    url: '/quote/getQuoteInfo',
    method: 'get',
    params: {'id':id}
  })
}












