package com.project.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;



public class MessageVo {
        @JsonProperty("userName")
        private String userName;

        @JsonProperty("content")
        private String content;


        public void setUserName(String userName) {
                System.out.println("유저테임 세터");
                this.userName = userName;
        }

        public void setContent(String content) {
                System.out.println("컨텐츠 세터");
                this.content = content;
        }

        public String getUserName() {
                return userName;
        }

        public String getContent() {
                return content;
        }

        public MessageVo() {
                System.out.println("생성");
        }
}