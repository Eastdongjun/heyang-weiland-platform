<script setup lang="ts">
</script>
<template>
  <div class="h-screen bg-[#f0f2f5] flex flex-col" style="font-family:'PingFang SC','Microsoft YaHei',sans-serif">
    <header class="h-[50px] bg-white border-b border-[#e8e8e8] flex items-center justify-between px-5 shrink-0">
      <div class="flex items-center gap-3"><a href="/patrol/attendance" class="text-[#1890ff] text-sm no-underline">‹ 返回</a><span class="text-sm font-semibold">考勤明细</span></div>
      <span class="text-xs text-[#999]">2026年5月</span>
    </header>
    <div class="flex-1 flex min-h-0"><aside class="w-[200px] bg-[#001529] p-4 shrink-0"><div class="text-xs text-[#1890ff] mb-3">日常考勤</div><div class="text-xs text-[rgba(255,255,255,0.45)] mb-2">加班记录</div><div class="text-xs text-[rgba(255,255,255,0.45)] mb-2">请假记录</div></aside>
    <main class="flex-1 p-4 overflow-auto"><div class="bg-white border border-[#e8e8e8] rounded-md p-6"><div class="text-base font-semibold mb-4">张三 — 5月考勤明细</div><table class="w-full text-sm border-collapse"><thead><tr class="bg-[#fafafa]"><th class="p-3 text-left text-xs font-medium">日期</th><th class="p-3 text-left text-xs font-medium">上班时间</th><th class="p-3 text-left text-xs font-medium">下班时间</th><th class="p-3 text-left text-xs font-medium">巡逻时长</th><th class="p-3 text-left text-xs font-medium">状态</th></tr></thead><tbody><tr v-for="r in [{d:'05-12',i:'08:00',o:'20:00',h:'12h',s:'正常',c:'#52c41a'},{d:'05-11',i:'08:00',o:'20:00',h:'12h',s:'正常',c:'#52c41a'},{d:'05-10',i:'08:15',o:'20:00',h:'11h45m',s:'迟到',c:'#faad14'},{d:'05-09',i:'08:00',o:'20:00',h:'12h',s:'正常',c:'#52c41a'},{d:'05-08',i:'08:00',o:'20:00',h:'12h',s:'正常',c:'#52c41a'}]" :key="r.d" class="border-b border-[#f0f0f0]"><td class="p-3">{{ r.d }}</td><td class="p-3 font-mono text-xs">{{ r.i }}</td><td class="p-3 font-mono text-xs">{{ r.o }}</td><td class="p-3 font-mono text-xs">{{ r.h }}</td><td class="p-3"><span class="font-semibold text-xs" :style="{color:r.c}">{{ r.s }}</span></td></tr></tbody></table><button class="mt-4 h-8 px-4 bg-white border border-[#d9d9d9] rounded text-xs cursor-pointer">📥 导出明细</button></div></main></div>
  </div>
</template>
