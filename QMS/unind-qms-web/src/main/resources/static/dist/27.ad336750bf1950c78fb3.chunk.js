webpackJsonp([27],{1238:function(t,e,n){"use strict";function r(t){l||n(2245)}Object.defineProperty(e,"__esModule",{value:!0});var i=n(1979),a=n.n(i);for(var o in i)"default"!==o&&function(t){n.d(e,t,function(){return i[t]})}(o);var s=n(2247),d=n.n(s),l=!1,p=n(1),c=r,f=p(a.a,d.a,!1,c,null,null);f.options.__file="src/views/qms/view/feedback/FeedbackDetails.vue",e.default=f.exports},1429:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=n(11),i=function(t){return t&&t.__esModule?t:{default:t}}(r);e.default={props:["shipmentInspectId","canUpload"],data:function(){return{dialogImageUrl:"",dialogVisible:!1,file:{},fileList:[]}},created:function(){},methods:{handleRemove:function(t,e){var n=this;if(t.id&&canUpload){var r={id:t.id};this.api.shipmentInspectFile.delete(r).then(function(t){n.fileList.remove(),n.$Message.info("删除成功")})}},handleUpload:function(t){var e=this;this.file=t;var n=new FormData;return n.append("file",this.file),n.append("bsShipmentId",this.shipmentInspectId),this.api.shipmentInspectFile.add(n).then(function(t){var n=(0,i.default)(t.data,{name:e.file.name});e.fileList.push(n)}),!0},downloadFile:function(t){var e={fsFileId:t.fsFileId};this.api.fileQms.getfile(e).then(function(t){t.click()})}}}},1430:function(t,e,n){e=t.exports=n(18)(!1),e.push([t.i,'/*客诉详情*/\r\n\t.feedbackdetails .text{\r\n\t\tfloat: left;margin-left:10px;font-size: 14px;color: #7D979C;\r\n\t}\r\n\t.feedbackdetails .i-div{\r\n\t\tfloat: left;\r\n\t}\r\n\t.feedbackdetails .i-div .ivu-icon{\r\n\t\tfont-size: 20px;\r\n\t}\r\n\t.feedbackdetails .text2{\r\n\t\theight: 50px;font-size: 14px;color: #7D979C;\r\n\t}\r\n\t.feedbackdetails #last-div{\r\n\t\tbackground-color: white;padding-top: 30px;padding-left:7%;padding-bottom: 50px;\r\n\t}\r\n\t.feedbackdetails #last-div p{\r\n\t\tfont-size: 14px;\r\n\t}\r\n\t.feedbackdetails #last-div .el-textarea{\r\n\t\twidth:50%;\r\n\t}\r\n\t.feedbackdetails #last-div .el-button{\r\n\t\twidth: 110px;height: 110px;\r\n\t}\r\n\t.feedbackdetails #last-div .el-icon-plus{\r\n\t\tdisplay: block;font-size: 30px;\r\n\t}\r\n\t.filelist{width: 60%;border:1px dashed #ccc; padding: 0 15px 15px;margin-top:10px;}\r\n\t.Colors{color:#20536e;}\r\n\r\n/*客诉处理*/\r\n.feedbackhandle .title1 {\r\n    float: left;\r\n}\r\n\r\n.feedbackhandle .title2 {\r\n    float: left;\r\n    display: inline-block;\r\n    line-height: 20px;\r\n    font-size: 14px;\r\n    color: #1c2438;\r\n    font-weight: 700;\r\n    margin-left: 1%;\r\n    margin-right: 1%;\r\n    margin-top: 8px;\r\n}\r\n\r\n.feedbackhandle #product-body {\r\n    height:120px;\r\n    padding-top: 20px;\r\n    padding-left: 4%;\r\n}\r\n\r\n.feedbackhandle .product-body-div {\r\n    height: 40px;\r\n    font-size: 14px;\r\n    float: left;\r\n    width:50%;    \r\n    color:#555;\r\n}\r\n\r\n.feedbackhandle .product-body-div span {\r\n    color: #35464e;\r\n}\r\n\r\n.feedbackhandle #handler-body {\r\n    padding-left: 5%;\r\n    padding-right: 5%;\r\n    padding-bottom: 10px;\r\n}\r\n\r\n.feedbackhandle #problem-body {\r\n    padding-top: 10px;\r\n    padding-left: 5%;\r\n    padding-bottom: 10px;\r\n}\r\n\r\n.feedbackhandle .principal-div {\r\n    height: 50px;\r\n    margin-top: 30px;\r\n}\r\n\r\n.feedbackhandle .principal-div1 {\r\n    float: left;\r\n    margin-top: 10px;\r\n    width: 60px;\r\n    font-size: 14px;\r\n}\r\n\r\n.feedbackhandle .principal-div2 {\r\n    float: left;\r\n}\r\n\r\n.feedbackhandle .time-div {\r\n    height: 50px;\r\n    margin-top: 20px;\r\n}\r\n\r\n.feedbackhandle .time-div1 {\r\n    float: left;\r\n    margin-top: 10px;\r\n    width: 60px;\r\n    font-size: 14px;\r\n}\r\n\r\n.feedbackhandle .submit-div {\r\n    height: 40px;\r\n    margin-top: 20px;\r\n    padding-top: 10px;\r\n}\r\n\r\n.feedbackhandle #measures-body {\r\n   /* padding-left: 2%;*/\r\n   /* padding-right: 2%;*/\r\n    padding-bottom: 10px;\r\n}\r\n\r\n.feedbackhandle #why-body {\r\n    padding-left: 5%;\r\n    padding-right: 5%;\r\n    padding-bottom: 10px;\r\n}\r\n\r\n.feedbackhandle #select-body {\r\n    padding-left: 5%;\r\n    padding-right: 5%;\r\n    padding-bottom: 10px;\r\n}\r\n\r\n.feedbackhandle #perform-body {\r\n    padding-left: 5%;\r\n    padding-right: 5%;\r\n    padding-bottom: 10px;\r\n}\r\n\r\n.feedbackhandle #prevent-body {\r\n    padding-left: 5%;\r\n    padding-right: 5%;\r\n    padding-bottom: 10px;\r\n}\r\n\r\n.feedbackhandle #bottom {\r\n    margin-bottom: 20px;\r\n    padding-top: 25px;\r\n    padding-bottom: 50px;\r\n}\r\n\r\n.feedbackhandle .submit-div .el-button {\r\n    width: 10%;\r\n    padding: 7px 20px;\r\n}\r\n\r\n/*提交客诉*/\r\n\t.feedbackreport #customer-body:after{\r\n\t\tcontent:".";\r\n        visibility:hidden;\r\n        display:block; \r\n        clear:both;\r\n\t}\r\n\t.feedbackreport .customer-body-div{\r\n\t\theight: 50px;\tfloat:left; \r\n\t}\r\n\t.feedbackreport .customer-body-text{\r\n\t\tfloat: left;margin-top: 10px;width: 130px;font-size: 14px;text-align: right;\r\n\t}\r\n    .feedbackreport .customer-body-input{\r\n        float: left;\r\n    }\r\n\t.feedbackreport .customer-body-input .el-input{\r\n\t\twidth: 300px;color:#444;\r\n\t}\r\n\t.feedbackreport #supplier-body:after{\r\n\t\tcontent:".";\r\n        visibility:hidden;\r\n        display:block; \r\n        clear:both;\r\n\t}\r\n\t.feedbackreport .supplier-body-div{\r\n\t\theight: 50px;float:left;\r\n\t}\r\n\t.feedbackreport .supplier-body-text{\r\n\t\tfloat: left;margin-top: 10px;width: 130px;font-size: 14px;text-align: right;\r\n\t}\r\n    .feedbackreport .supplier-body-input{\r\n        float: left;\r\n    }\r\n\t.feedbackreport .supplier-body-input .el-input{\r\n\t\twidth: 300px;color:#444;\r\n\t}\r\n\t.feedbackreport #product-body:after{\r\n\t\tcontent:".";\r\n        visibility:hidden;\r\n        display:block; \r\n        clear:both;\r\n\t}\r\n\t.feedbackreport .product-body-div{\r\n\t\theight: 50px;float:left;\r\n\t}\r\n\t.feedbackreport .product-body-text{\r\n\t\tfloat: left;margin-top: 10px;width: 130px;font-size: 14px;text-align: right;\r\n\t}\r\n    .feedbackreport .product-body-input{\r\n        float: left;\r\n    }\r\n\t.feedbackreport .product-body-input .el-input{\r\n\t\twidth: 300px;color:#444;\r\n\t}\r\n\t.feedbackreport #feedback-body{\r\n\t\tpadding-top: 10px;padding-bottom:50px;padding-left: 3%;\r\n\t}\r\n\t.feedbackreport #feedback-body-picture{\r\n\t\tmargin-top: 30px;\r\n\t}\r\n\t.feedbackreport #feedback-body-picture p{\r\n\t\tfont-size: 14px;\r\n\t}\r\n\t\r\n\t.feedbackreport .el-icon-plus{\r\n\t\tdisplay: block;font-size: 30px;\r\n\t}\r\n\r\n/*退货*/\r\n    .returngoods #basic-body1:after{\r\n        content:".";\r\n        visibility:hidden;\r\n        display:block; \r\n        clear:both;\r\n    }\r\n    .returngoods #basic-body1 .basic-body-div{\r\n        float: left;height: 50px;\r\n    }\r\n    .returngoods #basic-body1 .basic-body-text{\r\n        float: left;margin-top: 10px;width: 150px;margin-left:20px;font-size: 14px;text-align: right;\r\n    }\r\n    .returngoods #basic-body1 .basic-body-input{\r\n        float: left;width: 300px;\r\n    }\r\n    .returngoods #basic-body2:after{\r\n        content:".";\r\n        visibility:hidden;\r\n        display:block; \r\n        clear:both;\r\n    }\r\n    .returngoods #basic-body3:after{\r\n        content:".";\r\n        visibility:hidden;\r\n        display:block; \r\n        clear:both;\r\n    }\r\n    .returngoods .basic-body-div{\r\n        float: left;height: 50px;\r\n    }\r\n    .returngoods .basic-body-text{\r\n        float: left;margin-top: 10px;width: 100px;font-size: 14px;text-align: right;\r\n    }\r\n    .returngoods .basic-body-input{\r\n        float: left;width: 230px;\r\n    }\r\n    .returngoods .refund-body-div{\r\n        height: 50px;\r\n    }\r\n    .returngoods .refund-body-text{\r\n        float: left;margin-top: 10px;width: 100px;font-size: 14px;text-align: right;\r\n    }\r\n    .returngoods .refund-body-input{\r\n        float: left;width: 300px;\r\n    }\r\n    .returngoods #refund-body .ivu-checkbox-wrapper{\r\n        width: 160px;margin-top: 10px;margin-left: 10px;font-size: 14px;\r\n    } \r\n    .returngoods #refund-why:after{\r\n        content:".";\r\n        visibility:hidden;\r\n        display:block; \r\n        clear:both;\r\n    }',""])},1850:function(t,e,n){"use strict";function r(t){l||n(1851)}Object.defineProperty(e,"__esModule",{value:!0});var i=n(1429),a=n.n(i);for(var o in i)"default"!==o&&function(t){n.d(e,t,function(){return i[t]})}(o);var s=n(1853),d=n.n(s),l=!1,p=n(1),c=r,f=p(a.a,d.a,!1,c,null,null);f.options.__file="src/views/qms/components/otherattachments.vue",e.default=f.exports},1851:function(t,e,n){var r=n(1852);"string"==typeof r&&(r=[[t.i,r,""]]),r.locals&&(t.exports=r.locals);n(19)("96c57dc2",r,!1)},1852:function(t,e,n){e=t.exports=n(18)(!1),e.push([t.i,"\n.Uploadbox{margin-top:15px;\n}\n.el-upload--text {\n    background-color: #fff;\n    border: none;\n    border-radius: 6px;\n    box-sizing: border-box;\n  \n    text-align: center;\n    cursor: pointer;\n    position: relative;\n}\n.el-upload-list__item{width: 60%;\n}\n\n",""])},1853:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("el-upload",{staticClass:"upload-demo",attrs:{action:"","before-upload":t.handleUpload,"on-preview":t.downloadFile,"on-remove":t.handleRemove,"file-list":t.fileList}},[t.canUpload?n("el-button",{attrs:{size:"small",type:"success",plain:"",icon:"el-icon-plus"}},[t._v("\n    "+t._s(t.$t("upcoming.UploadAttachment"))+"\n  ")]):t._e(),t._v(" "),n("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"})],1)],1)},i=[];r._withStripped=!0;var a={render:r,staticRenderFns:i};e.default=a},1979:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=n(1850),i=function(t){return t&&t.__esModule?t:{default:t}}(r);e.default={data:function(){return{form:{}}},created:function(){this.form.id=this.$route.query.feedbackId,this.getData(),void 0==this.form.fileSet&&(this.form.fileSet=[])},components:{otherattachments:i.default},methods:{getData:function(){var t=this;this.api.feedback.getlist({id:this.form.id}).then(function(e){e.data.rows.length&&(t.form=e.data.rows.map(function(t){return delete t.createdBy,delete t.feedbackerBy,delete t.modifiedBy,t})[0]),void 0!=t.form.fileSet&&(t.form.fileSet=t.form.fileSet.map(function(t){return t.name=t.fsFileBy.bsName+t.fsFileBy.bsFileType,t}))})},downloadFile:function(t){var e={fsFileId:t.id};this.api.fileQms.getfile(e).then(function(t){t.click()})}}}},2245:function(t,e,n){var r=n(2246);"string"==typeof r&&(r=[[t.i,r,""]]),r.locals&&(t.exports=r.locals);n(19)("ffa48794",r,!1)},2246:function(t,e,n){e=t.exports=n(18)(!1),e.i(n(1430),""),e.push([t.i,"\n",""])},2247:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"feedbackdetails"},[n("div",{staticStyle:{height:"50px"}},[n("Col",{attrs:{md:9}},[n("div",{staticStyle:{width:"36%",height:"1px"}})]),t._v(" "),n("Col",{attrs:{md:3}},[n("div",{staticStyle:{width:"99%",height:"40px","border-radius":"8px 0px 0px 8px","background-color":"#76C6F3",color:"white","padding-top":"10px","font-size":"14px"}},[n("center",[t._v(t._s(t.$t("feedback.DetailsOfFeedback")))])],1)]),t._v(" "),n("Col",{attrs:{md:3}},[n("router-link",{attrs:{to:{path:"handle",query:{feedbackId:t.$route.query.feedbackId}}}},[n("el-button",{staticStyle:{width:"99%",height:"40px","border-radius":"0px 8px 8px 0px","font-size":"14px"},attrs:{type:"primary"}},[t._v("\n\t\t\t\t\t\t"+t._s(t.$t("feedback.CustomerComplaintHandling"))+"\n\t\t\t\t\t")])],1)],1),t._v(" "),n("Col",{attrs:{md:9}},[n("div",{staticStyle:{width:"36%",height:"1px"}})])],1),t._v(" "),n("Row",{style:{marginBottom:"20px"}},[n("Card",[n("p",{attrs:{slot:"title"},slot:"title"},[n("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"person"}}),t._v("\n                     "+t._s(t.$t("feedback.CustomerInformation"))+"\n               \t")],1),t._v(" "),n("div",{staticStyle:{height:"170px","padding-top":"20px"}},[n("Col",{attrs:{md:12}},[n("div",{staticStyle:{height:"50px","margin-left":"15%"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"orange"},attrs:{type:"social-buffer"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.CusCompanyName"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsCusCompanyName))])])]),t._v(" "),n("div",{staticStyle:{height:"50px","margin-left":"15%"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"orange"},attrs:{type:"android-person"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.ContactName"))+"："+t._s(t.form.bsCusCompanyPerson)),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsCusCompanyPerson))])])]),t._v(" "),n("div",{staticStyle:{height:"50px","margin-left":"15%"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"orange"},attrs:{type:"android-mail"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.ContactEmail"))+"："+t._s(t.form.bsCusCompanyEmail))])])]),t._v(" "),n("Col",{attrs:{md:12}},[n("div",{staticStyle:{height:"50px"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"#0096D9"},attrs:{type:"android-bookmark"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.CustomerID"))+"："+t._s(t.form.bsCusCompanyCode))])]),t._v(" "),n("div",{staticStyle:{height:"50px"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"#0096D9"},attrs:{type:"android-phone-portrait"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.ContactNumber"))+"："+t._s(t.form.bsCusCompanyMobile))])])])],1)])],1),t._v(" "),n("Row",{style:{marginBottom:"20px"}},[n("Card",[n("p",{attrs:{slot:"title"},slot:"title"},[n("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"ios-folder"}}),t._v("\n                     "+t._s(t.$t("feedback.SupplierInformation"))+"\n               \t")],1),t._v(" "),n("div",{staticStyle:{height:"180px","padding-top":"20px"}},[n("Col",{attrs:{md:12}},[n("div",{staticStyle:{height:"50px","margin-left":"15%"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"orange"},attrs:{type:"social-buffer"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.CusCompanyName"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsSuppCompanyName))])])]),t._v(" "),n("div",{staticStyle:{height:"50px","margin-left":"15%"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"orange"},attrs:{type:"android-person"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.ContactName"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsSuppCompanyPerson))])])]),t._v(" "),n("div",{staticStyle:{height:"50px","margin-left":"15%"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"orange"},attrs:{type:"android-mail"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.ContactEmail"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsSuppCompanyEmail))])])])]),t._v(" "),n("Col",{attrs:{md:12}},[n("div",{staticStyle:{height:"50px"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"#0096D9"},attrs:{type:"android-bookmark"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.CustomerID"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsSuppCompanyCode))])])]),t._v(" "),n("div",{staticStyle:{height:"50px"}},[n("div",{staticClass:"i-div"},[n("Icon",{staticStyle:{color:"#0096D9"},attrs:{type:"android-phone-portrait"}})],1),t._v(" "),n("div",{staticClass:"text"},[t._v(t._s(t.$t("feedback.ContactNumber"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsSuppCompanyMobile))])])])])],1),t._v(" "),n("div",{staticStyle:{height:"200px"}},[n("Col",{attrs:{md:12}},[n("div",{staticClass:"text2",staticStyle:{"margin-left":"15%"}},[t._v("\n\t\t\t\t"+t._s(t.$t("feedback.NumberOfQuestions"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.id))])]),t._v(" "),n("div",{staticClass:"text2",staticStyle:{"margin-left":"15%"}},[t._v("\n\t\t\t\t"+t._s(t.$t("feedback.ProductName"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsPrName))])]),t._v(" "),n("div",{staticClass:"text2",staticStyle:{"margin-left":"15%"}},[t._v("\n\t\t\t\t"+t._s(t.$t("feedback.ProductNumber"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsPrCode))])])]),t._v(" "),n("Col",{attrs:{md:12}},[n("div",{staticClass:"text2"},[t._v("\n\t\t\t\t"+t._s(t.$t("feedback.amount"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsPrNum))])]),t._v(" "),n("div",{staticClass:"text2"},[t._v("\n\t\t\t\t"+t._s(t.$t("feedback.place"))+":"),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsReportLocation))])]),t._v(" "),n("div",{staticClass:"text2"},[t._v("\n\t\t\t\t"+t._s(t.$t("feedback.origin"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsProductLocation))])]),t._v(" "),n("div",{staticClass:"text2"},[t._v("\n\t\t\t\t"+t._s(t.$t("feedback.TheDateOfProduction"))+"："),n("span",{staticClass:"Colors"},[t._v(t._s(t.form.bsProductDate))])])])],1)])],1),t._v(" "),n("Row",{style:{marginBottom:"20px"}},[n("Card",[n("p",{attrs:{slot:"title"},slot:"title"},[n("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"chatbox-working"}}),t._v("\n                     "+t._s(t.$t("feedback.CustomerComplaintInformation"))+"\n               \t")],1),t._v(" "),n("div",{attrs:{id:"last-div"}},[n("p",{staticStyle:{"margin-top":"30px"}},[t._v(t._s(t.$t("feedback.ProblemDescription")))]),t._v(" "),n("el-input",{attrs:{type:"textarea",autosize:{minRows:4}},model:{value:t.form.bsProblemDes,callback:function(e){t.$set(t.form,"bsProblemDes",e)},expression:"form.bsProblemDes"}}),t._v(" "),n("p",{staticStyle:{"margin-top":"30px"}},[t._v(t._s(t.$t("feedback.RelatedImages")))]),t._v(" "),n("otherattachments",{staticClass:"filelist",attrs:{fileList:t.form.fileSet}})],1)])],1)],1)},i=[];r._withStripped=!0;var a={render:r,staticRenderFns:i};e.default=a}});