<template>
  <div class="ivu-fs-release">
    <div class="fs-header">
      <h2>文档发布</h2>
    </div>
    <div class="ivu-fs-release-wrapper">
      <div class="fs-toolbar">
        <Row>
          <i-col>
            <Alert show-icon closable>文档默认权限为公开; 签字、盖章默认位置为第一页，要更改请在更多设置中设置</Alert>
          </i-col>
        </Row>
        <Row>
          <i-col span="3">
            <div class=" btn-box">
              <div class="btn-box-item">
                <Button size="small" type="ghost" @click="$refs.tree.handleAddFolder()" icon="android-add">添加目录</Button>
              </div>
              <!-- <div class="btn-box-item">
                <Button size="small" type="ghost" @click="$refs.tree.handleRemoveFolder()" icon="android-remove">删除目录</Button>
              </div> -->
            </div>
          </i-col>
          <i-col span="21">
            <div class=" btn-box">
              <div class="btn-box-item">
                <Upload ref="upload" :data="upload.params" :show-upload-list="false" :action="upload.url" :before-upload="handleBeforeUpload">
                  <Button size="small" type="ghost" icon="android-add">添加文件</Button>
                </Upload>
              </div>
              <div class="btn-box-item">
                <Button size="small" type="ghost" @click="handleRemoveFile" icon="android-remove"> 删除文件</Button>
              </div>
              <div class="btn-box-item">
                <Poptip placement="bottom" width="600">
                  <Button size="small" type="ghost" icon="android-more-horizontal">更多设置</Button>
                  <div slot="content" class="setting-more">
                    <Row>
                      <i-col>
                        <div class="btn-box">
                          <div class="btn-box-item" style="width:290px;">
                            <comp-permission @change="handleChangeAllPermission"></comp-permission>
                          </div>
                        </div>
                      </i-col>
                    </Row>
                    <Row>
                      <i-col>
                        <div class="btn-box">
                          <div class="btn-box-item">
                            <comp-extend @change="handleSetAllSign"></comp-extend>
                          </div>
                        </div>
                      </i-col>
                    </Row>
                  </div>
                </Poptip>
              </div>
            </div>
          </i-col>
        </Row>
      </div>
      <Layout>
        <Sider class="tree-wrapper">
          <comp-folder ref="tree" @on-selected-change="(arg)=>{this.selectedNode = arg}" editable></comp-folder>
        </Sider>
        <Layout>
          <Content>
            <div class="fs-body">
              <Table ref="grid" :data="data" :columns="grid.columns" :height="400" :show-header="false" @on-current-change="handleRowChange" no-data-text="请添加要发布的WORD文档" stripe highlight-row></Table>
            </div>
          </Content>
        </Layout>
      </Layout>
    </div>
    <div class="fs-footer">
      <div class="btn-box">
        <div class="btn-box-item">
          <Button type="ghost" size="small" icon="ios-cloud-upload-outline" @click="handleUpload(false)">上传</Button>
        </div>
        <div class="btn-box-item">
          <Button type="ghost" size="small" icon="android-upload" @click="handleUpload(true)">上传并发布</Button>
        </div>
        <div class="btn-box-item">
          <Checkbox v-model="isControl">是否受控文件</Checkbox>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import compPermission from "./permission";
import compFile from "./filename";
import compDoUpload from "./do-upload";
import compInput from "./edit-input";
import compExtend from "./extend";
import compFolder from "../folder/index";

