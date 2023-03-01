<template>
  <div>
    <CFormInput
      v-model="keyword"
      type="text"
      placeholder="Default input"
      aria-label="default input example"
    />
    <br />
    <CButton @click="clickSearch()" color="success">검색</CButton>
    <CRow>
      <CCard style="width: 18rem" v-for="item in movieDataList" :key="item">
        <CCardImage
          orientation="top"
          :src="item.image"
          width="200"
          height="200"
        />
        <CCardBody>
          <CCardTitle>{{ item.title }}</CCardTitle>
          <CCardText> {{ item.pubDate }} </CCardText>
        </CCardBody>
      </CCard>
    </CRow>
  </div>
</template>

<script>
export default {
  name: 'Movie',
  data() {
    return {
      movieDataList: [],
      keyword: '',
    }
  },
  mounted() {},
  methods: {
    movieList(query) {
      this.$axios.get('/api/movie?keyword=' + query).then((response) => {
        this.movieDataList = response.data.items

        console.log(response.data)
      })
    },
    clickSearch() {
      let query = ''
      query = this.keyword
      console.log('query', query)
      this.movieList(query)
    },
  },
}
</script>

<style></style>
