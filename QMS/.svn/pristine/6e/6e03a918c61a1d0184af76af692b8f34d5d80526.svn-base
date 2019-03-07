<template>
	<div>
		<Row :style="{marginBottom: '20px'}">
			<center><h4 style="font-size: 16px;">{{$t('product.DescriptionOfRiskManagementSystem')}}</h4></center>
		</Row>
		<Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    {{$t('product.ApprovalOfMassProduction')}}
               	</p>
               	<div>
            	<el-table :data="approval" style="width: 100%;">
					<el-table-column prop="level" :label="$t('product.level')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="instructions" :label="$t('approved.explain')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="score" :label="$t('product.RiskScore')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="note" :label="$t('sample.Remark')" align="center">
		    		</el-table-column>	    		
   				</el-table>
            	</div>
           </Card>           
        </Row>
        <Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    {{$t('product.ProcessReviewResults')}}
               	</p>
               	<div>
            	<el-table :data="process" style="width: 100%;">
					<el-table-column prop="level" :label="$t('product.level')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="instructions" :label="$t('approved.explain')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="score" :label="$t('product.RiskScore')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="note" :label="$t('sample.Remark')" align="center">
		    		</el-table-column>	    		
   				</el-table>
            	</div>
           	</Card>
        </Row>
        <Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    {{$t('product.SPC')}}
               	</p>
               	<div>
            	<el-table :data="SPC" style="width: 100%;">
					<el-table-column prop="level" :label="$t('product.level')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="instructions" :label="$t('approved.explain')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="score" :label="$t('product.RiskScore')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="note" :label="$t('sample.Remark')" align="center">
		    		</el-table-column>	    		
   				</el-table>
            	</div>
           	</Card>
        </Row>
        <Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    {{$t('product.GRR')}}
               	</p>
               	<div>
            	<el-table :data="GRR" style="width: 100%;">
					<el-table-column prop="level" :label="$t('product.level')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="instructions" :label="$t('approved.explain')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="score" :label="$t('product.RiskScore')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="note" :label="$t('sample.Remark')" align="center">
		    		</el-table-column>	    		
   				</el-table>
            	</div>
           	</Card>
        </Row>
        <Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    {{$t('product.EngineeringChange')}}
               	</p>
               	<div>
            	<el-table :data="engineeringchange" style="width: 100%;">
					<el-table-column prop="level" :label="$t('product.level')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="instructions" :label="$t('approved.explain')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="score" :label="$t('product.RiskScore')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="note" :label="$t('sample.Remark')" align="center">
		    		</el-table-column>	    		
   				</el-table>
            	</div>
           	</Card>
        </Row>
        <Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    {{$t('product.TheNumberOfUnqualifiedInspection')}}
               	</p>
               	<div>
            	<el-table :data="shipment" style="width: 100%;">
					<el-table-column prop="level" :label="$t('product.level')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="instructions" :label="$t('approved.explain')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="score" :label="$t('product.RiskScore')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="note" :label="$t('sample.Remark')" align="center">
		    		</el-table-column>	    		
   				</el-table>
            	</div>
           	</Card>
        </Row>
        <Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    {{$t('product.CustomerComplaintsAndTheirClosure')}}
               	</p>
               	<div>
            	<el-table :data="customercomplaint" style="width: 100%;">
					<el-table-column prop="level" :label="$t('product.level')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="instructions" :label="$t('approved.explain')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="score" :label="$t('product.RiskScore')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="note" :label="$t('sample.Remark')" align="center">
		    		</el-table-column>	    		
   				</el-table>
            	</div>
           	</Card>
        </Row>
        <Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    PPM
               	</p>
               	<div>
            	<el-table :data="PPM" style="width: 100%;">
					<el-table-column prop="level" :label="$t('product.level')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="instructions" :label="$t('approved.explain')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="score" :label="$t('product.RiskScore')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="note" :label="$t('sample.Remark')" align="center">
		    		</el-table-column>	    		
   				</el-table>
            	</div>
           	</Card>
        </Row>
        <Row :style="{marginBottom: '20px'}">
    		<Card>
				<p slot="title">
                    {{$t('product.NewProjectRisk')}}
               	</p>
               	<div>
            	<el-table :data="NEW" style="width: 100%;">
					<el-table-column prop="level" :label="$t('product.level')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="instructions" :label="$t('approved.explain')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="score" :label="$t('product.RiskScore')" align="center">
		    		</el-table-column>
		    		<el-table-column prop="note" :label="$t('sample.Remark')" align="center">
		    		</el-table-column>	    		
   				</el-table>
            	</div>
           	</Card>
        </Row>
	</div>
</template>

