<style lang="less">
    @import "./home.less";
    @import "../../styles/common.less";
</style>
<template>
    <div class="home-main">
         <Row>
            <!-- 待办事项开始 -->
                    <Col :md="24" :lg="24" :style="{marginBottom: '10px'}">
                        <Card>
                            <p slot="title" class="card-title">   <i class="el-icon-bell"></i>                     
                               {{$t('Dashboard.Todoitems')}}
                              <a class="seeMore" type="text"  slot="extra"  @click="pushTodolist()">
                                <span class="searchmore"><i class="el-icon-view"></i>{{$t('Dashboard.SeeMore')}}</span>
                               </a>
                            </p>  

                        <ul class="to-do-list-con listhheight" v-model="todolistdata">
                            <li v-for="item in toDoList ">
                                <el-row>
                                   <el-col :span="16"><a  @click="pushCreator(item)" class="grid-content bg-purple"><i class="el-icon-caret-right"></i>{{item.bsTitle}}</a></el-col>

                                   <el-col :span="4"><div class="bsCreatedTime"><i class="el-icon-time"></i>{{item.bsCreatedTime}}</div></el-col>
                                   <el-col :span="4" align="center" v-if="item.bsType==12||item.bsType==13||item.bsType==23"> <el-button   size="mini" type="primary" plain @click="closeItem(item.id)">{{$t('Dashboard.CloseTodo')}}</el-button></el-col>
                                </el-row>
                            </li>          
                        </ul>                          
                         
                        </Card>
                    </Col>
        </Row>
        <!--
        <Row :gutter="10" class="margin-top-10">
            <Col :md="24" :lg="8" :style="{marginBottom: '10px'}">
                <Card>
                    <p slot="title" class="card-title">
                        <Icon type="android-map"></Icon>
                        上周每日来访量统计
                    </p>
                    <div class="data-source-row">
                        <visite-volume></visite-volume>
                    </div>
                </Card>
            </Col>
            <Col :md="24" :lg="8" :style="{marginBottom: '10px'}">
                <Card>
                    <p slot="title" class="card-title">
                        <Icon type="ios-pulse-strong"></Icon>
                        数据来源统计
                    </p>
                    <div class="data-source-row">
                        <data-source-pie></data-source-pie>
                    </div>
                </Card>
            </Col>
            <Col :md="24" :lg="8">
                <Card>
                    <p slot="title" class="card-title">
                        <Icon type="android-wifi"></Icon>
                        各类用户服务调用变化统计
                    </p>
                    <div class="data-source-row">
                        <user-flow></user-flow>
                    </div>
                </Card>
            </Col>
        </Row>
        <Row class="margin-top-10">
            <Card>
                <p slot="title" class="card-title">
                    <Icon type="ios-shuffle-strong"></Icon>
                    上周每日服务调用量(万)
                </p>
                <div class="line-chart-con">
                    <service-requests></service-requests>
                </div>
            </Card>
        </Row>
        <Row :gutter="10"style="margin-top:10px;">
            <!-- <Col :md="24" :lg="8">
                <Row class-name="home-page-row1" :gutter="10">
                    <Col :md="12" :lg="24" :style="{marginBottom: '10px'}">
                        <Card>
                            <Row type="flex">
                                <Col span="8">
                                    <Row class-name="made-child-con-middle" type="flex" align="middle">
                                        <img class="avator-img" :src="avatorPath" />
                                    </Row>
                                </Col>
                                <Col span="16" style="padding-left:6px;">
                                    <Row class-name="made-child-con-middle" type="flex" align="middle">
                                        <div>
                                            <b class="card-user-infor-name">Admin</b>
                                            <p>super admin</p>
                                        </div>
                                    </Row>
                                </Col>
                            </Row>
                            
                         
                        </Card>
                    </Col>

                </Row>
            </Col> -->
        <!--
            <Col :md="24" :lg="16">
                <Row :gutter="5">
                    <Col :xs="24" :sm="12" :md="6" :style="{marginBottom: '10px'}">
                        <infor-card
                            id-name="user_created_count"
                            :end-val="count.createUser"
                            iconType="android-person-add"
                            color="#2d8cf0"
                            intro-text="今日新增用户"
                        ></infor-card>
                    </Col>
                    <Col :xs="24" :sm="12" :md="6" :style="{marginBottom: '10px'}">
                        <infor-card
                            id-name="visit_count"
                            :end-val="count.visit"
                            iconType="ios-eye"
                            color="#64d572"
                            :iconSize="50"
                            intro-text="今日浏览量"
                        ></infor-card>
                    </Col>
                    <Col :xs="24" :sm="12" :md="6" :style="{marginBottom: '10px'}">
                        <infor-card
                            id-name="collection_count"
                            :end-val="count.collection"
                            iconType="upload"
                            color="#ffd572"
                            intro-text="今日数据采集量"
                        ></infor-card>
                    </Col>
                    <Col :xs="24" :sm="12" :md="6" :style="{marginBottom: '10px'}">
                        <infor-card
                            id-name="transfer_count"
                            :end-val="count.transfer"
                            iconType="shuffle"
                            color="#f25e43"
                            intro-text="今日服务调用量"
                        ></infor-card>
                    </Col>
                </Row>
              
            </Col>
        </Row>
       -->
        
    </div>
