webpackJsonp([36],{1037:function(t,e,s){"use strict";function l(t){n||s(1791)}Object.defineProperty(e,"__esModule",{value:!0});var i=s(1603),a=s.n(i);for(var o in i)"default"!==o&&function(t){s.d(e,t,function(){return i[t]})}(o);var r=s(1793),p=s.n(r),n=!1,c=s(1),u=l,d=c(a.a,p.a,!1,u,null,null);d.options.__file="src\\view\\supplier\\InputInformation.vue",e.default=d.exports},1603:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});s(120);e.default={data:function(){return{thesupplier:{},supplierrisk:{},options1:[{value:1,label:"大于24个月无交货延迟"},{value:2,label:"大于12个月无交货延迟"},{value:3,label:"大于2个月无交货延迟"},{value:4,label:"3个月内有1次以上交货延迟"},{value:5,label:"3个月内有2次以上交货延迟"}],options2:[{value:1,label:"大于120天"},{value:2,label:"小于120天"},{value:3,label:"小于90天"},{value:4,label:"小于30天"},{value:5,label:"小于15天,或现金"}],checkList:[]}},created:function(){this.thesupplier=this.$store.getters.getSupplierData,this.getData()},methods:{getData:function(){var t=this,e=this.thesupplier.id;this.api.supplierrisk.getlist({bsSuppId:e}).then(function(e){t.supplierrisk=e.data.rows[0]})},reloadData:function(){this.getData()},edit:function(){var t=this,e=this.$Util.formattedParams(this.supplierrisk);this.api.supplierrisk.edit(e).then(function(e){t.$Message.info("修改完成")})}}}},1791:function(t,e,s){var l=s(1792);"string"==typeof l&&(l=[[t.i,l,""]]),l.locals&&(t.exports=l.locals);s(28)("23f29b0e",l,!1,{})},1792:function(t,e,s){e=t.exports=s(27)(!1),e.push([t.i,"\n.sup-inp .body{\n\theight: 50px;margin-top: 10px;margin-bottom: 10px;\n}\n.sup-inp .body-text{\n\tfloat: left;width: 150px;text-align:right;padding-top: 10px;\n}\n.sup-inp .body-model{\n\tfloat: left;\n}\n.sup-inp #bottom{\n\theight:150px;margin-top: 20px;margin-bottom: 20px;\n}\n",""])},1793:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var l=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"sup-inp"},[s("Row",{attrs:{gutter:10}},[s("Col",{style:{marginBottom:"20px"},attrs:{md:24,lg:12}},[s("Card",[s("p",{attrs:{slot:"title"},slot:"title"},[s("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"edit"}}),t._v("\n                     PPM\n                ")],1),t._v(" "),s("div",{staticClass:"body"},[s("div",{staticClass:"body-text"},[t._v("PPM：")]),t._v(" "),s("div",{staticClass:"body-model"},[s("el-input",{staticStyle:{width:"300px"},model:{value:t.supplierrisk.bsPpmValue,callback:function(e){t.$set(t.supplierrisk,"bsPpmValue",e)},expression:"supplierrisk.bsPpmValue"}})],1)])])],1),t._v(" "),s("Col",{style:{marginBottom:"20px"},attrs:{md:24,lg:12}},[s("Card",[s("p",{attrs:{slot:"title"},slot:"title"},[s("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"edit"}}),t._v("\n                     关键工序SPC制程能力监控\n                ")],1),t._v(" "),s("div",{staticClass:"body"},[s("div",{staticClass:"body-text"},[t._v("CPK：")]),t._v(" "),s("div",{staticClass:"body-model"},[s("el-input",{staticStyle:{width:"300px"},model:{value:t.supplierrisk.bsCpkValue,callback:function(e){t.$set(t.supplierrisk,"bsCpkValue",e)},expression:"supplierrisk.bsCpkValue"}})],1)])])],1)],1),t._v(" "),s("Row",{attrs:{gutter:10}},[s("Col",{style:{marginBottom:"20px"},attrs:{md:24,lg:12}},[s("Card",[s("p",{attrs:{slot:"title"},slot:"title"},[s("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"edit"}}),t._v("\n                     EHS不符合性\n                ")],1),t._v(" "),s("div",{staticClass:"body"},[s("div",{staticClass:"body-text"},[t._v("EHS不符合项目数：")]),t._v(" "),s("div",{staticClass:"body-model"},[s("el-input",{staticStyle:{width:"300px"},model:{value:t.supplierrisk.bsEhsValue,callback:function(e){t.$set(t.supplierrisk,"bsEhsValue",e)},expression:"supplierrisk.bsEhsValue"}})],1)])])],1),t._v(" "),s("Col",{style:{marginBottom:"20px"},attrs:{md:24,lg:12}},[s("Card",[s("p",{attrs:{slot:"title"},slot:"title"},[s("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"edit"}}),t._v("\n                     交货达成情况\n                ")],1),t._v(" "),s("div",{staticClass:"body",staticStyle:{"padding-left":"150px"}},[s("el-select",{staticStyle:{width:"300px"},model:{value:t.supplierrisk.bsDeliveryType,callback:function(e){t.$set(t.supplierrisk,"bsDeliveryType",e)},expression:"supplierrisk.bsDeliveryType"}},t._l(t.options1,function(t){return s("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})}))],1)])],1)],1),t._v(" "),s("Row",{attrs:{gutter:10}},[s("Col",{style:{marginBottom:"20px"},attrs:{md:24,lg:12}},[s("Card",{style:{marginBottom:"20px"}},[s("p",{attrs:{slot:"title"},slot:"title"},[s("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"edit"}}),t._v("\n                     验货情况\n                ")],1),t._v(" "),s("div",{staticClass:"body"},[s("div",{staticClass:"body-text"},[t._v("验货不合格次数：")]),t._v(" "),s("div",{staticClass:"body-model"},[s("el-input",{staticStyle:{width:"300px"},model:{value:t.supplierrisk.bsInspectValue,callback:function(e){t.$set(t.supplierrisk,"bsInspectValue",e)},expression:"supplierrisk.bsInspectValue"}})],1)])]),t._v(" "),s("Card",[s("p",{attrs:{slot:"title"},slot:"title"},[s("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"edit"}}),t._v("\n                     付款期\n                ")],1),t._v(" "),s("div",{staticClass:"body",staticStyle:{"padding-left":"150px"}},[s("el-select",{staticStyle:{width:"300px"},model:{value:t.supplierrisk.bsPaymentType,callback:function(e){t.$set(t.supplierrisk,"bsPaymentType",e)},expression:"supplierrisk.bsPaymentType"}},t._l(t.options2,function(t){return s("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})}))],1)])],1),t._v(" "),s("Col",{style:{marginBottom:"20px"},attrs:{md:24,lg:12}},[s("Card",[s("p",{attrs:{slot:"title"},slot:"title"},[s("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"edit"}}),t._v("\n                     商务总评\n                ")],1),t._v(" "),s("div",{staticStyle:{width:"300px",height:"245px","padding-top":"10px","padding-left":"20px"}},[s("el-checkbox",{staticStyle:{display:"block",height:"40px","margin-left":"30px"},attrs:{label:"公司股东构成的稳定性 ( 从上市公司 / 销售额 / 股东数量 / 地域 / 行业等考虑 )"},model:{value:t.supplierrisk.bsComStability,callback:function(e){t.$set(t.supplierrisk,"bsComStability",e)},expression:"supplierrisk.bsComStability"}}),t._v(" "),s("el-checkbox",{staticStyle:{display:"block",height:"40px"},attrs:{label:"未签订反腐协议"},model:{value:t.supplierrisk.bsAntiCorruptProtocol,callback:function(e){t.$set(t.supplierrisk,"bsAntiCorruptProtocol",e)},expression:"supplierrisk.bsAntiCorruptProtocol"}}),t._v(" "),s("el-checkbox",{staticStyle:{display:"block",height:"40px"},attrs:{label:"未签订长期协议"},model:{value:t.supplierrisk.bsLongTermProtocol,callback:function(e){t.$set(t.supplierrisk,"bsLongTermProtocol",e)},expression:"supplierrisk.bsLongTermProtocol"}}),t._v(" "),s("el-checkbox",{staticStyle:{display:"block",height:"40px"},attrs:{label:"未签订战略合作协议"},model:{value:t.supplierrisk.bsCooperProtocol,callback:function(e){t.$set(t.supplierrisk,"bsCooperProtocol",e)},expression:"supplierrisk.bsCooperProtocol"}}),t._v(" "),s("el-checkbox",{staticStyle:{display:"block",height:"40px"},attrs:{label:"未签订商务手册"},model:{value:t.supplierrisk.bsBusinessBroScore,callback:function(e){t.$set(t.supplierrisk,"bsBusinessBroScore",e)},expression:"supplierrisk.bsBusinessBroScore"}})],1)])],1)],1),t._v(" "),s("div",{attrs:{id:"bottom"}},[s("center",[s("el-button",{staticStyle:{padding:"7px 20px"},attrs:{type:"primary"},on:{click:function(e){t.edit()}}},[s("i",{staticClass:"el-icon-news"}),t._v("  "+t._s(t.$t("Button.SaveChanges"))+"\n            ")])],1)],1)],1)},i=[];l._withStripped=!0;var a={render:l,staticRenderFns:i};e.default=a}});