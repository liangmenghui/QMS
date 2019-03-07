<template>
  <div >
    <!-- <Row> -->
    <el-row style="margin-bottom:5px;margin-top:5px;">
      <el-col :span="4">
        <upload-excel-component
          v-if='isUpload'
          v-on:afterUpload="afterUpload"
          :on-success="handleSuccess"
          :before-upload="beforeUpload"
          
        >
        </upload-excel-component>
      </el-col>
      <el-col :span="15">
        <el-button
          type="button"
          @click="toList"
        >查看历史记录</el-button>
      <MessageBoxDelete
          @callConfirm="doDelete(fileId)"
          title="提示"
          contents="此操作将永久删除该行, 是否继续?"
          confirmTitle="确认删除"
      ></MessageBoxDelete> 
      </el-col>
      <el-col :span="5">
        <el-button
          type="button"
          @click="toInstructions"
        >操作说明<svg-icon icon-class="help" /></el-button>
      </el-col>
    </el-row>
    <el-collapse
      v-model="activeNames"
      v-show="isShow"
    >
      <el-collapse-item
        title="设置参数"
        name="1"
        class="collapse"
      >
        <el-row>
          <el-col :span="24" style="margin-bottom:-10px;">
            <el-form
              :inline="true"
              ref="formQuery"
              :model="formQuery"
              :rules="rules"
              class="demo-form-inline"
            >
              <el-col :span="24" style="margin-bottom:-10px;margin-top:5px;">
                <el-form-item
                  label="规格"
                  prop="standardCol"
                >
                  <el-select
                    v-model="formQuery.standardCol"
                    placeholder="规格列"
                    style="width:160px"
                  >
                    <el-option
                      v-for="item in optionHearder"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="类别">
                  <el-select
                    v-model="formQuery.categoryCol"
                    placeholder="类别列"
                    style="width:160px"
                  >
                    <el-option
                      v-for="item in optionHearder"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item
                  label="数量"
                  prop="quantityCol"
                >
                  <el-select
                    v-model="formQuery.quantityCol"
                    placeholder="数量列"
                    style="width:160px"
                  >
                    <el-option
                      v-for="item in optionHearder"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="封装">
                  <el-select
                    v-model="formQuery.packageCol"
                    placeholder="封装列"
                    style="width:160px"
                  >
                    <el-option
                      v-for="item in optionHearder"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="24" style="margin-bottom:-10px;margin-top:5px;">
                <el-form-item label="制造商">
                  <el-select
                    v-model="formQuery.makerCol"
                    placeholder="制造商列"
                    style="width:160px"
                  >
                    <el-option
                      v-for="item in optionHearder"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="料号">
                  <el-select
                    v-model="formQuery.makerCol"
                    placeholder="品牌料号"
                    style="width:160px"
                  >
                    <el-option
                      v-for="item in optionHearder"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col>
                <el-form-item label="规格的分隔符">
                  <!-- <el-input v-model="suppChineseName" placeholder="分隔符有'/'，'，'，'；'三种"></el-input> -->
                  <el-checkbox-group v-model="checkList">
                    <el-checkbox label="1">/</el-checkbox>
                    <el-checkbox label="2">,</el-checkbox>
                    <el-checkbox label="3">;</el-checkbox>
                    <el-checkbox label="4">-</el-checkbox>
                    <el-checkbox label="5">、</el-checkbox>
                    <el-checkbox label="6">*</el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
                <el-form-item>
                  <el-button
                    type="primary"
                    @click="doSimilarity('formQuery')"
                  >开始匹配K3数据</el-button>
                </el-form-item>
              </el-col>
            </el-form>
          </el-col>
        </el-row>
      </el-collapse-item>
      <el-collapse-item
        title="汇总信息"
        name="2"
        class="collapse"
        v-if="isShowTable"
      >
        <!--汇总行-->
        <el-row>
          <el-col
            :span='1'
            style="margin-left:10px;"
          >
            <el-tag>进度:</el-tag>
            <!-- <el-slider v-model="fillNum"  :max="totalNum"></el-slider> :color="fillPercent=='100'?'greed':'red'" -->
          </el-col>
          <el-col
            :span="20"
            class="progressbox"
          >
            <el-progress
              :text-inside="true"
              :stroke-width="18" 
              :color="color"
              :percentage="fillPercent"
              status="exception"
            ></el-progress>
            <span class="total">总数:{{this.totalNum}}</span>
          </el-col>
        </el-row>
        <ul class="priceTotal">
          <li>
            <p class="num1 num">{{this.fAuxPriceTotal}}</p>
            <p class="label">最近采购价汇总</p>
            <div class="downBtn">
              <el-button 
                round
              > <svg-icon icon-class="excel" style="width:45px;height:45px" /></el-button>
            </div>
          </li>
          <li>
            <p class="num2 num">{{this.fAuxPrice3MonthMaxTotal}}</p>
            <p class="label">三个月之内最高价汇总</p>
            <div class="downBtn">
              <el-button
                round
              ><svg-icon icon-class="excel" style="width:45px;height:45px" /></el-button>
            </div>
          </li>
          <li>
            <p class="num3 num">{{this.fAuxPrice3MonthMinTotal}}</p>
            <p class="label">三个月之内最低价汇总</p>
            <div class="downBtn">
              <el-button
                round
              ><svg-icon icon-class="excel" style="width:45px;height:45px" /></el-button>
            </div>
          </li>
        </ul>
      </el-collapse-item>
    </el-collapse>

    <!-- </Row> -->

    <!-- vue解析的excel数据 -->
    <el-table
      v-show="!isShowTable"
      :data="tableData"
      border
      style="width: 100%;margin-top:20px;"
    >
      <el-table-column
        v-for="item of tableHeader"
        :prop="item"
        :label="item"
        :key="item"
        show-overflow-tooltip
      />
    </el-table>
    <!-- 后台解析到的excel数据 -->
    <el-table
      class="sometable"
      v-show="isShowTable"
      :data="tableData"
      border
      :row-class-name="tableRowClassName"
      style="width: 100%;margin-top:0px;"
      row-key="CusBomId"
      :expand-row-keys="expands"
      @row-click="rowClick"
    >

      <el-table-column
        type="expand"
        prop="children"
      >

        <template slot-scope="props">
          <el-form
            label-position="left"
            inline
            class="demo-table-expand"
          >
            <!-- <el-form :inline="true" ref="formMath" :model="formMath" :rules="rules"  class="demo-form-inline">
            <el-col>
              <el-form-item label="匹配前" >
               <el-input   type="number" >{{ props.row.CusBomId }}</el-input>
            </el-form-item>
              <el-form-item>
                <el-button type="primary" size="mini" @click="doSimilarity('formQuery')">开始匹配K3数据</el-button>
            </el-form-item>
            </el-col>
        </el-form> -->
            <el-table
              :data="props.row.childrenData"
              border
              v-loading="loading"
              element-loading-text="拼命加载中"
              :highlight-current-row="true"
              style="width: 1100px;margin-top:10px;margin-left:47px;font-size: 12px"
            >

              <el-table-column
                prop="fItemId"
                label="K3物料Id"
                width="100"
                show-overflow-tooltip
              />

              <el-table-column
                prop="fNumber"
                label="物料编号"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                prop="fName"
                label="物料名称"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                prop="fModel"
                label="物料规格"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                prop="ratio"
                label="匹配率"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                prop="fAuxPriceDiscount"
                label="最近采购价"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                prop="fAuxPrice3MonthMax"
                label="三个月内的最高价"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                prop="fAuxPrice3MonthMin"
                label="三个月内的最低价"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                prop="fAuxPrice3MonthMin"
                label="库存均价"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                prop="fAuxPrice3MonthMin"
                label="SMT点数"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                prop="modifiedName"
                v-if='false'
                label="修改人"
                width="100"
                show-overflow-tooltip
              />
              <el-table-column
                label="取消/选中"
                width="100"
              >
                <template slot-scope="scope1">
                  <!-- <el-switch
                    v-model="scope1.row.checkStatus"
                    active-color="#13ce66"
                    inactive-color="#ff4949"
                    active-value="1"
                    inactive-value="0"
                    :change="doCheck(scope1.row.checkStatus,scope1.row)"
                  >
                  </el-switch> -->
                  <el-button
                    type="success"
                    v-if="scope1.row.checkStatus===0"
                    icon="el-icon-check"
                    @click="doCheck('1',scope1.row)"
                  >选中</el-button>
                  <el-button
                    type="danger"
                    v-if="scope1.row.checkStatus===1"
                    icon="el-icon-close"
                    @click="doCheck('0',scope1.row)"
                  >取消</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="筛选大类"
        width="100"
      >
        <template slot-scope="scope">
          <el-select
            v-model="scope.row.mateCategory"
            clearable
            placeholder="请选择物料大类"
            @change="onSelectedCategory($event, scope.row)"
          >
            <el-option
              v-for="item in meriOption"
              :key="item.fNumber"
              :label="item.fName"
              :value="item.fNumber"
            >
              <span style="float: left">{{ item.fNumber }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.fName }}</span>
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
        v-for="item of tableHeader"
        :prop="item"
        :label="item=='checkCode'?'选中的物料号':item"
        :key="item"
        v-if="item!='mateCategory'"
        width="100"
        show-overflow-tooltip
      /> <!--     v-if="item!='mateCategory'" -->
    </el-table>
  </div>
