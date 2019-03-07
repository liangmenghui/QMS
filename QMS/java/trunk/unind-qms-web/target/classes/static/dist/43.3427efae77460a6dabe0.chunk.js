webpackJsonp([43],{1048:function(e,t,a){"use strict";function o(e){d||a(1859)}Object.defineProperty(t,"__esModule",{value:!0});var i=a(1627),r=a.n(i);for(var n in i)"default"!==n&&function(e){a.d(t,e,function(){return i[e]})}(n);var s=a(1861),l=a.n(s),d=!1,m=a(1),u=o,c=m(r.a,l.a,!1,u,null,null);c.options.__file="src\\view\\product\\SampleManagement.vue",t.default=c.exports},1627:function(e,t,a){"use strict";function o(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var i=a(11),r=o(i),n=a(15),s=o(n);t.default={data:function(){var e=this;return{showRooterView:!1,formQuery:{bsName:""},dialog:{modal_dialog:!1,formItem:{},ruleForm:{bsName:[{required:!0,message:"请填写名称",trigger:"blur"}]}},datagrid:{queryParams:{page:1,rows:25,pkParent:-1},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:this.$t("sample.SampleName"),key:"bsName"},{title:this.$t("sample.Creater"),key:"bsCreater"},{title:this.$t("sample.CreateTime"),key:"bsCreatedTime"},{title:"操作",key:"action",render:function(t,a){var o=[];return o.push(t("Button",{props:{type:"primary",size:"small",disabled:!e.$Util.hasPerm("EDIT")},style:{marginRight:"5px"},on:{click:function(){e.showEditDialog(a)}}},"设置")),o.push(t("Button",{props:{type:"error",size:"small",disabled:!e.$Util.hasPerm("DELETE")},on:{click:function(){e.delete(a)}}},"删除")),t("div",o)}}]}}},created:function(){this.getData(),this.showRooterView=this.$route.matched.length>3},beforeUpdate:function(){this.showRooterView=this.$route.matched.length>3},watch:{$route:function(e,t){2==e.matched.length&&this.getData(),this.showRooterView=e.matched.length>2}},methods:{handleSubmit:function(e){this.getData()},getData:function(){var e=this;(0,s.default)(this.formQuery,this.datagrid.queryParams),this.api.sampleinfo.getlist(this.formQuery).then(function(t){t.result?e.datagrid.data=t.data:e.$Message.error(t.msg)})},reloadData:function(){this.getData()},changePage:function(e){this.datagrid.queryParams.page=e,this.getData()},edit:function(e){var t=this;this.api.sampleinfo.edit(e.row).then(function(e){e.result?t.reloadData():t.$Message.error(e.msg)})},delete:function(e){var t=this;this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",onOk:function(){t.api.sampleinfo.delete({id:e.row.id}).then(function(e){e.result?(t.reloadData(),t.$Modal.remove(),t.$Message.info("删除成功")):t.$Message.error(e.msg)})}})},showAddDialog:function(){this.dialog.modal_dialog=!0,this.dialog.formItem={}},showEditDialog:function(e){var t=e.row;t.bsSampleId=t.id,this.$store.commit("updateSampleDataStates",t),this.$router.push("sampleManagement/regularManagement")},ok:function(){var e=this;void 0!=(0,r.default)(this.dialog.formItem.id)&&"number"==typeof this.dialog.formItem.id?this.api.sampleinfo.edit(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)}):this.api.sampleinfo.add(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})},cancel:function(){}}}},1859:function(e,t,a){var o=a(1860);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(28)("d2582502",o,!1,{})},1860:function(e,t,a){t=e.exports=a(27)(!1),t.push([e.i,"\n.ApprovedFlowManagement .ivu-table {\n  font-size: 14px;\n}\n.ApprovedTermsManagement .ivu-table-cell {\n  font-size: 14px;\n}\n.SampleManagement .ivu-table {\n  font-size: 14px;\n}\n.SampleRegularManagement .ivu-table {\n  font-size: 14px;\n}\n",""])},1861:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return e.showRooterView?e.showRooterView?a("div",[a("router-view")],1):e._e():a("div",{staticClass:"SampleManagement"},[a("el-Row",{staticStyle:{"margin-bottom":"10px"}},[a("el-input",{staticStyle:{width:"200px",display:"inline-block"},attrs:{size:"medium",placeholder:e.$t("Button.Keyword-search")},model:{value:e.formQuery.keyWord,callback:function(t){e.$set(e.formQuery,"keyWord",t)},expression:"formQuery.keyWord"}}),e._v(" "),a("el-button",{staticStyle:{padding:"10px 10px"},attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.getData}},[e._v(e._s(e.$t("Button.Inquire")))]),e._v(" "),a("el-button",{directives:[{name:"perm-add",rawName:"v-perm-add"}],staticStyle:{padding:"10px 10px"},attrs:{type:"primary",icon:"el-icon-plus"},on:{click:function(t){e.showAddDialog()}}},[e._v(e._s(e.$t("sample.NewSample")))])],1),e._v(" "),a("el-Row",[a("Table",{attrs:{data:e.datagrid.data.rows,columns:e.datagrid.columns}}),e._v(" "),a("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[a("div",{staticStyle:{float:"right"}},[a("Page",{attrs:{total:e.datagrid.data.total,current:1,"page-size":e.datagrid.queryParams.rows,"page-size-opts":e.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage}})],1)])],1),e._v(" "),a("Modal",{attrs:{title:e.$t("sample.NewSample")},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.dialog.modal_dialog,callback:function(t){e.$set(e.dialog,"modal_dialog",t)},expression:"dialog.modal_dialog"}},[a("el-form",{ref:e.dialog.formItem,attrs:{model:e.dialog.formItem,rules:e.dialog.ruleForm,"label-width":"80"}},[a("el-form-item",{attrs:{label:e.$t("sample.SampleName"),prop:"bsName"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsName,callback:function(t){e.$set(e.dialog.formItem,"bsName",t)},expression:"dialog.formItem.bsName"}})],1)],1)],1)],1)},i=[];o._withStripped=!0;var r={render:o,staticRenderFns:i};t.default=r}});