<script setup>
import { onLaunch, onShow, onHide } from '@dcloudio/uni-app'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()

onLaunch(() => {
  console.log('🌿 湿地卫士 App 启动')
  userStore.checkLogin()
})

onShow(() => {
  console.log('App 显示')
})

onHide(() => {
  console.log('App 隐藏')
})
</script>

<style>
/* 全局样式 */
page {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
  font-size: 14px;
  color: #333;
  background-color: #f5f5f5;
}

/* uView 主题色覆盖 */
:root {
  --primary-color: #1a7a3a;
  --primary-dark: #145a2c;
  --danger-color: #e53935;
  --warning-color: #f57c00;
  --info-color: #1976d2;
  --success-color: #43a047;
}
</style>
