<template>
  <div class="">
    <el-card
      class="box-card"
      shadow="hover"
    >
      <div
        slot="header"
        class="clearfix"
      >
        <span class="sroreIcon"><i
            class="iconfont"
            style="font-size: 14px;margin-right:5px;"
          >&#xe693;</i>参数设置</span>
        <el-button
          style="float: right;"
          type="primary"
          @click="save()"
        >保存</el-button>
      </div>
      <el-card shadow="naver">
        <el-row>
          <el-form
            :model="form"
            :rules="ruleForm"
            ref="scoreForm"
            label-width="250px"
          
          >
            <el-col :span="12">
        <!--       <i
                class="iconfont"
                style="color:#42b983"
              > &#xe60b;</i> -->
              <el-form-item
                label="客户BOM匹配数据时:匹配率大于"
                prop="batchValue"
                style="display: inline-block;"
                label-position="left"
              >
                <el-input
                  type="number"
                  placeholder="请输入内容"
                  v-model.number="form.checkRate"
                >
                </el-input>
              </el-form-item>           
            </el-col>
            <el-col :span="12">
 <!--              <i class="iconfont" style="color:#42b983" > &#xe617;</i> -->
              <el-form-item
                label="自动勾选物料"
                prop="batchValue"
                      
                 >
                  <el-input
                  type="number"
                  placeholder="请输入内容"
                  v-model.number="form.checkRate"
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-form>
        </el-row>
      </el-card>
    </el-card>

  </div>
</template>
<script>
import { getList,updateSetting } from "@/api/setting";
export default {
  name: "setting",
  data() {
    return {
      form: {
        checkRate:''
      },
      ruleForm: {}
    };
  },
  created() {
    this.queryData();
  },
  methods: {
    //获取数据
    queryData() {
      getList().then(response => {
        if (response.result) {
          console.log(response.data);
          response.data.forEach(element => {
            if(element.code == 'customer_bom_check')this.form.checkRate=element.value
          });
        } else {
          this.$message.error(response.msg);
        }
      });
    },
    save(){
      updateSetting(this.form.checkRate).then(response => {
        if (response.result) {
          this.$message.info(response.msg);
        } else {
          this.$message.error(response.msg);
        }
      });
    }
  }
};
</script>
<style  scoped>
.sroreIcon {
  color: #42b983 !important;
  font-size: 14px;
}
</style>
 
