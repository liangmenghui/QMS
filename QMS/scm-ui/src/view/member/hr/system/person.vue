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
                            <Button type="primary" @click="handleSubmit()">查询</Button>
                        </Form-item> 
                        <Form-item >
                            <Button type="primary" @click="showAddDialog()" >新增</Button>
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
                    <!-- <i-col span="8"> -->
                         <FormItem label="人员编码"  prop='bsCode'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsCode" placeholder="请输入人员编码" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="人员名称"  prop='bsName'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsName" placeholder="请输入人员名称" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                    </FormItem>
                    <FormItem label="所属部门"  prop='pkDept'>
                        <Row>
                            <Col span="20" >
                            <!-- <Input v-model="formItem.pkDept" placeholder="请输入所属部门" ></Input> -->
                            <!-- <Select v-model="formItem.pkDept"  >
                                    <Option v-for="item in deptList"  :key="item.id" :value="item.bsName"  >{{item.bsName}}</Option>
                            </Select> -->
                            <Input v-model="formItem.pkDept" >
                                <Button slot="append" icon="ios-search" @click="hello1()"></Button>
                            </Input>
                            </Col>
                    </Row>
                    </FormItem>
                    <FormItem label="所属职位"  prop='pkJob'>
                        <Row>
                            <Col span="20" >
                            <!-- <Input v-model="formItem.pkJob" placeholder="请输入所属职务" ></Input> -->
                            <!-- <Select v-model="formItem.pkJob"  >
                                    <Option v-for="item in positionList"  :key="item.id" :value="item.bsName"  >{{item.bsName}}</Option>
                            </Select> -->
                            <Input v-model="formItem.pkJob" >
                                <Button slot="append" icon="ios-search" @click="hello2()"></Button>
                            </Input>
                            </Col>
                    </Row>
                    </FormItem>
                        <FormItem label="性别"  prop='bsGrender'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsGrender" placeholder="请输入性别" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="曾用名"  prop='bsAlias'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsAlias" placeholder="请输入曾用名" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="证件号"  prop='bsIdCard'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsIdCard" placeholder="请输入证件号" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="证件类型"  prop='bsIdType'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsIdType" placeholder="请输入证件类型" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="办公电话"  prop='bsOfficeTel'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsOfficeTel" placeholder="请输入办公电话" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="家庭电话"  prop='bsHomeTel'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsHomeTel" placeholder="请输入家庭电话" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="移动电话"  prop='bsMobile'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsMobile" placeholder="请输入移动电话" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="出生日期"  prop='bsBornDate'>
                            <Row>
                                <Col span="20" >
                                <DatePicker  v-model="formItem.bsBornDate"   type="datetime" placeholder="请选择" style="width: 350px"></DatePicker>
                                <!-- <Input v-model="formItem.bsVActivate" placeholder="请输入生效日期" ></Input> -->
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="入职日期"  prop='bsEntryDate'>
                            <Row>
                                <Col span="20" >
                                <DatePicker  v-model="formItem.bsEntryDate"   type="datetime" placeholder="请选择" style="width: 350px"></DatePicker>
                                <!-- <Input v-model="formItem.bsVDeactivate" placeholder="请输入失效日期" ></Input> -->
                                </Col>
                        </Row>
                        </FormItem>
                        <FormItem label="邮箱地址"  prop='bsEmail'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.bsEmail" placeholder="请输入邮箱地址" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="学历"  prop='bsEducation'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.bsEducation" placeholder="请输入学历" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="家庭住址"  prop='pkHomeAddr'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.pkHomeAddr" placeholder="请输入家庭住址" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="办公住址"  prop='pkOfficeAddr'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.pkOfficeAddr" placeholder="请输入办公住址" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="工作组织"  prop='pkOrgJob'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.pkOrgJob" placeholder="请输入工作组织" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                        <FormItem label="启用状态" >
                                <Row>
                                    <Col span="20" >
                                        <Select v-model="formItem.bsEnableState" placeholder="请选择" >
                                                <Option value="0" >可用</Option>
                                                <Option value="1">不可用</Option>
                                        </Select>
                                    </Col>
                            </Row>
                        </FormItem>
                        <FormItem label="离职状态" >
                                <Row>
                                    <Col span="20" >
                                        <Select v-model="formItem.bsDimissionState" placeholder="请选择" >
                                                <Option value="0" >在职</Option>
                                                <Option value="1">离职</Option>
                                        </Select>
                                    </Col>
                            </Row>
                        </FormItem>
                        <FormItem label="婚姻状态" >
                                <Row>
                                    <Col span="20" >
                                        <Select v-model="formItem.bsMarriedState" placeholder="请选择" >
                                                <Option value="0" >未婚</Option>
                                                <Option value="1">已婚</Option>
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
                                <FormItem label="人员编码"  prop='bsCode'>
                                        <Row>
                                            <Col span="20" >
                                            <Input v-model="formItem.bsCode" placeholder="请输入人员编码" ></Input>
                                            </Col>
                                    </Row>
                                    </FormItem>
                                    <FormItem label="人员名称"  prop='bsName'>
                                        <Row>
                                            <Col span="20" >
                                            <Input v-model="formItem.bsName" placeholder="请输入人员名称" ></Input>
                                            </Col>
                                    </Row>
                                    </FormItem>
                                </FormItem>
                                <FormItem label="所属部门"  prop='pkDept'>
                                        <Row>
                                            <Col span="20" >
                                            <!-- <Input v-model="formItem.pkDept" placeholder="请输入所属部门" ></Input> -->
                                            <!-- <Select v-model="formItem.pkDept"  >
                                                    <Option v-for="item in deptList"  :key="item.id" :value="item.bsName"  >{{item.bsName}}</Option>
                                            </Select> -->
                                            <Input v-model="formItem.pkDept" >
                                                <Button slot="append" icon="ios-search" @click="hello1()"></Button>
                                            </Input>
                                            </Col>
                                    </Row>
                                    </FormItem>
                                    <FormItem label="所属职位"  prop='pkJob'>
                                        <Row>
                                            <Col span="20" >
                                            <!-- <Input v-model="formItem.pkJob" placeholder="请输入所属职务" ></Input> -->
                                            <!-- <Select v-model="formItem.pkJob"  >
                                                    <Option v-for="item in positionList"  :key="item.id" :value="item.bsName"  >{{item.bsName}}</Option>
                                            </Select> -->
                                            <Input v-model="formItem.pkJob" >
                                                <Button slot="append" icon="ios-search" @click="hello2()"></Button>
                                            </Input>
                                            </Col>
                                    </Row>
                                    </FormItem>
                            <FormItem label="性别"  prop='bsGrender'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.bsGrender" placeholder="请输入性别" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="曾用名"  prop='bsAlias'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.bsAlias" placeholder="请输入曾用名" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="证件号"  prop='bsIdCard'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.bsIdCard" placeholder="请输入证件号" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="证件类型"  prop='bsIdType'>
                            <Row>
                                <Col span="20" >
                                <Input v-model="formItem.bsIdType" placeholder="请输入证件类型" ></Input>
                                </Col>
                        </Row>
                        </FormItem>
                            <FormItem label="办公电话"  prop='bsOfficeTel'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.bsOfficeTel" placeholder="请输入办公电话" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="家庭电话"  prop='bsHomeTel'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.bsHomeTel" placeholder="请输入家庭电话" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="移动电话"  prop='bsMobile'>
                                <Row>
                                    <Col span="20" >
                                    <Input v-model="formItem.bsMobile" placeholder="请输入移动电话" ></Input>
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="出生日期"  prop='bsBornDate'>
                                <Row>
                                    <Col span="20" >
                                    <DatePicker  v-model="formItem.bsBornDate"   type="datetime" placeholder="请选择" style="width: 350px"></DatePicker>
                                    <!-- <Input v-model="formItem.bsVActivate" placeholder="请输入生效日期" ></Input> -->
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="入职日期"  prop='bsEntryDate'>
                                <Row>
                                    <Col span="20" >
                                    <DatePicker  v-model="formItem.bsEntryDate"   type="datetime" placeholder="请选择" style="width: 350px"></DatePicker>
                                    <!-- <Input v-model="formItem.bsVDeactivate" placeholder="请输入失效日期" ></Input> -->
                                    </Col>
                            </Row>
                            </FormItem>
                            <FormItem label="邮箱地址"  prop='bsEmail'>
                                    <Row>
                                        <Col span="20" >
                                        <Input v-model="formItem.bsEmail" placeholder="请输入邮箱地址" ></Input>
                                        </Col>
                                </Row>
                                </FormItem>
                                <FormItem label="学历"  prop='bsEducation'>
                                    <Row>
                                        <Col span="20" >
                                        <Input v-model="formItem.bsEducation" placeholder="请输入学历" ></Input>
                                        </Col>
                                </Row>
                                </FormItem>
                                <FormItem label="家庭住址"  prop='pkHomeAddr'>
                                    <Row>
                                        <Col span="20" >
                                        <Input v-model="formItem.pkHomeAddr" placeholder="请输入家庭住址" ></Input>
                                        </Col>
                                </Row>
                                </FormItem>
                                <FormItem label="办公住址"  prop='pkOfficeAddr'>
                                    <Row>
                                        <Col span="20" >
                                        <Input v-model="formItem.pkOfficeAddr" placeholder="请输入办公住址" ></Input>
                                        </Col>
                                </Row>
                                </FormItem>
                                <FormItem label="工作组织"  prop='pkOrgJob'>
                                    <Row>
                                        <Col span="20" >
                                        <Input v-model="formItem.pkOrgJob" placeholder="请输入工作组织" ></Input>
                                        </Col>
                                </Row>
                                </FormItem>
                            <FormItem label="启用状态"  prop='bsEnableState'>
                                    <Row>
                                        <Col span="20" >
                                            <Select v-model="formItem.bsEnableState" placeholder="请选择" >
                                                    <Option value="0" >可用</Option>
                                                    <Option value="1">不可用</Option>
                                            </Select>
                                        </Col>
                                </Row>
                            </FormItem>
                            <FormItem label="离职状态"  prop='bsDimissionState'>
                                    <Row>
                                        <Col span="20" >
                                            <Select v-model="formItem.bsDimissionState" placeholder="请选择" >
                                                    <Option value="0" >在职</Option>
                                                    <Option value="1">离职</Option>
                                            </Select>
                                        </Col>
                                </Row>
                            </FormItem>
                            <FormItem label="婚姻状态" >
                                    <Row>
                                        <Col span="20" >
                                            <Select v-model="formItem.bsMarriedState" placeholder="请选择" >
                                                    <Option value="0" >未婚</Option>
                                                    <Option value="1">已婚</Option>
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

