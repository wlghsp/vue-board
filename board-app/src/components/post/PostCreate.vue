<template>
  <div>
    <b-input v-model="title" placeholder="제목을 입력해주세요."></b-input>
    <b-input v-model="password" placeholder="패스워드 입력해주세요"></b-input>
    <b-form-textarea
        v-model="content"
        placeholder="내용을 입력해 주세요"
        rows="3"
        max-rows="6"
    ></b-form-textarea>
    <br>
    <b-button @click="updateMode ? updatePost() : savePost()">저장</b-button>&nbsp;
    <b-button @click="cancel">취소</b-button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PostCreate',
  data() {
    return {
      title: '',
      content: '',
      password: '',
      userId: 'gogo',
      updateObject: null,
      updateMode: this.$route.params.postId > 0,
    }
  },
  created() {
    if (this.updateMode) {
      this.fetchPost();
    }
  },
  methods: {
    async fetchPost() {
      try {
        const postId = Number(this.$route.params.postId);
        const response = await axios.get(`/post/${postId}`);
        this.updateObject = response.data;
        this.title = this.updateObject.title;
        this.content = this.updateObject.content;
        this.password = this.updateObject.password;
      } catch (error) {
        console.error('Error fetching post:', error);
      }
    },
    async savePost() { // Save new post
      try {
        const postData = {
          title: this.title,
          content: this.content,
          userId: this.userId,
          password: this.password
        };

        const response = await axios.post('/post', postData);
        const postId = response.data.postId; // Assume the backend returns the new postId
        this.$router.push({ path: `/post/detail/${postId}` });
      } catch (error) {
        console.error('Error saving post:', error);
      }
    },
    async updatePost() { // Update existing post
      try {
        let postId = this.$route.params.postId;
        const postData = {
          postId: postId,
          title: this.title,
          content: this.content,
          password: this.password
        };
        await axios.put('/post', postData);
        this.$router.push({ path: `/post/detail/${postId}` });
      } catch (error) {
        console.error('Error updating post:', error);
      }
    },
    cancel() {
      this.$router.push({
        path: '/'
      })
    }
  }
}
</script>

<style>

</style>