<template>
    <div :class="wrapClass">
        <span>商品分类</span>
        <div></div>
    </div>
</template>

<script>
const prefixCls = "unind-hs";
export default {
  computed: {
    wrapClass() {
      return `${prefixCls}-cata`;
    }
  }
};
</script>
<style>
</style>