<script>
export default {
    data() {
      	return {
      		approval:[
      		{level:'1',instructions:'经客户批准，公司批准PSW',score:'3',note:''},
      		{level:'2',instructions:'经客户批准，公司临时批准PSW',score:'6',note:''},
      		{level:'3',instructions:'客户条件批准，公司临时批准PSW',score:'9',note:''},
      		{level:'4',instructions:'客户临时批准，公司临时批准PSW',score:'12',note:''},
      		{level:'5',instructions:'客户没批准，公司临时批准PSW',score:'15',note:'直升高风险'},
      		],
      		process:[
      		{level:'1',instructions:'过程控制全部符合要求',score:'2',note:''},
      		{level:'2',instructions:'过程审核符合程度90%以上，没有发现关键工序不符合的情况',score:'4',note:''},
      		{level:'3',instructions:'关键制程工序符合要求，但存在作业不规范，对质量有潜在影响的情况  ',score:'6',note:''},
      		{level:'4',instructions:'过程控制部分符合要求，80%~90%符合要求，对不符合项没有改善',score:'8',note:''},
      		{level:'5',instructions:'过程控制部分符合要求，低于80%符合要求，关键项目没有不符合要求的',score:'10',note:''},
      		],
      		SPC:[
      		{level:'1',instructions:'关键工序制程能力指数 CPK ≥1.67;无CPK要求  ',score:'2',note:''},
      		{level:'2',instructions:'关键工序制程能力指数 1.67 > CPK ≥1.33',score:'4',note:''},
      		{level:'3',instructions:'关键工序制程能力指数 1.33>CPK ≥1.00',score:'6',note:''},
      		{level:'4',instructions:'关键工序制程能力指数 1.00> CPK ≥0.8',score:'8',note:''},
      		{level:'5',instructions:'关键工序制程能力指数   CPK <0.8',score:'10',note:''},
      		],
      		GRR:[
      		{level:'1',instructions:'测量设备校验周期在有效期内， 对测试产品关键尺寸和关键设备有做GRR,  并且  GRR  ≤  10%    ',score:'2',note:''},
      		{level:'2',instructions:'测量设备校验周期在有效期内， 对测试产品关键尺寸和关键设备有做GRR,  并且 30%  ≥ GRR  > 10%    ',score:'4',note:''},
      		{level:'3',instructions:'测量设备校验周期在有效期内， 对测试产品关键尺寸和关键设备有做GRR,  并且  GRR  ≥ 30%  ，有改善措施  ',score:'6',note:''},
      		{level:'4',instructions:'测量设备校验周期在有效期内， 对测试产品关键尺寸和关键设备有做GRR,  并且  GRR  ≥ 30% ，没有改善措施',score:'8',note:''},
      		{level:'5',instructions:'测量设备校验周期不在有效期内， 对测试产品关键尺寸和关键设备没有做GRR分析   ',score:'10',note:''},
      		],
      		engineeringchange:[
      		{level:'1',instructions:'按照工程更改控制程序，落实执行。有ECN，ECR，和变更追踪没有异常出现',score:'3',note:''},
      		{level:'2',instructions:'有执行变更管理评估，发行ECN通知，后续的追踪结案管理有异常出现  ',score:'6',note:''},
      		{level:'3',instructions:'有执行变更管理评估，发行ECN通知，没有进行后续的追踪结案管理',score:'9',note:''},
      		{level:'4',instructions:'有发行ECN通知，没有进行后续的追踪结案管理',score:'12',note:''},
      		{level:'5',instructions:'没有按照工程更改控制程序，落实执行，没有做变更追踪',score:'15',note:'直升高风险'},
      		],
      		shipment:[
      		{level:'1',instructions:'0次',score:'3',note:''},
      		{level:'2',instructions:'1次    ',score:'6',note:''},
      		{level:'3',instructions:'>=2次   ',score:'9',note:''},
      		{level:'4',instructions:'>=3次',score:'12',note:''},
      		{level:'5',instructions:'连续发生3次及以上 ',score:'15',note:'直升高风险'},
      		],
      		customercomplaint:[
      		{level:'1',instructions:'0次  ',score:'3',note:''},
      		{level:'2',instructions:'1次及以上 ，6个月以上客诉    ',score:'6',note:''},
      		{level:'3',instructions:'1次及以上  ，3到6个月的客诉 ，且关闭  ',score:'9',note:''},
      		{level:'4',instructions:'1次 及以上  ，3个月以内的客诉，且关闭',score:'12',note:''},
      		{level:'5',instructions:'1次及以上，且未关闭  ',score:'15',note:'直升高风险'},
      		],
      		PPM:[
      		{level:'1',instructions:'PPM低于50  ',score:'2',note:'有一项得一分'},
      		{level:'2',instructions:'PPM低于200   大于50   ',score:'4',note:'有一项得一分'},
      		{level:'3',instructions:'PPM低于500 大于200  ',score:'6',note:'有一项得一分'},
      		{level:'4',instructions:'PPM高于500 ',score:'8',note:'有一项得一分'},
      		{level:'5',instructions:'PPM高于1000  ',score:'10',note:'有一项得一分'},
      		],
      		NEW:[
      		{level:'1',instructions:'3个月内所有新项目直接全部高风险 ',score:'',note:'判断项'},
      		{level:'2',instructions:'3~6个月内，新项目有客户端数据的，根据新的数据评估 ',score:'',note:'判断项'},
      		{level:'3',instructions:'6个月内，新项目高风险的，直接高风险',score:'',note:'判断项'},
      		{level:'4',instructions:'6个月后，所有高风险全部依据系统重新启动评估',score:'',note:'判断项'},
      		],
      	}
    }
}
</script>

<style>
</style>