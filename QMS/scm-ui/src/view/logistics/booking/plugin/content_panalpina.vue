<template>
        <form>
            <table class="formed" align="left" valign="left">
                <tr>
                    <td colspan="2" style="font-weight:700;height:30px">
                        <p>Shipper's Reference(发货人参考号)</p>
                    </td>
                     <td colspan="2" style="font-weight:700;height:30px">
                        <p>Shipper's Panalpina#(发货人的泛亚班拿RAR号码)</p>
                     </td>
                      <td colspan="2" style="font-weight:700;height:30px">
                        <p>Reserver HAWB#泛亚班拿提单号</p>
                     </td>
                     <td colspan="2" style="font-weight:700;height:30px">
                        <textarea rows="2" v-model='content.bsReserver'></textarea>
                     </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input v-model='bsShipperReference'>
                    </td>
                    <td colspan="2">
                        <input v-model='content.bsShipperPana'>
                    </td>
                    <td colspan="4">
                        <p></p>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        Shipper(Full Name and Address)发货人(完整公司名称及地址)
                    </td>
                    <td colspan="4">
                        <p></p>
                    </td>
                </tr>
                <tr>
                    <td colspan="1" style="width:150px">
                            公司名称 
                    </td>
                    <td colspan="3">
                            <input style='width:100%' v-model='content.bsCompany'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">            
                           Building 大厦 
                    </td>
                    <td colspan="3">
                            <input style='width:100%' v-model='content.bsBuilding'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">   
                            Street 街道
                    </td>
                    <td colspan="3">
                            <input style="width:100%" v-model='content.bsStreet'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1" style="width:150px">
                        <nobr>
                            ZIP code 邮政编码  
                        </nobr>
                    </td>
                    <td colspan="1">
                            <input style='width:100%' v-model='content.bsZipCode'>
                    </td>
                    <td colspan="1">
                        <nobr>
                          City 城市<input style='width:80%' v-model='content.bsCity'>
                        </nobr>
                    </td>
                    <td colspan="1">
                        <nobr>
                          State州 <input style='width:77%' v-model='content.bsState'>
                        </nobr>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">
                            Country 国家  
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsCountry'>
                    </td>
                    <td colspan="1">
                            Contact联系人
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsContact'> 
                    </td>
                </tr>
                <tr>
                    <td colspan="1" style="border-bottom-style:solid ;border-bottom-color:#4c4eda;border-bottom-width:thin">
                            Phone 电话   
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsPhone'> 
                    </td>
                    <td colspan="1">
                            Email电邮/Fax传真
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsEmail'> 
                    </td>
                </tr>
                <tr>
                        <td colspan="4" >
                            Consignee收货人(Please provider notify party if To Order 如"To Order",请注明通知方)
                        </td>
                        <td colspan="4">
                            <p></p>
                        </td>
                    </tr>
                    <tr>
                    <td colspan="1" style="width:150px">
                            公司名称 
                    </td>
                    <td colspan="3">
                            <input style='width:100%' v-model='content.bsConsigneeCompany'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">            
                           Building 大厦 
                    </td>
                    <td colspan="3">
                            <input style='width:100%' v-model='content.bsConsigneeBuilding'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">   
                            Street 街道
                    </td>
                    <td colspan="3">
                            <input style="width:100%" v-model='content.bsConsigneeStreet'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1" style="width:150px">
                        <nobr>
                            ZIP code 邮政编码  
                        </nobr>
                    </td>
                    <td colspan="1">
                            <input style='width:100%' v-model='content.bsConsigneeZipCode'>
                    </td>
                    <td colspan="1">
                        <nobr>
                          City 城市<input style='width:80%' v-model='content.bsConsigneeCity'>
                        </nobr>
                    </td>
                    <td colspan="1">
                        <nobr>
                          State州 <input style='width:77%' v-model='content.bsConsigneeState'>
                        </nobr>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">
                            Country 国家  
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsConsigneeCountry'>
                    </td>
                    <td colspan="1">
                            Contact联系人
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsConsigneeContact'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1" style="border-bottom-style:solid ;border-bottom-color:#4c4eda;border-bottom-width:thin">
                            Phone 电话   
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsConsigneePhone'> 
                    </td>
                    <td colspan="1">
                            Email电邮/Fax传真
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsConsigneeEmail'> 
                    </td>
                </tr>
                    <tr>
                            <td colspan="4">
                                Notify Party 通知方
                            </td>
                            <td colspan="4">
                                <p></p>
                            </td>
                        </tr>
                        <tr>
                    <td colspan="1" style="width:150px">
                            公司名称 
                    </td>
                    <td colspan="3">
                            <input style='width:100%' v-model='content.bsNotifyCompany'> 
                    </td>
                </tr>
                <tr>
                    <td colspan="1">            
                           Building 大厦 
                    </td>
                    <td colspan="3">
                            <input style='width:100%' v-model='content.bsNotifyBuilding'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">   
                            Street 街道
                    </td>
                    <td colspan="3">
                            <input style="width:100%" v-model='content.bsNotifyStreet'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1" style="width:150px">
                        <nobr>
                            ZIP code 邮政编码  
                        </nobr>
                    </td>
                    <td colspan="1">
                            <input style='width:100%' v-model='content.bsNotifyZipCode'>
                    </td>
                    <td colspan="1">
                        <nobr>
                          City 城市<input style='width:80%' v-model='content.bsNotifyCity'>
                        </nobr>
                    </td>
                    <td colspan="1">
                        <nobr>
                          State州 <input style='width:77%' v-model='content.bsNotifyState'>
                        </nobr>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">
                            Country 国家  
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsNotifyCountry'>
                    </td>
                    <td colspan="1">
                            Contact联系人
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsNotifyContact'>
                    </td>
                </tr>
                <tr>
                    <td colspan="1" style="border-bottom-style:solid ;border-bottom-color:#4c4eda;border-bottom-width:thin">
                            Phone 电话   
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsNotifyPhone'> 
                    </td>
                    <td colspan="1">
                            Email电邮/Fax传真
                    </td>
                    <td colspan="1">
                            <input style="width:100%" v-model='content.bsNotifyEmail'> 
                    </td>
                </tr>
                <tr>
                    <td colspan="1">
                        <p>Flight</p>
                        <br>
                    </td>
                    <td colspan="3">
                        <textarea rows="2" v-model='content.bsFlight'></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        Place of  Receipt收货地
                    </td>
                    <td colspan="2">
                        From(Airport of Departure)/起运地机场
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input v-model='content.bsReceipt'>
                    </td>
                    <td colspan="2">
                        <input v-model='content.bsFrom'>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                            To(Airport of Departure)/起运地机场
                    </td>
                    <td colspan="2">
                        Final Description
                    </td>
                    <td colspan="2">
                        <p>Decalarred Value for Carriage 运输申报价值</p>
                    </td>
                    <td colspan="2">
                        <p>Decalarred Value for Customs 海关申报价值</p>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input v-model='content.bsTo'>
                    </td>
                    <td colspan="2">
                        <input v-model='content.bsFinal'>
                    </td>
                    <td colspan="2">
                        <input v-model='content.bsCarriage'>
                    </td>
                    <td colspan="2">
                        <input v-model='content.bsCustoms'>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <p>Cargo Ready Date /Time提货或交货日期/时间</p>
                    </td>
                    <td colspan="1">
                        <input v-model='content.bsReadyDateY'>
                    </td>
                    <td colspan="1">
                        <input v-model='content.bsReadyDateD'>
                    </td>
                    <td colspan="2">
                        <p>Insurence Value to be Covered(承包价值)</p>
                    </td>
                    <td colspan="2">
                        <input v-model='content.bsCovered'>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <p>Pick Up Address提货地点</p>
                    </td>
                    <td colspan="4">
                        <p>Freight payable by 3rd party(Please provide contact detail/如属第三者付款(请提供详细联络数据)</p>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <textarea rows="8"  v-model='content.bsPickAddress'></textarea>
                    </td>
                    <td colspan="4">
                        <nobr>
                        <p>Name 公司名称</p><input v-model='content.bsTreCompany'>
                        </nobr>
                         <nobr>
                        <p>Address 地址</p><input v-model='content.bsTreAddress'>
                        </nobr>
                         <nobr>
                        <p>Contact 联系人</p><input v-model='content.bsTreContact'><p>Phone 电话</p><input v-model='content.bsTrePhone'><p>Email 电邮</p><input v-model='content.bsTreEmail'>
                        </nobr>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">
                        <p>Contact联络人</p>
                    </td>
                    <td colspan="1">
                        <input  v-model='content.bsContact'>
                    </td>
                    <td colspan="1">
                            <p>Tel电话</p>
                    </td>
                    <td colspan="1">
                            <input  v-model='content.bsTel'>
                    </td>
                </tr>
    
             <tr>
                    <td colspan="2" align=center style="font-weight:600">
                        <p>Marks: </p>
                        <p>(唛 头)</p>
                    </td>
                    <td colspan="1" align=center style="font-weight:600">
                      <p>Quantity</p>
                      <p>包装件数</p>
                    </td>
                    <td colspan="1" align=center style="font-weight:600">
                       <p>Package Type</p>
                       <p>包装类别</p>
                    </td>
                    <td colspan="1" align=center>
                        <P>Description of Goods</P>
                        <p>货物描述</p>
                        <p>(Please provider Cargo dimension请提供货物尺寸)</p>
                    </td>
                    <td colspan="1" align=center style="font-weight:600">
                        <p>HS Code</p>
                        <p>商品编码</p>
                     </td>
                     <td colspan="1" align=center style="font-weight:600">
                        <p>Gross weight</p>
                        <p>(Kilos)</p>
                        <p>毛重(公斤)</p>
                     </td>
                     <td colspan="1" align=center style="font-weight:600">
                        <p>Measurement</p>
                        <p>CBM</p>
                        <P>体积(立方米)</P>
                     </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input v-model="content.bsMarks">
                    </td>
                    <td colspan="1">
                        <input v-model="content.bsQuantity">
                    </td>
                    <td colspan="1">
                        <input v-model="content.bsPackagesType">
                    </td>
                    <td colspan="1">
                        <input v-model="content.bsDescription">
                    </td>
                    <td colspan="1">
                        <input v-model="content.bsHsCode">
                    </td>
                    <td colspan="1">
                        <input v-model="content.bsWeight">
                    </td>
                    <td colspan="1">
                        <input v-model='content.bsMeasurement'>
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
          receipts: [{ name: "", desc: "请选择" }],
          deliveries: [{ name: "", desc: "请选择" }],
          contentData:"",
          content: {
            bsShipperReference:'',
            bsShipperPana:'',
            bsReserver:'',
            bsCompany:'',bsBuilding:'',bsStreet:'',bsZipCode:'',bsCountry:'',bsCity:'',bsState:'',bsContact:'',bsPhone:'',bsEmail:'',
            bsConsigneeCompany:'',bsConsigneeBuilding:'',bsConsigneeStreet:'',bsConsigneeZipCode:"",bsConsigneeCity:'',bsConsigneeState:'',bsConsigneeContact:'',bsConsigneePhone:'',bsConsigneeEmail:'',
            bsNotifyCompany:'',bsNotifyBuilding:'',bsNotifyStreet:'',bsNotifyZipCode:"",bsNotifyCity:'',bsNotifyState:'',bsNotifyContact:'',bsNotifyPhone:'',bsNotifyEmail:'',
            bsFlight:'',
            bsTreAddress:'',bsTreCompany:'',bsTrePhone:'',bsTreEmail:'',bsTreContact:'',
            bsCarriage:'',
            bsCustoms:'',
            bsReceipt:'',
            bsFrom:'',
            bsTo:'',
            bsFinal:'',
            bsReadyDateD:'',
            bsReadyDateY:'',
            bsPickAddress:'',
            bsContact:'',
            bsTel:'',
            bsMarks:'',
            bsQuantity:'',
            bsPackagesType:'',
            bsHsCode:'',
            bsDescription:'',
            bsWeight:'',
            bsMeasurement:''
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
    