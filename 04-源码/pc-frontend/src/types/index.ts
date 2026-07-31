export interface User {
  id: number
  username: string
  realName: string
  phone: string
  avatar?: string
  gender?: number
  email?: string
  regionId?: number
  roleCode: string
  status: number
  lastLoginAt?: string
}

export interface PageResult<T> {
  records: T[]
  total: number
  size: number
  current: number
}

export interface AlarmItem {
  id: string
  alarmSource: string
  alarmType: string
  alarmLevel: number
  alarmStatus: string
  deviceId?: number
  deviceName?: string
  locationText?: string
  confidence?: number
  occurredAt: string
  snapshots?: string[]
  workOrderId?: string
}

export interface WorkOrderItem {
  id: string
  orderNo: string
  title: string
  priority: number
  orderStatus: number
  alarmId?: string
  locationText?: string
  assigneeId?: number
  created_at: string
}
