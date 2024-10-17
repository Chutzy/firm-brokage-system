package com.example.hubs.infra;

import com.example.hubs.constants.ResultCode;
import com.example.hubs.exception.CustomException;
import com.example.hubs.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@AllArgsConstructor
@Slf4j
@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public @ResponseBody
    ResponseEntity<BaseResponse> handleCustomException(final CustomException e) {
        return handleException(prepareBaseResponse(e.getMessage()));

    }

    private BaseResponse prepareBaseResponse(String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(ResultCode.ERROR.getCode());
        baseResponse.setResultMessage(message);
        return baseResponse;
    }

    private ResponseEntity<BaseResponse> handleException(BaseResponse baseResponse) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(baseResponse);
    }
}
