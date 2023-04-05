<template>
  <div>
    <h1>채팅</h1>
    <div>
      <div v-for="message in messages" :key="message.id">
        <strong>{{ message.username }}:</strong> {{ message.text }}
      </div>
    </div>
    <input
      v-model="message"
      @keyup.enter="send"
      placeholder="메시지를 입력하세요"
    />
  </div>
</template>

<script>
export default {
  name: 'Webrtc',
  data() {
    return {
      messages: [],
      message: '',
      username: '',
      ws: null,
    }
  },
  mounted() {
    this.username = prompt('닉네임을 입력하세요:')
    this.ws = new WebSocket('ws://localhost:8086/chat')
    this.ws.onmessage = (event) => {
      this.messages.push(JSON.parse(event.data))
    }
  },
  methods: {
    send() {
      if (this.message !== '') {
        const message = {
          username: this.username,
          text: this.message,
        }
        this.ws.send(JSON.stringify(message))
        this.message = ''
      }
    },
  },
}
</script>

<style scoped></style>
