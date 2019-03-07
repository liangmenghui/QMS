import httpService from '@/libs/service'

export default {
   
    getfile: function (data) {
        return httpService.download('/fileQms/get', data)   
    },
	upload: function (data) {
        return httpService.upload('/fileQms/upload ', data)   
    },
	getProductTree: function (data) {
        return httpService.get('/fs/doc/folder/getlist', data);
    },
    getExcel: function (data) {
        return httpService.download('/fileQms/getExcel', data) 
    },
    getReviewExcel: function (data) {
        return httpService.download('/fileQms/getReviewExcel', data) 
    },
    getFeedbackExcel: function (data) {
        return httpService.download('/fileQms/getFeedbackExcel', data) 
    },
    getShipmentExcel: function (data) {
        return httpService.download('/fileQms/getShipmentExcel', data) 
    },
	getProductExcel: function (data) {
        return httpService.download('/fileQms/getProductExcel', data) 
    },
}

