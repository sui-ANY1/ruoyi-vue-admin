import request from '@/utils/request'

// 查询员工人事信息列表
export function list员工管理(query) {
  return request({
    url: '/employee/员工管理/list',
    method: 'get',
    params: query
  })
}

// 查询员工人事信息详细
export function get员工管理(employeeId) {
  return request({
    url: '/employee/员工管理/' + employeeId,
    method: 'get'
  })
}

// 新增员工人事信息
export function add员工管理(data) {
  return request({
    url: '/employee/员工管理',
    method: 'post',
    data: data
  })
}

// 修改员工人事信息
export function update员工管理(data) {
  return request({
    url: '/employee/员工管理',
    method: 'put',
    data: data
  })
}

// 删除员工人事信息
export function del员工管理(employeeId) {
  return request({
    url: '/employee/员工管理/' + employeeId,
    method: 'delete'
  })
}
