<template>
    <div>
        <Row>
            <i-col span="24">
                <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe height="500"></Table>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" @on-change="changePage" show-total show-elevator show-sizer></Page>
                    </div>
                </div>
            </i-col>
        </Row>
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
                bsCode: '',
                bsName: ''
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
                        title: '流程ID',
                        key: 'procDefinitionId'
                    },
                    {
                        title: '流程名称',
                        key: 'procDefinitionName'
                    },
                    {
                        title: '发起人',
                        key: 'startUserCode'
                    },
                    {
                        title: '开始时间',
                        key: 'startTime',
                        render: (h, params) => {
                            let text = "";
                            if(typeof(params.row.startTime)=="number"&&params.row.startTime > 0) {
                                let date = new Date(params.row.startTime);
                                text = (date.getFullYear());
                                text = text +"-"+ ((date.getMonth()<10)?("0"+date.getMonth()):date.getMonth());
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
                        title: '结束时间',
                        key: 'endTime',
                        render: (h, params) => {
                            let text = "-";
                            if(typeof(params.row.endTime)=="number"&&params.row.endTime > 0) {
                                let date = new Date(params.row.endTime);
                                text = (date.getFullYear());
                                text = text +"-"+ ((date.getMonth()<10)?("0"+date.getMonth()):date.getMonth());
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
                        title: '任务耗时',
                        key: 'duration',
                        render: (h, params) => {
                            if(typeof(params.row.duration)!="Number"&&!params.row.duration) {
                                return h('span', {
                                }, '-');
                            }
                            let text = "";
                            let res = parseInt(params.row.duration/(24*60*1000), 10);
                            if(res > 0) {
                                text += (res+"小时");
                            }
                            res = parseInt(params.row.duration/(60*1000), 10);
                            if(res > 0) {
                                text += (res+"分");
                            }
                            res = parseInt(params.row.duration%(60), 10);
                            if(res > 0) {
                                text += (res+"秒");
                            }
                            return h('span', {
                            }, text);
                        }
                    },
                    {
                        title: '状态',
                        key: 'status',
                        render: (h, params) => {
                            return h('span', {
                            }, '审批中');
                        }
                    },
                    {
                        title: '操作',
                        key: 'action',
                        render: (h, params) => {
                            let ary = [];
                            ary.push(h('Button', {
                                props: {
                                    type: 'error',
                                    size: 'small'
                                },
                                on: {
                                    click: () => {
                                        //路由到表单页面
                                        //if(params.row.procDefinitionId=='fs-process') {
                                        //    this.$router.push({
                                        //        name: '',
                                        //        query: query
                                        //    });
                                        //}
                                        window.open("/modeler/diagram-viewer/index.html?processDefinitionId="+params.row.procDefinitionId+"&processInstanceId="+params.row.processInstanceId);
                                    }
                                }
                            }, '详情'))
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
        handleSubmit(name) {
            this.getData();
        },
        getData() {
            Object.assign(this.formQuery, this.datagrid.queryParams);
            this.api.workflow.myprocess.getlist(this.formQuery).then((res) => {
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
        }
    }
}
</script>
