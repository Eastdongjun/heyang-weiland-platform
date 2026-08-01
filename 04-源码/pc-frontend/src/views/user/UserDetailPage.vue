<script setup lang="ts">
import { useRoute } from 'vue-router'
const route = useRoute()
</script>
<template>
  <div class="h-screen bg-[#f0f2f5] flex flex-col" style="font-family:'PingFang SC','Microsoft YaHei',sans-serif">
    <header class="h-[50px] bg-white border-b border-[#e8e8e8] flex items-center justify-between px-5 shrink-0">
      <div class="flex items-center gap-3"><a href="/user" class="text-[#1890ff] text-sm no-underline">‹ 返回</a><span class="text-sm font-semibold">用户详情</span></div>
      <div class="flex items-center gap-4 text-xs text-[#999]"><span>用户ID {{ route.params.id || 'U-1003' }}</span><a href="/login" class="text-[#ff4d4f] no-underline">退出</a></div>
    </header>
    <div class="flex-1 flex min-h-0"><aside class="w-[200px] bg-[#001529] p-4 shrink-0"><div class="text-xs text-[#1890ff] mb-3">基本信息</div><div class="text-xs text-[rgba(255,255,255,0.45)] mb-2">权限角色</div><div class="text-xs text-[rgba(255,255,255,0.45)] mb-2">操作日志</div></aside>
    <main class="flex-1 p-4 overflow-auto"><div class="bg-white border border-[#e8e8e8] rounded-md p-6 max-w-xl"><div class="text-base font-semibold mb-4">基本信息</div><div class="space-y-3 text-sm"><div class="flex justify-between py-2 border-b border-[#f0f0f0]"><span class="text-[#999]">姓名</span><span>张三</span></div><div class="flex justify-between py-2 border-b border-[#f0f0f0]"><span class="text-[#999]">手机号</span><span>138****5678</span></div><div class="flex justify-between py-2 border-b border-[#f0f0f0]"><span class="text-[#999]">部门</span><span>巡逻保护部</span></div><div class="flex justify-between py-2 border-b border-[#f0f0f0]"><span class="text-[#999]">岗位</span><span>小队长</span></div><div class="flex justify-between py-2 border-b border-[#f0f0f0]"><span class="text-[#999]">状态</span><span class="text-[#52c41a] font-semibold">● 正常</span></div><div class="flex justify-between py-2"><span class="text-[#999]">最后登录</span><span>2026-05-12 08:25:30</span></div></div><div class="flex gap-3 mt-4"><button class="h-9 px-5 bg-[#1890ff] text-white text-xs rounded border-none cursor-pointer">编辑</button><button class="h-9 px-5 bg-white text-[#ff4d4f] text-xs rounded border border-[#d9d9d9] cursor-pointer">禁用账户</button></div></div></main></div>
  </div>
</template>
