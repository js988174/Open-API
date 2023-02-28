<template>
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
        <template #icon><CIcon icon="cil-user-follow" height="36" /></template>
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
        <template #icon><CIcon icon="cil-user-follow" height="36" /></template>
      </CWidgetStatsC>
    </CCol>
  </CRow>
  <CRow>
    <CCol :md="6" class="mb-4">
      <CCard>
        <CCardHeader>지역별 확진자 수</CCardHeader>
        <CCardBody> <CChartDoughnut :data="defaultData" /></CCardBody>
      </CCard>
    </CCol>
    <CCol :md="6" class="mb-4">
      <CCard>
        <CCardHeader> 예방 1차 접종 현황 </CCardHeader>
        <CCardBody>
          <CChartDoughnut :data="defaultVaccineData" />
        </CCardBody>
      </CCard>
    </CCol>
  </CRow>
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
    defaultVaccineData() {
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
              this.busanVaccine,
              this.daeguVaccine,
              this.gangwonVaccine,
              this.gyeonggiVaccine,
              this.incheonVaccine,
              this.seoulVaccine,
              this.ulsanVaccine,
            ],
          },
        ],
      }
    },
  },
  mounted() {
    this.covidList()
    this.covidVaccineList()
  },
  data() {
    return {
      covidDataList: [],
      covidVaccineDataList: [],
      covidKorea: [],
      covidUpdateTime: String,
      busan: '',
      daegu: '',
      gangwon: '',
      gyeonggi: '',
      incheon: '',
      seoul: '',
      ulsan: '',
      busanVaccine: '',
      daeguVaccine: '',
      gangwonVaccine: '',
      gyeonggiVaccine: '',
      incheonVaccine: '',
      seoulVaccine: '',
      ulsanVaccine: '',
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
      })
    },
    covidVaccineList() {
      this.$axios.get('/api/covid/vaccine').then((response) => {
        this.covidVaccineDataList = response.data

        this.busanVaccine = response.data.busan.vaccine_1.vaccine_1
        this.daeguVaccine = response.data.daegu.vaccine_1.vaccine_1
        this.gangwonVaccine = response.data.gangwon.vaccine_1.vaccine_1
        this.gyeonggiVaccine = response.data.gyeonggi.vaccine_1.vaccine_1
        this.incheonVaccine = response.data.incheon.vaccine_1.vaccine_1
        this.seoulVaccine = response.data.seoul.vaccine_1.vaccine_1
        this.ulsanVaccine = response.data.ulsan.vaccine_1.vaccine_1
      })
    },
  },
}
</script>

<style></style>
