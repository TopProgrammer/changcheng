var app1 = new Vue({
    el: '.login-title',
    data: {
        title: '长城试剑管理中心'
    },

});


var app2 = new Vue({
    el: '#login-form',
    data: {
        username: 'wangzhicheng',
        password: '12345678'
    },
    methods:{
        reverseMessage:function(){
            app1.title = app1.title.split('').reverse().join('');
        }
    }
});

Vue.component('todo-item', {
    // todo-item 组件现在接受一个
    // "prop"，类似于一个自定义特性。
    // 这个 prop 名为 todo。
    props: ['todo'],
    template: '<li>{{ todo.text }}</li>'
});
// 按照顺序
var app = new Vue({
    el: '#app',
    data: {
        todos:[{id:0,text:'长城试剑中心是一个好的平台'},{id:1,text:'纵毕生所学，造一家平台'}]
    }
});