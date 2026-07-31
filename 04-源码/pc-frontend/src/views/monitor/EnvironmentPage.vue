<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
const now = ref(new Date())
let timer: number
onMounted(() => { timer = window.setInterval(() => now.value = new Date(), 1000) })
onUnmounted(() => clearInterval(timer))
const metrics = [
  { label:'温度', value:'32.5', unit:'°C', icon:'🌡️', status:'warn', trend:'↑ 1.2°C', color:'text-[#FFCC00]' },
  { label:'湿度', value:'68', unit:'%', icon:'💧', status:'normal', trend:'稳定', color:'text-[#00BFFF]' },
  { label:'风速', value:'3.2', unit:'m/s', icon:'🌬️', status:'normal', trend:'东南风', color:'text-[#00BFFF]' },
  { label:'PM2.5', value:'28', unit:'μg/m³', icon:'🌫️', status:'normal', trend:'↓ 3.5', color:'text-[#00CC99]' },
  { label:'水质PH', value:'7.8', unit:'pH', icon:'🧪', status:'normal', trend:'稳定', color:'text-[#00BFFF]' },
  { label:'降雨量', value:'0', unit:'mm', icon:'🌧️', status:'normal', trend:'无', color:'text-[#6A8099]' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="h-16 bg-gradient-to-b from-[rgba(10,22,40,0.98)] to-[rgba(10,22,40,0.85)] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-7 shrink-0 relative z-[100]">
      <div class="absolute bottom-0 left-[10%] right-[10%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="text-xl font-bold tracking-[4px] bg-gradient-to-r from-white to-[#00BFFF] bg-clip-text text-transparent">环境监测中心</div>
      <div class="flex items-center gap-5"><span class="text-lg font-semibold text-[#00BFFF] font-mono">{{ now.toLocaleTimeString('zh-CN',{hour12:false}) }}</span><a href="/nav" class="text-xs text-[#8899AA] no-underline px-2.5 py-1 border border-[rgba(0,191,255,0.15)] rounded">返回</a></div>
    </header>
    <main class="flex-1 overflow-y-auto p-4">
      <div class="grid grid-cols-3 gap-3 mb-4">
        <div v-for="m in metrics" :key="m.label"
          class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5 hover:border-[rgba(0,191,255,0.3)] transition-all cursor-pointer">
          <div class="flex justify-between items-start mb-3"><span class="text-2xl">{{ m.icon }}</span><span class="text-[10px] text-[#8899AA]">{{ m.trend }}</span></div>
          <div class="text-[28px] font-bold font-mono" :class="m.color">{{ m.value }}<span class="text-sm font-normal text-[#8899AA] ml-1">{{ m.unit }}</span></div>
          <div class="text-[11px] text-[#8899AA] mt-1">{{ m.label }}</div>
        </div>
      </div>
      <div class="grid grid-cols-2 gap-3">
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-xs font-semibold text-[#00BFFF] mb-4 flex items-center gap-1.5"><span class="w-[3px] h-[11px] bg-[#00BFFF] rounded-sm inline-block" />24小时温度曲线</div>
          <div class="h-[150px] flex items-end gap-1">
            <div v-for="(h,i) in [22,23,24,26,28,30,32,33,34,32,30,28,26,25,24,23,22,23,25,27,29,31,33,32]" :key="i"
              class="flex-1 bg-gradient-to-t from-[#00BFFF] to-[rgba(0,191,255,0.3)] rounded-t-sm transition-all" :style="{height:(h/40*100)+'%'}" />
          </div>
        </div>
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-xs font-semibold text-[#00BFFF] mb-4 flex items-center gap-1.5"><span class="w-[3px] h-[11px] bg-[#00BFFF] rounded-sm inline-block" />监测站点分布</div>
          <div class="text-sm text-[#8899AA] space-y-2">
            <div class="flex justify-between py-2 border-b border-[rgba(0,191,255,0.08)]"><span>📍 A区湿地入口站</span><span class="text-[#00CC99]">● 在线</span></div>
            <div class="flex justify-between py-2 border-b border-[rgba(0,191,255,0.08)]"><span>📍 B区深水区站</span><span class="text-[#00CC99]">● 在线</span></div>
            <div class="flex justify-between py-2 border-b border-[rgba(0,191,255,0.08)]"><span>📍 C区采样点站</span><span class="text-[#FF3366]">● 离线</span></div>
            <div class="flex justify-between py-2"><span>📍 气象综合站</span><span class="text-[#00CC99]">● 在线</span></div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>
