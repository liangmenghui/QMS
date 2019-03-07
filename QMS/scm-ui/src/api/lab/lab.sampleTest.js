import httpService from '@/libs/service'

export default {
   
    saveData:function(data) {
        return  httpService.post('/lab/sampletest/saveData', data)
    },
    getData:function(data) {
        return  httpService.post('/lab/sampletest/getData', data)
    },
    selectTime:function(data) {
        return  httpService.post('/lab/sampletest/selectTime', data)
     }
    }