import httpService from '@/libs/service'

export default {
    add: function (data) {
        return httpService.post('/hr/sysperson/add', data)
    },
    list: function (data) {
        return httpService.get('/hr/sysperson/list', data)
    },
    delete: function (data) {
        return httpService.post('/hr/sysperson/delete', data)
    },
    update:function (data) {
        return httpService.post('/hr/sysperson/edit', data)
    },
    
}