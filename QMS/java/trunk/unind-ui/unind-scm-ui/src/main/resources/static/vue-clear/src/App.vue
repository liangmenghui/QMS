<template>
    <div id="app">
		<transition name="fade" mode="out-in">
			<router-view></router-view>
		</transition>
    </div>
</template>

<script>
export default {
    name: 'app'
}
</script>

<style lang="less">
    @import '~iview/dist/styles/iview.css';
    @import 'assets/style/global.less';
    @import 'https://cdn.bootcss.com/animate.css/3.5.2/animate.css';
</style>