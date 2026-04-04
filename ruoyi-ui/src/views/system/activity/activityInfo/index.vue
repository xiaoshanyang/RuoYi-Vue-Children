<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-form :inline="true" :model="query" class="form-search">
      <el-form-item label="活动名称">
        <el-input v-model="query.activityName" placeholder="活动名称" />
      </el-form-item>
      <el-form-item label="活动时间">
        <el-date-picker
          v-model="dateRange"
          type="daterange"
          value-format="yyyy-MM-dd"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList">查询</el-button>
      <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
    </el-form>

    <!-- 操作栏 -->
    <el-row class="mb8">
      <el-col>
        <el-button type="success" icon="el-icon-plus" @click="handleAdd">新增活动</el-button>
      </el-col>
    </el-row>

    <!-- 活动列表 -->
    <el-table :data="list" border>
      <el-table-column label="活动名称" prop="activityName" />
      <el-table-column label="活动内容" prop="content" />
      <el-table-column label="班级" prop="classFullName" width="120" />
      <el-table-column label="年级" prop="gradeName"  width="120"/>
      <el-table-column label="活动状态" width="110">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isClosed === 1" type="danger">已取消</el-tag>
          <el-tag v-else :type="getStatusTag(scope.row)">
            {{ getStatusText(scope.row) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" prop="beginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beginTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" prop="endTime" width="180" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="地点" prop="place" />
      <el-table-column label="负责人" prop="chargeBy"  width="120"/>
      <el-table-column label="操作">
         <template slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            @click="goMonitor(scope.row)"
            :disabled="isBeforeStart(scope.row)"
          >
            {{ scope.row.hasMonitor ? '修改监控' : '监控登记' }}
          </el-button>

          <!-- 编辑：未开始 & 未关闭 才能编辑 -->
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)"
            :disabled="!isBeforeStart(scope.row) || scope.row.isClosed === 1">
            编辑
          </el-button>

          <!-- 删除：未开始 才能删 -->
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)"
            :disabled="!isBeforeStart(scope.row)">
            删除
          </el-button>

          <el-button type="info" size="mini" @click="handleClose(scope.row)"
            :disabled="scope.row.isClosed === 1">
            取消活动
          </el-button>
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
    <el-dialog title="班级活动安排" :visible.sync="open" width="700px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">
        <el-form-item label="活动班级">
          <el-select v-model="form.classId" :disabled="isGetMyClass" @change="loadClassTeacher(form.classId)">
            <el-option
              v-for="cls in classOptions"
              :key="cls.classId"
              :label="cls.className"
              :value="cls.classId"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="活动模板" prop="templateId">
          <el-select v-model="form.templateId" placeholder="请选择活动模板" @change="selectTemplate">
            <el-option
              v-for="tmp in templateOptions"
              :key="tmp.templateId"
              :label="tmp.tempName"
              :value="tmp.templateId"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="活动名称" prop="activityName">
          <el-input v-model="form.activityName" />
        </el-form-item>

        <el-form-item label="活动时间">
          <el-date-picker v-model="form.beginTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" style="width:240px"/>
          <span style="margin:0 10px">至</span>
          <el-date-picker v-model="form.endTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" style="width:240px"/>
        </el-form-item>

        <el-form-item label="活动地点">
          <el-input v-model="form.place" />
        </el-form-item>

        <el-form-item label="活动负责人" prop="chargeBy">
          <el-select 
            v-model="form.chargeBy" 
            placeholder="请选择负责人"
            style="width:100%"
          >
            <el-option
              v-for="teacher in teacherList"
              :key="teacher.userId"
              :label="teacher.userName"
              :value="teacher.userId"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="活动内容">
          <el-input v-model="form.content" type="textarea" rows="4" />
        </el-form-item>
      </el-form>

      <div slot="footer">
        <el-button @click="open=false">取消</el-button>
        <el-button type="primary" @click="submit">保存</el-button>
      </div>
    </el-dialog>

    <!-- 取消原因弹窗 -->
    <el-dialog title="取消活动" :visible.sync="cancelDialog" width="500px">
      <el-form v-model="cancelForm" label-width="100px">
        <el-form-item label="取消原因">
          <el-select v-model="cancelForm.reason" placeholder="请选择原因" style="width:100%">
            <el-option label="天气原因" value="天气原因" />
            <el-option label="临时调课" value="临时调课" />
            <el-option label="活动取消" value="活动取消" />
            <el-option label="场地问题" value="场地问题" />
            <el-option label="其他" value="其他" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelDialog=false">取消</el-button>
        <el-button type="primary" @click="submitCancel">确认取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listActivity,
  getActivity,
  addActivity,
  updateActivity,
  classList,
  listTemplate,
  getTemplate,
  closeActivity
} from "@/api/system/activity";

import {  getMyClass, getClassTeachers } from "@/api/system/class"

