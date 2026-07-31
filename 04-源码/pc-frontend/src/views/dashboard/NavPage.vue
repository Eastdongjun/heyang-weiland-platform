<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const role = ref<'admin' | 'user'>(localStorage.getItem('userRole') as 'admin' | 'user' || 'admin')
const currentTime = ref(new Date().toLocaleString('zh-CN'))

setInterval(() => {
  currentTime.value = new Date().toLocaleString('zh-CN')
}, 1000)

const allModules = [
  { name: '指挥中心', icon: '📡', path: '/gis', role: 'all', desc: 'GIS大屏监控' },
  { name: '智慧安防', icon: '📹', path: '/monitor', role: 'all', desc: '视频监控管理' },
  { name: '环境监测', icon: '🌿', path: '/env', role: 'all', desc: '环境数据监测' },
  { name: '设备管理', icon: '⚙️', path: '/device', role: 'admin', desc: '设备台账维护' },
  { name: '告警中心', icon: '🔔', path: '/alert', role: 'all', desc: '告警查看与处置' },
  { name: '无人机管理', icon: '🛸', path: '/uav', role: 'all', desc: '无人机任务监控' },
  { name: '应急管理', icon: '🚨', path: '/emergency', role: 'all', desc: '应急预案与工单' },
  { name: '数据统计', icon: '📊', path: '/statistics', role: 'all', desc: '数据分析统计' },
  { name: '工单调度', icon: '📋', path: '/workorder', role: 'all', desc: '工单派发管理' },
  { name: '系统配置', icon: '🔧', path: '/system/params', role: 'admin', desc: '系统参数配置' },
]

const modules = computed(() =>
  allModules.filter(m => m.role === 'all' || m.role === role.value)
)

function toggleRole() {
  role.value = role.value === 'admin' ? 'user' : 'admin'
  localStorage.setItem('userRole', role.value)
}

function logout() {
  localStorage.removeItem('token')
  localStorage.removeItem('userRole')
  router.push('/login')
}
</script>

<template>
  <div class="min-h-screen bg-[#0A1628] flex flex-col">
    <!-- Header -->
    <header class="h-16 bg-[#0A1628] border-b border-[rgba(0,191,255,0.12)] flex items-center justify-between px-8 shrink-0 relative">
      <div class="absolute bottom-0 left-[15%] right-[15%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="w-[200px]" />
      <div class="text-center">
        <div class="text-xl font-bold tracking-[4px]"
          style="background: linear-gradient(90deg,#E8EDF5,#33CCFF); -webkit-background-clip:text; -webkit-text-fill-color:transparent;">
          合阳洽川湿地智慧管理平台
        </div>
        <div class="text-[11px] text-[#8899BB] tracking-[2px] mt-0.5">NAVIGATION CENTER</div>
      </div>
      <div class="flex items-center gap-5 w-[200px] justify-end">
        <span class="text-base font-semibold text-[#33CCFF] font-mono">{{ currentTime.split(' ')[1] || currentTime }}</span>
        <div class="flex items-center gap-1.5 px-2.5 py-1 rounded-md bg-[rgba(0,191,255,0.06)] border border-[rgba(0,191,255,0.15)] text-xs text-[#8899BB] cursor-pointer hover:border-[#00BFFF] hover:text-[#00BFFF] transition-colors"
          @click="toggleRole">
          <span class="w-7 h-7 rounded-full bg-gradient-to-br from-[rgba(0,191,255,0.6)] to-[#00BFFF] flex items-center justify-center text-xs font-bold text-[#0A1628]">管</span>
          <span>{{ role === 'admin' ? '管理员' : '普通用户' }}</span>
        </div>
        <button class="px-3 py-1 border border-[rgba(0,191,255,0.15)] rounded-md bg-transparent text-xs text-[#8899BB] hover:border-[#FF3366] hover:text-[#FF3366] transition-colors"
          @click="logout">退出</button>
      </div>
    </header>

    <!-- Grid -->
    <main class="flex-1 flex items-center justify-center p-6 relative">
      <div class="absolute inset-0 opacity-[0.02]"
        style="background-image: linear-gradient(rgba(0,191,255,0.5) 1px,transparent 1px), linear-gradient(90deg,rgba(0,191,255,0.5) 1px,transparent 1px); background-size: 50px 50px;" />

      <div class="relative z-10 grid grid-cols-5 gap-4 max-w-[1100px] w-full">
        <div
          v-for="m in modules"
          :key="m.name"
          class="bg-[#0F1E37] border border-[rgba(0,191,255,0.15)] rounded-lg p-6 cursor-pointer transition-all duration-200 hover:bg-[#1A2D4A] hover:border-[rgba(0,191,255,0.3)] hover:shadow-[0_0_20px_rgba(0,191,255,0.1)] hover:-translate-y-0.5 group"
          @click="router.push(m.path)"
        >
          <div class="text-3xl mb-3">{{ m.icon }}</div>
          <div class="text-sm font-semibold text-[#E8EDF5] mb-1 group-hover:text-[#00BFFF] transition-colors">{{ m.name }}</div>
          <div class="text-[11px] text-[#8899BB]">{{ m.desc }}</div>
        </div>
      </div>
    </main>
  </div>
</template>
