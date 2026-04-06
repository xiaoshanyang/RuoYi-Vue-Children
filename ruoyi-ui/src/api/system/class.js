import request from '@/utils/request'

// 查询班级列表
export function listClassInfo(query) {
  return request({
    url: '/system/activity/classInfo/list',
    method: 'get',
    params: query
  })
}

// 查询班级详细
export function getClassInfo(classId) {
  return request({
    url: '/system/activity/classInfo/' + classId,
    method: 'get'
  })
}

// 新增班级
export function addClassInfo(data) {
  return request({
    url: '/system/activity/classInfo',
    method: 'post',
    data: data
  })
}

// 修改班级
export function updateClassInfo(data) {
  return request({
    url: '/system/activity/classInfo',
    method: 'put',
    data: data
  })
}

// 删除班级
export function delClassInfo(classId) {
  return request({
    url: '/system/activity/classInfo/' + classId,
    method: 'delete'
  })
}

// 一键升班
export function upgradeGrade() {
  return request({
    url: '/system/activity/classInfo/upgrade',
    method: 'post'
  })
}

// 关联教师
// 获取班级教师
export function getClassTeachers(classId) {
  return request({
    url: '/system/activity/classInfo/teachers/' + classId,
    method: 'get'
  })
}

// 保存班级教师
export function saveClassTeachers(data) {
  return request({
    url: '/system/activity/classInfo/saveTeachers',
    method: 'post',
    data
  })
}

// 获取教师列表
export function teacherList() {
  return request({
    url: '/system/activity/classInfo/teacherList',
    method: 'get'
  })
}

// 获取未分班的教师列表
export function unbindedTeacherList(classId){
  return request({
    url: '/system/activity/classInfo/unbindedTeachers/'+classId,
    method: 'get'
  })
}

// 获取班级
export function getMyClass(){
  return request({
    url: '/system/activity/classInfo/getMyClass',
    method: 'get'
  })
}