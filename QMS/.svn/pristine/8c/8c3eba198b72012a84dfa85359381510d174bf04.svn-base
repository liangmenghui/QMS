<template>
  <div class="ivu-template">
    <Form :label-width="120">
      <Row>
        <i-col span="6">
          <FormItem label="客户">
            <i-select v-model="selectedCustomer" style="width:200px">
              <Option v-for="item in customers" :value="item.bsCustomer" :key="item.bsCustomer">{{item.bsCustomerDesc}}</option>
            </i-select>
          </FormItem>
        </i-col>
        <i-col span="6">
          <FormItem label="货代">
            <i-select v-model="selectedAgent" style="width:200px">
              <Option v-for="item in agents" :value="item.agent" :key="item.agent">{{item.desc}}</option>
            </i-select>
          </FormItem>
        </i-col>
      </Row>
      <Row>
        <i-col span="16">
          <FormItem label="Booking模板">
            <i-input disabled v-model="templatePath"></i-input>
          </FormItem>
        </i-col>
        <i-col span="8" class="btngroup">
          <div class="btn">
            <Upload action="/admin/lmp/booking/template/upload" :show-upload-list="false" :before-upload="handleUpload" :on-format-error="handleFormatError" :data="{customer:selectedCustomer,agent:selectedAgent}" :on-success="handleUploadSuccess" :on-error="handleUploadError" :accept="acceptype" :format="filetype">
              <i-button type="primary" :loading="this.uploadState" icon="ios-cloud-upload-outline">{{this.uploadState?"上传中":"上传模板"}}</i-button>
            </Upload>
          </div>
          <div class="btn">
            <Button type="primary" @click="handleLoadTemplate" icon="ios-loop-strong">加载模板</Button>
          </div>
        </i-col>
      </Row>
      <Row>
        <i-col>
          <form-item label="模板详细信息">
            <component ref="template" :formData="templateItems" :customer="selectedCustomer" :agent="selectedAgent" @on-commit="onCommit" :is="currentTemplateView"></component>
          </form-item>
        </i-col>
      </Row>
    </Form>
    <Row type="flex" justify="end" class="bottom">
      <i-col span="16" class="submitbtn">
        <Button type="success" @click="handleSave" size="large">保 存</Button>
        <Button type="info" @click="handleRest" size="large">清 除</Button>
      </i-col>
    </Row>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { templateComponents } from "@/view/logistics/booking/plugin";

