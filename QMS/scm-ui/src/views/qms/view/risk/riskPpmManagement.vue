<template>
	<!--
    	作者：offline
    	时间：2018-04-13
    	描述：PPM值录入
    -->
    <div class="ApprovedFlowManagement" v-if="!showRooterView">
            <div>
                <el-Row style="margin-bottom: 10px;">                                 		
                  	<el-input size="medium" style="width:200px;display: inline-block;" v-model="datagrid.queryParams.keyWord" :placeholder="$t('Button.Keyword-search')"></el-input>   
                	<el-date-picker v-model="datagrid.queryParams.bsYear" type="year" style="width:100px;" format="yyyy" value-format="yyyy"></el-date-picker>
					<el-button style="padding: 10px 10px;" type="primary" icon="el-icon-search" @click="handleSubmit('formQuery')">
                		{{$t('Button.Inquire')}}
                	</el-button>
					<el-button style="padding: 10px 10px;" type="primary" icon="el-icon-plus" v-perm-add="" @click="showAddDialog()">
						{{$t('Button.addPpm')}}
					</el-button>             		
                </el-Row>
                <el-Row>
                    <el-col :span="24">
                        <Table :data="datagrid.data.rows" :columns="datagrid.columns">                  
                        </Table>
                        <div style="margin: 10px;overflow: hidden">
                            <div style="float: right;">
                                <Page :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" @on-change="changePage" show-total show-elevator show-sizer></Page>
                            </div>
                        </div>
                    </el-col>
                </el-Row>
				<Modal v-model="dialog.modal_dialog" :title="$t('Button.addPpm')" @on-ok="ok" @on-cancel="cancel" width="500">
					<el-form ref="dialog.formItem" :model="dialog.formItem" label-position="right" :rules="dialog.ruleForm" label-width="100px" class="form-modal">
						<el-form-item :label="$t('product.name')" prop="bsPrName">
							<el-autocomplete v-model="dialog.formItem.bsPrName" 
								:fetch-suggestions="queryProduct" @select="handleSelectProduct" readonly style="width:100%;">
							</el-autocomplete>
						</el-form-item>
						
						<el-form-item :label="$t('product.SuppChieseName')" prop="bsSuppChieseName">
							<el-input v-model="dialog.formItem.bsSuppChieseName" readonly></el-input>
						</el-form-item>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'1'+$t('risk.monthCus')" prop="bsCusMonthResult1" v-if="nowMonth == 1 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult1" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'1'+$t('risk.monthUnind')" prop="bsMonthResult1" v-if="nowMonth == 1 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult1" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'2'+$t('risk.monthCus')" prop="bsCusMonthResult2" v-if="nowMonth == 2 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult2" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'2'+$t('risk.monthUnind')" prop="bsMonthResult2" v-if="nowMonth == 2 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult2" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'3'+$t('risk.monthCus')" prop="bsCusMonthResult3" v-if="nowMonth == 3 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult3" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'3'+$t('risk.monthUnind')" prop="bsMonthResult3" v-if="nowMonth == 3 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult3" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'4'+$t('risk.monthCus')" prop="bsCusMonthResult4" v-if="nowMonth == 4 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult4" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'4'+$t('risk.monthUnind')" prop="bsMonthResult4" v-if="nowMonth == 4 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult4" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'5'+$t('risk.monthCus')" prop="bsCusMonthResult5" v-if="nowMonth == 5 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult5" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'5'+$t('risk.monthUnind')" prop="bsMonthResult5" v-if="nowMonth == 5 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult5" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'6'+$t('risk.monthCus')" prop="bsCusMonthResult6" v-if="nowMonth == 6 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult6" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'6'+$t('risk.monthUnind')" prop="bsMonthResult6" v-if="nowMonth == 6 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult6" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'7'+$t('risk.monthCus')" prop="bsCusMonthResult7" v-if="nowMonth == 7 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult7" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'7'+$t('risk.monthUnind')" prop="bsMonthResult7" v-if="nowMonth == 7 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult7" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'8'+$t('risk.monthCus')" prop="bsCusMonthResult8" v-if="nowMonth == 8 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult8" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'8'+$t('risk.monthUnind')" prop="bsMonthResult8" v-if="nowMonth == 8 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult8" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'9'+$t('risk.monthCus')" prop="bsCusMonthResult9" v-if="nowMonth == 9 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult9" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'9'+$t('risk.monthUnind')" prop="bsMonthResult9" v-if="nowMonth == 9 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult9" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'10'+$t('risk.monthCus')" prop="bsCusMonthResult10" v-if="nowMonth == 10 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult10" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'10'+$t('risk.monthUnind')" prop="bsMonthResult10" v-if="nowMonth == 10 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult10" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'11'+$t('risk.monthCus')" prop="bsCusMonthResult11" v-if="nowMonth == 11 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult11" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'11'+$t('risk.monthUnind')" prop="bsMonthResult11" v-if="nowMonth == 11 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult11" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
						
						<el-row :gutter="30">
							<el-col :span="12">
								<el-form-item :label="'12'+$t('risk.monthCus')" prop="bsCusMonthResult12" v-if="nowMonth == 12 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsCusMonthResult12" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
							<el-col :span="12">
								<el-form-item :label="'12'+$t('risk.monthUnind')" prop="bsMonthResult12" v-if="nowMonth == 12 || isCheck">
									<el-input type="number" v-model="dialog.formItem.bsMonthResult12" v-bind:readonly="isCheck"></el-input>
								</el-form-item>
							</el-col>
						</el-row>
					</el-form>
                </Modal>
            </div>
    </div>
    <div v-else-if="showRooterView">
        <router-view></router-view>
    </div>
