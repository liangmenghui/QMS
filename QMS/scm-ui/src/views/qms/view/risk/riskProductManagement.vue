<template>
	<!--
    	作者：offline
    	时间：2018-04-08
    	描述：产品风险信息录入
    -->
    <div class="pro-inp">
		<el-Row style="margin-bottom: 10px;">
			<el-autocomplete v-model="productinfo.bsPrName" size="medium" style="width:230px;display: inline-block;" 
				:fetch-suggestions="queryProduct" @select="handleSelectProduct" :placeholder="$t('risk.selectProduct')"></el-autocomplete>
			<el-input size="medium" v-model="productinfo.bsSuppChieseName" style="width:230px;display: inline-block;" :placeholder="$t('product.SuppChieseName')" readonly></el-input> 
			<!-- <el-button style="padding: 10px 10px;" type="primary" icon="el-icon-search" @click="handleSubmit('formQuery')">
				{{$t('Button.Inquire')}}
			</el-button>  -->           		
		</el-Row>
		
		<!--
    	作者：offline
    	时间：2018-04-25
    	描述：产品风险评分记录
    -->
    	<Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    <Icon type="social-buffer" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.ProductRiskScoreRecord')}}
               	</p>
               	<div slot="extra" @click="toInstructions('product/riskManagement/instructions')">
					<Tooltip content="点击进入风险管理说明" placement="bottom-end">                   
                        <Icon type="information-circled" style="font-size: 25px"></Icon>
                    </Tooltip>	
     			</div>
     			
      		<div style="background-color: white;">
				<el-table :data="productriskArr" style="width: 100%;">
                    <el-table-column prop="bsRiskLevel" :label="$t('product.RiskLevel')">
                    </el-table-column>	
					<el-table-column prop="bsRiskScore" :label="$t('product.riskscore')">
                    </el-table-column>	
		    		<el-table-column :label="$t('product.EvaluateTime')">
		    			<template scope="scope">
							<span>{{scope.row.bsCreatedTime.substring(0,7)}}</span>
						</template>
		    		</el-table-column>
		    		<el-table-column prop="bsMassProductScore" :label="$t('product.ApprovalOfMassProduction')">
		    		</el-table-column>
		    		<el-table-column prop="bsApprovedScore" :label="$t('product.ProcessAudit')">
		    		</el-table-column>
		    		<!-- <el-table-column prop="bsStandardScore" :label="$t('product.StandardizedOperation')">
		    		</el-table-column> -->
		    		<el-table-column prop="bsCpkScore" :label="$t('product.SPC')">
		    		</el-table-column>
		    		<el-table-column prop="bsGrrScore" :label="$t('product.TestEquipmentCalibration')">
		    		</el-table-column>
		    		<el-table-column prop="bsEngineeringScore" :label="$t('product.EngineeringChange')">
		    		</el-table-column>
		    		<el-table-column prop="bsInspectScore" :label="$t('product.TheInspectionOfShipmentIsNotQualified')">
		    		</el-table-column>
