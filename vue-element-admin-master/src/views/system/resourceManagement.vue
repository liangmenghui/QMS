<template>
    <div>
        <el-card>
            <el-row>
                <el-col :span="12">
                    <el-form :inline="true" :model="formQuery" class="demo-form-inline">
                      <el-form-item label="账号">
                        <el-input v-model="formQuery.userCode" placeholder="账号"></el-input>
                      </el-form-item>
                      <el-form-item label="姓名">
                        <el-input v-model="formQuery.userName" placeholder="姓名"></el-input>
                      </el-form-item>
                      <el-form-item>
                         <el-button type="primary" size="mini" @click="handleSubmit('formQuery')">查找</el-button>
                      </el-form-item>

                  </el-form>
                </el-col>
                <el-col :span="6">
                     <el-button-group size="samll">
                     <el-button type="primary" icon="el-icon-plus" @click="showAddDialog()">新增</el-button>
                     <el-button type="primary" icon="el-icon-delete" @click="doDelete()">删除</el-button>
                     <el-button type="primary" icon="el-icon-edit" @click="showEditDialog()">编辑</el-button>
                     </el-button-group>
                </el-col>
                  <el-col :span="4" >
                     <el-button-group  size="samll">
                     <el-button type="primary" >分配角色</el-button>
                     <el-button type="primary" >重置密码</el-button>                     
                     </el-button-group>
                </el-col>
            </el-row>           
        </el-card>
        <template>
              <el-table
                ref="multipleTable"
                border
                :data="userTable"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange">
                <el-table-column
                  type="selection"
                  width="45">
                </el-table-column>
                 <el-table-column
                  prop="userCode"
                  label="账号"
                  width="120">
                </el-table-column>

                <el-table-column
                  prop="userName"
                  label="姓名"
                  width="120">
                </el-table-column>
                <el-table-column
                  prop="userMobile"
                  label="手机号"
                  show-overflow-tooltip>
                </el-table-column>
                 <el-table-column
                  prop="userEmail"
                  label="邮箱"
                  show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                  prop="userStatus"
                  label="状态"
                  show-overflow-tooltip>
                </el-table-column>
                 <el-table-column
                  prop="createdTime"
                  label="创建时间"
                  show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                  prop="modifiedTime"
                  label="更新时间"
                  show-overflow-tooltip>
                </el-table-column>
                 <el-table-column
                  prop="bsComment"
                  label="备注"
                  show-overflow-tooltip>
                </el-table-column>

              </el-table>             
        </template>
        <el-dialog ref="dialog.ruleForm" :visible.sync="dialog.modal_dialog" title="新增用户" width="500px" :modal="true" :close-on-click-modal="false" :close-on-press-escape="true" append-to-body>
            <p>
                <el-form :model="dialog.ruleForm" ref="adduserForm" :rules="dialog.ruleForm" :label-width="'100px'" >
    
                <el-row>
                    <el-col span="20">
                        <el-form-item label="编码" prop="userCode" >
                            <el-input v-model="dialog.formItem.userCode" size="small" placeholder="请输入" ></el-input>
                        </el-form-item>
                    </el-col>
                     <el-col span="20">
                        <el-form-item label="密码" prop="userPassword" >
                            <el-input v-model="dialog.formItem.userPassword" size="small" placeholder="请输入" ></el-input>
                        </el-form-item>
                    </el-col>
                    
                    <el-col span="20">
                        <el-form-item label="名称" prop="userName">
                            <el-input v-model="dialog.formItem.userName" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col>
                 <!--    <el-col span="20">
                        <el-form-item label="类型" prop="userType">
                            <el-input v-model="dialog.formItem.userType" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col> -->
                  <!--    <el-col span="20">
                        <el-form-item label="状态" prop="userStatus">
                            <el-input v-model="dialog.formItem.userStatus" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col> -->
                </el-row>
                <el-row>
                    <el-col span="20">
                        <el-form-item label="手机号" prop="bsMobile">
                            <el-input v-model="dialog.formItem.bsMobile" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col span="20">
                        <el-form-item label="邮箱地址" prop="bsEmail">
                            <el-input v-model="dialog.formItem.bsEmail" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
              
                <el-row>
                    <el-col span="20">
                        <el-form-item label="备注" prop="bsComment">
                            <el-input v-model="dialog.formItem.bsComment" size="small" type="textarea" placeholder="请输入..."></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                </el-form>
                <div slot="footer">
                    <el-button type="primary" size="small" :loading="false" @click="cancel">取 消</el-button>
                    <el-button type="primary" size="small" :loading="dialog.loading" @click="ok">确 定11</el-button>
                </div>
            </p>
        </el-dialog>
    </div>
 
