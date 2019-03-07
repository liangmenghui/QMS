import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/approvedEHSTerms/getlist', data)
    },
    add: function (data) {
        return httpService.post('/approvedEHSTerms/add', data)
    },
    edit: function (data) {
        return httpService.post('/approvedEHSTerms/edit', data)
    },
    delete: function (data) {
        return httpService.post('/approvedEHSTerms/delete', data)
    },
    getlist: function (data) {
        return httpService.get('/approvedEHSTerms/getlist',data)
    },
    addEHSRecord: function (data) {
        return httpService.post('/approvedEHSTerms/addEHSRecord',data)
    },
    getRecords: function (data) {
        return httpService.get('/approvedEHSTerms/getRecords',data)
    },
    getItemList: function (data) {
        return httpService.get('/approvedEHSTerms/getItemList',data)
    }
}