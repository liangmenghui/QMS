<style lang="less">
  
        
    .inputItem{
        margin-top: 10px;
    }
    
</style>

<template>
    <div class="component">
        <Modal
            v-model="isShow"
            :title="this.uiObject.title"
            @on-ok="ok"
            @on-cancel="cancel">
            <div style="padding:5px 5px;">
                <Input v-model="userModel.bsName" :placeholder="uiObject.nameholder" class="inputItem"></Input>
                <Input v-model="userModel.bsCode" :placeholder="uiObject.codeholder" class="inputItem"></Input>
                <Input v-model="userModel.bsComment" :placeholder="uiObject.commentholder" class="inputItem"></Input>
            </div>
        </Modal>
    </div>
</template>

<script>

    export default {
        data(){
            return{
                uiObject:{
                    title:'',
                    nameholder:'',
                    codeholder:'',
                    commentholder:''
                },
                userModel:{
                    bsName:'',
                    bsCode:'',
                    bsComment:'',
                    pkGroup:'-1',
                    pkOrg:'-1'
                }
            }
        },
        props:{
            isShow:Boolean,
            //type=1：添加用户，2：添加组，3：添加组织
            type:{
                type:Number,
                default:1
            }
        },
        created(){
            console.log(this.type);
            if(this.type == 1){
                this.uiObject.title = '添加用户';
                this.uiObject.nameholder = '请输入用户姓名';
                this.uiObject.codeholder = '请输入用户编码';
                this.uiObject.commentholder = '请输入用户备注';
            }else if(this.type == 2){
                this.uiObject.title = '添加组';
                this.uiObject.nameholder = '请输入组的名称';
                this.uiObject.codeholder = '请输入组编码';
                this.uiObject.commentholder = '请输入组备注';
            }else{
                this.uiObject.title = '添加组织';
                this.uiObject.nameholder = '请输入组织名称';
                this.uiObject.codeholder = '请输入组织编码';
                this.uiObject.commentholder = '请输入组织备注';
            }
        },
        methods:{
            ok () {
                this.$emit("onClickOk",this.userModel);
                
            },
            cancel () {
               this.$emit("onClickCancel");
            }
        }
    }

</script>