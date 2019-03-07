webpackJsonp([39],{1031:function(e,t,a){"use strict";function i(e){d||a(1763)}Object.defineProperty(t,"__esModule",{value:!0});var n=a(1592),o=a.n(n);for(var r in n)"default"!==r&&function(e){a.d(t,e,function(){return n[e]})}(r);var s=a(1765),l=a.n(s),d=!1,m=a(1),u=i,p=m(o.a,l.a,!1,u,null,null);p.options.__file="src\\view\\settings\\ApprovedTermsManagement.vue",t.default=p.exports},1592:function(e,t,a){"use strict";function i(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var n=a(11),o=i(n),r=a(15),s=i(r);t.default={data:function(){var e=this;return{readonly:!0,formQuery:{bsNo:""},isReadOnly:!0,isdisabled:!0,dialog:{modal_dialog:!1,formItem:{},ruleForm:{bsNo:[{required:!0,message:"请填写编码",trigger:"blur"}],bsName:[{required:!0,message:"请填写名称",trigger:"blur"}],bsType:[{required:!0,message:"请填写条款类型",trigger:"blur"}],bsContent:[{required:!0,message:"请填写条款内容",trigger:"blur"}],bsStandard:[{required:!0,message:"请填写评分标准",trigger:"blur"}]}},datagrid:{queryParams:{page:1,rows:25,pkParent:-1},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:this.$t("New-audit.Numbering"),key:"bsNo",width:80,className:"Artnumber"},{title:this.$t("New-audit.Terms-name"),key:"bsName",width:130},{title:this.$t("New-audit.Terms-type"),key:"bsType",width:100,render:function(t,a){return t("span",e.$t("approved.auditType["+a.row.bsType+"]"))}},{title:this.$t("New-audit.Terms-conditions"),key:"bsContent",width:300,render:function(e,t){var a=t.row.bsContent;return a.length>200&&(a=a.substring(0,200),a+="...."),e("span",a)}},{title:this.$t("New-audit.Grading"),key:"bsStandard",width:200,render:function(e,t){var a=t.row.bsStandard;return a.length>200&&(a=a.substring(0,200),a+="...."),e("span",a)}},{title:this.$t("New-audit.Remarks"),key:"bsRemark",width:100},{title:this.$t("New-audit.Operating"),key:"action",render:function(t,a){var i=[];return i.push(t("Button",{props:{type:"primary",size:"small",disabled:e.$Util.hasPerm("EDIT")},style:{marginRight:"5px"},on:{click:function(){e.showEditDialog(a)}}},e.$t("Button.Edit"))),i.push(t("Button",{props:{type:"primary",size:"small",disabled:e.$Util.hasPerm("QUERY")},style:{marginRight:"5px"},on:{click:function(){e.show(a)}}},e.$t("Button.View"))),i.push(t("Button",{props:{type:"error",size:"small",disabled:e.$Util.hasPerm("DELETE")},on:{click:function(){e.delete(a)}}},e.$t("Button.Delete"))),t("div",i)}}]}}},created:function(){this.getData()},methods:{handleSubmit:function(e){this.getData()},getData:function(){var e=this;(0,s.default)(this.formQuery,this.datagrid.queryParams),this.api.Audit.getlist(this.formQuery).then(function(t){e.datagrid.data=t.data})},changePage:function(e){this.datagrid.queryParams.page=e,this.getData()},delete:function(e){var t=this;this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",onOk:function(){t.api.Audit.delete({id:e.row.id}).then(function(e){console.log(e),e.result?(t.reloadData(),t.$Modal.remove(),t.$Message.info("删除成功")):t.$Message.error(e.msg)})}})},showAddDialog:function(){this.isReadOnly=!1,this.dialog.modal_dialog=!0,this.isdisabled=!1,this.dialog.formItem={}},showEditDialog:function(e){this.isReadOnly=!1,this.isdisabled=!1,this.dialog.modal_dialog=!0;var t=e.row;this.dialog.formItem={id:t.id,bsNo:t.bsNo,bsName:t.bsName,bsType:t.bsType,bsContent:t.bsContent,bsStandard:t.bsStandard,bsRemark:t.bsRemark}},show:function(e){var t=e.row;this.dialog.formItem=t,this.isReadOnly=!0,this.isdisabled=!0,this.dialog.modal_dialog=!0,this.dialog.formItem={id:t.id,bsNo:t.bsNo,bsName:t.bsName,bsType:t.bsType,bsContent:t.bsContent,bsStandard:t.bsStandard,bsRemark:t.bsRemark}},ok:function(){var e=this;this.$refs["dialog.formItem"].validate(function(t){t?void 0!=(0,o.default)(e.dialog.formItem.id)&&"number"==typeof e.dialog.formItem.id?e.api.Audit.edit(e.dialog.formItem).then(function(t){t.result?e.getData():e.$Message.error(t.msg)}):e.api.Audit.add(e.dialog.formItem).then(function(t){t.result?e.getData():e.$Message.error(t.msg)}):e.$Message.info("信息填写不完整")})},cancel:function(){},mydefineRow:function(){return"bscont"}}}},1763:function(e,t,a){var i=a(1764);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);a(28)("f5ca8c82",i,!1,{})},1764:function(e,t,a){t=e.exports=a(27)(!1),t.push([e.i,'\n.ApprovedFlowManagement .ivu-table {\n  font-size: 14px;\n}\n.ApprovedTermsManagement .ivu-table-cell {\n  font-size: 14px;\n}\n.SampleManagement .ivu-table {\n  font-size: 14px;\n}\n.SampleRegularManagement .ivu-table {\n  font-size: 14px;\n}\n.el-input {\n  width: 69%;\n}\n.el-textarea__inner {\n  width: 85%;\n}\n.el-form-item {\n  margin-bottom: 14px;\n}\n.ivu-input {\n  margin-top: 12px;\n  display: inline-block;\n  height: 40px;\n  padding: 11px 10px;\n  font-size: 12px;\n  border: 1px solid #dddee1;\n  color: #495060;\n  background-color: #fff;\n  background-image: none;\n  position: relative;\n  cursor: text;\n  /*margin-top:16px;*/\n  transition: border 0.2s ease-in-out, background 0.2s ease-in-out, box-shadow 0.2s ease-in-out;\n}\n.addform {\n  width: 480px;\n}\n.addform .el-input {\n  width: 300px;\n}\n.addform .el-textarea {\n  width: 350px;\n}\n.ivu-table td.bscont {\n  overflow: hidden;\n  text-overflow: ellipsis;\n  display: -webkit-box;\n  -webkit-line-clamp: 3;\n  height: 100px;\n}\n.ivu-table .bscont td {\n  font-size: 12px;\n  font-family: "Microsoft YaHei";\n  line-height: 24px;\n}\n.ivu-table td.Artnumber {\n  font-size: 16px;\n  font-family: "Arial";\n  text-align: center;\n}\n.searchbtn .el-button {\n  border-radius: 0px;\n  padding: 11px 14px;\n}\n/*.el-form-item__content{display: inline-block;}*/\n',""])},1765:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"ApprovedTermsManagement"},[a("el-Row",{staticStyle:{"margin-bottom":"10px"}},[a("el-input",{staticStyle:{width:"200px",display:"inline-block"},attrs:{size:"medium",placeholder:e.$t("Button.Keyword-search")},model:{value:e.formQuery.keyWord,callback:function(t){e.$set(e.formQuery,"keyWord",t)},expression:"formQuery.keyWord"}}),e._v(" "),a("el-button",{staticStyle:{padding:"10px 10px"},attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(t){e.handleSubmit("formQuery")}}},[e._v("\n      \t\t"+e._s(e.$t("Button.Inquire"))+"\n      \t")]),e._v(" "),a("el-button",{directives:[{name:"perm-add",rawName:"v-perm-add"}],staticStyle:{padding:"10px 10px"},attrs:{type:"primary",icon:"el-icon-plus"},on:{click:function(t){e.showAddDialog()}}},[e._v("\n      \t\t"+e._s(e.$t("Button.Addedterms"))+"\n      \t")])],1),e._v(" "),a("el-Row",[a("el-col",{attrs:{span:24}},[a("i-table",{staticStyle:{"table-layout":"fixed"},attrs:{border:"",data:e.datagrid.data.rows,columns:e.datagrid.columns,stripe:"","row-class-name":e.mydefineRow}}),e._v(" "),a("div",{staticStyle:{margin:"10px"}},[a("div",{staticStyle:{float:"right"}},[a("Page",{attrs:{total:e.datagrid.data.total,current:1,"page-size":e.datagrid.queryParams.rows,"page-size-opts":e.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage}})],1)])],1)],1),e._v(" "),a("Modal",{attrs:{title:e.$t("New-audit.New-terms")},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.dialog.modal_dialog,callback:function(t){e.$set(e.dialog,"modal_dialog",t)},expression:"dialog.modal_dialog"}},[a("p",[a("el-form",{ref:"dialog.formItem",staticClass:"addform",attrs:{model:e.dialog.formItem,rules:e.dialog.ruleForm,"label-width":"80"}},[a("el-form-item",{attrs:{label:e.$t("New-audit.Article-number"),prop:"bsNo"}},[a("el-input",{attrs:{placeholder:"请输入",readonly:e.isReadOnly},model:{value:e.dialog.formItem.bsNo,callback:function(t){e.$set(e.dialog.formItem,"bsNo",t)},expression:"dialog.formItem.bsNo"}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("New-audit.Terms-name"),prop:"bsName"}},[a("el-input",{attrs:{placeholder:"请输入",readonly:e.isReadOnly},model:{value:e.dialog.formItem.bsName,callback:function(t){e.$set(e.dialog.formItem,"bsName",t)},expression:"dialog.formItem.bsName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("New-audit.Terms-type"),prop:"bsType"}},[a("el-select",{attrs:{disabled:e.isdisabled,placeholder:"请输入"},model:{value:e.dialog.formItem.bsType,callback:function(t){e.$set(e.dialog.formItem,"bsType",t)},expression:"dialog.formItem.bsType"}},[a("el-option",{attrs:{label:e.$t("approved.auditType[1]"),value:1}}),e._v(" "),a("el-option",{attrs:{label:e.$t("approved.auditType[2]"),value:2}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("New-audit.Terms-conditions"),prop:"bsContent"}},[a("el-input",{attrs:{type:"textarea",placeholder:"请输入...",readonly:e.isReadOnly},model:{value:e.dialog.formItem.bsContent,callback:function(t){e.$set(e.dialog.formItem,"bsContent",t)},expression:"dialog.formItem.bsContent"}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("New-audit.Grading"),prop:"bsStandard"}},[a("el-input",{attrs:{autosize:{minRows:5},type:"textarea",placeholder:"请输入...",readonly:e.isReadOnly},model:{value:e.dialog.formItem.bsStandard,callback:function(t){e.$set(e.dialog.formItem,"bsStandard",t)},expression:"dialog.formItem.bsStandard"}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("New-audit.Remarks"),prop:"bsRemark"}},[e._v("  \n                    "),a("el-input",{attrs:{type:"textarea",placeholder:"请输入...",readonly:e.isReadOnly},model:{value:e.dialog.formItem.bsRemark,callback:function(t){e.$set(e.dialog.formItem,"bsRemark",t)},expression:"dialog.formItem.bsRemark"}})],1)],1)],1)])],1)},n=[];i._withStripped=!0;var o={render:i,staticRenderFns:n};t.default=o}});