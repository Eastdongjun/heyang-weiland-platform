<script setup lang="ts">
import { useRouter } from 'vue-router'
const router = useRouter()
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="fixed top-0 left-0 right-0 h-16 bg-[rgba(10,25,50,0.95)] border-b border-[rgba(0,191,255,0.3)] flex items-center px-6 z-[1000]" style="backdrop-filter:blur(12px)">
      <button class="w-9 h-9 border border-[rgba(0,191,255,0.15)] bg-[rgba(0,191,255,0.08)] rounded-md text-[#00BFFF] text-lg flex items-center justify-center hover:border-[#00BFFF] mr-4" @click="router.back()">‹</button>
      <span class="text-lg font-semibold text-[#00BFFF]">新建工单</span>
    </header>
    <div class="pt-[88px] px-6 pb-6 max-w-2xl mx-auto w-full overflow-y-auto">
      <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-6 space-y-4">
        <div><label class="text-xs text-[#8899AA] block mb-1.5">工单标题</label><input class="w-full h-10 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-sm text-[#E8EDF5] outline-none focus:border-[#00BFFF]" placeholder="请输入工单标题" /></div>
        <div class="grid grid-cols-2 gap-4">
          <div><label class="text-xs text-[#8899AA] block mb-1.5">优先级</label><select class="w-full h-10 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-sm text-[#E8EDF5] outline-none"><option>紧急</option><option>重要</option><option>一般</option></select></div>
          <div><label class="text-xs text-[#8899AA] block mb-1.5">工单类型</label><select class="w-full h-10 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-sm text-[#E8EDF5] outline-none"><option>巡检</option><option>维修</option><option>事件</option></select></div>
        </div>
        <div><label class="text-xs text-[#8899AA] block mb-1.5">位置</label><input class="w-full h-10 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-sm text-[#E8EDF5] outline-none focus:border-[#00BFFF]" placeholder="请输入位置" /></div>
        <div><label class="text-xs text-[#8899AA] block mb-1.5">描述</label><textarea class="w-full h-24 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 py-2 text-sm text-[#E8EDF5] outline-none focus:border-[#00BFFF] resize-none" placeholder="请输入工单描述" /></div>
        <div class="flex gap-3 pt-2"><button class="flex-1 py-2.5 rounded-md text-sm font-semibold bg-gradient-to-r from-[#00BFFF] to-[#0099cc] text-[#0A1628] border-none cursor-pointer">提交</button><button class="px-6 py-2.5 rounded-md text-sm bg-[rgba(0,191,255,0.08)] text-[#00BFFF] border border-[rgba(0,191,255,0.15)] cursor-pointer" @click="router.back()">取消</button></div>
      </div>
    </div>
  </div>
</template>
