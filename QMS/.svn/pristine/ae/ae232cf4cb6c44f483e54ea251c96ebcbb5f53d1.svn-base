import httpService from '@/libs/service'

export default {
	add: function (data) {
        return httpService.post('/productInfo/add', data)
    },
    delete: function (data) {
        return httpService.post('/productInfo/delete', data)
    },
    getlist: function (data) {
        return httpService.get('/productInfo/getlist', data)
    },   
    applyRiskLevel :function(data) {
        return httpService.post('/productInfo/applyRiskLevel ', data)
    },
    completeRiskLevel :function(data) {
        return httpService.post('/productInfo/completeRiskLevel ', data)
    },
    autoRiskLevel :function(data) {
        return httpService.post('/productInfo/autoRiskLevel ', data)
    },
	getProcessApproved: function (data) {
        return httpService.get('/productInfo/getProcessApproved', data)
    },
	getProcessApprovedExcel: function (data) {
        return httpService.download('/productInfo/getProcessApprovedExcel', data) 
    },
    getProductApproved: function (data) {
        return httpService.download('/productInfo/getProductApproved', data) 
    },
    getProductApprovedExcel: function(data){
        return httpService.download('/productInfo/getProductApprovedExcel', data)
    }
}

