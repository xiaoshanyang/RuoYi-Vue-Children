<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
      <el-form-item label="供应商名称" prop="supplierName">
        <el-input
          v-model="queryParams.supplierName"
          placeholder="请输入供应商名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="contact">
        <el-input
          v-model="queryParams.contact"
          placeholder="请输入联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="状态" clearable>
          <el-option
            v-for="dict in dict.type.supplier_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核类型" prop="auditType">
        <el-select v-model="queryParams.auditType" placeholder="审核类型" clearable>
          <el-option
            v-for="dict in dict.type.supplier_audit_type"
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
          v-hasPermi="['system:supplier:add']"
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
          v-hasPermi="['system:supplier:edit']"
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
          v-hasPermi="['system:supplier:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:supplier:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="supplierList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" v-if="hasEditOrRemovePerm"/>
      <el-table-column label="序号" type="index" width="80"  align="center" :index="index => index + 1"/>
      <el-table-column label="供应商名称" align="center" prop="supplierName" />
      <el-table-column label="联系人" align="center" prop="contact" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="营业执照号" align="center" prop="businessLicense" />
      <el-table-column label="食品经营许可证" align="center" prop="foodLicense" />
      <el-table-column label="状态" align="center" prop="status" >
         <template slot-scope="scope">
          <!--  0 = 待审核、1 = 正常、2 = 停用、3 = 驳回、4 = 待停用、5 = 待启用-->
          <dict-tag :options="dict.type.supplier_status" :value="scope.row.status" />
         </template>
      </el-table-column>
      <el-table-column label="审核类型" align="center" prop="auditType">
         <template slot-scope="scope">
          <!-- ：add-新增审核 disable-停用审核 enable-启用审核 -->
          <dict-tag :options="dict.type.supplier_audit_type" :value="scope.row.auditType" />
         </template>
      </el-table-column>

      <el-table-column label="操作描述" align="center" prop="operateDesc" />
      <el-table-column label="审核意见" align="center" prop="reason" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" width="260">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleView(scope.row)"
          >详情</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleEdit(scope.row)"
            v-hasPermi="['system:supplier:edit']"
            v-if="['0','3'].includes(scope.row.status)"
          >编辑</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:supplier:remove']"
            v-if="['0','3'].includes(scope.row.status)"
          >删除</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-close"
            @click="handleApplyDisable(scope.row)"
            v-hasPermi="['system:supplier:disable']"
            v-if="scope.row.status == '1'"
          >申请停用</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleApplyEnable(scope.row)"
            v-hasPermi="['system:supplier:enable']"
            v-if="scope.row.status == '2'"
          >申请启用</el-button>

          <!-- 园长：审核按钮 -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleAudit(scope.row)"
            v-hasPermi="['system:supplier:audit']"
            v-if="['0','4','5'].includes(scope.row.status)"
          >审核</el-button>
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

    <!-- 详情弹窗 -->
    <el-dialog title="供应商详情" :visible.sync="openView" width="600px" append-to-body>
      <el-form label-width="110px" :model="viewForm">
        <el-form-item label="供应商名称">
          <span>{{ viewForm.supplierName }}</span>
        </el-form-item>
        <el-form-item label="联系人">
          <span>{{ viewForm.contact }}</span>
        </el-form-item>
        <el-form-item label="联系电话">
          <span>{{ viewForm.phone }}</span>
        </el-form-item>
        <el-form-item label="营业执照号">
          <span>{{ viewForm.businessLicense }}</span>
        </el-form-item>
        <el-form-item label="食品经营许可证">
          <span>{{ viewForm.foodLicense }}</span>
        </el-form-item>
        <el-form-item label="地址">
          <span>{{ viewForm.address }}</span>
        </el-form-item>
        <el-form-item label="状态">
          <dict-tag :options="dict.type.supplier_status" :value="viewForm.status" />
        </el-form-item>
        <el-form-item label="审核类型">
          <dict-tag :options="dict.type.supplier_audit_type" :value="viewForm.auditType" />
        </el-form-item>
        <el-form-item label="操作描述">
          <span>{{ viewForm.operateDesc }}</span>
        </el-form-item>
        <el-form-item label="审核意见">
          <span>{{ viewForm.reason || '无' }}</span>
        </el-form-item>
        <el-form-item label="创建人">
          <span>{{ viewForm.createBy }}</span>
        </el-form-item>
        <el-form-item label="创建时间">
          <span>{{ viewForm.createTime }}</span>
        </el-form-item>
        <el-form-item label="审核人">
          <span>{{ viewForm.auditBy || '未审核' }}</span>
        </el-form-item>
        <el-form-item label="审核时间">
          <span>{{ viewForm.auditTime || '未审核' }}</span>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="openView = false">关闭</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改供应商信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="供应商名称" prop="supplierName">
          <el-input v-model="form.supplierName" placeholder="请输入供应商名称" />
        </el-form-item>
        <el-form-item label="营业执照号" prop="businessLicense">
          <el-input v-model="form.businessLicense" placeholder="请输入营业执照号" />
        </el-form-item>
        <el-form-item label="食品经营许可证" prop="foodLicense">
          <el-input v-model="form.foodLicense" placeholder="请输入食品经营许可证" />
        </el-form-item>
        <el-form-item label="联系人" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 申请停用/启用弹窗 -->
    <el-dialog title="操作申请" :visible.sync="openApply" width="500px" append-to-body>
      <el-form ref="applyForm" :model="applyForm" label-width="100px">
        <el-form-item :label="applyType === 'disable' ? '停用原因' : '启用原因'" prop="reason">
          <el-input type="textarea" v-model="applyForm.reason" placeholder="请输入原因" rows="3" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="openApply = false">取消</el-button>
        <el-button type="primary" @click="submitApply">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 审核弹窗 -->
    <el-dialog title="供应商审核" :visible.sync="openAudit" width="550px" append-to-body>
      <el-form ref="auditForm" :model="auditForm" label-width="100px">
        <el-form-item label="供应商名称">
          <span>{{ auditForm.supplierName }}</span>
        </el-form-item>
        <el-form-item label="审核类型">
          <dict-tag :options="dict.type.supplier_audit_type" :value="auditForm.auditType" />
        </el-form-item>
        <el-form-item label="操作描述">
          <span>{{ auditForm.operateDesc }}</span>
        </el-form-item>
        <el-form-item label="审核结果" prop="auditResult">
          <el-radio-group v-model="auditForm.auditResult">
            <el-radio label="1">通过</el-radio>
            <el-radio label="0">驳回</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核意见" prop="auditRemark">
          <el-input
            type="textarea"
            v-model="auditForm.auditRemark"
            placeholder="请填写审核意见"
            rows="3"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="openAudit = false">取消</el-button>
        <el-button type="primary" @click="submitAudit">提交审核</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listSupplier, getSupplier, delSupplier, addSupplier, updateSupplier, applyDisableSupplier,  applyEnableSupplier, auditSupplier  } from "@/api/system/supplier"

