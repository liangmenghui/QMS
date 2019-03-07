import httpService from '@/libs/service'

export default {
    getlist: function (data) {
        return httpService.get('/pmp/getlist', data)
    },
    runCabinetsPlan: function (data,loading,failure) {
        return httpService.postdata('/pmp/runCabinetsPlan', data,loading,failure)
    },
    getShipmentPlan:function(data){
        return httpService.get('/pmp/getShipmentPlan', data)
    },
    getShipmentPlanDetail:function(data){
        return httpService.get('/pmp/getShipmentPlanDetail', data)
    },
    runErpPoPlan:function(data,loading,failure){
        return httpService.get('/pmp/runErpPoPlan', data,loading,failure)
    },
    getPallet:function(data){
        return httpService.get('/pmp/getPallet', data)
    },
    editPallet:function(data){
        return httpService.get('/pmp/editPallet', data)
    },
    getDock:function(data){
        return httpService.get('/pmp/getDock', data)
    },
    editDock:function(data){
        return httpService.get('/pmp/editDock', data)
    },
    addDock:function(data){
        return httpService.get('/pmp/addDock', data)
    },
    selectTransCode:function(data){
        return httpService.get('/pmp/getAllTransCode', data)
    },
    selectYearWeek:function(data){
        return httpService.get('/pmp/getYearWeek', data)
    },
    addPallet:function(data){
        return httpService.get('/pmp/addPallet',data)
    },
    deletePallet:function(data){
        return httpService.get('/pmp/deletePallet',data)
    },
    selectTitle:function(data){
        return httpService.get('/pmp/selectTitle',data)
    },
    getShipmentPlanEdit:function(data){
        return httpService.get('/pmp/getShipmentPlanEdit',data)
    },
    querySumOfWeek:function(data){
        return httpService.get('/pmp/querySumOfWeek',data)
    },
    testShipment:function(data){
        return httpService.postdata('/pmp/testShipment',data)
    },
    saveShipment:function(data){
        return httpService.postdata('/pmp/saveShipment',data)
    }
}