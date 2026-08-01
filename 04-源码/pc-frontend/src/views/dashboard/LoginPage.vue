<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

const form = ref({ phone: '', code: '' })
const loading = ref(false)
const sending = ref(false)
const countdown = ref(0)
const error = ref('')
const currentTime = ref(new Date().toLocaleString('zh-CN'))

setInterval(() => {
  currentTime.value = new Date().toLocaleString('zh-CN')
}, 1000)

let timer: ReturnType<typeof setInterval> | null = null

/** 发送验证码 */
async function handleSendCode() {
  const phone = form.value.phone
  if (!/^1[3-9]\d{9}$/.test(phone)) {
    error.value = '请输入正确的手机号'
    return
  }
  sending.value = true
  error.value = ''
  try {
    await userStore.sendSmsCode(phone)
    countdown.value = 60
    timer = setInterval(() => {
      countdown.value--
      if (countdown.value <= 0 && timer) {
        clearInterval(timer)
        timer = null
      }
    }, 1000)
  } catch (e: any) {
    error.value = e?.response?.data?.message || '发送失败'
  } finally {
    sending.value = false
  }
}

/** 手机号验证码登录 */
async function handleLogin() {
  if (!form.value.phone || !form.value.code) {
    error.value = '请输入手机号和验证码'
    return
  }
  loading.value = true
  error.value = ''
  try {
    await userStore.login(form.value.phone, form.value.code)
    router.push('/nav')
  } catch (e: any) {
    error.value = e?.response?.data?.message || '登录失败'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="min-h-screen flex items-center justify-center bg-[#0A1628] relative overflow-hidden">
    <!-- 背景网格 -->
    <div class="absolute inset-0 opacity-5"
      style="background-image: linear-gradient(rgba(0,191,255,0.3) 1px,transparent 1px), linear-gradient(90deg,rgba(0,191,255,0.3) 1px,transparent 1px); background-size: 60px 60px;" />

    <div class="relative z-10 w-[420px]">
      <!-- Logo -->
      <div class="text-center mb-8">
        <div class="text-2xl font-bold tracking-[6px] mb-2"
          style="background: linear-gradient(90deg,#E8EDF5,#00BFFF); -webkit-background-clip:text; -webkit-text-fill-color:transparent;">
          合阳洽川湿地智慧管理平台
        </div>
        <div class="text-xs text-[#8899BB] tracking-[2px]">
          当前时间 {{ currentTime }}
        </div>
      </div>

      <!-- 登录卡片 -->
      <div class="bg-[#0F1D35] border border-[rgba(0,191,255,0.15)] rounded-lg p-8">
        <div class="text-base font-semibold text-[#E8EDF5] mb-6 flex items-center gap-2">
          <span class="w-[3px] h-4 bg-[#00BFFF] rounded-sm inline-block" />
          手机号登录
        </div>

        <div class="space-y-5">
          <div>
            <input
              v-model="form.phone"
              type="text"
              maxlength="11"
              placeholder="请输入手机号"
              class="w-full h-11 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-sm text-[#E8EDF5] placeholder-[#606060] outline-none focus:border-[#00BFFF] transition-colors"
            />
          </div>
          <div class="flex gap-2">
            <input
              v-model="form.code"
              type="text"
              maxlength="6"
              placeholder="请输入验证码"
              class="flex-1 h-11 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-sm text-[#E8EDF5] placeholder-[#606060] outline-none focus:border-[#00BFFF] transition-colors"
              @keyup.enter="handleLogin"
            />
            <button
              class="w-28 h-11 text-xs rounded font-medium transition-colors whitespace-nowrap"
              :class="countdown > 0 || sending ? 'bg-[#1a2a40] text-[#606060] cursor-not-allowed' : 'bg-[rgba(0,191,255,0.15)] text-[#00BFFF] hover:bg-[rgba(0,191,255,0.25)]'"
              :disabled="countdown > 0 || sending"
              @click="handleSendCode"
            >
              {{ sending ? '发送中...' : countdown > 0 ? `${countdown}s 后重发` : '获取验证码' }}
            </button>
          </div>

          <div v-if="error" class="text-xs text-[#FF3366]">{{ error }}</div>

          <button
            class="w-full h-11 bg-[#00BFFF] hover:bg-[#33CCFF] text-[#0A1628] font-semibold rounded text-sm transition-colors disabled:opacity-50"
            :disabled="loading"
            @click="handleLogin"
          >
            {{ loading ? '登录中...' : '登 录' }}
          </button>

          <div class="text-xs text-[#606060] text-center mt-2">
            开发环境万能验证码: 888888
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
