import request from '@/utils/request'

// 查询活动监控记录列表
export function listMonitor(query) {
  return request({
    url: '/activity/activityMonitor/list',
    method: 'get',
    params: query
  })
}

// 查询活动监控记录详细
export function getMonitor(id) {
  return request({
    url: '/activity/activityMonitor/' + id,
    method: 'get'
  })
}

// 新增活动监控记录
export function addMonitor(data) {
  return request({
    url: '/activity/activityMonitor',
    method: 'post',
    data: data
  })
}

// 修改活动监控记录
export function updateMonitor(data) {
  return request({
    url: '/activity/activityMonitor',
    method: 'put',
    data: data
  })
}

// 删除活动监控记录
export function delMonitor(id) {
  return request({
    url: '/activity/activityMonitor/' + id,
    method: 'delete'
  })
}
