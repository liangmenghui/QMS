<template>
  <div class="ivu-cata-item">
    <ul @mouseenter="handleMouseenter" @mouseleave="handleMouseleave">
      <li @click="handleClick">
        <Icon :type="arrapCls"></Icon>
        <div>{{cata.bsCataName}}</div>&nbsp;&nbsp;-&nbsp;&nbsp;{{cata.bsCataDesc}}
      </li>
      <transition name="cataitem">
        <li v-show=" currentVisible">
          <sub-item :firstProdcata="cata" @on-item-click="handleItemClick" @mouseenter.native="handleMouseenter" @mouseleave.native="handleMouseleave"></sub-item>
        </li>
      </transition>
    </ul>
  </div>
</template>
<script>
import subItem from "./cataSubItem";
export default {
  name: "cataItem",
  props: {
    cata: Object
  },
  components: {
    subItem
  },
  data() {
    return {
      currentVisible: false,
      arrawCls: "",
      itemModel: {
        bsCataName: "",
        bsCataDesc: ""
      }
    };
  },
  computed: {
    arrapCls() {
      return this.currentVisible ? "arrow-down-b" : "arrow-right-b";
    }
  },
  methods: {
    handleMouseenter() {
      if (this.timeout) clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        this.currentVisible = true;
      }, 250);
    },
    handleMouseleave() {
      if (this.timeout) {
        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          this.currentVisible = false;
        }, 150);
      }
    },
    handleItemClick(val) {
      this.$emit("on-item-click", val);
    },
    handleClick() {
      this.$emit("on-click", this.cata);
    }
  },
  watch: {
    cata: function(val) {
      this.itemModel = val;
    }
  }
};
</script>
<style scoped>
.ivu-cata-item {
  height: 48px;
  margin: 4px 16px 4px 0px;
  position: relative;
}
.ivu-cata-item > ul {
  cursor: pointer;
  border: 1px solid #dddee1;
  position: absolute;
  list-style-type: none;
  -webkit-transform: scale(1);
  -ms-transform: scale(1);
  transform: scale(1);
  width: 100%;
  min-height: 48px;
  z-index: 1000;
}
.ivu-cata-item > ul:hover {
  border: 1px solid #5cadff;
  z-index: 9999;
}
.ivu-cata-item > ul li:first-child {
  height: 48px;
  line-height: 48px;
  vertical-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  vertical-align: middle;
  padding: 0 0 0 16px;
}
.ivu-cata-item > ul li:first-child div {
  display: inline-block;
  width: 40px;
  text-align: center;
}
.ivu-cata-item > ul li:first-child i {
  padding: 0px 2px;
  width: 8px;
}
.cataitem-enter-active,
.cataitem-leave-active {
  transition: all 0.2s ease-in-out;
  -moz-transition: all 0.2s ease-in-out;
  -webkit-transition: all 0.2s ease-in-out;
  -o-transition: all 0.2s ease-in-out;
}
.cataitem-enter,
.cataitem-leave-active {
  opacity: 0;
}
</style>
