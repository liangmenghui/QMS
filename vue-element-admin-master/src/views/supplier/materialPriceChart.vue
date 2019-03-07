<template>
    <div>
        <el-card style="padding-bottom: 0;background: #f0f2f5">
        <el-row>
        <el-col :span="24">
            <el-form :inline="true" :model="formQuery" class="demo-form-inline">
              <el-form-item label="物料名称">
                <el-input v-model="formQuery.mateK3Code" placeholder="物料名称"></el-input>
              </el-form-item>
              <el-form-item label="起始日期" prop="startDate">
               <el-date-picker  
                style="width:186px;"   
                :picker-options="startTime"                      
                  v-model="formQuery.startDate"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="结束日期" prop="endDate">
               <el-date-picker  
                style="width:186px;"                         
                  v-model="formQuery.endDate"
                    :picker-options="endTime"
                  type="date"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
              <el-radio v-model="formQuery.flag" label="1" >采购订单价</el-radio>
              <el-radio v-model="formQuery.flag" label="2">发票价</el-radio>
            </el-form-item>
              <el-form-item>
                 <el-button type="primary" size="mini" @click="getData">查找</el-button>
              </el-form-item>
          </el-form> 
          </el-col>            
        </el-row>
    </el-card> 
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" v-show="isShow"/>
    </el-row>
</div> 
</template>
<script>
//import LineChart from '../dashboard/admin/components/LineChart'
import LineChart from '@/components/Charts/LineChart.vue'
import { getPriceChart } from '@/api/supplier'

const lineChartData = {
  legendData: [],
  xAxisData: [],
  sereisList: []
}

export default {
  name: 'DashboardAdmin',
  components: {
    LineChart
  },
  data() {
    return {
      formQuery: {
        startDate: '',
        endDate: '',
        flag:'1'
      },
      lineChartData: lineChartData,
      legendData: [],
      sereisList: [],
      xAxisData: [],
      isShow:false,
    /* start 开始时间小于今天,结束时间不能大于开始时间 */
      startTime: {
        disabledDate: time => {
          if (this.formQuery.endData) {
            return (
              time.getTime() > new Date(this.formQuery.endData).getTime()
            );
          } else {
            return time.getTime() > Date.now();
          }
        }
      },
      endTime:{
        disabledDate: time => {           
          if (this.formQuery.startDate) {
            return (
              time.getTime() > Date.now() ||
              time.getTime() < new Date(this.formQuery.startDate).getTime()
            );
          } else {
            return time.getTime() > Date.now();
          }
        }
     }
    }
  },
  created() {
    //this.getData(); 
  },
  methods: {
    getData() {
      this.isShow = false;
      //物料编号：01.10.00010、01.10.10130、01.30.10140、01.10.10170
      getPriceChart(this.formQuery.mateK3Code,this.formQuery.startDate,this.formQuery.endDate,this.formQuery.flag).then(response => {
        if (response.result) {
          //初始化数据
          this.initChartData()
          this.isShow = true;
          response.data.forEach(element => {
            //封装legend
            this.legendData.push(element[0].pcSuppName)
            lineChartData.legendData = this.legendData;
            //封装series
            var seriesData = [];
            this.xAxisData = [];
            element.forEach(ele => {
              seriesData.push(ele.pcPrice);
              //封装xAxis
              this.xAxisData.push(ele.pcMonth)
            })
            var sereisMap = {
              name: element[0].pcSuppName,
              smooth: true,
              type: 'line',
              data: seriesData,
              animationDuration: 2800
            }
            this.sereisList.push(sereisMap);
          });
          this.lineChartData.sereisList = this.sereisList
          this.lineChartData.xAxisData = this.xAxisData
          this.lineChartData = lineChartData
          console.log(this.lineChartData)
        } else {
          this.$message.error(response.msg);
        }

      });
    },
    initChartData(){
      //初始化参数
      this.legendData = []
      this.sereisList  = []
      this.xAxisData = []

      this.lineChartData.sereisList = []
      this.lineChartData.xAxisData = []
      this.lineChartData.legendData = [];

    }
  }
  
}
</script>
<style>
    .block {
    text-align: right;
    margin-top: 10px;
}

</style>