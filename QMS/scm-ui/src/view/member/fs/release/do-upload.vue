<template>
    <ul :class="classes">
        <li v-if="progress.showProgress">
            <transition name="fade">
                <i-progress :stroke-width="2" :percent="percent" :status="progressStatus"></i-progress>
            </transition>
        </li>
        <li :class="textCls">
            <Tooltip>
                <span>{{message}}</span>
                <div slot="content" style="white-space: normal;">
                    {{message}}
                </div>
            </Tooltip>
        </li>
    </ul>
</template>
<script>
import ajax from "@/libs/ajax";
import emitter from "../mixins/emitter";
const prefixCls = "fs-release-upload";
const aaaaindex = 0;
export default {
    name: "fsReleaseDoUpload",
    mixins: [emitter],
    props: {
        status: String,
        exception: [String, Error],
        file: File,
        format: {
            type: String,
            default: ""
        },
        maxSize: {
            type: Number
        },
        name: {
            type: String,
            default: "file"
        },
        headers: {
            type: Object,
            default() {
                return {};
            }
        },
        action: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            progress: {
                name: "",
                size: 0,
                percentage: 0,
                uid: "",
                showProgress: false,
                status: this.status,
                exception: this.exception
            },
            data: {},
            msg: ""
        };
    },
    computed: {
        message() {
            let msg = "";
            if (this.progress.status === "finished") {
                msg = "上传成功";
            } else if (this.progress.status === "fail") {
                msg = "上传失败";
                if (this.progress.exception) {
                    msg = this.progress.exception;
                }
            }
            return msg;
        },
        classes() {
            return `${prefixCls}`;
        },
        textCls() {
            return [
                `${prefixCls}-text`,
                {
                    [`${prefixCls}-success`]:
                        this.progress.status === "finished",
                    [`${prefixCls}-error`]: this.progress.status === "fail",
                    [`${prefixCls}-normal`]:
                        this.progress.status !== "finished" &&
                        this.progress.status !== "fail"
                }
            ];
        },
        percent() {
            let val = this.progress.percentage;
            if (!val) return 0;
            return parseInt(val, 10);
        },
        progressStatus() {
            return this.progress.status === "finished" &&
                this.progress.showProgress
                ? "success"
                : "normal";
        }
    },
    methods: {
        handleProgress(e, file) {
            this.progress.percentage = e.percent || 0;
        },
        handleSuccess(res, file) {
            if (res.result) {
                this.progress.status = "finished";
            } else {
                this.progress.status = "fail";
                this.progress.exception = res.msg;
            }
            this.progress.response = res;
            this.$emit("change", this.progress);
            setTimeout(() => {
                this.progress.showProgress = false;
            }, 1000);
        },
        handleError(err, response, file) {
            this.progress.status = "fail";
            this.progress.exception = err ? err.message : "";
            this.$emit("change", this.progress);
            setTimeout(() => {
                this.progress.showProgress = false;
            }, 1000);
        },
        post() {
            if (!this.file) return;
            if (this.format.length) {
                const _file_format = this.file.name
                    .split(".")
                    .pop()
                    .toLocaleLowerCase();
                const checked = this.format.some(
                    item => item.toLocaleLowerCase() === _file_format
                );
                if (!checked) {
                    this.$Message.error("文件格式错误");
                    return false;
                }
            }
            // check maxSize
            if (this.maxSize) {
                if (this.file.size > this.maxSize * 1024) {
                    this.$Message.error("文件太大");
                    return false;
                }
            }

            this.handleStart(this.file);
            let formData = new FormData();
            formData.append(this.name, this.file);

            ajax({
                headers: this.headers,
                withCredentials: false,
                file: this.file,
                data: this.data,
                filename: this.name,
                action: this.action,
                onProgress: e => {
                    this.handleProgress(e, this.file);
                },
                onSuccess: res => {
                    this.handleSuccess(res, this.file);
                },
                onError: (err, response) => {
                    this.handleError(err, response, this.file);
                }
            });
        },
        handleStart(file) {
            file.uid = Date.now() + this.tempIndex++;
            const _file = {
                status: "uploading",
                name: file.name,
                size: file.size,
                percentage: 0,
                uid: file.uid,
                showProgress: true,
                exception: ""
            };

            this.progress = _file;
        },
        parsePercentage(val) {
            if (!val) return 0;
            return parseInt(val, 10);
        }
    }
};
</script>
<style>
.fs-release-upload {
    list-style: none;
    box-sizing: border-box;
    display: flex;
}
.fs-release-upload li:first-child {
    padding: 10px 0 12px 0;
    flex: 1;
}
.fs-release-upload .fs-release-upload-text {
    width: 70px;
    padding: 10px 0 12px 0;
    box-sizing: border-box;
    overflow: hidden;
    text-overflow: ellipsis;
    -ms-text-overflow: ellipsis;
    -o-text-overflow: ellipsis;
    white-space: nowrap;
}
.fs-release-upload .fs-release-upload-success {
    color: #19be6b;
}
.fs-release-upload .fs-release-upload-error {
    color: #ed3f14;
}
.fs-release-upload .fs-release-upload-normal {
    color: #495060;
}
</style>
