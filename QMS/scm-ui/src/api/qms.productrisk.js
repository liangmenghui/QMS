import httpService from '@/libs/service'

export default {
	add: function (data) {
        return httpService.post('/productRisk/add', data)
    },
    edit: function (data) {
        return httpService.post('/productRisk/edit', data)
    },
    getlist: function (data) {
        return httpService.get('/productRisk/getlist', data)
    },   
}