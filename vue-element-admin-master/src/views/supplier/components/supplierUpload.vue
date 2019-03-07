<template>      
<div class="itemRecord">
  <el-upload 
  class="upload-demo"
  action="" 
  :before-upload="handleUpload"
  :on-preview="downloadFile"
  :on-remove="handleRemove"   
  >

  <el-button icon="el-icon-plus" size="small"type="primary" plain  >
     上传文件
  </el-button>
  <div slot="tip" class="el-upload__tip"></div>
</el-upload>  
</div>
</template>
<script>
import {uplaodFile,viewFile} from '@/api/supplier'
export default {
    props:['property'],
    data() {
        return {      
            file:{},
            fileList:[]
        }
    },
    created(){
       /* this.fileList.map(function (file) {
             file.name = file.fsFileBy.bsName; //显示文件名文件类型
             return file;           

        });*/
    },
    methods: {      
        handleUpload(file) {            
            this.file = file 
              let formData = new FormData();     
              formData.append('file', this.file);
            uplaodFile(formData).then((response) => {
                                         
                if(response.result) {
                    var file = Object.assign(response.data,{name:this.file.name});
                    this.$message.info(response.msg);
                    //this.fileList.push(file);    
                
                    this.$emit('on-upload', file, this.property);      
                }else {
                    this.$Message.error(res.msg);
                }
            });
            return false;
        }, 
        downloadFile(){

        },
        handleRemove(){
          
        }
    
    }
  }
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 130px;
    height: 130px;
    line-height: 130px;
    text-align: center;
  }
  .avatar {
    width: 130px;
    height: 130px;
    display: block;
  }
  .el-upload-list__item-name{margin-right:5px;}
</style>