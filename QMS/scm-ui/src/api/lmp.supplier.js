import httpservices from '@/libs/service'

export default {
    selectSupplier:function(data){
        return  httpservices.get("/lmp/infrastruct/supplier/paging",data);
    },
    all:function(data){
        return httpservices.get("/lmp/infrastruct/supplier/list",data);
    },
    insertSupplier:function(data){
        return  httpservices.get("/lmp/infrastruct/supplier/insertSupplier",data);
    },
    deleteSupplier:function(data){
        return  httpservices.get("/lmp/infrastruct/supplier/deleteSupplier",data);
    },
    updateSupplier:function(data){
        return  httpservices.get("/lmp/infrastruct/supplier/updateSupplier",data);
    },
    selectBySupplier:function(data){
        return  httpservices.get("/lmp/infrastruct/supplier/selectBySupplier",data);
    },
}