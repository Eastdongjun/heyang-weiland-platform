<script setup lang="ts">
import AdminLayout from '@/components/common/AdminLayout.vue'
const users = [
  { name:'张三', code:'U-1001', role:'小队长', phone:'138****5678', dept:'巡逻保护部', lastLogin:'2026-08-01 08:25', status:true },
  { name:'李四', code:'U-1002', role:'巡逻员', phone:'139****9012', dept:'巡逻保护部', lastLogin:'2026-08-01 07:50', status:true },
  { name:'王五', code:'U-1003', role:'调度员', phone:'137****3456', dept:'调度中心', lastLogin:'2026-07-31 22:10', status:true },
  { name:'赵六', code:'U-1004', role:'管理员', phone:'136****7890', dept:'指挥中心', lastLogin:'2026-08-01 10:30', status:true },
  { name:'钱七', code:'U-1005', role:'巡逻员', phone:'135****2345', dept:'巡逻保护部', lastLogin:'2026-07-30 17:00', status:false },
  { name:'孙八', code:'U-1006', role:'广播员', phone:'134****6789', dept:'宣传科', lastLogin:'2026-07-29 15:20', status:true },
]
</script>
<template>
  <AdminLayout>
    <div class="filter-bar">
      <select class="filter-select"><option>全部角色</option><option>管理员</option><option>调度员</option><option>巡逻员</option><option>小队长</option></select>
      <select class="filter-select"><option>全部状态</option><option>正常</option><option>禁用</option></select>
      <input class="filter-input" placeholder="搜索用户..." />
      <button class="btn-primary">+ 新增用户</button>
    </div>
    <div class="table-wrap">
      <table><thead><tr><th>姓名</th><th>工号</th><th>角色</th><th>手机</th><th>部门/区域</th><th>最后登录</th><th>状态</th><th>操作</th></tr></thead>
        <tbody><tr v-for="u in users" :key="u.code">
          <td><a href="#" class="name-link">{{ u.name }}</a></td><td class="code">{{ u.code }}</td>
          <td><span class="tag" :class="u.role==='管理员'?'tag-red':u.role==='调度员'?'tag-blue':u.role==='小队长'?'tag-green':'tag-default'">{{ u.role }}</span></td>
          <td>{{ u.phone }}</td><td>{{ u.dept }}</td><td class="time">{{ u.lastLogin }}</td>
          <td><span class="toggle-switch" :class="{on:u.status}" @click="u.status=!u.status"><span class="toggle-knob" /></span></td>
          <td class="actions"><a href="#">编辑</a><a href="#">重置密码</a><a href="#" class="del">{{ u.status?'禁用':'启用' }}</a></td>
        </tr></tbody>
      </table>
      <div class="pager">共 6 条</div>
    </div>
  </AdminLayout>
</template>
<style scoped>
.filter-bar { display:flex; gap:8px; margin-bottom:12px; }
.filter-select, .filter-input { height:32px; border:1px solid #d9d9d9; border-radius:4px; padding:0 8px; font-size:12px; outline:none; }
.filter-select:focus, .filter-input:focus { border-color:#1890ff; }
.filter-input { width:160px; }
.btn-primary { height:32px; padding:0 16px; background:#1890ff; color:#fff; border:none; border-radius:4px; font-size:12px; cursor:pointer; margin-left:auto; }
.table-wrap { background:#fff; border:1px solid #e8e8e8; border-radius:6px; overflow:hidden; }
table { width:100%; border-collapse:collapse; font-size:13px; }
thead th { background:#e6f0ff; color:#1890ff; padding:10px 12px; text-align:left; font-weight:500; font-size:12px; }
tbody td { padding:10px 12px; border-bottom:1px solid #f0f0f0; }
.code { font-family:"SF Mono",monospace; font-size:12px; color:#1890ff; }
.time { font-size:12px; color:#999; }
.name-link { color:#1890ff; text-decoration:none; font-weight:500; }
.tag { padding:2px 8px; border-radius:3px; font-size:11px; }
.tag-red { background:#fff1f0; color:#ff4d4f; }
.tag-blue { background:#e6f7ff; color:#1890ff; }
.tag-green { background:#f6ffed; color:#52c41a; }
.tag-default { background:#f5f5f5; color:#999; }
.toggle-switch { display:inline-block; width:44px; height:22px; background:#ccc; border-radius:11px; position:relative; cursor:pointer; transition:background .2s; }
.toggle-switch.on { background:#52c41a; }
.toggle-knob { position:absolute; top:2px; left:2px; width:18px; height:18px; background:#fff; border-radius:50%; transition:left .2s; }
.toggle-switch.on .toggle-knob { left:24px; }
.actions a { font-size:12px; color:#1890ff; margin-right:8px; text-decoration:none; }
.actions a.del { color:#ff4d4f; }
.pager { padding:12px; text-align:right; font-size:12px; color:#999; }
</style>