<!-- 		    		<el-table-column prop="" :label="$t('product.BusinessJudgement')">
		    		</el-table-column> -->
		    		<el-table-column prop="bsFeedbackScore" :label="$t('product.feedback')">
		    		</el-table-column>
		    		<el-table-column prop="bsPpmScore" label="PPM">
		    		</el-table-column>	    		
   				</el-table>		
      		</div>
      		</Card>
      	</Row>
		
		<Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    <Icon type="social-buffer" style="color: #ff9900;"></Icon> 
                    &nbsp;{{$t('product.RiskManagement')}}
					<!--  <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
               	</p>
				
				<div class="text item">
					<!--   量产批准情况 -->
					<el-card shadow="never" :style="{marginBottom: '10px'}" :body-style="{ padding: '10px' }">
						<el-row>
							<el-col :span="3">
								<div class="box-card-title">
									<i class="iconfont" style="color: #ff9900;">&#xe64f;</i>
									&nbsp;{{$t('product.ApprovalOfMassProduction')}}
								</div>
							</el-col>
							<el-col :span="16">
								<div class="">
									<el-select v-model="productrisk.bsMassProductType" style="width: 300px;" size="small">
										<el-option v-for="item in options1" :label="item.label" :value="item.value">
									  </el-option>
									</el-select>
								</div>
							</el-col>
						</el-row>
					</el-card>

					<!-- 过程审核 -->
					<el-card shadow="never" :style="{marginBottom: '10px'}" :body-style="{ padding: '10px' }">
						<el-row>
							<el-col :span="24">
								<div class="box-card-title">
									<i class="iconfont" style="color: #ff9900;font-size: 22px">&#xe65e;</i>
									&nbsp;{{$t('product.ProcessAudit')}}
									<span v-if="this.productinfo.bsProcessRecordId != undefined && productrisk.id != undefined">
										({{$t('New-audit.score_percent')}}：{{approvedInfo.rate}}
										&nbsp;{{$t('New-audit.approvedConclusion')}}：{{approvedInfo.standard}})
									</span>
						
									<el-select v-model="productrisk.bsApprovedType" style="width: 300px;margin-left:20px;"	size="small">
										<el-option v-for="item in optionApproved" :label="item.label" :value="item.value">
										</el-option>
									</el-select>
								</div>
							</el-col>
						</el-row>
						</el-card>
					<!-- 关键工序SPC制程能力监控 -->

					<el-card shadow="never" :style="{marginBottom: '10px'}" :body-style="{ padding: '10px' }">
						<el-row>
							<el-col :span="6">
								<div class="box-card-title">
									<i class="iconfont" style="color: #ff9900;">&#xe618;</i>
									&nbsp;{{$t('product.SPC')}}
								</div>
							</el-col>
							<el-col :span="16">
								<div class="">
									<div class="box-card-title">
										<span style="color:#409eff">CPK：</span>            
										<el-input v-model="productrisk.bsCpkValue" placeholder="请输入内容" style="width: 300px;display: inline-block;" size="small"></el-input>
									</div>
								</div>
							</el-col>
						</el-row>
					</el-card>


					<!-- 测试设备校验，测试精度符合性 -->
					<el-card shadow="never" :style="{marginBottom: '10px'}" :body-style="{ padding: '10px' }">
						<el-row>
							<el-col :span="5">
								<div class="box-card-title">
									<i class="iconfont" style="color: #ff9900; ">&#xe615;</i>
									<span> {{$t('product.TestEquipmentCalibration')}}</span>
								</div>
							</el-col>
							<el-col :span="10">
								<div class="box-card-title">
									<p style="display: inline-block;width: 130px;text-align: right; color:#409eff">{{$t('product.GRRValue')}} (%)：</p>        
									<el-input v-model="productrisk.bsGrrValue" placeholder="请输入内容" style="width: 280px;display: inline-block;" size="small"></el-input>
								</div>
							</el-col>
							<el-col :span="7" style="margin-left:20px;">
								<div class="box-card-title">                
									<div style="float: left;margin-top:10px">
										<div style="float: left;text-align: right; color: #409eff"> {{$t('product.ImprovementMeasure')}}：</div>
										<el-radio-group v-model="productrisk.bsGrrImprove">
											<el-radio :label="1">{{$t('product.have')}}</el-radio>
											<el-radio :label="0">{{$t('product.nothave')}}</el-radio>
										</el-radio-group>
									</div>
								</div>
							</el-col>
						</el-row>
					</el-card> 


					<!-- 工程更改-->
					<el-card shadow="never" :style="{marginBottom: '10px'}" :body-style="{ padding: '10px' }">
						<el-row>
							<el-col :span="3">
								<div class="box-card-title">
									<i class="iconfont" style="color: #ff9900; font-size:22px">&#xe648;</i>
									&nbsp;{{$t('product.EngineeringChange')}}
								</div>
							</el-col>
							<el-col :span="12">
								<div class="">
									<el-select v-model="productrisk.bsEngineeringType" style="width: 300px;" size="small">
										<el-option v-for="item in options3" :key="item.value" :label="item.label" :value="item.value">
										</el-option>
									</el-select>
								</div>
							</el-col>
			 
						</el-row>
					</el-card> 
					
					<!-- 新项目风险度 -->
					<el-card shadow="never" :style="{marginBottom: '10px'}" :body-style="{ padding: '10px' }">
						<el-row>
							<el-col :span="5">
								<div class="box-card-title">
									<i class="iconfont" style="color: #ff9900; font-size:21px">&#xe63f;</i>
									&nbsp;{{$t('product.newProject')}}
								</div>
							</el-col>
							<el-col :span="12">
								<div class="">
									<el-select v-model="productrisk.bsProjectType" style="width: 300px;" size="small">
										<el-option v-for="item in optionNewProject" :key="item.value" :label="item.label" :value="item.value">
										</el-option>
									</el-select>
								</div>
							</el-col>
						</el-row>
					</el-card>
				</div>
			</Card>
		</Row>
		
		<div id="bottom">
			<center>
            <el-button type="primary" @click="edit()" style="padding: 7px 20px;" v-if="productrisk.id != undefined">
            	<i class="el-icon-news"></i>&nbsp;&nbsp;{{$t('Button.SaveChanges')}}
            </el-button>
            </center>
        </div>
    </div>
</template>

