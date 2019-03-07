<template>
    <form>
        <table class="formed" align="left" valign="left">
            <tr>
                <td colspan="5">
                    <p>
                        <u>Shipper:</u>
                    </p>
                </td>
                <td colspan="5" rowspan="5">

                </td>
            </tr>
            <tr>
                <td colspan="5">
                    <textarea rows="4" v-model="content.shipper"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <p>PIC:</p>
                </td>
                <td><input type="text" v-model="content.shipperPic" /></td>
                <td>Tel:</td>
                <td><input type="text" v-model="content.shipperTel" /></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="5">
                    <p>
                        <u>Consignee (if 'order' state notify party)</u>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="5">
                    <textarea rows="4" v-model="content.consignee"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="5">
                    <p>
                        <u> Notify Party:</u>
                    </p>
                </td>
                <td colspan="5" style="text-align:center;">
                    <p>
                        <u>SHIPPING ORDER (OCEAN)</u>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="5">
                    <textarea rows="4" v-model="content.notify"></textarea>
                </td>
                <td colspan="5">

                </td>
            </tr>
            <tr>
                <td>
                    <p>
                        <u>Ocean Vessel/Voyage No.:</u>
                    </p>
                </td>
                <td colspan="2"><input type="text" v-model="content.vessel" /></td>
                <td>
                    <p>
                        <u>Port of loading:</u>
                    </p>
                </td>
                <td><input type="text" v-model="content.loading" /></td>
                <td colspan="5"></td>
            </tr>
            <tr>
                <td>
                    <p>
                        <u> Port of Discharge:</u>
                    </p>
                </td>
                <td colspan="2">
                    <input type="text" v-model="content.discharge" />
                </td>
                <td>
                    <p>
                        <u>Place of delivery:</u>
                    </p>
                </td>
                <td>
                    <input type="text" v-model="content.delivery" />
                </td>
                <td>
                    <p>
                        <u>Freight Payable at</u>
                    </p>
                </td>
                <td></td>
                <td colspan="2">
                    <p>
                        <u>Number of Original B/L required</u>
                    </p>
                </td>
                <td></td>
            </tr>
            <tr>
                <td colspan="10" style="text-align:center;">
                    <p>
                        <u>PARTICULARS FURNISHED BY SHIPPER</u>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p>
                        <u>Marks & Number</u>
                    </p>
                </td>
                <td>
                    <p>
                        <u>No. of Cntr or other pkg</u>
                    </p>
                </td>
                <td colspan="5">
                    <p>
                        <u>Description of Goods</u>
                    </p>
                </td>
                <td>
                    <p>
                        <u>Gross Weight</u>
                    </p>
                </td>
                <td>
                    <p>
                        <u>Measurement</u>
                    </p>
                </td>
            </tr>
            <tr v-for="item in detail" :key="item.index">
                <td colspan="2">
                    <p>
                        <u>{{item.summary.bsExt0}}</u>
                    </p>
                </td>
                <td>
                    <p>
                        <u>{{item.summary.bsExt1}}<br /></u>
                    </p>
                    <p>
                        <u>{{item.summary.bsContainer}}</u>
                    </p>
                </td>
                <td colspan="5">
                    <p>
                        <u>{{item.summary.bsExt4}}</u>
                    </p>
                </td>
                <td>
                    <p>
                        <u>{{item.summary.bsExt2}}</u>
                    </p>
                </td>
                <td>
                    <p>
                        <u>{{item.summary.bsExt3}}</u>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p>
                        <u>{{summary.mark}}</u>
                    </p>
                </td>
                <td>
                    <p>
                        <u>{{summary.boxQty}}</u>
                    </p>
                    <p>
                        <u>
                            {{summary.carton}}
                        </u>
                    </p>
                </td>
                <td colspan="5"></td>
                <td>
                    <p>
                        <u>
                            {{summary.weight}}
                        </u>
                    </p>
                </td>
                <td>
                    <p>
                        <u>
                            {{summary.volume}}
                        </u>
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
    },
    contentData: {
      type: [Array, Object],
      required: true
    }
  }, //props
  data() {
    return {
      content: {
        shipper: "",
        shipperPic: "",
        shipperTel: "",
        consignee: "",
        notify: "",
        vessel: "",
        loading: "",
        discharge: "",
        delivery: ""
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
  width: 140px;
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
  background-color: #e1e2e3;
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
