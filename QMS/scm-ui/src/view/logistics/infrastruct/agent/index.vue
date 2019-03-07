<template>
  <div class="ivu-agent">
    <Form class="query_area" ref="formQuery" :model="formQuery" :label-width=120 inline>
      <Row>
        <i-col span="12">
          <Form-item label="货代:">
            <Input v-model="formQuery.bsProvider" />
          </Form-item>
        </i-col>
        <i-col span="12" class="btngroup">
          <Button type="primary" @click="handleSubmit()">查 询</Button>
          <comp-add @closed="getData" class="btn-extern" ></comp-add>
        </i-col>
      </Row>
    </Form>
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
import compAdd from "./add";
import compOpera from "./operator";
// import uRoute from "./transprovider";
export default {
  components: {
    compAdd
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
        showAdd: false,
        showEdit: false,
        ruleForm: {
          bsProvider: [
            { required: true, message: "请填写货代", trigger: "blur" }
          ],
          bsContractor: [
            { required: true, message: "请填写联系人", trigger: "blur" }
          ],
          bsTel: [
            { required: true, message: "请填写电话号码", trigger: "blur" }
          ]
        }
      },
      formItem: {
        id: "",
        bsProvider: "",
        bsFax: "",
        bsAddress: "",
        bsTel: "",
        bsContractor: "",
        bsShipTo: "",
        bsEmail: "",
        bsShipFrom: ""
      },
      formQuery: {
        bsProvider: ""
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
            title: "邮箱",
            key: "bsEmail"
          },
          {
            title: "传真",
            key: "bsFax"
          },
          {
            title: "地址",
            key: "bsAddress"
          },
          {
            title: "电话",
            key: "bsTel"
          },
          {
            title: "联系人",
            key: "bsContractor"
          },
          {
            title: "收货地",
            key: "bsShipTo"
          },
          {
            title: "收货地",
            key: "bsShipFrom"
          },
          {
            title: "操作",
            width: 120,
            key: "action",
            render: (h, params) => {
              return h(compOpera, {
                props: {
                  row: params.row
                },
                on: {
                  refresh: () => {
                    this.getData();
                  }
                }
              });
            }
          }
        ]
      }
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      let param = {};
      Object.assign(param, this.datagrid.queryParams);
      Object.assign(param, { bsProvider: this.formQuery.bsProvider });
      this.api.lmp.provider.selectProvider(param).then(res => {
        if (res.result) {
          this.datagrid.data.rows = res.data.rows;
          this.datagrid.data.total = res.data.total;
        } else {
          this.$Message.error(res.msg);
        }
      });
    },
    handleAdd() {
      Object.keys(this.formItem).forEach(key => {
        this.formItem[key] = "";
      });
      this.dialog.showAdd = true;
    },
    check() {
      if (this.formItem.bsProvider == "" || this.formItem.bsProvider == null) {
        this.$Message.error("供应商名称不能为空");
        return false;
      } else if (
        this.formItem.bsContractor == "" ||
        this.formItem.bsContractor == null
      ) {
        this.$Message.error("联系人不能为空");
        return false;
      } else if (this.formItem.bsTel == "" || this.formItem.bsTel == null) {
        this.$Message.error("联系电话不能为空");
        return false;
      } else {
        return true;
      }
    },
    handleAddSubmit() {
      if (!this.check()) {
        return false;
      }
      this.api.lmp.provider.insertProvider(this.formItem).then(res => {
        if (res.result) {
          this.getData();
          this.$Message.success("添加成功！");
        } else {
          this.$Message.error("添加失败！");
        }
      });
    },
    handleEditSubmit() {
      if (!this.check()) {
        return false;
      }
      this.api.lmp.provider.updateProvider(this.formItem).then(res => {
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
          this.api.lmp.provider.deleteProvider(params.row).then(res => {
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
    handleEdit(params) {
      this.formItem = params.row;
      this.dialog.showEdit = true;
    },
    handleSubmit() {
      this.getData();
    },
    handleBindRouter() {
      this.$router.push("/lmp/infrastruct/forecast/transprovider");
    }, //handleBindRoute
    changePage(pageSize) {
      this.datagrid.queryParams.page = pageSize;
      this.getData();
    },
    chageSize(pageSizeOpts) {
      this.datagrid.queryParams.rows = pageSizeOpts;
      this.getData();
    }
  }
};
</script>
<style>
.ivu-agent .btngroup .ivu-btn,.btn-extern {
  margin-left: 12px;

}
.ivu-agent .btn-extern{
  display: inline-block;
}
</style>
