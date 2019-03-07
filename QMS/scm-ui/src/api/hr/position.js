import httpService from '@/libs/service'

export default {
    add: function (data) {
        return httpService.post('/hr/sysposition/add', data)
    },
    list: function (data) {
        return httpService.get('/hr/sysposition/list', data)
    },
    delete: function (data) {
        return httpService.post('/hr/sysposition/delete', data)
    },
    update:function (data) {
        return httpService.post('/hr/sysposition/edit', data)
    },
    gettree:function (data) {
        return httpService.post('/hr/sysposition/gettree', data)
    },
    
}