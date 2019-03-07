<template>
    <form>
        <table class="formed" align="left" valign="left">
           <tr style="width:70%">
               <td colspan="8" style="font-size:30px;height:40px;font-weight:600" >
                  <p>SHIPPING ORDER </p>
                </td>                
           </tr>
           <tr>
               <td colspan="4">
                   <p style="font-weight:600">Shipper:</p>
               </td>
               <td colspan="4" style="border-bottom-style:none;font-weight:700;font-style:italic">
                   <p>DHL GLOBAL FORWARDING/DANZAS</p>
               </td>
           </tr>
           <tr>
               <td colspan="4">
                   <textarea v-model='content.bsShipper' rows="3"></textarea>
               </td>
               <td colspan="4" style="border-bottom-style:none;font-weight:700;font-style:italic">
                   <p>Ningbo Branch</p>
                   <br>
                   <br>
                </td>
           </tr>
          <tr>
              <td colspan="1">
                <nobr>
                     联系人：<input v-model='content.bsShipperName' style="width:150px">
                </nobr>
              </td>
               <td colspan="1">
                <nobr>
                     Tel：<input style="width:150px" v-model='content.bsShipperTel'>
                </nobr>
              </td>
               <td colspan="2">
                <nobr >
                     E-mail：<input style="width:85%" v-model='content.bsShipperMail'>
                </nobr>
              </td>
              <td colspan="4" style="border-bottom-style:none">
                <p></p>
              </td>
          </tr>
          <tr>
              <td colspan="4">
                  <p style="font-weight:600">Consignee:</p>
              </td>
              <td colspan="4" style="border-style:none solid none solid">
                <p>丹沙中福货运代理有限公司</p>
              </td>
          </tr>
          <tr>
              <td colspan="4">
                  <textarea rows="3" v-model='bsConsignee'></textarea>
              </td>
              <td colspan="4">
                  <p>宁波市和义路77号,汇金大厦6楼</p>
                  <p>电话:(574)27700600 传真:(574)27909984</p>
                  <br>
              </td>
          </tr>
          <tr>
              <td colspan="4">
                  <p style="font-weight:600">Notify:</p>
              </td>
              <td colspan="4" align=center>
                  <p style="font-weight:600">S/O NO:</p>
              </td>
          </tr>
           <tr>
             <td colspan="4">
                 <textarea rows="3" v-model='bsNotify'></textarea>
             </td>
             <td colspan="4" align=center>
                 <div style="width:300px;">
                    <textarea rows="3"  v-model='bsSoNo'></textarea>
                </div>
             </td>
          </tr>
          <tr>
              <td colspan="2">
                  <p style="font-weight:600">Intended Vessel:</p>
              </td>
              <td colspan="2">
                  <p style="font-weight:600">Port of Loading:</p>
              </td>
              <td colspan="4">
                  <p></p>
              </td>
          </tr>
           <tr>
              <td colspan="2">
                  <input style="height:30px" v-model='content.bsVessel'>
              </td>
              <td colspan="2">
                    <input style="height:30px" v-model='content.bsLoading'>
              </td>
              <td colspan="4">
                   
              </td>
          </tr>
           <tr>
              <td colspan="2">
                  <p style="font-weight:600">Port of Discharge:</p>
              </td>
              <td colspan="2">
                  <p style="font-weight:600">Final Destination:</p>
              </td>
              <td colspan="2">
                  <p style="font-weight:600"> Number of Original B(s)/L:</p>
              </td>
              <td colspan="2">
                  <p> </p>
              </td>
          </tr>
          <tr>
                <td colspan="2">
                    <input style="height:30px" v-model='content.bsDischarge'>
                </td>
                <td colspan="2">
                      <input style="height:30px" v-model="content.bsDestination">
                </td>
                <td colspan="2">
                     <input style="height:30px" v-model="content.bsNumOrg">
                </td>
                <td colspan="2">
                  <p> </p>
                </td>
            </tr>
            <tr>
              <td colspan="1" style="font-weight:600">
                  <p>Marks and Nos</p>
              </td>
              <td colspan="1" style="font-weight:600">
                  <p>No of packages</p>
              </td>
              <td colspan="4" align=center style="font-weight:600">
                  <p> Description of Goods(Both in English & Chinese)</p>
              </td>
              <td colspan="1" style="font-weight:600">
                  <p> G.W.(KGS)</p>
              </td>
              <td colspan="1" style="font-weight:600">
                  <p>Meas.(CBM) </p>
              </td>
          </tr>
          <tr>
              <td colspan="1">
                  <input v-model='content.bsMarks'>
              </td>
              <td colspan="1">
                  <input  v-model='content.bsPackagesQuantity'>
              </td>
              <td colspan="4" align=center>
                   <input v-model='content.bsDescription' >
              </td>
              <td colspan="1">
                  <input v-model='content.bsWeight'>
              </td>
              <td colspan="1">
                  <input v-model='content.bsMeasurement'>
              </td>
          </tr>
          <tr>
              <td colspan="2" align=center>
                  total:
              </td>
              <td colspan="4" align=center>
                  
              </td>
              <td colspan="1">
                  <input>
              </td>
              <td colspan="1">
                  <input>
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
        bsSoNo:'',
        bsShipper: "",
        bsShipperName:'',
        bsShipperTel:'',
        bsShipperMail:'',
        bsConsignee:'',
        bsNotify:'',
        bsVessel:'',
        bsLoading:'',
        bsDestination:'',
        bsNumOrg:'',
        bsDischarge:'',
        bsDelivery:'',
        bsMarks:'',
        bsPackagesQuantity:'',
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
