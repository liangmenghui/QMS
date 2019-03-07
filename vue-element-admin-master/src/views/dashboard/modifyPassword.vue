<template>
    <div class="info_container">
        <el-row type="flex" class="row-bg" justify="center" :gutter="20">

        <el-col :span="8">
            <div class="area">
                <div class="pwdarea">
                    <p class="title"><i class="fa fa-edit"></i>修改密码</p>
                        <el-form class="form"  :model="pwdForm" :rules="pwdRules" ref="pwdForm" label-width="100px">
                        <el-form-item label="原密码" prop="oldPassword">
                            <el-input type="password" v-model="pwdForm.oldPassword" auto-complete="off" size="mini" placeholder="请输入原密码"></el-input>
                        </el-form-item>
                        <el-form-item label="新密码" prop="password">
                            <el-input type="password" v-model="pwdForm.password" auto-complete="off" size="mini" placeholder="请输入新密码"></el-input>
                        </el-form-item>
                        <el-form-item label="确认新密码" prop="rePassword">
                            <el-input type="password" v-model="pwdForm.rePassword" auto-complete="off" size="mini" placeholder="请输入确认新密码"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="resetPassword('pwdForm')">提交</el-button>
                            <el-button @click="resetForm('pwdForm')">重置</el-button>
                        </el-form-item>
                        </el-form>
                </div>
            </div>
            
        </el-col>
   <!--          <el-col :span="8">
            <div class="area">
                <div class="pwdarea">
                    <p class="title"><i class="fa fa-edit"></i>用户信息</p>
                        <el-form class="form"  :model="pwdForm" :rules="pwdRules" ref="pwdForm" label-width="100px">
                        <el-form-item label="账号" prop="password">
                            <el-input type="password" v-model="pwdForm.password" auto-complete="off" size="mini" placeholder="请输入原密码"></el-input>
                        </el-form-item>
                        <el-form-item label="名称" prop="userName">
                            <el-input  v-model="pwdForm.userName" auto-complete="off" size="mini" placeholder="请输入新密码"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号" prop="userMobile">
                            <el-input type="number" v-model="pwdForm.userMobile" auto-complete="off" size="mini" placeholder="请输入确认新密码"></el-input>
                        </el-form-item>
                         <el-form-item label="邮箱" prop="userEmail">
                            <el-input type="password" v-model="pwdForm.userEmail" auto-complete="off" size="mini" placeholder="请输入确认新密码"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('pwdForm')">提交</el-button>
                            <el-button @click="resetForm('pwdForm')">重置</el-button>
                        </el-form-item>
                        </el-form>
                </div>
            </div>
            
        </el-col> -->
          
        </el-row>
       
     

    </div>
</template>

<script>
import {changePassworde} from '@/api/user'
import { mapGetters } from 'vuex'
    export default {
        name:'modifyPassword',
        data(){
             // 附带callback(),是在明确通过验证的情况下去掉输入框上的loading
            let validateEmail = (rule, value, callback) => {
                if(value == ''){
                    callback(new Error('请输入邮箱~'));
                    return;
                }
                let emailRegex = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
                if (!emailRegex.test(value)) {
                    callback(new Error('邮箱格式不正确！'))
                } else {
                    callback();
                }
            };
      
            // validateField:对部分表单字段进行校验的方法
            let validateNewpassword = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入新密码'));
                } else {
                    if (this.pwdForm.rePassword !== '') {
                        this.$refs.pwdForm.validateField('rePassword');
                    }
                    callback();
                }
            };
            let validaterePassword = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入确认密码'));
                } else if (value !== this.pwdForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                   callback();
                }
            };
            return {
              
               pwdForm:{
                   oldPassword:'',
                   password:'',
                   rePassword:''
               },             
          
               pwdRules: {
                    oldPassword: [
                        { required: true, message: '请输入原密码', trigger: 'blur' },
                    ],
                    password: [
                        { required: true, validator:validateNewpassword, trigger: 'blur' },
                    ],
                    rePassword: [
                        { required: true, validator:validaterePassword, trigger: 'blur' },
                    ],
               },       
             
            };           

        },
        computed: {
        ...mapGetters([
         'name',     
        ])
     },
        created(){
           
        },
      	mounted() {
          
	    },
        methods: {
           /*重置密码*/
            resetPassword(){  
                if(this.pwdForm.password==this.pwdForm.rePassword){
                    changePassworde(this.name,this.pwdForm.oldPassword,this.pwdForm.password,this.pwdForm.rePassword).then((response) => {
                    if(response.result) {               
                        this.$message.info(response.msg);
                    }else {
                        this.$message.error(response.msg);
                    }
                });
                }else{
                    this.$message.info("两次密码不一致，请重新输入!");
                } 
              
            },
        
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },       
            

        }
    }
</script>

<style>
    .info_container{
        padding: 10px;
        margin: 50px 10px;
        overflow: auto;

    }
    .area{
        border:1px solid #ccc;
        padding: 0px 20px 10px 10px;
    }
     .title{
        text-align:center;
        width:100%;
        height:30px;
        line-height:30px;
        cursor: pointer;
        background-color: #3bc5ff;
        border:1px solid #3bc5ff;
        color: white;
        display: block;
        .fa{
          margin-right:5px;
       }
    }

</style>


