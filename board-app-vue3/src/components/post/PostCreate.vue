<template>
  <div>
    <b-form-input v-model="title" placeholder="제목을 입력해주세요."></b-form-input>
    <b-form-input v-model="password" placeholder="패스워드 입력해주세요"></b-form-input>
    <b-form-textarea
        v-model="content"
        max-rows="20"
        placeholder="내용을 입력해 주세요"
        rows="10"
    ></b-form-textarea>
    <br/>
    <b-button :disabled="isFormInvalid" @click="updateMode ? updatePost() : savePost()">저장</b-button>&nbsp;
    <b-button @click="cancel">취소</b-button>
  </div>
</template>

<script lang="ts">
import {computed, defineComponent, onMounted, ref} from 'vue';
import {useRoute, useRouter} from 'vue-router';
import axios from 'axios';

export default defineComponent({
  name: 'PostCreate',
  setup() {
    const title = ref<string>('');
    const content = ref<string>('');
    const password = ref<string>('');
    const userId = ref<string>('gogo');
    const updateObject = ref<any>(null);

    const route = useRoute();
    const router = useRouter();
    const updateMode = ref<boolean>(Number(route.params.postId) > 0);

    const fetchPost = async () => {
      try {
        const postId = Number(route.params.postId);
        const response = await axios.get(`/post/${postId}`);
        updateObject.value = response.data;
        title.value = updateObject.value.title;
        content.value = updateObject.value.content;
        password.value = updateObject.value.password;
      } catch (error) {
        console.error('Error fetching post:', error);
      }
    };

    const savePost = async () => {
      try {
        const postData = {
          title: title.value,
          content: content.value,
          userId: userId.value,
          password: password.value,
        };

        await axios.post('/post', postData);

        router.push({path: `/`});

      } catch (error) {
        console.error('Error saving post:', error);
      }
    };

    const updatePost = async () => {
      try {
        const postId = Number(route.params.postId);
        const postData = {
          postId: postId,
          title: title.value,
          content: content.value,
          password: password.value,
        };
        await axios.put('/post', postData);
        router.push({ path: `/post/detail/${postId}` });
      } catch (error) {
        console.error('Error updating post:', error);
      }
    };

    const cancel = () => {
      router.push({path: '/'});
    };

    const isFormInvalid = computed(() => {
      return !title.value || !content.value || !password.value;
    });

    onMounted(() => {
      if (updateMode.value) {
        fetchPost();
      }
    });

    return {
      title,
      content,
      password,
      updateMode,
      savePost,
      updatePost,
      cancel,
      isFormInvalid,
    };
  },
});
</script>

<style scoped>
/* 여기에 스타일을 추가할 수 있습니다 */
</style>