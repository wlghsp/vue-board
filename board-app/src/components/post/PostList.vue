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
    <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" align="center"
                  @page-click="pageClick(pageNumber)"></b-pagination>
    <b-button @click="writeContent">글쓰기</b-button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PostList",
  data() {
    return {
      currentPage: 1, // 현재 페이지
      perPage: 10, // 페이지당 보여줄 갯수
      items: [], // 초기값을 빈 배열로 설정
      rows: 0,
      fields: [
        {
          key: "postId",
          label: "번호"
        },
        {
          key: "title",
          label: "제목"
        },
        {
          key: "userId",
          label: "글쓴이"
        },
      ],
    };
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await axios.get(
            `/posts?page=${this.currentPage - 1}&size=${this.perPage}`
        );
        console.log(response.data)

        this.items = response.data.postResponses;
        this.rows = response.data.rows;

      } catch (error) {
        console.error("데이터를 불러오는 중 에러가 발생했습니다:", error);
      }
    },
    rowClick(item) {
      this.$router.push({
        path: `/post/detail/${item.postId}`
      });
    },
    writeContent() {
      this.$router.push({
        path: `/post/create`
      });
    },
    pageClick(pageNumber) {
      this.currentPage = pageNumber; // 클릭한 페이지 번호로 currentPage 업데이트
      this.fetchPosts(); // 업데이트된 페이지로 데이터 다시 불러오기
    }
  },
  watch: {
    currentPage() {
      this.fetchPosts(); // 페이지가 변경될 때마다 데이터를 다시 불러옴
    }
  },
  created() {
    this.fetchPosts(); // 컴포넌트가 생성될 때 데이터를 불러옴
  },
};
</script>