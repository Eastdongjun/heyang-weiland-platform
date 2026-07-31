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
    },
    {
      path: '/nav',
      name: 'Nav',
      component: () => import('@/views/dashboard/NavPage.vue'),
    },
    {
      path: '/gis',
      name: 'GisMap',
      component: () => import('@/views/dashboard/GisMapPage.vue'),
    },
    {
      path: '/alert',
      name: 'AlertCenter',
      component: () => import('@/views/alert/AlertCenterPage.vue'),
    },
    {
      path: '/alert/:id',
      name: 'AlertDetail',
      component: () => import('@/views/alert/AlertDetailPage.vue'),
    },
    {
      path: '/emergency',
      name: 'Emergency',
      component: () => import('@/views/emergency/EmergencyPage.vue'),
    },
    {
      path: '/emergency/order/:id',
      name: 'EmergencyOrderDetail',
      component: () => import('@/views/emergency/OrderDetailPage.vue'),
    },
    {
      path: '/emergency/plan/:id',
      name: 'EmergencyPlanDetail',
      component: () => import('@/views/emergency/PlanDetailPage.vue'),
    },
    {
      path: '/workorder',
      name: 'WorkOrder',
      component: () => import('@/views/workorder/WorkOrderPage.vue'),
    },
    {
      path: '/workorder/:id',
      name: 'WorkOrderDetail',
      component: () => import('@/views/workorder/WorkOrderDetailPage.vue'),
    },
    {
      path: '/workorder/create',
      name: 'WorkOrderCreate',
      component: () => import('@/views/workorder/WorkOrderCreatePage.vue'),
    },
    {
      path: '/monitor',
      name: 'Monitor',
      component: () => import('@/views/monitor/MonitorPage.vue'),
    },
    {
      path: '/env',
      name: 'Environment',
      component: () => import('@/views/monitor/EnvironmentPage.vue'),
    },
    {
      path: '/uav',
      name: 'UAV',
      component: () => import('@/views/monitor/UavPage.vue'),
    },
    {
      path: '/statistics',
      name: 'Statistics',
      component: () => import('@/views/monitor/StatisticsPage.vue'),
    },
    {
      path: '/device',
      name: 'Device',
      component: () => import('@/views/device/DevicePage.vue'),
    },
    {
      path: '/device/:id',
      name: 'DeviceDetail',
      component: () => import('@/views/device/DeviceDetailPage.vue'),
    },
    {
      path: '/user',
      name: 'User',
      component: () => import('@/views/user/UserPage.vue'),
    },
    {
      path: '/role',
      name: 'Role',
      component: () => import('@/views/user/RolePage.vue'),
    },
    {
      path: '/patrol/schedule',
      name: 'Schedule',
      component: () => import('@/views/patrol/SchedulePage.vue'),
    },
    {
      path: '/patrol/attendance',
      name: 'Attendance',
      component: () => import('@/views/patrol/AttendancePage.vue'),
    },
    {
      path: '/system/log',
      name: 'OperationLog',
      component: () => import('@/views/system/LogPage.vue'),
    },
    {
      path: '/system/params',
      name: 'SystemParams',
      component: () => import('@/views/system/ParamsPage.vue'),
    },
    {
      path: '/vr',
      name: 'VR',
      component: () => import('@/views/monitor/VrPage.vue'),
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'NotFound',
      component: () => import('@/views/error/NotFoundPage.vue'),
    },
  ],
})

export default router
