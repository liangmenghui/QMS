<template>
	<!--
    	作者：offline
    	时间：2018-03-27
    	描述：提交客诉
    -->
	<div class="feedbackreport">
		<Row>
			<!--客户信息 -->
			<Col :md="24" :style="{marginBottom: '10px'}">
				<Card :padding="10">
					<p slot="title">
                        <Icon type="person" style="color: #ff9900;"></Icon>
                        &nbsp;{{$t('feedback.CustomerInformation')}}
                   	</p>
		
    				<div id="customer-body">
          				<div class="customer-body-div">
    						<div class="customer-body-text">{{$t('feedback.CusCompanyName')}}：</div>
                            <div class="customer-body-input">
    						    <el-input v-model="form.bsCusCompanyName" placeholder="请输入内容"></el-input>
                            </div>
    					</div>
    					<div class="customer-body-div">
    						<div class="customer-body-text">{{$t('feedback.CustomerID')}}：</div>
                            <div class="customer-body-input">
    						    <el-input v-model="form.bsCusCompanyCode" placeholder="请输入内容"></el-input>
                            </div>
    					</div>
    					<div class="customer-body-div">
    						<div class="customer-body-text">{{$t('feedback.ContactName')}}：</div>
                            <div class="customer-body-input">
    						    <el-input v-model="form.bsCusCompanyPerson" placeholder="请输入内容"></el-input>
                            </div>
    					</div>
    					<div class="customer-body-div">
    						<div class="customer-body-text">{{$t('feedback.ContactNumber')}}：</div>
                            <div class="customer-body-input">
    						    <el-input v-model="form.bsCusCompanyMobile" placeholder="请输入内容"></el-input>
                            </div>
    					</div>
    					<div class="customer-body-div">
    						<div class="customer-body-text">{{$t('feedback.ContactEmail')}}：</div>
                            <div class="customer-body-input">
    						    <el-input v-model="form.bsCusCompanyEmail" placeholder="请输入内容"></el-input>
                            </div>
    					</div>
          			</div>
      			</Card>
      		</Col>
			
			<!--产品信息-->
            <Col :md="24" :style="{marginBottom: '20px'}">
                <Card :padding="10">
                    <p slot="title">
                        <Icon type="cube" style="color: #ff9900;"></Icon>
                        &nbsp;{{$t('feedback.ProductInformation')}}
                    </p>
					<el-button slot="extra" type="primary" style="padding: 7px 20px;" @click="showAddDialog()">
						{{$t('supplier.Added')}}
					</el-button>  
                
					<div id="product-body">
						<el-table :data="feedbackExtra" style="width: 100%;">
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
							<el-table-column fixed="right" :label="$t('New-audit.Operating')" width="150">
								<template slot-scope="scope">
									<el-button @click="showEditDialog(scope.row,scope.$index)" type="primary" size="mini">{{$t('Button.Edit')}}</el-button>
									<el-button @click="deleteDialog(scope.row,scope.$index)" type="error" size="mini">{{$t('Button.Delete')}}</el-button>
								</template>
							</el-table-column>
			
						</el-table>	
					</div>
                </Card>
            </Col>
			

      	    <!--产品信息-->
			<!-- 
            <Col :md="24" :style="{marginBottom: '20px'}">
                <Card>
                    <p slot="title">
                        <Icon type="cube" style="color: #ff9900;"></Icon>
                        &nbsp;{{$t('feedback.ProductInformation')}}
                    </p>
                
                <div id="product-body">
                    <div class="product-body-div">
                        <div class="product-body-text">{{$t('feedback.ProductName')}}：</div>
                        <div class="product-body-input">
                        <el-autocomplete style="width: 300px;"
                        class="inline-input"
                        v-model="form.bsPrName"
                        :fetch-suggestions="queryProduct"
                        @select="handleSelectProduct">
                        </el-autocomplete>
                        </div>
                    </div>
                    <div class="product-body-div">
                        <div class="product-body-text">{{$t('feedback.ProductNumber')}}：</div>
                        <div class="product-body-input">
                            <el-input v-model="form.bsPrCode" placeholder="请输入内容"></el-input>
                        </div>
                    </div>
					<div class="product-body-div">
                        <div class="product-body-text">{{$t('feedback.batchNo')}}：</div>
                        <div class="product-body-input">
                            <el-input v-model="form.bsBatchNo" placeholder="请输入内容"></el-input>
                        </div>
                    </div>
                    <div class="product-body-div">
                        <div class="product-body-text">{{$t('feedback.amount')}}：</div>
                        <div class="product-body-input">
                            <el-input v-model="form.bsPrNum" placeholder="请输入内容"></el-input>
                        </div>
                    </div>
                    <div class="product-body-div">
                        <div class="product-body-text">{{$t('feedback.place')}}：</div>
                        <div class="product-body-input">
                            <el-input v-model="form.bsReportLocation" placeholder="请输入内容"></el-input>
                        </div>
                    </div>
					-->
                    <!-- <div class="product-body-div">
                        <div class="product-body-text">{{$t('feedback.origin')}}：</div>
                        <div class="product-body-input">
                            <el-input v-model="form.bsProductLocation" placeholder="请输入内容"></el-input>
                        </div>
                    </div> -->
					<!--  
                    <div class="product-body-div">
                        <div class="product-body-text">{{$t('feedback.TheDateOfProduction')}}：</div>
                        <div class="product-body-input">
                            <el-date-picker v-model="form.bsProductDate" type="date" placeholder="选择日期">
                            </el-date-picker>
                        </div>
                    </div>
                </div>
                </Card>
            </Col>
			-->
      		<!--供应商信息 -->
			<!-- 
			<Col :md="24" :style="{marginBottom: '20px'}">
				<Card>
					<p slot="title">
                        <Icon type="ios-folder" style="color: #ff9900;"></Icon>
                        &nbsp;{{$t('feedback.SupplierInformation')}}
                   	</p>
				
    				<div id="supplier-body">
          				<div class="supplier-body-div">
    						<div class="supplier-body-text">{{$t('feedback.SupplierName')}}：</div>
                            <div class="supplier-body-input">
                    		<el-input style="width: 300px;"
                      		class="inline-input"
                      		v-model="form.bsSuppCompanyName">
                      		</el-input>
                            </div>
    					</div>
    					<div class="supplier-body-div">
    						<div class="supplier-body-text">{{$t('feedback.SupplierID')}}：</div>
                            <div class="supplier-body-input">
    						    <el-input v-model="form.bsSuppCompanyCode" placeholder="请输入内容"></el-input>
                            </div>
    					</div>
    					<div class="supplier-body-div">
    						<div class="supplier-body-text">{{$t('feedback.ContactName')}}：</div>
                            <div class="supplier-body-input">
    						    <el-input v-model="form.bsSuppCompanyPerson" placeholder="请输入内容"></el-input>
                            </div>
    					</div>
    					<div class="supplier-body-div">
    						<div class="supplier-body-text">{{$t('feedback.ContactNumber')}}：</div>
                            <div class="supplier-body-input">
    						    <el-input v-model="form.bsSuppCompanyMobile" placeholder="请输入内容"></el-input>
                            </div>
    					</div>
    					<div class="supplier-body-div">
    						<div class="supplier-body-text">{{$t('feedback.ContactEmail')}}：</div>
                            <div class="supplier-body-input">
    						    <el-input v-model="form.bsSuppCompanyEmail" placeholder="请输入内容"></el-input>
                            </div>
    					</div>
          			</div>
      			</Card>
      		</Col>
			-->
      	</Row>
      	
      	<Row>
      		<!--客诉信息-->
      	
			<Card>
				<p slot="title">
                    <Icon type="chatbox-working" style="color: #ff9900;"></Icon>
                    &nbsp;{{$t('feedback.CustomerComplaintInformation')}}
               	</p>
		
 				<div id="feedback-body">
					<div>
						<p style="font-size: 14px;">{{$t('feedback.ProblemDescription')}}</p>
						<el-input type="textarea" :autosize="{ minRows: 3}" placeholder="请输入内容" style="width: 500px;" v-model="form.bsProblemDes">
						</el-input>
					</div>
      	
      				<div id="feedback-body-picture">
      					<p>{{$t('feedback.RelatedImages')}}</p>
      					<div class="Upload">
      						<el-upload 
							  class="upload-demo"
							  action=""
							  :before-upload="handleUpload"
							  :on-preview="downloadFile"							  
							  :on-remove="handleRemove"
							  :file-list="fileList"  
							  >
							  <el-button size="medium" type="success" plain>
							    {{$t('upcoming.UploadAttachment')}}
							  </el-button>
							  <div slot="tip" class="el-upload__tip"></div>
							</el-upload>
      					</div>
      				</div>
      			</div>
      		</Card>
      	</Row>
		<Modal v-model="dialog.modal_dialog" :title="$t('Button.addPpm')" @on-ok="ok" @on-cancel="cancel" >
			<el-form ref="dialog.formItem" :model="dialog.formItem" label-position="right" :rules="dialog.ruleForm" label-width="100px" class="form-modal">
				<el-form-item :label="$t('feedback.ProductName')" prop="bsPrName">
					<el-autocomplete v-model="dialog.formItem.bsPrName" 
						:fetch-suggestions="queryProduct" @select="handleSelectProduct">
					</el-autocomplete>
				</el-form-item>
				<el-form-item :label="$t('feedback.ProductNumber')" prop="bsPrCode">
					<el-input v-model="dialog.formItem.bsPrCode" readonly></el-input>
				</el-form-item>
				<el-form-item :label="$t('feedback.batchNo')" prop="bsBatchNo">
					<el-input v-model="dialog.formItem.bsBatchNo"></el-input>
				</el-form-item>
				<el-form-item :label="$t('feedback.amount')" prop="bsPrNum">
					<el-input v-model="dialog.formItem.bsPrNum"></el-input>
				</el-form-item>
				<el-form-item :label="$t('feedback.place')" prop="bsReportLocation">
					<el-input v-model="dialog.formItem.bsReportLocation"></el-input>
				</el-form-item>
				<el-form-item :label="$t('feedback.TheDateOfProduction')" prop="bsProductDate">
					<el-date-picker v-model="dialog.formItem.bsProductDate" type="date" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
				</el-form-item>
				<el-form-item :label="$t('feedback.SupplierName')" prop="bsSuppCompanyName">
					<el-input v-model="dialog.formItem.bsSuppCompanyName" readonly></el-input>
				</el-form-item>
				<el-form-item :label="$t('feedback.SupplierID')" prop="bsSuppCompanyCode">
					<el-input v-model="dialog.formItem.bsSuppCompanyCode" readonly></el-input>
				</el-form-item>
				<el-form-item :label="$t('feedback.ContactName')" prop="bsSuppCompanyPerson">
					<el-input v-model="dialog.formItem.bsSuppCompanyPerson"></el-input>
				</el-form-item>
				<el-form-item :label="$t('feedback.ContactNumber')" prop="bsSuppCompanyMobile">
					<el-input v-model="dialog.formItem.bsSuppCompanyMobile"></el-input>
				</el-form-item>
				<el-form-item :label="$t('feedback.ContactEmail')" prop="bsSuppCompanyEmail">
					<el-input v-model="dialog.formItem.bsSuppCompanyEmail"></el-input>
				</el-form-item>
			</el-form>
		</Modal>

      	<!--
          	作者：offline
          	时间：2018-03-28
          	描述：bottom
          -->
      	<div style="margin-bottom: 100px ;margin-top: 50px;">
      		<center>
				<el-button type="success" style="width: 10%;padding: 7px 0;" @click="submitFeeback(0)">{{$t('Button.save')}}</el-button>
      			<el-button type="primary" style="width: 10%;padding: 7px 0;" @click="submitFeeback(1)">{{$t('feedback.submit')}}</el-button>
      		</center>
      	</div>
	</div>
