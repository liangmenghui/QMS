import httpService from '@/libs/service'

export default {
	add: function (data) {
        return httpService.post('/promote/add', data)
    },
    delete: function (data) {
        return httpService.post('/promote/delete', data)  
    },
    getlist: function (data) {
        return httpService.get('/promote/getlist', data)
    },   
    edit :function(data) {
        return httpService.post('/promote/edit', data)
    },
    updateContent :function(data) {
        return httpService.post('/promote/updateContent', data)
    }
    

}

