<script setup lang="ts">
import { useRoute } from 'vue-router'
const route = useRoute()
</script>
<template>
  <div class="min-h-screen bg-[#0A1628] text-[#E8EDF5] p-6">
    <h1 class="text-xl font-bold mb-4">告警详情 #{{ route.params.id }}</h1>
    <div class="bg-[#0F1D35] border border-[rgba(0,191,255,0.15)] rounded-lg p-6">
      <div class="space-y-3 text-sm">
        <div><span class="text-[#8899BB]">告警编号：</span>ALM-20260801-001</div>
        <div><span class="text-[#8899BB]">等级：</span><span class="px-1.5 py-0.5 rounded text-xs bg-[#FF3366] text-white">一级</span></div>
        <div><span class="text-[#8899BB]">触发设备：</span>热成像云台-01</div>
        <div><span class="text-[#8899BB]">触发时间：</span>2026-08-01 14:32:15</div>
        <div><span class="text-[#8899BB]">位置：</span>洽川湿地北部 (35.245, 110.265)</div>
        <div><span class="text-[#8899BB]">处置时间线：</span></div>
        <div class="pl-4 border-l-2 border-[rgba(0,191,255,0.2)] space-y-1 text-[#8899BB]">
          <div>14:32:15 — 系统自动检测</div><div>14:32:18 — 推送值班员</div><div>14:33:05 — 值班员确认</div>
        </div>
      </div>
    </div>
  </div>
</template>
