<template>
  <div class="ivu-uncom-customer">
    <un-grid-select v-model="selectCustomer" :text.sync="selectCustomerText" :total="page.total" :pagesize="page.pagesize" :currentPage="page.pageindex" textField="bsName" modelField="bsCode" :datas="customers" :columns="columns" filterLabel="客户编码" @on-page-change="onCustomerChange" @on-filter="onSearch" pageable filterable>
    </un-grid-select>
  </div>
</template>
<script>
const prefixCls = "ivu-uncom-customer";

export default {
  name: "unCustomer",
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
      selectCustomer: "",
      selectCustomerText: "",
      filterCustomerCode: "",
      customers: [],
      columns: [
        {
          title: "",
          key: "id",
          type: "index",
          width: 40,
          align: "center"
        },
        {
          title: "Organization ID",
          key: "bsOrganizationId",
          width: 140,
          align: "center"
        },
        {
          title: "Customer Code",
          key: "bsCode",
          minWidth: 100
        },
        {
          title: "Customer Name",
          key: "bsName",
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
  mounted() {
    this.loadPageData();
  },
  methods: {
    onCustomerChange(pageinfo) {
      this.page.pagesize = pageinfo.pageSize;
      this.page.pageindex = pageinfo.page;
      this.loadPageData();
    },
    loadPageData() {
      const { filterCustomerCode, page } = this;
      let param = {
        page: page.pageindex,
        rows: page.pagesize,
        pkParent: -1
      };
      Object.assign(param, { bsCode: filterCustomerCode });
      this.api.erp.customer.getlist(param).then(res => {
        if (res.result) {
          this.customers = res.data.rows;
          page.total = res.data.total;
        }
      });
    },
    onSearch(filterText) {
      this.filterCustomerCode = filterText;
      this.loadPageData();
    }
  },
  watch: {
    value(val) {
      this.selectCustomer = val;
    },
    text(val){
      this.selectCustomerText = val;
    },
    selectCustomer(now, before) {
      const newValue = JSON.stringify(now);
      const oldValue = JSON.stringify(before);
      if (newValue !== oldValue) {
        const vModelValue = this.selectCustomer;
        this.$emit("input", vModelValue); // to update v-model
      }
    },
    selectCustomerText(now, before) {
      const newValue = JSON.stringify(now);
      const oldValue = JSON.stringify(before);
      if (newValue !== oldValue) {
        const vModelValue = this.selectCustomerText;
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
