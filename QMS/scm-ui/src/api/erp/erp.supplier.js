import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/erp/supplier/getlist', data)
    },
    add: function (data) {
        return httpService.post('/erp/supplier/add', data)
    },
    edit: function (data) {
        return httpService.post('/erp/supplier/edit', data)
    },
    delete: function (data) {
        return httpService.post('/erp/supplier/delete', data)
    },
    download: function (data) {
        return httpService.download('/erp/supplier/download', data)
    }
}