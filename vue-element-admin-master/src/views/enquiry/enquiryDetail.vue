<template>
    <div class="contanier">  
<!-- 询价单详情  --> 
    <el-card class="box-card" shadow="never" v-loading="loading"> 
          <div slot="header" class="clearfix">
            <span>询价单</span>
            <el-button style="float: right;" type="primary" size="mini" @click="backList">返回列表</el-button>
            <el-button style="float: right; margin:0 5px" type="primary" size="mini" @click="showEditDialog('enqAdd',rowId)">编辑</el-button>
          </div>            
          <div class="newcont01 item">           
             <el-tabs v-model="activeName">
              <el-tab-pane label="询价明细" name="first">
                    <div class="detail-table">
                        <h3 class="conttitle">询价物料</h3> 
                          <el-table
                        ref="multipleTable"
                        border
                        :data="mateList"
                        tooltip-effect="dark"
                        style="width: 100%"
                         highlight-current-row              
                        >             
                        <el-table-column
                            type="index"
                            width="50">
                        </el-table-column>
                        <el-table-column
                          prop="mateName"
                          label="物料名称"
                          show-overflow-tooltip
                          width="160">
                        </el-table-column>
                         <el-table-column
                          prop="mateModel"
                          label="规格型号"
                          show-overflow-tooltip
                          width="160">
                        </el-table-column>                
                          <el-table-column
                          prop="eqUnit"
                          label="单位"
                          show-overflow-tooltip
                          width="140">
                        </el-table-column>
                          <el-table-column
                          prop="eqMateNum"
                          label="数量"
                          show-overflow-tooltip
                          width="">
                        </el-table-column>
                          <el-table-column
                          prop="eqMateDesc"
                          label="补充信息"
                          show-overflow-tooltip
                          width="">
                        </el-table-column>
                         <el-table-column
                          prop="eqUnitPrice"
                          label="最低报价(单价)"
                          show-overflow-tooltip
                          width="">
                           <template slot-scope="scope">                   
                             <span v-if="scope.row.eqUnitPrice==null">未报价</span>  
                            <span v-else>{{scope.row.eqUnitPrice}}</span>                          
                          </template>  
                        </el-table-column>
                        <el-table-column
                          prop="eqTotalPrice"
                          label="最低报价(总价)"
                          show-overflow-tooltip
                          width="140"> 
                           <template slot-scope="scope">  
                          <span v-if="scope.row.eqTotalPrice==null">未报价</span>  
                          <span v-else>{{scope.row.eqTotalPrice}}</span>
                          </template>                        
                        </el-table-column>                                          
                    </el-table>
                    </div>
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
                  width="140">
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
                  <template slot-scope="scope">
                    <span>{{ formatStata(scope.row.eqIsOnline)}}</span>                    
                  </template>                
                </el-table-column>
                <el-table-column
                  prop="eqTotalPrice"
                  label="邮件提醒"
                  show-overflow-tooltip>
                  <template slot-scope="scope">    
                    <span>{{ formatStata(scope.row.eqIsEmail)}}</span>               
                  </template>                 
                </el-table-column> 
                <el-table-column
                  prop="eqTotalPrice"
                  label="报价总金额"
                  show-overflow-tooltip
                  width="140">
                  <template slot-scope="scope">                   
                    <el-tag type="danger" v-if="scope.row.eqTotalPrice==null">未报价</el-tag>
                    <el-tag v-else>{{scope.row.eqTotalPrice}}</el-tag>
                  </template> 
                </el-table-column>           
               
            </el-table>        
              </el-tab-pane>
              <el-tab-pane label="基本信息" name="second">
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
                        <li style="width:100%">
                             <span class="li-name01">补充信息:</span>
                             <span class="li-cont">{{enquiryData.eqDesc}}</span>
                        </li>
                       </ul>    
                  </div>
              </el-tab-pane>
                   <!-- <el-tab-pane label="比价单" name="Three">
                  <div class="detail-table">
                       <h3 class="conttitle">比价明细</h3>
                  </div>
                  </el-tab-pane> -->              
            </el-tabs>
          </div>
      </el-card>
      </div>         

    </div>
 
</template>
<script>
import {getEnquiryInfo} from '@/api/enquiry'
  export default {
    name:'enquiryDetail',
    components:{  
    },
    data() {
      return {  
        loading:true,
        activeName: 'first',  
        mateList:[],
        suppList:[],
        enquiryData:[],
        rowId:this.$route.query.id,
      // 状态显示转换
      formatStata(status) {
        const statusMap = {
          0: "否",
          1: "是"
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
      this.loading = true
    //获取询价列表详情信息
    const id=this.$route.query.id
        getEnquiryInfo(id).then(response => {  
          this.loading = false       
            if(response.result) {
                this.mateList = response.data.mateList;
                this.suppList=response.data.suppList;
                this.enquiryData=response.data.enquiryData
            }else {
                    this.$message.error(res.msg);
                }      
            })      
        }, 
    backList(){
        //this. $router.back(-1);
        this.$router.push({path: 'enquiryList',query:{}});
    },
     //编辑
    showEditDialog(url,id) {
        this.$router.push({path: url,query:{id:id}});
    },  
    }
  }
</script>
<style>
.contanier{
    padding:15px;
}

.rebtn01{
    margin: 5px;
    padding: 0;
    width: 67px;
    height: 22px;
    background: url(/src/assets/img/rebtn.gif) no-repeat left -27px scroll;
    text-indent: -9999px;
    cursor: pointer;
    display: inline-block;
}
.rebtn02{
     margin: 5px;
    padding: 0;
    width: 67px;
    height: 22px;
    background: url(/src/assets/img/rebtn.gif) no-repeat left -54px scroll;
    text-indent: -9999px;
    cursor: pointer;
    display: inline-block;
}

    .block {
    text-align: right;
    margin-top: 10px;
    }
   .detail-table{
    margin: 7px 0 0 0;
    padding: 0 0 10px 0;
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

   .formStly .el-input--small .el-input__inner{height:27px;line-height: 27px;}

</style>