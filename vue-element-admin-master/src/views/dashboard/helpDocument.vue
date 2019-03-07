<template>
  <div class="dashboard-container" style="padding: 5px 20px">
      <el-row>
        <el-col :span="24">
            <div class="grid-content"><h3>系统帮助文档</h3></div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
           <h5>一、供应商注册信息填写</h5>
           <p  class="doucumentTitle"> 进入系统登录页面后，点击页面正下方的供应商注册，进入注册页面，浏览注册协议后，选中我已阅读并接受后才可点击立即注册按钮进入注册信息界面，随后填写注册信息（红色星号标注的为必填；注册大类通过点击下拉和回收，可多选；详细地址需要加入省市，以便后续查看）。最后点击注册按钮即可拥有供应商账号，使用本SRM系统。</p>
        </el-col>
        <center><img src="./img/form.jpg" height="600"></center>
        <h5>二、供应商注册列表以及手工注册</h5>
           <p  class="doucumentTitle">输入用户名密码成功登录系统后，进入的主页就是供应商基本资料页面，也可通过点击左侧菜单栏中的供应商注册进入本页面。在本页面中，可以列表进行编辑，查看，删除操作，用户首次登录需要完善其中的数据，基本资料、银行信息和资质信息三个模块。其中，两个备用联系人信息可随时更改，其他信息需联系管理员进行修改（提交审批后只能由管理员进行修改）。资质信息需按对应文档名称上传，支持所有常见文件的上传，若是图片，则会以缩略图显示；若是文件，则以文件图片代替，点击资质框下方的下载附件：供应商商务手册、供应商品质手册和信垦保密协议需签字盖章上传，供应商调查&预评估表需填写完整真实。首次登录完善信息后，点击保存修改后即可保存该信息，点击提交审批后，即可进行投标报价操作。</p>
        </el-col>
        <center><img src="./img/supplierlist.jpg" width="1000"></center>
      </el-row>

  </div>
</template>

<script>
export default {
  name: 'helpDocument',
  components: {  },
  data() {
    return {
     
    }
  },
  computed: {
    
  },
  created() {
   
  }
}
</script>
<style type="text/css">
    .grid-content{height: 60px;background:#EBEEF5;}
    .grid-content h3{line-height: 60px;margin-left: 20px;}
    .doucumentTitle{font-family: "Microsoft YaHei";font-size:14px;color:#666;line-height: 24px}
</style>