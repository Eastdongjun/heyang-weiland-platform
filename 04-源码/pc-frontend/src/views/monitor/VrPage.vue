<template>
  <div class="h-screen w-screen bg-[#0A1628] flex flex-col overflow-hidden" style="font-family:'PingFang SC','Microsoft YaHei','Helvetica Neue',sans-serif">
    <header class="h-16 bg-gradient-to-b from-[rgba(10,22,40,0.98)] to-[rgba(10,22,40,0.85)] border-b border-[rgba(0,191,255,0.15)] flex items-center justify-between px-7 shrink-0 relative z-[100]">
      <div class="absolute bottom-0 left-[10%] right-[10%] h-px bg-gradient-to-r from-transparent via-[#00BFFF] to-transparent opacity-60" />
      <div class="text-xl font-bold tracking-[4px] bg-gradient-to-r from-white to-[#00BFFF] bg-clip-text text-transparent">VR全景查看</div>
      <a href="/nav" class="text-xs text-[#8899AA] no-underline px-2.5 py-1 border border-[rgba(0,191,255,0.15)] rounded">返回</a>
    </header>
    <div class="flex-1 flex items-center justify-center">
      <div class="text-center space-y-4">
        <div class="text-8xl opacity-15">🥽</div>
        <div class="text-sm text-[#8899AA]">720° 湿地全景展示</div>
        <div class="flex gap-3 justify-center">
          <button class="px-4 py-2 bg-[rgba(0,191,255,0.12)] border border-[rgba(0,191,255,0.25)] rounded text-xs text-[#00BFFF]">处女泉</button>
          <button class="px-4 py-2 bg-[rgba(0,191,255,0.12)] border border-[rgba(0,191,255,0.25)] rounded text-xs text-[#00BFFF]">芦苇荡</button>
          <button class="px-4 py-2 bg-[rgba(0,191,255,0.12)] border border-[rgba(0,191,255,0.25)] rounded text-xs text-[#00BFFF]">观鸟台</button>
        </div>
      </div>
    </div>
  </div>
</template>
