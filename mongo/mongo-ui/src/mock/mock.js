import Mock from 'mockjs'

Mock.mock('http://localhost:8080/user',{
    'name': '@name',//随机生成姓名
    'email': '@email',//随机生成邮箱
    'age|1-10': 5,//年龄在1-10之间
})

Mock.mock('http://localhost:8080/menu',{
    'id': '@increment',//id自增
    'menu': 'menu',//名称为menu
    'order|1-20': 5,//排序在1-20之间
})

Mock.mock('http://localhost:8001/login',{'token':'123j123lk31joi'})