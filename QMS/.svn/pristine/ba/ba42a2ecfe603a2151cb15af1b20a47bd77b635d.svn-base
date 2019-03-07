<template>
    <!--
        作者：offline
        时间：2018-05-22
        描述：退货处理
    -->
    <div class="returngoods">
        <Row :style="{marginBottom: '20px'}">
            <center><h4 style="font-size: 16px;">{{$t('feedback.GenerateRefundForm')}}</h4></center>
        </Row>
        <Row :style="{marginBottom: '20px'}">
            <Card>
                <p slot="title">
                    <Icon type="social-buffer" style="color: #ff9900;font-size: 18px"></Icon>
                    &nbsp;{{$t('supplier.BasicInfo')}}
                </p>
                <div>
                    <div id="basic-body1">
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('feedback.supplier')}}：</div>
                            <div class="basic-body-input">
                                <el-autocomplete style="width: 300px;"
                                v-model="form.bsSuppCompanyName"
                                :fetch-suggestions="querySupplier"
                                @select="handleSelectSupplier">
                                </el-autocomplete>
                            </div>
                        </div>
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('feedback.DeductionsOrder')}}：</div>
                            <div class="basic-body-input">
                                <el-input v-model="form.bsRefundOrder"></el-input>
                            </div>
                        </div>
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('feedback.DateOfDeductions')}}：</div>
                            <div class="basic-body-input">
                                <DatePicker size="large" type="date" placeholder="选择日期" style="width: 300px" v-model="form.bsRefundDate"></DatePicker>
                            </div>
                        </div>
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('feedback.DateOfQualityAccident')}}：</div>
                            <div class="basic-body-input">
                                <DatePicker size="large" type="date" placeholder="选择日期" style="width: 300px" v-model="form.bsQualityAccidentDate"></DatePicker>
                            </div>
                        </div>
                    </div>
                    <div id="basic-body2">
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('feedback.notifier')}}：</div>
                            <div class="basic-body-input">
                                <el-input v-model="form.bsSuppCompanyPerson"></el-input>
                            </div>
                        </div>
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('supplier.Email')}}：</div>
                            <div class="basic-body-input">
                                <el-input v-model="form.bsSuppCompanyEmail"></el-input>
                            </div>
                        </div>
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('supplier.Mobile')}}：</div>
                            <div class="basic-body-input">
                                <el-input v-model="form.bsSuppCompanyMobile"></el-input>
                            </div>
                        </div>
                    </div>
                    <div id="basic-body3">
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('approved.Customer')}}：</div>
                            <div class="basic-body-input">
                                <el-input v-model="form.bsCusCompanyPerson"></el-input>
                            </div>
                        </div>
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('supplier.Email')}}：</div>
                            <div class="basic-body-input">
                                <el-input v-model="form.bsCusCompanyEmail"></el-input>
                            </div>
                        </div>
                        <div class="basic-body-div">
                            <div class="basic-body-text">{{$t('supplier.Mobile')}}：</div>
                            <div class="basic-body-input">
                                <el-input v-model="form.bsCusCompanyMobile"></el-input>
                            </div>
                        </div>
                    </div>
                </div>
            </Card>
        </Row>
        <Row :style="{marginBottom: '20px'}">
            <Card>
                <p slot="title">
                    <Icon type="cash" style="color: #ff9900;font-size: 18px"></Icon>
                    &nbsp;{{$t('feedback.TheRefundInformation')}}
                </p>
                <div id="refund-body">
                    <el-table :data="refunddata" style="width: 100%;">
                        <el-table-column prop="id" :label="$t('feedback.SequenceNumber')" align="center">
                        </el-table-column>
                        <el-table-column prop="bsReportNo" :label="$t('feedback.ReportNumber')">
                        </el-table-column>
                        <el-table-column prop="bsPrId" :label="$t('product.code')">
                        </el-table-column>
                        <el-table-column prop="bsNum" :label="$t('feedback.amount')">
                        </el-table-column>
                        <el-table-column prop="bsPrice" :label="$t('feedback.unitprice')">
                        </el-table-column>
                        <el-table-column prop="bsSum" :label="$t('feedback.deduction')">
                        </el-table-column>
                        <el-table-column prop="bsReason" :label="$t('feedback.DeductionsFor')">
                        </el-table-column>
                        <el-table-column :label="$t('Button.operating')">
                            <template slot-scope="scope">
                                <el-button size="mini" @click="deleterefund(scope)">
                                {{$t('Button.Delete')}}
                                </el-button>                    
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="margin-top: 20px;">
                        <center>           
                             <el-button style="padding: 5px 10px;" type="primary" icon="el-icon-plus" @click="showrefundModal()">
                                {{$t('feedback.addarefund')}}
                             </el-button> 
                        </center>
                    </div>
                    <Modal v-model="refundModal" :title="$t('feedback.TheRefundInformation')" @on-ok="addrefundok" @on-cancel="cancel" >
                        <div class="refund-body-div">
                            <div class="refund-body-text">{{$t('left-menu.Product')}}：</div>
                            <div class="refund-body-input">
                                <el-autocomplete style="width: 300px;"
                                v-model="form.bsPrName"
                                :fetch-suggestions="queryProduct"
                                @select="handleSelectProduct">
                                </el-autocomplete>
                            </div>
                        </div>
                        <div class="refund-body-div">
                            <div class="refund-body-text">{{$t('product.code')}}：</div>
                            <div class="refund-body-input">
                                <el-input v-model="form.bsPrCode"></el-input>
                            </div>
                        </div>
                        <div class="refund-body-div">
                            <div class="refund-body-text">{{$t('feedback.amount')}}：</div>
                            <div class="refund-body-input">
                                <el-input v-model="form.bsNum"></el-input>
                            </div>
                        </div>
                        <div class="refund-body-div">
                            <div class="refund-body-text">{{$t('feedback.unitprice')}}：</div>
                            <div class="refund-body-input">
                                <el-input v-model="form.bsPrice"></el-input>
                            </div>
                        </div>
                        <div class="refund-body-div">
                            <div class="refund-body-text">{{$t('feedback.deduction')}}：</div>
                            <div class="refund-body-input">
                                <el-input v-model="form.bsSum"></el-input>
                            </div>
                        </div>
                        <div id="refund-why">
                            <div class="refund-body-text">{{$t('feedback.DeductionsFor')}}：</div>
                            <div class="refund-body-input">
                                <el-input type="textarea" v-model="form.bsReason"></el-input>
                            </div>
                        </div>
                    </Modal>
                    <div style="margin-top: 20px;margin-bottom: 10px;">
                        <Alert show-icon>{{$t('feedback.DearSuppliersTheFollowingSituationsWillResultInAQualityDeduction')}}：
                        </Alert>
                        <Checkbox>{{$t('feedback.GoodsAreRejectedOrScrapped')}}</Checkbox>
                        <Checkbox>{{$t('feedback.EmergencyReplacementOfGoods')}}</Checkbox>
                        <Checkbox>{{$t('feedback.CustomerSiteSortingGoods')}}</Checkbox>
                        <Checkbox>{{$t('feedback.SeparateGoodsFromForeignWarehouses')}}</Checkbox>
                        <Checkbox>{{$t('feedback.labelerror')}}</Checkbox>
                        <Checkbox>{{$t('feedback.CustomersGiveInToDifferences')}}</Checkbox>
                        <Checkbox>{{$t('feedback.airfreight')}}</Checkbox>
                        <Checkbox>{{$t('feedback.rework')}}</Checkbox>
                        <Checkbox>{{$t('feedback.CustomerFines')}}</Checkbox>
                        <Checkbox>{{$t('feedback.GoodsReturnedToSupplier')}}</Checkbox>
                        <Checkbox>{{$t('feedback.CargoInclusion')}}</Checkbox>
                        <Checkbox>{{$t('feedback.TheCustomerIsOutOfStock')}}</Checkbox>
                        <Checkbox>{{$t('feedback.TheQuantityOfGoodsInTheBoxDoesNotTally')}}</Checkbox>
                        <Checkbox>{{$t('feedback.OtherPossibleCosts')}}</Checkbox>
                    </div>
                </div>
            </Card>
        </Row>
        <Row :style="{marginBottom: '20px'}">
            <Card>
                <p slot="title">
                    <Icon type="compose" style="color: #ff9900;font-size: 18px"></Icon>
                    &nbsp;{{$t('feedback.ApprovalInformation')}}
                </p>
                <div>
                    <el-table :data="refunddata" style="width: 100%;">
                        <el-table-column prop="" :label="$t('feedback.department')" align="center">
                        </el-table-column>
                        <el-table-column prop="" :label="$t('feedback.name')">
                        </el-table-column>
                        <el-table-column prop="" :label="$t('supplier.Position')">
                        </el-table-column>
                        <el-table-column prop="" :label="$t('feedback.date')">
                        </el-table-column>
                        <el-table-column :label="$t('Button.operating')">
                            <template slot-scope="scope">
                                <el-button size="mini">{{$t('Button.confirm')}}</el-button>
                                <el-button size="mini">{{$t('Button.Delete')}}</el-button>                   
                            </template>
                        </el-table-column>
                    </el-table>
                    <div style="margin-top: 20px;margin-bottom: 20px;">
                        <center>           
                            <el-button style="padding: 5px 10px;" type="primary" icon="el-icon-plus" @click="showApproverModal()">
                            {{$t('feedback.addapprover')}}
                            </el-button> 
                        </center>
                    </div>
                    <Modal v-model="approverModal" :title="$t('feedback.ApproverInformation')" @on-ok="ok" @on-cancel="cancel" >
                        <div class="refund-body-div">
                            <div class="refund-body-text">{{$t('feedback.approver')}}：</div>
                            <div class="refund-body-input">
                                <Select size="large" v-model="productNames" style="width: 300px;display: inline-block;" placeholder="选择">
                                    <Option v-for="item in productNames" :key="item.value" :label="item.label" :value="item.value">
                                    </Option>
                                </Select>
                            </div>
                        </div>
                        <div class="refund-body-div">
                            <div class="refund-body-text">{{$t('feedback.name')}}：</div>
                            <div class="refund-body-input">
                                <el-input></el-input>
                            </div>
                        </div>
                        <div class="refund-body-div">
                            <div class="refund-body-text">{{$t('supplier.Position')}}：</div>
                            <div class="refund-body-input">
                                <el-input></el-input>
                            </div>
                        </div>
                    </Modal>
                    <Alert show-icon>
                    {{$t('feedback.TheManagerOfTheMinistryOfCommerceIsRequiredToConfirmTheQualityDeductionOver10000RMB')}}
                    </Alert>
                </div>
            </Card>
        </Row>
        <div style="margin-bottom: 100px ;margin-top: 50px;">
            <center>
                <el-button type="primary" style="width: 10%;padding: 7px 0;" @click="submit">
                {{$t('feedback.submit')}}
                </el-button>
            </center>
        </div>
    </div>
