<template>
<div>
    <Button type="primary" @click="showDialog">客制化---</Button>
    <cust-dialog ref="custDialog" catelog="PermBo" rid="5"></cust-dialog>
</div>
</template>
<script>
import custDialog from '../../components/business-component/custDialog.vue';

export default {
    components: {
        custDialog
    },
    data () {
        return {
        }
    },
    methods: {
        showDialog() {
            this.$refs.custDialog.showDialog(5);
        },
        ok () {
            this.$Message.info('Clicked ok');
        },
        cancel () {
            this.$Message.info('Clicked cancel');
        }
    }
}
</script>