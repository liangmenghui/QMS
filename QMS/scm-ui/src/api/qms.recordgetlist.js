import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('approvedItemsRecord/getlist ', data)
    },
    add: function (data) {
        return httpService.post('/approvedItemsRecord/add', data)
    },
    edit: function (data) {
        return httpService.post('/approvedItemsRecord/edit', data)
    },
    delete: function (data) {
        return httpService.post('/approvedItemsRecord/delete', data)
    },
}