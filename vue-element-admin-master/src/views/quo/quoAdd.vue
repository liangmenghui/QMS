<template>
    <div style="padding:15px 15px;">  
      <el-card class="box-card" shadow="never" v-loading="loading"> 
          <div slot="header" class="clearfix">
            <el-row type="flex" class="row-bg" >
                <el-col :span="20" style="padding-top:5px;">
                     <span>供应商报价</span>
                </el-col>
                 <el-col :span="4">                      
                      <el-button style="float: right;" type="primary" size="mini" @click="backList">返回列表</el-button>                          
                </el-col>
            </el-row>  
          </div>
             <el-tabs v-model="activeName">
              <el-tab-pane label="填写报价单" name="first">
          <!--   填写报价单 -->
                  <div class="detail-table">
                       <h3 class="conttitle">报价标题</h3>
                       <el-row>
                        <el-col :span="14">                          
                            <el-input v-model="quoteData.qtTitle" placeholder="报价标题" style="width:350px"></el-input>                          
                        </el-col>                                   
                    </el-row>      
                  </div>
           <!--  询价单信息 -->
                    <div class="detail-table">
                        <h3 class="conttitle">产品报价</h3> 
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
                          width="160">
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
                          width="">
                        <template scope="scope">
                          <el-input             
                          v-model="scope.row.qtUnitPrice"
                          style="background:none;border:none;width:100%;"                                     
                          ></el-input>
                        </template>
                        </el-table-column>
                        <el-table-column
                          prop="qtTotalPrice"
                          label="总价"
                          show-overflow-tooltip
                          width="">
                        <template scope="scope">
                          <el-input             
                          v-model="scope.row.qtTotalPrice"
                          style="background:none;border:none;width:100%;"                                    
                          >{{calculateTotal(scope.row)}}</el-input>
                        </template>
                        </el-table-column>
                        <el-table-column
                          prop="qtMateDesc"
                          label="报价备注"
                          show-overflow-tooltip
                          width="140">
                          <template scope="scope">
                          <el-input             
                          v-model="scope.row.qtMateDesc"
                          style="background:none;border:none;width:100%;"
                          @change="handler(scope.row)"             
                          ></el-input>
                        </template>
                        </el-table-column>                    
                    </el-table>
                    </div>
              <!--   报价说明 -->
                          
                  <div class="detail-table">
                       <h3 class="conttitle">报价说明</h3>
                       <el-form :inline="true"  :model="quoteData" :rules="ruleForm" ref="form" label-width="80px" class="formStly" size="small">
                          <el-form-item label="报价日期" prop="qtStartDate">
                           <el-date-picker  
                            style="width:250px;"                                              
                            v-model="quoteData.qtStartDate"
                            type="date"
                            format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd"
                             placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>  
                        <el-form-item label="有效期至" prop="qtDeadLine">
                           <el-date-picker  
                            style="width:250px;"                                                 
                            v-model="quoteData.qtDeadLine"
                            type="date"
                            format="yyyy-MM-dd"
                            value-format="yyyy-MM-dd"
                            placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                         <el-form-item label="交货期" prop="qtDelDeadline">
                            <el-input v-model="quoteData.qtDelDeadline" placeholder="到款后10个工作日" style="width:250px;"></el-input>
                        </el-form-item>
                        <el-form-item label="交货地点" prop="qtDelLocation">
                            <el-input v-model="quoteData.qtDelLocation" placeholder="到款后10个工作日" style="width:250px;"></el-input>
                        </el-form-item>
                         <el-form-item label="验货方式" prop="qtAcceptType">
                            <el-input v-model="quoteData.qtAcceptType" placeholder="验货方式" style="width:250px;"></el-input>
                        </el-form-item>
                         <el-form-item label="付款方式" prop="qtPayMethod">
                            <el-input v-model="quoteData.qtPayMethod" placeholder="付款方式" style="width:250px;"></el-input>
                        </el-form-item>
                        <el-form-item label="补充说明" prop="qtDesc">
                            <el-input v-model="quoteData.qtDesc" placeholder="付款方式" style="width:250px;"></el-input>
                        </el-form-item>
                       </el-form> 
                        <center>
                            <el-button type="primary" style="width:200px; margin-top:25px" @click="hanleAddQuote">确认报价</el-button>                        
                        </center>  
                  </div>
              </el-tab-pane>

              <el-tab-pane label="询价单信息" name="second">
                  <div class="detail-table">
                       <h3 class="conttitle">询价单信息</h3>
                       <ul class="s-ul">
                          <li>
                             <span class="li-name01">询价标题:</span>
                             <span class="li-cont">{{quoteData.eqTitle}}</span>
                          </li>
                           <li>
                             <span class="li-name01">询价代码:</span>
                             <span class="li-cont">{{quoteData.eqCode}}</span>
                          </li>
                         <li>
                             <span class="li-name01">询价日期:</span>
                             <span class="li-cont">{{quoteData.eqStartDate}}</span>
                          </li>
                          <li>
                             <span class="li-name01">截止日期:</span>
                             <span class="li-cont">{{quoteData.eqDeadLine}}</span>
                          </li>                            
                       </ul>
                       <h3 class="conttitle">采购要求</h3>
                       <ul class="s-ul">
                          <li>
                             <span class="li-name01">工程地点:</span>
                             <span class="li-cont">{{quoteData.eqLocation}}</span>
                          </li>
                           <li>
                             <span class="li-name01">付款方式:</span>
                             <span class="li-cont">{{quoteData.eqPayMethod}}</span>
                          </li>
                          <li>
                             <span class="li-name01">是否含税:</span>
                             <span class="li-cont">{{quoteData.eqIsTax}}</span>
                          </li>
                           <li>
                             <span class="li-name01">交货期限:</span>
                             <span class="li-cont">{{quoteData.eqDelDeadline}}</span>
                          </li>
                           <li>
                             <span class="li-name01">税点:</span>
                             <span class="li-cont">{{quoteData.eqTaxPoint}}</span>
                          </li>
                           <li>
                             <span class="li-name01">付款方式:</span>
                             <span class="li-cont">{{quoteData.qtPayMethod}}</span>
                          </li>
                           <li style="width:100%">
                             <span class="li-name01">补充信息:</span>
                             <span class="li-cont">{{quoteData.eqDesc}}</span>
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
                       <!--   <el-table-column
                          prop="mateName"
                          label="序号"
                          show-overflow-tooltip
                          width="80">
                        </el-table-column> -->
                        <el-table-column
                          prop="suppChineseName"
                          label="供应商名称"
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
                          width="">
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
                            <span>{{ formatStata(scope.row.eqIsOnline)}}</span>
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
                  </div> 
            </el-tab-pane>              
            </el-tabs>          
       </el-card>

  </div>
