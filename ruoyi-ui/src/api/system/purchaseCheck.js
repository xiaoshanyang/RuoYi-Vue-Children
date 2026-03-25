import request from '@/utils/request'

export function listPurchaseCheck(query) {
  return request({
    url: '/system/purchaseCheck/list',
    method: 'get',
    params: query
  })
}

export function getPurchaseCheck(checkId) {
  return request({
    url: '/system/purchaseCheck/' + checkId,
    method: 'get'
  })
}

export function addPurchaseCheck(data) {
  return request({
    url: '/system/purchaseCheck',
    method: 'post',
    data
  })
}