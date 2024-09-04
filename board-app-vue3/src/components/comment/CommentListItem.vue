<template>
  <div>
    <div class="comment-list-item">
      <div class="comment-list-item-name">
        <div>{{ commentObj.writer }}</div>
      </div>
      <div class="comment-list-item-context">{{ commentObj.content }}</div>
      <div class="comment-list-item-button">
        <b-button variant="info" @click="editComment">수정</b-button>
        <b-button variant="info" @click="deleteComment">삭제</b-button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';
import axios from 'axios';

export default defineComponent({
  name: 'CommentListItem',
  props: {
    commentObj: {
      type: Object as PropType<{
        commentId: number;
        writer: string;
        content: string;
      }>,
      required: true,
    },
  },
  setup(props, { emit }) {
    const editComment = async () => {
      const newContent = prompt('댓글을 수정하세요:', props.commentObj.content);
      if (newContent !== null) {
        try {
          const updateData = {
            commentId: props.commentObj.commentId,
            content: newContent,
          };

          await axios.put('/comment', updateData);

          props.commentObj.content = newContent; // Update the content locally
          alert('댓글이 수정되었습니다.');
        } catch (error) {
          console.error('Failed to update comment:', error);
          alert('댓글 수정에 실패했습니다.');
        }
      }
    };

    const deleteComment = async () => {
      if (confirm('댓글을 삭제하시겠습니까?')) {
        try {
          await axios.delete('/comment', {
            data: { commentId: props.commentObj.commentId },
          });

          emit('comment-deleted', props.commentObj.commentId); // Emit an event to notify parent about the deletion
          alert('댓글이 삭제되었습니다.');
        } catch (error) {
          console.error('Failed to delete comment:', error);
          alert('댓글 삭제에 실패했습니다.');
        }
      }
    };

    return {
      editComment,
      deleteComment,
    };
  },
});
</script>

<style scoped>
.comment-list-item {
  display: flex;
  justify-content: space-between;
  padding-bottom: 1em;
}

.comment-list-item-name {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 0.5px solid black;
  padding: 1em;
}

.comment-list-item-context {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50em;
  border: 0.5px solid black;
}

.comment-list-item-button {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 0.5px solid black;
  padding: 1em;
}
</style>