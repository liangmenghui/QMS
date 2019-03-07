import httpService from '@/libs/service'

export default {
   
    add: function (data) {
        return httpService.post('/lab/equipment/add', data)
    },
    list: function (data) {
        return httpService.get('/lab/equipment/list', data)
    },
    delete: function (data) {
        return httpService.post('/lab/equipment/delete', data)
    },
    update:function (data) {
        return httpService.post('/lab/equipment/update', data)
    },
    selectbsName:function(data){
        return httpService.get('/lab/equipment/selectbsName', data)
    },
    selectEablee:function(data){
        return httpService.get('/lab/equipment/selectEablee', data)
    },
}