<template>
    <form>
        <table class="formed" align="left" valign="left">
            <tr>
                <td colspan="8" align=center style='height:65px;font-size:25px'>
                    <font >
                            BOOKING FORM     订舱委托书
                    </font>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="height:40px;width:15%">
                    <p>
                        <u>CUSTOMER REFERENCE NUMBER (INVOICE #)</u>
                    </p>
                </td>
                <td colspan="2" style="height:40px;width:25%">
                        <input v-model='form.bscustomerRefNum'  style="width:100%;height:40px"></input>
                </td>
                <td colspan="2" style="height:40px;width:15%">
                    <p>
                        <u>客  户  编  号          （发票号）</u>
                    </p>
                </td>
                <td colspan="2" style="height:40px;width:25%">
                        <input v-model='form.bscustomerRefNum'  style="width:100%;height:40px"></input>
                </td>
            </tr>
            <tr>
                <td colspan="8" align=center>
                    <p style="font-weight:800">SHIPMENT INFORMATION   货运信息</p>
                </td>

            </tr>
            <tr>
                <td colspan="1" style="width:10%">
                  <p>SHIPPER</p>
                </td>
                 <td colspan="3">
                        
                </td>
                 <td colspan="1" style="width:10%">
                  <p>CONSIGNEE</p>
                </td>
                 <td colspan="3">
                       
                </td>
            </tr>
           <tr>
               <td colspan="1">
                  <p>发货人</p>
                </td>
                 <td colspan="3">
                        <textarea v-model='form.bsShipper' rows="4" style="width:100%"></textarea>
                </td>
                 <td colspan="1">
                  <p>收货人</p>
                  <br>
                  <p>PLANT CODE</p>
                </td>
                 <td colspan="3">
                        <textarea v-model='form.bsConsigneeReciver' rows="2" style="width:100%"></textarea>
                        <textarea v-model='form.bsConsigneePlantCode' rows="2" style="width:100%"></textarea>
                </td>        
           </tr>

           <tr>
                <td colspan="1">
                   <p>CONTACT</p>
                 </td>
                  <td colspan="3">
                         <input v-model='form.bsShipperContact' rows="4" style="width:100%"></input>
                 </td>
                  <td colspan="1">
                   <p>CONTACT</p>
                 </td>
                  <td colspan="3">
                         <input v-model='form.bsConsigneeContact' rows="2" style="width:100%"></input>
                 </td>        
            </tr>
            <tr>
                <td colspan="1">
                   <p>E-MAIL</p>
                 </td>
                  <td colspan="3">
                         <input v-model='form.bsShipperEmail' rows="4" style="width:100%"></input>
                 </td>
                  <td colspan="1">
                   <p>E-MAIL</p>
                 </td>
                  <td colspan="3">
                         <input v-model='form.bsConsigneeEmail' rows="2" style="width:100%"></input>
                 </td>        
            </tr>
             <tr>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                   <p>PHONE #</p>
                 </td>
                  <td colspan="3" style="border-bottom-style:solid;border-bottom-color:#141414;">
                         <input v-model='form.bsShipperPhone' rows="4" style="width:100%"></input>
                 </td>
                  <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                   <p>PHONE #</p>
                 </td>
                  <td colspan="3" style="border-bottom-style:solid;border-bottom-color:#141414;">
                         <input v-model='form.bsConsigneePhone' rows="2" style="width:100%"></input>
                 </td>        
            </tr>
           <tr>
                <td colspan="1">
                   <p>ORDER</p>
                 </td>
                  <td colspan="3">
                        
                 </td>
                  <td colspan="1">
                   <p>NOTIFY</p>
                 </td>
                  <td colspan="3">
                        
                 </td>        
            </tr>
            <tr>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                   <p>指示人</p>
                 </td>
                  <td colspan="3" style="border-bottom-style:solid;border-bottom-color:#141414;">
                         <input v-model='form.bsOrder' rows="4" style="width:100%"></input>
                 </td>
                  <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                   <p>通知人</p>
                 </td>
                  <td colspan="3" style="border-bottom-style:solid;border-bottom-color:#141414;">
                         <input v-model='form.bsNotify' rows="2" style="width:100%"></input>
                 </td>        
            </tr>
            <tr>
                <td colspan="8" height="20px">
                    <p></p>
                </td>
            </tr>
            <tr>
                <td colspan="8" height="20px">
                    <p></p>
                </td>
            </tr>
           <tr>
               <td colspan="2" height="40px" >
                  <p>（AIR）PORT OF LOADING</p>
                  <p>装运（空）港</p>
               </td>
               <td  colspan="2" height="40px">
                  <input type="text" style="height:40px;width:100%"  v-model="form.bsLoading"></input>
               </td>
               <td colspan="2" height="40px">
                  <p>（AIR）PORT OF DISCHARGE</p>
                  <p>卸货（空）港</p>
               </td>
               <td  colspan="2" height="40px">
                  <input type="text" style="height:40px;width:100%"  v-model="form.bsDischarge"></input>
               </td>
           </tr>
           <tr>
                <td colspan="2" height="40px" >
                   <p>FINAL DESTINATION</p>
                   <p>最终目的地</p>
                </td>
                <td  colspan="2" height="40px">
                   <input type="text" style="height:40px;width:100%"  v-model="form.bsDestination"></input>
                </td>
                <td colspan="2" height="40px">
                   <p>CARGO READINESS</p>
                   <p>货物完成时间</p>
                </td>
                <td  colspan="2" height="40px">
                   <input type="text" style="height:40px;width:100%"  v-model="form.bsReadiness"></input>
                </td>
            </tr>
             <tr>
                <td colspan="2" height="40px" >
                   <p>E.T.S.  (Booking Request Date)</p>
                   <p>预计开航时间</p>
                </td>
                <td  colspan="2" height="40px">
                   <input type="text" style="height:40px;width:100%"  v-model="form.bsBookingRe"></input>
                </td>
                <td colspan="2" height="40px">
                   <p>E.T.A. REQUIRED</p>
                   <p>要求预计到港时间</p>
                </td>
                <td  colspan="2" height="40px">
                   <input type="text" style="height:40px;width:100%"  v-model="form.bsRequired"></input>
                </td>
            </tr>
            <tr>
                <td colspan="8" align=center>
                    <p style="font-weight:800">
                        <font >FREIGHT  运费</font>
                    </p>
                </td>
            </tr>
            <br>
            <tr>
                <td colspan="8" align=center>
                    <p style="font-weight:800">
                        <font >INCOTERMS 贸易条款</font>
                    </p>
                </td>
            </tr>
            <br>
            <tr>
                <td colspan="8" align=center>
                    <p style="font-weight:800">
                        <font >GOODS DETAILS  出货明细</font>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                   <p>DESCRIPTION</p>
                </td>
                <td  colspan="6" >
                   <input type="text" style="width:100%"  v-model="form.bsDesriptionEnglish"></input>
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                   <p>货物描述</p>
                </td>
                <td  colspan="6" >
                   <input type="text" style="width:100%"  v-model="form.bsDesriptionChinese"></input>
                </td>
            </tr>
            <tr>
                <td colspan="8" height="20px">
                    <p></p>
                </td>
            </tr>
          <tr>
                <td colspan="2">
                   <p>MARKS</p>
                 </td>
                  <td colspan="2">
                    <p>PACKAGE & PCS</p>
                 </td>
                  <td colspan="1">
                   <p>G.W.</p>
                 </td>
                  <td colspan="3">
                    <p>MEASUREMENT</p>  
                 </td>        
            </tr>
             <tr>
                <td colspan="2">
                   <p>唛头</p>
                 </td>
                  <td colspan="2">
                    <p>件数包装</p>
                 </td>
                  <td colspan="1">
                   <p>毛重</p>
                 </td>
                  <td colspan="3">
                    <p>体积</p>  
                 </td>        
            </tr>
            <tr>
                <td colspan="2">
                        <input type="text" style="width:100%"  v-model="form.bsMarks"></input>
                 </td>
                  <td colspan="2">
                        <input type="text" style="width:100%"  v-model="form.bsPakage"></input>
                 </td>
                  <td colspan="1">
                        <input type="text" style="width:100%"  v-model="form.bsGw"></input>
                 </td>
                  <td colspan="3">
                        <input type="text" style="width:100%"  v-model="form.bsMeasurement"></input>
                 </td>        
            </tr>
            <tr>
                <td colspan="8" align=center>
                    <p style="font-weight:800">
                        <font >CONTAINERS  集装箱</font>
                    </p>
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
        bscustomerRefNum: "",
        bsShipper: "",
        bsConsigneeReciver:'',
        bsConsigneePlantCode:'',
        bsShipperContact:'',
        bsConsigneeContact:'',
        bsShipperEmail:'',
        bsConsigneeEmail:'',
        bsShipperPhone:'',
        bsConsigneePhone:'',
        bsNotify:'',
        bsOrder:'',
        bsLoading:'',
        bsDischarge:'',
        bsDestination:'',
        bsReadiness:'',
        bsBookingRe:'',
        bsRequired:'',
        bsDesriptionEnglish:'',
        bsDesriptionChinese:'',
        bsMarks:'',
        bsPakage:'',
        bsGw:'',
        bsMeasurement:''
       
        
      }
    };
  }, //data
  methods: {
    loadContextData: function() {
      this.api.lmp.booking
        .getContextData({
          customer: this.customer,
          agent: this.agent,
          cabinets: this.cabinets
        })
        .then(res => {
          if (res.result) {
            //保留原数据
            this.contentData =JSON.parse(JSON.stringify(res.data));
            let formData = res.data;
            let cabinets = [];
            //转换成html能解析的
            formData.cabinets.forEach(ele => {
              ele.cabinetDetail.forEach((dele, index) => {
                cabinets.push({
                  partNo: dele.partNo,
                  qty: dele.qty,
                  cartonQty: dele.cartonQty,
                  partNoDesc: dele.partNoDesc,
                  loadingDate: dele.loadingDate,
                  firstRow: index == 0 ? true : false,
                  rowCount: ele.cabinetDetail.length,
                  volume: ele.cabinetVolume,
                  weight: ele.cabinetWeight
                });
              });
            });
            formData.cabinets = cabinets;
            this.form = formData;
            console.log(this.form);
          }
        });
    }, //loadContextData
    getFormData: function() {
      let validSuccess = true;
      if (this.form.receipt == undefined || this.form.receipt == "") {
        this.$Message.error("请选择收货地点");
        validSuccess = false;
      }
      if (this.form.delivery == undefined || this.form.delivery == "") {
        this.$Message.error("请选择目的地");
        validSuccess = false;
      }
      if (this.form.invoiceCode == undefined || this.form.invoiceCode == "") {
        this.$Message.error("请输入发票号");
        validSuccess = false;
      }
      this.$emit("validate", validSuccess);
      if (validSuccess == false) return;
      this.contentData.receipt = this.form.receipt;
      this.contentData.delivery = this.form.delivery;
      this.contentData.invoiceCode = this.form.invoiceCode;
      return this.contentData;
    } //getFormData
  }, //methods
  computed: {
    total() {
      let count = 0;
      this.cabinets.forEach(element => {
        count += element.qty;
      });
      return count;
    }
  }, //computed
  watch: {}, //watch
  mounted() {
    //初始化
    this.loadContextData();
  } //mounted
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
