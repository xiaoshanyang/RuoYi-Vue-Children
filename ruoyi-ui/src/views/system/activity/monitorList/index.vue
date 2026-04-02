<template>
  <div class="app-container">
    <el-table :data="list" border>
      <el-table-column label="活动名称" prop="activityName" />
      <el-table-column label="班级" prop="classFullName" />
      <el-table-column label="年级" prop="gradeName" />

      <el-table-column label="秩序">
        <template slot-scope="scope">
          {{ scope.row.orderStatus === 0 ? '良好' :
              scope.row.orderStatus === 1 ? '一般' : '较差' }}
        </template>
      </el-table-column>

      <el-table-column label="安全隐患">
        <template slot-scope="scope">
          {{ scope.row.safeHidden === 0 ? '无' : '有' }}
        </template>
      </el-table-column>

      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.monitorStatus === 1 ? 'danger' : 'success'">
            {{ scope.row.monitorStatus === 1 ? '异常' : '正常' }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="监控时间" prop="monitorTime" width="180" />
    </el-table>

    <el-pagination
      @size-change="getList"
      @current-change="getList"
      :current-page="query.pageNum"
      :page-size="query.pageSize"
      layout="total, prev, pager, next, jumper"
      :total="total"
    />
  </div>
</template>

<script>
import { listMonitor } from '@/api/system/activity'

export default {
  name: 'MonitorList',
  data() {
    return {
      query: { pageNum: 1, pageSize: 10 },
      list: [],
      total: 0
    }
  },
  created() {
    this.getList()
  },
  methods: {
    async getList() {
      const res = await listMonitor(this.query)
      this.list = res.rows
      this.total = res.total
    }
  }
}
</script>