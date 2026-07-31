<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const now = ref(new Date())
let timer: number
onMounted(() => { timer = window.setInterval(() => now.value = new Date(), 1000) })
onUnmounted(() => clearInterval(timer))

const orders = [
  { id: 'WO-001', type: '火情处置', level: '紧急', levelColor: 'text-[#FF3366] bg-[rgba(255,51,102,0.12)] border-[rgba(255,51,102,0.25)]', status: '处理中', statusColor: 'text-[#FFCC00]', location: '东岸芦苇荡', person: '张三', time: '10:42' },
  { id: 'WO-002', type: '设备抢修', level: '重要', levelColor: 'text-[#FFCC00] bg-[rgba(255,204,0,0.12)] border-[rgba(255,204,0,0.25)]', status: '待接单', statusColor: 'text-[#FF3366]', location: '南岸滩涂', person: '待派发', time: '09:15' },
  { id: 'WO-003', type: '水质异常排查', level: '一般', levelColor: 'text-[#40a9ff] bg-[rgba(64,169,255,0.12)] border-[rgba(64,169,255,0.25)]', status: '已完成', statusColor: 'text-[#00CC99]', location: 'C区采样点', person: '李四', time: '08:30' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="h-16 bg-gradient-to-b from-[rgba(10,22,40,0.98)] to-[rgba(10,22,40,0.85)] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-7 shrink-0 relative z-[100]">
      <div class="absolute bottom-0 left-[10%] right-[10%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="w-60 text-sm font-semibold text-[#00BFFF] font-mono">{{ now.getFullYear() }}年{{ now.getMonth()+1 }}月{{ now.getDate() }}日</div>
      <div class="absolute left-1/2 -translate-x-1/2 text-center"><div class="text-xl font-bold tracking-[4px] bg-gradient-to-r from-white to-[#00BFFF] bg-clip-text text-transparent">应急指挥中心</div><div class="text-[10px] text-[#8899AA] tracking-[1px] mt-0.5">快速响应 · 精准调度</div></div>
      <div class="flex items-center gap-5 text-[13px]"><span class="text-[#8899AA]">☀️ 26°C</span><span class="text-[22px] font-bold text-[#00BFFF] font-mono">{{ now.toLocaleTimeString('zh-CN',{hour12:false}) }}</span><a href="/nav" class="text-xs text-[#8899AA] no-underline px-2.5 py-1 border border-[rgba(0,191,255,0.15)] rounded">退出</a></div>
    </header>
    <div class="flex-1 p-4 overflow-auto">
      <div class="flex gap-4 mb-4">
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-4 flex-1 text-center"><div class="text-2xl font-bold text-[#E8EDF5] font-mono">12</div><div class="text-[11px] text-[#8899AA] mt-1">应急工单总数</div></div>
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-4 flex-1 text-center"><div class="text-2xl font-bold text-[#FF3366] font-mono">3</div><div class="text-[11px] text-[#8899AA] mt-1">处理中</div></div>
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-4 flex-1 text-center"><div class="text-2xl font-bold text-[#FFCC00] font-mono">2</div><div class="text-[11px] text-[#8899AA] mt-1">待派发</div></div>
        <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-md p-4 flex-1 text-center"><div class="text-2xl font-bold text-[#00CC99] font-mono">7</div><div class="text-[11px] text-[#8899AA] mt-1">已办结</div></div>
      </div>
      <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg overflow-hidden">
        <table class="w-full text-sm">
          <thead><tr class="text-[#8899AA] text-left border-b border-[rgba(0,191,255,0.1)] text-xs"><th class="p-3 font-medium">工单号</th><th class="p-3 font-medium">类型</th><th class="p-3 font-medium">级别</th><th class="p-3 font-medium">状态</th><th class="p-3 font-medium">位置</th><th class="p-3 font-medium">负责人</th><th class="p-3 font-medium">时间</th><th class="p-3 font-medium">操作</th></tr></thead>
          <tbody>
            <tr v-for="o in orders" :key="o.id" class="border-b border-[rgba(0,191,255,0.05)] hover:bg-[rgba(0,191,255,0.04)] cursor-pointer">
              <td class="p-3 text-[#00BFFF] font-mono">{{ o.id }}</td><td class="p-3 text-[#E8EDF5]">{{ o.type }}</td>
              <td class="p-3"><span class="px-2 py-0.5 rounded text-xs font-semibold border" :class="o.levelColor">{{ o.level }}</span></td>
              <td class="p-3"><span class="font-semibold" :class="o.statusColor">● {{ o.status }}</span></td>
              <td class="p-3 text-[#8899AA]">{{ o.location }}</td><td class="p-3">{{ o.person }}</td><td class="p-3 text-[#8899AA] font-mono text-xs">{{ o.time }}</td>
              <td class="p-3"><button class="text-xs text-[#00BFFF] hover:text-[#33CCFF] px-2 py-1 border border-[rgba(0,191,255,0.15)] rounded" @click="router.push('/emergency/order/'+o.id)">详情</button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
