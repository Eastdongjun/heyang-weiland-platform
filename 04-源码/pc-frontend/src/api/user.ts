import http from './http'
import type { User, PageResult } from '@/types'

/** 获取用户列表 */
export function getUserList(params: any): Promise<PageResult<User>> {
  return http.get('/users', { params })
}

/** 获取用户详情 */
export function getUserDetail(id: number): Promise<User> {
  return http.get(`/users/${id}`)
}

/** 新增用户 */
export function createUser(data: Partial<User>) {
  return http.post('/users', data)
}

/** 更新用户 */
export function updateUser(id: number, data: Partial<User>) {
  return http.put(`/users/${id}`, data)
}

/** 删除用户 */
export function deleteUser(id: number) {
  return http.delete(`/users/${id}`)
}
