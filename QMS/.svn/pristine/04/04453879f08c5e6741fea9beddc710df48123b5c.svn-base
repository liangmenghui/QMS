<template>
    <div class="component">
        <center v-if="perms==undefined || perms.edit">
            <el-button v-if="approvedItemRecord.bsStatus>0&&approvedItemRecord.bsStep>0" type="primary" style="padding: 7px 20px;" @click="saveRecord(0)" v-loading.fullscreen.lock="fullscreenLoading">
                <i class="el-icon-news"></i>&nbsp;&nbsp;{{$t('Button.SaveChanges')}}
            </el-button>            
            <!-- <el-button v-if="approvedItemRecord.bsStatus>0&&approvedItemRecord.bsStep>1" type="warning" style="padding: 7px 20px;margin-left: 3%;" @click="showModel(2)">
                <i class="el-icon-warning"></i>&nbsp;&nbsp;{{$t('Button.DismissReview')}}
            </el-button>         
            <el-button  v-if="approvedItemRecord.bsStatus>0" type="danger" style="padding: 7px 20px;margin-left: 3%;" @click="showModel(3)">
                <i class="el-icon-close"></i>&nbsp;&nbsp;{{$t('Button.AuditFailed')}}
            </el-button>
            <el-button  v-if="approvedItemRecord.bsStatus>0" type="success" style="padding: 7px 20px;margin-left: 3%;" @click="showModel(1)">
                <i class="el-icon-check"></i>&nbsp;&nbsp;{{$t('Button.Approved')}}
            </el-button> -->
        </center>
        <!-- <center v-if="perms!=undefined && perms.verify">
            <el-button type="primary" style="padding: 7px 20px;" @click="saveRecord(0)">
                <i class="el-icon-news"></i>&nbsp;&nbsp;{{$t('Button.SaveChanges')}}
            </el-button>
        </center>
        <Modal v-model="showFailedModel"  @on-ok="saveRecord(3)">
            <el-form label-width="80">
                <p style="font-size: 16px;color: #F56C6C;">{{$t('approved.failed_approved')}}</p>
                <el-form-item :label="$t('approved.result_comment')">
                    <el-input v-model="flowDesc" type="textarea"></el-input>
                </el-form-item>         
                <el-form-item :label="$t('approved.item_comment')">
                    <el-input v-model="resultDesc" type="textarea"></el-input>
                </el-form-item>                                    
            </el-form>
        </Modal>
        <Modal v-model="showSuccessModel"  @on-ok="saveRecord(1)">
            <el-form label-width="80">
                <p style="font-size: 16px;color: #67C23A;">{{$t('approved.comfirm_approved')}}</p>
                <el-form-item :label="$t('approved.result_comment')">
                    <el-input v-model="flowDesc" type="textarea"></el-input>
                </el-form-item>         
                <el-form-item :label="$t('approved.item_comment')">
                    <el-input v-model="resultDesc" type="textarea"></el-input>
                </el-form-item>                                  
            </el-form>
        </Modal>
        <Modal v-model="showRejectModel"  @on-ok="saveRecord(2)">
            <el-form label-width="80">
                <p style="font-size: 16px;color: #F56C6C;">{{$t('approved.reject_approved')}}</p>
                <el-form-item :label="$t('approved.result_comment')">
                    <el-input v-model="flowDesc" type="textarea"></el-input>
                </el-form-item>         
                <el-form-item :label="$t('approved.item_comment')">
                    <el-input v-model="resultDesc" type="textarea"></el-input>
                </el-form-item>                                    
            </el-form>
        </Modal> -->
    </div>
</template>

