<template>
  <div ref="file" class="fs-release-filename">
    <div class="label">文件名:</div>
    <div class="body">
      <Tooltip v-if="readonly">
        <span>{{value}}</span>
        <div slot="content" style="white-space: normal;">
          {{value}}
        </div>
      </Tooltip>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    value: String,
    readonly: {
      type: Boolean,
      default: true
    }
  }
};
</script>
<style>
.fs-release-filename {
  display: flex;
}
.fs-release-filename .label {
  display: inline-block;
  vertical-align: middle;
  box-sizing: border-box;
  color: #495060;
  font-weight: 1;
  text-align: right;
  padding: 10px 12px 10px 0;
  font-size: 12px;
}
.fs-release-filename .body {
  flex: 1;
  padding: 10px 0 10px 0;
  font-weight: 1;
  text-align: left;
  vertical-align: middle;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
