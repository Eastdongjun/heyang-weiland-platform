<script setup lang="ts">
import { useRouter } from 'vue-router'
const router = useRouter()
const pages = [
  { file: 'LoadingPage', title: '加载中' },
  { file: 'EmptyPage', title: '空状态' },
  { file: 'NoDataPage', title: '无数据' },
  { file: 'NoPermissionPage', title: '无权限' },
  { file: 'NotFoundPage', title: '404' },
  { file: 'ServerErrorPage', title: '服务器错误' },
]
</script>
<template>
  <div class="min-h-screen bg-[#0A1628] flex items-center justify-center flex-col gap-6" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <div class="text-[120px] font-bold opacity-10 text-[#00BFFF] font-mono leading-none">404</div>
    <div class="text-lg text-[#8899AA]">页面不存在</div>
    <div class="text-sm text-[#606060]">请检查URL是否正确，或返回导航页</div>
    <a href="/nav" class="px-6 py-2 bg-[rgba(0,191,255,0.12)] border border-[rgba(0,191,255,0.25)] rounded text-sm text-[#00BFFF] no-underline hover:bg-[rgba(0,191,255,0.2)] transition-colors">返回导航</a>

    <!-- 其他异常页快速导航 -->
    <div class="mt-8 text-xs text-[#606060]">异常页面预览：</div>
    <div class="flex gap-2">
      <button v-for="p in pages" :key="p.file" class="px-3 py-1 bg-[rgba(255,255,255,0.04)] border border-[rgba(255,255,255,0.06)] rounded text-xs text-[#8899AA] hover:text-[#00BFFF] transition-colors"
        @click="router.push('/'+p.file.replace('Page','').toLowerCase())">
        {{ p.title }}
      </button>
    </div>
  </div>
</template>
