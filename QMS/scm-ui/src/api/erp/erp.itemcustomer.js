import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/erp/itemcustomer/getlist', data)
    },
    add: function (data) {
        return httpService.post('/erp/itemcustomer/add', data)
    },
    edit: function (data) {
        return httpService.post('/erp/itemcustomer/edit', data)
    },
    delete: function (data) {
        return httpService.post('/erp/itemcustomer/delete', data)
    },
    download: function (data) {
        return httpService.download('/erp/itemcustomer/download', data)
    }
}