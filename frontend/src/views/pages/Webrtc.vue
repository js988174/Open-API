<template>
  <div id="app">
    유저이름:
    <input v-model="userName" type="text" />
    내용: <input v-model="message" type="text" @keyup="sendMessage" />
    <div v-for="(item, idx) in recvList" :key="idx">
      <h3>유저이름: {{ item.userName }}</h3>
      <h3>내용: {{ item.content }}</h3>
    </div>
  </div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
  name: 'Webrtc',
  data() {
    return {
      userName: '',
      message: '',
      recvList: [],
    }
  },
  created() {
    this.connect()
  },
  mounted() {
    // prompt('닉네임을 입력하ss세요:')
    // this.ws = new WebSocket('ws://localhost:8086/chat')
    // this.ws.onmessage = (event) => {
    //   this.messages.push(JSON.parse(event.data))
    // }
  },
  methods: {
    sendMessage(e) {
      if (e.keyCode === 13 && this.userName !== '' && this.message !== '') {
        this.send()
        this.message = ''
      }
    },
    send() {
      console.log('Send message:' + this.message)
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          userName: this.userName,
          content: this.message,
        }
        this.stompClient.send('/chat', JSON.stringify(msg), {})
      }
    },
    connect() {
      const serverURL = 'http://3.34.40.128:8086'
      let socket = new SockJS(serverURL)
      this.stompClient = Stomp.over(socket)
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          this.connected = true
          console.log('소켓 연결 성공', frame)
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe('/topic', (res) => {
            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            this.recvList.push(JSON.parse(res.body))
          })
        },
        (error) => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error)
          this.connected = false
        },
      )
    },
  },
}
</script>

<style scoped></style>
