webpackJsonp([82],{1194:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=o(1911),i=o.n(a);for(var r in a)"default"!==r&&function(t){o.d(e,t,function(){return a[t]})}(r);var l=o(2092),s=o.n(l),n=o(1),d=n(i.a,s.a,!1,null,null,null);d.options.__file="src/view/member/ext/customfield/index.vue",e.default=d.exports},1911:function(t,e,o){"use strict";function a(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var i=o(14),r=a(i),l=o(22),s=a(l),n=o(11),d=a(n),m=o(72),u=a(m),c=o(98);e.default={data:function(){var t=this;return{formQuery:{bsCode:"",bsName:""},dialog:{refs:{cataloglist:[]},modal_dialog:!1,formItem:{},ruleForm:{bsName:[{required:!0,message:"请填写字段名称",trigger:"blur"}],bsLabel:[{required:!0,message:"请填写标签名称",trigger:"blur"}]}},datagrid:{queryParams:{page:1,rows:25,pkParent:-1},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:"ID",key:"id"},{title:"所属目录",key:"bsCatelog"},{title:"字段名称",key:"bsName",align:"center",render:function(e,o){return e("Button",{props:{type:"text",size:"small"},on:{click:function(){var e={id:o.row.id};t.$router.push({name:"XTGL_KZHSXZ",query:e})}}},o.row.bsName)}},{title:"标签名称",key:"bsLabel"},{title:"顺序号",key:"bsSortNo"},{title:"查看数据",key:"show_more",align:"center",render:function(e,o){return e("Button",{props:{type:"text",size:"small"},on:{click:function(){var e={id:o.row.id};t.$router.push({name:"customfieldvalue",query:e})}}},"查看")}},{title:"操作",key:"action",render:function(e,o){var a=[];return a.push(e("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.showEditDialog(o)}}},"编辑")),a.push(e("Button",{props:{type:"error",size:"small"},on:{click:function(){t.delete(o)}}},"删除")),e("div",a)}}]}}},created:function(){this.getcataloglist()},computed:(0,u.default)({},(0,c.mapState)({menuData:function(t){return t.menuData}})),mounted:function(){this.init()},watch:{$route:function(){this.init()}},methods:{getcataloglist:function(){var t=this;this.api.admin.custom.getlist({}).then(function(e){e.result&&(console.log(e.data),t.dialog.refs.cataloglist=e.data.rows)})},init:function(){console.log(this.$route.query.bsCode),this.formQuery.id=this.$route.query.bsCode,this.getData()},handleSubmit:function(t){this.reloadData()},getData:function(){var t=this;(0,d.default)(this.formQuery,this.datagrid.queryParams),console.log("after===>"),console.log(this.formQuery),this.api.admin.customfield.getlist(this.formQuery).then(function(e){console.log("get list===>"),console.log(e.data),e.result?t.datagrid.data=e.data:t.$Message.error(e.msg)})},reloadData:function(){this.datagrid.data=this.getData()},changePage:function(t){this.datagrid.queryParams.page=t,this.datagrid.data=this.getData()},edit:function(t){var e=this;console.log(t),this.api.admin.customfield.edit(t.row).then(function(t){console.log(t),t.result?e.reloadData():e.$Message.error(t.msg)})},delete:function(t){var e=this;console.log(t),this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){e.api.admin.customfield.delete({id:t.row.id}).then(function(t){console.log(t),t.result?(e.reloadData(),e.$Message.info("删除成功"),e.$Modal.remove()):e.$Message.error(t.msg)})}})},showAddDialog:function(){this.dialog.modal_dialog=!0,this.dialog.formItem={}},showEditDialog:function(t){this.dialog.modal_dialog=!0;var e=t.row;this.dialog.formItem={id:e.id,bsCatelog:e.bsCatelog,bsName:e.bsName,bsLabel:e.bsLabel,bsSortNo:e.bsSortNo}},ok:function(){var t=this;console.log("this.dialog.formItem====>"+(0,s.default)(this.dialog.formItem.id)),void 0!=(0,s.default)(this.dialog.formItem.id)&&"string"==typeof this.dialog.formItem.id?(console.log("编辑1"),console.log((0,r.default)(this.dialog.formItem)),this.api.admin.customfield.edit(this.dialog.formItem).then(function(e){e.result?t.reloadData():t.$Message.error(e.msg)})):(console.log("新增1"),this.api.admin.customfield.add(this.dialog.formItem).then(function(e){e.result?t.reloadData():t.$Message.error(e.msg)}))},cancel:function(){}}}},2092:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",[o("Row",[o("i-col",[o("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:t.formQuery,inline:""}},[o("Form-item",{attrs:{prop:"bsName"}},[o("Input",{attrs:{placeholder:"字段名称"},model:{value:t.formQuery.bsName,callback:function(e){t.$set(t.formQuery,"bsName",e)},expression:"formQuery.bsName"}})],1),t._v(" "),o("Form-item",{attrs:{prop:"bsLabel"}},[o("Input",{attrs:{placeholder:"标签名称"},model:{value:t.formQuery.bsLabel,callback:function(e){t.$set(t.formQuery,"bsLabel",e)},expression:"formQuery.bsLabel"}})],1),t._v(" "),o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(e){t.handleSubmit("formQuery")}}},[t._v("查 询")])],1),t._v(" "),o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(e){t.showAddDialog()}}},[t._v("新 增")])],1)],1)],1)],1),t._v(" "),o("Row",[o("i-col",{attrs:{span:"24"}},[o("Table",{staticStyle:{height:"500px"},attrs:{data:t.datagrid.data.rows,columns:t.datagrid.columns,stripe:""}}),t._v(" "),o("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[o("div",{staticStyle:{float:"right"}},[o("Page",{attrs:{total:t.datagrid.data.total,current:1,"page-size":t.datagrid.queryParams.rows,"page-size-opts":t.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":t.changePage}})],1)])],1)],1),t._v(" "),o("Modal",{attrs:{title:""},on:{"on-ok":t.ok,"on-cancel":t.cancel},model:{value:t.dialog.modal_dialog,callback:function(e){t.$set(t.dialog,"modal_dialog",e)},expression:"dialog.modal_dialog"}},[o("p",[o("Form",{ref:t.dialog.ruleForm,attrs:{model:t.dialog.formItem,rules:t.dialog.ruleForm,"label-width":80}},[o("span",{staticStyle:{display:"none"}},[o("Input",{model:{value:t.dialog.formItem.id,callback:function(e){t.$set(t.dialog.formItem,"id",e)},expression:"dialog.formItem.id"}})],1),t._v(" "),o("Form-item",{attrs:{label:"所属目录",prop:"bsCatelog"}},[o("Select",{attrs:{placeholder:"请输入"},model:{value:t.dialog.formItem.bsCatelog,callback:function(e){t.$set(t.dialog.formItem,"bsCatelog",e)},expression:"dialog.formItem.bsCatelog"}},t._l(t.dialog.refs.cataloglist,function(e){return o("Option",{key:e.bsCode,attrs:{value:e.bsCode}},[t._v(t._s(e.bsCode)+" - "+t._s(e.bsComment))])}))],1),t._v(" "),o("Form-item",{attrs:{label:"字段名称",prop:"bsName"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:t.dialog.formItem.bsName,callback:function(e){t.$set(t.dialog.formItem,"bsName",e)},expression:"dialog.formItem.bsName"}})],1),t._v(" "),o("Form-item",{attrs:{label:"字段标签",prop:"bsLabel"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:t.dialog.formItem.bsLabel,callback:function(e){t.$set(t.dialog.formItem,"bsLabel",e)},expression:"dialog.formItem.bsLabel"}})],1),t._v(" "),o("Form-item",{attrs:{label:"顺序号",prop:"bsSortNo"}},[o("InputNumber",{staticStyle:{width:"100%"},attrs:{placeholder:"请输入",value:"1"},model:{value:t.dialog.formItem.bsSortNo,callback:function(e){t.$set(t.dialog.formItem,"bsSortNo",e)},expression:"dialog.formItem.bsSortNo"}})],1)],1)],1)])],1)},i=[];a._withStripped=!0;var r={render:a,staticRenderFns:i};e.default=r}});