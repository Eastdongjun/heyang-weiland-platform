<script setup lang="ts">
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()
</script>

<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">

    <header class="fixed top-0 left-0 right-0 h-16 bg-[rgba(10,25,50,0.95)] border-b border-[rgba(0,191,255,0.3)] flex items-center justify-between px-6 z-[1000]" style="backdrop-filter:blur(12px)">
      <div class="flex items-center gap-4">
        <button class="w-9 h-9 border border-[rgba(0,191,255,0.15)] bg-[rgba(0,191,255,0.08)] rounded-md text-[#00BFFF] text-lg cursor-pointer flex items-center justify-center hover:border-[#00BFFF]" @click="router.back()">‹</button>
        <span class="text-lg font-semibold text-[#00BFFF]">告警详情</span>
      </div>
      <div class="text-[13px] text-[#8899AA]">工单号 AL-20260512-{{ route.params.id || '001' }}</div>
    </header>

    <div class="pt-[88px] px-6 pb-6 grid grid-cols-[1fr_360px] gap-5 h-full">
      <div class="space-y-4 overflow-y-auto">
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-base font-semibold text-[#00BFFF] mb-4 flex items-center gap-2">🚨 告警信息</div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">告警类型</span><span class="px-3 py-0.5 rounded text-xs font-semibold bg-[rgba(255,51,102,0.12)] text-[#FF3366] border border-[rgba(255,51,102,0.25)]">非法闯入</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">告警等级</span><span class="px-3 py-0.5 rounded text-xs font-semibold bg-[rgba(255,51,102,0.12)] text-[#FF3366] border border-[rgba(255,51,102,0.25)]">一级</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">告警位置</span><span class="text-[#E8EDF5] font-medium">北门保护区入口</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">经纬度</span><span class="text-[#E8EDF5] font-medium">34.9876°N, 110.2345°E</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">触发时间</span><span class="text-[#E8EDF5] font-medium">2026-05-12 10:42:18</span></div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">持续时长</span><span class="text-[#E8EDF5] font-medium">12分钟 35秒</span></div>
          <div class="flex justify-between py-2.5 text-sm"><span class="text-[#8899AA]">处理状态</span><span class="text-[#FF3366] font-bold">● 未处理</span></div>
        </div>

        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-base font-semibold text-[#00BFFF] mb-4 flex items-center gap-2">✏️ 处置记录</div>
          <div class="relative pl-6 before:absolute before:left-1.5 before:top-1 before:bottom-1 before:w-0.5 before:bg-[rgba(0,191,255,0.15)]" style="before:content:''">
            <div class="relative mb-[18px]" style="&::before{content:'';position:absolute;left:-22px;top:4px;width:10px;height:10px;border-radius:50%;background:#00BFFF;border:2px solid #0A1628}">
              <div class="text-xs text-[#8899AA] mb-1">10:42:18</div><div class="text-[13px] text-[#E8EDF5]">视频分析系统检测到异常运动</div>
            </div>
            <div class="relative mb-[18px]" style="&::before{content:'';position:absolute;left:-22px;top:4px;width:10px;height:10px;border-radius:50%;background:#00BFFF;border:2px solid #0A1628}">
              <div class="text-xs text-[#8899AA] mb-1">10:42:20</div><div class="text-[13px] text-[#E8EDF5]">自动生成告警工单 AL-20260512-001</div>
            </div>
            <div class="relative mb-[18px]" style="&::before{content:'';position:absolute;left:-22px;top:4px;width:10px;height:10px;border-radius:50%;background:#00BFFF;border:2px solid #0A1628}">
              <div class="text-xs text-[#8899AA] mb-1">10:42:25</div><div class="text-[13px] text-[#E8EDF5]">推送至巡查员 张三 手机端</div>
            </div>
            <div class="relative" style="&::before{content:'';position:absolute;left:-22px;top:4px;width:10px;height:10px;border-radius:50%;background:#00BFFF;border:2px solid #0A1628}">
              <div class="text-xs text-[#8899AA] mb-1">10:43:00</div><div class="text-[13px] text-[#E8EDF5]">等待巡查员确认处置...</div>
            </div>
          </div>
        </div>
      </div>

      <div class="space-y-4">
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-base font-semibold text-[#00BFFF] mb-4 flex items-center gap-2">📷 截图证据</div>
          <div class="aspect-video bg-[rgba(10,28,60,0.6)] rounded-md border border-[rgba(0,191,255,0.15)] flex items-center justify-center text-[13px] text-[#8899AA]">[CAM-01 截图预览]</div>
        </div>
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
          <div class="text-base font-semibold text-[#00BFFF] mb-4 flex items-center gap-2">👤 责任人</div>
          <div class="flex justify-between py-2.5 border-b border-[rgba(0,191,255,0.15)] text-sm"><span class="text-[#8899AA]">巡查员</span><span class="text-[#E8EDF5] font-medium">张三 138****5678</span></div>
          <div class="flex justify-between py-2.5 text-sm"><span class="text-[#8899AA]">距离事发点</span><span class="text-[#E8EDF5] font-medium">240m</span></div>
          <div class="flex gap-3 mt-4">
            <button class="px-6 py-2.5 rounded-md text-sm font-semibold cursor-pointer border-none bg-gradient-to-r from-[#00BFFF] to-[#0099cc] text-[#0A1628]">派发处置</button>
            <button class="px-6 py-2.5 rounded-md text-sm font-semibold cursor-pointer bg-[rgba(0,191,255,0.08)] text-[#00BFFF] border border-[rgba(0,191,255,0.15)]">误报</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
