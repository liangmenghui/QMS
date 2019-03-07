<template>
    <div class="animated fadeIn">
        <Table border :columns="columns6" :data="data5"></Table>

        <div style="border-bottom: 1px solid #e9e9e9;padding-bottom:6px;margin-bottom:6px;">
        <Checkbox
            :indeterminate="indeterminate"
            :value="checkAll"
            @click.prevent.native="handleCheckAll">全选</Checkbox>
        </div>
        <Checkbox-group v-model="checkAllGroup" @on-change="checkAllGroupChange">
            <input type="checkbox" name="perm" v-for="item in data" :key="item" :value="item.id" ></input>{{item.bsName}}
            <!--<Checkbox v-for="item in data" :key="item" :value="item.id">{{item.bsName}}</Checkbox>-->
            <!--<Checkbox v-model="checked" label="香蕉"></Checkbox>
            <Checkbox v-model="checked" label="苹果"></Checkbox>
            <Checkbox v-model="checked" label="西瓜"></Checkbox>-->
        </Checkbox-group>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                columns6: [
                    {
                        title: '日期',
                        key: 'date'
                    },
                    {
                        title: '姓名',
                        key: 'name'
                    },
                    {
                        title: '年龄',
                        key: 'age',
                        filters: [
                            {
                                label: '大于25岁',
                                value: 1
                            },
                            {
                                label: '小于25岁',
                                value: 2
                            }
                        ],
                        filterMultiple: false,
                        filterMethod(value, row) {
                            if (value === 1) {
                                return row.age > 25;
                            } else if (value === 2) {
                                return row.age < 25;
                            }
                        }
                    },
                    {
                        title: '地址',
                        key: 'address',
                        filters: [
                            {
                                label: '北京',
                                value: '北京'
                            },
                            {
                                label: '上海',
                                value: '上海'
                            },
                            {
                                label: '深圳',
                                value: '深圳'
                            }
                        ],
                        filterMethod(value, row) {
                            return row.address.indexOf(value) > -1;
                        }
                    }
                ],
                data5: [
                    {
                        name: '王小明',
                        age: 18,
                        address: '北京市朝阳区芍药居',
                        date: '2016-10-03'
                    },
                    {
                        name: '张小刚',
                        age: 25,
                        address: '北京市海淀区西二旗',
                        date: '2016-10-01'
                    },
                    {
                        name: '李小红',
                        age: 30,
                        address: '上海市浦东新区世纪大道',
                        date: '2016-10-02'
                    },
                    {
                        name: '周小伟',
                        age: 26,
                        address: '深圳市南山区深南大道',
                        date: '2016-10-04'
                    }
                ],
                indeterminate: true,
                checkAll: false,
                checkAllGroup: [2],
                data: [{id:1,bsName:'香蕉'}, {id:2,bsName:'西瓜'}, {id:3,bsName:'苹果'}]
            }
        },
        methods: {
            handleCheckAll () {
                if (this.indeterminate) {
                    this.checkAll = false;
                } else {
                    this.checkAll = !this.checkAll;
                }
                this.indeterminate = false;

                if (this.checkAll) {
                    this.checkAllGroup = ['香蕉', '苹果', '西瓜'];
                } else {
                    this.checkAllGroup = [];
                }
            },
            checkAllGroupChange (data) {
                if (data.length === 3) {
                    this.indeterminate = false;
                    this.checkAll = true;
                } else if (data.length > 0) {
                    this.indeterminate = true;
                    this.checkAll = false;
                } else {
                    this.indeterminate = false;
                    this.checkAll = false;
                }
            }
        }
    }

</script>