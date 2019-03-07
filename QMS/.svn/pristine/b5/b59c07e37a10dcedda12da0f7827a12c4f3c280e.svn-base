<template>
    <div>
            <Row>
                    <i-col span="24">
                        <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe style="height:100px;"></Table>
                        
                    </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Table :data="datagrid.data1.content" :columns="datagrid.columns1" stripe style="height:auto;"></Table>
                    </i-col>
                </Row>

                <br>
                <br>
                <Row>
                        <Form :model="Query" :ref="dialog.ruleForm" :rules="dialog.ruleForm" :label-width="80" >  
                         <!-- <Form-item label="测试项" prop="testx"  v-for="tab in this.formQuery.items"  v-show="tab.testx !=null "> 
                             <Input readonly="readonly" v-model="tab.testx" type="text" placeholder="请输入..." style="display:float;width:30%;"></Input>
                             时间用时: <Input readonly="readonly" v-model="tab.testTime" type="text" placeholder="请输入..." style="display:float;width:30%;"></Input>
                             是否可用设备:<Input readonly="readonly" v-model="tab.eqName" type="text" placeholder="请输入..." style="display:float;width:30%;"></Input>
                             </Form-item>  -->
                         <Form-item label="总用时" prop="bsCostTime" >
                                <Input readonly="readonly" v-model="formQuery.bsCostTime" type="text" placeholder="请输入..."></Input>
                         </Form-item>
                         <Form-item label="实验计划开始时间" prop="bsSampleStatrtTime">
                                <DatePicker readonly="readonly" v-model="formQuery.bsSampleStatrtTime" type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                        </Form-item>
                        <Form-item label="预计完成时间" prop="bsCompTime">
                                <DatePicker readonly="readonly" v-model="formQuery.bsCompTime" type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                        </Form-item>
                        <Form-item label="测试人员" prop="pkPerson">
                             <Input  readonly="readonly" v-model="formQuery.pkPerson" type="text" placeholder="请输入测试人员..."></Input>
                        </Form-item>
                            </Form>
                </Row>
    </div>
</template>
<script>
import {mapState} from 'vuex'
export default {
    created(){
     
    },
    computed:{
        ...mapState({
            menuData:state=>state.menuData  
        })
    },
    data() {
        return {
            showM:false,
            form:{
                username:'',
                password:''
            },
          
            show:{
                '0':true
            },
            tabs:1,
            dialog: {
                ruleForm: {
                        // bsCostTime: [
                        // { required: true, message: '用时不能为空', trigger: 'blur' }
                    // ]
                },
                formItem:{                
                    bsCode:"",
                    bsName:'',
                    bsPartNo:''
                }
                },
            formQuery: {
                ATestx:[],
                AEqname:[],
                ATime:[],
                eqName:'',
                testx:'',
                bsSampleStatrtTime:'',
                bsCostTime:'',
                bsReportTime:'',
                pkPerson:'',
                items:[
                    {
                        // testTime:'',
                        // testx:'',
                        // eqName:''
                    }
                ]
            },
            num:2,
            formQu:{
               id:''
            },
            Query: {
                eqName:'',
                testTime:'',
                testx:''
            },
            dict: {
                bsStatus:[{value: 0, name:'待测试'},{value: 1, name:'测试中'},{value: 2, name:'已完成'},{value: 3, name:'已中止'}]
            },
            
            datagrid: {
                queryParams:{
                    page:1,
                    rows:25,
                    pkParent:-1
                },
                pagination: [25, 50, 100],
                data: {rows:[],total:0},
                data1: {rows:[],total:0},
                columns1:[
                {
                            title:'测试项',
                            key:'testx'
                        },
                        {
                            title:'测试设备',
                            key:'bsName'
                        },
                        {
                            title:'测试时间',
                            key:'testTime'
                        },

                        {
                            title:'Nominal Spec基准值',
                            key:'bsBaseline'
                        },
                        {
                            title:'USL+上限值',
                            key:'bsBaselineUp'
                        },
                        {
                            title:'USL+下限值',
                            key:'bsBaselineDown'
                        },
                        {
                            title:'测试数据',
                            key:'bsBaseData'
                        },

                        {
                            title:'Mean',
                            key:'bsMean'
                        },
                        {
                            title:'Range',
                            key:'bsRange'
                        },
                        {
                            title:'Result',
                            key:'bsResult'
                        },
                        {
                            title:'备注',
                            key:'bsSampleComment'
                        },

                ],
                columns: [
                   
                   
                {
                        title: '样品编码',
                        key: 'bsCode'
                    },
                    {
                        title: '样品名称',
                        key: 'bsName'
                    },
                    {
                        title: '登记时间',
                        key: 'bsSampleTime'
                    },
                    {
                        title: '要求报告时间',
                        key: 'bsReportTime'
                    },
                    {
                        title: '样品数量',
                        key: 'bsQty'
                    },
                    {
                        title: '状态',
                        key: 'bsStatus',
                        render: (h, params) => {
                            return h('span', this.dict.bsStatus[params.row.bsStatus].name);
                        }
                    },
                    {
                        title: '测试内容',
                        key: 'bsComment'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        render: (h, params) => {
                            let ary = [];
                            if(true) {
                                ary.push(h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.download(params)
                                        }
                                    }
                                }, '下载图纸'))
                            }
                            return h('div', ary);
                        }
                    }
                ]
            },
        }
    },
    watch: {
        '$route' () {
            this.init();
        }
    },
    mounted () {
        this.init();
    },
    methods:{
        getData(){
            this.dialog.formItem.bsCode=this.dialog.formItem.bsPartNo;
            this.api.lab.sampletest.getData(this.dialog.formItem).then((res)=>{                     
                                 if(res.result) {
                                    this.formQuery.bsCostTime=res.data.rows[0].bsCostTime;
                                    this.formQuery.bsSampleStatrtTime=res.data.rows[0].bsSampleStatrtTime;
                                    this.formQuery.bsCompTime=res.data.rows[0].bsCompTime;
                                    this.formQuery.pkPerson=res.data.rows[0].pkPerson;
                                }else {
                                    this.$Message.error(res.msg);
                             }                       
                            });  
        },
        init() {
            this.formQu.id=this.$route.query.row.id;
            this.api.lab.sample.selectById(this.formQu).then((res) =>{
                if(res.result) {
                    this.dialog.formItem.bsPartNo=res.data.rows[0].bsCode;
                    this.dialog.formItem.bsName=res.data.rows[0].bsName;
                            this.datagrid.data = res.data;
                            this.api.lab.sampletesttime.getlist(this.dialog.formItem).then((res)=>{                     
                                 if(res.result) {
                                    this.datagrid.data1 = res.data;
                                    this.getData();
                                }else {
                                    this.$Message.error(res.msg);
                             }                       
                            });
                }else {
                            this.$Message.error(res.msg);
              }
            });
           
        },
        //下载图纸
        download(params){
            window.location.href="/admin/lab/sample/download?id="+params.row.id;
            // //console.log(params);
            // this.api.lab.sample.download(params.row).then((res) =>{     
            // });
        },
}
}
</script>
