<template>
  <div class="ivu-fs-doc">
    <!--
    <Row>
      <i-col span="12">
        <Form :label-width="120" inline >
          <FormItem label="编码">
            <Input style="width:200px;" placeholder="请输入文件编码和产品编码" v-model="bsCode" />
          </FormItem>
        </Form>
      </i-col>
      <i-col span="12">
        <Button type="primary" @click="handlerQuery" >查 询</Button>
      </i-col>
    </Row>
    -->
    <Row>
      <i-col span="3">
        <comp-folder @on-selected-change="selectNode"></comp-folder>
      </i-col>
      <i-col span="21">
        <Row>
          <i-col>
            <div class="folder-list"></div>
          </i-col>
        </Row>
        <Row>
          <i-col>
            <Table :data="datagrid.data.rows" :columns="datagrid.columns" stripe height="560"></Table>
          </i-col>
        </Row>
        <Row type="flex" justify="end">
          <i-col>
            <Page class="paging" :total="datagrid.data.total" :current="1" :page-size="datagrid.queryParams.rows" :page-size-opts="datagrid.pagination" @on-change="changePage" show-total show-elevator show-sizer></Page>
          </i-col>
        </Row>
      </i-col>
    </Row>
  </div>
</template>

<script>
import fsExtend from "./extend";
import compFolder from "../folder/index";

export default {
  components: {
    fsExtend,
    compFolder
  },
  data() {
    return {
      bsCode: "",
      formQuery: {
        bsFolderId: -1
      },
      tree: {
        data: []
      },
      datagrid: {
        queryParams: {
          page: 1,
          rows: 25
        },
        pagination: [25, 50, 100],
        data: { rows: [], total: 0 },
        columns: [
          {
            title: "文件名称",
            key: "bsFile",
            ellipsis: true
          },
          {
            title: "文件编号",
            key: "bsFileCode",
            width: 120
          },
          {
            title: "上传日期",
            key: "bsCreatedTime",
            width: 100,
            render: (h, params) => {
              const t = params.row.bsCreatedTime.substring(0, 10);
              return h("span", t);
            }
          },
          {
            title: "文档状态",
            key: "bsStatus",
            width: 90,
            align: "center",
            render: (h, params) => {
              const bsStatus = params.row.bsStatus;
              let statusText = "";
              if (bsStatus == "UPLOAD") {
                statusText = "已上传";
              } else if (bsStatus == "PUBLISH") {
                statusText = "发布";
              } else if (bsStatus == "NORMAL") {
                statusText = "已发布";
              } else if (bsStatus == "CLOSE") {
                statusText = "关闭";
              } else if (bsStatus == "EXCEPTION") {
                statusText = "异常";
              }
              return h("span", statusText);
            }
          },
          {
            title: "版本",
            key: "bsFileVersion",
            width: 80
          },
          {
            title: "是否受控",
            key: "bsIsControl",
            width: 100,
            render: (h, params) => {
              const bsIsControl = params.row.bsIsControl;
              let controltext = "未受控";
              if (bsIsControl === 1) {
                controltext = "已受控";
              }
              return h("span", controltext);
            }
          },
          {
            title: "操作",
            key: "action",
            width: 120,
            render: (h, params) => {
              let _this = this;
              return h(fsExtend, {
                props: { row: params.row },
                on: {
                  click(type) {
                    _this.getData();
                  }
                }
              });
            }
          }
        ]
      }
    };
  },
  methods: {
    handlerQuery() {
      if (this.bsCode) {
        this.$Message.error("请输入文档编码或者产品编码");
        return;
      }
      this.api.fs.document.release.getFileList(bsCode).then(res => {
        if (res.result) {
        }
      });
    },
    changePage(page) {
      this.datagrid.queryParams.page = page;
      this.datagrid.data = this.getData();
    },
    getData() {
      let params = this.datagrid.queryParams;
      this.api.fs.document.release
        .getListByFolder(this.formQuery.bsFolderId, params)
        .then(res => {
          if (res.result) {
            this.datagrid.data = res.data;
          } else {
            this.$Message.error(res.msg);
          }
        });
    },
    selectNode(arg) {
      if (!arg) return;
      this.formQuery.bsFolderId = arg.data.id;
      this.getData();
    }
  }
};
</script>
<style>
.ivu-fs-doc .doc-query {
  padding: 24px 0 0 0;
  background-color: #f8f8f9;
}
.ivu-fs-doc .left-tree {
  padding: 24px 16px;
}
.ivu-fs-doc .folder-list {
  padding: 8px;
}
.ivu-fs-doc .paging {
  /* margin: 8px 16px; */
}
</style>
