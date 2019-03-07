<template>
    <Menu active-name="IDX" style="width:100%;height:100%;" :open-names="['1']" accordion>
        <Submenu v-for="item of data" :key="item.code" :name="item.code" @click.native="handleClick(item)">
            <template slot="title">
                <Icon :type="item.icon"></Icon>
                {{item.title}}
            </template>
            <MenuItem v-for="subitem in item.children" :name="subitem.code" @click.native="selectNode(subitem)">
                <Icon :type="item.icon"></Icon>
                {{subitem.title}}
            </MenuItem>
            <!--<Tree :data="item.children" class="layout-menu-left" @on-select-change="selectNode" ></Tree>-->
            <!--<cust-tree :data="item.children"></cust-tree>-->
        </Submenu>
    </Menu>
</template>
<script>
//import custTree from "./cust-tree.vue";
    export default {
        //components: {
        //    custTree,
        //},
        name: 'left-nav',
        props: {
            data: Array
        },
        data() {
            return {
            }
        },
        created() {
        },
        computed: {
        },
        methods: {
            handleClick(item) {
                this.$emit("handleNavClick", item);
            },
            selectNode(item) {
                this.$store.commit('updateMenuDataStates', item);
                this.$router.push({
                    path: item.path
                });
                this.$emit('on-change', item);
            }
        }
    };
</script>
<style>
    .ivu-menu-submenu-title>i, .ivu-menu-submenu-title span>i{
        margin-right:0;
    }
</style>