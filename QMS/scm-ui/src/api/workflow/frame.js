import httpService from '@/libs/service';

export default {
    getlist: function (data) {
        return httpService.get('/workflow/getmodellist', data);
    },
    delete: function (data) {
        return httpService.post('/workflow/delete', data);
    },
    add: function (data) {
        return httpService.post('/workflow/add', data);
    },
    deploy: function (data) {
        return httpService.get('/workflow/deploy', data);
    }
}