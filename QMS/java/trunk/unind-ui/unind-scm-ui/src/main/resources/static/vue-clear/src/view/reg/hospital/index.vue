<template>
    <div>
        <Form ref="formItem" :model="formItem" label-position="right" :label-width="100" >
            <Row>
                <i-col span="4">
                    <Form-item prop="bsCode">
                        <Input style="width:225px;" type="text" v-model="formItem.hospital.bsCode" placeholder="机构编码" />
                    </Form-item>
                </i-col>
                <i-col span="20">
                    <Form-item>
                        <Button type="primary" @click="handleSubmit('formItem')">查 询</Button>
                    </Form-item>
                    <Form-item v-if="menuData.perms.add">
                        <Button type="primary" @click="showAddDialog()">新 增</Button>
                    </Form-item>
                </i-col>
            </Row>
            <Row>
                <i-col span="24">
                    <Tabs>
                        <Tab-pane label="基础信息" icon="social-apple">
                            <Row>
                                <i-col span="20">
                                    <Form-item label="机构全称" prop="bsName">
                                        <Input type="text" v-model="formItem.hospital.bsName" placeholder="请输入"></Input>
                                    </Form-item>
                                    <Form-item label="机构简称" prop="bsShortName">
                                        <Input type="text" v-model="formItem.hospital.bsShortName" placeholder="请输入" />
                                    </Form-item>
                                    <Form-item label="咨询电话" prop="bsConsultNo">
                                        <Input type="text" v-model="formItem.hospital.bsConsultNo" placeholder="请输入" />
                                    </Form-item>
                                </i-col>
                                <i-col span="4" align="center">
                                    <img :src="formItem.hospital.bsUrl" style="height:100px;width:90px;max-height:100px;max-width:75px;" />
                                    <Upload action="//jsonplaceholder.typicode.com/posts/">
                                        <Button type="ghost" icon="ios-cloud-upload-outline">上传LOGO</Button>
                                    </Upload>
                                </i-col>
                            </Row>
                            <Form-item label="单位地址" prop="bsAddress">
                                <Input type="text" v-model="formItem.hospital.bsAddress" placeholder="请输入" />
                            </Form-item>
                            <Row>
                                <i-col span="8">
                                    <Form-item label="医院性质" prop="bsNature">
                                        <Radio-group v-model="formItem.hospital.bsNature">
                                            <Radio label="国有">国有</Radio>
                                            <Radio label="私企">私企</Radio>
                                        </Radio-group>
                                    </Form-item>
                                </i-col>
                                <i-col span="8">
                                    <Form-item label="医院等级" prop="bsGrade">
                                        <Radio-group v-model="formItem.hospital.bsGrade">
                                            <Radio label="-1">无</Radio>
                                            <Radio label="三级甲等">三级甲等</Radio>
                                            <Radio label="二级甲等">二级甲等</Radio>
                                        </Radio-group>
                                    </Form-item>
                                </i-col>
                                <i-col span="8">
                                    <Form-item label="机构类型" prop="bsType">
                                        <Radio-group v-model="formItem.hospital.bsType">
                                            <Radio label="-1">无</Radio>
                                            <Radio label="综合医院">综合医院</Radio>
                                            <Radio label="二级甲等">二级甲等</Radio>
                                        </Radio-group>
                                    </Form-item>
                                </i-col>
                            </Row>
                            <Row>
                                <i-col span="12">
                                    <Form-item label="经度" prop="bsLng">
                                        <Input type="text" v-model="formItem.hospital.bsLng" placeholder="请输入" />
                                    </Form-item>
                                </i-col>
                                <i-col span="12">
                                    <Form-item label="纬度" prop="bsLat">
                                        <Input type="text" v-model="formItem.hospital.bsLat" placeholder="请输入" />
                                    </Form-item>
                                </i-col>
                            </Row>
                            <Form-item label="开通状态" prop="bsOpenStatus">
                                <Input type="text" v-model="formItem.hospital.bsOpenStatus" placeholder="请输入" />
                            </Form-item>
                        </Tab-pane>
                        <Tab-pane label="详细信息" icon="social-windows">
                            <Form-item label="联系电话" prop="bsContactNo">
                                <Input type="text" v-model="formItem.hospital.bsContactNo" placeholder="请输入" />
                            </Form-item>
                            <Form-item label="传真" prop="bsFaxNo">
                                <Input type="text" v-model="formItem.hospital.bsFaxNo" placeholder="请输入" />
                            </Form-item>
                            <Form-item label="邮箱" prop="bsEmail">
                                <Input type="text" v-model="formItem.hospital.bsEmail" placeholder="请输入" />
                            </Form-item>
                            <Form-item label="官方网址" prop="bsWebsite">
                                <Input type="text" v-model="formItem.hospital.bsWebsite" placeholder="请输入" />
                            </Form-item>
                            <Form-item label="公交信息" prop="bsBusInfo">
                                <Input type="textarea" v-model="formItem.hospital.bsBusInfo" placeholder="请输入" />
                            </Form-item>
                            <Form-item label="机构简介" prop="bsIntro">
                                <Input type="textarea" v-model="formItem.hospital.bsIntro" placeholder="请输入" />
                            </Form-item>
                        </Tab-pane>
                        <Tab-pane label="预约配置" icon="social-windows">
                            <Form-item label="排班模式" prop="bsScheduleMode">
                                <Radio-group v-model="formItem.hospitalRegSet.bsScheduleMode">
                                    <Radio label="1">在线排班</Radio>
                                    <Radio label="2">医院排班</Radio>
                                </Radio-group>
                            </Form-item>
                            <Form-item label="医生展示" prop="bsDocVisible">
                                <Radio-group v-model="formItem.hospitalRegSet.bsDocVisible">
                                    <Radio label="1">放号后显示</Radio>
                                    <Radio label="2">预约即显示</Radio>
                                </Radio-group>
                            </Form-item>
                            <Form-item label="医生关联" prop="bsDocRel">
                                <Radio-group v-model="formItem.hospitalRegSet.bsDocRel">
                                    <Radio label="1">允许关联-需审核</Radio>
                                    <Radio label="2">允许关联-无需审核</Radio>
                                    <Radio label="3">不允许关联</Radio>
                                </Radio-group>
                            </Form-item>
                            <Row>
                                <i-col span="12">
                                    <Form-item label="预约比例" prop="bsRegScale">
                                        <Slider v-model="formItem.hospitalRegSet.bsRegScale"></Slider>
                                        <!--<Input type="text" v-model="formItem.hospital.bsRegScale" placeholder="请输入" />-->
                                    </Form-item>
                                </i-col>
                                <!--<i-col span="3">
                                    <Form-item label="" prop="bsRegScale">
                                        <Input type="text" v-model="formItem.hospital.bsRegScale" placeholder="请输入" />
                                    </Form-item>
                                </i-col>-->
                            </Row>
                            <Form-item label="启用预约公告" prop="bsEnableRn">
                                <Radio-group v-model="formItem.hospitalRegSet.bsEnableRn">
                                    <Radio label="1">启用</Radio>
                                    <Radio label="2">停用</Radio>
                                </Radio-group>
                            </Form-item>
                            <Form-item label="预约公告" prop="bsRegNotice">
                                <Input type="textarea" v-model="formItem.hospitalRegSet.bsRegNotice" placeholder="请输入" />
                            </Form-item>
                            <Form-item label="启用挂号公告" prop="bsEnableOn">
                                <Radio-group v-model="formItem.hospitalRegSet.bsEnableOn">
                                    <Radio label="1">启用</Radio>
                                    <Radio label="2">停用</Radio>
                                </Radio-group>
                            </Form-item>
                            <Form-item label="挂号公告" prop="bsOrderNotice">
                                <Input type="textarea" v-model="formItem.hospitalRegSet.bsOrderNotice" placeholder="请输入" />
                            </Form-item>
                            <Form-item label="就诊须知" prop="bsVisitNotice">
                                <Input type="textarea" v-model="formItem.hospitalRegSet.bsVisitNotice" placeholder="请输入" />
                            </Form-item>
                        </Tab-pane>
                        <Tab-pane label="号源配置" icon="social-tux">
                            <Row>
                                <i-col span="4">
                            <Form-item label="放号时间" prop="bsReleaseDay">
                                <Select type="text" v-model="formItem.hospitalRegSet.bsReleaseDay" placeholder="请输入" >
                                    <Option value="1">周一</Option>
                                    <Option value="2">周二</Option>
                                </Select>
                            </Form-item>
                                </i-col>
                                <i-col span="4">
                            <Form-item label="放号时间" prop="bsReleaseTime">
                                <Input type="text" v-model="formItem.hospitalRegSet.bsReleaseTime" placeholder="请输入" />
                            </Form-item>
                                </i-col>
                            </Row>
                            <Form-item label="放号周期" prop="bsReleasePeriod">
                                <Select type="text" v-model="formItem.hospitalRegSet.bsReleasePeriod" placeholder="请输入" >
                                    <Option value="1">一周</Option>
                                    <Option value="2">二周</Option>
                                    <Option value="3">三周</Option>
                                    <Option value="4">四周</Option>
                                    <Option value="5">五周</Option>
                                </Select>
                            </Form-item>
                            <Row>
                                <i-col span="4" align="left">
                                    上午
                                </i-col>
                                <i-col span="4" align="left">
                                    下午
                                </i-col>
                            </Row>
                            <Row>
                                <i-col span="4">
                                    <table>
                                        <tr>
                                            <td>08:00</td>
                                            <td>~</td>
                                            <td>09:00</td>
                                        </tr>
                                        <tr>
                                            <td>10:00</td>
                                            <td>~</td>
                                            <td>11:00</td>
                                        </tr>
                                        <tr>
                                            <td>11:00</td>
                                            <td>~</td>
                                            <td>12:00</td>
                                        </tr>
                                    </table>
                                </i-col>
                                <i-col span="4">
                                    <table>
                                        <tr>
                                            <td>13:00</td>
                                            <td>~</td>
                                            <td>14:00</td>
                                        </tr>
                                        <tr>
                                            <td>14:00</td>
                                            <td>~</td>
                                            <td>15:00</td>
                                        </tr>
                                        <tr>
                                            <td>15:00</td>  
                                            <td>~</td>
                                            <td>16:00</td>
                                        </tr>
                                    </table>
                                </i-col>
                            </Row>
                        </Tab-pane>
                        <Tab-pane label="其它" icon="social-tux"></Tab-pane>
                    </Tabs>
                </i-col>
            </Row>
        </Form>
    </div>
