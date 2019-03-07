<template>
    <div style="padding:15px 15px;">  
      <el-card class="box-card" shadow="never" v-loading="loading"> 
          <div slot="header" class="clearfix">
            <el-row type="flex" class="row-bg" >
                <el-col :span="20">
                     <span>报价单明细</span>
                </el-col>
                 <el-col :span="4">                      
                  <el-button style="float: right;" type="primary" size="mini" @click="backList">返回列表</el-button>
                  <el-button style="float: right; margin:0 5px" type="primary" size="mini" @click="showEditDialog('quoAdd',rowId)">编辑</el-button>          
                </el-col>
            </el-row>  
          </div>
             <el-tabs v-model="activeName">
              <el-tab-pane label="报价单" name="first">
          <!--   询价单信息 -->
                  <div class="detail-table">
                       <h3 class="conttitle">基本信息</h3>
                       <ul class="s-ul">
                          <li>
                             <span class="li-name01">报价标题:</span>
                             <span class="li-cont">{{quoteData.qtTitle}}</span>
                          </li>
                            <li>
                             <span class="li-name01">报价编号:</span>
                             <span class="li-cont">{{quoteData.qtCode}}</span>
                          </li>
                          <li>
                             <span class="li-name01">报价日期:</span>
                             <span class="li-cont">{{quoteData.qtStartDate}}</span>
                          </li>
                            <li>
                             <span class="li-name01">截止日期:</span>
                             <span class="li-cont">{{quoteData.qtDeadLine}}</span>
                          </li>
                          <li>
                             <span class="li-name01">交货期限:</span>
                             <span class="li-cont">{{quoteData.qtDelDeadline}}</span>
                          </li>
                          <li>
                             <span class="li-name01">付款方式:</span>
                             <span class="li-cont">{{quoteData.qtPayMethod}}</span>
                          </li>
                           <li>
                             <span class="li-name01">交货地址:</span>
                             <span class="li-cont">{{quoteData.qtDelLocation}}</span>
                          </li>
                           <li>
                             <span class="li-name01">验收方式:</span>
                             <span class="li-cont">{{quoteData.qtAcceptType}}</span>
                          </li>
                           <li>
                             <span class="li-name01">补充说明:</span>
                             <span class="li-cont">{{quoteData.qtDesc}}</span>
                          </li>
                           <li>
                             <span class="li-name01">报价总额:</span>
                             <span class="li-cont">{{quoteData.qtTotalPrice}}</span>
                          </li>
                       </ul>    
                  </div>
           <!--  报价明细 -->
                    <div class="detail-table">
                        <h3 class="conttitle">报价明细</h3> 
                          <el-table
                        ref="multipleTable"
                        border
                        :data="mateList"
                        tooltip-effect="dark"
                        style="width: 100%"
                         highlight-current-row              
                        >             
                        <!--  <el-table-column
                          prop="mateName"
                          label="序号"
                          show-overflow-tooltip
                          width="80">
                        </el-table-column> -->
                        <el-table-column
                          prop="mateName"
                          label="物料名称"
                          show-overflow-tooltip
                         >
                        </el-table-column>               
                          <el-table-column
                          prop="mateModel"
                          label="规格型号"
                          show-overflow-tooltip
                          width="140">
                        </el-table-column>
                          <el-table-column
                          prop="qtUnit"
                          label="单位"
                          show-overflow-tooltip
                          width="140">
                        </el-table-column>
                          <el-table-column
                          prop="qtMateNum"
                          label="数量"
                          show-overflow-tooltip
                          width="140">
                        </el-table-column>
                          <el-table-column
                          prop="qtUnitPrice"
                          label="单价"
                          show-overflow-tooltip
                          >
                          <template slot-scope="scope">
                            <el-tag type="danger" v-if="scope.row.qtUnitPrice==null">未报价</el-tag>
                            <el-tag v-else>{{scope.row.qtUnitPrice}}</el-tag>
                          </template>
                        </el-table-column>
                        <el-table-column
                          prop="qtTotalPrice"
                          label="总价"
                          show-overflow-tooltip
                          width="140">
                          <template slot-scope="scope">
                            <el-tag type="danger" v-if="scope.row.qtTotalPrice==null">未报价</el-tag>
                            <el-tag v-else>{{scope.row.qtTotalPrice}}</el-tag>
                          </template>
                        </el-table-column>
                        <el-table-column
                          prop="qtMateDesc"
                          label="报价备注"
                          show-overflow-tooltip
                          width="140">
                        </el-table-column>                    
                    </el-table>
                    </div>
                  <!--   报价人信息 -->
                  <div class="detail-table">
                       <h3 class="conttitle">报价人信息</h3>
                       <ul class="s-ul">
                          <li>
                             <span class="li-name01">公司名称:</span>
                             <span class="li-cont">{{quoteData.suppChineseName}}</span>
                          </li>
                            <li>
                             <span class="li-name01">联系人:</span>
                             <span class="li-cont">{{quoteData.suppContactName}}</span>
                          </li>
                          <li>
                             <span class="li-name01">电话:</span>
                             <span class="li-cont">{{quoteData.suppMobile}}</span>
                          </li>
                            <li>
                             <span class="li-name01">传真:</span>
                             <span class="li-cont">{{quoteData.suppFax}}</span>
                          </li>
                          <li>
                             <span class="li-name01">邮箱:</span>
                             <span class="li-cont">{{quoteData.suppEmail}}</span>
                          </li>
                          <li>
                             <span class="li-name01">报价方式:</span>
                             <span class="li-cont" v-if="quoteData.qtIsOnline==1">线上报价</span>
                             <span class="li-cont" v-else>线下报价</span>
                          </li>
                       </ul>    
                  </div>       
              </el-tab-pane>
              <el-tab-pane label="询价单信息" name="second">
                  <div class="detail-table">
                       <h3 class="conttitle">询价单信息</h3>
                       <ul class="s-ul">
                          <li>
                             <span class="li-name01">询价标题:</span>
                             <span class="li-cont">{{enquiryData.eqTitle}}</span>
                          </li>
                           <li>
                             <span class="li-name01">询价代码:</span>
                             <span class="li-cont">{{enquiryData.eqCode}}</span>
                          </li>
                         <li>
                             <span class="li-name01">询价日期:</span>
                             <span class="li-cont">{{enquiryData.eqStartDate}}</span>
                          </li>
                          <li>
                             <span class="li-name01">截止日期:</span>
                             <span class="li-cont">{{enquiryData.eqDeadLine}}</span>
                          </li>
                            <li>
                             <span class="li-name01">工程地点:</span>
                             <span class="li-cont">{{enquiryData.eqLocation}}</span>
                          </li>
                           <li>
                             <span class="li-name01">付款方式:</span>
                             <span class="li-cont">{{enquiryData.eqPayMethod}}</span>
                          </li>
                          <li>
                             <span class="li-name01">是否含税:</span>
                             <span class="li-cont" v-if="enquiryData.eqIsTax==1">是</span>
                             <span class="li-cont" v-else>否</span>
                          </li>
                           <li>
                             <span class="li-name01">交货期限:</span>
                             <span class="li-cont">{{enquiryData.eqDelDeadline}}</span>
                          </li>
                           <li>
                             <span class="li-name01">税点:</span>
                             <span class="li-cont">{{enquiryData.eqTaxPoint}}</span>
                          </li>
                           <li>
                             <span class="li-name01">补充信息:</span>
                             <span class="li-cont">{{enquiryData.eqDesc}}</span>
                          </li>
                       </ul>    
                  </div>
              </el-tab-pane>
            <el-tab-pane label="询价供应商" name="tride">
                 <!--   询价供应商 -->                          
                  <div class="detail-table">
                       <h3 class="conttitle">询价供应商</h3>
                         <el-table
                        ref="multipleTable"
                        border
                        :data="suppList"
                        tooltip-effect="dark"
                        style="width: 100%"
                         highlight-current-row              
                        >             
                        <el-table-column 
                        type="index" 
                        width="50">
                        </el-table-column>
                        <el-table-column
                          prop="suppChineseName"
                          label="供应商全称"
                          show-overflow-tooltip
                          width="160">
                        </el-table-column>               
                          <el-table-column
                          prop="suppContactName"
                          label="联系人"
                          show-overflow-tooltip
                          width="140">
                        </el-table-column>
                          <el-table-column
                          prop="suppMobile"
                          label="电话"
                          show-overflow-tooltip
                          width="140">
                        </el-table-column>
                          <el-table-column
                          prop="suppFax"
                          label="传真"
                          show-overflow-tooltip
                          width="140">
                        </el-table-column>
                          <el-table-column
                          prop="suppEmail"
                          label="邮箱"
                          show-overflow-tooltip
                          width="140">
                        </el-table-column>
                        <el-table-column
                          prop="eqIsOnline"
                          label="在线报价"
                          show-overflow-tooltip>
                          <template scope="scope">
                            <span>{{ formatStata(scope.row.eqIsOnline)}}</span>
                          </template>
                        </el-table-column>
                        <el-table-column
                          prop="eqTotalPrice"
                          label="邮件提醒"
                          show-overflow-tooltip>
                          <template scope="scope">
                            <span>{{ formatStata(scope.row.eqIsOnline)}}</span>
                          </template>
                        </el-table-column>
                        <el-table-column 
                        prop="eqTotalPrice" 
                        label="报价总金额" 
                        show-overflow-tooltip 
                        width="140">
                        <template scope="scope">
                          <el-tag type="danger" v-if="scope.row.eqTotalPrice==null">未报价</el-tag>
                          <el-tag v-else>{{scope.row.eqTotalPrice}}</el-tag>
                        </template> 
                        </el-table-column>          
                    </el-table>   
                  </div> 
            </el-tab-pane>              
            </el-tabs>
       </el-card>
  </div>
