import request from '@/utils/request'

// 查询食材采购验收列表
export function listCheck(query) {
  return request({
    url: '/system/check/list',
    method: 'get',
    params: query
  })
}

// 查询食材采购验收详细
export function getCheck(id) {
  return request({
    url: '/system/check/' + id,
    method: 'get'
  })
}

// 新增食材采购验收
export function addCheck(data) {
  return request({
    url: '/system/check',
    method: 'post',
    data: data
  })
}

// 修改食材采购验收
export function updateCheck(data) {
  return request({
    url: '/system/check',
    method: 'put',
    data: data
  })
}

// 删除食材采购验收
export function delCheck(id) {
  return request({
    url: '/system/check/' + id,
    method: 'delete'
  })
}
