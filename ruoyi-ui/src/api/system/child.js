import request from '@/utils/request'

// 查询幼儿信息（无冗余班级名称）列表
export function listChild(query) {
  return request({
    url: '/base/child/list',
    method: 'get',
    params: query
  })
}

// 查询幼儿信息（无冗余班级名称）详细
export function getChild(id) {
  return request({
    url: '/base/child/' + id,
    method: 'get'
  })
}

// 新增幼儿信息（无冗余班级名称）
export function addChild(data) {
  return request({
    url: '/base/child',
    method: 'post',
    data: data
  })
}

// 修改幼儿信息（无冗余班级名称）
export function updateChild(data) {
  return request({
    url: '/base/child',
    method: 'put',
    data: data
  })
}

// 删除幼儿信息（无冗余班级名称）
export function delChild(id) {
  return request({
    url: '/base/child/' + id,
    method: 'delete'
  })
}
