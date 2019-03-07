import httpService from '@/libs/service'

export default {
	add: function (data) {
        return httpService.post('/excelTemp/add', data)
    },
    edit: function (data) {
        return httpService.post('/excelTemp/edit', data)
    },
	delete: function (data) {
        return httpService.post('/excelTemp/delete', data)
    },
    getlist: function (data) {
        return httpService.get('/excelTemp/getlist', data)
    },   
}

