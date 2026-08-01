<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import L from 'leaflet'
import 'leaflet/dist/leaflet.css'

const router = useRouter()
const now = ref(new Date())
let timer: number
let map: any = null
const activeTab = ref('all')

onMounted(() => {
  timer = window.setInterval(() => now.value = new Date(), 1000)

  // Leaflet map (exact copy from prototype)
  map = L.map('alertMap', { zoomControl: false }).setView([35.23, 110.15], 13)
  L.tileLayer('https://webst0{s}.is.autonavi.com/appmaptile?style=6&x={x}&y={y}&z={z}', {
    subdomains: '1234',
    attribution: ''
  }).addTo(map)
  L.control.zoom({ position: 'topright' }).addTo(map)

  const alertPoints = [
    { lat: 35.235, lng: 110.145, level: 'danger', text: '非法闯入' },
    { lat: 35.225, lng: 110.155, level: 'warning', text: '水质异常' },
    { lat: 35.232, lng: 110.148, level: 'danger', text: '烟火检测' },
    { lat: 35.228, lng: 110.142, level: 'info', text: '设备离线' },
    { lat: 35.222, lng: 110.158, level: 'warning', text: '溶解氧偏低' },
    { lat: 35.238, lng: 110.152, level: 'info', text: '风速超限' },
    { lat: 35.22, lng: 110.16, level: 'danger', text: '非法捕捞' },
  ]

  const levelColors: any = { danger: '#FF3366', warning: '#FFCC00', info: '#40a9ff' }

  alertPoints.forEach((pt) => {
    const color = levelColors[pt.level]
    const iconHtml = `<div style="position:relative;width:20px;height:20px;"><div style="position:absolute;top:50%;left:50%;transform:translate(-50%,-50%);width:20px;height:20px;border:2px solid ${color};border-radius:50%;animation:pulse 1.8s infinite;"></div><div style="position:absolute;top:4px;left:4px;width:12px;height:12px;background:${color};border-radius:50%;box-shadow:0 0 8px ${color};"></div></div><style>@keyframes pulse{0%{width:20px;height:20px;opacity:1;}100%{width:50px;height:50px;opacity:0;}}</style>`
    const icon = L.divIcon({ html: iconHtml, className: '', iconSize: [20, 20], iconAnchor: [10, 10] })
    L.marker([pt.lat, pt.lng], { icon } as any).addTo(map).bindPopup(`<b>${pt.text}</b>`)
  })
})

onUnmounted(() => { clearInterval(timer); if (map) map.remove() })

const alerts = [
  { id:1,type:'非法闯入',time:'10:42:18',location:'北门湿地保护区入口',status:'pending',sLabel:'待处理',source:'AI视频识别',level:'danger' },
  { id:2,type:'水质异常',time:'10:38:05',location:'C区采样点3号',status:'processing',sLabel:'处理中',source:'传感器#S-07',level:'warning' },
  { id:3,type:'烟火检测',time:'10:25:33',location:'东岸芦苇荡区域',status:'processing',sLabel:'处理中',source:'无人机热成像',level:'danger' },
  { id:4,type:'设备离线',time:'10:12:00',location:'西门球机#CAM-12',status:'resolved',sLabel:'已处理',source:'网络恢复',level:'info' },
  { id:5,type:'溶解氧偏低',time:'09:58:22',location:'B区深水区监测站',status:'pending',sLabel:'待处理',source:'水质传感器',level:'warning' },
  { id:6,type:'风速超限',time:'09:45:10',location:'气象监测站',status:'resolved',sLabel:'已处理',source:'自动恢复',level:'info' },
  { id:7,type:'非法捕捞',time:'09:30:05',location:'核心保护区水域',status:'resolved',sLabel:'已处理',source:'巡逻队 dispatched',level:'danger' },
]

const tabs = [{k:'all',l:'全部'},{k:'pending',l:'待处理'},{k:'processing',l:'处理中'},{k:'resolved',l:'已处理'}]

const navItems = [
  {name:'GIS地图',path:'/gis'},{name:'视频监控',path:'/monitor'},{name:'无人机',path:'/uav'},
  {name:'环境监测',path:'/env'},{name:'告警中心',path:'/alert',active:true},{name:'数据统计',path:'/statistics'},
  {name:'应急管理',path:'/emergency'},{name:'VR查看',path:'/vr'},
]

const barData = [{h:'32%',l:'05-06'},{h:'48%',l:'05-07'},{h:'28%',l:'05-08'},{h:'56%',l:'05-09'},{h:'40%',l:'05-10'},{h:'72%',l:'05-11'},{h:'60%',l:'今日'}]
</script>

