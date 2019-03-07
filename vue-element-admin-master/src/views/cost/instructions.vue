<template>
  <div class="dashboard-container" style="padding: 5px 20px">
      <el-row>
        <el-col :span="24">
            <div class="grid-content"><h3>客户Bom成本预估操作说明</h3></div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
           <h5>一、客户BOM成本预估</h5>
           <p  class="doucumentTitle"> 根据客户提供的Excel文件,进行点击上传Bom文件,输入物料表表头开始列,设置好关键参数，比如规格列和数量列之后点击开始匹配K3数据。查询出数据之后，点击行，就会展示出相应的匹配数据</p>
        </el-col>
        <center><img src="./img/1.png"></center>
        <center><img src="./img/2.jpg"></center>
        <center><img src="./img/3.jpg"></center>
        <center><img src="./img/3-1.jpg"></center>
        <center><img src="./img/3-2.jpg"></center>
        <center><img src="./img/4.png"></center>
        <center><img src="./img/5.png"></center>
        <center><img src="./img/6.png"></center>
        <center><img src="./img/7.png"></center>
        
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