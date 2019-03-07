<template>
    <div>
        <Row>
            <i-col>
                <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                    <Form-item prop="bsCode">
                        <Input type="text" v-model="formQuery.bsCode" placeholder="参数编码" />
                    </Form-item>
                    <Form-item prop="bsName">
                        <Input type="text" v-model="formQuery.bsName" placeholder="参数名称" />
                    </Form-item>
                    <Form-item>
                        <Button type="primary" @click="handleSubmit('formQuery')">查 询</Button>
                    </Form-item>
                    <Form-item>
                        <Button type="primary" @click="showAddDialog()">新 增</Button>
                    </Form-item>
                </Form>
            </i-col>
        </Row>
        <Row>
            <i-col span="24">
                <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe style="height:500px;"></Table>
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
                    <Input v-model="dialog.formItem.id" type="hidden"></Input>
                    
                    <Form-item label="所属目录" prop="bsCatelog">
                        <Select v-model="dialog.formItem.bsCatelog" placeholder="请输入">
                            <Option v-for="item in dialog.refs.cataloglist" :value="item.bsCode" :key="item.bsCode">{{item.bsCode}} - {{item.bsComment}}</Option>
                        </Select>
                    </Form-item>
                    <Form-item label="字段名称" prop="bsName">
                        <Input v-model="dialog.formItem.bsName" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="字段标签" prop="bsLabel">
                        <Input v-model="dialog.formItem.bsLabel" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="顺序号" prop="bsSortNo">
                        <Input v-model="dialog.formItem.bsSortNo" placeholder="请输入"></Input>
                    </Form-item>
                </Form>
            </p>
        </Modal>
    </div>
</template>
<script>
import {mapState} from 'vuex'
export default {
    data() {
        return {
            formQuery: {
                bsCode: '',
                bsName: ''
            },
            dialog: {
                refs: {
                    cataloglist: []
                },
                modal_dialog: false,
                formItem: {
                },
                ruleForm: {
                    bsName: [
                        { required: true, message: '请填写字段名称', trigger: 'blur' }
                    ],
                    bsLabel: [
                        { required: true, message: '请填写标签名称', trigger: 'blur' }
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
                        title: 'ID',
                        key: 'id'
                    },
                    {
                        title: '所属目录',
                        key: 'bsCatelog'
                    },
                    {
                        title: '字段名称',
                        key: 'bsName',
                        align: 'center',
                        render: (h, params) => {
                            return h('Button', {
                                props: {
                                    type: 'text',
                                    size: 'small'
                                },
                                on: {   
                                    click: () => {
                                        let query = {id: params.row.id};
                                        this.$router.push({
                                            name: 'customfieldvalue',
                                            query: query
                                        });
                                    }
                                }
                            }, params.row.bsName);
                        }
                    },
                    {
                        title: '标签名称',
                        key: 'bsLabel'
                    },
                    {
                        title: '顺序号',
                        key: 'bsSortNo'
                    },
                    {
                        title: '查看数据',
                        key: 'show_more',
                        align: 'center',
                        render: (h, params) => {
                            return h('Button', {
                                props: {
                                    type: 'text',
                                    size: 'small'
                                },
                                on: {   
                                    click: () => {
                                        let query = {id: params.row.id};
                                        this.$router.push({
                                            name: 'customfieldvalue',
                                            query: query
                                        });
                                    }
                                }
                            }, '查看');
                        }
                    },
                    {
                        title: '操作',
                        key: 'action',
                        render: (h, params) => {
                            let ary = [];
                            //if(this.menuData.perms.EDIT) {
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
                            //}
                            //if(this.menuData.perms.DELETE) {
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
                            //}
                            return h('div', ary);
                        }
                    }
                ]
            }
        }
    },
    created(){
        this.getcataloglist();
    },
    computed:{
        ...mapState({
            menuData:state=>state.menuData  
        })
    },
    mounted () {
        this.init();
    },
    watch: {
        '$route' () {
            this.init();
        }
    },
    methods: {
        getcataloglist() {
            this.api.custom.getlist({}).then((res) => {
                if(res.result) {
                    this.dialog.refs.cataloglist = res.data.rows;
                }
            });
        },
        init() {
            this.formQuery.id=this.$route.query.bsCode;
            this.getData();
        },
        handleSubmit(name) {
            this.reloadData();
        },
        getData() {
            Object.assign(this.formQuery, this.datagrid.queryParams);
            this.api.customfield.getlist(this.formQuery).then((res) => {
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
            this.api.customfield.edit(params.row).then((res)=>{
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
                    this.api.customfield.delete({id:params.row.id}).then((res)=>{
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
            this.dialog.formItem = {id:r.id,bsCatelog:r.bsCatelog,bsName:r.bsName,bsLabel:r.bsLabel,bsSortNo:r.bsSortNo};
        },
        ok () {
            if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="string") {
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.customfield.edit(this.dialog.formItem).then((res) => {
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
                        this.api.customfield.add(this.dialog.formItem).then((res) => {
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
            
        }
    }
}
</script>