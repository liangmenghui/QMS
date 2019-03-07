import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/ext/customfield/getlist', data)
    },
    add: function (data) {
        return httpService.post('/ext/customfield/add', data)
    },
    edit: function (data) {
        return httpService.post('/ext/customfield/edit', data)
    },
    delete: function (data) {
        return httpService.post('/ext/customfield/delete', data)
    }
}