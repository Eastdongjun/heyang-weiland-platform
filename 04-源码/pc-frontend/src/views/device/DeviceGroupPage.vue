<script setup lang="ts">
import AdminLayout from '@/components/common/AdminLayout.vue'
const groups = [
  { name:'核心保护区', type:'区域分组', count:18, person:'张三', date:'2023-03-15', children:[
    {name:'核心区摄像头',type:'类型分组',count:10,person:'张三',date:'2023-03-15'},
    {name:'核心区传感器',type:'类型分组',count:5,person:'张三',date:'2023-03-15'},
    {name:'核心区无人机',type:'类型分组',count:3,person:'张三',date:'2023-03-15'},
  ]},
  { name:'南岸缓冲区', type:'区域分组', count:12, person:'李四', date:'2023-04-20', children:[
    {name:'南岸摄像头',type:'类型分组',count:7,person:'李四',date:'2023-04-20'},
    {name:'南岸传感器',type:'类型分组',count:5,person:'李四',date:'2023-04-20'},
  ]},
]
</script>
<template>
  <AdminLayout>
    <div class="stats-row"><div v-for="s in [{l:'总分组数',v:8,c:'#1890ff'},{l:'区域分组',v:5,c:'#52c41a'},{l:'类型分组',v:3,c:'#1890ff'},{l:'设备总数',v:50,c:'#faad14'}]" :key="s.l" class="stat-card"><div class="stat-num" :style="{color:s.c}">{{ s.v }}</div><div class="stat-label">{{ s.l }}</div></div></div>
    <div class="filter-bar"><select class="filter-select"><option>全部</option></select><input class="filter-input" placeholder="搜索分组名称..." /><button class="btn-primary">+ 新增分组</button></div>
    <div class="table-wrap">
      <table><thead><tr><th>分组名称</th><th>类型</th><th>设备数量</th><th>负责人</th><th>创建时间</th><th>操作</th></tr></thead>
        <tbody><template v-for="g in groups" :key="g.name">
          <tr><td class="font-semibold">{{ g.name }}</td><td><span class="tag tag-green">{{ g.type }}</span></td><td class="code">{{ g.count }}</td><td>{{ g.person }}</td><td class="time">{{ g.date }}</td><td class="actions"><a href="#">查看设备</a><a href="#">编辑</a><a href="#" class="del">删除</a></td></tr>
          <tr v-for="c in g.children" :key="c.name"><td class="pl-8 text-sm">└ {{ c.name }}</td><td><span class="tag tag-blue">{{ c.type }}</span></td><td class="code">{{ c.count }}</td><td>{{ c.person }}</td><td class="time">{{ c.date }}</td><td class="actions"><a href="#">查看设备</a><a href="#">编辑</a><a href="#" class="del">删除</a></td></tr>
        </template></tbody>
      </table>
      <div class="pager">共 11 条</div>
    </div>
  </AdminLayout>
</template>
<style scoped>
.stats-row { display:flex; gap:12px; margin-bottom:16px; }
.stat-card { flex:1; background:#fff; border:1px solid #e8e8e8; border-radius:6px; padding:16px; text-align:center; }
.stat-num { font-size:24px; font-weight:700; }
.stat-label { font-size:12px; color:#999; margin-top:4px; }
.filter-bar { display:flex; gap:8px; margin-bottom:12px; }
.filter-select, .filter-input { height:32px; border:1px solid #d9d9d9; border-radius:4px; padding:0 8px; font-size:12px; outline:none; }
.filter-input { width:180px; }
.btn-primary { height:32px; padding:0 16px; background:#1890ff; color:#fff; border:none; border-radius:4px; font-size:12px; cursor:pointer; margin-left:auto; }
.table-wrap { background:#fff; border:1px solid #e8e8e8; border-radius:6px; overflow:hidden; }
table { width:100%; border-collapse:collapse; font-size:13px; }
thead th { background:#e6f0ff; color:#1890ff; padding:10px 12px; text-align:left; font-weight:500; font-size:12px; }
tbody td { padding:10px 12px; border-bottom:1px solid #f0f0f0; }
.code { font-family:"SF Mono",monospace; font-size:12px; color:#1890ff; }
.time { font-size:12px; color:#999; }
.tag { padding:2px 8px; border-radius:3px; font-size:11px; }
.tag-green { background:#f6ffed; color:#52c41a; }
.tag-blue { background:#e6f7ff; color:#1890ff; }
.actions a { font-size:12px; color:#1890ff; margin-right:8px; text-decoration:none; }
.actions a.del { color:#ff4d4f; }
.pager { padding:12px; text-align:right; font-size:12px; color:#999; }
</style>
