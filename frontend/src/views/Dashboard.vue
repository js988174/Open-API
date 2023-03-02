<template>
  <div></div>
</template>

<script>
import avatar1 from '@/assets/images/avatars/1.jpg'

export default {
  name: 'Dashboard',
  components: {},
  setup() {
    const progressGroupExample1 = [{ title: 'Monday', value1: 34, value2: 78 }]
    const progressGroupExample2 = [
      { title: 'Male', icon: 'cil-user', value: 53 },
    ]
    const progressGroupExample3 = [
      {
        title: 'Organic Search',
        icon: 'cib-google',
        percent: 56,
        value: '191,235',
      },
      { title: 'Facebook', icon: 'cib-facebook', percent: 15, value: '51,223' },
    ]
    const tableExample = [
      {
        avatar: { src: avatar1, status: 'success' },
        user: {
          name: 'Yiorgos Avraamu',
          new: true,
          registered: 'Jan 1, 2021',
        },
        country: { name: 'USA', flag: 'cif-us' },
        usage: {
          value: 50,
          period: 'Jun 11, 2021 - Jul 10, 2021',
          color: 'success',
        },
        payment: { name: 'Mastercard', icon: 'cib-cc-mastercard' },
        activity: '10 sec ago',
      },
    ]

    return {
      tableExample,
      progressGroupExample1,
      progressGroupExample2,
      progressGroupExample3,
    }
  },
}
</script>
