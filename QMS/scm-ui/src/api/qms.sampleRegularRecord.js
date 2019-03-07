import httpService from '@/libs/service'

export default {
	add: function (data) {
        return httpService.post('/sampleRegularRecord/add', data)
    },
    delete: function (data) {
        return httpService.post('/sampleRegularRecord/delete', data)
    },
    getlist: function (data) {
        return httpService.get('/sampleRegularRecord/getlist', data)
    },   
}