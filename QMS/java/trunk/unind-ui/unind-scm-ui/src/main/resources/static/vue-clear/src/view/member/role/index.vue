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
                    <Input v-model="dialog.formItem.pkParent" value="-1"  type="hidden"></Input>
                    
                    <Form-item label="所属角色组" prop="pkSysRolegroup">
                        <Select v-model="dialog.formItem.pkSysRolegroup" placeholder="请输入">
                            <Option v-for="item in dialog.refs.rolegroup" :value="item.id" :key="item">{{item.bsCode}} - {{item.bsName}}</Option>
                        </Select>
                    </Form-item>
                    <Form-item label="角色编码" prop="bsCode">
                        <Input v-model="dialog.formItem.bsCode" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="角色名称" prop="bsName">
                        <Input v-model="dialog.formItem.bsName" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="备注">
                        <Input v-model="dialog.formItem.bsComment" type="textarea" placeholder="请输入..."></Input>
                    </Form-item>
                </Form>
            </p>
        </Modal>
        <!--分配权限-->
        <Modal v-model="perms.modal_dialog" title="分配权限" @on-ok="save" @on-cancel="unsave" >
            <p>
                <input type="hidden" id="roleId" name="roleId" value="" />
                <Tree ref="resrce_tree" :data="perms.tree.data" :render="renderTree" class="layout-menu-left" style="height:500px;" show-checkbox></Tree>
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
            perms: {
                modal_dialog: false,
                tree: {
                    data: []
                },
                formItem: {
                    pkPerms:[]
                }
            },
            dialog: {
                refs: {
                    rolegroup: []
                },
                modal_dialog: false,
                formItem: {
                },
                ruleForm: {
                    pkSysRolegroup: [
                        { required: true, message: '请选择所属角色组', trigger: 'blur' }
                    ],
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
                        title: '角色编码',
                        key: 'bsCode'
                    },
                    {
                        title: '角色名称',
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
                            if(this.menuData.perms.ALLOC) {   
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
                                            this.showAllocDialog(params)
                                        }
                                    }
                                }, '分配权限'));
                            }
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
                            if(this.menuData.perms.EDIT) {   
                                ary.push(h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.delete(params)
                                        }
                                    }
                                }, '删除'));
                            }
                            return h('div', ary);
                        }
                    }
                ]
            }
        }
    },
    created(){
        this.getRolegroup();
        // this.getTree();
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
        renderTree(h, { root, node, data }) {
            console.log(JSON.stringify(data));
            let ary = [
                h('span', data.title)
            ];
            if(data.perms) {
                let perms = data.perms;
                for(let i=0;i<perms.length;i++) {
                    //ary.push(h('span', [h('input', {attrs:{type:'checkbox',name:data.attributes.bsCode,value:perms[i].pkPerm}})], perms[i].permName));
                    ary.push(h('label', {style: {margin: '0 0 0 5px'}}, [h('input', {style: {margin: '5px'}, attrs:{type:'checkbox',name:data.attributes.bsCode,value:perms[i].pkPerm}}), h('span', perms[i].permName)]));
                }
            }
            return h('span', {
                    style: {
                        display: 'inline-block',
                        width: '100%'
                    }
                }, ary
            )
        },
        getData() {
            Object.assign(this.formQuery, this.datagrid.queryParams);
            console.log("after===>");
            console.log(this.formQuery);
            this.api.role.getlist(this.formQuery).then((res) => {
                console.log("get list===>");
                console.log(res.data);
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
            console.log(params);
            this.api.role.edit(params.row).then((res)=>{
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
                    this.api.role.delete({id:params.row.id}).then((res)=>{
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
            this.dialog.formItem = {id:r.id,pkSysRolegroup:r.pkSysRolegroup,bsCode:r.bsCode, bsName:r.bsName, bsComment:r.bsComment};
        },
        ok () {
            console.log("this.dialog.formItem====>"+typeof(this.dialog.formItem.id));
            if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {
                console.log("编辑1");
                console.log(JSON.stringify(this.dialog.formItem));
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.role.edit(this.dialog.formItem).then((res) => {
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
                        this.api.role.add(this.dialog.formItem).then((res) => {
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
        getRolegroup() {
            this.api.rolegroup.getlist({}).then((res) => {
                if(res.result) {
                    console.log(res.data);
                    this.dialog.refs.rolegroup = res.data.rows;
                }
            });
        },
        showAllocDialog(params) {
            this.perms.modal_dialog = true;
            this.getlist(params.row.id);
        },
        getlist(pkRole) {
            document.getElementById("roleId").value = pkRole;
            this.api.roleperm.getlist({pkRole:pkRole}).then((res) => {
                if(res.result) {
                    this.spellTitle(res.data.resrces, res.data.perms, res.data.alloced);
                    this.perms.tree.data = res.data.resrces;

                    //选中已分配
                    let callback = function(){
                        for(let resrceCode in res.data.alloced) {
                            let obj = document.getElementsByName(resrceCode);
                            if(obj.length==0) {
                                continue;
                            }
                            let alloced = res.data.alloced[resrceCode];
                            for(let i = 0; i < obj.length; i++) {
                                obj[i].checked = false;
                                for(let j = 0; j < alloced.length; j++) {
                                    if(obj[i].value==alloced[j].id) {
                                        obj[i].checked = true;
                                        break;
                                    }
                                }
                            }
                        }
                    };
                    setTimeout(callback, 20);
                }
            });
        },
        getNodes(ary, resrces) {
            //Deprecated
            for(let i = 0; i < resrces.length; i++) {
                let resrceCode = this.getNode(resrces[i]);
                if(resrces[i].attributes.bsLeaf==1) {
                    ary.push(resrceCode);
                }
                if(resrces[i].children) {
                    this.getNodes(ary, resrces[i].children);
                }
            }
        },
        getNode(resrce) {
            //Deprecated
            let ary = {resrceCode: resrce.attributes.bsCode};
            return ary;
        },
        spellTitle(resrces, perms, alloced) {
            let title = "", resrceCode = "";
            for(let i = 0; i < resrces.length; i++) {
                title = resrces[i].attributes.bsName+"<br/>";
                resrceCode = resrces[i].attributes.bsCode;
                if(perms[resrceCode]) {
                    let ary = perms[resrceCode];
                    if(ary) {
                        if(alloced[resrceCode]) {
                            if(alloced[resrceCode].length > 0) {
                                resrces[i].checked = true;
                            }
                        }
                        resrces[i].perms = ary;
                    }
                }
                if(resrces[i].children) {
                    this.spellTitle(resrces[i].children, perms, alloced)
                }
            }
        },
        getNodes(ary, resrces) {
            for(let i = 0; i < resrces.length; i++) {
                let resrceCode = this.getNode(resrces[i]);
                if(resrces[i].attributes.bsLeaf==1) {
                    ary.push(resrceCode);
                }
                if(resrces[i].children) {
                    this.getNodes(ary, resrces[i].children);
                }
            }
        },
        getNode(resrce) {
            let ary = {resrceCode: resrce.attributes.bsCode};
            return ary;
        },
        getPerms() {
            let res = [];
            let nodes = this.$refs['resrce_tree'].getCheckedNodes();
            for(let i = 0; i < nodes.length; i++) {
                this.addParent(res, nodes[i]);
                let perm = {};
                perm.pkResrce = nodes[i].id;
                let perms = [];
                let objs = document.getElementsByName(nodes[i].attributes.bsCode);
                for(let j = 0; j < objs.length; j++) {
                    if(objs[j].checked) {
                        perms.push(objs[j].value);
                    }
                }
                perm.pkPerms = perms.toString();
                res.push(perm);
            }
            console.log(res);
            // this.getSelectedNodes(ary, this.perms.tree.data);
            return res;
        },
        addParent(res, node) {
            if(!node.parent) {
                return;
            }
            this.addParent(res, node.parent);
            res.push({pkResrce: node.parent.id});
        },
        save() {
            let res = this.getPerms();
            let roleId = document.getElementById("roleId").value;
            this.api.roleperm.save({roleId:roleId, rrpvo: JSON.stringify(res)}).then((res) => {
                if(res.result) {
                    this.$Message.info("操作成功");
                }else {
                    this.$Message.error("操作失败");
                }
            });
        },
        unsave() {
        }
    }
}
</script>
