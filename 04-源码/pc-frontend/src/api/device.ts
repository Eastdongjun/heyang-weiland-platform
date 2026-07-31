import http from './http'

/** 获取设备列表 */
export function getDeviceList(params: any) {
  return http.get('/devices', { params })
}

/** 获取设备树 */
export function getDeviceTree() {
  return http.get('/devices/tree')
}

/** 获取设备详情 */
export function getDeviceDetail(id: number) {
  return http.get(`/devices/${id}`)
}

/** 新增设备 */
export function createDevice(data: any) {
  return http.post('/devices', data)
}

/** 更新设备 */
export function updateDevice(id: number, data: any) {
  return http.put(`/devices/${id}`, data)
}

/** 获取设备实时视频流 */
export function getDeviceStream(deviceId: number) {
  return http.get(`/devices/${deviceId}/stream`)
}