</template>

<script>
export default {
    data() {
      return {
        refunddata: [],
        productNames:[],
        form: {bsStatus:1,bsType:2},
        refundIdStr:[],
        suppliers:[],
        products:[],
        refundModal:false,
        approverModal:false,
      }
    },
    created(){
        this.getData();
        this.getRefundData();
    }, 
    methods:{
        getData(keyWord) {
            this.api.supplierinfo.getlist({page:1,rows:100,keyWord:keyWord}).then((res) => {
                this.suppliers = res.data.rows.map(function (row) {
                    row.value = row.bsSuppChieseName;
                    return row;
                });
            });            
        },
        getRefundData(){
            this.api.feedbackRefund.getlist().then((res) => {
                this.refunddata = res.data.rows;
            });
        },
        getProductData(suppCode){
            this.api.productinfo.getlist({suppCode:suppCode}).then((res) => {
                this.products = res.data.rows.map(function (row) {
                    row.value = row.bsPrName;
                    return row;
                });
            });
        },
        queryProduct(queryString, cb) {
            var products = this.products;
            var results = queryString ? products.filter(this.nameFilter(queryString)) : products;
            cb(results);
        },
        querySupplier(queryString,cb) {
            this.api.supplierinfo.getlist({page:1,rows:100,keyWord:queryString}).then((res) => {
                this.suppliers = res.data.rows.map(function (row) {
                    row.value = row.bsSuppChieseName;
                    return row;
                });
                cb(this.suppliers);
            });
        },
        supplierFilter(queryString) {
            return (model) => {
                return (model.bsSuppChieseName.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        productFileter(queryString) {
            return (model) => {
                return (model.bsPrName.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        handleSelectSupplier(item) {
            this.form.bsSuppId = item.id;
            this.form.bsSuppCompanyCode = item.bsSuppCode;
            this.getProductData(item.bsSuppCode);
        },
        handleSelectProduct(item) {
            this.form.bsPrId = item.id;
            this.form.bsPrName = item.bsPrName;
            this.form.bsPrCode = item.bsPrCode;
        },
        showrefundModal(){
            this.refundModal=true;          
        },
        showApproverModal(){
            this.approverModal=true;
        },
        addrefundok(){                       
            this.api.feedbackRefund.add(this.form).then((res) => { 
                this.getRefundData();                            
            });                                           
        },
        deleterefund(data){
            this.api.feedbackRefund.delete({id:data.row.id}).then((res) => {
                this.getRefundData();
            });           
        },
        ok(){},
        cancel(){},
        submit(){               
            for(var i=0;i<this.refunddata.length;i++){
                var refdata = this.refunddata[i];
                this.refundIdStr.push(refdata.id);
            }         
            var refunds={
                refundIdStr:this.refundIdStr.toString(),
            };
            debugger
            // this.api.feedback.add(refunds).then((res) => {
            // });
        },
    }
  }
</script>

<style>
    @import '~@/styles/feedback.css';
</style>