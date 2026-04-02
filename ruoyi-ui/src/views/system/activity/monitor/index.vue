<template>
  <div class="app-container">
    <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">

      <el-form-item label="活动信息">
        <span style="color:#666">
          {{ activityInfo.activityName }} — {{ activityInfo.classFullName }}
        </span>
      </el-form-item>

      <el-form-item label="监控时间" prop="monitorTime">
        <el-date-picker
          v-model="form.monitorTime"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
        />
      </el-form-item>

      <el-form-item label="活动秩序" prop="orderStatus">
        <el-select v-model="form.orderStatus">
          <el-option label="秩序良好" :value="0" />
          <el-option label="一般" :value="1" />
          <el-option label="秩序较差" :value="2" />
        </el-select>
      </el-form-item>

      <el-form-item label="安全隐患" prop="safeHidden">
        <el-select v-model="form.safeHidden">
          <el-option label="无隐患" :value="0" />
          <el-option label="存在隐患" :value="1" />
        </el-select>
      </el-form-item>

      <el-form-item label="幼儿情绪" prop="childEmotion">
        <el-select v-model="form.childEmotion">
          <el-option label="情绪良好" :value="0" />
          <el-option label="一般" :value="1" />
          <el-option label="情绪较差" :value="2" />
        </el-select>
      </el-form-item>

      <el-form-item label="是否异常" prop="monitorStatus">
        <el-switch
          v-model="form.monitorStatus"
          :active-value="1"
          :inactive-value="0"
          active-text="异常"
          inactive-text="正常"
        />
      </el-form-item>

      <el-form-item label="异常描述" v-if="form.monitorStatus === 1">
        <el-input v-model="form.abnormalDesc" type="textarea" rows="3" />
      </el-form-item>

      <el-form-item label="处理措施" v-if="form.monitorStatus === 1">
        <el-input v-model="form.handleMethod" type="textarea" rows="3" />
      </el-form-item>

      <el-form-item label="处理结果" v-if="form.monitorStatus === 1">
        <el-input v-model="form.handleResult" type="textarea" rows="3" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submit">保存监控记录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getActivity, addMonitor } from '@/api/system/activity'

export default {
  name: 'ActivityMonitor',
  data() {
    return {
      activityId: null,
      activityInfo: {},
      form: {
        monitorTime: '',
        orderStatus: 0,
        safeHidden: 0,
        childEmotion: 0,
        monitorStatus: 0,
        abnormalDesc: '',
        handleMethod: '',
        handleResult: ''
      },
      rules: {
        monitorTime: [{ required: true, message: '请选择监控时间' }]
      }
    }
  },
  created() {
    this.activityId = this.$route.query.activityId
    this.loadActivity()
  },
  methods: {
    async loadActivity() {
      const res = await getActivity(this.activityId)
      this.activityInfo = res.data
    },
    async submit() {
      await this.$refs.formRef.validate()
      this.form.activityId = this.activityId
      await addMonitor(this.form)
      this.$message.success('监控记录保存成功')
      this.$router.go(-1)
    }
  }
}
</script>