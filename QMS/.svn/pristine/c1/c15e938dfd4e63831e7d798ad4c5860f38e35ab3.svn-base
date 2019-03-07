// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import Vuex from 'vuex'
import VueI18n from 'vue-i18n'

import store from './vuex/store'
import router from './router'
import components from './components'
import directives from './directives/directives'
import ajax from './plugins/ajax'
import loading from './plugins/loading'
import api from './api/index'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import cn from './locale/cn-zh'
import en from './locale/en-us'
import './assets/js/flow.js'

/*import Xzszlc from './Xzszlc'*/


Vue.config.productionTip = false
Vue.prototype.api = api
Vue.use(ajax)
Vue.use(loading)
Vue.use(ElementUI)
Vue.use(VueI18n)

if(Vue.config.debug){

}
//Vue.http.defaults.baseURL = 'http://localhost:8081/admin'
Vue.prototype.HOST = '/admin'

Object.keys(components).forEach((key) => {
	Vue.component(key, components[key])
	Vue.prototype.$Loading = components['LoadingBar']
	Vue.prototype.$Message = components['Message']
	Vue.prototype.$Modal = components['Modal']
	Vue.prototype.$Notice = components['Notice']
})

const localizations = {
	'cn-zh': cn, 
	'en-us': en
}

const i18n = new VueI18n({
  locale: 'cn-zh', // set locale
  messages: localizations, // set locale messages
})

/* eslint-disable no-new */
new Vue({
	el: '#app',
	i18n,
	store,
	router,
	render: h => h(App)
})