<template>
    <span class="split-width">
        <Button v-bind:type="type" v-bind:size="size" @click="showDialog">{{name}}</Button>
        <cust-extprops ref="custExtprops" :data="data" @ok="ok" title="客制化属性"></cust-extprops>
    </span>
</template>
<script>
import custExtprops from './custExtprops.vue';
export default {
    components: {
        custExtprops
    },
    props:{
        type: String,
        size: String,
        name: String,
        catalog: String,
        rid: ''
    },
    data(){
        return{
            title:'客制化属性',
            visible:false,
            data: []
        }
    },
    computed: {
    },
    created(){
    },
    methods:{
        showDialog(val) {
            this.$emit("showDialog");
        },
        show(val) {
            if(typeof(val)=='number'||typeof(val)=='Number') {
                this.rid = val;
            }
            if(this.rid) {
                this.$refs.custExtprops.show(true);
                this.loadData(this.rid);
            }else {
                //console.log("rid is required");
            }
        },
        loadData(rid) {
            let handler = this.catalog+":"+rid;
            this.api.admin.customfieldvalue.getlist({catelog:this.catalog, handler:handler}).then((res)=>{
                if(res.result) {
                    for(let d in res.data.rows) {
                        res.data.rows[d].handler = handler+","+res.data.rows[d].bsName;
                    }
                    this.data = res.data.rows;
                    //Object.assign(this.list,res.data.rows);
                }
            });
        },
        ok (data) {
            this.api.admin.customfieldvalue.save({catelog:this.catalog, json: JSON.stringify(data)}).then((res)=>{
                if(res.result) {
                    this.$message.info(res.message);
                }else {
                    this.$message.error(res.message);
                }
            });
        },
        cancel () {
            this.$emit("cancel");
        }
    }
}

</script>
<style lang="less">
.split-width {padding-left: 3px}
</style>