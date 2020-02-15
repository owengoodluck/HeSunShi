<template>
	<div>
        <!--面包屑-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-menu"></i> 用户方案列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!-- 表头 -->
	    <el-col :span="24" class="warp-main">
            <!-- 工具条 -->
            <el-form :inline="true" :model="searchForm">
                <el-col :span="3">
                    <el-form-item>
                        <el-select v-model="searchForm.type" placeholder="状态" clearable>
                            <el-option key="1" label="已报价" value="1"></el-option>
                            <el-option key="2" label="未报价" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>

                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
                </el-form-item>
            </el-form>
            <!-- 列表 -->
            <el-table :data="tableData" border style="width: 100%" ref="multipleTable" highlight-current-row stripe>
                <el-table-column type="index" width="55" :index="indexMethod"></el-table-column>
                <el-table-column prop="nickName" label="用户昵称"></el-table-column>
                <el-table-column prop="phone" label="用户手机"></el-table-column>
                <el-table-column prop="customNumber" label="方案编号"></el-table-column>
                <el-table-column prop="name" label="方案名"></el-table-column>
                <el-table-column prop="#" label="状态">
                    <template slot-scope="scope">
                        <div v-if="scope.row.type == 1">
                            <span >已报价</span>   
                        </div>
                        <div v-if="scope.row.type == 2">
                            <div >未报价</div>
                            <el-button type="primary" plain size="small" @click="baojia(scope.$index, scope.row)" v-hasPermission="'customSpecification:offer'">报价</el-button>
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="#" label="盒型" width="100">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.typeImage)">
                            <span style="color: bule"/>{{scope.row.typeName}}</span>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="#" label="材质" width="100">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.materialImage)">
                            <span style="color: bule"/>{{scope.row.materialName}}</span>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="#" label="版面" width="100">
                    <template slot-scope="scope">
                        <el-button type="text" @click="lookBigIcon(scope.$index, scope.row.layoutImage)">
                            <span style="color: bule"/>{{scope.row.layoutName}}</span>
                        </el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="printMethodName" label="印刷方式"></el-table-column>
                <el-table-column prop="#" label="尺寸">
                    <template slot-scope="scope">
                        <span style="color: bule"/>{{scope.row.length}}cm*{{scope.row.width}}cm*{{scope.row.height}}cm</span>
                    </template>
                </el-table-column>
                <el-table-column prop="piece" label="件数"></el-table-column>
                <el-table-column prop="price" label="价格"></el-table-column>
                <el-table-column prop="fare" label="运费"></el-table-column>
                <el-table-column prop="updateTime" label="创建时间" width="140"></el-table-column>
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

            <el-dialog width="960px" title="报价" :visible.sync ="baojiaVisible" :close-on-click-modal="false">
                <el-form :model="baojiaForm" status-icon label-width="120px" :rules="baojiaFormRules" ref="editForm">
                    <el-row >
                        <el-col :span="12">
                            <el-form-item label="价格" prop="price">
                                <el-input type="number" v-model.trim="baojiaForm.price"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="运费" prop="fare">
                                <el-input type="number" v-model.trim="baojiaForm.fare"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click.native="baojiaVisible = false">取消</el-button>
                    <el-button type="primary" @click.native="baojiaSubmit">提交</el-button>
                </div>
            </el-dialog>
        </el-col>
	</div>
</template>

<script>
	export default {
		data(){
            var validateNormalprice = (rule, value, callback) => {
                if(value<0){
                    callback(new Error('数量不能小于0！'));
                }else{ 
                    callback();
                } 
            };
			return{
				url: 'customSpecification/queryByPage',
				tableData: [],
                cur_page: 1,
                total: 0,
                pageSize: 0,
                is_search: false,
				searchForm: {
                    type: ''
                },
                photo: '',
                showPhotoVisible: false,
                baojiaVisible: false,
                baojiaForm: {
                    id: "",
                    price: "",
                    fare: "",
                    formId: "",
                    customNumber: "",
                    uId: "",
                    name: "",
                    piece: ""
                },
                baojiaFormRules: {
                    price: [
                        {required: true, message: '请输入价格', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ],
                    fare: [
                        {required: true, message: '请输入运费', trigger: 'blur'},
                        {validator: validateNormalprice, trigger: 'blur'}
                    ]
                }
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
                let params = '?pageNo=' + that.cur_page + '&type=' + that.searchForm.type;
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
            baojia: function(index,row){
                let that = this;
                that.baojiaVisible = true;
                if (that.$refs.baojiaForm) {
                    that.$refs.baojiaForm.clearValidate();
                }
                that.baojiaForm = {
                    id: row.id,
                    price: "",
                    fare: "",
                    formId: row.formId,
                    customNumber: row.customNumber,
                    uId: row.uId,
                    name: row.name,
                    piece: row.piece
                }
            },
            //编辑
            baojiaSubmit: function () {
                let that = this;
                this.$refs.baojiaForm.validate((valid) => {
                    if (valid) {
                        let params = Object.assign({}, this.baojiaForm);
                        console.log(params)
                        that.$axios.post('customSpecification/quotedPrice', params)
                        .then(function (response) {
                            if (response.data.code == 200){
                                that.$message.success({showClose: true, message: '报价成功', duration: 2000});
                                that.baojiaVisible = false;
                                that.getData();
                            } else {
                                that.$message.error({showClose: true, message: response.data.message, duration: 2000});
                            }
                        });
                    }
                });
            },
		}
	}
</script>

<style>
.head_pic {
    border-radius: 60px;
}
</style>