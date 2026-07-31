<script setup lang="ts">
import { useRouter, useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()
const tabs = ['基础信息','运行状态','维保记录']
const activeTab = ref('基础信息')
import { ref } from 'vue'
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="h-14 bg-[#0F1D35] border-b border-[rgba(0,191,255,0.15)] flex items-center gap-4 px-6 shrink-0">
      <button class="text-[#00BFFF] hover:text-[#33CCFF]" @click="router.back()">‹ 返回</button>
      <span class="text-base font-semibold">设备详情 #{{ route.params.id }}</span>
    </header>
    <div class="flex-1 p-4 overflow-auto">
      <div class="flex gap-1 mb-4">
        <button v-for="t in tabs" :key="t" class="px-4 py-1.5 text-xs rounded border transition-all"
          :class="activeTab===t?'bg-[rgba(0,191,255,0.12)] text-[#00BFFF] border-[rgba(0,191,255,0.25)]':'text-[#8899AA] border-transparent hover:bg-[rgba(0,191,255,0.06)]'"
          @click="activeTab=t">{{ t }}</button>
      </div>
      <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5 max-w-2xl">
        <div v-if="activeTab==='基础信息'" class="space-y-3 text-sm">
          <div class="flex justify-between py-2 border-b border-[rgba(0,191,255,0.1)]"><span class="text-[#8899AA]">设备编码</span><span class="font-mono text-[#00BFFF]">CAM-001</span></div>
          <div class="flex justify-between py-2 border-b border-[rgba(0,191,255,0.1)]"><span class="text-[#8899AA]">设备名称</span><span>湿地北入口摄像头</span></div>
          <div class="flex justify-between py-2 border-b border-[rgba(0,191,255,0.1)]"><span class="text-[#8899AA]">设备类型</span><span>摄像头</span></div>
          <div class="flex justify-between py-2 border-b border-[rgba(0,191,255,0.1)]"><span class="text-[#8899AA]">IP地址</span><span class="font-mono">192.168.1.101</span></div>
          <div class="flex justify-between py-2 border-b border-[rgba(0,191,255,0.1)]"><span class="text-[#8899AA]">安装位置</span><span>北门入口</span></div>
          <div class="flex justify-between py-2"><span class="text-[#8899AA]">状态</span><span class="text-[#00CC99] font-semibold">● 在线</span></div>
        </div>
        <div v-else class="text-sm text-[#8899AA]">暂无{{ activeTab }}数据</div>
      </div>
    </div>
  </div>
</template>
