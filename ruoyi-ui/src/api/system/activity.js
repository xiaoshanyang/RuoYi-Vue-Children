import request from '@/utils/request'

// 查询班级列表
export function listClassInfo(query) {
  return request({
    url: '/activity/classInfo/list',
    method: 'get',
    params: query
  })
}

// 查询班级详细
export function getClassInfo(classId) {
  return request({
    url: '/activity/classInfo/' + classId,
    method: 'get'
  })
}

// 新增班级
export function addClassInfo(data) {
  return request({
    url: '/activity/classInfo',
    method: 'post',
    data: data
  })
}

// 修改班级
export function updateClassInfo(data) {
  return request({
    url: '/activity/classInfo',
    method: 'put',
    data: data
  })
}

// 删除班级
export function delClassInfo(classId) {
  return request({
    url: '/activity/classInfo/' + classId,
    method: 'delete'
  })
}

// 一键升班
export function upgradeGrade() {
  return request({
    url: '/activity/classInfo/upgrade',
    method: 'post'
  })
}