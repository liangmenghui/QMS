webpackJsonp([74],{1196:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s(1913),r=s.n(a);for(var o in a)"default"!==o&&function(t){s.d(e,t,function(){return a[t]})}(o);var m=s(2094),i=s.n(m),n=s(1),l=n(r.a,i.a,!1,null,null,null);l.options.__file="src/view/member/pmp/config/index.vue",e.default=l.exports},1913:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s(72),r=function(t){return t&&t.__esModule?t:{default:t}}(a),o=s(98);e.default={data:function(){return{formQuery:{page:1,rows:1,erpItemCustomerAgg:""},formItem:{erpItemCustomerAgg:"",bsMinSaftyInv:4,bsMaxSaftyInv:6,bsStdSaftyInv:5,bsMinLimit:2,bsMaxLimit:8,bsCabinetRatio:.9,bsHaulCycle:47,bsDayOfLoading:-2,bsDayOfInspect:-1,bsDayOfProd:40},ruleForm:{bsMinSaftyInv:[{required:!0,message:"请填写最小安全库存周数",trigger:"blur"}],bsMaxSaftyInv:[{required:!0,message:"请填写物料最大安全库存周数",trigger:"blur"}],bsStdSaftyInv:[{required:!0,message:"请填写标准安全库存周数",trigger:"blur"}],bsMinLimit:[{required:!0,message:"请填写安全库存极限值（最小）",trigger:"blur"}],bsMaxLimit:[{required:!0,message:"请填写安全库存极限值（最大）",trigger:"blur"}],bsCabinetRatio:[{required:!0,message:"请填写装柜系数",trigger:"blur"}],bsHaulCycle:[{required:!0,message:"请填写运输周期",trigger:"blur"}],bsDayOfLoading:[{required:!0,message:"请填写装柜日",trigger:"blur"}],bsDayOfInspect:[{required:!0,message:"请填写检验日",trigger:"blur"}],bsDayOfProd:[{required:!0,message:"请填写生产周期",trigger:"blur"}]}}},created:function(){},computed:(0,r.default)({},(0,o.mapState)({menuData:function(t){return t.menuData}})),mounted:function(){this.init()},watch:{$route:function(){this.init()}},methods:{init:function(){this.formQuery.erpItemCustomerAgg=this.$route.query.id,this.formItem.erpItemCustomerAgg=this.$route.query.id,this.getData()},selectItem:function(){},handleSubmit:function(t){this.getData()},getData:function(){var t=this;this.api.shipmentconfig.getdetail(this.formQuery).then(function(e){t.formItem.bsMinSaftyInv=e.data.bsMinSaftyInv,t.formItem.bsMaxSaftyInv=e.data.bsMaxSaftyInv,t.formItem.bsStdSaftyInv=e.data.bsStdSaftyInv,t.formItem.bsMinLimit=e.data.bsMinLimit,t.formItem.bsMaxLimit=e.data.bsMaxLimit,t.formItem.bsCabinetRatio=e.data.bsCabinetRatio,t.formItem.bsHaulCycle=e.data.bsHaulCycle,t.formItem.bsDayOfLoading=e.data.bsDayOfLoading,t.formItem.bsDayOfInspect=e.data.bsDayOfInspect,t.formItem.bsDayOfProd=e.data.bsDayOfProd,t.formItem.bsItemId=e.data.erpItemCustomerAgg.bsItemId,t.formItem.bsItemCode=e.data.erpItemCustomerAgg.bsItemCode,t.formItem.bsItemName=e.data.erpItemCustomerAgg.bsItemName,t.formItem.bsCustomerId=e.data.erpItemCustomerAgg.bsCustomerId,t.formItem.bsCustomerCode=e.data.erpItemCustomerAgg.bsCustomerCode,t.formItem.bsCustomerName=e.data.erpItemCustomerAgg.bsCustomerName,t.formItem.bsCustomerItemNumber=e.data.erpItemCustomerAgg.bsCustomerItemNumber,t.formItem.bsCustomerItemDesc=e.data.erpItemCustomerAgg.bsCustomerItemDesc})},reloadData:function(){this.formItem=this.getData()},delete:function(t){var e=this;console.log(t),this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){e.api.shipmentconfig.delete({id:t.row.id}).then(function(t){t.result?e.$Message.info("删除成功"):e.$Message.error(t.msg)})}})},save:function(){var t=this;this.api.shipmentconfig.save(this.formItem).then(function(e){e.result?t.$Message.info(e.msg):t.$Message.error(e.msg)})},cancel:function(){}}}},2094:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("Row",[s("i-col",[s("Form",{ref:"ruleForm",attrs:{model:t.formItem,"label-width":120}},[s("span",{staticStyle:{display:"none"}},[s("Input",{model:{value:t.formItem.erpItemCustomerAgg,callback:function(e){t.$set(t.formItem,"erpItemCustomerAgg",e)},expression:"formItem.erpItemCustomerAgg"}}),t._v(" "),s("Input",{attrs:{placeholder:"请输入"},model:{value:t.formItem.bsItemId,callback:function(e){t.$set(t.formItem,"bsItemId",e)},expression:"formItem.bsItemId"}}),t._v(" "),s("Input",{attrs:{placeholder:"请输入"},model:{value:t.formItem.bsCustomerId,callback:function(e){t.$set(t.formItem,"bsCustomerId",e)},expression:"formItem.bsCustomerId"}})],1),t._v(" "),s("Row",{staticStyle:{"margin-bottom":"10px"}},[s("i-col",{attrs:{span:"24"}},[s("Button",{attrs:{type:"primary"},on:{click:function(e){t.save()}}},[t._v("保 存")])],1)],1),t._v(" "),s("Row",[s("i-col",{attrs:{span:"24"}},[s("Row",[s("i-col",{attrs:{span:"4"}},[s("span",[t._v("物料编码：")])]),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("span",[t._v(t._s(t.formItem.bsItemCode))])]),t._v(" "),s("i-col",{attrs:{span:"4"}},[s("span",[t._v("物料名称：")])]),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("span",[t._v(t._s(t.formItem.bsItemName))])])],1)],1)],1),s("br"),t._v(" "),s("Row",[s("i-col",{attrs:{span:"24"}},[s("Row",[s("i-col",{attrs:{span:"4"}},[s("span",[t._v("客户编码：")])]),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("span",[t._v(t._s(t.formItem.bsCustomerCode))])]),t._v(" "),s("i-col",{attrs:{span:"4"}},[s("span",[t._v("客户名称：")])]),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("span",[t._v(t._s(t.formItem.bsCustomerName))])])],1)],1)],1),s("br"),t._v(" "),s("Row",[s("i-col",{attrs:{span:"24"}},[s("Row",[s("i-col",{attrs:{span:"4"}},[s("span",[t._v("客户物料编号：")])]),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("span",[t._v(t._s(t.formItem.bsCustomerItemDesc))])]),t._v(" "),s("i-col",{attrs:{span:"4"}},[s("span",[t._v("客户物料名称：")])]),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("span",[t._v(t._s(t.formItem.bsCustomerItemDesc))])])],1)],1)],1),s("br"),t._v(" "),s("Row",[s("i-col",{attrs:{span:"8"}},[s("Form-item",{attrs:{label:"安全库存周数（周）",prop:"bsMinSaftyInv"}},[s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsMinSaftyInv,callback:function(e){t.$set(t.formItem,"bsMinSaftyInv",e)},expression:"formItem.bsMinSaftyInv"}}),t._v("\n                        - "),s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsMaxSaftyInv,callback:function(e){t.$set(t.formItem,"bsMaxSaftyInv",e)},expression:"formItem.bsMaxSaftyInv"}})],1)],1),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("Form-item",{attrs:{label:"标准安全库存（周）",prop:"bsStdSaftyInv"}},[s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsStdSaftyInv,callback:function(e){t.$set(t.formItem,"bsStdSaftyInv",e)},expression:"formItem.bsStdSaftyInv"}})],1)],1),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("Form-item",{attrs:{label:"安全库存极限值（周）",prop:"bsMinLimit"}},[s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsMinLimit,callback:function(e){t.$set(t.formItem,"bsMinLimit",e)},expression:"formItem.bsMinLimit"}}),t._v("\n                        - "),s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsMaxLimit,callback:function(e){t.$set(t.formItem,"bsMaxLimit",e)},expression:"formItem.bsMaxLimit"}})],1)],1)],1),t._v(" "),s("Row",[s("i-col",{attrs:{span:"8"}},[s("Form-item",{attrs:{label:"装柜系数",prop:"bsCabinetRatio"}},[s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsCabinetRatio,callback:function(e){t.$set(t.formItem,"bsCabinetRatio",e)},expression:"formItem.bsCabinetRatio"}})],1)],1),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("Form-item",{attrs:{label:"运输周期",prop:"bsHaulCycle"}},[s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsHaulCycle,callback:function(e){t.$set(t.formItem,"bsHaulCycle",e)},expression:"formItem.bsHaulCycle"}})],1)],1),t._v(" "),s("i-col",{attrs:{span:"8"}},[s("Form-item",{attrs:{label:"装柜日",prop:"bsDayOfLoading"}},[s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsDayOfLoading,callback:function(e){t.$set(t.formItem,"bsDayOfLoading",e)},expression:"formItem.bsDayOfLoading"}})],1)],1)],1),t._v(" "),s("Row",[s("i-col",{attrs:{span:"8"}},[s("Form-item",{attrs:{label:"检验日",prop:"bsDayOfInspect"}},[s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsDayOfInspect,callback:function(e){t.$set(t.formItem,"bsDayOfInspect",e)},expression:"formItem.bsDayOfInspect"}})],1)],1),t._v(" "),s("i-col",{attrs:{span:"16"}},[s("Form-item",{attrs:{label:"生产周期",prop:"bsDayOfProd"}},[s("Input",{staticStyle:{width:"80px"},attrs:{placeholder:"请输入"},model:{value:t.formItem.bsDayOfProd,callback:function(e){t.$set(t.formItem,"bsDayOfProd",e)},expression:"formItem.bsDayOfProd"}})],1)],1)],1)],1)],1)],1)],1)},r=[];a._withStripped=!0;var o={render:a,staticRenderFns:r};e.default=o}});