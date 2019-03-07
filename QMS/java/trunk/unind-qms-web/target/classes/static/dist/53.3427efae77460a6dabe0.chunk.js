webpackJsonp([53],{1053:function(t,e,o){"use strict";function s(t){l||o(1874)}Object.defineProperty(e,"__esModule",{value:!0});var a=o(1632),i=o.n(a);for(var n in a)"default"!==n&&function(t){o.d(e,t,function(){return a[t]})}(n);var r=o(1876),p=o.n(r),l=!1,d=o(1),c=s,u=d(i.a,p.a,!1,c,null,null);u.options.__file="src\\view\\feedback\\FeedbackReport.vue",e.default=u.exports},1632:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=o(15),a=function(t){return t&&t.__esModule?t:{default:t}}(s);e.default={data:function(){return{options:[{value:"1",label:"投诉"},{value:"2",label:"退货"}],form:{},products:[],suppliers:[],file:{},fileList:[]}},created:function(){this.fileList.map(function(t){return t.name=t.fsFileBy.bsName+t.fsFileBy.bsFileType,t}),this.getData("")},methods:{getData:function(t){var e=this;this.api.supplierinfo.getlist({page:1,rows:100,keyWord:t}).then(function(t){e.suppliers=t.data.rows.map(function(t){return t.value=t.bsSuppChieseName,t})})},getProductData:function(t){var e=this;this.api.productinfo.getlist({suppCode:t}).then(function(t){e.products=t.data.rows.map(function(t){return t.value=t.bsPrName,t})})},queryProduct:function(t,e){var o=this.products;e(t?o.filter(this.nameFilter(t)):o)},querySupplier:function(t,e){var o=this;this.api.supplierinfo.getlist({page:1,rows:100,keyWord:t}).then(function(t){o.suppliers=t.data.rows.map(function(t){return t.value=t.bsSuppChieseName,t}),e(o.suppliers)})},supplierFilter:function(t){return function(e){return 0===e.bsSuppChieseName.toLowerCase().indexOf(t.toLowerCase())}},productFileter:function(t){return function(e){return 0===e.bsPrName.toLowerCase().indexOf(t.toLowerCase())}},handleSelectSupplier:function(t){this.form.bsSuppId=t.id,this.form.bsSuppCompanyName=t.bsSuppChieseName,this.form.bsSuppCompanyEmail=t.bsSuppEmail,this.form.bsSuppCompanyMobile=t.bsSuppMobile,this.form.bsSuppCompanyPersion=t.bsSuppContactName,this.form.bsSuppCompanyCode=t.bsSuppCode,this.getProductData(t.bsSuppCode)},handleSelectProduct:function(t){this.form.bsPrId=t.id,this.form.bsPrName=t.bsPrName,this.form.bsPrCode=t.bsPrCode},submitFeeback:function(){var t=this,e=this.fileList.map(function(t){return t.id});this.form.fileIdStr=e.toString(),void 0==this.form.id?this.api.feedback.add(this.form).then(function(e){t.form.id=e.data.id,t.$Message.info("提交成功")}):this.api.feedback.edit(this.form).then(function(e){t.$Message.info("修改成功")})},handleUpload:function(t){var e=this;this.file=t;var o=new FormData;return o.append("file",this.file),this.api.fileQms.upload(o).then(function(t){if(t.result){var o=(0,a.default)(t.data,{name:e.file.name});e.fileList.push(o)}else e.$Message.error(t.msg)}),!0},downloadFile:function(t){var e={fsFileId:t.id};console.log(e),this.api.fileQms.getfile(e).then(function(t){t.click()})},handleRemove:function(t,e){var o=this,s={id:t.id};this.api.approvedItemsRecordFile.delete(s).then(function(t){t.result?(o.fileList.remove(),o.$Message.info("删除成功")):o.$Message.error(t.msg)})}}}},1874:function(t,e,o){var s=o(1875);"string"==typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);o(28)("280d2910",s,!1,{})},1875:function(t,e,o){e=t.exports=o(27)(!1),e.push([t.i,"\n.ApprovedFlowManagement .ivu-table {\n  font-size: 14px;\n}\n.ApprovedTermsManagement .ivu-table-cell {\n  font-size: 14px;\n}\n.SampleManagement .ivu-table {\n  font-size: 14px;\n}\n.SampleRegularManagement .ivu-table {\n  font-size: 14px;\n}\n.feedbackreport #customer-body {\n  height: 340px;\n  padding-top: 10px;\n  white-space: nowrap;\n  width: 100%;\n  overflow-x: auto;\n}\n.feedbackreport .customer-body-div {\n  height: 50px;\n}\n.feedbackreport .customer-body-text {\n  display: inline-block;\n  width: 110px;\n  font-size: 14px;\n  text-align: right;\n}\n.feedbackreport .customer-body-input {\n  display: inline-block;\n}\n.feedbackreport #customer-body .el-input {\n  width: 250px;\n}\n.feedbackreport #supplier-body {\n  height: 340px;\n  padding-top: 10px;\n  white-space: nowrap;\n  width: 100%;\n  overflow-x: auto;\n}\n.feedbackreport .supplier-body-div {\n  height: 50px;\n}\n.feedbackreport .supplier-body-text {\n  display: inline-block;\n  width: 110px;\n  font-size: 14px;\n  text-align: right;\n}\n.feedbackreport .supplier-body-input {\n  display: inline-block;\n}\n.feedbackreport #supplier-body .el-input {\n  width: 250px;\n}\n.feedbackreport #product-body {\n  height: 340px;\n  padding-top: 10px;\n  white-space: nowrap;\n  width: 100%;\n  overflow-x: auto;\n}\n.feedbackreport .product-body-div {\n  height: 50px;\n}\n.feedbackreport .product-body-text {\n  display: inline-block;\n  width: 110px;\n  font-size: 14px;\n  text-align: right;\n}\n.feedbackreport .product-body-input {\n  display: inline-block;\n}\n.feedbackreport #product-body .el-input {\n  width: 250px;\n}\n.feedbackreport #feedback-body {\n  padding-top: 10px;\n  padding-bottom: 50px;\n  padding-left: 3%;\n}\n.feedbackreport #feedback-body-picture {\n  margin-top: 30px;\n}\n.feedbackreport #feedback-body-picture p {\n  font-size: 14px;\n}\n.feedbackreport .el-icon-plus {\n  display: block;\n  font-size: 30px;\n}\n.Upload {\n  margin-top: 15px;\n  width: 45%;\n}\n",""])},1876:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"feedbackreport"},[o("Row",{attrs:{gutter:10}},[o("Col",{style:{marginBottom:"20px"},attrs:{md:24,lg:8}},[o("Card",[o("p",{attrs:{slot:"title"},slot:"title"},[o("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"person"}}),t._v("\n                         "+t._s(t.$t("feedback.CustomerInformation"))+"\n                   \t")],1),t._v(" "),o("div",{attrs:{id:"customer-body"}},[o("div",{staticClass:"customer-body-div"},[o("p",{staticClass:"customer-body-text"},[t._v(t._s(t.$t("feedback.CusCompanyName"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsCusCompanyName,callback:function(e){t.$set(t.form,"bsCusCompanyName",e)},expression:"form.bsCusCompanyName"}})],1),t._v(" "),o("div",{staticClass:"customer-body-div"},[o("p",{staticClass:"customer-body-text"},[t._v(t._s(t.$t("feedback.CustomerID"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsCusCompanyCode,callback:function(e){t.$set(t.form,"bsCusCompanyCode",e)},expression:"form.bsCusCompanyCode"}})],1),t._v(" "),o("div",{staticClass:"customer-body-div"},[o("p",{staticClass:"customer-body-text"},[t._v(t._s(t.$t("feedback.ContactName"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsCusCompanyPersion,callback:function(e){t.$set(t.form,"bsCusCompanyPersion",e)},expression:"form.bsCusCompanyPersion"}})],1),t._v(" "),o("div",{staticClass:"customer-body-div"},[o("p",{staticClass:"customer-body-text"},[t._v(t._s(t.$t("feedback.ContactNumber"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsCusCompanyMobile,callback:function(e){t.$set(t.form,"bsCusCompanyMobile",e)},expression:"form.bsCusCompanyMobile"}})],1),t._v(" "),o("div",{staticClass:"customer-body-div"},[o("p",{staticClass:"customer-body-text"},[t._v(t._s(t.$t("feedback.ContactEmail"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsCusCompanyEmail,callback:function(e){t.$set(t.form,"bsCusCompanyEmail",e)},expression:"form.bsCusCompanyEmail"}})],1)])])],1),t._v(" "),o("Col",{style:{marginBottom:"20px"},attrs:{md:24,lg:8}},[o("Card",[o("p",{attrs:{slot:"title"},slot:"title"},[o("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"ios-folder"}}),t._v("\n                         "+t._s(t.$t("feedback.SupplierInformation"))+"\n                   \t")],1),t._v(" "),o("div",{attrs:{id:"supplier-body"}},[o("div",{staticClass:"supplier-body-div"},[o("p",{staticClass:"supplier-body-text"},[t._v(t._s(t.$t("feedback.SupplierName"))+"：")]),t._v(" "),o("el-autocomplete",{staticClass:"inline-input",staticStyle:{width:"250px"},attrs:{"fetch-suggestions":t.querySupplier},on:{select:t.handleSelectSupplier},model:{value:t.form.bsSuppCompanyName,callback:function(e){t.$set(t.form,"bsSuppCompanyName",e)},expression:"form.bsSuppCompanyName"}})],1),t._v(" "),o("div",{staticClass:"supplier-body-div"},[o("p",{staticClass:"supplier-body-text"},[t._v(t._s(t.$t("feedback.SupplierID"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsSuppCompanyCode,callback:function(e){t.$set(t.form,"bsSuppCompanyCode",e)},expression:"form.bsSuppCompanyCode"}})],1),t._v(" "),o("div",{staticClass:"supplier-body-div"},[o("p",{staticClass:"supplier-body-text"},[t._v(t._s(t.$t("feedback.ContactName"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsSuppCompanyPersion,callback:function(e){t.$set(t.form,"bsSuppCompanyPersion",e)},expression:"form.bsSuppCompanyPersion"}})],1),t._v(" "),o("div",{staticClass:"supplier-body-div"},[o("p",{staticClass:"supplier-body-text"},[t._v(t._s(t.$t("feedback.ContactNumber"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsSuppCompanyMobile,callback:function(e){t.$set(t.form,"bsSuppCompanyMobile",e)},expression:"form.bsSuppCompanyMobile"}})],1),t._v(" "),o("div",{staticClass:"supplier-body-div"},[o("p",{staticClass:"supplier-body-text"},[t._v(t._s(t.$t("feedback.ContactEmail"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsSuppCompanyEmail,callback:function(e){t.$set(t.form,"bsSuppCompanyEmail",e)},expression:"form.bsSuppCompanyEmail"}})],1)])])],1),t._v(" "),o("Col",{style:{marginBottom:"20px"},attrs:{md:24,lg:8}},[o("Card",[o("p",{attrs:{slot:"title"},slot:"title"},[o("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"cube"}}),t._v("\n                         "+t._s(t.$t("feedback.ProductInformation"))+"\n                   \t")],1),t._v(" "),o("div",{attrs:{id:"product-body"}},[o("div",{staticClass:"product-body-div"},[o("p",{staticClass:"product-body-text"},[t._v(t._s(t.$t("feedback.ProductName"))+"：")]),t._v(" "),o("el-autocomplete",{staticClass:"inline-input",staticStyle:{width:"250px"},attrs:{"fetch-suggestions":t.queryProduct},on:{select:t.handleSelectProduct},model:{value:t.form.bsPrName,callback:function(e){t.$set(t.form,"bsPrName",e)},expression:"form.bsPrName"}})],1),t._v(" "),o("div",{staticClass:"product-body-div"},[o("p",{staticClass:"product-body-text"},[t._v(t._s(t.$t("feedback.ProductNumber"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsPrCode,callback:function(e){t.$set(t.form,"bsPrCode",e)},expression:"form.bsPrCode"}})],1),t._v(" "),o("div",{staticClass:"product-body-div"},[o("p",{staticClass:"product-body-text"},[t._v(t._s(t.$t("feedback.amount"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsPrNum,callback:function(e){t.$set(t.form,"bsPrNum",e)},expression:"form.bsPrNum"}})],1),t._v(" "),o("div",{staticClass:"product-body-div"},[o("p",{staticClass:"product-body-text"},[t._v(t._s(t.$t("feedback.place"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsReportLocation,callback:function(e){t.$set(t.form,"bsReportLocation",e)},expression:"form.bsReportLocation"}})],1),t._v(" "),o("div",{staticClass:"product-body-div"},[o("p",{staticClass:"product-body-text"},[t._v(t._s(t.$t("feedback.origin"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsProductLocation,callback:function(e){t.$set(t.form,"bsProductLocation",e)},expression:"form.bsProductLocation"}})],1),t._v(" "),o("div",{staticClass:"product-body-div"},[o("p",{staticClass:"product-body-text"},[t._v(t._s(t.$t("feedback.TheDateOfProduction"))+"：")]),t._v(" "),o("el-input",{attrs:{placeholder:"请输入内容"},model:{value:t.form.bsProductDate,callback:function(e){t.$set(t.form,"bsProductDate",e)},expression:"form.bsProductDate"}})],1)])])],1)],1),t._v(" "),o("Row",[o("Card",[o("p",{attrs:{slot:"title"},slot:"title"},[o("Icon",{staticStyle:{color:"#ff9900"},attrs:{type:"chatbox-working"}}),t._v("\n                     "+t._s(t.$t("feedback.CustomerComplaintInformation"))+"\n               \t")],1),t._v(" "),o("div",{attrs:{id:"feedback-body"}},[o("div",[o("p",{staticStyle:{"font-size":"14px"}},[t._v(t._s(t.$t("feedback.CustomerComplaintType")))]),t._v(" "),o("el-select",{staticStyle:{width:"500px"},attrs:{placeholder:"请选择"},model:{value:t.form.bsType,callback:function(e){t.$set(t.form,"bsType",e)},expression:"form.bsType"}},t._l(t.options,function(t){return o("el-option",{key:t.value,attrs:{label:t.label,value:t.value}})}))],1),t._v(" "),o("div",[o("p",{staticStyle:{"font-size":"14px","margin-top":"30px"}},[t._v(t._s(t.$t("feedback.ProblemDescription")))]),t._v(" "),o("el-input",{staticStyle:{width:"500px"},attrs:{type:"textarea",autosize:{minRows:3},placeholder:"请输入内容"},model:{value:t.form.bsProblemDesc,callback:function(e){t.$set(t.form,"bsProblemDesc",e)},expression:"form.bsProblemDesc"}})],1),t._v(" "),o("div",{attrs:{id:"feedback-body-picture"}},[o("p",[t._v(t._s(t.$t("feedback.RelatedImages")))]),t._v(" "),o("div",{staticClass:"Upload"},[o("el-upload",{staticClass:"upload-demo",attrs:{action:"","before-upload":t.handleUpload,"on-preview":t.downloadFile,"on-remove":t.handleRemove,"file-list":t.fileList}},[o("el-button",{attrs:{size:"medium",type:"success",plain:""}},[t._v("\n\t\t\t\t\t\t\t    "+t._s(t.$t("upcoming.UploadAttachment"))+"\n\t\t\t\t\t\t\t  ")]),t._v(" "),o("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"})],1)],1)])])])],1),t._v(" "),o("div",{staticStyle:{"margin-bottom":"100px","margin-top":"50px"}},[o("center",[o("el-button",{staticStyle:{width:"10%",padding:"7px 0"},attrs:{type:"primary"},on:{click:t.submitFeeback}},[t._v(t._s(t.$t("feedback.submit")))])],1)],1)],1)},a=[];s._withStripped=!0;var i={render:s,staticRenderFns:a};e.default=i}});