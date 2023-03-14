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
      <tbody>
        <tr>
          <th class="no" scope="row">1</th>
          <td class="name">이름</td>
          <td class="title">제목</td>
          <td class="date">날짜</td>
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
  methods: {
    goWriter() {
      this.$router.push('./boardWrite')
    },
  },
}
</script>

<style scoped></style>
