import httpService from '@/libs/service'

export default {
   
    add: function (data) {
        return httpService.upload('/approvedTermsScoreFile/add', data)
    },    
    delete: function (data) {
        return httpService.post('/approvedTermsScoreFile/delete', data)
    }
}
