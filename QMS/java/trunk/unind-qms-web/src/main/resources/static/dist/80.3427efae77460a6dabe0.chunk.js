webpackJsonp([80],{1028:function(t,e,r){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=r(1589),s=r.n(a);for(var o in a)"default"!==o&&function(t){r.d(e,t,function(){return a[t]})}(o);var i=r(1756),m=r.n(i),l=r(1),n=l(s.a,m.a,!1,null,null,null);n.options.__file="src\\view\\logistics\\infrastruct\\cargo.vue",e.default=n.exports},1589:function(t,e,r){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=r(75),s=function(t){return t&&t.__esModule?t:{default:t}}(a),o=r(120);e.default={created:function(){this.getData()},computed:(0,s.default)({},(0,o.mapState)({menuData:function(t){return t.menuData}})),data:function(){var t=this;return{start:0,end:15,dialog:{modal_dialog:!1,modal_dialog1:!1,ruleForm:{bsPartNumber:[{required:!0,message:"请填写客户料号",trigger:"blur"}],bsItemNumber:[{required:!0,message:"请填写金合联料号",trigger:"blur"}],bsHts:[{required:!0,message:"请填写商品编码",trigger:"blur"}]}},formItem:{id:"",bsPartNumber:"",bsItemNumber:"",bsDescription:"",bsHts:""},formQuery:{bsPartNumber:"",bsItemNumber:""},datagrid:{queryParams:{page:1,rows:15,pkParent:-1},pagination:[15,50,100],data:{rows:[],total:0,rows1:[]},columns:[{title:"客户料号",key:"bsPartNumber"},{title:"金合联料号",key:"bsItemNumber"},{title:"料号描述",key:"bsDescription"},{title:"商品编码",key:"bsHts"},{title:"操作",key:"action",render:function(e,r){var a=[];return a.push(e("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.showEditDialog(r)}}},"编辑")),a.push(e("Button",{props:{type:"error",size:"small"},on:{click:function(){t.delete(r)}}},"删除")),e("div",a)}}]}}},methods:{getData:function(){var t=this;this.api.lmp.cargo.selectlist().then(function(e){e.result?(t.datagrid.data.rows1=t.datagrid.data.rows,t.datagrid.data.rows=e.data.rows.slice(t.start,t.end),t.datagrid.data.total=e.data.total):t.$Message.error(e.msg)})},showAddDialog:function(){this.formItem.id="",this.formItem.bsPartNumber="",this.formItem.bsItemNumber="",this.formItem.bsDescription="",this.formItem.bsHts="",this.dialog.modal_dialog=!0},check:function(){return""==this.formItem.bsPartNumber||null==this.formItem.bsPartNumber?(this.$Message.error("客户料号不能为空"),!1):""==this.formItem.bsItemNumber||null==this.formItem.bsItemNumber?(this.$Message.error("金合联料号不能为空"),!1):""!=this.formItem.bsHts&&null!=this.formItem.bsHts||(this.$Message.error("商品编码不能为空"),!1)},ok:function(){var t=this;if(!this.check())return!1;this.api.lmp.cargo.insertCargo(this.formItem).then(function(e){e.result?(t.getData(),t.$Message.success("添加成功！")):t.$Message.error("添加失败！")})},ok1:function(){var t=this;if(!this.check())return!1;this.api.lmp.cargo.updateCargo(this.formItem).then(function(e){e.result?(t.getData(),t.$Message.success("更新成功！")):t.$Message.error("更新失败！")})},cancel:function(){},delete:function(t){var e=this;console.log(t),this.api.lmp.cargo.deleteCargo(t.row).then(function(t){t.result?(e.getData(),e.$Message.success("删除成功！")):e.$Message.error("删除失败！")})},showEditDialog:function(t){this.formItem.id=t.row.id,this.formItem.bsPartNumber=t.row.bsPartNumber,this.formItem.bsItemNumber=t.row.bsItemNumber,this.formItem.bsHts=t.row.bsHts,this.formItem.bsDescription=t.row.bsDescription,this.dialog.modal_dialog1=!0},handleSubmit:function(){var t=this;this.api.lmp.cargo.selectByCargo(this.formQuery).then(function(e){e.result?t.datagrid.data=e.data:t.$Message.error(e.msg)})},changePage:function(t){this.start=(t-1)*this.datagrid.queryParams.rows,this.end=t*this.datagrid.queryParams.rows,this.getData()},chageSize:function(t){this.datagrid.queryParams.rows=t,this.start=0,this.end=t,this.getData()}}}},1756:function(t,e,r){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",[r("div",[r("Row",[r("i-col",[r("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:t.formQuery,inline:""}},[r("Form-item",[r("Input",{attrs:{placeholder:"请输入客户料号"},model:{value:t.formQuery.bsPartNumber,callback:function(e){t.$set(t.formQuery,"bsPartNumber",e)},expression:"formQuery.bsPartNumber"}})],1),t._v(" "),r("Form-item",[r("Input",{attrs:{placeholder:"请输入金合联料号"},model:{value:t.formQuery.bsItemNumber,callback:function(e){t.$set(t.formQuery,"bsItemNumber",e)},expression:"formQuery.bsItemNumber"}})],1),t._v(" "),r("Form-item",[r("Button",{attrs:{type:"primary"},on:{click:function(e){t.handleSubmit()}}},[t._v("查 询")])],1),t._v(" "),r("Form-item",[r("Button",{attrs:{type:"primary"},on:{click:function(e){t.showAddDialog()}}},[t._v("添加")])],1)],1)],1)],1)],1),t._v(" "),r("Modal",{attrs:{title:""},on:{"on-ok":t.ok1,"on-cancel":t.cancel},model:{value:t.dialog.modal_dialog1,callback:function(e){t.$set(t.dialog,"modal_dialog1",e)},expression:"dialog.modal_dialog1"}},[r("p"),r("div",[r("Row",[r("i-col",[r("Form",{ref:t.dialog.ruleForm,staticStyle:{"text-align":"center"},attrs:{model:t.formItem,"label-width":80,rules:t.dialog.ruleForm}},[r("FormItem",{attrs:{label:"客户料号",prop:"bsPartNumber"}},[r("Row",[r("Col",{attrs:{span:"15"}},[r("Input",{attrs:{placeholder:"请输入客户料号"},model:{value:t.formItem.bsPartNumber,callback:function(e){t.$set(t.formItem,"bsPartNumber",e)},expression:"formItem.bsPartNumber"}})],1)],1)],1),t._v(" "),r("FormItem",{attrs:{label:"金合联料号",prop:"bsItemNumber"}},[r("Row",[r("Col",{attrs:{span:"15"}},[r("Input",{attrs:{placeholder:"请输入金合联料号"},model:{value:t.formItem.bsItemNumber,callback:function(e){t.$set(t.formItem,"bsItemNumber",e)},expression:"formItem.bsItemNumber"}})],1)],1)],1),t._v(" "),r("FormItem",{attrs:{label:"商品编码",prop:"bsHts"}},[r("Row",[r("Col",{attrs:{span:"15"}},[r("Input",{attrs:{placeholder:"请输入商品编码"},model:{value:t.formItem.bsHts,callback:function(e){t.$set(t.formItem,"bsHts",e)},expression:"formItem.bsHts"}})],1)],1)],1),t._v(" "),r("FormItem",{attrs:{label:"料号描述",prop:"bsDescription"}},[r("Row",[r("Col",{attrs:{span:"15"}},[r("Input",{attrs:{type:"textarea",placeholder:"请输入料号描述"},model:{value:t.formItem.bsDescription,callback:function(e){t.$set(t.formItem,"bsDescription",e)},expression:"formItem.bsDescription"}})],1)],1)],1)],1)],1)],1)],1),t._v(" "),r("p")]),t._v(" "),r("Modal",{attrs:{title:""},on:{"on-ok":function(e){t.ok()},"on-cancel":t.cancel},model:{value:t.dialog.modal_dialog,callback:function(e){t.$set(t.dialog,"modal_dialog",e)},expression:"dialog.modal_dialog"}},[r("p"),r("div",[r("Row",[r("i-col",[r("Form",{ref:t.dialog.ruleForm,staticStyle:{"text-align":"center"},attrs:{model:t.formItem,"label-width":80,rules:t.dialog.ruleForm}},[r("FormItem",{attrs:{label:"客户料号",prop:"bsPartNumber"}},[r("Row",[r("Col",{attrs:{span:"15"}},[r("Input",{attrs:{placeholder:"请输入客户料号"},model:{value:t.formItem.bsPartNumber,callback:function(e){t.$set(t.formItem,"bsPartNumber",e)},expression:"formItem.bsPartNumber"}})],1)],1)],1),t._v(" "),r("FormItem",{attrs:{label:"金合联料号",prop:"bsItemNumber"}},[r("Row",[r("Col",{attrs:{span:"15"}},[r("Input",{attrs:{placeholder:"请输入金合联料号"},model:{value:t.formItem.bsItemNumber,callback:function(e){t.$set(t.formItem,"bsItemNumber",e)},expression:"formItem.bsItemNumber"}})],1)],1)],1),t._v(" "),r("FormItem",{attrs:{label:"商品编码",prop:"bsHts"}},[r("Row",[r("Col",{attrs:{span:"15"}},[r("Input",{attrs:{placeholder:"请输入商品编码"},model:{value:t.formItem.bsHts,callback:function(e){t.$set(t.formItem,"bsHts",e)},expression:"formItem.bsHts"}})],1)],1)],1),t._v(" "),r("FormItem",{attrs:{label:"料号描述",prop:"bsDescription"}},[r("Row",[r("Col",{attrs:{span:"15"}},[r("Input",{attrs:{type:"textarea",placeholder:"请输入料号描述"},model:{value:t.formItem.bsDescription,callback:function(e){t.$set(t.formItem,"bsDescription",e)},expression:"formItem.bsDescription"}})],1)],1)],1)],1)],1)],1)],1),t._v(" "),r("p")]),t._v(" "),r("Row",[r("i-col",{attrs:{span:"24"}},[r("Table",{staticStyle:{height:"auto"},attrs:{data:t.datagrid.data.rows,columns:t.datagrid.columns,stripe:""}}),t._v(" "),r("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[r("div",{staticStyle:{float:"right"}},[r("Page",{attrs:{total:t.datagrid.data.total,current:1,"page-size":t.datagrid.queryParams.rows,"page-size-opts":t.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":t.changePage,"on-page-size-change":t.chageSize}})],1)])],1)],1)],1)},s=[];a._withStripped=!0;var o={render:a,staticRenderFns:s};e.default=o}});