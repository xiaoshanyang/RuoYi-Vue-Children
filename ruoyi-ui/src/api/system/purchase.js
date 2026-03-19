import request from '@/utils/request'

// 查询食材采购申请列表
export function listPurchase(query) {
  return request({
    url: '/system/purchase/list',
    method: 'get',
    params: query
  })
}

// 查询食材采购申请详细
export function getPurchase(id) {
  return request({
    url: '/system/purchase/' + id,
    method: 'get'
  })
}

// 新增食材采购申请
export function addPurchase(data) {
  return request({
    url: '/system/purchase',
    method: 'post',
    data: data
  })
}

// 修改食材采购申请
export function updatePurchase(data) {
  return request({
    url: '/system/purchase',
    method: 'put',
    data: data
  })
}

// 删除食材采购申请
export function delPurchase(id) {
  return request({
    url: '/system/purchase/' + id,
    method: 'delete'
  })
}
