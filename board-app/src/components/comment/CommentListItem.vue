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

<script>
import CommentCreate from "./CommentCreate.vue";
import axios from "axios";

export default {
  name: "CommentListItem",
  props: {
    commentObj: Object,
    reloadComment: Function
  },
  components: {
    CommentCreate
  },
  data() {
    return {

    };
  },
  methods: {
    async editComment() {
      // You might want to show a modal or redirect to a new page for editing
      const newContent = prompt("댓글을 수정하세요:", this.commentObj.content);
      if (newContent !== null) {
        try {
          let updateData = {
            commentId: this.commentObj.commentId,
            content: newContent
          };
          const response = await axios.put('http://localhost:8080/comment', updateData);

          this.commentObj.content = newContent;  // Update the content locally
          alert("댓글이 수정되었습니다.");
        } catch (error) {
          console.error("Failed to update comment:", error);
          alert("댓글 수정에 실패했습니다.");
        }
      }
    },
    async deleteComment() {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        try {
          let deleteData = {
            data: { commentId: this.commentObj.commentId }
          };
          const response = await axios.delete('http://localhost:8080/comment', deleteData);

          this.$emit('comment-deleted', this.commentObj.commentId);  // Emit an event to notify parent about the deletion
          alert("댓글이 삭제되었습니다.");
        } catch (error) {
          console.error("Failed to delete comment:", error);
          alert("댓글 삭제에 실패했습니다.");
        }
      }
    }
  }
};
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