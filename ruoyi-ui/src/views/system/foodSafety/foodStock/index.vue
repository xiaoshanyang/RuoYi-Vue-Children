<template>
  <div class="container">
    <!-- 查询条件 -->
    <el-form :model="query" inline>
      <el-form-item label="食材名称">
        <el-input v-model="query.foodName" placeholder="请输入食材名称" />
      </el-form-item>
      <el-button type="primary" @click="getList">查询</el-button>
      <el-button @click="resetQuery">重置</el-button>
    </el-form>

    <!-- 库存列表 -->
    <el-table :data="list" border stripe>
      <el-table-column label="食材ID" prop="foodId" width="80" />
      <el-table-column label="食材名称" prop="foodName" />
      <el-table-column label="当前总库存" prop="totalStock" width="140" />

      <el-table-column label="操作" width="120">
        <template #default="scope">
          <el-button
            type="primary"
            size="mini"
            @click="viewBatch(scope.row.foodId)"
          >
            查看批次
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      v-model:current-page="query.pageNum"
      v-model:page-size="query.pageSize"
      :total="total"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
  </div>
</template>

<script>
import { listFoodStock } from "@/api/warehouse";

export default {
  name: "FoodStock",
  data() {
    return {
      list: [],
      total: 0,
      query: {
        pageNum: 1,
        pageSize: 10,
        foodName: "",
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    async getList() {
      const res = await listFoodStock(this.query);
      this.list = res.rows;
      this.total = res.total;
    },
    resetQuery() {
      this.query = {
        pageNum: 1,
        pageSize: 10,
        foodName: "",
      };
      this.getList();
    },
    viewBatch(foodId) {
      this.$tab.openTab("库存批次", `/system/warehouse/batch?foodId=${foodId}`);
    },
  },
};
</script>