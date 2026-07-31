import http from './http'

/** 获取工单列表 */
export function getWorkOrderList(params: any) {
  return http.get('/work-orders', { params })
}

/** 获取工单详情 */
export function getWorkOrderDetail(id: string) {
  return http.get(`/work-orders/${id}`)
}

/** 创建工单 */
export function createWorkOrder(data: any) {
  return http.post('/work-orders', data)
}

/** 派发工单 */
export function assignWorkOrder(id: string, assigneeId: number) {
  return http.put(`/work-orders/${id}/assign`, { assigneeId })
}

/** 审核工单 */
export function auditWorkOrder(id: string, opinion: string, approved: boolean) {
  return http.put(`/work-orders/${id}/audit`, { opinion, approved })
}

/** 工单统计 */
export function getWorkOrderStats() {
  return http.get('/work-orders/stats')
}
