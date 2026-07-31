<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const now = ref(new Date())
let timer: number
onMounted(() => { timer = window.setInterval(() => now.value = new Date(), 1000) })
onUnmounted(() => clearInterval(timer))

const activeTab = ref('all')
const tabs = [{k:'all',l:'全部'},{k:'pending',l:'待派发'},{k:'processing',l:'处理中'},{k:'done',l:'已完成'}]
const orders = [
  { id:'WO-20260801-001', type:'巡检', priority:'紧急', pColor:'text-[#FF3366] bg-[rgba(255,51,102,0.12)]', status:'待派发', sColor:'text-[#FF3366]', location:'北门入口', creator:'系统', time:'10:42' },
  { id:'WO-20260801-002', type:'维修', priority:'重要', pColor:'text-[#FFCC00] bg-[rgba(255,204,0,0.12)]', status:'处理中', sColor:'text-[#FFCC00]', location:'南岸滩涂', creator:'王值班', time:'09:15' },
  { id:'WO-20260801-003', type:'事件', priority:'一般', pColor:'text-[#40a9ff] bg-[rgba(64,169,255,0.12)]', status:'已完成', sColor:'text-[#00CC99]', location:'西门停车场', creator:'李四', time:'08:30' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="h-16 bg-gradient-to-b from-[rgba(10,22,40,0.98)] to-[rgba(10,22,40,0.85)] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-7 shrink-0 relative z-[100]">
      <div class="absolute bottom-0 left-[10%] right-[10%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="w-60 text-sm font-semibold text-[#00BFFF] font-mono">{{ now.getFullYear() }}年{{ now.getMonth()+1 }}月{{ now.getDate() }}日</div>
      <div class="absolute left-1/2 -translate-x-1/2 text-center"><div class="text-xl font-bold tracking-[4px] bg-gradient-to-r from-white to-[#00BFFF] bg-clip-text text-transparent">工单调度中心</div><div class="text-[10px] text-[#8899AA] tracking-[1px] mt-0.5">智能派单 · 全程追踪</div></div>
      <div class="flex items-center gap-5 text-[13px]"><span class="text-[22px] font-bold text-[#00BFFF] font-mono">{{ now.toLocaleTimeString('zh-CN',{hour12:false}) }}</span><a href="/nav" class="text-xs text-[#8899AA] no-underline px-2.5 py-1 border border-[rgba(0,191,255,0.15)] rounded">退出</a></div>
    </header>
    <div class="flex-1 p-4 overflow-auto">
      <div class="flex items-center justify-between mb-4">
        <div class="flex gap-1">
          <button v-for="t in tabs" :key="t.k" class="px-3 py-1.5 text-xs rounded border transition-all"
            :class="activeTab===t.k?'bg-[rgba(0,191,255,0.12)] text-[#00BFFF] border-[rgba(0,191,255,0.25)]':'text-[#8899AA] border-transparent hover:bg-[rgba(0,191,255,0.06)]'"
            @click="activeTab=t.k">{{t.l}}</button>
        </div>
        <button class="px-4 py-1.5 bg-gradient-to-r from-[#00BFFF] to-[#0099cc] text-[#0A1628] text-xs font-semibold rounded cursor-pointer border-none" @click="router.push('/workorder/create')">+ 新建工单</button>
      </div>
      <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg overflow-hidden">
        <table class="w-full text-sm">
          <thead><tr class="text-[#8899AA] text-left border-b border-[rgba(0,191,255,0.1)] text-xs"><th class="p-3 font-medium">工单号</th><th class="p-3 font-medium">类型</th><th class="p-3 font-medium">优先级</th><th class="p-3 font-medium">状态</th><th class="p-3 font-medium">位置</th><th class="p-3 font-medium">创建人</th><th class="p-3 font-medium">时间</th><th class="p-3 font-medium">操作</th></tr></thead>
          <tbody>
            <tr v-for="o in orders" :key="o.id" class="border-b border-[rgba(0,191,255,0.05)] hover:bg-[rgba(0,191,255,0.04)] cursor-pointer">
              <td class="p-3 text-[#00BFFF] font-mono text-xs">{{ o.id }}</td><td class="p-3 text-[#E8EDF5]">{{ o.type }}</td>
              <td class="p-3"><span class="px-2 py-0.5 rounded text-xs font-semibold" :class="o.pColor">{{ o.priority }}</span></td>
              <td class="p-3"><span class="font-semibold" :class="o.sColor">● {{ o.status }}</span></td>
              <td class="p-3 text-[#8899AA]">{{ o.location }}</td><td class="p-3">{{ o.creator }}</td><td class="p-3 text-[#8899AA] font-mono text-xs">{{ o.time }}</td>
              <td class="p-3 flex gap-2"><button class="text-xs text-[#00BFFF] hover:text-[#33CCFF]" @click="router.push('/workorder/'+o.id)">详情</button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
