<script setup lang="ts">
const records = [
  { name:'张三', dept:'巡逻一队', workDays:22, late:0, early:0, absent:0, overtime:12 },
  { name:'李四', dept:'巡逻二队', workDays:22, late:1, early:0, absent:0, overtime:8 },
  { name:'王五', dept:'巡逻一队', workDays:21, late:0, early:1, absent:1, overtime:15 },
  { name:'赵六', dept:'巡逻二队', workDays:22, late:0, early:0, absent:0, overtime:6 },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <aside class="w-[200px] shrink-0 bg-[#0F1D35] border-r border-[rgba(0,191,255,0.15)] p-4">
      <div class="text-xs text-[#8899AA] font-semibold mb-3 tracking-[1px]">巡逻管理</div>
      <nav class="space-y-0.5 text-sm">
        <div class="text-[#8899AA] hover:text-[#E8EDF5] rounded px-2 py-1.5 cursor-pointer">📅 排班管理</div>
        <div class="text-[#00BFFF] bg-[rgba(0,191,255,0.08)] rounded px-2 py-1.5 cursor-pointer">📊 考勤统计</div>
      </nav>
    </aside>
    <div class="flex-1 flex flex-col min-w-0">
      <header class="h-14 bg-[#0F1D35] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-6 shrink-0">
        <h1 class="text-base font-semibold">考勤统计 · 2026年8月</h1>
      </header>
      <div class="flex-1 overflow-auto p-4">
        <div class="grid grid-cols-4 gap-3 mb-4">
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-4 text-center"><div class="text-2xl font-bold text-[#00BFFF] font-mono">22</div><div class="text-[11px] text-[#8899AA] mt-1">应出勤天数</div></div>
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-4 text-center"><div class="text-2xl font-bold text-[#00CC99] font-mono">87</div><div class="text-[11px] text-[#8899AA] mt-1">实际出勤</div></div>
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-4 text-center"><div class="text-2xl font-bold text-[#FFCC00] font-mono">1</div><div class="text-[11px] text-[#8899AA] mt-1">迟到</div></div>
          <div class="bg-[rgba(15,30,55,0.85)] border border-[rgba(0,191,255,0.15)] rounded-lg p-4 text-center"><div class="text-2xl font-bold text-[#FF3366] font-mono">1</div><div class="text-[11px] text-[#8899AA] mt-1">缺勤</div></div>
        </div>
        <table class="w-full text-sm">
          <thead><tr class="text-[#8899AA] text-left border-b border-[rgba(0,191,255,0.1)] text-xs"><th class="p-2 font-medium">姓名</th><th class="p-2 font-medium">部门</th><th class="p-2 font-medium">出勤天</th><th class="p-2 font-medium">迟到</th><th class="p-2 font-medium">早退</th><th class="p-2 font-medium">缺勤</th><th class="p-2 font-medium">加班(h)</th></tr></thead>
          <tbody>
            <tr v-for="r in records" :key="r.name" class="border-b border-[rgba(0,191,255,0.05)] hover:bg-[rgba(0,191,255,0.04)]">
              <td class="p-2 font-semibold">{{ r.name }}</td><td class="p-2 text-xs">{{ r.dept }}</td><td class="p-2 text-[#00BFFF] font-mono">{{ r.workDays }}</td>
              <td class="p-2"><span :class="r.late>0?'text-[#FFCC00]':'text-[#8899AA]'">{{ r.late }}</span></td>
              <td class="p-2"><span :class="r.early>0?'text-[#FFCC00]':'text-[#8899AA]'">{{ r.early }}</span></td>
              <td class="p-2"><span :class="r.absent>0?'text-[#FF3366] font-semibold':'text-[#8899AA]'">{{ r.absent }}</span></td>
              <td class="p-2 text-[#00BFFF] font-mono">{{ r.overtime }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
