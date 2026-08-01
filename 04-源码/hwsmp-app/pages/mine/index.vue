<script setup>
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()

const menuItems = [
  { icon: '📊', label: '工作量统计', path: '/pages/mine/stats' },
  { icon: '📝', label: '历史工单', path: '/pages/mine/history' },
  { icon: '🎥', label: '视频监控', path: '/pages/video/list' },
  { icon: '🗺️', label: '离线地图', path: '/pages/mine/index' },
  { icon: '⚙️', label: '设置', path: '/pages/mine/settings' },
]

const handleLogout = () => userStore.logout()
</script>

<template>
  <view class="mine-page">
    <!-- 用户信息 -->
    <view class="user-card">
      <view class="avatar">👤</view>
      <view class="user-info">
        <text class="user-name">{{ userStore.userInfo?.realName || '未登录' }}</text>
        <text class="user-role">{{ userStore.userInfo?.roleCode || '-' }}</text>
      </view>
    </view>

    <!-- 菜单 -->
    <view class="menu-section">
      <view v-for="item in menuItems" :key="item.label" class="menu-item"
            @tap="item.path.startsWith('/pages') ? uni.navigateTo({ url: item.path }) : null">
        <text class="menu-icon">{{ item.icon }}</text>
        <text class="menu-label">{{ item.label }}</text>
        <text class="menu-arrow">></text>
      </view>
    </view>

    <!-- 退出 -->
    <view class="logout-section">
      <button class="logout-btn" @tap="handleLogout">退出登录</button>
    </view>
  </view>
</template>

<style scoped>
.mine-page { min-height: 100vh; background: #f5f5f5; }

.user-card {
  display: flex; align-items: center; gap: 24rpx;
  background: linear-gradient(135deg, #1a7a3a, #43a047);
  padding: 60rpx 40rpx 40rpx; margin-bottom: 24rpx;
}
.avatar { font-size: 64rpx; width: 96rpx; height: 96rpx; background: rgba(255,255,255,.2);
  border-radius: 50%; display: flex; align-items: center; justify-content: center; }
.user-name { font-size: 36rpx; color: #fff; font-weight: bold; display: block; }
.user-role { font-size: 24rpx; color: rgba(255,255,255,.7); margin-top: 8rpx; }

.menu-section { background: #fff; margin: 0 24rpx; border-radius: 16rpx; overflow: hidden; }
.menu-item {
  display: flex; align-items: center; padding: 32rpx 24rpx;
  border-bottom: 1rpx solid #f0f0f0; position: relative;
}
.menu-item:last-child { border-bottom: none; }
.menu-icon { font-size: 36rpx; margin-right: 20rpx; }
.menu-label { flex: 1; font-size: 28rpx; color: #333; }
.menu-arrow { font-size: 28rpx; color: #ccc; }

.logout-section { padding: 60rpx 24rpx; }
.logout-btn {
  width: 100%; height: 88rpx; line-height: 88rpx; background: #fff;
  border-radius: 16rpx; font-size: 30rpx; color: #e53935; border: none;
}
</style>
