<template>
    <form>
        <table class="formed" align="left" valign="left">
            <tr>
                <td colspan="4" style="font-weight:600">
                    <p>Shipper / Exporter (Name & Address) （发货人）</p>
                </td>
                <td colspan="4" style="font-weight:600">
                    <p>
                            
                    </p>
                </td>
            </tr>
           <tr>
               <td colspan="4">
                   <textarea v-model='content.bsShipper' rows="4"></textarea>
               </td>
               <td colspan="4" style="border-bottom-style:none;font-style:italic">
                    <p style="font-weight:700;font-size:20px">KUEHNE<span style="color:blue">+</span>NAGEL</p>
                    <p>kuehne+Nagel Limited Shanghao Branch Office</p>
                    <p>Ping An Mansion,Unit A,11 Floor, No.59 Machang Road,Hexi</p>
                    <p>District Tianjin 300203,P.R.China</p>
                    <p>Tel:022-83865756 Fax:022-83865915/83865917</p>
                </td>
           </tr>
           <tr>
              <td colspan="2" style="font-weight:600">
                <nobr>
                     Tel  :&nbsp&nbsp&nbsp&nbsp&nbsp<input v-model='content.bsShipperTel' style="width:150px">
                </nobr>
              </td>
               <td colspan="2" style="font-weight:600">
                <nobr>
                     Fax:<input style="width:80%" v-model='content.bsShipperFax'>
                </nobr>
              </td>
               <td colspan="4" style="font-weight:600">
                <nobr >
                     
                </nobr>
              </td>
          </tr>
          <tr>
              <td colspan="2" style="font-weight:600">
                <nobr>
                     Email：<input v-model='content.bsShipperEmail' style="width:150px">
                </nobr>
              </td>
               <td colspan="2" style="font-weight:600">
                <nobr>
                     Contact:<input style="width:80%" v-model='content.bsShipperContact'>
                </nobr>
              </td>
              <td colspan="4" style="border-bottom-style:none;text-align:right" >
                 <p>Service with Passion - </p>
              </td>
          </tr>
          <tr>
              <td colspan="4" style="font-weight:600">
                  <p>Consignee （收货人）</p>
              </td>
              <td colspan="4" style="font-weight:600;text-align:right;border-bottom-style:none">
                  <p>Total Quality Customer Satisfaction Through Team-work</p>
              </td>
          </tr>
          <tr>
              <td colspan="4">
                  <textarea rows="4" v-model='content.bsConsignee'></textarea>
              </td>
              <td colspan="4" style="border-style:none solid none solid">
                 <p align=right>德迅货运遍全球，顾客服务胜一筹。</p>
                  <p style='font-size:20px;text-align:center'>SHIPPING ORDER</p>
                  <br>
                  <br>
              </td>
          </tr>
          <tr>
              <td colspan="4">
                 <nobr>
                     Tel:<input v-model='content.bsConsigneeTel'>
                 </nobr>
              </td>
              <td colspan="4" style="border-style:none solid none solid">
                    <nobr>
                            Shipping Order (S/O) No:<input style="height:25px" v-model='content.bsShippingOrderNo'>
                    </nobr>
                 </td>
          </tr>

          <tr>
              <td colspan="4" style="font-weight:600">
                  <p>Notify（通知方）</p>
              </td>
              <td colspan="4" style="border-style:none solid none solid">
                  <p></p>
              </td>
          </tr>
          <tr>
              <td colspan="4">
                  <textarea rows="4" v-model='content.bsNotify'></textarea>
              </td>
              <td colspan="4" style="border-top-style:none">
                 <nobr>
                        Job Reference No:<input v-model='content.bsJobReferNo'>
                        <br>
                        <br>
                        <br>
                 </nobr>
              </td>
             
          </tr>
          <tr>
              <td colspan="2" style="font-weight:600">
                  <nobr>
                        Vessel/Voyage :<input style="width:70%" v-model='bsVessel'>
                  </nobr>
              </td>
              <td colspan="2" style="font-weight:600">
                  <nobr>
                        Place of Receipt:<input v-model="content.bsbsReceipt" style="width:70%">
                  </nobr>
              </td>
              <td colspan="4">
                  <p></p>
              </td>
          </tr>
          <tr>
              <td colspan="2" style="font-weight:600">
                  <p>Port of Loading(装货港) :</p>
              </td>
              <td colspan="2" style="font-weight:600">
                  <p>Port of Discharge: </p>
              </td>
              <td colspan="2">
                  <p>Place of Delivery(最总交货地点)</p>
              </td>
              <td colspan="2">
                  <P>Cargo Readiness (裝货时间)</P>
              </td>
          </tr>

          <tr>
              <td colspan="2" style="font-weight:600">
                    <input  v-model='content.bsLoading'>
              </td>
              <td colspan="2">
                <input  v-model='bsDischarge'>
              </td>
              <td colspan="2" style="font-weight:600">
                    <input  v-model='content.bsDelivery'>
              </td>
              <td colspan="2" style="font-weight:600">
                    <input  v-model='content.bsReadiness'>
              </td>
          </tr>
         <tr>
                <td colspan="2" align=center style="font-weight:600">
                    <p>Marks & Numbers </p>
                    <p>(唛 头)</p>
                </td>
                <td colspan="2" align=center style="font-weight:600">
                  <p>Description of goods</p>
                  <p>(货物名称)</p>
                </td>
                <td colspan="2" align=center style="font-weight:600">
                   <p>No. of Pkgs</p>
                   <p>(件 数)</p>
                </td>
                <td colspan="1" align=center style="font-weight:600">
                   <p>kilos</p>
                   <p>(公斤)</p>
                </td>
                <td colspan="1" align=center>
                    <P>CBM</P>
                    <p>(立方米)</p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input v-model="content.bsMarks">
                </td>
                <td colspan="2">
                   <input  v-model="content.bsDescription">
                </td>
                <td colspan="2">
                    <input v-model="content.bsPackages">
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
        bsShipperContact:'',
        bsShipperEmail:'',
        bsShipperTel:'',
        bsShipperFax:'',
        bsShipperContact:'',
        bsConsignee:'',
        bsNotify:'',
        bsVessel:'',
        bsLoading:'',
        bsDischarge:'',
        bsDelivery:'',
        bsReceipt:'',
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