</template>

<script>
export default {
    data() {
        return {
            form: {bsStatus:0,bsType:1,bsSuppCompanyName:''},
            products: [],
            suppliers: [],
            file:{},
            fileList:[],
			feedbackExtra:[],
			dialog: {
                modal_dialog: false,
                formItem: {
                },
            },
        }
    }, 
    created(){
		this.form.id = this.$route.query.feedbackId;
        this.fileList.map(function (file) {
             file.name = file.fsFileBy.bsName; //显示文件名文件类型
             return file; 
        });
		
		if(this.form.id != undefined){
			this.getData();
		}
        this.getProductData('');
    },
    methods: {
		getData() {
            this.api.feedback.getlist({id:this.form.id}).then((res)=>{
            	if(res.data.rows.length>0){
					this.form = res.data.rows[0];
					
					this.feedbackExtra = res.data.rows[0].infoExtraSet;
					
					if(res.data.rows[0].fileSet != undefined){
						this.fileList = res.data.rows[0].fileSet.map(function (file) {
							file.name = file.fsFileBy.bsName; //显示文件名文件类型
							return file; 
						}); 
					}
					
					delete this.form.infoExtraSet;
					delete this.form.createdBy;
					delete this.form.modifiedBy;
					delete this.form.feedbackerBy;
					delete this.form.fileSet;
				}
            });
        },
        getSupplierData(suppCode) {
            this.api.supplierinfo.getlist({bsSuppCode:suppCode}).then((res) => {
                this.suppliers = res.data.rows.map(function (row) {
                    row.value = row.bsSuppChieseName;
                    return row;
                });
                if(this.suppliers.length>0) this.handleSelectSupplier(this.suppliers[0]);
            });
        },
        getProductData(keyWord){
            this.api.productinfo.getlist({keyWord:keyWord}).then((res) => {
                this.products = res.data.rows.map(function (row) {
                    row.value = row.bsPrName + '('+row.bsSuppChieseName+')';
                    return row;
                });
            });
        },
        queryProduct(queryString, cb) {
            this.api.productinfo.getlist({keyWord:queryString}).then((res) => {
                this.products = res.data.rows.map(function (row) {
                    row.value = row.bsPrName + '('+row.bsSuppChieseName+')';
                    return row;
                });
                cb(this.products);
            });
        },
        productFileter(queryString) {
            return (model) => {
                return (model.bsPrName.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        handleSelectSupplier(item) {
			this.dialog.formItem.bsSuppId = item.id;
            this.dialog.formItem.bsSuppCompanyEmail = item.bsSuppEmail;
            this.dialog.formItem.bsSuppCompanyMobile = item.bsSuppMobile;
            this.dialog.formItem.bsSuppCompanyPerson = item.bsSuppContactName;
        },
        handleSelectProduct(item) {
			this.getSupplierData(item.bsSuppCode);
			this.dialog.formItem.bsPrId = item.id;
			this.dialog.formItem.bsPrCode = item.bsPrCode;
            this.dialog.formItem.bsPrName = item.bsPrName;
			this.dialog.formItem.bsSuppCompanyCode = item.bsSuppCode;
            this.dialog.formItem.bsSuppCompanyName = item.bsSuppChieseName;
        },
        submitFeeback(bsStatus){
            if(this.fileList.length>0){
                this.form.fileIdStr = this.fileList.map(function (file) {
                    return file.id;
                }).toString();
            }
        	
			this.form.bsStatus = bsStatus;
            if(this.form.id == undefined) {
				if(this.feedbackExtra.length != undefined && this.feedbackExtra.length>0){
					this.form.feedbackInfoExtraStr = JSON.stringify(this.feedbackExtra);
					this.api.feedback.add(this.form).then((res) => {
						if(bsStatus == 1){
							this.form.id = res.data.id;
							this.$router.push({path:"/qms/feedback/details",query:{feedbackId:res.data.id}});
							this.$Message.info("提交成功");
						}else{
							this.$Message.info("保存成功");
							this.getData();
						}
					});
				}else this.$Message.error("请添加客诉产品");
            }
            else {
                this.api.feedback.edit(this.form).then((res) => {
                    if(bsStatus == 1){
						this.$router.push({path:"/qms/feedback/details",query:{feedbackId:res.data.id}});
						this.$Message.info("提交成功");
					}else{
						this.$Message.info("保存成功");
						this.getData();
					}
                });
            }
        },
		showAddDialog() {
			this.dialog.formItem = {};
            this.dialog.modal_dialog = true;    
        },
        showEditDialog(row,index) {   
            this.dialog.formItem = this.$Util.formattedParams(row);
			
            this.dialog.formItem.index=index;
            this.dialog.modal_dialog = true;
        },
        ok () {
            this.$refs["dialog.formItem"].validate((valid) => {
                if (valid) {
                    if(typeof(this.dialog.formItem.index)!=undefined && typeof(this.dialog.formItem.index)=="number") {
						this.feedbackExtra[this.dialog.formItem.index].bsBatchNo = this.dialog.formItem.bsBatchNo;
						this.feedbackExtra[this.dialog.formItem.index].bsPrNum = this.dialog.formItem.bsPrNum;
						this.feedbackExtra[this.dialog.formItem.index].bsReportLocation = this.dialog.formItem.bsReportLocation;
						this.feedbackExtra[this.dialog.formItem.index].bsProductDate = this.dialog.formItem.bsProductDate;
						this.feedbackExtra[this.dialog.formItem.index].bsSuppCompanyPerson = this.dialog.formItem.bsSuppCompanyPerson;
						this.feedbackExtra[this.dialog.formItem.index].bsSuppCompanyMobile = this.dialog.formItem.bsSuppCompanyMobile;
						this.feedbackExtra[this.dialog.formItem.index].bsSuppCompanyEmail = this.dialog.formItem.bsSuppCompanyEmail;
						
						if(this.form.id != undefined && this.dialog.formItem.id != undefined){
							this.api.feedbackInfoExtra.edit(this.dialog.formItem).then((res) => {

							});
						}
                    }else {
						if(this.form.id != undefined){
							this.dialog.formItem.bsFeedbackId = this.form.id;
							this.api.feedbackInfoExtra.add(this.dialog.formItem).then((res) => {
								this.feedbackExtra.push(res.data);
							});
						}else this.feedbackExtra.push(this.dialog.formItem);
                    }
                } else {
                    this.$Message.info(this.$t('Error.ParamsRequire'));
                }
            });
        },
        cancel () {
            
        },
		deleteDialog(row,index) {
            this.$Modal.confirm({
                title: '提示信息',
                content: '<p>是否确定删除？</p>',
                onOk: () => {
					this.feedbackExtra.splice(index, 1);
					
					if(this.form.id != undefined){
						this.api.feedbackInfoExtra.delete({id:row.id}).then((res) => {

						});
					}
                }
            });
        },
        handleUpload (file) {

            this.file = file; 
            let formData = new FormData();     
            formData.append('file', this.file);
            
            this.api.fileQms.upload(formData).then((res) => {
                if(res.result) {     	
                    var file = Object.assign(res.data,{name:this.file.name});                   
                    this.fileList.push(file);       
                }else {
                    this.$Message.error(res.msg);
                }
            });          
           
            return true;           
        },

        downloadFile(file){ 
            	
            var params = {
                fsFileId:file.id
            };
            //console.log(params)
            this.api.fileQms.getfile(params).then((link) => {    
                  	
                link.click(); 
            });
        },
        handleRemove(file, fileList) {  
           /* if(!file.id || !canUpload) return;*/
            var params = {
                    id:file.id
                };      
            this.api.approvedItemsRecordFile.delete(params).then((res)=>{         
                if(res.result) {
                    //refresh
                    this.fileList.remove();
                    this.$Message.info('删除成功');
                }else {
                    this.$Message.error(res.msg);   
                }
            });
        }
    }   
};
</script>

<style lang="less">
	@import "../public.less";
    @import '~@/styles/feedback.css';

	.Upload{margin-top: 15px;width: 45%;}
</style>