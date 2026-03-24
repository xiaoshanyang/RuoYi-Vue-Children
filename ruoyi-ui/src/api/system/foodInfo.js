import request from '@/utils/request'

// 查询食材基础信息列表
export function listFoodInfo(query) {
  return request({
    url: '/system/foodInfo/list',
    method: 'get',
    params: query
  })
}

// 查询食材基础信息详细
export function getFoodInfo(foodId) {
  return request({
    url: '/system/foodInfo/' + foodId,
    method: 'get'
  })
}

// 新增食材基础信息
export function addFoodInfo(data) {
  return request({
    url: '/system/foodInfo',
    method: 'post',
    data: data
  })
}

// 修改食材基础信息
export function updateFoodInfo(data) {
  return request({
    url: '/system/foodInfo',
    method: 'put',
    data: data
  })
}

// 删除食材基础信息
export function delFoodInfo(foodId) {
  return request({
    url: '/system/foodInfo/' + foodId,
    method: 'delete'
  })
}
