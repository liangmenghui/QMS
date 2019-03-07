webpackJsonp([19],{1047:function(t,e,s){"use strict";function a(t){l||s(1856)}Object.defineProperty(e,"__esModule",{value:!0});var n=s(1626),i=s.n(n);for(var o in n)"default"!==o&&function(t){s.d(e,t,function(){return n[t]})}(o);var p=s(1858),c=s.n(p),l=!1,r=s(1),d=a,u=r(i.a,c.a,!1,d,null,null);u.options.__file="src\\view\\approved\\ProductInspectSummaryApproved.vue",e.default=u.exports},1110:function(t,e,s){"use strict";function a(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var n=s(15),i=a(n),o=s(23),p=a(o);e.default={props:["approvedItemRecord","type","data","needCoach"],data:function(){return{showFailedModel:!1,showSuccessModel:!1,showRejectModel:!1,isCoach:1,resultDesc:"",flowDesc:""}},created:function(){},methods:{showModel:function(t){3==t?this.showFailedModel=!0:2==t?this.showRejectModel=!0:this.showSuccessModel=!0},approved:function(t){var e=this;if(0!=t){var s={bsItemsId:this.approvedItemRecord.itemsBy.id,bsItemsRecordId:this.approvedItemRecord.id,bsResult:t,itemsDesc:this.flowDesc,resultDesc:this.resultDesc};this.needCoach&&(s.isCoach=this.isCoach),this.api.Audit.approved(s).then(function(s){e.$Message.info("提交审核结果完成"),e.bsStatus=t;var a=e.$route.matched[e.$route.matched.length-2].path,n={path:a,query:{bsFlowRecordId:e.$route.query.bsFlowRecordId}};e.$router.replace(n)})}},saveRecord:function(t){var e=this;if("audits"==this.type){for(var s=new Array,a=this.data,n=0;n<a.length;n++){var o={};o.bsItemsRecoreId=this.approvedItemRecord.id,o.bsTermsId=a[n].ID,o.bsScore=a[n].BS_SCORE,o.bsDesc=a[n].BS_DESC,void 0!=a[n].SCORE_ID&&(o.id=a[n].SCORE_ID),s.push(o)}(0,p.default)(s);this.api.Audit.records({approvedTermsScoreStr:(0,p.default)(s)}).then(function(s){e.$emit("updateTermsRecord"),e.$Message.info("保存成功"),e.approved(t)})}else if("inspect"==this.type){var c=(0,i.default)({},this.data);c=this.$Util.formattedParams(c),this.api.shipmentInspect.edit(c).then(function(s){e.$Message.info("保存成功"),e.approved(t)})}else if("quality"==this.type){var c=(0,i.default)({},this.data);c=this.$Util.formattedParams(c),this.api.qualityInspect.edit(c).then(function(s){e.$Message.info("保存成功"),e.approved(t)})}else this.approved(t)}}}},1111:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={props:["approvedData"],data:function(){return{}}}},1152:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s(1110),n=s.n(a);for(var i in a)"default"!==i&&function(t){s.d(e,t,function(){return a[t]})}(i);var o=s(1153),p=s.n(o),c=s(1),l=c(n.a,p.a,!1,null,null,null);l.options.__file="src\\components\\ApprovedAction.vue",e.default=l.exports},1153:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"component"},[s("center",[s("el-button",{staticStyle:{padding:"7px 20px"},attrs:{type:"primary"},on:{click:function(e){t.saveRecord(0)}}},[s("i",{staticClass:"el-icon-news"}),t._v("  "+t._s(t.$t("Button.SaveChanges"))+"\n        ")]),t._v(" "),t.approvedItemRecord.bsStep>1?s("el-button",{staticStyle:{padding:"7px 20px","margin-left":"3%"},attrs:{type:"danger"},on:{click:function(e){t.showModel(2)}}},[s("i",{staticClass:"el-icon-close"}),t._v("  "+t._s(t.$t("Button.DismissReview"))+"\n        ")]):t._e(),t._v(" "),s("el-button",{staticStyle:{padding:"7px 20px","margin-left":"3%"},attrs:{type:"danger"},on:{click:function(e){t.showModel(3)}}},[s("i",{staticClass:"el-icon-close"}),t._v("  "+t._s(t.$t("Button.AuditFailed"))+"\n        ")]),t._v(" "),s("el-button",{staticStyle:{padding:"7px 20px","margin-left":"3%"},attrs:{type:"success"},on:{click:function(e){t.showModel(1)}}},[s("i",{staticClass:"el-icon-check"}),t._v("  "+t._s(t.$t("Button.Approved"))+"\n        ")]),t._v(" "),s("Modal",{on:{"on-ok":function(e){t.saveRecord(3)}},model:{value:t.showFailedModel,callback:function(e){t.showFailedModel=e},expression:"showFailedModel"}},[s("el-form",{attrs:{"label-width":"80"}},[s("p",{staticStyle:{"font-size":"16px",color:"#F56C6C"}},[t._v("确认当前审核失败？")]),t._v(" "),void 0!=t.flowDesc&&t.flowDesc.length>0?s("el-form-item",{attrs:{label:"结果说明"}},[s("el-input",{attrs:{type:"textarea"},model:{value:t.flowDesc,callback:function(e){t.flowDesc=e},expression:"flowDesc"}})],1):t._e(),t._v(" "),s("el-form-item",{attrs:{label:"审核意见",prop:""}},[s("el-input",{attrs:{type:"textarea"},model:{value:t.resultDesc,callback:function(e){t.resultDesc=e},expression:"resultDesc"}})],1)],1)],1),t._v(" "),s("Modal",{on:{"on-ok":function(e){t.saveRecord(1)}},model:{value:t.showSuccessModel,callback:function(e){t.showSuccessModel=e},expression:"showSuccessModel"}},[s("el-form",{attrs:{"label-width":"80"}},[s("p",{staticStyle:{"font-size":"16px",color:"#67C23A"}},[t._v("确认当前审核通过？")]),t._v(" "),s("el-form-item",{attrs:{label:"结果说明",prop:""}},[s("el-input",{attrs:{type:"textarea"},model:{value:t.flowDesc,callback:function(e){t.flowDesc=e},expression:"flowDesc"}})],1),t._v(" "),s("el-form-item",{attrs:{label:"审核意见",prop:""}},[s("el-input",{attrs:{type:"textarea"},model:{value:t.resultDesc,callback:function(e){t.resultDesc=e},expression:"resultDesc"}})],1),t._v(" "),t.needCoach?s("el-form-item",{attrs:{label:t.$t("supplier.coach")}},[s("el-select",{model:{value:t.isCoach,callback:function(e){t.isCoach=e},expression:"isCoach"}},[s("el-option",{attrs:{label:"是",value:1}}),t._v(" "),s("el-option",{attrs:{label:"否",value:2}})],1)],1):t._e()],1)],1),t._v(" "),s("Modal",{on:{"on-ok":function(e){t.saveRecord(2)}},model:{value:t.showRejectModel,callback:function(e){t.showRejectModel=e},expression:"showRejectModel"}},[s("el-form",{attrs:{"label-width":"80"}},[s("p",{staticStyle:{"font-size":"16px",color:"#67C23A"}},[t._v("驳回当前审核？")]),t._v(" "),s("el-form-item",{attrs:{label:"结果说明",prop:""}},[s("el-input",{attrs:{type:"textarea"},model:{value:t.flowDesc,callback:function(e){t.flowDesc=e},expression:"flowDesc"}})],1),t._v(" "),s("el-form-item",{attrs:{label:"审核意见",prop:""}},[s("el-input",{attrs:{type:"textarea"},model:{value:t.resultDesc,callback:function(e){t.resultDesc=e},expression:"resultDesc"}})],1)],1)],1)],1)],1)},n=[];a._withStripped=!0;var i={render:a,staticRenderFns:n};e.default=i},1154:function(t,e,s){"use strict";function a(t){l||s(1155)}Object.defineProperty(e,"__esModule",{value:!0});var n=s(1111),i=s.n(n);for(var o in n)"default"!==o&&function(t){s.d(e,t,function(){return n[t]})}(o);var p=s(1157),c=s.n(p),l=!1,r=s(1),d=a,u=r(i.a,c.a,!1,d,null,null);u.options.__file="src\\components\\approvedResultRecord.vue",e.default=u.exports},1155:function(t,e,s){var a=s(1156);"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);s(28)("45c6c753",a,!1,{})},1156:function(t,e,s){e=t.exports=s(27)(!1),e.push([t.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",""])},1157:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticStyle:{"margin-top":"60px"}},[s("Card",[s("p",{attrs:{slot:"title"},slot:"title"},[t._v("审核状态: "+t._s(t.$t("approved.status["+t.approvedData.bsStatus+"]")))]),t._v(" "),s("p",{attrs:{slot:"title"},slot:"title"},[t._v("结果说明: "+t._s(t.approvedData.bsDesc))]),t._v(" "),t.approvedData.resultSet.length>0?s("div",[s("el-table",{staticStyle:{width:"100%"},attrs:{data:t.approvedData.resultSet}},[s("el-table-column",{attrs:{label:"审核结果"},scopedSlots:t._u([{key:"default",fn:function(e){return[s("span",[t._v(t._s(t.$t("product.bsResult["+(e.row.bsResult-1)+"]")))])]}}])}),t._v(" "),s("el-table-column",{attrs:{prop:"resultBy.bsName",label:"负责人"}}),t._v(" "),s("el-table-column",{attrs:{prop:"bsDesc",label:"审核意见"}}),t._v(" "),s("el-table-column",{attrs:{prop:"bsCreatedTime",label:"时间"}})],1)],1):t._e()])],1)},n=[];a._withStripped=!0;var i={render:a,staticRenderFns:n};e.default=i},1626:function(t,e,s){"use strict";function a(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var n=s(24),i=a(n),o=s(1152),p=a(o),c=s(1154),l=a(c);e.default={components:{ApprovedAction:p.default,approvedResultRecord:l.default},data:function(){return{shipmentInspectData:{},productInfo:{},perms:{}}},created:function(){this.getInspectData(),this.productInfo=this.$store.getters.getProductData,this.approvedItemRecord=this.$store.getters.getItemRecordData,this.perms.edit=(this.approvedItemRecord.users.includes(i.default.get("user"))||this.Util.hasPerm("EDIT"))&&1==this.approvedItemRecord.bsStatus},methods:{getInspectData:function(){var t=this,e={bsPrId:this.productInfo.id,bsFlowRecordId:this.$route.query.bsFlowRecordId};this.api.shipmentInspect.getlist(e).then(function(e){e.data.rows.length&&(t.shipmentInspectData=e.data.rows[0])})}}}},1856:function(t,e,s){var a=s(1857);"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);s(28)("707703ca",a,!1,{})},1857:function(t,e,s){e=t.exports=s(27)(!1),e.push([t.i,"\n.productinspectsummaryapproved #body{\n\tmargin-top: 1px;height:500px;background-color: white;padding-top: 40px;\n}\n.productinspectsummaryapproved .body-div{\n\theight: 50px;\n}\n.productinspectsummaryapproved .text{\n\tfloat: left;width: 80px;padding-top: 10px;font-size: 14px;\n}\n.productinspectsummaryapproved .input-div{\n\tfloat: left;\n}\n.productinspectsummaryapproved .input-div .el-input{\n\twidth: 200px;\n}\n.productinspectsummaryapproved .textarea-div{\n\tbackground-color: white;padding-bottom:30px;padding-left: 8%;\n}\n.productinspectsummaryapproved .textarea-div p{\n\tfont-size: 14px;\n}\n.productinspectsummaryapproved #bottom{\n\theight:150px;margin-bottom: 20px;background-color: white;padding-left: 5%;\n}\n",""])},1858:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"productinspectsummaryapproved"},[s("Row",{style:{marginBottom:"20px"}},[s("center",[s("h4",{staticStyle:{"font-size":"16px"}},[t._v("出货检验报告汇总")])])],1),t._v(" "),s("Row",{style:{marginBottom:"20px"}},[s("Card",[s("p",{attrs:{slot:"title"},slot:"title"},[s("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"document"}}),t._v("\n                     "+t._s(t.$t("approved.SummaryOfShipmentInspectionReport"))+"\n               \t")],1),t._v(" "),t.perms.edit?t._e():s("el-tag",{staticStyle:{"font-size":"14px"},attrs:{slot:"extra",color:"white"},slot:"extra"},[t._v("\n\t\t\t\t\t"+t._s(t.$t("approved.statusLabel"))+"："+t._s(t.$t("approved.status["+t.approvedItemRecord.bsStatus+"]"))+"\n\t\t\t\t")]),t._v(" "),s("div",{attrs:{id:"body"}},[s("Col",{attrs:{md:2}},[s("div",{staticStyle:{width:"8%",height:"1px"}})]),t._v(" "),s("Col",{attrs:{md:10}},[s("div",{staticClass:"topbody-div"},[s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("product.name"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.productInfo.bsPrName,callback:function(e){t.$set(t.productInfo,"bsPrName",e)},expression:"productInfo.bsPrName"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("product.code"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.productInfo.bsPrCode,callback:function(e){t.$set(t.productInfo,"bsPrCode",e)},expression:"productInfo.bsPrCode"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.DrawingNumber"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.InspectionStandard"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsInspectStandard,callback:function(e){t.$set(t.shipmentInspectData,"bsInspectStandard",e)},expression:"shipmentInspectData.bsInspectStandard"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.TestLevel"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsInspectLevel,callback:function(e){t.$set(t.shipmentInspectData,"bsInspectLevel",e)},expression:"shipmentInspectData.bsInspectLevel"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.DateInProduced"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsProductDate,callback:function(e){t.$set(t.shipmentInspectData,"bsProductDate",e)},expression:"shipmentInspectData.bsProductDate"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.InspectionDate"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsInspectDate,callback:function(e){t.$set(t.shipmentInspectData,"bsInspectDate",e)},expression:"shipmentInspectData.bsInspectDate"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.ProductQuantity"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsProductNum,callback:function(e){t.$set(t.shipmentInspectData,"bsProductNum",e)},expression:"shipmentInspectData.bsProductNum"}})],1)])])]),t._v(" "),s("Col",{attrs:{md:12}},[s("div",{staticClass:"topbody-div"},[s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.BoxNumber"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsBoxesNum,callback:function(e){t.$set(t.shipmentInspectData,"bsBoxesNum",e)},expression:"shipmentInspectData.bsBoxesNum"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.Customer"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsCustomer,callback:function(e){t.$set(t.shipmentInspectData,"bsCustomer",e)},expression:"shipmentInspectData.bsCustomer"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.ContractNumber"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsContractNo,callback:function(e){t.$set(t.shipmentInspectData,"bsContractNo",e)},expression:"shipmentInspectData.bsContractNo"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.BatchNumber"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsBatchNo,callback:function(e){t.$set(t.shipmentInspectData,"bsBatchNo",e)},expression:"shipmentInspectData.bsBatchNo"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.AQLLevel"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsAqlLevel,callback:function(e){t.$set(t.shipmentInspectData,"bsAqlLevel",e)},expression:"shipmentInspectData.bsAqlLevel"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.NumberReceivers"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsAcceptNum,callback:function(e){t.$set(t.shipmentInspectData,"bsAcceptNum",e)},expression:"shipmentInspectData.bsAcceptNum"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.NotNumberReceivers"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsRejectNum,callback:function(e){t.$set(t.shipmentInspectData,"bsRejectNum",e)},expression:"shipmentInspectData.bsRejectNum"}})],1)]),t._v(" "),s("div",{staticClass:"body-div"},[s("div",{staticClass:"text"},[t._v(t._s(t.$t("approved.SamplingSize"))+"：")]),t._v(" "),s("div",{staticClass:"input-div"},[s("el-input",{attrs:{readonly:""},model:{value:t.shipmentInspectData.bsSamplesNum,callback:function(e){t.$set(t.shipmentInspectData,"bsSamplesNum",e)},expression:"shipmentInspectData.bsSamplesNum"}})],1)])])])],1),t._v(" "),s("div",{staticClass:"textarea-div"},[s("p",[t._v(t._s(t.$t("approved.InspectionInstruction")))]),t._v(" "),s("el-input",{staticStyle:{width:"50%"},attrs:{type:"textarea",readonly:"",autosize:{minRows:4}},model:{value:t.shipmentInspectData.bsInspectDesc,callback:function(e){t.$set(t.shipmentInspectData,"bsInspectDesc",e)},expression:"shipmentInspectData.bsInspectDesc"}})],1),t._v(" "),s("div",{staticClass:"textarea-div"},[s("p",[t._v(t._s(t.$t("approved.IndividualOpinion")))]),t._v(" "),s("el-input",{staticStyle:{width:"50%"},attrs:{type:"textarea",readonly:!t.perms.edit,autosize:{minRows:4}},model:{value:t.shipmentInspectData.bsPersonalAdvise,callback:function(e){t.$set(t.shipmentInspectData,"bsPersonalAdvise",e)},expression:"shipmentInspectData.bsPersonalAdvise"}})],1),t._v(" "),s("div",{staticClass:"textarea-div"},[s("p",[t._v(t._s(t.$t("approved.CompanyViews")))]),t._v(" "),s("el-input",{staticStyle:{width:"50%"},attrs:{type:"textarea",readonly:!t.perms.edit,autosize:{minRows:4}},model:{value:t.shipmentInspectData.bsCompanyAdvise,callback:function(e){t.$set(t.shipmentInspectData,"bsCompanyAdvise",e)},expression:"shipmentInspectData.bsCompanyAdvise"}})],1),t._v(" "),s("div",{staticClass:"textarea-div",staticStyle:{"padding-bottom":"100px"}},[s("p",[t._v(t._s(t.$t("approved.note")))]),t._v(" "),s("el-input",{staticStyle:{width:"50%"},attrs:{type:"textarea",readonly:!t.perms.edit,autosize:{minRows:4}},model:{value:t.shipmentInspectData.bsRemark,callback:function(e){t.$set(t.shipmentInspectData,"bsRemark",e)},expression:"shipmentInspectData.bsRemark"}})],1)],1)],1),t._v(" "),s("approvedResultRecord",{attrs:{approvedData:t.approvedItemRecord}}),t._v(" "),t.perms.edit?s("div",{attrs:{id:"bottom"}},[s("ApprovedAction",{attrs:{type:"inspect",data:t.shipmentInspectData,approvedItemRecord:t.approvedItemRecord}})],1):t._e()],1)},n=[];a._withStripped=!0;var i={render:a,staticRenderFns:n};e.default=i}});