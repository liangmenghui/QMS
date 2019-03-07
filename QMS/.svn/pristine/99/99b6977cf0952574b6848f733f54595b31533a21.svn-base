<template>
  <div>
    <Form class="query_area" ref="formQuery" :model="formQuery" :label-width="140">
      <Row>
        <i-col span="5">
          <Form-item label="货代编码">
            <Input v-model="formQuery.bsAgent" placeholder="请输入货代编码"/>
          </Form-item>
        </i-col>
        <i-col span="5">
          <Form-item label="客户编码">
            <Input v-model="formQuery.bsCustomer" placeholder="请输入客户"/>
          </Form-item>
        </i-col>
        <i-col span="1">
          <Form-item>
            <Button type="primary" @click="handleSubmit()">查 询</Button>
          </Form-item>
        </i-col>
        <i-col span="1">
          <Form-item>
            <Button type="primary" @click="showAddDialog()">添加</Button>
          </Form-item>
        </i-col>
      </Row>
    </Form>
    <Modal class-name="cumould-modal" v-model="dialog.modal_dialog1" title="编辑" @on-ok="ok1" @on-cancel="cancel" width=800>
      <p slot="header" style="color:rgb(99, 168, 168);text-align:left; margin-left:20px;">
        <Icon type="information-circled"></Icon>
        <span>编 辑</span>
      </p>
      <div style="border:1px">
        <Row>
          <i-col>
            <Form :model="formItem" :label-width="100" style="padding:24px 6px 0px 6px;" ref="formItem" :rules="dialog.ruleForm">
              <Row>
                <i-col span="12">
                  <FormItem label="客户编码" prop='bsCustomer'>
                    <un-customer v-model="formItem.bsCustomer" :text.sync="formItem.bsCustomerDesc"></un-customer>
                  </FormItem>
                </i-col>
                <i-col span="12">
                  <FormItem label="货代编码" prop='bsAgent'>
                    <un-agent v-model="formItem.bsAgent" :text.sync="formItem.bsAgentDesc"></un-agent>
                  </FormItem>
                </i-col>
              </Row>
              <div style="background-color:#f1f2f3; margin-top:24px;text-align:left;padding:4px;">
                <div style="margin:6px 10px; font-weight:600; font-size:14px;margin-left:12px;">模板页面</div>
                <Row>
                  <i-col span="12">
                    <FormItem label="组件" prop='bsTemplateComponent'>
                      <Input v-model='formItem.bsTemplateComponent'/>
                    </FormItem>
                  </i-col>
                  <i-col span="12">
                    <FormItem label="解析组件" prop='bsTemplateParseComponent'>
                      <Input v-model='formItem.bsTemplateParseComponent'/>
                    </FormItem>
                  </i-col>
                </Row>
                <Row>
                  <i-col span="24">
                    <FormItem label="页面URL" prop='bsTemplateComponentUrl'>
                      <Input readonly="readonly"  v-model='formItem.bsTemplateComponentUrl' type="textarea" :autosize="{minRows: 2,maxRows: 5}" disabled/>
                    </FormItem>
                  </i-col>
                </Row>
              </div>
              <div style="background-color:#f1f2f3; margin-top:24px;text-align:left;padding:4px;">
                <div style="margin:6px 10px; font-weight:600; font-size:14px;margin-left:12px;">内容页面</div>
                <Row>
                  <i-col span="12">
                    <FormItem label="组件" prop='bsContentComponent'>
                      <Input v-model='formItem.bsContentComponent'/>
                    </FormItem>
                  </i-col>
                  <i-col span="12">
                    <FormItem label="解析组件" prop='bsContentParseComponent'>
                      <Input v-model='formItem.bsContentParseComponent'/>
                    </FormItem>
                  </i-col>
                </Row>
                <Row>
                  <i-col span="24">
                    <FormItem label="">
                      <Input readonly="readonly" type="textarea" :autosize="{minRows: 2,maxRows: 5}" disabled/>
                    </FormItem>
                  </i-col>
                </Row>
              </div>
            </Form>
          </i-col>
        </Row>
      </div>
    </Modal>

    <Modal class-name="cumould-modal" v-model="dialog.modal_dialog" title="添加" @on-ok="ok()" @on-cancel="cancel" width=800>
      <p slot="header" style="color:rgb(99, 168, 168);text-align:left; margin-left:20px;">
        <Icon type="information-circled"></Icon>
        <span>添 加</span>
      </p>
      <p>
        <div style="border:1px">
          <Row>
            <i-col>
              <Form :model="formItem" :label-width="100" style="padding:24px 6px 0px 6px;" ref="formItem" :rules="dialog.ruleForm">
                <Row>
                  <i-col span="12">
                    <FormItem label="客户编码" prop='bsCustomer'>
                      <un-customer v-model="formItem.bsCustomer" :text.sync="formItem.bsCustomerDesc"></un-customer>
                    </FormItem>
                  </i-col>
                  <i-col span="12">
                    <FormItem label="货代编码" prop='bsAgent'>
                      <un-agent v-model="formItem.bsAgent" :text.sync="formItem.bsAgentDesc"></un-agent>
                    </FormItem>
                  </i-col>
                </Row>
                <div style="background-color:#f1f2f3; margin-top:24px;text-align:left;padding:4px;">
                  <div style="margin:6px 10px; font-weight:600; font-size:14px;margin-left:12px;">模板页面</div>
                  <Row>
                    <i-col span="12">
                      <FormItem label="组件" prop='bsTemplateComponent'>
                        <Input v-model='formItem.bsTemplateComponent'/>
                      </FormItem>
                    </i-col>
                    <i-col span="12">
                      <FormItem label="解析组件" prop='bsTemplateParseComponent'>
                        <Input v-model='formItem.bsTemplateParseComponent'/>
                      </FormItem>
                    </i-col>
                  </Row>
                  <Row>
                    <i-col span="24">
                      <FormItem label="页面URL" prop='bsTemplateComponentUrl'>
                        <Input readonly="readonly" v-model='formItem.bsTemplateComponentUrl' type="textarea" :autosize="{minRows: 2,maxRows: 5}" disabled/>
                      </FormItem>
                    </i-col>
                  </Row>
                </div>
                <div style="background-color:#f1f2f3; margin-top:24px;text-align:left;padding:4px;">
                  <div style="margin:6px 10px; font-weight:600; font-size:14px;margin-left:12px;">内容页面</div>
                  <Row>
                    <i-col span="12">
                      <FormItem label="组件" prop='bsContentComponent'>
                        <Input v-model='formItem.bsContentComponent'/>
                      </FormItem>
                    </i-col>
                    <i-col span="12">
                      <FormItem label="解析组件" prop='bsContentParseComponent'>
                        <Input v-model='formItem.bsContentParseComponent'/>
                      </FormItem>
                    </i-col>
                  </Row>
                  <Row>
                    <i-col span="24">
                      <FormItem label="">
                        <Input readonly="readonly" type="textarea" :autosize="{minRows: 2,maxRows: 5}" disabled/>
                      </FormItem>
                    </i-col>
                  </Row>
                </div>
              </Form>
            </i-col>
          </Row>
        </div>
      </p>
    </Modal>
    <Row>
      <i-col span="24">
        <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe style="height:auto;"></Table>
        <div style="margin: 10px;overflow: hidden">
          <div style="float: right;">
            <Page :total="datagrid.data.total" :current="1" @on-change="changePage" @on-page-size-change="chageSize" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" show-total show-elevator show-sizer></Page>
          </div>
        </div>
      </i-col>
    </Row>
  </div>

