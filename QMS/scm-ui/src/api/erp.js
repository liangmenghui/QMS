import httpservices from '@/libs/service'

import apiconfig from './erp/erp.apiconfig'    //ERP API配置
import item from './erp/erp.item' //物料
import customer from './erp/erp.customer' //客户
import supplier from './erp/erp.supplier' //供应商
import itemcustomer from './erp/erp.itemcustomer' //物料-客户-客户物料号关系
import productinfo from './erp/erp.productinfo' //主组织物料详细信息

export default{
    item,
    customer,
    supplier,
    itemcustomer,
    productinfo,
    apiconfig
}