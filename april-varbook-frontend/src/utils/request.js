import axios from 'axios'
import errorCode from '@/utils/errorCode'
import {ElMessage, ElNotification} from 'element-plus'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: import.meta.env.VITE_APP_BASE_API,
    // 超时
    timeout: 1000 * 180
})

// request拦截器
service.interceptors.request.use(
    (config) => {
        // get请求映射params参数
        if (config.method === 'get' && config.params) {
            let url = `${config.url}?${tansParams(config.params)}`
            url = url.slice(0, -1)
            config.params = {}
            config.url = url
        }
        return config
    },
    (error) => {
        Promise.reject(error)
    }
)

// 响应拦截器
service.interceptors.response.use(
    (res) => {
        // 未设置状态码则默认成功状态
        const code = res.data.code || 200
        // 获取错误信息
        const msg = errorCode[code] || res.data.msg || errorCode['default']
        // 二进制数据则直接返回
        if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
            return res.data
        } else if (code === 500) {
            ElMessage({message: msg, type: 'error'})
            return Promise.reject(new Error(msg))
        } else if (code !== 200) {
            ElNotification.error({title: msg})
            return Promise.reject('error')
        } else {
            return Promise.resolve(res.data.data)
        }
    },
    (error) => {
    }
)
export default service
