import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/fs/file/getlist', data)
    },
    add: function (data) {
        return httpService.upload('/fs/file/add', data)
    },
    edit: function (data) {
        return httpService.upload('/fs/file/edit', data)
    },
    delete: function (data) {
        return httpService.post('/fs/file/delete', data)
    }
}