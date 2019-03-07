import httpservices from '@/libs/service'

export default {
    selectlist:function(data){
        return  httpservices.get("/lmp/infrastruct/hscode/paging",data);
    },
    all:function(data){
        return httpservices.get("/lmp/infrastruct/hscode/getlist",data);
    },
    insertHscode:function(data){
        return  httpservices.get("/lmp/infrastruct/hscode/insertHscode",data);
    },
    deleteHscode:function(data){
        return  httpservices.get("/lmp/infrastruct/hscode/deleteHscode",data);
    },
    updateHscode:function(data){
        return  httpservices.get("/lmp/infrastruct/hscode/updateHscode",data);
    },
    selectByHscode:function(data){
        return  httpservices.get("/lmp/infrastruct/hscode/selectByHscode",data);
    },
}