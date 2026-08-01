<script setup lang="ts">
import AdminLayout from '@/components/common/AdminLayout.vue'
const records = [
  { date:'05-12', name:'张三', group:'东区巡逻队', in:'08:00', out:'20:00', duration:'12h', status:'正常', sColor:'#52c41a' },
  { date:'05-12', name:'李四', group:'南区巡逻队', in:'08:15', out:'20:00', duration:'11h45m', status:'迟到', sColor:'#faad14' },
  { date:'05-12', name:'王五', group:'东区巡逻队', in:'—', out:'—', duration:'—', status:'缺勤', sColor:'#ff4d4f' },
  { date:'05-11', name:'赵六', group:'北区巡逻队', in:'08:00', out:'20:00', duration:'12h', status:'正常', sColor:'#52c41a' },
]
</script>
<template>
  <AdminLayout>
    <div class="stats-row"><div v-for="s in [{l:'当月人次',v:28,c:'#1890ff'},{l:'正常',v:26,c:'#52c41a'},{l:'迟到',v:1,c:'#faad14'},{l:'缺勤',v:1,c:'#ff4d4f'}]" :key="s.l" class="stat-card"><div class="stat-num" :style="{color:s.c}">{{ s.v }}</div><div class="stat-label">{{ s.l }}</div></div></div>
    <div class="table-wrap">
      <table class="w-full text-sm border-collapse"><thead><tr class="bg-[#e6f0ff]"><th class="p-3 text-left text-xs text-[#1890ff] font-medium">日期</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">姓名</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">班组</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">上班</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">下班</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">时长</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">状态</th></tr></thead>
        <tbody><tr v-for="r in records" :key="r.name+r.date" class="border-b border-[#f0f0f0] hover:bg-[#fafafa]"><td class="p-3">{{ r.date }}</td><td class="p-3 font-semibold">{{ r.name }}</td><td class="p-3 text-xs">{{ r.group }}</td><td class="p-3 text-xs font-mono">{{ r.in }}</td><td class="p-3 text-xs font-mono">{{ r.out }}</td><td class="p-3 text-xs font-mono">{{ r.duration }}</td><td class="p-3"><span class="font-semibold text-xs" :style="{color:r.sColor}">● {{ r.status }}</span></td></tr></tbody></table>
    </div>
  </AdminLayout>
</template>
<style scoped>
.stats-row { display:flex; gap:12px; margin-bottom:16px; }
.stat-card { flex:1; background:#fff; border:1px solid #e8e8e8; border-radius:6px; padding:16px; text-align:center; }
.stat-num { font-size:24px; font-weight:700; }
.stat-label { font-size:12px; color:#999; margin-top:4px; }
.table-wrap { background:#fff; border:1px solid #e8e8e8; border-radius:6px; overflow:hidden; }
</style>
