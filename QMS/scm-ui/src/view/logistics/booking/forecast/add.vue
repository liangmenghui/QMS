<template>
    <div>
      <h3>{{title}}</h3>

      <Form v-if="type='add'" ref="form" :model="form" :rules="formValidateRule" :label-width=100 inline>
      <Form v-if="type='view'" :model="form" :lable-width=100 inline >
        <Form-Item label="代理" prop="provider">
          <Select v-model="form.provider" style="width:140px;">
            <Option v-for="item in proxies" :value="item.bsProvider" :key="item.bsProvider">{{item.bsProviderDesc}}</option>
          </Select>
        </Form-Item>
        <Form-Item label="客户" prop="customer">
          <Select v-model="form.customer" style="width:140px;">
            <Option v-for="item in customers" :value="item.bsCustomer" :key="item.bsCustomer">{{item.bsCustomerDesc}}</option>
          </Select>
        </Form-Item>
        <Form-Item label="供应商" prop="factory">
          <Select v-model="form.factory" style="width:140px;">
            <Option v-for="item in suppliers" :value="item.bsFactory" :key="item.bsFactory">{{item.bsFactoryDesc}}</Option>
          </Select>
        </Form-Item>
        <div class="toolbar">
          <tooltip content="新增一行" placement="top-start">
            <i-button type="ghost" icon="ios-plus-empty" @click="handleFormToolbarAddLastRow"></i-button>
          </tooltip>
          <tooltip content="删除最后一行" placement="top-start">
            <i-button type="ghost" icon="ios-trash" @click="handleFormToolbarRemoveLastRow"></i-button>
          </tooltip>
        </div>
        <div class="box" ref="box">
          <Row v-for="(item,index) in form.items" :key="index" class-name="code-row-bg">
            <i-col span="8">
              <Form-Item label="料号" :prop="'items.'+index+'.bsItemNum'" :rules="{required:true,pattern:'^[0-9a-zA-Z]{0,22}$', message:'请输入正确的物料料号',trigger:'blur'}">
                <Input ref="input" v-model="item.bsItemNum" placeholder="请输入出货物料料号" />
              </Form-Item>
            </i-col>
            <i-col span="8">
              <Form-Item label="出货数量" :prop="'items.'+index+'.bsQty'" :rules="{ type:'number',required:true, message:'请输入正确的出货数量',trigger:'blur',transform(value){return Number(value);}}">
                <Input ref="input" v-model="item.bsQty" placeholder="请输入出货数量" />
              </Form-Item>
            </i-col>
            <i-col span="8">
              <Form-Item label="出货日期" :prop="'items.'+index+'.bsDay'" :rules="{required:true,pattern:'^[0-9]{4}-[0-9]{2}-[0-9]{2}',message:'请输入正确的出货日期',trigger:'blur'}">
                <Input ref="input" v-model="item.bsDay" placeholder="请输入出货日期" type="date" />
              </Form-Item>
            </i-col>
          </Row>
        </div>
      </Form>
    </div>
</template>
<script>

export default {
    name:"bookingforecastdetail",
    props:{
        title:{
            type:String,
            default:"预订仓"
        },
        type:{
            type:string,
            default:"view"
        }
    },
    data(){
        return{

        }
    },//data
    methods:{

    },//
};
</script>
<style>

</style>
