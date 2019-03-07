<template>
    <div class="top-menu">
        <Button type="text" @click="toggleClick">
            <Icon type="log-in" size="32"></Icon>
        </Button>
    </div>
</template>
<script>
export default {
    data() {
        return {
          
        }
    },
    props: {
        show: Boolean
    },
    methods: {
        toggleClick(){
            this.$Modal.confirm({
                title: '确认信息',
                content: '<p>是否确定退出登录？</p>',
                loading: false,
                onOk: () => {
                    this.api.login.logout({}).then((res) => {
                        if(res.result) {
                            this.$router.push({path: "/login"});
                        }
                    });
                }
            });
        }
    }
}
</script>

<style lang="less">

    .top-menu {text-align:right;height:60px;background-image: url('../assets/icon/background-img.png')}
    .logo {padding-top:18px;padding-left:30px;}

</style>