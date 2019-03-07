<template>
    <div>
        <div>
            <Row>
                <i-col>
                    <Form class="query_area" ref="formQuery" :model="formQuery" inline>
                        <Form-item>
                            <Input v-model="formQuery.bsSegment1" placeholder="请输入供应商编号" />
                        </Form-item>
                        <Form-item>
                            <Input v-model="formQuery.bsVendorName" placeholder="请输入供应商名称" />
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
            <Row>
                <i-col span="24">
                    <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe style="height:500px;"></Table>
                    <div style="margin: 10px;overflow: hidden">
                        <div style="float: right;">
                            <Page @on-change="changePage" @on-page-size-change="chageSize" :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" show-total show-elevator show-sizer></Page>
                        </div>
                    </div>
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
                            <Form :model="formItem" :label-width="80" style="text-align:center" :ref="dialog.ruleForm" :rules="dialog.ruleForm">
                                <FormItem label="组织ID" prop='bsOrganizationId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber v-model="formItem.bsOrganizationId" style="width: 255px" placeholder="请输入组织ID"></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="供应商ID" prop='bsVendorId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber v-model="formItem.bsVendorId" style="width: 255px" placeholder="请输入供应商ID"></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="供应商编号" prop='bsSegment1'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="formItem.bsSegment1" placeholder="请输入供应商编号" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="供应商名称" prop='bsVendorName'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="formItem.bsVendorName" placeholder="请输入地址" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="地址" prop='bsAddressLine1'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="formItem.bsAddressLine1" placeholder="请输入地址" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                            </Form>
                        </i-col>
                    </Row>
                </div>
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
                            <Form :model="formItem" :label-width="80" style="text-align:center" :ref="dialog.ruleForm" :rules="dialog.ruleForm">
                                <FormItem label="组织ID" prop='bsOrganizationId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber v-model="formItem.bsOrganizationId" style="width: 255px" placeholder="请输入组织ID"></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="供应商ID" prop='bsVendorId'>
                                    <Row>
                                        <i-col span="15">
                                            <InputNumber v-model="formItem.bsVendorId" style="width: 255px" placeholder="请输入供应商ID"></InputNumber>
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="供应商编号" prop='bsSegment1'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="formItem.bsSegment1" placeholder="请输入供应商编号" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="供应商名称" prop='bsVendorName'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="formItem.bsVendorName" placeholder="请输入地址" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="地址" prop='bsAddressLine1'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="formItem.bsAddressLine1" placeholder="请输入地址" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                            </Form>
                        </i-col>
                    </Row>
                </div>
        </Modal>

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
          bsVendorId: [
            { required: true, message: "请填写供应商ID", trigger: "blur" }
          ],
          bsSegment1: [
            { required: true, message: "请填写供应商编号", trigger: "blur" }
          ]
        }
      },
      formItem: {
        id: "",
        bsOrganizationId: "",
        bsVendorName: "",
        bsSegment1: "",
        bsVendorId: "",
        bsAddressLine1: ""
      },
      formQuery: {
        bsSegment1: "",
        bsVendorName: ""
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
            title: "供应商ID",
            key: "bsVendorId"
          },
          {
            title: "供应商编号",
            key: "bsSegment1"
          },
          {
            title: "供应商名称",
            key: "bsVendorName"
          },
          {
            title: "地址",
            key: "bsAddressLine1"
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
      this.api.lmp.supplier
        .selectSupplier(this.datagrid.queryParams)
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
      this.formItem.bsOrganizationId = "";
      this.formItem.bsVendorId = "";
      this.formItem.bsSegment1 = "";
      this.formItem.bsVendorName = "";
      this.formItem.bsAddressLine1 = "";
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
        this.formItem.bsVendorId == "" ||
        this.formItem.bsVendorId == null
      ) {
        this.$Message.error(" 供应商ID不能为空");
        return false;
      } else if (
        this.formItem.bsSegment1 == "" ||
        this.formItem.bsSegment1 == null
      ) {
        this.$Message.error("供应商编号不能为空");
        return false;
      } else {
        return true;
      }
    },
    ok() {
      if (!this.check()) {
        return false;
      }
      this.api.lmp.supplier.insertSupplier(this.formItem).then(res => {
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
      this.api.lmp.supplier.updateSupplier(this.formItem).then(res => {
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
          this.api.lmp.supplier.deleteSupplier(params.row).then(res => {
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
      this.formItem.bsOrganizationId = params.row.bsOrganizationId;
      this.formItem.bsVendorId = params.row.bsVendorId;
      this.formItem.bsSegment1 = params.row.bsSegment1;
      this.formItem.bsVendorName = params.row.bsVendorName;
      this.formItem.bsAddressLine1 = params.row.bsAddressLine1;
      this.dialog.modal_dialog1 = true;
    },
    handleSubmit() {
      this.api.lmp.supplier.selectBySupplier(this.formQuery).then(res => {
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