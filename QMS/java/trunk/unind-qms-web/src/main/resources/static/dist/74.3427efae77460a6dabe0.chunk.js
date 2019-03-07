webpackJsonp([74],{1011:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a(1568),i=a.n(o);for(var r in o)"default"!==r&&function(e){a.d(t,e,function(){return o[e]})}(r);var s=a(1726),l=a.n(s),n=a(1),d=n(i.a,l.a,!1,null,null,null);d.options.__file="src\\view\\member\\fs\\file\\index.vue",t.default=d.exports},1568:function(e,t,a){"use strict";function o(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var i=a(23),r=o(i),s=a(11),l=o(s),n=a(15),d=o(n);t.default={data:function(){var e=this;return{file:{},formQuery:{bsCode:"",bsName:""},tree:{data:[{expand:!0,title:"根目录",children:[]}]},dialog:{modal_dialog:!1,formItem:{},ruleForm:{bsCode:[{required:!0,message:"请填写编码",trigger:"blur"}],bsName:[{required:!0,message:"请填写名称",trigger:"blur"}],bsLeaf:[{required:!0,message:"请选择目录类型",trigger:"blur"}]}},datagrid:{queryParams:{page:1,rows:25,pkFsCatalog:-1},pagination:[25,50,100],data:{rows:[],total:0},columns:[{title:"所属目录",key:"pkFsCatalog",width:"100px",render:function(e,t){return e("span",t.row.catalog?t.row.catalog.bsName:"Unknown")}},{title:"文件编码",key:"bsCode",width:"100px"},{title:"文件名称",key:"bsName",width:"150px"},{title:"文件路径",key:"bsFilePath",width:"225px",render:function(e,t){return e("a",t.row.bsFilePath+"/"+t.row.bsName)}},{title:"创建时间",key:"bsCreatedTime",width:"150px"},{title:"更新时间",key:"bsModifiedTime",width:"150px"},{title:"操作",key:"action",render:function(t,a){var o=[];return o.push(t("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){e.showEditDialog(a)}}},"编辑")),o.push(t("Button",{props:{type:"error",size:"small"},on:{click:function(){e.delete(a)}}},"删除")),o.push(t("Button",{props:{type:"primary",size:"small"},on:{click:function(){e.download(a)}}},"下载")),t("div",o)}}]}}},created:function(){this.getTree(),this.getGroupList()},methods:{getGroupList:function(){var e=this;this.api.group.getlist({}).then(function(t){console.log("get list===>"),console.log(t.data),t.result?e.groups=t.data.rows:e.$Message.error(t.msg)})},getOrgList:function(e){var t=this;this.api.org.getlist({pkGroup:e}).then(function(e){console.log("get list===>"),console.log(e.data),e.result?t.orgs=e.data.rows:t.$Message.error(e.msg)})},handleUpload:function(e){return this.file=e,!1},handleSubmit:function(e){this.getData()},getData:function(){var e=this;(0,d.default)(this.formQuery,this.datagrid.queryParams),console.log("after===>"),console.log(this.formQuery),this.api.fsfile.getlist(this.formQuery).then(function(t){console.log("get list===>"),console.log(t.data),t.result?e.datagrid.data=t.data:e.$Message.error(t.msg)})},reloadData:function(){this.datagrid.data=this.getData()},changePage:function(e){this.datagrid.queryParams.page=e,this.datagrid.data=this.getData()},edit:function(e){var t=this;console.log(e),this.api.fscatalog.edit(e.row).then(function(e){console.log(e),e.result?t.reloadData():t.$Message.error(e.msg)})},delete:function(e){var t=this;console.log(e),this.$Modal.confirm({title:"提示信息",content:"<p>是否确定删除？</p>",loading:!0,onOk:function(){t.api.fsfile.delete({id:e.row.id}).then(function(e){console.log(e),e.result?(t.getTree(),t.reloadData(),t.$Message.info("删除成功"),t.$Modal.remove()):t.$Message.error(e.msg)})}})},download:function(e){windows.open("/fs/file/getattch?fileId="+e.row.id)},showAddDialog:function(){this.dialog.modal_dialog=!0;var e=this.$refs.tree.getSelectedNodes();0==e.length?this.dialog.formItem={pkFsCatalog:-1,parentName:"根目录"}:this.dialog.formItem={pkFsCatalog:e[0].id,parentName:e[0].title}},showEditDialog:function(e){this.dialog.modal_dialog=!0;var t=e.row;this.file=null,this.dialog.formItem={pkFsCatalog:t.pkFsCatalog,pkGroup:t.pkGroup,pkOrg:t.pkOrg,id:t.id,bsCode:t.bsCode,bsName:t.bsName,bsRevision:t.bsRevision,bsContentType:t.bsContentType,bsFileType:t.bsFileType,bsFileName:t.bsFileName,bsFileSize:t.bsFileSize,bsFilePath:t.bsFilePath,bsIsCurrentVersion:t.bsIsCurrentVersion,bsIsValid:t.bsIsValid,bsVerifyState:t.bsVerifyState};var a=this.$refs.tree.getSelectedNodes();0==a.length?(0,d.default)(this.dialog.formItem,{pkFsCatalog:-1,parentName:"根目录"}):(0,d.default)(this.dialog.formItem,{pkFsCatalog:a[0].id,parentName:a[0].title})},ok:function(){var e=this;if(console.log("this.dialog.formItem====>"+(0,l.default)(this.dialog.formItem.id)),void 0!=(0,l.default)(this.dialog.formItem.id)&&"number"==typeof this.dialog.formItem.id){console.log("编辑1");var t=new FormData;t.append("file",this.file),t.append("id",this.dialog.formItem.id),t.append("pkFsCatalog",this.dialog.formItem.pkFsCatalog),t.append("pkGroup",this.dialog.formItem.pkGroup),t.append("pkOrg",this.dialog.formItem.pkOrg),t.append("bsCode",this.dialog.formItem.bsCode),t.append("bsName",this.dialog.formItem.bsName),t.append("bsComment",this.dialog.formItem.bsComment),this.api.fsfile.edit(t).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})}else{console.log("新增1");var a=new FormData;a.append("file",this.file),a.append("pkFsCatalog",this.dialog.formItem.pkFsCatalog),a.append("pkGroup",this.dialog.formItem.pkGroup),a.append("pkOrg",this.dialog.formItem.pkOrg),a.append("bsCode",this.dialog.formItem.bsCode),a.append("bsName",this.dialog.formItem.bsName),a.append("bsComment",this.dialog.formItem.bsComment),this.api.fsfile.add(a).then(function(t){t.result?e.reloadData():e.$Message.error(t.msg)})}},cancel:function(){},getTree:function(){var e=this;this.api.fscatalog.gettree({}).then(function(t){var a=[{id:-1,expand:!0,title:"根目录",children:[]}];t.result&&(console.log(e.tree.data),a[0].children=t.data,e.tree.data=a)})},selectNode:function(e){console.log("selected"),this.datagrid.queryParams.pkFsCatalog=e[0].id,this.datagrid.data=this.getData()},getSelectedNodes:function(){this.dialog.formItem.pkFsCatalog=node[0].id,console.log((0,r.default)(this.dialog.formItem))}}}},1726:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Row",[a("i-col",[a("Form",{ref:"formQuery",staticClass:"query_area",attrs:{model:e.formQuery,inline:""}},[a("Form-item",{attrs:{prop:"bsCode"}},[a("Input",{attrs:{type:"text",placeholder:"文件编码"},model:{value:e.formQuery.bsCode,callback:function(t){e.$set(e.formQuery,"bsCode",t)},expression:"formQuery.bsCode"}})],1),e._v(" "),a("Form-item",{attrs:{prop:"bsName"}},[a("Input",{attrs:{type:"text",placeholder:"文件名称"},model:{value:e.formQuery.bsName,callback:function(t){e.$set(e.formQuery,"bsName",t)},expression:"formQuery.bsName"}})],1),e._v(" "),a("Form-item",[a("Button",{attrs:{type:"primary"},on:{click:function(t){e.handleSubmit("formQuery")}}},[e._v("查 询")])],1),e._v(" "),a("Form-item",[a("Button",{attrs:{type:"primary"},on:{click:function(t){e.showAddDialog()}}},[e._v("新 增")])],1)],1)],1)],1),e._v(" "),a("Row",[a("i-col",{attrs:{span:"4"}},[a("Tree",{ref:"tree",staticClass:"layout-menu-left",attrs:{data:e.tree.data},on:{"on-select-change":e.selectNode}})],1),e._v(" "),a("i-col",{attrs:{span:"20"}},[a("Table",{staticStyle:{height:"500px"},attrs:{data:e.datagrid.data.rows,columns:e.datagrid.columns,stripe:""}}),e._v(" "),a("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[a("div",{staticStyle:{float:"right"}},[a("Page",{attrs:{total:e.datagrid.data.total,current:1,"page-size":e.datagrid.queryParams.rows,"page-size-opts":e.datagrid.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":e.changePage}})],1)])],1)],1),e._v(" "),a("Modal",{attrs:{title:""},on:{"on-ok":e.ok,"on-cancel":e.cancel},model:{value:e.dialog.modal_dialog,callback:function(t){e.$set(e.dialog,"modal_dialog",t)},expression:"dialog.modal_dialog"}},[a("p",[a("Form",{attrs:{model:e.dialog.formItem,rules:e.dialog.ruleForm,"label-width":80,enctype:"multipart/form-data"}},[a("Input",{attrs:{type:"hidden"},model:{value:e.dialog.formItem.id,callback:function(t){e.$set(e.dialog.formItem,"id",t)},expression:"dialog.formItem.id"}}),e._v(" "),a("Input",{attrs:{value:"-1",type:"hidden"},model:{value:e.dialog.formItem.pkFsCatalog,callback:function(t){e.$set(e.dialog.formItem,"pkFsCatalog",t)},expression:"dialog.formItem.pkFsCatalog"}}),e._v(" "),a("Form-item",{attrs:{label:"集团",prop:"pkGroup"}},[a("Select",{attrs:{placeholder:"请输入"},on:{"on-change":e.getOrgList},model:{value:e.dialog.formItem.pkGroup,callback:function(t){e.$set(e.dialog.formItem,"pkGroup",t)},expression:"dialog.formItem.pkGroup"}},e._l(e.groups,function(t){return a("Option",{key:t.bsCode,attrs:{value:t.id}},[e._v(e._s(t.bsName))])}))],1),e._v(" "),a("Form-item",{attrs:{label:"组织",prop:"pkOrg"}},[a("Select",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.pkOrg,callback:function(t){e.$set(e.dialog.formItem,"pkOrg",t)},expression:"dialog.formItem.pkOrg"}},[a("Option",{attrs:{value:"1"}},[e._v("全局")]),e._v(" "),e._l(e.orgs,function(t){return a("Option",{key:t.bsCode,attrs:{value:t.id}},[e._v(e._s(t.bsName))])})],2)],1),e._v(" "),a("Form-item",{attrs:{label:"上级节点"}},[a("Input",{attrs:{disabled:"",placeholder:"请输入"},model:{value:e.dialog.formItem.parentName,callback:function(t){e.$set(e.dialog.formItem,"parentName",t)},expression:"dialog.formItem.parentName"}})],1),e._v(" "),a("Form-item",{attrs:{label:"文件编码",prop:"bsCode"}},[a("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsCode,callback:function(t){e.$set(e.dialog.formItem,"bsCode",t)},expression:"dialog.formItem.bsCode"}})],1),e._v(" "),a("Form-item",{attrs:{label:"文件名称",prop:"bsName"}},[a("Input",{attrs:{placeholder:"请输入"},model:{value:e.dialog.formItem.bsName,callback:function(t){e.$set(e.dialog.formItem,"bsName",t)},expression:"dialog.formItem.bsName"}})],1),e._v(" "),a("Form-item",{attrs:{label:"文件"}},[a("Upload",{attrs:{multiple:"",type:"drag","before-upload":e.handleUpload}},[null!==this.file?a("div",{staticStyle:{padding:"20px 0"}},[e._v("Upload file: "+e._s(this.file.name)+" ")]):e._e(),e._v(" "),null===this.file?a("div",{staticStyle:{padding:"20px 0"}},[a("Icon",{staticStyle:{color:"#3399ff"},attrs:{type:"ios-cloud-upload",size:"52"}}),e._v(" "),a("p",[e._v("Click or drag files here to upload")])],1):e._e()])],1),e._v(" "),a("Form-item",{attrs:{label:"备注"}},[a("Input",{attrs:{type:"textarea",autosize:{minRows:2,maxRows:5},placeholder:"请输入..."},model:{value:e.dialog.formItem.bsComment,callback:function(t){e.$set(e.dialog.formItem,"bsComment",t)},expression:"dialog.formItem.bsComment"}})],1)],1)],1)])],1)},i=[];o._withStripped=!0;var r={render:o,staticRenderFns:i};t.default=r}});