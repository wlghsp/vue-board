import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    posts: [],
    comments: {}, // 각 게시물 ID별로 댓글을 저장
  },
  mutations: {
    addPost(state, post) {
      state.posts.push(post);
    },
    updatePost(state, updatedPost) {
      const index = state.posts.findIndex(post => post.id === updatedPost.id);
      if (index !== -1) {
        Vue.set(state.posts, index, updatedPost);
      }
    },
    deletePost(state, postId) {
      state.posts = state.posts.filter(post => post.id !== postId);
    },
    addComment(state, { postId, comment }) {
      if (!state.comments[postId]) {
        Vue.set(state.comments, postId, []);
      }
      state.comments[postId].push(comment);
    },
  },
  actions: {
    addPost({ commit }, post) {
      commit('addPost', post);
    },
    updatePost({ commit }, updatedPost) {
      commit('updatePost', updatedPost);
    },
    deletePost({ commit }, postId) {
      commit('deletePost', postId);
    },
    addComment({ commit }, payload) {
      commit('addComment', payload);
    },
  },
  getters: {
    getComments: (state) => (postId) => {
      return state.comments[postId] || [];
    },
  },
});