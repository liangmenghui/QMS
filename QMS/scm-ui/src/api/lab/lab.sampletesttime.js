import httpService from '@/libs/service'

export default {
    
    save:function(data){
        return httpService.post('/lab/sampletesttime/save', data)
    },
    saveExcel:function(data){
        return httpService.get('/lab/sampletesttime/saveExcel', data)
    },
    getlist:function(data){
        return httpService.post('/lab/sampletesttime/getlist', data)
    },
    saveReport:function(data){
        return httpService.post('/lab/sampletesttime/saveReport', data)
    },
    selectlist:function(data){
        return httpService.post('/lab/sampletesttime/selectlist', data)
    },
    deleteData:function(data){
        return httpService.post('/lab/sampletesttime/deleteData', data)
    },
}