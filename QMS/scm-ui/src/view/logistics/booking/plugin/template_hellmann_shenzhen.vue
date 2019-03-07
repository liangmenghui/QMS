<template>
    <form>
        <table class="formed" align="left" valign="left">
            <tr>
                <td colspan="8" align=center  style='height:50px;font-size:25px;color:#003C9D '>
                    <font >
                            SHIPPING ORDER
                    </font>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="width:30%;font-weight:800;color:#003C9D">
                    <p>
                        <u>1. Booking Party (订舱人)</u>
                    </p>
                </td>
                <td colspan="1" style="width:10%;font-size:15px;color:#003C9D;font-weight:800" align=center>
                    <p style="border-bottom-style:none;">
                        <u>Shipping Order No.</u>
                    </p>
                    <p>
                        <u>(订舱编号)</u>
                    </p>
                </td>
                <td colspan="3" style="width:20%;border-bottom-style:none">
                        <input v-model='form.bsShipperNo'  style="width:100%;height:39px"></input>
                </td>
            </tr>
            <tr>
                <td colspan="2"></td>
                <td colspan="2"></td>
            </tr>

            <tr>
                <td colspan="4" >
                    <textarea  v-model='form.bsBookingPart' rows="4"></textarea>
                </td>
                <td colspan="4" style="font-size:13px;color:#003C9D;border-bottom-style:none">
                    <p>注意: </p>
                    <p>&nbsp&nbsp  (1) 截货期前未能出示有效出口证之货物将不被装箱/付运. </p>
                    <p>&nbsp&nbsp  (2) 如因货物超重引致任何后果或赔偿均由货主负责. </p>
                    <p>&nbsp&nbsp  (3) 请提前2周与我司下订单。如需我司安排拖车请在订单上注明.</p>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="width:10%;">
                 <nobr>
                        E-mail:<input v-model='form.bsBookingPartMail' style="width:81%">
                 </nobr>
                </td>
                 <td colspan="2">
                   <nobr>
                        CTC :<input v-model='form.bsBookingPartCtc' style="width:91%">
                  </nobr>     
                </td>
                 <td colspan="4" style="width:10%;border-bottom-style:none;border-top-style:none">
                  <p></p>
                </td>
                
            </tr>
            <tr>
                <td colspan="2" style="width:10%">
                 <nobr>
                        Tel No.<input v-model='bsBookingPartTel' style="width:81%">
                 </nobr>
                </td>
                 <td colspan="2">
                   <nobr>
                        Fax : <input v-model='bsBookingPartFax' style="width:91%">
                  </nobr>     
                </td>
                 <td colspan="4" style="width:10%">
                  <p></p>
                </td>
                
            </tr>
           <tr>
               <td colspan="4" style="width:30%;font-weight:800;color:#003C9D">
                  <p>2. Shipper (发货人) </p>
                </td>
                 <td colspan="4" align=center style="background-color:#0f23d3ce;color:#e1e2e3">
                       <p >* 请选择您的要求,以提供适当的服务,谢谢! *</p>
                </td>
                         
           </tr>

           <tr>
                  <td colspan="4">
                        <textarea  v-model='bsShipper' rows="4"></textarea>
                 </td>
                  <td colspan="4">
                         <p></p>
                 </td>        
            </tr>
             <tr>
                <td colspan="2" style="width:10%;">
                 <nobr>
                        E-mail:<input v-model='bsShipperMail' style="width:81%">
                 </nobr>
                </td>
                 <td colspan="2">
                   <nobr>
                        CTC :<input  v-model='bsShipperCtc' style="width:91%">
                  </nobr>     
                </td>
                 <td colspan="4" style="width:10%;border-bottom-style:none;border-top-style:none">
                  <p></p>
                </td>
                
            </tr>
            <tr>
                <td colspan="2" style="width:10%">
                 <nobr>
                        Tel No.<input  v-model='bsShipperTel' style="width:81%">
                 </nobr>
                </td>
                 <td colspan="2">
                   <nobr>
                        Fax : <input v-model='bsShipperFax' style="width:91%">
                  </nobr>     
                </td>
                 <td colspan="4" style="width:10%">
                  <p></p>
                </td>
                
            </tr>
            <tr>
               <td colspan="4" style="width:30%;font-weight:800;color:#003C9D">
                  <p>3. Consignee (收货人)  </p>
                </td>
                 <td colspan="4" align=center style="background-color:#0f23d3ce;color:#e1e2e3">
                       <p ></p>
                </td>                      
           </tr>
           <tr>
                <td colspan="4">
                      <textarea  v-model='bsConsignee' rows="4"></textarea>
               </td>
                <td colspan="4">
                       <p></p>
               </td>        
          </tr>
           <tr>
                <td colspan="2" style="width:10%">
                 <nobr>
                        Tel No.<input   v-model='bsConsigneeTel' style="width:81%">
                 </nobr>
                </td>
                 <td colspan="2">
                   <nobr>
                        Fax : <input   v-model='bsConsigneeFax' style="width:91%">
                  </nobr>     
                </td>
                 <td colspan="4" style="width:10%">
                  <p></p>
                </td>
                
            </tr>
            <tr>
               <td colspan="4" style="width:30%;font-weight:800;color:#003C9D">
                  <p>4. Notify party (通知人)  </p>
                </td>
                 <td colspan="4" align=center style="background-color:#0f23d3ce;color:#e1e2e3">
                       <p ></p>
                </td>                      
           </tr>
           <tr>
                <td colspan="4">
                      <textarea   v-model='bsNotifyParty' rows="4"></textarea>
               </td>
                <td colspan="4">
                       <p></p>
               </td>        
          </tr>
           <tr>
                <td colspan="2" style="width:10%">
                 <nobr>
                        Tel No.<input v-model='bsNotifyPartyTel' style="width:81%">
                 </nobr>
                </td>
                 <td colspan="2">
                   <nobr>
                        Fax : <input v-model='bsNotifyPartyFax' style="width:91%">
                  </nobr>     
                </td>
                 <td colspan="4" style="width:10%">
                  <p></p>
                </td>
                
            </tr>       
           <tr>
               <td colspan="2" style="font-weight:600;color:#003C9D" >
                  <p>Vessel (船名)</p>
               </td>
               <td colspan="2" style="font-weight:600;color:#003C9D">
                  <p>Voyage (航次)</p>
               </td>
               <td  colspan="2" >
                    <p></p>
               </td>
               <td  colspan="2" >
                    <p></p>
               </td>
           </tr>
           <tr>
                <td colspan="2"  >
                  <input v-model='bsVessel' ></input>
                </td>
                <td colspan="2" >
                    <input v-model='bsVoyage' ></input>
                </td>
                <td  colspan="2" >
                     <p></p>
                </td>
                <td  colspan="2" >
                     <p></p>
                </td>
            </tr>
            <tr>
                    <td colspan="2" style="font-weight:600;color:#003C9D" >
                       <p>5. Port of Receipt (收货地)</p>
                    </td>
                    <td colspan="2" style="font-weight:600;color:#003C9D">
                       <p>6. Port of Loading (装货港)</p>
                    </td>
                    <td  colspan="2" style="font-weight:600;color:#003C9D">
                         <p>ETD :</p>
                    </td>
                    <td  colspan="2" >
                         <p></p>
                    </td>
                </tr>
                <tr>
                     <td colspan="2"  >
                       <input v-model='bsReceipt' ></input>
                     </td>
                     <td colspan="2" >
                         <input  v-model='bsLoading'></input>
                     </td>
                     <td  colspan="2" >
                           <input  v-model='bsEtd'></input>
                     </td>
                     <td  colspan="2" >
                          <p></p>
                     </td>
                 </tr><tr>
                        <td colspan="2" style="font-weight:600;color:#003C9D" >
                           <p>7. Port of discharge (卸货港)</p>
                        </td>
                        <td colspan="2" style="font-weight:600;color:#003C9D" >
                           <p>8. Place of Delivery (目的地)</p>
                        </td>
                        <td  colspan="2" style="font-weight:600;color:#003C9D" >
                             <p>ETA :</p>
                        </td>
                        <td  colspan="2" >
                             <p></p>
                        </td>
                    </tr>
                    <tr>
                         <td colspan="2"  >
                           <input  v-model='bsDischarge' ></input>
                         </td>
                         <td colspan="2" >
                             <input   v-model='bsDelivery'></input>
                         </td>
                         <td  colspan="2" >
                               <input  v-model='bsEta' ></input>
                         </td>
                         <td  colspan="2" >
                              <p></p>
                         </td>
                     </tr>
            <tr>
                <td colspan="8" height="20px">
                    <p></p>
                </td>
            </tr>
             <tr>
                <td colspan="3" align=center style="font-weight:600;color:#003C9D">
                   <p>11. Marks and numbers</p>
                 </td>
                  <td colspan="1" align=center style="font-weight:600;color:#003C9D">
                    <p>12. Quantity and </p>
                    <p>Type of packages </p>
                 </td>
                  <td colspan="2" align=center style="font-weight:600;color:#003C9D">
                   <p>13. Description of Pkg. & Goods</p>
                   <p>(Particulars Furnished by Shipper)</p>
                 </td>
                  <td colspan="1"  align=center style="font-weight:600;color:#003C9D">
                    <p>14. Gross Weight</p>  
                    <p>(KGS)</p> 
                 </td>     
                 <td colspan="1" align=center style="font-weight:600;color:#003C9D">
                        <p>15. Measurement</p>  
                        <p>(CBM)</p> 
                     </td>     
            </tr>
            <tr>
                <td colspan="3">
                        <input type="text" style="width:100%"  v-model="form.bsMarks"></input>
                 </td>
                  <td colspan="1">
                        <input type="text" style="width:100%"  v-model="form.bsPackagesQuantity"></input>
                 </td>
                  <td colspan="2">
                        <input type="text" style="width:100%"  v-model="form.bsDescription"></input>
                 </td>
                  <td colspan="1">
                        <input type="text" style="width:100%"  v-model="form.bsWeight"></input>
                 </td>   
                 <td colspan="1">
                        <input type="text" style="width:100%"  v-model="form.bsMeasurement"></input>
                 </td>      
            </tr>
        </table>
    </form>
