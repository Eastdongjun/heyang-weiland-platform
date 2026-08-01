<script setup>
import { ref, onMounted } from 'vue'
import { getWorkOrders } from '@/api/workorder'

const orders = ref([])
const loading = ref(false)
const currentStatus = ref('')
const statusTabs = [
  { value: '', label: '全部' },
  { value: '0', label: '待派发' },
  { value: '1', label: '待接单' },
  { value: '2', label: '处理中' },
  { value: '3', label: '待审核' },
]

const loadOrders = async () => {
  loading.value = true
  try {
    const params = { page: 1, size: 20 }
    if (currentStatus.value) params.status = currentStatus.value
    const data = await getWorkOrders(params)
    orders.value = data.records || []
  } catch (e) {
    // 开发环境
  } finally {
    loading.value = false
  }
}

onMounted(() => loadOrders())

const statusMap = { 0: '待派发', 1: '待接单', 2: '处理中', 3: '待审核', 4: '已完成', 5: '已归档' }
const priorityMap = { 1: '紧急', 2: '高', 3: '中', 4: '低' }
const priorityClass = (p) => ({ 1: 'p-urgent', 2: 'p-high', 3: 'p-mid', 4: 'p-low' }[p] || '')

const onTabChange = (val) => {
  currentStatus.value = val
  loadOrders()
}

const onRefresh = () => loadOrders()
</script>

<template>
  <view class="workorder-page">
    <!-- 状态筛选 -->
    <scroll-view scroll-x class="tabs-scroll">
      <view class="tabs">
        <view v-for="tab in statusTabs" :key="tab.value"
              class="tab-item" :class="{ active: currentStatus === tab.value }"
              @tap="onTabChange(tab.value)">
          {{ tab.label }}
        </view>
      </view>
    </scroll-view>

    <!-- 工单列表 -->
    <view class="order-list">
      <view v-if="orders.length === 0 && !loading" class="empty">暂无工单</view>
      <view v-for="order in orders" :key="order.id" class="order-card"
            @tap="uni.navigateTo({ url: `/pages/workorder/detail?id=${order.id}` })">
        <view class="order-top">
          <text class="order-no">{{ order.orderNo }}</text>
          <text class="priority-tag" :class="priorityClass(order.priority)">
            {{ priorityMap[order.priority] || '-' }}
          </text>
        </view>
        <text class="order-title">{{ order.title }}</text>
        <view class="order-bottom">
          <text class="order-loc">📍 {{ order.locationText || '-' }}</text>
          <text class="order-status" :class="'s-' + order.orderStatus">
            {{ statusMap[order.orderStatus] || '-' }}
          </text>
        </view>
        <text class="order-time">⏰ {{ order.createdAt || '-' }}</text>
      </view>
    </view>
  </view>
</template>

<style scoped>
.workorder-page { min-height: 100vh; background: #f5f5f5; }

.tabs-scroll { background: #fff; padding: 20rpx 0; white-space: nowrap; }
.tabs { display: flex; padding: 0 24rpx; }
.tab-item {
  display: inline-block; padding: 12rpx 32rpx; font-size: 26rpx; color: #666;
  border-radius: 32rpx; margin-right: 16rpx; background: #f5f5f5;
}
.tab-item.active { background: #1a7a3a; color: #fff; }

.order-list { padding: 24rpx; }
.empty { text-align: center; color: #999; padding: 80rpx 0; font-size: 28rpx; }

.order-card {
  background: #fff; border-radius: 16rpx; padding: 24rpx; margin-bottom: 16rpx;
}
.order-top { display: flex; justify-content: space-between; align-items: center; margin-bottom: 12rpx; }
.order-no { font-size: 22rpx; color: #999; }
.priority-tag { font-size: 20rpx; padding: 2rpx 12rpx; border-radius: 8rpx; }
.p-urgent { background: #ffebee; color: #e53935; }
.p-high { background: #fff3e0; color: #f57c00; }
.p-mid { background: #e8f5e9; color: #43a047; }
.p-low { background: #f5f5f5; color: #999; }

.order-title { font-size: 30rpx; font-weight: 500; display: block; margin-bottom: 16rpx; }
.order-bottom { display: flex; justify-content: space-between; align-items: center; margin-bottom: 8rpx; }
.order-loc { font-size: 24rpx; color: #666; }
.order-status { font-size: 22rpx; padding: 2rpx 12rpx; border-radius: 8rpx; background: #e8f5e9; color: #388e3c; }
.s-0 { background: #fff3e0; color: #f57c00; }
.s-1 { background: #e3f2fd; color: #1976d2; }
.s-2 { background: #e8f5e9; color: #388e3c; }
.s-3 { background: #fce4ec; color: #c62828; }
.s-4,.s-5 { background: #f5f5f5; color: #999; }

.order-time { font-size: 22rpx; color: #bbb; }
</style>
