import request from '@/utils/request'

// 查询食材采购申请列表
export function listPurchase(query) {
  return request({
    url: '/purchase/request/list',
    method: 'get',
    params: query
  })
}

// 查询食材采购申请详细
export function getPurchase(id) {
  return request({
    url: '/purchase/request/' + id,
    method: 'get'
  })
}

// 新增食材采购申请
export function addPurchase(data) {
  return request({
    url: '/purchase/request',
    method: 'post',
    data: data
  })
}

// 修改食材采购申请
export function updatePurchase(data) {
  return request({
    url: '/purchase/request',
    method: 'put',
    data: data
  })
}

// 删除食材采购申请
export function delPurchase(id) {
  return request({
    url: '/purchase/request/' + id,
    method: 'delete'
  })
}
