import httpservices from '@/libs/service'

export default {
    selectlist:function(data){
        return  httpservices.get("/lmp/booking/config/paging",data);
    },
    all:function(data){
        return httpservices.get("/lmp/booking/config/getlist",data);
    },
    insertConfig:function(data){
        return  httpservices.get("/lmp/booking/config/insertConfig",data);
    },
    deleteConfig:function(data){
        return  httpservices.get("/lmp/booking/config/deleteConfig",data);
    },
    updateConfig:function(data){
        return  httpservices.get("/lmp/booking/config/updateConfig",data);
    },
    selectByConfig:function(data){
        return  httpservices.get("/lmp/booking/config/selectByConfig",data);
    },
}