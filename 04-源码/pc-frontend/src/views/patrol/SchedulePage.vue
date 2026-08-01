<script setup lang="ts">
const shifts = [
  { day:'周一', morning:'张三', afternoon:'李四', night:'王五' },
  { day:'周二', morning:'张三', afternoon:'赵六', night:'李四' },
  { day:'周三', morning:'李四', afternoon:'王五', night:'赵六' },
  { day:'周四', morning:'张三', afternoon:'李四', night:'王五' },
  { day:'周五', morning:'赵六', afternoon:'张三', night:'李四' },
  { day:'周六', morning:'王五', afternoon:'赵六', night:'张三' },
  { day:'周日', morning:'李四', afternoon:'王五', night:'赵六' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <aside class="w-[200px] shrink-0 bg-[#0F1D35] border-r border-[rgba(0,191,255,0.15)] p-4">
      <div class="text-xs text-[#8899AA] font-semibold mb-3 tracking-[1px]">巡逻管理</div>
      <nav class="space-y-0.5 text-sm">
        <div class="text-[#00BFFF] bg-[rgba(0,191,255,0.08)] rounded px-2 py-1.5 cursor-pointer">📅 排班管理</div>
        <div class="text-[#8899AA] hover:text-[#E8EDF5] rounded px-2 py-1.5 cursor-pointer">📊 考勤统计</div>
      </nav>
    </aside>
    <div class="flex-1 flex flex-col min-w-0">
      <header class="h-14 bg-[#0F1D35] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-6 shrink-0">
        <h1 class="text-base font-semibold">排班管理</h1>
        <button class="h-8 px-3 bg-[#00BFFF] text-[#0A1628] text-xs font-semibold rounded border-none cursor-pointer">+ 编辑排班</button>
      </header>
      <div class="flex-1 overflow-auto p-4">
        <table class="w-full text-sm">
          <thead><tr class="text-[#8899AA] text-left border-b border-[rgba(0,191,255,0.1)] text-xs"><th class="p-3 font-medium">日期</th><th class="p-3 font-medium">早班 (8:00-16:00)</th><th class="p-3 font-medium">中班 (16:00-24:00)</th><th class="p-3 font-medium">晚班 (0:00-8:00)</th></tr></thead>
          <tbody>
            <tr v-for="s in shifts" :key="s.day" class="border-b border-[rgba(0,191,255,0.05)] hover:bg-[rgba(0,191,255,0.04)]">
              <td class="p-3 font-semibold">{{ s.day }}</td>
              <td class="p-3"><span class="px-2 py-0.5 bg-[rgba(0,191,255,0.08)] rounded text-xs">{{ s.morning }}</span></td>
              <td class="p-3"><span class="px-2 py-0.5 bg-[rgba(0,191,255,0.08)] rounded text-xs">{{ s.afternoon }}</span></td>
              <td class="p-3"><span class="px-2 py-0.5 bg-[rgba(0,191,255,0.08)] rounded text-xs">{{ s.night }}</span></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
