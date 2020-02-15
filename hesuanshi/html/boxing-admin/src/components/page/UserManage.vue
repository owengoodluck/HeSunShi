<template>
	<div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 用户管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!-- 表头 -->
	    <el-col :span="24" class="warp-main">
            <!-- 工具条 -->
            <el-form :inline="true" :model="searchForm">
                <el-form-item>
                    <el-input v-model="searchForm.name" placeholder="用户昵称" clearable></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                </el-form-item>
            </el-form>
            <!-- 列表 -->
            <el-table :data="tableData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="nickName" label="昵称"></el-table-column>
                <el-table-column prop="#" label="用户头像" width="80">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.avatar)">
                            <img :src="scope.row.avatar" width="40" height="40" class="head_pic"/>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="gender" label="性别" width="80"></el-table-column>
                <el-table-column prop="province" label="省份"></el-table-column>
                <el-table-column prop="city" label="市区" ></el-table-column>
                <el-table-column prop="phone" label="手机号" width="140"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="140"></el-table-column>
            </el-table>
            <!-- 分页 -->
            <div class="pagination">
                <el-pagination
                    @current-change ="handleCurrentChange"
                    layout="prev, pager, next"
                    :total="total"
                    :page-size="pageSize">
                </el-pagination>
            </div>
            <!-- 显示图片的dialog -->
            <el-dialog :visible.sync ="showPhotoVisible" :close-on-click-modal="false" width="40%">
                <img :src="photo" class="head_pic" style="max-width:100%;max-height:100%;"/>
            </el-dialog>
        </el-col>
	</div>
</template>

<script>
	export default {
		data(){
			return{
				url: 'user/queryUserByPage',
				tableData: [],
                cur_page: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
				searchForm: {
                    name: ''
                },
                photo: '',
                showPhotoVisible: false
			}
		},
		mounted(){
		    // 调用初始化方法
			this.getData();
		},
		methods:{
		    // 序列号
			indexMethod(index) {
                return (this.cur_page - 1) * 10 + index + 1;
            },
            // 分页
            handleCurrentChange(val){
                this.cur_page = val;
                this.getData();
            },
            // 初始化数据
			getData(){
                let that = this;
                let params = '?pageNo=' + that.cur_page + '&nickName=' + that.searchForm.name;
                that.$axios.get(that.url + params).then((res) => {
                    if(res.data.code == 200){
                        that.tableData = res.data.data.list;
                        that.total = res.data.data.total;
                        that.pageSize = res.data.data.pageSize;
                    }
                })
            },
            // 搜索
        	search(){
                this.is_search = true;
                this.cur_page = 1;
                this.getData();
            },
            //点击查看大图片
            lookBigIcon: function (index, row) {
                let that = this;
                that.showPhotoVisible = true;
                that.photo = row;
            },
		}
	}
</script>

<style>
.head_pic {
    border-radius: 60px;
}
</style>