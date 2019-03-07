<template>
<div class="home-main">
         <el-row>
            <!-- 待办事项开始 -->
                    <el-col :md="24" :lg="24" :style="{marginBottom: '10px'}">
                        <el-card>
                          <div slot="header" class="clearfix">
    <span><svg-icon icon-class="todo" />                     
                               待办事项</span>
    <el-button icon="el-icon-search"  style="float: right; padding: 3px 0" type="text" size="medium"  @click="pushTodolist" > 查看更多</el-button></div>                       
                         
                        </el-card>
                    </el-col>
        </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
/*import GithubCorner from '@/components/GithubCorner'*/

export default {
  name: 'DashboardEditor',
  data() {
    return {
      emptyGif: 'https://wpimg.wallstcn.com/0e03b7da-db9e-4819-ba10-9016ddfdaed3',
      toDoList:[],
      todolistdata:{},
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'roles'
    ])
  },
   methods: {
     pushTodolist(){

     }
   }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
