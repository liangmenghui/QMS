<template>
    <div>
        <el-card>
            <el-row>
                <el-col :span="14">
                    <el-form :inline="true" :model="formQuery" class="demo-form-inline">
                      <el-form-item label="角色编号">
                        <el-input v-model="formQuery.roleCode" placeholder="角色编号"></el-input>
                      </el-form-item>
                      <el-form-item label="角色名称">
                        <el-input v-model="formQuery.roleName" placeholder="角色名称"></el-input>
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
              
            </el-row>           
        </el-card>
        <template>
              <el-table
                ref="multipleTable"
                border
                :data="roleTable"
                tooltip-effect="dark"
                style="width: 100%"
                 @select="select"
                @selection-change="handleSelectionChange">
                <el-table-column
                  type="selection"
                  width="45">
                </el-table-column>
                 <el-table-column
                  prop="roleCode"
                  label="角色编号"
                   show-overflow-tooltip
                 >
                </el-table-column>

                <el-table-column
                  prop="roleName"
                  label="角色名称"
                   show-overflow-tooltip
                  >
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
                  prop="roleComment"
                  label="备注"
                  show-overflow-tooltip>
                </el-table-column>

              </el-table>             
        </template>
          <div class="block">
            <el-pagination class="pull-right clearfix" @current-change="changePage" @size-change="SizeChange" :current="1" :current-page.sync="queryParams.page" :page-sizes="pageSizesList" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper" :page-size-opts="pageSizesList" :total="totalCount">

            </el-pagination>
        </div>
        <el-dialog ref="dialog.addRole" :visible.sync="dialog.modal_dialog" title="角色管理" width="500px" :modal="true" :close-on-click-modal="false" :close-on-press-escape="true" append-to-body>
            <p>
                <el-form :model="dialog.formItem" ref="adduserForm" :rules="dialog.ruleForm" :label-width="'100px'" >
    
                <el-row>
                   <el-col span="20">
                        <el-form-item label="id" prop="id" style="display: none" >
                            <el-input v-model="dialog.formItem.id" size="small" placeholder="请输入" ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col span="20">
                        <el-form-item label="角色编码" prop="roleCode" >
                            <el-input v-model="dialog.formItem.roleCode" size="small" placeholder="请输入" ></el-input>
                        </el-form-item>
                    </el-col>
                    
                    <el-col span="20">
                        <el-form-item label="角色名称" prop="roleName">
                            <el-input v-model="dialog.formItem.roleName" size="small" placeholder="请输入"></el-input>
                        </el-form-item>
                    </el-col>
              </el-row>
                <el-row>
                    <el-col span="20">
                        <el-form-item label="备注" prop="roleComment">
                            <el-input v-model="dialog.formItem.roleComment" size="small" type="textarea" placeholder="请输入..."></el-input>
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
    </div>
 
</template>
<script>

import {getRoleList,getCheckedRoles,saveUserRoles,addRole,delRoleInfo,editRole} from '@/api/role'
  export default {
    name:'roleManagement',
    data() {
      return {   
        dialogVisible: false, 
        rolesDate:[],
        checkedRolesDate:[],
        value4: [],  
        formQuery: {
                roleCode: '',
                roleName: ''
            },
        currentRow:[],
        roleTable:[{

        }], 
            
        dialog: {
                loading: false,
                modal_dialog: false,
                formItem: {
                    
                },
                ruleForm: {
                    roleCode: [
                        { required: true, message: '请填写角色编号', trigger: 'blur' }
                    ],
                    roleName: [
                        { required: true, message: '请填写名称', trigger: 'blur' }
                    ],
                  
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
        getRoleList(this.formQuery.roleCode,this.formQuery.roleName,this.queryParams.rows,this.queryParams.page).then(response => {              
             if (!response.data) { 
                reject('error')
          }
          this.roleTable = response.data.rows;
          this.totalCount =response.data.total;
        })
    },
    showAddDialog(){
        this.dialog.modal_dialog = true;
        this.dialog.formItem = {id: ''};
    },
/*编辑*/
    showEditDialog() {  
       
       if(!this.currentRow){               
            this.$message.error('请选择要操作的行!');
            return
        } 
        this.dialog.modal_dialog = true;
        let r =this.currentRow ;      
        this.dialog.formItem = {roleCode:r.roleCode, roleName:r.roleName, id:r.id, roleComment:r.roleComment};
    },
/*删除角色*/
    doDelete(){    
        if(!this.currentRow){
                this.$message.error("请选择要操作的行!");
                return
            }
        let r = this.currentRow;
        let id=r.id;      
            delRoleInfo(id).then(response => {                                           
                           
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

    handleSelectionChange(val){  
        this.currentRow = val[val.length-1];
    },
    select(selection,row){
    if(selection.length>1){
        selection.shift()
    }
    
},
/*新增*/
    ok() {     
   
       if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {             
                this.$refs['adduserForm'].validate((valid) => {
                    if (valid) {                   
                        editRole(this.dialog.formItem).then(response => {                                                   
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
                 this.$refs['adduserForm'].validate((valid) => {                 
                    if (valid) {
                       addRole(this.dialog.formItem).then(response => {                          
                        
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
        saveRoles(){
            var userId = this.currentRow[0].id;
            saveUserRoles(userId,this.value4.toString()).then(response => { 
                this.$message.info(response.msg);
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
        }
    }
  }
</script>
<style type="text/css">
.block {
    text-align: right;
    margin-top: 10px;
}
</style>