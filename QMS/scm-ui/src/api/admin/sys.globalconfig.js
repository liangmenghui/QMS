import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/globalconfig/getlist', data)
    },
    add: function (data) {
        return httpService.post('/globalconfig/add', data)
    },
    edit: function (data) {
        return httpService.post('/globalconfig/edit', data)
    },
    delete: function (data) {
        return httpService.post('/globalconfig/delete', data)
    }
}