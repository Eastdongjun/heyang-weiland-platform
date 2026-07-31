<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
const now = ref(new Date())
let timer: number
onMounted(() => { timer = window.setInterval(() => now.value = new Date(), 1000) })
onUnmounted(() => clearInterval(timer))

const devices = [
  { name:'湿地北入口', code:'CAM-001', type:'摄像头', status:'online' },
  { name:'核心区热成像', code:'CAM-002', type:'热成像云台', status:'online' },
  { name:'观鸟台', code:'CAM-003', type:'球机', status:'online' },
  { name:'南岸4G球机', code:'CAM-004', type:'球机', status:'offline' },
  { name:'码头监控', code:'CAM-005', type:'摄像头', status:'online' },
  { name:'西门入口', code:'CAM-006', type:'摄像头', status:'online' },
]
const selectedDevice = ref(devices[0])
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="h-16 bg-gradient-to-b from-[rgba(10,22,40,0.98)] to-[rgba(10,22,40,0.85)] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-7 shrink-0 relative z-[100]">
      <div class="absolute bottom-0 left-[10%] right-[10%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="text-xl font-bold tracking-[4px] bg-gradient-to-r from-white to-[#00BFFF] bg-clip-text text-transparent">视频监控中心</div>
      <div class="flex items-center gap-5"><span class="text-lg font-semibold text-[#00BFFF] font-mono">{{ now.toLocaleTimeString('zh-CN',{hour12:false}) }}</span><a href="/nav" class="text-xs text-[#8899AA] no-underline px-2.5 py-1 border border-[rgba(0,191,255,0.15)] rounded">返回</a></div>
    </header>
    <div class="flex-1 flex min-h-0 p-2 gap-2">
      <!-- Device Tree -->
      <aside class="w-[240px] shrink-0 bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-3 overflow-y-auto">
        <div class="text-xs font-semibold text-[#00BFFF] mb-3 flex items-center gap-1.5"><span class="w-[3px] h-[11px] bg-[#00BFFF] rounded-sm inline-block" />设备列表</div>
        <div v-for="d in devices" :key="d.code"
          class="flex items-center gap-2 py-2 px-2 rounded text-xs cursor-pointer transition-all mb-0.5"
          :class="selectedDevice.code===d.code?'bg-[rgba(0,191,255,0.12)] text-[#00BFFF]':'text-[#8899AA] hover:bg-[rgba(0,191,255,0.06)]'"
          @click="selectedDevice=d">
          <span class="w-1.5 h-1.5 rounded-full shrink-0" :class="d.status==='online'?'bg-[#00CC99]':'bg-[#808080]'" />
          <span class="flex-1 truncate">{{ d.name }}</span>
          <span class="text-[10px] opacity-50">{{ d.type }}</span>
        </div>
      </aside>
      <!-- Video Grid -->
      <main class="flex-1 grid grid-cols-2 gap-2 min-h-0">
        <div v-for="i in 4" :key="i"
          class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg flex items-center justify-center relative overflow-hidden group cursor-pointer">
          <div class="text-4xl opacity-20">📹</div>
          <div class="absolute top-2 left-2 text-[10px] text-[#8899AA] bg-[rgba(0,0,0,0.6)] px-2 py-0.5 rounded">{{ selectedDevice.name }} #{{ i }}</div>
          <div class="absolute bottom-2 right-2 text-[10px] text-[#00CC99] bg-[rgba(0,0,0,0.6)] px-2 py-0.5 rounded">LIVE</div>
          <!-- hover toolbar -->
          <div class="absolute bottom-0 left-0 right-0 h-10 bg-gradient-to-t from-[rgba(0,0,0,0.8)] to-transparent opacity-0 group-hover:opacity-100 transition-opacity flex items-center justify-center gap-3">
            <button class="text-xs text-white bg-[rgba(255,255,255,0.2)] px-2 py-1 rounded">📷</button>
            <button class="text-xs text-white bg-[rgba(255,255,255,0.2)] px-2 py-1 rounded">⏺</button>
            <button class="text-xs text-white bg-[rgba(255,255,255,0.2)] px-2 py-1 rounded">🔊</button>
            <button class="text-xs text-white bg-[rgba(255,255,255,0.2)] px-2 py-1 rounded">⛶</button>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>
