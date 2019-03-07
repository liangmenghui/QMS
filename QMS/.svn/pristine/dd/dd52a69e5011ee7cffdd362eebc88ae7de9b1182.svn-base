 <template>      
<div>
  <el-upload 
  class="upload-demo"
  action="" 
  :before-upload="handleUpload"
  > 
  <el-button type="primary"  size="small"><i class="el-icon-upload el-icon--right"></i>{{$t('approved.uploadfiles')}}</el-button>
 
   
  <div slot="tip" class="el-upload__tip"></div>
</el-upload>

</div>
</template>
<script>
export default {
    props:['property'],
    data() {
        return {
            dialogImageUrl: '',
            dialogVisible: false,
            file:{}
        }
    },
    created(){
    },
    methods: {    
        handleUpload (file) {
            this.file = file 
            let formData = new FormData();     
            formData.append('file', this.file);
            
            this.api.fileQms.upload(formData).then((res) => {
                if(res.result) {
                    var file = Object.assign(res.data,{name:this.file.name});
					this.$Message.info("上传成功");
                    this.$emit('on-upload', file, this.property);      
                }else {
                    this.$Message.error(res.msg);
                }
            });          
           
            return false;
           
        },
    }
  }
</script>
<style type="text/css">
    .Uploadbox{margin-top:15px;}
    /*.upload-demo{ width: 10%;}*/
    .fileup {
		/*width: 25%;
		float: left;*/
		display: inline-block;
	}
 /*   .el-upload--text {
    background-color: #fff;
    border: none;
    border-radius: 6px;
    box-sizing: border-box;
    width: 20%;
    height: 20%;
    text-align: center;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    
}*/

</style>