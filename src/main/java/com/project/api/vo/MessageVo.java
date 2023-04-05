package com.project.api.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class MessageVo {
        @JsonProperty("content")
        private String content;

        @JsonProperty("sender")
        private String sender;
}