<script>
	import {mapState} from 'vuex'
	export default {
    data() {
      return {
		productinfoArr:[],
		productinfo:{},
		productriskArr:[],
      	productrisk:{},
        options1: [{
          value: 1,
          label: '经客户批准,公司批准PSW'
        }, {
          value: 2,
          label: '经客户批准,公司临时批准PSW'
        }, {
          value: 3,
          label: '客户条件批准,公司临时批准PSW'
        }, {
          value: 4,
          label: '客户临时批准,公司临时批准PSW'
        }, {
          value: 5,
          label: '客户没批准,公司临时批准PSW'
        }], 
        options2: [{
          value: 1,
          label: '完整的生产作业指导书和检验指导书,与客户批准的生产工艺一致,并得到有效执行'
        }, {
          value: 2,
          label: '完整的生产作业指导书和检验指导书,但与客户批准的生产工艺不一致,产品符合要求'
        }, {
          value: 3,
          label: '不完整的生产作业指导书和检验指导书,与客户批准的生产工艺不一致,部分产品符合要求'
        }, {
          value: 4,
          label: '不完整的生产作业指导书和检验指导书,与客户批准的生产工艺不一致,未按标准化作业'
        }],
        options3: [{
          value: 1,
          label: '按照工程更改控制程序,落实执行.有ECN,ECR,和变更追踪没有异常出现'
        }, {
          value: 2,
          label: '有执行变更管理评估,发行ECN通知,后续的追踪结案管理有异常出现'
        }, {
          value: 3,
          label: '有执行变更管理评估,发行ECN通知,没有进行后续的追踪结案管理'
        }, {
          value: 4,
          label: '有发行ECN通知,没有进行后续的追踪结案管理'
        }, {
          value: 5,
          label: '没有按照工程更改控制程序,落实执行,没有做变更追踪'
        }],
		optionApproved: [{
          value: 1,
          label: '过程控制全部符合要求'
        }, {
          value: 2,
          label: '过程审核符合程度90%以上，没有发现关键工序不符合的情况'
        }, {
          value: 3,
          label: '关键制程工序符合要求，但存在作业不规范，对质量有潜在影响的情况'
        }, {
          value: 4,
          label: '过程控制部分符合要求，80%~90%符合要求，对不符合项没有改善'
        }, {
          value: 5,
          label: '过程控制部分符合要求，低于80%符合要求，关键项目没有不符合要求的'
        }],
		optionNewProject: [{
          value: 1,
          label: '3个月内所有新项目直接全部高风险'
        }, {
          value: 2,
          label: '3~6个月内，新项目有客户端数据的，根据新的数据评估'
        }, {
          value: 3,
          label: '6个月内，新项目高风险的，直接高风险'
        }, {
          value: 4,
          label: '6个月后，所有高风险全部依据系统重新启动评估'
        }],
		approvedInfo:{
			rate:'0%',
			standard:'合格'
		},
      }
    },
    created(){
		//this.productrisk = this.$store.getters.getRiskData;
		//this.getData();
    },
    methods:{
		getData(){
			this.api.productrisk.getlist({bsPrId:this.productinfo.id}).then((res) => {
				this.productriskArr = [];
				this.productriskArr.push(res.data.rows[0]);
                this.productrisk = res.data.rows[0];
            });
			
			if(this.productinfo.bsProcessRecordId != undefined){
				this.api.ApprovedItem.getrecords({bsFlowRecordId:this.productinfo.bsProcessRecordId}).then((res) => {
					var totalScores = 0;
					var getScores = 0;
					
					for (var i = 0; i < res.data.rows.length; i++) {
						var row = res.data.rows[i];

						for(var n=0;n<row.termsScoreSet.length;n++){
							var scoreInfo = row.termsScoreSet[n];
							
							if(scoreInfo.bsScore < scoreInfo.approvedTerms.bsScoreLine){ //不合格
								this.approvedInfo.standard = '不合格';
								break;
							}else this.approvedInfo.standard = '合格'; //合格
						}
						
						if(row.bsScoreNum != undefined){
							var scores = row.bsScoreNum.split('/');
							getScores += parseFloat(scores[0]);
							totalScores += parseFloat(scores[1]);
							//项目总得分小于项目分数线，不合格
							if(parseInt(scores[0]) < row.itemsBy.bsScoreLine){
								this.approvedInfo.standard = '不合格';
							}
						}
					};
					if(totalScores>0) this.approvedInfo.rate = (getScores/totalScores * 100).toFixed(2) + '%';
				});
			}
		},
        edit(){
        	var params = this.$Util.formattedParams(this.productrisk);
            this.api.productrisk.edit(params).then((res) => {
				this.$Message.info("修改完成");
				this.getData();
				/*var path = this.$route.matched[this.$route.matched.length-2].path;
				var route = {path:path,query:{id:params.bsPrId,refresh:true}};
				this.$router.replace(route);*/
            });
        },
		queryProduct(queryString, cb) {
            this.api.productinfo.getlist({keyWord:queryString}).then((res) => {
                this.productinfoArr = res.data.rows.map(function (row) {
                    row.value = row.bsPrName + '('+row.bsSuppChieseName+')';
                    return row;
                });
                cb(this.productinfoArr);
            });
        },
		handleSelectProduct(item) {
			this.productinfo = item;
			this.getData();
        },
		toInstructions(url){
      		this.$router.push({path:url,query:this.$route.query});
      	},
    }
  }
</script>

<style scoped>
@import '../../../../styles/product.css';
.box-card-title{
	font-size: 14px;
	font-family: "Microsoft YaHei";
}
.el-card__body {
	padding: 10px!important;
}
 
</style>