<template>
    <Modal v-model="visible" title="绑定插件" :loading="loading" @on-ok="ok" @on-visible-change="display" width=800>
        <p slot="header" style="color:rgb(99, 168, 168);text-align:left; margin-left:20px;">
            <Icon type="information-circled"></Icon>
            <span>绑定插件</span>
        </p>
        <div style="border:1px">
            <Row>
                <i-col>
                    <Form :model="form" ref="form" :label-width="100" style="padding:24px 6px 0px 6px;" :rules="rules">
                        <Row>
                            <i-col span="12">
                                <FormItem label="客户编码" prop='bsCustomer'>
                                    <un-customer v-model="form.bsCustomer" :text.sync="form.bsCustomerDesc"></un-customer>
                                </FormItem>
                            </i-col>
                            <i-col span="12">
                                <FormItem label="货代编码" prop='bsAgent'>
                                    <un-agent v-model="form.bsAgent" :text.sync="form.bsAgentDesc"></un-agent>
                                </FormItem>
                            </i-col>
                        </Row>
                        <Row>
                            <i-col span="16">
                                <FormItem label="插件名称" prop='bsConfigurationName'>
                                    <un-content v-model="form.bsConfigurationName" @on-select="handleSelectContent"></un-content>
                                </FormItem>
                            </i-col>
                        </Row>
                        <div style="background-color:#f1f2f3; margin-top:24px;text-align:left;padding:4px;">
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
                                        <Input readonly="readonly" v-model='form.bsTemplateComponentUrl' type="textarea" :autosize="{minRows: 2,maxRows: 5}" disabled/>
                                    </FormItem>
                                </i-col>
                            </Row>
                        </div>
                        <div style="background-color:#f1f2f3; margin-top:24px;text-align:left;padding:4px;">
                            <div style="margin:6px 10px; font-weight:600; font-size:14px;margin-left:12px;">内容页面</div>
                            <Row>
                                <i-col span="12">
                                    <FormItem label="组件" prop='bsContentComponent'>
                                        <Input v-model='form.bsContentComponent' />
                                    </FormItem>
                                </i-col>
                                <i-col span="12">
                                    <FormItem label="解析组件" prop='bsContentParseComponent'>
                                        <Input v-model='form.bsContentParseComponent' />
                                    </FormItem>
                                </i-col>
                            </Row>
                            <Row>
                                <i-col span="24">
                                    <FormItem label="">
                                        <Input readonly="readonly" type="textarea" :autosize="{minRows: 2,maxRows: 5}" disabled/>
                                    </FormItem>
                                </i-col>
                            </Row>
                        </div>
                    </Form>
                </i-col>
            </Row>
        </div>
    </Modal>
</template>
<script>
import unContent from "../components/unPluginContent";

export default {
  props: {
    value: {
      type: Boolean,
      required: true
    }
  },
  components: {
    unContent
  },
  data() {
    return {
      visible: false,
      loading: true,
      form: {
        bsAgent: "",
        bsAgentDesc: "",
        bsCustomer: "",
        bsCustomerDesc: "",
        bsConfigurationName: "",
        bsTemplateComponentUrl: "",
        bsTemplateComponent: "",
        bsTemplateParseComponent: "",
        bsContentComponent: "",
        bsContentParseComponent: ""
      },
      rules: {
        bsCustomer: [
          { required: true, message: "请选择客户编码", trigger: "blur" }
        ],
        bsAgent: [
          { required: true, message: "请选择货代编码", trigger: "blur" }
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
    handleSelectContent(content) {
      this.form.bsTemplateComponent = content.bsTemplateComponent;
      this.form.bsTemplateParseComponent = content.bsTemplateParseComponent;
      this.form.bsTemplateComponentUrl = content.bsTemplateComponentUrl;
      this.form.bsContentComponent = content.bsContentComponent;
      this.form.bsContentParseComponent = content.bsContentParseComponent;
      this.form.bsConfigurationName = content.bsConfigurationName;
    },
    ok() {
      this.$refs.form.validate(valid => {
        this.changeLoading();
        if (valid) {
          this.api.lmp.bookingconfiguration
            .insertBookingconfiguration(this.form)
            .then(res => {
              if (res.result) {
                this.visible = false;
                this.$emit("on-close");
                this.$Message.success("添加成功！");
              } else {
                this.$Message.error("添加失败！");
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
