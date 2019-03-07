import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/qualityInspect/getlist', data)
    },
    add: function (data) {
        return httpService.post('/qualityInspect/add', data)
    },
    edit: function (data) {
        return httpService.post('/qualityInspect/add', data)
    },
    delete: function (data) {
        return httpService.post('/qualityInspect/delete', data)
    },
}