<template>
	<!--
    	作者：offline
    	时间：2018-03-27
    	描述：数据监控
    -->
	<div>		
		<div style="height: 50px;">
			<Col :md="8">
				<div style="height: 1px;"></div>
			</Col>
			<Col :md="8">
				<Col :md="8">
					<router-link :to="{path: 'details'}">
					<el-button type="primary" style="width: 99%;border-radius: 8px 0px 0px 8px;height:40px;font-size: 14px;">
						{{$t('product.ProductDetail')}}
					</el-button>
					</router-link>
				</Col>						
				<Col :md="8">
					<router-link :to="{path: 'riskManagement'}">
					<el-button type="primary" style="width: 99%;height:40px;border-radius: 0px 0px 0px 0px;font-size: 14px;">
						{{$t('product.RiskManagement')}}
					</el-button>
					</router-link>
				</Col>
				<Col :md="8">
					<div  style="width: 99%;height:40px;border-radius: 0px 8px 8px 0px;background-color:#76C6F3;color:white;padding-top: 10px;font-size: 14px;">
						<center>{{$t('product.DataMonitor')}}</center>
					</div>
				</Col>
			</Col>
			<Col :md="8">
				<div style="height: 1px;"></div>
			</Col>
		</div>
			
		<div style="height:45px;background-color: white;">
			<div style="float: left;margin-left: 2%;margin-top: 10px;">
				<Icon type="ios-medical" style="font-size: 20px;color: #ff9900;"></Icon>
			</div>
			<div style="float: left;font-size: 15px;margin-left: 1%;margin-top: 12px;">生产质量数据</div>
		</div>
		<div style="margin-top: 1px;background-color: white;padding-top: 30px;padding-bottom: 100px;">
			<div style="width: 40%;height: 300px;border: 1px solid #DDDDDD;margin-left: 5%;"></div>
			<div style="margin-top: 50px;margin-left: 5%;">
      			<el-button style="width: 12%;padding: 7px 0px;">来料报告.doc</el-button>
      			<el-button style="width: 12%;padding: 7px 0px;">ROSH认证资料.doc</el-button>
      			<el-button style="width: 12%;padding: 7px 0px;">制程检验报告.doc</el-button>
      		</div>	
      	</div>
	</div>
</template>

<script>	
</script>

<style>
</style>