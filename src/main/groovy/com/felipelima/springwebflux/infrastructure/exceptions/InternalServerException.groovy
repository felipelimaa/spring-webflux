package com.felipelima.springwebflux.infrastructure.exceptions

import org.springframework.http.HttpStatus

class InternalServerException extends BusinessException {

    InternalServerException(String msg) {
        super(msg, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
