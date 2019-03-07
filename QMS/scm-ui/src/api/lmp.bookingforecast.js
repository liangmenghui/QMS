import httpservice from '@/libs/service'
import agent from './lmp.provider'

export default {
    proxies: function (data) {
        return agent.selectProvider(data);
    },
    suppliers: function (data) {

    },
    list: function (data) {
        return httpservice.get('/lmp/booking/forecast/list', data);
    }, remove: function (data) {
        return httpservice.get('/lmp/booking/forecast/remove', data);
    }, detail: function (data) {
        return httpservice.get('/lmp/booking/forecast/detail', data);
    },
    save: function (data) {
        return httpservice.postdata('/lmp/booking/forecast/save', data);
    }
}