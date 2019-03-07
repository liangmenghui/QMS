<template>
    <div>
        <el-card>
            <el-row>
                <el-col :span="12">
                    <el-form :inline="true" :model="formQuery" class="demo-form-inline">
                      <el-form-item :label="$t('user.userCode')">
                        <el-input v-model="formQuery.userCode" placeholder="账号"></el-input>
                      </el-form-item>
                      <el-form-item :label="$t('user.userName')">
                        <el-input v-model="formQuery.userName" placeholder="姓名"></el-input>
                      </el-form-item>
                      <el-form-item>
                         <el-button type="primary" size="mini" @click="handleSubmit('formQuery')">{{$t('Button.Inquire')}}</el-button>
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
                  <el-col :span="6" >
                     <el-button-group  size="samll">
                     <el-button type="primary" @click="doAssigRoles">分配角色</el-button>
                      <el-button type="primary" @click="showRestDialog">重置密码</el-button>                                      
                     </el-button-group>
                </el-col>
            </el-row>           
        </el-card>
        <template>
              <el-table
                  border
                ref="main_table"
                :data="userTable"
                tooltip-effect="dark"
                style="width: 100%"
                 highlight-current-row
                 @select="select"
                @selection-change="handleSelectionChange">
                <el-table-column
                  type="selection"
                  width="45">
                </el-table-column>
                 <el-table-column
                  prop="userCode"
                  label="账号"
                  width="120"
                  show-overflow-tooltip>
                </el-table-column>

                <el-table-column
                  prop="userName"
                  label="姓名"
                  width="120"
                  show-overflow-tooltip>
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
                  <template scope="scope">
                      <span v-bind:class="{blue:scope.row.userStatus==0, red:scope.row.userStatus==1}">{{$t('user.userStatus['+scope.row.userStatus+']')}}</span>
                  </template>
         
                </el-table-column>
                 <el-table-column
                  prop="createdTime"
                  label="创建时间"               
                  show-overflow-tooltip>
                  <template scope="scope">
                    <span>{{(scope.row.createdTime != undefined&& scope.row.createdTime!== null) ?  scope.row.createdTime.substring(0,10) : ''}}</span>
                 </template>
                </el-table-column>
                <el-table-column
                  prop="modifiedTime"
                  label="更新时间"
                  show-overflow-tooltip>
                 <template scope="scope">
                    <span>{{(scope.row.modifiedTime != undefined && scope.row.modifiedTime!== null) ?  scope.row.modifiedTime.substring(0,10) : ''}}
                    </span>
                 </template>
                </el-table-column>
                 <el-table-column
                  prop="userComment"
                  label="备注"
                  show-overflow-tooltip>
                </el-table-column>  

              </el-table>             
        </template>
         <div class="block">
            <el-pagination class="pull-right clearfix" @current-change="changePage" @size-change="SizeChange" :current="1" :current-page.sync="queryParams.page" :page-sizes="pageSizesList" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper" :page-size-opts="pageSizesList" :total="totalCount">
            </el-pagination>
        </div>
        <el-dialog ref="dialog.ruleForm" :visible.sync="dialog.modal_dialog" title="用户管理" width="500px" :modal="true" :close-on-click-modal="false" :close-on-press-escape="true" append-to-body>
            <p>
                <el-form :model="dialog.formItem" ref="dialog.ruleForm" :rules="dialog.ruleForm" :label-width="'100px'" >
                   <el-col :span="20">
                        <el-form-item label="id" prop="roleCode" style="display: none" >
                            <el-input v-model="dialog.formItem.id" size="small" placeholder="请输入" ></el-input>
                        </el-form-item>
                    </el-col>
                <el-row>
                    <el-col :span="20">
                        <el-form-item label="编码" prop="userCode" >
                            <el-input v-model="dialog.formItem.userCode" size="small" placeholder="请输入" ></el-input>
                        </el-form-item>                     
                    </el-col>                 
                    
                    <el-col :span="20">
                        <el-form-item label="名称" prop="userName">
                            <el-input v-model="dialog.formItem.userName" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col>
                 <!--    <el-col span="20">
                        <el-form-item label="类型" prop="userType">
                            <el-input v-model="dialog.formItem.userType" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col> -->
                  <el-col :span="20">
                        <el-form-item label="状态" prop="userStatus">
                             <el-select v-model="dialog.formItem.userStatus" placeholder="请选择活动区域" style="width:280px">
                              <el-option  v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>                          
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="20">
                        <el-form-item label="手机号" prop="userMobile">
                            <el-input v-model="dialog.formItem.userMobile" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="20">
                        <el-form-item label="邮箱地址" prop="userEmail">
                            <el-input v-model="dialog.formItem.userEmail" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
              
                <el-row>
                    <el-col :span="20">
                        <el-form-item label="备注" prop="userComment">
                            <el-input v-model="dialog.formItem.userComment" size="small" type="textarea" placeholder="请输入..."></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                </el-form>
                <div slot="footer">
                    <el-button type="primary" size="small" :loading="false" @click="cancel">取 消</el-button>
                    <el-button type="primary" size="small" :loading="dialog.loading" @click="ok">确 定</el-button>
                </div>
            </p>
        </el-dialog>
   <!--  重置密码 -->
       <el-dialog title="重置密码" :visible.sync="dialogFormVisible" width="400px">
          <el-form :model="resetForm">
            <!-- <el-form-item label="id" :label-width="formLabelWidth">
              <el-input v-model="resetForm.id" ></el-input>
            </el-form-item> -->
            <el-form-item label="重置密码" :label-width="formLabelWidth">
              <el-input v-model="resetForm.password" ></el-input>
            </el-form-item>
            <el-form-item label="再次确认" :label-width="formLabelWidth">
              <el-input v-model="resetForm.rePassword" ></el-input>
            </el-form-item>           
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="resetPassword">确 定</el-button>
          </div>
        </el-dialog>


        <!--分配权限-->
        <el-dialog
            title="分配角色"
            :visible.sync="dialogVisible"
            width="50%"
            :before-close="handleClose">
            <div style="text-align: center">
                <el-transfer
                style="text-align: left; display: inline-block"
                v-model="value4"
                :right-default-checked="checkedRolesData"
                :titles="['所有', '选中']"
                :button-texts="['移除', '添加']"
                :format="{
                    noChecked: '${total}',
                    hasChecked: '${checked}/${total}'
                }"
                @change="handleChange"
                :data="rolesData">
                <span slot-scope="{ option }"> {{ option.label }}</span>
                <!-- <el-button class="transfer-footer" slot="left-footer" size="small">操作</el-button>
                <el-button class="transfer-footer" slot="right-footer" size="small">操作</el-button> -->
                </el-transfer>
            </div>
            <div slot="footer">
                    <el-button type="primary" size="small" :loading="false" @click="dialogVisible=false">取 消</el-button>
                    <el-button type="primary" size="small" :loading="dialog.loading" @click="saveRoles">确 定</el-button>
                </div>
        </el-dialog>
        <!--end-->
    </div>
 
