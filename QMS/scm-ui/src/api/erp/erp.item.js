import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/erp/item/getlist', data)
    },
    add: function (data) {
        return httpService.post('/erp/item/add', data)
    },
    edit: function (data) {
        return httpService.post('/erp/item/edit', data)
    },
    delete: function (data) {
        return httpService.post('/erp/item/delete', data)
    },
    download: function (data) {
        return httpService.download('/erp/item/download', data)
    }
}