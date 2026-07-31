<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const now = ref(new Date())
let timer: number

onMounted(() => {
  timer = window.setInterval(() => now.value = new Date(), 1000)
})
onUnmounted(() => clearInterval(timer))

const headerDate = () => `${now.value.getFullYear()}年${now.value.getMonth()+1}月${now.value.getDate()}日`
const clockSec = () => now.value.toLocaleTimeString('zh-CN', { hour12: false })

const bottomNav = [
  { name: 'GIS地图', path: '/gis', active: true, svg: '<circle cx="12" cy="10" r="3"/><path d="M12 2C7 2 3 6.5 3 12c0 2 .5 3.5 1.5 5L12 22l7.5-5c1-1.5 1.5-3 1.5-5 0-5.5-4-10-9-10z"/>' },
  { name: '视频监控', path: '/monitor', svg: '<rect x="2" y="4" width="16" height="14" rx="2"/><polygon points="23,7 19,10 19,14 23,17"/>' },
  { name: '无人机', path: '/uav', svg: '<path d="M12 2L9 7l-4 1 3 2.5L7 15l5-3 5 3-1-4.5L19 8l-4-1z"/><line x1="12" y1="18" x2="12" y2="2"/>' },
  { name: '环境监测', path: '/env', svg: '<path d="M17 8C15 5 9 5 7 8c-3 4 0 9 5 12 5-3 8-8 5-12z"/><line x1="12" y1="20" x2="12" y2="11"/>' },
  { name: '告警中心', path: '/alert', svg: '<path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/>' },
  { name: '数据统计', path: '/statistics', svg: '<rect x="3" y="14" width="4" height="8" rx="1"/><rect x="10" y="8" width="4" height="14" rx="1"/><rect x="17" y="4" width="4" height="18" rx="1"/>' },
  { name: '应急管理', path: '/emergency', svg: '<path d="M12 2L3 7v6c0 5.5 4 10.5 9 11 5-.5 9-5.5 9-11V7l-9-5z"/><line x1="12" y1="8" x2="12" y2="14"/><circle cx="12" cy="17" r="1"/>' },
  { name: 'VR查看', path: '/vr', svg: '<rect x="2" y="6" width="20" height="12" rx="3"/><circle cx="8" cy="12" r="1.5"/><circle cx="16" cy="12" r="1.5"/><line x1="12" y1="9" x2="12" y2="15"/>' },
]

const alertRank = [
  { name: '核心保护区', val: 15, w: '100%' },
  { name: '北门入口区', val: 12, w: '80%' },
  { name: '东岸芦苇荡', val: 8, w: '53%' },
  { name: '南岸滩涂', val: 6, w: '40%' },
  { name: '西门停车场', val: 4, w: '27%' },
]

const chartBars = [
  { h: '64%', v: 18, d: '15', color: 'from-[#00BFFF] to-[rgba(0,191,255,0.3)]' },
  { h: '79%', v: 22, d: '16', color: 'from-[#00BFFF] to-[rgba(0,191,255,0.3)]' },
  { h: '54%', v: 15, d: '17', color: 'from-[#00BFFF] to-[rgba(0,191,255,0.3)]' },
  { h: '100%', v: 28, d: '18', color: 'from-[#FF3366] to-[rgba(255,51,102,0.4)]', danger: true },
  { h: '71%', v: 20, d: '19', color: 'from-[#00BFFF] to-[rgba(0,191,255,0.3)]' },
  { h: '89%', v: 25, d: '20', color: 'from-[#00BFFF] to-[rgba(0,191,255,0.3)]' },
  { h: '68%', v: 19, d: '21', color: 'from-[#00BFFF] to-[rgba(0,191,255,0.3)]' },
]

const pieItems = [
  { color: '#00BFFF', name: '非法闯入', pct: '37.5%' },
  { color: '#FFCC00', name: '设备故障', pct: '20.0%' },
  { color: '#FF3366', name: '环境异常', pct: '30.0%' },
  { color: '#00CC99', name: '消防预警', pct: '12.5%' },
]
</script>

