<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()
const now = ref(new Date())
let timer: number

onMounted(() => { timer = window.setInterval(() => now.value = new Date(), 1000) })
onUnmounted(() => clearInterval(timer))

function logout() {
  localStorage.removeItem('accessToken')
  router.push('/login')
}

const navGroups = [
  {
    label: '设备管理',
    items: [
      { name: '设备台账', path: '/device', icon: '📋' },
      { name: '设备分组', path: '/device/group', icon: '📁' },
      { name: '流量维保', path: '/device/maintenance', icon: '📶' },
    ]
  },
  {
    label: '工单调度',
    items: [
      { name: '工单调度', path: '/workorder', icon: '📝' },
    ]
  },
  {
    label: '用户权限',
    items: [
      { name: '用户管理', path: '/user', icon: '👥' },
      { name: '角色权限', path: '/role', icon: '🔑' },
    ]
  },
  {
    label: '巡逻管理',
    items: [
      { name: '排班管理', path: '/patrol/schedule', icon: '📅' },
      { name: '考勤统计', path: '/patrol/attendance', icon: '📊' },
    ]
  },
  {
    label: '系统运维',
    items: [
      { name: '系统参数', path: '/system/params', icon: '⚙️' },
      { name: '操作日志', path: '/system/log', icon: '📝' },
    ]
  },
]

const breadcrumbText = ref('')
function updateBreadcrumb() {
  const path = route.path
  for (const g of navGroups) {
    for (const item of g.items) {
      if (path.startsWith(item.path)) {
        breadcrumbText.value = item.name
        return
      }
    }
  }
  breadcrumbText.value = ''
}
onMounted(() => updateBreadcrumb())
</script>

<template>
  <div class="admin-shell">
    <!-- Header -->
    <header class="admin-header">
      <div class="admin-header-left">
        <span class="admin-logo">💧 合阳湿地后台管理</span>
        <a href="/nav" class="admin-back-link">← 返回导航</a>
      </div>
      <div class="admin-header-right">
        <span class="admin-time">{{ now.toLocaleString('zh-CN') }}</span>
        <span class="admin-user">管理员</span>
        <button class="admin-logout" @click="logout">退出</button>
      </div>
    </header>

    <div class="admin-body">
      <!-- Sidebar -->
      <aside class="admin-sidebar">
        <div v-for="group in navGroups" :key="group.label" class="sidebar-group">
          <div class="sidebar-group-label">{{ group.label }}</div>
          <router-link
            v-for="item in group.items"
            :key="item.path"
            :to="item.path"
            class="sidebar-item"
            :class="{ active: route.path.startsWith(item.path) }"
            @click="breadcrumbText = item.name"
          >
            <span class="sidebar-icon">{{ item.icon }}</span>
            <span>{{ item.name }}</span>
          </router-link>
        </div>
      </aside>

      <!-- Content -->
      <main class="admin-content">
        <div class="admin-content-header" v-if="breadcrumbText">
          <h2 class="admin-page-title">{{ breadcrumbText }}</h2>
        </div>
        <div class="admin-content-body">
          <slot />
        </div>
      </main>
    </div>
  </div>
</template>

<style scoped>
.admin-shell {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  background: #f0f2f5;
  font-family: "PingFang SC", "Microsoft YaHei", "Helvetica Neue", sans-serif;
  overflow: hidden;
}

/* Header */
.admin-header {
  height: 50px;
  background: #fff;
  border-bottom: 1px solid #e8e8e8;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  flex-shrink: 0;
  z-index: 100;
}
.admin-header-left {
  display: flex;
  align-items: center;
  gap: 16px;
}
.admin-logo {
  font-size: 15px;
  font-weight: 600;
  color: #001529;
}
.admin-back-link {
  font-size: 12px;
  color: #1890ff;
  text-decoration: none;
}
.admin-back-link:hover {
  color: #40a9ff;
}
.admin-header-right {
  display: flex;
  align-items: center;
  gap: 16px;
  font-size: 13px;
  color: #666;
}
.admin-time {
  color: #999;
}
.admin-user {
  color: #333;
}
.admin-logout {
  padding: 4px 12px;
  border: 1px solid #d9d9d9;
  border-radius: 4px;
  background: #fff;
  color: #ff4d4f;
  font-size: 12px;
  cursor: pointer;
  transition: all 0.2s;
}
.admin-logout:hover {
  border-color: #ff4d4f;
  background: #fff1f0;
}

/* Body */
.admin-body {
  flex: 1;
  display: flex;
  min-height: 0;
}

/* Sidebar */
.admin-sidebar {
  width: 200px;
  background: #001529;
  overflow-y: auto;
  flex-shrink: 0;
  padding: 8px 0;
}
.admin-sidebar::-webkit-scrollbar {
  width: 4px;
}
.admin-sidebar::-webkit-scrollbar-thumb {
  background: rgba(255,255,255,0.1);
  border-radius: 2px;
}

.sidebar-group {
  margin-bottom: 4px;
}
.sidebar-group-label {
  font-size: 11px;
  color: rgba(255,255,255,0.35);
  padding: 8px 16px 4px;
  text-transform: uppercase;
  letter-spacing: 1px;
}
.sidebar-item {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px 16px;
  font-size: 13px;
  color: rgba(255,255,255,0.65);
  text-decoration: none;
  transition: all 0.2s;
  cursor: pointer;
}
.sidebar-item:hover {
  color: #fff;
  background: rgba(255,255,255,0.05);
}
.sidebar-item.active {
  color: #fff;
  background: #1890ff;
}
.sidebar-icon {
  font-size: 14px;
  width: 16px;
  text-align: center;
}

/* Content */
.admin-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  min-width: 0;
  overflow: hidden;
}
.admin-content-header {
  background: #fff;
  padding: 12px 20px;
  border-bottom: 1px solid #e8e8e8;
  flex-shrink: 0;
}
.admin-page-title {
  font-size: 16px;
  font-weight: 600;
  color: #333;
  margin: 0;
}
.admin-content-body {
  flex: 1;
  overflow-y: auto;
  padding: 16px;
}
.admin-content-body::-webkit-scrollbar {
  width: 4px;
}
.admin-content-body::-webkit-scrollbar-thumb {
  background: #d9d9d9;
  border-radius: 2px;
}
</style>
