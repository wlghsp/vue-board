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

<script lang="ts">
import { defineComponent, ref } from 'vue';
import axios from 'axios';

export default defineComponent({
  name: 'CommentCreate',
  props: {
    postId: {
      type: Number,
      required: true,
    },
    reloadComment: {
      type: Function,
      required: true,
    },
  },
  setup(props) {
    const writer = ref<string>('인성');
    const content = ref<string>('');

    const createComment = async () => {
      try {
        const commentData = {
          postId: props.postId,
          content: content.value,
          writer: writer.value,
        };

        await axios.post(`/comment`, commentData);

        props.reloadComment();
        content.value = '';
      } catch (error) {
        console.error('실행 중 에러가 발생했습니다:', error);
      }
    };

    return {
      writer,
      content,
      createComment,
    };
  },
});
</script>

<style scoped>
.comment-create {
  display: flex;
  margin-bottom: 1em;
}
</style>