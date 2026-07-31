import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { loginByPhone, sendSmsCode as apiSendSmsCode, logout as apiLogout } from '@/api/auth'

interface UserInfo {
  userId: number
  username: string
  realName: string
  phone: string
  roleCode: string
}

export const useUserStore = defineStore('user', () => {
  const token = ref(localStorage.getItem('accessToken') || '')
  const refreshToken = ref(localStorage.getItem('refreshToken') || '')
  const userInfo = ref<UserInfo | null>(null)

  const isLoggedIn = computed(() => !!token.value)

  /** 发送验证码 */
  async function sendSmsCode(phone: string) {
    await apiSendSmsCode(phone)
  }

  /** 手机号登录 */
  async function login(phone: string, code: string) {
    const data: any = await loginByPhone(phone, code)
    token.value = data.accessToken
    refreshToken.value = data.refreshToken
    userInfo.value = {
      userId: data.userId,
      username: data.username,
      realName: data.realName,
      phone: data.phone,
      roleCode: data.roleCode,
    }
    localStorage.setItem('accessToken', data.accessToken)
    localStorage.setItem('refreshToken', data.refreshToken)
  }

  /** 退出登录 */
  async function logout() {
    try { await apiLogout() } catch {}
    token.value = ''
    refreshToken.value = ''
    userInfo.value = null
    localStorage.removeItem('accessToken')
    localStorage.removeItem('refreshToken')
  }

  return { token, refreshToken, userInfo, isLoggedIn, sendSmsCode, login, logout }
})
