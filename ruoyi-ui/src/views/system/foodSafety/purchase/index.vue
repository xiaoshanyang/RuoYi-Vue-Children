<template>
  <div class="app-container">

    <!-- 查询条件 -->
    <el-form :model="queryParams" size="small" inline label-width="80px">
      <el-form-item label="采购单号">
        <el-input v-model="queryParams.purchaseNo" clearable placeholder="请输入" />
      </el-form-item>
      <el-form-item label="供应商">
        <el-input v-model="queryParams.supplierName" clearable placeholder="请输入" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="queryParams.status" clearable placeholder="请选择">
          <el-option v-for="d in dict.type.purchase_status"
            :key="d.value" :label="d.label" :value="d.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getList">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col>
        <el-button type="primary" icon="el-icon-plus" @click="handleAdd" v-hasPermi="['system:purchase:add']">
          新增采购单
        </el-button>
      </el-col>
    </el-row>

    <!-- 主单列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="序号" type="index" width="60" align="center" />
      <el-table-column label="采购单号" prop="purchaseNo" align="center" />
      <el-table-column label="供应商" prop="supplierName" align="center" />
      <el-table-column label="总金额" align="center">
        <template slot-scope="scope">{{ scope.row.totalAmount }}</template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.purchase_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="创建人" prop="createBy" align="center" />
      <el-table-column label="创建时间" prop="createTime" align="center" width="180" />
      <el-table-column label="操作" align="center" width="260">
        <template slot-scope="scope">
          <el-button size="mini" text icon="el-icon-view" @click="handleView(scope.row)">
            查看明细
          </el-button>
          <el-button size="mini" text icon="el-icon-edit"
            v-if="scope.row.status === '0'"
            @click="handleEdit(scope.row)">
            修改
          </el-button>
          <el-button size="mini" text icon="el-icon-check"
            v-hasPermi="['system:purchase:audit']"
            v-if="['1'].includes(scope.row.status)"
            @click="handleAudit(scope.row)">
            审核
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <!-- 新增/编辑弹窗：主单信息 + 明细表格 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" label-width="100px" :rules="rules">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="采购单号" prop="purchaseNo">
              <el-input v-model="form.purchaseNo" readonly placeholder="自动生成" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="供应商" prop="supplierId">
              <div @click="handleOpenSupplier" style="cursor: pointer;">
                <el-input 
                  v-model="form.supplierName" 
                  readonly 
                  placeholder="点击选择"
                />
              </div>
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider>采购明细</el-divider>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleOpenFood">
          添加食材
        </el-button>

        <el-table :data="items" border style="width:100%;margin-top:10px">
          <el-table-column label="食材名称" prop="foodName" align="center" />
          <el-table-column label="类别" align="center">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.food_category" :value="scope.row.foodType" />
            </template>
          </el-table-column>
          <el-table-column label="单位" align="center">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.food_unit" :value="scope.row.unit" />
            </template>
          </el-table-column>
          <el-table-column label="单价" prop="price" align="center" />
          <el-table-column label="数量" align="center" width="200">
            <template slot-scope="scope">
              <el-input-number style="width:150px" v-model="scope.row.qty" :min="0.01" @change="computeItem(scope.row)" />
            </template>
          </el-table-column>
          <el-table-column label="小计" prop="subTotal" align="center" />
          <el-table-column label="操作" align="center" width="100">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-delete" @click="delItem(scope.$index)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <div style="text-align:right;margin-top:15px;font-weight:bold">
          合计金额：{{ totalAmount }} 元
        </div>
      </el-form>
      <div slot="footer">
        <el-button @click="open=false">取消</el-button>
        <el-button type="primary" @click="submitForm('draft')">保存</el-button>
        <el-button type="primary" @click="submitForm('submit')">保存并提交</el-button>
      </div>
    </el-dialog>

    <!-- 供应商选择弹窗 -->
    <el-dialog title="选择供应商" :visible.sync="openSupplierDialog" width="700px" append-to-body>
      <el-input v-model="supplierQuery" placeholder="搜索名称" style="margin-bottom:10px" />
      <el-table :data="supplierList" border height="300" @row-click="selectSupplier">
        <el-table-column label="名称" prop="supplierName" />
        <el-table-column label="联系人" prop="contact" />
        <el-table-column label="电话" prop="phone" />
        <el-table-column label="状态">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.supplier_status" :value="scope.row.status" />
          </template>
        </el-table-column>
      </el-table>
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
        <el-table-column label="单价" prop="price" />
      </el-table>
    </el-dialog>

    <!-- 审核弹窗 -->
    <el-dialog title="采购单审核" :visible.sync="openAudit" width="500px" append-to-body>
      <el-form :model="auditForm" label-width="100px">
        <el-form-item label="审核结果">
          <el-radio-group v-model="auditForm.result">
            <el-radio label="2">通过</el-radio>
            <el-radio label="3">驳回</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核意见">
          <el-input type="textarea" v-model="auditForm.remark" rows="3" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="openAudit=false">取消</el-button>
        <el-button type="primary" @click="submitAudit">提交</el-button>
      </div>
    </el-dialog>

    <!-- 查看采购单详情弹窗 -->
    <el-dialog
      title="采购单详情"
      :visible.sync="viewOpen"
      width="900px"
      append-to-body
    >
      <el-form :model="viewForm" label-width="120px" size="small">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="采购单号">
              <span>{{ viewForm.purchaseNo }}</span>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="供应商">
              <span>{{ viewForm.supplierName }}</span>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="总金额">
          <span>{{ viewForm.totalAmount }} 元</span>
        </el-form-item>
        <el-form-item label="状态">
          <dict-tag :options="dict.type.purchase_status" :value="viewForm.status" />
        </el-form-item>
        <el-form-item label="创建人">
          <span>{{ viewForm.createBy }}</span>
        </el-form-item>
        <el-form-item label="创建时间">
          <span>{{ viewForm.createTime }}</span>
        </el-form-item>
      </el-form>

      <el-divider>采购明细</el-divider>
      <el-table :data="viewItems" border>
        <el-table-column label="食材名称" prop="foodName" align="center" />
        <el-table-column label="类别" align="center">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.food_category" :value="scope.row.foodType" />
          </template>
        </el-table-column>
        <el-table-column label="单位" align="center">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.food_unit" :value="scope.row.unit" />
          </template>
        </el-table-column>
        <el-table-column label="单价" prop="price" align="center" />
        <el-table-column label="数量" prop="qty" align="center" />
        <el-table-column label="小计" prop="subTotal" align="center" />
      </el-table>

      <div slot="footer">
        <el-button @click="viewOpen = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listPurchase, getPurchase, addPurchase, updatePurchase, auditPurchase} from '@/api/system/purchase'
