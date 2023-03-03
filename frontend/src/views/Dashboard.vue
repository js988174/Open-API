<template>
  <div>
    <CRow>
      <CCol :xs="4">
        <CWidgetStatsF
          @click="goCovid"
          color="primary"
          title="코로나 페이지 바로가기"
        >
          <template #icon>
            <CIcon icon="cil-settings" size="xl" />
          </template>
        </CWidgetStatsF>
      </CCol>
      <CCol :xs="4">
        <CWidgetStatsF color="info" title="Title" value="$1.999,50">
          <template #icon>
            <CIcon icon="cil-settings" size="xl" />
          </template>
          <template #footer>
            <CLink
              class="font-weight-bold font-xs text-medium-emphasis"
              href="https://coreui.io/"
              rel="noopener norefferer"
              target="_blank"
            >
              View more
              <CIcon icon="cil-arrow-right" class="ms-auto" width="16" />
            </CLink>
          </template>
        </CWidgetStatsF>
      </CCol>
      <CCol :xs="4">
        <CWidgetStatsF color="warning" title="Title" value="$1.999,50">
          <template #icon>
            <CIcon icon="cil-settings" size="xl" />
          </template>
          <template #footer>
            <CLink
              class="font-weight-bold font-xs text-medium-emphasis"
              href="https://coreui.io/"
              rel="noopener norefferer"
              target="_blank"
            >
              View more
              <CIcon icon="cil-arrow-right" class="ms-auto" width="16" />
            </CLink>
          </template>
        </CWidgetStatsF>
      </CCol>
    </CRow>
  </div>
</template>

<script>
export default {
  name: 'Dashboard',
  components: {},
  data() {
    return {
      id: '',
      password: '',
      name: '',
    }
  },
  mounted() {},
  methods: {
    goCovid() {
      this.$router.push('./pages/covid')
    },
    goMovie() {
      this.$router.push('./pages/movie')
    },
  },
}
</script>
