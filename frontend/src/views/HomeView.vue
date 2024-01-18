<template>
  <el-container style="min-height: 100vh">
    <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%)">
      <el-menu :default-openeds="[]"
               style="min-height: 100%; overflow-x: hidden"
               background-color="rgb(48, 65, 86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
      >
<!--        header of left menu-->
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; margin-right: 5px">
          <b style="color: #cccccc" v-show = "logoTextShow">Administration System</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>
            <span slot="title">Navigator One</span>
          </template>
          <el-menu-item-group>
            <template slot="title">Group 1</template>
            <el-menu-item index="1-1">Option 1</el-menu-item>
            <el-menu-item index="1-2">Option 2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group 2">
            <el-menu-item index="1-3">Option 3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">Option4</template>
            <el-menu-item index="1-4-1">Option 4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">Navigator Two</span>
          </template>
          <el-menu-item-group>
            <template slot="title">Group 1</template>
            <el-menu-item index="2-1">Option 1</el-menu-item>
            <el-menu-item index="2-2">Option 2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group 2">
            <el-menu-item index="2-3">Option 3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">Option 4</template>
            <el-menu-item index="2-4-1">Option 4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span slot="title">Navigator Three</span>
          </template>
          <el-menu-item-group>
            <template slot="title">Group 1</template>
            <el-menu-item index="3-1">Option 1</el-menu-item>
            <el-menu-item index="3-2">Option 2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="Group 2">
            <el-menu-item index="3-3">Option 3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="3-4">
            <template slot="title">Option 4</template>
            <el-menu-item index="3-4-1">Option 4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 20px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>

        <el-dropdown style="width: 70px; cursor: pointer">
          <span>Tom</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>Personal Information</el-dropdown-item>
            <el-dropdown-item>Exist</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>

      <el-main>

        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">promotion management</a></el-breadcrumb-item>
        </el-breadcrumb>

<!--        search box-->
        <div style="margin: 10px 0">
          <el-input style="width: 200px" placeholder="Enter the name" suffix-icon="el-icon-search" v-model="username"></el-input>
          <el-input style="width: 200px; margin-left: 5px" placeholder="Enter the address" suffix-icon="el-icon-position"></el-input>
          <el-input style="width: 200px; margin-left: 5px" placeholder="Enter the email" suffix-icon="el-icon-message"></el-input>
          <el-button style="margin-left: 5px" type="primary" @click="load">search</el-button>
        </div>
<!--        add-->
        <div style="margin: 10px 0">
          <el-button type ="primary">add<i class="el-icon-plus" style="margin-left: 5px"></i></el-button>
          <el-button type ="primary">delete<i class="el-icon-minus" style="margin-left: 2px"></i></el-button>
          <el-button type ="primary">output<i class="el-icon-bottom" style="margin-left: 5px"></i></el-button>
          <el-button type ="primary">input<i class="el-icon-top" style="margin-left: 5px"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
          <el-table-column prop="id" label="Id" width="80"></el-table-column>
          <el-table-column prop="username" label="Username" width="220"></el-table-column>
          <el-table-column prop="nickname" label="Nickname" width="220"></el-table-column>
          <el-table-column prop="phone" label="Phone"></el-table-column>
          <el-table-column prop="email" label="Email" width="200"></el-table-column>
          <el-table-column prop="address" label="Address"></el-table-column>
          <el-table-column label="Process">
            <template slot-scope="scope">
              <el-button type="success">edit <i class="el-icon-edit"></i></el-button>
              <el-button type="danger">edit <i class="el-icon-remove"></i></el-button>
            </template>
          </el-table-column>
        </el-table>

        <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page.sync="currentPage4"
              :current-page="pageNum"
              :page-sizes="[2, 5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },
  data () {

    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      username: "",
      collapseBtnClass: 'el-icon-s-fold',
      isCollapsed: false,
      sideWidth: 200,
      logoTextShow : true
    }
  },
  created() {
    //request page finding
    this.load()
  },
  methods: {
    collapse() { //点击收缩按钮就会触发
      this.isCollapsed = !this.isCollapsed
      if(this.isCollapsed){ // fold
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else { // unfold
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    load() {
      fetch("http://localhost:9090/user/page?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize + "&username=" + this.username).then(res => res.json()).then( res=> {
        console.log(res)
        this.tableData = res.data
        this.total = res.total
      })
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style>
.headerBg {
  background: #746f6f !important;
}
</style>
