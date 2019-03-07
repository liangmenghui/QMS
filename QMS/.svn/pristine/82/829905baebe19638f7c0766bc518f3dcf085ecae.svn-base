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
                        <FormItem label="部门编码"  prop='bsCode'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsCode" placeholder="请输入部门编码" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="部门名称"  prop='bsName'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsName" placeholder="请输入部门名称" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="部门简称"  prop='bsShortName'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsShortName" placeholder="请输入部门简称" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="部门类别"  prop='bsDeptType'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsDeptType" placeholder="请输入部门类别" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="负责人"  prop='pkPrincipal'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.pkPrincipal" placeholder="请输入负责人" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="撤销标识"  prop='bsHrFlag'>
                            <Row>
                                <Col span="12" >
                                <InputNumber :min=0 :max=9 v-model="formItem.bsHrFlag" style="width:210px" placeholder="请输入撤销标识" ></InputNumber>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="版本号"  prop='pkPrincipal'>
                            <Row>
                                <Col span="12" >
                                <InputNumber :min=0 :max=99999  style="width:210px" v-model="formItem.bsVNo" placeholder="请输入版本号" ></InputNumber>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="请输入版本名称"  prop='bsVName'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsVName" placeholder="请输入版本名称" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="生效日期"  prop='bsVActivate'>
                            <Row>
                                <Col span="12" >
                                <DatePicker  v-model="formItem.bsVActivate"   type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                                <!-- <Input v-model="formItem.bsVActivate" placeholder="请输入生效日期" ></Input> -->
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="失效日期"  prop='bsVDeactivate'>
                            <Row>
                                <Col span="12" >
                                <DatePicker  v-model="formItem.bsVDeactivate"   type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                                <!-- <Input v-model="formItem.bsVDeactivate" placeholder="请输入失效日期" ></Input> -->
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="当前版本"  prop='bsVCurrent'>
                                <Row>
                                    <Col span="12" >
                                    <Input v-model="formItem.bsVCurrent" placeholder="请输入当前版本" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                        <FormItem label="成立日期"  prop='bsBuildDate'>
                             <Row>
                                <Col span="12" >
                                    <DatePicker  v-model="formItem.bsBuildDate"  type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                                <!-- <Input v-model="formItem.bsBuildDate" placeholder="请输入成立日期" ></Input> -->
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
                            <FormItem label="部门编码"  prop='bsBuildDate'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsCode" placeholder="请输入部门编码" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="部门名称"  prop='bsName'>
                            <Row>
                                <Col span="12" >
                                <Input v-model="formItem.bsName" placeholder="请输入部门名称" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                            <FormItem label="部门简称"  prop='bsShortName'>
                                <Row>
                                    <Col span="12" >
                                    <Input v-model="formItem.bsShortName" placeholder="请输入部门简称" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="部门类别"  prop='bsDeptType'>
                                <Row>
                                    <Col span="12" >
                                    <Input v-model="formItem.bsDeptType" placeholder="请输入部门类别" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="负责人"  prop='pkPrincipal'>
                                <Row>
                                    <Col span="12" >
                                    <Input v-model="formItem.pkPrincipal" placeholder="请输入负责人" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="撤销标识"  prop='bsHrFlag'>
                                <Row>
                                    <Col span="12" >
                                    <InputNumber style="width:210px"  :min=0 :max=9 v-model="formItem.bsHrFlag" placeholder="请输入撤销标识" ></InputNumber>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="版本号"  prop='bsVNo'>
                                <Row>
                                    <Col span="12" >
                                    <InputNumber style="width:210px" :min=0 :max=10000 v-model="formItem.bsVNo" placeholder="请输入版本号" ></InputNumber>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="请输入版本名称"  prop='bsVName'>
                                <Row>
                                    <Col span="12" >
                                    <Input v-model="formItem.bsVName" placeholder="请输入版本名称" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="生效日期"  prop='bsVActivate'>
                                <Row>
                                    <Col span="12" >
                                        <DatePicker  v-model="formItem.bsVActivate"   type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                                    <!-- <Input v-model="formItem.bsVActivate" placeholder="请输入生效日期" ></Input> -->
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="失效日期"  prop='bsVDeactivate'>
                                <Row>
                                    <Col span="12" >
                                        <DatePicker  v-model="formItem.bsVDeactivate"   type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                                    <!-- <Input v-model="formItem.bsVDeactivate" placeholder="请输入失效日期" ></Input> -->
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="当前版本"  prop='bsVCurrent'>
                                    <Row>
                                        <Col span="12" >
                                        <Input v-model="formItem.bsVCurrent" placeholder="请输入当前版本" ></Input>
                                        </Col>
                                </Row>
                                </FormItem>
                            <FormItem label="成立日期"  prop='bsBuildDate'>
                                 <Row>
                                    <Col span="12" >
                                        <DatePicker  v-model="formItem.bsBuildDate"  type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                                    <!-- <Input v-model="formItem.bsBuildDate" placeholder="请输入成立日期" ></Input> -->
                                    </Col>
                                 </Row>
                            </FormItem>
                            <FormItem label="启用状态"  prop='bsEnableState'>
                                    <Row>
                                        <Col span="12" >
                                            <Select v-model="formItem.bsEnableState"  >
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
    <!-- <Row>
            <i-col span="24">
                <Table :data="datagrid.data.rows"  width=auto :columns="datagrid.columns" stripe style="height:auto;"></Table>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page :total="datagrid.data.total" @on-change="changePage"  @on-page-size-change="chageSize"  :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination"  show-total show-elevator show-sizer></Page>
                    </div>
                </div>
            </i-col>
        </Row> -->
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
                end:25,
                dialog: {
                    ruleForm: {
                    bsCode: [
                        { required: true, message: "请输入编码", trigger: 'blur' }
                    ],
                    bsEnableState: [
                        { required: true, message: "请选择状态", trigger: 'blur' }
                    ],
                    bsHrFlag: [
                        { required: true, message: "请选择标志", trigger: 'blur' }
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
                    bsDeptType:"",
                    pkPrincipal:"",
                    bsHrFlag:"",
                    bsVNo:"",
                    bsVName:"",
                    bsVActivate:'',
                    bsVDeactivate:'',
                    bsVCurrent:'',
                    bsBuildDate:"",
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
                        rows:25,
                        pkParent:-1
                    },
                    pagination: [25, 50, 100],
                    data: {rows:[],total:0,rows1:[]},
                    columns: [
                      {
                            title: '部门编码',
                            key: 'bsCode',
                            width: 200
                        },
                        {
                            title: '部门名称',
                            key: 'bsName',
                            width: 200
                        },
                        {
                            title: '部门简称',
                            key: 'bsShortName',
                            width: 200
                        },
                        {
                            title: '部门类别',
                            key: 'bsDeptType',
                            width: 200
                        },
                        {
                            title: '负责人',
                            key: 'pkPrincipal',
                            width: 200
                        },
                        {
                            title: '撤销标识',
                            key: 'bsHrFlag',
                            width: 200
                        },
                        {
                            title: '版本号',
                            key: 'bsVNo',
                            width: 100
                        },
                        {
                            title: '版本名称',
                            key: 'bsVName',
                            width: 200
                        },
                        {
                            title: '生效日期',
                            key: 'bsVActivate',
                            width: 200
                        },
                        {
                            title: '失效日期',
                            key: 'bsVDeactivate',
                            width: 200
                        },
                        {
                            title: '当前版本',
                            key: 'bsVCurrent',
                            width: 200
                        },
                        {
                            title: '成立日期',
                            key: 'bsBuildDate',
                            width: 200
                        },
                        {
                            title: "启用状态",
                            key: 'bsEnableState',
                            width: 200,
                            render: (h, params) => {
                                return h('span', this.dict.status[params.row.bsEnableState].name);
                            }
                        },
                        {
                            title: this.$t('labSystem.eqManagement.action'),
                            key: 'action',
                            width: 200,
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
            this.api.hr.dept.gettree({}).then((res) => {
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
                this.api.hr.dept.list(this.datagrid.queryParams).then((res) => {
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
                   this.api.hr.dept.add(this.formItem).then((res) =>{
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
                title:"提示信息",
                content: this.$t('model.deleteContent'),
                loading: true,
                onOk: () => {
                    this.api.hr.dept.delete(params.row).then((res)=>{
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
                this.formItem.bsShortName=params.row.bsShortName;
                this.formItem.bsDeptType=params.row.bsDeptType;
                this.formItem.pkPrincipal=params.row.pkPrincipal;
                this.formItem.bsHrFlag=params.row.bsHrFlag;
                this.formItem.bsEnableState=params.row.bsEnableState;
                this.formItem.bsVNo=params.row.bsVNo;
                this.formItem.bsVName=params.row.bsVName;
                this.formItem.bsVActivate=params.row.bsVActivate;
                this.formItem.bsVDeactivate=params.row.bsVDeactivate;
                this.formItem.bsVCurrent=params.row.bsVCurrent;
                this.formItem.bsBuildDate=params.row.bsBuildDate;
                this.formItem.parentName=node[0].title;
                this.formItem.bsSortNo=params.row.bsSortNo;
                this.formItem.bsLevel=params.row.bsLevel;
                this.formItem.bsLeaf=params.row.bsLeaf;
                this.formItem.id=params.row.id;


               },
               
               update(params){
                this.api.hr.dept.update(params).then((res) =>{
                    if(res.result){
                        this.getDate();
                        this.getTree();
                        this.$Message.success("更新成功");
                     }
                     if(!res.result){
                      this.$Message.error("名称已存在，请重新添加");
                     }
                    
                });
               
               },
               handleSubmit(){
                this.formQuery.bsName=this.formQuery.bsName.trim();
                this.api.hr.dept.list(this.formQuery).then((res) =>{
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