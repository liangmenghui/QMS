<template>
  <ul>
    <li v-for="(item,index) in prodcatas" :key="index" @click="handleClickItem($event,item)">{{item.bsCataName}}&nbsp;&nbsp;-&nbsp;&nbsp;{{item.bsCataDesc}} </li>
  </ul>
</template>
<script>
export default {
  props: {
    firstProdcata: {
      type: Object
    }
  },
  data() {
    return {
      prodcatas: []
    };
  },
  mounted() {
    this.getItem();
  },
  methods: {
    getItem() {
      if (!this.firstProdcata) return;
      this.api.lmp.hs.getSecondCata({ id: this.firstProdcata.id }).then(res => {
        if (res.result && res.data) {
          this.prodcatas = res.data;
        }
      });
    },
    handleClickItem: function(e, item) {
      e.preventDefault();
      e.stopPropagation();
      this.$emit("on-item-click", item);
    }
  }
};
</script>
<style scoped>
ul {
  height: 200px;
  background-color: #fff;
  list-style: none;
  overflow-y: auto;
  overflow-x: hidden;
}
ul li {
  padding: 8px 0px 8px 16px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow-x: hidden;
}
ul li:hover {
  background-color: #e9eaec;
}
</style>
