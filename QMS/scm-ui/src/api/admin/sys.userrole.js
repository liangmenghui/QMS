import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/userrole/getlist', data)
    },
    getuserlist: function (data) {
        return httpService.get('/userrole/getuserlist', data)
    },
    save: function (data) {
        return httpService.post('/userrole/save', data)
    }
}