import httpservices from '@/libs/service'

export default {
    selectlist:function(data){
        return  httpservices.get("/lmp/infrastruct/item/paging",data);
    },
    all:function(data){
        return httpservices.get("/lmp/infrastruct/item/getlist",data);
    },
    insertItem:function(data){
        return  httpservices.get("/lmp/infrastruct/item/insertItem",data);
    },
    deleteItem:function(data){
        return  httpservices.get("/lmp/infrastruct/item/deleteItem",data);
    },
    updateItem:function(data){
        return  httpservices.get("/lmp/infrastruct/item/updateItem",data);
    },
    selectByItem:function(data){
        return  httpservices.get("/lmp/infrastruct/item/selectByItem",data);
    },
}