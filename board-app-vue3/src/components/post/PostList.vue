<template>
  <div>
    <b-table
        striped
        hover
        :items="items"
        :current-page="currentPage"
        :fields="fields"
        @row-clicked="rowClick"
    ></b-table>
    <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        align="center"
        @page-click="pageClick(pageNumber)"
    ></b-pagination>
    <b-button @click="writeContent">글쓰기</b-button>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted, watch } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: 'PostList',
  setup() {
    const router = useRouter();

    const currentPage = ref(1); // 현재 페이지
    const perPage = ref(10); // 페이지당 보여줄 갯수
    const items = ref<any[]>([]); // 게시글 리스트
    const rows = ref(0); // 총 게시글 수
    const fields = ref([
      { key: 'postId', label: '번호' },
      { key: 'title', label: '제목' },
      { key: 'userId', label: '글쓴이' },
    ]);

    const fetchPosts = async () => {
      try {
        const response = await axios.get(`/posts?page=${currentPage.value - 1}&size=${perPage.value}`);
        items.value = response.data.postResponses;
        rows.value = response.data.rows;
      } catch (error) {
        console.error('데이터를 불러오는 중 에러가 발생했습니다:', error);
      }
    };

    const rowClick = (item: any) => {
      router.push({
        path: `/post/detail/${item.postId}`,
      });
    };

    const writeContent = () => {
      router.push({
        path: '/post/create',
      });
    };

    const pageClick = (pageNumber: number) => {
      currentPage.value = pageNumber; // 클릭한 페이지 번호로 currentPage 업데이트
      fetchPosts(); // 업데이트된 페이지로 데이터 다시 불러오기
    };

    onMounted(fetchPosts);

    watch(currentPage, fetchPosts);

    return {
      currentPage,
      perPage,
      items,
      rows,
      fields,
      fetchPosts,
      rowClick,
      writeContent,
      pageClick,
    };
  },
});
</script>