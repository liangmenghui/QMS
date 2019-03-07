import httpService from '@/libs/service'

export default {
   
    add: function (data) {
        return httpService.post('/hr/sysdept/add', data)
    },
    list: function (data) {
        return httpService.get('/hr/sysdept/list', data)
    },
    delete: function (data) {
        return httpService.post('/hr/sysdept/delete', data)
    },
    update:function (data) {
        return httpService.post('/hr/sysdept/edit', data)
    },
    gettree:function (data) {
        return httpService.post('/hr/sysdept/gettree', data)
    },
}