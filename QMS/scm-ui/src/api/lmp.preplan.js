import httpservices from '@/libs/service'

export default {
    selectlist:function(data){
        return  httpservices.get("/lmp/infrastruct/preplan/selectlist",data);
    },
    selectby:function(data){
        return  httpservices.get("/lmp/infrastruct/preplan/selectby",data);
    },
    selectbyDate:function(data){
        return  httpservices.get("/lmp/infrastruct/preplan/selectbyDate",data);
    },
}