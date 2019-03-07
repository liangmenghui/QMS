import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/rolegroup/getlist', data)
    },
    add: function (data) {
        return httpService.post('/rolegroup/add', data)
    },
    edit: function (data) {
        return httpService.post('/rolegroup/edit', data)
    },
    delete: function (data) {
        return httpService.post('/rolegroup/delete', data)
    }
}