import Mock from 'mockjs'
import {baseUrl} from '@/utils/global'
import * as login from './modules/login'
import * as user from './modules/user'
import * as role from './modules/role'
import * as dept from './modules/dept'
import * as menu from './modules/menu'
import * as dict from './modules/dict'
import * as config from './modules/config'
import * as log from './modules/log'
import * as loginlog from './modules/loginlog'

let openMock = false

fnCreate(login,openMock)
fnCreate(user,openMock)
fnCreate(role,openMock)
fnCreate(dept,openMock)
fnCreate(menu,openMock)
fnCreate(dict,openMock)
fnCreate(config,openMock)
fnCreate(log,openMock)
fnCreate(loginlog,openMock)

function fnCreate (mod,isOpen = true){
    if (isOpen) {
        for (var key in mod) {
            ((res) => {
                if (res.isOpen != false) {
                    let url = baseUrl
                    if(!url.endsWith("/")){
                        url = url + "/"
                    }
                    url = url + res.url
                    Mock.mock(new RegExp(url),res.type,(opts) => {
                        opts['data'] = opts.body ? JSON.parse(opts.body) : null
                        delete opts.body
                        console.log("\n")
                        console.log('%comock拦截,请求：','color:blue',opts)
                        console.log('%comock拦截,响应','color:blue',res.data)
                        return res.data
                    })
                }
            }) (mod[key]() || {})
        }
    }
}