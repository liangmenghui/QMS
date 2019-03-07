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
                        S/O No.(落货纸号）<input v-model='content.bsSoNo' style='height:40px'>
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <nobr>
                        <textarea v-model='content.bsShipper' rows="3"></textarea>
                    </nobr>
                </td>
                <td colspan='4' style="border-bottom-style:none">

                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <nobr>
                        TEL:<input v-model='content.bsShipperTel' style='width:95%'>
                    </nobr>
                </td>
                <td colspan="4" style="border-bottom-style:none">
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspPIC: &nbsp&nbsp<input v-model='content.bsPic'>
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <p>Consignee 收货人</p>
                </td>
                <td colspan="4" style="border-bottom-style:none">
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspMail: &nbsp<input v-model='content.bsMail'>
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="border-bottom-style:none">
                    <textarea v-model='content.bsConsignee' rows="3"></textarea>
                </td>
                <td colspan="4" style="border-bottom-style:none">
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspTel: &nbsp&nbsp&nbsp<input v-model='content.bsTel'>
                    </nobr>
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspFax: &nbsp <input v-model='content.bsFax'>
                    </nobr>
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspDOC: <input v-model='content.bsDoc'>
                    </nobr>
                </td>
            </tr>
            <tr>
                <td colspan="4">

                </td>
                <td colspan="4" style="border-bottom-style:none">
                    <nobr>
                        &nbsp&nbsp&nbsp&nbsp&nbsp&nbspTel:&nbsp&nbsp&nbsp <input v-model='content.bsTel2' style='width:100%'>
                    </nobr>
                </td>
            </tr>

            <tr>
                <td colspan="2" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        TEL:<input v-model='content.bsConsigneeTel' style='width:220px'>
                    </nobr>
                </td>
                <td colspan="2" style="border-bottom-style:solid;border-bottom-color:#141414;">
                    <nobr>
                        Fax:<input v-model='content.bsConsigneeFax' style='width:250px'>
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
                    <textarea rows="4" v-model='content.notifyPartyName'></textarea>
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
                    <input v-model='content.bsPreCarryage' />
                </td>
                <td colspan="2">
                    <input v-model='content.bsReceipt' />
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
                    <input v-model='content.bsVessel' />
                </td>
                <td colspan="2">
                    <input v-model='content.bsLoading' />
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
                    <input v-model='content.bsDischarge' />
                </td>
                <td colspan="2">
                    <input v-model='content.bsDelivery' />
                </td>
                <td colspan="4">
                </td>
            </tr>
            <tr>
                <td colspan="2" align=center>
                    <p>Marks and numbers</p>
                    <p>(箱 唛)</p>
                </td>
                <td colspan="1" align=center>
                    <p>CTNS/PKGS</p>
                    <p>（包装种类/箱数）</p>
                </td>
                <td colspan="3" align=center>
                    <p> Description of Goods：</p>
                    <p>(中英文货品名)</p>
                </td>
                <td colspan="1" align=center>
                    <p>G.W.</p>
                    <p>(毛重/公斤)</p>
                </td>
                <td colspan="1" align=center>
                    <p>CBM</p>
                    <p>(尺码/立方米)</p>
                </td>
            </tr>
            <tr v-for="item in detail" :key="item.index">
                <td colspan="2">
                    <input v-model='item.summary.bsExt0' />
                </td>
                <td colspan="1">
                    <input v-model='item.summary.bsExt1' />
                </td>
                <td colspan="3">
                    <input v-model='item.summary.bsExt2' />
                </td>
                <td colspan="1">
                    <input v-model='item.summary.bsExt3' />
                </td>
                <td colspan="1">
                    <input v-model='item.summary.bsExt3' />
                </td>
            </tr>
            <tr>
                <td colspan="4" rowspan="2">
                    <p>
                        交仓地址：深圳盐田港后方16小区丹马士物流A仓（海关注册名：星辉仓)
                        <br> 仓库作业时间：周一至周六6：00-16：00 （节假日另行通知）
                        <br> 仓库电话：86-755-25218945 联系人：吴's/阿萍
                    </p>
                </td>
                <td colspan="2">
                    <p>是否需要拖车：</p>
                </td>
                <td colspan="2">
                    <p>H. S. Code （商品海关编码）：</p>
                </td>
            </tr>
            <tr>
                <td colspan="2"></td>
                <td colspan="2"><input v-model="content.bsHsCode" /></td>
            </tr>
            <tr>
                <td colspan="3">
                    <p>贸易条款Trade term agreed with consignee:</p>
                </td>
                <td colspan="3">
                    <p>必填 Cargo ready date（货物备好时间）：</p>
                </td>
                <td colspan="2">
                    <p>CFS Closing date（截仓时间）: </p>
                </td>
            </tr>
            <tr>
                <td colspan="3"></td>
                <td colspan="3">
                    <input v-model="content.bsShipDay" />
                </td>
                <td colspan="2"></td>
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
        bsCbm: "",
        bsCtns: "",
        bsLoading: "",
        bsDescription: "",
        bsGw: "",
        bsMarks: "",
        bsDischarge: "",
        bsDelivery: "",
        bsHsCode: "",
        bsShipDay: ""
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