</template>
<script>
import {getQuoteInfo,addQuote} from '@/api/quote'
  export default {
    name:'quoAdd',
    components:{
    
    },
    data() {
      return { 
        loading:false,       
        activeName: 'first',  
        mateList:[],
        suppList:[],
        quoteData:{
          qtMateList:[]
        },   
        //状态显示转换
        formatStata(status) {
          const statusMap = {
            0: "否",
            1: "是",
          };
          return statusMap[status];
        }, 
        ruleForm: {  
            qtStartDate: [{ required: true, message: '填写报价日期', trigger: 'blur' }],
            qtDeadLine: [{ required: true, message: '填写有效期', trigger: 'blur' }],                    
        },      
   
      }
    },  
    created(){
        this.getData(); 
    }, 
    methods: {
    //计算总价 
     calculateTotal: function (row) {
        row.qtTotalPrice = row.qtUnitPrice * row.qtMateNum
      return row.qtTotalPrice
    },     
    handleSubmit(name){
        this.getData();
    },
    getData(){   
    //获取报价信息   
      const id = this.$route.query.id;
      getQuoteInfo(id).then(response => {
        this.loading = false;
        if(response.result){         
          this.quoteData = response.data.quoteData;
          this.mateList = response.data.mateList;         
          this.suppList = response.data.suppList;
        }else{
          this.$message.error(res.msg);
        }
      })   
      
    },
    //确认新增填写报价单
    hanleAddQuote(){

        this.quoteData.qtMateList=this.mateList;
        this.loading=true;
        if (this.$route.query.id != undefined){
            addQuote(this.quoteData).then(response => { 
                this.loading = false
                this.$message.info(response.msg);
            })
        }else{           
            this.loading = false
            this.$message.info(response.msg);
          
        }
    },
    /*返回报价列表*/
    backList(){
        this.$router.push({path: 'quoList',query:{}});
    } 

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
   
   .el-card__header{
     padding:6px 20px!important ;
    
   }
   .formStly{
    margin-top:10px;
   }

</style>