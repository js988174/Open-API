<template>
  <div>
    <CFormInput
      v-model="keyword"
      type="text"
      placeholder="Default input"
      aria-label="default input example"
    />
    <br />
    <CButton @click="clickSearch(keyword)" color="success">검색</CButton>
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
  mounted() {
    this.movieList()
  },
  methods: {
    movieList(query) {
      this.$axios.get('/api/movie?keyword=' + query).then((response) => {
        this.movieDataList = response.data

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
