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
          <td class="date">@날짜</td>
        </tr>
      </tbody>
      <!--]--><!---->
    </table>
    <!--]-->
  </div>
</template>

<script>
export default {
  name: 'Board',
}
</script>

<style scoped>
.board-list {
  width: 768px;
  margin: auto;
}

.board-detail {
  width: 768px;
  margin: auto;
  text-align: left;
}

.board-contents {
  padding: 12px 8px;
  border-bottom: 1px solid #eee;
}

.common-buttons {
  padding: 8px;
  text-align: right;
}
</style>
