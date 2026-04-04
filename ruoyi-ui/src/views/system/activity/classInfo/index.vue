<template>
  <div class="app-container">
    <el-form :inline="true" :model="query" class="form-search">
      <el-form-item label="班级名称">
        <el-input v-model="query.className" placeholder="请输入班级名称"/>
      </el-form-item>
      <el-form-item label="年级">
        <el-select v-model="query.grade" placeholder="请选择">
          <el-option label="小班" :value="1"/>
          <el-option label="中班" :value="2"/>
          <el-option label="大班" :value="3"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getList">查询</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col>
        <el-button type="success" icon="el-icon-plus" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col>
        <el-button type="warning" @click="handleUpgrade">一键升班</el-button>
      </el-col>
    </el-row>

    <el-table :data="list" border>
      <el-table-column label="班级编号" prop="classCode"/>
      <el-table-column label="班级名称" prop="className"/>
      <el-table-column label="年级">
        <template slot-scope="scope">
          <el-tag :type="scope.row.grade ==1 ? 'primary' : scope.row.grade ==2 ? 'success' : 'info'">
            {{ scope.row.grade == 1 ? '小班' : scope.row.grade == 2 ? '中班' : '大班' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="学年" prop="schoolYear" />
      <el-table-column label="入园年份" prop="entryYear"/>
      <el-table-column label="班主任" prop="teacher"/>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status == 0 ? 'primary' : (scope.row.status == 1 ? 'danger' : 'success')">
            {{ scope.row.status == 0 ? '正常' : (scope.row.status == 1 ? '已升班' : '毕业') }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button type="success" size="mini" @click="openTeacherDialog(row)">配置教师</el-button>
          <el-button size="mini" type="warning" @click="handleUpgrade(scope.row)"
            :disabled="scope.row.status == 1 || scope.row.grade == 3">
            一键升班
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @size-change="getList"
      @current-change="getList"
      :current-page="query.pageNum"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="query.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    />

    <!-- 新增编辑弹窗 -->
    <el-dialog title="班级信息" :visible.sync="open" width="500px">
      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="班级编号" prop="classCode">
          <el-input v-model="form.classCode" placeholder="例如 C202401"/>
        </el-form-item>
        <el-form-item label="班级名称" prop="className">
          <el-input v-model="form.className" placeholder="例如 小一班"/>
        </el-form-item>
        <el-form-item label="年级" prop="grade">
          <el-select v-model="form.grade">
            <el-option label="小班" :value="1"/>
            <el-option label="中班" :value="2"/>
            <el-option label="大班" :value="3"/>
          </el-select>
        </el-form-item>
        <el-form-item label="入园年份" prop="entryYear">
          <el-input v-model.number="form.entryYear"/>
        </el-form-item>
        <el-form-item label="班主任">
          <el-input v-model="form.teacher"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="open = false">取消</el-button>
        <el-button type="primary" @click="submit">确定</el-button>
      </div>
    </el-dialog>

    <!-- 班级教师配置弹窗 -->
    <el-dialog title="班级教师配置" :visible.sync="teacherDialogVisible" width="600px">
      <el-form :model="teacherForm" label-width="120px">

        <el-form-item label="班级教师">
          <el-select
            v-model="teacherForm.userIds"
            multiple
            placeholder="请选择任课教师"
            style="width:100%"
          >
            <el-option
              v-for="user in teacherOptions"
              :key="user.userId"
              :label="user.userName"
              :value="user.userId"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="班主任">
          <el-select
            v-model="teacherForm.leaderUserId"
            placeholder="请指定班主任（必选）"
            style="width:100%"
          >
            <el-option
              v-for="uid in teacherForm.userIds"
              :key="uid"
              :label="getTeacherName(uid)"
              :value="uid"
            />
          </el-select>
        </el-form-item>

      </el-form>

      <div slot="footer">
        <el-button @click="teacherDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitTeachers">确定保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClassInfo, getClassInfo, addClassInfo, updateClassInfo, upgradeGrade, teacherList, getClassTeachers, saveClassTeachers } from "@/api/system/class";

export default {
  name: "ClassInfo",
  data() {
    return {
      query: { pageNum: 1, pageSize: 10, className: null, grade: null },
      list: [],
      total: 0,
      open: false,
      form: {},
      rules: {
        classCode: [{ required: true, message: "班级编号不能为空" }],
        className: [{ required: true, message: "班级名称不能为空" }],
        grade: [{ required: true, message: "年级不能为空" }],
        entryYear: [{ required: true, message: "入园年份不能为空" }]
      },
      teacherDialogVisible: false,
      currentClassId: null,
      teacherOptions: [],
      teacherForm: {
        userIds: [],
        leaderUserId: null
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    async getList() {
      const res = await listClassInfo(this.query);
      this.list = res.rows;
      this.total = res.total;
    },
    resetQuery() {
      this.query = { pageNum: 1, pageSize: 10, className: null, grade: null };
      this.getList();
    },
    handleAdd() {
      this.open = true;
      this.form = { status: "0" };
    },
    async handleEdit(row) {
      const res = await getClassInfo(row.classId);
      this.form = res.data;
      this.open = true;
    },
    async submit() {
      await this.$refs.formRef.validate();
      this.form.classId ? await updateClassInfo(this.form) : await addClassInfo(this.form);
      this.$message.success("操作成功");
      this.open = false;
      this.getList();
    },
    async handleUpgrade() {
      await upgradeGrade();
      this.$message.success("升班成功");
      this.getList();
    },
    // 打开教师配置
    async openTeacherDialog(row) {
      this.currentClassId = row.classId;
      this.teacherDialogVisible = true;
      await this.loadTeacherOptions();
      await this.loadClassTeacherSelected();
    },

    // 加载系统教师列表
    async loadTeacherOptions() {
      const res = await teacherList();
      this.teacherOptions = res.data;
    },

    // 加载该班已选教师
    async loadClassTeacherSelected() {
      const res = await getClassTeachers(this.currentClassId);
      let list = res.data;
      this.teacherForm.userIds = list.map(t => t.userId);

      let leader = list.find(t => t.isLeader === '1');
      this.teacherForm.leaderUserId = leader ? leader.userId : null;
    },

    // 根据ID获取教师姓名
    getTeacherName(userId) {
      let user = this.teacherOptions.find(u => u.userId === userId);
      return user ? user.userName : "";
    },

    // 提交保存
    async submitTeachers() {
      if (!this.teacherForm.leaderUserId) {
        this.$message.warning("请指定班主任");
        return;
      }
      await saveClassTeachers({
        classId: this.currentClassId,
        userIds: this.teacherForm.userIds,
        leaderUserId: this.teacherForm.leaderUserId
      });
      this.$message.success("保存成功");
      this.teacherDialogVisible = false;
    }
  }
};
</script>
