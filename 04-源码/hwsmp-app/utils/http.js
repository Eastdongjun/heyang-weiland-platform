// 🌿 湿地卫士 - HTTP 请求封装

// H5 模式使用相对路径（通过 Vite 代理），App/小程序用直连
// #ifdef H5
const BASE_URL = '/api/v1'
// #endif
// #ifndef H5
const BASE_URL = 'http://localhost:8080/api/v1'
// #endif

// 请求拦截
const request = (options) => {
  return new Promise((resolve, reject) => {
    const token = uni.getStorageSync('accessToken')
    
    uni.request({
      url: BASE_URL + options.url,
      method: options.method || 'GET',
      data: options.data,
      header: {
        'Content-Type': 'application/json',
        ...(token ? { 'Authorization': `Bearer ${token}` } : {}),
        ...options.header
      },
      timeout: 15000,
      success: (res) => {
        const { statusCode, data } = res
        if (statusCode === 200 && data.code === 200) {
          resolve(data.data)
        } else if (statusCode === 401) {
          uni.removeStorageSync('accessToken')
          uni.removeStorageSync('refreshToken')
          uni.reLaunch({ url: '/pages/login/login' })
          reject(new Error('未登录'))
        } else {
          uni.showToast({ title: data.message || '请求失败', icon: 'none' })
          reject(new Error(data.message || '请求失败'))
        }
      },
      fail: (err) => {
        uni.showToast({ title: '网络异常，请稍后重试', icon: 'none' })
        reject(err)
      }
    })
  })
}

// 便捷方法
export const get = (url, params) => request({ url, method: 'GET', data: params })
export const post = (url, data) => request({ url, method: 'POST', data })
export const put = (url, data) => request({ url, method: 'PUT', data })
export const del = (url) => request({ url, method: 'DELETE' })

export default request
