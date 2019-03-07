import httpservices from '@/libs/service'

export default {
    selectlist:function(data){
        return  httpservices.get("/lmp/infrastruct/cargo/paging",data);
    },
    all:function(data){
        return httpservices.get("/lmp/infrastruct/cargo/getlist",data);
    },
    insertCargo:function(data){
        return  httpservices.get("/lmp/infrastruct/cargo/insertCargo",data);
    },
    deleteCargo:function(data){
        return  httpservices.get("/lmp/infrastruct/cargo/deleteCargo",data);
    },
    updateCargo:function(data){
        return  httpservices.get("/lmp/infrastruct/cargo/updateCargo",data);
    },
    selectByCargo:function(data){
        return  httpservices.get("/lmp/infrastruct/cargo/selectByCargo",data);
    },
}