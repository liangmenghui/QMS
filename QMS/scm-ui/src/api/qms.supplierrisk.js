import httpService from '@/libs/service'

export default {
	add: function (data) {
        return httpService.post('/supplierRisk/add', data)
    },
    delete: function (data) {
        return httpService.post('/supplierRisk/delete', data)
    },
    edit: function (data) {
        return httpService.post('/supplierRisk/edit', data)
    },
    getlist: function (data) {
        return httpService.get('/supplierRisk/getlist', data)
    }
}