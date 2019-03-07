<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="24">
          <el-form :inline="true" :model="formQuery" class="demo-form-inline">
            <el-form-item label="关键字">
              <el-input v-model="formQuery.keyword" placeholder="关键字"></el-input>
            </el-form-item> 
            <el-form-item label="产品" prop="userStatus">
              <el-select v-model="formQuery.userStatus" placeholder="主营产品类别" style="width:280px">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="handleSubmit('formQuery')">查找</el-button>
              <el-button type="primary" icon="el-icon-search"  @click="doMatchK3">匹配K3数据</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-card>
    <el-tabs type="border-card">
          <el-tab-pane label="Srm供应商信息">
            <template>
              <el-table border v-loading="loading" ref="main_table" :data="supplierTable" tooltip-effect="dark" style="width: 100%" highlight-current-row @select="select" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="45">
                </el-table-column>
                <el-table-column prop="suppCode" label="供应商编号" width="120" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="suppAliaName" label="供应商简称" width="120" show-overflow-tooltip>                
                </el-table-column> 
                 <el-table-column prop="suppK3Code" label="供应商K3编号" width="120" show-overflow-tooltip>
                </el-table-column>           
                <el-table-column prop="suppChineseName" label="中文名称" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="suppContactName" label="联系人" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="suppMobile" label="电话" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="createdTime" label="注册时间" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="suppGrade" label="状态" show-overflow-tooltip>
                  <template scope="scope">
                    <span v-bind:class="{orange:scope.row.suppGrade==1 ,green:scope.row.suppGrade==2, gray:scope.row.suppGrade==3}">{{$t('supplier.Status['+scope.row.suppGrade+']')}}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作" width="140">
                  <!--<template slot-scope="scope">
                     <a class="operatIcon colorblue" @click="viewSupplier('addsupplier',scope.row)"><i class="el-icon-search"></i></a>
                    <a class="operatIcon colorgreen" @click="showEditDialog('addsupplier',scope.row)"><i class="el-icon-edit"></i></a>
                    <a class="operatIcon colorRed"  @click="doDelete(scope.row)"><i class="el-icon-delete"></i> </a>
                  </template> -->
                  <template slot-scope="scope">
                    <el-button icon="el-icon-search" size="mini" @click="viewSupplier('addsupplier',scope.row)" circle></el-button>
                    <el-button type="primary" size="mini" @click="showEditDialog('addsupplier',scope.row)" icon="el-icon-edit" circle></el-button>
                    <MessageBoxDel
                      @callConfirm="doDelete(scope.row)"
                      title="提示"
                      contents="此操作将永久删除该行, 是否继续?"
                      confirmTitle="确认删除"
                    ></MessageBoxDel> 
                  </template>
                </el-table-column>
              </el-table>
            </template>
            <div class="block">
              <el-pagination class="pull-right clearfix" @current-change="changePage" @size-change="SizeChange" :current="1" :current-page.sync="queryParams.page" :page-sizes="pageSizesList" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper" :page-size-opts="pageSizesList" :total="totalCount">
              </el-pagination>
            </div>
          </el-tab-pane>
          <el-tab-pane label="K3供应商信息">
            <template>
              <el-table border ref="main_table" :data="supplierK3Table" tooltip-effect="dark" style="width: 100%" highlight-current-row @select="select" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="45">
                </el-table-column>
                <el-table-column prop="fNumber" label="供应商编号" width="120" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="fShortName" label="供应商简称" width="120" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="fName" label="中文名称" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="fContact" label="联系人" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="fPhone" label="电话" show-overflow-tooltip>
                </el-table-column>
                <el-table-column prop="suppGrade" label="状态" show-overflow-tooltip>
                  <template scope="scope">
                    <span v-bind:class="{orange:scope.row.suppGrade==1 ,green:scope.row.suppGrade==2, gray:scope.row.suppGrade==3}">{{$t('supplier.Status['+2+']')}}</span>
                  </template>
                </el-table-column>
                
              </el-table>
            </template>
            <div class="block">
              <el-pagination class="pull-right clearfix" @current-change="changePageK3" @size-change="SizeChangeK3" :current="1" :current-page.sync="queryK3Params.page" :page-sizes="pageSizesList" :page-size="queryK3Params.rows" layout="total, sizes, prev, pager, next, jumper" :page-size-opts="pageSizesList" :total="totalK3Count">
              </el-pagination>
            </div>
          </el-tab-pane>
    </el-tabs>
    
    
    <!--end-->
  </div>
</template>
<script>
import { getSupplierListAll, delSupplierInfo, uplaodFile,doMatchK3 } from '@/api/supplier'
import MessageBoxDel from "@/components/Dialog/MessageBox.vue";
export default {
  name: 'userManagement',
  components: {
    MessageBoxDel
  },
  data() {
    return {
      dialogVisible: false,
      rolesDate: [],
      checkedRolesDate: [],
      value4: [],
      formQuery: {
        keyword: ''
      },
      currentRow: [],
      supplierTable: [{

      }],
      queryParams: {
        page: 1,
        rows: 10,
        pkParent: -1
      },
      queryK3Params: {
        page: 1,
        rows: 10,
        pkParent: -1
      },
      pageSizesList: [10, 20, 30, 40, 50, 100],
      totalCount: 0, //数据的总条数,
      totalK3Count:0,
      supplierK3Table: [{

      }],
      loading:true
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
      let suppGrade = 2;
      getSupplierListAll(this.formQuery.keyword, suppGrade, this.queryParams.rows, this.queryParams.page, this.queryK3Params.rows, this.queryK3Params.page).then(response => {
        if (!response.result) {
          this.$Message.error(response.msg);
          return
        }
        this.supplierTable = response.data.listSrm.rows;
        this.totalCount = response.data.listSrm.total;
        this.supplierK3Table = response.data.listK3.rows;
        this.totalK3Count = response.data.listK3.total;

        this.loading = false
      })
    },
    /*删除*/
    doDelete(row) {
      let id = row.id;
      delSupplierInfo(id).then(response => {

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
    showEditDialog(url, row) {
      console.log(row);
      this.$store.commit("updateSupplierDataStates", row);
      this.$router.push({ path: url, query: { id: row.id,type:'edite' } });
    },
    /*查看供应商详情*/
    viewSupplier(url, row) {
      this.$store.commit("updateSupplierDataStates", row);
      this.$router.push({ path: url, query: { id: row.id,type:'view' } });
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
    cancel() {
      this.dialog.modal_dialog = false;
    },
    doMatchK3(){
      doMatchK3().then(response => {
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
    changePage(page) {
      this.queryParams.page = page;
      this.getData();
    },
    changePageK3(page) {
      this.queryK3Params.page = page;
      this.getData();
    },
    SizeChange(size) {
      this.queryParams.rows = size;
      this.getData();
    },
    SizeChangeK3(size) {
      this.queryK3Params.rows = size;
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
