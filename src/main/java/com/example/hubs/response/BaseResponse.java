package com.example.hubs.response;

import com.example.hubs.constants.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse {

    private String resultCode;
    private String resultMessage;

    public BaseResponse() {
        this.resultCode = ResultCode.SUCCESS.getCode();
        this.resultMessage = ResultCode.SUCCESS.getValue();
    }
}
