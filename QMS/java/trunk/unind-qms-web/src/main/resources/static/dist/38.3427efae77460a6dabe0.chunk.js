webpackJsonp([38],{1057:function(t,e,n){"use strict";function i(t){c||n(1894)}Object.defineProperty(e,"__esModule",{value:!0});var a=n(1638),l=n.n(a);for(var o in a)"default"!==o&&function(t){n.d(e,t,function(){return a[t]})}(o);var s=n(1896),r=n.n(s),c=!1,p=n(1),d=i,v=p(l.a,r.a,!1,d,null,null);v.options.__file="src\\view\\settings\\RiskLevelManagement.vue",e.default=v.exports},1638:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={data:function(){return{tableData1:[{FXDJ:"一级",SHZQ:"36"},{FXDJ:"二级",SHZQ:"24"},{FXDJ:"三级",SHZQ:"12"},{FXDJ:"四级",SHZQ:"6"},{FXDJ:"五级",SHZQ:"3"}],tableData2:[{FXDJ:"一级",SHZQ:"120"},{FXDJ:"二级",SHZQ:"140"},{FXDJ:"三级",SHZQ:"160"},{FXDJ:"四级",SHZQ:"180"},{FXDJ:"五级",SHZQ:"200"}]}}}},1894:function(t,e,n){var i=n(1895);"string"==typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);n(28)("cd833f68",i,!1,{})},1895:function(t,e,n){e=t.exports=n(27)(!1),e.push([t.i,"\n.el-icon-info{     \n      line-height:30px;\n      display:inline-block;\n      margin-left:10px;\n      font-size:16px;\n}\n.tooltext{\n    line-height:25px;\n    font-size: 14px;\n}\n.Risk .el-table th{text-align: center;\n}\n",""])},1896:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"Risk"},[n("div",{staticStyle:{width:"95%","margin-left":"2%","margin-top":"20px"}},[n("div",{staticStyle:{height:"40px","background-color":"#F7F7F7","padding-top":"3px"}},[n("div",[n("center",[n("h5",{staticStyle:{display:"inline","font-size":"15px"}},[t._v("供应商风险等级管理")]),t._v(" "),n("el-tooltip",{attrs:{placement:"bottom"}},[n("div",{staticClass:"tooltext",attrs:{slot:"content"},slot:"content"},[t._v("\n    \t\t\t  审核周期: "),n("br"),t._v("\n    \t\t\t  在上次审核完成后,根据供应商风险等级,在对应的审核周期后,提醒管理员"),n("br"),t._v("\n    \t\t\t  再次进行供应商的审核,以确保供应商的供应水平是否仍然在可控范围内\n      \t\t\t")]),t._v(" "),n("i",{staticClass:"el-icon-info"})])],1)],1)]),t._v(" "),n("div",[n("el-table",{staticStyle:{width:"100%","text-align":"center"},attrs:{data:t.tableData1}},[n("el-table-column",{attrs:{prop:"FXDJ",label:"风险等级"}}),t._v(" "),n("el-table-column",{attrs:{prop:"SHZQ",label:"审核周期(月)"}})],1)],1)]),t._v(" "),n("div",{staticStyle:{width:"95%","margin-left":"2%","margin-top":"40px","margin-bottom":"100px"}},[n("div",{staticStyle:{height:"40px","background-color":"#F7F7F7","padding-top":"3px"}},[n("div",[n("center",[n("h5",{staticStyle:{display:"inline","font-size":"15px"}},[t._v("产品风险等级管理")]),t._v(" "),n("el-tooltip",{attrs:{placement:"bottom"}},[n("div",{staticClass:"tooltext",attrs:{slot:"content"},slot:"content"},[t._v("\n    \t\t\t 抽样水平: "),n("br"),t._v("\n    \t\t\t 根据产品的风险等级,在成品检验时抽取指定百分比的成品"),n("br"),t._v("\n    \t\t\t 进行检验,以确保供应商的供应水平是否仍然在可控范围内\n      \t\t\t")]),t._v(" "),n("i",{staticClass:"el-icon-info"})])],1)],1)]),t._v(" "),n("div",[n("el-table",{staticStyle:{width:"100%","text-align":"center"},attrs:{data:t.tableData2}},[n("el-table-column",{attrs:{prop:"FXDJ",label:"风险等级"}}),t._v(" "),n("el-table-column",{attrs:{prop:"SHZQ",label:"抽样水平(件)"}})],1)],1)])])},a=[];i._withStripped=!0;var l={render:i,staticRenderFns:a};e.default=l}});