webpackJsonp([58],{1006:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a(1563),r=a.n(o);for(var l in o)"default"!==l&&function(e){a.d(t,e,function(){return o[e]})}(l);var i=a(1721),s=a.n(i),d=a(1),n=d(r.a,s.a,!1,null,null,null);n.options.__file="src\\view\\member\\userrole\\index.vue",t.default=n.exports},1563:function(e,t,a){"use strict";function o(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var r=a(23),l=o(r),i=a(11),s=o(i),d=a(15),n=o(d);t.default={data:function(){return{formQuery:{bsCode:"",bsName:""},tree:{data:[{expand:!0,title:"根目录",children:[]}]},dialog:{modal_dialog:!1,formItem:{},ruleForm:{bsCode:[{required:!0,message:"请填写编码",trigger:"blur"}],bsName:[{required:!0,message:"请填写名称",trigger:"blur"}],bsEnableState:[{required:!0,message:"请选择启用状态",trigger:"blur"}]}},datagrid:{queryParams:{page:1,rows:25,pkParent:-1},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:"用户帐号",key:"bsCode"},{title:"用户姓名",key:"bsName"},{title:"创建时间",key:"bsCreatedTime"},{title:"更新时间",key:"bsModifiedTime"}]}}},created:function(){this.getTree()},methods:{handleSubmit:function(e){this.getData()},getData:function(){var e=this;(0,n.default)(this.formQuery,this.datagrid.queryParams),console.log("after===>"),console.log(this.formQuery),this.api.userrole.getuserlist(this.formQuery).then(function(t){console.log("get list===>"),console.log(t.data),t.result?e.datagrid.data=t.data:e.$Message.error(t.msg)})},reloadData:function(){this.datagrid.data=this.getData()},changePage:function(e){this.datagrid.queryParams.page=e,this.datagrid.data=this.getData()},edit:function(e){var t=this;console.log(e),this.api.resrce.edit(e.row).then(function(e){console.log(e),e.result?t.reloadData():t.$Message.error(e.msg)})},delete:function(e){var t=this;console.log(e),this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){t.api.resrce.delete({id:e.row.id}).then(function(e){console.log(e),e.result?(t.getTree(),t.reloadData(),t.$Message.info("删除成功"),t.$Modal.remove()):t.$Message.error(e.msg)})}})},showAddDialog:function(){this.dialog.modal_dialog=!0;var e=this.$refs.tree.getSelectedNodes();0==e.length?this.dialog.formItem={pkParent:-1,parentName:"根目录"}:this.dialog.formItem={pkParent:e[0].id,parentName:e[0].title}},showEditDialog:function(e){this.dialog.modal_dialog=!0;var t=e.row;this.dialog.formItem={id:t.id,bsCode:t.bsCode,bsName:t.bsName,bsUrl:t.bsUrl,bsIconCls:t.bsIconCls,bsSortNo:t.bsSortNo,bsComment:t.bsComment,bsEnableState:t.bsEnableState};var a=this.$refs.tree.getSelectedNodes();0==a.length?(0,n.default)(this.dialog.formItem,{pkParent:-1,parentName:"根目录"}):(0,n.default)(this.dialog.formItem,{pkParent:a[0].id,parentName:a[0].title})},ok:function(){var e=this;console.log("this.dialog.formItem====>"+(0,s.default)(this.dialog.formItem.id)),void 0!=(0,s.default)(this.dialog.formItem.id)&&"number"==typeof this.dialog.formItem.id?(console.log("编辑1"),console.log((0,l.default)(this.dialog.formItem)),this.$refs["dialog.ruleForm"].validate(function(t){t&&e.api.resrce.edit(e.dialog.formItem).then(function(t){t.result?(e.getTree(),e.reloadData()):e.$Message.error(t.msg)})})):(console.log("新增1"),this.$refs["dialog.ruleForm"].validate(function(t){t&&e.api.resrce.add(e.dialog.formItem).then(function(t){t.result?(e.getTree(),e.reloadData()):e.$Message.error(t.msg)})}))},cancel:function(){},getTree:function(){var e=this;this.api.role.gettree({}).then(function(t){t.result&&(console.log(t.data),e.tree.data=t.data)})},selectNode:function(e){console.log("selected:"+(0,l.default)(e)),1!=e[0].bsLevel&&(this.datagrid.queryParams.pkParent=e[0].id,this.datagrid.data=this.getData())},getSelectedNodes:function(){this.dialog.formItem.pkParent=node[0].id,console.log((0,l.default)(this.dialog.formItem))}}}},1721:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Row",[a("i-col",[a("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:e.formQuery,inline:""}},[a("Form-item",{attrs:{prop:"bsCode"}},[a("Input",{attrs:{type:"text",placeholder:"用户帐号"},model:{value:e.formQuery.bsCode,callback:function(t){e.$set(e.formQuery,"bsCode",t)},expression:"formQuery.bsCode"}})],1),e._v(" "),a("Form-item",{attrs:{prop:"bsName"}},[a("Input",{attrs:{type:"text",placeholder:"用户姓名"},model:{value:e.formQuery.bsName,callback:function(t){e.$set(e.formQuery,"bsName",t)},expression:"formQuery.bsName"}})],1),e._v(" "),a("Form-item",[a("Button",{attrs:{type:"primary"},on:{click:function(t){e.handleSubmit("formQuery")}}},[e._v("查 询")])],1),e._v(" "),a("Form-item",[a("Button",{attrs:{type:"primary"},on:{click:function(t){e.showAddDialog()}}},[e._v("新 增")])],1)],1)],1)],1),e._v(" "),a("Row",[a("i-col",{attrs:{span:"4"}},[a("Tree",{ref:"tree",staticClass:"layout-menu-left",staticStyle:{height:"500px"},attrs:{data:e.tree.data},on:{"on-select-change":e.selectNode}})],1),e._v(" "),a("i-col",{attrs:{span:"20"}},[a("Table",{staticStyle:{height:"500px"},attrs:{data:e.datagrid.data.rows,columns:e.datagrid.columns,stripe:""}}),e._v(" "),a("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[a("div",{staticStyle:{float:"right"}},[a("Page",{attrs:{total:e.datagrid.data.total,current:1,"page-size":e.datagrid.queryParams.rows,"page-size-opts":e.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage}})],1)])],1)],1),e._v(" "),a("Modal",{attrs:{title:""},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.dialog.modal_dialog,callback:function(t){e.$set(e.dialog,"modal_dialog",t)},expression:"dialog.modal_dialog"}},[a("p",[a("Form",{ref:e.dialog.ruleForm,attrs:{model:e.dialog.formItem,rules:e.dialog.ruleForm,"label-width":80}},[a("Input",{attrs:{type:"hidden"},model:{value:e.dialog.formItem.id,callback:function(t){e.$set(e.dialog.formItem,"id",t)},expression:"dialog.formItem.id"}}),e._v(" "),a("Input",{attrs:{value:"-1",type:"hidden"},model:{value:e.dialog.formItem.pkGroup,callback:function(t){e.$set(e.dialog.formItem,"pkGroup",t)},expression:"dialog.formItem.pkGroup"}}),e._v(" "),a("Input",{attrs:{value:"-1",type:"hidden"},model:{value:e.dialog.formItem.pkOrg,callback:function(t){e.$set(e.dialog.formItem,"pkOrg",t)},expression:"dialog.formItem.pkOrg"}}),e._v(" "),a("Form-item",{attrs:{label:"帐号",prop:"bsCode"}},[a("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCode,callback:function(t){e.$set(e.dialog.formItem,"bsCode",t)},expression:"dialog.formItem.bsCode"}})],1),e._v(" "),a("Form-item",{attrs:{label:"姓名",prop:"bsName"}},[a("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsName,callback:function(t){e.$set(e.dialog.formItem,"bsName",t)},expression:"dialog.formItem.bsName"}})],1),e._v(" "),a("Form-item",{attrs:{label:"手机号",prop:"bsMobile"}},[a("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsMobile,callback:function(t){e.$set(e.dialog.formItem,"bsMobile",t)},expression:"dialog.formItem.bsMobile"}})],1),e._v(" "),a("Form-item",{attrs:{label:"邮箱地址",prop:"bsEmail"}},[a("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsEmail,callback:function(t){e.$set(e.dialog.formItem,"bsEmail",t)},expression:"dialog.formItem.bsEmail"}})],1),e._v(" "),a("Form-item",{attrs:{label:"备注"}},[a("Input",{attrs:{type:"textarea",placeholder:"请输入..."},model:{value:e.dialog.formItem.bsComment,callback:function(t){e.$set(e.dialog.formItem,"bsComment",t)},expression:"dialog.formItem.bsComment"}})],1)],1)],1)])],1)},r=[];o._withStripped=!0;var l={render:o,staticRenderFns:r};t.default=l}});