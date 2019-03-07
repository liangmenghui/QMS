import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/ext/customfieldvalue/getlist', data)
    },
    save: function (data) {
        return httpService.post('/ext/customfieldvalue/save', data)
    },
    delete: function (data) {
        return httpService.post('/ext/customfieldvalue/delete', data)
    }
}