import Vue from "vue";
import VueRouter from "vue-router";
import PostList from "@/components/post/PostList.vue";
import PostDetail from "@/components/post/PostDetail.vue";
import PostCreate from "@/components/post/PostCreate.vue";

import NotFound from "@/components/NotFound";

Vue.use(VueRouter); // 뷰 어플리케이션에 라우터 플러그인을 추가한다.

// 라우터 객체를 생성
export default new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      writer: "PostList",
      component: PostList
    },
    {
      path: "/post/detail/:postId",
      writer: "PostDetail",
      component: PostDetail
    },
    {
      path: "/post/create/:postId?",
      writer: "PostCreate",
      component: PostCreate
    },
    {
      path: "*",
      component: NotFound
    }
  ]
});
