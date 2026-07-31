import http from './http'

/** 获取告警列表 */
export function getAlarmList(params: any) {
  return http.get('/alarms', { params })
}

/** 获取告警详情 */
export function getAlarmDetail(id: string) {
  return http.get(`/alarms/${id}`)
}

/** 确认告警 */
export function confirmAlarm(id: string) {
  return http.put(`/alarms/${id}/confirm`)
}

/** 标记误报 */
export function markFalseAlarm(id: string, reason: string) {
  return http.put(`/alarms/${id}/false-alarm`, { reason })
}

/** 告警统计 */
export function getAlarmStats() {
  return http.get('/alarms/stats')
}
