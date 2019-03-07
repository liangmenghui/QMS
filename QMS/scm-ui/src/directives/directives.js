import Vue from 'vue'
import Util from '../libs/util';

const link = Vue.directive('link', {
    inserted: function (el, value) {
        el.addEventListener("click", function (e) {
            location.href = value.value.path
        })
    }
})

function permission(el,perm,action) {
    if(!Util.hasPerm(perm)){
        if(action == undefined || action == 'remove')
            el.parentNode.removeChild(el);
        else el[action] = true;
    }
}

const perm = Vue.directive('perm',{
    bind: function (el, binding) {
        var params = binding.expression.split(',');
        if(params.length ==0 ) return;
        if(params.length == 1) params.push('remove');
        permission(el,params[0],params[1]);
    }
});

const permAdd = Vue.directive('perm-add',{
    bind: function (el, binding) {
        permission(el,'ADD',binding.expression);
    }
})

const permEdit = Vue.directive('perm-edit',{
    bind: function (el, binding) {
        permission(el,'EDIT',binding.expression);
    }
})

const permDelete = Vue.directive('perm-delete',{
    bind: function (el, binding) {
        permission(el,'DELETE',binding.expression);
    }
})

const permQuery = Vue.directive('perm-query',{
    bind: function (el, binding) {
        permission(el,'QUERY',binding.expression);
    }
})

const permImport = Vue.directive('perm-import',{
    bind: function (el, binding) {
        permission(el,'IMPORT',binding.expression);
    }
})

const permExport = Vue.directive('perm-export',{
    bind: function (el, binding) {
        permission(el,'EXPORT',binding.expression);
    }
})

const permVerify = Vue.directive('perm-verify',{
    bind: function (el, binding) {
        permission(el,'VERIFY',binding.expression);
    }
})

const permAlloc = Vue.directive('perm-alloc',{
    bind: function (el, binding) {
        permission(el,'ALLOC',binding.expression);
    }
})



export {
    link,
    permAdd,
    permEdit,
    permQuery,
    permDelete,
    permImport,
    permExport,
    permAlloc,
    permVerify
}