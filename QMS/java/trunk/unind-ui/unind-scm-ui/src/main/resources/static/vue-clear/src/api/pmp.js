import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/pmp/getlist', data)
    },
    add: function (data) {
        return httpService.post('/pmp/add', data)
    },
    edit: function (data) {
        return httpService.post('/pmp/edit', data)
    },
    delete: function (data) {
        return httpService.post('/pmp/delete', data)
    }
}