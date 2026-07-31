<script setup lang="ts">
const alerts = [
  { id: 1, level: 1, levelName: '一级', device: '热成像云台-01', type: '火警告警', location: '洽川湿地北部', time: '2026-08-01 14:32:15', status: '未处理', statusColor: 'text-[#FF3366]' },
  { id: 2, level: 2, levelName: '二级', device: '瞭望塔-03', type: '疑似火情', location: '处女泉景区', time: '2026-08-01 14:28:40', status: '未处理', statusColor: 'text-[#FF3366]' },
]
</script>
<template>
  <div class="min-h-screen bg-[#0A1628] text-[#E8EDF5]">
    <div class="p-6">
      <h1 class="text-xl font-bold mb-4 flex items-center gap-2"><span class="w-[3px] h-4 bg-[#00BFFF] rounded-sm inline-block" />告警中心</h1>
      <div class="bg-[#0F1D35] border border-[rgba(0,191,255,0.15)] rounded-lg p-4">
        <table class="w-full text-sm">
          <thead><tr class="text-[#8899BB] text-left border-b border-[rgba(0,191,255,0.1)]"><th class="p-2">等级</th><th class="p-2">设备</th><th class="p-2">类型</th><th class="p-2">位置</th><th class="p-2">时间</th><th class="p-2">状态</th></tr></thead>
          <tbody>
            <tr v-for="a in alerts" :key="a.id" class="border-b border-[rgba(0,191,255,0.05)] hover:bg-[rgba(0,191,255,0.04)] cursor-pointer">
              <td class="p-2"><span class="px-1.5 py-0.5 rounded text-xs font-semibold" :class="a.level === 1 ? 'bg-[#FF3366] text-white' : 'bg-[#FFA502] text-white'">{{ a.levelName }}</span></td>
              <td class="p-2">{{ a.device }}</td><td class="p-2">{{ a.type }}</td><td class="p-2 text-[#8899BB]">{{ a.location }}</td><td class="p-2 text-[#8899BB]">{{ a.time }}</td>
              <td class="p-2" :class="a.statusColor">{{ a.status }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
