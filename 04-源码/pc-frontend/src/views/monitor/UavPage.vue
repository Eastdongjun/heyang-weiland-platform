<script setup lang="ts">
const drones = [
  { name:'大疆 M300-01', status:'航拍中', online:true, battery:78, alt:120, speed:8.5, task:'湿地巡逻' },
  { name:'大疆 M300-02', status:'待命', online:true, battery:95, alt:0, speed:0, task:'-' },
  { name:'大疆 M30T-01', status:'充电中', online:true, battery:22, alt:0, speed:0, task:'-' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="h-16 bg-gradient-to-b from-[rgba(10,22,40,0.98)] to-[rgba(10,22,40,0.85)] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-7 shrink-0 relative z-[100]">
      <div class="absolute bottom-0 left-[10%] right-[10%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="text-xl font-bold tracking-[4px] bg-gradient-to-r from-white to-[#00BFFF] bg-clip-text text-transparent">无人机监控</div>
      <a href="/nav" class="text-xs text-[#8899AA] no-underline px-2.5 py-1 border border-[rgba(0,191,255,0.15)] rounded">返回</a>
    </header>
    <div class="flex-1 flex min-h-0 p-2 gap-2">
      <div class="flex-1 bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg flex items-center justify-center relative">
        <div class="text-6xl opacity-15">🛸</div>
        <div class="absolute top-4 left-4 text-xs text-[#8899AA]">实时画面 · {{ drones[0].name }}</div>
        <div class="absolute top-4 right-4 flex gap-2 text-[10px]"><span class="bg-[rgba(0,0,0,0.5)] px-2 py-0.5 rounded text-[#00CC99]">LIVE</span></div>
        <div class="absolute bottom-4 left-4 flex gap-4 text-[10px] text-[#8899AA]"><span>高度: {{ drones[0].alt }}m</span><span>速度: {{ drones[0].speed }}m/s</span><span>电量: <span :class="drones[0].battery<30?'text-[#FF3366]':'text-[#00CC99]'">{{ drones[0].battery }}%</span></span></div>
      </div>
      <aside class="w-[280px] shrink-0 bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-4 overflow-y-auto">
        <div class="text-xs font-semibold text-[#00BFFF] mb-3 flex items-center gap-1.5"><span class="w-[3px] h-[11px] bg-[#00BFFF] rounded-sm inline-block" />无人机列表</div>
        <div v-for="d in drones" :key="d.name" class="bg-[rgba(10,28,60,0.6)] border border-[rgba(0,191,255,0.12)] rounded-lg p-3 mb-2">
          <div class="flex justify-between items-center mb-1"><span class="text-sm font-semibold">{{ d.name }}</span><span class="text-[10px]" :class="d.online?'text-[#00CC99]':'text-[#808080]'">● {{ d.status }}</span></div>
          <div class="text-[11px] text-[#8899AA] space-y-0.5"><div>电量: {{ d.battery }}% | 高度: {{ d.alt }}m</div><div>任务: {{ d.task }}</div></div>
        </div>
      </aside>
    </div>
  </div>
</template>
