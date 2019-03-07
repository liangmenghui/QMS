<template>
    <div>
        <div>
            <Row>
                <i-col>
                    <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                        <Form-item>
                            <Input v-model="formQuery.bsProvider" placeholder="请输入货代"></Input>
                        </Form-item>
                        <Form-item>
                            <Button type="primary" @click="handleSubmit()">查 询</Button>
                        </Form-item>
                        <Form-item>
                            <Button type="primary" @click="showAddDialog()">添加</Button>
                        </Form-item>
                    </Form>
                </i-col>
            </Row>
        </div>

        <Modal v-model="dialog.modal_dialog1" title="" @on-ok="ok1" @on-cancel="cancel">
            <p slot="header" style="color:rgb(99, 168, 168);text-align:center">
                <Icon type="information-circled"></Icon>
                <span>编 辑</span>
            </p>
            <p>
                <div>
                    <Row>
                        <i-col>
                            <Form :model="formItem" :label-width="90" style="text-align:center" :ref="dialog.ruleForm" :rules="dialog.ruleForm">
                                <FormItem label="运输路线" prop='bsTransportCode'>
                                    <Row>
                                        <Col span="15">
                                        <Select v-model='formItem.bsTransportCode'>
                                            <Option v-for='x in bsTransportCodelist' :key='x' :value="x">{{x}}</Option>
                                        </Select>
                                        </Col>
                                    </Row>
                                </FormItem>
                                <FormItem label="货代" prop='bsProvider'>
                                    <Row>
                                        <Col span="15">
                                        <Input v-model="formItem.bsProvider" placeholder="请输入货代"></Input>
                                        </Col>
                                    </Row>
                                </FormItem>

                            </Form>
                        </i-col>
                    </Row>
                </div>
            </p>
        </Modal>

        <Modal v-model="dialog.modal_dialog" title="" @on-ok="ok" @on-cancel="cancel">
            <p slot="header" style="color:rgb(99, 168, 168);text-align:center">
                <Icon type="information-circled"></Icon>
                <span>添 加</span>
            </p>
            <p>
                <div>
                    <Row>
                        <i-col>
                            <Form :model="formItem" :label-width="90" style="text-align:center" :ref="dialog.ruleForm" :rules="dialog.ruleForm">
                                <FormItem label="运输路线" prop='bsTransportCode'>
                                    <Row>
                                        <Col span="15">
                                        <Select v-model='formItem.bsTransportCode'>
                                            <Option v-for='x in bsTransportCodelist' :key='x' :value="x">{{x}}</Option>
                                        </Select>
                                        </Col>
                                    </Row>
                                </FormItem>
                                <FormItem label="货代" prop='bsProvider'>
                                    <Row>
                                        <Col span="15">
                                        <Input v-model="formItem.bsProvider" placeholder="请输入货代"></Input>
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
            <i-col span="24">
                <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe style="height:auto;"></Table>
                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page @on-change="changePage" @on-page-size-change="chageSize" :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" show-total show-elevator show-sizer></Page>
                    </div>
                </div>
            </i-col>
        </Row>
    </div>

</template>
<script>
import { mapState } from "vuex";
export default {
  created() {
    this.getData();
    this.getTransCode();
  },
  computed: {
    ...mapState({
      menuData: state => state.menuData
    })
  },
  data() {
    return {
      bsTransportCodelist: [],
      start: 0,
      end: 15,
      dialog: {
        modal_dialog: false,
        modal_dialog1: false,
        ruleForm: {
          bsProvider: [
            { required: true, message: "请填写货代", trigger: "blur" }
          ],
          bsTransportCode: [
            { required: true, message: "请填写联系人", trigger: "blur" }
          ]
        }
      },
      formItem: {
        id: "",
        bsProvider: "",
        bsTransportCode: ""
      },
      formQuery: {
        bsProvider: "",
        bsTransportCode: ""
      },
      datagrid: {
        queryParams: {
          page: 1,
          rows: 15,
          pkParent: -1
        },
        pagination: [15, 50, 100],
        data: { rows: [], total: 0, rows1: [] },
        columns: [
          {
            title: "货代",
            key: "bsProvider"
          },
          {
            title: "运输路线",
            key: "bsTransportCode"
          },
          {
            title: "操作",
            key: "action",
            render: (h, params) => {
              let ary = [];
              // this.menuData.perms.EDIT
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
    getTransCode() {
      this.api.lmp.transprovider.selectTransCode().then(res => {
        if (res.result) {
          this.bsTransportCodelist = res.data.rows;
        } else {
          this.$Message.error(res.msg);
        }
      });
    },
    getData() {
      this.api.lmp.transprovider
        .selectlist(this.datagrid.queryParams)
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
      this.formItem.id = "";
      this.formItem.bsProvider = "";
      this.formItem.bsTransportCode = "";
      this.dialog.modal_dialog = true;
    },
    check() {
      if (this.formItem.bsProvider == "" || this.formItem.bsProvider == null) {
        this.$Message.error("供应商名称不能为空");
        return false;
      } else if (
        this.formItem.bsTransportCode == "" ||
        this.formItem.bsTransportCode == null
      ) {
        this.$Message.error("运输代码不能为空");
        return false;
      }
      {
        return true;
      }
    },
    ok() {
      if (!this.check()) {
        return false;
      }
      this.api.lmp.transprovider.add(this.formItem).then(res => {
        if (res.result) {
          this.getData();
          this.$Message.success("添加成功！");
        } else {
          this.$Message.error("添加失败！");
        }
      });
    },
    ok1() {
      if (!this.check()) {
        return false;
      }
      this.api.lmp.transprovider.update(this.formItem).then(res => {
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
          this.api.lmp.transprovider.delete(params.row).then(res => {
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
      this.formItem.id = params.row.id;
      this.formItem.bsProvider = params.row.bsProvider;
      this.formItem.bsTransportCode = params.row.bsTransportCode;
      this.dialog.modal_dialog1 = true;
    },
    handleSubmit() {
      this.api.lmp.transprovider.selectByProvide(this.formQuery).then(res => {
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