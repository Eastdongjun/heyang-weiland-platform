// 🌿 工单 API
import { get, post, put } from '@/utils/http'

// 工单分页查询
export const getWorkOrders = (params) => get('/work-orders', params)

// 工单详情
export const getWorkOrderDetail = (id) => get(`/work-orders/${id}`)

// 接单
export const acceptOrder = (id) => put(`/work-orders/${id}/accept`)

// 提交处理结果
export const submitResult = (id, data) => put(`/work-orders/${id}/process`, data)

// 工单统计
export const getWorkOrderStats = () => get('/work-orders/stats')
