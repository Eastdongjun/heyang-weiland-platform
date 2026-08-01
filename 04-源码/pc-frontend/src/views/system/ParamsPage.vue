<script setup lang="ts">
const params = [
  { key:'platform.name', label:'平台名称', value:'合阳洽川湿地智慧管理平台' },
  { key:'platform.logo', label:'平台Logo', value:'/logo.png' },
  { key:'alarm.auto_create_workorder', label:'告警自动建工单', value:'true', type:'switch' },
  { key:'alarm.escalation_timeout', label:'告警升级超时(分)', value:'5' },
  { key:'video.retention_days', label:'录像保留天数', value:'7' },
  { key:'night_mode.start', label:'夜间模式开始', value:'19:00' },
  { key:'night_mode.end', label:'夜间模式结束', value:'07:00' },
  { key:'session.timeout', label:'会话超时(分)', value:'30' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <aside class="w-[200px] shrink-0 bg-[#0F1D35] border-r border-[rgba(0,191,255,0.15)] p-4">
      <div class="text-xs text-[#8899AA] font-semibold mb-3 tracking-[1px]">系统配置</div>
      <nav class="space-y-0.5 text-sm">
        <div class="text-[#00BFFF] bg-[rgba(0,191,255,0.08)] rounded px-2 py-1.5 cursor-pointer">⚙️ 系统参数</div>
        <div class="text-[#8899AA] hover:text-[#E8EDF5] rounded px-2 py-1.5 cursor-pointer">📝 操作日志</div>
      </nav>
    </aside>
    <div class="flex-1 flex flex-col min-w-0">
      <header class="h-14 bg-[#0F1D35] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-6 shrink-0">
        <h1 class="text-base font-semibold">系统参数</h1>
        <button class="h-8 px-3 bg-[#00BFFF] text-[#0A1628] text-xs font-semibold rounded border-none cursor-pointer">保存</button>
      </header>
      <div class="flex-1 overflow-auto p-4">
        <div class="max-w-2xl space-y-3">
          <div v-for="p in params" :key="p.key"
            class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-4 flex items-center justify-between">
            <div><div class="text-sm font-semibold text-[#E8EDF5]">{{ p.label }}</div><div class="text-[10px] text-[#8899AA] font-mono mt-0.5">{{ p.key }}</div></div>
            <input v-if="!p.type" class="w-80 h-9 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-sm text-[#E8EDF5] outline-none focus:border-[#00BFFF] text-right font-mono" :value="p.value" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
