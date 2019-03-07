<template>
  <Tree :data="data" :render="handleRenderTree"></Tree>
</template>
<script>
import compTreeItem from "./tree-item";

function closeNode(nodes) {
  if (!nodes) return;
  nodes.forEach(item => {
    item.expand = false;
    item.selected = false;
    if (item.children) {
      closeNode(item.children);
    }
  });
}

export default {
  name: "IVU-FS-FOLDER",
  created() {
    this.getTree();
  },
  components: {
    compTreeItem
  },
  props: {
    editable: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      data: [{ id: -1, expand: false, title: "目录", children: [] }],
      selectedNode: null
    };
  },
  methods: {
    handleRenderTree(h, { root, node, data }) {
      let _this = this;
      return h(compTreeItem, {
        props: {
          root: root,
          node: node,
          data: data,
          editable: this.editable
        },
        on: {
          change(args) {
            _this.handleChangeFolder(args.data, args.newtitle);
          },
          click(args) {
            _this.selectedNode = args;
          }
        }
      });
    },
    getTree() {
      this.api.fs.document.folder.getTree().then(res => {
        this.folderList = [];
        if (res.result) {
          let data = [{ id: -1, expand: false, title: "目录", children: [] }];
          data[0].children = res.data;
          this.folderList = data;
          closeNode(data);
          data[0].expand = true;
          this.data = data;
        }
      });
    },
    handleChangeFolder(nodeData, newTitle) {
      this.api.fs.document.folder.edit(nodeData.id, newTitle).then(res => {
        if (res.result) {
          nodeData.title = newTitle;
        } else {
          this.$Message.error(res.msg);
        }
      });
    },
    handleAddFolder() {
      if (!this.selectedNode) {
        this.$Message.error("请选择父目录");
        return;
      }
      let { data } = this.selectedNode;
      let newFolder = "";

      this.$Modal.confirm({
        render: h => {
          return h("Input", {
            props: {
              value: this.value,
              autofocus: true,
              placeholder: "请输入目录名称"
            },
            on: {
              input: val => {
                newFolder = val;
              }
            }
          });
        },
        onOk: () => {
          if (!newFolder) {
            return;
          }

          this.api.fs.document.folder.add(data.id, newFolder).then(res => {
            if (res.result) {
              const children = data.children || [];
              children.push({
                id: res.data.id,
                expand: false,
                title: res.data.bsName,
                children: []
              });
              data.expand = true;
              this.$set(data, "children", children);
            } else {
              this.$Message.error(res.msg);
            }
          });
        }
      });
    },
    handleRemoveFolder() {
      if (!this.selectedNode) {
        this.$Message.error("请选择要删除的目录");
        return;
      }
      if (this.selectedNode.data.attributes.bsSys == true) {
        this.$Message.error("不能删除系统目录");
        return;
      }

      let { data, root, node } = this.selectedNode;
      this.api.fs.document.folder.remove(data.id).then(res => {
        if (res.result) {
          const parentKey = root.find(el => el === node).parent;
          const parent = root.find(el => el.nodeKey === parentKey).node;
          const index = parent.children.indexOf(data);
          parent.children.splice(index, 1);
          this.$Message.success("删除成功");
          this.selectedNode = undefined;
        } else {
          this.$Message.error(res.msg);
        }
      });
    }
  },
  watch: {
    selectedNode() {
      if (!this.selectedNode) return;
      this.$emit("on-selected-change", this.selectedNode);
    }
  }
};
</script>
