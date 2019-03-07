<template>
	<div class="component">
		<Card>			
        	<p slot="title" style="display: inline;">审核状态: {{$t("approved.status["+approvedData.bsStatus+"]")}}</p>  
        	<p slot="title" style="display: inline;margin-left: 30px;">结果说明: {{approvedData.bsDesc}}</p>
			<el-button slot="extra" type="primary" icon="el-icon-plus" style="padding: 7px 20px;" @click="showApprovedDesc()" v-if="perms.edit">
				{{$t('approved.AddApprovedDesc')}}
			</el-button>
         	
			<div v-if="approvedData.resultSet.length>0">
				<el-table :data="approvedData.resultSet" style="width: 100%;">
					<el-table-column :label="$t('approved.resultLabel')">
						<template scope="scope">
							<span v-if="isCoach==undefined">{{$t('approved.itemResultDesc['+(scope.row.bsResult)+']')}}</span>
							<span v-if="isCoach!=undefined">{{$t('approved.coachResultDesc['+(scope.row.bsResult)+']')}}</span>
						</template>
					</el-table-column>
					<el-table-column prop="resultBy.bsName" :label="$t('approved.principal')">
					</el-table-column>
					<el-table-column prop="bsDesc" :label="$t('approved.item_comment')">
					</el-table-column>
					<el-table-column prop="bsCreatedTime" :label="$t('ApprovedFlow.AudiTime')">
					</el-table-column>
					<el-table-column :label="$t('Button.operating')">
						<template slot-scope="scope">
							<el-button size="small" @click="handleEdit(scope.$index, scope.row)"  :disabled="!(perms.edit && scope.row.resultBy.bsCode == user)">{{$t('Button.Edit')}}</el-button>
							<el-button size="mini" type="warning" plain @click="delpromote(scope.$index, scope.row)" :disabled="!(perms.edit && scope.row.resultBy.bsCode == user)">{{$t('Button.Delete')}}</el-button>
						</template>
					</el-table-column>
				</el-table>	
			</div>
        </Card>
		
		<Modal v-model="dialog.modal_dialog" :title="$t('approved.AddApprovedDesc')" @on-ok="addApprovedDesc">
            <div class="modeform">
                <el-form :ref="dialog.formItem" :model="dialog.formItem" :rules="dialog.ruleForm" label-width="120px">
					<el-form-item prop="id" style="display:none;">   
                        <el-input v-model="dialog.formItem.id"></el-input>                     
                    </el-form-item >
                    <el-form-item :label="$t('approved.resultLabel')" prop="bsResult">
                        <el-select v-model="dialog.formItem.bsResult" :placeholder="$t('input')" style="width:70%;">
                            <el-option :label="$t('approved.itemResultDesc[1]')" :value="1"></el-option>
                            <el-option :label="$t('approved.itemResultDesc[2]')" :value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item :label="$t('approved.item_comment')" prop="bsDesc">   
                        <el-input v-model="dialog.formItem.bsDesc" type="textarea" :placeholder="$t('input')" style="width:70%;"></el-input>                     
                    </el-form-item >
                </el-form>
            </div>
        </Modal>
   	</div>
</template>

<script>
import Cookies from 'js-cookie';
export default {
	props:['approvedData','isCoach'],
	data(){
		return{
			perms: {
                edit: false
            },
			dialog: {
                modal_dialog: false,
                formItem: {
					id: ''
                }
			},
			user: ''
		}
	},
	created(){
		this.user = Cookies.get('user');
		this.perms.edit = 
			this.approvedData.users[this.approvedData.bsStep-1] == this.user &&
			this.approvedData.bsStatus == 1;
	},
	methods: { 
		getDate(){
			this.approvedData.resultSet.sort(function (r1,r2) {
				return r1.id>r2.id;
			});
		},
		showApprovedDesc(){
			this.dialog.formItem.id = '';
            this.dialog.modal_dialog = true;
        },
		addApprovedDesc(){
			var formItem = this.dialog.formItem;
            var form = {
				id: formItem.id,
				bsItemsRecordId: this.approvedData.id,
                bsResult: formItem.bsResult,
                bsDesc: formItem.bsDesc
            };
			this.api.ApprovedItemsResult.add(form).then((res) => {
				if(res.result) {
					if(form.id != ''){
						this.approvedData.resultSet.pop();
					}
					this.approvedData.resultSet.push(res.data);
				}
			});
		},
		//显示编辑
        handleEdit(index, row){
			this.dialog.formItem = Object.assign({}, row);          
            this.dialog.modal_dialog = true;
		},
		//删除列表
        delpromote(index, row){    
			this.$Modal.confirm({
				title: '提示信息',
				content: '<p>是否确定删除？</p>',
				onOk: () => {
					this.api.ApprovedItemsResult.delete({id:row.id}).then((res)=>{
						if(res.result) {
							this.$Message.info('删除成功');
							this.approvedData.resultSet.splice(index,1);
						}else {
							this.$Message.error(res.msg);   
						}
					});
				}
			});
        },
    }
};
</script>

<style>
.component{margin-top: 30px;}
</style>