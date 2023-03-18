<template>
  <div>
    <CForm>
      <CFormInput
        type="title"
        v-model="title"
        id="exampleFormControlInput1"
        label="제목"
        placeholder="제목을 입력해주세요."
        text="30글자 제한"
        aria-describedby="exampleFormControlInputHelpInline"
      />
    </CForm>
    <CForm>
      <CFormTextarea
        id="content"
        v-model="content"
        label="내용을 입력해주세요."
        rows="3"
        text="100글자 제한"
      ></CFormTextarea>
    </CForm>
    <CButton color="primary" variant="outline" @click="writeBoard"
      >입력하기</CButton
    >
  </div>
</template>

<script>
export default {
  name: 'BoardWrite',
  data() {
    return {
      title: '',
      content: '',
    }
  },
  mounted() {},
  methods: {
    writeBoard() {
      this.$axios
        .post('/api/board/write', {
          title: this.title,
          content: this.content,
        })
        .then((response) => {
          if (response.data.errorCode === 400) {
            alert(response.data.errorMessage)
          } else {
            alert('글 작성 완료')
            this.$router.push('/board')
          }
        })
    },
  },
}
</script>

<style></style>
