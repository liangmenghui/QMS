<template>
    <div>
        <Row :gutter="8">
            <i-col :span="12">
                <Button type="error" size="small" @click="deleteRow">删 除</Button>
            </i-col>
            <i-col :span="12">
                <comp-edit :source="row">编 辑</comp-edit>
                <!-- <Dropdown :transfer="true" placement="bottom-end">
                    <Button type="primary" size="small">更 多
                        <Icon type="arrow-down-b"></Icon>
                    </Button>
                    <DropdownMenu slot="list">
                        <DropdownItem>
                            <Row :gutter="16">
                                <i-col :span="12">
                                    <comp-edit :source="row">编 辑</comp-edit>
                                </i-col>
                                <i-col :span="12">
                                    <Button type="primary" size="small"> 绑 定</Button>
                                </i-col>
                            </Row>
                        </DropdownItem>
                    </DropdownMenu>
                </Dropdown> -->
            </i-col>
        </Row>
    </div>
</template>

<script>
import compEdit from "./edit";

export default {
  props: {
    row: {
      type: Object,
      required: true
    }
  },
  components: {
    compEdit
  },
  data() {
    return {};
  },
  methods: {
    deleteRow() {
      this.$Modal.confirm({
        title: "提示信息",
        content: "<p>是否确定删除？</p>",
        loading: true,
        onOk: () => {
          this.api.lmp.provider.deleteProvider(this.row).then(res => {
            if (res.result) {
              this.$Message.success("删除成功！");
              this.$Modal.remove();
              this.refresh();
            } else {
              this.$Message.error("删除失败！");
            }
          });
        }
      });
    },
    refresh() {
      this.$emit("refresh");
    }
  }
};
</script>
