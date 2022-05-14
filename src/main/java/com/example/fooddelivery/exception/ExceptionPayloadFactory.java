package com.example.fooddelivery.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionPayloadFactory {

    TECHNICAL_ERROR(0, HttpStatus.INTERNAL_SERVER_ERROR, "technical.error"),
    INVALID_PAYLOAD(1, HttpStatus.BAD_REQUEST, "invalid.request.payload"),

    // Email
    EMAIL_ALREADY_EXIST(5, HttpStatus.FOUND, "email.already.found"),

    // Student :
    STUDENT_NOT_FOUND(5, HttpStatus.NOT_FOUND, "student.not.found");



    private final Integer code;
    private final HttpStatus status;
    private final String message;

    public ExceptionPayload get() {
        return new ExceptionPayload(code, status, message);
    }
}