export default {
  data() {
    return {
      customers: [{ bsCustomer: "", bsCustomerDesc: "请选择客户" }],
      agents: [{ agent: "", name, url: "", desc: "请选择货代" }],
      uploadState: false,
      file: null,
      templatePath: "",
      currentTemplateView: "",
      templateItems: [],
      selectedAgent: "",
      selectedCustomer: "",
      filetype: ["xls", "xlsx"]
    };
  },
  mounted() {
    this.handleLoadCustomer();
  },
  components: {
    ...templateComponents
  },
  methods: {
    save(params) {
      let response = [];
      const { selectedCustomer, selectedAgent } = this;
      Object.keys(params).forEach(item => {
        response.push({
          key: item,
          value: params[item]
        });
      });
      response.push({ key: "customer", value: selectedCustomer });
      response.push({ key: "agent", value: selectedAgent });
      //  var a = JSON.stringify(response);
      this.api.lmp.booking.saveList(response).then(res => {
        if (res.result) {
          this.$Message.success("保存成功");
        }
      });
    },
    handleUpload: function(file) {
      const { selectedCustomer, selectedAgent, uploadState } = this;
      if (uploadState) {
        this.$message.warning("正在处理上传文件，请稍后再试...");
        return false;
      }
      this.file = null;
      this.templatePath = "";

      if (!selectedCustomer || !selectedAgent) {
        this.$Message.error("请先选择客户和货代。");
        return false;
      }
      this.file = file;
      this.templatePath = file.name;
      this.uploadState = true;
      return true;
    }, //handleUpload
    handleLoadCustomer: function() {
      this.customers = [{ bsCustomer: "", bsCustomerDesc: "请选择客户" }];
      this.api.lmp.booking.customers().then(res => {
        if (res.result) {
          let list = [{ bsCustomer: "", bsCustomerDesc: "请选择客户" }];
          res.data.forEach(element => {
            list.push({
              bsCustomer: element.bsCode,
              bsCustomerDesc: element.bsName
            });
          });
          this.customers = list;
        }
      });
    },
    handleLoadTemplate: function() {
      const { selectedCustomer, selectedAgent, uploadState } = this;
      if (!selectedCustomer || !selectedAgent) {
        this.$Message.error("请先选择客户和货代。");
        return false;
      }

      this.api.lmp.booking
        .getTemplate({ customer: selectedCustomer, agent: selectedAgent })
        .then(res => {
          if (res.result) {
            let formData = {};
            let json = res.data;
            json.forEach(item => {
              formData[item.bsName] = item.bsValue;
            });
            this.templateItems = formData;
            this.templatePath = formData["template"];
          }
        });
    }, //handLoadTemplate
    handleDownload: function() {
      const { selectedCustomer, selectedAgent, uploadState } = this;
      if (!selectedCustomer || !selectedAgent) {
        this.$Message.error("请先选择客户和货代。");
        return false;
      }
      this.api.lmp.booking.download({
        customer: selectedCustomer,
        agent: selectedAgent
      });
    },
    handleUploadSuccess: function(response, file) {
      if (response.result == true) {
        let formData = {};
        if (response.data) {
          response.data.forEach(item => {
            formData[item.bsName] = item.bsValue;
          });
        }
        this.$Message.info("文件" + file.name + "上传成功.");
      } else {
        this.$message.error(response.msg);
      }
      this.uploadState = false;
    }, //handleUploadSuccess
    handleUploadError: function(response, file) {
      this.$message.error("文件" + file.name + "上传失败。");
      this.uploadState = false;
    }, //handleUploadError
    handleFormatError: function(file) {
      this.$Message.error("模板格式错误,请上传正确的Booking文档模板。");
    }, //handleFormatError
    handleSave() {
      if (
        !this.selectedAgent ||
        !this.selectedCustomer ||
        !this.currentTemplateView
      ) {
        this.$Message.error("请选择模板");
        return;
      }
      let getData = this.$refs.template.getData;
      if (getData) {
        getData();
      }
    },
    handleRest() {
      this.selectedAgent = "";
      this.selectedCustomer = "";
      this.currentTemplateView = "";
      this.templatePath = "";
    },
    onCommit(formData) {
      this.save(formData);
    }
  }, //methods
  computed: {
    acceptype() {
      let tmp = this.filetype.reduce((x1, x2) => {
        return (x1 += ",." + x2);
      });
      return "." + tmp;
    }
  },
  watch: {
    selectedCustomer: function() {
      this.agents = [{ agent: "", name: "", desc: "请选择货代" }];
      const { selectedCustomer } = this;
      this.selectedAgent = "";
      this.currentTemplateView = "";
      if (!selectedCustomer) return;

      this.api.lmp.booking.agents({ customer: selectedCustomer }).then(res => {
        if (res.result) {
          let list = [{ agent: "", name: "", desc: "请选择货代" }];
          res.data.forEach(element => {
            list.push({
              agent: element.bsAgent,
              desc: element.bsAgent,
              name: element.bsTemplateComponent
            });
          });
          this.agents = list;
        }
      });
    },
    selectedAgent: function() {
      const { selectedCustomer, selectedAgent, agents } = this;

      if (!selectedAgent) {
        this.currentTemplateView = "";
        return;
      }

      this.currentTemplateView = agents.filter(
        item => item.agent == selectedAgent
      )[0].name;
    }
  } //watch
};
</script>
<style>
.ivu-template {
  margin-bottom: 80px;
}
.ivu-template .btngroup .btn {
  margin-left: 12px;
  float: left;
  flex: 0 0 auto;
}
.ivu-template .bottom {
  background-color: #f8f8f9;
  padding: 24px;
  position: fixed;
  bottom: 0;
  box-sizing: border-box;
  width: 100%;
}
.ivu-template .bottom .submitbtn .ivu-btn {
  width: 120px;
  margin-left: 24px;
}
</style>
