<template>
  <div>
    <div class="retrieval">
      <div class="title">
        <h3>报价状态</h3>
        <div class="filter">
          <ul>
            <li
              v-for="(item,index) in items"
              :key="index"
              @click="addClass(index)"
              v-bind:class="{ current:index==current}"
            ><a @click="changeStatus(index)"><span>{{item.sort}}</span></a></li>
          </ul>
        </div>
        <span
          class="more"
          @click="open=!open"
        ></span>
      </div>
      <div
        class="searchDiv"
        v-show="open"
      >
        <el-form
          :model="queryParams"
          ref="form"
          label-width="70px"
          class="formStly"
          size="small"
          :inline="true"
        >
          <el-row
            type="flex"
            class="row-bg"
          >
            <el-col :span="24">
                <el-form-item
                label="关键字"
                prop="eqTitle"
              >
                <el-input v-model="queryParams.keyword"></el-input>
              </el-form-item>
              <el-form-item
                label="报价日期"
                prop="qtStartDate"
              >
                <el-date-picker
                  style="width:183px"
                  v-model="queryParams.startDate"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期"
                >
                </el-date-picker>
              </el-form-item>
              <el-form-item
                label="到"
                prop="qtDeadLine"
              >
                <el-date-picker
                  style="width:183px"
                  v-model="queryParams.endDate"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <!-- <span class="rebtn01"></span>
              <span class="rebtn02"></span> -->
              <el-button type="primary"  @click="getData">查找</el-button>
              <el-button type="info"  @click="resetFrom">重置</el-button>
            </el-col>

          </el-row>
        </el-form>
      </div>
    </div>

    <template>
      <el-table
        @row-click="clickRow"
        ref="multipleTable"
        border
        :data="quoteTable"
        tooltip-effect="dark"
        style="width: 100%" 
        highlight-current-row v-loading="loading" 
      >
        <el-table-column
          label="序号"
          type="index"  
          width="50"  
          fixed     
        >
        </el-table-column>
        <el-table-column
          prop="qtCode"
          label="报价单编号"
          show-overflow-tooltip
          width="200"          
        >
        </el-table-column>
        <el-table-column
          prop="suppAliaName"
          label="供应商简称"
          show-overflow-tooltip
           width="100"
        >
        </el-table-column>
        <el-table-column
          prop="qtTitle"
          label="报价单标题"
          width="140"
          show-overflow-tooltip          
        >
        </el-table-column>
        <el-table-column
          prop="eqTitle"
          label="询价单标题" 
          width="120"         
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="qtStartDate"
          label="报价日期"         
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="qtDeadLine"
          label="截止日期"
          show-overflow-tooltip
        >          
        </el-table-column>
        <el-table-column
          prop="eqStartDate"
          label="询价日期"
          show-overflow-tooltip
        >
        </el-table-column>        
         <el-table-column
          prop="qtTotalPrice"
          label="报价总额"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="suppContactName"
          label="报价人"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="qtStatus"
          label="报价状态"
          show-overflow-tooltip
        >
        <template slot-scope="scope">
            <el-tag :type="statusFilter(scope.row.qtStatus)">{{ formatStata(scope.row.qtStatus)}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作" fixed="right"> 
          <template slot-scope="scope">
            <a
              class="operatIcon colorblue"
              @click="view(scope.row)" 
            ><i class="el-icon-search"></i></a>
            <a
              class="operatIcon colorgreen"
              @click="showEditDialog('quoAdd',scope.row)"
            ><i class="el-icon-edit"></i></a>          
          </template>
        </el-table-column>

      </el-table>
    </template>
    <div class="block">
      <el-pagination
        class="pull-right clearfix"
        @current-change="changePage"
        @size-change="SizeChange"
        :current="1"
        :current-page.sync="queryParams.page"
        :page-sizes="pageSizesList"
        :page-size="queryParams.rows"
        layout="total, sizes, prev, pager, next, jumper"
        :page-size-opts="pageSizesList"
        :total="totalCount"
      >
      </el-pagination>
    </div>
  </div>

</template>
<script>
import {
  addQuote,
  getQuoteList,
  delQuote,
  editQuote
} from "@/api/quote";

export default {
  name: "quoList",
  components: {
  
  },
  data() {
    return {
      loading:true,
      current: 0,
      open: false,
      items: [
        { sort: "全部" }, 
        { sort: "未报价"},
        { sort: "已报价"},         
      ],
      quoteTable: [{}],
      queryParams: {
        page: 1,
        rows: 15,
        pkParent: -1,
        qtStatus:0,
        keyword:'',
        startDate:'',
        endDate:''
      },
      pageSizesList: [15, 20, 30, 40, 50, 100],
      totalCount: 0, //数据的总条数,
      // el-tag类型转换
      statusFilter(status) {
        const statusMap = {
          1: "danger",
          2: "info",
          3: "success"
        };
        return statusMap[status];
      },
      // 状态显示转换
      formatStata(status) {
        const statusMap = {
          1: "未报价",
          2: "已报价",       
        };
        return statusMap[status];
      }
    };
  },
  created() {
    this.getData();
  },
  methods: {
    clickRow(row, event, column) {
        if(column.label != '操作'){
            this.view(row)
        }        
    },
    //单击添加选中项样式
    addClass: function(index) {
      this.current = index;
    },
    getData() {
        this.loading = true
      //获取询价列表
      getQuoteList(
        this.queryParams.qtStatus,this.queryParams.keyword ,this.queryParams.startDate,this.queryParams.endDate ,
        this.queryParams.rows,
        this.queryParams.page
      ).then(response => {
          this.loading = false
        if (response.result) {          
          this.quoteTable = response.data.rows;
          this.totalCount = response.data.total;      

        } else {
          this.$message.error(res.msg);
        }
      });
    },
    changeStatus(index){
        this.queryParams.qtStatus = index
        this.getData()
    },
    resetFrom(){
        //重置
        this.queryParams.keyword = ''
        this.queryParams.startDate = ''
        this.queryParams.endDate = ''
    },
    //编辑显示
    showEditDialog(url,row) {       
        this.$router.push({path: url,query:{id:row.id}});
    },
    //删除
    doDelete(row) {
      let id = row.id;
      delEnquiry(id).then(response => {
        if (response.result) {
          this.$message({
            message: response.msg,
            type: "success"
          });
          this.getData();
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    //查看详情
    view(row) {
      this.$router.push({path:'quoDetail',query:{id:row.id}});
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
};
</script>
<style>

.block {
  text-align: right;
  margin-top: 10px;
}
.detail-table {
  margin: 7px 0 0 0;
  padding: 0 0 10px 0;
  clear: both;
  overflow: hidden;
}
.s-ul {
  margin: 0 0 10px 0;
  padding: 0;
  clear: both;
  overflow: hidden;
  width: 100%;
}

.formStly .el-input--small .el-input__inner {
  height: 27px;
  line-height: 27px;
}
</style>