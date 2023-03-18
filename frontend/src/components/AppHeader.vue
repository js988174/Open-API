<template>
  <CHeader position="sticky" class="mb-4">
    <CContainer fluid>
      <CHeaderToggler class="ps-1" @click="$store.commit('toggleSidebar')">
        <CIcon icon="cil-menu" size="lg" />
      </CHeaderToggler>
      <CHeaderBrand class="mx-auto d-lg-none" to="/">
        <CIcon :icon="logo" height="48" alt="Logo" />
      </CHeaderBrand>
      <CHeaderNav class="d-none d-md-flex me-auto">
        <CNavItem>
          <CNavLink href="/dashboard"> Dashboard </CNavLink>
        </CNavItem>
        <CNavItem>
          <CNavLink href="#">{{ loginInfo }} 반갑습니다.</CNavLink>
        </CNavItem>
      </CHeaderNav>
      <CHeaderNav> </CHeaderNav>
    </CContainer>
    <CHeaderDivider />
    <CContainer fluid>
      <AppBreadcrumb />
    </CContainer>
  </CHeader>
</template>

<script>
import AppBreadcrumb from './AppBreadcrumb'
import { logo } from '@/assets/brand/logo'
export default {
  name: 'AppHeader',
  components: {
    AppBreadcrumb,
  },
  data() {
    return {
      loginInfo: '',
    }
  },
  setup() {
    return {
      logo,
    }
  },
  mounted() {
    this.myInfo()
  },
  methods: {
    myInfo() {
      this.$axios.get('/api/member/loginInfo').then((response) => {
        this.loginInfo = response.data.result.id
        console.log(this.loginInfo.username)
      })
    },
  },
}
</script>
