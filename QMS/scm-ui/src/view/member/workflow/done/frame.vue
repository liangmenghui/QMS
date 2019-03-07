<template>
    <div>
        <div>
        <Row>
                <i-col>
                    <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                        <Form-item >
                             <Input v-model="formQuery.bsKey" placeholder="请输入key" ></Input>
                        </Form-item>
                        <Form-item >
                             <Input v-model="formQuery.bsName" placeholder="请输入模型名称" ></Input>
                        </Form-item>
                        <Form-item >
                             <Input  v-model="formQuery.bsVersion" placeholder="请输入版本" ></Input >
                        </Form-item>
                        <Form-item >
                            <Button type="primary" @click="handleSubmit('formQuery')">{{$t('button.search')}}</Button>
                        </Form-item> 
                        <Form-item >
                            <Button type="primary" @click="showAddDialog()" >{{$t('button.add')}}</Button>
                        </Form-item>
                         <Form-item >
                            <Button type="primary" @click="importData()" >导入</Button>
                        </Form-item>
                    </Form>
                </i-col>
            </Row>
        </div>
        <Row>
                <Modal v-model="dialog.modal_dialog2" title="" @on-ok="addSample" @on-cancel="cancel" >
                        <Form :model="formQuery" :ref="dialog.ruleForm" :rules="dialog.ruleForm" :label-width="80">
                                  <Form-item prop="bsComment" label="导入模型:">  
                                        <Upload type="drag"  action="admin/workflow/import" :data={} :before-upload="uploadBefore" :on-success='uploadSuccess' :on-error='uploadError'>
                                            <div style="padding: 20px 0">
                                              <Icon type="ios-cloud-upload" size="40" style="color: #3399ff"></Icon>
                                              <p>请选择文件</p>
                                            </div>
                                          </Upload>
                                  </Form-item>
                                  <div style="color:red">
                                        <Form-item prop="bsComment" label="备注"> 
                                                ：选择一个包含图片信息（用于坐标定位）的文件 (.bpmn20.xml 或 .bpmn)。
                                        </Form-item>
                                  </div>
                        </Form>
                </Modal> 
            </Row>


        <Row>
            <i-col span="24">
                <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe height="500"></Table>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" @on-change="changePage"   @on-page-size-change="chageSize" show-total show-elevator show-sizer></Page>
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
                    <Form-item label="KEY" prop="bsKey">
                        <Input v-model="dialog.formItem.bsKey" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="模型名称" prop="bsName">
                        <Input v-model="dialog.formItem.bsName" placeholder="请输入"></Input>
                    </Form-item>
                    <Form-item label="版本" prop="bsVersion">
                        <InputNumber :min='1' style="width:407px" v-model="dialog.formItem.bsVersion" placeholder="请输入"></InputNumber>
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
import custDialog from '../../../../components/business-component/custDialog.vue';
export default {
    components: {
        custDialog
    },
    data() {
        return {
            formQuery: {
                bsModelId:'',
                bsKey: '',
                bsName: '',
                bsVersion:'',
                page:1,
                    rows:25,
                    pkParent:-1
            },
            extProps: {
                modal_dialog: false
            },
            dialog: {
                modal_dialog: false,
                modal_dialog2:false,
                formItem: {
                    bsKey:'',
                    bsVersion:'',
                    bsName:'',
                    bsComment:""
                },
                ruleForm: {
                    bsKey: [
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
                        title: "KEY",
                        key: 'key'
                    },
                    {
                        title: '模型名称',
                        key: 'name'
                    },
                    {
                        title: '版本',
                        key: 'version'
                    },
                    {
                        title: '元数据',
                        key: 'metaInfo'
                    },
                    {
                        title: '描述',
                        key: 'comment'
                    },
                    {
                        title: '创建时间',
                        key: 'createTime',
                        render: (h, params) => {
                            let text = "";
                            if(typeof(params.row.createTime)=="number"&&params.row.createTime > 0) {
                                let date = new Date(params.row.createTime);
                                text = (date.getFullYear());
                                text = text +"-"+ ((date.getMonth()<10)?("0"+(date.getMonth()+1)):date.getMonth());
                                text = text +"-"+ ((date.getDate()<10)?("0"+date.getDate()):date.getDate());
                                text = text +" "+ ((date.getHours()<10)?("0"+date.getHours()):date.getHours());
                                text = text +":"+ ((date.getMinutes()<10)?("0"+date.getMinutes()):date.getMinutes());
                                text = text +":"+ ((date.getSeconds()<10)?("0"+date.getSeconds()):date.getSeconds());
                            }
                            return h('span', {
                            }, text);
                        }
                    },
                    {
                        title: '最后修改时间',
                        key: 'lastUpdateTime',
                        render: (h, params) => {
                            let text = "";
                            if(typeof(params.row.lastUpdateTime)=="number"&&params.row.lastUpdateTime > 0) {
                                let date = new Date(params.row.lastUpdateTime);
                                text = (date.getFullYear());
                                text = text +"-"+ ((date.getMonth()<10)?("0"+(date.getMonth()+1)):date.getMonth());
                                text = text +"-"+ ((date.getDate()<10)?("0"+date.getDate()):date.getDate());
                                text = text +" "+ ((date.getHours()<10)?("0"+date.getHours()):date.getHours());
                                text = text +":"+ ((date.getMinutes()<10)?("0"+date.getMinutes()):date.getMinutes());
                                text = text +":"+ ((date.getSeconds()<10)?("0"+date.getSeconds()):date.getSeconds());
                            }
                            return h('span', {
                            }, text);
                        }
                    },
                   
            
                    {
                        title: '操作',
                        key: 'action',
                        render: (h, params) => {
                            let ary = [];
                            if(true) {
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
                            if(true) {
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
                            if(true) {
                                ary.push(h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginLeft: '5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.deployData(params)
                                        }
                                    }
                                }, '部署'))
                            }
                            if(true) {
                                ary.push(h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        // marginLeft: '5px',
                                        marginTop:'5px'
                                    },
                                    on: {
                                        click: () => {
                                            this.exportData(params)
                                        }
                                    }
                                }, '导出模型'))
                            }
                            return h('div', ary);
                        }
                    }
                ]
            }
        }
    },
    created(){
        this.getData();
    },
    computed:{
        ...mapState({
            menuData:state=>state.menuData  
        })
    },
    methods: {
        handleSubmit() {
            this.getData();
        },
        getData() {
            this.api.workflow.frame.getlist(this.formQuery).then((res) => {
                if(res.result) { 
                    if(res.data ==null){
                        res.data="";
                    } 
                    this.datagrid.data = res.data;
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },

        changePage(pageSize) {
            this.formQuery.page = pageSize;
            this.getData();
          },
          chageSize(pageSizeOpts) {
            this.formQuery.rows = pageSizeOpts;

            this.getData();
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
            console.log(params);
            this.formQuery.bsModelId=params.row.id;
            this.$Modal.confirm({
                title: '提示信息',
                content: '<p>是否确定删除？</p>',
                loading: true,
                onOk: () => {
                    this.api.workflow.frame.delete(this.formQuery).then((res) => {
                        if(res.result) {
                            this.$Modal.remove(); 
                            this.$Message.success('删除成功');
                            this.getData();
                        }else {
                            this.$Message.error(res.msg);
                        }
            });
                }
            });
        },
        importData(){
            this.dialog.modal_dialog2=true;
        },
        exportData(params){
            window.location.href="/admin/workflow/export?bsModelId="+params.row.id;
        },   
        deployData(params){
            this.formQuery.bsModelId=params.row.id;
            this.api.workflow.frame.deploy(this.formQuery).then((res) => {
                if(res.result) { 
                    this.$Message.sucess("部署成功");
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },   
        showAddDialog() {
            this.dialog.modal_dialog = true;
            this.dialog.formItem = {};
        },
        showEditDialog(params) {
            // this.dialog.modal_dialog = true;
            // let r = params.row;
            // this.dialog.formItem = {id:r.id,bsKey:r.key, bsName:r.name, bsComment:r.comment};
            window.open("/modeler/modeler.html?modelId="+params.row.id);
        },
        ok () {        
            //if(typeof(this.dialog.formItem.bsVersion)!=undefined && typeof(this.dialog.formItem.bsVersion)=="number"){
                this.api.workflow.frame.add(this.dialog.formItem).then((res) => {
                            if(res.result) {
        
                                this.$Message.sucess("添加成功");
                                this.getData();
                            }else {
                                this.$Message.error(res.msg);
                            }
                        });
            //}else{
            //    this.$Message.error("请正确填写版本信息");
            //}
                       
           
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
