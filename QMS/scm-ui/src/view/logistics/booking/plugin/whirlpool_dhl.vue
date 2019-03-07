<template>
  <form>
    <table class="form" align="left" valign="left">
      <tr>
        <td>
          <p>
            <u>Shipper /Exporter (Name & Address)</u>
          </p>
        </td>
        <td>
          <p>
            <u>Manufacturer (Name & Address)</u>
          </p>
        </td>
        <td>
          <p>
            <u>Notify3 (Name & Address)</u>
          </p>
        </td>
      </tr>
      <tr>
        <td><input type="text" v-model="form.shipperName" class="f-input f-blue" /></td>
        <td><input type="text" v-model="form.manuName" class="f-input f-blue" /></td>
        <td>
          <select v-model="form.notify3Name" class="f-input f-blue">
            <option v-for="item in notify3Names" :key="item.name" :value="item.name" >{{item.desc}}</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <textarea rows="5" class="f-input f-blue" v-model="form.shipperAddress"></textarea>
        </td>
        <td>
          <textarea rows="5" v-model="form.manuAddress" class="f-input f-blue"></textarea>
        </td>
        <td>
          <textarea rows="5" v-model="form.notify3Address" class="f-input"></textarea>
        </td>
      </tr>
      <tr>
        <td class="sperator">
          <p>
            <u>Consignee (Name & Address)</u>
          </p>
        </td>
        <td class="sperator">
          <p>
            <u>Final Destination/Ship To (Name & Address)</u>
          </p>
        </td>
        <td class="sperator" rowspan="10" align="left" valign="left">
          <table>
            <tr>
              <td class="sperator">
                <p>
                  <u>Freight Payment Terms</u>
                </p>
              </td>
            </tr>
            <tr>
              <td>
                <div class="f-blue">
                  <label for="payment_prepaid"><input id="payment_prepaid" type="checkbox" value="prepaid" v-model="payments" name="form.payment" />Pre-Paid</label>
                  <label for="payment_collect"><input id="payment_collect" type="checkbox" value="collect" v-model="payments" name="form.payment" />Collect</label>
                </div>
              </td>
            </tr>
            <tr>
              <td class="sperator">
                <p>
                  <u>Required Documents</u>
                </p>
              </td>
            </tr>
            <tr>
              <td>
                <div class="f-blue">
                  <label for="document_fcr"><input id="document_fcr" type="checkbox" name="form.document" value="fcr" v-model="documents" />FCR</label>
                  <label for="document_sea"><input id="document_sea" type="checkbox" name="form.document" value="seaWayBill" v-model="documents" />Sea Waybill</label>
                  <label for="document_air"><input id="document_air" type="checkbox" name="form.document" value="airHAWB" v-model="documents" />Air HAWB</label>
                  <label for="document_origin"><input id="document_origin" type="checkbox" name="form.document" value="original" v-model="documents" />Original B/L</label>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <p>
                  <u>Incoterms and Location</u>
                </p>
              </td>
            </tr>
            <tr>
              <td>
                <select v-model="form.consigneeName" class="f-input f-blue" />
              </td>
            </tr>
            <tr>
              <td>
                <p>
                  <u>Special Documents</u>
                </p>
              </td>
            </tr>
            <tr>
              <td>
                <div>
                  <label for="specialDoc_credit" style="display:block;"><input id="specialDoc_credit" type="checkbox" name="form-specialDoc" value="credit" v-model="specialDocs" />Letter of Credit</label>
                  <label for="specialDoc_packaging" style="display:block;"><input id="specialDoc_packaging" type="checkbox" name="form-specialDoc" value="packaging" v-model="specialDocs" />Wood Packaging Materials</label>
                  <label for="specialDoc_certificate" style="display:block;"><input id="specialDoc_certificate" type="checkbox" name="form-specialDoc" value="certificate" v-model="specialDocs" />Other Certificate</label>
                </div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>
          <select v-model="form.consigneeName" class="f-input f-blue">
            <option v-for="item in consigneeNames" :key="item.name"  :value="item.name">{{item.desc}}</option>
          </select>
        </td>
        <td><input type="text" v-model="form.destinationName" class="f-input f-blue" /></td>
      </tr>
      <tr>
        <td>
          <textarea rows="5" v-model="form.consigneeAddress" class="f-input"></textarea>
        </td>
        <td>
          <textarea rows="5" v-model="form.destinationAddress" class="f-input"></textarea>
        </td>
      </tr>
      <tr>
        <td class="sperator">
          <p>
            <u>Notify 1 (Name & Address)</u>
          </p>
        </td>
        <td class="sperator">
          <p>
            <u>Notify 2 (Name & Address)</u>
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <select v-model="form.notify1Name" class="f-input f-blue">
            <option v-for="item in notify1Names" :key="item.name"  :value="item.name" >{{item.desc}}</option>
          </select>
        </td>
        <td>
          <select v-model="form.notify2Name" class="f-input f-blue">
            <option v-for="item in notify2Names" :key="item.name"  :value="item.name" >{{item.desc}}</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>
          <textarea rows="5" v-model="form.notify1Address" class="f-input"></textarea>
        </td>
        <td>
          <textarea rows="5" v-model="form.notify2Address" class="f-input"></textarea>
        </td>
      </tr>
      <tr>
        <td class="sperator">
          <p>
            <u>Vessel/Voyage&nbsp;-&nbsp;
              <p>per schedule provided by KN</p>
            </u>
          </p>
        </td>
        <td class="sperator">
          <p>
            <u>Place Of Receipt(Origin)</u>
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <textarea rows="2" v-model="form.vessel" class="f-input"></textarea>
        </td>
        <td>
          <textarea rows="2" v-model="form.placeOfReceipt" class="f-input f-blue"></textarea>
        </td>
      </tr>
      <tr>
        <td class="sperator">
          <p>
            <u>Port Of Loading (Origin)</u>
          </p>
        </td>
        <td class="sperator">
          <p>
            <u>Port Of Discharge (Destination)</u>
          </p>
        </td>
      </tr>
      <tr>
        <td>
          <textarea rows="2" v-model="form.portOfLoading" class="f-input f-blue"></textarea>
        </td>
        <td>
          <textarea rows="2" v-model="form.portOfDischarge" class="f-input"></textarea>
        </td>
      </tr>
    </table>
  </form>