</template>
<script>
import {addUserInfo,getUserInfo,getUserList} from '@/api/user'
  export default {
    name:'resourceManagement',
    data() {
      return {        
        formQuery: {
                bsCode: '',
                bsName: ''
            },
        userTable:[{

        }],
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        dialog: {
                loading: false,
                modal_dialog: false,
                formItem: {
                    
                },
                ruleForm: {
                    userCode: [
                        { required: true, message: '请填写帐号', trigger: 'blur' }
                    ],
                    userName: [
                        { required: true, message: '请填写姓名', trigger: 'blur' }
                    ],
                    bsMobile: [
                        { required: true, message: '请填写正确手机号', trigger: 'blur',type:'number'}
                    ]
                }
        },
      }
    },
    methods: {
    handleSubmit(name){       
        this.getData();
    },
    getData(){        
        getUserList().then(response => {           
             if (!response.data) { 
                reject('error')
          }
          this.userTable = response.data
        }).catch(error => {
          reject(error)
        })
    },
    showAddDialog(){
        this.dialog.modal_dialog = true;

    },
/*编辑*/
 showEditDialog(params) {
        let selection = this.$refs.main_table.getSelection();
        if(selection.length==0) {
            this.$message.error("请选择要操作的行");
            return;
        }
        this.dialog.modal_dialog = true;
        let r = selection[0];
        this.dialog.formItem = {pkGroup:r.pkGroup, pkOrg:r.pkOrg, id:r.id,bsCode:r.bsCode, bsName:r.bsName, bsMobile:r.bsMobile, bsEmail:r.bsEmail,pkPerson:r.pkPerson,bsComment:r.bsComment};
    },
    handleSelectionChange(val){

},
/*新增*/
    ok() {     
    /*  console.log(this.dialog.formItem.userName)
      var param={
         userCode:this.dialog.formItem.userCode,
         userName:this.dialog.formItem.userName,
         userPassword:this.dialog.formItem.userPassword
      }*/
       addUserInfo(this.dialog.formItem).then(response => {                        
                            this.dialog.loading = false;
                            if(res.result) {
                                this.dialog.modal_dialog = false;
                                this.getData();
                            }else {
                                this.$message.error(res.msg);
                            }
                        }).catch(error => {
                            reject(error)
                    });
      /*
       if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {
              
               this.$refs.adduserForm.validate(valid => {
                    if (valid) {
                        this.dialog.loading = true;
                        addUserInfo(param).then(response => {
                            debugger
                            this.dialog.loading = false;
                            if(res.result) {
                                this.dialog.modal_dialog = false;
                                this.getData();
                            }else {
                                this.$Message.error(res.msg);
                            }
                        }).catch(error => {
                         reject(error)
                    });
                     }
                });
            }else {
                 this.$refs["dialog.ruleForm"].validate((valid) => {
                    this.dialog.loading = true;
                    if (valid) {
                       addUserInfo(userTable).then(response => {
                        debugger
                            this.dialog.loading = false;
                            if(res.result) {
                                this.dialog.modal_dialog = false;
                                this.getData();
                            }else {
                                this.$Message.error(res.msg);
                            }
                        }).catch(error => {
                            reject(error)
                    });
                    }
                });
            }*/
        },
        cancel() {
            this.dialog.modal_dialog = false;
        },
    }
  }
</script>