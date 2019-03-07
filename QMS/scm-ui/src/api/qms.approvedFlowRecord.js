import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/approvedFlowRecord/getlist', data)
    },
    add: function (data) {
        return httpService.post('/approvedFlowRecord/add', data)
    },
    edit: function (data) {
        return httpService.post('/approvedFlowRecord/edit', data)
    },
    close: function (data) {
        return httpService.post('/approvedFlowRecord/close', data)
    },
    delete: function (data) {
        return httpService.post('/approvedFlowRecord/delete', data)
    }
}