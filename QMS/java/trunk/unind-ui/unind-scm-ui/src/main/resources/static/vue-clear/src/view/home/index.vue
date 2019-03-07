<template>
    <div class="layout" :class="{'layout-hide-text': spanLeft < 5}">
        <div class="layout-top">
            <top-menu></top-menu>
        </div>
        <Row type="flex" style="min-height:100%">
            <i-col :span="spanLeft" class="layout-menu-left">
                <left-menu></left-menu>
            </i-col>
            <i-col :span="spanRight">
                <div class="layout-header">
                    <i-button type="text" @click="toggleClick">
                        <Icon type="navicon" size="32"></Icon>
                    </i-button>
                    <span class="layout-breadcrumb">
                        <Breadcrumb>
                            <Breadcrumb-item href="#">首页</Breadcrumb-item>
                            <Breadcrumb-item href="#">应用中心</Breadcrumb-item>
                            <Breadcrumb-item>某应用</Breadcrumb-item>
                        </Breadcrumb>
                    </span>
                </div>
                <div class="layout-content">
                    <div class="layout-content-main">
                    <transition name="fade" mode="out-in">
                        <router-view class="router-view"></router-view>
                    </transition>
                    </div>
                </div>
            </i-col>
        </Row>
        <div class="layout-copy">
            深圳金合联供应链技术有限公司 Copyright © 2017~2020
        </div>-->
    </div>
</template>


<script>
import TopMenu from '../../components/top-menu.vue'
import LeftMenu from '../../components/left-menu.vue'
export default {
    data() {
        return {
            grid:{
            },
            spanLeft: 5,
            spanRight: 19
        }
    },
    computed: {
        iconSize () {
            return this.spanLeft === 5 ? 14 : 24;
        }
    },
    methods: {
        toggleClick () {
            if (this.spanLeft === 5) {
                this.spanLeft = 2;
                this.spanRight = 22;
            } else {
                this.spanLeft = 5;
                this.spanRight = 19;
            }
        },
        clicked() {
            //Ajax调用示例
            this.api.test.joke().then((res) => {
                console.log(res)
            })
        }
    },
    components: {
        TopMenu, LeftMenu
    }
}
</script>

<style lang="less">

    @import '~iview/dist/styles/iview.css';
    @import '../../assets/style/global.less';
    @import 'https://cdn.bootcss.com/animate.css/3.5.2/animate.css';
    .layout{
        background: #f5f7f9;
        position: relative;
        overflow: hidden;
        height: 100%;
    }
    .layout-menu-left{
        height: 100%;
    }
    .layout-logo{
        height: 60px;
        text-align: center;
        background-color: white;
        line-height: 60px;
    }

</style>