export default {
  name: "ActivityInfo",
  data() {
    return {
      query: { pageNum: 1, pageSize: 10, activityName: null },
      dateRange: null,
      list: [],
      total: 0,
      classOptions: [],
      templateOptions: [],
      open: false,
      form: {},
      rules: {
        templateId: [{ required: true, message: "请选择活动模板" }],
        activityName: [{ required: true, message: "活动名称不能为空" }],
        beginTime: [{ required: true, message: "请选择开始时间" }],
        endTime: [{ required: true, message: "请选择结束时间" }]
      },
      teacherList: [], // 班级教师列表
      isGetMyClass: false,
      cancelDialog: false,
      cancelForm: {
        reason: ""
      }
    };
  },
  created() {
    this.loadClassOptions();
    this.loadTemplateOptions();
    this.getList();
  },
  methods: {
    async getList() {
      const params = { ...this.query };
      if (this.dateRange && this.dateRange.length === 2) {
        params.beginTime = this.dateRange[0];
        params.endTime = this.dateRange[1];
      }
      const res = await listActivity(params);
      this.list = res.rows;
      this.total = res.total;
    },
    resetQuery() {
      this.query = { pageNum: 1, pageSize: 10, activityName: null };
      this.dateRange = null;
      this.getList();
    },

    async loadClassOptions() {
      const res = await classList();
      this.classOptions = res.data;
    },
    async loadTemplateOptions() {
      const res = await listTemplate();
      this.templateOptions = res.rows;
    },
    // 选中班级后加载本班老师
    async loadClassTeacher(classId) {
      if (!classId) {
        this.teacherList = []
        return
      }
      // 请求班级老师
      const res = await getClassTeachers(classId)
      this.teacherList = res.data || []

      // 默认选中当前登录用户
      const currentName = this.$store.state.user.nickName
      if (this.teacherList.some(t => t.userName === currentName)) {
        this.form.chargeBy = currentName
      }
    },
    async handleAdd() {
      this.open = true;
      this.form = {};
      // 自动带入教师班级
      const res = await getMyClass();
      if (res.data) {
        this.form.classId = res.data.classId;
        // 加载本班老师
        this.loadClassTeacher(res.data.classId)
        this.isGetMyClass = true
      }else{
        // 变为可用
        this.isGetMyClass = false
      }
      console.log(this.isGetMyClass)
    },
    async handleEdit(row) {
      const res = await getActivity(row.activityId);
      this.form = res.data;
      this.open = true;
      // 加载班级老师
      this.loadClassTeacher(this.form.classId)
    },
    async handleDelete(row) {
      await this.$confirm('此操作将永久删除该活动, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      });
      // 调用删除接口
      await delActivity(row.activityId);
      this.$message.success('删除成功');
      this.getList();
    },
    // 打开取消弹窗
    async handleClose(row) {
      await this.$confirm("确认取消该活动？取消后无法监控、无法编辑。", "提示", { type: "warning" });
      this.currentRow = row;
      this.cancelForm.reason = "";
      this.cancelDialog = true;
    },

    // 提交取消（带原因 + 时间）
    async submitCancel() {
      if (!this.cancelForm.reason) {
        return this.$message.warning("请选择取消原因");
      }

      await closeActivity(this.currentRow.activityId, {
        cancelReason: this.cancelForm.reason,
        cancelTime: new Date()
      });

      this.$message.success("活动已取消");
      this.cancelDialog = false;
      this.getList();
    },
    async selectTemplate(tid) {
      const res = await getTemplate(tid);
      const t = res.data;
      this.form.activityName = t.tempName;
      this.form.content = t.tempContent;
    },

    async submit() {
      await this.$refs.formRef.validate();
      // 回填班级快照
      const cls = this.classOptions.find(c => c.classId === this.form.classId);
      this.form.classCode = cls.classCode;
      this.form.gradeName = cls.grade === 1 ? "小班" : cls.grade === 2 ? "中班" : "大班";
      this.form.classFullName = cls.className;

      this.form.activityId
        ? await updateActivity(this.form)
        : await addActivity(this.form);

      this.$message.success("保存成功");
      this.open = false;
      this.getList();
    },

    goMonitor(row) {
      this.$router.push({
        path: "/activity/ActivityMonitor",
        query: { activityId: row.activityId }
      });
    },

    // ========== 状态判断：纯前端计算，不存库 ==========
    isBeforeStart(row) {
      const now = new Date();
      const begin = new Date(row.beginTime);
      return now < begin;
    },

    getStatusText(row) {
      const now = new Date();
      const begin = new Date(row.beginTime);
      const end = new Date(row.endTime);
      if (now < begin) return "未开始";
      if (now > end) return "已结束";
      return "进行中";
    },

    getStatusTag(row) {
      const now = new Date();
      const begin = new Date(row.beginTime);
      const end = new Date(row.endTime);
      if (now < begin) return "info";
      if (now > end) return "success";
      return "primary";
    }
  }
};
</script>