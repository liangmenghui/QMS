<template>      
<div class="itemRecord">
  <el-upload 
  class="upload-demo"
  action=""
  :before-upload="handleUpload"
  :on-preview="downloadFile"
  :on-remove="handleRemove"
  :file-list="fileList"  
  >
  <el-button size="small" type="primary" plain icon="el-icon-plus" v-if="canUpload">
    {{$t('upcoming.UploadAttachment')}}
  </el-button>
  <div slot="tip" class="el-upload__tip"></div>
</el-upload>
<!-- <div>{{bsTermsScoreId}}</div> -->

</div>
</template>
<script>
export default {
    props:['fileList','canUpload','bsTermsScoreId'],
    data() {
        return {
            dialogImageUrl: '',
            dialogVisible: false,
            file:[],
        }
    },
    created(){
        this.fileList.map(function (file) {
             file.name = file.fsFileBy.bsName; //显示文件名文件类型
             return file; 
        });
    },
    methods: {    
        handleRemove(file, fileList) {  
            if(!file.id || !this.canUpload) return;
            var params = {
                    id:file.id
                };      
            this.api.approvedTermsScoreFile.delete(params).then((res)=>{         
                if(res.result) {
                    //refresh
                    this.fileList.remove(file);
                    this.$Message.info('删除成功');
                }else {
                    this.$Message.error(res.msg);   
                }
            });
        },
     
        handleUpload (file) {
            this.file = file            
            this.getData();
            return true;
        },
        getData() { 
            let formData = new FormData();     
            formData.append('file', this.file);
			formData.append("bsTermsScoreId",this.bsTermsScoreId);
            this.api.approvedTermsScoreFile.add(formData).then((res) => {
                if(res.result) {
                    var file = Object.assign(res.data,{name:this.file.name});
                    this.fileList.push(file);       
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        downloadFile(file){  
            var params = {
                fsFileId:file.fsFileId
            };        
            this.api.fileQms.getfile(params).then((link) => {
                link.click(); 
            });
			//var params = {
            //    bsShipmentId:5152
            //};        
            //this.api.fileQms.getShipmentExcel(params).then((link) => {
            //    link.click(); 
            //});
        }
    }
  }
</script>
<style type="text/css">
    .Uploadbox{margin-top:15px;}
    .itemRecord .el-upload--text { 
    border: none;
    border-radius: 6px;
    box-sizing: border-box;
  
    text-align: center;
    cursor: pointer;
    position: relative;   
    
}

</style>