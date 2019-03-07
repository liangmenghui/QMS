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
            ><a @click="changeStatus(index)"><span>{{item.sort}}({{item.num}})</span></a></li>
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
          type="index"    width="50"       
        >
        </el-table-column>
        <el-table-column
          prop="qtCode"
          label="报价单编号"
          show-overflow-tooltip
        width="160"
        >
        </el-table-column>
        <el-table-column
          prop="suppAliaName"
          label="供应商简称"
          show-overflow-tooltip
          width="120"
        >
        </el-table-column>
        <el-table-column
          prop="qtTitle"
          label="报价单标题"          
          show-overflow-tooltip
        >
        </el-table-column>
         <el-table-column
          prop="eqTitle"
          label="询价单标题"          
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
        <template scope="scope">
            <el-tag :type="statusFilter(scope.row.qtStatus)">{{ formatStata(scope.row.qtStatus)}}</el-tag>
          </template>
        </el-table-column>

        <el-table-column
          label="操作"
          width="120"
        >
          <template scope="scope">
           <el-button type="primary" plain size="mini"  @click="showEditDialog('quoAdd',scope.row)">填写报价</el-button>           
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
  name: "quoAddList",
  components: {
  
  },
  data() {
    return {
      loading:true,
      current: 0,
      open: false,
      items: [       
        { sort: "未报价",num:0},         
      ],
      quoteTable: [{}],
      queryParams: {
        page: 1,
        rows: 15,
        pkParent: -1,
        eqStatus:0,
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
            this.$router.push({path: 'quoAdd',query:{id:row.id}});
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
        this.queryParams.eqStatus,this.queryParams.keyword ,this.queryParams.startDate,this.queryParams.endDate ,
        this.queryParams.rows,
        this.queryParams.page
      ).then(response => {
          this.loading = false
        if (response.result) {          
          this.quoteTable = response.data.rows;
          this.totalCount = response.data.total;
          this.items[0].num = response.data.total
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    changeStatus(index){
        this.queryParams.eqStatus = index
        getData()
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
/*搜索框部分*/
.retrieval {
  margin: 0;
  padding: 0;
  clear: both;
  overflow: hidden;
}
.title {
  margin: 0 0 10px 0;
  padding: 3px 10px 0 0;
  background: #ededed;
  clear: both;
  overflow: hidden;
  height: 30px;
  line-height: 30px;
}
.title h3 {
  float: left;
  margin: 0;
  padding: 0 0 0 10px;
  font: 12px/24px "宋体", Arial, Tahoma, sans-serif;
  color: #fff;
  background: url(/src/assets/img/title-bg.gif) no-repeat left top scroll;
  height: 24px;
  width: 80px;
}

.title .filter {
  float: left;
  display: inline;
  margin: 0 0 0 15px;
  padding: 2px 0;
}
.title .filter ul {
  float: left;
  display: inline;
  margin: 0;
  padding: 0;
}
.title .filter ul li {
  float: left;
  display: inline;
  margin: 0 5px 0 0;
  padding: 0;
  height: 20px;
  vertical-align: middle;
  color: #333;
  font: 12px/20px Arial, Helvetica, sans-serif;
}
.title .filter ul li a {
  color: #333;
  text-decoration: none;
  padding: 0 10px 0 0;
  float: left;
}
.title .filter ul li.current a {
  float: left;
  padding: 0 10px 0 0;
  background: #050f36;
  text-decoration: none;
  color: #fff;
  text-decoration: none;
}
.title .filter ul li.current a span {
  float: left;
  background: #050f36;
  text-decoration: none;
  color: #fff;
  text-decoration: none;
}

.title .filter ul li a span {
  float: left;
  display: inline;
  padding: 0 0 0 10px;
  margin: 0;
  color: #333;
  font: 12px/20px Arial, Helvetica, sans-serif;
}

.title span.more {
  float: right;
  margin: 3px 0 0 0;
  padding: 0;
  background: url(/src/assets/img/retrieve-btn.gif) no-repeat left top scroll;
  width: 61px;
  height: 18px;
  cursor: pointer;
}
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