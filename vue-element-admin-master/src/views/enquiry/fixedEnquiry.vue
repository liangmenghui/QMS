<template>
    <div>
        <el-card>
            <el-row>
                <el-col :span="14">
                    <el-form :inline="true" :model="formQuery" class="demo-form-inline">
                      <el-form-item label="供应商编号">
                        <el-input v-model="formQuery.inquirySuppCode" placeholder="请输入供应商编号查询"></el-input>
                      </el-form-item>
                      <el-form-item label="">
                        <el-input v-model="formQuery.keyWord" placeholder="关键字查询"></el-input>
                      </el-form-item>
                      <el-form-item>
                         <el-button type="primary" size="small" @click="getData">查找</el-button>
                      </el-form-item>
                  </el-form>
                </el-col>
                <el-col :span="6">
                     <el-button-group size="samll">
                     <el-button type="primary" icon="el-icon-plus" @click="showAddDialog()">新增询价</el-button>                                   
                     </el-button-group>
                </el-col>            
            </el-row>           
        </el-card>
        <template>
              <el-table
                ref="multipleTable"
                :data="inquiryTable"
                tooltip-effect="dark"
                style="width: 100%"
                 highlight-current-row              
                @selection-change="handleSelectionChange">
                <el-table-column
                  type="selection"
                  width="55">
                </el-table-column>
                 <el-table-column
                  prop="mateName"
                  label="物料名称"
                  width="120">
                </el-table-column>
                <el-table-column
                  prop="mateModel"
                  label="物料规格"
                  width="120">
                </el-table-column>
                <el-table-column
                  prop="purchasingAgent"
                  label="采购员"
                  show-overflow-tooltip>
                </el-table-column>             
                 <el-table-column
                  prop="inquiryNumber"
                  label="询价数量"
                  show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                  prop="inquirySuppCode"
                  label="供应商编号"
                  show-overflow-tooltip>
                </el-table-column>                
                <el-table-column
                  prop="inquirySuppName"
                  label="供应商名称"
                  show-overflow-tooltip>                
                </el-table-column>
                <el-table-column
                  prop="inquiryStartDate"
                  label="询价起始日期"
                  show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                  prop="inquiryDeadLine"
                  label="询价截止日期"
                  show-overflow-tooltip>
                </el-table-column>             

                <el-table-column label="操作" width="120">
                <template slot-scope="scope">
                    <a class="operatIcon colorblue" @click="viewDilog(scope.row)"><i class="el-icon-search"></i></a>     
                    <a class="operatIcon colorgreen" @click="showEditDialog(scope.row)"><i class="el-icon-edit"></i></a> 
                                   
                <MessageBox @callConfirm="doDelete(scope.row)" title="提示" contents="此操作将永久删除该行, 是否继续?" confirmTitle="确认删除"></MessageBox>                        
                </template>
                 </el-table-column>           

              </el-table>             
        </template>
         <div class="block">
            <el-pagination class="pull-right clearfix" @current-change="changePage" @size-change="SizeChange" :current="1" :current-page.sync="queryParams.page" :page-sizes="pageSizesList" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper" :page-size-opts="pageSizesList" :total="totalCount">
            </el-pagination>
        </div>  
<!-- 模态框  -->
      <el-dialog ref="dialog.ruleForm" :visible.sync="dialog.modal_dialog" title="新增询价" width="900px" :modal="true" :close-on-click-modal="false" :close-on-press-escape="true" append-to-body>
        <p>
            <el-form :model="dialog.formItem" ref="dialog.ruleForm" :rules="dialog.ruleForm" :label-width="'100px'" >
              <!--  <el-col span="20">
                    <el-form-item label="id" prop="roleCode"  >
                        <el-input v-model="dialog.formItem.mateId" size="small" placeholder="请输入" ></el-input>
                    </el-form-item>
                </el-col> -->
            <el-row>
                <el-col :span="8">
                    <el-form-item label="物料名称" prop="mateName" >                        
                        <el-autocomplete v-model="dialog.formItem.mateName"  v-bind:disabled="isdisabled"
                        :fetch-suggestions="queryProduct" @select="handleSelectProduct" style="width: 100%">
                        </el-autocomplete>
                    </el-form-item>                     
                </el-col>                 
                
                <el-col :span="8">
                    <el-form-item label="物料规格" prop="mateModel">
                        <el-input v-model="dialog.formItem.mateModel" size="small" placeholder="请输入" v-bind:readonly="isReadOnly"></el-input>
                    </el-form-item>
                </el-col>
                   <el-col :span="8">
                    <el-form-item label="供应商编号" prop="inquirySuppCode">
                        <el-input v-model="dialog.formItem.inquirySuppCode" size="small" placeholder="请输入" v-bind:readonly="isReadOnly"></el-input>
                    </el-form-item>
                </el-col>          
            </el-row>
            <el-row>                
              <el-col :span="8">
                <el-form-item label="物料分类" prop="mateCategory">
                    <!--  <el-select v-model="dialog.formItem.categoryName" placeholder="请选择活动区域"  v-bind:disabled="isdisabled">
                      <el-option  v-for="item in category" :key="item.value" :label="item.label" :value="item.value" ></el-option>                         
                    </el-select> -->
                     <el-input v-model="dialog.formItem.mateCategory" size="small" placeholder="请输入" v-bind:readonly="isReadOnly"></el-input>
                </el-form-item>
                </el-col>             
                <el-col :span="8">
                    <el-form-item label="供应商名称" prop="inquirySuppName">
                        <el-input v-model="dialog.formItem.inquirySuppName" size="small" placeholder="请输入" v-bind:readonly="isReadOnly"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="询价数量" prop="inquiryNumber">
                        <el-input v-model="dialog.formItem.inquiryNumber" size="small" placeholder="请输入" v-bind:readonly="isReadOnly"></el-input>
                    </el-form-item>
                </el-col>               
            </el-row>
            <el-row>
                <el-col :span="8">
                    <el-form-item label="采购员" prop="purchasingAgent">
                        <el-input v-model="dialog.formItem.purchasingAgent" size="small" placeholder="请输入" v-bind:readonly="isReadOnly"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="联系人" prop="inquiryContact">
                        <el-input v-model="dialog.formItem.inquiryContact" size="small" placeholder="请输入" v-bind:readonly="isReadOnly"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="电话" prop="inquiryMobile">
                        <el-input v-model="dialog.formItem.inquiryMobile" size="small" placeholder="请输入" v-bind:readonly="isReadOnly"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            </el-form>
            <div slot="footer">
                <el-button type="primary" size="small"  @click="dialog.modal_dialog = false">取 消</el-button>
                <el-button type="primary" size="small" :loading="dialog.loading" @click="ok">确 定</el-button>
            </div>
        </p>
    </el-dialog>  
        <!--end-->
    </div>
 
