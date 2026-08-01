<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import AdminLayout from '@/components/common/AdminLayout.vue'

const router = useRouter()
const activeTab = ref('all')
const tabs = [{k:'all',l:'全部'},{k:'pending',l:'待处理'},{k:'processing',l:'处理中'},{k:'done',l:'已完成'}]

const orders = [
  { id:'WO-20260801-001', type:'非法闯入', priority:'一级', pColor:'#ff4d4f', status:'待处理', sColor:'#faad14', location:'北门保护区入口', creator:'系统自动', handler:'张三', time:'10:42' },
  { id:'WO-20260801-002', type:'水质异常', priority:'二级', pColor:'#faad14', status:'处理中', sColor:'#1890ff', location:'C区采样点3号', creator:'王五', handler:'李四', time:'09:15' },
  { id:'WO-20260801-003', type:'日常巡检', priority:'三级', pColor:'#1890ff', status:'已完成', sColor:'#52c41a', location:'东岸芦苇荡', creator:'赵六', handler:'赵六', time:'08:30' },
]

const showModal = ref(false)
</script>
<template>
  <AdminLayout>
    <div class="stats-row">
      <div v-for="s in [{l:'本月工单',v:86,c:'#1890ff'},{l:'待处理',v:12,c:'#faad14'},{l:'处理中',v:5,c:'#1890ff'},{l:'已完成',v:69,c:'#52c41a'}]" :key="s.l" class="stat-card">
        <div class="stat-num" :style="{color:s.c}">{{ s.v }}</div><div class="stat-label">{{ s.l }}</div>
      </div>
    </div>
    <div class="filter-bar">
      <div class="tabs">
        <button v-for="t in tabs" :key="t.k" class="tab-btn" :class="{active:activeTab===t.k}" @click="activeTab=t.k">{{ t.l }}</button>
      </div>
      <button class="btn-primary" @click="showModal=true">+ 派发工单</button>
    </div>
    <div class="table-wrap">
      <table><thead><tr><th>工单号</th><th>类型</th><th>优先级</th><th>位置</th><th>派发人</th><th>处理人</th><th>时间</th><th>状态</th><th>操作</th></tr></thead>
        <tbody><tr v-for="o in orders" :key="o.id" class="clickable" @click="router.push('/workorder/'+o.id)">
            <td class="code">{{ o.id }}</td><td>{{ o.type }}</td>
            <td><span class="tag" :style="{background:o.pColor+'20',color:o.pColor}">{{ o.priority }}</span></td>
            <td>{{ o.location }}</td><td>{{ o.creator }}</td><td>{{ o.handler }}</td><td class="time">{{ o.time }}</td>
            <td><span :style="{color:o.sColor}">● {{ o.status }}</span></td>
            <td class="actions"><a href="#">跟进</a><a href="#">转派</a><a href="#" class="del">关闭</a></td>
        </tr></tbody>
      </table>
      <div class="pager">共 86 条 &nbsp; 1 2 3 4</div>
    </div>
  </AdminLayout>
</template>

<style scoped>
.stats-row { display:flex; gap:12px; margin-bottom:16px; }
.stat-card { flex:1; background:#fff; border:1px solid #e8e8e8; border-radius:6px; padding:16px; text-align:center; }
.stat-num { font-size:24px; font-weight:700; }
.stat-label { font-size:12px; color:#999; margin-top:4px; }
.filter-bar { display:flex; gap:8px; margin-bottom:12px; align-items:center; justify-content:space-between; }
.tabs { display:flex; gap:4px; }
.tab-btn { padding:5px 12px; border:1px solid #d9d9d9; border-radius:4px; background:#fff; color:#666; font-size:12px; cursor:pointer; }
.tab-btn.active { border-color:#1890ff; color:#1890ff; background:#e6f7ff; }
.btn-primary { height:32px; padding:0 16px; background:#1890ff; color:#fff; border:none; border-radius:4px; font-size:12px; cursor:pointer; }
.table-wrap { background:#fff; border:1px solid #e8e8e8; border-radius:6px; overflow:hidden; }
table { width:100%; border-collapse:collapse; font-size:13px; }
thead th { background:#e6f0ff; color:#1890ff; padding:10px 12px; text-align:left; font-weight:500; font-size:12px; }
tbody td { padding:10px 12px; border-bottom:1px solid #f0f0f0; }
tr.clickable { cursor:pointer; } tr.clickable:hover { background:#fafafa; }
.code { font-family:"SF Mono",monospace; font-size:12px; color:#1890ff; }
.time { font-size:12px; color:#999; }
.actions a { font-size:12px; color:#1890ff; margin-right:8px; text-decoration:none; }
.actions a.del { color:#ff4d4f; }
.tag { padding:2px 8px; border-radius:3px; font-size:11px; font-weight:500; }
.pager { padding:12px; text-align:right; font-size:12px; color:#999; }
</style>
