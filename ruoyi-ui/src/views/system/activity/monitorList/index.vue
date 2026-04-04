<template>
  <div class="app-container">
    <el-form :inline="true" :model="query" class="form-search">
      <el-form-item label="活动名称">
        <el-input v-model="query.activityName" placeholder="活动名称" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="query.monitorStatus" clearable>
          <el-option label="全部" :value="-1" />
          <el-option label="正常" :value="0" />
          <el-option label="异常" :value="1" />
        </el-select>
      </el-form-item>
      <el-button type="primary" @click="getList">查询</el-button>
      <el-button @click="resetQuery">重置</el-button>
    </el-form>

    <el-table :data="list" border>
      <el-table-column label="活动名称" prop="activityName" />
      <el-table-column label="班级" prop="classFullName" />
      <el-table-column label="秩序">
        <template slot-scope="scope">
          {{ scope.row.orderStatus === 0 ? '良好' : 
              scope.row.orderStatus === 1 ? '一般' : '较差' }}
        </template>
      </el-table-column>
      <el-table-column label="隐患">
        <template slot-scope="scope">
          {{ scope.row.safeHidden === 0 ? '无' : '有' }}
        </template>
      </el-table-column>
      <el-table-column label="情绪">
        <template slot-scope="scope">
          {{ scope.row.childEmotion === 0 ? '良好' : 
              scope.row.childEmotion === 1 ? '一般' : '较差' }}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.monitorStatus === 1 ? 'danger' : 'success'">
            {{ scope.row.monitorStatus === 1 ? '异常' : '正常' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="登记时间" prop="monitorTime" width="180" />
      <el-table-column label="登记人" prop="monitorBy" />
    </el-table>

    <el-pagination
      @size-change="getList"
      @current-change="getList"
      :current-page="query.pageNum"
      :page-size="query.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    />
  </div>
</template>

<script>
import { listMonitor } from "@/api/system/activity";

export default {
  name: "MonitorList",
  data() {
    return {
      query: { pageNum: 1, pageSize: 10, activityName: "", monitorStatus: "" },
      list: [],
      total: 0
    };
  },
  created() {
    this.getList();
  },
  methods: {
    async getList() {
      const res = await listMonitor(this.query);
      this.list = res.rows;
      this.total = res.total;
    },
    resetQuery() {
      this.query = { pageNum: 1, pageSize: 10, activityName: "", monitorStatus: "" };
      this.getList();
    }
  }
};
</script>