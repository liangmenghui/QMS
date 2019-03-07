<template>
    <div style="width:100%;height:100%;" id="inspectValue"></div>
</template>

<script>
import echarts from 'echarts';
export default {
    name: 'inspectValue',
    props:['values','labels'],
    data () {
        return {
            option : {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'验货不合格次数',
            type:'bar',
            barWidth: '60%',
            data:[] 
        }
    ]
},
		inspectValue:{}
        };
    },
    watch: {
		'values' (to,from){
            this.option.series[0].data = to;
            this.inspectValue.setOption(this.option);
            
		},
		'labels' (to,from){
            this.option.xAxis[0].data = to;
            this.inspectValue.setOption(this.option);
		}
	},
    mounted () {
        this.$nextTick(() => {
            let inspectValue = echarts.init(document.getElementById('inspectValue'));
            inspectValue.setOption(this.option);
			this.inspectValue=inspectValue;
            window.addEventListener('resize', function () {
                inspectValue.resize();
            });
        });
    }
};
</script>
