<template>
    <div>
        <div>
            <Row>
                <i-col>
                    <Form class="query_area" ref="formQuery" :model="formQuery"   inline>
                        <Form-item >
                            <Input v-model="formQuery.cdSendDate" placeholder="请输入发货日期" style="width:150px"></Input>
                        </Form-item>
                        <div style="display:inline-block;">
                            <p style="width:50px;display:inline-block;text-align:right;margin-right:20px;">运输路线</p>
                            <Select v-model="formQuery.cdTransCode" style="width:180px" filterable @on-change="changeSelect">
                                <Option v-for="item in bsTransportCodelist" :value="item.bsTransportCode" :key="item.bsTransportCode">{{item.desc}}</Option>
                            </Select>
                        </div>
                        <Form-item >
                            <Button type="primary" @click="handleSubmit()">查 询</Button>
                        </Form-item> 
                    </Form>
                </i-col>
            </Row>
        </div>
        <Row>
            <i-col span="24">
                <Table :data="datagrid.data.rows" :columns="datagrid.columns"   @on-expand='expandChange'  stripe style="height:auto;"></Table>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page :total="datagrid.data.total" placement="top" :current="1" @on-change="changePage"  @on-page-size-change="chageSize" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination"  show-total show-elevator show-sizer ></Page>
                    </div>
                </div>
            </i-col>
        </Row>
        <Modal v-model="dialog.modal_dialog" :mask-closable="false" :closable="false" title="">
                <div slot="footer">
                    <Button type="primary" size="large" @click="testShipment(titlelist)">拼柜测试</Button>
                    <Button type="primary" size="large" @click="editPlanCancel()">取消</Button>
                    <Button type="primary" size="large" @click="saveShipment(titlelist)">确定</Button>
                </div>
                <Form :model="dialog.formItem" :label-width="100" ref= "dialog.formItem"  >
                    <i-col v-for="item in titlelist" :key="item.cdCustomerItemNum" >
                        <Form-item :label="item.cdCustomerItemNum">
                            <Input-number v-model="item.cdSendNum"  placeholder="请输入" style="width:80%"></Input-number>
                            <Poptip placement="right" width="250">
                                <Button @click="querySumOfWeek(item)" type="ghost">详情</Button>
                                <div class="api" slot="content">
                                    <Table :data="datagrid2.data.rows" :columns="datagrid2.columns"></Table>
                                </div>
                            </Poptip>
                        </Form-item>
                    </i-col>
                    <Form-item label="拼柜系数" prop="ratio">
                        <Input style="width:80%" v-model="dialog.formItem.ratio" readonly></Input>
                    </Form-item>
                </Form>
        </Modal>
   </div>
