<script setup lang="ts">
import AdminLayout from '@/components/common/AdminLayout.vue'
const groups = [
  { title:'站点信息', fields:[{l:'站点名称',v:'合阳湿地智慧管理平台'},{l:'站点简称',v:'合阳湿地'},{l:'Logo URL',v:'/assets/logo.png',hint:'建议尺寸 120×40px'}] },
  { title:'地图默认配置', fields:[{l:'默认中心纬度',v:'35.23'},{l:'默认中心经度',v:'110.28'},{l:'默认缩放级别',v:'13',hint:'1-18'}] },
  { title:'系统运行参数', fields:[{l:'设备心跳间隔(秒)',v:'60'},{l:'数据保留周期(天)',v:'90',hint:'自动归档'},{l:'登录超时(分钟)',v:'30'},{l:'并发会话数上限',v:'50'}] },
]
</script>
<template>
  <AdminLayout>
    <div class="max-w-3xl space-y-4">
      <div v-for="g in groups" :key="g.title" class="bg-white border border-[#e8e8e8] rounded-md overflow-hidden">
        <div class="text-sm font-semibold text-[#1890ff] p-3 border-b border-[#e8e8e8] bg-[#fafafa]">{{ g.title }}</div>
        <div class="grid grid-cols-3 gap-3 p-4">
          <div v-for="f in g.fields" :key="f.l" class="flex flex-col gap-1">
            <label class="text-xs text-[#666]">{{ f.l }}</label>
            <input class="h-9 border border-[#d9d9d9] rounded px-3 text-sm outline-none focus:border-[#1890ff]" :value="f.v" />
            <span v-if="f.hint" class="text-[10px] text-[#999]">{{ f.hint }}</span>
          </div>
        </div>
      </div>
      <button class="h-9 px-6 bg-[#1890ff] text-white text-sm rounded border-none cursor-pointer hover:bg-[#40a9ff]">💾 保存配置</button>
    </div>
  </AdminLayout>
</template>
