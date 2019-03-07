<template>
	<!--
    	作者：offline
    	时间：2018-04-08
    	描述：产品风险信息录入
    -->
    <div class="pro-inp">
    	<Row :gutter="10">
    	<!--
        	作者：offline
        	时间：2018-04-08
        	描述：量产批准情况
        -->
        <Col :md="24" :lg="12" :style="{marginBottom: '20px'}">
        	<Card>
				<p slot="title">
                    <Icon type="edit" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.ApprovalOfMassProduction')}}
               	</p>
		
			<div class="body">
				<el-select v-model="productrisk.bsMassProductType" style="width: 300px;margin-left: 130px;">
    				<el-option v-for="item in options1" :label="item.label" :value="item.value">
    				</el-option>
  				</el-select>
			</div>
			</Card>
		</Col>
		
		<!--
        	作者：offline
        	时间：2018-04-08
        	描述：过程审核
        -->
        <Col :md="24" :lg="12" :style="{marginBottom: '20px'}">
        	<Card>
				<p slot="title">
                    <Icon type="edit" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.ProcessAudit')}}
					<span v-if="this.productrisk.bsFlowRecordId != undefined">
						({{$t('New-audit.score_percent')}}：{{approvedInfo.rate}}
						&nbsp;{{$t('New-audit.approvedConclusion')}}：{{approvedInfo.standard}})
					</span>
               	</p>
        	
			<div class="body">
				<el-select v-model="productrisk.bsApprovedType" style="width: 300px;margin-left: 130px;">
					<el-option v-for="item in optionApproved" :label="item.label" :value="item.value">
					</el-option>
  				</el-select>
			</div>
			</Card>
		</Col>
		</Row>
		
		<Row :gutter="10">
		<!--
        	作者：offline
        	时间：2018-04-08
        	描述：关键工序SPC制程能力监控
        -->
        <Col :md="24" :lg="12" :style="{marginBottom: '20px'}">
        	<Card>
				<p slot="title">
                    <Icon type="edit" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.SPC')}}
               	</p>
        	
			<div class="body">
				<div class="body-text">CPK：</div>
				<div class="body-model">
					<el-input v-model="productrisk.bsCpkValue" placeholder="请输入内容" style="width: 300px;"></el-input>
				</div>
			</div>
			</Card>
		</Col>
		
		<!--
        	作者：offline
        	时间：2018-04-08
        	描述：测试设备校验，测试精度的符合性
        -->
        <Col :md="24" :lg="12" :style="{marginBottom: '20px'}">
        	<Card>
				<p slot="title">
                    <Icon type="edit" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.TestEquipmentCalibration')}}
               	</p>
        	
			<div class="body">
				<div style="height: 50px;float: left;">
					<p style="display: inline-block;width: 130px;text-align: right;">{{$t('product.GRRValue')}} (%)：</p>				
					<el-input v-model="productrisk.bsGrrValue" placeholder="请输入内容" style="width: 80px;display: inline-block;"></el-input>
				</div>
				<div style="height: 50px;float: left;padding-top: 10px;margin-left: 10px;">
					<div style="float: left;width: 130px;text-align: right;">{{$t('product.ImprovementMeasure')}}：</div>
					<div style="float: left;margin-left: 20px;">
						<el-radio-group v-model="productrisk.bsGrrImprove">
							<el-radio :label="1">{{$t('product.have')}}</el-radio>
  							<el-radio :label="0">{{$t('product.nothave')}}</el-radio>
  						</el-radio-group>
					</div>
				</div>
			</div>
			</Card>
		</Col>
		</Row>

		<Row :gutter="10">
		<!--
        	作者：offline
        	时间：2018-04-08
        	描述：工程更改
        -->
		<Col :md="24" :lg="12" :style="{marginBottom: '20px'}">
			<Card>
				<p slot="title">
                    <Icon type="edit" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.EngineeringChange')}}
               	</p>
        
			<div class="body">
				<el-select v-model="productrisk.bsEngineeringType" style="width: 300px;margin-left: 130px;">
    				<el-option v-for="item in options3" :key="item.value" :label="item.label" :value="item.value">
    				</el-option>
  				</el-select>
			</div>
			</Card>
		</Col>
		
		<!--
        	作者：offline
        	时间：2018-04-08
        	描述：验货情况
        -->
        <Col :md="24" :lg="12" :style="{marginBottom: '20px'}">
        	<Card>
				<p slot="title">
                    <Icon type="edit" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.InspectionSituation')}}
               	</p>
        	
			<div class="body">
				<div class="body-text">{{$t('product.TheInspectionOfShipmentIsNotQualified')}}：</div>
				<div class="body-model">
					<el-input v-model="productrisk.bsInspectValue" placeholder="请输入内容" style="width: 300px;" readonly></el-input>
				</div>
			</div>
			</Card>
		</Col>
		</Row>
		
		<Row :gutter="10">
		<!--
        	作者：offline
        	时间：2018-04-08
        	描述：客诉及关闭
        -->
        <Col :md="24" :lg="12" :style="{marginBottom: '20px'}">
        	<Card>
				<p slot="title">
                    <Icon type="edit" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.feedback')}}
               	</p>
        	
			<div class="body">
				<el-select v-model="productrisk.bsFeedbackType" style="width: 300px;margin-left: 130px;" disabled>
    				<el-option v-for="item in optionFeedback" :key="item.value" :label="item.label" :value="item.value">
    				</el-option>
  				</el-select>
			</div>
			</Card>
		</Col>
		
		<!--
        	作者：offline
        	时间：2018-04-08
        	描述：PPM
        -->
        <Col :md="24" :lg="12" :style="{marginBottom: '20px'}">
        	<Card>
				<p slot="title">
                    <Icon type="edit" style="color: #ff9900;"></Icon>
                    &nbsp;PPM
               	</p>
        	
			<div class="body">
				<div class="body-text">PPM：</div>
				<div class="body-model">
					<el-input v-model="productrisk.bsPpmValue" placeholder="请输入内容" style="width: 300px;" readonly></el-input>
				</div>
			</div>
			</Card>
		</Col>
		</Row>
		
		<Row :gutter="10">		
		<!--
        	作者：offline
        	时间：2018-04-08
        	描述：新项目风险度
        -->
        <Col :md="24" :style="{marginBottom: '20px'}">
        	<Card :style="{marginBottom: '20px'}">
				<p slot="title">
                    <Icon type="edit" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('product.newProject')}}
               	</p>
        	
			<div class="body">
				<el-select v-model="productrisk.bsProjectType" style="width: 700px;margin-left: 130px;">
    				<el-option v-for="item in optionNewProject" :key="item.value" :label="item.label" :value="item.value">
    				</el-option>
  				</el-select>
			</div>
			</Card>
		</Col>
		</Row>
		
		<div id="bottom">
			<center>
            <el-button type="primary" @click="edit()" style="padding: 7px 20px;">
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
		optionFeedback: [{
          value: 1,
          label: '0次'
        }, {
          value: 2,
          label: '1次及以上 ，6个月以上客诉'
        }, {
          value: 3,
          label: '1次及以上 ，3到6个月的客诉 ，且关闭'
        }, {
          value: 4,
          label: '1次 及以上 ，3个月以内的客诉，且关闭'
        }, {
          value: 5,
          label: '1次及以上，且未关闭'
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
		this.productrisk = this.$store.getters.getRiskData;
		this.getData();
    },
    methods:{
		getData(){
			if(this.productrisk.bsFlowRecordId != undefined){
				this.api.ApprovedItem.getrecords({bsFlowRecordId:this.productrisk.bsFlowRecordId}).then((res) => {
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
				var path = this.$route.matched[this.$route.matched.length-2].path;
				var route = {path:path,query:{id:params.bsPrId,refresh:true}};
				this.$router.replace(route);
            });
        },	
    }
  }
</script>

<style>
  @import '../../../../styles/product.css';
</style>