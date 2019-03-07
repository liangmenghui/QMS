<template>
    <div>
        <Row>
            <i-col>
                <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                    <Form-item prop="bsCode">
                        <Input type="text" v-model="formQuery.bsCode" placeholder="设备编码" />
                    </Form-item>
                    <Form-item prop="bsName">
                        <Input type="text" v-model="formQuery.bsName" placeholder="设备名称" />
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
        <Modal v-model="dialog.modal_dialog" title="" @on-ok="ok" @on-cancel="cancel" >
            <p>
                <Form :model="dialog.formItem" :ref="dialog.ruleForm" :rules="dialog.ruleForm" :label-width="80">
                    <Input v-model="dialog.formItem.id" type="hidden"></Input>
                    <Input v-model="dialog.formItem.pkGroup" value="-1"  type="hidden"></Input>
                    <Input v-model="dialog.formItem.pkOrg" value="-1"  type="hidden"></Input>
                    
                    <Form-item label="设备编码" prop="bsCode">
                        <Input v-model="dialog.formItem.bsCode" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="设备名称" prop="bsName">
                        <Input v-model="dialog.formItem.bsName" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="设备位置" prop="bsPosition">
                        <Input v-model="dialog.formItem.bsPosition" placeholder="请输入"></Input>
                        <!--<Select v-model="dialog.formItem.bsPosition" placeholder="请选择">
                            <Option value="1">二楼1#实验室</Option>
                            <Option value="0">二楼2#实验室</Option>
                        </Select>-->
                    </Form-item>
                    <Form-item label="是否可用" prop="bsEnableState">
                        <Select v-model="dialog.formItem.bsEnableState" placeholder="请选择">
                            <Option :value="1">可用</Option>
                            <Option :value="0">不可用</Option>
                        </Select>
                    </Form-item>
                    <Form-item label="设备状态" prop="bsStatus">
                        <Select v-model="dialog.formItem.bsStatus" placeholder="请选择">
                            <Option :value="0">正常</Option>
                            <Option :value="1">维修中</Option>
                            <Option :value="2">保养中</Option>
                        </Select>
                    </Form-item>
                    <Form-item label="设备来源" prop="bsOrigin">
                        <Input v-model="dialog.formItem.bsOrigin" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="管理员" prop="pkBelongTo">
                        <Input v-model="dialog.formItem.pkBelongTo" placeholder="请输入"></Input>
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
                        title: '设备编码',
                        key: 'bsCode'
                    },
                    {
                        title: '设备名称',
                        key: 'bsName'
                    },
                    {
                        title: '设备位置',
                        key: 'bsPosition'
                    },
                    {
                        title: '是否可用',
                        key: 'bsEnableState',
                        render: (h, params) => {
                            return h('span', (params.row.bsEnableState==1?'可用':'不可用'))
                        }
                    },
                    {
                        title: '设备状态',
                        key: 'bsStatus',
                        render: (h, params) => {
                            if(params.row.bsStatus==0) {
                                return h('span', '正常');
                            }else if(params.row.bsStatus==1) {
                                return h('span', '维修中');
                            }else if(params.row.bsStatus==2) {
                                return h('span', '保养中');
                            }else {
                                return h('span', 'Unknown');
                            }
                        }
                    },
                    {
                        title: '设备来源',
                        key: 'bsOrigin'
                    },
                    {
                        title: '管理员',
                        key: 'pkBelongTo'
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
            },
            
            // add_display: false,
            // edit_display: false,
            // delete_display: false
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
            this.api.lab.equipment.getlist(this.formQuery).then((res) => {
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
            this.api.lab.equipment.edit(params.row).then((res)=>{
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
                    this.api.lab.equipment.delete({id:params.row.id}).then((res)=>{
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
            //console.log(JSON.stringify(r));
            this.dialog.formItem = {id:r.id,bsCode:r.bsCode, bsName:r.bsName, bsPosition:r.bsPosition, bsEnableState:r.bsEnableState, bsStatus:r.bsStatus, bsOrigin:r.bsOrigin, pkBelongTo:r.pkBelongTo, bsComment:r.bsComment};
        },
        ok () {
            //console.log("this.dialog.formItem====>"+typeof(this.dialog.formItem.id));
            if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {
                //console.log("编辑1");
                //console.log(JSON.stringify(this.dialog.formItem));
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.lab.equipment.edit(this.dialog.formItem).then((res) => {
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
                        this.api.lab.equipment.add(this.dialog.formItem).then((res) => {
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
