import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/org/getlist', data)
    },
    addOrganization:function(data){
        return httpService.post('/org/add', data)
    },
    editOrganization:function(data){
        return httpService.post('/org/edit', data)
    },
    deleteOrganization:function(data){
        return httpService.post('/org/delete', data)
    }
}