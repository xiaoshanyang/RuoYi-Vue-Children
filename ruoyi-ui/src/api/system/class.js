import request from '@/utils/request'

// 查询班级信息（按届命名）列表
export function listClass(query) {
  return request({
    url: '/base/class/list',
    method: 'get',
    params: query
  })
}

// 查询班级信息（按届命名）详细
export function getClass(id) {
  return request({
    url: '/base/class/' + id,
    method: 'get'
  })
}

// 新增班级信息（按届命名）
export function addClass(data) {
  return request({
    url: '/base/class',
    method: 'post',
    data: data
  })
}

// 修改班级信息（按届命名）
export function updateClass(data) {
  return request({
    url: '/base/class',
    method: 'put',
    data: data
  })
}

// 删除班级信息（按届命名）
export function delClass(id) {
  return request({
    url: '/base/class/' + id,
    method: 'delete'
  })
}