</template>

<script>
import UploadExcelComponent from "./components/UploadExcel.vue";
import { getK3Bom, getBomMatch, getBomData, doCheckMateriel,delBom } from "@/api/cost";
import MessageBoxDelete from "./components/MessageBox.vue";
export default {
  name: "clientBomRate",
  components: { UploadExcelComponent,MessageBoxDelete },
  data() {
    return {
      tableData: [],
      tableHeader: [],
      dropCol: [],
      isUpload: true,
      isShow: false,
      isShowTable: false,
      activeNames: ["1"],
      optionHearder: [],
      formQuery: {
        standardCol: "",
        categoryCol: "",
        quantityCol: "",
        packageCol: "",
        makerCol: ""
      },
      checkList: ["1", "2", "3"],
      fileId: "",
      expands: [],
      expandTableData: [],
      rules: {
        standardCol: [
          { required: true, message: "请选择规格列", trigger: "change" }
        ],
        quantityCol: [
          { required: true, message: "请选择数量列", trigger: "change" }
        ]
      },
      loading: true,
      formMath: {
        mathNum: "",
        mathRatio: ""
      },
      currentRow: [],
      fillPercent: 0,
      fAuxPriceTotal: 0,
      fAuxPrice3MonthMaxTotal: 0,
      fAuxPrice3MonthMinTotal: 0,
      fillNum: 0, //已勾选数量
      totalNum: 0,
      meriOption: [],
      color:'red',
    };
  },
  created() {
    //非新增
    if (this.$route.query.fileId) {
      this.getBomDateByFileId(this.$route.query.fileId);
    }
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      if (row.checkStatus == 1) {
        return "success-row";
      } else {
        return "warning-row";
      }
    },
    beforeUpload(file) {
      const isLt1M = file.size / 1024 / 1024 < 1;
      if (isLt1M) {
        return true;
      }
      this.$message({
        message: "Please do not upload files larger than 1m in size.",
        type: "warning"
      });
      return false;
    },
    handleSuccess({ results, header }) {
      this.optionHearder = [];

      this.isUpload = true;
      this.tableData = results;
      this.tableHeader = header;

      this.tableHeader.forEach(element => {
        var option = { value: element, label: element };
        this.optionHearder.push(option);
      });

      this.isShow = true;
    },
    afterUpload(fileId) {
      //获取该文件的fid
      this.fileId = fileId;
      this.getBomDateByFileId(fileId);
    },
    doSimilarity(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          getK3Bom(
            this.formQuery.standardCol,
            this.formQuery.categoryCol,
            this.formQuery.quantityCol,
            this.formQuery.packageCol,
            this.formQuery.makerCol,
            this.checkList.toString(),
            this.fileId
          ).then(response => {
            if (!response.result) {
              this.$message.error(response.msg);
              return;
            }
            this.$message.info("匹配成功，请点击行展开查看!");
            this.tableHeader = response.data.header;
            this.tableData = response.data.results;
            this.isShowTable = true;
          });
        } else {
          return false;
        }
      });
    },
    getBomDateByFileId(fileId) {
      //根据fileId获取参数设置信息以及bom信息
      getBomData(fileId).then(response => {
        if (!response.result) {
          this.$message.error(response.msg);
          return;
        }
        //填充表格数据
        this.tableHeader = response.data.header;
        this.tableData = response.data.results;

        //填充下拉框数据
        this.optionHearder = [];
        this.tableHeader.forEach(element => {
          var option = { value: element, label: element };
          this.optionHearder.push(option);
        });

        this.meriOption = response.data.listCategory;

        //填充参数设置
        this.formQuery.standardCol = response.data.bomParams.standardCol;
        this.formQuery.quantityCol = response.data.bomParams.quantityCol;
        if (response.data.bomParams.categoryCol)
          this.formQuery.categoryCol = response.data.bomParams.categoryCol;
        if (response.data.bomParams.packageCol)
          this.formQuery.packageCol = response.data.bomParams.packageCol;
        if (response.data.bomParams.makerCol)
          this.formQuery.makerCol = response.data.bomParams.makerCol;

        //填充进度数据
        this.fillPercent =
          (response.data.totalCost.chosenNum /
            response.data.totalCost.totalNum) *
          100;
          if(this.fillPercent == 100)this.color = 'green'
       // this.fillNum =  response.data.totalCost.chosenNum;
        this.totalNum = response.data.totalCost.totalNum;

        this.fAuxPriceTotal = response.data.totalCost.fAuxPriceDiscount;
        this.fAuxPrice3MonthMaxTotal =
          response.data.totalCost.fAuxPrice3MonthMax;
        this.fAuxPrice3MonthMinTotal =
          response.data.totalCost.fAuxPrice3MonthMin;

        //设置控件的展示
        this.fileId = fileId;
        this.isShowTable = true;
        this.isShow = true;
      });
    },
    doCheck(isCheck, rows) {
      doCheckMateriel(rows.id, isCheck).then(response => {
        if (!response.result) {
          this.$message.error(response.msg);
          return;
        }
        this.$message.info("操作成功!");
        this.currentRow.childrenData = response.data.bomMatchList;

        this.currentRow.checkStatus = isCheck; //改变行颜色
        this.currentRow.checkCode = response.data.bomList.checkCode; //获取选中的K3物料号

        //填充进度数据
        this.fillPercent =
          (response.data.totalCost.chosenNum /
            response.data.totalCost.totalNum) *
          100;

        this.fAuxPriceTotal = response.data.totalCost.fAuxPriceDiscount;
        this.fAuxPrice3MonthMaxTotal =
          response.data.totalCost.fAuxPrice3MonthMax;
        this.fAuxPrice3MonthMinTotal =
          response.data.totalCost.fAuxPrice3MonthMin;
      });
    },
    toInstructions() {
      this.$router.push({ path: "instructionsCost", query: this.$route.query });
    },
    toList() {
      this.$router.push({ path: "clientBomList", query: this.$route.query });
    },
    onSelectedCategory(event, row) {
      // console.log(event)
      // console.log(row)
      this.rowClick(row, "", "");
    },
    //在<table>里，我们已经设置row的key值设置为每行数据id：row-key="id"
    rowClick(row, event, column) {
      this.currentRow = row;
      //获取匹配数据
      if (!row.childrenData) {
        this.loading = true;
        getBomMatch(row.CusBomId, row.mateCategory, 10, 0.4).then(response => {
          if (!response.result) {
            this.$message.error(response.msg);
            return;
          }
          row.childrenData = response.data;
          this.loading = false;
        });
      }

      //--end
      Array.prototype.remove = function(val) {
        let index = this.indexOf(val);
        if (index > -1) {
          this.splice(index, 1);
        }
      };

      if (this.expands.indexOf(row.CusBomId) < 0) {
        this.expands = [];
        this.expands.push(row.CusBomId);
      } else {
        this.expands.remove(row.CusBomId);
      }
    },
     /*删除*/
    doDelete(rowID) {      
      let id = rowID;
      delBom(id).then(response => {
        if (response.result) {
          this.$message({
            message: response.msg,
            type: 'success'
          });
          this.$router.push({ path: 'clientBomList', query: {}});
        } else {
          this.$message.error(res.msg);
        }
      })
    },
  }
};
</script>
<style type="text/css">
/*.demo-form-inline {
  padding: 10px;
}*/
.demo-form-inline .el-form-item__label {
  font-size: 12px;
  font-family: "Microsoft YaHei";
  margin-left:15px;
}
.collapse .el-collapse-item__header {
  font-size: 13px;
  height: 30px;
  line-height:38px;
}
.Operation {
  font-size: 13px;
  cursor: pointer;
}
.el-table .success-row {
  background: #9fe0a6 !important ;
}
.el-table .warning-row {
  background: #f6a556 !important ;
}
.sometable th {
  background: none !important;
}
.priceTotal {
  display: flex;
  height: 70px;
  background: #f7f8fd;
  border: 1px solid #e2e7f0;
}
.priceTotal li {
  list-style: none;
  flex: 1;
  /* text-align: center;*/
  height: 100%;
  background: none;
  border-right: 1px solid #e2e7f0;
  cursor: pointer;
  padding-left: 15px;
  position: relative;
}
.priceTotal p.label {
  color: #96a6b6;
}
.priceTotal .num {
  font-size: 18px;
  height: 8px;
  margin-top: 10px;
}
.priceTotal p.num1 {
  color: #fb7884;
  /* text-align:center; */
}
.priceTotal p.num2 {
  color: #03a9f3;
}
.priceTotal p.num3 {
  color: #9675ce;
}
.progressbox {
  position: relative;
}
.progressbox .total {
  position: absolute;
  right: -5%;
  top: -2px;
  font-size: 14px;
}
.downBtn {
  position: absolute;
  right: 44px;
  top: 4px;
  width: 50px;
  height: 50px;
}
</style>