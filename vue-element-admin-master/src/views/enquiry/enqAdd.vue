<template>
   <div class="container" >     
        <el-card class="box-card" shadow="never" v-loading="loading"> 
          <div slot="header" class="clearfix">
            <span>新增询价单</span>
          <!--   <el-button style="float: right;" type="primary" size="mini" @click="backBtn">返回询价列表</el-button> -->
          </div>
          <el-form :model="enquiryData" :rules="ruleForm" ref="form" label-width="80px" class="formStly" size="small">    
            <div class="newcont01 item">           
            <div class="">
            <h3 class="conttitle">询价标题</h3>            
                    <el-row type="flex" class="row-bg" >
                        <el-col :span="11">
                            <el-form-item label="询价标题" prop="eqTitle">
                                <el-input v-model="enquiryData.eqTitle"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>                   
            </div>
          </div> 
        </el-form>
 
     <!--    询价物料  --> 
        <div class="newcont02 item">           
            <div class="enqtitle">
            <h3 class="conttitle">询价物料</h3>     
              <el-form :model="materiel" :rules="ruleForm" ref="form" label-width="80px" class="formStly" size="small">          
                    <el-row type="flex" class="row-bg" >
                         <!-- <el-col :span="6">
                            <el-form-item label="物料分类" prop="">
                              <el-select style="width:100%">
                                   <el-option
                                      v-for="item in options"
                                      :key="item.value"
                                      :label="item.label"
                                      :value="item.value">
                                    </el-option>
                              </el-select>
                            </el-form-item>
                        </el-col>                        
                        <el-col :span="6">
                            <el-form-item label="物料代码" prop="mateK3Code">
                              <el-input v-model="form.mateK3Code"></el-input>
                            </el-form-item>
                        </el-col>  -->
                        <el-col :span="6">
                            <el-form-item label="物料名称" prop="mateName">
                              <el-input v-model="materiel.mateName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="规格型号" prop="mateModel">
                              <el-input v-model="materiel.mateModel"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="物料单位" prop="eqUnit">
                              <el-input v-model="materiel.eqUnit"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row> 
                     <el-row type="flex" class="row-bg" >                        
                      
                        <el-col :span="6">
                            <el-form-item label="物料数量" prop="eqMateNum">
                              <el-input type="number" v-model="materiel.eqMateNum"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="补充信息" prop="eqMateDesc">
                              <el-input v-model="materiel.eqMateDesc"></el-input>
                            </el-form-item>
                        </el-col>                          
                        <el-col :span="4" style="margin-left:15px;">
                           <el-button type="danger" icon="el-icon-plus" @click="addMateriel">添加产品</el-button>
                        </el-col>
                    </el-row>
                   
            </el-form>                   
            </div>
            <template>
              <el-table
                ref="multipleTable"
                border
                :data="materielList"
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
                  width="140">
                </el-table-column>
                  <el-table-column
                  prop="eqMateDesc"
                  label="补充信息"
                  show-overflow-tooltip
                  width="140">
                </el-table-column>
                 <el-table-column
                  prop="suppGrade"
                  label="点击取消"
                  show-overflow-tooltip>
                  <template scope="scope">       
                    <span><a class="operatIcon colorblue" @click="romoveMateriel(scope.row)"><i class="el-icon-delete"></i></a></span>
                </template>
                </el-table-column>
            </el-table>
         </template>
          </div>
   <!--    询价供应商 -->
           <div class="newcont02 item">           
            <div class="enqtitle">
            <h3 class="conttitle">询价供应商</h3>   
                 <el-form :model="supplierInfo" :rules="ruleForm" ref="form" label-width="75px" class="formStly" size="small">      
                    <el-row type="flex" class="row-bg" >
                        <el-col :span="6">
                            <el-form-item label="供应商编号" prop="suppK3Code">
                              <el-autocomplete v-model="supplierInfo.supplierName"  
                        :fetch-suggestions="querySupplier" @select="handleSelectSupplier" style="width:180px;">
                    </el-autocomplete>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="简称" prop="suppAliaName">
                                <el-input v-model="supplierInfo.suppAliaName"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="全称" prop="suppChineseName">
                                <el-input v-model="supplierInfo.suppChineseName"style="width:100%"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="">                      
                                <el-checkbox v-model="supplierInfo.eqIsOnline" label="在线报价"  value="0"></el-checkbox>
                                <el-checkbox v-model="supplierInfo.eqIsEmail" label="邮件提醒" value="1"></el-checkbox>                                
                            </el-form-item>                            
                        </el-col>
                    </el-row> 
                   <el-row type="" class="" >             
                
                    <el-col :span="5">
                        <el-form-item label="电话" prop="suppMobile">
                            <el-input v-model="supplierInfo.suppMobile" style="width:180px"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="传真" prop="suppFax">
                            <el-input v-model="supplierInfo.suppFax"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4">
                        <el-form-item label="邮箱" prop="suppEmail">
                            <el-input v-model="supplierInfo.suppEmail"></el-input>
                        </el-form-item>                                            
                    </el-col>
                    <el-col :span="4">
                        <el-form-item label="联系人" prop="suppContactName">
                               <el-input v-model="supplierInfo.suppContactName"></el-input>
                           <!--  <el-select style="width:180px" >
                                   <el-option
                                      v-for="item in options"
                                      :key="item.value"
                                      :label="item.label"
                                      :value="item.value">
                                    </el-option>
                              </el-select> -->
                        </el-form-item>
                    </el-col>    
                    <el-col :span="4">
                      <el-button type="danger" icon="el-icon-plus" style="margin-left:15px;" @click="addSupplier">添加供应商</el-button>    
                    </el-col>                 
                </el-row>                
                </el-form>  
            </div>
          </div>
              <template>
              <el-table
                ref="multipleTable"
                border
                :data="supplierList"
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
                <template scope="scope">       
                    <span v-if="scope.row.eqIsOnline==1">是</span>
                    <span v-if="scope.row.eqIsOnline==0">否</span>
                </template>               
                </el-table-column>
                 <el-table-column
                  prop="eqIsEmail"
                  label="邮箱提醒"
                  show-overflow-tooltip>
                   <template scope="scope">       
                    <span v-if="scope.row.eqIsEmail==1">是</span>
                    <span v-if="scope.row.eqIsEmail==0">否</span>
                </template>                   
                </el-table-column>
                <el-table-column
                  prop="suppGrade"
                  label="点击取消"
                  show-overflow-tooltip>
                   <template scope="scope">       
                    <span><a class="operatIcon colorblue" @click="romoveSupplier(scope.row)"><i class="el-icon-delete"></i></a></span>
                </template>                 
                </el-table-column>
            </el-table>
         </template> 
