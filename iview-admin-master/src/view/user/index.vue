<template>
    <div>
     <Card>
<!--     按钮区 -->
        <Row>
             <Col span="12">
                  <Form class="query_area" ref="formQuery" :model="formQuery"  inline>
                    <Form-item label="账号" prop="bsCode" :label-width="80">
                        <Input v-model="formQuery.bsCode" placeholder="帐号"  />
                    </Form-item>
                    <Form-item label="姓名" prop="bsName" :label-width="50">
                        <Input v-model="formQuery.bsName" placeholder="姓名" />
                    </Form-item>
                    <Form-item>
                        <i-button type="primary" @click="handleSubmit('formInline')" size="small">查找</i-button>
                    </Form-item>
                </Form>
             </Col>
              <Col span="6">
                    <ButtonGroup>
                        <Button type="success" ghost @click="showAddDialog()">新增</Button>
                        <Button type="success" ghost @click="showEditDialog()" >修改</Button>
                        <Button type="success" ghost @click="doDelete()">删除</Button>
                     </ButtonGroup>              
             </Col>
              <Col span="6">
                    <ButtonGroup>
                        <Button type="success" ghost  @click="resetpassword">重置密码</Button>
                        <Button type="success" ghost  @click="showAllocDialog" >分配角色</Button>
                     </ButtonGroup>              
             </Col>
        </Row>
    
      <Table border ref="selection" :columns="columns" :data="datagrid.data.rows"></Table> 
         
    </Card>
    <div class="pagenation">
        <Page :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" @on-change="changePage" show-total show-elevator show-sizer></Page>
    </div>
    <Modal v-model="dialog.modal_dialog" title="用户管理" @on-ok="ok" @on-cancel="cancel" width="520" >
        <Form class="query_area" ref="formQuery" :model="formQuery"  inline>
            <Form-item label="账号" prop="bsCode" :label-width="60">
                <Input v-model="formQuery.bsCode" placeholder="帐号"  />
            </Form-item>
            <Form-item label="姓名" prop="bsName" :label-width="60">
                <Input v-model="formQuery.bsName" placeholder="姓名" />
            </Form-item>           
        </Form>
    </Modal>   
    </div>     
</template>

<script>
 import Tables from '_c/tables'
export default {
  name: 'role',
  components: {
Tables
  },
  data () {
    return {
       formQuery: {
            bsCode: '',
            bsName: ''
        },       
         dialog: {
            loading: false,
            modal_dialog: false,
            formItem: {
                pkPerson: '',
                personName: ''
            },
            ruleForm: {
                bsCode: [
                    { required: true, message: '请填写帐号', trigger: 'blur' }
                ],
                bsName: [
                    { required: true, message: '请填写姓名', trigger: 'blur' }
                ]
            }
        },
        columns:[
            {
            type: 'selection',
            width: 60,
            align: 'center'
            },
            {
                title: '账号',
                key: 'name'
            },
            {
                title: '姓名',
                key: 'age'
            },
            {
                title: '手机号',
                key: 'address'
            },
            {
                title: '邮箱',
                key: 'address'
            },
            {
                title: '状态',
                key: 'address'
            },
            {
                title: '备注',
                key: 'address'
            },
            {
                title: '删除标识',
                key: 'address'
            },
            {
                title: '创建时间',
                key: 'address'
            },
          
        ],
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
                    title: '选项',
                    type: 'selection',
                    fixed:'left',
                    width: 75
                },                  
               
                {
                    title: '帐号',
                    key: 'bsCode',
                    fixed:'left',
                    width: 125
                },
                {
                    title: '姓名',
                    key: 'bsName',
                    width: 150
                },
                // {
                //     title: '人员编码',
                //     key: 'pkPerson',
                //     hidden:true
                // },
                {
                    title: '手机号',
                    key: 'bsMobile',
                    width: 125
                },
                {
                    title: '邮箱',
                    key: 'bsEmail',
                    width: 200
                },
                {
                    title: '状态',
                    key: 'bsStatus',
                    render: (row) => {
                        let text,color;
                        if(row.bsStatus==1) {
                            text = "启用";
                            color= "green";
                        }else if(row.bsStatus==2) {
                            text = "停用";
                            color= "red";
                        }else if(row.bsStatus==3) {
                            text = "锁定";
                            color= "red";
                        }
                        let html = "<span style='color:"+color+"'>"+text+"</span>";
                        return html;
                    },
                    width: 100
                },
                {
                    title: this.$t('user.mark'),
                    key: 'bsComment',
                    width: 300
                },
                {
                    title: '删除标识',
                    key: 'bsIsDel',
                    render: (row) => {
                        let text;
                        if(row.bsIsDel==0) {
                            text = "-";
                        }else if(row.bsIsDel==1) {
                            text = "已删除";
                        }
                        return text;
                    },
                    width: 100
                },
                {
                    title: '创建时间',
                    key: 'bsCreatedTime',
                    width: 150
                },
                {
                    title: '更新时间',
                    key: 'bsModifiedTime',
                    width: 150
                }
            ]
        },     
     
    }
  },
  methods: {
       showAddDialog() {   
            this.isReadOnly = false;    
            this.dialog.modal_dialog = true;           
            this.dialog.formItem = {};
        },
  }
}
</script>

<style lang="less">
.update-paste{
  &-con{
    height: 350px;
  }
  &-btn-con{
    box-sizing: content-box;
    height: 30px;
    padding: 15px 0 5px;
  }
}
.paste-tip{
  color: #19be6b;
}
.bgblock{padding:10px;background-color: #fff;}
.pagenation{text-align:right;margin-top:10px;}
</style>
