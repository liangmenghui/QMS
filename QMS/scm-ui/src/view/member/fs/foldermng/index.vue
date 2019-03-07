<template>
    <div class="ivu-fs-foldermng">
        <Layout>
            <Sider class="tree-wrapper">
                <comp-folder ref="tree" @on-selected-change="(arg)=>this.selectedFolder = arg"></comp-folder>
            </Sider>
            <Layout>
                <Content class="content">
                    <Tabs>
                        <TabPane label="基本信息" icon="android-folder-open">
                            <Row>
                                <i-col span="12">
                                    <comp-base :folder="selectedFolder"></comp-base>
                                </i-col>
                            </Row>
                        </TabPane>
                        <!-- <TabPane label="文件管理" icon="document"></TabPane>
                        <TabPane label="权限设置" icon="ios-locked-outline"></TabPane> -->
                    </Tabs>
                </Content>
            </Layout>
        </Layout>
    </div>
</template>

<script>
import compFolder from "../folder/index";
import compBase from "./base-info.vue";
export default {
  name: "IVU-FS-FOLDERMNG",
  components: {
    compFolder,
    compBase
  },
  data() {
    return {
      selectedFolder: null
    };
  },
  mounted() {
    this.$on("on-remove-folder", () => {
      this.$refs.tree.handleRemoveFolder();
    });
    this.$on("on-change-folder", arg => {
      this.$refs.tree.handleChangeFolder(arg.data, arg.newName);
    });
  }
};
</script>
<style>
.ivu-fs-foldermng .tree-wrapper {
  padding: 16px 0px 16px 8px;
  background-color: #f1f2f2;
  overflow: auto;
}
.ivu-fs-foldermng .content {
  background-color: #fff;
}
</style>
