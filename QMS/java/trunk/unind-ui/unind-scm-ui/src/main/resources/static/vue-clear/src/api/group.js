import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/group/getlist', data)
    },
    addGroup:function(data){
        return httpService.post('/group/add', data)
    },
    editGroup:function(data){
        return httpService.post('/group/edit', data)
    },
    deleteGroup:function(data){
        return httpService.post('/group/delete', data)
    }
}