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
            <CIcon :icon="cibAddthis" size="xl" />
          </template>
        </CWidgetStatsF>
      </CCol>
      <CCol :xs="4">
        <CWidgetStatsF
          @click="goMovie"
          color="info"
          title="영화 페이지 바로가기"
        >
          <template #icon>
            <CIcon :icon="cibMonzo" size="xl" />
          </template>
        </CWidgetStatsF>
      </CCol>
      <CCol :xs="4">
        <CWidgetStatsF
          @click="goWeather"
          color="warning"
          title="날씨 페이지 바로가기"
        >
          <template #icon>
            <CIcon :icon="cilFlower" size="xl" />
          </template>
        </CWidgetStatsF>
      </CCol>
    </CRow>
  </div>
</template>

<script>
import { CIcon } from '@coreui/icons-vue'
import { cibAddthis, cibMonzo, cilFlower } from '@coreui/icons'

export default {
  name: 'Dashboard',
  components: {
    CIcon,
  },
  setup() {
    return {
      cibAddthis,
      cibMonzo,
      cilFlower,
    }
  },
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
    goWeather() {
      this.$router.push('./pages/weather')
    },
  },
}
</script>
