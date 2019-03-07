<template>
        <div>
    <div>
        <Row>
                <i-col>
                    <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                        <Form-item >
                             <Input v-model="formQuery.bsName" :placeholder="$t('labSystem.eqManagement.inputEqName')" ></Input>
                        </Form-item>
                        <Form-item >
                            <Button type="primary" @click="handleSubmit('formQuery')">{{$t('button.search')}}</Button>
                        </Form-item> 
                        <Form-item v-if="menuData.perms.ADD">
                            <Button type="primary" @click="showAddDialog()" >{{$t('button.add')}}</Button>
                        </Form-item>
                    </Form>
                </i-col>
            </Row>
        </div>
        <Modal v-model="dialog.modal_dialog"  title="" @on-ok="t" @on-cancel="cancel" >
                <p>
            <div >
                <Row>
                    <i-col>
    <Form :model="formItem" :label-width="80" style="text-align:center"  :ref="dialog.ruleForm" :rules="dialog.ruleForm" >
        <FormItem :label="$t('labSystem.eqManagement.name')"  prop='bsName'>
            <Row>
                <Col span="12" >
                 <Input v-model="formItem.bsName" :placeholder="$t('labSystem.eqManagement.inputEqName')" ></Input>
                </Col>
           </Row>
        </FormItem>
        <FormItem :label="$t('labSystem.eqManagement.state')" >
                <Row>
                    <Col span="12" >
                        <Select v-model="formItem.bsState" :placeholder="$t('labSystem.eqManagement.select')" >
                                <Option value="0" >可用</Option>
                                <Option value="1">不可用</Option>
                        </Select>
                    </Col>
               </Row>
        </FormItem>

    </Form> 
     </i-col>
    </Row>
</div>
</p>
</Modal>

<Modal v-model="dialog.modal_dialog1" title="" @on-ok="update(formItem)" @on-cancel="cancel" >
    <p>
<div >
    <Row>
        <i-col>
<Form :model="formItem" :label-width="80" style="text-align:center" >
<FormItem :label="$t('labSystem.eqManagement.name')"  >
<Row>
    <Col span="12" >
     <Input v-model="formItem.bsName" :placeholder="$t('labSystem.eqManagement.inputEqName')"></Input>
    </Col>
</Row>
</FormItem>
<FormItem :label="$t('labSystem.eqManagement.state')" >
    <Row>
        <Col span="12" >
            <Select v-model="formItem.bsState"  :placeholder="$t('labSystem.eqManagement.select')">
                    <Option value="0" >可用</Option>
                    <Option value="1">不可用</Option>
            </Select>
        </Col>
   </Row>
</FormItem>

</Form> 
</i-col>
</Row>
</div>
</p>
</Modal>

    <Row>
            <i-col span="24">
                <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe style="height:auto;"></Table>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page :total="datagrid.data.total" @on-change="changePage"  @on-page-size-change="chageSize"  :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination"  show-total show-elevator show-sizer></Page>
                    </div>
                </div>
            </i-col>
        </Row>
 </div>


