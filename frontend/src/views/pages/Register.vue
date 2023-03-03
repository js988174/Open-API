<template>
  <div class="bg-light min-vh-100 d-flex flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol :md="9" :lg="7" :xl="6">
          <CCard class="mx-4">
            <CCardBody class="p-4">
              <CForm>
                <h1>회원가입</h1>
                <p class="text-medium-emphasis">Create your account</p>
                <CInputGroup class="mb-3">
                  <CInputGroupText>@</CInputGroupText>
                  <CFormInput placeholder="id" autocomplete="id" v-model="id" />
                </CInputGroup>
                <CInputGroup class="mb-3">
                  <CInputGroupText>
                    <CIcon icon="cil-lock-locked" />
                  </CInputGroupText>
                  <CFormInput
                    v-model="password"
                    type="password"
                    placeholder="Password"
                    autocomplete="new-password"
                  />
                </CInputGroup>
                <CInputGroup class="mb-3">
                  <CInputGroupText>
                    <CIcon icon="cil-user" />
                  </CInputGroupText>
                  <CFormInput
                    placeholder="name"
                    autocomplete="name"
                    v-model="name"
                  />
                </CInputGroup>
                <div class="d-grid">
                  <CButton @click="createMember" color="success"
                    >회원 가입</CButton
                  >
                </div>
              </CForm>
            </CCardBody>
          </CCard>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data() {
    return {
      id: '',
      password: '',
      name: '',
    }
  },
  mounted() {},
  methods: {
    createMember() {
      this.$axios
        .post('/api/member/create', {
          id: this.id,
          password: this.password,
          name: this.name,
        })
        .then((response) => {
          if (response.data.errorCode === 400) {
            alert(response.data.errorMessage)
          } else {
            alert('회원가입이 완료되었습니다. 로그인 화면으로 돌아갑니다')
            this.$router.push('./login')
          }
        })
    },
  },
}
</script>
