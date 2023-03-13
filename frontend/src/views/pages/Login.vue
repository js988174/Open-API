<template>
  <div class="bg-light min-vh-100 d-flex flex-row align-items-center">
    <CContainer>
      <CRow class="justify-content-center">
        <CCol :md="8">
          <CCardGroup>
            <CCard class="p-4">
              <CCardBody>
                <CForm>
                  <h1>Login</h1>
                  <p class="text-medium-emphasis">Sign In to your account</p>
                  <CInputGroup class="mb-3">
                    <CInputGroupText>
                      <CIcon icon="cil-user" />
                    </CInputGroupText>
                    <CFormInput
                      v-model="id"
                      placeholder="Username"
                      autocomplete="username"
                    />
                  </CInputGroup>
                  <CInputGroup class="mb-4">
                    <CInputGroupText>
                      <CIcon icon="cil-lock-locked" />
                    </CInputGroupText>
                    <CFormInput
                      v-model="password"
                      type="password"
                      placeholder="Password"
                      autocomplete="current-password"
                    />
                  </CInputGroup>
                  <CRow>
                    <CCol :xs="6">
                      <CButton color="primary" class="px-4" @click="login">
                        Login
                      </CButton>
                    </CCol>
                  </CRow>
                </CForm>
              </CCardBody>
            </CCard>
            <CCard class="text-white bg-primary py-5" style="width: 44%">
              <CCardBody class="text-center">
                <div>
                  <h2>회원 가입</h2>
                  <p>다양한 API 테스트</p>
                  <CButton
                    @click="goRegister"
                    color="light"
                    variant="outline"
                    class="mt-3"
                  >
                    회원가입 하로 가기
                  </CButton>
                </div>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      id: '',
      password: '',
      loginInfo: '',
    }
  },
  mounted() {},
  methods: {
    login() {
      this.$axios
        .post('/api/member/login', {
          id: this.id,
          password: this.password,
        })
        .then((response) => {
          if (response.data.errorCode === 400) {
            alert(response.data.errorMessage)
          } else {
            this.$axios.defaults.headers.common[
              'X-AUTH-TOKEN'
            ] = ` ${response.data.token}`
            alert('로그인이 완료되었습니다. 메인 화면으로 돌아갑니다')
            this.myInfo()
            this.$router.push('/dashboard')
          }
        })
    },
    myInfo() {
      this.$axios.post('/api/member/loginInfo').then((response) => {
        this.loginInfo = response.data
        console.log(this.loginInfo)
      })
    },
    goRegister() {
      this.$router.push('/pages/register')
    },
  },
}
</script>