</template>

<script>
import Vue from "vue";
export default {
  props: {
    formData: {
      type: [Number, Object, Array, String]
    },
    customer: {
      type: [Number, String]
    },
    agent: {
      type: [Number, String]
    }
  },
  data() {
    return {
      receipts: [{ name: "", desc: "请选择" }],
      deliveries: [{ name: "", desc: "请选择" }],
      contentData:"",
      form: {
        bsShipperNo:'',
        bsBookingPart:'',
        bsBookingPartMail:'',
        bsBookingPartCtc:'',
        bsBookingPartTel:'',
        bsBookingPartFax:'',
        bsShipper: "",
        bsShipperMail:'',
        bsShipperCtc:'',
        bsShipperTel:'',
        bsShipperFax:'',
        bsConsignee:'',
        bsConsigneeTel:'',
        bsConsigneeFax:'',   
        bsNotifyParty:'',
        bsNotifyPartyTel:'',
        bsNotifyPartyFax:'',
        bsVessel:'',
        bsVoyage:'',
        bsLoading:'',
        bsReceipt:'',
        bsEtd:'',
        bsDischarge:'',
        bsDelivery:'',
        bsEta:'',
        bsMarks:'',
        bsPackagesQuantity:'',
        bsDescription:'',
        bsWeight:'',
        bsMeasurement:''
      }
    };
  }, //data
  methods: {
    getData() {
      //校验
      this.$emit("on-commit", this.form);
    }
  }, //methods
  watch: {
    formData(value) {
      let val = JSON.stringify(value);
      if (!val) return;
      this.form = JSON.parse(val);
    }
  } //watch
};
</script>

<style>
table {
  padding: 0;
  margin: 0;
  border-collapse: separate;
  border-spacing: 0px;
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
