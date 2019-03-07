<template>
  <div style="width:100%;display:inline-block;margin-bottom:-12px;">
    <un-grid-select v-model="displayText" modelField="bsConfigurationName" :datas="datas" :columns="columns" filterLabel="插件名称" @on-filter="handleSearch" @on-row-click="handleSelect" filterable>
    </un-grid-select>
  </div>
</template>
<script>
const prefixCls = "ivu-uncom-content";

export default {
  name: "unPluginContent",
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
      displayText: "",
      datas: [],
      columns: [
        {
          title: "NO",
          key: "id",
          type: "index",
          width: 60,
          align: "center"
        },
        {
          title: "插件名称",
          key: "bsConfigurationName",
          width: 320,
          align: "center"
        },
        {
          title: "Template Component",
          key: "bsTemplateComponent",
          minWidth: 60
        },
        {
          title: "Content Component",
          key: "bsContentComponent",
          minWidth: 60
        }
      ]
    };
  },
  mounted() {
    this.handleSearch("");
  },
  methods: {
    handleSearch(searchText) {
      this.api.lmp.bookingconfiguration
        .contentList({ name: searchText })
        .then(res => {
          if (res.result) {
            this.datas = res.data;
          }
        });
    },
    handleSelect(row) {
      this.$emit("on-select", row);
    }
  },
  watch: {
    value(val) {
      this.displayText = val;
    },
    displayText(now, before) {
      const newValue = JSON.stringify(now);
      const oldValue = JSON.stringify(before);
      if (newValue !== oldValue) {
        this.$emit("input", this.displayText); // to update v-model
      }
    }
  }
};
</script>

