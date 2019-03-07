<template>
    <div>
        <div>
            <Row>
                <i-col>
                    <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                        <Form-item>
                            <Input v-model="formQuery.bsSegment1" placeholder="请输入物料编码" />
                        </Form-item>
                        <Form-item>
                            <Input v-model="formQuery.bsDescription" placeholder="请输入物料名称" />
                        </Form-item>
                        <Form-item>
                            <Button type="primary" @click="handleSubmit()">查 询</Button>
                        </Form-item>
                        <!-- <Form-item >
                        <Button type="primary" @click="showAddDialog()" >添加</Button>
                    </Form-item> -->
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
                            <Form :model="formItem" :label-width="80" style="text-align:center" ref="formItem" :rules="dialog.ruleForm">
                                <FormItem label="组织ID" prop='bsOrganizationId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber v-model="formItem.bsOrganizationId" style="width: 255px" placeholder="组织ID"></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="物料ID" prop='bsInventoryItemId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber v-model="formItem.bsInventoryItemId" style="width: 255px" placeholder="物料ID"></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label=" 物料编码" prop='bsSegment1'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="formItem.bsSegment1" placeholder="请输入物料编码" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="物料类别ID" prop='bsCategoryId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber type='text' v-model="formItem.bsCategoryId" style="width: 255px" placeholder="请输入物料类别ID "></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="物料名称" prop='bsDescription'>
                                    <Row>
                                        <i-col span="15">
                                            <Input type='text' v-model="formItem.bsDescription" placeholder="请输入物料名称 " />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="物料类别名称" prop='bsCategoryDesc'>
                                    <Row>
                                        <i-col span="15">
                                            <Input type='text' v-model="formItem.bsCategoryDesc" placeholder="请输入物料类别名称 " />
                                        </i-col>
                                    </Row>
                                </FormItem>
                            </Form>
                        </i-col>
                    </Row>
                </div>
        </Modal>

        <Modal v-model="dialog.modal_dialog" title="" @on-ok="ok()" @on-cancel="cancel">
            <p slot="header" style="color:rgb(99, 168, 168);text-align:center">
                <Icon type="information-circled"></Icon>
                <span>添 加</span>
            </p>
            <p>
                <div>
                    <Row>
                        <i-col>
                            <Form :model="formItem" :label-width="80" style="text-align:center" ref="formItem" :rules="dialog.ruleForm">
                                <FormItem label="组织ID" prop='bsOrganizationId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber v-model="formItem.bsOrganizationId" style="width: 255px" placeholder="组织ID"></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="物料ID" prop='bsInventoryItemId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber v-model="formItem.bsInventoryItemId" style="width: 255px" placeholder="物料ID"></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label=" 物料编码" prop='bsSegment1'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="formItem.bsSegment1" placeholder="请输入物料编码" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="物料类别ID" prop='bsCategoryId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber type='text' v-model="formItem.bsCategoryId" style="width: 255px" placeholder="请输入物料类别ID "></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="物料名称" prop='bsDescription'>
                                    <Row>
                                        <i-col span="15">
                                            <Input type='text' v-model="formItem.bsDescription" placeholder="请输入物料名称 " />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="物料类别名称" prop='bsCategoryDesc'>
                                    <Row>
                                        <i-col span="15">
                                            <Input type='text' v-model="formItem.bsCategoryDesc" placeholder="请输入物料类别名称 " />
                                        </i-col>
                                    </Row>
                                </FormItem>
                            </Form>
                        </i-col>
                    </Row>
                </div>
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
  created() {
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
      end: 15,
      dialog: {
        modal_dialog: false,
        modal_dialog1: false,
        ruleForm: {
          bsOrganizationId: [
            { required: true, message: "请填写组织ID", trigger: "blur" }
          ],
          bsInventoryItemId: [
            { required: true, message: "请填写物料编码", trigger: "blur" }
          ],
          bsSegment1: [
            { required: true, message: "请填写物料类别ID", trigger: "blur" }
          ]
        }
      },
      formItem: {
        id: "",
        bsOrganizationId: "",
        bsInventoryItemId: "",
        bsSegment1: "",
        bsCategoryId: "",
        bsDescription: "",
        bsCategoryDesc: ""
      },
      formQuery: {
        bsSegment1: "",
        bsDescription: ""
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
            title: "组织ID",
            key: "bsOrganizationId"
          },

          {
            title: "物料ID",
            key: "bsInventoryItemId"
          },
          {
            title: "物料编码",
            key: "bsSegment1"
          },
          {
            title: "物料类别ID",
            key: "bsCategoryId"
          },
          {
            title: "物料名称",
            key: "bsDescription"
          },
          {
            title: "物料类别名称",
            key: "bsCategoryDesc"
          },
          {
            title: "操作",
            key: "action",
            render: (h, params) => {
              let ary = [];
              this.menuData.perms.EDIT;
              // if(true) {
              //     ary.push(h('Button', {
              //         props: {
              //             type: 'primary',
              //             size: 'small'
              //         },
              //         style: {
              //             marginRight: '5px'
              //         },
              //         on: {
              //             click: () => {
              //                 this.showEditDialog(params)
              //             }
              //         }
              //     }, '编辑'));
              // }
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
      this.api.lmp.item.selectlist(this.datagrid.queryParams).then(res => {
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
      this.formItem.bsOrganizationId = "";
      this.formItem.bsSegment1 = "";
      this.formItem.bsInventoryItemId = "";
      this.formItem.bsCategoryId = "";
      this.formItem.bsDescription = "";
      this.formItem.bsCategoryDesc = "";
      this.dialog.modal_dialog = true;
    },
    check() {
      if (
        this.formItem.bsOrganizationId == "" ||
        this.formItem.bsOrganizationId == null
      ) {
        this.$Message.error("组织ID不能为空");
        return false;
      } else if (
        this.formItem.bsInventoryItemId == "" ||
        this.formItem.bsInventoryItemId == null
      ) {
        this.$Message.error("物料ID不能为空");
        return false;
      } else if (
        this.formItem.bsSegment1 == "" ||
        this.formItem.bsSegment1 == null
      ) {
        this.$Message.error("物料编码不能为空");
        return false;
      } else {
        return true;
      }
    },
    ok() {
      if (!this.check()) {
        return false;
      }
      this.api.lmp.item.insertItem(this.formItem).then(res => {
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
      this.api.lmp.item.updateItem(this.formItem).then(res => {
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
          this.api.lmp.item.deleteItem(params.row).then(res => {
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
      this.formItem.bsCategoryDesc = params.row.bsCategoryDesc;
      this.formItem.bsSegment1 = params.row.bsSegment1;
      this.formItem.bsOrganizationId = params.row.bsOrganizationId;
      this.formItem.bsInventoryItemId = params.row.bsInventoryItemId;
      this.formItem.bsCategoryId = params.row.bsCategoryId;
      this.formItem.bsDescription = params.row.bsDescription;
      this.dialog.modal_dialog1 = true;
    },
    handleSubmit() {
      this.api.lmp.item.selectByItem(this.formQuery).then(res => {
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