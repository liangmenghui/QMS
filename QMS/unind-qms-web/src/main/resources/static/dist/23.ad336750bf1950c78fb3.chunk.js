webpackJsonp([23],{1190:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=o(1906),r=o.n(a);for(var i in a)"default"!==i&&function(e){o.d(t,e,function(){return a[e]})}(i);var n=o(2084),s=o.n(n),l=o(1),d=l(r.a,s.a,!1,null,null,null);d.options.__file="src/view/erp/supplier/index.vue",t.default=d.exports},1262:function(e,t,o){"use strict";function a(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var r=o(14),i=a(r),n=o(1276),s=a(n);t.default={components:{custExtprops:s.default},props:{type:String,size:String,name:String,catelog:String,rid:""},data:function(){return{title:"客制化属性",visible:!1,data:[]}},computed:{},created:function(){},methods:{showDialog:function(e){"Number"==typeof e&&(this.rid=e),this.rid?(this.$refs.custExtprops.show(!0),this.loadData(this.rid)):console.log("rid is required")},loadData:function(e){var t=this,o=this.catelog+":"+e;this.api.admin.customfieldvalue.getlist({catelog:this.catelog,handler:o}).then(function(e){if(console.log(e),e.result){for(var a in e.data.rows)e.data.rows[a].handler=o+","+e.data.rows[a].bsName;t.data=e.data.rows}})},ok:function(e){var t=this;this.api.admin.customfieldvalue.save({catelog:this.catelog,json:(0,i.default)(e)}).then(function(e){e.result?t.$message.info(e.message):t.$message.error(e.message)})},cancel:function(){this.$emit("cancel")}}}},1263:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={name:"cust-extprops",props:{title:String,data:Array},data:function(){return{formItem:{},visible:!1}},created:function(){},methods:{show:function(e){this.visible=!0},ok:function(){this.$emit("ok",this.data)},cancel:function(){this.$emit("cancel")}}}},1273:function(e,t,o){"use strict";function a(e){d||o(1274)}Object.defineProperty(t,"__esModule",{value:!0});var r=o(1262),i=o.n(r);for(var n in r)"default"!==n&&function(e){o.d(t,e,function(){return r[e]})}(n);var s=o(1280),l=o.n(s),d=!1,u=o(1),c=a,m=u(i.a,l.a,!1,c,null,null);m.options.__file="src/components/business-component/custDialog.vue",t.default=m.exports},1274:function(e,t,o){var a=o(1275);"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);o(19)("489ad722",a,!1)},1275:function(e,t,o){t=e.exports=o(18)(!1),t.push([e.i,"\n.split-width {\n  padding-left: 3px;\n}\n",""])},1276:function(e,t,o){"use strict";function a(e){d||o(1277)}Object.defineProperty(t,"__esModule",{value:!0});var r=o(1263),i=o.n(r);for(var n in r)"default"!==n&&function(e){o.d(t,e,function(){return r[e]})}(n);var s=o(1279),l=o.n(s),d=!1,u=o(1),c=a,m=u(i.a,l.a,!1,c,null,null);m.options.__file="src/components/business-component/custExtprops.vue",t.default=m.exports},1277:function(e,t,o){var a=o(1278);"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);o(19)("38340c12",a,!1)},1278:function(e,t,o){t=e.exports=o(18)(!1),t.push([e.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",""])},1279:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("Modal",{attrs:{transfer:"",title:e.title},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.visible,callback:function(t){e.visible=t},expression:"visible"}},[o("div",{staticStyle:{padding:"5px 5px"}},[o("Form",{ref:"ruleForm",attrs:{"label-width":80}},e._l(e.data,function(t){return o("Row",{key:t.bsName},[o("i-col",{attrs:{span:"24"}},[o("span",{staticStyle:{display:"none"}},[o("Input",{model:{value:t.id,callback:function(o){e.$set(t,"id",o)},expression:"item.id"}})],1),e._v(" "),o("Form-item",{attrs:{label:t.bsLabel}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:t.bsValue,callback:function(o){e.$set(t,"bsValue",o)},expression:"item.bsValue"}})],1)],1)],1)}))],1)])},r=[];a._withStripped=!0;var i={render:a,staticRenderFns:r};t.default=i},1280:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("span",{staticClass:"split-width"},[o("Button",{attrs:{type:e.type,size:e.size},on:{click:e.showDialog}},[e._v(e._s(e.name))]),e._v(" "),o("cust-extprops",{ref:"custExtprops",attrs:{data:e.data,title:"客制化属性"},on:{ok:e.ok}})],1)},r=[];a._withStripped=!0;var i={render:a,staticRenderFns:r};t.default=i},1906:function(e,t,o){"use strict";function a(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var r=o(14),i=a(r),n=o(22),s=a(n),l=o(11),d=a(l),u=o(72),c=a(u),m=o(98),p=o(1273),f=a(p);t.default={components:{custDialog:f.default},data:function(){var e=this;return{formQuery:{bsCode:"",bsName:""},dialog:{modal_dialog:!1,formItem:{},ruleForm:{bsOrganizationId:[{required:!0,message:"请填写组织ID",trigger:"blur"}],bsSupplierId:[{required:!0,message:"请填写供应商ID",trigger:"blur"}],bsCode:[{required:!0,message:"请填写编码",trigger:"blur"}],bsName:[{required:!0,message:"请填写名称",trigger:"blur"}]}},datagrid:{queryParams:{page:1,rows:25,pkParent:-1},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:"组织ID",key:"bsOrganizationId"},{title:"供应商ID",key:"bsVendorId"},{title:"供应商编码",key:"bsCode"},{title:"供应商名称",key:"bsName"},{title:"地址",key:"bsAddressLine1"},{title:"删除标识",key:"bsIsDel",render:function(e,t){return e("span",{style:{fontColor:"red"}},0==t.row.bsIsDel?"-":"已删除")}},{title:"创建时间",key:"bsCreatedTime"},{title:"更新时间",key:"bsModifiedTime"},{title:"操作",key:"action",render:function(t,o){var a=[];return e.menuData.perms.EDIT&&a.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.showEditDialog(o)}}},"编辑")),e.menuData.perms.DELETE&&a.push(t("Button",{props:{type:"error",size:"small"},on:{click:function(){e.delete(o)}}},"删除")),e.menuData.perms.CUSTOMFIELD&&a.push(t(f.default,{props:{type:"primary",size:"small",name:"客制化",ref:"custDialog",catelog:"ErpSupplierBo",rid:o.row.id},on:{click:function(){e.$refs.custDialog.showDialog()}}})),t("div",a)}}]}}},created:function(){},computed:(0,c.default)({},(0,m.mapState)({menuData:function(e){return e.menuData}})),methods:{handleSubmit:function(e){this.getData()},getData:function(){var e=this;(0,d.default)(this.formQuery,this.datagrid.queryParams),console.log("after===>"),console.log(this.formQuery),this.api.erp.supplier.getlist(this.formQuery).then(function(t){console.log("get list===>"),console.log(t.data),t.result?e.datagrid.data=t.data:e.$Message.error(t.msg)})},reloadData:function(){this.datagrid.data=this.getData()},changePage:function(e){this.datagrid.queryParams.page=e,this.datagrid.data=this.getData()},edit:function(e){var t=this;console.log(e),this.api.erp.supplier.edit(e.row).then(function(e){console.log(e),e.result?t.reloadData():t.$Message.error(e.msg)})},delete:function(e){var t=this;console.log(e),this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){t.api.erp.supplier.delete({id:e.row.id}).then(function(e){console.log(e),e.result?(t.reloadData(),t.$Message.info("删除成功"),t.$Modal.remove()):t.$Message.error(e.msg)})}})},download:function(){var e=this;this.api.erp.supplier.download(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})},showAddDialog:function(){this.dialog.modal_dialog=!0,this.dialog.formItem={}},showEditDialog:function(e){this.dialog.modal_dialog=!0;var t=e.row;this.dialog.formItem={id:t.id,bsCode:t.bsCode,bsName:t.bsName,bsOrganizationId:t.bsOrganizationId,bsVendorId:t.bsVendorId,bsAddressLine1:t.bsAddressLine1}},ok:function(){var e=this;console.log("this.dialog.formItem====>"+(0,s.default)(this.dialog.formItem.id)),void 0!=(0,s.default)(this.dialog.formItem.id)&&"number"==typeof this.dialog.formItem.id?(console.log("编辑1"),console.log((0,i.default)(this.dialog.formItem)),this.api.erp.supplier.edit(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})):(console.log("新增1"),this.api.erp.supplier.add(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)}))},cancel:function(){}}}},2084:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("Row",[o("i-col",[o("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:e.formQuery,inline:""}},[o("Form-item",{attrs:{prop:"bsCode"}},[o("Input",{attrs:{type:"text",placeholder:"供应商编码"},model:{value:e.formQuery.bsCode,callback:function(t){e.$set(e.formQuery,"bsCode",t)},expression:"formQuery.bsCode"}})],1),e._v(" "),o("Form-item",{attrs:{prop:"bsName"}},[o("Input",{attrs:{type:"text",placeholder:"供应商名称"},model:{value:e.formQuery.bsName,callback:function(t){e.$set(e.formQuery,"bsName",t)},expression:"formQuery.bsName"}})],1),e._v(" "),o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(t){e.handleSubmit("formQuery")}}},[e._v("查 询")])],1),e._v(" "),e._e(),e._v(" "),o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(t){e.download()}}},[e._v("导 入")])],1)],1)],1)],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"24"}},[o("Table",{attrs:{data:e.datagrid.data.rows,columns:e.datagrid.columns,stripe:"",height:"500"}}),e._v(" "),o("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[o("div",{staticStyle:{float:"right"}},[o("Page",{attrs:{total:e.datagrid.data.total,current:1,"page-size":e.datagrid.queryParams.rows,"page-size-opts":e.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage}})],1)])],1)],1),e._v(" "),o("Modal",{attrs:{title:"",width:"800"},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.dialog.modal_dialog,callback:function(t){e.$set(e.dialog,"modal_dialog",t)},expression:"dialog.modal_dialog"}},[o("p",[o("Form",{ref:e.dialog.ruleForm,attrs:{model:e.dialog.formItem,rules:e.dialog.ruleForm,"label-width":100}},[o("span",{staticStyle:{display:"none"}},[o("Input",{model:{value:e.dialog.formItem.id,callback:function(t){e.$set(e.dialog.formItem,"id",t)},expression:"dialog.formItem.id"}}),e._v(" "),o("Input",{attrs:{value:"-1"},model:{value:e.dialog.formItem.pkGroup,callback:function(t){e.$set(e.dialog.formItem,"pkGroup",t)},expression:"dialog.formItem.pkGroup"}}),e._v(" "),o("Input",{attrs:{value:"-1"},model:{value:e.dialog.formItem.pkOrg,callback:function(t){e.$set(e.dialog.formItem,"pkOrg",t)},expression:"dialog.formItem.pkOrg"}})],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"组织ID",prop:"bsOrganizationId"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsOrganizationId,callback:function(t){e.$set(e.dialog.formItem,"bsOrganizationId",t)},expression:"dialog.formItem.bsOrganizationId"}})],1)],1),e._v(" "),o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"供应商ID",prop:"bsVendorId"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsVendorId,callback:function(t){e.$set(e.dialog.formItem,"bsVendorId",t)},expression:"dialog.formItem.bsVendorId"}})],1)],1)],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"供应商编码",prop:"bsCode"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCode,callback:function(t){e.$set(e.dialog.formItem,"bsCode",t)},expression:"dialog.formItem.bsCode"}})],1)],1),e._v(" "),o("i-col",{attrs:{span:"12"}},[o("Form-item",{attrs:{label:"供应商名称",prop:"bsName"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsName,callback:function(t){e.$set(e.dialog.formItem,"bsName",t)},expression:"dialog.formItem.bsName"}})],1)],1)],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"24"}},[o("Form-item",{attrs:{label:"地址",prop:"bsAddressLine1"}},[o("Input",{attrs:{type:"textarea",placeholder:"请输入..."},model:{value:e.dialog.formItem.bsAddressLine1,callback:function(t){e.$set(e.dialog.formItem,"bsAddressLine1",t)},expression:"dialog.formItem.bsAddressLine1"}})],1)],1)],1)],1)],1)])],1)},r=[];a._withStripped=!0;var i={render:a,staticRenderFns:r};t.default=i}});