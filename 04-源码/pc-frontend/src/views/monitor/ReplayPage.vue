<script setup lang="ts">
import { useRouter } from 'vue-router'
const router = useRouter()
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif;color:#fff">
    <header class="fixed top-0 left-0 right-0 h-16 bg-[rgba(10,25,50,0.95)] border-b border-[rgba(0,191,255,0.3)] flex items-center justify-between px-6 z-[1000]" style="backdrop-filter:blur(12px)">
      <div class="flex items-center gap-4"><button class="w-9 h-9 border border-[rgba(0,191,255,0.15)] bg-[rgba(0,191,255,0.08)] rounded-md text-[#00BFFF] text-lg flex items-center justify-center hover:border-[#00BFFF]" @click="router.back()">‹</button><span class="text-lg font-semibold text-[#00BFFF]">视频回放</span></div>
      <div class="text-[13px] text-[#8899AA]">CAM-01 北门保护区入口</div>
    </header>
    <div class="pt-[88px] px-6 pb-6 grid grid-cols-[1fr_320px] gap-5 h-full">
      <div class="flex flex-col">
        <div class="flex-1 bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg flex items-center justify-center text-[#8899AA] text-sm">[H.265 录像回放 - 05-12 10:30至11:00]</div>
        <div class="h-12 mt-3 bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg flex items-center px-4 gap-3">
          <span class="text-xs text-[#8899AA]">10:30:00</span>
          <div class="flex-1 h-2 bg-[rgba(0,191,255,0.1)] rounded-full relative"><div class="absolute h-full w-[35%] bg-[#00BFFF] rounded-full" /></div>
          <span class="text-xs text-[#8899AA]">11:00:00</span>
        </div>
      </div>
      <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
        <div class="text-base font-semibold text-[#00BFFF] mb-4">📄 录像片段</div>
        <div class="space-y-3">
          <div class="flex gap-3 p-2 rounded hover:bg-[rgba(0,191,255,0.06)] cursor-pointer"><div class="w-20 h-14 bg-[rgba(10,28,60,0.6)] rounded flex items-center justify-center text-xs text-[#8899AA]">截图</div><div><div class="text-sm">告警触发</div><div class="text-xs text-[#8899AA]">10:42:18 · 2分钟</div></div></div>
          <div class="flex gap-3 p-2 rounded hover:bg-[rgba(0,191,255,0.06)] cursor-pointer"><div class="w-20 h-14 bg-[rgba(10,28,60,0.6)] rounded flex items-center justify-center text-xs text-[#8899AA]">截图</div><div><div class="text-sm">正常巡查</div><div class="text-xs text-[#8899AA]">10:45:00 · 15分钟</div></div></div>
          <div class="flex gap-3 p-2 rounded hover:bg-[rgba(0,191,255,0.06)] cursor-pointer"><div class="w-20 h-14 bg-[rgba(10,28,60,0.6)] rounded flex items-center justify-center text-xs text-[#8899AA]">截图</div><div><div class="text-sm">开始录像</div><div class="text-xs text-[#8899AA]">10:30:00 · 30分钟</div></div></div>
        </div>
      </div>
    </div>
  </div>
</template>
