import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/roleperms/getlist', data)
    },
    save: function (data) {
        return httpService.post('/roleperms/save', data)
    }
}