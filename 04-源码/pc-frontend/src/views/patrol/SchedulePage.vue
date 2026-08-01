<script setup lang="ts">
import AdminLayout from '@/components/common/AdminLayout.vue'
const shifts = [
  { day:'5/11 周一', morning:'张三', afternoon:'李四', night:'王五' },
  { day:'5/12 周二', morning:'张三', afternoon:'赵六', night:'李四' },
  { day:'5/13 周三', morning:'李四', afternoon:'王五', night:'赵六' },
]
</script>
<template>
  <AdminLayout>
    <div class="filter-bar">
      <select class="filter-select"><option>2026年第20周 (5.11-5.17)</option></select>
      <select class="filter-select"><option>全部班组</option></select>
      <button class="btn-primary">+ 生成排班</button>
    </div>
    <div class="table-wrap">
      <table class="w-full text-sm border-collapse">
        <thead><tr class="bg-[#e6f0ff]"><th class="p-3 text-left text-xs text-[#1890ff] font-medium">日期</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">白班 (8:00-16:00)</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">中班 (16:00-24:00)</th><th class="p-3 text-left text-xs text-[#1890ff] font-medium">夜班 (0:00-8:00)</th></tr></thead>
        <tbody>
          <tr v-for="s in shifts" :key="s.day" class="border-b border-[#f0f0f0] hover:bg-[#fafafa]">
            <td class="p-3 font-semibold">{{ s.day }}</td>
            <td class="p-3"><span class="px-2 py-0.5 bg-[#f0f5ff] text-xs rounded">{{ s.morning }}</span></td>
            <td class="p-3"><span class="px-2 py-0.5 bg-[#f0f5ff] text-xs rounded">{{ s.afternoon }}</span></td>
            <td class="p-3"><span class="px-2 py-0.5 bg-[#f0f5ff] text-xs rounded">{{ s.night }}</span></td>
          </tr>
        </tbody>
      </table>
    </div>
  </AdminLayout>
</template>
<style scoped>
.filter-bar { display:flex; gap:8px; margin-bottom:12px; }
.filter-select { height:32px; border:1px solid #d9d9d9; border-radius:4px; padding:0 8px; font-size:12px; outline:none; }
.btn-primary { height:32px; padding:0 16px; background:#1890ff; color:#fff; border:none; border-radius:4px; font-size:12px; cursor:pointer; margin-left:auto; }
.table-wrap { background:#fff; border:1px solid #e8e8e8; border-radius:6px; overflow:hidden; }
</style>
