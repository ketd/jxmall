<template>
  <div class="app-container">
    <el-form :model="queryParams.data" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                  <el-form-item label="sku_id" prop="skuId">
                    <el-input
                        v-model="queryParams.data.skuId"
                        placeholder="请输入sku_id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="spu_id" prop="spuId">
                    <el-input
                        v-model="queryParams.data.spuId"
                        placeholder="请输入spu_id"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="商品名字" prop="spuName">
                    <el-input
                        v-model="queryParams.data.spuName"
                        placeholder="请输入商品名字"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="会员昵称" prop="memberNickName">
                    <el-input
                        v-model="queryParams.data.memberNickName"
                        placeholder="请输入会员昵称"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="星级" prop="star">
                    <el-input
                        v-model="queryParams.data.star"
                        placeholder="请输入星级"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="会员ip" prop="memberIp">
                    <el-input
                        v-model="queryParams.data.memberIp"
                        placeholder="请输入会员ip"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="显示状态" prop="showStatus">
                    <el-select v-model="queryParams.data.showStatus" placeholder="请选择显示状态" clearable>
                      <el-option
                          v-for="dict in dict.type.show_status"
                          :key="dict.value"
                          :label="dict.label"
                          :value="dict.value"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="购买时属性组合" prop="spuAttributes">
                    <el-input
                        v-model="queryParams.data.spuAttributes"
                        placeholder="请输入购买时属性组合"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="点赞数" prop="likesCount">
                    <el-input
                        v-model="queryParams.data.likesCount"
                        placeholder="请输入点赞数"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="回复数" prop="replyCount">
                    <el-input
                        v-model="queryParams.data.replyCount"
                        placeholder="请输入回复数"
                        clearable
                        @keyup.enter.native="handleQuery"
                    />
                  </el-form-item>
                  <el-form-item label="评论类型" prop="commentType">
                    <el-select v-model="queryParams.data.commentType" placeholder="请选择评论类型" clearable>
                      <el-option
                          v-for="dict in dict.type.comment_type"
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
            v-hasPermi="['product:spuComment:add']"
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
            v-hasPermi="['product:spuComment:edit']"
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
            v-hasPermi="['product:spuComment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['product:spuComment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="spuCommentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
              <el-table-column label="id" align="center" prop="id" />
              <el-table-column label="sku_id" align="center" prop="skuId" />
              <el-table-column label="spu_id" align="center" prop="spuId" />
              <el-table-column label="商品名字" align="center" prop="spuName" />
              <el-table-column label="会员昵称" align="center" prop="memberNickName" />
              <el-table-column label="星级" align="center" prop="star" />
              <el-table-column label="会员ip" align="center" prop="memberIp" />
              <el-table-column label="显示状态" align="center" prop="showStatus">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.show_status" :value="scope.row.showStatus"/>
                </template>
              </el-table-column>
              <el-table-column label="购买时属性组合" align="center" prop="spuAttributes" />
              <el-table-column label="点赞数" align="center" prop="likesCount" />
              <el-table-column label="回复数" align="center" prop="replyCount" />
              <el-table-column label="评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]" align="center" prop="resources" width="100">
                <template slot-scope="scope">
                  <image-preview :src="scope.row.resources" :width="50" :height="50"/>
                </template>
              </el-table-column>
              <el-table-column label="内容" align="center" prop="content" />
              <el-table-column label="用户头像" align="center" prop="memberIcon" width="100">
                <template slot-scope="scope">
                  <image-preview :src="scope.row.memberIcon" :width="50" :height="50"/>
                </template>
              </el-table-column>
              <el-table-column label="评论类型" align="center" prop="commentType">
                <template slot-scope="scope">
                      <dict-tag :options="dict.type.comment_type" :value="scope.row.commentType"/>
                </template>
              </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['product:spuComment:edit']"
          >修改</el-button>
          <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['product:spuComment:remove']"
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

    <!-- 添加或修改商品评价对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                        <el-form-item label="sku_id" prop="skuId">
                          <el-input v-model="form.skuId" placeholder="请输入sku_id" />
                        </el-form-item>
                        <el-form-item label="spu_id" prop="spuId">
                          <el-input v-model="form.spuId" placeholder="请输入spu_id" />
                        </el-form-item>
                        <el-form-item label="商品名字" prop="spuName">
                          <el-input v-model="form.spuName" placeholder="请输入商品名字" />
                        </el-form-item>
                        <el-form-item label="会员昵称" prop="memberNickName">
                          <el-input v-model="form.memberNickName" placeholder="请输入会员昵称" />
                        </el-form-item>
                        <el-form-item label="星级" prop="star">
                          <el-input v-model="form.star" placeholder="请输入星级" />
                        </el-form-item>
                        <el-form-item label="会员ip" prop="memberIp">
                          <el-input v-model="form.memberIp" placeholder="请输入会员ip" />
                        </el-form-item>
                        <el-form-item label="显示状态" prop="showStatus">
                          <el-radio-group v-model="form.showStatus">
                            <el-radio
                                v-for="dict in dict.type.show_status"
                                :key="dict.value"
                                :label="parseInt(dict.value)"
                            >{{dict.label}}</el-radio>
                          </el-radio-group>
                        </el-form-item>
                        <el-form-item label="购买时属性组合" prop="spuAttributes">
                          <el-input v-model="form.spuAttributes" placeholder="请输入购买时属性组合" />
                        </el-form-item>
                        <el-form-item label="点赞数" prop="likesCount">
                          <el-input v-model="form.likesCount" placeholder="请输入点赞数" />
                        </el-form-item>
                        <el-form-item label="回复数" prop="replyCount">
                          <el-input v-model="form.replyCount" placeholder="请输入回复数" />
                        </el-form-item>
                        <el-form-item label="评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]" prop="resources">
                          <image-upload v-model="form.resources" @upload-success="handleUploadSuccess"/>
                        </el-form-item>
                        <el-form-item label="内容">
                          <editor v-model="form.content" :min-height="192"/>
                        </el-form-item>
                        <el-form-item label="用户头像" prop="memberIcon">
                          <image-upload v-model="form.memberIcon" @upload-success="handleUploadSuccess"/>
                        </el-form-item>
                        <el-form-item label="评论类型" prop="commentType">
                          <el-radio-group v-model="form.commentType">
                            <el-radio
                                v-for="dict in dict.type.comment_type"
                                :key="dict.value"
                                :label="parseInt(dict.value)"
                            >{{dict.label}}</el-radio>
                          </el-radio-group>
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
  import {
      listSpuCommentPage,
    getSpuComment,
    delSpuComment,
    addSpuComment,
    updateSpuComment ,
    exportSpuComment
  } from "@/api/product/spuComment";

  export default {
    name: "SpuComment",
        dicts: ['show_status', 'comment_type'],
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
        // 商品评价表格数据
              spuCommentList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
            data:{
                        skuId: null,
                        spuId: null,
                        spuName: null,
                        memberNickName: null,
                        star: null,
                        memberIp: null,
                        showStatus: null,
                        spuAttributes: null,
                        likesCount: null,
                        replyCount: null,
                        resources: null,
                        content: null,
                        memberIcon: null,
                        commentType: null
          }
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询商品评价列表 */
      getList() {
        this.loading = true;
        listSpuCommentPage(this.queryParams).then(response => {
          this.spuCommentList = response.data.rows;
          this.total = response.data.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
                        id: null,
                        skuId: null,
                        spuId: null,
                        spuName: null,
                        memberNickName: null,
                        star: null,
                        memberIp: null,
                        createTime: null,
                        showStatus: null,
                        spuAttributes: null,
                        likesCount: null,
                        replyCount: null,
                        resources: null,
                        content: null,
                        memberIcon: null,
                        commentType: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加商品评价";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getSpuComment(id).then(response => {
          this.form = response.data.data;
          this.open = true;
          this.title = "修改商品评价";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateSpuComment(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addSpuComment(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = [].concat(row.id !== undefined ? row.id : [], this.ids); // 将属性ID合并为一个数组
        this.$modal.confirm('是否确认删除商品属性编号为"' + ids.join(', ') + '"的数据项？').then(() => {
          return delSpuComment(ids);
        }).then(() => {
          // 删除成功后刷新列表并显示成功消息
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
          // 可以在这里处理删除失败的情况，比如显示错误消息
        });

      },
  /** 导出按钮操作 */
  handleExport(row)
  {
    const ids = [].concat(row.id !== undefined ? row.id : [], this.ids); // 将属性ID合并为一个数组
    this.$modal.confirm('是否确认导出商品属性编号为"' + ids.join(', ') + '"的数据项？').then(() => {
    return exportSpuComment(ids);
    }).then(() => {
    this.$modal.msgSuccess("导出成功");
    this.getList();
    }).catch(() => {
    console.log("导出失败");
    });

  },
  handleUploadSuccess(fileName) {
    this.form.commentType = fileName;
  },
  handleUploadSuccess(fileName) {
    this.form.commentType = fileName;
  },
  }
  };
</script>


