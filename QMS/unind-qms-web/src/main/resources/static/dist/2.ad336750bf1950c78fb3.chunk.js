webpackJsonp([2],{1325:function(e,t,a){"use strict";function o(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var s=a(11),r=o(s),i=a(14),l=o(i);t.default={props:["approvedItemRecord","type","data","sampleRegularRecords","perms"],data:function(){return{showFailedModel:!1,showSuccessModel:!1,showRejectModel:!1,isCoach:1,resultDesc:"",flowDesc:""}},created:function(){},mounted:function(){},methods:{showModel:function(e){3==e?this.showFailedModel=!0:2==e?this.showRejectModel=!0:this.showSuccessModel=!0},approved:function(e){var t=this;if(0!=e){var a={bsItemsId:this.approvedItemRecord.itemsBy.id,bsItemsRecordId:this.approvedItemRecord.id,bsResult:e,itemsDesc:this.flowDesc,resultDesc:this.resultDesc};this.api.Audit.approved(a).then(function(a){t.$Message.info("提交审核结果完成"),t.bsStatus=e;var o=t.$route.matched[t.$route.matched.length-2].path,s={path:o,query:{bsFlowRecordId:t.$route.query.bsFlowRecordId,refresh:!0}};t.$router.replace(s)})}else{var o=this.$route.matched[this.$route.matched.length-2].path,s={path:o,query:{bsFlowRecordId:this.$route.query.bsFlowRecordId,refresh:!0}};this.$router.replace(s)}},saveRecord:function(e){var t=this;if("audits"==this.type){for(var a=new Array,o=this.data,s=0;s<o.length;s++){var i={};i.bsItemsRecoreId=this.approvedItemRecord.id,i.bsTermsId=o[s].ID,i.bsScore=o[s].BS_SCORE,i.bsDesc=o[s].BS_DESC,void 0!=o[s].SCORE_ID&&(i.id=o[s].SCORE_ID),a.push(i)}(0,l.default)(a);this.api.Audit.records({approvedTermsScoreStr:(0,l.default)(a)}).then(function(a){t.$emit("updateTermsRecord"),t.$Message.info("保存成功"),t.approved(e)})}else if("inspect"==this.type){var n=(0,r.default)({},this.data);if(n=this.$Util.formattedParams(n),this.api.shipmentInspect.edit(n).then(function(a){t.$Message.info("保存成功"),t.approved(e)}),void 0!=this.sampleRegularRecords){for(var n={bsShipmentInspectId:n.id},d=[],s=0;s<this.sampleRegularRecords.length;s++)for(var c=this.sampleRegularRecords[s],p=0;p<c.records.length;p++){var u=c.records[p];d.push(this.$Util.formattedParams(u))}n.sampleRegularRecordStr=(0,l.default)(d),this.api.sampleRegularRecord.add(n).then(function(e){t.$emit("saved")})}}else if("quality"==this.type){var n=(0,r.default)({},this.data);n=this.$Util.formattedParams(n),this.api.qualityInspect.edit(n).then(function(a){t.$Message.info("保存成功"),t.approved(e)})}else this.approved(e)}}}},1326:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={props:["approvedData","isCoach"],data:function(){return{}}}},1350:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a(1325),s=a.n(o);for(var r in o)"default"!==r&&function(e){a.d(t,e,function(){return o[e]})}(r);var i=a(1351),l=a.n(i),n=a(1),d=n(s.a,l.a,!1,null,null,null);d.options.__file="src/views/qms/components/approvedAction.vue",t.default=d.exports},1351:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"component"},[void 0==e.perms||e.perms.edit?a("center",[a("el-button",{staticStyle:{padding:"7px 20px"},attrs:{type:"primary"},on:{click:function(t){e.saveRecord(0)}}},[a("i",{staticClass:"el-icon-news"}),e._v("  "+e._s(e.$t("Button.SaveChanges"))+"\n        ")]),e._v(" "),e.approvedItemRecord.bsStatus>0&&e.approvedItemRecord.bsStep>1?a("el-button",{staticStyle:{padding:"7px 20px","margin-left":"3%"},attrs:{type:"warning"},on:{click:function(t){e.showModel(2)}}},[a("i",{staticClass:"el-icon-warning"}),e._v("  "+e._s(e.$t("Button.DismissReview"))+"\n        ")]):e._e(),e._v(" "),e.approvedItemRecord.bsStatus>0?a("el-button",{staticStyle:{padding:"7px 20px","margin-left":"3%"},attrs:{type:"danger"},on:{click:function(t){e.showModel(3)}}},[a("i",{staticClass:"el-icon-close"}),e._v("  "+e._s(e.$t("Button.AuditFailed"))+"\n        ")]):e._e(),e._v(" "),e.approvedItemRecord.bsStatus>0?a("el-button",{staticStyle:{padding:"7px 20px","margin-left":"3%"},attrs:{type:"success"},on:{click:function(t){e.showModel(1)}}},[a("i",{staticClass:"el-icon-check"}),e._v("  "+e._s(e.$t("Button.Approved"))+"\n        ")]):e._e()],1):e._e(),e._v(" "),void 0!=e.perms&&e.perms.verify?a("center",[a("el-button",{staticStyle:{padding:"7px 20px"},attrs:{type:"primary"},on:{click:function(t){e.saveRecord(0)}}},[a("i",{staticClass:"el-icon-news"}),e._v("  "+e._s(e.$t("Button.SaveChanges"))+"\n        ")])],1):e._e(),e._v(" "),a("Modal",{on:{"on-ok":function(t){e.saveRecord(3)}},model:{value:e.showFailedModel,callback:function(t){e.showFailedModel=t},expression:"showFailedModel"}},[a("el-form",{attrs:{"label-width":"80"}},[a("p",{staticStyle:{"font-size":"16px",color:"#F56C6C"}},[e._v(e._s(e.$t("approved.failed_approved")))]),e._v(" "),a("el-form-item",{attrs:{label:e.$t("approved.result_comment")}},[a("el-input",{attrs:{type:"textarea"},model:{value:e.flowDesc,callback:function(t){e.flowDesc=t},expression:"flowDesc"}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("approved.item_comment")}},[a("el-input",{attrs:{type:"textarea"},model:{value:e.resultDesc,callback:function(t){e.resultDesc=t},expression:"resultDesc"}})],1)],1)],1),e._v(" "),a("Modal",{on:{"on-ok":function(t){e.saveRecord(1)}},model:{value:e.showSuccessModel,callback:function(t){e.showSuccessModel=t},expression:"showSuccessModel"}},[a("el-form",{attrs:{"label-width":"80"}},[a("p",{staticStyle:{"font-size":"16px",color:"#67C23A"}},[e._v(e._s(e.$t("approved.comfirm_approved")))]),e._v(" "),a("el-form-item",{attrs:{label:e.$t("approved.result_comment")}},[a("el-input",{attrs:{type:"textarea"},model:{value:e.flowDesc,callback:function(t){e.flowDesc=t},expression:"flowDesc"}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("approved.item_comment")}},[a("el-input",{attrs:{type:"textarea"},model:{value:e.resultDesc,callback:function(t){e.resultDesc=t},expression:"resultDesc"}})],1)],1)],1),e._v(" "),a("Modal",{on:{"on-ok":function(t){e.saveRecord(2)}},model:{value:e.showRejectModel,callback:function(t){e.showRejectModel=t},expression:"showRejectModel"}},[a("el-form",{attrs:{"label-width":"80"}},[a("p",{staticStyle:{"font-size":"16px",color:"#F56C6C"}},[e._v(e._s(e.$t("approved.reject_approved")))]),e._v(" "),a("el-form-item",{attrs:{label:e.$t("approved.result_comment")}},[a("el-input",{attrs:{type:"textarea"},model:{value:e.flowDesc,callback:function(t){e.flowDesc=t},expression:"flowDesc"}})],1),e._v(" "),a("el-form-item",{attrs:{label:e.$t("approved.item_comment")}},[a("el-input",{attrs:{type:"textarea"},model:{value:e.resultDesc,callback:function(t){e.resultDesc=t},expression:"resultDesc"}})],1)],1)],1)],1)},s=[];o._withStripped=!0;var r={render:o,staticRenderFns:s};t.default=r},1352:function(e,t,a){"use strict";function o(e){d||a(1353)}Object.defineProperty(t,"__esModule",{value:!0});var s=a(1326),r=a.n(s);for(var i in s)"default"!==i&&function(e){a.d(t,e,function(){return s[e]})}(i);var l=a(1355),n=a.n(l),d=!1,c=a(1),p=o,u=c(r.a,n.a,!1,p,null,null);u.options.__file="src/views/qms/components/approvedResultRecord.vue",t.default=u.exports},1353:function(e,t,a){var o=a(1354);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(19)("510cd950",o,!1)},1354:function(e,t,a){t=e.exports=a(18)(!1),t.push([e.i,"\n.component{margin-top: 30px;\n}\n",""])},1355:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"component"},[a("Card",[a("p",{staticStyle:{display:"inline"},attrs:{slot:"title"},slot:"title"},[e._v("审核状态: "+e._s(e.$t("approved.status["+e.approvedData.bsStatus+"]")))]),e._v(" "),a("p",{staticStyle:{display:"inline","margin-left":"30px"},attrs:{slot:"title"},slot:"title"},[e._v("结果说明: "+e._s(e.approvedData.bsDesc))]),e._v(" "),e.approvedData.resultSet.length>0?a("div",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.approvedData.resultSet}},[a("el-table-column",{attrs:{label:"审核结果"},scopedSlots:e._u([{key:"default",fn:function(t){return[void 0==e.isCoach?a("span",[e._v(e._s(e.$t("approved.itemResultDesc["+t.row.bsResult+"]")))]):e._e(),e._v(" "),void 0!=e.isCoach?a("span",[e._v(e._s(e.$t("approved.coachResultDesc["+t.row.bsResult+"]")))]):e._e()]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"resultBy.bsName",label:"负责人"}}),e._v(" "),a("el-table-column",{attrs:{prop:"bsDesc",label:"审核意见"}}),e._v(" "),a("el-table-column",{attrs:{prop:"bsCreatedTime",label:"时间"}})],1)],1):e._e()])],1)},s=[];o._withStripped=!0;var r={render:o,staticRenderFns:s};t.default=r},1428:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a(11),s=function(e){return e&&e.__esModule?e:{default:e}}(o);t.default={props:["fileList","canUpload"],data:function(){return{dialogImageUrl:"",dialogVisible:!1,file:[]}},created:function(){this.fileList.map(function(e){return e.name=e.fsFileBy.bsName+e.fsFileBy.bsFileType,e})},methods:{handleRemove:function(e,t){var a=this;if(e.id&&canUpload){var o={id:e.id};this.api.approvedItemsRecordFile.delete(o).then(function(e){e.result?(a.fileList.remove(),a.$Message.info("删除成功")):a.$Message.error(e.msg)})}},handleUpload:function(e){return this.file=e,this.getData(),!0},getData:function(){var e=this,t=new FormData;t.append("file",this.file),t.append("bsItemsRecordId",this.$route.query.itemsRecordId),this.api.approvedItemsRecordFile.add(t).then(function(t){if(t.result){var a=(0,s.default)(t.data,{name:e.file.name});e.fileList.push(a)}else e.$Message.error(t.msg)})},downloadFile:function(e){var t={fsFileId:e.fsFileId};this.api.fileQms.getfile(t).then(function(e){e.click()})}}}},1722:function(e,t,a){"use strict";function o(e){d||a(1723)}Object.defineProperty(t,"__esModule",{value:!0});var s=a(1428),r=a.n(s);for(var i in s)"default"!==i&&function(e){a.d(t,e,function(){return s[e]})}(i);var l=a(1725),n=a.n(l),d=!1,c=a(1),p=o,u=c(r.a,n.a,!1,p,null,null);u.options.__file="src/views/qms/components/upload.vue",t.default=u.exports},1723:function(e,t,a){var o=a(1724);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(19)("3905d981",o,!1)},1724:function(e,t,a){t=e.exports=a(18)(!1),t.push([e.i,"\n.Uploadbox{margin-top:15px;\n}\n.el-upload--text {\n    background-color: #fff;\n    border: none;\n    border-radius: 6px;\n    box-sizing: border-box;\n  \n    text-align: center;\n    cursor: pointer;\n    position: relative;\n}\n.el-upload-list__item{width: 60%;\n}\n",""])},1725:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-upload",{staticClass:"upload-demo",attrs:{action:"","before-upload":e.handleUpload,"on-preview":e.downloadFile,"on-remove":e.handleRemove,"file-list":e.fileList}},[e.canUpload?a("el-button",{attrs:{size:"small",type:"primary",plain:"",icon:"el-icon-plus"}},[e._v("\n    "+e._s(e.$t("upcoming.UploadAttachment"))+"\n  ")]):e._e(),e._v(" "),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"})],1)],1)},s=[];o._withStripped=!0;var r={render:o,staticRenderFns:s};t.default=r},1960:function(e,t,a){"use strict";function o(e){return e&&e.__esModule?e:{default:e}}Object.defineProperty(t,"__esModule",{value:!0});var s=a(1722),r=o(s),i=a(1350),l=o(i),n=a(25),d=o(n),c=a(1352),p=o(c);t.default={data:function(){return{AuditFailedModal:!1,AuditFailedForm:{},ApprovedForm:{},audits:{},approvedItemData:{itemsRecordId:0,itemsId:0},terms:[],dialogImageUrl:"",dialogVisible:!1,approvedItemRecord:{},productInfo:{},supplierInfo:{},perms:{edit:!1,verify:!1},scoreOptions:[{label:"5",value:5},{label:"4",value:4},{label:"3",value:3},{label:"2",value:2},{label:"1",value:1},{label:"0",value:0},{label:"NA",value:-1}]}},created:function(){this.approvedItemRecord=this.$store.getters.getItemRecordData,this.approvedItemRecord.bsSuppId?this.supplierInfo=this.$store.getters.getSupplierData:this.productInfo=this.$store.getters.getProductData,void 0==this.approvedItemRecord.fileSet&&(this.approvedItemRecord.fileSet=[]),this.approvedItemData=this.$route.query,this.getData();d.default.get("user");this.perms.edit=this.approvedItemRecord.users.includes(d.default.get("user"))&&this.approvedItemRecord.bsStatus<2,this.perms.verify=this.$Util.hasPerm("VERIFY")},beforeUpdate:function(){this.showRooterView=this.$route.matched.length>3},watch:{$route:function(e,t){this.showRooterView=e.matched.length>3}},components:{Upload:r.default,ApprovedAction:l.default,approvedResultRecord:p.default},methods:{getData:function(){var e=this;this.api.Audit.getlist(this.approvedItemData).then(function(t){e.terms=t.data})},updateTermsRecord:function(e){this.getData()},formatter:function(){}}}},2189:function(e,t,a){var o=a(2190);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(19)("3515d080",o,!1)},2190:function(e,t,a){t=e.exports=a(18)(!1),t.push([e.i,'\n.layout-content{padding:20px;background: #fff\n}\n.uploadtext{float: left;margin-right: 20px;font-size: 14px;\n}\n.uploadtxt .el-upload-dragger{border:none;\n}\n.fileList .el-upload-list__item {\n    margin-top: 10px;\n    margin-left: 65px;\n    padding: 10px;\n}\n.fltxt{float :left;display: inline-block;\n}\n.frtxt{float: right; text-align: right; margin-bottom: 10px;\n}\n.el-table .cell{font-family: "Microsoft YaHei"\n}\n.el-table th{background: #f2f7fb;\n}\n.productInfo{font-size: 14px;margin-bottom: 10px;\n}\n.productInfo .el-icon-info{font-size: 18px;color: #269ddd;margin-right: 10px;\n}\n.productInfo h5{font-family: "Microsoft YaHei"\n}\n.box-card{border:none;\n}\n.el-table .cell{font-family: "Microsoft YaHei"!important;\n}\n\n',""])},2191:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"layout-content",staticStyle:{height:"100%","background-color":"white"}},[a("div",{staticStyle:{height:"60px","font-size":"16px"}},[a("center",[a("h4",[e._v(e._s(e.approvedItemRecord.itemsBy.bsName))])])],1),e._v(" "),a("el-card",{staticClass:"box-card",attrs:{shadow:"hover"}},[e.approvedItemRecord.bsPrId?a("div",[a("Col",{staticClass:"productInfo",attrs:{md:8}},[a("i",{staticClass:"el-icon-info"}),a("b",[e._v(e._s(e.$t("product.name"))+":")]),e._v(" "+e._s(e.productInfo.bsPrName))]),e._v(" "),a("Col",{staticClass:"productInfo",attrs:{md:6}},[a("span",[a("b",[e._v(e._s(e.$t("product.code"))+":")]),e._v(" "+e._s(e.productInfo.bsPrCode))])]),e._v(" "),a("Col",{attrs:{md:8}},[e.perms.edit?e._e():a("div",{staticClass:"frtxt",staticStyle:{"margin-top":"-10px"}},[a("Tag",{attrs:{type:"dot",color:"blue"}},[e._v(e._s(e.$t("approved.statusLabel"))+" : "+e._s(e.$t("approved.status["+e.approvedItemRecord.bsStatus+"]")))])],1)])],1):e._e(),e._v(" "),e.approvedItemRecord.bsSuppId?a("div",[a("Col",{staticClass:"productInfo",attrs:{md:8}},[a("h5",{staticStyle:{"font-size":"14px"}},[e._v(e._s(e.$t("product.SuppChieseName"))+": "+e._s(e.supplierInfo.bsSuppChieseName)+" "+e._s(e.supplierInfo.bsSuppEnglishName))])]),e._v(" "),a("Col",{staticClass:"productInfo",attrs:{md:8}},[a("h5",{staticStyle:{"font-size":"14px"}},[e._v(e._s(e.$t("product.SuppCode"))+": "+e._s(e.supplierInfo.bsSuppCode))])])],1):e._e(),e._v(" "),a("div",[a("el-table",{staticStyle:{width:"100%"},attrs:{border:"",data:e.terms}},[a("el-table-column",{attrs:{prop:"BS_NO",label:e.$t("New-audit.Article-number"),align:"center",width:"80"}}),e._v(" "),"zh-CN"==e.$i18n.locale?a("el-table-column",{attrs:{prop:"BS_CONTENT",align:"center",width:"320",label:e.$t("New-audit.Terms-conditions")}}):e._e(),e._v(" "),"en-US"==e.$i18n.locale?a("el-table-column",{attrs:{prop:"BS_CONTENT_EN",align:"center",width:"320",label:e.$t("New-audit.Terms-conditions")}}):e._e(),e._v(" "),a("el-table-column",{attrs:{label:e.$t("New-audit.Auditscore"),width:"120",align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-select",{attrs:{placeholder:e.$t("choose"),disabled:!e.perms.edit&&!e.perms.verify},model:{value:t.row.BS_SCORE,callback:function(a){e.$set(t.row,"BS_SCORE",a)},expression:"scope.row.BS_SCORE"}},e._l(e.scoreOptions,function(e){return a("el-option",{key:e.value,attrs:{value:e.value,label:e.label}})}))]}}])}),e._v(" "),a("el-table-column",{attrs:{label:e.$t("New-audit.AuditInstructions"),align:"center"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-input",{attrs:{type:"textarea",autosize:{minRows:1.3},readonly:!e.perms.edit&&!e.perms.verify},model:{value:t.row.BS_DESC,callback:function(a){e.$set(t.row,"BS_DESC",a)},expression:"scope.row.BS_DESC"}})]}}])}),e._v(" "),"zh-CN"==e.$i18n.locale?a("el-table-column",{attrs:{prop:"BS_STANDARD",label:e.$t("New-audit.Grading"),align:"center"}}):e._e(),e._v(" "),"en-US"==e.$i18n.locale?a("el-table-column",{attrs:{prop:"BS_STANDARD_EN",label:e.$t("New-audit.Grading"),align:"center"}}):e._e()],1)],1)]),e._v(" "),a("el-card",{staticStyle:{"margin-top":"30px"},attrs:{shadow:"hover"}},[a("div",{staticClass:"uploadtxt",staticStyle:{margin:"30px"}},[a("h6",{staticClass:"uploadtext"},[e._v(e._s(e.$t("upcoming.UploadAttachment")))]),e._v(" "),a("div",{staticClass:"fileList",staticStyle:{padding:"0 15px"}},[a("upload",{attrs:{fileList:e.approvedItemRecord.fileSet,canUpload:e.perms.edit}})],1)])]),e._v(" "),a("approvedResultRecord",{attrs:{approvedData:e.approvedItemRecord}}),e._v(" "),a("div",{staticStyle:{"margin-top":"20px","margin-bottom":"50px"}},[a("ApprovedAction",{attrs:{type:"audits",data:e.terms,approvedItemRecord:e.approvedItemRecord,perms:e.perms},on:{updateTermsRecord:e.updateTermsRecord}})],1)],1)},s=[];o._withStripped=!0;var r={render:o,staticRenderFns:s};t.default=r},509:function(e,t,a){"use strict";function o(e){d||a(2189)}Object.defineProperty(t,"__esModule",{value:!0});var s=a(1960),r=a.n(s);for(var i in s)"default"!==i&&function(e){a.d(t,e,function(){return s[e]})}(i);var l=a(2191),n=a.n(l),d=!1,c=a(1),p=o,u=c(r.a,n.a,!1,p,null,null);u.options.__file="src/views/qms/view/approved/ApprovedItemRecordDetails.vue",t.default=u.exports}});