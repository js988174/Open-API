<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Your Vue.js App"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  },
  data() {
    return {
      dataList: "",
    };
  },
  mounted() {
    this.get();
  },
  methods: {
    get() {
      console.log("GET");
      this.$axios.get('http://localhost:8086/api/test').then((response) => {
        this.dataList = response.data;
        console.log(this.dataList);
      }).catch(function(err){
            console.log("error: ", err);
          });
    },
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