</template>
<script>
import {mapState} from 'vuex'
export default {
    data() {
        return {
            formItem: {
                hospital:{
                    bsNature: '国有',
                    bsGrade: -1,
                    bsType: -1
                },
                hospitalRegSet:{
                    bsScheduleMode: 1,
                    bsRegScale: 100,
                    bsDocVisible: 1,
                    bsDocRel: 3,
                    bsEnableRn: 1,
                    bsRegNotice: '',
                    bsEnableOn: 1,
                    bsOrderNotice: '',
                    bsVisitNotice: ''
                },
                hospitalScheduleRegSets:{}
            },
            data: {}
        }
    },
    created(){
    },
    computed:{
        ...mapState({
            menuData:state=>state.menuData  
        })
    },
    methods: {
        handleSubmit(name) {
            this.getData();
        },
        getData() {
            this.api.hospital.getlist(this.formQuery).then((res) => {
                console.log("get list===>");
                console.log(res.data);
                if(res.result) {
                    this.datagrid.data = res.data;
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        reloadData() {
            this.data = this.getData();
        },
        edit(params) {
            console.log(params);
            this.api.perm.edit(params.row).then((res)=>{
                console.log(res)
                if(res.result) {
                    //refresh
                    this.reloadData();
                }else {
                    this.$Message.error(res.msg);
                }
            });
        },
        delete(params) {
            console.log(params);
            this.$Modal.confirm({
                title: '提示信息',
                content: '<p>是否确定删除？</p>',
                loading: true,
                onOk: () => {
                    this.api.perm.delete({id:params.row.id}).then((res)=>{
                        console.log(res)
                        if(res.result) {
                            //refresh
                            this.reloadData();
                            this.$Message.info('删除成功');
                            this.$Modal.remove();
                        }else {
                            this.$Message.error(res.msg);
                        }
                    });
                }
            });
        },
        save() {
            console.log("this.dialog.formItem====>"+typeof(this.dialog.formItem.id));
            if(typeof(this.dialog.formItem.id)!=undefined && typeof(this.dialog.formItem.id)=="number") {
                console.log("编辑1");
                console.log(JSON.stringify(this.dialog.formItem));
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.perm.edit(this.dialog.formItem).then((res) => {
                            if(res.result) {
                                //refresh
                                this.reloadData();
                            }else {
                                this.$Message.error(res.msg);
                            }
                        });
                //     }
                // });
            }else {
                console.log("新增1");
                // this.$refs["dialog.ruleForm"].validate((valid) => {
                //     if (valid) {
                        this.api.perm.add(this.dialog.formItem).then((res) => {
                            if(res.result) {
                                //refresh
                                this.reloadData();
                            }else {
                                this.$Message.error(res.msg);
                            }
                        });
                //     }
                // });
            }
        },
        reset(name) {
            this.$refs[name].resetFields();
        }
    }
}
</script>
