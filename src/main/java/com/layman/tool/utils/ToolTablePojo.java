package com.layman.tool.utils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ToolTablePojo {
    private Integer code;
    private String msg;
    private Long count;
    private Object data;

    public ToolTablePojo(Integer code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
