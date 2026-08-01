// 🌿 湿地卫士 - H5 客户端入口
// uni-app 3.0 H5 模式需要显式创建并挂载 App

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import App from './App.vue'

const app = createApp(App)
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
app.use(pinia)
app.mount('#app')

console.log('🌿 湿地卫士 H5 启动成功')
