<template>
	<!--
    	作者：offline
    	时间：2018-03-27
    	描述：产品详情
    -->
	<div class="productdetails">
		<!--
        	作者：offline
        	时间：2018-03-28
        	描述：top
        -->
		<div style="height: 50px;">
			<Col :md="9">
				<div style="height: 1px;"></div>
			</Col>
			<Col :md="6">
				<Col :md="12">
				<div  style="width: 99%;height:40px;border-radius: 8px 0px 0px 8px;background-color:#76C6F3;color:white;padding-top: 10px;font-size: 14px;">
					<center>{{$t('product.ProductDetail')}}</center>
				</div>
				</Col>
				<Col :md="12">
				<router-link :to="{path: 'riskManagement'}">
				<el-button type="primary" style="width: 99%;border-radius: 0px 8px 8px 0px;height:40px;font-size: 14px;">
					{{$t('product.RiskManagement')}}
				</el-button>
				</router-link>
				</Col>
				<!--<Col :md="8">
				<router-link :to="{path: 'dataMonitor'}">
				<el-button type="primary" style="width: 99%;height:40px;border-radius: 0px 8px 8px 0px;font-size: 14px;">
					{{$t('product.DataMonitor')}}
				</el-button>
				</router-link>
				</Col>-->
			</Col>
			<Col :md="9">
				<div style="height: 1px;"></div>
			</Col>
		</div>
		<!--
        	作者：offline
        	时间：2018-03-28
        	描述：产品详情
       -->
        <Row>
        	<Card>
        		<p slot="title">
                    <Icon type="cube" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.ProductDetail')}}
               	</p>			
				<el-tag slot="extra" color="white" style="margin-top: -5px;font-size: 14px;">
					{{$t('approved.statusLabel')}}: {{$t('product.productstatus['+productData.bsIsApprove+']')}}
				</el-tag>
		
      			<div id="product-body" v-if="supplierData != undefined">      				
					<div class="product-body-div">
						<div class="product-body-text">
							{{$t('product.code')}}：	
						</div>
						<div class="product-body-input">
							<el-input readonly v-model="productData.bsPrCode"></el-input>
						</div>
					</div>
					<div class="product-body-div">
						<div class="product-body-text">
							{{$t('product.name')}}：
						</div>
						<div class="product-body-input">
							<el-input readonly v-model="productData.bsPrName"></el-input>
						</div>
					</div>
					<div class="product-body-div">
						<div class="product-body-text">
							{{$t('product.Type')}}：	
						</div>
						<div class="product-body-input">
							<el-input readonly v-model="productData.bsCateDesc"></el-input>
						</div>
					</div>
					<div class="product-body-div">
						<div class="product-body-text">
							{{$t('product.BlueprintNo')}}：	
						</div>
						<div class="product-body-input">
							<el-input readonly v-model="productData.bsPaperNum"></el-input>
						</div>
					</div>
					<div class="product-body-div">
						<div class="product-body-text">
							{{$t('product.BlueprintVersion')}}：	
						</div>
						<div class="product-body-input">
							<el-input readonly v-model="productData.bsPaperVersion"></el-input>	
						</div>
					</div>					
					<div class="product-body-div">
						<div class="product-body-text">
							{{$t('product.SuppChieseName')}}：	
						</div>
						<div class="product-body-input">
							<el-input readonly v-model="productData.bsSuppChieseName"></el-input>
						</div>
					</div>
					<div class="product-body-div">
						<div class="product-body-text">
							{{$t('product.Contact')}}：	
						</div>
						<div class="product-body-input">
							<el-input readonly v-model="supplierData.bsSuppContactName"></el-input>
						</div>
					</div>
					<div class="product-body-div">
						<div class="product-body-text">
							{{$t('product.ContactInformation')}}：	
						</div>
						<div class="product-body-input">
							<el-input readonly v-model="supplierData.bsSuppMobile"></el-input>
						</div>
					</div>
					<div class="product-body-div">
						<div class="product-body-text">
							{{$t('product.RiskLevel')}}：
						</div>
						<div class="product-body-input">
							<el-input readonly v-model="productData.bsRiskLevel"></el-input>
						</div>
					</div>					
      			</div>	     	
      		</Card>
      	</Row>
		
		<!--
        	作者：offline
        	时间：2018-08-03
        	描述：文控管理
       -->
        <Row style="margin-top:10px;">
        	<Card>
        		<p slot="title">
                    <Icon type="cube" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.controllFile')}}
               	</p>			
		
      			<div class="ivu-fs-doc" style="overflow:hidden;">      				
					<i-col span="3">
						<comp-folder @on-selected-change="selectNode" :productInfo="productData"></comp-folder>
					</i-col>
					<i-col span="21">
						<Row>
							<i-col>
								<div class="folder-list"></div>
							</i-col>
						</Row>
						<Row>
							<i-col>
								<Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe height="560"></Table>
							</i-col>
						</Row>
						<Row type="flex" justify="end">
							<i-col>
								<Page class="paging" :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" @on-change="changePage" show-total show-elevator show-sizer></Page>
							</i-col>
						</Row>
					</i-col>
      			</div>	     	
      		</Card>
      	</Row>
   	</div>		
