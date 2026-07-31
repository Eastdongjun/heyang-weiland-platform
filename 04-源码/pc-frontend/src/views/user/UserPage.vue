<script setup lang="ts">
const users = [
  { id:1, username:'admin', name:'管理员', role:'ADMIN', phone:'138****0001', dept:'指挥中心', status:'正常', lastLogin:'2026-08-01 10:30' },
  { id:2, username:'zhangsan', name:'张三', role:'RANGER', phone:'138****5678', dept:'巡逻一队', status:'正常', lastLogin:'2026-08-01 09:15' },
  { id:3, username:'lisi', name:'李四', role:'RANGER', phone:'139****9012', dept:'巡逻二队', status:'正常', lastLogin:'2026-07-31 17:20' },
  { id:4, username:'wangwu', name:'王五', role:'MAINTAINER', phone:'137****3456', dept:'维保组', status:'禁用', lastLogin:'2026-07-25 08:00' },
]
</script>
<template>
  <div class="h-screen w-screen bg-[#0A1628] flex overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <aside class="w-[200px] shrink-0 bg-[#0F1D35] border-r border-[rgba(0,191,255,0.15)] p-4">
      <div class="text-xs text-[#8899AA] font-semibold mb-3 tracking-[1px]">用户管理</div>
      <nav class="space-y-0.5 text-sm">
        <div class="text-[#00BFFF] bg-[rgba(0,191,255,0.08)] rounded px-2 py-1.5 cursor-pointer">👥 用户列表</div>
        <div class="text-[#8899AA] hover:text-[#E8EDF5] rounded px-2 py-1.5 cursor-pointer">🔑 角色权限</div>
      </nav>
    </aside>
    <div class="flex-1 flex flex-col min-w-0">
      <header class="h-14 bg-[#0F1D35] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-6 shrink-0">
        <h1 class="text-base font-semibold">用户管理</h1>
        <button class="h-8 px-3 bg-[#00BFFF] text-[#0A1628] text-xs font-semibold rounded border-none cursor-pointer">+ 新增用户</button>
      </header>
      <div class="flex-1 overflow-auto p-4">
        <table class="w-full text-sm">
          <thead><tr class="text-[#8899AA] text-left border-b border-[rgba(0,191,255,0.1)] text-xs"><th class="p-2 font-medium">用户名</th><th class="p-2 font-medium">姓名</th><th class="p-2 font-medium">角色</th><th class="p-2 font-medium">手机号</th><th class="p-2 font-medium">部门</th><th class="p-2 font-medium">状态</th><th class="p-2 font-medium">最后登录</th><th class="p-2 font-medium">操作</th></tr></thead>
          <tbody>
            <tr v-for="u in users" :key="u.id" class="border-b border-[rgba(0,191,255,0.05)] hover:bg-[rgba(0,191,255,0.04)]">
              <td class="p-2 font-mono text-xs">{{ u.username }}</td><td class="p-2">{{ u.name }}</td>
              <td class="p-2"><span class="px-1.5 py-0.5 rounded text-[10px] font-semibold" :class="u.role==='ADMIN'?'bg-[rgba(0,191,255,0.15)] text-[#00BFFF]':'bg-[rgba(0,191,255,0.08)] text-[#8899AA]'">{{ u.role }}</span></td>
              <td class="p-2 text-[#8899AA] text-xs">{{ u.phone }}</td><td class="p-2 text-xs">{{ u.dept }}</td>
              <td class="p-2"><span class="text-xs font-semibold" :class="u.status==='正常'?'text-[#00CC99]':'text-[#FF3366]'">● {{ u.status }}</span></td>
              <td class="p-2 text-[#8899AA] text-xs">{{ u.lastLogin }}</td>
              <td class="p-2"><button class="text-xs text-[#00BFFF] mr-2">编辑</button><button class="text-xs text-[#FF3366]">禁用</button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