</template>

<script>
import cityData from './map-data/get-city-value.js';
import homeMap from './components/map.vue';
import dataSourcePie from './components/dataSourcePie.vue';
import visiteVolume from './components/visiteVolume.vue';
import serviceRequests from './components/serviceRequests.vue';
import userFlow from './components/userFlow.vue';
import countUp from './components/countUp.vue';
import inforCard from './components/inforCard.vue';
import mapDataTable from './components/mapDataTable.vue';
import toDoListItem from './components/toDoListItem.vue';

export default {
    name: 'home',
    components: {
        homeMap,
        dataSourcePie,
        visiteVolume,
        serviceRequests,
        userFlow,
        countUp,
        inforCard,
        mapDataTable,
        toDoListItem,
             
        
    },
    data () {
        return {
            toDoList: [              
            ],
            todolistdata:{},
            count: {
                createUser: 496,
                visit: 3264,
                collection: 24389305,
                transfer: 39503498
            },
            cityData: cityData,
            showAddNewTodo: false,
            newToDoItemValue: ''
        };
    },

    created(){
        this.getTodoData();
    },
    computed: {
        avatorPath () {
            return localStorage.avatorImgPath;
        }
    },
    methods: {
        addNewToDoItem () {
            this.showAddNewTodo = true;
        },
        addNew () {
            if (this.newToDoItemValue.length !== 0) {
                this.toDoList.unshift({
                    title: this.newToDoItemValue
                });
                setTimeout(() => {
                    this.newToDoItemValue = '';
                }, 200);
                this.showAddNewTodo = false;
            } else {
                this.$Message.error('请输入待办事项内容');
            }
        },
        cancelAdd () {
            this.showAddNewTodo = false;
            this.newToDoItemValue = '';
        },
          pushCreator(item){
        this.$store.commit("updateTodoStates",item);
       this.$router.replace({path:item.path});
    },
        getTodoData(){
            this.api.todo.getlist({bsStatus:0}).then((res) => { 
                var _this = this;  
                this.toDoList = res.data.rows.map(function (todo) {
                    todo.path = _this.$Util.getPathFromTodo(todo);
                    return todo;
                });
            });

        },
        pushTodolist(){
             this.$router.push({path: '/qms/todoItems'});
        },
        closeItem(item){    
    this.api.todo.close({Id:item}).then((res) => {  
         if(res.result) {                                
                this.$message({
                    message: '关闭待办成功！',
                    type: 'success',
                    duration:'1000'
                 });
            }else {
                this.$Message.error(res.msg);   
        }
    });
  },
   pushCreator(item){
        this.$store.commit("updateTodoStates",item);
        this.$router.push({path:item.path});
    },
    }
};
</script>
<style>
    .seeMore{margin-left: 50%;}    
    .search{font-size:  14px;font-family: "Microsoft YaHei"; text-align: right;margin-left:20px;}
    .card-title{position: relative;}
    .searchmore{position: absolute;right: 50px;}
    .listhheight{height: auto; padding-left:20px;overflow: hidden;}
    .listhheight li{line-height: 45px;list-style: none;}
</style>