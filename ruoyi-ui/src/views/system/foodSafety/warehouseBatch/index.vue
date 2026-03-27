<template>
  <div class="app-container">

    <el-form :model="query" size="small" :inline="true" label-width="80px">
      <el-form-item label="食材名称">
        <el-input v-model="query.foodName" placeholder="请输入" />
      </el-form-item>
      <el-button type="primary" @click="getList">查询</el-button>
      <el-button @click="resetQuery">重置</el-button>
    </el-form>

    <el-table v-loading="loading" :data="list" border >
      <el-table-column label="序号" type="index" width="60" align="center" :index="index => index + 1" />
      <el-table-column label="批次ID" prop="batchId" width="100" align="center"/>
      <el-table-column label="食材名称" prop="foodName" align="center"/>
      <el-table-column label="食材类别" align="center" prop="foodType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.food_category" :value="scope.row.foodType" />
        </template>
      </el-table-column>
      <el-table-column label="计量单位" align="center" prop="unit">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.food_unit" :value="scope.row.unit" />
        </template>
      </el-table-column>
      <el-table-column label="入库数量" prop="inQty" width="120" align="center"/>
      <el-table-column label="剩余数量" prop="remainQty" width="120" align="center"/>
      <el-table-column label="入库时间" prop="inTime" width="180" align="center">
        <template slot-scope="scope">
          {{ parseTime(scope.row.inTime) }}
        </template>
      </el-table-column>
      <el-table-column label="采购单号" prop="purchaseId" width="120" align="center"/>
      <el-table-column label="验收单号" prop="checkId" width="120" align="center"/>
      <el-table-column label="供应商名称" align="center" prop="supplierName" />
    </el-table>

   <pagination
      v-show="total>0"
      :total="total"
      :page.sync="query.pageNum"
      :limit.sync="query.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listBatch } from "@/api/system/warehouse";

export default {
  name: "WarehouseBatch",
  dicts: ['food_category', 'food_unit'],
  data() {
    return {
      // 遮罩层
      loading: true,
      list: [],
      total: 0,
      query: {
        pageNum: 1,
        pageSize: 10,
        foodId: null,
        foodName: ""
      }
    };
  },
  created() {
    // RuoYi Vue2 正确接收参数写法
    if (this.$route.query && this.$route.query.foodId) {
      this.query.foodId = this.$route.query.foodId;
    }
    this.getList();
  },
  methods: {
    async getList() {
      this.loading = true;
      const res = await listBatch(this.query);
      this.list = res.rows;
      this.total = res.total;
      this.loading = false;
    },
    resetQuery() {
      this.query = {
        pageNum: 1,
        pageSize: 10,
        foodId: this.$route.query && this.$route.query.foodId ? this.$route.query.foodId : null,
        foodName: ""
      };
      this.getList();
    }
  }
};
</script>