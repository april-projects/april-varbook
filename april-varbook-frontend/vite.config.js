import vue from '@vitejs/plugin-vue'
import path from 'path'
import AutoImport from 'unplugin-auto-import/vite'
import ElementPlus from 'unplugin-element-plus/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import Components from 'unplugin-vue-components/vite'
import {defineConfig, loadEnv} from 'vite'

export default defineConfig(({mode, command}) => {
    const env = loadEnv(mode, process.cwd())
    console.log(env)
    const {VITE_APP_ENV} = env
    return {
        // 添加此行代码
        lintOnSave: false,
        // 部署生产环境和开发环境下的URL。
        // 默认情况下，vite 会假设你的应用是被部署在一个域名的根路径上
        // 例如 https://www.xxx.vip/。如果应用被部署在一个子路径上，你就需要用这个选项指定这个子路径。例如，如果你的应用被部署在 https://www.xxx.vip/admin/，则设置 baseUrl 为 /admin/。
        base: VITE_APP_ENV === 'production' ? '/' : '/',
        plugins: [
            vue(),
            ElementPlus({}),
            AutoImport({
                resolvers: [ElementPlusResolver()]
            }),
            Components({
                resolvers: [ElementPlusResolver()]
            })
        ],
        resolve: {
            // https://cn.vitejs.dev/config/#resolve-alias
            alias: {
                // 设置路径
                '~': path.resolve(__dirname, './'),
                // 设置别名
                '@': path.resolve(__dirname, './src'),
            },
            // https://cn.vitejs.dev/config/#resolve-extensions
            extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue'],
        },
        // vite 相关配置
        server: {
            port: 8888,
            host: true,
            open: true,
            proxy: {
                // https://cn.vitejs.dev/config/#server-proxy
                '/dev-api': {
                    target: 'http://127.0.0.1:9090',
                    changeOrigin: true,
                    rewrite: p => p.replace(/^\/dev-api/, ''),
                },
            },
        },
        //fix:error:stdin>:7356:1: warning: "@charset" must be the first rule in the file
        css: {
            postcss: {
                plugins: [
                    {
                        postcssPlugin: 'internal:charset-removal',
                        AtRule: {
                            charset: atRule => {
                                if (atRule.name === 'charset') {
                                    atRule.remove()
                                }
                            },
                        },
                    },
                ],
            },
        },
    }
})

