import httpservice from '@/libs/service'

export default{

    selectList:function(data){
        return httpservice.get('/lmp/booking/configuration/page',data);
    },
    deleteBookingconfiguration:function(data){
        return httpservice.get('/lmp/booking/configuration/delete',data);
    },
    insertBookingconfiguration:function(data){
        return httpservice.get('/lmp/booking/configuration/add',data);
    },
    updateBookingconfiguration:function(data){
        return httpservice.get('/lmp/booking/configuration/update',data);
    },
    selectBy:function(data){
        return httpservice.get('/lmp/booking/configuration/get',data);
    },
   addContent:function(data){
       return httpservice.post('/lmp/booking/configuration/addContent',data);
   },
   getById:function(data){
       return httpservice.get('/lmp/booking/configuration/getById',data);
   },
   contentList:function(data){
       return httpservice.get('/lmp/booking/configuration/content/list',data);
   }
}