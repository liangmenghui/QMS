//官方地址： https://github.com/mzabriskie/axios
//文档地址：https://www.kancloud.cn/yunye/axios/234845

import Vue from 'vue'
import Qs from 'qs'
import router from '../router'

export default {
    get: function (url, data, loading = true) {

        return new Promise(function (resolve, reject) {

            if (loading) {
                Vue.$Message.loading({
                    content: '请稍候...',
                    duration: 0
                })
            }

            Vue.http({
                url: Vue.prototype.HOST+url,
                params: data
            }).then((res) => {

                setTimeout(() => {
                    Vue.$Message.destroy()
                }, 300)

                //TODO: 请求失败可在这进行统一处理
                console.log("===================================>");
                console.log(res);
                console.log("<===================================");
                if(!res.data.result && res.data.data==90000) {
                    Vue.$Modal.confirm({
                        title:"",
                        content:"登录已失效，是否重新登录？",
                        onOk: function() {
                    router.push("/login");
                        },
                        onCancel: function() {

                        }
                    });
                    return;
                }
                resolve(res.data)
            }, (res) => {
                Vue.$Message.destroy()
                reject(res.data)
            })
        })
    },
    post: function (url, data, loading = true) {

        return new Promise(function (resolve, reject) {

            if (loading) {
                Vue.$Message.loading({
                    content: '请稍候...',
                    duration: 0
                })
            }

            Vue.http({
                url: Vue.prototype.HOST+url,
                method: 'POST',
                headers: {
                    "X-Requested-With": "XMLHttpRequest",
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                data: Qs.stringify(data)
            }).then((res) => {

                setTimeout(() => {
                    Vue.$Message.destroy()
                }, 300)

                //TODO: 请求失败可在这进行统一处理

                resolve(res.data)
            }, (res) => {
                Vue.$Message.destroy()
                reject(res.data)
            })
        })
    }
}