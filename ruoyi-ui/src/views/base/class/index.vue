<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="届数" prop="gradeYear">
        <el-input
          v-model="queryParams.gradeYear"
          placeholder="请输入届数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级编码" prop="classCode">
        <el-input
          v-model="queryParams.classCode"
          placeholder="请输入班级编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级全称" prop="className">
        <el-input
          v-model="queryParams.className"
          placeholder="请输入班级全称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班主任ID" prop="teacherId">
        <el-input
          v-model="queryParams.teacherId"
          placeholder="请输入班主任ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班主任姓名" prop="teacherName">
        <el-input
          v-model="queryParams.teacherName"
          placeholder="请输入班主任姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="幼儿人数" prop="studentCount">
        <el-input
          v-model="queryParams.studentCount"
          placeholder="请输入幼儿人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:class:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:class:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:class:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:class:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="届数" align="center" prop="gradeYear" />
      <el-table-column label="班级编码" align="center" prop="classCode" />
      <el-table-column label="班级全称" align="center" prop="className" />
      <el-table-column label="班级类型：小班/中班/大班" align="center" prop="classType" />
      <el-table-column label="班主任ID" align="center" prop="teacherId" />
      <el-table-column label="班主任姓名" align="center" prop="teacherName" />
      <el-table-column label="幼儿人数" align="center" prop="studentCount" />
      <el-table-column label="状态：0正常 1停用" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:class:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:class:remove']"
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

    <!-- 添加或修改班级信息（按届命名）对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="届数" prop="gradeYear">
          <el-input v-model="form.gradeYear" placeholder="请输入届数" />
        </el-form-item>
        <el-form-item label="班级编码" prop="classCode">
          <el-input v-model="form.classCode" placeholder="请输入班级编码" />
        </el-form-item>
        <el-form-item label="班级全称" prop="className">
          <el-input v-model="form.className" placeholder="请输入班级全称" />
        </el-form-item>
        <el-form-item label="班主任ID" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入班主任ID" />
        </el-form-item>
        <el-form-item label="班主任姓名" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入班主任姓名" />
        </el-form-item>
        <el-form-item label="幼儿人数" prop="studentCount">
          <el-input v-model="form.studentCount" placeholder="请输入幼儿人数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClass, getClass, delClass, addClass, updateClass } from "@/api/system/class"

export default {
  name: "Class",
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
      // 班级信息（按届命名）表格数据
      classList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        gradeYear: null,
        classCode: null,
        className: null,
        classType: null,
        teacherId: null,
        teacherName: null,
        studentCount: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        gradeYear: [
          { required: true, message: "届数不能为空", trigger: "blur" }
        ],
        classCode: [
          { required: true, message: "班级编码不能为空", trigger: "blur" }
        ],
        className: [
          { required: true, message: "班级全称不能为空", trigger: "blur" }
        ],
        classType: [
          { required: true, message: "班级类型：小班/中班/大班不能为空", trigger: "change" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询班级信息（按届命名）列表 */
    getList() {
      this.loading = true
      listClass(this.queryParams).then(response => {
        this.classList = response.rows
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
        id: null,
        gradeYear: null,
        classCode: null,
        className: null,
        classType: null,
        teacherId: null,
        teacherName: null,
        studentCount: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加班级信息（按届命名）"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getClass(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改班级信息（按届命名）"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateClass(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addClass(this.form).then(response => {
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
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除班级信息（按届命名）编号为"' + ids + '"的数据项？').then(function() {
        return delClass(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/class/export', {
        ...this.queryParams
      }, `class_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
