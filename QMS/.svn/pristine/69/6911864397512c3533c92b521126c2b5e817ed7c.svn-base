<template>
  <div class="ivu-audit">
    <Button :type="btnType" @click="visible =true" :class="cls" :size="size">
      <slot></slot>
    </Button>
    <Modal v-model="visible" title="审批" :loading="loading" @on-ok="handleAccept" @on-cancel="handleReject" ok-text="通过" cancel-text="不通过" width="720">
      <Form :label-width="80">
        <Row>
          <i-col span="20">
            <FormItem label="文档:">
              <Input v-model="fileName" disabled />
            </FormItem>
          </i-col>
          <i-col span="4">
            <Row type="flex" justify="center">
              <i-col>
                <Button type="ghost" style="width:80px;" @click="handleDownload">下 载</Button>
              </i-col>
            </Row>
          </i-col>
        </Row>
        <Row>
          <i-col>
            <FormItem label="审批意见:">
              <Input type="textarea" v-model="suggestion" :autosize="{minRows: 5,maxRows: 5}" />
            </FormItem>
          </i-col>
        </Row>
      </Form>
    </Modal>
  </div>
</template>
<script>
export default {
  name: "FsAudit",
  props: {
    type: {
      type: String,
      default: "ghost"
    },
    cls: {
      type: String,
      default: ""
    },
    size: {
      type: String,
      default: "default"
    },
    docId: {
      type: [Number, String]
      //   required: true
    },
    processId: {
      type: [Number, String]
      //   required: true
    }
  },
  data() {
    return {
      visible: false,
      loading: true,
      btnType: this.type,
      fileName: "",
      suggestion: ""
    };
  },
  created() {
    let { docId } = this;
    if (docId) {
      this.api.fs.document.release.getDocument(docId, "").then(res => {
        if (res.result) {
          this.fileName = res.data.bsFile;
        } else {
          this.$Message.error(res.msg);
        }
      });
    }
  },
  methods: {
    close() {
      this.visible = false;
    },
    suspend() {
      this.loading = false;
      this.$nextTick(() => {
        this.loading = true;
      });
    },
    handleDownload() {
      this.api.fs.document.release.downloadOrigin(this.docId, "").then(res => {
        if (!res.result) {
          this.$Message.error(res.msg);
        }
      });
    },
    handleAccept() {
      let { processId, suggestion } = this;
      this.suspend();
      this.api.fs.document.release
        .workflowVerify(processId, { comment: suggestion, accept: 1 })
        .then(res => {
          if (res.result) {
            this.$Message.success("提交成功");
            this.close();
            this.$emit("close", { dialogReuslt: "yes" });
          } else {
            this.$Message.error(res.msg);
          }
        });
    },
    handleReject() {
      let { processId, suggestion } = this;
      this.suspend();
      this.api.fs.document.release
        .workflowVerify(processId, { comment: suggestion, accept: 0 })
        .then(res => {
          if (res.result) {
            this.$Message.success("提交成功");
            tis.close();
            this.$emit("close", true);
          } else {
            this.$Message.error(res.msg);
          }
        });
    }
  }
};
</script>
<style>
</style>
