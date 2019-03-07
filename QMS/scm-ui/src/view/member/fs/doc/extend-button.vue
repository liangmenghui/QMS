<template>
  <Poptip trigger="hover" placement="bottom-end" :transfer="true" popper-class="ivu-extend-button">
    <Button type="success" size="small">{{text}}</Button>
    <div slot="content">
      <slot></slot>
    </div>
  </Poptip>
</template>
<script>
export default {
  name: "ExtendButton",
  props: {
    text: {
      type: String,
      required: true
    }
  },
  data() {
    return {};
  }
};
</script>
<style>
.ivu-extend-button .ivu-poptip-body-content {
  overflow: hidden;
  min-height: 60px;
  padding: 3px 8px;
}
</style>
