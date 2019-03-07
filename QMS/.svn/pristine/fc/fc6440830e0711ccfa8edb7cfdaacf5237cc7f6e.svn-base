<template>
    <Modal v-model="visible" title="添加插件" :loading="loading" @on-ok="ok" @on-visible-change="display" width=800>
        <p slot="header" style="color:rgb(99, 168, 168);text-align:left; margin-left:20px;">
            <Icon type="information-circled"></Icon>
            <span>添加插件</span>
        </p>
        <Form :model="form" ref="form" :label-width="100" style="padding:24px 6px 0px 6px;" :rules="rules">
            <Row>
                <i-col span="12">
                    <FormItem label="插件名称" prop="bsConfigurationName">
                        <Input v-model="form.bsConfigurationName" />
                    </FormItem>
                </i-col>
            </Row>
            <Row>
                <i-col span="24" style="background-color:#f1f2f3; margin-top:24px;text-align:left;padding:4px;">
                    <div style="margin:6px 10px; font-weight:600; font-size:14px;margin-left:12px;">模板页面</div>
                    <Row>
                        <i-col span="12">
                            <FormItem label="组件" prop='bsTemplateComponent'>
                                <Input v-model='form.bsTemplateComponent' />
                            </FormItem>
                        </i-col>
                        <i-col span="12">
                            <FormItem label="解析组件" prop='bsTemplateParseComponent'>
                                <Input v-model='form.bsTemplateParseComponent' />
                            </FormItem>
                        </i-col>
                    </Row>
                    <Row>
                        <i-col span="24">
                            <FormItem label="页面URL" prop='bsTemplateComponentUrl'>
                                <Input v-model='form.bsTemplateComponentUrl' type="textarea" :autosize="{minRows: 2,maxRows: 5}" disabled />
                            </FormItem>
                        </i-col>
                    </Row>
                </i-col>
            </Row>
            <Row>
                <i-col span="24" style="background-color:#f1f2f3; margin-top:24px;text-align:left;padding:4px;">
                    <div style="margin:6px 10px; font-weight:600; font-size:14px;margin-left:12px;">内容页面</div>
                    <Row>
                        <i-col span="12">
                            <FormItem label="组件" prop='bsContentComponent'>
                                <Input v-model='form.bsContentComponent' />
                            </FormItem>
                        </i-col>
                        <i-col span="12">
                            <FormItem label="解析组件" prop='bsContentParseComponent'>
                                <Input v-model="form.bsContentParseComponent" />
                            </FormItem>
                        </i-col>
                    </Row>
                    <Row>
                        <i-col span="24">
                            <FormItem label="">
                                <Input v-model="form.bsContentComponentUrl" type="textarea" :autosize="{minRows: 2,maxRows: 5}" disabled />
                            </FormItem>
                        </i-col>
                    </Row>
                </i-col>
            </Row>
        </Form>
    </Modal>
</template>

<script>
export default {
  props: {
    value: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      form: {},
      visible: false,
      loading: true,
      form: {
        bsConfigurationName: "",
        bsTemplateComponent: "",
        bsTemplateParseComponent: "",
        bsTemplateComponentUrl: "",
        bsContentComponent: "",
        bsContentParseComponent: "",
        bsContentComponentUrl: ""
      },
      rules: {
        bsConfigurationName: [
          { required: true, message: "请输入插件名称", trigger: "blur" }
        ],
        bsTemplateComponent: [
          {
            required: true,
            message: "请输入插件 template 组件名称",
            trigger: "blur"
          }
        ],
        bsTemplateParseComponent: [
          {
            required: true,
            message: "请输入插件 template parse 组件名称",
            trigger: "blur"
          }
        ],
        bsContentComponent: [
          {
            required: true,
            message: "请输入插件 content 组件名称",
            trigger: "blur"
          }
        ],
        bsContentParseComponent: [
          {
            required: true,
            message: "请输入插件 content parse 组件名称",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    changeLoading() {
      this.loading = false;
      this.$nextTick(() => {
        this.loading = true;
      });
    },
    ok() {
      this.$refs.form.validate(valid => {
        this.changeLoading();
        if (valid) {
          this.api.lmp.bookingconfiguration
            .addContent(this.form)
            .then(response => {
              if (response.result) {
                this.visible = false;
                this.$Message.success("添加成功");
              } else {
                this.$Message.error(response.msg);
              }
            });
        }
      });
    },
    display(val) {
      this.$emit("input", val);
    }
  },
  watch: {
    value(val) {
      this.visible = val;
      //初始化
      if (val == true) {
        Object.keys(this.form).forEach(item => {
          this.form[item] = "";
        });
      }
    }
  }
};
</script>
