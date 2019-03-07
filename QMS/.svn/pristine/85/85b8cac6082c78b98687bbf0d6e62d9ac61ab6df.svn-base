<template>
	<!--
    	作者：offline
    	时间：2018-03-27
    	描述：供应商补充信息
    -->
	<div class="supplieradvanceinfo">
		<!--
        	作者：offline
        	时间：2018-03-28
        	描述：top
        -->
		<div style="height: 50px;">
			<Col :md="8">
				<div style="height: 1px;"></div>
			</Col>
			<Col :md="8">
				<Col :md="8">
					<router-link :to="{path: 'basicInfo'}">
					<el-button type="primary" style="width: 99%;border-radius: 8px 0px 0px 8px;height:40px;font-size: 14px;padding: 0;">
						{{$t('supplier.BasicInfo')}}
					</el-button>
					</router-link>				
				</Col>
				<Col :md="8">
					<div  style="width: 99%;height:40px;border-radius: 0px 0px 0px 0px;background-color:#76C6F3;color:white;padding-top: 10px;font-size: 14px;">
						<center>{{$t('supplier.AdvancelInfo')}}</center>
					</div>
				</Col>
				<Col :md="8">
					<router-link :to="{path: 'riskManagement'}">
					<el-button type="primary" style="width: 99%;height:40px;border-radius: 0px 8px 8px 0px;font-size: 14px;padding: 0;">
						{{$t('supplier.RiskManage')}}
					</el-button>
					</router-link>
				</Col>
			</Col>
			<Col :md="8">
				<div style="height: 1px;"></div>
			</Col>
		</div>
	
		<!--
        	作者：offline
        	时间：2018-03-28
        	描述：银行信息
       -->
        <Row>
        	<Card>
				<p slot="title">
                    <Icon type="social-usd" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('supplier.BankInformation')}}
               	</p>
			
		<div id="bank-body">
			<div class="bank-body-div">
				<div class="bank-body-text">{{$t('supplier.BankAccount')}}：</div>
				<div class="bank-body-input">
					<el-input readonly v-model="suppInfo.bsBankAccount"></el-input>
				</div>
			</div>
			<div class="bank-body-div">
				<div class="bank-body-text">{{$t('supplier.AccountName')}}：</div>
				<div class="bank-body-input">
					<el-input readonly v-model="suppInfo.bsAccountName"></el-input>
				</div>
			</div>
			<div class="bank-body-div">
				<div class="bank-body-text">{{$t('supplier.DepositBank')}}：</div>
				<div class="bank-body-input">
					<el-input readonly v-model="suppInfo.bsDepositBank"></el-input>
				</div>
			</div>
			<div class="bank-body-div">
				<div class="bank-body-text">{{$t('supplier.SubsidiaryBank')}}：</div>
				<div class="bank-body-input">
					<el-input readonly v-model="suppInfo.bsSubsidiaryBank"></el-input>
				</div>
			</div>
			<div class="bank-body-div">
				<div class="bank-body-text">{{$t('supplier.PurchaseFlag')}}：</div>
				<div class="bank-body-input">
					<el-input readonly v-model="suppInfo.bsPurchaseFlag"></el-input>
				</div>
			</div>
			<div class="bank-body-div">
				<div class="bank-body-text">{{$t('supplier.PayCondition')}}：</div>
				<div class="bank-body-input">
					<el-input readonly v-model="suppInfo.bsPayCondition"></el-input>
				</div>
			</div>
			<div class="bank-body-div">
				<div class="bank-body-text">{{$t('supplier.TermsOfPayment')}}：</div>
				<div class="bank-body-input">
					<el-input readonly></el-input>
				</div>
			</div>
			<div class="bank-body-div">
				<div class="bank-body-text">{{$t('supplier.Currency')}}：</div>
				<div class="bank-body-input">
					<el-input readonly v-model="suppInfo.bsCurrency"></el-input>
				</div>
			</div>
			<div class="bank-body-div">
				<div class="bank-body-text">{{$t('supplier.VatCode')}}：</div>
				<div class="bank-body-input">
					<el-input readonly v-model="suppInfo.bsVatCode"></el-input>
				</div>
			</div>
		</div>
			</Card>
		</Row>
	</div>
</template>

<script>
	import {mapState} from 'vuex'
	export default {
    data() {
      return {
      	suppInfo: {},

      }
    },
    created(){
        this.suppInfo = this.$store.getters.getSupplierData;
    },
    methods:{
    	Download(){  		
    	}
    }
}
</script>

<style >  
	  @import '~@/styles/supplier.css';
</style> 