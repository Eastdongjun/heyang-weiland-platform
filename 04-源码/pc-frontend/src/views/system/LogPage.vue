<script setup lang="ts">
import AdminLayout from '@/components/common/AdminLayout.vue'
const logs = [
  { time:'2026-08-01 10:30:15', user:'赵六', ip:'192.168.1.100', type:'登录', action:'用户登录系统', result:'成功', tColor:'#1890ff' },
  { time:'2026-08-01 10:35:22', user:'赵六', ip:'192.168.1.100', type:'修改', action:'修改工单 WO-20260801-002 状态', result:'成功', tColor:'#faad14' },
  { time:'2026-08-01 10:50:08', user:'张三', ip:'10.0.0.55', type:'登录', action:'APP端登录', result:'成功', tColor:'#1890ff' },
  { time:'2026-08-01 11:02:41', user:'赵六', ip:'192.168.1.100', type:'新增', action:'新增用户 钱七', result:'成功', tColor:'#52c41a' },
  { time:'2026-08-01 11:15:33', user:'赵六', ip:'192.168.1.100', type:'删除', action:'删除设备 CAM-0105', result:'成功', tColor:'#ff4d4f' },
]
</script>
<template>
  <AdminLayout>
    <div class="filter-bar">
      <select class="filter-select"><option>全部类型</option><option>登录</option><option>新增</option><option>修改</option><option>删除</option></select>
      <input class="filter-input" placeholder="搜索关键字..." />
      <button class="btn-default">📥 导出</button>
    </div>
    <div class="table-wrap">
      <table class="w-full text-sm border-collapse"><thead><tr class="bg-[#e6f0ff]"><th class="p-3 text-left text-xs text-[#1890ff] font-medium">时间</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">用户</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">IP地址</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">操作类型</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">操作内容</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">结果</th></tr></thead>
        <tbody><tr v-for="l in logs" :key="l.time" class="border-b border-[#f0f0f0] hover:bg-[#fafafa]"><td class="p-3 text-xs font-mono text-[#999]">{{ l.time }}</td><td class="p-3 font-semibold">{{ l.user }}</td><td class="p-3 text-xs font-mono text-[#999]">{{ l.ip }}</td><td class="p-3"><span class="px-2 py-0.5 rounded text-[11px] font-medium text-white" :style="{background:l.tColor}">{{ l.type }}</span></td><td class="p-3 text-xs">{{ l.action }}</td><td class="p-3"><span class="text-xs font-semibold text-[#52c41a]">✓ {{ l.result }}</span></td></tr></tbody></table>
      <div class="p-3 text-right text-xs text-[#999]">共 156 条 &nbsp; 1 2 3 4</div>
    </div>
  </AdminLayout>
</template>
<style scoped>
.filter-bar { display:flex; gap:8px; margin-bottom:12px; }
.filter-select, .filter-input { height:32px; border:1px solid #d9d9d9; border-radius:4px; padding:0 8px; font-size:12px; outline:none; }
.filter-input { width:180px; }
.btn-default { height:32px; padding:0 12px; background:#fff; border:1px solid #d9d9d9; border-radius:4px; font-size:12px; cursor:pointer; margin-left:auto; }
.table-wrap { background:#fff; border:1px solid #e8e8e8; border-radius:6px; overflow:hidden; }
</style>
