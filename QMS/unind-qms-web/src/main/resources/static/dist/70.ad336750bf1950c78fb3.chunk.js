webpackJsonp([70],{1199:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=o(1916),r=o.n(a);for(var s in a)"default"!==s&&function(e){o.d(t,e,function(){return a[e]})}(s);var i=o(2099),l=o.n(i),n=o(1),d=n(r.a,l.a,!1,null,null,null);d.options.__file="src/view/member/pmp/tsr/index.vue",t.default=d.exports},1916:function(e,t,o){"use strict";function a(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var r=o(22),s=a(r),i=o(11),l=a(i);t.default={data:function(){var e=this;return{formQuery:{},formQuery1:{},dialog:{ruleForm:{bsItemNum:[{required:!0,message:"请填写物料编码",trigger:"blur"}],bsCustomerNum:[{required:!0,message:"请填写客户编号",trigger:"blur"}]},modal_dialog:!1,formItem:{bsTransTime:1,bsWeekDay:0}},dialog2:{ruleForm2:{bsTransportCode:[{required:!0,message:"请填写运输路线代码",trigger:"blur"}]},modal_dialog2:!1,formItem2:{}},datagrid1:{queryParams:{page:1,rows:10},pagination:[10,20,50,100],data:{rows:[],total:0},columns:[{title:"运输路线代码",key:"bsTransportCode"},{title:"运输路线名称",key:"bsTransportName"},{title:"运输路线描述",key:"bsTransportDesc"},{title:"起运港",key:"bsPol"},{title:"目的港",key:"bsPod"},{title:"开始日期",key:"bsShipDate"},{title:"货运时间",key:"bsShipTime"},{title:"操作",key:"action",render:function(t,o){var a=[];return a.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.showBoundDialog(o)}}},"绑定")),a.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.showEditDialog(o)}}},"编辑")),a.push(t("Button",{props:{type:"error",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.deleteTransCode(o)}}},"删除")),t("div",a)}}]},datagrid2:{queryParams:{page:1,rows:10},pagination:[10,20,50,100],data:{rows:[],total:0},columns:[{title:"运输代码",key:"bsTransportCode"},{title:"物料编码",key:"bsItemNum"},{title:"客户编号",key:"bsCustomerNum"},{title:"运输周期",key:"bsTransTime"},{title:"出货星期",key:"bsWeekDay",render:function(e,t){return 0==t.row.bsWeekDay?e("span","星期日"):1==t.row.bsWeekDay?e("span","星期一"):2==t.row.bsWeekDay?e("span","星期二"):3==t.row.bsWeekDay?e("span","星期三"):4==t.row.bsWeekDay?e("span","星期四"):5==t.row.bsWeekDay?e("span","星期五"):6==t.row.bsWeekDay?e("span","星期六"):e("span","Unknown")}},{title:"操作",key:"action",render:function(t,o){var a=[];return a.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.showEditDetailDialog(o)}}},"编辑")),a.push(t("Button",{props:{type:"error",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.deleteDetail(o)}}},"删除")),t("div",a)}}]}}},created:function(){this.getData(),this.getData2()},methods:{handleSubmit1:function(e){this.datagrid1.queryParams.page=1,this.getData()},handleSubmit2:function(e){this.datagrid2.queryParams.page=1,this.getData2()},getData:function(){var e=this;(0,l.default)(this.formQuery,this.datagrid1.queryParams),console.log("after===>"),console.log(this.formQuery),this.api.tsr.gettranlist(this.formQuery).then(function(t){console.log("get list===>"),console.log(t.data),t.result?e.datagrid1.data=t.data:e.$Message.error(t.msg)})},rowClick:function(e,t){console.log(e.bsTransportCode),this.formQuery1.bsTransportCode=e.bsTransportCode,this.getData2()},getData2:function(){var e=this;(0,l.default)(this.formQuery1,this.datagrid2.queryParams),console.log("mayi===>"),console.log(this.formQuery1),this.api.tsr.getdetaillist(this.formQuery1).then(function(t){console.log("get list===>"),console.log(t.data),t.result?e.datagrid2.data=t.data:e.$Message.error(t.msg)})},reloadData:function(){this.getData()},changePage:function(e){this.datagrid1.queryParams.page=e,this.getData()},changePageSize:function(e){this.datagrid1.queryParams.rows=e,this.getData()},changePage2:function(e){this.datagrid2.queryParams.page=e,this.getData2()},changePageSize2:function(e){this.datagrid2.queryParams.rows=e,this.getData()},showBoundDialog:function(e){console.log(e),this.dialog.modal_dialog=!0,this.dialog.formItem.id=null,this.dialog.formItem.bsTransportCode=e.row.bsTransportCode},showEditDetailDialog:function(e){console.log(e),this.dialog.modal_dialog=!0,this.dialog.formItem.bsTransportCode=e.row.bsTransportCode;var t=e.row;this.dialog.formItem={id:t.id,bsTransportCode:t.bsTransportCode,bsInventoryItemId:t.bsInventoryItemId,bsCustomerNum:t.bsCustomerNum,bsItemNum:t.bsItemNum,bsWeekDay:t.bsWeekDay,bsTransTime:t.bsTransTime}},showAddDialog:function(){this.dialog2.modal_dialog2=!0,this.dialog2.formItem2.id=null},showEditDialog:function(e){this.dialog2.modal_dialog2=!0;var t=e.row;this.dialog2.formItem2={id:t.id,bsTransportCode:t.bsTransportCode,bsTransportName:t.bsTransportName,bsTransportDesc:t.bsTransportDesc,bsPol:t.bsPol,bsPod:t.bsPod,bsShipDate:t.bsShipDate,bsShipTime:t.bsShipTime}},addDetailCancel:function(){this.dialog.modal_dialog=!1,this.$refs["dialog.formItem"].resetFields()},addDetail:function(e){var t=this;this.$refs["dialog.formItem"].validate(function(o){o?void 0!=(0,s.default)(t.dialog.formItem.id)&&"number"==typeof t.dialog.formItem.id?(console.log("编辑1"),t.api.tsr.editDetail(e).then(function(e){e.result?(t.dialog.modal_dialog=!1,t.$refs["dialog.formItem"].resetFields(),t.getData2()):t.$Message.info(e.msg)})):(console.log("新增1"),t.api.tsr.addDetail(e).then(function(e){e.result?(t.dialog.modal_dialog=!1,t.$refs["dialog.formItem"].resetFields(),t.getData2()):t.$Message.info(e.msg)})):t.$Message.error("表单验证失败!")})},addCancel:function(){this.dialog2.modal_dialog2=!1,this.$refs["dialog2.formItem2"].resetFields()},deleteDetail:function(e){var t=this;console.log(e),this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){t.api.tsr.deleteDetail({id:e.row.id}).then(function(e){console.log(e),e.result?(t.datagrid2.data=t.getData2(),t.$Message.info("删除成功"),t.$Modal.remove()):t.$Message.error(e.msg)})}})},deleteTransCode:function(e){var t=this;console.log(e),this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",onOk:function(){t.api.tsr.deleteTransCode({id:e.row.id}).then(function(e){console.log(e),e.result?(t.datagrid.data=t.getData(),t.getData2()):t.$Message.error(e.msg)})}})},ok:function(){var e=this;console.log("this.dialog2.formItem2====>"+(0,s.default)(this.dialog2.formItem2.id)),this.$refs["dialog2.formItem2"].validate(function(t){t?void 0!=(0,s.default)(e.dialog2.formItem2.id)&&"number"==typeof e.dialog2.formItem2.id?(console.log("编辑1"),e.api.tsr.edit(e.dialog2.formItem2).then(function(t){t.result?(e.dialog2.modal_dialog2=!1,e.$refs["dialog2.formItem2"].resetFields(),e.datagrid.data=e.getData()):e.$Message.error(t.msg)})):(console.log("新增1"),e.api.tsr.add(e.dialog2.formItem2).then(function(t){t.result?(e.dialog2.modal_dialog2=!1,e.$refs["dialog2.formItem2"].resetFields(),e.datagrid.data=e.getData()):e.$Message.error(t.msg)})):e.$Message.error("表单验证失败!")})}}}},2099:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("Row",[o("div",{staticStyle:{height:"auto","margin-top":"10px"}},[o("div",{staticStyle:{height:"49%"}},[o("i-col",[o("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:e.formQuery,inline:""}},[o("Form-item",{attrs:{prop:"bsTransportCode"}},[o("Input",{attrs:{type:"text",placeholder:"运输路线代码"},model:{value:e.formQuery.bsTransportCode,callback:function(t){e.$set(e.formQuery,"bsTransportCode",t)},expression:"formQuery.bsTransportCode"}})],1),e._v(" "),o("Form-item",{attrs:{prop:"bsTransportName"}},[o("Input",{attrs:{type:"text",placeholder:"运输路线名称"},model:{value:e.formQuery.bsTransportName,callback:function(t){e.$set(e.formQuery,"bsTransportName",t)},expression:"formQuery.bsTransportName"}})],1),e._v(" "),o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(t){e.handleSubmit1("formQuery")}}},[e._v("查 询")])],1),e._v(" "),o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(t){e.showAddDialog()}}},[e._v("新 增")])],1)],1)],1),e._v(" "),o("i-col",[o("Table",{attrs:{"highlight-row":"",data:e.datagrid1.data.rows,columns:e.datagrid1.columns,stripe:"",height:"300"},on:{"on-row-click":e.rowClick}}),e._v(" "),o("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[o("div",{staticStyle:{float:"right"}},[o("Page",{attrs:{total:e.datagrid1.data.total,placement:"top",current:1,"page-size":e.datagrid1.queryParams.rows,"page-size-opts":e.datagrid1.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage,"on-page-size-change":e.changePageSize}})],1)])],1)],1),e._v(" "),o("div",{staticStyle:{height:"49%"}},[o("i-col",[o("Form",{ref:"formQuery1",staticClass:"query_area",attrs:{model:e.formQuery1,inline:""}},[o("Form-item",{attrs:{prop:"bsTransportCode"}},[o("Input",{attrs:{type:"text",placeholder:"运输路线代码"},model:{value:e.formQuery1.bsTransportCode,callback:function(t){e.$set(e.formQuery1,"bsTransportCode",t)},expression:"formQuery1.bsTransportCode"}})],1),e._v(" "),o("Form-item",{attrs:{prop:"bsItemNum"}},[o("Input",{attrs:{type:"text",placeholder:"物料编码"},model:{value:e.formQuery1.bsItemNum,callback:function(t){e.$set(e.formQuery1,"bsItemNum",t)},expression:"formQuery1.bsItemNum"}})],1),e._v(" "),o("Form-item",{attrs:{prop:"bsCustomerNum"}},[o("Input",{attrs:{type:"text",placeholder:"客户编号"},model:{value:e.formQuery1.bsCustomerNum,callback:function(t){e.$set(e.formQuery1,"bsCustomerNum",t)},expression:"formQuery1.bsCustomerNum"}})],1),e._v(" "),o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(t){e.handleSubmit2("formQuery1")}}},[e._v("查 询")])],1),e._v(" "),o("Form-item",{attrs:{prop:"bsInventoryItemId"}},[o("Input",{attrs:{type:"hidden",placeholder:"供应商"},model:{value:e.formQuery1.bsInventoryItemId,callback:function(t){e.$set(e.formQuery1,"bsInventoryItemId",t)},expression:"formQuery1.bsInventoryItemId"}})],1)],1)],1),e._v(" "),o("i-col",[o("Table",{attrs:{data:e.datagrid2.data.rows,columns:e.datagrid2.columns,stripe:"",height:"300"}}),e._v(" "),o("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[o("div",{staticStyle:{float:"right"}},[o("Page",{attrs:{total:e.datagrid2.data.total,placement:"top",current:1,"page-size":e.datagrid2.queryParams.rows,"page-size-opts":e.datagrid2.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage2,"on-page-size-change":e.changePageSize2}})],1)])],1)],1)])]),e._v(" "),o("Modal",{attrs:{"mask-closable":!1,closable:!1,title:""},model:{value:e.dialog.modal_dialog,callback:function(t){e.$set(e.dialog,"modal_dialog",t)},expression:"dialog.modal_dialog"}},[o("div",{attrs:{slot:"footer"},slot:"footer"},[o("Button",{attrs:{type:"text",size:"large"},on:{click:function(t){e.addDetailCancel()}}},[e._v("取消")]),e._v(" "),o("Button",{attrs:{type:"primary",size:"large"},on:{click:function(t){e.addDetail(e.dialog.formItem)}}},[e._v("确定")])],1),e._v(" "),o("Form",{ref:"dialog.formItem",attrs:{model:e.dialog.formItem,rules:e.dialog.ruleForm,"label-width":100}},[o("Input",{attrs:{type:"hidden"},model:{value:e.dialog.formItem.id,callback:function(t){e.$set(e.dialog.formItem,"id",t)},expression:"dialog.formItem.id"}}),e._v(" "),o("Form-item",{attrs:{label:"运输路线代码"}},[o("Input",{attrs:{disabled:"",placeholder:"请输入"},model:{value:e.dialog.formItem.bsTransportCode,callback:function(t){e.$set(e.dialog.formItem,"bsTransportCode",t)},expression:"dialog.formItem.bsTransportCode"}})],1),e._v(" "),o("Form-item",{attrs:{label:"物料编码",prop:"bsItemNum"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsItemNum,callback:function(t){e.$set(e.dialog.formItem,"bsItemNum",t)},expression:"dialog.formItem.bsItemNum"}})],1),e._v(" "),o("Form-item",{attrs:{label:"客户编号",prop:"bsCustomerNum"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCustomerNum,callback:function(t){e.$set(e.dialog.formItem,"bsCustomerNum",t)},expression:"dialog.formItem.bsCustomerNum"}})],1),e._v(" "),o("Form-item",{attrs:{label:"运输周期",prop:"bsTransTime"}},[o("Input-number",{staticStyle:{width:"100%"},attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsTransTime,callback:function(t){e.$set(e.dialog.formItem,"bsTransTime",t)},expression:"dialog.formItem.bsTransTime"}})],1),e._v(" "),o("Form-item",{attrs:{label:"出货星期",prop:"bsWeekDay"}},[o("Select",{attrs:{placeholder:"请选择"},model:{value:e.dialog.formItem.bsWeekDay,callback:function(t){e.$set(e.dialog.formItem,"bsWeekDay",t)},expression:"dialog.formItem.bsWeekDay"}},[o("Option",{attrs:{value:0}},[e._v("星期日")]),e._v(" "),o("Option",{attrs:{value:1}},[e._v("星期一")]),e._v(" "),o("Option",{attrs:{value:2}},[e._v("星期二")]),e._v(" "),o("Option",{attrs:{value:3}},[e._v("星期三")]),e._v(" "),o("Option",{attrs:{value:4}},[e._v("星期四")]),e._v(" "),o("Option",{attrs:{value:5}},[e._v("星期五")]),e._v(" "),o("Option",{attrs:{value:6}},[e._v("星期六")])],1)],1)],1)],1),e._v(" "),o("Modal",{attrs:{"mask-closable":!1,closable:!1},model:{value:e.dialog2.modal_dialog2,callback:function(t){e.$set(e.dialog2,"modal_dialog2",t)},expression:"dialog2.modal_dialog2"}},[o("div",{attrs:{slot:"footer"},slot:"footer"},[o("Button",{attrs:{type:"text",size:"large"},on:{click:function(t){e.addCancel()}}},[e._v("取消")]),e._v(" "),o("Button",{attrs:{type:"primary",size:"large"},on:{click:function(t){e.ok()}}},[e._v("确定")])],1),e._v(" "),o("Form",{ref:"dialog2.formItem2",attrs:{model:e.dialog2.formItem2,rules:e.dialog2.ruleForm2,"label-width":100}},[o("Input",{attrs:{type:"hidden"},model:{value:e.dialog2.formItem2.id,callback:function(t){e.$set(e.dialog2.formItem2,"id",t)},expression:"dialog2.formItem2.id"}}),e._v(" "),o("Form-item",{attrs:{label:"运输路线代码",prop:"bsTransportCode"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog2.formItem2.bsTransportCode,callback:function(t){e.$set(e.dialog2.formItem2,"bsTransportCode",t)},expression:"dialog2.formItem2.bsTransportCode"}})],1),e._v(" "),o("Form-item",{attrs:{label:"运输路线名称",prop:"bsTransportName"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog2.formItem2.bsTransportName,callback:function(t){e.$set(e.dialog2.formItem2,"bsTransportName",t)},expression:"dialog2.formItem2.bsTransportName"}})],1),e._v(" "),o("Form-item",{attrs:{label:"运输路线描述",prop:"bsTransportDesc"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog2.formItem2.bsTransportDesc,callback:function(t){e.$set(e.dialog2.formItem2,"bsTransportDesc",t)},expression:"dialog2.formItem2.bsTransportDesc"}})],1),e._v(" "),o("Form-item",{attrs:{label:"起运港",prop:"bsPol"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog2.formItem2.bsPol,callback:function(t){e.$set(e.dialog2.formItem2,"bsPol",t)},expression:"dialog2.formItem2.bsPol"}})],1),e._v(" "),o("Form-item",{attrs:{label:"目的港",prop:"bsPod"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog2.formItem2.bsPod,callback:function(t){e.$set(e.dialog2.formItem2,"bsPod",t)},expression:"dialog2.formItem2.bsPod"}})],1),e._v(" "),o("Form-item",{attrs:{label:"货运日期",prop:"bsShipDate"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog2.formItem2.bsShipDate,callback:function(t){e.$set(e.dialog2.formItem2,"bsShipDate",t)},expression:"dialog2.formItem2.bsShipDate"}})],1),e._v(" "),o("Form-item",{attrs:{label:"货运时间",prop:"bsShipTime"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog2.formItem2.bsShipTime,callback:function(t){e.$set(e.dialog2.formItem2,"bsShipTime",t)},expression:"dialog2.formItem2.bsShipTime"}})],1)],1)],1)],1)},r=[];a._withStripped=!0;var s={render:a,staticRenderFns:r};t.default=s}});