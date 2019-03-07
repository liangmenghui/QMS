import httpService from '@/libs/service'

export default {
    gettranlist: function (data) {
        return httpService.get('/tsr/gettranlist', data)
    },
    addDetail:function (data){
        return httpService.get('/tsr/addDetail',data)
    },
    editDetail:function(data){
        return httpService.get('/tsr/editDetail',data)
    },
    deleteDetail:function(data){
        return httpService.get('/tsr/deleteDetail',data)
    },
    getdetaillist:function (data){
        return httpService.get('/tsr/getDetailList',data)
    },
    edit:function(data){
        return httpService.get('/tsr/editTrans',data)
    },
    add:function(data){
        return httpService.get('/tsr/addTrans',data)
    },
    deleteTransCode:function(data){
        return httpService.get('/tsr/deleteTransCode',data)
    }
}