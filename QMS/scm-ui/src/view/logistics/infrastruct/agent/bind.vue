<template>
    <div>
        <Button type="primary" size="small" @click="handleUpdate">编 辑</Button>
        <Modal v-model="visible" :loading="loading" @on-ok="handleSubmit">
            <Transfer :data="unselectedItems" :target-keys="selectedItems" :render-format="renderFormat" filterable>

            </Transfer>
        </Modal>
    </div>
</template>

<script>
export default {
  props: {
    value: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      visible: false,
      loading: true,
      unselectedItems: this.getUnselectedItems(),
      selectedItems: this.getSelectedItems()
    };
  },
  methods: {
    getUnselectedItems() {
    },
    getSelectedItems() {},
    renderFormat(displayItem) {},
    handleSubmit() {}
  }
};
</script>