</template>

<script>
	//import {mapState} from 'vuex';
	import fsExtend from "./components/extend";
	import compFolder from "./components/compFolder";
	export default {
	components: {
		fsExtend,
		compFolder
	},
    data() {
      return {
   		productData:{},
		supplierData:{},
		formQuery: {
			bsFolderId: -1
		},
		tree: {
			data: []
		},
		datagrid: {
			queryParams: {
				page: 1,
				rows: 25
			},
			pagination: [25, 50, 100],
			data: { rows: [], total: 0 },
			columns: [
			{
				title: "文件名称",
				key: "bsFile",
				ellipsis: true
			},
			{
				title: "文件编号",
				key: "bsFileCode",
				width: 120
			},
			{
				title: "上传日期",
				key: "bsCreatedTime",
				width: 100,
				render: (h, params) => {
					const t = params.row.bsCreatedTime.substring(0, 10);
					return h("span", t);
				}
			},
			{
				title: "文档状态",
				key: "bsStatus",
				width: 90,
				align: "center",
				render: (h, params) => {
					const bsStatus = params.row.bsStatus;
					let statusText = "";
					if (bsStatus == "UPLOAD") {
						statusText = "已上传";
					} else if (bsStatus == "PUBLISH") {
						statusText = "发布";
					} else if (bsStatus == "NORMAL") {
						statusText = "已发布";
					} else if (bsStatus == "CLOSE") {
						statusText = "关闭";
					} else if (bsStatus == "EXCEPTION") {
						statusText = "异常";
					}
					return h("span", statusText);
				}
			},
			{
				title: "版本",
				key: "bsFileVersion",
				width: 80
			},
			{
				title: "是否受控",
				key: "bsIsControl",
				width: 100,
				render: (h, params) => {
					const bsIsControl = params.row.bsIsControl;
					let controltext = "未受控";
					if (bsIsControl === 1) {
						controltext = "已受控";
					}
					return h("span", controltext);
				}
			},
			{
				title: "操作",
				key: "action",
				width: 120,
				render: (h, params) => {
					let _this = this;
					return h(fsExtend, {
						props: { row: params.row },
						on: {
							click(type) {
								_this.getData();
							}
						}
				});
            }
        }]
    }
    } 
    },
    created(){
		this.productData=this.$store.getters.getProductData;
		this.getData();
    },
    methods:{
		getData(){
			//获取供应商信息
			this.api.supplierinfo.getlist({bsSuppCode:this.productData.bsSuppCode}).then((res) => {
				this.supplierData = res.data.rows[0];
				//this.productData=this.$store.getters.getProductData;
			});
		},
		changePage(page) {
			this.datagrid.queryParams.page = page;
			this.datagrid.data = this.getTreeData();
		},
		getTreeData() {
			let params = this.datagrid.queryParams;
			this.api.fs.document.release.getListByFolder(this.formQuery.bsFolderId, params).then(res => {
				if (res.result) {
					this.datagrid.data = res.data;
				} else {
					this.$Message.error(res.msg);
				}
			});
		},
		selectNode(arg) {
			if (!arg) return;
			this.formQuery.bsFolderId = arg.data.id;
			this.getTreeData();
		}
    }
  }
</script>

<style>
	@import '~@/styles/product.css';
</style>