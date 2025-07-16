import request from '@/utils/request'
// 翻译
const translate = (params) =>
    request({
        url: '/translate/translate',
        method: 'post',
        params
    })

// 统计
const total = () =>
    request({
        url: '/translate/total',
        method: 'get'
    })

export {translate, total}
