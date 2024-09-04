<template>
  <div>
    <div :key="item.commentId" v-for="item in comments">
      <CommentListItem
          :commentObj="item"
          @comment-deleted="removeComment"
      ></CommentListItem>
    </div>
    <CommentCreate :postId="postId"
                   :reloadComment="reloadComment"
    />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import CommentListItem from './CommentListItem.vue';
import CommentCreate from './CommentCreate.vue';
import axios from 'axios';

export default defineComponent({
  name: 'CommentList',
  components: {
    CommentListItem,
    CommentCreate,
  },
  props: {
    postId: {
      type: Number,
      required: true,
    },
  },
  setup(props) {
    const comments = ref<Array<{ commentId: number; writer: string; content: string }>>([]);

    const reloadComment = async () => {
      try {
        const response = await axios.get(`/comment/${props.postId}`);
        comments.value = response.data;
      } catch (error) {
        console.error('데이터를 불러오는 중 에러가 발생했습니다:', error);
      }
    };

    const removeComment = (commentId: number) => {
      comments.value = comments.value.filter(comment => comment.commentId !== commentId);
    };

    onMounted(reloadComment);

    return {
      comments,
      reloadComment,
      removeComment,
    };
  },
});
</script>

<style scoped>
/* 스타일을 추가할 수 있습니다 */
</style>