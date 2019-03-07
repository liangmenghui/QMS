import Cookies from 'js-cookie';
var mStorage = window.localStorage;

const materiel = {
    state: {
        metaData: mStorage.getItem('metaData'),
    },

    getters: {
        getMetaData: (state)=>state.metaData == null?{}:JSON.parse(state.metaData),
    },

  mutations: {
  /*  SET_CODE: (state, code) => {
      state.code = code
    },*/
    updateMetaDataStates(state,_metaData) {
        state.metaData = JSON.stringify(_metaData);
        mStorage.setItem('metaData', state.metaData);
    },

  },

  actions: {
 


  }
}

export default materiel
