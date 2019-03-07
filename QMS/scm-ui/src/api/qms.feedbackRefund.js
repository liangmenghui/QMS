import httpService from '@/libs/service'

export default {   
    add: function (data) {
        return httpService.post('/feedbackRefund/add', data)
    },
    edit: function (data) {
        return httpService.post('/feedbackRefund/edit', data)
    },
    delete: function (data) {
        return httpService.post('/feedbackRefund/delete', data)
    },
    getlist: function (data) {
        return httpService.get('/feedbackRefund/getlist', data)
    },
}