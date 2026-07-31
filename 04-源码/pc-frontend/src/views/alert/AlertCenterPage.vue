<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const now = ref(new Date())
let timer: number
const activeTab = ref('all')

onMounted(() => { timer = window.setInterval(() => now.value = new Date(), 1000) })
onUnmounted(() => clearInterval(timer))

const alerts = [
  { id: 1, type: '非法闯入', time: '10:42:18', location: '北门湿地保护区入口', status: 'pending', statusLabel: '待处理', source: 'AI视频识别', level: 'danger' },
  { id: 2, type: '水质异常', time: '10:38:05', location: 'C区采样点3号', status: 'processing', statusLabel: '处理中', source: '传感器#S-07', level: 'warning' },
  { id: 3, type: '烟火检测', time: '10:25:33', location: '东岸芦苇荡区域', status: 'processing', statusLabel: '处理中', source: '无人机热成像', level: 'danger' },
  { id: 4, type: '设备离线', time: '10:12:00', location: '西门球机#CAM-12', status: 'resolved', statusLabel: '已处理', source: '网络恢复', level: 'info' },
  { id: 5, type: '溶解氧偏低', time: '09:58:22', location: 'B区深水区监测站', status: 'pending', statusLabel: '待处理', source: '水质传感器', level: 'warning' },
  { id: 6, type: '风速超限', time: '09:45:10', location: '气象监测站', status: 'resolved', statusLabel: '已处理', source: '自动恢复', level: 'info' },
  { id: 7, type: '非法捕捞', time: '09:30:05', location: '核心保护区水域', status: 'resolved', statusLabel: '已处理', source: '巡逻队 dispatched', level: 'danger' },
]

const tabs = [
  { key: 'all', label: '全部' },
  { key: 'pending', label: '待处理' },
  { key: 'processing', label: '处理中' },
  { key: 'resolved', label: '已处理' },
]

const navItems = [
  { name: 'GIS地图', path: '/gis' },
  { name: '视频监控', path: '/monitor' },
  { name: '无人机', path: '/uav' },
  { name: '环境监测', path: '/env' },
  { name: '告警中心', path: '/alert', active: true },
  { name: '数据统计', path: '/statistics' },
  { name: '应急管理', path: '/emergency' },
  { name: 'VR查看', path: '/vr' },
]

const navSvgs: Record<string, string> = {
  'GIS地图': '<circle cx="12" cy="10" r="3"/><path d="M12 2C7 2 3 6.5 3 12c0 2 .5 3.5 1.5 5L12 22l7.5-5c1-1.5 1.5-3 1.5-5 0-5.5-4-10-9-10z"/>',
  '视频监控': '<rect x="2" y="4" width="16" height="14" rx="2"/><polygon points="23,7 19,10 19,14 23,17"/>',
  '无人机': '<path d="M12 2L9 7l-4 1 3 2.5L7 15l5-3 5 3-1-4.5L19 8l-4-1z"/><line x1="12" y1="18" x2="12" y2="2"/>',
  '环境监测': '<path d="M17 8C15 5 9 5 7 8c-3 4 0 9 5 12 5-3 8-8 5-12z"/><line x1="12" y1="20" x2="12" y2="11"/>',
  '告警中心': '<path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/>',
  '数据统计': '<rect x="3" y="14" width="4" height="8" rx="1"/><rect x="10" y="8" width="4" height="14" rx="1"/><rect x="17" y="4" width="4" height="18" rx="1"/>',
  '应急管理': '<path d="M12 2L3 7v6c0 5.5 4 10.5 9 11 5-.5 9-5.5 9-11V7l-9-5z"/><line x1="12" y1="8" x2="12" y2="14"/><circle cx="12" cy="17" r="1"/>',
  'VR查看': '<rect x="2" y="6" width="20" height="12" rx="3"/><circle cx="8" cy="12" r="1.5"/><circle cx="16" cy="12" r="1.5"/><line x1="12" y1="9" x2="12" y2="15"/>',
}

