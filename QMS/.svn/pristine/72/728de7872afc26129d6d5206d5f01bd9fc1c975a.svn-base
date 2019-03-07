import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/mw/apiconfig/getlist', data)
    },
    add: function (data) {
        return httpService.postdata('/mw/apiconfig/add', data)
    },
    edit: function (data) {
        return httpService.postdata('/mw/apiconfig/edit', data)
    },
    delete: function (data) {
        return httpService.post('/mw/apiconfig/delete', data)
    },
    test: function (data, revision, code) {
        return httpService.post('../openapi/'+revision+'/mw/'+code, data)
    }
}