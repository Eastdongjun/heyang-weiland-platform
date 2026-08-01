<script setup lang="ts">
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif;color:#fff">
    <header class="fixed top-0 left-0 right-0 h-16 bg-[rgba(10,25,50,0.95)] border-b border-[rgba(0,191,255,0.3)] flex items-center justify-between px-6 z-[1000]" style="backdrop-filter:blur(12px)">
      <div class="flex items-center gap-4"><button class="w-9 h-9 border border-[rgba(0,191,255,0.15)] bg-[rgba(0,191,255,0.08)] rounded-md text-[#00BFFF] text-lg flex items-center justify-center hover:border-[#00BFFF]" @click="router.back()">‹</button><span class="text-lg font-semibold text-[#00BFFF]">无人机任务详情</span></div>
      <div class="text-[13px] text-[#8899AA]">{{ route.params.id || 'TASK-001' }}</div>
    </header>
    <div class="pt-[88px] px-6 pb-6 grid grid-cols-[1fr_360px] gap-5 h-full">
      <div class="space-y-4"><div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg flex-1 flex items-center justify-center text-[#8899AA] text-sm h-[300px]">[DJI M30T 实时图传]</div><div class="grid grid-cols-3 gap-3"><div class="aspect-video bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded flex items-center justify-center text-xs text-[#8899AA]">航拍截图 1</div><div class="aspect-video bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded flex items-center justify-center text-xs text-[#8899AA]">航拍截图 2</div><div class="aspect-video bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded flex items-center justify-center text-xs text-[#8899AA]">航拍截图 3</div></div></div>
      <div class="space-y-4">
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5"><div class="text-base font-semibold text-[#00BFFF] mb-4">任务信息</div><div class="space-y-2 text-sm"><div class="flex justify-between"><span class="text-[#8899AA]">型号</span><span>DJI M30T</span></div><div class="flex justify-between"><span class="text-[#8899AA]">飞行高度</span><span>86.4m</span></div><div class="flex justify-between"><span class="text-[#8899AA]">飞行速度</span><span>12.3m/s</span></div><div class="flex justify-between"><span class="text-[#8899AA]">电量</span><span class="text-[#00CC99]">78%</span></div><div class="flex justify-between"><span class="text-[#8899AA]">巡逻区域</span><span>洽川湿地核心区</span></div><div class="flex justify-between"><span class="text-[#8899AA]">执行状态</span><span class="text-[#00CC99]">航拍中</span></div></div><div class="flex gap-3 mt-4"><button class="flex-1 py-2 rounded text-sm font-semibold bg-[rgba(0,191,255,0.15)] text-[#00BFFF] border border-[rgba(0,191,255,0.25)] cursor-pointer">一键返航</button><button class="flex-1 py-2 rounded text-sm font-semibold bg-gradient-to-r from-[#00BFFF] to-[#0099cc] text-[#0A1628] border-none cursor-pointer">实时拍照</button></div></div>
      </div>
    </div>
  </div>
</template>
