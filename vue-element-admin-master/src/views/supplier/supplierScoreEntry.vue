<template>
  <div>    
     <div style="margin-top:10px;margin-left:20px;">
          <el-form :inline="true" :model="scoreForm" class="demo-form-inline">           
                <el-form-item label="供应商名称" prop="supplierName" >
                    <el-autocomplete v-model="scoreForm.supplierName"  
                        :fetch-suggestions="querySupplier" @select="handleSelectSupplier" style="width:220px;">
                    </el-autocomplete>
                </el-form-item>
                <el-form-item label="K3编号" prop="suppK3Code">
                    <el-input v-model="scoreForm.suppK3Code" readonly></el-input>
                </el-form-item>
                 <el-form-item label="id" prop="suppId" style="display: none">
                    <el-input v-model="scoreForm.suppId" readonly ></el-input>
                </el-form-item>
          </el-form>
   </div>

    <div style="padding:7px 25px 3px 22px">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix" >
            <span class="sroreIcon"><i class="iconfont">&#xe61c;</i>供应商评分管理</span>
            <el-button style="float: right;"  type="primary" size="mini" @click="Save()" >保存</el-button>
          </div>
          <el-card shadow="naver">
            <el-row>
               <el-form :model="scoreForm" :rules="ruleForm" ref="scoreForm" label-width="120px">
                    <el-col :span="8">
                  <el-form-item  label="进料抽检合格率:" prop="batchValue">
                     <el-input type="number"   placeholder="请输入内容" v-model.number="scoreForm.batchValue">
                    <template slot="append">%</template>
                  </el-input>                    
                  </el-form-item>
                </el-col>

                <el-col :span="8" :offset="4">
                   <el-form-item label="进料抽检得分"  prop="batchScore">             
                   <el-input   placeholder="请输入内容" v-model="scoreForm.batchScore">
                       <template slot="append">分</template>
                   </el-input>
                 </el-form-item>
                </el-col>
               </el-form>              
              </el-row>  
          </el-card>
            <el-card shadow="naver" style="margin:10px 0;">
            <el-row>
               <el-form :model="scoreForm" :rules="ruleForm" ref="scoreForm" label-width="120px">
                    <el-col :span="8">
                  <el-form-item  label="制程品质占比:" prop="processValue">
                     <el-input  type="number" placeholder="请输入内容" v-model="scoreForm.processValue">
                    <template slot="append">%</template>
                  </el-input>                    
                  </el-form-item>
                </el-col>

                <el-col :span="8" :offset="4">
                   <el-form-item label="制程品质得分"  prop="processScore">             
                   <el-input   placeholder="请输入内容" v-model="scoreForm.processScore">
                       <template slot="append">分</template>
                   </el-input>
                 </el-form-item>
                </el-col>
               </el-form>              
              </el-row>  
          </el-card>
            <el-card shadow="naver" style="margin:10px 0;">
            <el-row>
               <el-form :model="scoreForm" :rules="ruleForm" ref="scoreForm" label-width="120px">
                    <el-col :span="8">
                  <el-form-item  label="异常回复时效:" prop="replyValue">
                     <el-input  type="number" placeholder="请输入内容" v-model="scoreForm.replyValue">
                    <template slot="append">小时</template>
                  </el-input>                    
                  </el-form-item>
                </el-col> 
                  <el-col :span="8" :offset='4'>
                  <el-form-item  label="异常回复得分:" prop="replyScore">
                     <el-input  type="number" placeholder="请输入内容" v-model="scoreForm.replyScore">
                    <template slot="append">小时</template>
                  </el-input>                    
                  </el-form-item>
                </el-col>              
               </el-form>              
              </el-row>  
          </el-card>         
          <el-card shadow="naver" style="margin:10px 0;">
            <el-row>
               <el-form :model="scoreForm" :rules="ruleForm" ref="scoreForm" label-width="120px">
                <el-col :span="8">
                  <el-form-item  label="ROHS无有害物质:" prop="rohsValue">
                     <el-input type="number" placeholder="0:无 / 1:有" v-model="scoreForm.rohsValue">                  
                  </el-input>                    
                  </el-form-item>
                </el-col> 
                <el-col :span="8" :offset="4">
                   <el-form-item label="ROHS得分:"  prop="rohsScore">             
                   <el-input   placeholder="请输入内容" v-model="scoreForm.rohsScore">
                       <template slot="append">分</template>
                   </el-input>
                 </el-form-item>
                </el-col>             
               </el-form>              
              </el-row>  
          </el-card>
           <el-card shadow="naver" style="margin:10px 0;">
            <el-row>
               <el-form :model="scoreForm" :rules="ruleForm" ref="scoreForm" label-width="120px">
                    <el-col :span="8">
                  <el-form-item  label="超额运费:" prop="freightValue">
                     <el-input type="number"  placeholder="请输入内容" v-model="scoreForm.freightValue">
                      <template slot="append">次/月</template>                  
                    </el-input>                                        
                  </el-form-item>
                </el-col>  
                 <el-col :span="8" :offset="4">
                   <el-form-item label="超额运费得分:"  prop="freightScore">             
                   <el-input  placeholder="请输入内容" v-model="scoreForm.freightScore">
                       <template slot="append">分</template>
                   </el-input>
                 </el-form-item>
                </el-col>             
               </el-form>              
              </el-row>  
          </el-card>
             <el-card shadow="naver" style="margin:10px 0;">
            <el-row>
               <el-form :model="scoreForm" :rules="ruleForm" ref="scoreForm" label-width="120px">
                    <el-col :span="8">
                  <el-form-item  label="准时交付率:" prop="deliveryValue">
                     <el-input type="number"  placeholder="请输入内容" v-model="scoreForm.deliveryValue">
                      <template slot="append">%</template>                  
                    </el-input>                                        
                  </el-form-item>
                </el-col>  
                 <el-col :span="8" :offset="4">
                   <el-form-item label="准时交付率得分:"  prop="deliveryScore">             
                   <el-input  placeholder="请输入内容" v-model="scoreForm.deliveryScore">
                       <template slot="append">分</template>
                   </el-input>
                 </el-form-item>
                </el-col>             
               </el-form>              
              </el-row>  
          </el-card>
        <el-card shadow="naver" style="margin:10px 0;">
            <el-row>
               <el-form :model="scoreForm" :rules="ruleForm" ref="scoreForm" label-width="120px">
                    <el-col :span="8">
                  <el-form-item  label="降价比率:" prop="priceValue">
                     <el-input type="number" placeholder="请输入内容" v-model="scoreForm.priceValue">
                      <template slot="append">%</template>                  
                    </el-input>                                        
                  </el-form-item>
                </el-col>  
                 <el-col :span="8" :offset="4">
                   <el-form-item label="价格得分:"  prop="priceScore">             
                   <el-input  placeholder="请输入内容" v-model="scoreForm.priceScore">
                       <template slot="append">分</template>
                   </el-input>
                 </el-form-item>
                </el-col>             
               </el-form>              
              </el-row>  
          </el-card>
        </el-card>
    </div>
         

