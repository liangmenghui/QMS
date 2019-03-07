import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/shipmentInspect/getlist', data)
    },
    add: function (data) {
        return httpService.post('/shipmentInspect/add', data)
    },
    edit: function (data) {
        return httpService.post('/shipmentInspect/edit', data)
    },
    delete: function (data) {
        return httpService.post('/shipmentInspect/delete', data)
    },
    addRecord: function (data) {
        return httpService.post('/shipmentInspectRecord/add', data)
    },
    editRecord: function (data) {
        return httpService.post('/shipmentInspectRecord/edit', data)
    },
    getrecords: function (data) {
        return httpService.get('/shipmentInspectRecord/getlist', data)
    },
    
}