<template>
    <div>
        <el-card>
            <el-row>
                <el-col :span="14">
                    <el-form :inline="true" :model="formQuery" class="demo-form-inline">
                      <el-form-item label="供应商编号">
                        <el-input v-model="formQuery.suppCode" placeholder="供应商编号"></el-input>
                      </el-form-item>
                      <el-form-item label="供应商名称">
                        <el-input v-model="formQuery.suppChineseName" placeholder="供应商名称"></el-input>
                      </el-form-item>
                      <el-form-item>
                         <el-button type="primary" size="mini" @click="getData">查找</el-button>
                      </el-form-item>
                  </el-form>
                </el-col>
                <el-col :span="6">
                     <el-button-group size="samll">
                     <el-button type="primary" icon="el-icon-plus" @click="Addsupplier('addsupplier')">新增审核</el-button>
                     <el-button type="primary" @click="importExecl">导入EXCEL</el-button>                    
                     </el-button-group>
                </el-col>                
            </el-row>           
        </el-card>
        <template>
              <el-table
                ref="multipleTable"
                :data="supplierTable"
                tooltip-effect="dark"
                style="width: 100%"
                 highlight-current-row              
                @selection-change="handleSelectionChange">
                <el-table-column
                  type="selection"
                  width="55">
                </el-table-column>
                 <el-table-column
                  prop="suppCode"
                  label="供应商编号"
                  width="120">
                </el-table-column>
                <el-table-column
                  prop="suppAliaName"
                  label="供应商简称"
                  width="120">
                </el-table-column>
                <el-table-column
                  prop="suppChineseName"
                  label="审核年份"
                  show-overflow-tooltip>
                </el-table-column>             
                 <el-table-column
                  prop="suppContactName"
                  label="审核人"
                  show-overflow-tooltip>
                </el-table-column>
                <el-table-column
                  prop="suppMobile"
                  label="电话"
                  show-overflow-tooltip>
                </el-table-column>
                
                <el-table-column
                  prop="suppGrade"
                  label="审核结果"
                  show-overflow-tooltip>
                  <template scope="scope">       
                    <span v-bind:class="{orange:scope.row.suppGrade==1 ,green:scope.row.suppGrade==2, gray:scope.row.suppGrade==3}">{{$t('supplier.Status['+scope.row.suppGrade+']')}}</span>
                </template>
                </el-table-column>
                <el-table-column label="操作" width="120">
                <template slot-scope="scope">
                <!--     <a class="operatIcon colorblue" @click="viewSupplier('supplierDetails',scope.row)"><i class="el-icon-search"></i></a>  -->    
                      <el-button type="primary" size="mini" @click="showEditDialog(scope.row)" icon="el-icon-edit" circle></el-button>                                 
                <MessageBox @callConfirm="doDelete(scope.row)" title="提示" contents="此操作将永久删除该行, 是否继续?" confirmTitle="确认删除"></MessageBox>                        
                </template>
                 </el-table-column>           

              </el-table>             
        </template>
         <div class="block">
            <el-pagination class="pull-right clearfix" @current-change="changePage" @size-change="SizeChange" :current="1" :current-page.sync="queryParams.page" :page-sizes="pageSizesList" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper" :page-size-opts="pageSizesList" :total="totalCount">
            </el-pagination>
        </div>     
        <!--end-->
    </div>
 
</template>
<script>
import {getSupplierList,delSupplierInfo,uplaodFile,updateStatus} from '@/api/supplier'
import MessageBox from '../../components/Dialog/MessageBox.vue'
  export default {
    name:'supplierApprove',
    components:{
    MessageBox
  },
    data() {
      return {   
        dialogVisible: false, 
        rolesDate:[],
        multipleSelection: [],
        checkedRolesDate:[],
        value4: [],    
        formQuery: {
            suppCode: '',
            suppChineseName: ''
            },
        currentRow:[],
        supplierTable:[{

        }],          
        queryParams: {
            page: 1,
            rows: 10,
            pkParent: -1
            },
        pageSizesList: [10, 20, 30, 40, 50, 100],
        totalCount: 0, //数据的总条数,
      }
    }, 
    created(){
        this.getData();   

    },
    watch:{
      //路由发生变化时，运行getData函数
    '$route':"getData"
    },
    methods: {      
    handleSubmit(name){
        this.getData();
    },
    getData(){      
      let suppGrade=1;      
        getSupplierList(this.formQuery.suppCode,this.formQuery.suppChineseName,suppGrade,this.queryParams.rows,this.queryParams.page).then(response => {
                if (!response.data) { 
                reject('error')
          }        
        this.supplierTable = response.data.rows;
        this.totalCount =response.data.total;
        })
    },
    Addsupplier(url,row){
         this.$router.push({path: url});
    },
//删除
    doDelete(row){        
        let id=row.id;     
           delSupplierInfo(id).then(response => {                                            
            if(response.result) {
                this.$message({
                  message: response.msg,
                  type: 'success'
                });                              
                this.getData();
            }else {
                this.$Message.error(res.msg);
            }
        }) 
   
    },

//获取被选中的行的数量
    getSelectedCount(){
        //alert( this.multipleSelection.length)
        return this.multipleSelection.length
    },
//获取被选中的行的数据的id，放到一个数组中   
    getSelectedIds(){
        let ids=[]
        this.multipleSelection.map((item)=> {
            ids.push(item.id)
        })
        return ids;
    },
      
//编辑
    showEditDialog(url,row) {      
       this.$store.commit("updateSupplierDataStates",row);
       this.$router.push({path: url,query:{id:row.id}}); 
    },
    //查看供应商详情
    viewSupplier(url,row) {
       this.$store.commit("updateSupplierDataStates",row);
       this.$router.push({path: url,query:{id:row.id}});
    },

    handleSelectionChange(val){    
        this.multipleSelection = val;
    },

    importExecl(){        
        if(!this.currentRow){               
            this.$message.error('请选择要分配角色的用户');
            return
        }
        
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
<style>
    .block {
    text-align: right;
   
}


</style>