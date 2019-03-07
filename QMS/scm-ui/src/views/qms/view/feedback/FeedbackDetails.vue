<template>
	<!--
    	作者：offline
    	时间：2018-03-27
    	描述：客诉详情
    -->
	<div class="feedbackdetails">
		<div style="height: 50px;">
			<Col :md="9">
				<div style="width: 36%;height: 1px;"></div>
			</Col>
			<Col :md="3">
				<div style="width: 99%;height:40px;border-radius: 8px 0px 0px 8px;background-color:#76C6F3;color:white;padding-top: 10px;font-size: 14px;">
					<center>{{$t('feedback.DetailsOfFeedback')}}</center>
				</div>
			</Col>
			<Col :md="3">
				<router-link :to="{path: 'handle',query:{feedbackId:$route.query.feedbackId}}">
					<el-button type="primary" style="width: 99%;height:40px;border-radius:0px 8px 8px 0px;font-size: 14px;">
						{{$t('feedback.CustomerComplaintHandling')}}
					</el-button>
				</router-link>
			</Col>
			<Col :md="9">
				<div style="width: 36%;height: 1px;"></div>
			</Col>
		</div>
<!--
	作者：offline
	时间：2018-03-22
	描述：客户信息
-->		
		<Row :style="{marginBottom: '20px'}">
			<Card>
				<p slot="title">
                    <Icon type="person" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('feedback.CustomerInformation')}}
               	</p>
	
				<div style="overflow: hidden;">
					<Col :md="12">
					<div class="customer-leftcol-div">
						<div class="i-div">
							<Icon type="social-buffer" style="color: orange;"></Icon>
						</div>
						<div class="text">{{$t('feedback.CusCompanyName')}}：<span class="Colors">{{form.bsCusCompanyName}}</span></div>
					</div>
					<div class="customer-leftcol-div">
						<div class="i-div">
							<Icon type="android-person" style="color: orange;"></Icon>
						</div>
						<div class="text">{{$t('feedback.ContactName')}}：<span class="Colors">{{form.bsCusCompanyPerson}}</span></div>		
					</div>
					<div class="customer-leftcol-div">
						<div class="i-div">
							<Icon type="android-mail" style="color: orange;"></Icon>
						</div>
						<div class="text">{{$t('feedback.ContactEmail')}}：{{form.bsCusCompanyEmail}}</div>		
					</div>
					</Col>
					<Col :md="12">
					<div class="customer-rightcol-div">
						<div class="i-div">
							<Icon type="android-bookmark" style="color: #0096D9;"></Icon>
						</div>
						<div class="text">{{$t('feedback.CustomerID')}}：{{form.bsCusCompanyCode}}</div>			
					</div>			
					<div class="customer-rightcol-div">
						<div class="i-div">
							<Icon type="android-phone-portrait" style="color: #0096D9;"></Icon>
						</div>
						<div class="text">{{$t('feedback.ContactNumber')}}：{{form.bsCusCompanyMobile}}</div>
					</div>		
					</Col>
				</div>
				
      		</Card>
      	</Row>
		
<!--
	作者：offline
	时间：2018-03-22
	描述：产品信息
-->		
		<Row :style="{marginBottom: '20px'}">
			<Card>
				<p slot="title">
                    <Icon type="person" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('feedback.ProductInformation')}}
               	</p>
				
				<div style="overflow: hidden;">
					<el-table :data="form.infoExtraSet" style="width: 100%;">
						<el-table-column prop="bsPrCode" :label="$t('feedback.ProductNumber')" width="100"></el-table-column>
						<el-table-column prop="bsPrName" :label="$t('feedback.ProductName')" width="160"></el-table-column>	
						<el-table-column prop="bsBatchNo" :label="$t('feedback.batchNo')" width="100"></el-table-column>
						<el-table-column prop="bsPrNum" :label="$t('feedback.amount')" width="60"></el-table-column>
						<el-table-column prop="bsReportLocation" :label="$t('feedback.place')" width="100"></el-table-column>
						<el-table-column prop="bsProductDate" :label="$t('feedback.TheDateOfProduction')" width="100"></el-table-column>
						<el-table-column prop="bsSuppCompanyCode" :label="$t('feedback.SupplierID')" width="100"></el-table-column>
						<el-table-column prop="bsSuppCompanyName" :label="$t('feedback.SupplierName')" width="160"></el-table-column>
						<el-table-column prop="bsSuppCompanyPerson" :label="$t('feedback.ContactName')" width="80"></el-table-column>
						<el-table-column prop="bsSuppCompanyEmail" :label="$t('feedback.ContactEmail')" width="120"></el-table-column>
						<el-table-column prop="bsSuppCompanyMobile" :label="$t('feedback.ContactNumber')" width="120"></el-table-column>
					</el-table>	
				</div>			
      		</Card>
      	</Row>


		<!--
		<Row :style="{marginBottom: '20px'}">
			<Card>
				<p slot="title">
                    <Icon type="person" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('feedback.ProductInformation')}}
               	</p>
				
				<div style="overflow: hidden;">
					<Col :md="12">
						<div class="customer-leftcol-div">
							<div class="i-div">
								<Icon type="android-list" style="color: orange;"></Icon>
							</div>
							<div class="text">{{$t('feedback.ProductName')}}：<span class="Colors">{{form.bsPrName}}</span></div>	
						</div>
						<div class="customer-leftcol-div">
							<div class="i-div">
								<Icon type="navicon-round" style="color: orange;"></Icon>
							</div>
							<div class="text">{{$t('feedback.amount')}}：<span class="Colors">{{form.bsPrNum}}</span></div>		
						</div>	
						<div class="customer-leftcol-div">
							<div class="i-div">
								<Icon type="android-locate" style="color: orange;"></Icon>
							</div>
							<div class="text">{{$t('feedback.origin')}}：<span class="Colors">{{form.bsProductLocation}}</span></div>	
						</div>
					</Col>
					<Col :md="12">
						<div class="customer-rightcol-div">
							<div class="i-div">
								<Icon type="ios-keypad" style="color: #0096D9;"></Icon>
							</div>
							<div class="text">{{$t('feedback.ProductNumber')}}：<span class="Colors">{{form.bsPrCode}}</span></div>
						</div>
								
						<div class="customer-rightcol-div">
							<div class="i-div">
								<Icon type="android-pin" style="color: #0096D9;"></Icon>
							</div>
							<div class="text">{{$t('feedback.place')}}：<span class="Colors">{{form.bsReportLocation}}</span></div>	
						</div>			
						
						<div class="customer-rightcol-div">
							<div class="i-div">
								<Icon type="android-time" style="color: #0096D9;"></Icon>
							</div>
							<div class="text">{{$t('feedback.TheDateOfProduction')}}：<span class="Colors">{{form.bsProductDate}}</span></div>
						</div>
					</Col>
				</div>			
      		</Card>
      	</Row>
		-->
