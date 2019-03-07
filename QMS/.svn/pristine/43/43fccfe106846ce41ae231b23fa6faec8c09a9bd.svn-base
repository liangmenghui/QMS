<template>
  <div>
    <Row>
      <i-col span=24>
        <div v-if="mutliAgent == true">
          <div style="padding: 0 0 24px;line-height:32px;font-size:12px;">
            <div style="display:inline-block;">
              <p style="width:100px;display:inline-block;text-align:right;margin-right:20px;">客户:</p>
              <Select v-model="form.customer" style="width:200px">
                <Option v-for="item in customers" :value="item.customer" :key="item.customer">{{item.desc}}</Option>
              </Select>
            </div>
            <div style="display:inline-block;">
              <p style="width:100px;display:inline-block;text-align:right;margin-right:20px;">货代:</p>
              <i-select v-model="form.agent" style="width:200px">
                <Option v-for="item in agents" :value="item.agent" :key="item.agent">{{item.desc}}</option>
              </i-select>
            </div>
          </div>
        </div>
        <div v-if="mutliAgent == false">
          <div style="padding:0 0 24px;line-height:32px;font-size:12px;">
            <div style="display:inline-block;">
              <p style="width:100px;display:inline-block;text-align:right;margin-right:20px;">客户:</p>
              <p style="width:200px;display:inline;">{{form.customer}}</p>
            </div>
            <div style="display:inline-block;">
              <p style="width:100px;display:inline-block;text-align:right;margin-right:20px;">货代:</p>
              <p style="width:200px;display:inline;">{{form.agent}}</p>
            </div>
          </div>
        </div>
      </i-col>
    </Row>
    <Row>
      <i-col span="24">
        <component ref="content" @on-commit="handleCommit" :cabinets="cabinets" :customer="form.customer" :agent="form.agent" :contentData="content" :is="currentContextView"></component>
      </i-col>
    </Row>
    <Row>
      <i-col span="24">
        <div style="height:86px;padding-top:24px;background-color:#f1f2f3;">
          <Button type="success" @click="handleSubmit" size="large" style="float:right;margin-right:60px;" :loading="loading">保存</Button>
        </div>
      </i-col>
    </Row>
  </div>
</template>
<script>
import { contextComponents } from "@/view/logistics/booking/plugin";

