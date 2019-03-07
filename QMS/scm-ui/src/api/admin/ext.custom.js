import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/ext/custom/getlist', data)
    },
    add: function (data) {
        return httpService.post('/ext/custom/add', data)
    },
    edit: function (data) {
        return httpService.post('/ext/custom/edit', data)
    },
    delete: function (data) {
        return httpService.post('/ext/custom/delete', data)
    }
}