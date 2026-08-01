<script setup>
import { ref } from 'vue'
const type = ref('fire')
const desc = ref('')
const location = ref('')

const handleSubmit = () => {
  if (!desc.value) { uni.showToast({ title: '请输入描述', icon: 'none' }); return }
  uni.showToast({ title: '上报成功！', icon: 'success' })
  setTimeout(() => uni.navigateBack(), 1500)
}
</script>

<template>
  <view class="report-page">
    <view class="form-card">
      <view class="form-item">
        <text class="form-label">事件类型</text>
        <view class="type-selector">
          <view v-for="t in [{k:'fire',v:'🔥 火情'},{k:'smoke',v:'💨 烟雾'},{k:'other',v:'📋 其他'}]"
                :key="t.k" class="type-btn" :class="{ active: type === t.k }" @tap="type = t.k">
            {{ t.v }}
          </view>
        </view>
      </view>
      <view class="form-item">
        <text class="form-label">描述</text>
        <textarea v-model="desc" placeholder="请描述现场情况..." maxlength="500" />
      </view>
      <view class="form-item">
        <text class="form-label">位置</text>
        <input v-model="location" placeholder="自动获取或手动输入" readonly />
      </view>
      <button class="submit-btn" @tap="handleSubmit">提交上报</button>
    </view>
  </view>
</template>

<style scoped>
.report-page { min-height: 100vh; background: #f5f5f5; padding: 24rpx; }
.form-card { background: #fff; border-radius: 16rpx; padding: 32rpx; }
.form-item { margin-bottom: 32rpx; }
.form-label { font-size: 28rpx; font-weight: bold; display: block; margin-bottom: 16rpx; }
.type-selector { display: flex; gap: 16rpx; }
.type-btn {
  flex: 1; text-align: center; padding: 20rpx; border-radius: 12rpx;
  border: 2rpx solid #e8e8e8; font-size: 26rpx;
}
.type-btn.active { border-color: #1a7a3a; background: #e8f5e9; color: #1a7a3a; }
textarea { width: 100%; height: 200rpx; border: 2rpx solid #e8e8e8; border-radius: 12rpx; padding: 16rpx; font-size: 26rpx; }
input { width: 100%; height: 80rpx; border: 2rpx solid #e8e8e8; border-radius: 12rpx; padding: 0 16rpx; font-size: 26rpx; }
.submit-btn { width: 100%; height: 88rpx; line-height: 88rpx; background: #e53935; color: #fff; border-radius: 12rpx; font-size: 30rpx; border: none; margin-top: 20rpx; }
</style>