const barData = [
  { h: '32%', label: '05-06' }, { h: '48%', label: '05-07' }, { h: '28%', label: '05-08' },
  { h: '56%', label: '05-09' }, { h: '40%', label: '05-10' }, { h: '72%', label: '05-11' }, { h: '60%', label: '今日' },
]
</script>

<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden font-['PingFang_SC','Microsoft_YaHei','Helvetica_Neue',sans-serif]">

    <!-- Top Bar -->
    <header class="h-16 bg-gradient-to-b from-[rgba(10,22,40,0.98)] to-[rgba(10,22,40,0.85)] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-7 shrink-0 relative z-[100]">
      <div class="absolute bottom-0 left-[10%] right-[10%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="w-60 text-sm font-semibold text-[#00BFFF] font-mono">{{ now.getFullYear() }}年{{ now.getMonth()+1 }}月{{ now.getDate() }}日</div>
      <div class="absolute left-1/2 -translate-x-1/2 text-center">
        <div class="text-xl font-bold tracking-[4px] bg-gradient-to-r from-white to-[#00BFFF] bg-clip-text text-transparent">洽川湿地告警中心</div>
        <div class="text-[10px] text-[#8899AA] tracking-[1px] mt-0.5">实时告警 · 智能处置</div>
      </div>
      <div class="flex items-center gap-5 text-[13px] text-[#CCCCCC]">
        <span class="flex items-center gap-1 text-[#8899AA]">☀️ 26°C · 晴</span>
        <span class="text-[22px] font-bold text-[#00BFFF] font-mono">{{ now.toLocaleTimeString('zh-CN',{hour12:false}) }}</span>
        <a href="/nav" class="text-xs text-[#8899AA] no-underline px-2.5 py-1 border border-[rgba(0,191,255,0.15)] rounded">退出</a>
      </div>
    </header>

    <!-- Main -->
    <div class="flex-1 flex min-h-0 relative">
      <div class="flex-1 flex min-h-0 gap-2 p-2">

        <!-- LEFT: Alert List -->
        <div class="w-[260px] shrink-0 flex flex-col gap-2 min-h-0 border border-[rgba(0,191,255,0.15)] rounded p-2">
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md flex-1 min-h-0 flex flex-col relative overflow-hidden">
            <div class="absolute top-0 left-0 w-3 h-3 border-t-2 border-l-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="absolute top-0 right-0 w-3 h-3 border-t-2 border-r-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="absolute bottom-0 left-0 w-3 h-3 border-b-2 border-l-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="absolute bottom-0 right-0 w-3 h-3 border-b-2 border-r-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] px-3.5 pt-3 pb-2.5 flex items-center gap-2 shrink-0"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />实时告警</div>
            <!-- Filter Tabs -->
            <div class="flex gap-1 px-3.5 pb-2.5 border-b border-[rgba(0,191,255,0.15)] shrink-0">
              <button v-for="t in tabs" :key="t.key" class="px-3 py-1 text-xs rounded border border-transparent transition-all duration-200"
                :class="activeTab === t.key ? 'bg-[rgba(0,191,255,0.12)] text-[#00BFFF] border-[rgba(0,191,255,0.25)]' : 'text-[#8899AA] hover:bg-[rgba(0,191,255,0.08)] hover:text-[#E8EDF5]'"
                @click="activeTab = t.key">{{ t.label }}</button>
            </div>
            <!-- Alert Items -->
            <div class="flex-1 overflow-y-auto px-3.5 py-2.5">
              <div v-for="a in alerts" :key="a.id"
                class="flex items-start gap-2.5 py-2.5 border-b border-[rgba(0,191,255,0.06)] cursor-pointer transition-all duration-200 hover:bg-[rgba(0,191,255,0.04)] -mx-3.5 px-3.5"
                :class="{ 'border-b-0': a.id === alerts.length }"
                @click="router.push(`/alert/${a.id}`)">
                <div class="w-[3px] h-9 rounded-sm shrink-0 mt-0.5" :class="{
                  'bg-[#FF3366] shadow-[0_0_6px_rgba(255,51,102,0.3)]': a.level === 'danger',
                  'bg-[#FFCC00]': a.level === 'warning',
                  'bg-[#40a9ff]': a.level === 'info',
                }" />
                <div class="flex-1 min-w-0">
                  <div class="flex justify-between items-center mb-1">
                    <span class="text-[13px] font-semibold text-[#E8EDF5]">{{ a.type }}</span>
                    <span class="text-[11px] text-[#8899AA] font-mono shrink-0">{{ a.time }}</span>
                  </div>
                  <div class="text-xs text-[#8899AA] mb-1.5">📍 {{ a.location }}</div>
                  <div class="flex justify-between items-center">
                    <span class="text-[11px] px-2 py-0.5 rounded-[10px] border"
                      :class="{
                        'bg-[rgba(255,51,102,0.1)] text-[#FF3366] border-[rgba(255,51,102,0.2)]': a.status === 'pending',
                        'bg-[rgba(255,204,0,0.1)] text-[#FFCC00] border-[rgba(255,204,0,0.2)]': a.status === 'processing',
                        'bg-[rgba(120,210,180,0.1)] text-[#00BFFF] border-[rgba(120,210,180,0.2)]': a.status === 'resolved',
                      }">{{ a.statusLabel }}</span>
                    <span class="text-[11px] text-[#8899AA]">{{ a.source }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- CENTER: Map -->
        <div class="flex-1 flex flex-col min-h-0 border border-[rgba(0,191,255,0.15)] rounded overflow-hidden">
          <div class="flex-1 flex items-center justify-center relative">
            <div class="text-5xl opacity-15">🗺️</div>
            <div class="absolute bottom-4 left-4 text-[11px] text-[#8899AA]">告警点位分布 · 高德地图</div>
            <!-- Alert markers (CSS only, no map lib) -->
            <div v-for="(a, i) in alerts" :key="'m'+i"
              class="absolute w-3 h-3 rounded-full animate-pulse"
              :style="{
                left: `${15 + (i * 12)}%`,
                top: `${20 + (i % 3) * 25}%`,
                background: a.level === 'danger' ? '#FF3366' : a.level === 'warning' ? '#FFCC00' : '#40a9ff',
                boxShadow: `0 0 8px ${a.level === 'danger' ? '#FF3366' : a.level === 'warning' ? '#FFCC00' : '#40a9ff'}`
              }" />
          </div>
        </div>

        <!-- RIGHT: Statistics -->
        <div class="w-[280px] shrink-0 flex flex-col gap-2 min-h-0 border border-[rgba(0,191,255,0.15)] rounded p-2">
          <!-- KPI -->
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md relative overflow-hidden shrink-0">
            <div class="absolute top-0 left-0 w-3 h-3 border-t-2 border-l-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="absolute top-0 right-0 w-3 h-3 border-t-2 border-r-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] px-3.5 pt-3 pb-2.5 flex items-center gap-2"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />今日概览</div>
            <div class="grid grid-cols-2 gap-2.5 px-3.5 pb-3">
              <div class="bg-[rgba(0,191,255,0.04)] border border-[rgba(0,191,255,0.1)] rounded p-3 text-center">
                <div class="text-2xl font-bold text-[#E8EDF5] font-mono mb-1">27</div>
                <div class="text-[11px] text-[#8899AA]">告警总数</div>
              </div>
              <div class="bg-[rgba(0,191,255,0.04)] border border-[rgba(0,191,255,0.1)] rounded p-3 text-center">
                <div class="text-2xl font-bold text-[#FF3366] font-mono mb-1">5</div>
                <div class="text-[11px] text-[#8899AA]">待处理</div>
              </div>
              <div class="bg-[rgba(0,191,255,0.04)] border border-[rgba(0,191,255,0.1)] rounded p-3 text-center">
                <div class="text-2xl font-bold text-[#FFCC00] font-mono mb-1">3</div>
                <div class="text-[11px] text-[#8899AA]">处理中</div>
              </div>
              <div class="bg-[rgba(0,191,255,0.04)] border border-[rgba(0,191,255,0.1)] rounded p-3 text-center">
                <div class="text-2xl font-bold text-[#00BFFF] font-mono mb-1">19</div>
                <div class="text-[11px] text-[#8899AA]">已处理</div>
              </div>
            </div>
          </div>

          <!-- Pie -->
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md relative overflow-hidden shrink-0">
            <div class="absolute top-0 left-0 w-3 h-3 border-t-2 border-l-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="absolute top-0 right-0 w-3 h-3 border-t-2 border-r-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] px-3.5 pt-3 pb-2.5 flex items-center gap-2"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />告警类型分布</div>
            <div class="flex items-center gap-4 px-3.5 pb-3">
              <div class="w-[90px] h-[90px] rounded-full shrink-0 shadow-[0_0_12px_rgba(0,191,255,0.1)]"
                style="background: conic-gradient(#FF3366 0deg 126deg, #FFCC00 126deg 216deg, #40a9ff 216deg 288deg, #00BFFF 288deg 360deg)" />
              <div class="flex flex-col gap-1.5">
                <div v-for="(l, i) in [{c:'#FF3366',n:'安防事件',p:'35%'},{c:'#FFCC00',n:'环境异常',p:'25%'},{c:'#40a9ff',n:'设备故障',p:'20%'},{c:'#00BFFF',n:'消防预警',p:'20%'}]" :key="i"
                  class="flex items-center gap-1.5 text-xs text-[#8899AA]">
                  <span class="w-2 h-2 rounded-sm" :style="{background:l.c}" />{{ l.n }} {{ l.p }}
                </div>
              </div>
            </div>
          </div>

          <!-- Bar Chart -->
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md relative overflow-hidden flex-1 min-h-0 flex flex-col">
            <div class="absolute top-0 left-0 w-3 h-3 border-t-2 border-l-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="absolute top-0 right-0 w-3 h-3 border-t-2 border-r-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="absolute bottom-0 left-0 w-3 h-3 border-b-2 border-l-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="absolute bottom-0 right-0 w-3 h-3 border-b-2 border-r-2 border-[#00BFFF] opacity-60 pointer-events-none" />
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] px-3.5 pt-3 pb-2.5 flex items-center gap-2 shrink-0"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />7天告警趋势</div>
            <div class="flex-1 flex items-end justify-between gap-1.5 px-3.5 pb-3">
              <div v-for="b in barData" :key="b.label" class="flex-1 flex flex-col items-center gap-1">
                <div class="w-full bg-gradient-to-t from-[#00BFFF] to-[rgba(0,191,255,0.3)] rounded-t-sm min-h-1 transition-all duration-500" :style="{ height: b.h }" />
                <span class="text-[10px] text-[#8899AA]">{{ b.label }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Bottom Nav -->
      <nav class="fixed bottom-0 left-0 right-0 h-[52px] bg-gradient-to-t from-[#0A1628] via-[#0A1628]/30 flex items-center justify-center gap-1 z-[1000] px-3">
        <button v-for="nav in navItems" :key="nav.name"
          class="relative flex flex-col items-center justify-center gap-0.5 py-1 px-2.5 bg-transparent border border-transparent rounded-md text-[10px] cursor-pointer transition-all duration-[0.25s] min-w-14 no-underline"
          :class="nav.active ? 'bg-[rgba(0,191,255,0.1)] border-[rgba(0,191,255,0.3)] !text-[#00BFFF]' : 'text-[#8899AA] hover:bg-[rgba(0,191,255,0.12)] hover:border-[rgba(0,191,255,0.15)] hover:text-[#00BFFF] hover:-translate-y-0.5'"
          @click="router.push(nav.path)">
          <span class="w-4 h-4 flex items-center justify-center transition-transform duration-[0.25s]" v-html="`<svg viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='1.8' stroke-linecap='round' stroke-linejoin='round'>${navSvgs[nav.name]||''}</svg>`" />
          <span>{{ nav.name }}</span>
          <span v-if="nav.active" class="absolute bottom-0 left-[30%] right-[30%] h-0.5 bg-[#00BFFF] rounded-sm" />
        </button>
      </nav>
    </div>
  </div>
</template>

<style scoped>
::-webkit-scrollbar { width: 4px; }
::-webkit-scrollbar-thumb { background: rgba(0,191,255,0.15); border-radius: 2px; }
::-webkit-scrollbar-track { background: transparent; }
</style>
