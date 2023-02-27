<template>
  <div>
    <CAlert color="primary">{{ covidUpdateTime }}</CAlert>
    <CRow>
      <CCol :xs="4">
        <CWidgetStatsC
          class="mb-3"
          :value="covidKorea.totalCnt"
          inverse
          color="info"
          :progress="{ value: 100 }"
          title="국내 확진자"
        >
          <template #icon><CIcon icon="cil-people" height="36" /></template>
        </CWidgetStatsC>
      </CCol>
      <CCol :xs="4">
        <CWidgetStatsC
          class="mb-3"
          :value="covidKorea.incDec"
          inverse
          color="success"
          :progress="{ value: 75 }"
          title="당일 확진자"
        >
          <template #icon
            ><CIcon icon="cil-user-follow" height="36"
          /></template>
        </CWidgetStatsC>
      </CCol>
      <CCol :xs="4">
        <CWidgetStatsC
          class="mb-3"
          :value="covidKorea.incDecF"
          inverse
          color="info"
          :progress="{ value: 5 }"
          title="전일 대비 증가"
        >
          <template #icon
            ><CIcon icon="cil-user-follow" height="36"
          /></template>
        </CWidgetStatsC>
      </CCol>
    </CRow>
    <CCol :md="6" class="mb-4">
      <CCard>
        <CCardHeader>Doughnut Chart</CCardHeader>
        <CCardBody> <CChartDoughnut :data="defaultData" /></CCardBody>
      </CCard>
    </CCol>
  </div>
</template>

<script>
import { CChartDoughnut } from '@coreui/vue-chartjs'
export default {
  name: '지역별 확진자 수',
  components: { CChartDoughnut },
  computed: {
    defaultData() {
      return {
        labels: ['부산', '대구', '강원', '경기', '인천', '서울', '울산'],
        datasets: [
          {
            backgroundColor: [
              '#41B883',
              '#E46651',
              '#00D8FF',
              '#DD1B16',
              '#0076A3',
              '#FFA500',
              '#8B00FF',
            ],
            data: [
              this.busan,
              this.daegu,
              this.gangwon,
              this.gyeonggi,
              this.incheon,
              this.seoul,
              this.ulsan,
            ],
          },
        ],
      }
    },
  },
  mounted() {
    this.covidList()
  },
  data() {
    return {
      covidDataList: [],
      covidKorea: [],
      covidUpdateTime: String,
      busan: '',
      daegu: '',
      gangwon: '',
      gyeonggi: '',
      incheon: '',
      seoul: '',
      ulsan: '',
    }
  },
  methods: {
    covidList() {
      this.$axios.get('/api/covid').then((response) => {
        this.covidDataList = response.data
        this.covidUpdateTime = response.data.API.updateTime
        this.covidKorea = response.data.korea

        // 지역별 확진자 수
        this.busan = response.data.busan.incDec
        this.daegu = response.data.daegu.incDec
        this.gangwon = response.data.gangwon.incDec
        this.gyeonggi = response.data.gyeonggi.incDec
        this.incheon = response.data.incheon.incDec
        this.seoul = response.data.seoul.incDec
        this.ulsan = response.data.ulsan.incDec

        console.log('updateTIme', this.covidKorea)
        console.log('covid19', this.covidDataList)
      })
    },
  },
}
</script>

<style></style>
