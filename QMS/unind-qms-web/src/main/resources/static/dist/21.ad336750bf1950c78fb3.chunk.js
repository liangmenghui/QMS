webpackJsonp([21],{1184:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a(1899),i=a.n(o);for(var s in o)"default"!==s&&function(e){a.d(t,e,function(){return o[e]})}(s);var r=a(2074),n=a.n(r),l=a(1),d=l(i.a,n.a,!1,null,null,null);d.options.__file="src/view/member/fs/file/index.vue",t.default=d.exports},1262:function(e,t,a){"use strict";function o(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var i=a(14),s=o(i),r=a(1276),n=o(r);t.default={components:{custExtprops:n.default},props:{type:String,size:String,name:String,catelog:String,rid:""},data:function(){return{title:"客制化属性",visible:!1,data:[]}},computed:{},created:function(){},methods:{showDialog:function(e){"Number"==typeof e&&(this.rid=e),this.rid?(this.$refs.custExtprops.show(!0),this.loadData(this.rid)):console.log("rid is required")},loadData:function(e){var t=this,a=this.catelog+":"+e;this.api.admin.customfieldvalue.getlist({catelog:this.catelog,handler:a}).then(function(e){if(console.log(e),e.result){for(var o in e.data.rows)e.data.rows[o].handler=a+","+e.data.rows[o].bsName;t.data=e.data.rows}})},ok:function(e){var t=this;this.api.admin.customfieldvalue.save({catelog:this.catelog,json:(0,s.default)(e)}).then(function(e){e.result?t.$message.info(e.message):t.$message.error(e.message)})},cancel:function(){this.$emit("cancel")}}}},1263:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={name:"cust-extprops",props:{title:String,data:Array},data:function(){return{formItem:{},visible:!1}},created:function(){},methods:{show:function(e){this.visible=!0},ok:function(){this.$emit("ok",this.data)},cancel:function(){this.$emit("cancel")}}}},1273:function(e,t,a){"use strict";function o(e){d||a(1274)}Object.defineProperty(t,"__esModule",{value:!0});var i=a(1262),s=a.n(i);for(var r in i)"default"!==r&&function(e){a.d(t,e,function(){return i[e]})}(r);var n=a(1280),l=a.n(n),d=!1,u=a(1),p=o,m=u(s.a,l.a,!1,p,null,null);m.options.__file="src/components/business-component/custDialog.vue",t.default=m.exports},1274:function(e,t,a){var o=a(1275);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(19)("489ad722",o,!1)},1275:function(e,t,a){t=e.exports=a(18)(!1),t.push([e.i,"\n.split-width {\n  padding-left: 3px;\n}\n",""])},1276:function(e,t,a){"use strict";function o(e){d||a(1277)}Object.defineProperty(t,"__esModule",{value:!0});var i=a(1263),s=a.n(i);for(var r in i)"default"!==r&&function(e){a.d(t,e,function(){return i[e]})}(r);var n=a(1279),l=a.n(n),d=!1,u=a(1),p=o,m=u(s.a,l.a,!1,p,null,null);m.options.__file="src/components/business-component/custExtprops.vue",t.default=m.exports},1277:function(e,t,a){var o=a(1278);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(19)("38340c12",o,!1)},1278:function(e,t,a){t=e.exports=a(18)(!1),t.push([e.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",""])},1279:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("Modal",{attrs:{transfer:"",title:e.title},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.visible,callback:function(t){e.visible=t},expression:"visible"}},[a("div",{staticStyle:{padding:"5px 5px"}},[a("Form",{ref:"ruleForm",attrs:{"label-width":80}},e._l(e.data,function(t){return a("Row",{key:t.bsName},[a("i-col",{attrs:{span:"24"}},[a("span",{staticStyle:{display:"none"}},[a("Input",{model:{value:t.id,callback:function(a){e.$set(t,"id",a)},expression:"item.id"}})],1),e._v(" "),a("Form-item",{attrs:{label:t.bsLabel}},[a("Input",{attrs:{placeholder:"请输入"},model:{value:t.bsValue,callback:function(a){e.$set(t,"bsValue",a)},expression:"item.bsValue"}})],1)],1)],1)}))],1)])},i=[];o._withStripped=!0;var s={render:o,staticRenderFns:i};t.default=s},1280:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("span",{staticClass:"split-width"},[a("Button",{attrs:{type:e.type,size:e.size},on:{click:e.showDialog}},[e._v(e._s(e.name))]),e._v(" "),a("cust-extprops",{ref:"custExtprops",attrs:{data:e.data,title:"客制化属性"},on:{ok:e.ok}})],1)},i=[];o._withStripped=!0;var s={render:o,staticRenderFns:i};t.default=s},1899:function(e,t,a){"use strict";function o(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var i=a(22),s=o(i),r=a(11),n=o(r),l=a(72),d=o(l),u=a(98),p=a(1273),m=o(p);t.default={components:{custDialog:m.default},data:function(){var e=this;return{groups:[],orgs:[],file:{},formQuery:{bsCode:"",bsName:""},tree:{data:[{expand:!0,title:"根目录",children:[]}]},dialog:{modal_dialog:!1,formItem:{},ruleForm:{bsCode:[{required:!0,message:"请填写编码",trigger:"blur"}],bsName:[{required:!0,message:"请填写名称",trigger:"blur"}],bsLeaf:[{required:!0,message:"请选择目录类型",trigger:"blur"}]}},datagrid:{queryParams:{page:1,rows:25,pkFsCatalog:-1},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:"所属目录",key:"pkFsCatalog",render:function(e,t){return e("span",t.row.catalog?t.row.catalog.bsName:"Unknown")}},{title:"文件编码",key:"bsCode"},{title:"文件名称",key:"bsName"},{title:"文件路径",key:"bsFilePath",render:function(e,t){return e("a",{on:{click:function(){window.open("/admin/fs/file/get/"+t.row.id+"/?filepath="+t.row.bsFilePath+"/"+t.row.bsFileName)}}},t.row.bsFilePath+"/"+t.row.bsFileName)}},{title:"删除标识",key:"bsIsDel",render:function(e,t){var a=void 0;return 0==t.row.bsIsDel?a="-":1==t.row.bsIsDel&&(a="已删除"),e("span",a)}},{title:"创建时间",key:"bsCreatedTime"},{title:"更新时间",key:"bsModifiedTime"},{title:"操作",key:"action",render:function(t,a){var o=[];return e.menuData.perms.EDIT&&o.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.showEditDialog(a)}}},"编辑")),e.menuData.perms.DELETE&&o.push(t("Button",{props:{type:"error",size:"small"},on:{click:function(){e.delete(a)}}},"删除")),e.menuData.perms.DOWNLOAD&&o.push(t("Button",{props:{type:"primary",size:"small"},on:{click:function(){e.download(a)}}},"下载")),e.menuData.perms.CUSTOMFIELD&&ary.push(t(m.default,{props:{type:"primary",size:"small",name:"客制化",ref:"custDialog",catelog:"FsFileBo",rid:a.row.id},on:{click:function(){e.$refs.custDialog.showDialog()}}})),t("div",o)}}]}}},created:function(){this.getTree(),this.getGroupList()},computed:(0,d.default)({},(0,u.mapState)({menuData:function(e){return e.menuData}})),methods:{getGroupList:function(){var e=this;this.api.admin.group.getlist({}).then(function(t){console.log("get list===>"),console.log(t.data),t.result?e.groups=t.data.rows:e.$Message.error(t.msg)})},getOrgList:function(e){var t=this;this.api.admin.organization.getlist({pkGroup:e}).then(function(e){console.log("get list===>"),console.log(e.data),e.result?t.orgs=e.data.rows:t.$Message.error(e.msg)})},handleUpload:function(e){return this.file=e,!1},handleSubmit:function(e){this.getData()},getData:function(){var e=this;(0,n.default)(this.formQuery,this.datagrid.queryParams),this.api.fs.fsfile.getlist(this.formQuery).then(function(t){t.result?e.datagrid.data=t.data:e.$Message.error(t.msg)})},reloadData:function(){this.datagrid.data=this.getData()},changePage:function(e){this.datagrid.queryParams.page=e,this.datagrid.data=this.getData()},edit:function(e){var t=this;this.api.fs.fscatalog.edit(e.row).then(function(e){e.result?t.reloadData():t.$Message.error(e.msg)})},delete:function(e){var t=this;this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){t.api.fs.fsfile.delete({id:e.row.id}).then(function(e){e.result?(t.getTree(),t.reloadData(),t.$Message.info("删除成功"),t.$Modal.remove()):t.$Message.error(e.msg)})}})},download:function(e){windows.open("/fs/file/getattch?fileId="+e.row.id)},showAddDialog:function(){this.dialog.modal_dialog=!0;var e=this.$refs.tree.getSelectedNodes();0==e.length?this.dialog.formItem={pkFsCatalog:-1,parentName:"根目录"}:this.dialog.formItem={pkFsCatalog:e[0].id,parentName:e[0].title}},showEditDialog:function(e){this.dialog.modal_dialog=!0;var t=e.row;this.file=null,this.dialog.formItem={pkFsCatalog:t.pkFsCatalog,pkGroup:t.pkGroup,pkOrg:t.pkOrg,id:t.id,bsCode:t.bsCode,bsName:t.bsName,bsRevision:t.bsRevision,bsContentType:t.bsContentType,bsFileType:t.bsFileType,bsFileName:t.bsFileName,bsFileSize:t.bsFileSize,bsFilePath:t.bsFilePath,bsIsCurrentVersion:t.bsIsCurrentVersion,bsIsValid:t.bsIsValid,bsVerifyState:t.bsVerifyState};var a=this.$refs.tree.getSelectedNodes();0==a.length?(0,n.default)(this.dialog.formItem,{pkFsCatalog:-1,parentName:"根目录"}):(0,n.default)(this.dialog.formItem,{pkFsCatalog:a[0].id,parentName:a[0].title})},ok:function(){var e=this;if(void 0!=(0,s.default)(this.dialog.formItem.id)&&"number"==typeof this.dialog.formItem.id){var t=new FormData;t.append("file",this.file),t.append("id",this.dialog.formItem.id),t.append("pkFsCatalog",this.dialog.formItem.pkFsCatalog),t.append("pkGroup",this.dialog.formItem.pkGroup),t.append("pkOrg",this.dialog.formItem.pkOrg),t.append("bsCode",this.dialog.formItem.bsCode),t.append("bsName",this.dialog.formItem.bsName),t.append("bsComment",this.dialog.formItem.bsComment),this.api.fs.fsfile.edit(t).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})}else{var a=new FormData;a.append("file",this.file),a.append("pkFsCatalog",this.dialog.formItem.pkFsCatalog),a.append("pkGroup",this.dialog.formItem.pkGroup),a.append("pkOrg",this.dialog.formItem.pkOrg),a.append("bsCode",this.dialog.formItem.bsCode),a.append("bsName",this.dialog.formItem.bsName),a.append("bsComment",this.dialog.formItem.bsComment),this.api.fs.fsfile.add(a).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})}},cancel:function(){},getTree:function(){var e=this;this.api.fs.fscatalog.gettree({}).then(function(t){var a=[{id:-1,expand:!0,title:"根目录",children:[]}];t.result&&(a[0].children=t.data,e.tree.data=a)})},selectNode:function(e){this.datagrid.queryParams.pkFsCatalog=e[0].id,this.datagrid.data=this.getData()},getSelectedNodes:function(){this.dialog.formItem.pkFsCatalog=node[0].id}}}},2074:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Row",[a("i-col",[a("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:e.formQuery,inline:""}},[a("Form-item",{attrs:{prop:"bsCode"}},[a("Input",{attrs:{type:"text",placeholder:"文件编码"},model:{value:e.formQuery.bsCode,callback:function(t){e.$set(e.formQuery,"bsCode",t)},expression:"formQuery.bsCode"}})],1),e._v(" "),a("Form-item",{attrs:{prop:"bsName"}},[a("Input",{attrs:{type:"text",placeholder:"文件名称"},model:{value:e.formQuery.bsName,callback:function(t){e.$set(e.formQuery,"bsName",t)},expression:"formQuery.bsName"}})],1),e._v(" "),a("Form-item",[a("Button",{attrs:{type:"primary"},on:{click:function(t){e.handleSubmit("formQuery")}}},[e._v("查 询")])],1),e._v(" "),a("Form-item",[a("Button",{attrs:{type:"primary"},on:{click:function(t){e.showAddDialog()}}},[e._v("新 增")])],1)],1)],1)],1),e._v(" "),a("Row",[a("i-col",{attrs:{span:"4"}},[a("Tree",{ref:"tree",staticClass:"layout-menu-left",attrs:{data:e.tree.data},on:{"on-select-change":e.selectNode}})],1),e._v(" "),a("i-col",{attrs:{span:"20"}},[a("Table",{attrs:{data:e.datagrid.data.rows,columns:e.datagrid.columns,stripe:"",height:"500"}}),e._v(" "),a("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[a("div",{staticStyle:{float:"right"}},[a("Page",{attrs:{total:e.datagrid.data.total,current:1,"page-size":e.datagrid.queryParams.rows,"page-size-opts":e.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage}})],1)])],1)],1),e._v(" "),a("Modal",{attrs:{title:""},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.dialog.modal_dialog,callback:function(t){e.$set(e.dialog,"modal_dialog",t)},expression:"dialog.modal_dialog"}},[a("p",[a("Form",{attrs:{model:e.dialog.formItem,rules:e.dialog.ruleForm,"label-width":80,enctype:"multipart/form-data"}},[a("span",{staticStyle:{display:"none"}},[a("Input",{model:{value:e.dialog.formItem.id,callback:function(t){e.$set(e.dialog.formItem,"id",t)},expression:"dialog.formItem.id"}}),e._v(" "),a("Input",{attrs:{value:"-1"},model:{value:e.dialog.formItem.pkFsCatalog,callback:function(t){e.$set(e.dialog.formItem,"pkFsCatalog",t)},expression:"dialog.formItem.pkFsCatalog"}})],1),e._v(" "),a("Form-item",{attrs:{label:"集团",prop:"pkGroup"}},[a("Select",{attrs:{placeholder:"请输入"},on:{"on-change":e.getOrgList},model:{value:e.dialog.formItem.pkGroup,callback:function(t){e.$set(e.dialog.formItem,"pkGroup",t)},expression:"dialog.formItem.pkGroup"}},e._l(e.groups,function(t){return a("Option",{key:t.bsCode,attrs:{value:t.id}},[e._v(e._s(t.bsName))])}))],1),e._v(" "),a("Form-item",{attrs:{label:"组织",prop:"pkOrg"}},[a("Select",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.pkOrg,callback:function(t){e.$set(e.dialog.formItem,"pkOrg",t)},expression:"dialog.formItem.pkOrg"}},[a("Option",{attrs:{value:"1"}},[e._v("全局")]),e._v(" "),e._l(e.orgs,function(t){return a("Option",{key:t.bsCode,attrs:{value:t.id}},[e._v(e._s(t.bsName))])})],2)],1),e._v(" "),a("Form-item",{attrs:{label:"上级节点"}},[a("Input",{attrs:{disabled:"",placeholder:"请输入"},model:{value:e.dialog.formItem.parentName,callback:function(t){e.$set(e.dialog.formItem,"parentName",t)},expression:"dialog.formItem.parentName"}})],1),e._v(" "),a("Form-item",{attrs:{label:"文件名称",prop:"bsName"}},[a("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsName,callback:function(t){e.$set(e.dialog.formItem,"bsName",t)},expression:"dialog.formItem.bsName"}})],1),e._v(" "),a("Form-item",{attrs:{label:"文件"}},[a("Upload",{attrs:{multiple:"",type:"drag","before-upload":e.handleUpload}},[null!==this.file?a("div",{staticStyle:{padding:"20px 0"}},[e._v("Upload file: "+e._s(this.file.name)+" ")]):e._e(),e._v(" "),null===this.file?a("div",{staticStyle:{padding:"20px 0"}},[a("Icon",{staticStyle:{color:"#3399ff"},attrs:{type:"ios-cloud-upload",size:"52"}}),e._v(" "),a("p",[e._v("Click or drag files here to upload")])],1):e._e()])],1),e._v(" "),a("Form-item",{attrs:{label:"备注"}},[a("Input",{attrs:{type:"textarea",autosize:{minRows:2,maxRows:5},placeholder:"请输入..."},model:{value:e.dialog.formItem.bsComment,callback:function(t){e.$set(e.dialog.formItem,"bsComment",t)},expression:"dialog.formItem.bsComment"}})],1)],1)],1)])],1)},i=[];o._withStripped=!0;var s={render:o,staticRenderFns:i};t.default=s}});