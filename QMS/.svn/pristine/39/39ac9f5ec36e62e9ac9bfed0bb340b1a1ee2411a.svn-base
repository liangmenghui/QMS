<template>
    <form>
        <table class="formed" align="left" valign="left">
           <tr style="width:70%">
               <td colspan="4" style="width:20%;">
                  <p>Shipper </p>
                </td>
                 <td colspan="1" style="width:10%">
                       <p ></p>
                </td>
                <td colspan="1" style="width:5%">
                    <p>  S/O No.</p>
                    <br>
                    <p></p>
                </td>
                <td colspan="2" style="width:10%">
                    <textarea rows="2" v-model="form.bsSoNo"></textarea>
                </td>          
           </tr>
           <tr>
               <td colspan="4">
                   <textarea v-model="form.bsShipper" rows="3"></textarea>
               </td>
               <td colspan="1">
                   
                </td>
                <td colspan="1">
                    <p>  E/L No.</p>
                    <br>
                    <p></p>  
                </td>
                <td colspan="2">
                   <textarea v-model="form.bsElNo" rows="2"></textarea>
                </td>
           </tr>
           <tr>
              <td colspan="2" style="border-bottom-style: solid;border-width:2px;border-color:#000">
                  <nobr>
                     PIC: <input v-model="form.bsShipperPic" style="width:92%">
                  </nobr>
              </td> 
              <td colspan="2" style="border-bottom-style: solid;border-width:2px;border-color:#000">
                  <nobr>
                    Tel :  <input v-model="form.bsShipperTel" style="width:92%">
                  </nobr>     
             </td> 
              <td colspan="4" style="border-bottom-style: solid;border-width:2px;border-color:#000"></td> 
           </tr>
           <tr>
               <td colspan="4">
                   <p>Consignee (if 'order' state notify party)</p> 
               </td>
               <td colspan="4" style="border-bottom-style:none">
                  <p>Pantos Logistics (Shenzhen) Co., Ltd.</p>
               </td>
           </tr>
           <tr>
               <td colspan="4" style="border-bottom-style: solid;border-width:2px;border-color:#000">
                   <textarea v-model="form.bsConsignee" rows="5"></textarea>
               </td>
               <td colspan="4" style="border-bottom-style: solid #000;border-width:2px">
                   <p>RM05/06, 10/F, TOWER B, TILEY CENTRAL </p>
                   <p>PLAZA, NO.3 HAIDE ROAD, NANSHAN </p>
                   <p>DISTRICT, SHENZHEN, CHINA </p>
                   <br>
                   <br>
               </td>
           </tr>
           <tr>
               <td colspan="4" >
                   <p> Notify Party</p>
               </td>
               <td colspan="4" align=center style="font-weight:800;font-size:18px">
                   <p>  SHIPPING ORDER (OCEAN)</p>
               </td>
           </tr>
           <tr>
               <td colspan="4" >
                   <textarea  v-model="form.bsNotifyParty" rows="5"></textarea>
               </td>
               <td colspan="4" style="border-style:none none none solid">
                    <textarea v-model="form.bsShippingOrder" rows="5" ></textarea>
                </td>
           </tr>
           <tr>
               <td colspan="2">
                  <p> Ocean Vessel/Voyage No. </p>
               </td>
               <td colspan="2">
                  <p>    Port of loading </p>
               </td>
               <td colspan="2" style="border-style:none none none solid">
                    <p> CLOSING:  </p>
               </td>
               <td colspan="1">
                 <p>ETD:</p>
               </td>
               <td colspan="1">
                  <input  v-model="form.bsEtd">
               </td>
           </tr>
           <tr>
                <td colspan="2">
                        <input v-model="form.bsVessel">
                </td>
                <td colspan="2">
                        <input v-model="form.bsLoading">
                </td>
                <td colspan="2">
                        <input  v-model="form.bsClosing">
                </td>
                <td colspan="1">
                  <p>ETA:</p>
                </td>
                <td colspan="1">
                     <input v-model="form.bsEta">
                </td>
            </tr>
            <tr>
               <td colspan="2">
                  <p>  Port of Discharge </p>
               </td>
               <td colspan="2">
                  <p>   Place of delivery</p>
               </td>
               <td colspan="2">
                    <p> </p>
               </td>
               <td colspan="2">
                 <p>Number of Original B/L required</p>
               </td>
           </tr>
           <tr>
                <td colspan="2">
                        <input v-model='form.bsDischarge'>
                </td>
                <td colspan="2">
                        <input v-model="form.bsDelivery">
                </td>
                <td colspan="2">
                  
                </td>
                <td colspan="2">
                     <input v-model="form.bsRequired">
                </td>
            </tr>
            <tr>
                <td colspan="8" align=center style="font-weight:700">
                    PARTICULARS FURNISHED BY SHIPPER
                </td>
            </tr>
            <tr align=center>
                <td colspan="2">
                    <p>Marks & Number</p>
                </td>
                <td colspan="1">
                     <p>No. of Cntr or other pkg</p>
                </td>
                <td colspan="3">
                    <p>Description of Goods</p>
                </td>
                <td colspan="1">
                    <p>Gross Weight</p>
                    <p>KGS</p>
                </td>
                <td colspan="1">
                    <p>Measurement</p>
                    <p>CBM</p>
                </td>
            </tr>
            <tr align=center>
                <td colspan="2">
                   <input v-model="form.bsMarks">
                </td>
                <td colspan="1">
                      <input v-model="form.bsPackagesQuantity">
                </td>
                <td colspan="3">
                     <input v-model="form.bsDescription">
                </td>
                <td colspan="1">
                    <input v-model="form.bsWeight">
                </td>
                <td colspan="1">
                    <input v-model="form.bsMeasurement">
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
    }
  }, //props
  data() {
    return {
      receipts: [{ name: "", desc: "请选择" }],
      deliveries: [{ name: "", desc: "请选择" }],
      contentData:"",
      form: {
        bsSoNo:'',
        bsShipper: "",
        bsShipperTel:'',
        bsShipperPic:'',
        bsConsignee:'',  
        bsNotifyParty:'',
        bsShippingOrder:'',
        bsVessel:'',
        bsLoading:'',
        bsDischarge:'',
        bsDelivery:'',
        bsRequired:'',
        bsMarks:'',
        bsPackagesQuantity:'',
        bsDescription:'',
        bsWeight:'',
        bsMeasurement:'',
        bsFreightPayable:'',
        bsClosing:'',
        bsEta:'',
        bsEtd:'',
       
        
      }
    };
  }, //data
  methods: {
    
   
  }, //methods

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
