import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/erp/productinfo/getlist', data)
    },
    add: function (data) {
        return httpService.post('/erp/productinfo/add', data)
    },
    edit: function (data) {
        return httpService.post('/erp/productinfo/edit', data)
    },
    delete: function (data) {
        return httpService.post('/erp/productinfo/delete', data)
    },
    download: function (data) {
        return httpService.download('/erp/productinfo/download', data)
    }
}