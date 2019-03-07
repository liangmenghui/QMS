<template>
    <form>
        <table class="formed" align="left" valign="left">
            <tr>
                <td colspan="8" align=left style='height:25px;font-size:10px'>
                    <font color=blue>
                        此单包含了散货的订舱单格式和收费标准，希望能对您有所帮助！
                    </font>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="height:40px">
                    <p>
                        <u>Shipper 发货人</u>
                    </p>
                </td>
                <td colspan="2">

                </td>
                <td colspan="2" class="td2">
                    <nobr>
                        S/O No.(落货纸号）<input v-model='form.bsSoNo' style='height:40px'>
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <nobr>
                        <textarea v-model='form.bsShipper' rows="3"></textarea>
                    </nobr>
                </td>
                <td colspan='4' style="border-bottom-style:none">

                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <nobr>
                        TEL:<input v-model='form.bsShipperTel' style='width:95%'>
                    </nobr>
                </td>
                <td colspan="4" style="border-bottom-style:none">
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspPIC: &nbsp&nbsp<input v-model='form.bsPic'>
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <p>Consignee 收货人</p>
                </td>
                <td colspan="4" style="border-bottom-style:none">
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspMail: &nbsp<input v-model='form.bsMail'>
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="border-bottom-style:none">
                    <textarea v-model='form.bsConsignee' rows="3"></textarea>
                </td>
                <td colspan="4" style="border-bottom-style:none">
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspTel: &nbsp&nbsp&nbsp<input v-model='form.bsTel'>
                    </nobr>
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspFax: &nbsp <input v-model='form.bsFax'>
                    </nobr>
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspDOC: <input v-model='form.bsDoc'>
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="4">

                </td>
                <td colspan="4" style="border-bottom-style:none">
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspTel:&nbsp&nbsp&nbsp <input v-model='form.bsTel2' style='width:100%'>
                    </nobr>
                </td>
            </tr>

            <tr>
                <td colspan="2" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        TEL:<input v-model='form.bsConsigneeTel' style='width:220px'>
                    </nobr>
                </td>
                <td colspan="2" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        Fax:<input v-model='form.bsConsigneeFax' style='width:250px'>
                    </nobr>
                </td>
                <td colspan="4" style="border-bottom-style:solid;border-bottom-color:#141414;">

                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <p>Notify party 通知人</p>
                </td>
                <td colspan="1">
                    <p>Sea Freight</p>
                </td>
                <td colspan="3">
                    <p>Service Type on </p>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <textarea rows="4" v-model='form.notifyPartyName'></textarea>
                </td>
                <td colspan="1">

                </td>
                <td colspan="3">

                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <p>Pre-Carriage by (前程运输)</p>
                </td>
                <td colspan="2">
                    <p>Place of Receipt(收货地点)</p>
                </td>
                <td colspan="2">
                    <p>取单地址（必选）：</p>
                </td>
                <td colspan="2">
                    <p>快递提单或发票(快递服务费RMB20/SET)</p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input v-model='form.bsPreCarryage' />
                </td>
                <td colspan="2">
                    <input v-model='form.bsReceipt' />
                </td>
                <td colspan="2">

                </td>
                <td colspan="2">

                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p>Vessel / Voy. (船名/航次)</p>
                </td>
                <td colspan="2">
                    <p>Port of Loading(装货港)</p>
                </td>
                <td colspan="4">
                    <p>付款币种（必选）：</p>
                </td>

            </tr>
            <tr>
                <td colspan="2">
                    <input v-model='form.bsVessel' />
                </td>
                <td colspan="2">
                    <input v-model='form.bsLoading' />
                </td>
                <td colspan="4">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p>Port of Discharge(卸货港)</p>
                </td>
                <td colspan="2">
                    <p>Place of Delivery(交货地)</p>
                </td>
                <td colspan="4">
                    <p>出货后需要出仓报关清单：</p>
                </td>

            </tr>
            <tr>
                <td colspan="2">
                    <input v-model='form.bsDischarge' />
                </td>
                <td colspan="2">
                    <input v-model='form.bsDelivery' />
                </td>
                <td colspan="4">
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
        bsShipper: "",
        bsSoNo: "",
        bsShipperTel: "",
        bsMail: "",
        bsPic: "",
        bsConsignee: "",
        bsTel: "",
        bsDoc: "",
        bsFax: "",
        bsTel2: "",
        bsConsigneeTel: "",
        bsConsigneeFax: "",
        notifyPartyName: "",
        bsPreCarryage: "",
        bsReceipt: "",
        bsVessel: "",
        bsLoading: "",
        bsDischarge: "",
        bsDelivery: ""
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
