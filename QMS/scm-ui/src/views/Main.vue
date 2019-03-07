<style lang="less">
    @import "./main.less";
</style>

<template>
    <div class="main" :class="{'main-hide-text': shrink}">
        <div class="sidebar-menu-con" :style="{width: shrink?'60px':'200px', overflow: shrink ? 'visible' : 'hidden'}">
            <div slot="top" class="logo-con">
                <img v-show="!shrink"  src="../images/logo.png" key="max-logo" />
                <img v-show="shrink" src="../images/logo-min.jpg" key="min-logo" />
            </div>
            <left-nav :data="leftMenu" @on-change="handleSubmenuChange"></left-nav>
        </div>
        <div class="main-header-con" :style="{paddingLeft: shrink?'60px':'200px'}">
            <div class="main-header">
                <div class="navicon-con">
                    <Button :style="{transform: 'rotateZ(' + (this.shrink ? '-90' : '0') + 'deg)'}" type="text" @click="toggleClick">
                        <Icon type="navicon" size="32"></Icon>
                    </Button>
                </div>
                <div class="header-middle-con">
                    <div class="main-breadcrumb">
                        <top-nav :data="topMenu" @handleNavClick="handleNavClick"></top-nav>
                    </div>
                </div>
                <div class="header-avator-con">
                     <div class="language">
                   
                     <el-dropdown trigger="hover" placement="bottom">                   
                    <span class="el-dropdown-link"><Icon type="ios-world-outline" class="Person"></Icon> </span>
                    <el-dropdown-menu slot="dropdown">                       
                        <el-dropdown-item @click.native="changelangugetocn('cn-zh')">简体中文</el-dropdown-item>
                        <el-dropdown-item @click.native="changelangugetoen('en-US')">English</el-dropdown-item>
                        <el-dropdown-item @click.native="changelangugetotw('cn-tw')">繁体中文</el-dropdown-item>           
                    </el-dropdown-menu>
                </el-dropdown>
                </div>
                    <full-screen v-model="isFullScreen" @on-change="fullscreenChange"></full-screen>
                    <lock-screen></lock-screen>
                    <message-tip v-model="mesCount"></message-tip>
                    <theme-switch></theme-switch>
                    
                    <div class="user-dropdown-menu-con">
                        <Row type="flex" justify="end" align="middle" class="user-dropdown-innercon">
                            <Dropdown transfer trigger="click" @on-click="handleClickUserDropdown">
                                <a href="javascript:void(0)">
                                    <span class="main-user-name">{{ userName }}</span>
                                    <Icon type="arrow-down-b"></Icon>
                                </a>
                                <DropdownMenu slot="list">
                                    <DropdownItem name="ownSpace">个人中心</DropdownItem>
                                    <DropdownItem name="loginout" divided>退出登录</DropdownItem>
                                </DropdownMenu>
                            </Dropdown>
                            
                        </Row>
                    </div>
                </div>
            </div>
            <div class="tags-con">
                <tags-page-opened :pageTagsList="pageTagsList"></tags-page-opened>
            </div>
        </div>
        <div class="single-page-con" :style="{left: shrink?'60px':'200px'}">
            <div class="single-page">
                <keep-alive :include="cachePage">
                    <router-view  :langz="language"></router-view>
                </keep-alive>
            </div>
        </div>
    </div>
