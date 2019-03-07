import Cookies from 'js-cookie';
var mStorage = window.localStorage;

const supplierData = {
    state: {
        supplierData: mStorage.getItem('supplierData'),
    },

    getters: {
        getSupplierData: (state)=>state.supplierData == null?{}:JSON.parse(state.supplierData),
    },

  mutations: {
  /*  SET_CODE: (state, code) => {
      state.code = code
    },*/
    updateSupplierDataStates(state,_supplierData) {
        state.supplierData = JSON.stringify(_supplierData);
        mStorage.setItem('supplierData', state.supplierData);
    },

  },

  actions: {
 


  }
}

export default supplierData
