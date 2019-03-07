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
                            <Row>
                                    <i-col span="24">
                                        <Form-item label="上级节点">
                                            <Input v-model="formItem.parentName" disabled placeholder="请输入"></Input>
                                        </Form-item>
                                    </i-col>
                                </Row>
                                <Row>
                                    <i-col span="12">
                                        <Form-item label="资源类型" prop="bsLeaf">
                                            <RadioGroup v-model="formItem.bsLeaf">
                                                <Radio :label="1">
                                                    <span>菜 单</span>
                                                </Radio>
                                                <Radio :label="0">
                                                    <span>目 录</span>
                                                </Radio>
                                            </RadioGroup>
                                        </Form-item>
                                    </i-col>
        
                                    <i-col span="12">
                                        <Form-item label="序号" prop="bsSortNo">
                                            <InputNumber v-model="formItem.bsSortNo" :max="9999999" :min="1" placeholder="请输入" ></InputNumber>
                                        </Form-item>
                                    </i-col>
                                </Row>
                                <Row>
                                    <i-col span="24">
                                        <Form-item label="图标">
                                            <Input v-model="formItem.bsIconCls" placeholder="请输入"></Input>
                                        </Form-item>                            
                                    </i-col>
                                   
                                </Row>
                        <FormItem label="职务编码"  prop='bsCode'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsCode" placeholder="请输入职务编码" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="职务名称"  prop='bsName'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsName" placeholder="请输入职务名称" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="职务类别"  prop='pkJobType'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.pkJobType" placeholder="请输入职务类别" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="任职要求"  prop='bsJobReq'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsJobReq" placeholder="请输入任职要求" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="启用状态" prop='bsEnableState'>
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
                                <Row>
                                        <i-col span="24">
                                            <Form-item label="上级节点">
                                                <Input v-model="formItem.parentName" disabled placeholder="请输入"></Input>
                                            </Form-item>
                                        </i-col>
                                    </Row>
                                    <Row>
                                        <i-col span="12">
                                            <Form-item label="资源类型" prop="bsLeaf">
                                                <RadioGroup v-model="formItem.bsLeaf">
                                                    <Radio :label="1">
                                                        <span>菜 单</span>
                                                    </Radio>
                                                    <Radio :label="0">
                                                        <span>目 录</span>
                                                    </Radio>
                                                </RadioGroup>
                                            </Form-item>
                                        </i-col>
            
                                        <i-col span="12">
                                            <Form-item label="序号" prop="bsSortNo">
                                                <InputNumber v-model="formItem.bsSortNo" :max="9999999" :min="1" placeholder="请输入" ></InputNumber>
                                            </Form-item>
                                        </i-col>
                                    </Row>
                                    <Row>
                                        <i-col span="24">
                                            <Form-item label="图标">
                                                <Input v-model="formItem.bsIconCls" placeholder="请输入"></Input>
                                            </Form-item>                            
                                        </i-col>
                                       
                                    </Row>
                        <FormItem label="职务编码"  prop='bsCode'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsCode" placeholder="请输入职务编码" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="职务名称"  prop='bsName'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsName" placeholder="请输入职务名称" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                            <FormItem label="职务类别"  prop='pkJobType'>
                                <Row>
                                    <Col span="12" >
                                    <Input v-model="formItem.pkJobType" placeholder="请输入职务类别" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="任职要求"  prop='bsJobReq'>
                                <Row>
                                    <Col span="12" >
                                    <Input v-model="formItem.bsJobReq" placeholder="请输入任职要求" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="启用状态" prop='bsEnableState' >
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
        <i-col span="4">
            <Tree ref="tree" :data="tree.data"  class="layout-menu-left" @on-select-change="selectNode" ></Tree>
        </i-col>
        <i-col span="20">
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
          this.getTree();
        },
        computed:{
        ...mapState({
            menuData:state=>state.menuData  
        })
    },
    props:['langz'],
        data () {
            return {
                tree: {
                data: [{expand: true, title: '根目录', children:[]}]
            },
                start:0,
                end:15,
                dialog: {
                    ruleForm: {
                    bsCode: [
                        { required: true, message:"请输入编码", trigger: 'blur' }
                    ],
                    bsEnableState: [
                        { required: true, message:"请选择状态", trigger: 'blur' }
                    ],
                    bsJobReq: [
                        { required: true, message:"请输入任职要求", trigger: 'blur' }
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
                    bsCode:'',
                    bsName:'',
                    bsJobReq:"",
                    pkJobType:"",
                    bsEnableState:'',
                    pkParent:'',
                    parentName:'',
                    bsLeaf:'',
                    bsSortNo:'',
                    bsIconCls:''
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
                            title: '职务编码',
                            key: 'bsCode'
                        },
                        {
                            title: '职务名称',
                            key: 'bsName'
                        },
                        {
                            title: '职务类别',
                            key: 'pkJobType'
                        },
                        {
                            title: '任职要求',
                            key: 'bsJobReq'
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
            getTree() {
            this.api.hr.job.gettree({}).then((res) => {
                let data = [{id:-1, expand: true, title: '根目录', children: []}];
                if(res.result) {
                    
                    data[0].children = res.data;
                    this.tree.data = data;
                }
            });
        },

        selectNode(node) {
            this.datagrid.queryParams.pkParent = node[0].id;
            this.getDate();
        },
        getSelectedNodes() {
            this.formItem.pkParent = node[0].id
        },
            getDate(){
                this.api.hr.job.list(this.datagrid.queryParams).then((res) => {
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
                   this.api.hr.job.add(this.formItem).then((res) =>{
                       if(res.result){
                          this.getDate();
                          this.getTree();
                          this.$Message.success(this.$t('msaage.sucess'));
                       }else{
                        this.$Message.error(this.$t('msaage.failure'));
                       }
                   });
        },

        showAddDialog() {
            let node = this.$refs["tree"].getSelectedNodes();
            this.formItem ={};
            if(node.length==0) {
                this.formItem = {pkParent: -1, parentName: "根目录", bsLevel:1, bsEnableState:1};
            }else {
                let bsLevel = 1;
                if(node[0].id==-1) {
                    bsLevel = 1;
                }else {
                    bsLevel = node[0].attributes.bsLevel+1;
                }
                this.formItem = {pkParent: node[0].id, parentName: node[0].title, bsLevel:bsLevel, bsEnableState:1};
            }
            if(this.formItem.bsLevel<=2) {
                this.formItem.bsLeaf = 0;
            }
               this.dialog.modal_dialog = true;
        },

        cancel () {
            
        },

         delete:function(params){
                this.$Modal.confirm({
                title: "提示信息",
                content: this.$t('model.deleteContent'),
                loading: true,
                onOk: () => {
                    this.api.hr.job.delete(params.row).then((res)=>{
                        if(res.result) {
                            this.getDate();
                            this.getTree();
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
                let node = this.$refs["tree"].getSelectedNodes();
                this.dialog.modal_dialog1= true;
                this.formItem.bsCode=params.row.bsCode;
                this.formItem.bsName=params.row.bsName;
                this.formItem.pkJobType=params.row.pkJobType;
                this.formItem.bsJobReq=params.row.bsJobReq; 
                this.formItem.bsEnableState=params.row.bsEnableState;
                this.formItem.parentName=node[0].title;
                this.formItem.bsSortNo=params.row.bsSortNo;
                this.formItem.bsLevel=params.row.bsLevel;
                this.formItem.bsLeaf=params.row.bsLeaf;
                this.formItem.id=params.row.id;


               },
               
               update(params){
                this.api.hr.job.update(params).then((res) =>{
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
                this.api.hr.job.list(this.formQuery).then((res) =>{
                    if(res.result) {
                            this.datagrid.data = res.data;
                        }else {
                            this.$Message.error(res.msg);
                        }
                        });
                    
                },
                changePage(pageSize) {
                    this.datagrid.queryParams.page = pageSize;
                    // this.start=(pageSize-1)*this.datagrid.queryParams.rows;
                    // this.end=pageSize*this.datagrid.queryParams.rows;
                    this.getDate();
                  },
                  chageSize(pageSizeOpts) {
                    this.datagrid.queryParams.rows = pageSizeOpts;
                    // this.datagrid.queryParams.rows=pageSizeOpts;
                    // this.start=0;
                    // this.end=pageSizeOpts;
                    this.getDate();
                  }
        }
    }
</script>