</template>
<script>
  import {mapState} from 'vuex'
    export default {
        created(){
          this.getDate();
        },
        computed:{
        ...mapState({
            menuData:state=>state.menuData  
        })
    },
    props:['langz'],
        data () {
            return {
                start:0,
                end:15,
                dialog: {
                    ruleForm: {
                    // bsCode: [
                    //     { required: true, message: '请填写编码', trigger: 'blur' }
                    // ],
                    bsName: [
                        { required: true, message: this.$t('labSystem.eqManagement.inputEqName'), trigger: 'blur' }
                    ]
                },
                modal_dialog: false,
                modal_dialog1:false
                },
                message: '新增',
                dict: {
                bsStatus:[{value: 0, name:'可用'},{value: 1, name:'不可用'}]
                },
                formQuery:
                {
                    bsCode: '',
                    bsName: ''
                },
                isShow:false,
                formItem:{
                    id:"",
                    bsName:"",
                    bsCode:"",
                    bsState:0,
                    bsManager:"",
                    bsPosition:""
                },
                datagrid: {
                    queryParams:{
                        page:1,
                        rows:15,
                        pkParent:-1
                    },
                    pagination: [15, 50, 100],
                    data: {rows:[],total:0,rows1:[]},
                    columns: [

                        {
                            title: this.$t('labSystem.eqManagement.name'),
                            key: 'bsName'
                        },
                        
                        {
                            title: this.$t('labSystem.eqManagement.state'),
                            key: 'bsState',
                            render: (h, params) => {
                                return h('span', this.dict.bsStatus[params.row.bsState].name);
                            }
                        },
                        {
                            title: this.$t('labSystem.eqManagement.createTime'),
                            key: 'bsCreatedTime'
                        },
                        {
                            title: this.$t('labSystem.eqManagement.action'),
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
                                    }, this.$t('button.modify')));
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
                                    }, this.$t('button.delete')))
                                }
                                return h('div', ary);
                            }
                        }
                    ]
                },
                }
        },
        watch:{
            langz() {
                console.log(this.datagrid.columns);
            //需要发生改变的时候触发监听事件就行
            this.datagrid.columns=[
                
            {
                title: this.$t('labSystem.eqManagement.name'),
                key: 'bsName'
            },
            
            {
                title: this.$t('labSystem.eqManagement.state'),
                key: 'bsState',
                render: (h, params) => {
                    return h('span', this.dict.bsStatus[params.row.bsState].name);
                }
            },
            {
                title: this.$t('labSystem.eqManagement.createTime'),
                key: 'bsCreatedTime'
            },
            {
                title: this.$t('labSystem.eqManagement.action'),
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
                        }, this.$t('button.modify')));
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
                        }, this.$t('button.delete')))
                    }
                    return h('div', ary);
                }
            }
            ]
        },
    },
        methods:{
            getDate(){
                this.api.lab.equipment.list().then((res) => {
                    if(res.result) {
                        this.datagrid.data.rows1=this.datagrid.data.rows;
                        this.datagrid.data.rows = res.data.rows.slice(this.start,this.end);
                        this.datagrid.data.total=res.data.total;
                       }else {
                        this.$Message.error(res.msg);
                      }
                });
             },
            t(){
                if(this.formItem.bsName==''){
                 return this.$Message.error(this.$t('labSystem.eqManagement.inputEqName'));
                 }else{
                    this.formItem.bsName= this.formItem.bsName.trim();
                    
                   this.api.lab.equipment.add(this.formItem).then((res) =>{
                       if(res.result){
                          this.getDate();
                          this.$Message.success(this.$t('msaage.sucess'));
                       }else{
                        this.$Message.error(this.$t('msaage.failure'));
                       }
                   });
                 }
               },

               showAddDialog() {
               this.dialog.modal_dialog = true;
               this.dialog.formItem ={};
        },

        cancel () {
            
        },

         delete:function(params){
                this.$Modal.confirm({
                title: this.$t('model.titel'),
                content: this.$t('model.deleteContent'),
                loading: true,
                onOk: () => {
                    this.api.lab.equipment.delete(params.row).then((res)=>{
                        if(res.result) {
                            this.getDate();
                            this.$Message.info(this.$t('msaage.sucess'));
                            this.$Modal.remove();
                        }else {
                            this.$Message.error(this.$t('msaage.failure'));
                        }
                    });
                }
            });
               },

               showEditDialog(params){
                this.dialog.modal_dialog1= true;
                this.formItem.eqCode=params.row.eqCode;
                this.formItem.bsName=params.row.bsName;
                this.formItem.eqNameY=params.row.bsName;
                this.formItem.eqPosition=params.row.eqPosition;
                this.formItem.eqManager=params.row.eqManager;
                this.formItem.bsState=params.row.bsState;
                this.formItem.id=params.row.id;


               },
               
               update(params){

                this.api.lab.equipment.update(params).then((res) =>{
                    if(res.result){
                        this.getDate();
                        this.$Message.success("更新成功");
                     }
                     if(!res.result){
                      this.$Message.error("名称已存在，请重新添加");
                     }
                    
                });
               
               },
               handleSubmit(){
                this.formQuery.bsName=this.formQuery.bsName.trim();
                this.api.lab.equipment.list(this.formQuery).then((res) =>{
                    if(res.result) {
                            this.datagrid.data = res.data;
                        }else {
                            this.$Message.error(res.msg);
                        }
                        });
                    
                },
                changePage (pageSize) {
                    this.start=(pageSize-1)*this.datagrid.queryParams.rows;
                    this.end=pageSize*this.datagrid.queryParams.rows;
                    this.getDate();
                },
                chageSize(pageSizeOpts){
                    this.datagrid.queryParams.rows=pageSizeOpts;
                    this.start=0;
                    this.end=pageSizeOpts;
                    this.getDate();
               }
        }
    }
</script>