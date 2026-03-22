<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="幼儿姓名" prop="childName">
        <el-input
          v-model="queryParams.childName"
          placeholder="请输入幼儿姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级ID" prop="classId">
        <el-input
          v-model="queryParams.classId"
          placeholder="请输入班级ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别：0男 1女" prop="gender">
        <el-input
          v-model="queryParams.gender"
          placeholder="请输入性别：0男 1女"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出生日期" prop="birthday">
        <el-date-picker clearable
          v-model="queryParams.birthday"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出生日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="家长姓名" prop="parentName">
        <el-input
          v-model="queryParams.parentName"
          placeholder="请输入家长姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="家长联系电话" prop="parentPhone">
        <el-input
          v-model="queryParams.parentPhone"
          placeholder="请输入家长联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入园时间" prop="enrollTime">
        <el-date-picker clearable
          v-model="queryParams.enrollTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择入园时间">
        </el-date-picker>
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
          v-hasPermi="['system:child:add']"
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
          v-hasPermi="['system:child:edit']"
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
          v-hasPermi="['system:child:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:child:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="childList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="幼儿姓名" align="center" prop="childName" />
      <el-table-column label="班级ID" align="center" prop="classId" />
      <el-table-column label="性别：0男 1女" align="center" prop="gender" />
      <el-table-column label="出生日期" align="center" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="过敏信息" align="center" prop="allergyInfo" />
      <el-table-column label="家长姓名" align="center" prop="parentName" />
      <el-table-column label="家长联系电话" align="center" prop="parentPhone" />
      <el-table-column label="入园时间" align="center" prop="enrollTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enrollTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态：0正常 1请假 2退学" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:child:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:child:remove']"
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

    <!-- 添加或修改幼儿信息（无冗余班级名称）对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="幼儿姓名" prop="childName">
          <el-input v-model="form.childName" placeholder="请输入幼儿姓名" />
        </el-form-item>
        <el-form-item label="班级ID" prop="classId">
          <el-input v-model="form.classId" placeholder="请输入班级ID" />
        </el-form-item>
        <el-form-item label="性别：0男 1女" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别：0男 1女" />
        </el-form-item>
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker clearable
            v-model="form.birthday"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="过敏信息" prop="allergyInfo">
          <el-input v-model="form.allergyInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="家长联系电话" prop="parentPhone">
          <el-input v-model="form.parentPhone" placeholder="请输入家长联系电话" />
        </el-form-item>
        <el-form-item label="入园时间" prop="enrollTime">
          <el-date-picker clearable
            v-model="form.enrollTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入园时间">
          </el-date-picker>
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
import { listChild, getChild, delChild, addChild, updateChild } from "@/api/system/child"

export default {
  name: "Child",
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
      // 幼儿信息（无冗余班级名称）表格数据
      childList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        childName: null,
        classId: null,
        gender: null,
        birthday: null,
        allergyInfo: null,
        parentName: null,
        parentPhone: null,
        enrollTime: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        childName: [
          { required: true, message: "幼儿姓名不能为空", trigger: "blur" }
        ],
        classId: [
          { required: true, message: "班级ID不能为空", trigger: "blur" }
        ],
        parentPhone: [
          { required: true, message: "家长联系电话不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询幼儿信息（无冗余班级名称）列表 */
    getList() {
      this.loading = true
      listChild(this.queryParams).then(response => {
        this.childList = response.rows
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
        childName: null,
        classId: null,
        gender: null,
        birthday: null,
        allergyInfo: null,
        parentName: null,
        parentPhone: null,
        enrollTime: null,
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
      this.title = "添加幼儿信息（无冗余班级名称）"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getChild(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改幼儿信息（无冗余班级名称）"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateChild(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addChild(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除幼儿信息（无冗余班级名称）编号为"' + ids + '"的数据项？').then(function() {
        return delChild(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/child/export', {
        ...this.queryParams
      }, `child_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
