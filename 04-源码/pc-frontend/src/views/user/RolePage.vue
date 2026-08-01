<script setup lang="ts">
import AdminLayout from '@/components/common/AdminLayout.vue'
const roles = [
  { name:'管理员', desc:'系统最高权限，可管理所有模块', users:2 },
  { name:'调度员', desc:'负责应急调度、工单派发', users:3 },
  { name:'小队长', desc:'管理巡逻队员，查看区域数据', users:4 },
  { name:'巡逻员', desc:'接收任务、提交处置反馈', users:18 },
  { name:'维保人员', desc:'设备维护、流量管理', users:2 },
]
const modules = ['设备台账','工单调度','用户管理','排班管理','考勤统计','操作日志','角色权限','大屏GIS']
const perms = ['查看','新增','修改','删除','导出']
</script>
<template>
  <AdminLayout>
    <div class="flex gap-4" style="min-height:0">
      <div class="role-list" style="width:260px;flex-shrink:0">
        <div v-for="r in roles" :key="r.name" class="role-card bg-white border border-[#e8e8e8] rounded-md p-3 mb-2 cursor-pointer hover:border-[#1890ff]">
          <div class="flex justify-between items-center mb-1"><span class="text-sm font-semibold">{{ r.name }}</span><span class="text-[11px] text-[#999]">{{ r.users }}人</span></div>
          <div class="text-xs text-[#999]">{{ r.desc }}</div>
        </div>
      </div>
      <div class="flex-1 bg-white border border-[#e8e8e8] rounded-md p-4">
        <div class="flex justify-between items-center mb-3"><h3 class="text-sm font-semibold">权限矩阵（管理员）</h3><button class="h-8 px-4 bg-[#1890ff] text-white text-xs rounded border-none cursor-pointer">保存修改</button></div>
        <table class="w-full text-xs border-collapse">
          <thead><tr><th class="text-left p-2 bg-[#fafafa] border-b">功能模块</th><th v-for="p in perms" :key="p" class="text-center p-2 bg-[#fafafa] border-b">{{ p }}</th></tr></thead>
          <tbody><tr v-for="m in modules" :key="m"><td class="p-2 border-b font-medium">{{ m }}</td><td v-for="p in perms" :key="p" class="text-center p-2 border-b"><input type="checkbox" :checked="!(m==='大屏GIS'&&p!=='查看')" class="accent-[#1890ff]" /></td></tr></tbody>
        </table>
      </div>
    </div>
  </AdminLayout>
</template>
