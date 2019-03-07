<template>
  <div class="dashboard-container">
    <component :is="currentRole"/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import adminDashboard from './admin'
import editorDashboard from './editor'
import addSupplier from '@/views/supplier/addSupplier.vue'
import { getSupplierByLoginName} from '@/api/supplier'

export default {
  name: 'Dashboard',
  components: { adminDashboard, editorDashboard ,addSupplier},
  data() {
    return {
      currentRole: '',
      formQuery: {
        loginName: ''
      }
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  created() {

    if(this.roles.includes('admin')){
      this.currentRole = 'adminDashboard'
    }else if(this.roles.includes('supplier_grade_tobe')){
      getSupplierByLoginName(this.name).then(response => {
        if (!response.result) {
          this.$Message.error(response.msg);
          return
        }
        this.$store.commit("updateSupplierDataStates", response.data.rows[0]);
        console.log(response.data.rows[0]);
        this.currentRole = 'addSupplier'
      })
      
    }else{
      this.currentRole = 'editorDashboard'
    }
  }
}
</script>
