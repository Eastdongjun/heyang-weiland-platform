<script setup lang="ts">
const logs = [
  { id:1, user:'admin', action:'登录系统', target:'系统', ip:'192.168.1.100', time:'2026-08-01 10:30:15' },
  { id:2, user:'admin', action:'确认告警', target:'AL-001 火警告警', ip:'192.168.1.100', time:'2026-08-01 10:35:22' },
  { id:3, user:'zhangsan', action:'接单', target:'WO-001 巡检工单', ip:'10.0.0.55', time:'2026-08-01 10:50:08' },
  { id:4, user:'admin', action:'修改设备', target:'CAM-003 参数调整', ip:'192.168.1.100', time:'2026-08-01 11:02:41' },
  { id:5, user:'lisi', action:'提交处置', target:'WO-003 维修工单', ip:'10.0.0.88', time:'2026-08-01 11:15:33' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <aside class="w-[200px] shrink-0 bg-[#0F1D35] border-r border-[rgba(0,191,255,0.15)] p-4">
      <div class="text-xs text-[#8899AA] font-semibold mb-3 tracking-[1px]">系统配置</div>
      <nav class="space-y-0.5 text-sm">
        <div class="text-[#8899AA] hover:text-[#E8EDF5] rounded px-2 py-1.5 cursor-pointer">⚙️ 系统参数</div>
        <div class="text-[#00BFFF] bg-[rgba(0,191,255,0.08)] rounded px-2 py-1.5 cursor-pointer">📝 操作日志</div>
      </nav>
    </aside>
    <div class="flex-1 flex flex-col min-w-0">
      <header class="h-14 bg-[#0F1D35] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-6 shrink-0">
        <h1 class="text-base font-semibold">操作日志</h1>
        <div class="text-[10px] text-[#8899AA]">只追加 · 不可删除 · 保留≥1年</div>
      </header>
      <div class="flex-1 overflow-auto p-4">
        <table class="w-full text-sm">
          <thead><tr class="text-[#8899AA] text-left border-b border-[rgba(0,191,255,0.1)] text-xs"><th class="p-2 font-medium">时间</th><th class="p-2 font-medium">操作人</th><th class="p-2 font-medium">操作</th><th class="p-2 font-medium">操作对象</th><th class="p-2 font-medium">IP地址</th></tr></thead>
          <tbody>
            <tr v-for="l in logs" :key="l.id" class="border-b border-[rgba(0,191,255,0.05)] hover:bg-[rgba(0,191,255,0.04)]">
              <td class="p-2 text-[#8899AA] font-mono text-xs">{{ l.time }}</td>
              <td class="p-2 font-semibold">{{ l.user }}</td><td class="p-2">{{ l.action }}</td><td class="p-2">{{ l.target }}</td>
              <td class="p-2 text-[#8899AA] font-mono text-xs">{{ l.ip }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