</template>

<script>
import Vue from "vue";
export default {
  data() {
    return {
      notify1Names: [],
      notify2Names: [],
      notify3Names: [],
      consigneeNames: [],
      payments: [],
      documents: [],
      specialDocs: [],
      form: {
        agent: "",
        customer: "",
        shipperName: "",
        shipperAddress: "",
        manuName: "",
        manuAddress: "",
        notify3Name: "",
        notify3Address: "",
        destinationName: "",
        consigneeName: "",
        consigneeAddress: "",
        destinationAddress: "",
        notify1Name: "",
        notify1Address: "",
        notify2Name: "",
        notify2Adress: "",
        vessel: "",
        placeOfReceipt: "",
        portOfLoading: "",
        portOfDischarge: "",
        payment: "",
        document: "",
        specialDoc: ""
      }
    };
  }, //data
  methods: {
    loadConsigneeNames: function() {
      this.api.lmp.booking
        .getOptionsByKey({
          customer: this.form.customer,
          agent: this.form.agent,
          key: "lmp_booking_pagecontext_consignee"
        })
        .then(res => {
          if (res.result) {
            let list = [{name:"",desc:"请选择"}];
            let defaultVal = "";
            res.data.forEach(element => {
              list.push({
                name: element.bsDec,
                desc: element.bsName
              });
              if (element.bsIsDefault == 0) {
                defaultVal = element.bsName;
              }
            });
            this.consigneeNames = list;
            this.form.consigneeName = defaultVal;
          }
        });
    },
    loadNotify1Names: function() {
      this.api.lmp.booking
        .getOptionsByKey({
          customer: this.form.customer,
          agent: this.form.agent,
          key: "lmp_booking_pagecontext_notify1"
        })
        .then(res => {
          if (res.result) {
            let list = [{name:"",desc:"请选择"}];
            let defaultVal = "";
            res.data.forEach(element => {
              list.push({
                name: element.bsDec,
                desc: element.bsName
              });
              if (element.bsIsDefault == 0) {
                defaultVal = element.bsName;
              }
            });
            this.notify1Names = list;
            this.form.notify1Name = defaultVal;
          }
        });
    },
    loadNotify2Names: function() {
      this.api.lmp.booking
        .getOptionsByKey({
          customer: this.form.customer,
          agent: this.form.agent,
          key: "lmp_booking_pagecontext_notify2"
        })
        .then(res => {
          if (res.result) {
            let list = [{name:"",desc:"请选择"}];
            let defaultVal = "";
            res.data.forEach(element => {
              list.push({
                name: element.bsDec,
                desc: element.bsName
              });
              if (element.bsIsDefault == 0) {
                defaultVal = element.bsName;
              }
            });
            this.notify2Names = list;
            this.form.notify2Name = defaultVal;
          }
        });
    },
    loadNotify3Names: function() {
      this.api.lmp.booking
        .getOptionsByKey({
          customer: this.form.customer,
          agent: this.form.agent,
          key: "lmp_booking_pagecontext_notify3"
        })
        .then(res => {
          if (res.result) {
            let list = [{name:"",desc:"请选择"}];
            let defaultVal = "";
            res.data.forEach(element => {
              list.push({
                name: element.bsDec,
                desc: element.bsName
              });
              if (element.bsIsDefault == 0) {
                defaultVal = element.bsName;
              }
            });
            this.notify3Names = list;
            this.form.notify3Name = defaultVal;
          }
        });
    }
  }, //methods
  computed: {
    commitData(){
      //提交数据
      return this.$store.state.eable;
    }

  }, //computed
  watch: {
    //模拟实现单选
    documents() {
      let length = this.documents.length;
      let currVal = this.form.document;
      if (length > 1) {
        let checked = [];
        let checkedItem = this.documents[this.documents.length - 1];
        checked.push(checkedItem);
        this.documents = checked;
      } else if (length == 0) {
        this.documents.push(currVal);
      }
      this.form.document = this.documents[0];
    },
    payments() {
      let length = this.payments.length;
      let currVal = this.form.payment;
      if (length > 1) {
        let checked = [];
        let checkedItem = this.payments[this.payments.length - 1];
        checked.push(checkedItem);
        this.payments = checked;
      } else if (length == 0) {
        this.payments.push(currVal);
      }
      this.form.payment = this.payments[0];
    },
    specialDocs() {
      let length = this.specialDocs.length;
      let currVal = this.form.specialDoc;
      if (length > 1) {
        let checked = [];
        let checkedItem = this.specialDocs[this.specialDocs.length - 1];
        checked.push(checkedItem);
        this.specialDocs = checked;
      } else if (length == 0) {
        this.specialDocs.push(currVal);
      }
      this.form.specialDoc = this.specialDocs[0];
    },
    commitData(){
        let commitState = this.$store.state.eable;
        if(commitState == 1){ //0 非提交，1 提交
          this.$store.state.data = this.form;
        }
    }
  }, //watch
  mounted() {
    //初始化
    this.documents.push("fcr");
    this.payments.push("prepaid");
    this.specialDocs.push("credit");
    this.form.customer = this.$route.params.customer;
    this.form.agent = this.$route.params.agent;
    this.loadConsigneeNames();
    this.loadNotify1Names();
    this.loadNotify2Names();
    this.loadNotify3Names();
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

.form {
  margin: 6px 10px;
  border: 2px solid #000;
  border-right: 0;
  overflow: hidden;
}
.form > tr {
  padding: 0px;
}
.form > tr > td {
  border: 0;
  border-right: 2px solid #000;
  padding: 0px;
  width: 360px;
}

.form > tr:last-child > td {
  border-bottom: 0px;
}
.form > tr > .sperator {
  border-top: 2px solid #000;
}

.form table {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
.form p {
  font-size: 12px;
  font-weight: bold;
  margin: 3px 5px;
}
.form u > p {
  font-size: 10px;
  display: inline;
  margin: -1px;
}
.f-input {
  width: 100%;
  border: 0px;
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
