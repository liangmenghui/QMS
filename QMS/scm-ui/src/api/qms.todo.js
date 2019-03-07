import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/todoInfo/getlist', data)
    },
    add: function (data) {
        return httpService.post('/todoInfo/add', data)
    },
    edit: function (data) {
        return httpService.post('/todoInfo/edit', data)
    },
    delete: function (data) {
        return httpService.post('/todoInfo/delete', data)
    },
    close: function (data) {
        return httpService.post('/todoInfo/close', data)
    },
	addReview: function (data) {
        return httpService.post('/todoInfo/addReview', data)
    }
}