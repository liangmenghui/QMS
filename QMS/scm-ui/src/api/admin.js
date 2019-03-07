﻿import httpservices from '@/libs/service'

import login from './admin/login.js'
import group from './admin/sys.group.js'     //组
import organization from './admin/sys.organization.js'
import resrce from './admin/sys.resrce.js'    //资源
import user from './admin/sys.user.js'       //用户
import rolegroup from './admin/sys.rolegroup.js'     //角色组
import role from './admin/sys.role.js'     //角色
import perm from './admin/sys.perm.js'     //权限
import userrole from './admin/sys.userrole.js'     //用户角色
import roleperm from './admin/sys.roleperm.js'     //角色权限
import resrceperm from './admin/sys.resrceperm.js'     //资源权限
import userresrce from './admin/sys.userresrce.js'     //授权的资源权限列表

import globalconfig from './admin/sys.globalconfig.js'  //全局应用配置

import custom from './admin/ext.custom' //客制化目录配置
import customfield from './admin/ext.customfield' //客制化字段配置
import customfieldvalue from './admin/ext.customfieldvalue' //客制化属性-值
import serialno from './admin/ext.serialno' //编码生成规则配置
import online from './admin/sys.online.js' //在线用户管理
import ref from './admin/sys.ref.js' //ref参照

export default{
    login,
    group,
    organization,
    resrce,
    user,
    rolegroup,
    role,
    perm,
    userrole,
    roleperm,
    resrceperm,
    userresrce,
    globalconfig,
    custom,
    customfield,
    customfieldvalue,
    serialno,
    online,
    ref
}