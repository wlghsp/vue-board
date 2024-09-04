<template>
  <div>
    <div id="grid"></div>
    <b-button style="margin-right: 10px;" @click="writeContent">글쓰기</b-button>
    <b-button variant="danger" @click="deleteSelectedPosts">삭제</b-button>
  </div>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import Grid from 'tui-grid';
import 'tui-grid/dist/tui-grid.css';
import {useRouter} from 'vue-router';
import 'tui-pagination/dist/tui-pagination.css';
import axios from "axios";

export default defineComponent({
  name: 'PostList',
  setup() {
    const router = useRouter();
    const currentPage = ref(1); // 현재 페이지 상태를 저장하는 ref
    const perPage = ref(10); // 페이지당 보여줄 데이터 수
    let gridInstance;

    const dataSource = {
      api: {
        readData: {
          url: 'http://localhost:8080/posts',
          method: 'GET',
          initParams: {
            page: currentPage.value,
            perPage: perPage.value,
          },
        },
      },
    };

    const writeContent = () => {
      router.push({
        path: '/post/create',
      });
    };
    const deleteSelectedPosts = async () => {
      const checkedRows = gridInstance?.getCheckedRows();
      if (!checkedRows || checkedRows.length === 0) {
        alert('삭제할 게시글을 체크해주세요.');
        return;
      }

      if (confirm('게시글을 삭제하시겠습니까?')) {
        try {
          await axios.delete('/posts', {data: {
              'postIds': checkedRows.map((row) => row.postId)
            }});

          alert('게시글이 삭제되었습니다.');

          // 삭제 후 그리드 데이터 갱신
          gridInstance?.removeCheckedRows(); // 그리드에서 삭제된 행 제거 (API 호출 후 처리)
          updateGridPage();
        } catch (error) {
          console.error('Failed to delete post:', error);
          alert('게시글 삭제에 실패했습니다.');
        }
      }
    }

    const updateGridPage = () => {
      if (gridInstance) {
        gridInstance.reloadData();
      }
    };

    onMounted(() => {
      gridInstance = new Grid({
        el: document.getElementById('grid')!,
        data: dataSource,
        rowHeaders: ['checkbox'],
        scrollX: false,
        scrollY: true,
        columns: [
          {header: '번호', name: 'postId', sortingType: 'desc', sortable: true},
          {header: '제목', name: 'title', sortingType: 'desc', sortable: true},
          {header: '글쓴이', name: 'userId', sortingType: 'desc', sortable: true},
          {header: '댓글 수', name: 'commentCount', sortingType: 'desc', sortable: true},
        ],
        pageOptions: {
          useClient: false,
          perPage: perPage.value,
        },
      });

      gridInstance.on('click', (ev) => {

        const {rowKey, columnName} = ev;
        const rowData = gridInstance.getRow(rowKey);

        if (columnName === 'title' && rowData) {
          router.push({
            path: `/post/detail/${rowData.postId}`,
          });
        }
      });


    });


    return {
      writeContent,
      deleteSelectedPosts
    };
  },
});
</script>

<style scoped>
#grid {
  margin-bottom: 20px;
}
</style>