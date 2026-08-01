// 🌿 用户状态管理
import { defineStore } from 'pinia'
import { loginByPhone, sendSmsCode } from '@/api/auth'

export const useUserStore = defineStore('user', {
  state: () => ({
    token: '',
    refreshToken: '',
    userInfo: null,
    isLogin: false
  }),

  getters: {
    userId: (state) => state.userInfo?.userId,
    roleCode: (state) => state.userInfo?.roleCode
  },

  actions: {
    // 发送验证码
    async sendCode(phone) {
      await sendSmsCode(phone)
    },

    // 手机号验证码登录
    async login(phone, code) {
      const data = await loginByPhone(phone, code)
      if (data) {
        this.token = data.accessToken
        this.refreshToken = data.refreshToken
        this.userInfo = {
          userId: data.userId,
          username: data.username,
          realName: data.realName,
          phone: data.phone,
          roleCode: data.roleCode
        }
        this.isLogin = true
        uni.setStorageSync('accessToken', data.accessToken)
        uni.setStorageSync('refreshToken', data.refreshToken)
        return true
      }
      return false
    },

    // 检查登录状态
    checkLogin() {
      const token = uni.getStorageSync('accessToken')
      if (token) {
        this.token = token
        this.isLogin = true
      }
    },

    // 退出登录
    logout() {
      this.token = ''
      this.refreshToken = ''
      this.userInfo = null
      this.isLogin = false
      uni.removeStorageSync('accessToken')
      uni.removeStorageSync('refreshToken')
      uni.reLaunch({ url: '/pages/login/login' })
    }
  },

  persist: {
    key: 'user-store',
    storage: {
      getItem: (key) => uni.getStorageSync(key),
      setItem: (key, value) => uni.setStorageSync(key, value)
    }
  }
})