</template>
<script>
import { mapState } from "vuex";
export default {
  mounted() {
    this.getData();
  },
  computed: {
    ...mapState({
      menuData: state => state.menuData
    })
  },
  data() {
    return {
      start: 0,
      end: 20,
      dialog: {
        modal_dialog: false,
        modal_dialog1: false,
        ruleForm: {
          bsCustomer: [
            { required: true, message: "请选择客户编码", trigger: "blur" }
          ],
          bsAgent: [
            { required: true, message: "请选择货代编码", trigger: "blur" }
          ]
        }
      },
      formItem: {
        id: "",
        bsAgent: "",
        bsAgentDesc: "",
        bsCustomer: "",
        bsCustomerDesc: "",
        bsTemplateComponentUrl: "",
        bsTemplateComponent: "",
        bsTemplateParseComponent: "",
        bsContentComponent: "",
        bsContentParseComponent: ""
      },
      formQuery: {
        bsAgent: "",
        bsCustomer: ""
      },
      datagrid: {
        queryParams: {
          page: 1,
          rows: 20,
          pkParent: -1
        },
        pagination: [20, 50, 100],
        data: { rows: [], total: 0, rows1: [] },
        columns: [
          {
            title: "货代编码",
            key: "bsAgent"
          },
          {
            title: "货代",
            key: "bsAgentDesc"
          },
          {
            title: "客户编码",
            key: "bsCustomer"
          },
          {
            title: "客户",
            key: "bsCustomerDesc"
          },
          {
            title: "模板页面 组件URL",
            key: "bsTemplateComponentUrl"
          },
          {
            title: "模板页面 组件",
            key: "bsTemplateComponent"
          },
          {
            title: "模板页面 解析组件",
            key: "bsTemplateParseComponent"
          },
          {
            title: "内容页面 组件",
            key: "bsContentComponent"
          },
          {
            title: "内容页面 解析组件",
            key: "bsContentParseComponent"
          },
          {
            title: "操作",
            key: "action",
            render: (h, params) => {
              let ary = [];
              this.menuData.perms.EDIT;
              if (true) {
                ary.push(
                  h(
                    "Button",
                    {
                      props: {
                        type: "primary",
                        size: "small"
                      },
                      style: {
                        marginRight: "5px"
                      },
                      on: {
                        click: () => {
                          this.showEditDialog(params);
                        }
                      }
                    },
                    "编辑"
                  )
                );
              }
              if (true) {
                ary.push(
                  h(
                    "Button",
                    {
                      props: {
                        type: "error",
                        size: "small"
                      },
                      on: {
                        click: () => {
                          this.delete(params);
                        }
                      }
                    },
                    "删除"
                  )
                );
              }
              return h("div", ary);
            }
          }
        ]
      }
    };
  },

  methods: {
    getData() {
      this.api.lmp.bookingconfiguration
        .selectList(this.datagrid.queryParams)
        .then(res => {
          if (res.result) {
            this.datagrid.data.rows = res.data.rows;
            this.datagrid.data.total = res.data.total;
          } else {
            this.$Message.error(res.msg);
          }
        });
    },
    showAddDialog() {
      this.$refs["formItem"].resetFields();
      this.formItem.id = "";
      this.formItem.bsAgent = "";
      this.formItem.bsAgentDesc = "";
      this.formItem.bsCustomer = "";
      this.formItem.bsCustomerDesc = "";
      this.formItem.bsTemplateComponentUrl = "";
      this.formItem.bsTemplateComponent = "";
      this.formItem.bsTemplateParseComponent = "";
      this.formItem.bsContentComponent = "";
      this.formItem.bsContentParseComponent = "";
      this.dialog.modal_dialog = true;
    },
    ok() {
      this.api.lmp.bookingconfiguration
        .insertBookingconfiguration(this.formItem)
        .then(res => {
          if (res.result) {
            this.getData();
            this.$Message.success("添加成功！");
          } else {
            this.$Message.error("添加失败！");
          }
        });
    },
    ok1() {
      this.api.lmp.bookingconfiguration
        .updateBookingconfiguration(this.formItem)
        .then(res => {
          if (res.result) {
            this.getData();
            this.$Message.success("更新成功！");
          } else {
            this.$Message.error("更新失败！");
          }
        });
    },
    cancel() {},
    delete(params) {
      this.$Modal.confirm({
        title: "提示信息",
        content: "<p>是否确定删除？</p>",
        loading: true,
        onOk: () => {
          this.api.lmp.bookingconfiguration
            .deleteBookingconfiguration(params.row)
            .then(res => {
              if (res.result) {
                this.getData();
                this.$Message.success("删除成功！");
                this.$Modal.remove();
              } else {
                this.$Message.error("删除失败！");
              }
            });
        }
      });
    },
    showEditDialog(params) {
      this.$refs["formItem"].resetFields();
      this.formItem.id = params.row.id;
      this.formItem.bsAgent = params.row.bsAgent;
      this.formItem.bsAgentDesc = params.row.bsAgentDesc;
      this.formItem.bsCustomer = params.row.bsCustomer;
      this.formItem.bsCustomerDesc = params.row.bsCustomerDesc;
      this.formItem.bsTemplateComponentUrl = params.row.bsTemplateComponentUrl;
      this.formItem.bsTemplateComponent = params.row.bsTemplateComponent;
      this.formItem.bsTemplateParseComponent =
        params.row.bsTemplateParseComponent;
      this.formItem.bsContentComponent = params.row.bsContentComponent;
      this.formItem.bsContentParseComponent =
        params.row.bsContentParseComponent;
      this.dialog.modal_dialog1 = true;
    },
    handleSubmit() {
      this.api.lmp.bookingconfiguration.selectBy(this.formQuery).then(res => {
        if (res.result) {
          this.datagrid.data = res.data;
        } else {
          this.$Message.error(res.msg);
        }
      });
    },
    changePage(pageSize) {
      this.datagrid.queryParams.page = pageSize;
      // this.start=(pageSize-1)*this.datagrid.queryParams.rows;
      // this.end=pageSize*this.datagrid.queryParams.rows;
      this.getData();
    },
    chageSize(pageSizeOpts) {
      this.datagrid.queryParams.rows = pageSizeOpts;
      // this.start=0;
      // this.end=pageSizeOpts;
      this.getData();
    }
  }
};
</script>
<style>
.cumould-modal {
  z-index: 1000;
}
</style>