</template>
<script>
import {addUserInfo,getUserList,editUser,delUserInfo,resetPassword} from '@/api/user'
import {getRoleList,getCheckedRoles,saveUserRoles} from '@/api/role'

  export default {
    name:'userManagement',
    data() {
        
        var checkPhone = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('手机号不能为空'));
        } else {
          const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
          console.log(reg.test(value));
          if (reg.test(value)) {
            callback();
          } else {
            return callback(new Error('请输入正确的手机号'));
          }
        }
   
    }

    var checkEmail = (rule, value, callback) => {
            const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
            if (!value) {
              return callback(new Error('邮箱不能为空'))
            }
            setTimeout(() => {
              if (mailReg.test(value)) {
                callback()
              } else {
                callback(new Error('请输入正确的邮箱格式'))
              }
            }, 100)
          }
      return {   
        dialogVisible: false, 
        rolesData:[],
        checkedRolesData:[],
        value4: [],        
        renderFunc(h, option) {
          return <span>{ option.label }</span>;
        },
        options:[{
            value: '0',
            label:"启用"
        },{
            value: '1',
            label:"禁用"
        }],
        resetForm:{},
        formLabelWidth: '100px',
        formQuery: {
                userCode: '',
                userName: ''
            },
        currentRow:[],
        userTable:[{

        }], 
        dialogFormVisible: false,
            
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
                    userMobile: [{ required: true, trigger: 'blur', validator:checkPhone}],
                    userEmail: [{ required: true, trigger: 'blur', validator:checkEmail}],                   
                }
        },
        queryParams: {
                page: 1,
                rows: 15,
                pkParent: -1
            },
            pageSizesList: [10, 20, 30, 40, 50, 100],
            totalCount: 0, //数据的总条数,
      }
    },
    created(){
        this.getData();
    },
    methods: {
    handleSubmit(name){
        this.getData();
    },
    getData(){ 
        getUserList(this.formQuery.userCode,this.formQuery.userName,this.queryParams.rows,this.queryParams.page).then(response => {
            if (!response.data) { 
            reject('error')
          }            
        this.userTable = response.data.rows;   
        //format(this.userTable.createdTime,'yyyy-MM-dd');
        this.totalCount =response.data.total;
        })
    },
    showAddDialog(){
        this.dialog.modal_dialog = true;
        this.dialog.formItem = {id: ''};
    },
/*编辑*/
    showEditDialog() {  

        if(!this.currentRow.id){               
            this.$message.error('请选择要操作的行');
            return
        }
        this.dialog.modal_dialog = true;  
        let r=this.currentRow
        this.dialog.formItem = {userCode:r.userCode, userName:r.userName, id:r.id, userEmail:r.userEmail,userMobile:r.userMobile,userComment:r.userComment};
        /*this.dialog.formItem = Object.assign({},row); */

    },
    handleSelectionChange(val){  
        if(val.length>0){
            this.currentRow = val[val.length-1];
        }else{
            this.currentRow = [];
        }
        
    },
    select(selection,row){
    if(selection.length>1){
        selection.shift()
    }    
},
/*删除*/
    doDelete(){      
       if(!this.currentRow.id){               
            this.$message.error('请选择要操作的行');
            return
        } 
        let id=this.currentRow.id    
        delUserInfo(id).then(response => {                                           
                       
            if(response.result) { 
                this.$message({
                message: response.msg,
                type: 'success'
                });                             
                this.getData();
            }else {
                this.$message.error(response.msg);
                }
        })
    },
    showRestDialog(){
  /*  let selection = this.currentRow ;  
    let row = selection[0]; */    
         if(!this.currentRow.id){
            this.$message.error("请选择要操作的行");
             return
        }else{
            this.dialogFormVisible=true;
            this.resetForm.id=this.currentRow.id;
        }    
    },
/*重置密码*/
    resetPassword(){
        let selection = this.currentRow;      
        // let row = selection[0];
        this.resetForm.id=selection.id;
        if(this.resetForm.password==this.resetForm.rePassword){
            resetPassword(this.resetForm.id,this.resetForm.password,this.resetForm.rePassword).then((response) => {
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
/*新增*/
    ok() {     
   
       if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {             
                this.$refs['dialog.ruleForm'].validate((valid) => {
                    if (valid) {
                       /* this.dialog.loading = true;*/
                        editUser(this.dialog.formItem).then(response => {                                                   
                            this.dialog.loading = false;
                            if(response.result) {
                                this.dialog.loading = false;
                                this.dialog.modal_dialog= false
                                this.getData();
                            }else {
                                this.$message.error(res.msg);
                            }
                        })
                     }
                });
            }else {
                 this.$refs['dialog.ruleForm'].validate((valid) => {
                    this.dialog.loading = true;
                    if (valid) {
                        addUserInfo(this.dialog.formItem).then(response => {
                            
                           /* this.dialog.loading = false;*/
                            if(response.result) {                                
                                this.dialog.loading = false;
                                this.dialog.modal_dialog=false;
                                this.getData();
                            }else {
                                this.$message.error(res.msg);
                            }
                        })
                    }
                });
            }
        },
        doAssigRoles(){            
            if(!this.currentRow.id){               
                this.$message.error('请选择要分配角色的用户');
                return
            }
            getCheckedRoles(this.currentRow.id).then(response => { 
              this.rolesData=[];         //所有角色的编号和名称
              this.value4=[];            //所选用户的角色编号
              this.checkedRolesData=[];  //左侧列表被用户选中的角色编号
              var date = response.data
              date.List.forEach((item, index) => {
                this.rolesData.push({
                  key:item.roleCode,
                  label:item.roleName
                })
              });
              this.value4 = date.CheckedList;
              this.checkedRolesData = date.CheckedList;
              this.dialogVisible = true;
            })
        },
        saveRoles(){
            var userId = this.currentRow.id;
            saveUserRoles(userId,this.value4.toString()).then(response => { 
                this.$message.info(response.msg)
                this.dialogVisible = false
            })
        },
      
        handleChange(value, direction, movedKeys) {
            console.log(value, direction, movedKeys);
        },
        cancel() {
            this.dialog.modal_dialog = false;
        },
        changePage(page) {          
            this.queryParams.page = page;
     
            this.getData();
        },
        SizeChange(size) {
            this.queryParams.rows = size;
            this.getData();
        },
        handleClose(){

        }
    }
  }
</script>
<style>
    .block {
    text-align: right;
    margin-top: 10px;
}
.blue{color:#66b1ff;}
.red{color:red;}
</style>