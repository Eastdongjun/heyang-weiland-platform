<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const search = ref('')
const devices = [
  { id:1, code:'CAM-001', name:'湿地北入口摄像头', type:'摄像头', status:'在线', location:'北门入口', ip:'192.168.1.101', onlineTime:'3天12时' },
  { id:2, code:'CAM-002', name:'核心区热成像云台', type:'热成像云台', status:'在线', location:'核心保护区', ip:'192.168.1.102', onlineTime:'15天8时' },
  { id:3, code:'CAM-003', name:'观鸟台球机', type:'球机', status:'在线', location:'观鸟台', ip:'192.168.1.103', onlineTime:'7天3时' },
  { id:4, code:'CAM-004', name:'南岸4G球机', type:'球机', status:'离线', location:'南岸滩涂', ip:'4G', onlineTime:'-', offlineTime:'2时15分' },
  { id:5, code:'SEN-001', name:'A区环境传感器', type:'传感器', status:'在线', location:'湿地入口', ip:'192.168.1.201', onlineTime:'30天' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <aside class="w-[200px] shrink-0 bg-[#0F1D35] border-r border-[rgba(0,191,255,0.15)] p-4">
      <div class="text-xs text-[#8899AA] font-semibold mb-3 tracking-[1px]">设备管理</div>
      <nav class="space-y-0.5 text-sm">
        <div class="text-[#00BFFF] bg-[rgba(0,191,255,0.08)] rounded px-2 py-1.5 cursor-pointer">📋 设备台账</div>
        <div class="text-[#8899AA] hover:text-[#E8EDF5] rounded px-2 py-1.5 cursor-pointer">📁 设备分组</div>
        <div class="text-[#8899AA] hover:text-[#E8EDF5] rounded px-2 py-1.5 cursor-pointer">📶 流量维保</div>
      </nav>
    </aside>
    <div class="flex-1 flex flex-col min-w-0">
      <header class="h-14 bg-[#0F1D35] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-6 shrink-0">
        <h1 class="text-base font-semibold">设备台账</h1>
        <div class="flex gap-2">
          <input v-model="search" class="h-8 w-48 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-xs text-[#E8EDF5] outline-none focus:border-[#00BFFF]" placeholder="搜索设备..." />
          <button class="h-8 px-3 bg-[#00BFFF] text-[#0A1628] text-xs font-semibold rounded border-none cursor-pointer">+ 新增</button>
        </div>
      </header>
      <div class="flex-1 overflow-auto p-4">
        <table class="w-full text-sm">
          <thead><tr class="text-[#8899AA] text-left border-b border-[rgba(0,191,255,0.1)] text-xs"><th class="p-2 font-medium">编码</th><th class="p-2 font-medium">名称</th><th class="p-2 font-medium">类型</th><th class="p-2 font-medium">状态</th><th class="p-2 font-medium">位置</th><th class="p-2 font-medium">IP</th><th class="p-2 font-medium">在线时长</th><th class="p-2 font-medium">操作</th></tr></thead>
          <tbody>
            <tr v-for="d in devices" :key="d.id" class="border-b border-[rgba(0,191,255,0.05)] hover:bg-[rgba(0,191,255,0.04)] cursor-pointer" @click="router.push('/device/'+d.id)">
              <td class="p-2 text-[#00BFFF] font-mono text-xs">{{ d.code }}</td><td class="p-2">{{ d.name }}</td><td class="p-2 text-[#8899AA]">{{ d.type }}</td>
              <td class="p-2"><span class="font-semibold text-xs" :class="d.status==='在线'?'text-[#00CC99]':'text-[#808080]'">● {{ d.status }}</span></td>
              <td class="p-2 text-[#8899AA]">{{ d.location }}</td><td class="p-2 text-[#8899AA] font-mono text-xs">{{ d.ip }}</td>
              <td class="p-2 text-[#8899AA] text-xs">{{ d.onlineTime || d.offlineTime }}</td>
              <td class="p-2"><button class="text-xs text-[#00BFFF] hover:text-[#33CCFF]">详情</button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
