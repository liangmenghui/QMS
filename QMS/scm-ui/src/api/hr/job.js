import httpService from '@/libs/service'

export default {
   
    add: function (data) {
        return httpService.post('/hr/sysjob/add', data)
    },
    list: function (data) {
        return httpService.get('/hr/sysjob/list', data)
    },
    delete: function (data) {
        return httpService.post('/hr/sysjob/delete', data)
    },
    update:function (data) {
        return httpService.post('/hr/sysjob/edit', data)
    },
    gettree:function (data) {
        return httpService.post('/hr/sysjob/gettree', data)
    },
}