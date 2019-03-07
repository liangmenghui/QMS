 <template>      
<div>
  <el-upload 
  class="upload-demo"
  action=""
  :before-upload="handleUpload"
  :on-preview="downloadFile"
  :on-remove="handleRemove"
  :file-list="fileList"  
  >
  <el-button size="small" type="success" plain icon="el-icon-plus" v-if="canUpload">
    {{$t('upcoming.UploadAttachment')}}
  </el-button>
  <div slot="tip" class="el-upload__tip"></div>
</el-upload>

</div>
</template>
<script>
export default {
    props:['shipmentInspectId','canUpload','fileList'],

    data() {
        return {
            dialogImageUrl: '',
            dialogVisible: false,
            file:[],
            //fileList:[],           
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
            this.api.shipmentInspectFile.delete(params).then((res)=>{   
                this.fileList.remove();
                this.$Message.info('删除成功');
            });
        },
        handleUpload (file) {
            this.file = file;  
            let formData = new FormData();     
            formData.append('file', this.file);
            formData.append("bsShipmentId",this.shipmentInspectId);

            this.api.shipmentInspectFile.add(formData).then((res) => {
                var file = Object.assign(res.data,{name:this.file.name});
                this.fileList.push(file);    
            });
            return true;
        },
        downloadFile(file){
            var params = {
                fsFileId:file.fsFileId
            };        
            this.api.fileQms.getfile(params).then((link) => {
                link.click(); 
            });
        }
    }
  }
</script>
<style type="text/css">
    .Uploadbox{margin-top:15px;}
    .el-upload--text {
    background-color: #fff;
    border: none;
    border-radius: 6px;
    box-sizing: border-box;
  
    text-align: center;
    cursor: pointer;
    position: relative;   
    
}

.el-upload-list__item{width: 60%;}

</style>