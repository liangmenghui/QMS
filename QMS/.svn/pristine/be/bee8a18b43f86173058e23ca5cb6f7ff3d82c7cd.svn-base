<template>
    <div>
        <Row>
            <i-col>
                <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                    <Form-item prop="bsCode">
                        <Input type="text" v-model="formQuery.bsCode" placeholder="角色编码" />
                    </Form-item>
                    <Form-item prop="bsName">
                        <Input type="text" v-model="formQuery.bsName" placeholder="角色名称" />
                    </Form-item>
                    <Form-item v-if="menuData.perms.QUERY">
                        <Button type="primary" @click="handleSubmit('formQuery')">查 询</Button>
                    </Form-item>
                    <Form-item v-if="menuData.perms.ADD">
                        <Button type="primary" @click="showAddDialog()">新 增</Button>
                    </Form-item>
                </Form>
            </i-col>
        </Row>
        <Row>
            <i-col span="24">
                <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe height="500"></Table>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" @on-change="changePage" show-total show-elevator show-sizer></Page>
                    </div>
                </div>
            </i-col>
        </Row>
        <Modal v-model="dialog.modal_dialog" title="" @on-ok="ok" @on-cancel="cancel" >
            <p>
                <Form :model="dialog.formItem" :ref="dialog.ruleForm" :rules="dialog.ruleForm" :label-width="80">
                    <span style="display:none;">
                        <Input v-model="dialog.formItem.id"></Input>
                        <Input v-model="dialog.formItem.pkGroup" value="-1"></Input>
                        <Input v-model="dialog.formItem.pkOrg" value="-1"></Input>
                    </span>
                    <Form-item label="权限编码" prop="bsCode">
                        <Input v-model="dialog.formItem.bsCode" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="权限名称" prop="bsName">
                        <Input v-model="dialog.formItem.bsName" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="备注">
                        <Input v-model="dialog.formItem.bsComment" type="textarea" placeholder="请输入..."></Input>
                    </Form-item>
                </Form>
            </p>
        </Modal>
    </div>
</template>
<script>
import {mapState} from 'vuex'
import custDialog from '../../../components/business-component/custDialog.vue';
export default {
    components: {
        custDialog
    },
    data() {
        return {
            formQuery: {
                bsCode: '',
                bsName: ''
            },
            extProps: {
                modal_dialog: false
            },
            dialog: {
                modal_dialog: false,
                formItem: {
                },
                ruleForm: {
                    bsCode: [
                        { required: true, message: '请填写编码', trigger: 'blur' }
                    ],
                    bsName: [
                        { required: true, message: '请填写名称', trigger: 'blur' }
                    ]
                }
            },
            datagrid: {
                queryParams:{
                    page:1,
                    rows:25,
                    pkParent:-1
                },
                pagination: [25, 50, 100],
                data: {rows:[],total:0},
                columns: [
                    {
                        title: '权限编码',
                        key: 'bsCode'
                    },
                    {
                        title: '权限名称',
                        key: 'bsName'
                    },
                    {
                        title: '删除标识',
                        key: 'bsIsDel',
                        render: (h, params) => {
                            return h('span', {
                                    style: {
                                        fontColor: 'red'
                                    }
                                }, params.row.bsIsDel==0?'-':'已删除');
                        }
                    },
                    {
                        title: '创建时间',
                        key: 'bsCreatedTime'
                    },
                    {
                        title: '更新时间',
                        key: 'bsModifiedTime'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        render: (h, params) => {
                            let ary = [];
                            if(this.menuData.perms.EDIT) {
                                ary.push(h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.showEditDialog(params)
                                        }
                                    }
                                }, '编辑'));
                            }
                            if(this.menuData.perms.DELETE) {
                                ary.push(h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.delete(params)
                                        }
                                    }
                                }, '删除'))
                            }
                            if(this.menuData.perms.CUSTOMFIELD) {
                                ary.push(h(custDialog, {
                                    props:{
                                        type: 'primary',
                                        size: 'small',
                                        name: '客制化',
                                        ref:'custDialog',
                                        catelog: 'SysPermBo',
                                        rid: params.row.id
                                    },
                                    on: {
                                        click: () => {
                                            this.$refs.custDialog.showDialog();
                                        }
                                    }
                                }));
                            }
                            return h('div', ary);
                        }
                    }
                ]
            },
            
            // add_display: false,
            // edit_display: false,
            // delete_display: false
        }
    },
    created(){
        // this.getTree();
        // alert(this.menuData.bsUrl);
        // alert(this.menuData.perms.add);
        // this.add_display = this.menuData.perms.add;
        // this.edit_display = this.menuData.perms.edit;
        // this.delete_display = this.menuData.perms.delete;
    },
    computed:{
        ...mapState({
            menuData:state=>state.menuData  
        })
    },
    methods: {
        handleSubmit(name) {
            this.getData();
        },
        getData() {
            Object.assign(this.formQuery, this.datagrid.queryParams);
            this.api.admin.perm.getlist(this.formQuery).then((res) => {
                if(res.result) {
                    this.datagrid.data = res.data;
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        reloadData() {
            this.datagrid.data = this.getData();
        },
        changePage (page) {
            this.datagrid.queryParams.page = page;
            this.datagrid.data = this.getData();
        },
        edit(params) {
            this.api.admin.perm.edit(params.row).then((res)=>{
                if(res.result) {
                    //refresh
                    this.reloadData();
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        delete(params) {
            this.$Modal.confirm({
                title: '提示信息',
                content: '<p>是否确定删除？</p>',
                loading: true,
                onOk: () => {
                    this.api.admin.perm.delete({id:params.row.id}).then((res)=>{
                        if(res.result) {
                            //refresh
                            this.reloadData();
                            this.$Message.info('删除成功');
                            this.$Modal.remove();
                        }else {
                            this.$Message.error(res.msg);
                        }
                    });
                }
            });
        },
        showAddDialog() {
            this.dialog.modal_dialog = true;
            this.dialog.formItem = {};
        },
        showEditDialog(params) {
            this.dialog.modal_dialog = true;
            let r = params.row;
            this.dialog.formItem = {id:r.id,bsCode:r.bsCode, bsName:r.bsName, bsComment:r.bsComment};
        },
        ok () {
            if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.admin.perm.edit(this.dialog.formItem).then((res) => {
                            if(res.result) {
                                //refresh
                                this.reloadData();
                            }else {
                                this.$Message.error(res.msg);
                            }
                        });
                //     }
                // });
            }else {
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.admin.perm.add(this.dialog.formItem).then((res) => {
                            if(res.result) {
                                //refresh
                                this.reloadData();
                            }else {
                                this.$Message.error(res.msg);
                            }
                        });
                //     }
                // });
            }
        },
        cancel () {
            
        },
        showPropsDialog(params) {
            this.extProps.modal_dialog = true;
            let rid = params.row.id;
            this.$refs.custExtprops.loadData(rid);
        },
        save() {
            this.$refs.custExtprops.save();
        }
    }
}
</script>
