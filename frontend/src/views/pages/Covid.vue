<template>
  <div>
    <CFormTextarea
      placeholder="{{this.covidDataList.API.updateTime}}"
      aria-label="Readonly textarea example"
      disabled
      readonly
    ></CFormTextarea>
    <CRow>
      <CCol :xs="4">
        <CWidgetStatsC
          class="mb-3"
          value="87.500"
          inverse
          color="info"
          :progress="{ value: 75 }"
          title="Visitors"
        >
          <template #icon><CIcon icon="cil-people" height="36" /></template>
        </CWidgetStatsC>
      </CCol>
      <CCol :xs="4">
        <CWidgetStatsC
          class="mb-3"
          value="385"
          inverse
          color="success"
          :progress="{ value: 75 }"
          title="New clients"
        >
          <template #icon
            ><CIcon icon="cil-user-follow" height="36"
          /></template>
        </CWidgetStatsC>
      </CCol>
      <CCol :xs="4">
        <CWidgetStatsC
          class="mb-3"
          value="1238"
          inverse
          color="info"
          :progress="{ value: 75 }"
          title="Product sold"
        >
          <template #icon><CIcon icon="cil-basket" height="36" /></template>
        </CWidgetStatsC>
      </CCol>
    </CRow>
  </div>
</template>

<script>
export default {
  name: 'Covid',
  mounted() {
    this.covidList()
  },
  data() {
    return {
      covidDataList: [],
    }
  },
  methods: {
    covidList() {
      this.$axios.get('/api/covid').then((response) => {
        this.covidDataList = response.data
        console.log('covid19', this.covidDataList)
      })
    },
  },
}
</script>

<style></style>
