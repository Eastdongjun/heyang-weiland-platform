<script setup>
import { ref, onMounted } from 'vue'
import { getWorkOrders } from '@/api/workorder'

const stats = ref({ pending: 5, processing: 3, today: 12 })
const orders = ref([])
const loading = ref(false)

// 快捷功能
const quickActions = [
  { icon: '🔥', label: '火情上报', path: '/pages/index/report', type: 'danger' },
  { icon: '📋', label: '待办工单', path: '/pages/workorder/list', badge: 3 },
  { icon: '🎥', label: '视频监控', path: '/pages/video/list' },
  { icon: '🛩️', label: '无人机', path: '/pages/drone/view' },
]

// 加载最新工单
const loadOrders = async () => {
  loading.value = true
  try {
    const data = await getWorkOrders({ page: 1, size: 3 })
    orders.value = data.records || []
  } catch (e) {
    // 开发环境静默
  } finally {
    loading.value = false
  }
}

onMounted(() => loadOrders())

// 格式化工单状态
const statusMap = { 0: '待派发', 1: '待接单', 2: '处理中', 3: '待审核', 4: '已完成', 5: '已归档' }
const getStatusClass = (s) => ({ 0: 's-pending', 1: 's-wait', 2: 's-doing', 3: 's-review' }[s] || 's-done')
</script>

<template>
  <view class="home-page">
    <!-- 头部 -->
    <view class="header">
      <view class="header-top">
        <view class="header-greeting">
          <text class="greeting-text">🌿 湿地卫士</text>
          <text class="sub-text">合阳洽川湿地智慧管理平台</text>
        </view>
        <view class="header-avatar">👤</view>
      </view>

      <!-- 天气 + SOS -->
      <view class="header-actions">
        <view class="weather-card">
          <text>☀️ 32°C 晴 | 东风 3级</text>
        </view>
        <button class="sos-btn" @tap="uni.navigateTo({ url: '/pages/index/report' })">🆘 SOS</button>
      </view>
    </view>

    <!-- 统计卡片 -->
    <view class="stats-row">
      <view class="stat-card stat-red">
        <text class="stat-num">{{ stats.pending }}</text>
        <text class="stat-label">待处理告警</text>
      </view>
      <view class="stat-card stat-orange">
        <text class="stat-num">{{ stats.processing }}</text>
        <text class="stat-label">处理中工单</text>
      </view>
      <view class="stat-card stat-green">
        <text class="stat-num">{{ stats.today }}</text>
        <text class="stat-label">今日巡查</text>
      </view>
    </view>

    <!-- 快捷功能 -->
    <view class="quick-actions">
      <view v-for="item in quickActions" :key="item.label" class="action-item"
            :class="item.type === 'danger' ? 'action-danger' : ''"
            @tap="item.path.includes('index') ? uni.navigateTo({ url: item.path }) : uni.switchTab({ url: item.path })">
        <view class="action-icon">{{ item.icon }}</view>
        <text class="action-label">{{ item.label }}</text>
        <view v-if="item.badge" class="action-badge">{{ item.badge }}</view>
      </view>
    </view>

    <!-- 最新工单 -->
    <view class="section">
      <view class="section-header">
        <text class="section-title">📋 最新工单</text>
        <text class="section-more" @tap="uni.switchTab({ url: '/pages/workorder/list' })">全部 ></text>
      </view>
      <view v-if="orders.length === 0 && !loading" class="empty-state">暂无工单</view>
      <view v-for="order in orders" :key="order.id" class="order-card"
            @tap="uni.navigateTo({ url: `/pages/workorder/detail?id=${order.id}` })">
        <view class="order-header">
          <text class="order-no">{{ order.orderNo }}</text>
          <text class="order-status" :class="getStatusClass(order.orderStatus)">
            {{ statusMap[order.orderStatus] || '未知' }}
          </text>
        </view>
        <text class="order-title">{{ order.title }}</text>
        <text class="order-location">📍 {{ order.locationText || '-' }}</text>
      </view>
    </view>
  </view>
</template>

<style scoped>
.home-page { min-height: 100vh; background: #f5f5f5; padding-bottom: 40rpx; }

.header {
  background: linear-gradient(135deg, #1a7a3a, #43a047);
  padding: 40rpx 32rpx 32rpx;
}
.header-top { display: flex; justify-content: space-between; align-items: flex-start; }
.greeting-text { font-size: 40rpx; font-weight: bold; color: #fff; display: block; }
.sub-text { font-size: 24rpx; color: rgba(255,255,255,.7); margin-top: 8rpx; }
.header-avatar { font-size: 48rpx; }

.header-actions { display: flex; justify-content: space-between; align-items: center; margin-top: 24rpx; }
.weather-card {
  background: rgba(255,255,255,.2); border-radius: 12rpx; padding: 12rpx 20rpx;
  font-size: 24rpx; color: #fff; flex: 1;
}
.sos-btn {
  background: #e53935; color: #fff; border-radius: 24rpx;
  padding: 12rpx 36rpx; font-size: 28rpx; font-weight: bold; border: none; margin-left: 16rpx;
}

.stats-row { display: flex; padding: 24rpx 24rpx 0; gap: 16rpx; }
.stat-card { flex: 1; background: #fff; border-radius: 16rpx; padding: 24rpx; text-align: center; }
.stat-num { font-size: 48rpx; font-weight: bold; display: block; }
.stat-red .stat-num { color: #e53935; }
.stat-orange .stat-num { color: #f57c00; }
.stat-green .stat-num { color: #1a7a3a; }
.stat-label { font-size: 22rpx; color: #999; margin-top: 8rpx; display: block; }

.quick-actions {
  display: grid; grid-template-columns: repeat(4, 1fr);
  margin: 24rpx; background: #fff; border-radius: 16rpx; padding: 24rpx 16rpx;
}
.action-item {
  display: flex; flex-direction: column; align-items: center; gap: 8rpx;
  position: relative; padding: 12rpx 0;
}
.action-icon { font-size: 44rpx; }
.action-label { font-size: 22rpx; color: #666; }
.action-danger { background: #fff5f5; border-radius: 12rpx; }
.action-badge {
  position: absolute; top: 0; right: 20rpx; background: #e53935; color: #fff;
  font-size: 20rpx; min-width: 32rpx; height: 32rpx; line-height: 32rpx; text-align: center;
  border-radius: 16rpx; padding: 0 8rpx;
}

.section { margin: 24rpx; background: #fff; border-radius: 16rpx; padding: 24rpx; }
.section-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 24rpx; }
.section-title { font-size: 30rpx; font-weight: bold; }
.section-more { font-size: 24rpx; color: #1a7a3a; }

.empty-state { text-align: center; color: #999; padding: 40rpx 0; font-size: 26rpx; }

.order-card {
  padding: 24rpx 0;
  border-bottom: 1rpx solid #f0f0f0;
}
.order-card:last-child { border-bottom: none; }
.order-header { display: flex; justify-content: space-between; margin-bottom: 8rpx; }
.order-no { font-size: 22rpx; color: #999; }
.order-status { font-size: 22rpx; padding: 2rpx 12rpx; border-radius: 8rpx; }
.s-pending { background: #fff3e0; color: #f57c00; }
.s-wait { background: #e3f2fd; color: #1976d2; }
.s-doing { background: #e8f5e9; color: #388e3c; }
.s-review { background: #fce4ec; color: #c62828; }
.s-done { background: #f5f5f5; color: #999; }
.order-title { font-size: 28rpx; font-weight: 500; display: block; margin-bottom: 8rpx; }
.order-location { font-size: 22rpx; color: #999; }
</style>
