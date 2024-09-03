<template>
  <div>
    <div :key="item.commentId" v-for="item in this.comments">
      <CommentListItem :commentObj="item" @comment-deleted="removeComment"></CommentListItem>
    </div>
    <CommentCreate
        :postId="postId"
        :reloadComment="reloadComment"/>
  </div>
</template>

<script>
import CommentListItem from "./CommentListItem.vue";
import CommentCreate from "./CommentCreate.vue";
import axios from "axios";

export default {
  name: "CommentList",
  props: {
    postId: Number
  },
  data() {
    return {
      comments: []
    };
  },
  components: {
    CommentListItem,
    CommentCreate
  },
  methods: {
    async reloadComment() {
      try {
        console.log(this.postId);
        const response = await axios.get(
            `/comment/${this.postId}`
        );

        this.comments = response.data;

      } catch (error) {
        console.error("데이터를 불러오는 중 에러가 발생했습니다:", error);
      }
    },
    removeComment(commentId) {
      this.comments = this.comments.filter(comment => comment.commentId !== commentId);
    }
  },
  created() {
    this.reloadComment(); // 컴포넌트가 생성될 때 데이터를 불러옴
  },
};
</script>

<style>
</style>