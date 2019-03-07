import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/lab/sample/getlist', data)
    },
    add: function (data) {
        return httpService.post('/lab/sample/add', data)
    },
    edit: function (data) {
        return httpService.post('/lab/sample/edit', data)
    },
    delete: function (data) {
        return httpService.post('/lab/sample/delete', data)
    },
    checkinSave: function (data) {
        return httpService.post('/lab/sample/checkinSave', data)
    },
    selectSample: function (data) {
        return httpService.get('/lab/sample/selectSample', data)
    },
    forward :function(data) {
        return httpService.get('/lab/sample/forward', data)
    },
    getTime:function(data) {
        return  httpService.get('/lab/sample/getTime', data)
    },
    saveTest:function(data) {
        return  httpService.post('/lab/sampleTest/saveTest', data)
    },
    testcomp:function(data) {
        return  httpService.get('/lab/sampleTest/testcomp', data)
    },
    selectall:function(data){
        return  httpService.get('/lab/sample/selectall', data)
    },
    selectall1:function(data){
        return  httpService.get('/lab/sampleTest/selectall1', data)
    },
    deleteSample: function(data){
        return  httpService.get('/lab/sampleTest/deleteSample', data)
    },
    CreateInvAccAlias: function(data){
        return  httpService.post('/openapi/V1.0/prod/CreateInvAccAlias', data)
    },
    selectById: function(data){
        return  httpService.post('/lab/sample/selectById', data)
    },
    updatestate: function(data){
        return  httpService.post('/lab/sampleTest/updatestate', data)
    },
    saveTestx: function(data){
        return  httpService.post('/lab/sampleTest/saveTestx', data)
    },
    selectSampleTestTime: function(data){
        return  httpService.post('/lab/sampleTest/selectSampleTestTime', data)
    },
    download:function(data){
        return httpService.get('/lab/sample/download', data)
    },
    updateStatus:function(data){
        return httpService.post('/lab/sample/updateStatus', data)
    },
    sendMail:function(data){
        return httpService.get('/lab/mail/sendMail', data)
    },
    updateStatuss:function(data){
        return httpService.post('/lab/sample/updateStatuss', data)
    },
}