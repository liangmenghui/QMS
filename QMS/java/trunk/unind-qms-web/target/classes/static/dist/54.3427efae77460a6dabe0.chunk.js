webpackJsonp([54],{1050:function(e,t,a){"use strict";function n(e){u||a(1865)}Object.defineProperty(t,"__esModule",{value:!0});var o=a(1629),i=a.n(o);for(var r in o)"default"!==r&&function(e){a.d(t,e,function(){return o[e]})}(r);var s=a(1867),l=a.n(s),u=!1,c=a(1),d=n,p=c(i.a,l.a,!1,d,null,null);p.options.__file="src\\view\\feedback\\FeedbackList.vue",t.default=p.exports},1629:function(e,t,a){"use strict";function n(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var o=a(15),i=n(o),r=a(2),s=n(r);t.default={data:function(){var e;return e={formQuery:{},showRooterView:!1,tableData:[],expands:[]},(0,s.default)(e,"showRooterView",!1),(0,s.default)(e,"filters",{}),(0,s.default)(e,"queryParams",{page:1,rows:25,pkParent:-1}),(0,s.default)(e,"pageSizesList",[25,50,100]),(0,s.default)(e,"totalCount",0),e},created:function(){this.showRooterView=this.$route.matched.length>2,this.getData()},beforeUpdate:function(){this.showRooterView=this.$route.matched.length>2},watch:{$route:function(e,t){2==e.matched.length&&this.getData(),this.showRooterView=e.matched.length>2}},methods:{getData:function(){var e=this,t=(0,i.default)(this.formQuery,this.$route.query);this.api.feedback.getlist(t).then(function(t){e.tableData=t.data.rows,e.totalCount=t.data.total})},rowClick:function(e,t,a){Array.prototype.remove=function(e){var t=this.indexOf(e);t>-1&&this.splice(t,1)},this.expands.indexOf(e.id)<0?this.expands.push(e.id):this.expands.remove(e.id)},to:function(e,t){this.$router.push({path:e,query:{feedbackId:t}})},changePage:function(e){this.queryParams.page=e,this.getData()},SizeChange:function(e){}}}},1865:function(e,t,a){var n=a(1866);"string"==typeof n&&(n=[[e.i,n,""]]),n.locals&&(e.exports=n.locals);a(28)("a8243d94",n,!1,{})},1866:function(e,t,a){t=e.exports=a(27)(!1),t.push([e.i,"\n.ApprovedFlowManagement .ivu-table {\n  font-size: 14px;\n}\n.ApprovedTermsManagement .ivu-table-cell {\n  font-size: 14px;\n}\n.SampleManagement .ivu-table {\n  font-size: 14px;\n}\n.SampleRegularManagement .ivu-table {\n  font-size: 14px;\n}\n.el-input-group__append,\n.el-input-group__prepend {\n  background-color: #409eff;\n  color: #fff;\n}\n.feedback-list {\n  width: 100%;\n  padding-top: 10px;\n}\n.block {\n  text-align: right;\n  margin-top: 10px;\n}\n.mytable.el-table th {\n  background: #f0f0f0 !important;\n}\n.main .single-page-con {\n  background: #fff;\n}\n",""])},1867:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return e.showRooterView?e.showRooterView?a("div",[a("router-view")],1):e._e():a("div",{staticClass:"feedbacklist"},[a("div",[a("el-input",{staticStyle:{width:"200px",display:"inline-block"},attrs:{size:"medium",placeholder:e.$t("Button.Keyword-search")},model:{value:e.formQuery.keyWord,callback:function(t){e.$set(e.formQuery,"keyWord",t)},expression:"formQuery.keyWord"}}),e._v(" "),a("el-button",{staticStyle:{padding:"10px 10px"},attrs:{slot:"append",type:"primary",icon:"el-icon-search"},on:{click:e.getData},slot:"append"},[e._v(e._s(e.$t("Button.Inquire")))])],1),e._v(" "),a("el-table",{staticClass:"feedback-list mytable",attrs:{data:e.tableData,"row-key":"id","expand-row-keys":e.expands},on:{"row-click":e.rowClick}},[a("el-table-column",{attrs:{type:"expand"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){e.to("feedback/details",t.row.id)}}},[e._v(e._s(e.$t("feedback.details")))]),e._v(" "),a("el-button",{attrs:{type:"primary",size:"small"},on:{click:function(a){e.to("feedback/handle",t.row.id)}}},[e._v(e._s(e.$t("feedback.dispose")))])],1)]}}])}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("feedback.number"),width:"100",prop:"id"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("feedback.product"),width:"150",prop:"bsPrName"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("feedback.supplier"),prop:"bsSuppCompanyName"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("feedback.time"),prop:"bsCreatedTime"}}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("feedback.type"),width:"130"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.$t("feedback.feedbacktype["+t.row.bsType+"]")))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("feedback.state")},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e.$t("feedback.statusDesc["+t.row.bsStatus+"]")))])]}}])})],1),e._v(" "),a("div",{staticClass:"block"},[a("el-pagination",{staticClass:"pull-right clearfix",attrs:{current:1,"current-page":e.queryParams.page,"page-sizes":e.pageSizesList,"page-size":e.queryParams.rows,layout:"total, sizes, prev, pager, next, jumper","page-size-opts":e.pageSizesList,total:e.totalCount},on:{"current-change":e.changePage,"size-change":e.SizeChange,"update:currentPage":function(t){e.$set(e.queryParams,"page",t)}}})],1)],1)},o=[];n._withStripped=!0;var i={render:n,staticRenderFns:o};t.default=i}});