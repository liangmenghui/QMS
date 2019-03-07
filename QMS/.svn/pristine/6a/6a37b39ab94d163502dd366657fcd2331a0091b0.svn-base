import httpService from '@/libs/service'

export default {
    getFirstCata(data) {
        return httpService.get("/lmp/hs/prodcata/getFirstCata", data);
    },
    getSecondCata(data) {
        return httpService.get("/lmp/hs/prodcata/getSecondCata", data);
    },
    paging(data) {
        return httpService.get("/lmp/hs/prodcata/paging", data);
    },
    save( data){
        return httpService.postdata("/lmp/hs/prodcata/save",data);
    }
}