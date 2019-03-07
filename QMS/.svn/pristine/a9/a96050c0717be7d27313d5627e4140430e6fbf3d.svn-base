<template>
	<!--
    	作者：offline
    	时间：2018-04-02
    	描述：审核记录
    -->
	<div class="layout-content">	
		<div style="padding-bottom:5px;border-bottom: 1px solid #ebeef5;">
            <el-button size="small" type="primary" v-if="createType == 1" @click="pushToApprovedCreator('createApprovedRecord',supplierData)" :disabled="supplierData.bsIsApprove==1">
				{{$t('supplier.NewAudit')}}
			</el-button>
			<el-button size="small" type="primary" v-if="createType == 2" @click="pushToApprovedCreator('createApprovedRecord',productData)" :disabled="productData.bsIsApprove==1" >
				{{$t('product.NewProcessAudit')}}
			</el-button>
    	</div>  
	
      	<el-table :data="RecordlistTable" style="width: 100%;">
      		<el-table-column prop="flowBy.bsName" :label="$t('ApprovedFlow.ProjectName')" align="center">
		    </el-table-column>
		    <el-table-column prop="bsCreatedTime" :label="$t('ApprovedFlow.AudiTime')">
		    </el-table-column>
		    <el-table-column :label="$t('ApprovedFlow.FlowType')">
		    	<template scope="scope">
					<span>{{$t('approved.type['+scope.row.flowBy.bsType+']')}}</span>
				</template>
		    </el-table-column>
		    <el-table-column prop="flowBy.approvedBy.bsName" :label="$t('ApprovedFlow.Principal')">
		    </el-table-column>
		    <el-table-column :label="$t('ApprovedFlow.AuditResults')">
		    	<template scope="scope">
           <span class="circle"  v-bind:class="{bgblue:scope.row.bsResult==0, bggreen:scope.row.bsResult==1 ,bgorange:scope.row.bsResult==2}"></span>

					<span v-bind:class="{blue:scope.row.bsResult==0, green:scope.row.bsResult==1 ,orange:scope.row.bsResult==2, gray:scope.row.bsResult==3}">{{$t('approved.flow_result['+scope.row.bsResult+']')}}</span>

				</template>
		    </el-table-column>
   			<el-table-column :label="$t('Button.operating')">
			    <template slot-scope="scope">
			        <el-button
			          size="mini"
			          @click="details('approved',scope)">{{$t('Button.Details')}}</el-button>			        
			    </template>
   		 	</el-table-column>
   		</el-table>		
	</div>	
</template>

<script>
    export default {
    data() {
      return {
        RecordlistTable: [],
		supplierData:{bsIsApprove:0},
		productData:{bsIsApprove:0},
		createType:0,
      }
    },
    created(){
        this.getlist();
    }, 
    methods:{
    	getlist(){  
            if(this.$route.path.includes('supplier'))  {
				this.createType = 1;
				this.supplierData = this.$store.getters.getSupplierData;
                this.api.approvedFlowRecord.getlist({bsSuppId:this.supplierData.id}).then((res) => {
                    this.RecordlistTable = res.data.rows;
                }); 
            }		  		
            else if(this.$route.path.includes('product')) {
				this.createType = 2;
				this.productData = this.$store.getters.getProductData;
                this.api.approvedFlowRecord.getlist({bsPrId:this.productData.id}).then((res) => {
                    this.RecordlistTable = res.data.rows;
                }); 
            }  
    	},
    	details(url,data){
    		this.$router.push({path: url,query:{bsFlowRecordId:data.row.id}});
    	},
		pushToApprovedCreator(url,data){
			if(this.createType == 1){
				this.$router.push({path: url,query: {supplier:data.id,bsName:data.bsSuppChieseName,bsCode:data.bsSuppCode}});
			}else if(this.createType == 2){
				this.$router.push({path: url,query: {product:data.id,bsName:data.bsPrName,bsCode:data.bsPrCode}});
			}
        },
	}
  }
</script>

<style>
.circle{
  font-size: 14px;color: #27558e;width: 10px;height: 10px;display: inline-block;background-color:#999;
  border-radius: 50%; 
    margin-right: 5px
}

.layout-content{padding-left: 1%;padding-right: 1%;}
.red{color: #fd5822;}
.blue{color: #409EFF;}
.green{color: #67C23A;}
.orange{color: #fd5822;}
.gray{color: #333;}

 .bgred{background-color:#fd5822;}
.bgblue{background-color: #409EFF;}
.bggreen{background-color: #67C23A;}
.bgorange{background-color: #fd5822;}
.bggray{background-color: #333;}
</style>
