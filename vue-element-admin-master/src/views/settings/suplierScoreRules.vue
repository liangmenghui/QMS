<template>
  <div class="">
    <el-table
      border
      v-loading="isShow" element-loading-text="拼命加载中"
      :data="listData"
      :span-method="objectSpanMethod"
      class="tableArea"
      style="width: 100%"
    >
      <el-table-column
        prop="ruleName"
        label="评分项"
        align="center"
        width="200"
      />
      <el-table-column
        prop="rulePercent"
        align="center"
        label="占比(共100%)"
        width="100"
      >
        <template slot-scope="scope">
          <span style="color:#CC0033">{{ scope.row.rulePercent }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="ruleTypeScore"
        align="center"
        label="类型总分(共100分)"
        width="100"
      >
      </el-table-column>
      <el-table-column
        label="评分标准"
        align="center"
      >
        <el-table-column
          prop="ruleStandard"
          align="center"
          label="评分细则"
        >
        </el-table-column>
        <el-table-column
          prop="ruleScore"
          align="center"
          label="得分值"
        >
          <template slot-scope="scope">
            <el-input             
              v-model="scope.row.ruleScore"
              style="background:none;border:none;width:100%;"
              @change="handler(scope.row)"
              onkeyup='this.value=this.value.replace(/\D/gi,"")'
            ></el-input>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column
        prop="remark"
        align="center"
        label="备注"
      >
      </el-table-column>
         <el-table-column label="操作" width="80"align="center">
            <template slot-scope="scope">            
                <a class="operatIcon colorgreen" @click="showEditDialog(scope.row)"><i class="el-icon-edit"></i></a>
            </template>
        </el-table-column> 
    </el-table>
    <!--  编辑 -->
        <el-dialog :title="dialogTitle" :visible.sync="dialog.dialogVisible" width="30%">
          <el-form :model="scoreForm" ref="formItem" :rules="dialog.ruleForm" style="width:90%">
             <el-form-item label="评分项" :label-width="formLabelWidth" prop="ruleName">
              <el-input  v-model="scoreForm.ruleName"  :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="占比" :label-width="formLabelWidth" prop="rulePercent">
              <!-- <el-input  v-model="scoreForm.rulePercent"></el-input> -->
              <el-input  type="number" placeholder="请输入内容" v-model="scoreForm.rulePercent">
                <template slot="append">%</template>
              </el-input>
            </el-form-item>
            <el-form-item label="类型总分" :label-width="formLabelWidth"  prop="ruleTypeScore">
              <el-input v-model="scoreForm.ruleTypeScore" ></el-input>
            </el-form-item> 
             <el-form-item label="评分细则" :label-width="formLabelWidth"  prop="ruleStandard">
              <el-input v-model="scoreForm.ruleStandard" ></el-input>
            </el-form-item>   
             <el-form-item label="得分值" :label-width="formLabelWidth"  prop="ruleScore">
              <el-input type="number" v-model="scoreForm.ruleScore" ></el-input>
            </el-form-item>          
            <el-form-item label="备注" :label-width="formLabelWidth" prop="remark">
              <el-input v-model="scoreForm.remark"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialog.dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="OK">确 定</el-button>
          </div>
        </el-dialog>
  </div>
</template>
<script>
import { getlist,edit,updateScore } from "@/api/supplierScoreRules";
export default {
  name: "supplierScoreRules",
  data() {
    return {
      rowList: [],
      spanArr: [],
      position: 0,
      listData: [],
      dialogTitle:"编辑评分明细",
       dialog: {
        loading: false,
        dialogVisible: false              
      },
      formLabelWidth: '100px',
      scoreForm:{},
      isShow:true
    };
  },
  created() {
    this.queryData();
  },
  methods: {
    //获取数据
    queryData() {
      getlist().then(response => {
        if (response.result) {
         // console.log(response.data);
          this.listData = response.data;
          this.isShow = false
          this.rowspan();
        } else {
          this.$message.error(response.msg);
        }
      });
    },
    //查询相同列
    rowspan() {
      this.listData.forEach((item, index) => {
        if (index === 0) {
          this.spanArr.push(1);
          this.position = 0;
        } else {
          if (
            this.listData[index].ruleName === this.listData[index - 1].ruleName
          ) {
            this.spanArr[this.position] += 1;
            this.spanArr.push(0);
          } else {
            this.spanArr.push(1);
            this.position = index;
          }
        }
      });
    },
    //表格合并行
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      if (columnIndex === 0) {
        const _row = this.spanArr[rowIndex];
        const _col = _row > 0 ? 1 : 0;
        return {
          rowspan: _row,
          colspan: _col
        };
      }
      if (columnIndex === 1) {
        const _row = this.spanArr[rowIndex];
        const _col = _row > 0 ? 1 : 0;
        return {
          rowspan: _row,
          colspan: _col
        };
      }
      if (columnIndex === 2) {
        const _row = this.spanArr[rowIndex];
        const _col = _row > 0 ? 1 : 0;
        return {
          rowspan: _row,
          colspan: _col
        };
      }
    },
    //刷新页面
    getData(){
      getlist().then(response => {
        if (response.result) {
         // console.log(response.data);
          this.listData = response.data;
         
        } else {
          this.$message.error(response.msg);
        }
      });      
    },
    //编辑
    showEditDialog(row){
        this.scoreForm=Object.assign({}, row);        
        this.dialog.dialogVisible=true;
    },
    //修改得分值
    OK(){      
        //if(this.scoreForm.id!=undefined&&this.scoreForm.id=='number'){
             edit(this.scoreForm).then(response => {             
              if (response.result) {
                this.dialog.dialogVisible = false;               
                this.getData()
              } else {
                this.$Message.error(res.msg);
              }
            });
        //}
    },
    handler(row){
       updateScore(row.id,row.ruleScore).then(response => {                       
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
};
</script>
<style lang="scss" scoped>

.el-select {
  margin-right: 15px;
}
.el-input {
  margin-right: 15px;
  width: 200px;
}
.tableArea {
  margin-top: 20px;
  box-shadow: 0 0 8px 0 #aaa;
}
i[class^="el-icon"] {
  margin-right: 5px;
  font-size: 16px;
  cursor: pointer;
}
.modify_table {
  td {
    padding: 10px;
  }
}
.item_title {
  text-align: right;
}
</style>
 
