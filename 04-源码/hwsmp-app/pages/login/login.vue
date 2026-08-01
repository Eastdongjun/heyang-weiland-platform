<script setup>
import { ref } from 'vue'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
const phone = ref('13800138000')
const code = ref('')
const codeSent = ref(false)
const countdown = ref(0)
const loading = ref(false)

let timer = null

// 发送验证码
const handleSendCode = async () => {
  if (!/^1[3-9]\d{9}$/.test(phone.value)) {
    uni.showToast({ title: '请输入正确的手机号', icon: 'none' })
    return
  }
  try {
    await userStore.sendCode(phone.value)
    codeSent.value = true
    countdown.value = 60
    uni.showToast({ title: '验证码已发送 (开发: 888888)', icon: 'success' })
    timer = setInterval(() => {
      countdown.value--
      if (countdown.value <= 0) {
        clearInterval(timer)
        codeSent.value = false
      }
    }, 1000)
  } catch (e) {
    uni.showToast({ title: '发送失败，请重试', icon: 'none' })
  }
}

// 登录
const handleLogin = async () => {
  if (!code.value) {
    uni.showToast({ title: '请输入验证码', icon: 'none' })
    return
  }
  loading.value = true
  try {
    const ok = await userStore.login(phone.value, code.value)
    if (ok) {
      uni.showToast({ title: '登录成功', icon: 'success' })
      setTimeout(() => uni.switchTab({ url: '/pages/index/index' }), 500)
    }
  } catch (e) {
    uni.showToast({ title: '登录失败', icon: 'none' })
  } finally {
    loading.value = false
  }
}

// 微信一键登录
const handleWechatLogin = () => {
  uni.login({
    provider: 'weixin',
    success: () => uni.showToast({ title: '微信登录开发中', icon: 'none' })
  })
}
</script>

<template>
  <view class="login-page">
    <!-- Logo -->
    <view class="logo-section">
      <view class="logo-icon">🌿</view>
      <text class="app-name">湿地卫士</text>
      <text class="app-subtitle">合阳洽川湿地智慧管理平台</text>
    </view>

    <!-- 登录表单 -->
    <view class="form-section">
      <view class="input-group">
        <text class="input-label">手机号</text>
        <input class="input-field" v-model="phone" type="number" maxlength="11" placeholder="请输入手机号" />
      </view>

      <view class="input-group">
        <text class="input-label">验证码</text>
        <view class="code-row">
          <input class="input-field code-input" v-model="code" type="number" maxlength="6" placeholder="请输入验证码" />
          <button class="code-btn" :disabled="countdown > 0" @tap="handleSendCode">
            {{ countdown > 0 ? `${countdown}s` : (codeSent ? '重新发送' : '获取验证码') }}
          </button>
        </view>
      </view>

      <button class="login-btn" :loading="loading" @tap="handleLogin">
        {{ loading ? '登录中...' : '登录' }}
      </button>

      <view class="divider">
        <view class="divider-line" />
        <text class="divider-text">其他方式登录</text>
        <view class="divider-line" />
      </view>

      <view class="wechat-btn" @tap="handleWechatLogin">
        <text class="wechat-icon">💬</text>
        <text>微信一键登录</text>
      </view>
    </view>

    <text class="agreement-text">登录即同意《用户协议》和《隐私政策》</text>
  </view>
</template>

<style scoped>
.login-page {
  min-height: 100vh;
  background: linear-gradient(135deg, #1a7a3a 0%, #43a047 50%, #81c784 100%);
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 120rpx 40rpx 60rpx;
}

.logo-section {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 80rpx;
}

.logo-icon { font-size: 80rpx; margin-bottom: 20rpx; }
.app-name { font-size: 48rpx; font-weight: bold; color: #fff; }
.app-subtitle { font-size: 24rpx; color: rgba(255,255,255,.7); margin-top: 12rpx; }

.form-section {
  width: 100%;
  background: #fff;
  border-radius: 24rpx;
  padding: 60rpx 40rpx 40rpx;
}

.input-group { margin-bottom: 32rpx; }
.input-label { font-size: 28rpx; color: #333; margin-bottom: 12rpx; display: block; }
.input-field {
  width: 100%;
  height: 88rpx;
  border: 2rpx solid #e8e8e8;
  border-radius: 12rpx;
  padding: 0 24rpx;
  font-size: 30rpx;
  box-sizing: border-box;
}

.code-row { display: flex; gap: 20rpx; }
.code-input { flex: 1; }
.code-btn {
  width: 200rpx;
  height: 88rpx;
  line-height: 88rpx;
  background: #1a7a3a;
  color: #fff;
  border-radius: 12rpx;
  font-size: 24rpx;
  text-align: center;
  border: none;
}
.code-btn[disabled] { background: #ccc; }

.login-btn {
  width: 100%;
  height: 96rpx;
  line-height: 96rpx;
  background: linear-gradient(135deg, #1a7a3a, #43a047);
  color: #fff;
  border-radius: 12rpx;
  font-size: 32rpx;
  font-weight: bold;
  margin-top: 20rpx;
  border: none;
}

.divider {
  display: flex;
  align-items: center;
  margin: 48rpx 0 32rpx;
}
.divider-line { flex: 1; height: 2rpx; background: #e8e8e8; }
.divider-text { padding: 0 24rpx; font-size: 24rpx; color: #999; }

.wechat-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12rpx;
  font-size: 28rpx;
  color: #07c160;
  padding: 16rpx;
}
.wechat-icon { font-size: 36rpx; }

.agreement-text {
  margin-top: 48rpx;
  font-size: 22rpx;
  color: rgba(255,255,255,.6);
}
</style>
