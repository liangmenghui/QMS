webpackJsonp([89],{1208:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s(1926),n=s.n(a);for(var i in a)"default"!==i&&function(t){s.d(e,t,function(){return a[t]})}(i);var o=s(2114),l=s.n(o),r=s(1),u=r(n.a,l.a,!1,null,null,null);u.options.__file="src/view/logistics/booking/template.vue",e.default=u.exports},1926:function(t,e,s){"use strict";function a(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var n=s(44),i=a(n),o=s(72),l=a(o),r=(s(98),s(505));e.default={data:function(){return{customers:[{bsCustomer:"",bsCustomerDesc:"请选择客户"}],agents:[{agent:"",name:name,url:"",desc:"请选择货代"}],uploadState:!1,file:null,templatePath:"",currentTemplateView:"",templateItems:[],selectedAgent:"",selectedCustomer:""}},mounted:function(){this.handleLoadCustomer()},components:(0,l.default)({},r.templateComponents),methods:{save:function(t){var e=this,s=[],a=this.selectedCustomer,n=this.selectedAgent;(0,i.default)(t).forEach(function(e){s.push({key:e,value:t[e]})}),s.push({key:"customer",value:a}),s.push({key:"agent",value:n}),this.api.lmp.booking.saveList(s).then(function(t){t.result&&e.$Message.success("保存成功")})},handleUpload:function(t){var e=this.selectedCustomer,s=this.selectedAgent;return this.uploadState?(this.$message.warning("正在处理上传文件，请稍后再试..."),!1):(this.file=null,this.templatePath="",e&&s?(this.file=t,this.templatePath=t.name,this.uploadState=!0,!0):(this.$Message.error("请先选择客户和货代。"),!1))},handleLoadCustomer:function(){var t=this;this.customers=[{bsCustomer:"",bsCustomerDesc:"请选择客户"}],this.api.lmp.booking.customers().then(function(e){if(e.result){var s=[{bsCustomer:"",bsCustomerDesc:"请选择客户"}];e.data.forEach(function(t){s.push({bsCustomer:t.bsCode,bsCustomerDesc:t.bsName})}),t.customers=s}})},handleLoadTemplate:function(){var t=this,e=this.selectedCustomer,s=this.selectedAgent;this.uploadState;if(!e||!s)return this.$Message.error("请先选择客户和货代。"),!1;this.api.lmp.booking.getTemplate({customer:e,agent:s}).then(function(e){if(e.result){var s={};e.data.forEach(function(t){s[t.bsName]=t.bsValue}),t.templateItems=s,t.templatePath=s.template}})},handleDownload:function(){var t=this.selectedCustomer,e=this.selectedAgent;this.uploadState;if(!t||!e)return this.$Message.error("请先选择客户和货代。"),!1;this.api.lmp.booking.download({customer:t,agent:e})},handleUploadSuccess:function(t,e){if(1==t.result){var s={};t.data&&t.data.forEach(function(t){s[t.bsName]=t.bsValue}),this.$Message.info("文件"+e.name+"上传成功.")}else this.$message.error(t.msg);this.uploadState=!1},handleUploadError:function(t,e){this.$message.error("文件"+e.name+"上传失败。"),this.uploadState=!1},handleFormatError:function(t){this.$Message.error("模板格式错误,请上传正确的Booking文档模板。")},handleSave:function(){var t=this.$refs.template.getData;t&&t()},onCommit:function(t){this.save(t)}},watch:{selectedCustomer:function(){var t=this;this.agents=[{agent:"",name:"",desc:"请选择货代"}];var e=this.selectedCustomer;this.api.lmp.booking.agents({customer:e}).then(function(e){if(e.result){var s=[{agent:"",name:"",desc:"请选择货代"}];e.data.forEach(function(t){s.push({agent:t.bsAgent,desc:t.bsAgent,name:t.bsTemplateComponent})}),t.agents=s}})},selectedAgent:function(){var t=(this.selectedCustomer,this.selectedAgent),e=this.agents;if(!t)return void(this.currentTemplateView="");this.currentTemplateView=e.filter(function(e){return e.agent==t})[0].name}}}},2114:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("Row",[s("i-col",{attrs:{span:"24"}},[s("Form",{staticStyle:{"min-height":"720px"},attrs:{"label-width":120}},[s("div",{staticStyle:{padding:"0 0 24px","line-height":"32px","font-size":"12px"}},[s("div",{staticStyle:{display:"inline-block"}},[s("p",{staticStyle:{width:"100px",display:"inline-block","text-align":"right","margin-right":"20px"}},[t._v("客户")]),t._v(" "),s("i-select",{staticStyle:{width:"200px"},model:{value:t.selectedCustomer,callback:function(e){t.selectedCustomer=e},expression:"selectedCustomer"}},t._l(t.customers,function(e){return s("Option",{key:e.bsCustomer,attrs:{value:e.bsCustomer}},[t._v(t._s(e.bsCustomerDesc))])}))],1),t._v(" "),s("div",{staticStyle:{display:"inline-block"}},[s("p",{staticStyle:{width:"100px",display:"inline-block","text-align":"right","margin-right":"20px"}},[t._v("货代")]),t._v(" "),s("i-select",{staticStyle:{width:"200px"},model:{value:t.selectedAgent,callback:function(e){t.selectedAgent=e},expression:"selectedAgent"}},t._l(t.agents,function(e){return s("Option",{key:e.agent,attrs:{value:e.agent}},[t._v(t._s(e.desc))])}))],1),t._v(" "),s("div",{staticStyle:{float:"right"}},[s("Button",{staticStyle:{float:"right","margin-right":"20px"},attrs:{type:"primary",icon:"ios-loop-strong"},on:{click:t.handleLoadTemplate}},[t._v("加载模板")])],1)]),t._v(" "),s("form-item",{attrs:{label:"Booking模板"}},[s("div",{staticStyle:{"margin-right":"140px"}},[s("i-input",{attrs:{disabled:""},model:{value:t.templatePath,callback:function(e){t.templatePath=e},expression:"templatePath"}})],1),t._v(" "),s("Upload",{staticStyle:{float:"right","margin-right":"20px","margin-top":"-34px"},attrs:{action:"/admin/lmp/booking/template/upload","show-upload-list":!1,"before-upload":t.handleUpload,"on-format-error":t.handleFormatError,data:{customer:t.selectedCustomer,agent:t.selectedAgent},"on-success":t.handleUploadSuccess,"on-error":t.handleUploadError,format:["xls","xlsx"]}},[s("i-button",{attrs:{type:"primary",loading:this.uploadState,icon:"ios-cloud-upload-outline"}},[t._v(t._s(this.uploadState?"上传中":"上传模板"))])],1)],1),t._v(" "),s("form-item",{attrs:{label:"模板详细信息"}},[s(t.currentTemplateView,{ref:"template",tag:"component",attrs:{formData:t.templateItems,customer:t.selectedCustomer,agent:t.selectedAgent},on:{"on-commit":t.onCommit}})],1)],1)],1)],1),t._v(" "),s("Row",[s("i-col",{attrs:{span:"24"}},[s("div",{staticStyle:{height:"86px","padding-top":"24px","background-color":"#f1f2f3"}},[s("Button",{staticStyle:{float:"right","margin-right":"60px"},attrs:{type:"success",size:"large"},on:{click:t.handleSave}},[t._v("保存")])],1)])],1)],1)},n=[];a._withStripped=!0;var i={render:a,staticRenderFns:n};e.default=i}});