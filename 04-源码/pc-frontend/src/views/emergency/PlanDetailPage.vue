<script setup lang="ts">
import { useRouter } from 'vue-router'
const router = useRouter()
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="fixed top-0 left-0 right-0 h-16 bg-[rgba(10,25,50,0.95)] border-b border-[rgba(0,191,255,0.3)] flex items-center px-6 z-[1000]" style="backdrop-filter:blur(12px)">
      <button class="w-9 h-9 border border-[rgba(0,191,255,0.15)] bg-[rgba(0,191,255,0.08)] rounded-md text-[#00BFFF] text-lg flex items-center justify-center hover:border-[#00BFFF] mr-4" @click="router.back()">‹</button>
      <span class="text-lg font-semibold text-[#00BFFF]">应急预案详情</span>
    </header>
    <div class="pt-[88px] px-6 pb-6 max-w-3xl mx-auto w-full overflow-y-auto space-y-4">
      <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-5">
        <div class="text-base font-semibold text-[#00BFFF] mb-4">火情应急处置预案</div>
        <div class="space-y-3 text-sm"><div class="text-[#8899AA]">启动条件：</div><div class="text-[#E8EDF5]">热成像温度≥35°C 且烟雾浓度≥70%，或人工确认火情</div><div class="text-[#8899AA] mt-4">处置步骤：</div><div class="text-[#E8EDF5] space-y-1"><div>1. 确认火情位置和等级</div><div>2. 通知最近巡逻员赶赴现场</div><div>3. 调取周边摄像头实时画面</div><div>4. 联动消防部门</div><div>5. 记录处置全过程</div></div></div>
      </div>
    </div>
  </div>
</template>
