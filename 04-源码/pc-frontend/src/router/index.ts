import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/login',
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/dashboard/LoginPage.vue'),
      meta: { requiresAuth: false },
    },
    {
      path: '/nav',
      name: 'Nav',
      component: () => import('@/views/dashboard/NavPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/gis',
      name: 'GisMap',
      component: () => import('@/views/dashboard/GisMapPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/alert',
      name: 'AlertCenter',
      component: () => import('@/views/alert/AlertCenterPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/alert/:id',
      name: 'AlertDetail',
      component: () => import('@/views/alert/AlertDetailPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/emergency',
      name: 'Emergency',
      component: () => import('@/views/emergency/EmergencyPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/emergency/order/:id',
      name: 'EmergencyOrderDetail',
      component: () => import('@/views/emergency/OrderDetailPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/emergency/plan/:id',
      name: 'EmergencyPlanDetail',
      component: () => import('@/views/emergency/PlanDetailPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/workorder',
      name: 'WorkOrder',
      component: () => import('@/views/workorder/WorkOrderPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/workorder/:id',
      name: 'WorkOrderDetail',
      component: () => import('@/views/workorder/WorkOrderDetailPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/workorder/create',
      name: 'WorkOrderCreate',
      component: () => import('@/views/workorder/WorkOrderCreatePage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/monitor',
      name: 'Monitor',
      component: () => import('@/views/monitor/MonitorPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/monitor/replay',
      name: 'MonitorReplay',
      component: () => import('@/views/monitor/ReplayPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/uav/:id',
      name: 'UavDetail',
      component: () => import('@/views/monitor/UavDetailPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/env',
      name: 'Environment',
      component: () => import('@/views/monitor/EnvironmentPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/uav',
      name: 'UAV',
      component: () => import('@/views/monitor/UavPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/statistics',
      name: 'Statistics',
      component: () => import('@/views/monitor/StatisticsPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/device',
      name: 'Device',
      component: () => import('@/views/device/DevicePage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/device/:id',
      name: 'DeviceDetail',
      component: () => import('@/views/device/DeviceDetailPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/device/group',
      name: 'DeviceGroup',
      component: () => import('@/views/device/DeviceGroupPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/device/maintenance',
      name: 'DeviceMaintenance',
      component: () => import('@/views/device/DeviceMaintenancePage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/user',
      name: 'User',
      component: () => import('@/views/user/UserPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/role',
      name: 'Role',
      component: () => import('@/views/user/RolePage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/patrol/schedule',
      name: 'Schedule',
      component: () => import('@/views/patrol/SchedulePage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/patrol/attendance',
      name: 'Attendance',
      component: () => import('@/views/patrol/AttendancePage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/patrol/attendance/:id',
      name: 'AttendanceDetail',
      component: () => import('@/views/patrol/AttendanceDetailPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/system/log',
      name: 'OperationLog',
      component: () => import('@/views/system/LogPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/system/params',
      name: 'SystemParams',
      component: () => import('@/views/system/ParamsPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/vr',
      name: 'VR',
      component: () => import('@/views/monitor/VrPage.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/loading',
      name: 'Loading',
      component: () => import('@/views/error/LoadingPage.vue'),
      meta: { requiresAuth: false },
    },
    {
      path: '/empty',
      name: 'Empty',
      component: () => import('@/views/error/EmptyPage.vue'),
      meta: { requiresAuth: false },
    },
    {
      path: '/nodata',
      name: 'NoData',
      component: () => import('@/views/error/NoDataPage.vue'),
      meta: { requiresAuth: false },
    },
    {
      path: '/nopermission',
      name: 'NoPermission',
      component: () => import('@/views/error/NoPermissionPage.vue'),
      meta: { requiresAuth: false },
    },
    {
      path: '/servererror',
      name: 'ServerError',
      component: () => import('@/views/error/ServerErrorPage.vue'),
      meta: { requiresAuth: false },
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'NotFound',
      component: () => import('@/views/error/NotFoundPage.vue'),
      meta: { requiresAuth: false },
    },
  ],
})

// 路由守卫：登录检查
router.beforeEach((to, _from, next) => {
  const token = localStorage.getItem('accessToken')
  if (to.meta.requiresAuth !== false && !token) {
    next('/login')
  } else if (to.path === '/login' && token) {
    next('/nav')
  } else {
    next()
  }
})

export default router
