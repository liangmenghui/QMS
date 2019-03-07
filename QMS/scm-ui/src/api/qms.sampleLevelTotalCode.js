import httpService from '@/libs/service'

export default {
     getlist: function (data) {
        return httpService.get('/sampleLevelTotalCode/getlist', data)
    },   
    edit: function (data) {
        return httpService.post('/sampleLevelTotalCode/edit', data)
    },
    
}