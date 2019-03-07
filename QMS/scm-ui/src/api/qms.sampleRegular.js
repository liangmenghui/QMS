import httpService from '@/libs/service'

export default {
	add: function (data) {
        return httpService.post('/sampleRegular/add', data)
    },
    delete: function (data) {
        return httpService.post('/sampleRegular/delete', data)
    },
    edit: function (data) {
        return httpService.post('/sampleRegular/edit', data)
    },
    getlist: function (data) {
        return httpService.get('/sampleRegular/getlist', data)
    },   
}