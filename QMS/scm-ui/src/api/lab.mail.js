import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/lab/mail/getlist', data)
    },
    delete: function (data) {
        return httpService.get('/lab/mail/delete', data)
    },
    update: function (data) {
        return httpService.get('/lab/mail/update', data)
    },
    save: function (data) {
        return httpService.get('/lab/mail/save', data)
    },
    
}