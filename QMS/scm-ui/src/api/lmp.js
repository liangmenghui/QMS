﻿import httpservices from '@/libs/service'

import bookingforecast from './lmp.bookingforecast' //预订仓
import provider from './lmp.provider' //货代
import booking from './lmp.booking'
import supplier from './lmp.supplier' //货代
import config from './lmp.config' //货代基础数据
import cargo from './lmp.cargo' //商品料号维护
import preplan from './lmp.preplan'   //出货计划
import transprovider from './lmp.transprovider'   //出货计划
import item from './lmp.item'   //出货计划
import hscode from './lmp.hscode'  //商品信息
import bookingconfiguration from './lmp.bookingconfiguration'  

export default{
    bookingforecast,
    provider,
    booking,
    supplier,
    config,
    cargo,
    preplan,
    transprovider,
    item,
    hscode,
    bookingconfiguration,
}