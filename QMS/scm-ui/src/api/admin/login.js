import httpService from '@/libs/service'

export default {
    logon: function (data) {
        return httpService.post('/logon', data)
    },
    logout: function (data) {
        return httpService.post('/logout', data)
    }
}