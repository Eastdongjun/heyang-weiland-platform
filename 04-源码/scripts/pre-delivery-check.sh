#!/bin/bash
# 合阳湿地智慧管理平台 · 交付前质量门禁
# 基于控制平面标准化 quality-gates.sh —— 测试弱化/同源/契约 + 测试覆盖提示
# 用法: ./pre-delivery-check.sh [--strict]
# 说明: 项目当前零自动化测试(2026-08-09), 门禁以"提示测试缺口"模式运行不阻断;
#        --strict 时将测试缺口计入 FAIL。新增测试后自动进入扫描。
set -uo pipefail
REPO="/Users/donglai/东来/01-Projects/07-合阳湿地公园/04-源码"
GATES="/Users/donglai/Agent/0-Claude/control-plane/scripts/quality-gates.sh"
STRICT="${1:-}"
PASS=0; FAIL=0
ok()  { echo "✅ $1"; PASS=$((PASS+1)); }
bad() { echo "❌ $1"; FAIL=$((FAIL+1)); }

echo "════════ 合阳湿地交付前质量门禁 $(date '+%Y-%m-%d %H:%M:%S') ════════"

# [1] 质量防自欺门禁(标准化脚本)
echo "--- [1] 质量防自欺门禁(测试弱化/同源/契约) ---"
if bash "$GATES" "$REPO/hwsmp-server" "$REPO/pc-frontend/src" "$STRICT" >/tmp/hy-qg.log 2>&1; then
  # 汇总子脚本 PASS/WARN 到主脚本计数; FAIL 计入 FAIL
  QG_PASS=$(grep -oE "PASS=[0-9]+" /tmp/hy-qg.log | grep -oE "[0-9]+")
  QG_FAIL=$(grep -oE "FAIL=[0-9]+" /tmp/hy-qg.log | grep -oE "[0-9]+")
  QG_WARN=$(grep -oE "WARN=[0-9]+" /tmp/hy-qg.log | grep -oE "[0-9]+")
  PASS=$((PASS + QG_PASS + QG_WARN))
  FAIL=$((FAIL + QG_FAIL))
  sed 's/^/  /' /tmp/hy-qg.log | grep -E "✅|⚠️|质量门禁"
else
  echo "  ❌ 质量门禁失败:"; sed 's/^/     /' /tmp/hy-qg.log | tail -10
  FAIL=$((FAIL+1))
fi
rm -f /tmp/hy-qg.log

# [2] 测试覆盖提示(从零到一引导)
echo "--- [2] 测试覆盖状态 ---"
BACKEND_TESTS=$(find "$REPO/hwsmp-server" -path "*/src/test/*" -name "*.java" 2>/dev/null | wc -l)
FRONTEND_TESTS=$(find "$REPO/pc-frontend/src" \( -name "*.spec.*" -o -name "*.test.*" \) -not -path "*/node_modules/*" 2>/dev/null | wc -l)
if [ "$BACKEND_TESTS" -gt 0 ] || [ "$FRONTEND_TESTS" -gt 0 ]; then
  ok "已有自动化测试: 后端 $BACKEND_TESTS / 前端 $FRONTEND_TESTS"
else
  if [ "$STRICT" = "--strict" ]; then
    bad "零自动化测试(后端/前端) —— --strict 模式阻断, 需先建测试基线"
  else
    echo "  ⚠️  当前零自动化测试 —— 建议为高风险模块(设备/告警/工作流/权限)建立测试基线"
  fi
fi

echo "══════════════════════════════════════════════════"
echo "结果: PASS=$PASS FAIL=$FAIL"
[ "$FAIL" -gt 0 ] && { echo "❌ 交付前质量门禁未通过"; exit 1; } || { echo "✅ 交付前质量门禁通过 —— 可以交付"; exit 0; }
