  <!--
      作者：offline
      时间：2018-04-02
      描述： 新增供应商
    -->
<template>
  <div class="layout">

    <el-collapse
      v-model="expandedCollapses[0]"
      @change="handleChange"
    >
      <el-collapse-item name="1">
        <template slot="title">
          <svg-icon
            icon-class="user"
            style="font-size: 18px;padding-right:5px"
          />基本资料
        </template>
        <div class="content">
            <el-form :model="form" :rules="ruleForm" ref="form" label-width="120px" class="formStly" size="small">
                    <el-row type="flex" class="row-bg" >
                        <el-col :span="11" style="display: none">
                            <el-form-item label="id" prop="id">
                                 <el-input v-model="form.id"></el-input>
                              </el-form-item>
                        </el-col>
                        <el-col :span="11">
                            <el-form-item label="用户名" prop="loginName">
                                 <el-input :disabled="true" v-model="form.loginName" placeholder="用户名为非必填项，如果不填写，会根据公司中文名称的首字母自动生成。"></el-input>
                              </el-form-item>
                        </el-col>
                         <el-col :span="11">
                            <el-form-item label="公司中文名" prop="suppChineseName">
                                 <el-input v-model="form.suppChineseName"></el-input>
                            </el-form-item>
                        </el-col>                                        
                    </el-row>
                  <!--   <el-row type="flex" class="row-bg" justify="space-between">
                        <el-col :span="11">
                            <el-form-item label="确认密码" prop="passwordOK">
                                 <el-input v-model="form.passwordOK"></el-input>
                              </el-form-item>
                        </el-col>
                        <el-col :span="11">
                            <el-form-item label="初审人" prop="suppAddress">
                                 <el-input v-model="form.primaryApprover"></el-input>
                            </el-form-item>
                        </el-col>                                           
                    </el-row> -->
                  <!--   <el-row type="flex" class="row-bg" justify="space-between">
                    <el-col :span="11">
                            <el-form-item label="供应商编号" prop="suppCode" >
                                 <el-input v-model="form.suppCode"  readonly></el-input>
                            </el-form-item>
                        </el-col>
                         <el-col :span="11">
                            <el-form-item label="密码" prop="loginPwd">
                                 <el-input v-model="form.loginPwd"></el-input>
                              </el-form-item>
                        </el-col>
                </el-row> -->
                     <el-row type="flex" class="row-bg" >
                        <el-col :span="11">
                            <el-form-item label="供应商简称" prop="suppAliaName">
                                 <el-input v-model="form.suppAliaName"></el-input>
                              </el-form-item>
                        </el-col>
                         <el-col :span="11">
                            <el-form-item label="公司英文名" prop="suppEnglishName">
                                 <el-input v-model="form.suppEnglishName"></el-input>
                              </el-form-item>
                        </el-col>                                           
                    </el-row>
                 
                   <!--  <el-row type="flex" class="row-bg" justify="space-between">                 
                        
                         <el-col :span="11">
                           <el-form-item label="供应商类别" prop="suppType" >
                                  <el-select v-model="form.suppType" placeholder="供应商类别"style="width:100%">
                                      <el-option label="区域一" value="shanghai"></el-option>
                                      <el-option label="区域二" value="beijing"></el-option>
                                    </el-select>
                            </el-form-item>
                        </el-col>  
                        <el-col :span="11">
                           <el-form-item label="注册大类" prop="metalDescribe">
                                  <el-select v-model="form.metalDescribe" placeholder="注册大类" style="width:100%">
                                      <el-option label="区域一" value="电容"></el-option>
                                      <el-option label="区域二" value="电阻"></el-option>
                                    </el-select>
                            </el-form-item>
                            <el-form-item label="供应商级别" prop="suppGrade" >
                                 <el-select v-model="form.suppGrade" placeholder="供应商级别" style="width:100%">
                                    <el-option  v-for="item in suppGradeOptions" :key="item.value" :label="item.label" :value="item.value"></el-option>                          
                                 </el-select>
                            </el-form-item>
                        </el-col>                                         
                    </el-row> -->
                     <el-row type="flex" class="row-bg">                  
                          <el-col :span="11">
                          <el-form-item label="传真号码" prop="suppFax">
                                  <el-input v-model="form.suppFax"></el-input>
                            </el-form-item>
                        </el-col>                         
                        <el-col :span="11">
                           <el-form-item label="详细地址" prop="suppAddress">
                                <el-input  v-model="form.suppAddress"></el-input>
                            </el-form-item>
                        </el-col>
                                                                
                    </el-row>                                  
                     <el-row type="flex" class="row-bg">                       
                        <el-col :span="6">
                           <el-form-item label="注册资金(万元)" prop="registeredCapital" >
                                <!--  <el-input v-model="form.registeredCapital"></el-input> -->
                                <el-input  v-model="form.registeredCapital" type="number"  placeholder="请输入数值"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="5">
                           <el-form-item label="货币种类" prop="currencyType" style="width: 100%">
                              <el-select
                                v-model="form.currencyType"
                                :multiple="false"
                                filterable
                                allow-create
                                default-first-option
                                placeholder="请选择货币">
                                <el-option
                                  v-for="item in currency"
                                  :key="item.value"
                                  :label="item.label"
                                  :value="item.value">
                                </el-option>
                              </el-select>
                           
                            </el-form-item>
                        </el-col>
                        <el-col :span="11">
                           <el-form-item label="经营范围" prop="businessScope">
                                <el-input v-model="form.businessScope"></el-input>
                            </el-form-item>
                        </el-col>                                      
                    </el-row>
                        
                      <el-row type="flex" class="row-bg" > 
                         <el-col :span="11">
                           <el-form-item label="备注" prop="remarks">
                                  <el-input v-model="form.remarks"></el-input>
                            </el-form-item>
                        </el-col>  
                        <el-col :span="11">
                           <el-form-item label="供应商K3编号" prop="suppK3Code" v-show="isShowK3Code">
                                  <el-input v-model="form.suppK3Code"></el-input>
                            </el-form-item>
                        </el-col>                                                                                      
                    </el-row>
                   
                     <el-row type="flex" class="row-bg" > 
                        <el-col :span="11">
                           <el-form-item label="审核状态:" prop="suppGrade">
                                <!--   <el-input v-model="form.suppGrade" v-if=""></el-input> -->
                                  <el-tag color="white" v-bind:class="{orange:form.suppGrade==1 ,green:form.suppGrade==2, gray:form.suppGrade==3}"
                                   style="width: 100px;font-size: 14px; border:none; text-align: left;">
                                     {{$t('supplier.Status['+form.suppGrade+']')}}         
                                   </el-tag>
                            </el-form-item>
                        </el-col>                                                                                    
                    </el-row>                           

                </el-form>
        </div>
      </el-collapse-item>
      <el-collapse-item name="2">
        <template slot="title">
          <svg-icon
            icon-class="user"
            style="font-size: 18px;padding-right:5px"
          />联系人信息
        </template>
        <div class="content">
          <el-form
            :model="form"
            :rules="ruleForm"
            ref="form"
            label-width="100px"
            class="formStly"
            size="small"
          >
            <el-row
              type="flex"
              class="row-bg"
              justify="space-between"
            >
              <el-col :span="8">
                <el-form-item
                  label="姓名"
                  prop="suppContactName"
                >
                  <el-input v-model="form.suppContactName"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item
                  label="联系电话"
                  prop="suppMobile"
                >
                  <el-input v-model="form.suppMobile"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item
                  label="电子邮箱"
                  prop="suppEmail"
                >
                  <el-input v-model="form.suppEmail"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row
              type="flex"
              class="row-bg"
            >
              <el-col :span="8">
                <el-form-item
                  label="职位"
                  prop="suppPosition"
                >
                  <el-input v-model="form.suppPosition"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item
                  label="性别"
                  prop="suppSex"
                >
                  <el-input v-model="form.suppSex"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-collapse-item>
      <el-collapse-item name="3">
        <template slot="title">
          <svg-icon
            icon-class="documentation"
            style="font-size: 18px;padding-right:5px"
          />银行信息
        </template>
        <div class="content">
          <el-form
            :model="form"
            ref="form"
            label-width="100px"
            class="formStly"
            size="small"
          >
            <el-row
              type="flex"
              class="row-bg"
              justify="space-between"
            >
              <el-col :span="11">
                <el-form-item
                  label="账户名"
                  prop="accountName"
                >
                  <el-input v-model="form.accountName"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="11">
                <el-form-item
                  label="银行账户"
                  prop="bankAccount"
                >
                  <el-input v-model="form.bankAccount"></el-input>
                </el-form-item>
              </el-col>

            </el-row>
            <el-row
              type="flex"
              class="row-bg"
              justify="space-between"
            >
              <el-col :span="11">
                <el-form-item
                  label="发票抬头"
                  prop="accountName"
                >
                  <el-input v-model="form.accountName"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="11">
                <el-form-item
                  label="纳税人识别号"
                  prop="bankAccount"
                >
                  <el-input v-model="form.bankAccount"></el-input>
                </el-form-item>
              </el-col>

            </el-row>
            <el-row
              type="flex"
              class="row-bg"
              justify="space-between"
            >
              <el-col :span="11">
                <el-form-item
                  label="城市地址"
                  prop="city"
                >
                  <el-input v-model="form.city"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="11">
                <el-form-item
                  label="开户银行"
                  prop="depositBank"
                >
                  <el-input v-model="form.depositBank"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row
              type="flex"
              class="row-bg"
              justify="space-between"
            >
              <el-col :span="11">
                <el-form-item
                  label="付款方式"
                  prop="payMethod"
                >
                  <el-input v-model="form.payMethod"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="11">
                <el-form-item
                  label="付款条件"
                  prop="depositBank"
                >
                  <el-input v-model="form.payCondition"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </el-collapse-item>
      <el-collapse-item name="4">
        <template slot="title">
          <svg-icon
            icon-class="excel"
            style="font-size: 18px;padding-right:5px"
          />资质证件 <span style="font-size: 12px;margin-left: 15px;color:#555">*请上传公司资质文件、营业执照、ROHS报告（有效期）、ISO体系文件（有效期管理）、等文件的电子档</span>
        </template>
        <div class="content">
          <!--  <el-col :span="20">
                  <div class="">
                        <upload></upload>                       
                    </div>
              </el-col> -->
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="">
                <upload
                  :property="'suppFile1'"
                  v-model="form.suppFile1"
                  @on-upload="fileUpload"
                ></upload>
                <ul
                  class="el-upload-list el-upload-list--picture"
                  v-if="form.suppFile1"
                >
                  <li
                    tabindex="0"
                    class="el-upload-list__item is-success"
                  >
                    <i class="el-icon-picture pic"></i>
                    <a
                      style="display: inline-block;"
                      class="el-upload-list__item-name"
                      @click="downloadFile(form.suppFile1)"
                    >下载文件 </a>
                    <a
                      style="display: inline-block;"
                      class="el-upload-list__item-name"
                      @click="handleRemove(form.suppFile1,'suppFile1')"
                    >删除文件 </a>
                    <label class="el-upload-list__item-status-label"><i class="el-icon-upload-success el-icon-check"></i></label><i class="el-icon-close"></i><i class="el-icon-close-tip">按 delete 键可删除</i>
                  </li>
                </ul>
                <p>三证合一-开票资质</p>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <upload
                  :property="'suppFile2'"
                  v-model="form.suppFile2"
                  @on-upload="fileUpload"
                ></upload>
                <ul
                  class="el-upload-list el-upload-list--picture"
                  v-if="form.suppFile2"
                >
                  <li
                    tabindex="0"
                    class="el-upload-list__item is-success"
                  >
                    <i class="el-icon-picture pic"></i>
                    <a
                      style="display: inline-block;"
                      class="el-upload-list__item-name"
                      @click="downloadFile(form.suppFile2)"
                    >下载文件 </a>
                    <a
                      style="display: inline-block;"
                      class="el-upload-list__item-name"
                      @click="handleRemove(form.suppFile2,'suppFile2')"
                    >删除文件 </a>
                    <label class="el-upload-list__item-status-label"><i class="el-icon-upload-success el-icon-check"></i></label><i class="el-icon-close"></i><i class="el-icon-close-tip">按 delete 键可删除</i>
                  </li>
                </ul>
                <p>供应商营业执照</p>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <upload
                  :property="'suppFile3'"
                  v-model="form.suppFile3"
                  @on-upload="fileUpload"
                ></upload>
                <ul
                  class="el-upload-list el-upload-list--picture"
                  v-if="form.suppFile3"
                >
                  <li
                    tabindex="0"
                    class="el-upload-list__item is-success"
                  >
                    <i class="el-icon-picture pic"></i>
                    <a
                      style="display: inline-block;"
                      class="el-upload-list__item-name"
                      @click="downloadFile(form.suppFile3)"
                    >下载文件 </a>
                    <a
                      style="display: inline-block;"
                      class="el-upload-list__item-name"
                      @click="handleRemove(form.suppFile3,'suppFile3')"
                    >删除文件 </a>
                    <label class="el-upload-list__item-status-label"><i class="el-icon-upload-success el-icon-check"></i></label><i class="el-icon-close"></i><i class="el-icon-close-tip">按 delete 键可删除</i>
                  </li>
                </ul>
                <p>ROHS报告(有限期)</p>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="">
                <upload
                  :property="'suppFile4'"
                  v-model="form.suppFile4"
                  @on-upload="fileUpload"
                ></upload>
                <ul
                  class="el-upload-list el-upload-list--picture"
                  v-if="form.suppFile4"
                >
                  <li
                    tabindex="0"
                    class="el-upload-list__item is-success"
                  >
                    <i class="el-icon-picture pic"></i>
                    <a
                      style="display: inline-block;"
                      class="el-upload-list__item-name"
                      @click="downloadFile(form.suppFile4)"
                    >下载文件 </a>
                    <a
                      style="display: inline-block;"
                      class="el-upload-list__item-name"
                      @click="handleRemove(form.suppFile4,'suppFile4')"
                    >删除文件 </a>
                    <label class="el-upload-list__item-status-label"><i class="el-icon-upload-success el-icon-check"></i></label><i class="el-icon-close"></i><i class="el-icon-close-tip">按 delete 键可删除</i>
                  </li>
                </ul>
                <p>ISO体系文件(有限期管理)</p>
              </div>
            </el-col>
          </el-row>

        </div>
      </el-collapse-item>
    </el-collapse>
    <center
      style="margin-top:30px;"
      v-show="isShow"
    >
      <el-button
        type="primary"
        @click="addsupplierBtn"
        style="width:200px"
      >保存</el-button>
    </center>
  </div>