<!-- 采购要求   -->    
        <el-form :model="enquiryData" :rules="ruleForm" ref="form" label-width="80px" class="formStly" size="small">      
            <div class="newcont01 item">           
            <div class="enqtitle">
            <h3 class="conttitle">采购要求</h3>            
                <el-row type="flex" class="row-bg" >
                    <el-col :span="8">
                        <el-form-item label="询价日期" prop="eqStartDate">
                           <el-date-picker  
                            style="width:100%;"                                               
                              v-model="enquiryData.eqStartDate"
                              type="datetime"
                              format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd"
                              placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="截止日期" prop="eqDeadLine">
                           <el-date-picker  
                            style="width:100%;"                                               
                              v-model="enquiryData.eqDeadLine"
                              type="datetime"
                              format="yyyy-MM-dd"
                              value-format="yyyy-MM-dd"
                              placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                    </el-col>
                  
                </el-row> 
                <el-row type="flex" class="row-bg" >
                    <el-col :span="8">
                        <el-form-item label="工程地点" prop="eqLocation">
                              <el-input v-model="enquiryData.eqLocation"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="付款方式" prop="eqPayMethod">
                            <el-input v-model="enquiryData.eqPayMethod"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="交货期限" prop="eqDelDeadline">
                           <el-input v-model="enquiryData.eqDelDeadline"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row> 
                <el-row type="flex" class="row-bg" >
                    <el-col :span="8">
                        <el-form-item label="税点" prop="eqTaxPoint">
                            <el-input v-model="enquiryData.eqTaxPoint"></el-input>
                         </el-form-item>
                   </el-col>
                    <el-col :span="8">
                        <el-form-item label="开票要求" prop="endDate">
                             <el-radio-group v-model="enquiryData.eqIsTax">
                                <el-radio :label="1">含税</el-radio>
                                <el-radio :label="0">不含税</el-radio>                          
                              </el-radio-group>                         
                        </el-form-item>
                    </el-col>
                 
                </el-row>
            </div>
          </div> 
        </el-form>
        <div class="newcont04"></div> 
        <center> <el-button type="primary" style="width:200px;" @click="hanleAddEnquiry">确认发布</el-button></center>  
        </el-card>        
   </div>
 
