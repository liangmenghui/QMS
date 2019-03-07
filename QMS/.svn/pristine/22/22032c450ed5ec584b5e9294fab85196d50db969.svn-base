<template>
    <div>
        <Row>
            <i-col>
                
            </i-col>
        </Row>
        <Row>
            <i-col>
                <Form class="query_area" :model="formQuery" :label-width="120" inline>
                    <Form-item label="商品编码">
                        <Input type="text" v-model="formQuery.bsCataCode" />
                    </Form-item>
                    <Form-item label="商品名称">
                        <Input type="text" v-model="formQuery.bsCataName" />
                    </Form-item>
                    <Form-item>
                        <Button type="primary" @click="handleSubmit">查 询</Button>
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
import { mapState } from "vuex";
export default {
  data() {
    return {
      formQuery: {
        bsCataCode: "",
        bsCataName: ""
      },
      datagrid: {
        queryParams: {
          page: 1,
          rows: 25,
          pkParent: -1
        },
        pagination: [25, 50, 100],
        data: { rows: [], total: 0 },
        columns: [
          {
            title: "商品编码",
            key: "bsHsCode"
          },
          {
            title: "商品名称",
            key: "bsHsName"
          },
          {
            title: "一级分类",
            key: "bsFirstCataName"
          },
          {
            title: "二级分类",
            key: "bsSecondCataName"
          },
          {
            title: "商品描述",
            key: "bsHsDesc"
          },
          {
            title: "规格",
            key: "bsHsSpeci"
          },
          {
            title: "单位",
            key: "bsUnit"
          },
          {
            title: "单价",
            key: "bsPrice"
          },
          {
            title: "货币",
            key: "bsCurrency"
          },
          {
            title: "退税率",
            key: "bsRebate"
          },
          {
            title: "税率",
            key: "bsRate"
          }
        ]
      }
    };
  },
  created() {},
  computed: {
    ...mapState({
      menuData: state => state.menuData
    })
  },
  methods: {
    handleSubmit(name) {
      this.getData();
    },
    getData() {
      Object.assign(this.formQuery, this.datagrid.queryParams);
      console.log("after===>");
      console.log(this.formQuery);
      this.api.erp.customer.getlist(this.formQuery).then(res => {
        console.log("get list===>");
        console.log(res.data);
        if (res.result) {
          this.datagrid.data = res.data;
        } else {
          this.$Message.error(res.msg);
        }
      });
    },
    reloadData() {
      this.datagrid.data = this.getData();
    },
    changePage(page) {
      this.datagrid.queryParams.page = page;
      this.datagrid.data = this.getData();
    },
    edit(params) {
      console.log(params);
      this.api.erp.customer.edit(params.row).then(res => {
        console.log(res);
        if (res.result) {
          //refresh
          this.reloadData();
        } else {
          this.$Message.error(res.msg);
        }
      });
    },
    delete(params) {
      console.log(params);
      this.$Modal.confirm({
        title: "提示信息",
        content: "<p>是否确定删除？</p>",
        loading: true,
        onOk: () => {
          this.api.erp.customer.delete({ id: params.row.id }).then(res => {
            console.log(res);
            if (res.result) {
              //refresh
              this.reloadData();
              this.$Message.info("删除成功");
              this.$Modal.remove();
            } else {
              this.$Message.error(res.msg);
            }
          });
        }
      });
    },
    download() {
      this.api.erp.customer.download(this.dialog.formItem).then(res => {
        if (res.result) {
          //refresh
          this.reloadData();
        } else {
          this.$Message.error(res.msg);
        }
      });
    },
    showAddDialog() {
      this.dialog.modal_dialog = true;
      this.dialog.formItem = {};
    },
    showEditDialog(params) {
      this.dialog.modal_dialog = true;
      let r = params.row;
      this.dialog.formItem = {
        id: r.id,
        bsCode: r.bsCode,
        bsName: r.bsName,
        bsCustomerId: r.bsCustomerId,
        bsAddress: r.bsAddress,
        bsOrganizationId: r.bsOrganizationId,
        bsRegion: r.bsRegion
      };
    },
    ok() {
      console.log("this.dialog.formItem====>" + typeof this.dialog.formItem.id);
      if (
        typeof this.dialog.formItem.id != undefined &&
        typeof this.dialog.formItem.id == "number"
      ) {
        console.log("编辑1");
        console.log(JSON.stringify(this.dialog.formItem));
        // this.$refs["dialog.ruleForm"].validate((valid) => {
        //     if (valid) {
        this.api.erp.customer.edit(this.dialog.formItem).then(res => {
          if (res.result) {
            //refresh
            this.reloadData();
          } else {
            this.$Message.error(res.msg);
          }
        });
        //     }
        // });
      } else {
        console.log("新增1");
        // this.$refs["dialog.ruleForm"].validate((valid) => {
        //     if (valid) {
        this.api.erp.customer.add(this.dialog.formItem).then(res => {
          if (res.result) {
            //refresh
            this.reloadData();
          } else {
            this.$Message.error(res.msg);
          }
        });
        //     }
        // });
      }
    },
    cancel() {}
  }
};
</script>
