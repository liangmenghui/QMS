import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/resrceperm/getlist', data)
    },
    save: function (data) {
        return httpService.post('/resrceperm/save', data)
    }
}