import request from '@/utils/request'

export function listPurchase(query) {
  return request({ url: '/system/purchase/list', method: 'get', params: query })
}
export function getPurchase(id) {
  return request({ url: '/system/purchase/' + id, method: 'get' })
}
export function addPurchase(data) {
  return request({ url: '/system/purchase', method: 'post', data })
}
export function updatePurchase(data) {
  return request({ url: '/system/purchase', method: 'put', data })
}
export function auditPurchase(data) {
  return request({ url: '/system/purchase/audit', method: 'put', data })
}