<template>
    <div>
        <Button type="primary" @click="handleAdd">添 加</Button>
        <Modal v-model="visible" :loading="loading" @on-ok="handleSubmit">
            <p slot="header" style="color:rgb(99, 168, 168);text-align:center">
                <Icon type="information-circled"></Icon>
                <span>添 加</span>
            </p>
            <p>
                <div>
                    <Row>
                        <i-col>
                            <Form :model="form" :label-width="80" style="text-align:center" :ref="ruleForm" :rules="ruleForm">
                                <FormItem label="货代" prop='bsProvider'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="form.bsProvider" placeholder="请输入货代" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="传真" prop='bsFax'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="form.bsFax" placeholder="请输入传真" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="邮箱" prop='bsEmail'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="form.bsEmail" placeholder="请输入邮箱" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="地址" prop='bsAddress'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="form.bsAddress" placeholder="请输入地址" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="电话" prop='bsTel'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="form.bsTel" placeholder="请输入电话" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="联系人" prop='bsContractor'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="form.bsContractor" placeholder="请输入联系人" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="收货地" prop='bsShipTo'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="form.bsShipTo" placeholder="请输入收货地" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                                <FormItem label="发货地" prop='bsShipFrom'>
                                    <Row>
                                        <i-col span="15">
                                            <Input v-model="form.bsShipFrom" placeholder="请输入发货地" />
                                        </i-col>
                                    </Row>
                                </FormItem>
                            </Form>
                        </i-col>
                    </Row>
                </div>
        </Modal>
    </div>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
      loading: true,
      form: {
        bsProvider: "",
        bsFax: "",
        bsAddress: "",
        bsTel: "",
        bsContractor: "",
        bsShipTo: "",
        bsEmail: "",
        bsShipFrom: ""
      },
      ruleForm: {
        bsProvider: [
          { required: true, message: "请填写货代", trigger: "blur" }
        ],
        bsContractor: [
          { required: true, message: "请填写联系人", trigger: "blur" }
        ],
        bsTel: [{ required: true, message: "请填写电话号码", trigger: "blur" }]
      }
    };
  },
  methods: {
    handleAdd() {
      Object.keys(this.form).forEach(key => {
        this.form[key] = "";
      });
      this.visible = true;
    },
    handleSubmit() {
      this.stopPropagation();
      if (this.validate()) {
        this.api.lmp.provider.insertProvider(this.form).then(res => {
          if (res.result) {
            this.$Message.success("添加成功！");
            this.close();
          } else {
            this.$Message.error("添加失败！");
          }
        });
      }
    },
    validate: function() {
      if (this.form.bsProvider == "" || this.form.bsProvider == null) {
        this.$Message.error("货代不能为空");
        return false;
      } else if (
        this.form.bsContractor == "" ||
        this.form.bsContractor == null
      ) {
        this.$Message.error("联系人不能为空");
        return false;
      } else if (this.form.bsTel == "" || this.form.bsTel == null) {
        this.$Message.error("联系电话不能为空");
        return false;
      } else {
        return true;
      }
    },
    stopPropagation() {
      this.loading = false;
      this.$nextTick(() => {
        this.loading = true;
      });
    },
    close() {
      this.visible = false;
      this.$emit("closed");
    }
  }
};
</script>
