<template>
  <div>
    <CForm>
      <CFormInput
        type="title"
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
        label="내용을 입력해주세요."
        rows="3"
        text="100글자 제한"
      ></CFormTextarea>
    </CForm>
    <CButton color="primary" variant="outline">입력하기</CButton>
  </div>
</template>

<script>
export default {
  name: 'BoardWrite',
}
</script>

<style></style>