<Modal  v-model="dialog.modal_dialog2" title="" @on-ok="ok2" @on-cancel="cancel2" >
    <div style="height:1000px">
        <i-col span="20">
                <Tree ref="tree" :data="tree.data"  class="layout-menu-left" @on-select-change="selectNode" height="auto"></Tree>
        </i-col>
    </div>
</Modal>

<Modal  v-model="dialog.modal_dialog3" title="" @on-ok="ok3" @on-cancel="cancel3" >
        <div style="height:800px">
            <i-col span="20">
                    <Tree ref="tree1" :data="tree1.data"  class="layout-menu-left" @on-select-change="selectNode1" height="auto"></Tree>
            </i-col>
        </div>
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
          this.getDeptList();
          this.getPositionList();
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
            tree1: {
                data: [{expand: true, title: '根目录', children:[]}]
            },
                deptList:[],
                positionList:[],
                start:0,
                end:15,
                dialog: {
                    ruleForm: {
                    bsCode: [
                        { required: true, message: "请输入编码", trigger: 'blur' }
                    ],
                    bsDimissionState: [
                        { required: true, message: "请选择状态", trigger: 'blur' }
                    ],
                    bsEnableState: [
                        { required: true, message: "请选择状态", trigger: 'blur' }
                    ]
                },
                modal_dialog: false,
                modal_dialog1:false,
                modal_dialog2:false,
                modal_dialog3:false
                },
                dict: {
                    status:[{value: 0, name:'可用'},{value: 1, name:'不可用'}],
                    status1:[{value: 0, name:'在职'},{value: 1, name:'离职'}],
                    status2:[{value: 0, name:'未婚'},{value: 1, name:'已婚'}],
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
                    bsGrender:"",
                    bsAlias:"",
                    bsBornDate:"",
                    bsIdType:"",
                    bsIdCard:"",
                    bsEntryDate:'',
                    bsOfficeTel:'',
                    bsHomeTel:'',
                    bsMobile:"",
                    bsEmail:'',
                    bsEnableState:'',
                    bsDimissionState:'',
                    bsEducation:'',
                    bsMarriedState:'',
                    pkHomeAddr:'',
                    pkOfficeAddr:'',
                    pkOrgJob:'',
                    pkDept:"",
                    pkJob:'',
                    
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
                            title: '人员编码',
                            key: 'bsCode',
                            width: 200
                        },
                        {
                            title: '人员名称',
                            key: 'bsName',
                            width: 200
                        },
                        {
                            title: '所属部门',
                            key: 'pkDept',
                            width: 200
                        },
                        {
                            title: '所属职位',
                            key: 'pkJob',
                            width: 200
                        },
                        {
                            title: '性别',
                            key: 'bsGrender',
                            width: 200
                        },
                        {
                            title: '曾用名',
                            key: 'bsAlias',
                            width: 200
                        },
                        {
                            title: '出生日期',
                            key: 'bsBornDate',
                            width: 200
                        },
                        {
                            title: '证件类型',
                            key: 'bsIdType',
                            width: 200
                        },
                        {
                            title: '证件号',
                            key: 'bsIdCard',
                            width: 200
                        },
                        {
                            title: '入职日期',
                            key: 'bsEntryDate',
                            width: 200
                        },
                        {
                            title: '办公电话',
                            key: 'bsOfficeTel',
                            width: 200
                        },
                        {
                            title: '家庭电话',
                            key: 'bsHomeTel',
                            width: 200
                        },
                        {
                            title: '移动电话',
                            key: 'bsMobile',
                            width: 200
                        },
                        {
                            title: '邮箱地址',
                            key: 'bsEmail',
                            width: 200
                        },
                        {
                            title: '学历',
                            key: 'bsEducation',
                            width: 200
                        },
                        {
                            title: '家庭住址',
                            key: 'pkHomeAddr',
                            width: 200
                        },
                        {
                            title: '办公住址',
                            key: 'pkOfficeAddr',
                            width: 200
                        },
                        {
                            title: '工作组织',
                            key: 'pkOrgJob',
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
                            title: "离职状态",
                            key: 'bsEnableState',
                            width: 200,
                            render: (h, params) => {
                                return h('span', this.dict.status1[params.row.bsDimissionState].name);
                            }
                        },
                        {
                            title: "婚姻状态",
                            key: 'bsEnableState',
                            width: 200,
                            render: (h, params) => {
                                return h('span', this.dict.status2[params.row.bsMarriedState].name);
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
                this.datagrid.queryParams.pkParent = node[0].title;
                console.log(node);
            this.formItem.pkDept=this.datagrid.queryParams.pkParent;
            },
            getTree1() {
                this.api.hr.position.gettree({}).then((res) => {
                    let data = [{id:-1, expand: true, title: '根目录', children: []}];
                    if(res.result) {
                        
                        data[0].children = res.data;
                        this.tree1.data = data;
                    }
                });
            },

            selectNode1(node) {
                this.datagrid.queryParams.pkParent = node[0].title;
                console.log(node);
            this.formItem.pkJob=this.datagrid.queryParams.pkParent;
            },
            hello1(){
                this.dialog.modal_dialog2= true;
                this.getTree();
            },
            hello2(){
                this.dialog.modal_dialog3= true;
                this.getTree1();
            },
            cancel2(){
                this.formItem.pkDept="";
            },
            cancel3(){
                this.formItem.pkJob="";
            },
            getDate(){
                this.api.hr.person.list().then((res) => {
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
                   this.api.hr.person.add(this.formItem).then((res) =>{
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
                        this.api.hr.person.delete(params.row).then((res)=>{
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
                this.formItem.bsGrender=params.row.bsGrender;
                this.formItem.bsAlias=params.row.bsAlias;
                this.formItem.bsBornDate=params.row.bsBornDate;
                this.formItem.bsIdType=params.row.bsIdType;
                this.formItem.bsIdCard=params.row.bsIdCard;
                this.formItem.bsEntryDate=params.row.bsEntryDate;
                this.formItem.bsOfficeTel=params.row.bsOfficeTel;
                this.formItem.bsHomeTel=params.row.bsHomeTel;
                this.formItem.bsMobile=params.row.bsMobile;
                this.formItem.bsEmail=params.row.bsEmail;
                this.formItem.bsEnableState=params.row.bsEnableState;
                this.formItem.bsDimissionState=params.row.bsDimissionState;
                this.formItem.bsEducation=params.row.bsEducation;
                this.formItem.bsMarriedState=params.row.bsMarriedState;
                this.formItem.pkHomeAddr=params.row.pkHomeAddr;
                this.formItem.pkOfficeAddr=params.row.pkOfficeAddr;
                this.formItem.pkOrgJob=params.row.pkOrgJob;
                this.formItem.pkDept=params.row.pkDept;
                this.formItem.pkJob=params.row.pkJob;
                this.formItem.id=params.row.id;


               },
               
               getDeptList(){
                    this.api.hr.dept.list().then((res) => {
                        if(res.result) {
                           this.deptList=res.data.rows;
                           }else {
                            this.$Message.error(res.msg);
                          }
                    });
                 },

                 getPositionList(){
                    this.api.hr.position.list().then((res) => {
                        if(res.result) {
                            this.positionList=res.data.rows;
                            console.log(this.positionList);
                           }else {
                            this.$Message.error(res.msg);
                          }
                    });
                 },

               update(params){
                this.api.hr.person.update(params).then((res) =>{
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
                this.api.hr.person.list(this.formQuery).then((res) =>{
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