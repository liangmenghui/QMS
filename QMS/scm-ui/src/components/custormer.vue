<template id="myComponent">
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      prop="date"
      label="编号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="产品"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="供应商">
    </el-table-column>
    <el-table-column
      prop="address"
      label="时间">
    </el-table-column>
    <el-table-column
      prop="address"
      label="类型">
    </el-table-column>
    <el-table-column
      prop="address"
      label="状态">
    </el-table-column>
  </el-table>
</template>

<script>
  import {mapState} from 'vuex'
    export default {
    data() {
      return {
        tableData: [],
        filters: {},
        showRooterView: false
      }
    },
    created(){
        this.getlist();
    },
    beforeUpdate:function(){
        this.showRooterView = this.$route.matched.length>2;
    },
    methods:{
      getlist(){
        this.api.supplierinfo.getlist().then((res) => {
          this.tableData = res.data.rows;
        });
      },
      pushToChildWithData(url,row){
        this.$store.commit("updateSupplierDataStates",row.row);
        this.$router.push({path: url});
      }
    }
}
</script>