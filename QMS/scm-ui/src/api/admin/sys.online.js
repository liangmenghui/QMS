import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/online/getlist', data)
    },
    kickout: function (data) {
        return httpService.post('/online/kickout', data)
    }
}