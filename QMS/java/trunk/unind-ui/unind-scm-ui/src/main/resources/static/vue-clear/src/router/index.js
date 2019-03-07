//文档地址：https://router.vuejs.org/zh-cn/

import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const prefix = '/scm'
const routes = [
    {
        path: '/login',
        name: 'login',
        component: resolve => require(['@/view/member/user/login'], resolve)
    },
    {
        path: '/404',
        name: '',
        component: resolve => require(['@/view/error/404'], resolve)
    },
    {
        path: '/',
        component: resolve => require(['@/view/home/index'], resolve),
        name: 'home',
        iconCls: 'el-icon-message',//图标样式class
        children: [
            { path: prefix + '/main', component: resolve => require(['@/view/member/index'], resolve), name: '主页', hidden: true },
            { path: prefix + '/group', component: resolve => require(['@/view/member/group/grouplist'], resolve), name: 'group' },
            { path: prefix + '/organization', component: resolve => require(['@/view/member/org/orglist'], resolve), name: 'organization' },
            { path: prefix + '/resrce', component: resolve => require(['@/view/member/resrce/index'], resolve), name: '资源管理' },
            { path: prefix + '/user', component: resolve => require(['@/view/member/user/index'], resolve), name: '用户管理' },
            { path: prefix + '/userrole', component: resolve => require(['@/view/member/userrole/index'], resolve), name: '用户角色' },
            { path: prefix + '/rolegroup', component: resolve => require(['@/view/member/rolegroup/index'], resolve), name: '角色组' },
            { path: prefix + '/role', component: resolve => require(['@/view/member/role/index'], resolve), name: '角色管理' },
            { path: prefix + '/perm', component: resolve => require(['@/view/member/perm/index'], resolve), name: '权限列表' },
            { path: prefix + '/modifyUser', component: resolve => require(['@/view/member/user/modifyUser'], resolve), name: 'modifyUser'},
            { path: prefix + '/pmp', component: resolve => require(['@/view/member/pmp/index'], resolve), name: '计划预测'}
        ]
    }
    
]

//路由配置
const RouterConfig = {
    mode: 'history',
    routes: routes
}

const router = new VueRouter(RouterConfig)

router.beforeEach((to, from, next) => {
  if (to.path == '/login') {
    sessionStorage.removeItem('user');
  }
  let user = JSON.parse(sessionStorage.getItem('user'));
  if (!user && to.path != '/login') {
      console.log('ccc')
    next({ path: '/login' })
  } else {
    next()
  }
})

export default router