<script>
// var XLSX = require('xlsx')  //引入xlsx
import Cookies from 'js-cookie';
export default {
    props:['approvedItemRecord','type','data','sampleRegularRecords','perms','confirmInfo'], //type: audits:条款审核 inspect:出货检验报告 quality:质量检验报告
    data() {
        return {
            showFailedModel:false,
            showSuccessModel:false,
            showRejectModel: false,
            isCoach: 1,
            resultDesc: "",
            flowDesc: "",
			fullscreenLoading: false,
        }
    },
    created(){
    },
    mounted () {

    },
    methods: {    
        /*showModel(result){
            if(result == 3) this.showFailedModel = true;
            else if(result == 2) this.showRejectModel = true;
            else this.showSuccessModel = true;
        },
        approved(result) {
            if(result != 0) {
                var params = {
                    bsItemsId: this.approvedItemRecord.itemsBy.id,
                    bsItemsRecordId: this.approvedItemRecord.id,
                    bsResult: result,
                    itemsDesc : this.flowDesc,
                    resultDesc : this.resultDesc,
					prodResult : 0
                };
				//成品检验是审核完成时
				if(this.confirmInfo != undefined && this.confirmInfo.length>0 && result == 1){
					//当前核查结果为可接受,直接跳到流程负责人
					if(this.confirmInfo[this.confirmInfo.length-1].bsResult == '1'){
						params.prodResult = 1;
					}else if(this.confirmInfo[this.confirmInfo.length-1].bsResult == '2'){ //当前核查结果为不可接受
						params.prodResult = 2;
					}
				}
				
                this.api.Audit.approved(params).then((res) => {
                    this.$Message.info("提交审核结果完成");
					this.bsStatus = result;
					
					//当前核查结果为不可接受时，关闭当前审核，重建成品检验审核
					if(params.prodResult == 2){
						this.inspect();
					}else{
						var path = this.$route.matched[this.$route.matched.length-2].path;
						var route = {path:path,query:{bsFlowRecordId:this.$route.query.bsFlowRecordId,refresh:true}};
						this.$router.replace(route);
					}
                });
            }
            else {
                var path = this.$route.matched[this.$route.matched.length-2].path;
                var route = {path:path,query:{bsFlowRecordId:this.$route.query.bsFlowRecordId,refresh:true}};
                this.$router.replace(route);
            }
        },*/
        saveRecord(result){
			this.fullscreenLoading = true;
			
            if(this.type == 'audits') {
                var list = new Array();
				var promoteList = new Array();
				var totalSocore = 0;
                var rows = this.data;
                for (var i = 0;  i < rows.length; i++) {
                    var data = {};
					var promoteData = {};
					
					if(rows[i].bsScore != -1 && rows[i].bsScore != -2){
						totalSocore = rows[i].bsScore + totalSocore;
					}
					data.bsScore = rows[i].bsScore;
                    data.bsItemsRecordId = this.approvedItemRecord.id;
                    data.bsTermsId = rows[i].bsTermsId,
                    data.bsDesc = rows[i].bsDesc;
                    data.id = rows[i].id;
                    list.push(data);
					
					//存入不符合项
					if(rows[i].bsScore != -1 && rows[i].bsScore != -2){
						//条款评分低于合格分数线时，存入不符合项
						if(rows[i].bsScore < rows[i].approvedTerms.bsScoreLine){
							promoteData.bsFlowRecordId = this.approvedItemRecord.bsFlowRecordId;
							promoteData.bsItemsRecordId = rows[i].bsItemsRecordId;
							promoteData.bsContent = rows[i].bsDesc;
							promoteData.bsTermsId = rows[i].bsTermsId;
							promoteData.bsTermsNo = rows[i].approvedTerms.bsNo;
							promoteList.push(promoteData);
						}
					}
                }
				
                //var approvedTermsScoreList = JSON.stringify(list);
                this.api.Audit.records({approvedTermsScoreStr:JSON.stringify(list)}).then((res) => {
					//最后两人保存时，存入不符合项
					//if(this.approvedItemRecord.bsStep > (this.approvedItemRecord.recorderSet.length-2)){
						if(totalSocore < this.approvedItemRecord.itemsBy.bsScoreLine){
							promoteList = new Array();
							for (var i = 0;  i < rows.length; i++) {
								var promoteData = {};
								promoteData.bsFlowRecordId = this.approvedItemRecord.bsFlowRecordId;
								promoteData.bsItemsRecordId = this.approvedItemRecord.id;
								promoteData.bsContent = rows[i].bsDesc;
								promoteData.bsTermsId = rows[i].bsTermsId;
								promoteData.bsTermsNo = rows[i].approvedTerms.bsNo;
								promoteList.push(promoteData);
							}
						}
						//保存不符合项
						this.api.promote.add({promoteStr:JSON.stringify(promoteList),bsItemsRecordId:this.approvedItemRecord.id}).then((res) => {
						
						});
					//}
				
                    //this.$emit('updateTermsRecord');
                    //this.$Message.info("保存成功");
                    //this.approved(result);
					
					this.fullscreenLoading = false;
					//返回
					var path = this.$route.matched[this.$route.matched.length-2].path;
					var route = {path:path,query:{bsFlowRecordId:this.$route.query.bsFlowRecordId,refresh:true}};
					this.$router.replace(route);
                });
            }
            else if(this.type == 'inspect') {
				//当前审核人非质检员时
				if(this.approvedItemRecord.bsStep != 1 &&this.confirmInfo != undefined && this.confirmInfo.length>0){
					//保存当前核查人的核查结果
					var inspectParams = {
						bsShipmentId: this.confirmInfo[this.confirmInfo.length-1].bsShipmentId,
						bsInspecter: this.confirmInfo[this.confirmInfo.length-1].bsInspecter,
						bsDesc: this.confirmInfo[this.confirmInfo.length-1].bsDesc,
						bsResult: this.confirmInfo[this.confirmInfo.length-1].bsResult,
						id: this.confirmInfo[this.confirmInfo.length-1].id
					};
					
					this.api.shipmentInspect.editRecord(inspectParams).then((res) => {
						//this.$Message.info("提交完成");
					});
				}
				
                var params = Object.assign({},this.data);
                params = this.$Util.formattedParams(params);
                this.api.shipmentInspect.edit(params).then((res) => {
					var shipmentInfo = res.data;

					//this.$Message.info("保存成功");
                    //this.approved(result);
					
					this.fullscreenLoading = false;
					//返回
					var path = this.$route.matched[this.$route.matched.length-2].path;
					var route = {path:path,query:{bsFlowRecordId:this.$route.query.bsFlowRecordId,refresh:true}};
					this.$router.replace(route);
                });
                
                if(this.sampleRegularRecords!=undefined){
                    var params = {bsShipmentInspectId:params.id};
                    var records = [];
                    for (var i = 0; i < this.sampleRegularRecords.length; i++) {
                        var regular = this.sampleRegularRecords[i];
                        for (var j = 0; j < regular.records.length ; j++) {
							//regular.records[j].bsIsResult = this.sampleRegularRecords[0].records[j].bsIsResult;
                            var record = regular.records[j];
                            records.push(this.$Util.formattedParams(record));
                        }
                    }
                    params.sampleRegularRecordStr = JSON.stringify(records);
                    this.api.sampleRegularRecord.add(params).then((res) => {
                        //this.$emit('saved');
                    });
                }
            }
            else {
                //this.approved(result);
            }
        },
		/*inspect(){
			var bsDesc = '此次成品检验不可接受，关闭并重新进行成品检验';
			this.api.approvedFlowRecord.close({id:this.approvedItemRecord.bsFlowRecordId,bsDesc:bsDesc}).then((res) => {
				this.api.orderInspect.addAgain({id:this.data.bsOrderId}).then((res) => {
					this.$Message.info("已关闭此次成品检验，并重新新建了成品检验");
					var path = this.$route.matched[this.$route.matched.length-2].path;
					var route = {path:path,query:{bsFlowRecordId:this.$route.query.bsFlowRecordId,refresh:true}};
					this.$router.replace(route);
				});
			});
		},*/
    }
 } 
</script>