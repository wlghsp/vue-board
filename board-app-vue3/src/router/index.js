import { createRouter, createWebHistory } from 'vue-router';
import PostList from '@/components/post/PostList.vue';
import PostDetail from '@/components/post/PostDetail.vue';
import PostCreate from '@/components/post/PostCreate.vue';
import NotFound from '@/components/NotFound.vue';

// 라우터 구성 배열을 정의합니다.
const routes = [
  {
    path: '/',
    name: 'PostList',
    component: PostList
  },
  {
    path: '/post/detail/:postId',
    name: 'PostDetail',
    component: PostDetail
  },
  {
    path: '/post/create/:postId?',
    name: 'PostCreate',
    component: PostCreate
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFound
  }
];

// 라우터 객체를 생성합니다.
const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;