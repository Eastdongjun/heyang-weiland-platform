<script setup lang="ts">
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="fixed top-0 left-0 right-0 h-16 bg-[rgba(10,25,50,0.95)] border-b border-[rgba(0,191,255,0.3)] flex items-center justify-between px-6 z-[1000]" style="backdrop-filter:blur(12px)">
      <div class="flex items-center gap-4"><button class="w-9 h-9 border border-[rgba(0,191,255,0.15)] bg-[rgba(0,191,255,0.08)] rounded-md text-[#00BFFF] text-lg flex items-center justify-center hover:border-[#00BFFF]" @click="router.back()">‹</button><span class="text-lg font-semibold text-[#00BFFF]">工单详情</span></div>
      <div class="text-[13px] text-[#8899AA]">工单号 {{ route.params.id || 'WO-001' }}</div>
    </header>
    <div class="pt-[88px] px-6 pb-6 grid grid-cols-[1fr_360px] gap-5 h-full">
      <div class="space-y-4 overflow-y-auto">
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-base font-semibold text-[#00BFFF] mb-4">📋 工单信息</div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">工单编号</span><span class="text-[#00BFFF] font-mono">{{ route.params.id }}</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">工单类型</span><span class="text-[#E8EDF5]">巡检</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">优先级</span><span class="px-3 py-0.5 rounded text-xs font-semibold bg-[rgba(255,51,102,0.12)] text-[#FF3366] border border-[rgba(255,51,102,0.25)]">紧急</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">状态</span><span class="text-[#FF3366] font-bold">● 待派发</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">位置</span><span class="text-[#E8EDF5]">北门入口</span></div>
          <div class="flex justify-between py-2.5 text-sm"><span class="text-[#8899AA]">创建时间</span><span class="text-[#E8EDF5]">2026-08-01 10:42:18</span></div>
        </div>
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-base font-semibold text-[#00BFFF] mb-4">⏱ 处置时间线</div>
          <div class="relative pl-6 before:absolute before:left-1.5 before:top-1 before:bottom-1 before:w-0.5 before:bg-[rgba(0,191,255,0.15)]">
            <div class="relative mb-4"><div class="text-xs text-[#8899AA] mb-1">10:42:18</div><div class="text-[13px]">系统自动创建工单</div></div>
            <div class="relative mb-4"><div class="text-xs text-[#8899AA] mb-1">10:45:00</div><div class="text-[13px]">待值班员派发</div></div>
          </div>
        </div>
      </div>
      <div class="space-y-4">
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-base font-semibold text-[#00BFFF] mb-4">👤 派发</div>
          <div class="space-y-3 text-sm">
            <div class="text-[#8899AA]">推荐巡逻员</div>
            <div class="bg-[rgba(0,191,255,0.06)] border border-[rgba(0,191,255,0.1)] rounded p-3 flex justify-between"><span>张三</span><span class="text-[#8899AA] text-xs">距离 240m · 0单</span></div>
            <div class="bg-[rgba(0,191,255,0.06)] border border-[rgba(0,191,255,0.1)] rounded p-3 flex justify-between"><span>李四</span><span class="text-[#8899AA] text-xs">距离 580m · 1单</span></div>
          </div>
          <button class="w-full mt-4 px-6 py-2.5 rounded-md text-sm font-semibold bg-gradient-to-r from-[#00BFFF] to-[#0099cc] text-[#0A1628] border-none cursor-pointer">确认派发</button>
        </div>
      </div>
    </div>
  </div>
</template>
