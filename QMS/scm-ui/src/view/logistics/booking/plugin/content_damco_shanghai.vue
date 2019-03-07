<template>
    <form>
        <table class="formed" align="left" valign="left">
            <tr>
                <td colspan="8" align=center> SHIPPER LETTER OF INSTRUCTION (SI)</td>
            </tr>
            <tr>
                <td colspan="2" rowspan="2">
                    <p>
                        <u>Shipper 's name & address:</u>
                    </p>
                    <input v-model='content.bsShipperName' />
                </td>
                <td colspan="2">
                    <nobr>KC code:<input v-model='content.bsShapperKCcode' /></nobr>
                </td>
                <td colspan="4">
                    <p>
                        <u>Damco China Limited</u>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <nobr>Email:<input v-model='content.bsShipperEmail' /></nobr>
                </td>
                <td colspan='4' rowspan="2">
                    <p>
                        33/F Golden Business Center, No. 2028 Shennan Dong Road, Shenzhen
                        <br> 深圳罗湖区深南东路2028号罗湖商务中心33楼
                        <br> TEL:+ 86 0755- 25021601 Contact: Vivian
                        <br> Office Hour: Mon-Fri, 09:00-18:00 (lunch 12:00-13:30)
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea rows="3" v-model="content.bsShipperAddress"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <nobr>TEL:<input v-model='content.bsShipperTel' /></nobr>
                </td>
                <td colspan="2">
                    <nobr>Ctc:<input v-model='content.bsShipperCtc' /></nobr>
                </td>
                <td>
                    <nobr>Fax:<input v-model='content.bsShipperFax' /></nobr>
                </td>
                <td colspan="4"></td>
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
                <td colspan="4">
                    <input v-model='content.bsConsigneeName' />
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea rows="3" v-model='content.bsConsigneeAddress'></textarea>
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <nobr>Tel:<input type="text" v-model='content.bsConsigneeTel' /></nobr>
                </td>
                <td colspan="2">
                    <nobr>Ctc:<input type="text" v-model='content.bsconsigneeCtc' /></nobr>
                </td>
                <td colspan="4">
                    <nobr>Amount to be insured / currency:<input v-model='content.bsConsigneeAmount' /></nobr>
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
                    <input v-model='content.bsNotifyPartyName' />
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea v-model='content.bsNotifyPartyAddress' rows="3"></textarea>
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4">
                    <nobr>Tel:<input type="notifyPartyTel" v-model='content.bsNotifyPartyTel'></nobr>
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td>
                    <nobr>Fax:<input type="text" v-model='content.bsNotifyPartyFax'></nobr>
                </td>
                <td></td>
                <td>
                    <nobr>Ctc:<input type="text" v-model='content.bsNotifyPartyCtc'></nobr>
                </td>
                <td></td>
                <td colspan="4"></td>
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
                    <input v-model='content.bsDeparture'>
                </td>
                <td colspan="2">
                    <input v-model='content.bsDestination'>
                </td>
                <td colspan="2">
                    <input v-model='content.bsCarriage'>
                </td>
                <td colspan="2">
                    <input v-model='content.bsCustoms'>
                </td>
            </tr>
            <tr>
                <td colspan="2">Air freight charges</td>
                <td colspan="2">Local charges (CN)</td>
                <td colspan="2">MAWB or HAWB No.:</td>
                <td colspan="2">Booking No (SO入倉號):</td>
            </tr>
            <tr>
                <td colspan="2"></td>
                <td colspan="2"></td>
                <td colspan="2"><input v-model='content.bsNo' /></td>
                <td colspan="2"><input v-model='content.bsBookingNo' /></td>
            </tr>
            <tr>
                <td colspan="2"></td>
                <td colspan="2"></td>
                <td colspan="2"></td>
                <td colspan="2">*請帶此SI 連SO交倉</td>
            </tr>
            <tr>
                <td colspan="2">Shipping Mark</td>
                <td colspan="2">Commodity (with HS code if any)</td>
                <td>Order No/PO (If any)</td>
                <td>Carton No</td>
                <td>Gross Weight</td>
                <td>Dimension</td>
            </tr>
            <tr v-for="item in detail" :key="item.index">
                <td colspan="2">
                    <input v-model='item.summary.bsExt0' />
                </td>
                <td colspan="2">
                    <input v-model='item.summary.bsExt1' />
                </td>
                <td>
                    <input v-model='item.summary.bsExt2' />
                </td>
                <td>
                    <input v-model='item.summary.bsExt3' />
                </td>
                <td>
                    <input v-model='item.summary.bsExt4' />
                </td>
                <td>
                    <input v-model='item.summary.bsExt5' />
                </td>
            </tr>
            <tr>
                <td colspan="4"></td>
                <td colspan="2">
                    Total Carton:<input v-model='summary.boxQty' />
                </td>
                <td colspan="2">
                    Total Gwt/Vol wt:
                    <div>
                        <input v-model='summary.weight' readonly="true" style="width:45%;float:left;">
                        <p style="width:1%;float:left;margin-left:5px;margin-right:10px;">/</p><input v-model="summary.volume" readonly="true" style="width:45%;float:left;" />
                    </div>
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
      content: {
        bsShipperKCcode: "",
        bsShipperName: "",
        bsShipperAddress: "",
        bsShipperEmail: "",
        bsShipperTel: "",
        bsShipperCtc: "",
        bsShipperFax: "",
        bsConsigneeName: "",
        bsConsigneeAddress: "",
        bsConsigneeAmount: "",
        bsConsigneeTel: "",
        bsConsigneeCtc: "",
        bsNotifyPartyName: "",
        bsNotifyPartyAddress: "",
        bsNotifyPartyCtc: "",
        bsNotifyPartyFax: "",
        bsNotifyPartyTel: "",
        bsDeparture: "",
        bsDestination: "",
        bsCarriage: "",
        bsCustoms: "",
        bsNo: "",
        bsBookingNo: ""
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
}
.td2 {
  font-weight: bold;
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
  border-top: 2px solid rgb(46, 13, 13);
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
  border: 1px solid #c1c1c3;
  outline: none;
  width: 100%;
  border-radius: 3px;
}
.formed nobr {
  display: flex;
}
.formed input {
  background-color: #f8f9fa;
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
