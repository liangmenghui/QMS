<template>
    <Modal transfer
        v-model="visible"
        v-bind:title="title"
        @on-ok="ok"
        @on-cancel="cancel">
        <div style="padding:5px 5px;">
        <Form ref="ruleForm" :label-width="80">
            <Row v-for="item in data" :key="item.bsName">
                <i-col span="24">
                    <span style="display:none;"><Input v-model="item.id"></Input></span>
                    <Form-item :label="item.bsLabel">
                        <Input v-model="item.bsValue" placeholder="请输入"></Input>
                    </Form-item>
                </i-col>
            </Row>
        </Form>
        </div>
    </Modal>
</template>
<script>

export default {
    name: 'cust-extprops',
    props: {
        title: String,
        data: Array
    },
    data() {
        return {
            formItem: {},
            visible: false
        }
    },
    created() {
    },
    methods: {
        show (val) {
            this.visible = true;
        },
        ok() {
            this.$emit("ok", this.data);
        },
        cancel() {
            this.$emit("cancel");
        }
    }
};
</script>

<style>
</style>
