import request from '@/utils/request'

// 库存总览列表
export function listFoodStock(query) {
  return request({
    url: '/system/warehouse/stock/list',
    method: 'get',
    params: query
  })
}

// 异常报表分页查询
export function listAbnormalReport(query) {
  return request({
    url: '/system/warehouse/abnormalReport',
    method: 'get',
    params: query
  })
}

// 导出异常报表
export function exportAbnormalReport(query) {
  return request({
    url: '/system/warehouse/abnormalReport/export',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 领用单创建接口
export function addWarehouseOut(data) {
  return request({
    url: '/system/warehouse/createOut',
    method: 'post',
    data: data
  })
}