</template>
<script>
import {mapState} from 'vuex';
export default {
    created(){
    //   this.getData();
      this.getTransCode();
      this.getYearWeek();
    },
    computed:{
        ...mapState({
            menuData:state=>state.menuData  
        })
    },
    data () {
        return {
            titlelist:[],
            bsTransportCodelist: [],
            cdYearWeekList: [],
            formQuery:{
                cdSendDate:'',
                cdTransCode:''
            },
            dialog: {
                modal_dialog: false,
                formItem:{
                   ratio:0,
                },
            },
            datagrid2: {
                data: {rows:[],total:0,rows1:[]},
                columns: [
                    {
                        title: '日期',
                        key: 'tDate'
                    },
                    {
                        title: '库存周数',
                        key: 'numOfWeek',
                        render: (h, params) => {
                            return h('span',{
                              style:{
                                 color: (params.row.numOfWeek >= 8)?"#2d8cf0":(params.row.numOfWeek <= 2?"#ed3f14":"#000000")
                              }
                          },params.row.numOfWeek)
                        }
                    }
                    ]
            },
            datagrid: {
                queryParams:{
                    page:1,
                    rows:15,
                },
                pagination: [15, 50, 100],
                data: {rows:[],total:0,rows1:[]},
                columns: [],
            },
            }
    },
    methods:{
        pullColumsData(){
            this.datagrid.columns = [];
            let s={title:'出货日期',key:'cdSendDate'};
            this.datagrid.columns.push(s);
            Object.assign(this.formQuery, this.datagrid.queryParams);
            this.api.pmp.selectTitle(this.formQuery).then((res) =>{
                if(res.result) {
                    let list = [];
                    res.data.forEach(element => {
                        let ss={
                            key:'CU'+element.cdCustomerItemNum+'-'+element.cdBoxSize,
                            renderHeader: (h, index) => {
                                return h('div', {
                                    domProps: {
                                        innerHTML: element.cdCustomerItemNum+" <br>包装:"+element.cdBoxSize
                                    }
                                }); 
                            }
                        };
                        list.push({
                            cdCustomerItemNum:element.cdCustomerItemNum+"-"+element.cdBoxSize,
                            cdItemNo:element.cdItemNo,
                            cdCustomerNo:element.cdCustomerNo,
                        });
                        this.datagrid.columns.push(ss);
                    });
                    this.titlelist = list;
                }
                let sss = {
                        title:'操作',
                        key: 'action',
                        render: (h, params) => {
                            let buttons = [];
                            buttons.push(h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        this.showEditPlanDialog(params)
                                    }
                                }
                            }, '编辑'));
                        return h('div', buttons);
                        }
                    }
                this.datagrid.columns.push(sss);
            })
        },
        querySumOfWeek(item){
            this.api.pmp.querySumOfWeek(item).then((res) =>{
                if(res.result){
                    this.datagrid2.data = res.data;
                }else{
                    this.$Message.error(res.msg);
                }
            });
        },
        testShipment(params){
            console.log(params);
            this.api.pmp.testShipment(params).then((res) =>{
                if(res.result) {
                    console.log(res);
                    this.dialog.formItem.ratio = res.data;
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        saveShipment(params){
            this.api.pmp.saveShipment(params).then((res) =>{
                if(res.result) {
                    this.dialog.modal_dialog = false;
                    this.pullColumsData();
                    this.getData();
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        //获取列表
        getData(){
            Object.assign(this.formQuery, this.datagrid.queryParams);
            this.api.pmp.getShipmentPlanEdit(this.formQuery).then((res) =>{
                if(res.result) {
                    // console.log(res.data);
                    this.datagrid.data = res.data;
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        //查 询
        handleSubmit(){
            this.getData();
        },
        showEditPlanDialog(params){
            this.dialog.modal_dialog = true;
            let r = params.row;
            for(let jj in this.titlelist) {
                this.titlelist[jj].cdSendDate = r.cdSendDate;
                this.titlelist[jj].cdTransCode = r.cdTransCode;
                let check = 0;
                for(let ii in r){
                    if('CU'+this.titlelist[jj].cdCustomerItemNum == ii){
                        this.titlelist[jj].cdSendNum = parseInt(r[ii]);
                        check = 1;
                    }
                }
                if(check == 0){
                    this.titlelist[jj].cdSendNum = 0;
                }
            }
        },
        editPlanCancel(){
            this.dialog.modal_dialog = false;
            this.$refs['dialog.formItem'].resetFields();
        },
        //换页
        changePage (page) {
            this.datagrid.queryParams.page = page;
            this.getData();
        },
        //改变每页显示数据
        chageSize(pageSize){    
            this.datagrid.queryParams.rows = pageSize;
            this.getData();
        },
        changeSelect(){
            this.pullColumsData();
            this.getData();
        },
        //点击扩展
        expandChange(rows, status) {},
        //获取所有的运输路线
        getTransCode(){
            this.api.pmp.selectTransCode().then((res) =>{
                if(res.result) {
                    let list = [];
                    res.data.forEach(element => {
                        list.push({
                        bsTransportCode: element.bsTransportCode,
                        desc: element.bsTransportCode
                        });
                    });
                    this.bsTransportCodelist = list;
                }
            })
        },
        //获取所有的数据周数
        getYearWeek(){
            this.api.pmp.selectYearWeek().then((res) =>{
                if(res.result) {
                    let list = [];
                    res.data.forEach(element => {
                        list.push({
                        cdYearWeek: element.cdYearWeek,
                        desc: element.cdYearWeek
                        });
                    });
                    this.cdYearWeekList = list;
                }
            })
        },
 },
}
</script>