<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden font-['PingFang_SC','Microsoft_YaHei','Helvetica_Neue',sans-serif]">

    <!-- ===== TOP BAR ===== -->
    <header class="h-16 bg-gradient-to-b from-[rgba(10,22,40,0.98)] to-[rgba(10,22,40,0.85)] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-7 shrink-0 relative z-[100]">
      <div class="absolute bottom-0 left-[10%] right-[10%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="w-60 text-sm font-semibold text-[#00BFFF] font-mono">{{ headerDate() }}</div>
      <div class="absolute left-1/2 -translate-x-1/2 text-center">
        <div class="text-xl font-bold tracking-[4px] bg-gradient-to-r from-white to-[#00BFFF] bg-clip-text text-transparent">洽川湿地GIS指挥中心</div>
        <div class="text-[10px] text-[#8899AA] tracking-[1px] mt-0.5">智慧治理 · 一屏观全域</div>
      </div>
      <div class="flex items-center gap-5 text-[13px] text-[#CCCCCC]">
        <span class="flex items-center gap-1 text-[#8899AA]">☀️ 26°C · 晴</span>
        <span class="text-[22px] font-bold text-[#00BFFF] font-mono">{{ clockSec() }}</span>
        <a href="/nav" class="text-xs text-[#8899AA] no-underline px-2.5 py-1 border border-[rgba(0,191,255,0.15)] rounded">退出</a>
      </div>
    </header>

    <!-- ===== MAIN ===== -->
    <main class="flex-1 flex flex-col min-h-0 relative">
      <div class="flex-1 flex min-h-0 gap-2 p-2">

        <!-- LEFT PANEL -->
        <aside class="w-[260px] shrink-0 flex flex-col gap-2 overflow-y-auto p-2.5 border border-[rgba(0,191,255,0.15)] rounded z-10">
          <!-- 概况 -->
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-3">
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] mb-2.5 flex items-center gap-1.5"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />○ 合阳县 · 概况</div>
            <div class="text-center py-1 pb-2 border-b border-[rgba(0,191,255,0.15)] mb-2">
              <div class="text-[28px] font-bold text-[#00BFFF] font-mono">1,342</div>
              <div class="text-[10px] text-[#8899AA]">km² · 辖区面积</div>
            </div>
            <div class="grid grid-cols-2 gap-2">
              <div class="bg-[rgba(10,28,60,0.6)] border border-[rgba(0,191,255,0.15)] rounded p-2.5">
                <div class="text-[10px] text-[#8899AA] tracking-[0.5px]">总人口</div>
                <div class="text-lg font-bold text-[#00BFFF] font-mono mt-0.5">42.3<span class="text-[11px] font-normal text-[#8899AA]">万</span></div>
              </div>
              <div class="bg-[rgba(10,28,60,0.6)] border border-[rgba(0,191,255,0.15)] rounded p-2.5">
                <div class="text-[10px] text-[#8899AA] tracking-[0.5px]">行政村</div>
                <div class="text-lg font-bold text-[#00BFFF] font-mono mt-0.5">215</div>
              </div>
              <div class="bg-[rgba(10,28,60,0.6)] border border-[rgba(0,191,255,0.15)] rounded p-2.5">
                <div class="text-[10px] text-[#8899AA] tracking-[0.5px]">湿地面积</div>
                <div class="text-lg font-bold text-[#00BFFF] font-mono mt-0.5">176<span class="text-[11px] font-normal text-[#8899AA]">km²</span></div>
              </div>
              <div class="bg-[rgba(10,28,60,0.6)] border border-[rgba(0,191,255,0.15)] rounded p-2.5">
                <div class="text-[10px] text-[#8899AA] tracking-[0.5px]">监控设备</div>
                <div class="text-lg font-bold text-[#00BFFF] font-mono mt-0.5">98<span class="text-[11px] font-normal text-[#8899AA]">台</span></div>
              </div>
            </div>
          </div>

          <!-- 核心指标 -->
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-3">
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] mb-2.5 flex items-center gap-1.5"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />● 今日核心指标</div>
            <div class="grid grid-cols-2 gap-2">
              <div class="bg-[rgba(10,28,60,0.6)] border border-[rgba(0,191,255,0.15)] rounded p-2.5">
                <div class="text-[10px] text-[#8899AA]">事件总数</div>
                <div class="text-[22px] font-bold text-[#FFCC00] font-mono mt-0.5">47</div>
                <div class="text-[10px] text-[#8899AA] mt-0.5 flex justify-between"><span>同比</span><span class="text-[#00CC99]">↑ 8.2%</span></div>
              </div>
              <div class="bg-[rgba(10,28,60,0.6)] border border-[rgba(0,191,255,0.15)] rounded p-2.5">
                <div class="text-[10px] text-[#8899AA]">待处理</div>
                <div class="text-[22px] font-bold text-[#FF3366] font-mono mt-0.5">12</div>
                <div class="text-[10px] text-[#8899AA] mt-0.5 flex justify-between"><span>环比</span><span class="text-[#FF3366]">↑ 15.3%</span></div>
              </div>
              <div class="bg-[rgba(10,28,60,0.6)] border border-[rgba(0,191,255,0.15)] rounded p-2.5">
                <div class="text-[10px] text-[#8899AA]">处理中</div>
                <div class="text-[22px] font-bold text-[#00E5CC] font-mono mt-0.5">28</div>
                <div class="text-[10px] text-[#8899AA] mt-0.5 flex justify-between"><span>完成率</span><span class="text-[#00CC99]">68.4%</span></div>
              </div>
              <div class="bg-[rgba(10,28,60,0.6)] border border-[rgba(0,191,255,0.15)] rounded p-2.5">
                <div class="text-[10px] text-[#8899AA]">已办结</div>
                <div class="text-[22px] font-bold text-[#00CC99] font-mono mt-0.5">7</div>
                <div class="text-[10px] text-[#8899AA] mt-0.5 flex justify-between"><span>设备在线</span><span class="text-[#00CC99]">87/98</span></div>
              </div>
            </div>
          </div>

          <!-- 告警量 TOP5 -->
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-3 flex-1 min-h-0">
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] mb-2.5 flex items-center gap-1.5"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />◎ 告警量 TOP5</div>
            <div class="flex flex-col gap-1">
              <div v-for="(r, i) in alertRank" :key="r.name" class="flex items-center gap-2 py-1 px-2 rounded text-[11px] hover:bg-[rgba(0,191,255,0.12)] transition-colors cursor-pointer">
                <span class="w-4 h-4 rounded flex items-center justify-center text-[9px] font-bold font-mono shrink-0"
                  :class="i < 3 ? 'bg-gradient-to-br from-[#FFCC00] to-[#ff8c00] text-white' : 'bg-[rgba(10,28,60,0.6)] text-[#8899AA]'">{{ i + 1 }}</span>
                <span class="flex-1 text-[#CCCCCC]">{{ r.name }}</span>
                <span class="font-mono text-xs font-semibold text-[#00BFFF]">{{ r.val }}</span>
              </div>
            </div>
          </div>
        </aside>

        <!-- ===== MAP ===== -->
        <section class="flex-1 relative min-w-0 overflow-hidden border border-[rgba(0,191,255,0.15)] rounded z-10">
          <div id="amap-container" class="w-full h-full" />
          <!-- map corners -->
          <div class="absolute top-0 left-0 w-4 h-4 border-t-2 border-l-2 border-[#00BFFF] opacity-60 pointer-events-none z-[5]" />
          <div class="absolute top-0 right-0 w-4 h-4 border-t-2 border-r-2 border-[#00BFFF] opacity-60 pointer-events-none z-[5]" />
          <div class="absolute bottom-0 left-0 w-4 h-4 border-b-2 border-l-2 border-[#00BFFF] opacity-60 pointer-events-none z-[5]" />
          <div class="absolute bottom-0 right-0 w-4 h-4 border-b-2 border-r-2 border-[#00BFFF] opacity-60 pointer-events-none z-[5]" />
          <div class="absolute bottom-4 left-4 z-[5] text-[10px] text-[#8899AA] font-mono">合阳县 · 洽川湿地 · zoom 12.0</div>
          <div class="absolute bottom-4 right-4 z-[5] flex gap-3 bg-[rgba(10,22,40,0.8)] border border-[rgba(0,191,255,0.15)] rounded px-3 py-2 text-[10px]">
            <span class="flex items-center gap-1 text-[#8899AA]"><span class="w-2 h-2 rounded-sm bg-[#00BFFF]" />监控点位</span>
            <span class="flex items-center gap-1 text-[#8899AA]"><span class="w-2 h-2 rounded-sm bg-[#FF3366]" />告警区域</span>
            <span class="flex items-center gap-1 text-[#8899AA]"><span class="w-2 h-2 rounded-sm bg-[#00CC99]" />正常区域</span>
          </div>

          <!-- Floating alert panel -->
          <div class="absolute top-3 right-3 z-[5] bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded w-[220px] p-2">
            <div class="text-[10px] font-semibold text-[#00BFFF] mb-1.5 flex items-center">⚠ 实时告警 <a href="/alert" class="text-[#00BFFF] text-[9px] no-underline ml-auto">全部→</a></div>
            <div class="flex items-center gap-1.5 py-0.5 border-b border-[rgba(0,191,255,0.15)]"><span class="w-1.5 h-1.5 rounded-full bg-[#FF3366] shadow-[0_0_6px_rgba(255,51,102,0.3)] shrink-0" /><span class="text-[#CCCCCC] text-[10px]">核心区北门 非法闯入</span><span class="text-[#8899AA] text-[9px] font-mono ml-auto">10:42</span></div>
            <div class="flex items-center gap-1.5 py-0.5 border-b border-[rgba(0,191,255,0.15)]"><span class="w-1.5 h-1.5 rounded-full bg-[#FFCC00] shrink-0" /><span class="text-[#CCCCCC] text-[10px]">CAM-0203 设备离线</span><span class="text-[#8899AA] text-[9px] font-mono ml-auto">10:25</span></div>
            <div class="flex items-center gap-1.5 py-0.5 border-b border-[rgba(0,191,255,0.15)]"><span class="w-1.5 h-1.5 rounded-full bg-[#FFCC00] shrink-0" /><span class="text-[#CCCCCC] text-[10px]">水质监测站C区 异常</span><span class="text-[#8899AA] text-[9px] font-mono ml-auto">10:18</span></div>
            <div class="flex items-center gap-1.5 py-0.5"><span class="w-1.5 h-1.5 rounded-full bg-[#00CC99] shrink-0" /><span class="text-[#CCCCCC] text-[10px]">东岸火警误报 已处理</span><span class="text-[#8899AA] text-[9px] font-mono ml-auto">09:45</span></div>
          </div>
        </section>

        <!-- RIGHT PANEL -->
        <aside class="w-[280px] shrink-0 flex flex-col gap-2 overflow-y-auto p-2.5 border border-[rgba(0,191,255,0.15)] rounded z-10">
          <!-- 区域告警排名 -->
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-3 flex-1 min-h-0 flex flex-col">
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] mb-2.5 flex items-center gap-1.5"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />▦ 区域告警排名</div>
            <div class="flex flex-col gap-1 px-0.5">
              <div v-for="(r, i) in alertRank" :key="r.name" class="flex items-center gap-1.5 text-[10px]">
                <span class="w-3.5 h-3.5 rounded-sm flex items-center justify-center text-[8px] font-bold font-mono shrink-0"
                  :class="i < 3 ? 'bg-gradient-to-br from-[#FFCC00] to-[#ff8c00] text-white' : 'bg-[rgba(10,28,60,0.6)] text-[#8899AA]'">{{ i + 1 }}</span>
                <span class="w-14 text-[#CCCCCC] shrink-0 truncate">{{ r.name }}</span>
                <div class="flex-1 h-2 bg-[rgba(10,28,60,0.6)] rounded overflow-hidden"><div class="h-full rounded transition-all duration-500 bg-gradient-to-r from-[#00BFFF] to-[rgba(0,229,204,0.5)]" :style="{ width: r.w }" /></div>
                <span class="w-[18px] font-mono font-semibold text-[#00BFFF] text-right shrink-0">{{ r.val }}</span>
              </div>
            </div>
          </div>

          <!-- 近7日告警趋势 -->
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-3 flex-1 min-h-0 flex flex-col">
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] mb-2.5 flex items-center gap-1.5"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />▨ 近7日告警趋势</div>
            <div class="flex gap-3 px-1 pb-1 text-[10px] text-[#8899AA] shrink-0">
              <span>峰值: <span class="text-[#FF3366] font-semibold">28</span> 件</span>
              <span>均值: <span class="text-[#00BFFF] font-semibold">21</span> 件/日</span>
            </div>
            <div class="flex-1 flex items-end gap-1 px-0.5 pt-1">
              <div v-for="(b, i) in chartBars" :key="i" class="flex-1 flex flex-col items-center gap-0.5">
                <div class="w-full rounded-t relative" :style="{ paddingBottom: b.h, background: `linear-gradient(180deg,${b.danger ? '#FF3366' : '#00BFFF'},${b.danger ? 'rgba(255,51,102,0.4)' : 'rgba(0,191,255,0.3)'})` }">
                  <span class="absolute -top-3.5 left-1/2 -translate-x-1/2 text-[8px] font-mono" :class="b.danger ? 'text-[#FF3366] font-semibold' : 'text-[#CCCCCC]'">{{ b.v }}</span>
                </div>
                <span class="text-[7px] text-[#8899AA]">{{ b.d }}</span>
              </div>
            </div>
          </div>

          <!-- 事件类型占比 -->
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-3 flex-1 min-h-0 flex flex-col">
            <div class="text-xs font-semibold text-[#00BFFF] tracking-[1px] mb-2.5 flex items-center gap-1.5"><span class="w-[3px] h-3 bg-[#00BFFF] rounded-sm inline-block" />● 事件类型占比</div>
            <div class="flex gap-3 px-1 pb-1 text-[10px] text-[#8899AA] shrink-0">
              <span>最多: <span class="text-[#00BFFF] font-semibold">非法闯入</span></span>
              <span>占比 <span class="text-[#00BFFF] font-semibold">35.7%</span></span>
            </div>
            <div class="flex-1 flex items-center gap-2.5 p-1">
              <div class="w-[70px] h-[70px] rounded-full shrink-0 border-2 border-[#0A1628]"
                style="background: conic-gradient(#00BFFF 0deg 135deg, #FFCC00 135deg 207deg, #FF3366 207deg 315deg, #00CC99 315deg 360deg)" />
              <div class="flex-1 flex flex-col gap-0.5 text-[9px]">
                <div v-for="p in pieItems" :key="p.name" class="flex items-center gap-1">
                  <span class="w-1.5 h-1.5 rounded-full shrink-0" :style="{ background: p.color }" />
                  <span class="text-[#CCCCCC]">{{ p.name }}</span>
                  <span class="ml-auto text-[#00BFFF] font-mono">{{ p.pct }}</span>
                </div>
              </div>
            </div>
          </div>
        </aside>
      </div>

      <!-- ===== BOTTOM NAV ===== -->
      <nav class="h-[52px] bg-gradient-to-t from-[#0A1628] via-[#0A1628]/30 flex items-center justify-center gap-1 shrink-0 px-3">
        <button v-for="nav in bottomNav" :key="nav.name"
          class="relative flex flex-col items-center justify-center gap-0.5 py-1 px-2.5 bg-transparent border border-transparent rounded-md text-[10px] cursor-pointer transition-all duration-[0.25s] min-w-14 no-underline"
          :class="nav.active ? 'bg-[rgba(0,191,255,0.1)] border-[rgba(0,191,255,0.3)] !text-[#00BFFF]' : 'text-[#8899AA] hover:bg-[rgba(0,191,255,0.12)] hover:border-[rgba(0,191,255,0.15)] hover:text-[#00BFFF] hover:-translate-y-0.5'"
          @click="router.push(nav.path)">
          <span class="w-4 h-4 flex items-center justify-center transition-transform duration-[0.25s]" v-html="`<svg viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='1.8' stroke-linecap='round' stroke-linejoin='round'>${nav.svg}</svg>`" />
          <span>{{ nav.name }}</span>
          <span v-if="nav.active" class="absolute bottom-0 left-[30%] right-[30%] h-0.5 bg-[#00BFFF] rounded-sm shadow-[0_0_6px_rgba(0,191,255,0.25)]" />
        </button>
      </nav>
    </main>
  </div>
</template>

<style scoped>
::-webkit-scrollbar { width: 3px; }
::-webkit-scrollbar-thumb { background: rgba(0,191,255,0.15); border-radius: 2px; }
::-webkit-scrollbar-track { background: transparent; }
</style>
