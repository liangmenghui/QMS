import httpService from '@/libs/service'

export default {
   
    getfile: function (data) {
        return httpService.get('/fileQms/get', data)  
    }   
   
}