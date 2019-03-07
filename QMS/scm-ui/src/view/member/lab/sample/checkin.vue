<template>    
    <div>
            <Row>
                    <Modal v-model="dialog.modal_dialog2" title="" @on-ok="To" @on-cancel="cancel2" >
                        <p>
                            <Form :model="dialog.formQuery"  :label-width="80">
                                <Form-item label="" prop="bsCode">
                                    <p>在规定时间内完不成是否仍然继续测试</p>
                                </Form-item>
                        </Form>
                        </p>
                    </Modal> 
                </Row>
        
                <Row>
                    <Modal v-model="dialog.modal_dialog3" title="" @on-ok="from" @on-cancel="cancel" >
                        <p>
                            <Form :model="dialog.formQuery"  :label-width="80">
                                <Form-item label="" prop="bsCode">
                                    <a href="mailto:haorooms@126.com?subject=样品测试结果">点击发送邮件或者点击确定自动发送邮件</a>
                                </Form-item>
                        </Form>
                        </p>
                    </Modal> 
                </Row>

               <Row>
                    <i-col span="24">
                        <Table :data="dialog.data.rows" :columns="dialog.columns" stripe style="height:100px;"></Table>
                    </i-col>
                </Row>
                <!-- //测试信息反显 -->
                <!-- <Row>
                        <i-col span="24">
                            <Table :data="dialog.data1.rows" :columns="dialog.columns1" stripe style="height:auto;"></Table>
                        </i-col>
                    </Row> -->

                <br>
                <br>
                <Row>
                        <i-col span="12">
                            <Button type="primary" size="small" @click="addRow">添加测试项</Button>&nbsp;&nbsp;
                        </i-col>
                        <i-col span="12">
                        </i-col>
                </Row>
                <Row>
                    <i-col span="24">
                        <Table ref="dialogTable" :data="dialog.datagrid.data.rows" highlight-row :columns="dialog.datagrid.columns" stripe height="auto"></Table>
                        <div style="margin: 10px;overflow: hidden">
                            <div style="float: right;">
                                <Page :total="dialog.datagrid.data.total"  @on-change="changePage"  @on-page-size-change="chageSize" :current="1" :page-size="dialog.queryParams.rows" :page-size-opts="dialog.pagination"  show-total show-elevator show-sizer></Page>
                            </div>
                        </div>
                        <br>
                    <div v-if='this.formQuery.bsStatus==0'>
                        <div style="display:inline">                       
                                总用时：
                                <Input disabled="disabled" v-model="formQuery.bsCostTime"   placeholder="请输入..."></Input>
                        </div>
                        <br><br>
                        <div style="width:50%;float:left">
                            开始实验时间
                            <DatePicker  v-model="formQuery.bsSampleStatrtTime"  @on-change='change' type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                        </div>
                        <div style="width:50%;float:left">    
                            预计完成时间：
                            <DatePicker  v-model="formQuery.bsCompTime"   type="datetime" placeholder="请选择" style="width: 200px"></DatePicker>
                        </div>
                        <br><br><br>
                        <div >    
                            测试人员：
                                <Input  v-model="formQuery.pkPerson" type="text" placeholder="请输入测试人员..."></Input>
                        </div>
                    </div>
                        <br><br>
                        <!-- v-if='this.formQuery.bsStatus==0' -->
                        <div v-if='this.formQuery.bsStatus==0'>
                        <Button type="primary"  @click="ok()" >开始测试</Button>
                        </div>
                        <br><br>
                        <!-- v-if='this.formQuery.bsStatus !=0' -->
                        <div style="float:left"  >
                        <Button type="primary" @click="saveExcel">生成实验报告</Button>
                        <!-- <Button type="primary" @click="saveReport">saveReport</Button>   -->
                       </div>
                    </i-col>    
                </Row>
     </div>
