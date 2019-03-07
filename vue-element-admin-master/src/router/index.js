import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/views/layout/Layout'

/* Router Modules */
import componentsRouter from './modules/components'
import chartsRouter from './modules/charts'
import tableRouter from './modules/table'
import nestedRouter from './modules/nested'

/** note: Submenu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
  **/
export const constantRouterMap = [
  /*首页*/
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      name: 'Dashboard',
      meta: { title: 'dashboard', icon: 'dashboard', noCache: true }
    }]
  },

   {
    path: '/helpDocument',
    component: Layout,
    redirect: 'helpDocument',
    children: [{
      path: 'helpDocument',
      component: () => import('@/views/dashboard/helpDocument'),
      name: 'helpDocument',
      meta: { title: '系统帮助', icon: 'help', noCache: true }
    }]
  },
  {
    path: '/modifyPassword',
    component: Layout,
    redirect: 'modifyPassword',
    hidden: true,
    children: [{
      path: '/modifyPassword',
      component: () => import('@/views/dashboard/modifyPassword'),
      name: 'modifyPassword',
      meta: { title: '修改密码', icon: 'people', noCache: true }
    }]
  },

  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [{
      path: '/redirect/:path*',
      component: () => import('@/views/redirect/index')
    }]
  },
  
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/register/index'),
    hidden: true, 
  },
{
    path: '/registerForm',
    component: () => import('@/views/register/registerForm'),
    name: 'registerForm',
    //redirect: '/login',
    meta: { title: 'documentation', icon: 'documentation', }
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/authredirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401'),
    hidden: true
  },

  {
    path: '/documentation',
    component: Layout,
    redirect: '/documentation/index',
    hidden: true,
    children: [{
      path: 'index',
      component: () => import('@/views/documentation/index'),
      name: 'Documentation',
      meta: { title: 'documentation', icon: 'documentation', noCache: true }
    }]
  },
  {
    path: '/guide',
    component: Layout,
    redirect: '/guide/index',
    hidden: true,
    children: [{
      path: 'index',
      component: () => import('@/views/guide/index'),
      name: 'Guide',
      meta: { title: 'guide', icon: 'guide', noCache: true }
    }]
  }
]

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  /*用户管理*/
  {
    path: '/systemManagement',
    component: Layout,
    redirect: '/systemManagement',
    name: 'system',
    meta: {
      title: '系统管理',
      icon: 'system',
      roles: ['admin']
    },
    children: [{
        path: 'resourceManagement',
        component: () => import('@/views/system/resourceManagement'),
        name: 'resourceManagement',
        meta: { title: '资源管理', icon: 'international' }
      },
      {
        path: 'userManagement',
        component: () => import('@/views/system/userManagement'),
        name: 'userManagement',
        meta: { title: '用户管理', icon: 'user' }
      },

      {
        path: 'roleManagement',
        component: () => import('@/views/system/roleManagement'),
        name: 'roleManagement',
        meta: { title: '角色管理', icon: 'user' }
      }
    ]
  },
 /*基础设置*/
 {
  path: '/settings',
  component: Layout,
  redirect: '/settings',
  name: 'settings',
  meta: {
    title: '基础设置',
    icon: 'setting',
    roles: ['admin', 'supplier_manager']
  },
  children: [{
    path: 'suplierScoreRules',
    component: () => import('@/views/settings/suplierScoreRules'),
    name: 'suplierScoreRules',
    meta: { title: '供应商评分规则', icon: 'table' , roles: ['admin','supplier_manager']}
  },
  {
    path: 'setting',
    component: () => import('@/views/settings/setting'),
    name: 'setting',
    meta: { title: '基础参数', icon: 'edit' , roles: ['admin','supplier_manager']}
  }
  ]
},
  //供应商
  {
    path: '/supplier',
    component: Layout,
    redirect: 'noredirect', 
    name:'supplier',
    meta: {
      title: '供应商',
      icon: 'menu_supplier',
      roles: ['admin', 'supplier_manager','supplier_grade_pass']
    },
    children: [{
        path: 'supplierList',
        component: () => import('@/views/supplier/supplierList'),
        name: 'supplierList',
        meta: { title: '待审核供应商', icon: 'peoples', roles: ['admin', 'supplier_manager']}
      },
      {
        path: 'qualifiedSupplier',
        component: () => import('@/views/supplier/qualifiedSupplier'),
        name: 'qualifiedSupplier',
        meta: { title: '合格供应商列表', icon: 'user',}
      },      
      {
        path: 'supplierMothScore',
        component: () => import('@/views/supplier/supplierMothScore'),
        name: 'supplierMothScore',
        meta: { title: '供应商月度评分', icon: 'table' , roles: ['admin']}
      },
       
      {
        path: 'supplierScoreEntry',
        component: () => import('@/views/supplier/supplierScoreEntry'),
        name: 'supplierScoreEntry',
        meta: { title: '供应商评分录入', icon: 'edit' , roles: ['admin','supplier_manager']}
      },
      {
        path: 'supplierApprove',
        component: () => import('@/views/supplier/supplierApprove'),
        name: 'supplierApprove',
        //hidden: true,
        meta: { title: '供应商年度审核', icon: 'documentation' , roles: ['admin']}
      }, {
        path: 'addSupplier',
        component: () => import('@/views/supplier/addSupplier'),
        name: 'addSupplier',
        hidden: true,
        meta: { title: '编辑供应商', icon: 'edit' }
      },
      {
        path:'materialPriceChart',
        component: () => import('@/views/supplier/materialPriceChart'),
        name:'materialPriceChart',
        meta: {icon: 'chart', title: '物料价格曲线',roles: ['admin', 'supplier_manager']}
    }
    ]
  },
  //物料管理

  {
    path: '/materiel',
    component: Layout,  
    redirect:'materielManage',  
    name:'materielManage',
    meta: {
      title: '物料',
      icon: 'mate',
      roles: ['admin', 'supplier_manager']
    },
    children: [{
        path:'materielManage',
        component: () => import('@/views/materiel/materielManage'),
        name:'materielManage',
        meta: {icon:'documentation', title: '物料管理',roles: ['admin','supplier_manager']}

    },{
        path:'materialCategory',
        component: () => import('@/views/materiel/materialCategory'),
        name:'materialCategory',
        meta: {icon: 'list', title: '物料分类',roles: ['admin,supplier_manager']}

    }
    ]
  },

   //询价管理

  {
    path: '/enquiry',
    component: Layout,  
    redirect:'enquiry',  
    name:'enquiry',
    meta: {
      title: '询价管理',
      icon: 'enquiry',
      roles: ['admin', 'supplier_manager']
    },
    children: [
      {
        path: 'enqAdd',
        component: () => import('@/views/enquiry/enqAdd'),
        name: 'enqAdd',
        meta: { title: '新料询价', icon: 'menu_enqAdd',roles: ['admin','supplier_manager'] }
      },{
        path:'enquiryList',
        component: () => import('@/views/enquiry/enquiryList'),
        name:'enquiryList',
        meta: {icon:'cost', title: '询价单查询',roles: ['admin','supplier_manager']}

    },
    {
        path:'enquiryDetail',
        component: () => import('@/views/enquiry/enquiryDetail'),
        name:'enquiryDetail',hidden: true,
        meta: {icon: 'icon', title: '询价单详情',roles: ['admin','supplier_manager']}
    },
    {
        path:'materialCategory',
        component: () => import('@/views/materiel/materialCategory'),
        name:'',
        meta: {icon: 'gdxj', title: '固定询价',roles: ['admin']}
    }
    ]
  },
     //报价管理

  {
    path: '/quotation',
    component: Layout,  
    redirect:'quotation',  
    name:'quotation',
    meta: {
      title: '报价管理',
      icon: 'quote',
      roles: ['admin', 'supplier_manager']
    },
    children: [{
        path:'quoList',        
        component: () => import('@/views/quo/quoList'),
        name:'quoList',
        meta: {icon:'documentation', title: '报价单查询',roles: ['admin','supplier_manager']}

    },{
        path:'quoDetail',
        component: () => import('@/views/quo/quoDetail'),
        name:'quoDetail', hidden: true,
        meta: {icon: 'icon', title: '报价单明细',roles: ['admin','supplier_manager']}
    },
    {
        path:'quoAddList',
        component: () => import('@/views/quo/quoAddList'),
        name:'quoAddList', 
        meta: {icon: 'icon', title: '填写报价单',roles: ['admin','supplier_manager']}
    },
    {
        path:'quoAdd',
        component: () => import('@/views/quo/quoAdd'),
        name:'quoDateEnd',hidden: true,
        meta: {icon: 'icon', title: '填写报价单',roles: ['admin','supplier_manager']}
    }
   
    ]
  },