</template>
<script>
import {addMaterielInquiry,getMaterielInquiryList,delMaterielInquiry,editMaterielInquiry} from '@/api/inquiry'
import {getMaterielList} from '@/api/materiel'
import MessageBox from './components/MessageBox.vue'
  export default {
    name:'fixedInquiry',
    components:{
     MessageBox
    },
    data() {
      return {        
        rolesDate:[],
        category:[],
        dialogFormVisible :false,
        materiel:[],
        isReadOnly:true,
        isdisabled:true,
        multipleSelection: [],
        checkedRolesDate:[],
        value4: [],    
        formQuery: {
            suppCode: '',
            suppChineseName: ''
            },
        currentRow:[],
        inquiryTable:[{

        }],          
        queryParams: {
            page: 1,
            rows: 15,
            pkParent: -1
            },
        pageSizesList: [15, 20, 30, 40, 50, 100],
        totalCount: 0, //数据的总条数,
         dialog: {
                loading: false,
                modal_dialog: false,
                formItem: {
                    
                },
                ruleForm: {
                    mateName: [
                        { required: true, message: '请填写物料名称', trigger: 'blur' }
                    ],
                    mateModel: [
                        { required: true, message: '请填写物料规格', trigger: 'blur' }
                    ],                   
                                    
                }
        },
      }
    }, 
    created(){
        this.getData(); 
    },
    watch:{
    '$route':"getData"
    },
    methods: {      
    handleSubmit(name){
        this.getData();
    },
    getData(){   
    //获取询价列表
        getMaterielInquiryList(this.formQuery.inquirySuppCode,this.formQuery.keyWord,this.queryParams.rows,this.queryParams.page).then(response => {         
                if (!response.data) { 
                reject('error')
          }        
        this.inquiryTable = response.data.rows;
        this.totalCount =response.data.total;
        })
      
    },
    //显示物料信息
    handleSelectProduct(item) {       
        this.getMaterielData(item.mateName);
        this.dialog.formItem.mateId = item.id;
        this.dialog.formItem.mateModel = item.mateModel;  
        this.dialog.formItem.mateName = item.mateName;  
        this.dialog.formItem.categoryName=item.categoryName;     
    },
    //获取物料信息
    getMaterielData(keyWord){
        getMaterielList(this.formQuery.mateK3Code, keyWord, this.queryParams.rows, this.queryParams.page).then(response => {
        if (!response.data) {
          reject('error')
        }
        this.materiel = response.data.rows.map(function (row) {             
            return row;
        });
      })
    },
    queryProduct(queryString, cb) {       
      getMaterielList(this.formQuery.mateK3Code, queryString, this.queryParams.rows, this.queryParams.page).then((res) => {
            this.materiel = res.data.rows.map(function (row) {
                row.value = row.mateName;
                return row;
            });
            cb(this.materiel);
        });
    },
    showAddDialog(){
        this.dialog.modal_dialog = true;
        this.dialog.formItem = {id: ''};        
        this.isReadOnly=false;
        this.isdisabled=false;
       
    },
    //编辑显示
     showEditDialog(row) {     
     this.dialog.formItem =Object.assign({},row);
     this.dialogTitle="编辑物料";
     this.isReadOnly = false;
     this.isdisabled=false;
     this.dialog.modal_dialog = true;
    },
    //新增,编辑列表
    ok() {     
   
       if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {             
                this.$refs['dialog.ruleForm'].validate((valid) => {
                    if (valid) {
                       /* this.dialog.loading = true;*/
                        editMaterielInquiry(this.dialog.formItem).then(response => {                                                   
                            this.dialog.loading = false;
                            if(response.result) {
                                this.dialog.loading = false;
                                this.dialog.modal_dialog= false;                              
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
                        addMaterielInquiry(this.dialog.formItem).then(response => {                           
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
    //删除
    doDelete(row){        
        let id=row.id;     
           delMaterielInquiry(id).then(response => {                                   
            if(response.result) {
                this.$message({
                  message: response.msg,
                  type: 'success'
                });                              
                this.getData();
            }else {
                this.$message.error(res.msg);
            }
        }) 
   
    },
    //查看详情
    viewDilog(row) {
        this.dialog.formItem =Object.assign({},row);  
        this.isReadOnly = true;   
        this.isdisabled=true;    
        this.dialog.modal_dialog = true;
    },

    handleSelectionChange(val){    
        this.multipleSelection = val;
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
<style>
    .block {
    text-align: right;
    margin-top: 10px;
}
</style>