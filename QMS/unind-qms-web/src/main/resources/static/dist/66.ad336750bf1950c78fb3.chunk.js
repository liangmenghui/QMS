webpackJsonp([66],{1176:function(t,e,n){"use strict";function i(t){l||n(2064)}Object.defineProperty(e,"__esModule",{value:!0});var a=n(1891),s=n.n(a);for(var r in a)"default"!==r&&function(t){n.d(e,t,function(){return a[t]})}(r);var o=n(2066),c=n.n(o),l=!1,d=n(1),u=i,h=d(s.a,c.a,!1,u,null,null);h.options.__file="src/view/member/org/orglist.vue",e.default=h.exports},1891:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=n(14),a=function(t){return t&&t.__esModule?t:{default:t}}(i);e.default={data:function(){var t=this;return{pagination:[25,50,100],total:0,page:0,isShowAddOrg:!1,searchValue:"",cityList:[],model1:"",columns:[{title:"日期",key:"bsCreatedTime",align:"center",width:200},{title:"姓名",key:"bsName",align:"center",width:200},{title:"编码",key:"bsCode",align:"center",width:200},{title:"是否删除",key:"bsIsDel",width:200,align:"center",render:function(t,e){return 1==e.row.bsIsDel?"已删除":""}},{title:"备注",key:"bsComment",align:"center"},{title:"操作",key:"action",width:200,align:"center",render:function(e,n){return e("div",[e("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.$Loading.start(),t.$router.push({path:"/modifyUser",query:{param:(0,a.default)(n.row),type:3}})}}},"编辑"),e("Button",{props:{type:"error",size:"small"},on:{click:function(){var e=t;t.$Modal.confirm({title:"用户信息",content:"是否删除此记录",onOk:function(){this.$Loading.start(),e.deleteOrg(n.row.id)}})}}},"删除")])}}],tList:[]}},components:{},created:function(){this.getOrgList()},methods:{OnSearch:function(){},changePage:function(){},getOrgList:function(){var t=this;this.api.admin.organization.getlist({page:1,rows:20}).then(function(e){t.tList=e.data.rows,t.total=e.data.total,t.page=e.data.page})},deleteOrg:function(t){var e=this;this.api.admin.organization.deleteOrg({id:t}).then(function(t){1==t.result&&(e.getOrgList(),e.$Message.success("删除成功"))})},okClick:function(t){var e=this;this.isShowAddOrg=!1,this.api.admin.organization.addOrganization(t).then(function(t){1==t.result&&e.getOrgList()})},cancelClick:function(){this.isShowAddOrg=!1}}}},2064:function(t,e,n){var i=n(2065);"string"==typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);n(19)("3fa1027d",i,!1)},2065:function(t,e,n){e=t.exports=n(18)(!1),e.push([t.i,"\n.search-condition {\n  height: 55px;\n  padding: 10px 0;\n}\n.search-condition .serach-item {\n  width: 150px;\n  float: left;\n  margin-left: 10px;\n}\n.main-table {\n  margin: 10px 10px;\n}\n.page-control {\n  float: right;\n  height: 55px;\n  padding: 10px 10px;\n}\n",""])},2066:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"animated fadeIn"},[n("div",{staticClass:"search-condition"},[n("Input",{staticClass:"serach-item",attrs:{placeholder:"请输入组织名称"},model:{value:t.searchValue,callback:function(e){t.searchValue=e},expression:"searchValue"}}),t._v(" "),n("Button",{staticClass:"serach-item",attrs:{type:"ghost",shape:"circle"},nativeOn:{click:function(e){t.OnSearch(e)}}},[t._v("查询")]),t._v(" "),n("Button",{staticClass:"serach-item",attrs:{type:"ghost",shape:"circle"},nativeOn:{click:function(e){t.isShowAdduser=!0}}},[t._v("新增")])],1),t._v(" "),n("Table",{staticClass:"main-table",attrs:{border:"",columns:t.columns,data:t.tList}}),t._v(" "),n("div",{staticClass:"page-control"},[n("Page",{attrs:{total:t.total,current:1,"page-size":20,"page-size-opts":t.pagination,"show-total":"","show-elevator":"","show-sizer":""},on:{"on-change":t.changePage}})],1),t._v(" "),n("add-role",{attrs:{isShow:t.isShowAddOrg,type:1},on:{onClickOk:t.okClick,onClickCancel:t.cancelClick}})],1)},a=[];i._withStripped=!0;var s={render:i,staticRenderFns:a};e.default=s}});