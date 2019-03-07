<style lang = "less">
    .page{
        padding: 15px 10px;
    }
    .form-item{
        width: 500px;
    }
</style>
<template>
    <div class="page">

        <Form :model="formData" :label-width="80">
            <Form-item :label="formUI.nameTitle" class="form-item">
                <Input v-model = "formData.bsName" placeholder="请输入"></Input>
            </Form-item>
            <Form-item :label="formUI.codeTitle"  class="form-item">
                <Input v-model = 'formData.bsCode' placeholder="请输入"></Input>
            </Form-item>
            <Form-item label="备注"  class="form-item">
                <Input v-model = 'formData.bsComment' type="textarea" :rows="4" placeholder="请输入"></Input>
            </Form-item>
            <Form-item>
                <Button @click = "confirmEdit">提交</Button>
                <Button @click = "cancelEidt">取消</Button>
            </Form-item>
            
        </Form>
    </div>
</template>
<script>

    export default{
        data(){
            return{
                formData:{
                    bsName:'',
                    bsCode:'',
                    bsComment:'',
                    id:''
                },
                formUI:{
                    nameTitle:'',
                    codeTitle:'',
                    commentTitle:'备注'
                },
                receiveData:{
                    type:1
                }
            }
        },
        created(){
    
            //init data
            let formObj = JSON.parse(this.$route.query.param);
            this.formData.bsName    = formObj.bsName;
            this.formData.bsCode    = formObj.bsCode;
            this.formData.bsComment = formObj.bsComment;
            this.formData.id        = formObj.id; 
            this.receiveData.type   = this.$route.query.type;

            if(this.receiveData.type == 1){
                this.formUI.nameTitle = "姓名";
                this.formUI.codeTitle = "编码";
        
            }else if(this.receiveData.type == 2){
                this.formUI.nameTitle = "组名称";
                this.formUI.codeTitle = "组编码";
                this.formData.id = formObj.id;
            }else{
                this.formUI.nameTitle = "组织名称";
                this.formUI.codeTitle = "组织编码";
                this.formData.id = formObj.id;
            }
        },
        methods:{
            cancelEidt(){
                history.go(-1);
            },
            confirmEdit(){
               if(this.type == 1){
                    this.modifyUser();
               }else if(this.type == 2){
                    this.modifyGroup();
               }else{
                   this.modifyOrg();
               }
            },
            modifyUser(){
                this.$Loading.start();
                this.api.user.editUser(this.formData).then((res)=>{
                    console.log(res);
                    this.$Loading.finish();
                    if(res.result == true){
                        this.$Message.success('修改成功')
                        history.go(-1);
                    }else{
                        this.$Message.error('修改失败')
                    }
                })
            },
            modifyGroup(){
                this.$Loading.start();
                this.api.group.editGroup(this.formData).then((res)=>{
                    console.log(res);
                    this.$Loading.finish();
                    if(res.result == true){
                        this.$Message.success('修改成功')
                        history.go(-1);
                    }else{
                        this.$Message.error('修改失败')
                    }
                })
            },
            modifyOrg(){
                this.$Loading.start();
                console.log(this.formData)
                this.api.organization.editOrganization(this.formData).then((res)=>{
                    console.log(res);
                    this.$Loading.finish();
                    if(res.result == true){
                        this.$Message.success('修改成功')
                        history.go(-1);
                    }else{
                        this.$Message.error('修改失败')
                    }
                })
            }
        }
    }

</script>

