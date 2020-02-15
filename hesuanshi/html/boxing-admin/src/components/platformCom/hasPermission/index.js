const hasPermission = {
    install (Vue, options){
        Vue.directive('hasPermission', {
            bind(el, binding, vnode){
                let permissionList = localStorage.getItem("permissions");
                
                if (!permissionList || !permissionList.includes(binding.value)) {
                    el.parentNode.removeChild(el);
                }
            }
        })
    }
}

export default hasPermission