import { listSupplier } from '@/api/system/supplier'
import { listFoodInfo } from '@/api/system/foodInfo'

export default {
  name: "Purchase",
  dicts: [
    'purchase_status',
    'supplier_status',
    'food_category',
    'food_unit'
  ],
  data() {
    return {
      loading: false,
      list: [],
      total: 0,
      queryParams: { pageNum:1, pageSize:10, purchaseNo: null, supplierName: null, status: null },

      open: false,
      title: '',
      form: { purchaseNo: '', supplierId: null, supplierName: '' },
      items: [],
      totalAmount: 0,

      openSupplierDialog: false,
      supplierList: [],
      supplierQuery: '',

      openFoodDialog: false,
      foodList: [],
      foodQuery: '',

      openAudit: false,
      auditForm: { purchaseId: null, result: '1', remark: '' },

       // 查看详情弹窗
      viewOpen: false,
      viewForm: {},
      viewItems: []
    }
  },
  created() {
    this.getList()
  },
  computed: {
    rules() {
      return {
        supplierId: [{ required: true, message: '请选择供应商', trigger: 'blur' }]
      }
    }
  },
  methods: {
    async getList() {
      this.loading = true
      const res = await listPurchase(this.queryParams)
      this.list = res.rows
      this.total = res.total
      this.loading = false
    },
    resetQuery() {
      this.queryParams = { pageNum:1, pageSize:10, purchaseNo: null, supplierName: null, status: null }
      this.getList()
    },

    // 新增
    handleAdd() {
      this.open = true
      this.title = '新增采购单'
      this.form = { purchaseNo: `CG${new Date().getTime()}`, supplierId: null, supplierName: '' }
      this.items = []
      this.totalAmount = 0
    },

    // 编辑
    async handleEdit(row) {
      this.open = true
      this.title = '编辑采购单'
      const res = await getPurchase(row.purchaseId)
      this.form = res.data
      this.items = res.data.items || []
      this.totalAmount = this.form.totalAmount || 0
    },

    // 加载供应商
    async handleOpenSupplier() {
      console.log('加载供应商列表')
      // 先加载数据
      const res = await listSupplier({ status: '1' })
      this.supplierList = res.rows

      // 再打开弹窗
      this.openSupplierDialog = true
    },
    // 选择供应商
    selectSupplier(row) {
      this.form.supplierId = row.supplierId
      this.form.supplierName = row.supplierName
      this.openSupplierDialog = false
    },

    // 加载食材
    async handleOpenFood() {
      // 先加载数据
      const res = await listFoodInfo({ status: '0' })
      this.foodList = res.rows

      // 再打开弹窗
      this.openFoodDialog = true
    },
    // 选择食材
    selectFood(row) {
      const exist = this.items.some(i => i.foodId === row.foodId)
      if (exist) {
        this.$modal.msgWarning('已添加该食材')
        return
      }
      this.items.push({
        foodId: row.foodId,
        foodName: row.foodName,
        foodType: row.foodType,
        unit: row.unit,
        price: parseFloat(row.price) || 0,
        qty: 1,
        subTotal: parseFloat(row.price) || 0
      })
      this.computeTotal()
      this.openFoodDialog = false
    },

    // 计算单行小计
    computeItem(row) {
      row.subTotal = (row.price * row.qty).toFixed(2)
      this.computeTotal()
    },
    // 计算总金额
    computeTotal() {
      this.totalAmount = this.items.reduce((t, i) => t + parseFloat(i.subTotal), 0).toFixed(2)
    },
    // 删除明细行
    delItem(index) {
      this.items.splice(index, 1)
      this.computeTotal()
    },

    // 提交保存
    async submitForm(type) {
      await this.$refs.form.validate()
      // ====================== 关键 ======================
      if (type === 'draft') {
        this.form.status = '0'; // 草稿
      } else if (type === 'submit') {
        this.form.status = '1'; // 待审核
      }

      const data = {
        ...this.form,
        totalAmount: this.totalAmount,
        items: this.items
      }
      if (this.form.purchaseId) {
        await updatePurchase(data)
        this.$modal.msgSuccess('修改成功')
      } else {
        await addPurchase(data)
        this.$modal.msgSuccess('创建成功')
      }
      this.open = false
      this.getList()
    },

    // 审核
    handleAudit(row) {
      this.openAudit = true
      this.auditForm = { purchaseId: row.purchaseId, result: '1', remark: '' }
    },
    async submitAudit() {
      await auditPurchase(this.auditForm)
      this.$modal.msgSuccess('审核成功')
      this.openAudit = false
      this.getList()
    },
    // 查看采购单详情
    async handleView(row) {
      const res = await getPurchase(row.purchaseId)
      this.viewForm = res.data
      this.viewItems = res.data.items || []
      this.viewOpen = true
    },
  }
}
</script>