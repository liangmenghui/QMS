<template>
    <div style="width:100%;height:100%;" id="riskScore"></div>
</template>

<script>
import echarts from 'echarts';
export default {
    name: 'riskScore',
    props:['values','labels'],
    data () {
        return {
            option : {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : [],
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'风险评分',
            type:'line',
            data:[] 
        }
    ]
},
		riskScore:{}
        };
    },
    watch: {
		'values' (to,from){
            this.option.series[0].data = to;
            this.riskScore.setOption(this.option);
            
		},
		'labels' (to,from){
            this.option.xAxis[0].data = to;
            this.riskScore.setOption(this.option);
		}
	},
    mounted () {
        this.$nextTick(() => {
            let riskScore = echarts.init(document.getElementById('riskScore'));
            riskScore.setOption(this.option);
			this.riskScore=riskScore;
            window.addEventListener('resize', function () {
                riskScore.resize();
            });
        });
    }
};
</script>
