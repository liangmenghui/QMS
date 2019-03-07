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
                        KC code::<input v-model='form.bsShapperKCcode' style="width:85px" />
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
                        <input v-model='form.bsShipperName' />
                    </nobr>
                </td>
                <td colspan="1">
                    <nobr>
                        Email:<input v-model='form.bsShipperEmail' style="width:105px" />
                    </nobr>
                </td>
                <td colspan='4' style="border-bottom-style:none">
                    <p>
                        33/F Golden Business Center, No. 2028 Shennan Dong Road, Shenzhen
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="4" rowspan="3">
                    <textarea rows="3" v-model="form.bsShipperAddress"></textarea>
                </td>
                <td colspan="4" rowspan="1" style="border-bottom-style:none">
                    <p>深圳罗湖区深南东路2028号罗湖商务中心33楼</p>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="border-bottom-style:none">
                    <p>TEL:+ 86 0755- 25021601 Contact: Vivian</p>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="border-bottom-style:none">
                    <p>
                        <u>Office Hour: Mon-Fri, 09:00-18:00 (lunch 12:00-13:30)</u>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        TEL:<input v-model='form.bsShipperTel' style='width:150px'>
                    </nobr>
                </td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        Ctc:<input v-model='form.bsShipperCtc' style='width:150px'>
                    </nobr>
                </td>
                <td colspan="2" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        Fax:<input v-model='form.bsShipperFax' style='width:150px'>
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
                    <input v-model='form.bsConsigneeName' />
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea rows="3" v-model='form.bsConsigneeAddress'></textarea>
                </td>
                <td colspan="4" rowspan="1"></td>
            </tr>
            <tr>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        Tel:<input type="text" v-model='form.bsConsigneeTel' />
                    </nobr>
                </td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;"></td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        Ctc:<input type="text" v-model='form.bsconsigneeCtc' />
                    </nobr>
                </td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;"></td>
                <td colspan="4" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        Amount to be insured / currency:<input v-model='form.bsConsigneeAmount' style="width:100%">
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
                    <input v-model='form.bsNotifyPartyName' />
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea v-model='form.bsNotifyPartyAddress' rows="3"></textarea>
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4">
                    <nobr>
                        Tel:<input type="notifyPartyTel" v-model='form.bsNotifyPartyTel'>
                    </nobr>
                </td>
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        Fax:<input type="text" v-model='form.bsNotifyPartyFax'>
                    </nobr>
                </td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;"></td>
                <td colspan="1" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        Ctc:<input type="text" v-model='form.bsNotifyPartyCtc'>
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
                    <input v-model='form.bsDeparture'>
                </td>
                <td colspan="2">
                    <input v-model='form.bsDestination'>
                </td>
                <td colspan="2">
                    <input v-model='form.bsCarriage'>
                </td>
                <td colspan="2">
                    <input v-model='form.bsCustoms'>
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

                </td>
                <td colspan="2">

                </td>
                <td colspan="2">
                    <input v-model='form.bsNo'>
                </td>
                <td colspan="2">
                    <input v-model='form.bsBookingNo'>
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
      form: {
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
  width: 96%;
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
