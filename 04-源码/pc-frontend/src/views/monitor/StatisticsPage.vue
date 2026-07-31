<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
const now = ref(new Date())
let timer: number
onMounted(() => { timer = window.setInterval(() => now.value = new Date(), 1000) })
onUnmounted(() => clearInterval(timer))

const kpis = [
  { label: '访问总量', value: '12,847', trend: 'up', pct: '+12.5%', color: '#00BFFF', icon: '👥' },
  { label: '设备在线', value: '87/98', trend: 'up', pct: '88.8%', color: '#00CC99', icon: '📡' },
  { label: '告警总数', value: '27', trend: 'down', pct: '-8.3%', color: '#FFCC00', icon: '🔔' },
  { label: '工单完成', value: '19', trend: 'up', pct: '82.6%', color: '#8B5CF6', icon: '📋' },
  { label: '巡逻里程', value: '126', trend: 'flat', pct: 'km', color: '#00E5CC', icon: '🚶' },
  { label: '无人机飞行', value: '8', trend: 'up', pct: '+2', color: '#40a9ff', icon: '🛸' },
]

const monthlyAlerts = [
  { month:'1月', val:32, w:'100%' },{ month:'2月', val:28, w:'88%' },{ month:'3月', val:35, w:'100%' },
  { month:'4月', val:25, w:'71%' },{ month:'5月', val:40, w:'100%' },{ month:'6月', val:38, w:'95%' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#080E1A] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="h-16 bg-gradient-to-b from-[rgba(8,14,26,0.98)] to-[rgba(8,14,26,0.85)] border-b border-[rgba(0,191,255,0.12)] flex items-center justify-between px-7 shrink-0 relative z-[100]">
      <div class="absolute bottom-0 left-[10%] right-[10%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="text-xl font-bold tracking-[4px] bg-gradient-to-r from-white to-[#00BFFF] bg-clip-text text-transparent">数据统计分析</div>
      <div class="flex items-center gap-5"><span class="text-lg font-semibold text-[#00BFFF] font-mono">{{ now.toLocaleTimeString('zh-CN',{hour12:false}) }}</span><a href="/nav" class="text-xs text-[#6A8099] no-underline px-2.5 py-1 border border-[rgba(0,191,255,0.12)] rounded">返回</a></div>
    </header>
    <main class="flex-1 overflow-y-auto p-4">
      <!-- KPI Cards -->
      <div class="grid grid-cols-6 gap-3 mb-4">
        <div v-for="k in kpis" :key="k.label"
          class="relative bg-gradient-to-br from-[rgba(12,24,48,0.9)] to-[rgba(8,18,38,0.7)] border border-[rgba(0,191,255,0.12)] rounded-[10px] p-4 overflow-hidden cursor-pointer transition-all duration-[0.35s] hover:-translate-y-0.5 hover:border-[#00BFFF] hover:shadow-[0_12px_40px_rgba(0,0,0,0.4),0_0_30px_rgba(0,191,255,0.2)]">
          <div class="absolute -right-2 -bottom-2 text-[56px] opacity-[0.04] pointer-events-none">{{ k.icon }}</div>
          <div class="flex justify-between items-start"><span class="text-[28px] font-bold font-mono tracking-[-0.5px]" :style="{color:k.color}">{{ k.value }}</span></div>
          <div class="text-[11px] text-[#6A8099] mt-1.5 tracking-[0.5px]">{{ k.label }}</div>
          <div class="flex justify-between items-center mt-2 pt-2 border-t border-[rgba(0,191,255,0.12)] text-[10px]">
            <span :class="{'text-[#00CC99]':k.trend==='up','text-[#FF3366]':k.trend==='down','text-[#6A8099]':k.trend==='flat'}">{{ k.pct }}</span>
            <span class="text-[#6A8099]">{{ k.trend==='up'?'↑':k.trend==='down'?'↓':'→' }}</span>
          </div>
        </div>
      </div>

      <!-- Charts Grid -->
      <div class="grid grid-cols-2 gap-3">
        <div class="bg-[rgba(12,24,48,0.88)] border border-[rgba(0,191,255,0.12)] rounded-lg overflow-hidden">
          <div class="text-xs font-semibold text-[#00BFFF] px-3.5 pt-3 pb-2.5 border-b border-[rgba(0,191,255,0.12)] flex items-center gap-1.5"><span class="w-[3px] h-[11px] bg-[#00BFFF] rounded-sm inline-block" />月度告警趋势</div>
          <div class="p-3.5">
            <div class="flex flex-col gap-1.5">
              <div v-for="m in monthlyAlerts" :key="m.month" class="flex items-center gap-2">
                <span class="w-9 text-[11px] text-[#6A8099] font-mono shrink-0">{{ m.month }}</span>
                <div class="flex-1 h-3 bg-[rgba(8,18,38,0.7)] rounded-md overflow-hidden"><div class="h-full rounded-md bg-gradient-to-r from-[#00BFFF] to-[#00E5CC]" :style="{width:m.w}" /></div>
                <span class="w-10 text-right text-[11px] font-mono font-semibold text-[#00BFFF] shrink-0">{{ m.val }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="bg-[rgba(12,24,48,0.88)] border border-[rgba(0,191,255,0.12)] rounded-lg overflow-hidden">
          <div class="text-xs font-semibold text-[#00BFFF] px-3.5 pt-3 pb-2.5 border-b border-[rgba(0,191,255,0.12)] flex items-center gap-1.5"><span class="w-[3px] h-[11px] bg-[#00BFFF] rounded-sm inline-block" />工单处理分布</div>
          <div class="p-3.5 flex items-center gap-4">
            <div class="w-[100px] h-[100px] rounded-full shrink-0" style="background:conic-gradient(#00CC99 0deg 216deg,#FFCC00 216deg 270deg,#FF3366 270deg 324deg,#00BFFF 324deg 360deg)" />
            <div class="flex flex-col gap-1.5 text-xs text-[#6A8099]">
              <div class="flex items-center gap-1.5"><span class="w-2 h-2 rounded-sm bg-[#00CC99]" />已完成 60%</div>
              <div class="flex items-center gap-1.5"><span class="w-2 h-2 rounded-sm bg-[#FFCC00]" />处理中 15%</div>
              <div class="flex items-center gap-1.5"><span class="w-2 h-2 rounded-sm bg-[#FF3366]" />待派发 15%</div>
              <div class="flex items-center gap-1.5"><span class="w-2 h-2 rounded-sm bg-[#00BFFF]" />已归档 10%</div>
            </div>
          </div>
        </div>
        <div class="bg-[rgba(12,24,48,0.88)] border border-[rgba(0,191,255,0.12)] rounded-lg overflow-hidden">
          <div class="text-xs font-semibold text-[#00BFFF] px-3.5 pt-3 pb-2.5 border-b border-[rgba(0,191,255,0.12)] flex items-center gap-1.5"><span class="w-[3px] h-[11px] bg-[#00BFFF] rounded-sm inline-block" />设备类型分布</div>
          <div class="p-3.5 flex items-end gap-1.5 h-[120px] pt-4">
            <div v-for="(b,i) in [{l:'摄像头',h:'80%',v:42,c:'#00BFFF'},{l:'传感器',h:'55%',v:28,c:'#00CC99'},{l:'球机',h:'35%',v:18,c:'#FFCC00'},{l:'云台',h:'20%',v:10,c:'#8B5CF6'}]" :key="i" class="flex-1 flex flex-col items-center gap-1">
              <span class="text-[10px] font-mono font-semibold" :style="{color:b.c}">{{ b.v }}</span>
              <div class="w-full rounded-t-sm" :style="{height:b.h,background:`linear-gradient(180deg,${b.c},rgba(0,191,255,0.2))`}" />
              <span class="text-[10px] text-[#6A8099]">{{ b.l }}</span>
            </div>
          </div>
        </div>
        <div class="bg-[rgba(12,24,48,0.88)] border border-[rgba(0,191,255,0.12)] rounded-lg overflow-hidden">
          <div class="text-xs font-semibold text-[#00BFFF] px-3.5 pt-3 pb-2.5 border-b border-[rgba(0,191,255,0.12)] flex items-center gap-1.5"><span class="w-[3px] h-[11px] bg-[#00BFFF] rounded-sm inline-block" />区域告警分布</div>
          <div class="p-3.5 flex flex-col gap-1.5">
            <div v-for="(r,i) in [{n:'核心保护区',v:15,p:'100%'},{n:'北门入口区',v:12,p:'80%'},{n:'东岸芦苇荡',v:8,p:'53%'},{n:'南岸滩涂',v:6,p:'40%'}]" :key="i" class="flex items-center gap-2">
              <span class="w-20 text-[11px] text-[#6A8099] shrink-0 truncate">{{ r.n }}</span>
              <div class="flex-1 h-3 bg-[rgba(8,18,38,0.7)] rounded-md overflow-hidden"><div class="h-full rounded-md bg-gradient-to-r from-[#FF3366] to-[#FFCC00]" :style="{width:r.p}" /></div>
              <span class="w-6 text-right text-[11px] font-mono font-semibold text-[#FF3366] shrink-0">{{ r.v }}</span>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
