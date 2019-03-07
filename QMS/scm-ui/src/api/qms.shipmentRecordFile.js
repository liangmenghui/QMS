import httpService from '@/libs/service'

export default {
   
    delete: function (data) {
        return httpService.post('/shipmentInspectRecordFile/delete', data)
    },
    add: function (data) {
        return httpService.upload('/shipmentInspectRecordFile/add', data)
    }
}

