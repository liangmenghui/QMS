webpackJsonp([24],{1191:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=o(1907),r=o.n(s);for(var a in s)"default"!==a&&function(e){o.d(t,e,function(){return s[e]})}(a);var i=o(2085),n=o.n(i),l=o(1),m=l(r.a,n.a,!1,null,null,null);m.options.__file="src/view/erp/itemcustomer/index.vue",t.default=m.exports},1262:function(e,t,o){"use strict";function s(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var r=o(14),a=s(r),i=o(1276),n=s(i);t.default={components:{custExtprops:n.default},props:{type:String,size:String,name:String,catelog:String,rid:""},data:function(){return{title:"客制化属性",visible:!1,data:[]}},computed:{},created:function(){},methods:{showDialog:function(e){"Number"==typeof e&&(this.rid=e),this.rid?(this.$refs.custExtprops.show(!0),this.loadData(this.rid)):console.log("rid is required")},loadData:function(e){var t=this,o=this.catelog+":"+e;this.api.admin.customfieldvalue.getlist({catelog:this.catelog,handler:o}).then(function(e){if(console.log(e),e.result){for(var s in e.data.rows)e.data.rows[s].handler=o+","+e.data.rows[s].bsName;t.data=e.data.rows}})},ok:function(e){var t=this;this.api.admin.customfieldvalue.save({catelog:this.catelog,json:(0,a.default)(e)}).then(function(e){e.result?t.$message.info(e.message):t.$message.error(e.message)})},cancel:function(){this.$emit("cancel")}}}},1263:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={name:"cust-extprops",props:{title:String,data:Array},data:function(){return{formItem:{},visible:!1}},created:function(){},methods:{show:function(e){this.visible=!0},ok:function(){this.$emit("ok",this.data)},cancel:function(){this.$emit("cancel")}}}},1273:function(e,t,o){"use strict";function s(e){m||o(1274)}Object.defineProperty(t,"__esModule",{value:!0});var r=o(1262),a=o.n(r);for(var i in r)"default"!==i&&function(e){o.d(t,e,function(){return r[e]})}(i);var n=o(1280),l=o.n(n),m=!1,u=o(1),d=s,c=u(a.a,l.a,!1,d,null,null);c.options.__file="src/components/business-component/custDialog.vue",t.default=c.exports},1274:function(e,t,o){var s=o(1275);"string"==typeof s&&(s=[[e.i,s,""]]),s.locals&&(e.exports=s.locals);o(19)("489ad722",s,!1)},1275:function(e,t,o){t=e.exports=o(18)(!1),t.push([e.i,"\n.split-width {\n  padding-left: 3px;\n}\n",""])},1276:function(e,t,o){"use strict";function s(e){m||o(1277)}Object.defineProperty(t,"__esModule",{value:!0});var r=o(1263),a=o.n(r);for(var i in r)"default"!==i&&function(e){o.d(t,e,function(){return r[e]})}(i);var n=o(1279),l=o.n(n),m=!1,u=o(1),d=s,c=u(a.a,l.a,!1,d,null,null);c.options.__file="src/components/business-component/custExtprops.vue",t.default=c.exports},1277:function(e,t,o){var s=o(1278);"string"==typeof s&&(s=[[e.i,s,""]]),s.locals&&(e.exports=s.locals);o(19)("38340c12",s,!1)},1278:function(e,t,o){t=e.exports=o(18)(!1),t.push([e.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",""])},1279:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("Modal",{attrs:{transfer:"",title:e.title},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.visible,callback:function(t){e.visible=t},expression:"visible"}},[o("div",{staticStyle:{padding:"5px 5px"}},[o("Form",{ref:"ruleForm",attrs:{"label-width":80}},e._l(e.data,function(t){return o("Row",{key:t.bsName},[o("i-col",{attrs:{span:"24"}},[o("span",{staticStyle:{display:"none"}},[o("Input",{model:{value:t.id,callback:function(o){e.$set(t,"id",o)},expression:"item.id"}})],1),e._v(" "),o("Form-item",{attrs:{label:t.bsLabel}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:t.bsValue,callback:function(o){e.$set(t,"bsValue",o)},expression:"item.bsValue"}})],1)],1)],1)}))],1)])},r=[];s._withStripped=!0;var a={render:s,staticRenderFns:r};t.default=a},1280:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("span",{staticClass:"split-width"},[o("Button",{attrs:{type:e.type,size:e.size},on:{click:e.showDialog}},[e._v(e._s(e.name))]),e._v(" "),o("cust-extprops",{ref:"custExtprops",attrs:{data:e.data,title:"客制化属性"},on:{ok:e.ok}})],1)},r=[];s._withStripped=!0;var a={render:s,staticRenderFns:r};t.default=a},1907:function(e,t,o){"use strict";function s(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var r=o(14),a=s(r),i=o(22),n=s(i),l=o(11),m=s(l),u=o(72),d=s(u),c=o(98),p=o(1273),f=s(p);t.default={components:{custDialog:f.default},data:function(){var e=this;return{formQuery:{bsCode:"",bsName:""},dialog:{modal_dialog:!1,formItem:{},ruleForm:{bsOrganizationId:[{required:!0,message:"请填写组织ID",trigger:"blur"}],bsItemId:[{required:!0,message:"请填写物料ID",trigger:"blur"}],bsCode:[{required:!0,message:"请填写编码",trigger:"blur"}],bsName:[{required:!0,message:"请填写名称",trigger:"blur"}]}},datagrid:{queryParams:{page:1,rows:25,pkParent:-1},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:"物料编码",key:"bsItemCode"},{title:"部件号",key:"bsCustomerItemNumber"},{title:"客户编码",key:"bsCustomerCode"},{title:"客户名称",key:"bsCustomerName"},{title:"物料名称",key:"bsItemName"},{title:"删除标识",key:"bsIsDel",render:function(e,t){return e("span",{style:{fontColor:"red"}},0==t.row.bsIsDel?"-":"已删除")}},{title:"组织ID",key:"bsOrganizationId"},{title:"物料ID",key:"bsItemId"},{title:"客户ID",key:"bsCustomerId"},{title:"更新时间",key:"bsModifiedTime"},{title:"操作",key:"action",render:function(t,o){var s=[];return s.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){var t={id:o.row.id,bsItemCode:o.row.bsItemCode,bsItemName:o.row.bsItemName,bsCustomerCode:o.row.bsCustomerCode,bsCustomerName:o.row.bsCustomerName,bsCustomerItemNumber:o.row.bsCustomerItemNumber,bsCustomerItemDesc:o.row.bsCustomerItemDesc};e.$router.push({name:"PMP_SHIPMENT_CONFIG",query:t})}}},"扩展表")),e.menuData.perms.EDIT&&s.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.showEditDialog(o)}}},"编辑")),e.menuData.perms.DELETE&&s.push(t("Button",{props:{type:"error",size:"small"},on:{click:function(){e.delete(o)}}},"删除")),e.menuData.perms.CUSTOMFIELD&&s.push(t(f.default,{props:{type:"primary",size:"small",name:"客制化",ref:"custDialog",catelog:"ErpItemCustomerBo",rid:o.row.id},on:{click:function(){e.$refs.custDialog.showDialog()}}})),t("div",s)}}]}}},created:function(){},computed:(0,d.default)({},(0,c.mapState)({menuData:function(e){return e.menuData}})),methods:{handleSubmit:function(e){this.getData()},getData:function(){var e=this;(0,m.default)(this.formQuery,this.datagrid.queryParams),console.log("after===>"),console.log(this.formQuery),this.api.erp.itemcustomer.getlist(this.formQuery).then(function(t){console.log("get list===>"),console.log(t.data),t.result?e.datagrid.data=t.data:e.$Message.error(t.msg)})},reloadData:function(){this.datagrid.data=this.getData()},changePage:function(e){this.datagrid.queryParams.page=e,this.datagrid.data=this.getData()},edit:function(e){var t=this;console.log(e),this.api.erp.itemcustomer.edit(e.row).then(function(e){console.log(e),e.result?t.reloadData():t.$Message.error(e.msg)})},delete:function(e){var t=this;console.log(e),this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){t.api.erp.itemcustomer.delete({id:e.row.id}).then(function(e){console.log(e),e.result?(t.reloadData(),t.$Message.info("删除成功"),t.$Modal.remove()):t.$Message.error(e.msg)})}})},download:function(){var e=this;this.api.erp.itemcustomer.download(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})},showAddDialog:function(){this.dialog.modal_dialog=!0,this.dialog.formItem={}},showEditDialog:function(e){this.dialog.modal_dialog=!0;var t=e.row;this.dialog.formItem={id:t.id,bsCode:t.bsCode,bsName:t.bsName,bsItemId:t.bsItemId,bsItemCode:t.bsItemCode,bsItemName:t.bsItemName,bsCustomerId:t.bsCustomerId,bsCustomerCode:t.bsCustomerCode,bsCustomerName:t.bsCustomerName,bsCustomerItemNumber:t.bsCustomerItemNumber,bsCustomerItemDesc:t.bsCustomerItemDesc}},ok:function(){var e=this;console.log("this.dialog.formItem====>"+(0,n.default)(this.dialog.formItem.id)),void 0!=(0,n.default)(this.dialog.formItem.id)&&"number"==typeof this.dialog.formItem.id?(console.log("编辑1"),console.log((0,a.default)(this.dialog.formItem)),this.api.erp.itemcustomer.edit(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})):(console.log("新增1"),this.api.erp.itemcustomer.add(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)}))},cancel:function(){}}}},2085:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("Row",[o("i-col",[o("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:e.formQuery,inline:""}},[o("Form-item",{attrs:{prop:"bsCustomerItemNumber"}},[o("Input",{attrs:{type:"text",placeholder:"客户物料编码"},model:{value:e.formQuery.bsCustomerItemNumber,callback:function(t){e.$set(e.formQuery,"bsCustomerItemNumber",t)},expression:"formQuery.bsCustomerItemNumber"}})],1),e._v(" "),o("Form-item",{attrs:{prop:"bsCustomerItemDesc"}},[o("Input",{attrs:{type:"text",placeholder:"客户物料名称"},model:{value:e.formQuery.bsCustomerItemDesc,callback:function(t){e.$set(e.formQuery,"bsCustomerItemDesc",t)},expression:"formQuery.bsCustomerItemDesc"}})],1),e._v(" "),o("Form-item",{attrs:{prop:"bsItemCode"}},[o("Input",{attrs:{type:"text",placeholder:"物料编码"},model:{value:e.formQuery.bsItemCode,callback:function(t){e.$set(e.formQuery,"bsItemCode",t)},expression:"formQuery.bsItemCode"}})],1),e._v(" "),o("Form-item",{attrs:{prop:"bsItemName"}},[o("Input",{attrs:{type:"text",placeholder:"物料名称"},model:{value:e.formQuery.bsItemName,callback:function(t){e.$set(e.formQuery,"bsItemName",t)},expression:"formQuery.bsItemName"}})],1),e._v(" "),o("Form-item",{attrs:{prop:"bsCustomerCode"}},[o("Input",{attrs:{type:"text",placeholder:"客户编码"},model:{value:e.formQuery.bsCustomerCode,callback:function(t){e.$set(e.formQuery,"bsCustomerCode",t)},expression:"formQuery.bsCustomerCode"}})],1),e._v(" "),o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(t){e.handleSubmit("formQuery")}}},[e._v("查 询")])],1),e._v(" "),e._e(),e._v(" "),o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(t){e.download()}}},[e._v("导 入")])],1)],1)],1)],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"24"}},[o("Table",{attrs:{data:e.datagrid.data.rows,columns:e.datagrid.columns,stripe:"",height:"500"}}),e._v(" "),o("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[o("div",{staticStyle:{float:"right"}},[o("Page",{attrs:{total:e.datagrid.data.total,current:1,"page-size":e.datagrid.queryParams.rows,"page-size-opts":e.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage}})],1)])],1)],1),e._v(" "),o("Modal",{attrs:{title:"",width:"800"},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.dialog.modal_dialog,callback:function(t){e.$set(e.dialog,"modal_dialog",t)},expression:"dialog.modal_dialog"}},[o("p",[o("Form",{ref:e.dialog.ruleForm,attrs:{model:e.dialog.formItem,rules:e.dialog.ruleForm,"label-width":80}},[o("span",{staticStyle:{display:"none"}},[o("Input",{model:{value:e.dialog.formItem.id,callback:function(t){e.$set(e.dialog.formItem,"id",t)},expression:"dialog.formItem.id"}})],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"物料ID",prop:"bsItemId"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsItemId,callback:function(t){e.$set(e.dialog.formItem,"bsItemId",t)},expression:"dialog.formItem.bsItemId"}})],1)],1),e._v(" "),o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"物料编码",prop:"bsItemCode"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsItemCode,callback:function(t){e.$set(e.dialog.formItem,"bsItemCode",t)},expression:"dialog.formItem.bsItemCode"}})],1)],1)],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"24"}},[o("Form-item",{attrs:{label:"物料名称",prop:"bsItemName"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsItemName,callback:function(t){e.$set(e.dialog.formItem,"bsItemName",t)},expression:"dialog.formItem.bsItemName"}})],1)],1)],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"客户ID",prop:"bsCustomerId"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCustomerId,callback:function(t){e.$set(e.dialog.formItem,"bsCustomerId",t)},expression:"dialog.formItem.bsCustomerId"}})],1)],1),e._v(" "),o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"客户编码",prop:"bsCustomerCode"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCustomerCode,callback:function(t){e.$set(e.dialog.formItem,"bsCustomerCode",t)},expression:"dialog.formItem.bsCustomerCode"}})],1)],1)],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"24"}},[o("Form-item",{attrs:{label:"客户名称",prop:"bsCustomerName"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCustomerName,callback:function(t){e.$set(e.dialog.formItem,"bsCustomerName",t)},expression:"dialog.formItem.bsCustomerName"}})],1)],1)],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"客户物料号",prop:"bsCustomerItemNumber"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCustomerItemNumber,callback:function(t){e.$set(e.dialog.formItem,"bsCustomerItemNumber",t)},expression:"dialog.formItem.bsCustomerItemNumber"}})],1)],1),e._v(" "),o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"客户物料名称",prop:"bsCustomerItemDesc"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCustomerItemDesc,callback:function(t){e.$set(e.dialog.formItem,"bsCustomerItemDesc",t)},expression:"dialog.formItem.bsCustomerItemDesc"}})],1)],1)],1)],1)],1)])],1)},r=[];s._withStripped=!0;var a={render:s,staticRenderFns:r};t.default=a}});