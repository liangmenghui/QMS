import httpservices from '@/libs/service'

export default {
    selectProvider:function(data){
        return  httpservices.get("/lmp/infrastruct/provider/paging",data);
    },
    all:function(data){
        return httpservices.get("/lmp/infrastruct/provider/list",data);
    },
    insertProvider:function(data){
        return  httpservices.get("/lmp/infrastruct/provider/insertProvider",data);
    },
    deleteProvider:function(data){
        return  httpservices.get("/lmp/infrastruct/provider/deleteProvider",data);
    },
    updateProvider:function(data){
        return  httpservices.get("/lmp/infrastruct/provider/updateProvider",data);
    },
}