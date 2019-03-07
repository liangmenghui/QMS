webpackJsonp([61],{1008:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=o(1565),r=o.n(a);for(var l in a)"default"!==l&&function(e){o.d(t,e,function(){return a[e]})}(l);var s=o(1723),i=o.n(s),n=o(1),d=n(r.a,i.a,!1,null,null,null);d.options.__file="src\\view\\member\\role\\index.vue",t.default=d.exports},1565:function(e,t,o){"use strict";function a(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var r,l=o(2),s=a(l),i=o(11),n=a(i),d=o(15),m=a(d),u=o(23),c=a(u),g=o(75),p=a(g),f=o(120);t.default={data:function(){var e=this;return{formQuery:{bsCode:"",bsName:""},perms:{modal_dialog:!1,tree:{data:[]},formItem:{pkPerms:[]}},dialog:{refs:{rolegroup:[]},modal_dialog:!1,formItem:{},ruleForm:{pkSysRolegroup:[{required:!0,message:"请选择所属角色组",trigger:"blur"}],bsCode:[{required:!0,message:"请填写编码",trigger:"blur"}],bsName:[{required:!0,message:"请填写名称",trigger:"blur"}]}},datagrid:{queryParams:{page:1,rows:25,pkParent:-1},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:"角色编码",key:"bsCode"},{title:"角色名称",key:"bsName"},{title:"创建时间",key:"bsCreatedTime"},{title:"更新时间",key:"bsModifiedTime"},{title:"操作",key:"action",render:function(t,o){var a=[];return e.menuData.perms.ALLOC&&a.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.showAllocDialog(o)}}},"分配权限")),e.menuData.perms.EDIT&&a.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.showEditDialog(o)}}},"编辑")),e.menuData.perms.EDIT&&a.push(t("Button",{props:{type:"error",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.delete(o)}}},"删除")),t("div",a)}}]}}},created:function(){this.getRolegroup()},computed:(0,p.default)({},(0,f.mapState)({menuData:function(e){return e.menuData}})),methods:(r={handleSubmit:function(e){this.getData()},renderTree:function(e,t){var o=(t.root,t.node,t.data);console.log((0,c.default)(o));var a=[e("span",o.title)];if(o.perms)for(var r=o.perms,l=0;l<r.length;l++)a.push(e("label",{style:{margin:"0 0 0 5px"}},[e("input",{style:{margin:"5px"},attrs:{type:"checkbox",name:o.attributes.bsCode,value:r[l].pkPerm}}),e("span",r[l].permName)]));return e("span",{style:{display:"inline-block",width:"100%"}},a)},getData:function(){var e=this;(0,m.default)(this.formQuery,this.datagrid.queryParams),console.log("after===>"),console.log(this.formQuery),this.api.role.getlist(this.formQuery).then(function(t){console.log("get list===>"),console.log(t.data),t.result?e.datagrid.data=t.data:e.$Message.error(t.msg)})},reloadData:function(){this.datagrid.data=this.getData()},changePage:function(e){this.datagrid.queryParams.page=e,this.datagrid.data=this.getData()},edit:function(e){var t=this;console.log(e),this.api.role.edit(e.row).then(function(e){console.log(e),e.result?t.reloadData():t.$Message.error(e.msg)})},delete:function(e){var t=this;console.log(e),this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){t.api.role.delete({id:e.row.id}).then(function(e){console.log(e),e.result?(t.reloadData(),t.$Message.info("删除成功"),t.$Modal.remove()):t.$Message.error(e.msg)})}})},showAddDialog:function(){this.dialog.modal_dialog=!0,this.dialog.formItem={}},showEditDialog:function(e){this.dialog.modal_dialog=!0;var t=e.row;this.dialog.formItem={id:t.id,pkSysRolegroup:t.pkSysRolegroup,bsCode:t.bsCode,bsName:t.bsName,bsComment:t.bsComment}},ok:function(){var e=this;console.log("this.dialog.formItem====>"+(0,n.default)(this.dialog.formItem.id)),void 0!=(0,n.default)(this.dialog.formItem.id)&&"number"==typeof this.dialog.formItem.id?(console.log("编辑1"),console.log((0,c.default)(this.dialog.formItem)),this.api.role.edit(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})):(console.log("新增1"),this.api.role.add(this.dialog.formItem).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)}))},cancel:function(){},getRolegroup:function(){var e=this;this.api.rolegroup.getlist({}).then(function(t){t.result&&(console.log(t.data),e.dialog.refs.rolegroup=t.data.rows)})},showAllocDialog:function(e){this.perms.modal_dialog=!0,this.getlist(e.row.id)},getlist:function(e){var t=this;document.getElementById("roleId").value=e,this.api.roleperm.getlist({pkRole:e}).then(function(e){if(e.result){t.spellTitle(e.data.resrces,e.data.perms,e.data.alloced),t.perms.tree.data=e.data.resrces;var o=function(){for(var t in e.data.alloced){var o=document.getElementsByName(t);if(0!=o.length)for(var a=e.data.alloced[t],r=0;r<o.length;r++){o[r].checked=!1;for(var l=0;l<a.length;l++)if(o[r].value==a[l].id){o[r].checked=!0;break}}}};setTimeout(o,20)}})},getNodes:function(e,t){for(var o=0;o<t.length;o++){var a=this.getNode(t[o]);1==t[o].attributes.bsLeaf&&e.push(a),t[o].children&&this.getNodes(e,t[o].children)}},getNode:function(e){return{resrceCode:e.attributes.bsCode}},spellTitle:function(e,t,o){for(var a="",r=0;r<e.length;r++){if(e[r].attributes.bsName+"<br/>",a=e[r].attributes.bsCode,t[a]){var l=t[a];l&&(o[a]&&o[a].length>0&&(e[r].checked=!0),e[r].perms=l)}e[r].children&&this.spellTitle(e[r].children,t,o)}}},(0,s.default)(r,"getNodes",function(e,t){for(var o=0;o<t.length;o++){var a=this.getNode(t[o]);1==t[o].attributes.bsLeaf&&e.push(a),t[o].children&&this.getNodes(e,t[o].children)}}),(0,s.default)(r,"getNode",function(e){return{resrceCode:e.attributes.bsCode}}),(0,s.default)(r,"getPerms",function(){for(var e=[],t=this.$refs.resrce_tree.getCheckedNodes(),o=0;o<t.length;o++){this.addParent(e,t[o]);var a={};a.pkResrce=t[o].id;for(var r=[],l=document.getElementsByName(t[o].attributes.bsCode),s=0;s<l.length;s++)l[s].checked&&r.push(l[s].value);a.pkPerms=r.toString(),e.push(a)}return console.log(e),e}),(0,s.default)(r,"addParent",function(e,t){t.parent&&(this.addParent(e,t.parent),e.push({pkResrce:t.parent.id}))}),(0,s.default)(r,"save",function(){var e=this,t=this.getPerms(),o=document.getElementById("roleId").value;this.api.roleperm.save({roleId:o,rrpvo:(0,c.default)(t)}).then(function(t){t.result?e.$Message.info("操作成功"):e.$Message.error("操作失败")})}),(0,s.default)(r,"unsave",function(){}),r)}},1723:function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("Row",[o("i-col",[o("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:e.formQuery,inline:""}},[o("Form-item",{attrs:{prop:"bsCode"}},[o("Input",{attrs:{type:"text",placeholder:"角色编码"},model:{value:e.formQuery.bsCode,callback:function(t){e.$set(e.formQuery,"bsCode",t)},expression:"formQuery.bsCode"}})],1),e._v(" "),o("Form-item",{attrs:{prop:"bsName"}},[o("Input",{attrs:{type:"text",placeholder:"角色名称"},model:{value:e.formQuery.bsName,callback:function(t){e.$set(e.formQuery,"bsName",t)},expression:"formQuery.bsName"}})],1),e._v(" "),e.menuData.perms.QUERY?o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(t){e.handleSubmit("formQuery")}}},[e._v("查 询")])],1):e._e(),e._v(" "),e.menuData.perms.ADD?o("Form-item",[o("Button",{attrs:{type:"primary"},on:{click:function(t){e.showAddDialog()}}},[e._v("新 增")])],1):e._e()],1)],1)],1),e._v(" "),o("Row",[o("i-col",{attrs:{span:"24"}},[o("Table",{staticStyle:{height:"500px"},attrs:{data:e.datagrid.data.rows,columns:e.datagrid.columns,stripe:""}}),e._v(" "),o("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[o("div",{staticStyle:{float:"right"}},[o("Page",{attrs:{total:e.datagrid.data.total,current:1,"page-size":e.datagrid.queryParams.rows,"page-size-opts":e.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage}})],1)])],1)],1),e._v(" "),o("Modal",{attrs:{title:""},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.dialog.modal_dialog,callback:function(t){e.$set(e.dialog,"modal_dialog",t)},expression:"dialog.modal_dialog"}},[o("p",[o("Form",{ref:e.dialog.ruleForm,attrs:{model:e.dialog.formItem,rules:e.dialog.ruleForm,"label-width":80}},[o("Input",{attrs:{type:"hidden"},model:{value:e.dialog.formItem.id,callback:function(t){e.$set(e.dialog.formItem,"id",t)},expression:"dialog.formItem.id"}}),e._v(" "),o("Input",{attrs:{value:"-1",type:"hidden"},model:{value:e.dialog.formItem.pkGroup,callback:function(t){e.$set(e.dialog.formItem,"pkGroup",t)},expression:"dialog.formItem.pkGroup"}}),e._v(" "),o("Input",{attrs:{value:"-1",type:"hidden"},model:{value:e.dialog.formItem.pkOrg,callback:function(t){e.$set(e.dialog.formItem,"pkOrg",t)},expression:"dialog.formItem.pkOrg"}}),e._v(" "),o("Input",{attrs:{value:"-1",type:"hidden"},model:{value:e.dialog.formItem.pkParent,callback:function(t){e.$set(e.dialog.formItem,"pkParent",t)},expression:"dialog.formItem.pkParent"}}),e._v(" "),o("Form-item",{attrs:{label:"所属角色组",prop:"pkSysRolegroup"}},[o("Select",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.pkSysRolegroup,callback:function(t){e.$set(e.dialog.formItem,"pkSysRolegroup",t)},expression:"dialog.formItem.pkSysRolegroup"}},e._l(e.dialog.refs.rolegroup,function(t){return o("Option",{key:t,attrs:{value:t.id}},[e._v(e._s(t.bsCode)+" - "+e._s(t.bsName))])}))],1),e._v(" "),o("Form-item",{attrs:{label:"角色编码",prop:"bsCode"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCode,callback:function(t){e.$set(e.dialog.formItem,"bsCode",t)},expression:"dialog.formItem.bsCode"}})],1),e._v(" "),o("Form-item",{attrs:{label:"角色名称",prop:"bsName"}},[o("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsName,callback:function(t){e.$set(e.dialog.formItem,"bsName",t)},expression:"dialog.formItem.bsName"}})],1),e._v(" "),o("Form-item",{attrs:{label:"备注"}},[o("Input",{attrs:{type:"textarea",placeholder:"请输入..."},model:{value:e.dialog.formItem.bsComment,callback:function(t){e.$set(e.dialog.formItem,"bsComment",t)},expression:"dialog.formItem.bsComment"}})],1)],1)],1)]),e._v(" "),o("Modal",{attrs:{title:"分配权限"},on:{"on-ok":e.save,"on-cancel":e.unsave},model:{value:e.perms.modal_dialog,callback:function(t){e.$set(e.perms,"modal_dialog",t)},expression:"perms.modal_dialog"}},[o("p",[o("input",{attrs:{type:"hidden",id:"roleId",name:"roleId",value:""}}),e._v(" "),o("Tree",{ref:"resrce_tree",staticClass:"layout-menu-left",attrs:{data:e.perms.tree.data,render:e.renderTree,"show-checkbox":""}})],1)])],1)},r=[];a._withStripped=!0;var l={render:a,staticRenderFns:r};t.default=l}});