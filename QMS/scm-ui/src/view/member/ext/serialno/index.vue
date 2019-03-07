<template>
    <div>
        <Row>
            <i-col>
                <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                    <Form-item prop="bsCode">
                        <Input v-model="formQuery.bsCode" placeholder="编码" />
                    </Form-item>
                    <Form-item prop="bsName">
                        <Input v-model="formQuery.bsName" placeholder="名称" />
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
                <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe style="height:500px;"></Table>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" @on-change="changePage" show-total show-elevator show-sizer></Page>
                    </div>
                </div>
            </i-col>
        </Row>
        <Modal v-model="dialog.modal_dialog" title="" @on-ok="ok" @on-cancel="cancel" width="800" >
            <p>
                <Form :model="dialog.formItem" :ref="dialog.ruleForm" :rules="dialog.ruleForm" :label-width="80">
                    <span style="display:none;">
                    <Input v-model="dialog.formItem.id"></Input>
                    <Input v-model="dialog.formItem.pkGroup" value="-1"></Input>
                    <Input v-model="dialog.formItem.pkOrg" value="-1"></Input>
                    </span>
                <Row>
                    <i-col span="12">
                        <Form-item label="编码" prop="bsCode">
                            <Input v-model="dialog.formItem.bsCode" placeholder="请输入"></Input>
                        </Form-item>
                    </i-col>
                    <i-col span="12">
                        <Form-item label="名称" prop="bsName">
                            <Input v-model="dialog.formItem.bsName" placeholder="请输入"></Input>
                        </Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="12">
                        <Form-item label="前缀" prop="bsPrefix">
                            <Input v-model="dialog.formItem.bsPrefix" placeholder="请输入"></Input>
                        </Form-item>
                    </i-col>
                    <i-col span="12">
                        <Form-item label="后缀" prop="bsSurffix">
                            <Input v-model="dialog.formItem.bsSurffix" placeholder="请输入"></Input>
                        </Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="12">
                        <Form-item label="初始值" prop="bsInitVal">
                            <InputNumber style="width:100%" v-model="dialog.formItem.bsInitVal" placeholder="请输入" value="1" ></InputNumber>
                        </Form-item>
                    </i-col>
                    <i-col span="12">
                        <Form-item label="最大值" prop="bsMaxVal">
                            <InputNumber style="width:100%" v-model="dialog.formItem.bsMaxVal" placeholder="请输入" ></InputNumber>
                        </Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="12">
                        <Form-item label="长度" prop="bsLength">
                            <InputNumber style="width:100%" v-model="dialog.formItem.bsLength" placeholder="请输入" value="5" ></InputNumber>
                        </Form-item>
                    </i-col>
                    <i-col span="12">
                        <Form-item label="当前值" prop="bsValue">
                            <InputNumber style="width:100%" v-model="dialog.formItem.bsValue" placeholder="请输入" value="1" ></InputNumber>
                        </Form-item>
                    </i-col>
                    <i-col span="12">
                        <Form-item label="启用状态" prop="bsEnableState">
                            <RadioGroup v-model="dialog.formItem.bsEnableState">
                                <Radio :label="1">
                                    <span>启 用</span>
                                </Radio>
                                <Radio :label="0">
                                    <span>停 用</span>
                                </Radio>
                            </RadioGroup>
                        </Form-item>
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Form-item label="备注">
                            <Input v-model="dialog.formItem.bsComment" type="textarea" placeholder="请输入..."></Input>
                        </Form-item>
                    </i-col>
                </Row>
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
                        title: '编码',
                        key: 'bsCode'
                    },
                    {
                        title: '名称',
                        key: 'bsName'
                    },
                    {
                        title: '前缀',
                        key: 'bsPrefix'
                    },
                    {
                        title: '后缀',
                        key: 'bsSurffix'
                    },
                    {
                        title: '初始值',
                        key: 'bsInitVal'
                    },
                    {
                        title: '最大值',
                        key: 'bsMaxVal'
                    },
                    {
                        title: '长度',
                        key: 'bsLength'
                    },
                    {
                        title: '启用状态',
                        key: 'bsEnableState'
                    },
                    {
                        title: '当前值',
                        key: 'bsValue'
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
                            return h('div', ary);
                        }
                    }
                ]
            }
        }
    },
    created(){
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
            //console.log("after===>");
            //console.log(this.formQuery);
            this.api.admin.serialno.getlist(this.formQuery).then((res) => {
                //console.log("get list===>");
                //console.log(res.data);
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
            //console.log(params);
            this.api.admin.serialno.edit(params.row).then((res)=>{
                //console.log(res)
                if(res.result) {
                    //refresh
                    this.reloadData();
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        delete(params) {
            //console.log(params);
            this.$Modal.confirm({
                title: '提示信息',
                content: '<p>是否确定删除？</p>',
                loading: true,
                onOk: () => {
                    this.api.admin.serialno.delete({id:params.row.id}).then((res)=>{
                        //console.log(res)
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
            this.dialog.formItem = {id:r.id,bsCode:r.bsCode, bsName:r.bsName, bsPrefix:r.bsPrefix, bsSurffix:r.bsSurffix, bsInitVal:r.initVal, bsMaxVal:r.bsMaxVal, bsLength:r.bsLength, bsValue:r.bsValue, bsEnableState:r.bsEnableState, bsComment:r.bsComment};
        },
        ok () {
            //console.log("this.dialog.formItem====>"+typeof(this.dialog.formItem.id));
            if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {
                //console.log("编辑1");
                //console.log(JSON.stringify(this.dialog.formItem));
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.admin.serialno.edit(this.dialog.formItem).then((res) => {
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
                //console.log("新增1");
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.admin.serialno.add(this.dialog.formItem).then((res) => {
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
