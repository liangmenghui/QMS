import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/forecast/getlist', data)
    },
    add: function (data) {
        return httpService.post('/forecast/add', data)
    },
    upload: function (data) {
        return httpService.upload('/forecast/upload', data)
    },
    edit: function (data) {
        return httpService.post('/forecast/edit', data)
    },
    delete: function (data) {
        return httpService.post('/forecast/delete', data)
    }
}