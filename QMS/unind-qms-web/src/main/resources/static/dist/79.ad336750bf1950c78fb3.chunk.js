webpackJsonp([79],{1203:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a(1921),r=a.n(o);for(var s in o)"default"!==s&&function(t){a.d(e,t,function(){return o[t]})}(s);var i=a(2107),n=a.n(i),l=a(1),m=l(r.a,n.a,!1,null,null,null);m.options.__file="src/view/member/lab/equipment/equipment.vue",e.default=m.exports},1921:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a(72),r=function(t){return t&&t.__esModule?t:{default:t}}(o),s=a(98);e.default={created:function(){this.getDate()},computed:(0,r.default)({},(0,s.mapState)({menuData:function(t){return t.menuData}})),data:function(){var t=this;return{start:0,end:15,dialog:{ruleForm:{bsCode:[{required:!0,message:"请填写编码",trigger:"blur"}],bsName:[{required:!0,message:"请填写名称",trigger:"blur"}]},modal_dialog:!1,modal_dialog1:!1},message:"新增",dict:{bsStatus:[{value:0,name:"可用"},{value:1,name:"不可用"}]},formQuery:{bsCode:"",bsName:""},isShow:!1,formItem:{id:"",bsName:"",bsCode:"",bsState:0,bsManager:"",bsPosition:""},datagrid:{queryParams:{page:1,rows:15,pkParent:-1},pagination:[15,50,100],data:{rows:[],total:0,rows1:[]},columns:[{title:"设备名称",key:"bsName"},{title:"设备状态",key:"bsState",render:function(e,a){return e("span",t.dict.bsStatus[a.row.bsState].name)}},{title:"创建时间",key:"bsCreatedTime"},{title:"操作",key:"action",render:function(e,a){var o=[];return t.menuData.perms.EDIT&&o.push(e("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.showEditDialog(a)}}},"编辑")),t.menuData.perms.DELETE&&o.push(e("Button",{props:{type:"error",size:"small"},on:{click:function(){t.delete(a)}}},"删除")),e("div",o)}}]}}},methods:{getDate:function(){var t=this;this.api.equipment.list().then(function(e){e.result?(t.datagrid.data.rows1=t.datagrid.data.rows,t.datagrid.data.rows=e.data.rows.slice(t.start,t.end),t.datagrid.data.total=e.data.total):t.$Message.error(e.msg)})},t:function(){var t=this;if(""==this.formItem.bsName)return this.$Message.error("填写信息有误");this.formItem.bsName=this.formItem.bsName.trim(),this.api.equipment.add(this.formItem).then(function(e){e.result?(t.getDate(),t.$Message.success("添加成功")):t.$Message.error("名称已存在，请重新添加")})},showAddDialog:function(){this.dialog.modal_dialog=!0,this.dialog.formItem={}},cancel:function(){},delete:function(t){var e=this;this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){e.api.equipment.delete(t.row).then(function(t){t.result?(e.getDate(),e.$Message.info("删除成功"),e.$Modal.remove()):e.$Message.error("删除失败")})}})},showEditDialog:function(t){this.dialog.modal_dialog1=!0,this.formItem.eqCode=t.row.eqCode,this.formItem.bsName=t.row.bsName,this.formItem.eqNameY=t.row.bsName,this.formItem.eqPosition=t.row.eqPosition,this.formItem.eqManager=t.row.eqManager,this.formItem.bsState=t.row.bsState,this.formItem.id=t.row.id},update:function(t){var e=this;this.api.equipment.update(t).then(function(t){t.result&&(e.getDate(),e.$Message.success("更新成功")),t.result||e.$Message.error("名称已存在，请重新添加")})},handleSubmit:function(){var t=this;this.formQuery.bsName=this.formQuery.bsName.trim(),this.api.equipment.list(this.formQuery).then(function(e){e.result?t.datagrid.data=e.data:t.$Message.error(e.msg)})},changePage:function(t){this.start=(t-1)*this.datagrid.queryParams.rows,this.end=t*this.datagrid.queryParams.rows,this.getDate()},chageSize:function(t){this.datagrid.queryParams.rows=t,this.start=0,this.end=t,this.getDate()}}}},2107:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",[a("Row",[a("i-col",[a("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:t.formQuery,inline:""}},[a("Form-item",[a("Input",{attrs:{placeholder:"请输入设备名称"},model:{value:t.formQuery.bsName,callback:function(e){t.$set(t.formQuery,"bsName",e)},expression:"formQuery.bsName"}})],1),t._v(" "),a("Form-item",[a("Button",{attrs:{type:"primary"},on:{click:function(e){t.handleSubmit("formQuery")}}},[t._v("查 询")])],1),t._v(" "),t.menuData.perms.ADD?a("Form-item",[a("Button",{attrs:{type:"primary"},on:{click:function(e){t.showAddDialog()}}},[t._v("新 增")])],1):t._e()],1)],1)],1)],1),t._v(" "),a("Modal",{attrs:{title:""},on:{"on-ok":t.t,"on-cancel":t.cancel},model:{value:t.dialog.modal_dialog,callback:function(e){t.$set(t.dialog,"modal_dialog",e)},expression:"dialog.modal_dialog"}},[a("p"),a("div",[a("Row",[a("i-col",[a("Form",{ref:t.dialog.ruleForm,staticStyle:{"text-align":"center"},attrs:{model:t.formItem,"label-width":80,rules:t.dialog.ruleForm}},[a("FormItem",{attrs:{label:"设备名称",prop:"bsName"}},[a("Row",[a("Col",{attrs:{span:"12"}},[a("Input",{attrs:{placeholder:"请输入设备名称"},model:{value:t.formItem.bsName,callback:function(e){t.$set(t.formItem,"bsName",e)},expression:"formItem.bsName"}})],1)],1)],1),t._v(" "),a("FormItem",{attrs:{label:"设备状态"}},[a("Row",[a("Col",{attrs:{span:"12"}},[a("Select",{model:{value:t.formItem.bsState,callback:function(e){t.$set(t.formItem,"bsState",e)},expression:"formItem.bsState"}},[a("Option",{attrs:{value:"0"}},[t._v("可用")]),t._v(" "),a("Option",{attrs:{value:"1"}},[t._v("不可用")])],1)],1)],1)],1)],1)],1)],1)],1),t._v(" "),a("p")]),t._v(" "),a("Modal",{attrs:{title:""},on:{"on-ok":function(e){t.update(t.formItem)},"on-cancel":t.cancel},model:{value:t.dialog.modal_dialog1,callback:function(e){t.$set(t.dialog,"modal_dialog1",e)},expression:"dialog.modal_dialog1"}},[a("p"),a("div",[a("Row",[a("i-col",[a("Form",{staticStyle:{"text-align":"center"},attrs:{model:t.formItem,"label-width":80}},[a("FormItem",{attrs:{label:"设备名称"}},[a("Row",[a("Col",{attrs:{span:"12"}},[a("Input",{attrs:{placeholder:"请输入设备名称"},model:{value:t.formItem.bsName,callback:function(e){t.$set(t.formItem,"bsName",e)},expression:"formItem.bsName"}})],1)],1)],1),t._v(" "),a("FormItem",{attrs:{label:"设备状态"}},[a("Row",[a("Col",{attrs:{span:"12"}},[a("Select",{model:{value:t.formItem.bsState,callback:function(e){t.$set(t.formItem,"bsState",e)},expression:"formItem.bsState"}},[a("Option",{attrs:{value:"0"}},[t._v("可用")]),t._v(" "),a("Option",{attrs:{value:"1"}},[t._v("不可用")])],1)],1)],1)],1)],1)],1)],1)],1),t._v(" "),a("p")]),t._v(" "),a("Row",[a("i-col",{attrs:{span:"24"}},[a("Table",{staticStyle:{height:"auto"},attrs:{data:t.datagrid.data.rows,columns:t.datagrid.columns,stripe:""}}),t._v(" "),a("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[a("div",{staticStyle:{float:"right"}},[a("Page",{attrs:{total:t.datagrid.data.total,current:1,"page-size":t.datagrid.queryParams.rows,"page-size-opts":t.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":t.changePage,"on-page-size-change":t.chageSize}})],1)])],1)],1)],1)},r=[];o._withStripped=!0;var s={render:o,staticRenderFns:r};e.default=s}});