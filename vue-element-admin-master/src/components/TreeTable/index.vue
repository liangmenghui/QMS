<template>
  <el-table :data="formatData" :row-style="showRow" v-bind="$attrs">
    <el-table-column v-if="columns.length===0" width="150">
      <template slot-scope="scope">
        <span v-for="space in scope.row._level" :key="space" class="ms-tree-space"/>
        <span v-if="iconShow(0,scope.row)" class="tree-ctrl" @click="toggleExpanded(scope.$index)">
          <i v-if="!scope.row._expanded" class="el-icon-circle-plus"/>
          <i v-else class="el-icon-remove"/>
        </span>
        {{ scope.$index }}
      </template>
    </el-table-column>
    <el-table-column v-for="(column, index) in columns" v-else :key="column.value" :label="column.text" :width="column.width">
      <template slot-scope="scope">
        <!-- Todo -->        
        <span v-for="space in scope.row._level" v-if="index === 0" :key="space" class="ms-tree-space"/>       
        <span v-if="index==0&&scope.row.sLevel!=3" class="tree-ctrl" @click="toggleExpanded(scope.$index)">
          <i v-if="!scope.row._expanded" class="el-icon-circle-plus"/>                   
          <i v-else class="el-icon-remove"/>
        </span>
        <el-input  v-if="index==2"   type="number"      
          v-model="scope.row[column.value]"
          style="background:none;border:none;width:100%;"
          @change="handleCheckChange(scope.row)"             
          >              
        </el-input>
       <!--  <el-radio-group v-else-if="index==3&&scope.row._level==3" v-model="scope.row[column.value]">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>                          
        </el-radio-group> -->            
        <span v-else>{{scope.row[column.value]}}</span>
      <!--  {{ scope.row[column.value] }} -->
      </template>
    </el-table-column>

    <slot/>
  </el-table>
</template>

<script>
import { getSmtPoint,updateStmPoints } from '@/api/smtpoint'
import treeToArray from './eval'
export default {
  name: 'TreeTable',
  props: {
    /* eslint-disable */
    data: {
      type: [Array, Object],
      required: true
    },
    columns: {
      type: Array,
      default: () => []
    },   
    evalFunc: Function,
    evalArgs: Array,
    expandAll: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    // 格式化数据源
    formatData: function() {
      let tmp
      if (!Array.isArray(this.data)) {       
        tmp = [this.data]
      } else {
        tmp = this.data
      }
      const func = this.evalFunc || treeToArray
      const args = this.evalArgs ? Array.concat([tmp, this.expandAll], this.evalArgs) : [tmp, this.expandAll]
      return func.apply(null, args)
    }
  },
  methods: {
    showRow: function(row) {
      const show = (row.row.parent ? (row.row.parent._expanded && row.row.parent._show) : true)
      row.row._show = show
      return show ? 'animation:treeTableShow 1s;-webkit-animation:treeTableShow 1s;' : 'display:none;'
    },
    // 切换下级是否展开
    toggleExpanded: function(trIndex) {  
     
      const record = this.formatData[trIndex]
      record._expanded = !record._expanded
      //console.log(record)
      if(record._expanded==true){             
         this.$emit("clickSearch",record);       
      }      
        
    },
    // 图标显示
    iconShow(index, record) {
      return (index === 0 && record.children && record.children.length > 0)
    },
    //修改STM点数
    handleCheckChange(row){    
        const param={
              id:row.id,
              sCode:row.sCode,
              sName:row.sName,
              sPoints:row.sPoints,
              sCategoryId:row.sCategoryId,
              isSpecial:row.isSpecial,
              parentId:row.parentId,
              sLevel:row.sLevel
        } 
        updateStmPoints(param).then(response => {                       
            if(response.result) {                              
              this.$message({
                type: 'success',
                message: response.msg
              });                         
         }else {
            this.$message.error(res.msg);
        }
    })
     
    }
  }
}
</script>
<style rel="stylesheet/css">
  @keyframes treeTableShow {
    from {opacity: 0;}
    to {opacity: 1;}
  }
  @-webkit-keyframes treeTableShow {
    from {opacity: 0;}
    to {opacity: 1;}
  }
</style>

<style lang="scss" rel="stylesheet/scss" scoped>
  $color-blue: #2196F3;
  $space-width: 18px;
  .ms-tree-space {
    position: relative;
    top: 1px;
    display: inline-block;
    font-style: normal;
    font-weight: 400;
    line-height: 1;
    width: $space-width;
    height: 14px;
    &::before {
      content: ""
    }
  }
  .processContainer{
    width: 100%;
    height: 100%;
  }
  table td {
    line-height: 26px;
  }

  .tree-ctrl{
    position: relative;
    cursor: pointer;
    color: $color-blue;
    margin-left: -$space-width;
  }
</style>
