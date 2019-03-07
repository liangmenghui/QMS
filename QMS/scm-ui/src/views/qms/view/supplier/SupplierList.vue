<template>
	<!--
    	作者：offline
    	时间：2018-03-27
    	描述：供应商列表
    -->
    <div class="SupplierList">
  <section v-if="!showRooterView">
    <!--工具条-->
   
    	<Row style="margin-bottom: 10px;">                        
            <Select size="large" style="width: 200px;display: inline-block;margin-top: -4px;" v-model="formQuery.bsRiskLevel" placeholder="按风险等级搜索">
              	<Option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              	</Option>
            </Select>              
            <el-input size="medium" style="width: 200px;display: inline-block;" v-model="formQuery.keyWord" :placeholder="$t('Button.Keyword-search')">
           	</el-input>             
            <el-button style="padding: 10px 10px;" type="primary" icon="el-icon-search" @click="getData">{{$t('Button.Inquire')}}</el-button>
            <el-button style="padding: 10px 10px;" type="primary" icon="el-icon-refresh" @click="cleargetData">{{$t('Button.Reset')}}</el-button>          
      	</Row>
        <template>
            <el-table class="mytable"
            :data="tableData"
            style="width: 100%"
            row-key="id"
            :expand-row-keys="expands"
            @row-click="rowClick">
            <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                	<el-button v-perm-query size="small" type="primary" @click="pushToChildWithData('supplier/basicInfo',props)">
                		{{$t('supplier.BasicInfo')}}
                	</el-button>
                	<el-button v-perm-query size="small" type="primary" @click="pushToChildWithData('supplier/advanceInfo',props)">
                		{{$t('supplier.AdvancelInfo')}}
                	</el-button>
                  <el-button v-perm-query size="small" type="primary" @click="pushToChildWithData('supplier/eHSManagement',props)">
                    {{$t('supplier.EHSApproved')}}
                  </el-button>
                	<el-button v-perm-query size="small" type="primary" @click="pushToChildWithData('supplier/riskManagement',props)">
                		{{$t('supplier.RiskManage')}}
                	</el-button>

					
					<!--
					<el-button v-perm-alloc size="small" type="primary" @click="pushToApprovedCreator('supplier/createApprovedRecord',props)" :disabled="props.row.bsIsApprove==1">
						{{$t('supplier.NewAudit')}}
					</el-button>
                	<el-button  v-perm-verify size="small"  type="primary" @click="pushToSupplierArrovedFlow('supplier/approved',props)" :disabled="props.row.bsIsApprove==0">
                		{{$t('supplier.SupplierAudit')}}
                	</el-button>
					-->
					
                	<el-button v-perm-query size="small" type="primary" @click="pushToChildWithData('supplier/approvedRecordsList',props)">
                		{{$t('supplier.SupplierAudit')}}
                	</el-button>
                   <el-button v-perm-query size="small" type="primary" @click="pushToChildWithData('supplier/CustomerReview',props)">
                    {{$t('supplier.CustomerReview')}}
                  </el-button>
              </el-form>
            </template>
          </el-table-column>
		  <el-table-column
            :label="$t('supplier.RiskLevel')" width="100px"
            prop="bsRiskLevel">
          </el-table-column>
		  
          <el-table-column
            :label="$t('supplier.code')" width="100px"
            prop="bsSuppCode">
          </el-table-column>
          <el-table-column
            :label="$t('supplier.ChieseName')" width="360px"
            prop="bsSuppChieseName">
          </el-table-column>
          <el-table-column
            :label="$t('supplier.AliaName')" width="120px"
            prop="bsSuppAliaName">
          </el-table-column>
          <!-- <el-table-column
            :label="$t('supplier.SuppStatus')" width="150px"
            prop="bsSuppStatus" :formatter="formatter">
          </el-table-column> -->
          <el-table-column
            :label="$t('supplier.ContactName')" width="100px"
            prop="bsSuppContactName">
          </el-table-column>
          <el-table-column
            :label="$t('supplier.Mobile')" width="160px"
            prop="bsSuppMobile">
          </el-table-column>
          
          <el-table-column
            :label="$t('supplier.Type')" width="150px"
            prop="bsSuppType">
          </el-table-column>
        </el-table>
       
        </template> 
         <template> 
            <div class="block">   
                <el-pagination
                  @current-change="changePage"
                  @size-change="SizeChange"
                  :current="1"
                  :current-page.sync="queryParams.page"
                  :page-sizes="pageSizesList"
                  :page-size="queryParams.rows"
                  layout="total, sizes, prev, pager, next, jumper"                 
                  :page-size-opts="pageSizesList"
                  :total="totalCount">
                </el-pagination>
             </div>     
        </template>        
      
  </section>

  <section v-else-if="showRooterView">
    <router-view></router-view>
  </section>
 </div>
</template>

<script>
export default {
    data() {
      return {
        tableData: [],
        expands: [],
        formQuery: {},
        showRooterView: false,
        queryParams:{
                page:1,
                rows:10,
                pkParent:-1
            },      
        pageSizesList: [10, 20, 30, 40, 50, 100],
        totalCount: 0,//数据的总条数,
        options: [{
          value: '1',
          label: '1级'
        }, {
          value: '2',
          label: '2级'
        }, {
          value: '3',
          label: '3级'
        }, {
          value: '4',
          label: '4级'
        }, {
          value: '5',
          label: '5级'
        }],
        value: '',
      }
    },
    created(){
        this.getData();
        this.showRooterView = this.$route.matched.length>2;
    },
    watch: {
        '$route' (to, from) {
            if(to.matched.length == 2) {
                this.getData();
            }
            this.showRooterView = to.matched.length>2;
        }
    },
    methods:{
    	getData(){
            let filter = Object.assign({},this.$route.query,this.formQuery, this.queryParams);
    		this.api.supplierinfo.getlist(filter).then((res) => {
              	this.tableData = res.data.rows;
                this.totalCount= res.data.total;
            });
    	},

      cleargetData(){
        this.formQuery.bsRiskLevel='';   

      },
    	rowClick(row, event, column) {
                Array.prototype.remove = function (val) {
                    let index = this.indexOf(val);
                    if (index > -1) {
                        this.splice(index, 1);
                    }
                };
 
                if (this.expands.indexOf(row.id) < 0) {
                    this.expands.push(row.id);
                } else {
                    this.expands.remove(row.id);
                }
        },
      pushToChildWithData(url,row){
        this.$store.commit("updateSupplierDataStates",row.row);
        this.$router.push({path: url});
      },
      pushToSupplierArrovedFlow(url,data) {
        this.$router.push({path: url,query:{bsFlowRecordId:data.row.bsSuppRecordId}});
      },
      pushToApprovedCreator(url,data){
        this.$router.push({path: url,query: {supplier:data.row.id,bsName:data.row.bsSuppChieseName,bsCode:data.row.bsSuppCode}});
      },
      changePage (page) {
            this.queryParams.page = page;
            this.getData();
        },
        SizeChange(size){
            this.queryParams.rows = size;
            this.getData();
        },
        formatter(row, column){
            return this.$t('supplier.StatusDesc['+row.bsSuppStatus+']');
        }
    }
}
</script>

<style lang="less">
	@import "../public.less";
.ivu-input{height: 38px;}
.block{text-align: right;margin-top:10px;}

.mytable.el-table th{
     background:#f0f0f0 !important;
           
  }
</style>