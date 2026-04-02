<template>
  <div class="app-container">
    <el-form :inline="true" :model="query" class="form-search">
      <el-form-item label="活动名称">
        <el-input v-model="query.activityName" placeholder="活动名称"/>
      </el-form-item>
      <el-form-item label="班级">
        <el-select v-model="query.classId" placeholder="选择班级" clearable>
          <el-option
            v-for="cls in classOptions"
            :key="cls.classId"
            :label="cls.className"
            :value="cls.classId"
          />
        </el-select>
      </el-form-item>
      <el-button type="primary" @click="getList">查询</el-button>
      <el-button @click="resetQuery">重置</el-button>
    </el-form>

    <el-row class="mb8">
      <el-col>
        <el-button type="success" icon="el-icon-plus" @click="handleAdd">新增活动</el-button>
      </el-col>
    </el-row>

    <el-table :data="list" border>
      <el-table-column label="活动名称" prop="activityName"/>
      <el-table-column label="班级" prop="classFullName"/>
      <el-table-column label="年级" prop="gradeName"/>
      <el-table-column label="活动时间" prop="activityTime" width="180">
         <template  slot-scope="scope">
          <span>{{ parseTime(scope.row.activityTime) }} </span>
        </template>
      </el-table-column>
      <el-table-column label="地点" prop="place" width="120"/>
      <el-table-column label="负责人" prop="chargeBy"/>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="warning" size="mini" @click="goMonitor(scope.row)">监控登记</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="getList"
      @current-change="getList"
      :current-page="query.pageNum"
      :page-sizes="[10,20,50]"
      :page-size="query.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    />

    <!-- 新增/编辑弹窗 -->
    <el-dialog title="活动信息" :visible.sync="open" width="600px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="活动班级" prop="classId">
          <el-select v-model="form.classId" placeholder="选择班级" @change="handleClassChange">
            <el-option
              v-for="cls in classOptions"
              :key="cls.classId"
              :label="cls.className"
              :value="cls.classId"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="活动名称" prop="activityName">
          <el-input v-model="form.activityName"/>
        </el-form-item>

        <el-form-item label="活动时间" prop="activityTime">
          <el-date-picker
            v-model="form.activityTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
          />
        </el-form-item>

        <el-form-item label="活动地点">
          <el-input v-model="form.place"/>
        </el-form-item>

        <el-form-item label="负责人">
          <el-input v-model="form.chargeBy"/>
        </el-form-item>

        <el-form-item label="活动内容">
          <el-input v-model="form.content" type="textarea" rows="3"/>
        </el-form-item>
      </el-form>

      <div slot="footer">
        <el-button @click="open=false">取消</el-button>
        <el-button type="primary" @click="submit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listActivity, getActivity, addActivity, updateActivity, classList } from "@/api/system/activity";

export default {
  name: "Activity",
  data() {
    return {
      query: { pageNum: 1, pageSize: 10, activityName: "", classId: null },
      list: [],
      total: 0,
      classOptions: [],
      open: false,
      form: {},
      rules: {
        classId: [{ required: true, message: "请选择班级" }],
        activityName: [{ required: true, message: "请填写活动名称" }],
        activityTime: [{ required: true, message: "请选择活动时间" }]
      }
    };
  },
  created() {
    this.loadClassOptions();
    this.getList();
  },
  methods: {
    async loadClassOptions() {
      const res = await classList();
      this.classOptions = res.data;
    },
    async getList() {
      const res = await listActivity(this.query);
      this.list = res.rows;
      this.total = res.total;
    },
    resetQuery() {
      this.query = { pageNum: 1, pageSize: 10, activityName: "", classId: null };
      this.getList();
    },
    handleAdd() {
      this.open = true;
      this.form = {activityTime: new Date()};
    },
    async handleEdit(row) {
      const res = await getActivity(row.activityId);
      this.form = res.data;
      this.open = true;
    },
    // 选择班级时自动快照
    handleClassChange(classId) {
      const cls = this.classOptions.find(c => c.classId === classId);
      if (!cls) return;
      this.form.classCode = cls.classCode;
      this.form.classFullName = cls.className;
      this.form.gradeName = cls.grade === 1 ? "小班" : cls.grade === 2 ? "中班" : "大班";
    },
    async submit() {
      await this.$refs.formRef.validate();
      this.form.activityTime = new Date(this.form.activityTime)
      this.form.activityId
        ? await updateActivity(this.form)
        : await addActivity(this.form);
      this.$message.success("操作成功");
      this.open = false;
      this.getList();
    },
    // 去监控登记
    goMonitor(row) {
      this.$router.push({
        path: "/activity/monitor",
        query: { activityId: row.activityId }
      });
    }
  }
};
</script>