<template>
    <div>
        <Row>
            <i-col>
                <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                    <Form-item prop="bsCode">
                        <Input type="text" v-model="formQuery.bsCode" placeholder="用户编码" />
                    </Form-item>
                    <Form-item prop="bsName">
                        <Input type="text" v-model="formQuery.bsName" placeholder="用户名称" />
                    </Form-item>
                    <Form-item >
                        <Button type="primary" @click="handleSubmit('formQuery')">查 询</Button>
                    </Form-item>
                </Form>
            </i-col>
        </Row>
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
import custDialog from '../../../components/business-component/custDialog.vue';
export default {
    components: {
        custDialog
    },
    data() {
        return {
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
                        title: 'ID',
                        key: 'NO'
                    },
                    {
                        title: '操作类',
                        key: 'bsCode'
                    },
                    {
                        title: '日志级别',
                        key: 'bsName'
                    },
                    {
                        title: '日志',
                        key: 'host'
                    },
                    {
                        title: '线程',
                        key: 'startTimeStamp'
                    },
                    {
                        title: '信息',
                        key: 'stopTimeStamp'
                    },
                    {
                        title: '追踪ID',
                        key: 'lastAccessTime'
                    },
                    {
                        title: '操作系统',
                        key: 'lastAccessTime'
                    },
                    {
                        title: '时间',
                        key: 'lastAccessTime'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        render: (h, params) => {
                            let ary = [];
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
                                }, '踢出'))
                            }
                            return h('div', ary);
                        }
                    }
                ]
            }
        }
    },
    created(){
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
            this.api.admin.online.getlist(this.formQuery).then((res) => {
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
        delete(params) {
            this.$Modal.confirm({
                title: '提示信息',
                content: '<p>是否确定踢出用户'+params.row.bsCode+'？</p>',
                onOk: () => {
                    this.api.admin.online.kickout({sessionId:params.row.id}).then((res)=>{
                        if(res.result) {
                            //refresh
                            this.reloadData();
                            this.$Message.info('踢出成功');
                            this.$Modal.remove();
                        }else {
                            this.$Message.error(res.msg);
                        }
                    });
                }
            });
        }
    }
}
</script>
