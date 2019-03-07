<template>
 <!--  搜索框 -->
 <div>
  <div class="retrieval">
      <div class="title">
        <h3>设置状态</h3>
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
            <el-col :span="6">
                <el-form-item
                label="关键字"
                prop="eqTitle"
              >
                <el-input v-model="queryParams.keyword"></el-input>
              </el-form-item>           
            </el-col>
            <el-col :span="4">              
              <!-- <el-button type="primary" @click="getData()">查找</el-button> -->
              <el-button type="info"  @click="resetFrom()">重置</el-button>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </div>      
    <div>
       <tree-table :data="data" :columns="columns" v-loading="loading" border @clickSearch="clickgetChildren"/>        
    </div>
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
import { getSmtPoint,updateStmPoints } from '@/api/smtpoint'
import treeTable from '@/components/TreeTable'
export default {
  name: "setSmtPoint",
  components: {
  treeTable
  },
  data() {
    return {
      queryParams:{        
        keyword:'',
        setStatus:0,
        page: 1,
        rows: 15,    
      },
      pageSizesList: [15, 20, 30, 40, 50, 100],
      totalCount: 0, //数据的总条数,
      loading:false,
      open: true,
      current:0,
      columns: [
        {
          text: '物料名称',
          value: 'sName',
          width:440
        },
        {
          text: '物料编码',
          value: 'sCode',
          width: 230        
        },        
        {
          text: '设置SMT点数',
          value: 'sPoints'
        },
       /*  {
          text: '是否单录入',
          value: 'isSpecial'
        } */   
      ],
      data: [],
      items: [
        { sort: "全部" }, 
        { sort: "未设置"},
        { sort: "已设置"},         
      ],     
      }   
  },
  created(){
    this.getdata();
  },
  methods:{
    //切换设置状态查询
    changeStatus(index){        
        this.queryParams.setStatus = index
        this.getdata()
    },
    //单击添加选中项样式
    addClass(index) {
      this.current = index;
    },
    //重置按钮
     resetFrom(){   
        this.queryParams.keyword = ''
        this.queryParams.startDate = ''
        this.queryParams.endDate = ''
    },
    //获取类别  
    getdata(){           
       getSmtPoint(0,1,
        this.queryParams.setStatus,
        this.queryParams.keyword, 
        this.queryParams.rows,
        this.queryParams.page).then(response=>{       
          if(!response.result){
            this.$message.error(response.msg);
            return
          }
          this.data = response.data.rows;
          this.totalCount = response.data.total;  

       })
    },
    //点击获取物料
    clickgetChildren(record){      
       this.loading=true; 
        const sLevel=parseInt(record.sLevel)+1;
        getSmtPoint(
        record.parentId,
        sLevel,this.queryParams.setStatus,
        this.queryParams.keyword, 
        this.queryParams.rows,
        this.queryParams.page).then(response=>{         
          if(!response.result){
            this.loading=false
            this.$message.error(response.msg);
            return
          }  
          record.children = response.data.rows;
          this.loading=false         
       })
    },
    changePage(page){
      this.queryParams.page=page;
      this.getData();
    },
    SizeChange(size){
      this.queryParams.rows=size;   
      this.getData();
    }
  
  }
};
</script>
<style>
.searchbox{
  margin:10px 0px 0px 10px; 
}
.block{
    text-align: right;
    margin-top:10px;
}

</style>