export default {
  name: "iFsRelease",
  components: {
    compPermission,
    compFolder,
    compFile,
    compDoUpload,
    compInput,
    compExtend
  },
  data() {
    let vm = this;
    return {
      upload: {
        url: "/admin/fs/doc/release/upload",
        params: {},
        filetype: ["docx", "doc"]
      },
      grid: {
        columns: [
          {
            key: "order",
            title: "order",
            type: "selection",
            width: 40
          },
          {
            key: "fileName",
            width: 300,
            render: (h, params) => {
              return h(compFile, {
                props: {
                  value: params.row.fileName,
                  readonly: true
                }
              });
            }
          },
          {
            key: "code",
            width: 200,
            render: (h, params) => {
              return h(compInput, {
                props: {
                  label: "文件编号:",
                  value: params.row.code,
                  status: params.row.uploadstatus
                },
                on: {
                  change: node => {
                    let row = vm.data[params.index];
                    row.code = node;
                  }
                }
              });
            }
          },
          {
            key: "version",
            width: 200,
            render: (h, params) => {
              return h(compInput, {
                props: {
                  label: "版本:",
                  value: params.row.version,
                  status: params.row.uploadstatus
                },
                on: {
                  change: node => {
                    let row = vm.data[params.index];
                    row.version = node;
                  }
                }
              });
            }
          },
          {
            key: "upload",
            width: 300,
            render: (h, params) => {
              let index = params.index;
              return h(compDoUpload, {
                ref: "upload",
                props: {
                  file: params.row.file,
                  status: params.row.uploadstatus,
                  exception: params.row.uploadexception,
                  action: vm.upload.url,
                  format: vm.upload.format
                },
                on: {
                  change(form) {
                    vm.data[index].uploadstatus = form.status;
                    vm.data[index].uploadexception = form.exception;
                  }
                }
              });
            }
          }
        ]
      },
      data: [],
      selectedNode: null,
      isControl: false
    };
  },
  methods: {
    handleBeforeUpload(file) {
      if (!this.selectedNode) {
        this.$Message.error("请选择要上传的目录");
        return;
      }
      for (let i in this.data) {
        let item = this.data[i];
        if (item.fileName == file.name) {
          this.$Message.error("文件已存在列表中。");
          return false;
        }
      }
      this.data.push({
        fileName: file.name,
        file: file,
        sign: "first",
        seal: "first",
        permission: "public"
      });
      return false;
    },
    handleRowChange(currentRow, oldCurrentRow) {
      let objData = this.$refs.grid.$refs.tbody.objData;
      let element = objData[currentRow.keyIndex];
      if (element) {
        element._isChecked = !element._isChecked;
        element._isHighlight = !element._isHighlight;
      }
    },
    handleRemoveFile() {
      let selections = this.$refs.grid.getSelection();
      let selectionIndexs = [];
      selections.forEach(element => selectionIndexs.push(element.keyIndex));
      this.data = this.data.filter(
        (item, index) => selectionIndexs.indexOf(index) == -1
      );
    },
    handleSelectedChange(arg) {
      this.selectedNode = arg;
    },
    handleChangeAllPermission(permission) {
      this.data.forEach(item => {
        item.permission = permission.name;
      });
    },
    handleSetAllSign(val) {
      if (val.sign) {
        this.data.forEach(item => {
          item.sign = val.sign;
        });
      }
      if (val.seal) {
        this.data.forEach(item => {
          item.seal = val.seal;
        });
      }
    },
    handleUpload(publish) {
      if (this.data.length == 0) {
        this.$Message.error("请选择要上传的文件");
        return;
      }

      for (let index in this.data) {
        let item = this.data[index];
        item.publish = publish;
        if (!item.permission) {
          this.$Message.error("未设置权限");
          return;
        }
        if (!item.code) {
          this.$Message.error("未设置编码");
          return;
        }
        if (!item.version) {
          this.$Message.error("未设置版本");
          return;
        }
        if (!item.sign) {
          this.$Message.error("未设置签名位置");
        }
        if (!item.seal) {
          this.$Message.error("未设置盖章位置");
        }
      }

      this.$refs.grid.$refs.tbody.$children.forEach(item => {
        let data = {};
        let index = item._props.row.keyIndex;
        let vm = this;
        data.permission = vm.data[index].permission;
        data.folderId = this.selectedNode.data.id;
        data.folder = this.selectedNode.data.title;
        data.fileName = vm.data[index].fileName;
        data.publish = vm.data[index].publish;
        data.file = vm.data[index].file;
        data.status = vm.data[index].uploadstatus;
        data.exception = vm.data[index].uploadexception;
        data.version = vm.data[index].version;
        data.code = vm.data[index].code;
        data.sign = vm.data[index].sign;
        data.seal = vm.data[index].seal;
        data.isControl = this.isControl ? 1 : 0;
        if (data.status != "finished") {
          item.$children.forEach(cell => {
            let upload = cell.$refs.upload;
            if (upload) {
              upload._data.data = data;
              upload.post();
            }
          });
        }
      });
    }
  },
  computed: {
    acceptype() {
      let tmp = this.upload.filetype.reduce((x1, x2) => {
        return (x1 += ",." + x2);
      });
      return "." + tmp;
    }
  },

  watch: {
    data: {
      handler() {
        this.data.forEach((item, index) => {
          item.keyIndex = index;
        });
      }
    }
  }
};
</script>
<style>
.ivu-fs-release .fs-header {
  background-color: #dddee1;
  margin: 0 0 24px 0;
  padding: 24px 16px;
  height: 80px;
  line-height: 36px;
  vertical-align: middle;
}
.ivu-fs-release .fs-header h2 {
  margin-left: 32px;
}
.ivu-fs-release .fs-body {
  margin: 0 16px 12px 16px;
}
.ivu-fs-release .ivu-fs-release-wrapper .fs-toolbar {
  margin: 0px 0 4px 0;
}
.ivu-fs-release .btn-box {
  display: inline-block;
  overflow: hidden;
}
.ivu-fs-release .btn-box .btn-box-item {
  margin-left: 8px;
  display: inline-block;
}
.ivu-fs-release .btn-box .btn-box-item:first-child {
  margin-left: 0px;
}
.ivu-fs-release .ivu-fs-release-wrapper .fs-body {
  background-color: #f8f8f9;
  height: 400px;
}
.ivu-fs-release .ivu-table-wrapper {
  border: none 0 #fff;
}
.ivu-fs-release .ivu-table-wrapper .ivu-table,
.ivu-table::before,
.ivu-table::after,
.ivu-table td {
  background-color: #f8f8f9;
}
.ivu-fs-release .fs-footer {
  text-align: center;
  margin-top: 12px;
}
.ivu-fs-release .fs-footer .ivu-btn {
  width: 120px;
}
.ivu-fs-release .tree-wrapper {
  padding: 16px 0px 16px 8px;
  background-color: #f1f2f2;
  overflow: auto;
}
</style>
