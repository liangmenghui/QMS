/*
 * @Description:导出 ERP 供应链管理系统 业务组件 并注册成VUE全局组件 
 * @Author: steven.liu 
 * @Date: 2018-05-28 12:18:13 
 * @Last Modified by: steven.liu
 * @Last Modified time: 2018-05-28 12:41:25
 */

import unAgent from './unAgent'
import unCustomer from './unCustomer'
//注册 物流管理系统下的 Booking订仓 content、template 等插件 为全局组件
import { contextComponents, templateComponents } from '@/view/logistics/booking/plugin'

const unindBussinessComponent = {
    unCustomer,
    unAgent,
    ...contextComponents, 
    ...templateComponents
}

export default unindBussinessComponent;