<!--
	作者：offline
	时间：2018-03-22
	描述：供应商信息
-->		
		<!--
		<Row :style="{marginBottom: '20px'}">
			<Card>
				<p slot="title">
                    <Icon type="ios-folder" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('feedback.SupplierInformation')}}
               	</p>
		
				<div style="overflow: hidden;">
					<Col :md="12">
						<div class="customer-leftcol-div">
							<div class="i-div">
								<Icon type="social-buffer" style="color: orange;"></Icon>
							</div>
							<div class="text">{{$t('feedback.CusCompanyName')}}：<span class="Colors">{{form.bsSuppCompanyName}}</span></div>
						</div>
						<div class="customer-leftcol-div">
							<div class="i-div">
								<Icon type="android-person" style="color: orange;"></Icon>
							</div>
							<div class="text">{{$t('feedback.ContactName')}}：<span class="Colors">{{form.bsSuppCompanyPerson}}</span></div>		
						</div>
						<div class="customer-leftcol-div">
							<div class="i-div">
								<Icon type="android-mail" style="color: orange;"></Icon>
							</div>
							<div class="text">{{$t('feedback.ContactEmail')}}：<span class="Colors">{{form.bsSuppCompanyEmail}}</span></div>		
						</div>
					</Col>
						<Col :md="12">
						<div class="customer-rightcol-div">
							<div class="i-div">
								<Icon type="android-bookmark" style="color: #0096D9;"></Icon>
							</div>
							<div class="text">{{$t('feedback.CustomerID')}}：<span class="Colors">{{form.bsSuppCompanyCode}}</span></div>			
						</div>			
						<div class="customer-rightcol-div">
							<div class="i-div">
								<Icon type="android-phone-portrait" style="color: #0096D9;"></Icon>
							</div>
							<div class="text">{{$t('feedback.ContactNumber')}}：<span class="Colors">{{form.bsSuppCompanyMobile}}</span></div>
						</div>		
					</Col>
				</div>
      		</Card>
      	</Row>
		-->
<!--
	作者：offline
	时间：2018-03-22
	描述：客诉信息
-->     
		<Row :style="{marginBottom: '20px'}">
			<Card>
				<p slot="title">
                    <Icon type="chatbox-working" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('feedback.CustomerComplaintInformation')}}
               	</p>
      	
				<div id="last-div">
					<p>{{$t('feedback.ProblemDescription')}}</p>
					<el-input type="textarea" :autosize="{ minRows: 4}" v-model="form.bsProblemDes">
					</el-input>
					<!-- <p style="margin-top: 30px;">{{$t('feedback.RelatedImages')}}</p> -->

					<!-- <otherattachments :fileList="form.fileSet" class="filelist"></otherattachments> -->
					
			      	<div id="feedback-body-picture">
      					<!-- <p>{{$t('feedback.RelatedImages')}}</p> -->
      					<div class="Upload">
      						<el-upload class="upload-demo" action="" :on-preview="downloadFile" :file-list="form.fileList">
							    <div slot="tip" class="el-upload__tip"></div>
							</el-upload>
      					</div>
      				</div>
					
				</div>
      		</Card>
      	</Row>
	</div>
</template>

<script>
  export default {
    data() {
        return {
            form: {},
        }
    }, 
    created(){
        this.form.id = this.$route.query.feedbackId;       
		if(this.form.fileList == undefined) this.form.fileList = [];
        this.getData();
    },
    components: { 
    },
    methods: {
        getData() {
            this.api.feedback.getlist({id:this.form.id}).then((res)=>{
            	if(res.data.rows.length>0){
					this.form = res.data.rows[0];
					
					this.form.fileList = res.data.rows[0].fileSet.map(function (file) {
						file.name = file.fsFileBy.bsName; //显示文件名文件类型
						return file; 
					}); 
				}
            });
        },
          downloadFile(file){  
            var params = {
                fsFileId:file.fsFileId
            };        
            this.api.fileQms.getfile(params).then((link) => {
                link.click(); 
            });
        }
    }   
};
</script>

<style>
	@import '~@/styles/feedback.css';
	.Upload{width: 45%;}
	.customer-leftcol-div{
		height: 35px;
		line-height: 35px;
		margin-left: 15%;
	}
	.customer-rightcol-div{
		height: 35px;
		line-height: 35px;
	}
</style>