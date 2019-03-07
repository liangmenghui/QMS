<template>
    <form>
        <table class="formed" align="left" valign="left">
            <tr>
                <td colspan="2" style="font-weight:700;height:30px">
                    <p>Shipper's Reference(发货人参考号)</p>
                </td>
                 <td colspan="2" style="font-weight:700;height:30px">
                    <p>Shipper's Panalpina#(发货人的泛亚班拿RAR号码)</p>
                 </td>
                  <td colspan="2" style="font-weight:700;height:30px">
                    <p>Reserver HAWB#泛亚班拿提单号</p>
                 </td>
                 <td colspan="2" style="font-weight:700;height:30px">
                    <textarea rows="2"></textarea>
                 </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input>
                </td>
                <td colspan="2">
                    <input>
                </td>
            </tr>


            <tr >
                <td colspan="1">
                    <p>Shipper</p>
                    <p>发货人</p>
                </td>
                <td colspan="3">
                    <textarea rows="5" v-model='content.bsShipper'></textarea>
                </td>
                <td colspan="2">
                    <p>Customer Reference:</p>
                    <p>客户编码：</p>
                </td>
                <td colspan="2">
                    <textarea rows="5" v-model='content.bsCustomerReference'></textarea>
                </td>
            </tr>
            <tr >
                <td colspan="1">
                    <p>Consignee:</p>
                    <p>收货人：</p>
                </td>
                <td colspan="3">
                    <textarea rows="5" v-model='content.bsConsignee'></textarea>
                </td>
                <td colspan="2">
                    <p>Date of Departure:</p>
                    <p>离港日：</p>
                    <p>Name of Vessel:</p>
                    <p>船名: </p>
                </td>
                <td colspan="2">
                    <textarea rows="2" v-model='content.bsDeparture'></textarea>
                    <textarea rows="3" v-model='content.bsVessel'></textarea>
                </td>
            </tr>
            <tr >
                <td colspan="1">
                    <p>Notify Part:</p>
                    <p>通知方</p>
                </td>
                <td colspan="3">
                    <textarea rows="5" v-model='content.bsNotifyPart'></textarea>
                </td>
                <td colspan="2">
                    <p>Shipping Line:</p>
                    <p>船公司: </p>
                </td>
                <td colspan="2">
                    <textarea rows="5" v-model='content.bsShippingLine'></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p>Port of Loading:</p>
                    <p>启运港</p>
                </td>
                <td colspan="2">
                    <input style='height:30px' v-model='content.bsLoading'>
                </td>
                <td colspan="2">
                    <p>Port of Destination:</p>
                    <p>目的港</p>
                </td>
                <td colspan="2" >
                        <input style='height:30px' v-model='content.bsDestination'>
                </td>
            </tr>
           
           
         
         <tr>
                <td colspan="2" align=center style="font-weight:600">
                    <p>Marks: </p>
                    <p>(唛 头)</p>
                </td>
                <td colspan="2" align=center style="font-weight:600">
                  <p>Packages / Pieces:</p>
                  <p>件数</p>
                </td>
                <td colspan="2" align=center style="font-weight:600">
                   <p>Description of Goods:</p>
                   <p>货物品名</p>
                </td>
                <td colspan="1" align=center style="font-weight:600">
                   <p>Gross Weight:</p>
                   <p>毛重</p>
                </td>
                <td colspan="1" align=center>
                    <P>CBM</P>
                    <p>Measurement:</p>
                    <p>体积</p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input v-model="content.bsMarks">
                </td>
                <td colspan="2">
                   <input  v-model="content.bsPackages">
                </td>
                <td colspan="2">
                    <input v-model="content.bsDescription">
                </td>
                <td colspan="1">
                    <input v-model="content.bsWeight">
                </td>
                <td colspan="1">
                    <input v-model='content.bsMeasurement'>
                </td>
            </tr>
        </table>
    </form>
</template>

<script>
import Vue from "vue";
export default {
  props: {
    customer: {
      type: String
    },
    agent: {
      type: String
    },
    cabinets: {
      type: Array
    },
    contentData: {
      type: [Array, Object],
      required: true
    }
  }, //props
  data() {
    return {
      receipts: [{ name: "", desc: "请选择" }],
      deliveries: [{ name: "", desc: "请选择" }],
      contentData:"",
      content: {
        bsShipper: "",
        bsCustomerReference:'',
        bsConsignee:'',
        bsNotifyPart:'',
        bsVessel:'',
        bsDeparture:'',
        bsLoading:'',
        bsShippingLine:'',
        bsDestination:'',
        bsMarks:'',
        bsPackages:'',
        bsDescription:'',
        bsWeight:'',
        bsMeasurement:''
       
        
      },
      summary: {},
      detail: []
    };
  }, //data
  methods: {
    getData: function() {
      let o = {};
      o.content = this.content;
      o.summary = this.summary;
      o.detail = this.detail;
      this.$emit("on-commit", o);
    } //getFormData
  }, //methods
  watch: {
    contentData(val) {
      if (!val || !val.content || !val.detail || !val.summary) return;
      this.content = val.content;
      this.detail = val.detail;
      this.summary = val.summary;
      if (this.cabinets && this.cabinets.length > 0) {
        this.content.bsShipDay = this.cabinets[0].day;
      }
    }
  }
};
</script>

<style>
table {
  padding: 0;
  margin: 0;
  border-collapse: separate;
  border-spacing: 0px;
  width:90%
}
.td2{
    font-weight:bold
}
.formed {
  margin: 6px 10px;
  border: 2px solid #000;
  line-height: 1.6em;
  letter-spacing: 0.02em;
  overflow: hidden;
}
.formed tr {
  padding: 0px;
}
.formed tr td {
  border: 0;
  border-right: 1px solid #c1c1c3;
  border-bottom: 1px dashed #c1c1c3;
  padding: 0px;
  padding: 1px 1px;
}

.formed > tr:last-child > td {
  border-bottom: 0;
}
.formed .sperator {
  border-top: 2px solid #000;
}

.formed p > u {
  margin: 3px 5px;
  font-size: 12px;
  display: inline;
  line-height: 12px;
  text-decoration: none;
  font-family: Arial, Helvetica, sans-serif;
}
.formed input,
select {
  width: 100%;
  border: 1px solid #c1c1c3;
}
.formed input {
  background-color: #f8f9fa;
  width:96%;
}
.formed label {
  margin-left: 30px;
  position: relative;
}
.formed input[type="checkbox"] {
  margin-left: 2px;
  position: absolute;
  left: -20px;
  bottom: 1px;
}

.formed textarea {
  resize: none;
  width: 100%;
  border: 1px solid #c1c1c3;
  background-color: #e1e2e3;
}
.f-blue {
  background-color: #c0d9f1;
}
.f-white {
  background-color: #fff;
}
.f-yellow {
  background-color: #ff0;
}
</style>
