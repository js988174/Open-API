package com.project.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardListResult<T,T2> {
    private T totalCount;
    private T2 result;

}
