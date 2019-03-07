<template>
    <form>
        <table class="formed" align="left" valign="left">
            <tr>
                <td colspan="8" align=center style='height:40px;font-weight:700;font-size:25px'>
                        SHIPPER LETTER OF INSTRUCTION (SI)
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <p>
                        <u>Shipper 's name & address:</u>
                    </p>
                </td>
                <td colspan="1">
                        <nobr>
                            KC code::<input v-model='form.shapperKCcode' style="width:85px"></input>
                        </nobr>
                </td>
                <td colspan="4" class="td2">
                    <p>
                        <u>Damco China Limited</u>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                   <nobr>
                    <input v-model='shipperName' style="width:100%"></input>
                    </nobr>
                </td>
                <td colspan="1">
                    <nobr>
                       Email:<input v-model='form.shipperEmail' style="width:105px"></input>
                    </nobr>
                </td>
                <td colspan='4'  style="border-bottom-style:none">
                    <p>
                       33/F Golden Business Center, No. 2028 Shennan Dong Road, Shenzhen
                    </p>               
                </td>
            </tr>
            <tr>
                <td colspan="4" rowspan="3">
                    <textarea readonly="true" rows="3" v-model="form.shipperAddress"></textarea>
                </td>
                <td colspan="4" rowspan="1"  style="border-bottom-style:none">
                        <p>深圳罗湖区深南东路2028号罗湖商务中心33楼</p>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="border-bottom-style:none">
                    <p>TEL:+ 86 0755- 25021601  Contact: Vivian</p>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="border-bottom-style:none">
                    <p>
                        <u>Office Hour: Mon-Fri, 09:00-18:00 (lunch 12:00-13:30)</u>
                    </p>
                </td>
            </tr>
            <tr >
                <td colspan="1"  style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                       TEL:<input v-model='form.shipperTel' style='width:150px'> 
                    </nobr>
                </td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                   <nobr>
                       Ctc:<input v-model='form.shipperCtc' style='width:150px'> 
                    </nobr>
                </td>
                <td colspan="2" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                       Fax:<input v-model='form.shipperFax' style='width:150px'> 
                    </nobr>
                </td>
                <td colspan="4" style="border-bottom-style:solid;border-bottom-color:#141414;">

                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <p>Consignee's name & address:</p>
                </td>
                <td colspan="4">
                    <p>Type of service required:</p>
                </td>
            </tr>
            <tr>
                <td colspan="4" rowspan="1">
                    <input v-model='form.consigneeName' style="width:100%"></input>
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4"  >
                        <textarea rows="3" v-model='form.consigneeAddress'></textarea>
                </td>
                <td colspan="4" rowspan="1" ></td>
            </tr>
            <tr>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                  <nobr>
                        Tel:<input type="text"  v-model='form.consigneeTel' ></input>
                  </nobr>
                </td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;"></td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                  <nobr>
                        Ctc:<input type="text" v-model='form.consigneeCtc' ></input>
                 </nobr>
                </td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;"></td>
                <td colspan="4" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                            Amount to be insured / currency:<input v-model='form.consigneeAmount'  style="width:100%">
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    Notify party:
                </td>
                <td colspan="2">
                    Airline counter sign required:
                </td>
                <td colspan="2">
                    Export declaration required:
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <input v-model='form.notifyPartyName'  style="width:100%"></input>
                </td>
                <td colspan="4" ></td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea v-model='form.notifyPartyAddress'  rows="3"></textarea>
                </td>
                <td colspan="4" ></td>
            </tr>
            <tr>
                <td colspan="4" >
                    <nobr>
                     Tel:<input type="notifyPartyTel" v-model='form.notifyPartyTel'>
                    </nobr>
                </td>
                <td colspan="4" ></td>
            </tr>
             <tr>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                     Fax:<input type="text" v-model='form.notifyPartyFax'>
                    </nobr>
                </td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;"></td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                     Ctc:<input type="text" v-model='form.notifyPartyCtc'>
                    </nobr>
                </td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;"></td>
                <td colspan="4" style="border-bottom-style:solid;border-bottom-color:#141414;"></td>
            </tr>
            <tr>
                <td colspan="2">
                        Airport of departure (出發港):
                </td>
                <td colspan="2">
                        Airport of destination (目的港):
                </td>
                <td colspan="2">
                        Declared value for carriage
                </td>
                <td colspan="2">
                        Declared value for customs:
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input v-model='departure'>
                 </td>
                <td colspan="2">
                    <input v-model='destination'>
                </td>
                <td colspan="2">
                    <input v-model='carriage'>
                </td>
                <td colspan="2">
                    <input v-model='customs'>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                        Air freight charges
                </td>
                <td colspan="2">
                        Local charges (CN)
                </td>
                <td colspan="2">
                        MAWB or HAWB No.:
                </td>
                <td colspan="2">
                        Booking No (SO入倉號):
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input v-model='charges'>
                </td>
                <td colspan="2">
                    <input v-model='localCharges'>
                </td>
                <td colspan="2">
                    <input v-model='No'>
                </td>
                <td colspan="2">
                     <input v-model='bookingNo'> 
                </td>
            </tr>
            <tr>
                    <td colspan="2">
                        <input v-model='bsName' style="background-color:rgb(255, 250, 250);border:0px" disabled="disabled">
                    </td>
                    <td colspan="2">
                        <input v-model='bsName'style="background-color:rgb(255, 250, 250);border:0px" disabled="disabled">
                    </td>
                    <td colspan="2">
                        <input v-model='bsName'style="background-color:rgb(255, 250, 250);border:0px" disabled="disabled">
                    </td>
                    <td colspan="2" style="color:red">
                            *請帶此SI 連SO交倉
                    </td>
                </tr>
            <tr>
                <td colspan="2" bgcolor='#46A3FF' >
                        Shipping Mark
                </td>
                <td colspan="2" bgcolor='#46A3FF'>
                        Commodity (with HS code if any)
                </td>
                <td colspan="1" bgcolor='#46A3FF'>
                        Order No/PO (If any)
                </td>
                <td colspan="1" bgcolor='#46A3FF'>
                        Carton No
               </td>
               <td colspan="1" bgcolor='#46A3FF'>
                    Carton No
               </td>
               <td colspan="1" bgcolor='#46A3FF'>
                    Carton No
               </td>
            </tr>
            <tr></tr>
            <tr>
                <td colspan="2" >
                    <input v-model='bsName'>
                </td>
                <td colspan="2">
                        <input v-model='bsName'>
                    </td>
                <td colspan="1">
                    <input v-model='bsName' style="width:100%">
                </td>
                <td colspan="1">
                        <input v-model='bsName'>
                </td>
                <td colspan="1">
                    <input v-model='bsName'>
                </td>
                <td colspan="1">
                    <input v-model='bsName'>
                </td>
            </tr>
            <tr>
                <td colspan="4" bgcolor='#46A3FF'>

                </td>
                <td colspan="1">
                    <nobr>
                            Total Carton:<input v-model='bsName' style="width:190px">
                    </nobr>
                </td>
                <td colspan="2" bgcolor='#46A3FF'>
                     <nobr>
                            Total Gwt/Vol wt:
                    </nobr>
                </td>
                <td colspan="1">
                        <input v-model='bsName' style="background-color:#46A3FF;width:100%">
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
        shipperKCcode:"",
        shipperName: "",
        shipperAddress: "",
        shipperEmail:"",
        shipperTel:'',
        shipperCtc:'',
        shipperFax:'',
        consigneeName: "",
        consigneeAddress: "",
        consigneeAmount: "",
        consigneeTel:"",
        consigneeCtc:'',
        notifyPartyName:'',
        notifyPartyAddress:'',
        notifyPartyCtc:'',
        notifyPartyFax:'',
        notifyPartyTel:'',
        departure:'',
        destination :'',
        carriage:'',
        customs:'',
        charges:'',
        localCharges:'',
        No:'',
        bookingNo:'',

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
            //console.log(this.form);
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
