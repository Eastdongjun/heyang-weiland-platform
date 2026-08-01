<script setup lang="ts">
const roles = [
  { id:1, name:'ADMIN', label:'系统管理员', users:1, desc:'全部权限，可管理系统配置、用户、设备等所有模块' },
  { id:2, name:'OPERATOR', label:'指挥中心值班员', users:3, desc:'值班操作：GIS大屏、视频监控、告警处置、工单调度' },
  { id:3, name:'RANGER', label:'一线巡逻员', users:8, desc:'仅App端：接收任务、导航、拍照反馈、查看视频' },
  { id:4, name:'MAINTAINER', label:'维保人员', users:2, desc:'设备管理、维保计划、流量监控' },
]
const menus = ['GIS大屏','视频监控','告警中心','应急管理','工单调度','环境监测','数据统计','无人机管理','VR查看','设备台账','设备分组','流量维保','用户管理','角色权限','排班管理','考勤统计','操作日志','系统参数']
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <aside class="w-[200px] shrink-0 bg-[#0F1D35] border-r border-[rgba(0,191,255,0.15)] p-4">
      <div class="text-xs text-[#8899AA] font-semibold mb-3 tracking-[1px]">用户管理</div>
      <nav class="space-y-0.5 text-sm">
        <div class="text-[#8899AA] hover:text-[#E8EDF5] rounded px-2 py-1.5 cursor-pointer">👥 用户列表</div>
        <div class="text-[#00BFFF] bg-[rgba(0,191,255,0.08)] rounded px-2 py-1.5 cursor-pointer">🔑 角色权限</div>
      </nav>
    </aside>
    <div class="flex-1 flex min-w-0">
      <div class="w-[300px] shrink-0 border-r border-[rgba(0,191,255,0.12)] p-4 overflow-y-auto">
        <h2 class="text-sm font-semibold mb-3">角色列表</h2>
        <div v-for="r in roles" :key="r.id"
          class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.12)] rounded-lg p-3 mb-2 cursor-pointer hover:border-[rgba(0,191,255,0.3)] transition-all">
          <div class="flex justify-between items-center mb-1"><span class="text-sm font-semibold text-[#00BFFF] font-mono">{{ r.name }}</span><span class="text-[10px] text-[#8899AA]">{{ r.users }}人</span></div>
          <div class="text-xs text-[#E8EDF5] mb-1">{{ r.label }}</div>
          <div class="text-[10px] text-[#8899AA] leading-relaxed">{{ r.desc }}</div>
        </div>
      </div>
      <div class="flex-1 p-4 overflow-y-auto">
        <h2 class="text-sm font-semibold mb-3">菜单权限分配</h2>
        <div class="grid grid-cols-3 gap-2">
          <div v-for="m in menus" :key="m" class="flex items-center gap-2 p-2 bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.12)] rounded text-xs text-[#8899AA] hover:border-[#00BFFF] cursor-pointer transition-all">
            <input type="checkbox" checked class="accent-[#00BFFF]" /><span>{{ m }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
