<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import AdminLayout from '@/components/common/AdminLayout.vue'

const router = useRouter()
const search = ref('')
const typeFilter = ref('')
const statusFilter = ref('')

const devices = [
  { id:1, code:'CAM-0001', name:'湿地入口摄像头', type:'摄像头', status:'在线', network:'有线', area:'核心区', heartbeat:'2026-08-01 12:30' },
  { id:2, code:'CAM-0192', name:'热成像云台', type:'热成像云台', status:'在线', network:'有线', area:'核心区', heartbeat:'2026-08-01 12:30' },
  { id:3, code:'CAM-0203', name:'南岸4G球机', type:'球机', status:'离线', network:'4G', area:'缓冲区', heartbeat:'2026-08-01 10:15' },
  { id:4, code:'SEN-1001', name:'气象监测站-1号', type:'传感器', status:'在线', network:'4G', area:'东区', heartbeat:'2026-08-01 12:28' },
  { id:5, code:'UAV-001', name:'无人机机场-A', type:'无人机机场', status:'在线', network:'有线', area:'机库', heartbeat:'2026-08-01 12:29' },
  { id:6, code:'SEN-1008', name:'水质采样点-B3', type:'传感器', status:'在线', network:'4G', area:'南区', heartbeat:'2026-08-01 12:27' },
  { id:7, code:'CAM-0105', name:'北门枪机', type:'摄像头', status:'在线', network:'有线', area:'北区', heartbeat:'2026-08-01 12:30' },
  { id:8, code:'AUD-2001', name:'声纹识别站', type:'声纹', status:'在线', network:'4G', area:'东区', heartbeat:'2026-08-01 12:25' },
]

const showAddModal = ref(false)
</script>

<template>
  <AdminLayout>
    <div class="stats-row">
      <div class="stat-card" v-for="s in [{l:'设备总数',v:50,c:'#1890ff'},{l:'在线',v:39,c:'#52c41a'},{l:'离线',v:11,c:'#ff4d4f'},{l:'4G设备',v:28,c:'#faad14'},{l:'待维保',v:6,c:'#ff7a45'}]" :key="s.l">
        <div class="stat-num" :style="{color:s.c}">{{ s.v }}</div>
        <div class="stat-label">{{ s.l }}</div>
      </div>
    </div>

    <div class="filter-bar">
      <select v-model="typeFilter" class="filter-select"><option value="">全部类型</option><option>摄像头</option><option>热成像云台</option><option>球机</option><option>传感器</option><option>无人机机场</option></select>
      <select v-model="statusFilter" class="filter-select"><option value="">全部状态</option><option>在线</option><option>离线</option></select>
      <input v-model="search" class="filter-input" placeholder="搜索设备名称/编码..." />
      <button class="btn-primary" @click="showAddModal=true">+ 添加设备</button>
      <button class="btn-default">📥 导出</button>
    </div>

    <div class="table-wrap">
      <table>
        <thead><tr><th>设备名称</th><th>编码</th><th>类型</th><th>状态</th><th>网络</th><th>区域</th><th>最后心跳</th><th>操作</th></tr></thead>
        <tbody>
          <tr v-for="d in devices" :key="d.id" @click="router.push('/device/'+d.id)" class="clickable">
            <td>{{ d.name }}</td><td class="code">{{ d.code }}</td>
            <td><span class="tag" :class="d.type==='摄像头'?'tag-blue':d.type==='热成像云台'?'tag-cyan':d.type==='球机'?'tag-purple':d.type==='无人机机场'?'tag-green':'tag-orange'">{{ d.type }}</span></td>
            <td><span class="status-dot" :class="d.status==='在线'?'online':'offline'" /> {{ d.status }}</td>
            <td>{{ d.network }}</td><td>{{ d.area }}</td>
            <td class="time">{{ d.heartbeat }}</td>
            <td class="actions"><a @click.stop href="#">详情</a><a @click.stop href="#">配置</a><a @click.stop href="#" class="del">删除</a></td>
          </tr>
        </tbody>
      </table>
      <div class="pager">共 50 条 &nbsp; 1 2 3 4 5</div>
    </div>
  </AdminLayout>
</template>

<style scoped>
.stats-row { display:flex; gap:12px; margin-bottom:16px; }
.stat-card { flex:1; background:#fff; border:1px solid #e8e8e8; border-radius:6px; padding:16px; text-align:center; }
.stat-num { font-size:24px; font-weight:700; font-family:"SF Mono",monospace; }
.stat-label { font-size:12px; color:#999; margin-top:4px; }

.filter-bar { display:flex; gap:8px; margin-bottom:12px; align-items:center; flex-wrap:wrap; }
.filter-select, .filter-input { height:32px; border:1px solid #d9d9d9; border-radius:4px; padding:0 8px; font-size:12px; outline:none; background:#fff; }
.filter-select:focus, .filter-input:focus { border-color:#1890ff; }
.filter-input { width:180px; }

.btn-primary { height:32px; padding:0 16px; background:#1890ff; color:#fff; border:none; border-radius:4px; font-size:12px; cursor:pointer; }
.btn-primary:hover { background:#40a9ff; }
.btn-default { height:32px; padding:0 12px; background:#fff; color:#666; border:1px solid #d9d9d9; border-radius:4px; font-size:12px; cursor:pointer; }

.table-wrap { background:#fff; border:1px solid #e8e8e8; border-radius:6px; overflow:hidden; }
table { width:100%; border-collapse:collapse; font-size:13px; }
thead th { background:#e6f0ff; color:#1890ff; padding:10px 12px; text-align:left; font-weight:500; font-size:12px; }
tbody td { padding:10px 12px; border-bottom:1px solid #f0f0f0; }
tr.clickable { cursor:pointer; }
tr.clickable:hover { background:#fafafa; }
.code { font-family:"SF Mono",monospace; font-size:12px; color:#1890ff; }
.time { font-size:12px; color:#999; }
.actions a { font-size:12px; color:#1890ff; margin-right:8px; text-decoration:none; }
.actions a.del { color:#ff4d4f; }
.actions a:hover { opacity:0.7; }

.tag { padding:2px 8px; border-radius:3px; font-size:11px; }
.tag-blue { background:#e6f7ff; color:#1890ff; }
.tag-cyan { background:#e6fffb; color:#13c2c2; }
.tag-purple { background:#f9f0ff; color:#722ed1; }
.tag-green { background:#f6ffed; color:#52c41a; }
.tag-orange { background:#fff7e6; color:#fa8c16; }

.status-dot { display:inline-block; width:6px;height:6px;border-radius:50%; margin-right:4px; }
.status-dot.online { background:#52c41a; }
.status-dot.offline { background:#d9d9d9; }

.pager { padding:12px; text-align:right; font-size:12px; color:#999; }
</style>
