<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属部门ID" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入所属部门ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="员工姓名" prop="employeeName">
        <el-input
          v-model="queryParams.employeeName"
          placeholder="请输入员工姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="岗位" prop="post">
        <el-input
          v-model="queryParams.post"
          placeholder="请输入岗位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="薪资" prop="salary">
        <el-input
          v-model="queryParams.salary"
          placeholder="请输入薪资"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入职日期" prop="entryDate">
        <el-date-picker clearable
          v-model="queryParams.entryDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择入职日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="员工头像地址" prop="avatar">
        <el-input
          v-model="queryParams.avatar"
          placeholder="请输入员工头像地址"
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
          v-hasPermi="['employee:员工管理:add']"
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
          v-hasPermi="['employee:员工管理:edit']"
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
          v-hasPermi="['employee:员工管理:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['employee:员工管理:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="员工管理List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="员工主键ID" align="center" prop="employeeId" />
      <el-table-column label="所属部门ID" align="center" prop="deptId" />
      <el-table-column label="员工姓名" align="center" prop="employeeName" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="岗位" align="center" prop="post" />
      <el-table-column label="薪资" align="center" prop="salary" />
      <el-table-column label="入职日期" align="center" prop="entryDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.entryDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态 0在职 1离职" align="center" prop="status" />
      <el-table-column label="员工头像地址" align="center" prop="avatar" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['employee:员工管理:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['employee:员工管理:remove']"
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

    <!-- 添加或修改员工人事信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="所属部门ID" prop="deptId">
              <el-input v-model="form.deptId" placeholder="请输入所属部门ID" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="员工姓名" prop="employeeName">
              <el-input v-model="form.employeeName" placeholder="请输入员工姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="手机号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="岗位" prop="post">
              <el-input v-model="form.post" placeholder="请输入岗位" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="薪资" prop="salary">
              <el-input v-model="form.salary" placeholder="请输入薪资" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="入职日期" prop="entryDate">
              <el-date-picker clearable
                v-model="form.entryDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择入职日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="员工头像地址" prop="avatar">
              <el-input v-model="form.avatar" placeholder="请输入员工头像地址" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { list员工管理, get员工管理, del员工管理, add员工管理, update员工管理 } from "@/api/employee/员工管理"

export default {
  name: "员工管理",
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
      // 员工人事信息表格数据
      员工管理List: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        employeeName: null,
        phone: null,
        email: null,
        post: null,
        salary: null,
        entryDate: null,
        status: null,
        avatar: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deptId: [
          { required: true, message: "所属部门ID不能为空", trigger: "blur" }
        ],
        employeeName: [
          { required: true, message: "员工姓名不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询员工人事信息列表 */
    getList() {
      this.loading = true
      list员工管理(this.queryParams).then(response => {
        this.员工管理List = response.rows
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
        employeeId: null,
        deptId: null,
        employeeName: null,
        phone: null,
        email: null,
        post: null,
        salary: null,
        entryDate: null,
        status: null,
        avatar: null,
        createBy: null,
        createTime: null,
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
      this.ids = selection.map(item => item.employeeId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加员工人事信息"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const employeeId = row.employeeId || this.ids
      get员工管理(employeeId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改员工人事信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.employeeId != null) {
            update员工管理(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            add员工管理(this.form).then(response => {
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
      const employeeIds = row.employeeId || this.ids
      this.$modal.confirm('是否确认删除员工人事信息编号为"' + employeeIds + '"的数据项？').then(function() {
        return del员工管理(employeeIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('employee/员工管理/export', {
        ...this.queryParams
      }, `员工管理_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