export default {
  props: {
    cabinets: {
      type: [Object, Array],
      required: true
    }
  }, //props
  data() {
    return {
      currentContextView: "",
      mutliAgent: false,
      initializeState: false,
      validateResult: true, //校验结果
      loading: false,
      agents: [{ agent: "", desc: "请选择货代" }],
      form: {
        customer: "",
        agent: ""
      },
      content: "" //页面数据
    };
  }, //data
  components: {
    ...contextComponents
  },
  methods: {
    handleSubmit: function() {
      let getDataFunc = this.$refs.content.getData;
      if (!getDataFunc) {
        this.$Message.error("插件错误,未实现 getData");
        return;
      }

      getDataFunc();
    },
    handleCommit(formData) {
      if (!JSON.stringify(formData)) return;

      let t = this.decoding(formData);
      this.api.lmp.booking
        .saveContent({
          customer: this.form.customer,
          agent: this.form.agent,
          cabinets: this.cabinets,
          formData: JSON.stringify(t)
        })
        .then(res => {
          if (res.result) {
            this.$Message.success("保存成功");
            this.modalHidden();
          }
        });
    },
    initCustomer: function() {
      this.api.lmp.booking.getCustomerByPlan(this.cabinets).then(res => {
        if (!res.result) {
          return;
        }

        let list = [{ customer: "", desc: "请选择客户" }];
        this.form.customer = "";

        res.data.forEach(element => {
          list.push({
            customer: element.bsCode,
            desc: element.bsName
          });
        });

        this.customers = list;
        if (this.customers.length > 1) {
          this.form.customer = this.customers[1].customer;
        }
        //初始化货代
        this.initAgent();
      });
    }, //getCustomer
    initAgent: function() {
      this.api.lmp.booking.getAgentByPlan(this.cabinets).then(res => {
        if (!res.result) {
          return;
        }

        if (res.data.length == 0) {
          this.$Message.error("找不到对应的货代信息。");
          this.modalHidden();
          return;
        }

        let list = [{ agent: "", name, url: "", desc: "请选择货代" }];
        this.form.agent = "";

        res.data.forEach(element => {
          list.push({
            agent: element.bsProvider,
            desc: element.bsProvider
          });
        });

        this.agents = list;
        if (this.agents.length == 2) {
          this.form.agent = this.agents[1].agent;
          this.mutliAgent = false;
        } else if (this.agents.length > 2) {
          this.mutliAgent = true;
        }
        //初始化完成显示页面
        this.initializeState = true;
      });
    }, //getAgent
    encoding(src) {
      if (this.isArray(src) == true) {
        let o = {};
        let oArray = [];
        let bArray = false;
        src.forEach(item => {
          if (this.isArray(item)) {
            o.push(this.encoding(item));
          } else if (this.isObject(item)) {
            if (
              Object.keys(item).length == 2 &&
              Object.keys(item).indexOf("key") >= 0 &&
              Object.keys(item).indexOf("value") >= 0
            ) {
              o[item["key"]] = item["value"];
            } else {
              let t = {};
              Object.keys(item).forEach(item_key => {
                if (this.isArray(item[item_key])) {
                  let tVal = this.encoding(item[item_key]);
                  t[item_key] = tVal;
                } else {
                  t[item_key] = item[item_key];
                }
              });
              oArray.push(t);
              bArray = true;
            }
          }
        });
        if (bArray == true && Object.keys(o).length > 0) {
          oArray.push(o);
        }
        return bArray ? oArray : o;
      }
    },
    decoding(src) {
      let o = {
        content: [],
        summary: [],
        detail: []
      };
      if (src.content) {
        Object.keys(src.content).forEach(item => {
          o.content.push({ key: item, value: src.content[item] });
        });
      }
      if (src.summary) {
        Object.keys(src.summary).forEach(item => {
          o.summary.push({ key: item, value: src.summary[item] });
        });
      }
      if (src.detail) {
        src.detail.forEach(item => {
          let to = {
            summary: [],
            detail: []
          };
          if (item.summary) {
            Object.keys(item.summary).forEach(sitem => {
              to.summary.push({
                key: sitem,
                value: item.summary[sitem]
              });
            });
          }
          if (item.detail) {
            item.detail.forEach(dItem => {
              let ddo = [];
              Object.key(dItem).forEach(ddItem => {
                ddo.push({ key: this.ddItem, value: dItem[this.ddItem] });
              });
              to.summary.push(ddo);
            });
          }
          o.detail.push(to);
        });
      }
      return o;
    },
    isArray: function(o) {
      return Object.prototype.toString.call(o) === "[object Array]";
    },
    isObject: function(o) {
      return Object.prototype.toString.call(o) === "[object Object]";
    },
    loadPageView: function() {
      this.api.lmp.booking
        .getContextByPlan({
          customer: this.form.customer,
          agent: this.form.agent
        })
        .then(res => {
          if (res.result) {
            let component = res.data;
            if (
              component === undefined ||
              component.length == 0 ||
              component === null
            ) {
              this.$Message.error("获取页面错误。");
              return;
            }
            let contentPage = res.data;
            this.content = {};
            this.currentContextView = contentPage;
            this.api.lmp.booking
              .getContextData({
                customer: this.form.customer,
                agent: this.form.agent,
                cabinets: this.cabinets
              })
              .then(res => {
                if (res.result) {
                  let retData = JSON.parse(JSON.stringify(res.data));
                  let d = {};
                  d.content = this.encoding(retData.content);
                  d.summary = this.encoding(retData.summary);
                  d.detail = this.encoding(retData.detail);
                  this.content = d;
                }
              });
          }
        });
    }, //loadPageView
    init: function() {
      this.initializeState = false;
      //获取对应的货代 以及显示的数据 每个初始化项完成之后，都需要对初始化状态做更改.
      this.initCustomer();
    }, //modalDisplay
    modalHidden: function() {
      this.$emit("close");
    },
    handleValidate: function(result, message) {
      if (typeof result == "boolean") {
        if (result === false) {
          if (message != undefined && message != null)
            this.$Message.error(message);
          this.validateResult = result;
        }
      }
      //非boolean类型 不处理
    } //handleValidate
  },
  watch: {
    initializeState() {
      if (this.initializeState == true) {
        //当所有初始化成功之后才算初始化完成
        this.$emit("initializeClosing");
      }
    },
    pageView() {
      if (!this.form.customer || !this.form.agent) {
        return;
      }
      this.loadPageView();
    },
    customer() {
      const { customer } = this;
      if (customer.length > 0) {
        this.initAgent();
      }
    }
  }, //watch
  computed: {
    pageView() {
      return { agent: this.form.agent };
    }
  } //computed
};
</script>
