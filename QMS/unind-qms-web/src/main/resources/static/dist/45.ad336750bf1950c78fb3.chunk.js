webpackJsonp([45],{1227:function(t,n,e){"use strict";function l(t){c||e(2177)}Object.defineProperty(n,"__esModule",{value:!0});var o=e(1956),r=e.n(o);for(var s in o)"default"!==s&&function(t){e.d(n,t,function(){return o[t]})}(s);var a=e(2179),i=e.n(a),c=!1,p=e(1),u=l,v=p(r.a,i.a,!1,u,null,null);v.options.__file="src/views/qms/view/supplier/Instructions.vue",n.default=v.exports},1956:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default={data:function(){return{customercomplaint:[{level:"1",instructions:"0次  ",score:"3",note:""},{level:"2",instructions:"1次及以上 ，6个月以上客诉 ",score:"6",note:""},{level:"3",instructions:"1次及以上  ，3到6个月的客诉 ，且关闭",score:"9",note:""},{level:"4",instructions:"1次 及以上  ，3个月以内的客诉，且关闭 ",score:"12",note:""},{level:"5",instructions:"1次及以上，且未关闭  ",score:"15",note:"直升高风险"}],PPM:[{level:"1",instructions:"PPM低于50  ",score:"2",note:""},{level:"2",instructions:"PPM低于200   大于50 ",score:"4",note:""},{level:"3",instructions:"PPM低于500 大于200  ",score:"6",note:""},{level:"4",instructions:"PPM高于500 ",score:"8",note:""},{level:"5",instructions:"PPM高于1000 ",score:"10",note:""}],delivery:[{level:"1",instructions:"大于24个月无交货延迟  ",score:"3",note:""},{level:"2",instructions:"大于12个月无交货延迟",score:"6",note:""},{level:"3",instructions:"大于2个月无交货延迟  ",score:"9",note:""},{level:"4",instructions:"3个月内有1次以上交货延迟",score:"12",note:""},{level:"5",instructions:"3个月内有2次以上交货延迟",score:"15",note:"直升高风险"}],inspection:[{level:"1",instructions:"0次 ",score:"2",note:""},{level:"2",instructions:"1次 ",score:"4",note:""},{level:"3",instructions:">=2次  ",score:"6",note:""},{level:"4",instructions:">=3次 ",score:"8",note:""},{level:"5",instructions:"连续发生3次及以上 ",score:"10",note:""}],payment:[{level:"1",instructions:"大于120天",score:"1",note:""},{level:"2",instructions:"小于120天 ",score:"2",note:""},{level:"3",instructions:"小于90天",score:"3",note:""},{level:"4",instructions:"小于30天",score:"4",note:""},{level:"5",instructions:"小于15天或者现金",score:"5",note:""}],SPC:[{level:"1",instructions:"关键工序制程能力指数   CPK ≥1.67  ;无CPK要求  ",score:"2",note:""},{level:"2",instructions:"关键工序制程能力指数 1.67 ≥ CPK ≥1.33 ",score:"4",note:""},{level:"3",instructions:"关键工序制程能力指数  CPK ≥1.33  ",score:"6",note:""},{level:"4",instructions:"关键工序制程能力指数1.33> CPK ≥1 ",score:"8",note:""},{level:"5",instructions:"关键工序制程能力指数   CPK <1  ",score:"10",note:""}],highriskproducts:[{level:"1",instructions:"无高风险产品 ",score:"2",note:""},{level:"2",instructions:"有其他高风险产品同系列产品  ",score:"4",note:""},{level:"3",instructions:"有高风险产品产值超过100万RMB/或者数量1个",score:"6",note:""},{level:"4",instructions:"高风险产品产值超过300万RMB/或者数量2个",score:"8",note:""},{level:"5",instructions:"高风险产品产值超过600万RMB/或者数量大于3个",score:"10",note:""}],systemaudit:[{level:"1",instructions:"客户审核通过，金合联审核通过 ",score:"2",note:""},{level:"2",instructions:"客户审核通过，金合联审核未审核",score:"4",note:""},{level:"3",instructions:"客户审核通过，金合联审核不通过",score:"6",note:""},{level:"4",instructions:"客户审核不通过，金合联审核通过",score:"8",note:""},{level:"5",instructions:"客户审核不通过，金合联审核不通过",score:"10",note:""}],EHS:[{level:"1",instructions:"被我方或客方查出不符合项目少于 ≤2条",score:"1",note:""},{level:"2",instructions:"被我方或客方查出不符合项目少于 ≤3条",score:"2",note:""},{level:"3",instructions:"被我方或客方查出不符合项目少于 ≤4条",score:"3",note:""},{level:"4",instructions:"被我方或客方查出不符合项目少于 ≤5条",score:"4",note:""},{level:"5",instructions:"被我方或客方查出不符合项目大于 ≥6条",score:"5",note:""}],businessjudgement:[{level:"1",instructions:"公司股东构成的稳定性（从上市公司/销售额/股东数量/地域/行业等考虑） ",score:"2",note:"有一项得一分"},{level:"2",instructions:"反腐协议未签订 ",score:"2",note:"有一项得一分"},{level:"3",instructions:"长期协议未签订",score:"2",note:"有一项得一分"},{level:"4",instructions:"战略合作协议未签订",score:"2",note:"有一项得一分"},{level:"5",instructions:"商务手册未签订",score:"2",note:"有一项得一分"}]}}}},2177:function(t,n,e){var l=e(2178);"string"==typeof l&&(l=[[t.i,l,""]]),l.locals&&(t.exports=l.locals);e(19)("963689a8",l,!1)},2178:function(t,n,e){n=t.exports=e(18)(!1),n.push([t.i,"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",""])},2179:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var l=function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",[e("Row",{style:{marginBottom:"20px"}},[e("center",[e("h4",{staticStyle:{"font-size":"16px"}},[t._v(t._s(t.$t("product.DescriptionOfRiskManagementSystem")))])])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    "+t._s(t.$t("product.feedback"))+"\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.customercomplaint}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    PPM\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.PPM}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    "+t._s(t.$t("supplier.DeliveryAchieved"))+"\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.delivery}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    "+t._s(t.$t("product.TheNumberOfUnqualifiedInspection"))+"\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.inspection}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    "+t._s(t.$t("product.payment"))+"\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.payment}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    "+t._s(t.$t("product.SPC"))+"\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.SPC}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    "+t._s(t.$t("product.TheProportionOfHigh-riskProducts"))+"\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.highriskproducts}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    "+t._s(t.$t("supplier.SystemAudit"))+"\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.systemaudit}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    "+t._s(t.$t("supplier.EHS"))+"\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.EHS}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1),t._v(" "),e("Row",{style:{marginBottom:"20px"}},[e("Card",[e("p",{attrs:{slot:"title"},slot:"title"},[t._v("\n                    "+t._s(t.$t("product.BusinessJudgement"))+"\n               \t")]),t._v(" "),e("div",[e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.businessjudgement}},[e("el-table-column",{attrs:{prop:"level",label:t.$t("product.level"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"instructions",label:t.$t("approved.explain"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"score",label:t.$t("product.RiskScore"),align:"center"}}),t._v(" "),e("el-table-column",{attrs:{prop:"note",label:t.$t("sample.Remark"),align:"center"}})],1)],1)])],1)],1)},o=[];l._withStripped=!0;var r={render:l,staticRenderFns:o};n.default=r}});