export default {
  name: "Supplier",
  dicts: ['supplier_status', 'supplier_audit_type'],
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
      // 供应商信息表格数据
      supplierList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        supplierName: null,
        supplierCode: null,
        contact: null,
        phone: null,
        address: null,
        businessLicense: null,
        foodLicense: null,
        auditType: null,
        operateDesc: null,
        status: null,
        reason: null,
        auditBy: null,
        auditTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        supplierName: [
          { required: true, message: "供应商名称不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态 0 = 待审核、1 = 正常、2 = 停用、3 = 驳回、4 = 待停用、5 = 待启用不能为空", trigger: "change" }
        ],
      },
      // 申请停用/启用
      openApply: false,
      applyType: "disable",
      applyForm: {
        supplierId: null,
        reason: null
      },
      // View详情
      openView: false,
      viewForm: {},
      // 审核
      openAudit: false,
      auditForm: {
        supplierId: null,
        supplierName: null,
        auditType: null,
        operateDesc: null,
        auditResult: "1",
        auditRemark: null
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询供应商信息列表 */
    getList() {
      this.loading = true
      listSupplier(this.queryParams).then(response => {
        this.supplierList = response.rows
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
        supplierId: null,
        supplierName: null,
        supplierCode: null,
        contact: null,
        phone: null,
        address: null,
        businessLicense: null,
        foodLicense: null,
        auditType: null,
        operateDesc: null,
        status: null,
        reason: null,
        createBy: null,
        createTime: null,
        auditBy: null,
        auditTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.supplierId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加供应商信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const supplierId = row.supplierId || this.ids
      getSupplier(supplierId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改供应商信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.supplierId != null) {
            updateSupplier(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addSupplier(this.form).then(response => {
              this.$modal.msgSuccess("新增成功，待审核")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const supplierIds = row.supplierId || this.ids
      this.$modal.confirm('是否确认删除供应商信息编号为"' + supplierIds + '"的数据项？').then(function() {
        return delSupplier(supplierIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/supplier/export', {
        ...this.queryParams
      }, `supplier_${new Date().getTime()}.xlsx`)
    },
    // 是否具有编辑或删除权限
    hasEditOrRemovePerm() {
      return this.hasPermi('system:supplier:edit') || this.hasPermi('system:supplier:remove');
    },
    // 申请停用
    handleApplyDisable(row) {
      this.openApply = true;
      this.applyType = "disable";
      this.applyForm = { supplierId: row.supplierId, reason: null };
    },
    // 申请启用
    handleApplyEnable(row) {
      this.openApply = true;
      this.applyType = "enable";
      this.applyForm = { supplierId: row.supplierId, reason: null };
    },
    // 提交停用/启用申请
    submitApply() {
      this.$refs["applyForm"].validate(valid => {
        if (valid) {
          let api = this.applyType === 'disable' ? applyDisableSupplier : applyEnableSupplier;
          api(this.applyForm).then(() => {
            this.$modal.msgSuccess("申请成功，待园长审核");
            this.openApply = false;
            this.getList();
          });
        }
      });
    },
    // 查看详情
    handleView(row) {
      this.openView = true;
      getSupplier(row.supplierId).then(res => {
        console.log(res.data);
        this.viewForm = res.data;
      });
    },
    // 打开审核弹窗
    handleAudit(row) {
      this.openAudit = true;
      getSupplier(row.supplierId).then(res => {
        this.auditForm = {
          supplierId: res.data.supplierId,
          supplierName: res.data.supplierName,
          auditType: res.data.auditType,
          operateDesc: res.data.operateDesc,
          auditResult: "1",
          auditRemark: ""
        };
      });
    },

    // 提交审核
    submitAudit() {
      this.$refs["auditForm"].validate(valid => {
        if (valid) {
          // 调用审核接口
          auditSupplier(this.auditForm).then(() => {
            this.$modal.msgSuccess("审核成功");
            this.openAudit = false;
            this.getList();
          });
        }
      });
    },
  }
}
</script>
