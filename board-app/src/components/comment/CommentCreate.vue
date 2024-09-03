<template>
  <div class="comment-create">
    <b-input-group :prepend="writer" class="mt-3">
      <b-form-textarea
          id="textarea"
          v-model="content"
          :placeholder="'코멘트를 달아주세요~!'"
          rows="3"
          max-rows="6"
      ></b-form-textarea>
      <b-input-group-append>
        <b-button variant="info" @click="createComment">작성하기</b-button>
      </b-input-group-append>
    </b-input-group>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: "CommentCreate",
  props: {
    postId: Number,
    reloadComment: Function
  },
  data() {
    return {
      writer: "인성",
      content: ""
    };
  },
  methods: {
    async createComment() {
      try {
        const commentData = {
          postId: this.postId,
          content: this.content,
          writer: this.writer,
        };
        console.log(commentData);

        await axios.post(`/comment`, commentData);

        this.reloadComment();
        this.content = "";
      } catch (error) {
        console.error("실행 중 에러가 발생했습니다:", error);
      }
    },
  }
};
</script>

<style scoped>
.comment-create {
  display: flex;
  margin-bottom: 1em;
}
</style>