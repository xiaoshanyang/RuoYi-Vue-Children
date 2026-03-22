import request from '@/utils/request'

// 查询活动信息列表
export function listActivity(query) {
  return request({
    url: '/activity/activityInfo/list',
    method: 'get',
    params: query
  })
}

// 查询活动信息详细
export function getActivity(id) {
  return request({
    url: '/activity/activityInfo/' + id,
    method: 'get'
  })
}

// 新增活动信息
export function addActivity(data) {
  return request({
    url: '/activity/activityInfo',
    method: 'post',
    data: data
  })
}

// 新增活动信息
export function startActivity(data) {
  return request({
    url: '/activity/activityInfo/start',
    method: 'post',
    data: data
  })
}

// 修改活动信息
export function updateActivity(data) {
  return request({
    url: '/activity/activityInfo',
    method: 'put',
    data: data
  })
}

// 删除活动信息
export function delActivity(id) {
  return request({
    url: '/activity/activityInfo/' + id,
    method: 'delete'
  })
}