</template>
<script>
  import {getQuoteInfo} from '@/api/quote'
  export default {
    name:'quoDetail',
    components:{
    },
    data() {
      return {
        loading:true,
        activeName: 'first',     
        quoteData:[],
        mateList:[],
        enquiryData:[],
        suppList:[],
        rowId:this.$route.query.id,  
        currentRow:[],
        //状态显示转换
        formatStata(status) {
          const statusMap = {
            0: "否",
            1: "是",
          };
          return statusMap[status];
        }
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
      this.loading = true;
      //获取询价列表
      const id = this.$route.query.id;
      getQuoteInfo(id).then(response => {
        this.loading = false;
        if(response.result){         
          this.quoteData = response.data.quoteData;
          this.mateList = response.data.mateList;
          this.enquiryData = response.data.enquiryData;
          this.suppList = response.data.suppList;
        }else{
          this.$message.error(res.msg);
        }
      }) 
    },
    backList(){
      this.$router.push({path: 'quoList',query:{}});
    },
     //编辑
    showEditDialog(url,id) {
        this.$router.push({path: url,query:{id:id}});
    }, 

    }
  }
</script>
<style>
    .block {
    text-align: right;
    margin-top: 10px;
}

   .detail-table{
    margin: 7px 0 0 0;
    /*padding: 0 0 10px 0;*/
    clear: both;
    overflow: hidden;
   }
   .s-ul{
    margin: 0 0 10px 0;
    padding: 0;
    clear: both;
    overflow: hidden;
    width: 100%;
   }
 
   .el-card__header{
     padding:12px 20px ;
   }

</style>