<template>
        <div>
    <div>
        <Row>
                <i-col>
                    <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                            <Form-item >
                                    <Input v-model="formQuery.bsCode" placeholder="请输入编码" ></Input>
                               </Form-item>
                               <Form-item >
                                    <Input v-model="formQuery.bsName" placeholder="请输入名称" ></Input>
                               </Form-item>
                        <Form-item >
                            <Button type="primary" @click="handleSubmit()">{{$t('button.search')}}</Button>
                        </Form-item> 
                        <Form-item >
                            <Button type="primary" @click="showAddDialog()" >{{$t('button.add')}}</Button>
                        </Form-item>
                    </Form>
                </i-col>
            </Row>
        </div>
        <Modal v-model="dialog.modal_dialog"  title="" @on-ok="addok" @on-cancel="cancel" >
                <p>
            <div >
                <Row>
                    <i-col>
                    <Form :model="formItem" :label-width="80" style="text-align:center"  :ref="dialog.ruleForm" :rules="dialog.ruleForm" >
                         <FormItem label="工种编码"  prop='bsCode'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsCode" placeholder="请输入工种编码" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="工种名称"  prop='bsName'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsName" placeholder="请输入工种名称" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="工种简称"  prop='bsShortName'>
                                <Row>
                                    <Col span="12" >
                                    <Input v-model="formItem.bsShortName" placeholder="请输入工种简称" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                        <FormItem label="启用状态"  prop='bsEnableState'>
                                <Row>
                                    <Col span="12" >
                                        <Select v-model="formItem.bsEnableState" placeholder="请选择" >
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
                        <Form :model="formItem" :label-width="80" style="text-align:center"  :ref="dialog.ruleForm" :rules="dialog.ruleForm" >
                         <FormItem label="工种编码"  prop='bsCode'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsCode" placeholder="请输入工种编码" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="工种名称"  prop='bsName'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsName" placeholder="请输入工种名称" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                            
                            <FormItem label="工种简称"  prop='bsShortName'>
                                    <Row>
                                        <Col span="12" >
                                        <Input v-model="formItem.bsShortName" placeholder="请输入工种简称" ></Input>
                                        </Col>
                                </Row>
                                </FormItem>
                            <FormItem label="启用状态"  prop='bsEnableState'>
                                    <Row>
                                        <Col span="12" >
                                            <Select v-model="formItem.bsEnableState" placeholder="请选择" >
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
                <Table :data="datagrid.data.rows"  width=auto :columns="datagrid.columns" stripe style="height:auto;"></Table>
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
                    bsCode: [
                        { required: true, message: "请输入编码", trigger: 'blur' }
                    ],
                    bsEnableState: [
                        { required: true, message: "请选择状态", trigger: 'blur' }
                    ]
                },
                modal_dialog: false,
                modal_dialog1:false
                },
                dict: {
                    status:[{value: 0, name:'可用'},{value: 1, name:'不可用'}]
                },
                formQuery:
                {
                    bsCode: '',
                    bsName: ''
                },
                isShow:false,
                formItem:{
                    id:"",
                    bsShortName:'',
                    bsCode:'',
                    bsName:'',
                    bsEnableState:"",
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
                            title: '工种编码',
                            key: 'bsCode'
                        },
                        {
                            title: '工种名称',
                            key: 'bsName'
                        },
                        {
                            title: '工种简称',
                            key: 'bsShortName'
                        },
                       
                        {
                            title: "启用状态",
                            key: 'bsEnableState',
                            render: (h, params) => {
                                return h('span', this.dict.status[params.row.bsEnableState].name);
                            }
                        },
                        {
                            title: this.$t('labSystem.eqManagement.action'),
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
                                    }, this.$t('button.modify')));
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
                                    }, this.$t('button.delete')))
                                }
                                return h('div', ary);
                            }
                        }
                    ]
                },
                }
        },
        
        methods:{
            getDate(){
                this.api.hr.profession.list().then((res) => {
                    if(res.result) {
                        this.datagrid.data.rows1=this.datagrid.data.rows;
                        this.datagrid.data.rows = res.data.rows.slice(this.start,this.end);
                        this.datagrid.data.total=res.data.total;
                       }else {
                        this.$Message.error(res.msg);
                      }
                });
             },
             addok(){ 
                   this.api.hr.profession.add(this.formItem).then((res) =>{
                       if(res.result){
                          this.getDate();
                          this.$Message.success(this.$t('msaage.sucess'));
                       }else{
                        this.$Message.error(this.$t('msaage.failure'));
                       }
                   });
        },

        showAddDialog() {
               this.dialog.modal_dialog = true;
               this.formItem ={};
        },

        cancel () {
            
        },

         delete:function(params){
                this.$Modal.confirm({
                title: "提示信息",
                content: this.$t('model.deleteContent'),
                loading: true,
                onOk: () => {
                    this.api.hr.profession.delete(params.row).then((res)=>{
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
                this.formItem.bsCode=params.row.bsCode;
                this.formItem.bsName=params.row.bsName;
                this.formItem.bsShortName=params.row.bsShortName;
                this.formItem.bsEnableState=params.row.bsEnableState;
                this.formItem.id=params.row.id;


               },
               
               update(params){
                this.api.hr.profession.update(params).then((res) =>{
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
                this.api.hr.profession.list(this.formQuery).then((res) =>{
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