</template>
<script>
import {addEnquiry,getEnquiryInfo,editEnquiry} from '@/api/enquiry'
import {getSupplierList} from '@/api/supplier'
  export default {
    name:'enqAdd',
    components:{
    
    },
    data() {
      return {    
          loading:false,    
        supplierInfo:{
            suppK3Code:"",
            suppAliaName:"",
            suppContactName:"",
            suppChineseName:"",
            eqIsOnline:true,
            eqIsEmail:true,
            suppMobile:"",
            suppFax:"",
            suppEmail:"",
            eqMateDesc:"",
            suppId:"",
            suppNum:""
        },    
        options:{},
        enquiryData:{
            eqMateList:[],
            eqSuppList:[]
        },
        ruleForm: {  
            mateName: [{ required: true, message: '请输入产品名称', trigger: 'blur' }],
            mateModel: [{ required: true, message: '请输入规格型号', trigger: 'blur' }],
            eqMateNum: [{ required: true, message: '请输入物料数量', trigger: 'blur' }],          
        },
        mateNum:1,
        suppNum:1,       
        materiel:{
            mateNum:"",
            mateName:"",
            mateModel:"",
            eqUnit:"",
            eqMateNum:"",
            eqMateDesc:""
        }, 
        materielList:[],   
        supplierList:[],    
       
      }
    }, 
    created(){
        this.getData(); 
    },
   /* watch:{
    '$route':"getData"
    },*/
    methods: {      
    getData(){
        
    //编辑  
      if (this.$route.query.id != undefined){
          this.loading = true
        const id=this.$route.query.id
        getEnquiryInfo(id).then(response => {    
            this.loading = false              
            if(response.result) {             
                this.materielList = response.data.mateList;
                this.supplierList= response.data.suppList; 
                this.enquiryData= response.data.enquiryData;       
            }else {
                    this.$message.error(res.msg);
            }      
            })
        }
    },   

    addMateriel(){
       //添加物料
       
       var records = {}
      records.mateNum = this.mateNum;
      records.mateName = this.materiel.mateName
      records.eqUnit = this.materiel.eqUnit
      records.mateModel = this.materiel.mateModel
      records.eqMateNum = this.materiel.eqMateNum
        records.eqMateDesc = this.materiel.eqMateDesc
        this.materielList.push(records)
        this.mateNum ++;
   },
   romoveMateriel(item){
       console.log(item)
       for(var n=0;n<this.materielList.length;n++){
           var m = this.materielList[n];
           if(m.mateNum == item.mateNum){
               this.materielList.splice(n,1)
           }
       }
   },
  //获取供应商信息
    querySupplier(queryString, cb) {      
       getSupplierList('','',1,1000).then(response => {  
            this.param = response.data.rows.map(function (row) {
                row.value = row.suppK3Code;
                return row;
            });
            cb(this.param);
        });
    }, 
    handleSelectSupplier(item) {       
        this.supplierInfo.suppId = item.id;
        this.supplierInfo.suppK3Code = item.suppK3Code;
        this.supplierInfo.suppChineseName=item.suppChineseName;
        this.supplierInfo.suppAliaName=item.suppAliaName;
        this.supplierInfo.suppContactName=item.suppContactName;
        this.supplierInfo.suppMobile=item.suppMobile;
        this.supplierInfo.suppEmail=item.suppEmail;
  
    },
   //添加供应商
   addSupplier(){   
        var arr = {};
        arr.suppNum=this.suppNum;
        arr.suppId=this.supplierInfo.suppId
        arr.suppK3Code=this.supplierInfo.suppK3Code;       
        arr.suppChineseName=this.supplierInfo.suppChineseName;
        arr.suppAliaName=this.supplierInfo.suppAliaName;
        arr.suppContactName=this.supplierInfo.suppContactName;
        arr.suppMobile=this.supplierInfo.suppMobile;
        arr.suppFax=this.supplierInfo.suppFax;
        arr.suppEmail=this.supplierInfo.suppEmail;
        arr.eqIsOnline=this.supplierInfo.eqIsOnline==true?1:0;
        arr.eqIsEmail=this.supplierInfo.eqIsEmail==true?1:0; 

        this.supplierList.push(arr);       
        this.suppNum++;
   },
   romoveSupplier(item){
         console.log(item)
       for(var n=0;n<this.supplierList.length;n++){
           var m = this.supplierList[n];
           if(m.suppNum == item.suppNum){
               this.supplierList.splice(n,1)
           }
       }
   },
   handleChecked(val){
        console.log(this.eqIsOnline)
   },
    //新增询价
    hanleAddEnquiry(){
        //物料和供应商不运行为空
        if(this.materielList.length==0){
            this.$message.info('物料信息不允行为空');
            return
        } 
        if(this.supplierList.length==0){
            this.$message.info('供应商信息不允行为空');
            return
        } 

        this.loading = true
        this.enquiryData.eqMateList=this.materielList;
        this.enquiryData.eqSuppList=this.supplierList;
        if (this.$route.query.id != undefined){
            editEnquiry(this.enquiryData).then(response => { 
                this.loading = false
                this.$message.info(response.msg);
            })
        }else{
            addEnquiry(this.enquiryData).then(response => { 
                this.loading = false
                this.$message.info(response.msg);
            })
        }
    } 
  
    }
               
}

</script>
<style>
    li{list-style: none}
   .container{
      padding:5px;
   }
   .container .el-card__header{
     padding:5px 20px;
   }
   .enqtitle{
     width:100%;
     border-bottom: 1px dashed #e5e5e5;
   }
 
   .item{       
    display: inline;
    margin: 0;
    padding: 0 0 5px 0;
    width: 100%;
   }
   .enqAddbtn{
    padding:5px 5px 5px;
   }
   .invoice{
        width:100%;
        border:1px solid #d9d9d9;
   }
   .newcont04{   
    margin: 15px 0 0 0;
    padding: 5px 0 0 0;
    width: 100%;
   
   }
</style>