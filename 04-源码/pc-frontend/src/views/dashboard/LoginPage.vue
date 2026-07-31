<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const form = ref({ username: 'admin', password: '' })
const loading = ref(false)
const error = ref('')
const currentTime = ref(new Date().toLocaleString('zh-CN'))

setInterval(() => {
  currentTime.value = new Date().toLocaleString('zh-CN')
}, 1000)

function handleLogin() {
  if (!form.value.username || !form.value.password) {
    error.value = '请输入账号和密码'
    return
  }
  loading.value = true
  error.value = ''
  setTimeout(() => {
    loading.value = false
    localStorage.setItem('userRole', 'admin')
    localStorage.setItem('token', 'mock-token')
    router.push('/nav')
  }, 800)
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
          用户登录
        </div>

        <div class="space-y-5">
          <div>
            <input
              v-model="form.username"
              type="text"
              placeholder="请输入账号"
              class="w-full h-11 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-sm text-[#E8EDF5] placeholder-[#606060] outline-none focus:border-[#00BFFF] transition-colors"
            />
          </div>
          <div>
            <input
              v-model="form.password"
              type="password"
              placeholder="请输入密码"
              class="w-full h-11 bg-[#0A1628] border border-[rgba(0,191,255,0.15)] rounded px-3 text-sm text-[#E8EDF5] placeholder-[#606060] outline-none focus:border-[#00BFFF] transition-colors"
              @keyup.enter="handleLogin"
            />
          </div>

          <div v-if="error" class="text-xs text-[#FF3366]">{{ error }}</div>

          <button
            class="w-full h-11 bg-[#00BFFF] hover:bg-[#33CCFF] text-[#0A1628] font-semibold rounded text-sm transition-colors disabled:opacity-50"
            :disabled="loading"
            @click="handleLogin"
          >
            {{ loading ? '登录中...' : '登 录' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
