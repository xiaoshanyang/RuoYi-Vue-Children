<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="食材名称" prop="foodName">
        <el-input v-model="queryParams.foodName" placeholder="请输入食材名称" clearable />
      </el-form-item>
      <el-form-item label="食材类别" prop="foodType">
         <el-select v-model="queryParams.foodType" placeholder="请选择类别" clearable>
          <el-option
            v-for="dict in dict.type.food_category"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.food_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:foodInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:foodInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange" border>
      <el-table-column label="序号" type="index" width="60" align="center" :index="index => index + 1" />
      <el-table-column label="食材名称" align="center" prop="foodName" />
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
      <el-table-column label="参考单价" align="center" prop="price" />
      <el-table-column label="保质期(天)" align="center" prop="expireDays" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.food_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:foodInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:foodInfo:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 新增/编辑弹窗 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="食材名称" prop="foodName">
          <el-input v-model="form.foodName" placeholder="请输入食材名称" />
        </el-form-item>
        <el-form-item label="食材类别" prop="foodType">
          <el-select v-model="form.foodType" placeholder="请选择类别" clearable>
            <el-option
              v-for="dict in dict.type.food_category"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="计量单位" prop="unit">
          <el-select v-model="form.unit" placeholder="请选择计量单位" clearable>
            <el-option
              v-for="dict in dict.type.food_unit"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="参考单价" prop="price">
          <el-input v-model="form.price" placeholder="请输入参考单价" />
        </el-form-item>
        <el-form-item label="保质期(天)" prop="expireDays">
          <el-input v-model="form.expireDays" placeholder="请输入保质期天数" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="0">正常</el-radio>
            <el-radio label="1">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" v-model="form.remark" rows="3" placeholder="备注信息" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="open = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFoodInfo, getFoodInfo, delFoodInfo, addFoodInfo, updateFoodInfo } from "@/api/system/foodInfo"

export default {
  name: "Info",
  dicts: ['food_category', 'food_unit', 'food_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 食材基础信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        foodName: null,
        foodCode: null,
        foodType: null,
        spec: null,
        unit: null,
        expireDays: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        foodName: [
          { required: true, message: "食材名称不能为空", trigger: "blur" }
        ],
        foodType: [
          { required: true, message: "食材类型不能为空", trigger: "change" }
        ],
        unit: [
          { required: true, message: "计量单位不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询食材基础信息列表 */
    getList() {
      this.loading = true
      listFoodInfo(this.queryParams).then(response => {
        this.infoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        foodId: null,
        foodName: null,
        foodCode: null,
        foodType: null,
        spec: null,
        unit: null,
        expireDays: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getFoodList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.foodId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加食材基础信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const foodId = row.foodId || this.ids
      getFoodInfo(foodId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改食材基础信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.foodId != null) {
            updateFoodInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addFoodInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const foodIds = row.foodId || this.ids
      this.$modal.confirm('是否确认删除食材基础信息编号为"' + foodIds + '"的数据项？').then(function() {
        return delFoodInfo(foodIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
