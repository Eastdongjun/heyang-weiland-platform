import http from './http'

/** 发送短信验证码 */
export function sendSmsCode(phone: string) {
  return http.post('/auth/sms-code', null, { params: { phone } })
}

/** 手机号验证码登录 */
export function loginByPhone(phone: string, code: string) {
  return http.post('/auth/login', { phone, code })
}

/** 退出登录 */
export function logout() {
  return http.post('/auth/logout')
}
