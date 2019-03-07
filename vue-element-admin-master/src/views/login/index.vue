<template>
  <div class="login-container">
    <div class="logo"><img src="./img/logo.png" width="240"></div>
    <div class="login-form"><div class="sysyName"> <span style="letter-spacing:9px;font-size: 18px;">供应商关系管理平台</span><br><span style="color:#555">Supplier relationship management </span></div>
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">{{ $t('login.title') }}</h3>
     <!--    <lang-select class="set-language"/> -->
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          v-model="loginForm.username"
          :placeholder="$t('login.username')"
          name="username"
          type="text"
          auto-complete="on" 
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :type="passwordType"
          v-model="loginForm.password"
          :placeholder="$t('login.password')"
          name="password"
          auto-complete="on"
          @keyup.enter.native="handleLogin" />
        <span class="show-pwd" @click="showPwd">
          <svg-icon icon-class="eye" />
        </span>
      </el-form-item>
      <el-row>
        <el-col :span="8"><el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin"> <svg-icon icon-class="user" style="margin-right: 10px" />{{ $t('login.logIn') }}</el-button></el-col>
         <el-col :span="8" :offset="6">
             <el-button :loading="loading" style="width:100%;margin-bottom:30px; background:#204780;color:#fff;border:none;" @click="handleRister('register')"> <svg-icon icon-class="form" style="margin-right: 10px" />注册</el-button>
         </el-col>
       </el-row>     
    
    </el-form>
    </div>
    <div class="login_img">
       <img src="./img/login_img.png">
    </div>
    <div class="footer">版权所有 © 2018-2019  深圳市信恳智能电子股份有限公司--www.szxinken.com</div>  

  </div>
</template>

<script>
//import { isvalidUsername } from '@/utils/validate'
//import LangSelect from '@/components/LangSelect'
import SocialSign from './socialsignin'

export default {
  name: 'Login',
  components: { SocialSign },
  data() {  
  
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', message: "账号不能为空"}],
        password: [{ required: true, trigger: 'blur',message: "密码不能为空" }]
      },
      passwordType: 'password',
      loading: false,
      showDialog: false,
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }

  },
  created() {
    // window.addEventListener('hashchange', this.afterQRScan)
  },
  destroyed() {
    // window.removeEventListener('hashchange', this.afterQRScan)
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          //this.loading = true
          this.$store.dispatch('LoginByUsername', this.loginForm).then(() => {      
           
            this.loading = false
            this.$router.push({ path: this.redirect || '/' })
           
          }).catch(() => {
            this.$Message.error(res.msg);
            this.loading = false
          })
        } else {
            console.log('error submit')
            return false
        }
      })
    },
    handleRister(url) {       
       this.$router.push({path: url}); 
    },
    afterQRScan() {
      // const hash = window.location.hash.slice(1)
      // const hashObj = getQueryObject(hash)
      // const originUrl = window.location.origin
      // history.replaceState({}, '', originUrl)
      // const codeMap = {
      //   wechat: 'code',
      //   tencent: 'code'
      // }
      // const codeName = hashObj[codeMap[this.auth_type]]
      // if (!codeName) {
      //   alert('第三方登录失败')
      // } else {
      //   this.$store.dispatch('LoginByThirdparty', codeName).then(() => {
      //     this.$router.push({ path: '/' })
      //   })
      // }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg:#fff;
  $light_gray:#333;
  $innerbg:rgba(255,255,255,0.5);
  $cursor: #333;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input{
      color: $cursor;
      &::first-line {
        color: $light_gray;
      }
    }
  }

  /* reset element-ui css */
  .login-container {
    .logo{
      margin:10px;
    }
    .el-input {
      display: inline-block;
      height: 42px;
      width: 80%;
      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 42px;
        caret-color: $cursor;
        &:-webkit-autofill {
          -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
      line-height:22px!important;
    }   
 
    .footer{position:absolute;bottom:10px;text-align:center;width:100%;color:#fff;}
    .login_img{position:absolute;bottom:15%;left:10%;}
  }
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#222;
$light_gray:#444;

.login-container {
  position: fixed;
  top:0;
  left:100;
  height: 100%;
  width: 100%;
  min-width: 1000px;
  background:url('./img/bg.png') no-repeat center center;
  background-size:100% 100%;
  .login-form {
    position: absolute;
    left: 180px;
    right: 0px;
    width: 430px;
    max-width: 100%;
    padding: 35px 35px 15px 35px;
    margin: 150px auto;
   background: rgba(255,255,255,0.9);
   box-shadow: 2px 13px 16px #368fdc;

  }
.sysyName{position:absolute;top:-60px; width:80%;text-align:center;color:#0068c5;}
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  .title-container {
    position: relative;
    .title {
      font-size: 26px;
      color: #399aed;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
    .set-language {
      color: #fff;
      position: absolute;
      top: 5px;
      right: 0px;
    }
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
  .thirdparty-button {
    position: absolute;
    right: 35px;
    bottom: 28px;
  }

}


</style>
