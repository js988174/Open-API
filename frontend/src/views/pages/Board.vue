<template>
  <div class="docs-example border rounded-top p-4">
    <table class="table table-striped">
      <thead>
        <tr>
          <th class="no" scope="col">#</th>
          <th class="name" scope="col">이름</th>
          <th class="title" scope="col">제목</th>
          <th class="date" scope="col">날짜</th>
        </tr>
      </thead>
      <tbody v-for="item in boardDataList" v-bind:key="item">
        <tr>
          <th class="no" scope="row">{{ item.boardNo }}</th>
          <td class="name">{{ item.writer }}</td>
          <td class="title">{{ item.title }}</td>
          <td class="date">{{ item.regDate }}</td>
        </tr>
      </tbody>
    </table>
    <CButton color="primary" variant="outline" @click="goWriter"
      >글 쓰기</CButton
    >
  </div>
</template>

<script>
export default {
  name: 'Board',
  data() {
    return {
      boardDataList: [],
    }
  },
  mounted() {
    this.boardList()
  },
  methods: {
    goWriter() {
      this.$router.push('./boardWrite')
    },
    boardList() {
      this.$axios.get('/api/board/list').then((response) => {
        if (response.data.errorCode === 400) {
          alert(response.data.errorMessage)
        } else {
          this.boardDataList = response.data.result
          console.log(this.boardDataList)
        }
      })
    },
  },
}
</script>

<style scoped></style>
