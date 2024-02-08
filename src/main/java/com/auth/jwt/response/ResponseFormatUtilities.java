package com.auth.jwt.response;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.http.HttpStatus;

public class ResponseFormatUtilities {

    public static BasicResponse<?> createSuccessResponse(BasicResponse<?> basicResponse, String messageCode, String messageDescription) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessageCode(messageCode);
        successResponse.setMessageDescription(messageDescription);
        basicResponse.setMessage(successResponse);
        basicResponse.setStatus(Boolean.TRUE);
        basicResponse.setStatusCode(HttpStatus.OK.value());
        return basicResponse;
    }

    public static BasicResponse<?> createErrorResponse(BasicResponse<?> basicResponse, String errorCode, String errorMessage) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setErrorMessage(errorMessage);
        basicResponse.setError(errorResponse);
        basicResponse.setStatus(Boolean.FALSE);
        basicResponse.setStatusCode(HttpStatus.OK.value());
        return basicResponse;
    }

    public static BasicResponse<T> setData(BasicResponse<T> basicResponse, T data){
        basicResponse.setData(data);
        return basicResponse;
    }

}



