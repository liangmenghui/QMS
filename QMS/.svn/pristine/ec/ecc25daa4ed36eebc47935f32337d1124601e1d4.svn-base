<template>
    <div class="left-menu">
        <!--<Menu :open-names="['1']" active-name="1" accordion style="width:100%">
            <Submenu name="1">
                <template slot="title">
                    <Icon type="ios-paper"></Icon>
                    系统管理    
                </template>
                <Menu-item name="1-1" @click.native="to('/resrce')">
                    <Icon type="document-text"></Icon>
                    资源管理
                </Menu-item>
                <Menu-item name="1-2" @click.native="to('/main')">
                    <Icon type="document-text"></Icon>
                    测试
                    </Menu-item>
            </Submenu>
            <Submenu name="2">
                <template slot="title">
                    <Icon type="ios-people"></Icon>
                    用户管理
                </template>
                <Menu-item name="2-1" @click.native="to('/user')">
                    <Icon type="document-text"></Icon>
                    用户管理
                </Menu-item>
                <Menu-item name="2-2"  @click.native="to('/group')">
                    <Icon type="chatbubbles"></Icon>
                    组管理
                </Menu-item>
                <Menu-item name="2-3"  @click.native="to('/organization')">
                    <Icon type="chatbubbles"></Icon>
                    组织管理
                </Menu-item>
                <Menu-item name="2-4"  @click.native="to('/rolegroup')">
                    <Icon type="chatbubbles"></Icon>
                    角色组管理
                </Menu-item>
                <Menu-item name="2-5"  @click.native="to('/role')">
                    <Icon type="chatbubbles"></Icon>
                    角色管理
                </Menu-item>
                <Menu-item name="2-6"  @click.native="to('/perm')">
                    <Icon type="chatbubbles"></Icon>
                    操作权限管理
                </Menu-item>
                <Menu-item name="2-7"  @click.native="to('/userrole')">
                    <Icon type="chatbubbles"></Icon>
                    用户角色管理
                </Menu-item>
            </Submenu>
            <Submenu name="3">
                <template slot="title">
                    <Icon type="stats-bars"></Icon>
                    统计分析
                </template>
                <Menu-group title="使用">
                    <Menu-item name="3-1">新增和启动</Menu-item>
                    <Menu-item name="3-2">活跃分析</Menu-item>
                    <Menu-item name="3-3">时段分析</Menu-item>
                </Menu-group>
                <Menu-group title="留存">
                    <Menu-item name="3-4">用户留存</Menu-item>
                    <Menu-item name="3-5">流失用户</Menu-item>
                </Menu-group>
            </Submenu>

        </Menu>-->

        <Menu :open-names="['1']" active-name="1" accordion style="width:100%">
            <Submenu v-for="item of data" :key="item" :name="item.resrceId">
                <template slot="title">
                    <Icon type="ios-paper"></Icon>
                    {{item.resrceName}}
                </template>
                <Menu-item v-if="item.children.length>0" v-for="child in item.children" :key="child" :name="child.resrceCode" @click.native="to(child)">
                    <Icon type="document-text"></Icon>
                    {{child.resrceName}}
                </Menu-item>
            </Submenu>
        </Menu>
    </div>
</template>
<script>
export default {
    data() {
        return {
            theme3:'light',
            data: []
        }
    },
    created() {
        this.getlist();
    },
  
    methods: {
        clicked() {
            //Ajax调用示例
            this.api.test.joke().then((res) => {
                console.log(res)
            })
        },
        to(obj) {
            console.log(JSON.stringify(obj));
            this.$store.commit('updateMenuDataStates',obj);
            this.$router.push({
                path: obj.bsUrl
                //path: '' + obj
            })
        },
        getlist() {
            this.api.userresrce.getlist().then((res) => {
                if(res.result) {
                    this.data = res.data;
                }
            });
        }
    }
}

</script>

<style lang="less">
</style>