</template>
<script>
import { getFile, deleteFile } from "@/api/supplier";
import { addSupplierInfo, editSupplier } from "@/api/supplier";
import upload from "./components/supplierUpload.vue";
export default {
  components: {
    upload
  },
  name: "addSupplier",
  data() {
    var checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("手机号不能为空"));
      } else {
        const reg = /^1[3|4|5|7|8][0-9]\d{8}$/;
        console.log(reg.test(value));
        if (reg.test(value)) {
          callback();
        } else {
          return callback(new Error("请输入正确的手机号"));
        }
      }
    };
    var checkEmail = (rule, value, callback) => {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      if (!value) {
        return callback(new Error("邮箱不能为空"));
      }
      setTimeout(() => {
        if (mailReg.test(value)) {
          callback();
        } else {
          callback(new Error("请输入正确的邮箱格式"));
        }
      }, 100);
    };
    return {
      expandedCollapses: ["1", "0", "0"],

      form: {
        suppFile1: "",
        suppFile2: "",
        suppFile3: "",
        suppFile4: ""
      },
        currency: [{
          value: '美元USD',
          label: '美元USD'
        }, {
          value: '人民币CNY',
          label: '人民币CNY'
        }, {
          value: '日元JPY',
          label: '日元JPY'
        }],
        default: [],
      
      ruleForm: {
       /* loginName: [
          { required: true, message: "请填写用户名", trigger: "blur" }
        ],*/
        loginPwd: [
          { required: true, message: "请填写登录密码", trigger: "blur" }
        ],
        suppChineseName: [
          { required: true, message: "请填写供应商名称", trigger: "blur" }
        ],
        suppMobile: [
          { required: true, trigger: "blur", validator: checkPhone }
        ],
        suppEmail: [{ required: true, trigger: "blur", validator: checkEmail }]
      },
      isShow: true,
      isDisabled:true,
      isShowK3Code:false
    };
  },
  created() {
    if (this.$route.query.type) {
      if (this.$route.query.id != undefined)
        this.form = this.$store.getters.getSupplierData;
        //this.form.suppGrade

      if (this.$route.query.type == "view") {
        this.isShow = false;
      }else{
          this.isDisabled = false;
          this.isShowK3Code = true
      }
    } else {
      //注册供应商
      this.form = this.$store.getters.getSupplierData;
    }
  },
  methods: {
    addsupplierBtn() {
      this.$forceUpdate();
      if (typeof this.form.id != undefined && typeof this.form.id == "number") {
        editSupplier(this.form).then(response => {
          if (response.result) {
            this.$message({
              message: response.msg,
              type: "success"
            });
            this.$router.go(-1);
          } else {
            this.$message.error(res.msg);
          }
        });
      } else {
        addSupplierInfo(this.form).then(response => {
          if (response.result) {
           this.$confirm('用户名：'+response.data.loginName+'<br>'+'密码：'+ response.data.loginPwd+'<br>'+'供应商名称：'+response.data.suppChineseName, '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              dangerouslyUseHTMLString: true,
              type: 'warning'
            }).then(() => {
              this.$message({
                type: 'success',
                message: response.msg
              });
            }).catch(() => {
              this.$error({
                type: 'info',
                message: response.msg
              });          
            }); 
           
          } else {
            this.$message.error(res.msg);
          }
        });
      }
    },
    fileUpload(file, property) {
      this.form[property] = file.id;
      // console.log(this.form)
    },
    downloadFile(fsFileId) {
      window.location.href =
        process.env.BASE_API + "/file/get?fsFileId=" + fsFileId;
    },
    handleRemove(fsFileId, property) {
      deleteFile(fsFileId).then(response => {
        if (response.result) {
          this.$message({
            message: response.msg,
            type: "success"
          });
        } else {
          this.$message.error(res.msg);
        }
        //this.fileList.remove();
        this.form[property] = "";
      });
    },
    handleChange() {}
  }
};
</script>
<style>
.block {
  text-align: right;
  margin-top: 10px;
}

.layout {
  padding: 20px;
}
.content {
  width: 100%;
  padding: 20px;
}
.pic {
  vertical-align: middle;
  display: inline-block;
  width: 70px;
  height: 70px;
  float: left;
  position: relative;
  z-index: 1;
  margin-left: -80px;
  font-size: 70px;
  color: #13ce66;
}
</style>