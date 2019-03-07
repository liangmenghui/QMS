webpackJsonp([51],{1236:function(t,e,a){"use strict";function i(t){m||a(2239)}Object.defineProperty(e,"__esModule",{value:!0});var o=a(1977),l=a.n(o);for(var s in o)"default"!==s&&function(t){a.d(e,t,function(){return o[t]})}(s);var r=a(2241),n=a.n(r),m=!1,d=a(1),p=i,u=d(l.a,n.a,!1,p,null,null);u.options.__file="src/views/qms/view/product/SampleRegularManagement.vue",e.default=u.exports},1977:function(t,e,a){"use strict";function i(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var o=a(22),l=i(o),s=a(11),r=i(s);e.default={data:function(){var t=this;return{seen:!1,productData:{},formQuery:{bsName:""},dialog:{modal_dialog:!1,formItem:{},ruleForm:{bsName:[{required:!0,message:"请填写名称",trigger:"blur"}]}},datagrid:{data:{rows:[],total:0},columns:[{title:this.$t("sample.RegularName"),key:"bsName"},{title:this.$t("sample.upLimit"),key:"bsUpLimit"},{title:this.$t("sample.lowLimit"),key:"bsLowLimit"},{title:this.$t("sample.Tool"),key:"bsTool"},{title:this.$t("sample.Unit"),key:"bsUnit"},{title:this.$t("sample.Remark"),key:"bsRemark"},{title:"操作",key:"action",render:function(e,a){var i=[];return i.push(e("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.showEditDialog(a)}}},"设置")),i.push(e("Button",{props:{type:"error",size:"small"},on:{click:function(){t.delete(a)}}},"删除")),e("div",i)}}]}}},created:function(){this.productData=this.$store.getters.getProductData,this.getData()},methods:{handleSubmit:function(t){this.getData()},getData:function(){var t=this;(0,r.default)(this.formQuery,this.datagrid.queryParams),this.api.sampleRegular.getlist({bsPrId:this.productData.id}).then(function(e){e.result?t.datagrid.data=e.data:t.$Message.error(e.msg)})},changePage:function(t){this.datagrid.queryParams.page=t,this.datagrid.data=this.getData()},edit:function(t){var e=this;this.api.sampleRegular.edit(t.row).then(function(t){t.result?e.getData():e.$Message.error(t.msg)})},delete:function(t){var e=this;this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",onOk:function(){e.api.sampleRegular.delete({id:t.row.id}).then(function(t){t.result?(e.getData(),e.$Modal.remove(),e.$Message.info("删除成功")):e.$Message.error(t.msg)})}})},showAddDialog:function(){this.dialog.modal_dialog=!0,this.dialog.formItem={bsPrId:this.productData.id}},showEditDialog:function(t){this.dialog.modal_dialog=!0,this.dialog.formItem={bsPrId:this.productData.id,id:t.row.id,bsName:t.row.bsName,bsUnit:t.row.bsUnit,bsTool:t.row.bsTool,bsUpLimit:t.row.bsUpLimit,bsLowLimit:t.row.bsLowLimit}},ok:function(){var t=this;void 0!=(0,l.default)(this.dialog.formItem.id)&&"number"==typeof this.dialog.formItem.id?this.api.sampleRegular.edit(this.dialog.formItem).then(function(e){t.getData()}):this.api.sampleRegular.add(this.dialog.formItem).then(function(e){t.getData()})},cancel:function(){}}}},2239:function(t,e,a){var i=a(2240);"string"==typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);a(19)("7b4e37f4",i,!1)},2240:function(t,e,a){e=t.exports=a(18)(!1),e.push([t.i,"\n.ApprovedFlowManagement .ivu-table {\n  font-size: 14px;\n}\n.ApprovedTermsManagement .ivu-table-cell {\n  font-size: 14px;\n}\n.SupplierList .el-table {\n  font-size: 14px;\n}\n.ProductList .el-table {\n  font-size: 14px;\n}\n.SampleManagement .ivu-table {\n  font-size: 14px;\n}\n.SampleRegularManagement .ivu-table {\n  font-size: 14px;\n}\n.feedbacklist .el-table {\n  font-size: 14px;\n}\n",""])},2241:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"SampleRegularManagement"},[a("div",[a("Table",{attrs:{data:t.datagrid.data.rows,columns:t.datagrid.columns}})],1),t._v(" "),a("div",{staticStyle:{"margin-top":"50px","margin-bottom":"100px"}},[a("center",[a("el-button",{staticStyle:{padding:"10px 10px"},attrs:{type:"primary",icon:"el-icon-plus"},on:{click:function(e){t.showAddDialog()}}},[t._v(t._s(t.$t("sample.NewSampleRegular"))+"\n            ")])],1)],1),t._v(" "),a("Modal",{attrs:{title:t.$t("sample.NewSampleRegular")},on:{"on-ok":t.ok,"on-cancel":t.cancel},model:{value:t.dialog.modal_dialog,callback:function(e){t.$set(t.dialog,"modal_dialog",e)},expression:"dialog.modal_dialog"}},[a("el-form",{ref:t.dialog.formItem,attrs:{model:t.dialog.formItem,rules:t.dialog.ruleForm,"label-width":"80"}},[a("el-form-item",{attrs:{label:t.$t("sample.RegularName"),prop:"bsName"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:t.dialog.formItem.bsName,callback:function(e){t.$set(t.dialog.formItem,"bsName",e)},expression:"dialog.formItem.bsName"}})],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("sample.upLimit"),prop:"bsUpLimit"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:t.dialog.formItem.bsUpLimit,callback:function(e){t.$set(t.dialog.formItem,"bsUpLimit",e)},expression:"dialog.formItem.bsUpLimit"}})],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("sample.lowLimit"),prop:"bsLowLimit"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:t.dialog.formItem.bsLowLimit,callback:function(e){t.$set(t.dialog.formItem,"bsLowLimit",e)},expression:"dialog.formItem.bsLowLimit"}})],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("sample.Tool"),prop:"bsTool"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:t.dialog.formItem.bsTool,callback:function(e){t.$set(t.dialog.formItem,"bsTool",e)},expression:"dialog.formItem.bsTool"}})],1),t._v(" "),a("el-form-item",{attrs:{label:t.$t("sample.Unit"),prop:"bsUnit"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:t.dialog.formItem.bsUnit,callback:function(e){t.$set(t.dialog.formItem,"bsUnit",e)},expression:"dialog.formItem.bsUnit"}})],1),t._v(" "),a("el-form-item",{attrs:{prop:"bsSampleId"}},[t.seen?a("el-input",{model:{value:t.dialog.formItem.bsSampleId,callback:function(e){t.$set(t.dialog.formItem,"bsSampleId",e)},expression:"dialog.formItem.bsSampleId"}}):t._e()],1)],1)],1)],1)},o=[];i._withStripped=!0;var l={render:i,staticRenderFns:o};e.default=l}});