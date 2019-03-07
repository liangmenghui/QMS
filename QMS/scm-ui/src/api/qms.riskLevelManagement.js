import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/riskLevelManagement/getlist', data)
    }
}