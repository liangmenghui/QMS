<template>
    <div class="login-container">
        <Form ref="form_login" :model="form_login" :rules="form_login_rule">
            <h3 class="title">系统登录</h3>
            <Row>
                <Col span="12">
                    <Form-item prop="username">
                        <Input v-model="form_login.username" placeholder="请输入用户名" size="large" style="width:280px">
                        <Icon type="ios-person-outline" slot="prepend"></Icon>
                        </Input>
                    </Form-item>
                </Col>
            </Row>
            <br/>
            <Row>
                <Col span="12">
                    <Form-item prop="password">
                        <Input type="password" v-model="form_login.password" placeholder="请输入密码" size="large" style="width:280px">
                        <Icon type="ios-locked-outline" slot="prepend"></Icon>
                        </Input>
                    </Form-item>
                </Col>
            </Row>
            <Row>
                <Col span="12">
                    <Checkbox v-model="form_login.rememberme" label="false" size="large"><span>&nbsp;&nbsp;记住密码</span></Checkbox>
                </Col>
            </Row>
            <br/>
            <Row>
                <Col span="12">
                    <Button type="primary" :loading="loading" @click="handleLogin('form_login')" style="width:280px;">
                        <span v-if="!loading">登&nbsp;录</span>
                        <span v-else>Loading...</span>
                    </Button>
                </Col>
            </Row>
        </Form>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                loading: false,
                isShowAdduser:false,
                form_login: {
                    username:'',
                    password:'',
                    rememberme: false
                },
                form_login_rule: {
                    username: [
                        { required: true, message: '请填写用户名', trigger: 'blur' },
                        { type: 'string', min: 1, message: '用户名不能为空', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请填写密码', trigger: 'blur' },
                        { type: 'string', min: 4, message: '密码长度不能小于4位', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
 
            handleLogin(name) {
                this.loading = true;
                console.log(this);
                this.$refs[name].validate((valid) => {
                    this.loading = false;
                    if (valid) {
                        this.api.login.logon(this.form_login).then((res) => {
                            console.log(res.data)
                            if(res.result) {
                                sessionStorage.setItem("user", JSON.stringify(res.data));
                                this.$Message.success("登录成功，正在跳转...");
                                //跳转
                                this.$router.push({path: "/"});
                            }else {
                                this.$Message.error(res.msg);
                            }
                        })
                    } else {
                        this.$Message.error('请输入正确的用户名或密码!');
                    }
                })
            }
        }
    }
</script>
<style lang="less">
    .login-container {
        /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
        -webkit-border-radius: 5px;
        border-radius: 5px;
        -moz-border-radius: 5px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        .title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
        }
        .remember {
        margin: 0px 0px 35px 0px;
        }
    }
</style>