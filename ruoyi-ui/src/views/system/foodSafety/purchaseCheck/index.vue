<template>
  <div class="app-container">

    <!-- 查询区域 -->
    <el-form :model="queryParams" size="small" inline label-width="80px">
      <el-form-item label="采购单号">
        <el-input v-model="queryParams.purchaseNo" clearable placeholder="请输入" />
      </el-form-item>
      <el-form-item label="供应商">
        <el-input v-model="queryParams.supplierName" clearable placeholder="请输入" />
      </el-form-item>
      <el-form-item label="验收结果">
        <el-select v-model="queryParams.checkResult" clearable placeholder="请选择">
          <el-option
            v-for="d in dict.type.purchase_check_result"
            :key="d.value"
            :label="d.label"
            :value="d.value"
          />
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
        <el-button
          type="primary"
          icon="el-icon-plus"
          @click="openSelectPurchaseDialog"
          v-hasPermi="['system:purchaseCheck:add']"
        >
          新增验收单
        </el-button>
      </el-col>
    </el-row>

    <!-- 验收单列表 -->
    <el-table v-loading="loading" :data="list" border>
      <el-table-column label="序号" type="index" width="60" align="center" />
      <el-table-column label="采购单号" prop="purchaseNo" align="center" />
      <el-table-column label="供应商" prop="supplierName" align="center" />
      <el-table-column label="验收结果" align="center" prop="checkResult">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.purchase_check_result" :value="scope.row.checkResult" />
        </template>
      </el-table-column>
      <el-table-column label="验收人" prop="checkBy" align="center" />
      <el-table-column label="验收时间" prop="checkTime" align="center" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="120">
        <template slot-scope="scope">
          <el-button size="mini" text icon="el-icon-view" @click="handleView(scope.row)">
            查看
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 选择采购单弹窗（只能选已审核通过的） -->
    <el-dialog title="选择采购单" :visible.sync="selectPurchaseDialog" width="900px" append-to-body>
      <el-table :data="purchaseList" border height="350" @row-click="onSelectPurchase">
        <el-table-column label="采购单号" prop="purchaseNo" />
        <el-table-column label="供应商" prop="supplierName" />
        <el-table-column label="总金额" prop="totalAmount" />
        <el-table-column label="状态" align="center">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.purchase_status" :value="scope.row.status" />
          </template>
        </el-table-column>
        <el-table-column label="创建时间" prop="createTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime) }}</span>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 验收表单弹窗 -->
    <el-dialog title="食材验收" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" label-width="120px" size="small">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="采购单号">
              <el-input v-model="form.purchaseNo" readonly />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="供应商">
              <el-input v-model="form.supplierName" readonly />
            </el-form-item>
          </el-col>
        </el-row>

        <el-divider>验收明细</el-divider>

        <el-table :data="items" border style="width:100%">
          <el-table-column label="食材名称" prop="foodName" align="center" />
          <el-table-column label="采购数量" prop="planQty" align="center" />
          <el-table-column label="实收数量" align="center" width="200">
            <template slot-scope="scope">
              <el-input-number
                v-model="scope.row.realQty"
                :min="0"
                :precision="2"
                style="width:150px" 
                @change="calcItemResult(scope.row)"
              />
            </template>
          </el-table-column>
          <el-table-column label="保质期" align="center">
            <template slot-scope="scope">
              <el-radio-group v-model="scope.row.expireCheck" @change="calcItemResult(scope.row)" class="radio-vertical">
                <el-radio label="1">合格</el-radio>
                <el-radio label="2">不合格</el-radio>
              </el-radio-group>
            </template>
          </el-table-column>
          <el-table-column label="农残检测" align="center">
            <template slot-scope="scope">
              <el-radio-group v-model="scope.row.pesticideCheck" @change="calcItemResult(scope.row)" class="radio-vertical">
                <el-radio label="1">合格</el-radio>
                <el-radio label="2">不合格</el-radio>
              </el-radio-group>
            </template>
          </el-table-column>
          <el-table-column label="外观新鲜度" align="center">
            <template slot-scope="scope">
              <el-radio-group v-model="scope.row.appearanceCheck" @change="calcItemResult(scope.row)" class="radio-vertical">
                <el-radio label="1">合格</el-radio>
                <el-radio label="2">不合格</el-radio>
              </el-radio-group>
            </template>
          </el-table-column>
          <el-table-column label="明细结果" align="center">
            <template slot-scope="scope">
              <el-tag :type="scope.row.itemResult === '1' ? 'success' : 'danger'">
                {{ scope.row.itemResult === '1' ? '合格' : (scope.row.itemResult === '2' ? '不合格' : '') }}
              </el-tag>
            </template>
          </el-table-column>
        </el-table>

        <el-form-item label="验收总评" label-width="120px" style="margin-top:20px">
          <el-radio-group v-model="form.checkResult">
            <el-radio label="1">验收完成</el-radio>
            <el-radio label="2">保存（未完成）</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="验收意见">
          <el-input type="textarea" v-model="form.checkRemark" rows="3" placeholder="请填写验收情况" />
        </el-form-item>
      </el-form>

      <div slot="footer">
        <el-button @click="open = false">取消</el-button>
        <el-button type="primary" @click="submitCheck">提交验收</el-button>
      </div>
    </el-dialog>

    <!-- 查看详情弹窗 -->
    <el-dialog title="验收详情" :visible.sync="viewOpen" width="1000px" append-to-body>
      <el-form label-width="120px" :model="viewForm" size="small">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="采购单号"><span>{{ viewForm.purchaseNo }}</span></el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="供应商"><span>{{ viewForm.supplierName }}</span></el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="验收结果">
          <dict-tag :options="dict.type.purchase_check_result" :value="viewForm.checkResult" />
        </el-form-item>
        <el-form-item label="验收意见"><span>{{ viewForm.checkRemark }}</span></el-form-item>
        <el-form-item label="验收人"><span>{{ viewForm.checkBy }}</span></el-form-item>
        <el-form-item label="验收时间"><span>{{ parseTime(viewForm.checkTime) }}</span></el-form-item>
      </el-form>

      <el-divider>验收明细</el-divider>
      <el-table :data="viewItems" border>
        <el-table-column label="食材名称" prop="foodName" />
        <el-table-column label="采购数量" prop="planQty" />
        <el-table-column label="实收数量" prop="realQty" />
        <el-table-column label="保质期" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.expireCheck === '1' ? 'success' : 'danger'">
              {{ scope.row.expireCheck === '1' ? '合格' : (scope.row.expireCheck === '2' ? '不合格' : '') }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="农残检测" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.pesticideCheck === '1' ? 'success' : 'danger'">
              {{ scope.row.pesticideCheck === '1' ? '合格' : (scope.row.pesticideCheck === '2' ? '不合格' : '') }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="外观新鲜度" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.appearanceCheck === '1' ? 'success' : 'danger'">
              {{ scope.row.appearanceCheck === '1' ? '合格' : (scope.row.appearanceCheck === '2' ? '不合格' : '') }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="明细结果" align="center">
          <template slot-scope="scope">
            <el-tag :type="scope.row.itemResult === '1' ? 'success' : 'danger'">
              {{ scope.row.itemResult === '1' ? '合格' : (scope.row.itemResult === '2' ? '不合格' : '') }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>

      <div slot="footer">
        <el-button @click="viewOpen = false">关闭</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {
  listPurchaseCheck,
  getPurchaseCheck,
  addPurchaseCheck
} from '@/api/system/purchaseCheck'

import { listPurchase, getPurchase } from '@/api/system/purchase'

export default {
  name: "PurchaseCheck",
  dicts: ['purchase_status', 'purchase_check_result'],
  data() {
    return {
      loading: false,
      list: [],
      total: 0,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        purchaseNo: null,
        supplierName: null,
        checkResult: null
      },

      // 选择采购单
      selectPurchaseDialog: false,
      purchaseList: [],

      // 验收表单
      open: false,
      form: {},
      items: [],

      // 详情
      viewOpen: false,
      viewForm: {},
      viewItems: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 查询列表
    async getList() {
      this.loading = true
      const res = await listPurchaseCheck(this.queryParams)
      this.list = res.rows
      this.total = res.total
      this.loading = false
    },
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        purchaseNo: null,
        supplierName: null,
        checkResult: null
      }
      this.getList()
    },

    // 打开选择采购单
    async openSelectPurchaseDialog() {
      this.selectPurchaseDialog = true
      // 只加载已审核通过的采购单
      const res = await listPurchase({ status: '2' })
      this.purchaseList = res.rows
    },

    // 选中采购单，自动构建验收单
    async onSelectPurchase(row) {
      this.selectPurchaseDialog = false
      this.open = true

      // 构造主单
      this.form = {
        purchaseId: row.purchaseId,
        purchaseNo: row.purchaseNo,
        supplierId: row.supplierId,
        supplierName: row.supplierName,
        checkResult: '1',
        checkRemark: ''
      }

      let res = await getPurchase(row.purchaseId)

      // 构造明细，默认全部合格
      this.items = res.data.items.map(i => ({
        foodId: i.foodId,
        foodName: i.foodName,
        planQty: i.qty,
        realQty: i.qty,
        expireCheck: '0',
        pesticideCheck: '0',
        appearanceCheck: '0',
        itemResult: '0'
      }))
    },

    // 自动计算单项结果
    calcItemResult(row) {
      if (
        row.expireCheck === '2' ||
        row.pesticideCheck === '2' ||
        row.appearanceCheck === '2'
      ) {
        row.itemResult = '2'
      } else {
        row.itemResult = '1'
      }
    },

    // 提交验收
    async submitCheck() {
      const data = {
        ...this.form,
        items: this.items
      }
      await addPurchaseCheck(data)
      this.$modal.msgSuccess("验收成功")
      this.open = false
      this.getList()
    },

    // 查看详情
    async handleView(row) {
      const res = await getPurchaseCheck(row.checkId)
      this.viewForm = res.data
      this.viewItems = res.data.items || []
      this.viewOpen = true
    }
  }
}
</script>

<style>
.radio-vertical {
  display: flex !important;
  flex-direction: column !important;
  align-items: center !important;
  gap: 6px !important; /* 上下间距 */
}
/* 强制每个 el-radio 占满一行 */
.radio-vertical .el-radio {
  display: block !important;
  margin: 2px 0 !important;
  line-height: 24px !important;
}
</style>