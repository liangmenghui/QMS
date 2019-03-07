<template>
  <div class="ivu-release-tree-item" @dblclick="handleDoubleClick" @click="handleClick" v-click-outside="handleCommit">
    <span class="wrapper">
      <span v-show="readonly">
        <Icon type="ios-folder-outline" class="icon"></Icon>
        <span :class="titleCls">{{this.data.title}}</span>
      </span>
      <span v-show="!readonly">
        <Icon type="ios-folder-outline" class="icon"></Icon>
        <Input ref="title" size="small" v-model="title" @on-enter="handleCommit" @on-blur="handleCommit" />
      </span>
    </span>
  </div>
</template>
<script>
import { directive as clickOutside } from "v-click-outside-x";
import Emitter from "@/libs/mixins/emitter";
export default {
  name: "FsReleaseTreeItem",
  props: {
    data: Object,
    node: [Object, Array],
    root: [Object, Array],
    editable: Boolean
  },
  directives: { clickOutside },
  mixins: [Emitter],
  data() {
    return {
      internaldata: this.data,
      internalnode: this.node,
      internalroot: this.root,
      readonly: true,
      title: this.data.title
    };
  },
  methods: {
    handleDoubleClick() {
      if (this.internaldata.id == -1 || this.editable == false||this.internaldata.attributes.bsSys == true) {
        return;
      }
      this.readonly = false;
      this.$refs.title.focus();
    },
    handleClick() {
      if (this.readonly == false) return;
      this.internaldata.expand = !this.internaldata.expand;

      this.$emit("click", {
        root: this.internalroot,
        node: this.internalnode,
        data: this.internaldata
      });

      if (this.data.disabled) return;
      this.dispatch("Tree", "on-selected", this.data.nodeKey);
    },
    handleCommit() {
      if (this.readonly == true) {
        return;
      }
      if (this.title != this.internaldata.title) {
        this.$emit("change", {
          root: this.internalroot,
          node: this.internalnode,
          data: this.internaldata,
          newtitle: this.title
        });
      }
      this.readonly = true;
    }
  },
  computed: {
    titleCls() {
      return [
        `ivu-tree-title`,
        {
          [`ivu-tree-title-selected`]: this.internalnode.node.selected
        }
      ];
    }
  }
};
</script>
<style>
.ivu-release-tree-item {
  display: inline-block;
}
.ivu-release-tree-item .wrapper {
  display: inline-block;
  width: 100%;
  padding: 2px;
  cursor: pointer;
}
.ivu-release-tree-item .icon {
  margin-right: 6px;
}
</style>