</template>
<script>
import {mapState} from 'vuex';
import expandRow from './table-expand.vue';
export default {
    data() {
        return {
            show:true,
            dialog: {
                data: {rows:[],total:0},
                modal_dialog: false,
                modal_dialog2:false,
                modal_dialog4:false,
                datagrid:{
                    data: {rows:[],total:0},
                    columns:[
                        {
                        type: 'expand',
                        key: 'bsCode',
                        align: 'center',
                        render: (h, params) => {
                            return h(expandRow, {
                                props: {
                                    data:params
                                },
                                on: {
                                    returnadd: (value) => {
                                        let index = value.index;
                                        let row = value.row;
                                        Object.assign(this.dialog.datagrid.data.rows[index], value.row);
                                        this.formQuery.bsCostTime=0;
                                        for(let i=0;i<this.dialog.datagrid.data.rows.length;i++){
                                            this.formQuery.bsCostTime+=Number(this.dialog.datagrid.data.rows[i].testTime)*Number(this.formQuery.bsQty);
                                        }
                                        this.CompTime();
                                    },
                                    returncancle: (value) => {
                                        let index = value.index;
                                        this.$refs.dialogTable.toggleExpand(index);
                                    },
                                    getRowLoad:(value) =>{
                                        let index = value.index;
                                        this.removeRow(value);
                                    }
                                }
                            })
                        }
                        },
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
                        {
                        title: '操作',
                        key: 'action',
                        width: 150,
                        align: 'center',
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: () => {
                                            this.removeRow(params)
                                        }
                                    }
                                }, '移除行')
                            ]);
                        }
                    }         
                    ],
                },
                queryParams:{
                    page:1,
                    rows:15,
                    pkParent:-1
                },
                dict: {
                bsStatus:[{value: 0, name:'待测试'},{value: 1, name:'测试中'},{value: 2, name:'已完成'},{value: 3, name:'已中止'}]
                  },
                pagination: [15, 50, 100],
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
                        title: '产品编码',
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
                            return h('span', this.dialog.dict.bsStatus[params.row.bsStatus].name);
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
                    
                ],
                modal_dialog: false,
                modal_dialog1:false
                },
            formQuery: {
                bsSampleComment:'',
                testx:'',
                bsSampleStatrtTime:'',
                bsCostTime:0,
                bsCode: '',
                bsName: '',
                bsReportTime:'',
                bsSampleTime:'',
                bsComment:'',
                bsQty:'',
                bsCompTime:'',
                bjsj:'',
                pkPerson:'',
                bsStatus:0,
                ATestx:[],
                ATime:[],
                AEqname:[],
                flag:'',
                bsSampleId:'',
            },
            Query: {
                bsName:'',
                bsCode:''
            },
            bsQuery:{
                bsPartNo:'',
                bsName:"",
                bsSampleId:'',
                rows:15,
                page:1
            },
            formQu: {
                id:'',
                bsSampleId:'',
            },
        }
    },
    computed:{
        ...mapState({
            menuData:state=>state.menuData  
        })
    },
    created(){
        this.formQuery.bsSampleStatrtTime=new Date();
        this.getData();
      
    },
    
   
    methods: {
       
        //selectlist
        selectlist(){
            
             if(this.formQuery.bsStatus !=0){
                      this.bsQuery.bsSampleId=this.formQu.bsSampleId;
                      this.api.lab.sampletesttime.selectlist(this.bsQuery).then((res) =>{
                      this.dialog.datagrid.data.rows=[];
                if(res.data.total !=0) {
                    for(let i=0;i<res.data.total;i++){                   
                     this.dialog.datagrid.data.total = res.data.total;
                        this.dialog.datagrid.data.rows.push({bsSampleId:this.formQu.bsSampleId,testx:res.data.rows[i].testx,testTime:res.data.rows[i].testTime,
                        bsName:res.data.rows[i].bsName,
                        bsBaseline:res.data.rows[i].bsBaseline,
                        bsBaselineDown:res.data.rows[i].bsBaselineDown,
                        bsBaselineUp:res.data.rows[i].bsBaselineUp,
                        bsBaseData:res.data.rows[i].bsBaseData,
                        bsMean:res.data.rows[i].bsMean,
                        bsRange:res.data.rows[i].bsRange,
                        bsResult:res.data.rows[i].bsResult,
                        bsSampleComment:res.data.rows[i].bsSampleComment,
                        bsPartNo:this.formQuery.bsCode,
                        bsSampleName:this.formQuery.bsName});
                        }
                       }else{}
                      })
              }else{
                    
                    this.api.lab.sampletesttime.selectlist(this.bsQuery).then((res) =>{
                    this.dialog.datagrid.data.rows=[];
                 if(res.data.total !=0) {
                    for(let i=0;i<res.data.total;i++){                   
                     this.dialog.datagrid.data.total = res.data.total;
                        this.dialog.datagrid.data.rows.push({bsSampleId:this.formQu.bsSampleId,testx:res.data.rows[i].testx,testTime:res.data.rows[i].testTime,
                        bsName:res.data.rows[i].bsName,
                        bsBaseline:res.data.rows[i].bsBaseline,
                        bsBaselineDown:res.data.rows[i].bsBaselineDown,
                        bsBaselineUp:res.data.rows[i].bsBaselineUp,
                        bsBaseData:"",
                        bsMean:"",
                        bsRange:"",
                        bsResult:"",
                        bsSampleComment:res.data.rows[i].bsSampleComment,
                        bsPartNo:this.formQuery.bsCode,
                        bsSampleName:this.formQuery.bsName});
                        }
                       }else{}
                      })
                     }
                    
            //         }
            //     }else {
            //         //this.$Message.error(res.msg);
            //  }
            // })
        },

        addRowT(){
            if(this.formQuery.bsStatus !=0){
                this.bsQuery.bsName=this.formQuery.bsName;
                this.bsQuery.bsPartNo=this.formQuery.bsCode;
                this.selectlist();
            }else{
                this.bsQuery.bsPartNo=this.formQuery.bsCode;
                this.selectlist();
            }
           
            

         
        },
        //获取产品编码下的所有信息
        getItem(){ 
            this.bsQuery.bsPartNo=this.formQuery.bsCode;
            this.api.lab.sampletesttime.selectlist(this.bsQuery).then((res) =>{
                if(res.result) {
                    this.dialog.data1 = res.data;
                }else {
                    this.$Message.error(res.msg);
             }
            })
        },
        //获取样品信息
        getData(){
            this.formQu.id=this.$route.query.row.id;
            this.api.lab.sample.selectById(this.formQu).then((res) =>{
                if(res.result) {
                    this.dialog.data = res.data;
                    this.formQuery.bsQty=res.data.rows[0].bsQty;
                    this.formQu.id=res.data.rows[0].id;
                    this.formQu.bsSampleId=res.data.rows[0].id;
                    this.formQu.bsSampleId=res.data.rows[0].id;
                    this.formQuery.bsStatus=res.data.rows[0].bsStatus;
                    this.formQuery.bsCode=res.data.rows[0].bsCode;
                    this.formQuery.bsName=res.data.rows[0].bsName;
                    this.formQuery.bsQty=res.data.rows[0].bsQty;
                    this.formQuery.bsReportTime=res.data.rows[0].bsReportTime;
                    // this.getItem();
                    this.addRowT();
                }else {
                    this.$Message.error(res.msg);
             }
            });
        },
        //增加行
        addRow() {
            this.dialog.datagrid.data.total = this.dialog.datagrid.data.total+1;
            this.dialog.datagrid.data.rows.push({bsSampleId:this.formQu.bsSampleId,testx:"",testTime:"",bsName:"",bsBaseline:"",bsBaselineDown:'',bsBaselineUp:"",bsBaseData:"",bsMeam:"",bsRange:"",bsResult:"",bsPartNo:this.formQuery.bsCode,
        bsSampleName:this.formQuery.bsName,bsSampleComment:this.formQuery.bsSampleComment
        });
        },
        //移除行
        removeRow(params) {
            this.dialog.datagrid.data.rows.splice(params.index, 1);
        },
        //预计完成时间
        CompTime(){
            this.api.lab.sampletest.selectTime(this.formQuery).then((res)=>{
            if(res.data ==""){
                this.formQuery.id=(this.formQuery.bsSampleStatrtTime).getTime();
                this.api.lab.sample.getTime(this.formQuery).then((res) =>{
                    this.formQuery.bsCompTime=res.data.bsSampleStatrtTime;
                })
            }else{
                this.$Modal.confirm({
                title: '提示信息',
                content: '<p>开始时间存在别的样品</p>',
                loading: true,
                onOk: () => {
                        this.$Modal.remove();
                        }
                });
              }
            })
            
        },
        change(){
            this.CompTime();
        },
        cancel(){

        },
        //开始测试
        saveTest(){
            this.api.lab.sample.updateStatus(this.formQuery).then((res) =>{
                if(res.result) {
                    this.dialog.datagrid.data.rows=[];
                  this.getData();
                  this.show=false;
                }else {
                this.$Message.error(res.msg);
              }
            })
        },
        cancel2(){
            this.formQuery.bsStatus=3;
            this.formQuery.id=this.formQu.id;
            this.saveTest();
            this.dialog.modal_dialog3=true;
        },
        ok(){
           if(this.formQuery.bsCompTime=="" ||this.formQuery.bsCompTime==undefined){
               this.$Message.error("完成时间为空")
           }else{
            this.formQuery.id=this.formQu.id;
            this.formQuery.bsReportTime=new Date(this.formQuery.bsReportTime);
            // alert(this.formQuery.bsCompTime);
            this.formQuery.bsCompTime=new Date(this.formQuery.bsCompTime);
            var s =(this.formQuery.bsCompTime).getTime() >(this.formQuery.bsReportTime).getTime();
            if(s){
                this.dialog.modal_dialog2=true;
            }else{
                this.formQuery.bsStatus= 1;
                this.saveTest();
                this.saveData();
                
           }
         }
        },
      //保存时间和测试人员信息
       saveData(){
           this.api.lab.sampletest.saveData(this.formQuery).then((res) =>{
            if(res.result){
                    this.$Message.success("成功");
                }else{
                    this.$Message.error("失败");
                }
           })
       },

        To(){
             this.formQuery.id=this.formQu.id;
             this.formQuery.bsStatus=1;
             this.saveTest();
             this.saveData();
        },
       //保存测试项信息
        saveTestx(){
            for(let i=0;i<this.dialog.datagrid.data.rows.length;i++){
                   this.formQuery.ATime.push(this.dialog.datagrid.data.rows[i].testTime);
                   this.formQuery.ATestx.push(this.dialog.datagrid.data.rows[i].testx);
                   this.formQuery.AEqname.push(this.dialog.datagrid.data.rows[i].eqName);
            }
            this.api.lab.sample.saveTestx(this.formQuery);
        },
        from(){
            this.formQuery.flag="testPerson";
            this.api.lab.sample.sendMail(this.formQuery).then((res) =>{
                if(res.result){
                    this.$Message.success("发送邮件成功");
                }else{
                    this.$Message.error("自动发送邮件失败，请手动发送");
                }
            })
        },
        //下载图纸
        download(params){
            window.location.href="/admin/lab/sample/download?id="+params.row.id;
        },
        //保存excel
        saveExcel(){
              this.bsQuery.bsSampleId=this.formQu.bsSampleId;
              alert(this.formQu.bsSampleId);
              this.bsQuery.bsName=this.formQuery.bsName;
              this.api.lab.sampletesttime.saveExcel(this.bsQuery).then((res) =>{
                  if(res.result){
                      this.$Message.success("实验数据Excel生成成功");
                  }else{
                      this.$Message.error("保存失败");
                  }

              })
            },
        saveReport(){
                this.Query.bsCode=this.formQuery.bsCode;
                this.Query.bsName=this.formQuery.bsName;
                this.api.lab.sampletesttime.saveReport(this.Query).then((res)=> {
                    if(res.result){
                        this.$Message.success("实验报告Excel生成成功");
                    }else{
                        this.$Message.error("保存失败");
                    }
           
                })
            },    
            changePage (pageSize) {
               this.bsQuery.page=pageSize;
               this.selectlist();
            },
            chageSize(pageSizeOpts){
               this.bsQuery.rows=pageSizeOpts;
               this.selectlist();
    }
     }
}
</script>
<style>
        Input{
            display:float;
            width:25%;
        }
        Select{
            display:float;
            width:25%;
        }   
       </style>