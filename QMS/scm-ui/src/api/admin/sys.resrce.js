import httpService from '@/libs/service'

export default {
    gettree: function (data) {
        return httpService.get('/resrce/gettree', data)
    },
    getlist: function (data) {
        return httpService.get('/resrce/getlist', data)
    },
    add: function (data) {
        return httpService.post('/resrce/add', data)
    },
    edit: function (data) {
        return httpService.post('/resrce/edit', data)
    },
    delete: function (data) {
        return httpService.post('/resrce/delete', data)
    }
}