</template>
<style>
    .logo-con{background: #2e76ee;}
    .ivu-breadcrumb{display: inline-block;}
    .navbutton{    position: absolute;
    left: 30%;
    top: 11px;}
    .language{
    display:inline-block;margin-right:40px;position:relative;   
 }
.language a{color:#0a435f; display:inline-block; height:30px;}
.language a:hover{color:#fff;}
.language .Person{font-size:24px;position:absolute;top:-16px; color: blue; }
.main .single-page-con .single-page{margin-top:25px!important;}
.main .single-page-con{background: #fff!important;}
.main-header .header-middle-con{top:-17px!important;}

</style>

<script>
import topNav from "./main-components/top-nav.vue";
import leftNav from "./main-components/left-nav.vue";
import tagsPageOpened from "./main-components/tags-page-opened.vue";
import fullScreen from "./main-components/fullscreen.vue";
import lockScreen from "./main-components/lockscreen/lockscreen.vue";
import messageTip from "./main-components/message-tip.vue";
import themeSwitch from "./main-components/theme-switch/theme-switch.vue";
import Cookies from "js-cookie";
import util from "@/libs/util.js";

export default {
  components: {
    topNav,
    leftNav,
    tagsPageOpened,
    fullScreen,
    lockScreen,
    messageTip,
    themeSwitch
  },
  data() {
    return {
      language:this.$i18n.locale,
      shrink: false,
      userName: "",
      isFullScreen: false,
      openedSubmenuArr: this.$store.state.app.openedSubmenuArr,
      topMenu: [],
      leftMenu: []
    };
  },
  computed: {
    pageTagsList() {
      return this.$store.state.app.pageOpenedList; // 打开的页面的页面对象
    },
    currentPath() {
      return this.$store.state.app.currentPath; // 当前面包屑数组
    },
    avatorPath() {
      return localStorage.avatorImgPath;
    },
    cachePage() {
      return this.$store.state.app.cachePage;
    },
    lang() {
      return this.$store.state.app.lang;
    },
    menuTheme() {
      return this.$store.state.app.menuTheme;
    },
    mesCount() {
      return this.$store.state.app.messageCount;
    }
  },
  methods: {
    changelangugetocn(){
        this.$i18n.locale='zh-CN';
        this.language = 'zh-CN';
        Cookies.set('language','zh-CN');
    },
    changelangugetoen(){
        this.$i18n.locale='en-US'
        this.language = 'en-US';
        Cookies.set('language','en-US');
    },
    changelangugetotw(){
       this.$i18n.locale='zh-TW'
       this.language = 'zh-TW';
       Cookies.set('language','zh-TW');
    },
    init() {
      let pathArr = util.setCurrentPath(this, this.$route.name);
      //this.$store.commit('updateMenulist');
      if (pathArr.length >= 2) {
        this.$store.commit("addOpenSubmenu", pathArr[1].name);
      }
      this.userName = Cookies.get("user");
      let messageCount = 3;
      this.messageCount = messageCount.toString();
      this.checkTag(this.$route.name);
      this.$store.commit("setMessageCount", 3);
      this.getMenuList();
    },
    toggleClick() {
      this.shrink = !this.shrink;
    },
    handleClickUserDropdown(name) {
      if (name === "ownSpace") {
        util.openNewPage(this, "ownspace_index");
        this.$router.push({
          name: "ownspace_index"
        });
      } else if (name === "loginout") {
        // 退出登录
        this.api.admin.login.logout().then(res => {
          if (res.result) {
            this.$store.commit("logout", this);
            this.$store.commit("clearAllTags");
            this.$router.push({
              name: "login"
            });
          }
        });
      }
    },
    checkTag(name) {
      let openpageHasTag = this.pageTagsList.some(item => {
        if (item.name === name) {
          return true;
        }
      });
      if (!openpageHasTag) {
        //  解决关闭当前标签后再点击回退按钮会退到当前页时没有标签的问题
        util.openNewPage(
            this,
            name,
            this.$route.params || {},
            this.$route.query || {}
        );
      }
    },
    handleSubmenuChange(val) {      
    },
    beforePush(name) {
      // if (name === 'accesstest_index') {
      //     return false;
      // } else {
      //     return true;
      // }
      return true;
    },
    fullscreenChange(isFullScreen) {
    },
    getIviewData(data, tagList, permissions) {
        let ary = [];
        for(let index in data) {
            let menu = data[index];
            let m = {};
            m.id = menu.resrceId;
            if(m.id == undefined) continue;
            m.code = menu.resrceCode;
            m.name = menu.resrceCode;
            m.title = menu.resrceName;
            m.icon = menu.bsIconCls;
            m.path = menu.bsUrl;
            m.perms = menu.perms;
            if (menu.bsUrl && menu.perms) {
              var url = menu.bsUrl.split("?")[0];
              permissions[url] = m.perms;
            }
            m.attributes = menu;
            if(menu.children) {
                m.children = this.getIviewData(menu.children, tagList, permissions);
                if(m.children.length==1) {
                    tagList.push(m.children[0]);
                }else {
                    tagList.push(...m.children);
                }
            }
            ary.push(m);
        }
        return ary;
    },
    getMenuList() {
      this.api.admin.userresrce.getlist().then(res => {
        if (res.result) {
          let tagsList = [], permissions = {};
          this.topMenu = this.getIviewData(res.data, tagsList, permissions);
          if(this.topMenu.length) this.leftMenu = this.topMenu[0].children;
          this.$store.commit("setTagsList", tagsList);
          this.$store.commit("updateAllPermissions", permissions);
          if(this.topMenu && this.topMenu[0].children && this.topMenu[0].children.length>0) {
            this.leftMenu = this.topMenu[0].children;
          }
        }
      });
    },
    handleNavClick(item) {
      this.leftMenu = item.children;
    }
  },
  watch: {
    $route(to) {
      this.$store.commit("setCurrentPageName", to.name);

      let pathArr = util.setCurrentPath(this, to.name);
      if (pathArr.length > 2) {
        this.$store.commit("addOpenSubmenu", pathArr[1].name);
      }
      this.checkTag(to.name);
      localStorage.currentPageName = to.name;
    },
    lang() {
      util.setCurrentPath(this, this.$route.name); // 在切换语言时用于刷新面包屑
    }
  },
  mounted() {
    this.init();
  },
  created() {
    // 显示打开的页面的列表
    this.$store.commit("setOpenedList");
  }
};
</script>
