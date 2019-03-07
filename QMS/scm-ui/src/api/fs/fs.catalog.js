import httpService from '@/libs/service'

export default {
    gettree: function (data) {
        return httpService.get('/fs/catalog/gettree', data)
    },
    getlist: function (data) {
        return httpService.get('/fs/catalog/getlist', data)
    },
    add: function (data) {
        return httpService.post('/fs/catalog/add', data)
    },
    edit: function (data) {
        return httpService.post('/fs/catalog/edit', data)
    },
    delete: function (data) {
        return httpService.post('/fs/catalog/delete', data)
    }
}