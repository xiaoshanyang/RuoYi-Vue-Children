<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form :inline="true" :model="query" class="form-search">
      <el-form-item label="活动名称">
        <el-input v-model="query.tempName" placeholder="请输入活动名称" />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList">查询</el-button>
      <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
    </el-form>

    <!-- 操作按钮 -->
    <el-row class="mb8">
      <el-col>
        <el-button type="success" icon="el-icon-plus" @click="handleAdd">新增活动模板</el-button>
      </el-col>
    </el-row>

    <!-- 表格 -->
    <el-table :data="list" border>
      <el-table-column label="活动名称" prop="tempName" />
      <el-table-column label="活动内容" prop="tempContent" />
      <el-table-column label="安全注意事项" prop="safeNotice" />
      <el-table-column label="创建时间" prop="createTime" width="180" />
      <el-table-column label="操作" width="120">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
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

    <!-- 新增/编辑弹窗 -->
    <el-dialog title="活动模板" :visible.sync="open" width="600px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">
        <el-form-item label="活动名称" prop="tempName">
          <el-input v-model="form.tempName" placeholder="请填写活动名称" />
        </el-form-item>
        <el-form-item label="活动内容">
          <el-input v-model="form.tempContent" type="textarea" rows="3" placeholder="活动内容说明" />
        </el-form-item>
        <el-form-item label="安全注意事项">
          <el-input v-model="form.safeNotice" type="textarea" rows="3" placeholder="安全提醒" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="open = false">取消</el-button>
        <el-button type="primary" @click="submit">确定保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTemplate, getTemplate, addTemplate, updateTemplate } from "@/api/system/activity";

export default {
  name: "ActivityTemplate",
  data() {
    return {
      query: { pageNum: 1, pageSize: 10, tempName: "" },
      list: [],
      total: 0,
      open: false,
      form: {},
      rules: {
        tempName: [{ required: true, message: "活动名称不能为空", trigger: "blur" }]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    async getList() {
      const res = await listTemplate(this.query);
      this.list = res.rows;
      this.total = res.total;
    },
    resetQuery() {
      this.query = { pageNum: 1, pageSize: 10, tempName: "" };
      this.getList();
    },
    handleAdd() {
      this.open = true;
      this.form = {};
    },
    async handleEdit(row) {
      const res = await getTemplate(row.templateId);
      this.form = res.data;
      this.open = true;
    },
    async submit() {
      await this.$refs.formRef.validate();
      this.form.templateId
        ? await updateTemplate(this.form)
        : await addTemplate(this.form);
      this.$message.success("操作成功");
      this.open = false;
      this.getList();
    }
  }
};
</script>