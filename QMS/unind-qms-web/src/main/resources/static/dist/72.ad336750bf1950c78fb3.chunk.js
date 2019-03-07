webpackJsonp([72],{1198:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var o=e(1915),r=e.n(o);for(var i in o)"default"!==i&&function(t){e.d(a,t,function(){return o[t]})}(i);var s=e(2098),n=e.n(s),l=e(1),d=l(r.a,n.a,!1,null,null,null);d.options.__file="src/view/member/pmp/mrp/index.vue",a.default=d.exports},1915:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var o=e(11),r=function(t){return t&&t.__esModule?t:{default:t}}(o);a.default={data:function(){return{loading2:!1,formQuery:{itemNum:"",customerItemNum:""},selectData:[],dialog:{modal_dialog:!1,formQuery:{bsTransportCode:"",bsTransportName:""},datagrid:{queryParams:{page:1,rows:50},pagination:[20,50,100],data:{rows:[],total:0},columns:[{type:"selection",width:80,align:"center"},{title:"运输代码",key:"bsTransportCode"},{title:"运输名称",key:"bsTransportName"}]}},datagrid:{queryParams:{page:1,rows:25},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:"物料编码",key:"itemNum"},{title:"物料描述",key:"itemDesc"},{title:"客户部件",key:"customerItemNum"},{title:"客户编码",key:"customerNum"},{title:"运输周期",key:"pTime"},{title:"需求日期",key:"tDate"},{title:"星期",key:"week",render:function(t,a){return 0==a.row.week?t("span","星期日"):1==a.row.week?t("span","星期一"):2==a.row.week?t("span","星期二"):3==a.row.week?t("span","星期三"):4==a.row.week?t("span","星期四"):5==a.row.week?t("span","星期五"):6==a.row.week?t("span","星期六"):t("span","Unknown")}},{title:"客户需求",key:"qtyX"},{title:"发货日期",key:"sDate"},{title:"发货柜号",key:"packNo"},{title:"待入库",key:"qtyY"},{title:"预计库存",key:"qtyM"},{title:"PO发放数",key:"qtyMrp"},{title:"PR建议数",key:"qtyPr"}]}}},created:function(){this.getData()},methods:{handleSubmit:function(t){this.getData()},getData:function(){var t=this;(0,r.default)(this.formQuery,this.datagrid.queryParams),this.api.pmp.getlist(this.formQuery).then(function(a){a.result?t.datagrid.data=a.data:t.$Message.error(a.msg)})},reloadData:function(){this.datagrid.data=this.getData()},changePage:function(t){this.datagrid.queryParams.page=t,this.datagrid.data=this.getData()},changePageSize:function(t){this.datagrid.queryParams.rows=t,this.datagrid.data=this.getData()},dialogChangePage:function(t){console.log(this.dialog.datagrid.selectData),this.dialog.datagrid.queryParams.page=t,this.dialog.datagrid.data=this.getTransSportData()},dialogChangePageSize:function(t){this.dialog.datagrid.queryParams.rows=t,this.dialog.datagrid.data=this.getTransSportData()},showTransCode:function(){this.dialog.modal_dialog=!0,this.selectData=[],this.getTransSportData()},queryTransCode:function(){this.getTransSportData()},getTransSportData:function(){var t=this;(0,r.default)(this.dialog.formQuery,this.dialog.datagrid.queryParams),this.api.tsr.gettranlist(this.dialog.formQuery).then(function(a){console.log("get list===>"),console.log(a.data),a.result?t.dialog.datagrid.data=a.data:t.$Message.error(a.msg)})},saveIdBySelect:function(t,a){console.log(this.selectData),this.selectData=t},saveIdBySelectAll:function(t){this.selectData=t},cancelSelect:function(t,a){this.selectData=t},cabinetsPlanCancel:function(){this.dialog.modal_dialog=!1},runCabinetsPlan:function(){var t=this;this.loading2=!0,console.log(this.selectData),this.dialog.modal_dialog=!1,this.api.pmp.runCabinetsPlan(this.selectData,this.loading,function(t){return!1}).then(function(a){a.result?(t.$Message.success({content:"自动拼柜成功，可以在拼柜结果中查看!",duration:10,closable:!0}),t.loading2=!1):(t.$Message.error({content:a.msg,duration:10,closable:!0}),t.loading2=!1)})},runErpPoPlan:function(){var t=this;this.api.pmp.runErpPoPlan(this.formQuery,this.loading,function(t){return!1}).then(function(a){a.result?(t.$Message.success({content:"拉取客户需求成功!",duration:10,closable:!0}),t.datagrid.data=t.getData()):t.$Message.error({content:"拉取客户需求失败 请联系管理人员!",duration:10,closable:!0})})}}}},2098:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var o=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[e("Row",[e("i-col",[e("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:t.formQuery,inline:""}},[e("Form-item",{attrs:{prop:"itemNum"}},[e("Input",{attrs:{type:"text",placeholder:"物料编码"},model:{value:t.formQuery.itemNum,callback:function(a){t.$set(t.formQuery,"itemNum",a)},expression:"formQuery.itemNum"}})],1),t._v(" "),e("Form-item",{attrs:{prop:"customerItemNum"}},[e("Input",{attrs:{type:"text",placeholder:"客户部件"},model:{value:t.formQuery.customerItemNum,callback:function(a){t.$set(t.formQuery,"customerItemNum",a)},expression:"formQuery.customerItemNum"}})],1),t._v(" "),e("Form-item",[e("Button",{attrs:{type:"primary"},on:{click:function(a){t.handleSubmit("formQuery")}}},[t._v("查 询")])],1),t._v(" "),e("Form-item",[e("Button",{attrs:{type:"primary"},on:{click:function(a){t.runErpPoPlan()}}},[t.loading?t._e():e("span",[t._v("需求拉取")])])],1),t._v(" "),e("Form-item",[e("Button",{attrs:{type:"primary",loading:t.loading2},on:{click:function(a){t.showTransCode()}}},[t.loading2?e("span",[t._v("Loading...")]):e("span",[t._v("自动拼柜")])])],1)],1)],1)],1),t._v(" "),e("i-col",{attrs:{span:"24"}},[e("Table",{attrs:{data:t.datagrid.data.rows,columns:t.datagrid.columns,stripe:"",height:"700"}}),t._v(" "),e("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[e("div",{staticStyle:{float:"right"}},[e("Page",{attrs:{total:t.datagrid.data.total,current:1,placement:"top","page-size":t.datagrid.queryParams.rows,"page-size-opts":t.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":t.changePage,"on-page-size-change":t.changePageSize}})],1)])],1),t._v(" "),e("Modal",{attrs:{"mask-closable":!1,closable:!1,title:""},model:{value:t.dialog.modal_dialog,callback:function(a){t.$set(t.dialog,"modal_dialog",a)},expression:"dialog.modal_dialog"}},[e("div",{attrs:{slot:"footer"},slot:"footer"},[e("Button",{attrs:{type:"text",size:"large"},on:{click:function(a){t.cabinetsPlanCancel()}}},[t._v("取消")]),t._v(" "),e("Button",{attrs:{type:"primary",size:"large"},on:{click:function(a){t.runCabinetsPlan()}}},[t._v("确定")])],1),t._v(" "),e("Row",[e("i-col",{attrs:{span:"24"}},[e("Form",{ref:"dialog.formQuery",staticClass:"query_area",attrs:{model:t.dialog.formQuery,inline:""}},[e("Form-item",{attrs:{prop:"bsTransportCode"}},[e("Input",{attrs:{type:"text",placeholder:"运输路线代码"},model:{value:t.dialog.formQuery.bsTransportCode,callback:function(a){t.$set(t.dialog.formQuery,"bsTransportCode",a)},expression:"dialog.formQuery.bsTransportCode"}})],1),t._v(" "),e("Form-item",{attrs:{prop:"bsTransportName"}},[e("Input",{attrs:{type:"text",placeholder:"运输路线名称"},model:{value:t.dialog.formQuery.bsTransportName,callback:function(a){t.$set(t.dialog.formQuery,"bsTransportName",a)},expression:"dialog.formQuery.bsTransportName"}})],1),t._v(" "),e("Form-item",[e("Button",{attrs:{type:"primary"},on:{click:function(a){t.queryTransCode("dialog.formQuery")}}},[t._v("查 询")])],1)],1),t._v(" "),e("Table",{attrs:{data:t.dialog.datagrid.data.rows,"highlight-row":"",columns:t.dialog.datagrid.columns,stripe:"",height:"500"},on:{"on-select-cancel":t.cancelSelect,"on-select":t.saveIdBySelect,"on-select-all":t.saveIdBySelectAll}}),t._v(" "),e("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[e("div",{staticStyle:{float:"right"}},[e("Page",{attrs:{total:t.dialog.datagrid.data.total,current:1,placement:"top","page-size":t.dialog.datagrid.queryParams.rows,"page-size-opts":t.dialog.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":t.dialogChangePage,"on-page-size-change":t.dialogChangePageSize}})],1)])],1)],1)],1)],1)},r=[];o._withStripped=!0;var i={render:o,staticRenderFns:r};a.default=i}});