</template>
<script>
export default {
    data() {
        return {
            selecedtUser:{},
            showRooterView: false,
			isReadOnly:false,
			isCheck:false,
            dialog: {
                modal_dialog: false,
                formItem: {
                },
                /*ruleForm: {
                    bsPrName: [
                        { required: true, message: '请选择产品', trigger: 'blur' }
                    ],
                }*/
            },
            datagrid: {
                queryParams:{
                    page:1,
                    rows:10,
                    pkParent:-1,
					bsYear: new Date().getFullYear()+'',
                },
                pagination: [10, 25, 50, 100],
                data: {rows:[],total:0},
                columns: [
                    {
                        title:this.$t('product.name'),
                        key: 'bsPrName',
						fixed: 'left',
						width:200,
						render: (h, params) => {
                            return h('span', params.row.productBy.bsPrName)
                        }
                    },                   
                    {
                        title: this.$t('product.SuppChieseName'),
                        key: 'bsSuppChieseName', 
						fixed: 'left',
						width:200,
						render: (h, params) => {
                            return h('span', params.row.productBy.bsSuppChieseName)
                        }
                    },
                    {
                        title: '1'+this.$t('risk.month'),
                        key: 'monthResult1',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult1+params.row.bsCusMonthResult1)
                        }
                    },
					{
                        title: '2'+this.$t('risk.month'),
                        key: 'monthResult2',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult2+params.row.bsCusMonthResult2)
                        }
                    },
					{
                        title: '3'+this.$t('risk.month'),
                        key: 'monthResult3',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult3+params.row.bsCusMonthResult3)
                        }
                    },
					{
                        title: '4'+this.$t('risk.month'),
                        key: 'monthResult4',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult4+params.row.bsCusMonthResult4)
                        }
                    },
					{
                        title: '5'+this.$t('risk.month'),
                        key: 'monthResult5',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult5+params.row.bsCusMonthResult5)
                        }
                    },
					{
                        title: '6'+this.$t('risk.month'),
                        key: 'monthResult6',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult6+params.row.bsCusMonthResult6)
                        }
                    },
					{
                        title: '7'+this.$t('risk.month'),
                        key: 'monthResult7',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult7+params.row.bsCusMonthResult7)
                        }
                    },
					{
                        title: '8'+this.$t('risk.month'),
                        key: 'monthResult8',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult8+params.row.bsCusMonthResult8)
                        }
                    },
					{
                        title: '9'+this.$t('risk.month'),
                        key: 'monthResult9',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult9+params.row.bsCusMonthResult9)
                        }
                    },
					{
                        title: '10'+this.$t('risk.month'),
                        key: 'monthResult10',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult10+params.row.bsCusMonthResult10)
                        }
                    },
					{
                        title: '11'+this.$t('risk.month'),
                        key: 'monthResult11',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult11+params.row.bsCusMonthResult11)
                        }
                    },
					{
                        title: '12'+this.$t('risk.month'),
                        key: 'monthResult12',
						width: 70,
						render: (h, params) => {
                            return h('span', params.row.bsMonthResult12+params.row.bsCusMonthResult12)
                        }
                    },
                    {
                        title: this.$t('ApprovedFlow.Operating'),
                        key: 'action',
						fixed: 'right',
						width: 128,
                        render: (h, params) => {
                            let ary = [];
							{
                                ary.push(h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small',
                                        disabled: !this.$Util.hasPerm('QUERY')
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.showCheckDialog(params)
                                        }
                                    }
                                }, this.$t('Button.View')));
                            }
                            {
                                ary.push(h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small',
                                        disabled: !this.$Util.hasPerm('EDIT')
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.showEditDialog(params)
                                        }
                                    }
                                }, this.$t('Button.Edit')));
                            }
                            {
                                ary.push(h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small',
                                        disabled: !this.$Util.hasPerm('DELETE')
                                    },
                                    on: {
                                        click: () => {
                                            this.delete(params)
                                        }
                                    }
                                }, this.$t('Button.Delete')));
                            }
                            return h('div', ary);
                        }
                    }
                ]
            },
            users :[],
			productInfo:{}, 
			nowMonth: new Date().getMonth()+1,
        }
    },
    created(){
        this.getData();
        this.showRooterView = this.$route.matched.length>2;
    },
    beforeUpdate:function(){
        this.showRooterView = this.$route.matched.length>2;
    },
    watch: {
        '$route' (to, from) {
            this.showRooterView = to.matched.length>2;
        }
    },
    methods: {
        handleSubmit(name) {         
            this.getData();
        },
        getData() {
            this.api.productPpm.getlist(this.datagrid.queryParams).then((res) => {
                this.datagrid.data = res.data;
            });

			/*this.api.admin.user.getlist().then((res)=>{               
                this.users = res.data.rows;
            });*/
        },
        reloadData() {
            this.getData();
        },
        changePage (page) {
            this.datagrid.queryParams.page = page;
            this.datagrid.data = this.getData();
        },
		queryProduct(queryString, cb) {
            this.api.productinfo.getlist({keyWord:queryString}).then((res) => {
                var products = res.data.rows.map(function (row) {
                    row.value = row.bsPrName + '('+row.bsSuppChieseName+')';
                    return row;
                });
                cb(products);
            });
        },
		handleSelectProduct(item) {
            this.dialog.formItem.bsPrId = item.id;
            this.dialog.formItem.bsPrName = item.bsPrName;
            this.dialog.formItem.bsSuppChieseName = item.bsSuppChieseName;
        },
        delete(params) {
            this.$Modal.confirm({
                title: '提示信息',
                content: '<p>是否确定删除？</p>',
                onOk: () => {
                    this.api.productPpm.delete({id:params.row.id}).then((res)=>{
                        if(res.result) {
                            this.reloadData();
                            this.$Modal.remove();
                            this.$Message.info('删除成功');
                        }else {
                            this.$Message.error(res.msg);   
                        }
                    });
                }
            });
        },
		showCheckDialog(params){
			this.dialog.formItem = this.$Util.formattedParams(params.row);
			//this.dialog.formItem.bsPrId = this.dialog.formItem.productBy.id;
			this.dialog.formItem.bsPrName = this.dialog.formItem.productBy.bsPrName;
			this.dialog.formItem.bsSuppChieseName = this.dialog.formItem.productBy.bsSuppChieseName;
			
            this.dialog.formItem.index=params.index;
            delete this.dialog.formItem.productBy;
            this.dialog.modal_dialog = true;
			
			this.isCheck = true;
		},
        showAddDialog() {
			this.dialog.formItem = {};
            this.dialog.modal_dialog = true;    
			
			this.isCheck = false;
        },
        showEditDialog(params) {   
            this.dialog.formItem = this.$Util.formattedParams(params.row);
			//this.dialog.formItem.bsPrId = this.dialog.formItem.productBy.id;
			this.dialog.formItem.bsPrName = this.dialog.formItem.productBy.bsPrName;
			this.dialog.formItem.bsSuppChieseName = this.dialog.formItem.productBy.bsSuppChieseName;
			
            this.dialog.formItem.index=params.index;
            delete this.dialog.formItem.productBy;
            this.dialog.modal_dialog = true;
			
			this.isCheck = false;
        },
        ok () {
            //this.dialog.formItem.bsApprovederId = this.selecedtUser.id;
            this.$refs["dialog.formItem"].validate((valid) => {
                if (valid) {
					if(!this.isCheck){
						if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {
							this.api.productPpm.edit(this.dialog.formItem).then((res) => {
								if(res.result) {
									this.getData();
								}else {
									this.$Message.error(res.msg);
								}
							});
						}else {
							this.api.productPpm.add(this.dialog.formItem).then((res) => {
								if(res.result) {
									this.getData();
								}else {
									this.$Message.error(res.msg);
								}
							});
						}
					}
                } else {
                    this.$Message.info(this.$t('Error.ParamsRequire'));
                }
            });
        },
        cancel () {
            
        },
    }
}
</script>
<style>

.searchbtn .el-button{border-radius: 0px;padding: 11px 14px;}
.toolbar .el-form--inline .el-form-item{vertical-align: baseline;}
.tip{margin-left:70px; color:#777;}
</style>