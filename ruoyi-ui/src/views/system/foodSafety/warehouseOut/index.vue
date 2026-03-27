<template>
  <div class="app-container">

    <!-- 查询区域 -->
    <el-form :model="query" size="small" :inline="true" label-width="80px">
      <el-form-item label="领用时间">
        <el-date-picker
          v-model="query.timeRange"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd HH:mm:ss"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="异常状态">
        <el-select v-model="query.abnormalStatus" clearable placeholder="请选择">
          <el-option label="全部" :value="null"></el-option>
          <el-option label="正常" :value="0"></el-option>
          <el-option label="异常" :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-button type="primary" @click="getList">查询</el-button>
      <el-button @click="resetQuery">重置</el-button>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:warehouseOunt:add']"
        >新增</el-button>
      </el-col>
      
    </el-row>

    <el-table v-loading="loading" :data="list" border >
      <el-table-column label="序号" type="index" width="60" align="center" :index="index => index + 1" />
      <el-table-column label="领用单号" prop="outId"></el-table-column>
      <el-table-column label="领用时间" prop="outTime">
        <template  slot-scope="scope">
          <span>{{ parseTime(scope.row.outTime) }} </span>
        </template>
      </el-table-column>
      <el-table-column label="操作人" prop="createBy"></el-table-column>
      <el-table-column label="状态" width="100">
        <template slot-scope="scope">
          <el-tag :type="scope.row.abnormalStatus == 1 ? 'danger' : 'success'">
            {{ scope.row.abnormalStatus == 1 ? "异常" : "正常" }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="异常说明" prop="abnormalNote"></el-table-column>
      <el-table-column label="操作" width="120">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-view"
            size="mini"
            @click="viewDetail(scope.row.outId)"
            >查看明细</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
   <pagination
      v-show="total>0"
      :total="total"
      :page.sync="query.pageNum"
      :limit.sync="query.pageSize"
      @pagination="getList"
    />

    <!-- 新增领用弹窗 -->
    <el-dialog title="食材领用" :visible.sync="open" width="700px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="领用时间">
          <el-date-picker
            v-model="form.outTime"
            type="datetime"
            placeholder="选择领用时间"
            value-format="yyyy-MM-dd HH:mm:ss"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="是否异常">
          <el-switch
            v-model="form.abnormalStatus"
            active-value="1"
            inactive-value="0"
            active-text="异常"
            inactive-text="正常"
          ></el-switch>
        </el-form-item>
        <el-form-item label="异常说明" v-if="form.abnormalStatus == 1">
          <el-input
            v-model="form.abnormalNote"
            type="textarea"
            rows="3"
            placeholder="请填写异常原因"
          ></el-input>
        </el-form-item>
      </el-form>

      <div style="margin-top: 15px">
        <el-button type="primary" size="mini" @click="handleOpenFood" style="margin-bottom: 10px">新增食材</el-button>
        <el-table :data="itemList" border>
          <el-table-column label="食材名称" prop="foodName"></el-table-column>
          <el-table-column label="单位">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.food_unit" :value="scope.row.unit" />
            </template>
          </el-table-column>
          <el-table-column label="库存数量" prop="stockQty" />
          <el-table-column label="领用数量" width="200">
            <template slot-scope="scope">
              <el-input-number
                v-model="scope.row.outQty"
                :min="0.1"
                step="0.1"
                style="width:150px" 
              ></el-input-number>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="100">
            <template slot-scope="scope">
              <el-button
                type="danger"
                size="mini"
                @click="itemList.splice(scope.$index, 1)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取消</el-button>
        <el-button type="success" @click="submitOut">确认领用（FIFO）</el-button>
      </div>
    </el-dialog>

    <!-- 食材选择弹窗 -->
    <el-dialog title="选择食材" :visible.sync="openFoodDialog" width="700px" append-to-body>
      <el-input v-model="foodQuery" placeholder="搜索食材" style="margin-bottom:10px" />
      <el-table :data="foodList" border height="300" @row-click="selectFood">
        <el-table-column label="名称" prop="foodName" />
        <el-table-column label="类别">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.food_category" :value="scope.row.foodType" />
          </template>
        </el-table-column>
        <el-table-column label="单位">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.food_unit" :value="scope.row.unit" />
          </template>
        </el-table-column>
        <el-table-column label="库存" prop="totalStock" />
      </el-table>
    </el-dialog>

    <!-- 查看食材领用详情弹窗 -->
    <el-dialog
      title="食材领用详情"
      :visible.sync="viewOpen"
      width="900px"
      append-to-body
    >
      <el-table :data="viewItems" border>
        <el-table-column label="序号" type="index" width="60" align="center" :index="index => index + 1" />
        <el-table-column label="食材名称" prop="foodName"></el-table-column>
        <el-table-column label="单位">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.food_unit" :value="scope.row.unit" />
          </template>
        </el-table-column>
        <el-table-column label="领用数量" width="200" prop="outQty" />            
      </el-table>

      <div slot="footer">
        <el-button @click="viewOpen = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOut, addWarehouseOut, listFoodStock, getOutById } from "@/api/system/warehouse";

export default {
  name: "WarehouseOut",
  dicts: ['food_category', 'food_unit'],
  data() {
    return {
      // 遮罩层
      loading: false,
      list: [],
      total: 0,
      open: false,
      query: {
        pageNum: 1,
        pageSize: 10,
        timeRange: [],
        abnormalStatus: null,
      },
      form: {
        outTime: "",
        abnormalStatus: "0",
        abnormalNote: "",
      },
      itemList: [],
      foodList: [],
      viewItems: [],
      viewOpen: false,
      foodQuery: '',
      openFoodDialog: false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      let params = {
        pageNum: this.query.pageNum,
        pageSize: this.query.pageSize,
        abnormalStatus: this.query.abnormalStatus,
        startTime: this.query.timeRange ? this.query.timeRange[0] : null,
        endTime: this.query.timeRange ? this.query.timeRange[1] : null,
      };
      listOut(params).then((res) => {
        this.list = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    resetQuery() {
      this.query = {
        pageNum: 1,
        pageSize: 10,
        timeRange: [],
        abnormalStatus: null,
      };
      this.getList();
    },
    handleAdd() {
      this.open = true;
      this.form = {
        outTime: new Date(),
        abnormalStatus: "0",
        abnormalNote: "",
      };
      this.itemList = [];
    },
    // 加载食材
    async handleOpenFood() {
      // 这里可以改成选择食材的方式，目前为了简化直接新增一行空数据
      // 先加载数据
      const res = await listFoodStock()
      this.foodList = res.rows

      // 再打开弹窗
      this.openFoodDialog = true
    },
    // 选择食材
    selectFood(row) {
      const exist = this.itemList.some(i => i.foodId === row.foodId)
      if (exist) {
        this.$modal.msgWarning('已添加该食材')
        return
      }
      this.itemList.push({
        foodId: row.foodId,
        foodName: row.foodName,
        unit: row.unit,
        stockQty: row.totalStock,
        outQty: 1,
      })
      this.openFoodDialog = false
    },
    closeDialog() {
      this.open = false;
    },
    submitOut() {
      let data = {
        outTime: this.form.outTime,
        abnormalStatus: this.form.abnormalStatus,
        abnormalNote: this.form.abnormalNote,
        itemList: this.itemList,
      };
      addWarehouseOut(data).then(() => {
        this.$modal.msgSuccess("领用成功");
        this.open = false;
        this.getList();
      });
    },
    async viewDetail(outId) {
      var res = await getOutById(outId);
      console.log(res)
      this.viewItems = res.data.items;
      this.viewOpen = true;
    },
  },
};
</script>