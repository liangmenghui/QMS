<template>
  <a class="operatIcon colorRed"  @click="open"><i class="el-icon-delete"></i> </a>
</template>
<script>
export default {
  props: {
    contents: {
      type: String,
      default: '这是一段内容'
    },
    title: {
      type: String,
      default: '标题名称'
    },
    confirmTitle: {
      type: String,
      default: '确认'
    },
    showClose :{type:Boolean,default:true},
  },

  methods: {
   open() {
        var _this = this
        this.$confirm(this.contents, this.title, {
          distinguishCancelAndClose: true,
          confirmButtonText: this.confirmTitle,
          cancelButtonText: '取消',       
          type: 'warning'         
        }).then(() => {
          _this.$emit('callConfirm');
        }).catch(() => {
          this.$message({
          type: 'info',
          message: '已取消删除'
        }); 
      });
     } 
    }
}
</script>
<style type="text/css">
    .el-icon-close:before {
    color: #333;
}
</style>
