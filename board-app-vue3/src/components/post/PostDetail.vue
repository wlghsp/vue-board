<template>
  <div>
    <b-card>
      <div class="content-detail-content-info">
        <div class="content-detail-content-info-left">
          <div class="content-detail-content-info-left-number">{{ postId }}</div>
          <div class="content-detail-content-info-left-subject">{{ title }}</div>
        </div>
        <div class="content-detail-content-info-right">
          <div class="content-detail-content-info-right-user">글쓴이: {{ userId }}</div>
        </div>
      </div>
      <div class="content-detail-content">{{ content }}</div>
      <div class="content-detail-button">
        <b-button variant="primary" @click="updateData">수정</b-button>&nbsp;
        <b-button variant="success" @click="deleteData">삭제</b-button>
      </div>
      <div class="content-detail-comment">
        <CommentList :postId="postId"></CommentList>
      </div>
    </b-card>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import CommentList from '../comment/CommentList.vue';
import axios from 'axios';

export default defineComponent({
  name: 'PostDetail',
  components: {
    CommentList,
  },
  setup() {
    const route = useRoute();
    const router = useRouter();

    const postIdVal = ref<number | null>(null);
    const title = ref<string>('');
    const content = ref<string>('');
    const userId = ref<string>('');

    const fetchPostDetail = async () => {
      postIdVal.value = Number(route.params.postId);

      try {
        const response = await axios.get(`/post/${route.params.postId}`);
        const obj = response.data;

        title.value = obj.title;
        content.value = obj.content;
        userId.value = obj.userId;
      } catch (error) {
        console.error('데이터를 불러오는 중 에러가 발생했습니다:', error);
      }
    };

    const deleteData = async () => {
      if (confirm('게시글을 삭제하시겠습니까?')) {
        try {
          await axios.delete(`/post/${postIdVal.value}`);
          alert('게시글이 삭제되었습니다.');
          router.push({ path: '/' });
        } catch (error) {
          console.error('Failed to delete post:', error);
          alert('게시글 삭제에 실패했습니다.');
        }
      }
    };

    const updateData = () => {
      router.push({ path: `/post/create/${postIdVal.value}` });
    };

    onMounted(fetchPostDetail);

    const postId = Number(route.params.postId);

    return {
      postId,
      postIdVal,
      title,
      content,
      userId,
      fetchPostDetail,
      deleteData,
      updateData,
    };
  },
});
</script>

<style scoped>
.content-detail-content-info {
  border: 1px solid black;
  display: flex;
  justify-content: space-between;
}

.content-detail-content-info-left {
  width: 720px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}

.content-detail-content-info-right {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.content-detail-content {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 720px;
}

.content-detail-button {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}

.content-detail-comment {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}
</style>