package com.project.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListResult<T,T2> {
    private T totalCount;
    private T2 result;

}
