<template>
  <div class="ivu-cata-wrap">
    <div class="ivu-cata-title">
      <h3>商品分类</h3>
    </div>
    <div>
      <Row :gutter="16" v-for="(row,index) in catas" :key="index">
        <i-col :span="4" v-for="cellIndex in cellCount" :key="cellIndex">
          <cata-item v-if="row[cellIndex-1]" :cata="row[cellIndex-1]" @on-click="handleCataClick" @on-item-click="handleCataItemClick"></cata-item>
        </i-col>
      </Row>
    </div>
  </div>
</template>

<script>
const rowCellCount = 6;
import cataItem from "./cataItem";

export default {
  components: {
    cataItem
  },
  data() {
    return {
      cellCount: rowCellCount,
      catas: []
    };
  },
  mounted() {
    this.getCatas();
  },
  methods: {
    getCatas() {
      this.catas = [];
      this.api.lmp.hs.getFirstCata().then(res => {
        if (res.result && res.data && res.data.length) {
          let src = res.data;
          let row = 0;
          let total = 0;
          while (total < src.length) {
            let tmp = [];
            for (let i = 0; i < rowCellCount; i++, ++total) {
              if (total > src.length) break;
              tmp.push(src[row * rowCellCount + i]);
            }
            row++;
            this.catas.push(tmp);
          }
          //初始化数据 模拟单击功能 注意方法调用
          this.handleCataClick(src[0]);
        }
      });
    },
    handleCataClick(val) {
      this.$emit("on-click", val);
    },
    handleCataItemClick(val) {
      this.$emit("on-item-click", val);
    },
    refresh() {
      this.getCatas();
    }
  },
  computed: {}
};
</script>
<style scoped>
.ivu-cata-wrap {
  background-color: #f8f8f9;
  padding: 32px 24px;
  margin: 24px 16px;
  border-radius: 6px;
  position: relative;
}
.ivu-cata-title {
  padding: 16px 8px;
  border-bottom: 1px solid #dddee1;
  margin-bottom: 8px;
}
</style>
