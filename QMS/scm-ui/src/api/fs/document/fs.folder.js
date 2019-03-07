import httpService from '@/libs/service'

export default {
    getTree: function () {
        return httpService.get('/fs/doc/folder/folder-list', {});
    },
    add: function (id, title) {
        return httpService.post('/fs/doc/folder/add/' + id, { title: title });
    },
    remove: function (id) {
        return httpService.post('/fs/doc/folder/remove/' + id, {});
    },
    edit: function (id, title) {
        return httpService.post('/fs/doc/folder/set/' + id, { title: title });
    },
    get: function (id) {
        return httpService.get("/fs/doc/folder/get/" + id);
    }
}