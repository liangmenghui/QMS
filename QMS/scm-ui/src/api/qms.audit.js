import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/approvedTerms/getlist', data)
    },
    add: function (data) {
        return httpService.post('/approvedTerms/add', data)
    },
    edit: function (data) {
        return httpService.post('/approvedTerms/edit', data)
    },
    delete: function (data) {
        return httpService.post('/approvedTerms/delete', data)
    },
    records: function (data) {
        return httpService.post('/approvedTermsScore/add', data)
    },
    approved: function (data) {
        return httpService.post('/approvedItemsRecord/approved', data)
    },
	getTermsScore: function (data) {
        return httpService.get('/approvedTermsScore/getlist', data)
    },
	getuser: function (data) {
        return httpService.get('/feedbackInfo/getuser', data)
    },
}