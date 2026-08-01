<script setup lang="ts">
import { onMounted, onUnmounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import maplibregl from 'maplibre-gl'
import 'maplibre-gl/dist/maplibre-gl.css'

const router = useRouter()

// Clock
const now = ref(new Date())
let clockTimer: number
const headerDate = () => `${now.value.getFullYear()}年${now.value.getMonth()+1}月${now.value.getDate()}日`
const clockSec = () => now.value.toLocaleTimeString('zh-CN',{hour12:false})

let map: any = null

// Bottom nav
const bottomNav = [
  { name:'GIS地图', path:'/gis', active:true },
  { name:'视频监控', path:'/monitor' },
  { name:'无人机', path:'/uav' },
  { name:'环境监测', path:'/env' },
  { name:'告警中心', path:'/alert' },
  { name:'数据统计', path:'/statistics' },
  { name:'应急管理', path:'/emergency' },
  { name:'VR查看', path:'/vr' },
]

onMounted(() => {
  clockTimer = window.setInterval(() => now.value = new Date(), 1000)

  // MapLibre GL init (exact copy from prototype)
  map = new maplibregl.Map({
    container:'realMap',
    style:{version:8,sources:{},layers:[]},
    center:[110.240,35.205],
    zoom:12,
    pitch:45,
    bearing:-10,
    minZoom:10,
    maxZoom:17,
    attributionControl:false
  } as any)
  map.addControl(new maplibregl.NavigationControl({showCompass:true,showZoom:true,visualizePitch:true}),'bottom-right')
  map.setFog({color:'rgba(10,22,40,0.85)','high-color':'rgba(15,35,60,0.4)','horizon-blend':0.3,range:[2,12]})

  map.on('load',()=>{
    // Dark basemap
    map!.addSource('dark-source',{type:'raster',tiles:['https://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png'],tileSize:256})
    // Satellite
    map!.addSource('sat-source',{type:'raster',tiles:['https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}'],tileSize:256})
    // Terrain
    map!.addSource('dem-source',{type:'raster-dem',tiles:['https://s3.amazonaws.com/elevation-tiles-prod/terrarium/{z}/{x}/{y}.png'],tileSize:256,encoding:'terrarium',maxzoom:15})
    map!.addLayer({id:'dark-layer',type:'raster',source:'dark-source',layout:{visibility:'visible'}})
    map!.addLayer({id:'sat-layer',type:'raster',source:'sat-source',layout:{visibility:'none'}})
    map!.setTerrain({source:'dem-source',exaggeration:1.5})
    map!.addLayer({id:'hillshade',type:'hillshade',source:'dem-source',paint:{'hillshade-illumination-direction':315,'hillshade-exaggeration':0.4,'hillshade-shadow-color':'#000000','hillshade-highlight-color':'rgba(0,191,255,0.08)'}})

    // County boundary
    const hc=[[[110.337661,35.059985],[110.344882,35.078605],[110.346448,35.084334],[110.373708,35.13422],[110.364731,35.140251],[110.364338,35.198013],[110.378347,35.210868],[110.374826,35.251937],[110.385208,35.264306],[110.393566,35.270322],[110.399008,35.278563],[110.402427,35.28245],[110.403975,35.286488],[110.41237,35.29131],[110.415165,35.294418],[110.418871,35.299878],[110.421107,35.302666],[110.426397,35.307734],[110.434894,35.315036],[110.438586,35.317899],[110.427409,35.322924],[110.409889,35.328383],[110.407482,35.328846],[110.39765,35.329142],[110.395252,35.328956],[110.390724,35.329275],[110.388465,35.328445],[110.387814,35.326737],[110.386012,35.324194],[110.38384,35.321607],[110.377885,35.319474],[110.374456,35.319264],[110.367803,35.319899],[110.357634,35.321268],[110.354206,35.321264],[110.353467,35.321817],[110.35282,35.323755],[110.351341,35.324122],[110.347183,35.324112],[110.343108,35.321798],[110.341255,35.321607],[110.332657,35.320667],[110.333817,35.317857],[110.333673,35.317021],[110.332056,35.316697],[110.3266,35.317145],[110.323869,35.317561],[110.322622,35.3182],[110.323125,35.319174],[110.326226,35.319875],[110.326965,35.320381],[110.328956,35.324308],[110.329977,35.329991],[110.328545,35.330864],[110.321467,35.331962],[110.30597,35.33376],[110.296818,35.335096],[110.290229,35.337281],[110.287411,35.338431],[110.284343,35.33866],[110.282684,35.338312],[110.275814,35.335893],[110.270551,35.335101],[110.267276,35.334915],[110.264138,35.335836],[110.257264,35.338608],[110.253433,35.338932],[110.250985,35.338908],[110.247062,35.33928],[110.242733,35.340435],[110.237752,35.342744],[110.233973,35.345563],[110.231621,35.346908],[110.222445,35.351187],[110.209689,35.362749],[110.207245,35.365105],[110.203839,35.366913],[110.201077,35.367795],[110.198688,35.369231],[110.196937,35.371334],[110.195643,35.374061],[110.196064,35.375397],[110.198452,35.377981],[110.199284,35.379274],[110.19951,35.380747],[110.199094,35.382087],[110.197856,35.383751],[110.19233,35.388333],[110.185192,35.392986],[110.183677,35.39507],[110.182665,35.397239],[110.182438,35.399642],[110.181833,35.401491],[110.180082,35.403112],[110.175439,35.404409],[110.172764,35.405939],[110.170139,35.406869],[110.163971,35.407755],[110.154486,35.409872],[110.144876,35.412617],[110.140916,35.414285],[110.134138,35.419652],[110.127688,35.426353],[110.12663,35.428016],[110.126127,35.430785],[110.125988,35.433882],[110.125669,35.435016],[110.120735,35.429941],[110.118896,35.426663],[110.116493,35.425071],[110.111702,35.424752],[110.105701,35.42386],[110.103649,35.424394],[110.102434,35.423317],[110.101575,35.420462],[110.101917,35.418408],[110.101362,35.417426],[110.099773,35.416063],[110.099385,35.413856],[110.098008,35.412994],[110.097033,35.411597],[110.095083,35.410344],[110.093582,35.410706],[110.092154,35.408975],[110.0922,35.407617],[110.089696,35.407665],[110.087386,35.406678],[110.083352,35.407507],[110.081971,35.408189],[110.079906,35.408637],[110.077928,35.409853],[110.075452,35.409629],[110.073599,35.409085],[110.070152,35.408513],[110.069219,35.409791],[110.068327,35.410167],[110.06598,35.410153],[110.06428,35.411712],[110.063134,35.412136],[110.06246,35.413833],[110.060792,35.414743],[110.059008,35.413928],[110.056093,35.411302],[110.054499,35.410739],[110.053529,35.409319],[110.053496,35.40827],[110.051279,35.404948],[110.050327,35.405338],[110.050073,35.407917],[110.050951,35.410863],[110.052766,35.41357],[110.053117,35.415944],[110.052665,35.416583],[110.051205,35.416454],[110.049412,35.414624],[110.045194,35.412608],[110.042255,35.410925],[110.04092,35.411406],[110.040416,35.413437],[110.038245,35.413957],[110.035172,35.412083],[110.032543,35.411325],[110.030557,35.411521],[110.028496,35.412655],[110.02407,35.413852],[110.021483,35.413361],[110.020813,35.411707],[110.019103,35.410672],[110.017001,35.41021],[110.014122,35.408632],[110.01354,35.406521],[110.014016,35.403451],[110.013633,35.401801],[110.010246,35.398974],[110.010463,35.39702],[110.011623,35.393768],[110.011507,35.392314],[110.010325,35.388672],[110.006596,35.384414],[110.006254,35.380141],[110.005353,35.377457],[110.003976,35.375955],[110.002812,35.371582],[110.003879,35.368053],[110.004766,35.361089],[110.001112,35.355022],[110.002332,35.350252],[110.00199,35.348869],[110.00004,35.347719],[109.999093,35.346221],[110.002174,35.345105],[110.003011,35.344347],[110.004064,35.341852],[110.006887,35.336923],[110.008851,35.332181],[110.008874,35.330148],[110.006185,35.328674],[110.004189,35.325019],[109.998913,35.317919],[109.998192,35.316014],[109.994477,35.313266],[109.994338,35.31087],[109.994371,35.304012],[109.99463,35.300604],[109.993211,35.293721],[109.992038,35.291673],[109.98817,35.290794],[109.986826,35.290217],[109.987293,35.286102],[109.988341,35.283667],[109.991326,35.281137],[109.992112,35.27896],[109.995207,35.276553],[109.995706,35.271707],[109.997037,35.271249],[109.997854,35.26995],[109.996736,35.266526],[109.996418,35.262257],[109.998293,35.257711],[110.001306,35.255571],[110.002646,35.253451],[110.003394,35.251598],[110.007645,35.247548],[110.008592,35.245709],[110.00898,35.243545],[110.011364,35.240631],[110.011669,35.239213],[110.014109,35.237498],[110.015448,35.23616],[110.01652,35.23338],[110.019343,35.233629],[110.023797,35.235024],[110.025668,35.235215],[110.034969,35.234283],[110.037399,35.232721],[110.039871,35.229238],[110.040176,35.22716],[110.039003,35.225264],[110.029998,35.220472],[110.028602,35.218436],[110.028866,35.217051],[110.031291,35.215383],[110.033601,35.214466],[110.034507,35.21373],[110.034646,35.212531],[110.03374,35.208498],[110.033763,35.205497],[110.03131,35.202744],[110.028348,35.200632],[110.026311,35.199585],[110.022356,35.198132],[110.019967,35.19623],[110.013138,35.189018],[110.011262,35.186633],[110.009364,35.185036],[110.008791,35.183693],[110.009156,35.182656],[110.014196,35.180118],[110.016936,35.179759],[110.023137,35.18169],[110.024504,35.181695],[110.026588,35.181012],[110.028459,35.17986],[110.02742,35.177355],[110.027535,35.175782],[110.028833,35.175395],[110.032534,35.175773],[110.033722,35.175084],[110.033509,35.174343],[110.029993,35.170872],[110.028344,35.168291],[110.028344,35.167368],[110.029526,35.16296],[110.031425,35.157992],[110.032978,35.156333],[110.034937,35.15509],[110.036009,35.153775],[110.036235,35.151929],[110.035657,35.149571],[110.035745,35.147228],[110.036466,35.144895],[110.036905,35.14104],[110.038383,35.139892],[110.040449,35.139596],[110.04512,35.140581],[110.047134,35.140237],[110.047666,35.138529],[110.047596,35.136578],[110.046016,35.132924],[110.04445,35.130499],[110.0425,35.129973],[110.038476,35.129523],[110.036651,35.128251],[110.036928,35.126954],[110.040583,35.122807],[110.03989,35.122252],[110.036508,35.121171],[110.032659,35.120257],[110.031407,35.119726],[110.029933,35.117521],[110.025682,35.113187],[110.024925,35.111426],[110.028367,35.109139],[110.027701,35.108632],[110.023506,35.108006],[110.022485,35.107479],[110.022448,35.106647],[110.023765,35.103269],[110.023257,35.101116],[110.021797,35.099029],[110.020549,35.098092],[110.017666,35.097785],[110.013369,35.098996],[110.012247,35.098704],[110.011932,35.096249],[110.01129,35.095187],[110.009086,35.095297],[110.006568,35.096077],[109.999643,35.095283],[109.99402,35.09367],[109.991931,35.092742],[109.988813,35.090885],[109.983647,35.08686],[109.976611,35.080883],[109.97686,35.079361],[109.979341,35.076422],[109.981651,35.074972],[109.987052,35.074019],[109.991451,35.072244],[109.994412,35.070104],[109.996214,35.066208],[109.998113,35.065207],[109.999915,35.064796],[110.001033,35.064015],[110.002738,35.061526],[110.00788,35.060851],[110.013854,35.061124],[110.018484,35.061502],[110.020614,35.062345],[110.026773,35.061483],[110.030797,35.06177],[110.035893,35.060861],[110.037834,35.060171],[110.038393,35.058467],[110.038203,35.056384],[110.038619,35.053478],[110.03922,35.051286],[110.042833,35.045708],[110.044173,35.043122],[110.045009,35.04248],[110.048802,35.041657],[110.051482,35.038937],[110.053981,35.03876],[110.059161,35.041724],[110.060819,35.040618],[110.062395,35.038037],[110.064797,35.035015],[110.068406,35.032065],[110.070998,35.030451],[110.072282,35.028239],[110.072421,35.024312],[110.072841,35.022094],[110.074371,35.020619],[110.084946,35.020562],[110.093993,35.020983],[110.095425,35.020475],[110.096483,35.018028],[110.103414,35.018215],[110.1121,35.017765],[110.118633,35.017712],[110.121414,35.018517],[110.122615,35.020456],[110.125683,35.023263],[110.127896,35.023373],[110.129116,35.024135],[110.128857,35.028268],[110.129481,35.029795],[110.132359,35.029963],[110.138075,35.029451],[110.142589,35.030088],[110.144617,35.030131],[110.14853,35.028766],[110.151021,35.027607],[110.152716,35.027142],[110.156953,35.027181],[110.164965,35.026979],[110.167501,35.025543],[110.168005,35.021342],[110.169201,35.018358],[110.172699,35.014982],[110.174727,35.013961],[110.176977,35.01331],[110.178779,35.012151],[110.17974,35.010719],[110.180798,35.007643],[110.181029,35.004918],[110.182318,35.001914],[110.182642,34.993233],[110.184028,34.991426],[110.186232,34.990454],[110.189595,34.989706],[110.194932,34.9894],[110.200554,34.986985],[110.203775,34.986429],[110.20967,34.984848],[110.212891,34.982902],[110.214364,34.982347],[110.242382,34.980612],[110.258262,34.980008],[110.27099,34.977952],[110.274331,34.976812],[110.275149,34.975782],[110.274987,34.973558],[110.279575,34.9571],[110.28705,34.967059],[110.299562,34.979045],[110.301521,34.988619],[110.320871,35.005152],[110.32209,35.017927],[110.320584,35.038482],[110.333133,35.04508],[110.337661,35.059985]]]
    map!.addSource('county',{type:'geojson',data:{type:'Feature',properties:{},geometry:{type:'Polygon',coordinates:hc as [number,number][][]}}})
    map!.addLayer({id:'county-fill',type:'fill',source:'county',paint:{'fill-color':'#00BFFF','fill-opacity':0.02}})
    map!.addLayer({id:'county-line',type:'line',source:'county',paint:{'line-color':'#00BFFF','line-width':2.5,'line-opacity':0.6}})
    map!.addLayer({id:'county-glow',type:'line',source:'county',paint:{'line-color':'#00BFFF','line-width':6,'line-opacity':0.05}})

    // Wetland
    map!.addSource('wetland',{type:'geojson',data:{type:'Feature',properties:{},geometry:{type:'Polygon',coordinates:[[[110.18,35.28],[110.20,35.285],[110.22,35.29],[110.25,35.295],[110.28,35.30],[110.31,35.305],[110.34,35.30],[110.36,35.295],[110.38,35.285],[110.39,35.27],[110.40,35.25],[110.395,35.23],[110.39,35.21],[110.38,35.195],[110.36,35.18],[110.34,35.175],[110.31,35.17],[110.28,35.165],[110.25,35.17],[110.22,35.175],[110.20,35.185],[110.185,35.20],[110.175,35.22],[110.17,35.24],[110.175,35.26],[110.18,35.275],[110.18,35.28]]]}}})
    map!.addLayer({id:'wetland-fill',type:'fill',source:'wetland',paint:{'fill-color':'#00E5CC','fill-opacity':0.04}})
    map!.addLayer({id:'wetland-line',type:'line',source:'wetland',paint:{'line-color':'#00E5CC','line-width':1.5,'line-opacity':0.5,'line-dasharray':[6,4]}})

    // Core zone
    map!.addSource('core',{type:'geojson',data:{type:'Feature',properties:{},geometry:{type:'Polygon',coordinates:[[[110.22,35.27],[110.25,35.275],[110.28,35.28],[110.31,35.285],[110.33,35.28],[110.34,35.27],[110.345,35.255],[110.34,35.24],[110.33,35.225],[110.31,35.215],[110.28,35.21],[110.25,35.215],[110.23,35.225],[110.22,35.24],[110.215,35.255],[110.22,35.27]]]}}})
    map!.addLayer({id:'core-fill',type:'fill',source:'core',paint:{'fill-color':'#FF3366','fill-opacity':0.04}})
    map!.addLayer({id:'core-line',type:'line',source:'core',paint:{'line-color':'#FF3366','line-width':1.2,'line-opacity':0.5,'line-dasharray':[3,3]}})
    map!.addLayer({id:'core-glow',type:'line',source:'core',paint:{'line-color':'#FF3366','line-width':4,'line-opacity':0.06}})

    // River
    map!.addSource('river',{type:'geojson',data:{type:'Feature',properties:{},geometry:{type:'LineString',coordinates:[[110.42,35.35],[110.415,35.34],[110.41,35.33],[110.405,35.32],[110.395,35.31],[110.385,35.30],[110.375,35.29],[110.37,35.28],[110.365,35.27],[110.36,35.26],[110.355,35.25],[110.35,35.24],[110.345,35.23],[110.34,35.22],[110.335,35.21],[110.33,35.20],[110.325,35.19],[110.32,35.18],[110.315,35.17],[110.31,35.16],[110.305,35.15],[110.30,35.14],[110.295,35.13]]}}})
    map!.addLayer({id:'river-line',type:'line',source:'river',paint:{'line-color':'#40a9ff','line-width':3,'line-opacity':0.5}})

    // Device markers
    const devs=[{name:'湿地北入口摄像头',type:'cam',lng:110.19,lat:35.275,s:'online'},{name:'核心区热成像',type:'ptz',lng:110.275,lat:35.255,s:'online'},{name:'南岸4G球机',type:'cam',lng:110.295,lat:35.215,s:'warning'},{name:'观鸟台摄像头',type:'cam',lng:110.32,lat:35.235,s:'offline'},{name:'码头监控',type:'cam',lng:110.315,lat:35.205,s:'online'},{name:'环境监测站A',type:'env',lng:110.26,lat:35.245,s:'online'},{name:'无人机机场',type:'uav',lng:110.22,lat:35.265,s:'online'},{name:'瞭望塔',type:'tower',lng:110.23,lat:35.285,s:'online'}]
    const ico:{[key:string]:string}={cam:'📹',ptz:'🎥',env:'🌡',uav:'🛸',tower:'🗼'}
    devs.forEach(d=>{
      const el=document.createElement('div')
      el.style.cursor='pointer'
      el.onclick=function(){router.push('/device')}
      el.innerHTML=`<div style="width:28px;height:28px;border-radius:50%;background:${d.s==='online'?'rgba(0,204,153,0.25)':d.s==='warning'?'rgba(255,204,0,0.25)':'rgba(255,51,102,0.25)'};border:2px solid ${d.s==='online'?'#00CC99':d.s==='warning'?'#FFCC00':'#FF3366'};display:flex;align-items:center;justify-content:center;font-size:13px;box-shadow:0 0 10px ${d.s==='online'?'rgba(0,204,153,0.4)':d.s==='warning'?'rgba(255,204,0,0.4)':'rgba(255,51,102,0.4)'}">${ico[d.type]||'📹'}</div>`
      new maplibregl.Marker({element:el,anchor:'center'}).setLngLat([d.lng,d.lat]).addTo(map!)
    })

    // Landmarks
    const landmarks = [{name:'洽川镇',lng:110.20,lat:35.22},{name:'处女泉',lng:110.27,lat:35.24},{name:'福山',lng:110.21,lat:35.285}]
    landmarks.forEach((l: any)=>{
      const el=document.createElement('div')
      el.innerHTML=`<span style="color:rgba(255,255,255,0.4);font-size:10px;font-weight:500;text-shadow:0 0 4px rgba(0,0,0,0.9);white-space:nowrap;">${l.name}</span>`
      new maplibregl.Marker({element:el,anchor:'center'}).setLngLat([l.lng,l.lat]).addTo(map!)
    })
  })
})

// Layer switch
function switchLayer(mode:string){
  if(!map) return
  if(mode==='dark'){ map.setLayoutProperty('dark-layer','visibility','visible'); map.setLayoutProperty('sat-layer','visibility','none') }
  else if(mode==='sat'){ map.setLayoutProperty('dark-layer','visibility','none'); map.setLayoutProperty('sat-layer','visibility','visible') }
  else{ map.setLayoutProperty('dark-layer','visibility','visible'); map.setLayoutProperty('sat-layer','visibility','none') }
}

const activeLayer = ref('dark')
function setLayer(mode:string){ activeLayer.value = mode; switchLayer(mode) }

onUnmounted(()=>{ clearInterval(clockTimer); if(map) map.remove() })

// Panels data
const alertRank = [
  { name:'核心保护区',val:15 },{ name:'北门入口区',val:12 },{ name:'东岸芦苇荡',val:8 },{ name:'南岸滩涂',val:6 },{ name:'西门停车场',val:4 }
]

const chartBars: any = [{v:18,h:64},{v:22,h:79},{v:15,h:54},{v:28,h:100,danger:true},{v:20,h:71},{v:25,h:89},{v:19,h:68}]
</script>

<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden font-['PingFang_SC','Microsoft_YaHei','Helvetica_Neue',sans-serif]" style="color:#fff">

    <!-- TOP BAR -->
    <header class="top-bar">
      <div class="top-left" style="width:240px">
        <span style="font-size:14px;font-weight:600;color:var(--accent);font-family:var(--font-num)">{{ headerDate() }}</span>
      </div>
      <div class="top-center">
        <div class="top-title">洽川湿地GIS指挥中心</div>
        <div class="top-title-sub">智慧治理 · 一屏观全域</div>
      </div>
      <div class="top-right">
        <span class="top-weather">☀️ 26°C · 晴</span>
        <span class="top-time">{{ clockSec() }}</span>
        <a href="/nav" class="top-exit">退出</a>
      </div>
    </header>

    <!-- MAIN -->
    <main class="main">
      <div class="main-row">
        <!-- LEFT PANEL -->
        <aside class="left-panel">
          <div class="panel">
            <div class="panel-title">○ 合阳县 · 概况</div>
            <div style="text-align:center;padding:4px 0 8px;border-bottom:1px solid var(--border);margin-bottom:8px">
              <div style="font-size:28px;font-weight:700;color:var(--accent);font-family:var(--font-num)">1,342</div>
              <div style="font-size:10px;color:var(--text3)">km² · 辖区面积</div>
            </div>
            <div class="kpi-grid">
              <div class="kpi-card"><div class="kpi-label">总人口</div><div class="kpi-val" style="font-size:18px">42.3<span style="font-size:11px;font-weight:400;color:var(--text3)">万</span></div></div>
              <div class="kpi-card"><div class="kpi-label">行政村</div><div class="kpi-val" style="font-size:18px">215</div></div>
              <div class="kpi-card"><div class="kpi-label">湿地面积</div><div class="kpi-val" style="font-size:18px">176<span style="font-size:11px;font-weight:400;color:var(--text3)">km²</span></div></div>
              <div class="kpi-card"><div class="kpi-label">监控设备</div><div class="kpi-val" style="font-size:18px">98<span style="font-size:11px;font-weight:400;color:var(--text3)">台</span></div></div>
            </div>
          </div>

          <div class="panel">
            <div class="panel-title">● 今日核心指标</div>
            <div class="kpi-grid">
              <div class="kpi-card"><div class="kpi-label">事件总数</div><div class="kpi-val warn">47</div><div class="kpi-sub"><span>同比</span><span class="up">↑ 8.2%</span></div></div>
              <div class="kpi-card"><div class="kpi-label">待处理</div><div class="kpi-val danger">12</div><div class="kpi-sub"><span>环比</span><span class="down">↑ 15.3%</span></div></div>
              <div class="kpi-card"><div class="kpi-label">处理中</div><div class="kpi-val" style="color:var(--cyan)">28</div><div class="kpi-sub"><span>完成率</span><span class="up">68.4%</span></div></div>
              <div class="kpi-card"><div class="kpi-label">已办结</div><div class="kpi-val green">7</div><div class="kpi-sub"><span>设备在线</span><span class="up">87/98</span></div></div>
            </div>
          </div>

          <div class="panel" style="flex:1;min-height:0">
            <div class="panel-title">◎ 告警量 TOP5</div>
            <div class="rank-list">
              <div v-for="(r,i) in alertRank" :key="r.name" class="rank-item">
                <div class="rank-num" :class="{top:i<3}">{{ i+1 }}</div>
                <span class="rank-name">{{ r.name }}</span>
                <span class="rank-val">{{ r.val }}</span>
              </div>
            </div>
          </div>
        </aside>

        <!-- MAP -->
        <section class="map-wrap">
          <div id="realMap" />
          <div class="map-corner tl" /><div class="map-corner tr" /><div class="map-corner bl" /><div class="map-corner br" />
          <div class="map-layer-switch">
            <button class="map-layer-btn" :class="{active:activeLayer==='dark'}" @click="setLayer('dark')">暗色</button>
            <button class="map-layer-btn" :class="{active:activeLayer==='sat'}" @click="setLayer('sat')">卫星</button>
          </div>
          <div class="map-info">合阳县 · 洽川湿地 · zoom 12.0</div>
          <div class="map-legend">
            <span class="legend-item"><span class="legend-dot" style="background:var(--accent)" />监控点位</span>
            <span class="legend-item"><span class="legend-dot" style="background:var(--danger)" />告警区域</span>
            <span class="legend-item"><span class="legend-dot" style="background:var(--green)" />正常区域</span>
          </div>
          <div class="floating-alert-panel">
            <div class="floating-alert-title">⚠ 实时告警 <a href="/alert" style="color:var(--accent);font-size:9px;text-decoration:none;margin-left:auto">全部→</a></div>
            <div class="floating-alert-item"><span class="event-dot red" /><span style="color:var(--text2);font-size:10px">核心区北门 非法闯入</span><span style="color:var(--text3);font-size:9px;font-family:var(--font-num);margin-left:auto">10:42</span></div>
            <div class="floating-alert-item"><span class="event-dot yellow" /><span style="color:var(--text2);font-size:10px">CAM-0203 设备离线</span><span style="color:var(--text3);font-size:9px;font-family:var(--font-num);margin-left:auto">10:25</span></div>
            <div class="floating-alert-item"><span class="event-dot yellow" /><span style="color:var(--text2);font-size:10px">水质监测站C区 异常</span><span style="color:var(--text3);font-size:9px;font-family:var(--font-num);margin-left:auto">10:18</span></div>
            <div class="floating-alert-item"><span class="event-dot green" /><span style="color:var(--text2);font-size:10px">东岸火警误报 已处理</span><span style="color:var(--text3);font-size:9px;font-family:var(--font-num);margin-left:auto">09:45</span></div>
          </div>
        </section>

        <!-- RIGHT PANEL -->
        <aside class="right-panel">
          <div class="panel" style="flex:1;min-height:0;display:flex;flex-direction:column">
            <div class="panel-title">▦ 区域告警排名</div>
            <div class="chart-box" style="display:flex;flex-direction:column;gap:4px;padding:0 2px">
              <div v-for="(r,i) in alertRank" :key="r.name" class="rank-h-item">
                <span class="rank-h-num" :class="{top:i<3}">{{ i+1 }}</span>
                <span class="rank-h-name">{{ r.name }}</span>
                <div class="rank-h-track"><div class="rank-h-bar" :style="{width:r.val/15*100+'%',background:`linear-gradient(90deg,var(--accent),rgba(0,229,204,${1-i*0.2}))`}" /></div>
                <span class="rank-h-val">{{ r.val }}</span>
              </div>
            </div>
          </div>

          <div class="panel" style="flex:1;min-height:0;display:flex;flex-direction:column">
            <div class="panel-title">▨ 近7日告警趋势</div>
            <div style="flex-shrink:0;display:flex;gap:12px;padding:0 4px 4px;font-size:10px;color:var(--text3)">
              <span>峰值: <span style="color:var(--danger);font-weight:600">28</span> 件</span>
              <span>均值: <span style="color:var(--accent);font-weight:600">21</span> 件/日</span>
            </div>
            <div style="flex:1;display:flex;align-items:flex-end;gap:4px;padding:4px 2px 0">
              <div v-for="(b,i) in chartBars" :key="i"
                style="flex:1;display:flex;flex-direction:column;align-items:center;gap:2px">
                <div :style="{width:'100%',height:0,paddingBottom:b.h+'%',background:b.danger?'linear-gradient(180deg,var(--danger),rgba(255,51,102,0.4))':'linear-gradient(180deg,var(--accent),rgba(0,191,255,0.3))',borderRadius:'3px 3px 0 0',position:'relative'}">
                  <span :style="{position:'absolute',top:'-14px',left:'50%',transform:'translateX(-50%)',fontSize:'8px',color:b.danger?'var(--danger)':'var(--text2)',fontFamily:'var(--font-num)',fontWeight:b.danger?'600':'400'}">{{ b.v }}</span>
                </div>
                <span style="font-size:7px;color:var(--text3)">{{ 15+Number(i) }}</span>
              </div>
            </div>
          </div>

          <div class="panel" style="flex:1;min-height:0;display:flex;flex-direction:column">
            <div class="panel-title">● 事件类型占比</div>
            <div style="flex-shrink:0;display:flex;gap:12px;padding:0 4px 4px;font-size:10px;color:var(--text3)">
              <span>最多: <span style="color:var(--accent);font-weight:600">非法闯入</span></span>
              <span>占比 <span style="color:var(--accent);font-weight:600">35.7%</span></span>
            </div>
            <div style="flex:1;display:flex;align-items:center;gap:10px;padding:4px">
              <div style="width:70px;height:70px;border-radius:50%;background:conic-gradient(#00BFFF 0deg 135deg,#FFCC00 135deg 207deg,#FF3366 207deg 315deg,#00CC99 315deg 360deg);flex-shrink:0;border:2px solid var(--bg-deep)" />
              <div style="flex:1;display:flex;flex-direction:column;gap:3px;font-size:9px">
                <div style="display:flex;align-items:center;gap:4px"><span style="width:6px;height:6px;border-radius:50%;background:#00BFFF;flex-shrink:0" /><span style="color:var(--text2)">非法闯入</span><span style="margin-left:auto;color:var(--accent);font-family:var(--font-num)">37.5%</span></div>
                <div style="display:flex;align-items:center;gap:4px"><span style="width:6px;height:6px;border-radius:50%;background:#FFCC00;flex-shrink:0" /><span style="color:var(--text2)">设备故障</span><span style="margin-left:auto;color:var(--accent);font-family:var(--font-num)">20.0%</span></div>
                <div style="display:flex;align-items:center;gap:4px"><span style="width:6px;height:6px;border-radius:50%;background:#FF3366;flex-shrink:0" /><span style="color:var(--text2)">环境异常</span><span style="margin-left:auto;color:var(--accent);font-family:var(--font-num)">30.0%</span></div>
                <div style="display:flex;align-items:center;gap:4px"><span style="width:6px;height:6px;border-radius:50%;background:#00CC99;flex-shrink:0" /><span style="color:var(--text2)">消防预警</span><span style="margin-left:auto;color:var(--accent);font-family:var(--font-num)">12.5%</span></div>
              </div>
            </div>
          </div>
        </aside>
      </div>

      <!-- BOTTOM NAV -->
      <nav class="bottom-nav">
        <button v-for="nav in bottomNav" :key="nav.name" class="nav-btn" :class="{active:nav.active}" @click="router.push(nav.path)">
          <span class="nav-icon">
            <svg viewBox="0 0 24 24" v-if="nav.name==='GIS地图'"><circle cx="12" cy="10" r="3"/><path d="M12 2C7 2 3 6.5 3 12c0 2 .5 3.5 1.5 5L12 22l7.5-5c1-1.5 1.5-3 1.5-5 0-5.5-4-10-9-10z"/></svg>
            <svg viewBox="0 0 24 24" v-else-if="nav.name==='视频监控'"><rect x="2" y="4" width="16" height="14" rx="2"/><polygon points="23,7 19,10 19,14 23,17"/></svg>
            <svg viewBox="0 0 24 24" v-else-if="nav.name==='无人机'"><path d="M12 2L9 7l-4 1 3 2.5L7 15l5-3 5 3-1-4.5L19 8l-4-1z"/><line x1="12" y1="18" x2="12" y2="2"/></svg>
            <svg viewBox="0 0 24 24" v-else-if="nav.name==='环境监测'"><path d="M17 8C15 5 9 5 7 8c-3 4 0 9 5 12 5-3 8-8 5-12z"/><line x1="12" y1="20" x2="12" y2="11"/></svg>
            <svg viewBox="0 0 24 24" v-else-if="nav.name==='告警中心'"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg>
            <svg viewBox="0 0 24 24" v-else-if="nav.name==='数据统计'"><rect x="3" y="14" width="4" height="8" rx="1"/><rect x="10" y="8" width="4" height="14" rx="1"/><rect x="17" y="4" width="4" height="18" rx="1"/></svg>
            <svg viewBox="0 0 24 24" v-else-if="nav.name==='应急管理'"><path d="M12 2L3 7v6c0 5.5 4 10.5 9 11 5-.5 9-5.5 9-11V7l-9-5z"/><line x1="12" y1="8" x2="12" y2="14"/><circle cx="12" cy="17" r="1"/></svg>
            <svg viewBox="0 0 24 24" v-else><rect x="2" y="6" width="20" height="12" rx="3"/><circle cx="8" cy="12" r="1.5"/><circle cx="16" cy="12" r="1.5"/><line x1="12" y1="9" x2="12" y2="15"/></svg>
          </span>
          <span>{{ nav.name }}</span>
        </button>
      </nav>
    </main>
  </div>
</template>

<style scoped>
* { margin:0; padding:0; box-sizing:border-box; }

.top-bar { height:64px; background:linear-gradient(180deg,rgba(10,22,40,0.98),rgba(10,22,40,0.85)); border-bottom:1px solid var(--border); display:flex; align-items:center; justify-content:space-between; padding:0 28px; flex-shrink:0; position:relative; z-index:100; }
.top-bar::after { content:''; position:absolute; bottom:-1px; left:10%; right:10%; height:1px; background:linear-gradient(90deg,transparent,var(--accent),transparent); opacity:.6; }
.top-left { display:flex; align-items:center; gap:16px; }
.top-title { font-size:20px; font-weight:700; letter-spacing:4px; background:linear-gradient(90deg,#fff,var(--accent)); -webkit-background-clip:text; -webkit-text-fill-color:transparent; background-clip:text; }
.top-title-sub { font-size:10px; color:var(--text3); letter-spacing:1px; margin-top:2px; }
.top-center { display:flex; flex-direction:column; align-items:center; gap:4px; }
.top-right { display:flex; align-items:center; gap:20px; font-size:13px; color:var(--text2); }
.top-time { font-size:18px; font-weight:600; color:var(--accent); font-family:var(--font-num); }
.top-weather { display:flex; align-items:center; gap:4px; color:var(--text3); }
.top-exit { color:var(--text3); font-size:12px; text-decoration:none; padding:4px 10px; border:1px solid var(--border); border-radius:4px; }

.main { flex:1; display:flex; flex-direction:column; min-height:0; position:relative; }
.main-row { flex:1; display:flex; min-height:0; gap:8px; padding:8px; }

.panel { background:var(--bg-card); border:1px solid var(--border); border-radius:6px; padding:12px 14px; }
.panel-title { font-size:12px; font-weight:600; color:var(--accent); letter-spacing:1px; margin-bottom:10px; display:flex; align-items:center; gap:6px; text-transform:uppercase; }
.panel-title::before { content:''; width:3px; height:12px; background:var(--accent); border-radius:2px; }

.left-panel { width:260px; flex-shrink:0; padding:10px; display:flex; flex-direction:column; gap:8px; overflow-y:auto; z-index:10; border:1px solid var(--border); border-radius:4px; }
.left-panel::-webkit-scrollbar { width:3px; }
.left-panel::-webkit-scrollbar-thumb { background:var(--border); border-radius:2px; }

.kpi-grid { display:grid; grid-template-columns:1fr 1fr; gap:8px; }
.kpi-card { background:var(--bg-card2); border:1px solid var(--border); border-radius:4px; padding:10px 12px; }
.kpi-card .kpi-label { font-size:10px; color:var(--text3); letter-spacing:.5px; }
.kpi-card .kpi-val { font-size:22px; font-weight:700; color:var(--accent); font-family:var(--font-num); line-height:1.2; margin-top:2px; }
.kpi-card .kpi-val.danger { color:var(--danger); }
.kpi-card .kpi-val.warn { color:var(--warn); }
.kpi-card .kpi-val.green { color:var(--green); }
.kpi-card .kpi-sub { font-size:10px; color:var(--text3); margin-top:2px; display:flex; justify-content:space-between; }
.kpi-card .kpi-sub .up { color:var(--green); }
.kpi-card .kpi-sub .down { color:var(--danger); }

.rank-list { display:flex; flex-direction:column; gap:4px; }
.rank-item { display:flex; align-items:center; gap:8px; padding:5px 8px; border-radius:3px; font-size:11px; transition:background .2s; }
.rank-item:hover { background:var(--accent-dim); }
.rank-num { width:16px; height:16px; border-radius:3px; background:var(--bg-card2); display:flex; align-items:center; justify-content:center; font-size:9px; font-weight:700; color:var(--text3); font-family:var(--font-num); }
.rank-num.top { background:linear-gradient(135deg,var(--warn),#ff8c00); color:#fff; }
.rank-name { flex:1; color:var(--text2); }
.rank-val { font-family:var(--font-num); font-size:12px; font-weight:600; color:var(--accent); }

.right-panel { width:280px; flex-shrink:0; padding:10px; display:flex; flex-direction:column; gap:8px; overflow-y:auto; z-index:10; border:1px solid var(--border); border-radius:4px; }
.right-panel::-webkit-scrollbar { width:3px; }
.right-panel::-webkit-scrollbar-thumb { background:var(--border); border-radius:2px; }

.event-list { display:flex; flex-direction:column; gap:5px; }
.event-item { display:flex; align-items:center; gap:8px; padding:7px 10px; background:var(--bg-card2); border:1px solid var(--border); border-radius:4px; font-size:11px; transition:all .2s; cursor:pointer; }
.event-item:hover { border-color:var(--accent); background:var(--accent-dim); }
.event-dot { width:6px; height:6px; border-radius:50%; flex-shrink:0; }
.event-dot.red { background:var(--danger); box-shadow:0 0 6px var(--danger-glow); }
.event-dot.yellow { background:var(--warn); }
.event-dot.green { background:var(--green); }
.event-type { color:var(--text3); min-width:54px; }
.event-name { flex:1; color:var(--text2); overflow:hidden; text-overflow:ellipsis; white-space:nowrap; }
.event-time { color:var(--text3); font-family:var(--font-num); font-size:10px; }

.chart-box { height:110px; position:relative; margin:0; }

.rank-h-item { display:flex; align-items:center; gap:6px; font-size:10px; }
.rank-h-num { width:14px;height:14px;border-radius:2px;display:flex;align-items:center;justify-content:center;font-size:8px;font-weight:700;font-family:var(--font-num);flex-shrink:0; }
.rank-h-num.top { background:linear-gradient(135deg,var(--warn),#ff8c00);color:#fff; }
.rank-h-name { width:56px;color:var(--text2);flex-shrink:0;overflow:hidden;text-overflow:ellipsis;white-space:nowrap; }
.rank-h-track { flex:1;height:8px;background:var(--bg-card2);border-radius:4px;overflow:hidden; }
.rank-h-bar { height:100%;border-radius:4px;transition:width .5s; }
.rank-h-val { width:18px;font-family:var(--font-num);font-weight:600;color:var(--accent);text-align:right;flex-shrink:0; }

.map-wrap { flex:1; position:relative; min-width:0; overflow:hidden; border:1px solid var(--border); border-radius:4px; }
#realMap { width:100%; height:100%; }
.map-wrap::before { content:''; position:absolute; top:0; left:0; right:0; height:1px; background:linear-gradient(90deg,transparent,var(--accent),transparent); opacity:.5; z-index:2; pointer-events:none; }
.map-corner { position:absolute; width:16px; height:16px; border-color:var(--accent); border-style:solid; pointer-events:none; z-index:5; opacity:.6; }
.map-corner.tl { top:-1px; left:-1px; border-width:2px 0 0 2px; }
.map-corner.tr { top:-1px; right:-1px; border-width:2px 2px 0 0; }
.map-corner.bl { bottom:-1px; left:-1px; border-width:0 0 2px 2px; }
.map-corner.br { bottom:-1px; right:-1px; border-width:0 2px 2px 0; }

.map-layer-switch { position:absolute; top:16px; left:50%; transform:translateX(-50%); z-index:10; display:flex; gap:4px; background:rgba(10,22,40,0.9); border:1px solid var(--border); border-radius:6px; padding:4px; }
.map-layer-btn { padding:5px 14px; border:none; border-radius:4px; background:transparent; color:var(--text3); font-size:11px; cursor:pointer; transition:all .2s; }
.map-layer-btn:hover { color:var(--text2); }
.map-layer-btn.active { background:var(--accent-dim); color:var(--accent); }
.map-info { position:absolute; bottom:16px; left:16px; z-index:5; font-size:10px; color:var(--text3); font-family:var(--font-num); }
.map-legend { position:absolute; bottom:16px; right:16px; z-index:5; display:flex; gap:12px; background:rgba(10,22,40,0.8); border:1px solid var(--border); border-radius:4px; padding:8px 12px; font-size:10px; }
.legend-item { display:flex; align-items:center; gap:4px; color:var(--text3); }
.legend-dot { width:8px; height:8px; border-radius:2px; }

.floating-alert-panel { position:absolute; top:12px; right:12px; z-index:5; background:var(--bg-card); border:1px solid var(--border); border-radius:4px; width:220px; padding:8px 10px; }
.floating-alert-title { font-size:10px; font-weight:600; color:var(--accent); margin-bottom:6px; display:flex; align-items:center; }
.floating-alert-item { display:flex; align-items:center; gap:5px; padding:3px 0; border-bottom:1px solid var(--border); }
.floating-alert-item:last-child { border-bottom:none; }

.bottom-nav { height:52px; background:linear-gradient(180deg,transparent,#0A1628 30%); display:flex; align-items:center; justify-content:center; gap:4px; flex-shrink:0; padding:0 12px; }
.nav-btn { position:relative; display:flex; flex-direction:column; align-items:center; justify-content:center; gap:2px; padding:4px 10px; background:transparent; border:1px solid transparent; border-radius:6px; color:var(--text3); font-size:10px; cursor:pointer; transition:all .25s; min-width:56px; text-decoration:none; }
.nav-btn:hover { background:var(--accent-dim); border-color:var(--border); color:var(--accent); transform:translateY(-2px); }
.nav-btn.active { background:rgba(0,191,255,0.1); border-color:var(--border-bright); color:var(--accent); }
.nav-btn.active::after { content:''; position:absolute; bottom:-1px; left:30%; right:30%; height:2px; background:var(--accent); border-radius:1px; box-shadow:0 0 6px var(--accent-glow); }
.nav-icon svg { width:16px; height:16px; display:block; fill:none; stroke:currentColor; stroke-width:1.8; stroke-linecap:round; stroke-linejoin:round; transition:transform .25s; }
.nav-btn:hover .nav-icon svg { transform:scale(1.12); }

::-webkit-scrollbar { width:3px; }
::-webkit-scrollbar-thumb { background:var(--border); border-radius:2px; }
::-webkit-scrollbar-track { background:transparent; }
</style>
