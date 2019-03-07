<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="24">
          <el-form :inline="true" :model="formQuery" class="demo-form-inline">
            <el-form-item label="关键字">
              <el-input v-model="formQuery.keyWord" placeholder="关键字"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary"  @click="handleSubmit('formQuery')">查找</el-button>
              <el-button type="primary" icon="el-icon-upload"  @click="toClientBomRate">客户Bom导入</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-card>

            <template>
              <el-table  border ref="main_table" :data="dataTable" tooltip-effect="dark" style="width: 100%" highlight-current-row @select="select" @selection-change="handleSelectionChange" @row-click="clickRow" v-loading="loading">
                <el-table-column type="selection" width="45" />
                <el-table-column prop="createdTime" label="询价时间" show-overflow-tooltip />
                <el-table-column prop="fileName" label="询价文件名称" show-overflow-tooltip />
                <el-table-column prop="modifiedName" label="操作人" show-overflow-tooltip />
                <el-table-column prop="remark" label="备注" show-overflow-tooltip >
                <template  slot-scope="scope">
                   <el-input type="" name="" v-model="scope.row.remark" style="background:none;border:none;width:100%;"  @blur="handler(scope.row)"></el-input>
                </template>
              </el-table-column>
                <el-table-column label="操作" width="140">
                  <template slot-scope="scope">
                    <el-button icon="el-icon-search" size="mini" @click="viewBox(scope.row,'view')" circle></el-button>
                    <el-button type="primary" size="mini" @click="viewBox(scope.row,'edite')" icon="el-icon-edit" circle></el-button>
                    <MessageBoxDelete
                      @callConfirm="doDelete(scope.row)"
                      title="提示"
                      contents="此操作将永久删除该行, 是否继续?"
                      confirmTitle="确认删除"
                    ></MessageBoxDelete> 
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
import { getBomList,delBom,modifyRemark } from '@/api/cost'
import MessageBoxDelete from "@/components/Dialog/MessageBox.vue";
export default {
  name:'clientBomList',
  components: {
    MessageBoxDelete
  },
  data() {
    return {
      loading: false,
      rolesDate: [],
      checkedRolesDate: [],
      value4: [],
      formQuery: {
        keyWord: ''
      },
      currentRow: [],
      dataTable: [{

      }],
      queryParams: {
        page: 1,
        rows: 10,
        pkParent: -1
      },
      pageSizesList: [10, 20, 30, 40, 50, 100],
      totalCount: 0 //数据的总条数,
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
      this.loading = true
      let suppGrade = 2;
      getBomList(this.formQuery.keyWord, this.queryParams.rows, this.queryParams.page).then(response => {
        this.loading = false
        if (!response.result) {
          this.$Message.error(response.msg);
          return
        }
        this.dataTable = response.data.rows;
        this.totalCount = response.data.total;
      })
    },
    /*删除*/
    doDelete(row) {
      let id = row.fileId;
      delBom(id).then(response => {
        if (response.result) {
          this.$message({
            message: response.msg,
            type: 'success'
          });
          this.getData();
        } else {
          this.$message.error(res.msg);
        }
      })

    },
    /*编辑*/
    viewBox(row, type) {
      //console.log(row);
      this.$router.push({ path: 'clientBomRate', query: { fileId: row.fileId,type:type } });
    },
    toClientBomRate() {
      this.$router.push({path:'clientBomRate',query:{}});
    },
    handleSelectionChange(val) {
      this.currentRow = val
      console.log(this.currentRow)
    },
    select(selection, row) {
      if (selection.length > 1) {
        selection.shift()
      }
    },
    clickRow(row,event,column){
        if(event.target.nodeName!="INPUT"  && column.label != '操作'){
          this.$router.push({ path: 'clientBomRate', query: { fileId: row.fileId,type:'edite' } });
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
    },
    //修改备注
    handler(row){      
      
       modifyRemark(row.id,row.remark).then(response => { 
        if(response.result) {
                this.$message({
                  message: response.msg,
                  type: 'success'
                });                              
                this.getData();
            }else {
                this.$message.error(res.msg);
            }                      
           /* if(response.result) {                          
             this.$confirm('备注修改成功', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              dangerouslyUseHTMLString: true,
              type: 'warning'
            }).then(() => {
              this.$message({
                type: 'success',
                message: response.msg
              });
            }).catch(() => {
              this.$error({
                type: 'info',
                message: response.msg
              });          
            });                          
       
         }else {
            this.$message.error(res.msg);
        }*/
    })
    }
  }
}

</script>
<style>
.block {
  text-align: right;
  margin-top: 10px;
}
.el-table th, .el-table tr{ cursor: pointer; }

</style>
