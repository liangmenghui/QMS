import httpService from '@/libs/service'

export default {
    addAgain: function (data) {
        return httpService.post('/orderInspect/addAgain', data)
    },
}