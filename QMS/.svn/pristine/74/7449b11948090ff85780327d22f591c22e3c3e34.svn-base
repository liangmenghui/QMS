<template>
    <div class="fs-foldermng-base">
        <Row>
            <i-col>
                <Form :label-width="120">
                    <FormItem label="目录名称">
                        <Row :gutter="16">
                            <i-col span="19">
                                <Input v-model="form.bsName" />
                            </i-col>
                            <i-col span="4">
                                <Button type="ghost" @click="handleRemoveFolder">删除目录</Button>
                            </i-col>
                        </Row>
                    </FormItem>
                    <FormItem label="目录代码">
                        <Row :gutter="16">
                            <i-col span="12">
                                <Input v-model="form.bsCode" disabled />
                            </i-col>
                            <i-col span="4">
                                <FormItem>
                                    <Checkbox v-model="form.bsSys" disabled>系统目录</Checkbox>
                                </FormItem>
                            </i-col>
                        </Row>
                    </FormItem>
                    <FormItem label="物理路径">
                        <Row :gutter="16">
                            <i-col span="19">
                                <Input v-model="form.bsUrl" disabled />
                            </i-col>
                            <i-col span="4">
                                <!-- <Button type="ghost">更改路径</Button> -->
                            </i-col>
                        </Row>
                    </FormItem>
                    <FormItem label="上级目录">
                        <Row :gutter="16">
                            <i-col span="12">
                                <Input v-model="bsParentName" disabled />
                            </i-col>
                        </Row>
                    </FormItem>
                    <FormItem label="创建时间">
                        <Row :gutter="16">
                            <i-col span="12">
                                <Input v-model="form.bsCreatedTime" disabled />
                            </i-col>
                        </Row>
                    </FormItem>
                </Form>
            </i-col>
        </Row>
        <div class="footer">
            <div class="btn-box">
                <div class="btn-box-item">
                    <Button type="success" @click="handleSetFolder">设 置</Button>
                </div>
                <div class="btn-box-item">
                    <Button type="ghost" @click="reset">重 置</Button>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import emitter from "@/libs/mixins/emitter";

export default {
  name: "FS_FOLDERMNG_BASE",
  props: {
    folder: {
      type: [Object]
    }
  },
  mixins: [emitter],
  data() {
    return {
      form: {
        bsName: "",
        bsCode: "",
        bsUrl: "",
        bsParent: {},
        bsSys: false,
        bsCreatedTime: ""
      }
    };
  },
  methods: {
    reset() {
      Object.keys(this.form).forEach(el => (this.form[el] = ""));
      this.form.bsSys = false;
    },
    handleRemoveFolder() {
      if (!this.form.bsCode) return;
      this.$Modal.confirm({
        title: "删除目录",
        content: "将删除目录及其所有文件以及子目录，是否继续?",
        onOk: () => {
          this.dispatch("IVU-FS-FOLDERMNG", "on-remove-folder");
        }
      });
    },
    handleSetFolder() {
      if (!this.form.bsCode) return;
      this.dispatch("IVU-FS-FOLDERMNG", "on-change-folder", {
        data: this.folder.data,
        newName: this.form.bsName
      });
    }
  },
  computed: {
    bsParentName() {
      return this.form.bsParent ? this.form.bsParent.bsName : "";
    }
  },
  watch: {
    folder() {
      this.reset();
      if (!this.folder || this.folder.data.id == -1) {
        return;
      }
      this.api.fs.document.folder.get(this.folder.data.id).then(res => {
        if (res.result) {
          this.form = res.data;
        } else {
          this.$Message.error(res.msg);
        }
      });
    }
  }
};
</script>
<style>
.fs-foldermng-base .footer {
  text-align: center;
  margin-top: 12px;
}
.fs-foldermng-base .btn-box {
  display: inline-block;
  overflow: hidden;
}
.fs-foldermng-base .btn-box .btn-box-item {
  margin-left: 8px;
  display: inline-block;
}
.fs-foldermng-base .btn-box .btn-box-item:first-child {
  margin-left: 0px;
}
</style>
