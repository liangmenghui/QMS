webpackJsonp([7],{1606:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=o(24),a=function(t){return t&&t.__esModule?t:{default:t}}(s);e.default={data:function(){return{showRooterView:!1,approvedItemRecords:{},approvedFlowRecordData:{flowBy:{}},supplierInfo:{},productInfo:{},recordInfo:{},modal_dialog:!1,comfirm_dialog:{bsResult:0,bsDesc:""},allItmesPassed:!0,perms:{}}},created:function(){this.bsFlowRecordId=this.$route.query.bsFlowRecordId,this.comfirm_dialog.id=this.bsFlowRecordId,this.showRooterView=this.$route.matched.length>3,this.getData()},watch:{$route:function(t,e){this.showRooterView=t.matched.length>3}},methods:{getData:function(){var t=this;this.api.ApprovedItem.getrecords({bsFlowRecordId:this.bsFlowRecordId}).then(function(e){t.approvedItemRecords=[];for(var o=0;o<e.data.rows.length;o++){var s=e.data.rows[o];if(t.allItmesPassed&&3==s.bsStatus&&(t.allItmesPassed=!1),void 0==s.itemsBy)return s;var a=s.itemsBy.recorderSet;if(a.sort(function(t,e){return t.bsPriority>e.bsPriority}),void 0!=a&&a.length>0){for(var r=[],i=0;i<a.length;i++){var n=a[i];n.bsIsDel||r.push(n)}s.users=r.map(function(t){return t.recorderBy.bsName})}t.approvedItemRecords.push(s)}}),this.api.approvedFlowRecord.getlist({id:this.bsFlowRecordId}).then(function(e){t.approvedFlowRecordData=e.data.rows[0],t.perms.edit=(t.$Util.hasPerm("VERIFY")||t.approvedFlowRecordData.flowBy.approvedBy.bsName==a.default.get("user"))&&t.approvedFlowRecordData.bsStatus<2,t.getInfo()})},getInfo:function(){var t=this;void 0!=this.approvedFlowRecordData.bsPrId&&this.api.productinfo.getlist({id:this.approvedFlowRecordData.bsPrId}).then(function(e){t.productInfo=e.data.rows[0],t.$store.commit("updateProductDataStates",t.productInfo)}),void 0!=this.approvedFlowRecordData.bsSuppId&&this.api.supplierinfo.getlist({id:this.approvedFlowRecordData.bsSuppId}).then(function(e){t.supplierInfo=e.data.rows[0],t.$store.commit("updateSupplierDataStates",t.supplierInfo)})},pushToChildWithData:function(t){var e={itemsRecordId:t.id,itemsId:t.bsItemsId,bsFlowRecordId:this.bsFlowRecordId};switch(1==this.approvedFlowRecordData.bsType&&e.bsType,this.approvedFlowRecordData.bsType){case 1:e.bsType=21;break;case 2:e.bsType=11;break;case 3:e.bsType=22}this.$store.commit("updateItemRecordDataStates",t),this.$router.push({path:t.itemsBy.bsRouter,query:e})},pushToApprovedReport:function(t){this.$router.push({path:t}),this.$store.commit("updateFlowRecordDataStates",this.approvedItemRecords)},closeApproved:function(t){this.modal_dialog=!0,this.comfirm_dialog.bsResult=t},comfirmApproved:function(){var t=this;0==this.comfirm_dialog.bsResult?this.api.approvedFlowRecord.close(this.comfirm_dialog).then(function(e){t.$Message.info("关闭审核成功"),t.getData()}):this.api.approvedFlowRecord.edit(this.comfirm_dialog).then(function(e){t.$Message.info("提交成功"),t.getData()})}}}},1800:function(t,e,o){var s=o(1801);"string"==typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);o(28)("5bf0f73b",s,!1,{})},1801:function(t,e,o){e=t.exports=o(27)(!1),e.push([t.i,'\n.circle{\r\n\tfont-size: 14px;color: #27558e;width: 10px;height: 10px;display: inline-block;background-color:#27558e;\r\n\tborder-radius: 50%; \r\n    margin-right: 5px\n}\n.red{color:#fd5822;\n}\n.blue{color: #409EFF;\n}\n.green{color: #67C23A;\n}\n.orange{color: #fd5822;\n}\n.gray{color: #333;\n}\n.bgred{background-color:#fd5822;\n}\n.bgblue{background-color: #409EFF;\n}\n.bggreen{background-color: #67C23A;\n}\n.bgorange{background-color: #fd5822;\n}\n.bggray{background-color: #333;\n}\n.mt10{margin-top:10px;\n}\n.title-panel{\r\n\t\t\tpadding: 20px 40px;\r\n\t\t\t/*margin-bottom: 30px;*/\r\n   \t\t\tbackground-color: #FFFFFF;\r\n    \t\tborder-radius: 3px;\r\n   \t\t\t-moz-border-radius: 3px;\r\n   \t\t\t -webkit-border-radius: 3px;\r\n   \t\t\t /*box-shadow: 0 1px 5px 0 rgba(0, 0, 0, 0.05);*/\r\n   \t\t\t width: 100%;\n}\n.setpadding{padding: 12px 40px;border: 1px solid #ddd;\r\n    border-top: none;\n}\n.layout-content{\r\n\tbackground-color: white;\n}\n.Title{\r\n\t\tpadding-left: 1%;padding-right: 1%;\r\n\t\theight: 45px;line-height: 50px; font-size: 14px;\r\n\t\t font-family: "Microsoft YaHei";font-weight:600;\r\n\t\t text-align: center; margin-bottom: 15px;\r\n         background: #f0f0f0;\r\n         margin-top:10px;\n}\n.td1{\r\n\t\twidth: 25%;\r\n\t\theight: 55px;\r\n\t\ttext-align: center;\r\n\t\tfont-size: 14px;\r\n\t\tfont-family: "Microsoft YaHei";\n}\n.status{color:#657180;font-family: "Microsoft YaHei";font-size: 14px;\n}\n.cpshtd2{\r\n\t\theight: 50px;\r\n\t\tbackground-color: #f5f7f9;\r\n\t\tpadding-left: 50px;\n}\n.grid-content .ivu-icon{font-size: 20px;color:#888; margin-right: 10px;\n}\n.grid-content{font-size: 15px;\n}\n.top .fr{float:right; margin-right: 6%;\n}\n.top .fl{float:left;display: inline-block;width: 70%;line-height: 45px;\n}\n.RecordeBtn{width: 140px;padding:12px 12px;\n}\n.tag{text-align: right;margin-left: 40%;\n}\n.fr .el-button--success{padding:10px 10px;\n}\n.icon{font-size: 14px;\n}\n.top .grid-content .ivu-icon{color:#fd5822;\n}\n.bscode{line-height: 50px;color:16px;height: 50px;\n}\n.bscode .icon{font-size: 18px;color: blue\n}\n.approvedItem .el-button{padding: 9px 20px;\n}\n.approvedstatus{float:right;font-size:14px;margin-right: 6%;\n}\n.panel1{background: #f1f6fc!important\n}\r\n\r\n',""])},1802:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=o(2),a=function(t){return t&&t.__esModule?t:{default:t}}(s),r=function(){var t=this,e=t.$createElement,o=t._self._c||e;return t.showRooterView?t.showRooterView?o("div",[o("router-view")],1):t._e():o("div",{},[o("el-row",{staticClass:"top"},[o("el-col",{staticClass:"title-panel panel1",attrs:{span:24}},[o("div",{staticClass:"grid-content bg-purple-dark"},[o("div",{staticClass:"fl"},[o("Icon",{staticClass:"icon",attrs:{type:"information-circled"}}),t._v(t._s(t.approvedFlowRecordData.flowBy.bsName))],1),t._v(" "),o("div",{staticClass:"fl"},[o("Icon",{attrs:{type:"person"}}),t._v("\n                    "+t._s(t.$t("ApprovedFlow.Principal"))+": "+t._s(t.approvedFlowRecordData.flowBy.approvedBy.bsName)+"\n                ")],1),t._v(" "),o("div",{staticClass:"fr"},[o("el-button",{staticStyle:{width:"140px",padding:"8px 0px"},attrs:{type:"success"},on:{click:function(e){t.pushToApprovedReport("approved/report")}}},[t._v("\n  \t\t\t\t\t\t"+t._s(t.$t("approved.AuditReport"))+"\n  \t\t\t\t\t")])],1)])]),t._v(" "),o("el-col",{staticClass:"title-panel setpadding",attrs:{span:24}},[o("div",{staticClass:"grid-content bg-purple-dark"},[o("div",{staticClass:"approvedstatus"},[2==t.approvedFlowRecordData.bsStatus?o("el-tag",{staticStyle:{width:"140px","font-size":"14px"},attrs:{color:"white"}},[t._v("\n                        "+t._s(t.$t("approved.resultLabel"))+" ："+t._s(t.$t("approved.flow_result["+t.approvedFlowRecordData.bsResult+"]"))+"\n                        ")]):t._e(),t._v(" "),t.approvedFlowRecordData.bsStatus<2?o("el-tag",{staticStyle:{width:"140px","font-size":"14px"},attrs:{color:"white"}},[t._v("\n                        "+t._s(t.$t("approved.statusLabel"))+" ："+t._s(t.$t("approved.flow_status["+t.approvedFlowRecordData.bsStatus+"]"))+"\n                        ")]):t._e()],1),t._v(" "),t.approvedFlowRecordData.flowBy.bsType<=20?o("div",[o("div",{staticClass:"fl"},[o("Icon",{attrs:{type:"ios-color-filter"}}),t._v(t._s(t.$t("product.name"))+": "+t._s(t.productInfo.bsPrName))],1),t._v(" "),o("br"),t._v(" "),o("div",{staticClass:"fl bscode"},[o("Icon",{staticClass:"icon",attrs:{type:"ios-list"}}),t._v(t._s(t.$t("product.code"))+": "+t._s(t.productInfo.bsPrCode))],1)]):t._e(),t._v(" "),t.approvedFlowRecordData.flowBy.bsType>20?o("div",[o("div",{staticClass:"fl"},[t._v(t._s(t.$t("product.SuppChieseName"))+": "+t._s(t.supplierInfo.bsSuppChieseName)+" "+t._s(t.supplierInfo.bsSuppEnglishName))]),t._v(" "),o("br"),t._v(" "),o("div",{staticClass:"fl"},[t._v(t._s(t.$t("product.SuppCode"))+": "+t._s(t.supplierInfo.bsSuppCode))])]):t._e()])])],1),t._v(" "),o("div",{staticClass:"layout-content"},[o("div",{staticClass:"Title"},[o("el-row",{attrs:{gutter:20}},[o("el-col",{attrs:{span:6}},[o("div",{staticClass:"grid-content bg-purple"},[t._v(t._s(t.$t("approved.AuditingItem")))])]),t._v(" "),o("el-col",{attrs:{span:6}},[o("div",{staticClass:"grid-content bg-purple"},[t._v(t._s(t.$t("approved.statusLabel")))])]),t._v(" "),o("el-col",{attrs:{span:6}},[o("div",{staticClass:"grid-content bg-purple"},[t._v(t._s(t.$t("approved.principal")))])]),t._v(" "),o("el-col",{attrs:{span:6}},[o("div",{staticClass:"grid-content bg-purple"},[t._v(t._s(t.$t("approved.note")))])])],1)],1),t._v(" "),o("table",{staticClass:"approvedItem",staticStyle:{width:"100%"}},t._l(t.approvedItemRecords,function(e){return o("tr",[o("td",{staticClass:"td1"},[o("el-button",{staticStyle:{width:"220px",padding:"10px 0px"},attrs:{plain:"",type:"success",disabled:0==e.bsStatus},on:{click:function(o){t.pushToChildWithData(e)}}},[t._v("\n                        "+t._s(e.itemsBy.bsName)+"\n                    ")])],1),t._v(" "),o("td",{staticClass:"td1",class:(0,a.default)({gray:0==e.bsStatus,blue:1==e.bsStatus,green:2==e.bsStatus,orange:3==e.bsStatus},"gray",4==e.bsStatus)},[o("span",{staticClass:"circle",class:(0,a.default)({bggray:0==e.bsStatus,bgblue:1==e.bsStatus,bggreen:2==e.bsStatus,bgorange:3==e.bsStatus},"bggray",4==e.bsStatus)}),t._v(t._s(t.$t("approved.status["+e.bsStatus+"]"))+"\n                ")]),t._v(" "),o("td",{staticClass:"td1"},[t._v(t._s(e.users.toString()))]),t._v(" "),o("td",{staticClass:"td1"},[t._v(t._s(e.bsDesc))])])})),t._v(" "),o("div",{staticStyle:{"padding-top":"40px","padding-bottom":"40px"}},[o("center",[o("div",[t._v(t._s(t.approvedFlowRecordData.bsDesc))])])],1),t._v(" "),t.perms.edit?o("div",{staticStyle:{"padding-top":"0px","padding-bottom":"40px"}},[0==t.approvedFlowRecordData.bsStatus?o("center",[o("el-button",{staticStyle:{width:"10%",padding:"10px 0px"},attrs:{type:"danger"},on:{click:function(e){t.closeApproved(0)}}},[t._v(t._s(t.$t("approved.CloseTheAudit")))])],1):t._e(),t._v(" "),1==t.approvedFlowRecordData.bsStatus?o("center",[t.allItmesPassed?o("el-button",{staticStyle:{width:"10%",padding:"10px 0px"},attrs:{type:"success"},on:{click:function(e){t.closeApproved(1)}}},[t._v(t._s(t.$t("Button.Approved")))]):t._e(),t._v(" "),t.allItmesPassed?t._e():o("el-button",{staticStyle:{width:"10%",padding:"10px 0px"},attrs:{type:"success"},on:{click:function(e){t.closeApproved(1)}}},[t._v(t._s(t.$t("Button.yieldApproved")))]),t._v(" "),o("el-button",{staticStyle:{width:"10%",padding:"10px 0px"},attrs:{type:"danger"},on:{click:function(e){t.closeApproved(2)}}},[t._v(t._s(t.$t("Button.AuditFailed")))])],1):t._e()],1):t._e(),t._v(" "),o("Modal",{on:{"on-ok":function(e){t.comfirmApproved()}},model:{value:t.modal_dialog,callback:function(e){t.modal_dialog=e},expression:"modal_dialog"}},[o("el-form",{attrs:{"label-width":"80"}},[o("p",{staticStyle:{"font-size":"16px",color:"#67C23A"}},[t._v(t._s(t.$t("approved.flow_corfirm["+t.comfirm_dialog.bsResult+"]")))]),t._v(" "),o("el-form-item",{attrs:{label:"说明",prop:""}},[o("el-input",{attrs:{type:"textarea"},model:{value:t.comfirm_dialog.bsDesc,callback:function(e){t.$set(t.comfirm_dialog,"bsDesc",e)},expression:"comfirm_dialog.bsDesc"}})],1)],1)],1)],1)],1)},i=[];r._withStripped=!0;var n={render:r,staticRenderFns:i};e.default=n},442:function(t,e,o){"use strict";function s(t){p||o(1800)}Object.defineProperty(e,"__esModule",{value:!0});var a=o(1606),r=o.n(a);for(var i in a)"default"!==i&&function(t){o.d(e,t,function(){return a[t]})}(i);var n=o(1802),d=o.n(n),p=!1,l=o(1),c=s,u=l(r.a,d.a,!1,c,null,null);u.options.__file="src\\view\\approved\\ApprovedFlowRecordDetails.vue",e.default=u.exports}});