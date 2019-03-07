<template>
  <div class="ivu-extend">
    <Row :gutter="16">
      <i-col :span="12">
        <Button type="primary" size="small">查看</Button>
      </i-col>
      <i-col :span="12">
        <extend-button text="更多">
          <Row :gutter="16" class="extend-row">
            <i-col>
              <Button size="small" type="info" @click="handlePublish">发布</Button>
              <!-- <Button size="small" type="info" @click="handleRepublish">重新发布</Button> -->
              <Button size="small" type="info" @click="handleStart">提交审批</Button>
            </i-col>
          </Row>
          <Row :gutter="16">
            <i-col>
              <Button size="small" type="info" @click="handleDownload">下载</Button>
              <Button size="small" type="info" @click="handleOriginDownload">源文件下载</Button>
              <Button size="small" type="info" @click="handleRemove">删除</Button>
            </i-col>
          </Row>
        </extend-button>
      </i-col>
    </Row>
  </div>
</template>
<script>
import ExtendButton from "./extend-button";
export default {
  name: "iFsDocExtend",
  props: {
    row: {
      type: Object,
      required: true
    }
  },
  data() {
    return {};
  },
  components: {
    ExtendButton
  },
  methods: {
    handleDownload() {
      this.api.fs.document.release
        .download(this.row.id, this.row.bsFileVersion)
        .then(res => {
          if (res.result == false) {
            this.$Message.error(res.msg);
          }
        });
    },
    handleRemove() {
      this.api.fs.document.release
        .remove(this.row.id, this.row.bsFileVersion)
        .then(res => {
          if (res.result) {
            this.$Message.success("删除成功");
            this.$emit("click", "remove");
          } else {
            this.$Message.error(res.msg);
          }
        });
    },
    handleOriginDownload() {
      this.api.fs.document.release
        .downloadOrigin(this.row.id, this.row.bsFileVersion)
        .then(res => {
          if (res.result == false) {
            this.$Message.error(res.msg);
          }
        });
    },
    handlePublish() {
      this.api.fs.document.release
        .publish(this.row.id, this.row.bsFileVersion)
        .then(res => {
          if (res.result) {
            this.$Message.success("操作成功");
            this.$emit("click", "publish");
          } else {
            this.$Message.error(res.msg);
          }
        });
    },
    handleRepublish() {
      this.api.fs.document.release
        .republish(this.row.id, this.row.bsFileVersion)
        .then(res => {
          if (res.result) {
            this.$Message.success("操作成功");
            this.$emit("click", "republish");
          } else {
            this.$Message.error(res.msg);
          }
        });
    },
    handleStart() {
      let processDefinitionKey = "fs-process";
      this.api.fs.document.release
        .start(this.row.id, processDefinitionKey)
        .then(res => {
          if (res.result) {
            this.$Message.success("操作成功");
          } else {
            this.$Message.error(res.msg);
          }
        });
    }
  }
};
</script>
<style>
.extend-row {
  margin-bottom: 12px;
}
</style>
