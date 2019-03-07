<template>
    <div class="ivu-release-extend">
        <Row>
            <i-col>
                <Form :label-width="80" inline>
                    <FormItem label="签名位置:">
                        <RadioGroup v-model="signval">
                            <Radio label="first">第一页</Radio>
                            <Radio label="all">所有页</Radio>
                        </RadioGroup>
                    </FormItem>
                    <FormItem label="印章位置:">
                        <RadioGroup v-model="sealval">
                            <Radio label="first">第一页</Radio>
                            <Radio label="all">所有页</Radio>
                        </RadioGroup>
                    </FormItem>
                </Form>
            </i-col>
        </Row>
    </div>
</template>
<script>
export default {
    name: "ReleaseExtend",
    props: {
        sign: String,
        seal: String
    },
    data() {
        return {
            signval: this.sign,
            sealval: this.seal
        };
    },
    created() {
        if (!this.sign) this.signval = "first";
        if (!this.seal) this.sealval = "first";
    },
    watch: {
        sealval() {
            if (!this.sealval) return;
            this.$emit("change", { seal: this.sealval });
        },
        signval() {
            if (!this.signval) return;
            this.$emit("change", { sign: this.signval });
        }
    }
};
</script>

<style>
</style>
