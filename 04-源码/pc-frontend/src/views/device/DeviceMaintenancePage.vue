<script setup lang="ts">
import AdminLayout from '@/components/common/AdminLayout.vue'
const devices = [
  { name:'湿地入口摄像头', code:'CAM-0001', network:'有线', plan:'—', used:'—', pct:0, status:'—', sColor:'#999', nextDate:'2026-07-15' },
  { name:'热成像云台', code:'CAM-0192', network:'有线', plan:'—', used:'—', pct:0, status:'—', sColor:'#999', nextDate:'2026-06-20' },
  { name:'南岸4G球机', code:'CAM-0203', network:'4G', plan:'50GB', used:'38.2GB', pct:76, status:'预警', sColor:'#faad14', nextDate:'2026-06-10' },
  { name:'气象监测站-1号', code:'SEN-1001', network:'4G', plan:'30GB', used:'12.5GB', pct:42, status:'正常', sColor:'#52c41a', nextDate:'2026-08-01' },
  { name:'水质采样点-B3', code:'SEN-1008', network:'4G', plan:'30GB', used:'31.8GB', pct:106, status:'超限', sColor:'#ff4d4f', nextDate:'2026-07-20' },
  { name:'声纹识别站', code:'AUD-2001', network:'4G', plan:'50GB', used:'45.6GB', pct:91, status:'预警', sColor:'#faad14', nextDate:'2026-06-15' },
]
</script>
<template>
  <AdminLayout>
    <div class="stats-row"><div v-for="s in [{l:'4G设备数',v:28,c:'#1890ff'},{l:'本月总流量',v:'1,240 GB',c:'#1890ff'},{l:'正常',v:22,c:'#52c41a'},{l:'预警',v:4,c:'#faad14'},{l:'超限',v:2,c:'#ff4d4f'}]" :key="s.l" class="stat-card"><div class="stat-num" :style="{color:s.c}">{{ s.v }}</div><div class="stat-label">{{ s.l }}</div></div></div>
    <div class="table-wrap">
      <table><thead><tr><th>设备名称</th><th>编码</th><th>网络</th><th>套餐上限</th><th>本月用量</th><th>使用率</th><th>状态</th><th>下次维保</th><th>操作</th></tr></thead>
        <tbody><tr v-for="d in devices" :key="d.code">
          <td>{{ d.name }}</td><td class="code">{{ d.code }}</td><td>{{ d.network }}</td><td>{{ d.plan }}</td><td>{{ d.used }}</td>
          <td>
            <template v-if="d.network==='4G'">
              <div class="progress-wrap"><div class="progress-bar" :style="{width:d.pct+'%',background:d.pct>90?'#ff4d4f':d.pct>70?'#faad14':'#52c41a'}" /></div>
              <span class="text-[11px] ml-1">{{ d.pct }}%</span>
            </template>
            <span v-else>—</span>
          </td>
          <td><span :style="{color:d.sColor}" class="font-semibold text-xs">● {{ d.status }}</span></td>
          <td class="time">{{ d.nextDate }}</td>
          <td class="actions"><a href="#">详情</a><a v-if="d.status==='预警'" href="#">续费</a><a v-if="d.status==='超限'" href="#" class="del">超限处理</a></td>
        </tr></tbody>
      </table>
    </div>
  </AdminLayout>
</template>
<style scoped>
.stats-row { display:flex; gap:12px; margin-bottom:16px; }
.stat-card { flex:1; background:#fff; border:1px solid #e8e8e8; border-radius:6px; padding:16px; text-align:center; }
.stat-num { font-size:24px; font-weight:700; }
.stat-label { font-size:12px; color:#999; margin-top:4px; }
.table-wrap { background:#fff; border:1px solid #e8e8e8; border-radius:6px; overflow:hidden; }
table { width:100%; border-collapse:collapse; font-size:13px; }
thead th { background:#e6f0ff; color:#1890ff; padding:10px 12px; text-align:left; font-weight:500; font-size:12px; }
tbody td { padding:10px 12px; border-bottom:1px solid #f0f0f0; }
.code { font-family:"SF Mono",monospace; font-size:12px; color:#1890ff; }
.time { font-size:12px; color:#999; }
.progress-wrap { display:inline-block; width:80px; height:8px; background:#f0f0f0; border-radius:4px; overflow:hidden; vertical-align:middle; }
.progress-bar { height:100%; border-radius:4px; }
.actions a { font-size:12px; color:#1890ff; margin-right:8px; text-decoration:none; }
.actions a.del { color:#ff4d4f; }
</style>
