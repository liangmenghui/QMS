<template>
  <div ref="floder" class="fs-release-folder" @mouseenter="handleMouseEnter" @mouseleave="handleMouseLeave">
    <div class="label">目录:</div>
    <div class="text">
      <Tooltip>
        {{displayText}}
        <div slot="content" style="white-space: normal;">{{displayText}}</div>
      </Tooltip>
    </div>
    <div class="setting" v-show="buttonVisible">
      <Poptip v-model="dropdownVisible" trigger="click" placement="bottom-end">
        <Button type="info" size="small" icon="ios-gear-outline">设置</Button>
        <div slot="content">
          <Tree ref="tree" :data="folderList" @on-select-change="selectNode"></Tree>
        </div>
      </Poptip>
    </div>
  </div>
</template>
<script>
import extendButton from "../doc/extend-button";

function getPath(nodes, findVal) {
  if (!nodes || !findVal) return;
  for (let i = 0; i < nodes.length; i++) {
    let element = nodes[i];
    if (element.id === findVal) {
      return element.title;
    }
  }
  for (let i = 0; i < nodes.length; i++) {
    let item = nodes[i];
    if (item.children) {
      let ret = getPath(item.children, findVal);
      if (ret) {
        return item.id == -1 ? ret : item.title + "/" + ret;
      }
    }
  }
}
function findNode(nodes, findVal) {
  if (!nodes || !findVal) return;
  for (let i = 0; i < nodes.length; i++) {
    let element = nodes[i];
    if (element.id === findVal) {
      element.expand = true;
      element.selected = true;
      return true;
    }
  }
  for (let i = 0; i < nodes.length; i++) {
    let item = nodes[i];
    if (item.children) {
      if (findNode(item.children, findVal) == true) {
        item.expand = true;
        return true;
      }
    }
  }
}
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
  name: "fsReleaseFolder",
  props: {
    value: [String, Number],
    text: [String]
  },
  components: {
    extendButton
  },
  data() {
    return {
      selectedValue: "",
      selectedText: "",
      selectedNode: {},
      emptyText: "点击设置目录",
      buttonVisible: false,
      dropdownVisible: true,
      folderList: []
    };
  },
  mounted() {
    if (this.value) this.selectedValue = this.value;
    if (this.text) this.selectedText = this.text;
    this.getFolder();
  },
  methods: {
    getFolder() {
      if (this.folderList && this.folderList.length > 0) {
        return;
      }
      this.api.fs.document.folder.getTree().then(res => {
        this.folderList = [];
        if (res.result) {
          let data = [{ id: -1, expand: false, title: "目录", children: [] }];
          data[0].children = res.data;
          this.folderList = data;
          closeNode(data);
          data[0].expand = true;
          //初始化选中值
          if (this.value) {
            let findVal = this.value;
            findNode(data, findVal);
          }
        }
      });
    },
    handleMouseEnter() {
      this.buttonVisible = true;
    },
    handleMouseLeave() {
      this.buttonVisible = false;
    },
    selectNode(node) {
      if (!node) return;
      let currentNode = node.length == 0 ? this.selectedNode : node[0];
      if (!currentNode || currentNode.length == 0) {
        return;
      }
      if (node.length != 0) {
        this.selectedNode = node[0];
      }

      if (
        currentNode.id == -1 ||
        (currentNode.children && currentNode.children.length != 0)
      ) {
        currentNode.expand = !currentNode.expand;
      } else {
        this.selectedValue = currentNode.id;
        this.selectedText = currentNode.attributes.bsUrl; //getPath(this.folderList, currentNode.id);
        this.dropdownVisible = false;
        this.$emit("change", {
          id: this.selectedValue,
          title: this.selectedText
        });
      }
    }
  },
  computed: {
    displayText() {
      const { selectedText, emptyText } = this;
      return selectedText ? selectedText : emptyText;
    }
  },
  watch: {
    buttonVisible() {
      let { folderList, dropdownVisible } = this;
      dropdownVisible = this.buttonVisible;
      if (!folderList || folderList.length == 0) return;
      findNode(folderList, this.value);
    }
  }
};
</script>
<style>
.fs-release-folder {
  box-sizing: border-box;
  display: flex;
  width: 100%;
}
.fs-release-folder .label {
  width: 70px;
  display: inline-block;
  text-align: right;
  vertical-align: middle;
  box-sizing: border-box;
  color: #495060;
  font-weight: 1;
  padding: 10px 12px 10px 0;
  font-size: 12px;
}
.fs-release-folder .text {
  font-weight: 1;
  padding: 10px 0 10px 0;
  flex: 1;
  color: #495060;
  font-size: 12px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.fs-release-folder .setting {
  padding: 6px 0px 6px 0px;
  display: inline-block;
  width: 60px;
}
</style>
