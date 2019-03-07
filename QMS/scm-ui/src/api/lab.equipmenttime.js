import httpService from '@/libs/service'

export default {
   
    add: function (data) {
        return httpService.get('/lab/equipmentTime/add', data)
    },
    list:function (data) {
        return httpService.get('/lab/equipmentTime/list', data)
    },
    delete: function (data) {
        return httpService.get('/lab/equipmentTime/delete', data)
    },
    update:function (data) {
        return httpService.get('/lab/equipmentTime/update', data)
    },
    selectTestx:function(data){
        return httpService.get('/lab/equipmentTime/selectTestx', data)
    },
    selectTestTime:function(data){
        return httpService.get('/lab/equipmentTime/selectTestTime', data)
    }
    
}