<!--end-->
</div>
</template>
<script>
import {getSupplierList} from '@/api/supplier'
import {addScore} from '@/api/supplierScore'
import MessageBoxDelete from "@/components/Dialog/MessageBox.vue";
export default {
  components: {
    MessageBoxDelete
  },
  data() {
    return {
      dialogVisible: false,     
      formQuery: { 
        
      },
      param:[],     
      scoreForm:{
         suppId:'',
         suppK3Code:'',
         suppChineseName:''
      }, 
      queryParams: {
        page: 1,
        rows: 10,
        pkParent: -1
      },
    ruleForm: {                   
            batchValue: [
                { required: true,  message: '请填写内容'},
                {validator(r,v,b){(/^[\d]*$/).test(v)?b():b(new Error('请填写数字'))}}
            ]                  
        },     
    }
  },
  created() {
  /*  this.getData();*/    
  },
  methods: {
    handleSubmit(name) {
      this.getData();
    },
    //获取供应商信息
    querySupplier(queryString, cb) {      
       getSupplierList('','',1,1000).then(response => {  
            this.param = response.data.rows.map(function (row) {
                row.value = row.suppChineseName;
                return row;
            });
            cb(this.param);
        });
    },    
    handleSelectSupplier(item) {       
        this.scoreForm.suppId = item.id;
        this.scoreForm.suppK3Code = item.suppK3Code;
        this.scoreForm.suppChineseName=item.suppChineseName
  
    },
    //保存供应商评分信息
    Save(){    
      addScore(this.scoreForm).then(response => {  
       this.$message.info(response.msg);
       this.scoreForm=response.data   
    });
  },

  }
}

</script>
<style  scoped>
.block {
  text-align: right;
  margin-top: 10px;
}

.sroreIcon{color:#42b983!important;font-size: 13px;}

</style>
