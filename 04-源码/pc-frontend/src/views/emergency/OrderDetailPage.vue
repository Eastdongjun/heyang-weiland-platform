<script setup lang="ts">
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="fixed top-0 left-0 right-0 h-16 bg-[rgba(10,25,50,0.95)] border-b border-[rgba(0,191,255,0.3)] flex items-center justify-between px-6 z-[1000]" style="backdrop-filter:blur(12px)">
      <div class="flex items-center gap-4"><button class="w-9 h-9 border border-[rgba(0,191,255,0.15)] bg-[rgba(0,191,255,0.08)] rounded-md text-[#00BFFF] text-lg flex items-center justify-center hover:border-[#00BFFF]" @click="router.back()">‹</button><span class="text-lg font-semibold text-[#00BFFF]">应急工单详情</span></div>
      <div class="text-[13px] text-[#8899AA]">{{ route.params.id }}</div>
    </header>
    <div class="pt-[88px] px-6 pb-6 grid grid-cols-[1fr_360px] gap-5 h-full overflow-y-auto">
      <div class="space-y-4">
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-base font-semibold text-[#00BFFF] mb-4">🚨 工单信息</div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">工单号</span><span class="text-[#00BFFF] font-mono">{{ route.params.id }}</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">类型</span><span class="text-[#E8EDF5]">火情处置</span></div>
          <div class="flex justify-between py-2.5 text-sm"><span class="text-[#8899AA]">位置</span><span class="text-[#E8EDF5]">东岸芦苇荡</span></div>
        </div>
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-base font-semibold text-[#00BFFF] mb-4">✏️ 处置记录</div>
          <div class="text-sm text-[#8899AA] space-y-2"><div>10:42 — 告警触发，自动创建应急工单</div><div>10:45 — 值班员确认，派发至巡逻员张三</div><div>10:50 — 张三接单，开始处置</div></div>
        </div>
      </div>
      <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
        <div class="text-base font-semibold text-[#00BFFF] mb-4">📋 关联信息</div>
        <div class="text-sm text-[#8899AA] space-y-2"><div>关联告警：AL-20260801-001</div><div>应急预案：火情应急处置预案</div></div>
        <button class="w-full mt-4 px-6 py-2.5 rounded-md text-sm font-semibold bg-gradient-to-r from-[#00BFFF] to-[#0099cc] text-[#0A1628] border-none cursor-pointer">确认执行</button>
      </div>
    </div>
  </div>
</template>
