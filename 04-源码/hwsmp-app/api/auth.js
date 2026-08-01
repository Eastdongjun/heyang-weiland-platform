// 🌿 认证 API
import { post, get } from '@/utils/http'

// 发送验证码
export const sendSmsCode = (phone) => post('/auth/sms-code', { phone })

// 手机号验证码登录
export const loginByPhone = (phone, code) => post('/auth/login', { phone, code })

// 获取当前用户信息
export const getUserInfo = () => get('/auth/userinfo')

// 退出登录
export const logout = () => post('/auth/logout')
