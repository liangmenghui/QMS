<template>
  <div class="app-container">
    <upload-excel-component v-if='isUpload'   :on-success="handleSuccess" :before-upload="beforeUpload"/>
    <el-table :data="tableData" border row-key="序号" highlight-current-row style="width: 100%;margin-top:20px;">
      /* <el-table-column v-for="item of tableHeader" :prop="item" :label="item" :key="item"/> */
       <el-table-column v-for="(item, index) in tableHeader"
        :key="`col_${index}`"
        :prop="dropCol[index].prop"
        :label="item.label"> 
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
import UploadExcelComponent from '@/components/UploadExcel/index.vue'

export default {
  name: 'UploadExcel',
  components: { UploadExcelComponent },
  data() {
    return {
      tableData: [],
      tableHeader: [],
      dropCol : [],
      isUpload:true
    }
  },
  mounted() {
    this.columnDrop()
  },
  methods: {
    beforeUpload(file) {


      const isLt1M = file.size / 1024 / 1024 < 1

      if (isLt1M) {

        return true
      }

      this.$message({
        message: 'Please do not upload files larger than 1m in size.',
        type: 'warning'
      })
      return false
    },
    handleSuccess({ results, header }) {
      this.isUpload = true
      this.tableData = results
      this.tableHeader = header
      this.dropCol = header;
    },
    //列拖拽
    columnDrop() {
      const wrapperTr = document.querySelector('.el-table__header-wrapper tr')
      this.sortable = Sortable.create(wrapperTr, {
        animation: 180,
        delay: 0,
        onEnd: evt => {
          const oldItem = this.dropCol[evt.oldIndex]
          this.dropCol.splice(evt.oldIndex, 1)
          this.dropCol.splice(evt.newIndex, 0, oldItem)
        }
      })
    }

  }
}
</script>
