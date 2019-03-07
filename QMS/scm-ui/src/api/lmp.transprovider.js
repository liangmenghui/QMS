import httpservices from '@/libs/service'

export default {
    selectlist:function(data){
        return  httpservices.get("/lmp/infrastruct/transprovider/paging",data);
    },
    add:function(data){
        return  httpservices.get("/lmp/infrastruct/transprovider/add",data);
    },
    selectTransCode:function(data){
        return  httpservices.get("/lmp/infrastruct/transprovider/selectTransCode",data);
    },
    delete:function(data){
        return  httpservices.get("/lmp/infrastruct/transprovider/delete",data);
    },
    update:function(data){
        return  httpservices.get("/lmp/infrastruct/transprovider/update",data);
    },
    selectByProvide:function(data){
        return  httpservices.get("/lmp/infrastruct/transprovider/selectByProvide",data);
    },
}