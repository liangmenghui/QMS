<template>
  <div class="ivu-uncom-customer">
    <un-grid-select v-model="selectAgent" :text.sync="selectAgentText" :total="page.total" :pagesize="page.pagesize" :currentPage="page.pageindex" textField="bsProvider" modelField="bsProvider" :datas="agents" :columns="columns" filterLabel="货代编码" @on-page-change="onCustomerChange" @on-filter="onSearch" pageable filterable>
    </un-grid-select>
  </div>
</template>
<script>
const prefixCls = "ivu-uncom-customer";

export default {
  name: "unAgent",
  props: {
    value: {
      type: [Number, String]
    },
    text: {
      type: [Number, String]
    }
  },
  data() {
    return {
      selectAgent: "",
      selectAgentText: "",
      filterText: "",
      agents:[],
      columns: [
        {
          title: "",
          key: "id",
          type: "index",
          width: 40,
          align: "center"
        },
        {
          title: "货代编码",
          key: "bsProvider",
          minWidth: 100
        },
        {
          title: "货代",
          key: "bsProvider",
          minWidth: 120
        }
      ],
      page: {
        total: 0,
        pagesize: 10,
        pageindex: 1
      }
    };
  },
  mounted(){
    this.loadAgents();
  },
  methods: {
    onCustomerChange(pageinfo) {
      this.page.pagesize = pageinfo.pageSize;
      this.page.pageindex = pageinfo.page;
      this.loadAgents();
    },
    onSearch(filterText) {
      this.filterText = filterText;
      this.loadAgents();
    },
    loadAgents() {
      const { filterText, page } = this;
      let param = {
        page: page.pageindex,
        rows: page.pagesize,
        pkParent: -1
      };
      Object.assign(param, { bsProvider: filterText });
      this.api.lmp.provider.selectProvider(param).then(res => {
        if (res.result) {
          page.total = res.data.total;
          this.agents = res.data.rows;
        }
      });
    }
  },
  watch: {
    value(val){
        this.selectAgent = val;
    },
    text(val){
      this.selectAgentText = val;
    },
    selectAgent(now, before) {
      const newValue = JSON.stringify(now);
      const oldValue = JSON.stringify(before);
      if (newValue !== oldValue) {
        const vModelValue = this.selectAgent;
        this.$emit("input", vModelValue); // to update v-model
      }
    },
    selectAgentText(now, before) {
      const newValue = JSON.stringify(now);
      const oldValue = JSON.stringify(before);
      if (newValue !== oldValue) {
        const vModelValue = this.selectAgentText;
        this.$emit("update:text", vModelValue);
      }
    }
  }
};
</script>

<style>
.ivu-uncom-customer {
  width: 100%;
  display: inline-block;
  margin-bottom: -12px;
}
</style>
