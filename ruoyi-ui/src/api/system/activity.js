import request from '@/utils/request'

// 活动模板
export function listTemplate(query) {
  return request({
    url: '/system/activity/template/list',
    method: 'get',
    params: query
  })
}

export function getTemplate(templateId) {
  return request({
    url: '/system/activity/template/' + templateId,
    method: 'get'
  })
}

export function addTemplate(data) {
  return request({
    url: '/system/activity/template',
    method: 'post',
    data
  })
}

export function updateTemplate(data) {
  return request({
    url: '/system/activity/template',
    method: 'put',
    data
  })
}

// 活动列表
export function listActivity(query) {
  return request({
    url: '/system/activity/activityInfo/list',
    method: 'get',
    params: query
  })
}

// 活动详情
export function getActivity(activityId) {
  return request({
    url: '/system/activity/activityInfo/' + activityId,
    method: 'get'
  })
}

// 新增活动
export function addActivity(data) {
  return request({
    url: '/system/activity/activityInfo',
    method: 'post',
    data: data
  })
}

// 修改活动
export function updateActivity(data) {
  return request({
    url: '/system/activity/activityInfo',
    method: 'put',
    data: data
  })
}

// 删除活动
export function delActivity(activityId) {
  return request({
    url: '/system/activity/activityInfo/' + activityId,
    method: 'delete'
  })
}

// 取消活动
export function closeActivity(activityId, data) {
  return request({
    url: '/activity/activity/close/' + activityId,
    method: 'put',
    data: data
  })
}

// 获取班级下拉
export function classList() {
  return request({
    url: '/system/activity/activityInfo/classList',
    method: 'get'
  })
}


// Monitor
// 新增监控
export function addMonitor(data) {
  return request({
    url: '/system/activity/monitor',
    method: 'post',
    data: data
  })
}

// 监控记录列表
export function listMonitor(query) {
  return request({
    url: '/system/activity/monitor/list',
    method: 'get',
    params: query
  })
}