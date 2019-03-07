<template>
  <div>
    <Row>
      <i-col span="24">
        <comp-cata ref="prodcata" @on-click="handleCataClick" @on-item-click="handleCataItemClick"></comp-cata>
      </i-col>
    </Row>
    <Row>
      <i-col>
        <comp-import class="ivu-cata-edit" @on-close="handleRefreshProdcata"></comp-import>
      </i-col>
    </Row>
    <Row>
      <i-col span="24">
        <Table :data="grid.data.rows" :columns="grid.columns" stripe height="500"></Table>
        <div style="margin: 10px;overflow: hidden">
          <div style="float: right;">
            <Page :total="grid.data.total" :current="1" :page-size="grid.page.rows" :page-size-opts="grid.pageSize" @on-change="getProdcata" show-total show-elevator show-sizer></Page>
          </div>
        </div>
      </i-col>
    </Row>
  </div>
</template>
<script>
import { mapState } from "vuex";
import compCata from "./cata";
import compImport from "./import";

export default {
  components: {
    compCata,
    compImport
  },
  data() {
    return {
      query: {
        type: "",
        id: -1
      },
      grid: {
        page: {
          page: 1,
          rows: 25,
          pkParent: -1
        },
        pageSize: [25, 50, 100],
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
  computed: {
    ...mapState({
      menuData: state => state.menuData
    })
  },
  methods: {
    handleCataClick(val) {
      this.query.type = "cata";
      this.query.id = val.id;
      this.getProdcata();
    },
    handleCataItemClick(val) {
      this.query.type = "cata-item";
      this.query.id = val.id;
      this.getProdcata();
    },
    handleRefreshProdcata() {
      this.$refs.prodcata.refresh();
    },
    getProdcata() {
      let param = {};
      Object.assign(param, this.query);
      Object.assign(param, this.grid.query);
      this.api.lmp.hs.paging(param).then(res => {
        if (res.result) {
          this.grid.data = res.data;
          this.grid.rows = res.total;
        }
      });
    }
  }
};
</script>
<style scoped>
.ivu-cata-edit {
  margin: 8px 16px;
}
</style>
