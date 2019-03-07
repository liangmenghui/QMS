import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/user/getlist', data)
    },
    add:function(data){
        return httpService.post('/user/add',data)
    },
    delete:function(data){
        return httpService.post('/user/delete',data)
    },
    edit:function(data){
        return httpService.post('/user/edit',data)
    },
    editpassword:function(data){
        return httpService.post('/user/editpassword',data)
    },
    resetpassword:function(data){
        return httpService.post('/user/resetpassword',data)
    },
    current:function(data){
        return httpService.post('/user/current',data)
    }
}
