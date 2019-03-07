<template>
  <el-row>
    <el-col :span="24">
      <div>
        <el-card>
          <el-row>
            <el-col :span="16">
              <el-form :inline="true" :model="formQuery" class="demo-form-inline">
                <el-form-item label="物料号">
                  <el-input v-model="formQuery.mateK3Code" placeholder="K3物料号"></el-input>
                </el-form-item>
                <el-form-item label="名称">
                  <el-input v-model="formQuery.mateName" placeholder="物料名称"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" size="mini" @click="handleSubmit('formQuery')">查找</el-button>
                </el-form-item>
              </el-form>
            </el-col>
            <el-col :span="6">
              <el-button-group size="samll">
                <el-button type="primary" icon="el-icon-plus" @click="showAddDialog()">新增类别</el-button>
                <el-button type="primary" @click="importExecl">导入EXCEL</el-button>
              </el-button-group>
            </el-col>
          </el-row>
        </el-card>
        <template>
          <el-table ref="materielTable" :data="materielTable" tooltip-effect="dark" style="width: 100%" highlight-current-row @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="45">
            </el-table-column>
            <el-table-column prop="mateK3Code" label="代码" >
            </el-table-column>
            <el-table-column prop="categoryName" label="名称">
            </el-table-column>       
            <el-table-column label="操作" width="120">
              <template slot-scope="scope">
                <a class="operatIcon colorblue" @click="viewMateriel(scope.row)"><i class="el-icon-search"></i></a>
                <a class="operatIcon colorgreen" @click="showEditDialog(scope.row)"><i class="el-icon-edit"></i></a>
                <!-- <a class="operatIcon colorRed"  @click="open(scope.row)"><i class="el-icon-delete"></i> </a> -->
                 <MessageBox @callConfirm="doDelete(scope.row)" title="提示" contents="此操作将永久删除该行, 是否继续?" confirmTitle="确认删除"></MessageBox>
              </template>
            </el-table-column>
          </el-table>
        </template>
        <div class="block">
          <el-pagination class="pull-right clearfix" @current-change="changePage" @size-change="SizeChange" :current="1" :current-page.sync="queryParams.page" :page-sizes="pageSizesList" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper" :page-size-opts="pageSizesList" :total="totalCount">
          </el-pagination>
        </div>
        <!--  新增物料 -->
        <el-dialog :title="dialogTitle" :visible.sync="dialog.dialogVisible" width="35%">
          <el-form :model="materielForm" ref="dialog.formItem" :rules="dialog.ruleForm" style="width:80%">
            <el-form-item label="物料ID" :label-width="formLabelWidth" style="display: none" prop="categoryId">
              <el-input v-model="materielForm.categoryId" v-bind:readonly="isReadOnly"></el-input>
            </el-form-item>
            <el-form-item label="物料名称" :label-width="formLabelWidth"  prop="mateName">
              <el-input v-model="materielForm.mateName" v-bind:readonly="isReadOnly"></el-input>
            </el-form-item>           
            <el-form-item label="备注" :label-width="formLabelWidth" prop="remark">
              <el-input v-model="materielForm.remark" v-bind:readonly="isReadOnly"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="OK">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </el-col>
  </el-row>
</template>
<script>
import { addMaterielInfo,getMaterielList,delMaterielInfo,editMateriel} from '@/api/materiel'
import MessageBox from '../../components/Dialog/MessageBox.vue'
export default {
  name: 'materialCategory',
  components:{
    MessageBox
  },
  data() {
    return {
    isReadOnly:true,
    isdisabled:true,
      dialog: {
        loading: false,
        dialogVisible: false,        
        ruleForm: {
          mateName: [
            { required: true, message: '请填物料名称', trigger: 'blur' }
          ],
          categoryName: [
            { required: true, message: '请选择物料类别', trigger: 'blur' }
          ],
          mateModel: [{ required: true,message: '请填写规格', trigger: 'blur' }]
        }
      },
      materielForm: {},
      rolesDate: [],
      multipleSelection: [],
      dialogTitle: '新增物料',
      categoryOptions: {},
      formLabelWidth: '100px',
      formQuery: {
        mateModel: '',
        mateName: ''
      },
     suppliers: [],
      currentRow: [],
      materielTable: [{

      }],
      tree: {
        data: [{ expand: true, label: '电阻类', children: [] }]
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
  created() {
    this.getData();
  },
  methods: {
    handleSubmit(name) {
      this.getData();
    },
    getData() {
      let suppGrade = 1;
      getMaterielList(this.formQuery.mateK3Code, this.formQuery.mateName, this.queryParams.rows, this.queryParams.page).then(response => {
        if (!response.data) {
          reject('error')
        }
        this.materielTable = response.data.rows;
        this.totalCount = response.data.total;
      })
    },
    showAddDialog() {
      this.materielForm = { id: '' };
      this.isdisabled=false;
      this.isReadOnly=false;
      this.dialog.dialogVisible = true;
    },
    /*新增&编辑*/
    OK() {    
      if (typeof(this.materielForm.id) != undefined && typeof(this.materielForm.id) == "number") {

        this.$refs['dialog.formItem'].validate((valid) => {
          if (valid) {
            editMateriel(this.materielForm).then(response => {

              if (response.result) {
                this.dialog.dialogVisible = false
                this.getData();
              } else {
                this.$Message.error(res.msg);
              }
            })
          }
        });
      } else {
        this.$refs['dialog.formItem'].validate((valid) => {
          if (valid) {
            addMaterielInfo(this.materielForm).then(response => {

              if (response.result) {
                this.dialog.dialogVisible = false;
                this.getData();
              } else {
                this.$message.error(res.msg);
              }
            })
          }
        });
      }
    },
    //删除  
    doDelete(row) {       
      let id = row.id;
      delMaterielInfo(id).then(response => {
        if (response.result) {
          this.$message({
            message: response.msg,
            type: 'success'
          });
          this.getData();
        } else {
          this.$Message.error(res.msg);
        }
      })

    },
    //编辑
    showEditDialog(row) {      
     this.materielForm =Object.assign({},row);
     this.dialogTitle="编辑物料";
     this.dialog.dialogVisible=true;
     this.isReadOnly = false;
     this.isdisabled=false;

     
    },
    //查看物料
    viewMateriel(row) {       
      /*  let r = params.row;*/
        this.materielForm = {
            categoryId:row.categoryId,
            mateName:row.mateName,
            categoryName:row.categoryName,                
            mateModel:row.mateModel           
        };
        this.isReadOnly = true;   
        this.isdisabled=true;    
        this.dialog.dialogVisible = true;
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    importExecl() {
      if (!this.currentRow) {
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
  margin-top: 10px;
}

.tree {
  background: #f7ffff;
  height: 700px;
  width: 100%;
}

.tree h5 {
  margin: 0;
  padding: 0 0 0 18px;
  background-color: #d8f0f0;
  font: 12px/34px "宋体", Arial, Tahoma, sans-serif;
  color: #001f3a;
  height: 34px;
  font-weight: bold;
}

</style>
