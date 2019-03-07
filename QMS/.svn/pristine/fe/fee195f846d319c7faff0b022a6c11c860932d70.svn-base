<template>
    <div class="ivu-editinput" @dblclick="handleMouseEnter" v-click-outside="handleClickOutside">
        <label class="label">{{this.label}}</label>
        <div class="content" v-show="disable">
            <Tooltip>
                <span>{{this.inputValue}}</span>
                <div slot="content" style="white-space: normal;">
                    {{this.inputValue}}
                </div>
            </Tooltip>
        </div>
        <Input ref="input" class="content" v-show="!disable" v-model="inputValue" @on-blur="handleMouseLeave" />
    </div>
</template>
<script>
import { directive as clickOutside } from "v-click-outside-x";

export default {
    name: "EditInput",
    props: {
        status: [String],
        label: String,
        value: [String, Number]
    },
    directives: { clickOutside },
    data() {
        return {
            inputValue: this.value,
            mouseEnter: false
        };
    },
    computed: {
        isFinished() {
            return this.status == "finished";
        },
        disable() {
            let tmp = this.status == "finished" || !this.mouseEnter;
            return tmp;
        }
    },
    methods: {
        handleMouseEnter() {
            if (this.isFinished) {
                return;
            }
            this.mouseEnter = true;
            this.$refs.input.focus();
        },
        handleClickOutside() {
            this.mouseEnter = false;
        },
        handleMouseLeave() {
            this.mouseEnter = false;
            this.$emit("change", this.inputValue);
        }
    }
};
</script>
<style>
.ivu-editinput {
    display: flex;
}
.ivu-editinput .label {
    font-size: 12px;
    line-height: 32px;
    display: inline-block;
    vertical-align: middle;
    box-sizing: border-box;
    color: #495060;
    font-weight: 1;
    text-align: right;
    padding: 10px 12px 10px 0;
}
.ivu-editinput .content {
    flex: 1;
    padding: 10px 0 10px 0;
    font-weight: 1;
    line-height: 32px;
    vertical-align: middle;
    box-sizing: border-box;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
</style>
