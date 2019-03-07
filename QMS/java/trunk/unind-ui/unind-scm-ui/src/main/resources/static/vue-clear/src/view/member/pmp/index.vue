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
                    <Form-item>
                        <Button type="primary" @click="handleSubmit('formQuery')">查 询</Button>
                    </Form-item>
                    <Form-item>
                        <Button type="primary" @click="showAddDialog()">新 增</Button>
                    </Form-item>
                </Form>
            </i-col>
        </Row>
        <!-- <Row>
            <i-col span="24">
                <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe style="height:500px;"></Table>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" @on-change="changePage" show-total show-elevator show-sizer></Page>
                    </div>
                </div>
            </i-col>
        </Row> -->
                <table id="test">
                    <thead id="test_header"></thead>
                    <tbody id="test_body"></tbody>
                </table>
        <Modal v-model="dialog.modal_dialog" title="" @on-ok="ok" @on-cancel="cancel" >
            <p>
                <Form :model="dialog.formItem" :ref="dialog.ruleForm" :rules="dialog.ruleForm" :label-width="80">
                    <Input v-model="dialog.formItem.id" type="hidden"></Input>
                    <Input v-model="dialog.formItem.pkGroup" value="-1"  type="hidden"></Input>
                    <Input v-model="dialog.formItem.pkOrg" value="-1"  type="hidden"></Input>
                    
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
                        title: '权限编码',
                        key: 'bsCode'
                    },
                    {
                        title: '权限名称',
                        key: 'bsName'
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
        getData();
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
            console.log("after===>");
            console.log(this.formQuery);
            this.api.pmp.getlist(this.formQuery).then((res) => {
                console.log("get list===>");
                console.log(res.data);
                if(res.result) {
                    //this.datagrid.data = res.data;
                    //let rows = [];
                    //this.datagrid.data = {rows:rows,total:9999999};

                    //this.datagrid.columns = [];
                    //for(let i = 0; i < res.data.length; i++) {
                    //    this.datagrid.columns.push({title: res.data[i].itemNo,key: 'itemNo'});
                    //}

                    //
                    let thead = document.getElementById("test_header");
                    let body = document.getElementById("test_body");
                    let trObj, tdObj, text, inputObj;
                    let tdObj1,tdObj2,tdObj3,tdObj4,tdObj5,tdObj6,tdObj7;
                    
                    let trObj1 = document.createElement("tr");
                    let trObj2 = document.createElement("tr");
                    let trObj3 = document.createElement("tr");
                    let trObj4 = document.createElement("tr");
                    
                    for(let i = 0; i < res.data.length; i++) {
                        tdObj = document.createElement("td");
                        tdObj.setAttribute("colspan", 9);
                        tdObj.setAttribute("nowrap", "nowrap");
                        tdObj.innerHTML = res.data[i].itemNo;
                        trObj1.appendChild(tdObj);

                        tdObj1 = document.createElement("td");
                        tdObj1.setAttribute("colspan", 5);
                        tdObj1.setAttribute("nowrap", "nowrap");
                        tdObj1.innerHTML = res.data[i].comment;
                        trObj2.appendChild(tdObj1);
                        
                        tdObj2 = document.createElement("td");
                        tdObj2setAttribute("colspan", 1);
                        tdObj2.setAttribute("nowrap", "nowrap");
                        tdObj2.innerHTML = "生产周期";
                        trObj2.appendChild(tdObj2);
                        
                        tdObj3 = document.createElement("td");
                        tdObj3setAttribute("colspan", 3);
                        tdObj3.setAttribute("nowrap", "nowrap");
                        tdObj3.innerHTML = res.data[i].periodOfProd;
                        trObj2.appendChild(tdObj3);

                        tdObj1 = document.createElement("td");
                        tdObj1setAttribute("colspan", 2);
                        tdObj1.setAttribute("nowrap", "nowrap");
                        tdObj1.innerHTML = res.data[i].partNo;
                        trObj3.appendChild(tdObj1);
                        
                        tdObj2 = document.createElement("td");
                        tdObj2setAttribute("colspan", 1);
                        tdObj2.setAttribute("nowrap", "nowrap");
                        tdObj2.innerHTML = "最小发运";
                        trObj3.appendChild(tdObj2);
                        
                        tdObj3 = document.createElement("td");
                        tdObj3setAttribute("colspan", 1);
                        tdObj3.setAttribute("nowrap", "nowrap");
                        tdObj3.innerHTML = res.data[i].minShipment;
                        trObj3.appendChild(tdObj3);
                        
                        tdObj4 = document.createElement("td");
                        tdObj4setAttribute("colspan", 2);
                        tdObj4.setAttribute("nowrap", "nowrap");
                        tdObj4.innerHTML = res.data[i].minShipment;
                        trObj3.appendChild(tdObj4);
                        
                        tdObj5 = document.createElement("td");
                        tdObj5setAttribute("colspan", 1);
                        tdObj5.setAttribute("nowrap", "nowrap");
                        tdObj5.innerHTML = "运输周期";
                        trObj3.appendChild(tdObj5);
                        
                        tdObj6 = document.createElement("td");
                        tdObj6setAttribute("colspan", 2);
                        tdObj6.setAttribute("nowrap", "nowrap");
                        tdObj6.innerHTML = res.data[i].periodOfDelivery;
                        trObj3.appendChild(tdObj6);

                        //inputObj = document.createElement("input");
                        //inputObj.name = "test1";
                        //inputObj.value = "vlav";
                        //inputObj.style = "width:10px;";
                        //tdObj.appendChild(inputObj);
                        //trObj.appendChild(tdObj);
                    }
                    thead.appendChild(trObj1);
                    thead.appendChild(trObj2);
                    thead.appendChild(trObj3);

                    let length = res.data[0].mrpInfoVOList.length;
                    for(let j = 0; j < length; j++) {
                        let trObj = document.createElement("tr");
                        for(let i = 0; i < res.data.length; i++) {
                            for(let x = 0; x < 9; x++) {
                                let tdObj = document.createElement("td");
                                tdObjsetAttribute("colspan", 1);
                                tdObj.style = "width:75px;";
                                inputObj = document.createElement("input");
                                inputObj.name = "day";
                                inputObj.value = "day";
                                inputObj.style = "width:65px;";
                                tdObj.appendChild(inputObj);
                                trObj.appendChild(tdObj);
                            }
                        }

                        thead.appendChild(trObj);
                    }
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
            console.log(params);
            this.api.perm.edit(params.row).then((res)=>{
                console.log(res)
                if(res.result) {
                    //refresh
                    this.reloadData();
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        delete(params) {
            console.log(params);
            this.$Modal.confirm({
                title: '提示信息',
                content: '<p>是否确定删除？</p>',
                loading: true,
                onOk: () => {
                    this.api.perm.delete({id:params.row.id}).then((res)=>{
                        console.log(res)
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
            console.log("this.dialog.formItem====>"+typeof(this.dialog.formItem.id));
            if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {
                console.log("编辑1");
                console.log(JSON.stringify(this.dialog.formItem));
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.perm.edit(this.dialog.formItem).then((res) => {
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
                console.log("新增1");
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.perm.add(this.dialog.formItem).then((res) => {
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
