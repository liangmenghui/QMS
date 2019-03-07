<template>
  <div ref="permission" class="fs-release-permission">
    <Form :label-width="80">
      <FormItem label="文档权限:">
        <ButtonGroup>
          <Button v-for="(item,index) in permissionList" :key="index" :type="type(index)" @click="handleClick(index)" size="small">{{item.text}}</Button>
        </ButtonGroup>
      </FormItem>
    </Form>
  </div>
</template>
<script>
export default {
    name: "fsReleasePermission",
    props: {
        value: String
    },
    data() {
        return {
            selectedIndex: 0,
            permissionList: []
        };
    },
    created() {
        this.permissionList.push({ name: "public", text: "公开" });
        this.permissionList.push({ name: "internal", text: "内部公开" });
        this.permissionList.push({ name: "protected", text: "机密" });
        this.permissionList.push({ name: "private", text: "绝密" });
        this.$emit("change", this.permissionList[this.selectedIndex]);
    },
    methods: {
        handleClick(index) {
            this.selectedIndex = index;
            this.$emit("change", this.permissionList[this.selectedIndex]);
        },
        type(index) {
            return this.selectedIndex === index ? "primary" : "ghost";
        }
    },
    watch: {
        value(val) {
            if (!val) return;
            for (let i = 0; i < this.permissionList.length; i++) {
                let item = this.permissionList[i];
                if (item.name === val) {
                    this.selectedIndex = i;
                    break;
                }
            }
        }
    }
};
</script>
<style>
.fs-release-permission .label {
    text-align: right;
    vertical-align: middle;
    box-sizing: border-box;
    color: #495060;
    font-weight: 1;
    float: left;
    padding: 10px 12px 10px 0;
    font-size: 12px;
}
.fs-release-permission .btn {
    position: relative;
    padding: 6px 0 6px 0;
    font-weight: 1;
    text-align: left;
    vertical-align: middle;
    box-sizing: border-box;
}
</style>
