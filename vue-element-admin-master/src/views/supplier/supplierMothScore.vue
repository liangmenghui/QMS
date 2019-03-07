<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="24">
          <el-form :inline="true" :model="formQuery" class="demo-form-inline">
            <el-form-item label="关键字">
              <el-input v-model="formQuery.keyword" placeholder="关键字"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary"  @click="handleSubmit('formQuery')">查找</el-button>
              <el-button type="primary"  @click="scoreEntry()">供应商评分录入</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-card>

        <template>
          <el-table border ref="main_table"   @row-click="rowClick" :data="dataTable" tooltip-effect="dark" style="width: 100%" highlight-current-row >
            <el-table-column
                type="index"                
                width="40"
                fixed
                >
             </el-table-column>
            <el-table-column prop="suppK3Code" label="K3编号" show-overflow-tooltip />
            <el-table-column prop="suppChineseName" label="供应商名称" show-overflow-tooltip width="150" />
            <el-table-column prop="batchValue" label="进料抽检验" align="center">
            <el-table-column prop="batchScore" label="得分">
            </el-table-column>
            <el-table-column prop="batchReason" label="扣分原因">
            </el-table-column>
            </el-table-column>
            <el-table-column prop="processLevel" label="制程品质" align="center">
                 <el-table-column prop="processScore" label="得分">
                 </el-table-column>
                 <el-table-column prop="processReason" label="扣分原因">
                 </el-table-column>
            </el-table-column>
            <el-table-column prop="replyValue" label="异常回复时效" align="center">
                 <el-table-column prop="replyScore" label="得分">
                 </el-table-column>               
            </el-table-column>
             <el-table-column prop="rohsValue" label="是否有ROHS标识" align="center">
                 <el-table-column prop="rohsScore" label="得分">
                 </el-table-column>               
            </el-table-column>
            <el-table-column prop="feedBackValue" label="是否有顾客中断干扰" align="center">
                 <el-table-column prop="feedBackScore" label="得分">
                 </el-table-column>               
            </el-table-column>
            <el-table-column prop="freightValue" label="超额运费" align="center">
                 <el-table-column prop="freightValue" label="得分">
                 </el-table-column>               
            </el-table-column>
              <el-table-column prop="freightValue" label="超额运费" align="center">
                 <el-table-column prop="freightScore" label="得分">
                 </el-table-column>               
            </el-table-column>
             <el-table-column prop="deliveryValue" label="交期" align="center">
                 <el-table-column prop="deliveryScore" label="得分">
                 </el-table-column>               
            </el-table-column>
             <el-table-column prop="priceValue" label="降价比率" align="center">
                 <el-table-column prop="priceScore" label="得分">
                 </el-table-column>               
            </el-table-column>

            <el-table-column prop="suppLevel" label="供应商等级" show-overflow-tooltip />
            <el-table-column prop="suppScore" label="总分" show-overflow-tooltip fixed="right" width="50">
                <template scope="scope">
                    <span style="color:red;">{{scope.row.suppScore}}</span>
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
import { getScoreList } from '@/api/supplierScore'
import MessageBoxDelete from "@/components/Dialog/MessageBox.vue";
export default {
  components: {
    MessageBoxDelete
  },
  data() {
    return {
      dialogVisible: false,     
      formQuery: {
        keyword: ''
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
      getScoreList(this.formQuery.keyword, this.queryParams.rows, this.queryParams.page).then(response => {
        if (!response.result) {
          this.$Message.error(response.msg);
          return
        }
        this.dataTable = response.data.rows;
        this.totalCount = response.data.total;
      })
    },
    //点击行，查看子表
    rowClick(row, event, column){
      
    },
    scoreEntry(){
         this.$router.push({path:'supplierScoreEntry'})
    },
    changePage(page) {
      this.queryParams.page = page;
      this.getData();
    },
    SizeChange(size) {
      this.queryParams.rows = size;
      this.getData();
    },

  }
}

</script>
<style>
.block {
  text-align: right;
  margin-top: 10px;
}

.el-table thead｛color:#555!important｝

</style>
