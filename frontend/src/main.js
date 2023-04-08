import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import router from './router'
import store from './store'

import CoreuiVue from '@coreui/vue'
import CIcon from '@coreui/icons-vue'
import { iconsSet as icons } from '@/assets/icons'
import DocsExample from '@/components/DocsExample'

axios.defaults.baseURL = 'http://3.34.40.128:8086'
const app = createApp(App)
app.config.globalProperties.$axios = axios
app.use(store)
app.use(router)
app.use(CoreuiVue)

app.provide('icons', icons)
app.component('CIcon', CIcon)
app.component('DocsExample', DocsExample)

app.mount('#app')
