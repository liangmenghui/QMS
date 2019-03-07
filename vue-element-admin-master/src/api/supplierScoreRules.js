import request from '@/utils/request'


//获取供应商评分规则
export function getlist() {
  return request({    
    url: '/supplierScoreRule/getlist',
    method: 'get'
  })
}

//编辑供应商评分规则
export function edit(data) {
  return request({    
    url: '/supplierScoreRule/edit',
    method: 'post',
    data
  })
}
//单独编辑供应商得分
export function updateScore(id,ruleScore) {
  return request({    
    url: '/supplierScoreRule/updateScore',
    method: 'post',
    params:{'id':id,'ruleScore':ruleScore}
  })
}




