<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden font-['PingFang_SC','Microsoft_YaHei','Helvetica_Neue',sans-serif]" style="color:#fff">

    <!-- Top Bar -->
    <header class="top-bar">
      <div class="top-left" style="width:240px">
        <span id="headerDate" style="font-size:14px;font-weight:600;color:#00BFFF;font-family:'DIN Pro','SF Mono',monospace">{{ now.getFullYear() }}年{{ now.getMonth()+1 }}月{{ now.getDate() }}日</span>
      </div>
      <div class="top-center">
        <div class="top-title">洽川湿地告警中心</div>
        <div class="top-title-sub">实时告警 · 智能处置</div>
      </div>
      <div class="top-right">
        <span class="top-weather">☀️ 26°C · 晴</span>
        <span id="clockSec" style="font-size:22px;font-weight:700;color:#00BFFF;font-family:'DIN Pro','SF Mono',monospace">{{ now.toLocaleTimeString('zh-CN',{hour12:false}) }}</span>
        <a href="/nav" style="color:#8899AA;font-size:12px;text-decoration:none;padding:4px 10px;border:1px solid rgba(0,191,255,0.15);border-radius:4px">退出</a>
      </div>
    </header>

    <div class="main">
      <div class="main-row">
        <!-- LEFT: Alert List -->
        <div class="col-left">
          <div class="panel" style="flex:1;min-height:0">
            <div class="corner corner-tl" /><div class="corner corner-tr" /><div class="corner corner-bl" /><div class="corner corner-br" />
            <div class="panel-title">实时告警</div>
            <div class="filter-tabs">
              <div v-for="t in tabs" :key="t.k" class="filter-tab" :class="{active:activeTab===t.k}" @click="activeTab=t.k">{{ t.l }}</div>
            </div>
            <div class="panel-body">
              <div v-for="a in alerts" :key="a.id" class="alert-item" @click="router.push('/alert/'+a.id)">
                <div class="alert-level-bar" :class="{'level-danger':a.level==='danger','level-warning':a.level==='warning','level-info':a.level==='info'}" />
                <div class="alert-content">
                  <div class="alert-header"><span class="alert-type">{{ a.type }}</span><span class="alert-time">{{ a.time }}</span></div>
                  <div class="alert-location">{{ a.location }}</div>
                  <div class="alert-footer">
                    <span class="alert-badge" :class="{'badge-pending':a.status==='pending','badge-processing':a.status==='processing','badge-resolved':a.status==='resolved'}">{{ a.sLabel }}</span>
                    <span style="font-size:11px;color:#8899AA">{{ a.source }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- CENTER: Map -->
        <div class="col-center">
          <div id="alertMap" style="width:100%;height:100%" />
        </div>

        <!-- RIGHT: Statistics -->
        <div class="col-right">
          <div class="panel" style="flex-shrink:0">
            <div class="corner corner-tl" /><div class="corner corner-tr" /><div class="corner corner-bl" /><div class="corner corner-br" />
            <div class="panel-title">今日概览</div>
            <div class="kpi-row">
              <div class="kpi-card"><div class="kpi-value" style="color:#fff">27</div><div class="kpi-label">告警总数</div></div>
              <div class="kpi-card"><div class="kpi-value" style="color:#FF3366">5</div><div class="kpi-label">待处理</div></div>
              <div class="kpi-card"><div class="kpi-value" style="color:#FFCC00">3</div><div class="kpi-label">处理中</div></div>
              <div class="kpi-card"><div class="kpi-value" style="color:#00BFFF">19</div><div class="kpi-label">已处理</div></div>
            </div>
          </div>

          <div class="panel" style="flex-shrink:0">
            <div class="corner corner-tl" /><div class="corner corner-tr" /><div class="corner corner-bl" /><div class="corner corner-br" />
            <div class="panel-title">告警类型分布</div>
            <div class="panel-body">
              <div class="pie-wrap">
                <div class="pie-circle" />
                <div class="pie-legend">
                  <div class="legend-item"><span class="legend-dot" style="background:#FF3366" />安防事件 35%</div>
                  <div class="legend-item"><span class="legend-dot" style="background:#FFCC00" />环境异常 25%</div>
                  <div class="legend-item"><span class="legend-dot" style="background:#40a9ff" />设备故障 20%</div>
                  <div class="legend-item"><span class="legend-dot" style="background:#00BFFF" />消防预警 20%</div>
                </div>
              </div>
            </div>
          </div>

          <div class="panel" style="flex:1;min-height:0">
            <div class="corner corner-tl" /><div class="corner corner-tr" /><div class="corner corner-bl" /><div class="corner corner-br" />
            <div class="panel-title">7天告警趋势</div>
            <div class="panel-body">
              <div class="bar-chart">
                <div v-for="b in barData" :key="b.l" class="bar-col"><div class="bar-fill" :style="{height:b.h}" /><span class="bar-label">{{ b.l }}</span></div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Bottom Nav -->
      <nav class="bottom-nav">
        <button v-for="nav in navItems" :key="nav.name" class="nav-btn" :class="{active:nav.active}" @click="router.push(nav.path)">
          <span class="nav-icon"><svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="10" r="3"/><path d="M12 2C7 2 3 6.5 3 12c0 2 .5 3.5 1.5 5L12 22l7.5-5c1-1.5 1.5-3 1.5-5 0-5.5-4-10-9-10z"/></svg></span>
          <span>{{ nav.name }}</span>
        </button>
      </nav>
    </div>
  </div>
</template>

<style scoped>
:root {
  --bg-deep:#0A1628;--bg-card:rgba(15,30,55,0.85);--bg-card2:rgba(10,28,60,0.6);--header-bg:rgba(10,25,50,0.95);
  --accent:#00BFFF;--accent-dim:rgba(0,191,255,0.12);--accent-glow:rgba(0,191,255,0.25);
  --accent-bright:#40a9ff;--cyan:#00E5CC;--danger:#FF3366;--danger-glow:rgba(255,51,102,0.3);
  --warn:#FFCC00;--green:#00CC99;--text:#FFFFFF;--text2:#CCCCCC;--text3:#8899AA;
  --border:rgba(0,191,255,0.15);--border-bright:rgba(0,191,255,0.3);
  --font-num:"DIN Pro","SF Mono",monospace;--text-dim:#8899AA;--warning:#FFCC00;--info:#40a9ff;--info-glow:rgba(64,169,255,0.4);
}
* { margin:0;padding:0;box-sizing:border-box; }

.top-bar { height:64px;background:linear-gradient(180deg,rgba(10,22,40,0.98),rgba(10,22,40,0.85));border-bottom:1px solid var(--border);display:flex;align-items:center;justify-content:space-between;padding:0 28px;flex-shrink:0;position:relative;z-index:100; }
.top-bar::after { content:'';position:absolute;bottom:-1px;left:10%;right:10%;height:1px;background:linear-gradient(90deg,transparent,var(--accent),transparent);opacity:.6; }
.top-left { display:flex;align-items:center;gap:16px; }
.top-title { font-size:20px;font-weight:700;letter-spacing:4px;background:linear-gradient(90deg,#fff,var(--accent));-webkit-background-clip:text;-webkit-text-fill-color:transparent;background-clip:text; }
.top-title-sub { font-size:10px;color:var(--text3);letter-spacing:1px;margin-top:2px; }
.top-center { display:flex;flex-direction:column;align-items:center;gap:4px; }
.top-right { display:flex;align-items:center;gap:20px;font-size:13px;color:var(--text2); }
.top-time { font-size:18px;font-weight:600;color:var(--accent);font-family:var(--font-num); }
.top-weather { display:flex;align-items:center;gap:4px;color:var(--text3); }

.main { flex:1;display:flex;flex-direction:column;min-height:0;position:relative; }
.main-row { flex:1;display:flex;min-height:0;gap:8px;padding:8px; }

.col-left { width:260px;display:flex;flex-direction:column;gap:8px;min-height:0;border:1px solid var(--border);border-radius:4px;padding:8px; }
.col-right { width:280px;display:flex;flex-direction:column;gap:8px;min-height:0;border:1px solid var(--border);border-radius:4px;padding:8px; }
.col-center { flex:1;display:flex;flex-direction:column;min-height:0;border:1px solid var(--border);border-radius:4px;overflow:hidden; }

.panel { background:var(--bg-card);border:1px solid var(--border);border-radius:6px;position:relative;overflow:hidden;display:flex;flex-direction:column; }
.corner { position:absolute;width:12px;height:12px;border-color:var(--accent);border-style:solid;pointer-events:none;opacity:.6; }
.corner-tl { top:0;left:0;border-width:2px 0 0 2px; }
.corner-tr { top:0;right:0;border-width:2px 2px 0 0; }
.corner-bl { bottom:0;left:0;border-width:0 0 2px 2px; }
.corner-br { bottom:0;right:0;border-width:0 2px 2px 0; }

.panel-title { font-size:12px;font-weight:600;color:var(--accent);padding:12px 14px 10px;display:flex;align-items:center;gap:8px;flex-shrink:0; }
.panel-title::before { content:'';width:3px;height:12px;background:var(--accent);border-radius:2px; }
.panel-body { padding:10px 14px;overflow-y:auto;flex:1; }
.panel-body::-webkit-scrollbar { width:4px; }
.panel-body::-webkit-scrollbar-thumb { background:var(--border);border-radius:2px; }

.filter-tabs { display:flex;gap:4px;padding:0 14px 10px;border-bottom:1px solid var(--border);flex-shrink:0; }
.filter-tab { padding:5px 12px;font-size:12px;color:var(--text-dim);cursor:pointer;border-radius:3px;border:1px solid transparent;transition:all .2s; }
.filter-tab:hover { background:rgba(0,191,255,0.08);color:var(--text); }
.filter-tab.active { background:rgba(0,191,255,0.12);color:var(--accent);border-color:rgba(0,191,255,0.25); }

.alert-item { display:flex;align-items:flex-start;gap:10px;padding:10px 0;border-bottom:1px solid rgba(0,191,255,0.06);cursor:pointer;transition:background .2s; }
.alert-item:hover { background:rgba(0,191,255,0.04);margin:0 -14px;padding-left:14px;padding-right:14px; }
.alert-item:last-child { border-bottom:none; }

.alert-level-bar { width:3px;height:36px;border-radius:2px;flex-shrink:0;margin-top:2px; }
.level-danger { background:var(--danger);box-shadow:0 0 6px var(--danger-glow); }
.level-warning { background:var(--warning); }
.level-info { background:var(--info);box-shadow:0 0 6px var(--info-glow); }

.alert-content { flex:1;min-width:0; }
.alert-header { display:flex;justify-content:space-between;align-items:center;margin-bottom:4px; }
.alert-type { font-size:13px;font-weight:600;color:var(--text); }
.alert-time { font-size:11px;color:var(--text-dim);font-family:monospace;flex-shrink:0; }
.alert-location { font-size:12px;color:var(--text-dim);margin-bottom:6px; }
.alert-footer { display:flex;justify-content:space-between;align-items:center; }

.alert-badge { font-size:11px;padding:2px 8px;border-radius:10px;border:1px solid; }
.badge-pending { background:rgba(255,51,102,0.1);color:var(--danger);border-color:rgba(255,51,102,0.2); }
.badge-processing { background:rgba(255,204,0,0.1);color:var(--warning);border-color:rgba(255,204,0,0.2); }
.badge-resolved { background:rgba(120,210,180,0.1);color:var(--accent);border-color:rgba(120,210,180,0.2); }

.kpi-row { display:grid;grid-template-columns:1fr 1fr;gap:10px;padding:10px 14px; }
.kpi-card { background:rgba(0,191,255,0.04);border:1px solid rgba(0,191,255,0.1);border-radius:4px;padding:12px;text-align:center; }
.kpi-value { font-size:24px;font-weight:700;font-family:"SF Mono",monospace;margin-bottom:4px; }
.kpi-label { font-size:11px;color:var(--text-dim); }

.pie-wrap { display:flex;align-items:center;gap:16px;padding:10px 0; }
.pie-circle { width:90px;height:90px;border-radius:50%;background:conic-gradient(var(--danger) 0deg 90deg,var(--warning) 90deg 180deg,var(--info) 180deg 270deg,var(--accent) 270deg 360deg);flex-shrink:0;box-shadow:0 0 12px rgba(0,191,255,0.1); }
.pie-legend { display:flex;flex-direction:column;gap:6px; }
.legend-item { display:flex;align-items:center;gap:6px;font-size:12px;color:var(--text-dim); }
.legend-dot { width:8px;height:8px;border-radius:2px; }

.bar-chart { display:flex;align-items:flex-end;justify-content:space-between;height:120px;padding:10px 0;gap:6px; }
.bar-col { flex:1;display:flex;flex-direction:column;align-items:center;gap:4px; }
.bar-fill { width:100%;background:linear-gradient(180deg,var(--accent),rgba(0,191,255,0.3));border-radius:2px 2px 0 0;min-height:4px;transition:height .5s; }
.bar-label { font-size:10px;color:var(--text-dim); }

.bottom-nav { position:fixed;bottom:0;left:0;right:0;height:52px;background:linear-gradient(180deg,transparent,#0A1628 30%);display:flex;align-items:center;justify-content:center;gap:4px;z-index:1000;padding:0 12px; }
.nav-btn { position:relative;display:flex;flex-direction:column;align-items:center;justify-content:center;gap:2px;padding:4px 10px;background:transparent;border:1px solid transparent;border-radius:6px;color:#8899AA;font-size:10px;cursor:pointer;transition:all .25s;min-width:56px; }
.nav-btn:hover { background:rgba(0,191,255,0.12);border-color:rgba(0,191,255,0.15);color:#00BFFF;transform:translateY(-2px); }
.nav-btn.active { background:rgba(0,191,255,0.1);border-color:rgba(0,191,255,0.3);color:#00BFFF; }
.nav-btn.active::after { content:'';position:absolute;bottom:-1px;left:30%;right:30%;height:2px;background:#00BFFF;border-radius:1px; }
.nav-icon svg { width:16px;height:16px;display:block; }

::-webkit-scrollbar { width:4px; }
::-webkit-scrollbar-thumb { background:rgba(0,191,255,0.15);border-radius:2px; }
::-webkit-scrollbar-track { background:transparent; }
</style>