//成本管理

 {
    path: '/cost',
    component: Layout,  
    redirect:'/cost',  
    name:'cost',
    meta: {
      title: '成本管理',
      icon: 'costC',
      roles: ['admin', 'supplier_manager']
    },
    children: [{
        path:'clientBomRate',
        component: () => import('@/views/cost/clientBomRate'),
        name:'clientBomRate',
        meta: {icon:"link", title: '客户BOM导入',roles: ['admin','supplier_manager']}

    },{
      path:'clientBomList',
      component: () => import('@/views/cost/clientBomList'),
      name:'clientBomList',
      meta: {icon:"list", title: '客户BOM列表',roles: ['admin','supplier_manager']}
    },{
      path:'instructionsCost',
      component: () => import('@/views/cost/instructions'),
      name:'instructionsCost', hidden: true,
      meta: { title: '操作说明',roles: ['admin','supplier_manager']}
  },{
      path:'priceChart',
      component: () => import('@/views/cost/priceChart'),
      name:'priceChart',
      meta: {icon:'quxian', title: '物料成本价格曲线',roles: ['admin','supplier_manager']}
  },
  {
      path:'setSmtPoint',
      component: () => import('@/views/cost/setSmtPoint'),
      name:'setSmtPoint',
      meta: {icon:'example', title: '设置SMT点数',roles: ['admin','supplier_manager']}
  }
    
    ]
  }, 

  {
    path: '/permission',
    component: Layout,
    redirect: '/permission/index',
    alwaysShow: true, // will always show the root menu
    meta: {
      title: 'permission',
      icon: 'lock',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [{
        path: 'page',
        component: () => import('@/views/permission/page'),
        name: 'PagePermission',
        meta: {
          title: 'pagePermission',
          roles: ['admin'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'directive',
        component: () => import('@/views/permission/directive'),
        name: 'DirectivePermission',
        meta: {
          title: 'directivePermission'
          // if do not set roles, means: this page does not require permission
        }
      }
    ]
  },

  {
    path: '/icon',
    component: Layout,
    children: [{
      path: 'index',
      component: () => import('@/views/svg-icons/index'),
      name: 'Icons',
      meta: { title: 'icons', icon: 'icon', noCache: true, roles: ['admin'] }
    }]
  },

  /** When your routing table is too long, you can split it into small modules**/
  componentsRouter,
  chartsRouter,
  nestedRouter,
  tableRouter,

  {
    path: '/example',
    component: Layout,
    redirect: '/example/list',
    name: 'Example',
    meta: {
      title: 'example',
      icon: 'example',
      roles: ['admin']
    },
    children: [{
        path: 'create',
        component: () => import('@/views/example/create'),
        name: 'CreateArticle',
        meta: { title: 'createArticle', icon: 'edit' }
      },
      {
        path: 'edit/:id(\\d+)',
        component: () => import('@/views/example/edit'),
        name: 'EditArticle',
        meta: { title: 'editArticle', noCache: true },
        hidden: true
      },
      {
        path: 'list',
        component: () => import('@/views/example/list'),
        name: 'ArticleList',
        meta: { title: 'articleList', icon: 'list' }
      }
    ]
  },

  {
    path: '/tab',
    component: Layout,
    children: [{
      path: 'index',
      component: () => import('@/views/tab/index'),
      name: 'Tab',
      meta: { title: 'tab', icon: 'tab', roles: ['admin'] }
    }]
  },

  {
    path: '/error',
    component: Layout,
    redirect: 'noredirect',
    name: 'ErrorPages',
    meta: {
      title: 'errorPages',
      icon: '404',
      roles: ['admin']
    },
    children: [{
        path: '401',
        component: () => import('@/views/errorPage/401'),
        name: 'Page401',
        meta: { title: 'page401', noCache: true, roles: ['admin'] }
      },
      {
        path: '404',
        component: () => import('@/views/errorPage/404'),
        name: 'Page404',
        meta: { title: 'page404', noCache: true }
      }
    ]
  },

  {
    path: '/error-log',
    component: Layout,
    redirect: 'noredirect',
    meta: {
      roles: ['admin']
    },
    children: [{
      path: 'log',
      component: () => import('@/views/errorLog/index'),
      name: 'ErrorLog',
      meta: { title: 'errorLog', icon: 'bug' }
    }]
  },

  {
    path: '/excel',
    component: Layout,
    redirect: '/excel/export-excel',
    name: 'Excel',
    meta: {
      title: 'excel',
      icon: 'excel',
      roles: ['admin']
    },
    children: [{
        path: 'export-excel',
        component: () => import('@/views/excel/exportExcel'),
        name: 'ExportExcel',
        meta: { title: 'exportExcel' }
      },
      {
        path: 'export-selected-excel',
        component: () => import('@/views/excel/selectExcel'),
        name: 'SelectExcel',
        meta: { title: 'selectExcel' }
      },
      {
        path: 'upload-excel',
        component: () => import('@/views/excel/uploadExcel'),
        name: 'UploadExcel',
        meta: { title: 'uploadExcel' }
      }
    ]
  },

  {
    path: '/zip',
    component: Layout,
    redirect: '/zip/download',
    alwaysShow: true,
    meta: { title: 'zip', icon: 'zip', roles: ['admin'] },
    children: [{
      path: 'download',
      component: () => import('@/views/zip/index'),
      name: 'ExportZip',
      meta: { title: 'exportZip' }
    }]
  },

  {
    path: '/theme',
    component: Layout,
    redirect: 'noredirect',
    meta: {
      roles: ['admin']
    },
    children: [{
      path: 'index',
      component: () => import('@/views/theme/index'),
      name: 'Theme',
      meta: { title: 'theme', icon: 'theme' }
    }]
  },

  {
    path: '/clipboard',
    component: Layout,
    redirect: 'noredirect',
    children: [{
      path: 'index',
      component: () => import('@/views/clipboard/index'),
      name: 'ClipboardDemo',
      meta: { title: 'clipboardDemo', icon: 'clipboard', roles: ['admin'] }
    }]
  },

  {
    path: '/i18n',
    component: Layout,
    meta: {
      roles: ['admin']
    },
    children: [{
      path: 'index',
      component: () => import('@/views/i18n-demo/index'),
      name: 'I18n',
      meta: { title: 'i18n', icon: 'international', roles: ['admin'] }
    }]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [{
      path: 'https://github.com/PanJiaChen/vue-element-admin',
      meta: { title: 'externalLink', icon: 'link', roles: ['admin'] }
    }]
  },   

  { path: '*', redirect: '/404', hidden: true },

]
