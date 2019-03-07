import httpService from '@/libs/service'

export default {
    edit: function (data) {
        return httpService.post('/riskApprovedRecord/edit', data)
    },
	delete: function (data) {
        return httpService.post('/riskApprovedRecord/delete', data)
    },
    getlist: function (data) {
        return httpService.get('/riskApprovedRecord/getlist', data)
    },   
	approve: function (data) {
        return httpService.post('/riskApprovedRecord/approve', data)
    }, 
}

